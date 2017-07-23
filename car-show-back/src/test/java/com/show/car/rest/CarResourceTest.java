package com.show.car.rest;

import com.show.car.domain.mongo.Car;
import com.show.car.dto.NewCarDTO;
import com.show.car.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by mmarzougui on 07/03/2017.
 */
@WebMvcTest(CarResource.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class CarResourceTest   {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarService carService;

    @Test
    public void testGetCarListShouldReturnListOfCars() throws Exception {

//        List<NewCarDTO> cars = Collections.
//                singleton(createCar())
//                .stream().map(car -> new NewCarDTO())
//                .collect(Collectors.toList());
//
//        given(this.carService.getCars()).willReturn(cars);
//        this.mvc.perform(get("/car/").accept(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk());
    }

    @Test
    public void testCarDetailShouldReturnCar() throws Exception {
//        given(this.carService.getCarDetail(anyLong())).willReturn(createCar());
//        this.mvc.perform(get("/car/1").accept(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk());
    }

    private Car createCar() {
        Car car = new Car();
        car.setName("car for test");
        car.setMiles(17);

        car.setCylinders(7);
        car.setDisplacement(200);
        car.setHorsepower(120);
        car.setWeight(4000);
        car.setAcceleration(22);
        car.setYear(1980);
        car.setOrigin("USA");
        return car;
    }
}
