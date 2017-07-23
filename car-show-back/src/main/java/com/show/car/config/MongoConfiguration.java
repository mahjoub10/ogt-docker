package com.show.car.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.show.car.repository.mongo")
public class MongoConfiguration {


    @Bean
    public GridFsTemplate gridFsTemplate (MongoDbFactory dbFactory, MongoConverter converter) {
        return  new GridFsTemplate(dbFactory,converter,"photos");

    }
}
