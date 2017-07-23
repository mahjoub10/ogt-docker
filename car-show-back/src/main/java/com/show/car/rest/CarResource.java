package com.show.car.rest;

import com.show.car.dto.CarDetailsDTO;
import com.show.car.dto.NewCarDTO;
import com.show.car.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by mmarzougui on 23/02/2017.
 */
@RequestMapping("/api/car")
@RestController
public class CarResource {

    private static Logger log = LoggerFactory.getLogger(CarResource.class) ;

    @Autowired
    private CarService carService ;



    @RequestMapping(value = "/upload",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void upload(@RequestPart("car") NewCarDTO car, @RequestPart("media") List<MultipartFile> media) throws  Exception{

        log.info("Request to upload new car");
        carService.upload(car, media);
    }

    /**
     * Get the content of file in http response.
     *
     * @param name
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/file/{name:.+}",method = RequestMethod.GET)
    public void getFileContent(@PathVariable("name")String name, HttpServletResponse response) throws Exception{
        log.info("Request to get file content with  name: '{}'",name);
        carService.getFileContent(name,response);

    }

    /**
     * Get finest details of selected car.
     *
     * @param reference
     * @return
     * @throws Exception
     */
    @GetMapping("/details/{reference:.+}")
    public ResponseEntity<CarDetailsDTO> getCarDetails(@PathVariable("reference") String reference) throws  Exception{

        log.info("Request to get car details : '{}'",reference);
        CarDetailsDTO result = carService.getCarWithDetails(reference);
        return  ResponseEntity.ok(result) ;
    }
}
