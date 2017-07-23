package com.show.car.repository;

import com.show.car.AbstractUnitTest;
import com.show.car.domain.mongo.Car;
import com.show.car.repository.mongo.CarRepository;
import com.show.car.util.DataGenerator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by mmarzougui on 07/03/2017.
 */
@DataJpaTest
@Import(DataGenerator.class)
public class CarRepositoryTest extends AbstractUnitTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private DataGenerator dataGenerator ;

    @Autowired
    private CarRepository repository;

    @Test
    public void FindOneByIdShouldReturnCar() {
        this.testEntityManager.persist(dataGenerator.createCar());
//        Optional<Car> car = this.repository.findOneById(1);
//        assertThat(car.isPresent()).isTrue();
//        assertThat(car.get().getName()).isEqualTo("car for test") ;
//        assertThat(car.get().getOrigin()).isEqualTo("USA");
    }

    @Test
    public void FindOneByIdWhenNoCarShouldReturnNull() {
//        Optional<Car> car = this.repository.findOneById(1000l);
//        assertThat(car.isPresent()).isFalse();
    }

}
