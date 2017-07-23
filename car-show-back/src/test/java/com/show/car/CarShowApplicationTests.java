package com.show.car;

import com.show.car.domain.mongo.Car;
import com.show.car.service.CarService;
import com.show.car.util.DataGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CarShowApplicationTests {


    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DataGenerator dataGenerator ;

    @MockBean
    private CarService carService;

    @Before
    public void setup() throws Exception {
//        given(this.carService.getCarDetail(1l))
//                .willReturn(dataGenerator.createCar());
    }

    @Test
    public void testGetCarDetailShouldReturnCar() {
        ResponseEntity<Car> response = this.restTemplate
                .getForEntity("/car/{id}", Car.class, 1l);
        assertThat(response.getBody().getName()).isEqualTo("car for test");
    }




}
