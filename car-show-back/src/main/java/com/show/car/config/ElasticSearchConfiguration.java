package com.show.car.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories("com.show.car.repository.elastic")
public class ElasticSearchConfiguration {
}
