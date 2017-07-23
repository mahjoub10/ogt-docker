package com.show.car;

import com.show.car.config.CarProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableConfigurationProperties({ CarProperties.class})
@EnableAsync
public class CarShowApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarShowApplication.class, args);
	}
}
