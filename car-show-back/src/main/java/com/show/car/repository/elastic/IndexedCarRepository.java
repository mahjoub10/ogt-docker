package com.show.car.repository.elastic;

import com.show.car.domain.elastic.IndexedCar;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IndexedCarRepository extends ElasticsearchRepository<IndexedCar, String> {
}
