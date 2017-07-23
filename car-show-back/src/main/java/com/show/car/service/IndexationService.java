package com.show.car.service;

import com.show.car.domain.elastic.IndexedCar;
import com.show.car.domain.jpa.User;
import com.show.car.dto.NewCarDTO;
import com.show.car.mapper.CarMapper;
import com.show.car.repository.elastic.IndexedCarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class IndexationService {

    private Logger log = LoggerFactory.getLogger(IndexationService.class) ;

    private IndexedCarRepository indexedCarRepository ;

    private CarMapper carMapper ;

    private UserService userService ;

    public IndexationService(IndexedCarRepository indexedCarRepository, CarMapper carMapper, UserService userService) {
        this.indexedCarRepository = indexedCarRepository;
        this.carMapper = carMapper;
        this.userService = userService;
    }

    public  void indexNewCar(String referenceCar, String thumb, NewCarDTO newCarDTO) throws  Exception {

        log.info("Indexing new car ");

        User currentUser = userService.getCurrentUser();
        IndexedCar indexedCar = carMapper.fromCarToIndexedCar(newCarDTO);
        indexedCar.setThumb(thumb);
        indexedCar.setReference(referenceCar);
        indexedCar.setCreationDate(new Date());
        indexedCar.setCreatorFirstName(currentUser.getFirstName());
        indexedCar.setCreatorLastName(currentUser.getLastName());
        indexedCarRepository.save(indexedCar);


    }
}
