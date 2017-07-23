package com.show.car.service;

import com.show.car.AbstractUnitTest;
import com.show.car.domain.mongo.Car;
import com.show.car.dto.NewCarDTO;
import com.show.car.repository.mongo.CarRepository;
import com.show.car.util.DataGenerator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;

/**
 * Created by mmarzougui on 07/03/2017.
 */
public class CarServiceTest extends AbstractUnitTest  {

    @MockBean
    private CarRepository carRepository ;

    @Autowired
    private CarService carService ;

    @Autowired
    private DataGenerator dataGenerator ;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void  testGetCarsShouldReturnListOfCars(){

//        given(carRepository.findAll()).willReturn(Collections.singletonList(dataGenerator.createCar()));
//        List<NewCarDTO>  result = carService.getCars();
//        assertThat(result.isEmpty()).isFalse();
//        assertThat(result.size()).isEqualTo(1);
//        assertThat(result.stream().findFirst().get().getName()).isEqualTo("car for test") ;
    }

    @Test
    public void  testGetCarsWhenRuntimeExceptionShouldThrowException(){

//        given(carRepository.findAll()).willThrow(new RuntimeException("Unknown database car"));
//        this.thrown.expect(RuntimeException.class);
//        this.thrown.expectMessage("Unknown database car");
//        carService.getCars();
    }

    @Test
    public void testGetCarDetailShouldReturnCarDetail() throws Exception {
//        given(carRepository.findOneById(anyLong())).willReturn(Optional.of(dataGenerator.createCar()));
//        Car result = carService.getCarDetail(1L);
//        assertThat(result).isNotNull();
//        assertThat(result.getName()).isEqualTo("car for test");

    }

    @Test
    public void testGetCarDetailWhenNoCarShouldThrowException() throws Exception {
//        given(carRepository.findOneById(anyLong())).willReturn(Optional.empty());
//        this.thrown.expect(Exception.class);
//        this.thrown.expectMessage("No car with id : 1 has been found");
//        carService.getCarDetail(1L);


    }
}
