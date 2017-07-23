package com.show.car.mapper;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.show.car.domain.elastic.IndexedCar;
import com.show.car.domain.jpa.User;
import com.show.car.domain.mongo.Car;
import com.show.car.dto.CarDetailsDTO;
import com.show.car.dto.CarResultDTO;
import com.show.car.dto.NewCarDTO;
import com.show.car.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Date;

/**
 * Custom mapper fro DTO and domain .
 * <p>
 * This mapper uses  mapstruct : <a>http://mapstruct.org/</a>
 * <p>
 * Created by mmarzougui on 08/04/2017.
 */
@Mapper(componentModel = "spring")
public abstract class CarMapper {


    @Mapping(source = "name", target = "name")
    public  abstract NewCarDTO fromCarTOCarDTO(Car car);

    @Mapping(source = "name", target = "name")
    public  abstract Car fromCarDtoTOCar(NewCarDTO car);

    @Mapping(source = "name", target = "name")
    public abstract IndexedCar fromCarToIndexedCar(NewCarDTO car);

    @Mapping(source = "name", target = "name")
    public abstract CarResultDTO fromIndexedCarToCarResultDTO(IndexedCar car);

    @Mapping(source = "name", target = "name")
    public abstract CarDetailsDTO fromCarToCarDetailsDTO(Car car);

    @Mapping(source = "login", target = "login")
    public  abstract UserDTO fromUserToUserDTO(User user);

    @Mapping(source = "login", target = "login")
    public  abstract User fromUserDtoToUser(UserDTO user);

    public DBObject createMetaData(String referenceCar, String id){
        DBObject metadata = new BasicDBObject();
        metadata.put("creationDate", new Date());
        metadata.put("id", id);
        metadata.put("referenceCar",referenceCar);
        return  metadata ;
    }
}
