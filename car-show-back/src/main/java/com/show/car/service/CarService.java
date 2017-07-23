package com.show.car.service;

import com.show.car.domain.jpa.User;
import com.show.car.domain.mongo.Car;
import com.show.car.dto.CarDetailsDTO;
import com.show.car.dto.NewCarDTO;
import com.show.car.mapper.CarMapper;
import com.show.car.repository.mongo.CarRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mmarzougui on 23/02/2017.
 */
@Service
public class CarService {

    private Logger log = LoggerFactory.getLogger(CarService.class);

   private CarRepository  repository ;

   private CarMapper mapper ;

   private GridFsService gridFsService ;

   private UserService userService ;

    private CarRepository carRepository;

    public CarService(CarRepository repository, CarMapper mapper, GridFsService gridFsService, UserService userService,
                      CarRepository carRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.gridFsService = gridFsService;
        this.userService = userService;
        this.carRepository = carRepository ;
    }



    public void upload(NewCarDTO car, List<MultipartFile> medias) throws  Exception{

        log.info("Uploading new car");
        User currentUser = userService.getCurrentUser();
        String reference = ObjectId.get().toString();
        Car newCar = mapper.fromCarDtoTOCar(car);
        newCar.setCreator(currentUser.getLogin());
        newCar.setCreatorEmail(currentUser.getEmail());
        newCar.setCreatorMobile(currentUser.getMobile());
        newCar.setCreatorFirstName(currentUser.getFirstName());
        newCar.setCreatorLastName(currentUser.getLastName());
        newCar.setReference(reference);
        newCar.setCreationDate(new Date());
        newCar.setModificationDate(new Date());
        repository.save(newCar) ;
        //asynchronous  save the media file
        gridFsService.processMedia(reference, medias,car);

    }

    public void getFileContent(String name, HttpServletResponse response) throws  Exception {

        log.info("Get the file content of media : '{}'",name);
        gridFsService.getFile(name, response) ;
    }

    public CarDetailsDTO getCarWithDetails(String reference) throws Exception {

        log.info("Getting car details for : '{}'", reference);

        return carRepository.findOneByReference(reference)
                .map(car -> mapper.fromCarToCarDetailsDTO(car))
                .orElseThrow(() -> new Exception("No car with reference : '" + reference + "'has been found"));

    }
}
