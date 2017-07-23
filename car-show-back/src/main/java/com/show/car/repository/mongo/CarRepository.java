package com.show.car.repository.mongo;

import com.show.car.domain.mongo.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Created by mmarzougui on 23/02/2017.
 */
public interface CarRepository extends MongoRepository<Car, String> {

    Optional<Car> findOneByReference(String reference) ;
}
