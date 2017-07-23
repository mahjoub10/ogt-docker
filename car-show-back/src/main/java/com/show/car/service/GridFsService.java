package com.show.car.service;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.show.car.domain.mongo.Car;
import com.show.car.dto.NewCarDTO;
import com.show.car.mapper.CarMapper;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.bson.types.ObjectId;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service responsible to uploading media file to mongo.
 */
@Service
public class GridFsService {

    private Logger log = LoggerFactory.getLogger(GridFsService.class);

    private static String IMAGE_TYPE = "image";

    private static String FILE_CONTENT_ENDPOINT = "/api/car/file/";

    private GridFsTemplate gridFsTemplate;

    private IndexationService indexationService ;

    private CarMapper carMapper ;

    private MongoTemplate mongoTemplate ;

    public GridFsService(GridFsTemplate gridFsTemplate, IndexationService indexationService, CarMapper carMapper, MongoTemplate mongoTemplate) {
        this.gridFsTemplate = gridFsTemplate;
        this.indexationService = indexationService;
        this.carMapper = carMapper;
        this.mongoTemplate = mongoTemplate;
    }

    //@Async("carAsyncExecutor")
    public void processMedia(String referenceCar, List<MultipartFile> medias, NewCarDTO car) throws Exception {

        log.info("Uploading '{}' files to mongo", medias.size());

        String idMiniature = createMiniature(medias, referenceCar);

        String thumb = createFileUrl(idMiniature);

        List<String> photos = processFiles(medias, referenceCar);

        List<String> photosUrl = photos.stream().map(ph -> createFileUrl(ph)).collect(Collectors.toList());

        updateCarWithFilesUrl(referenceCar,thumb, photosUrl);

        indexationService.indexNewCar(referenceCar, thumb,car);

    }


    public void getFile(String fileId, HttpServletResponse response) throws  Exception{

        //Find the target file using it's hashed id. if no file found we send back a blank response.
        GridFSDBFile fsFile = findFileByName(fileId);
        if (fsFile == null) {
            log.warn("No file found for id : '{}'", fileId);
            return;
        }

        /**
         * transform file to byte and use http response to write it back to user.
         the user will be able to see the file directly in the browser .
         */
        generateHttpResponseWithFileContent(response, fsFile);

    }


    private void updateCarWithFilesUrl(String reference, String thumb, List<String> photos){

        Criteria criteria = Criteria.where("reference").is(reference);
        Update update = new Update().set("thumb",thumb).set("photos", photos);
        Query query = new Query(criteria);
        mongoTemplate.updateMulti(query, update, Car.class);

    }

    //find file using it's name
    private GridFSDBFile findFileByName(String name) {
        //search file using the unique id stored in metadata
        Criteria criteria = Criteria.where("filename").is(name);
        Query query = new Query(criteria);
        GridFSDBFile fsFile = gridFsTemplate.findOne(query);
        return fsFile;
    }

    private String createFileUrl(String id){

        return String.format("%s%s",FILE_CONTENT_ENDPOINT,id);
    }

    //stream file content as out put stream
    private void generateHttpResponseWithFileContent(HttpServletResponse response, GridFSDBFile fsFile) throws IOException {
        //The content type of the file
        response.setContentType(fsFile.getContentType());

        // the length
        response.setContentLengthLong(fsFile.getLength());

        //The inputstream from mongo
        InputStream inputStream = fsFile.getInputStream();

        //response out put stream will be used to write the file content.
        OutputStream out = response.getOutputStream();
        byte[] buf = new byte[1024];
        int count = 0;
        while ((count = inputStream.read(buf)) >= 0) {
            out.write(buf, 0, count);
        }
        out.close();
        inputStream.close();
    }

    private String createMiniature(List<MultipartFile> medias, String referenceCar) throws Exception {

        MultipartFile firstImage = medias.stream().filter(file -> isImage(file.getContentType())).findFirst().orElse(null);
        String thumb = null;

        if (firstImage != null) {
            String contentType = firstImage.getContentType();
            InputStream resizedImageInputStream = generateMiniature(firstImage.getInputStream(), firstImage.getContentType());
            thumb = storeFile(firstImage.getOriginalFilename(), resizedImageInputStream, contentType, referenceCar);
        }
        return thumb;
    }

    private List<String> processFiles(List<MultipartFile> medias, String referenceCar) throws Exception {

        List<String> photos = new ArrayList<>();

        for (MultipartFile file : medias) {
            //get input stream from attachment
            InputStream inputStream = file.getInputStream();

            //get content type
            String contentType = file.getContentType();

            String name = storeFile(file.getOriginalFilename(), inputStream, contentType, referenceCar);
            photos.add(name);
        }

       return  photos ;
    }


    /**
     * check if the current file is image
     *
     * @param contentType
     * @return
     */
    private boolean isImage(String contentType) {
        return contentType != null && !contentType.isEmpty() && contentType.substring(0, 5).equals(IMAGE_TYPE);
    }


    private String storeFile(String filename, InputStream inputStream, String contentType, String referenceCar) {

        //create custom meta data in addition to default information provided by Gridfs.
        String id = ObjectId.get().toString();
        String uniqueName = generateUniqueFileName(FilenameUtils.getExtension(filename));
        DBObject metadata = carMapper.createMetaData(referenceCar, id);
        //store the file and it's metadata
        gridFsTemplate.store(inputStream, uniqueName, contentType, metadata);
        return uniqueName;
    }

    /**
     * resize image the original image.
     *
     * @param inputStream
     * @param contentType
     * @return
     * @throws Exception
     */
    private InputStream generateMiniature(InputStream inputStream, String contentType) throws Exception {
        BufferedImage bufferedImage = ImageIO.read(inputStream);
        String formatName = contentType.substring(contentType.lastIndexOf("/") + 1);
        BufferedImage resizedImage = Scalr.resize(bufferedImage, 200, 200);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, formatName, os);
        InputStream resizedInputStream = new ByteArrayInputStream(os.toByteArray());
        return resizedInputStream;
    }


    private String generateUniqueFileName(String extension) {
        String rndchars = RandomStringUtils.randomAlphanumeric(20);
        return  String.format("%s%s.%s",rndchars,System.currentTimeMillis(),extension);
    }


}
