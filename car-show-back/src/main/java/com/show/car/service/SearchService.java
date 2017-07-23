package com.show.car.service;

import com.show.car.domain.elastic.IndexedCar;
import com.show.car.dto.CarDetailsDTO;
import com.show.car.dto.CarResultDTO;
import com.show.car.dto.SearchParam;
import com.show.car.mapper.CarMapper;
import com.show.car.repository.elastic.IndexedCarRepository;
import com.show.car.repository.mongo.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service groups all search operations.
 */
@Service
public class SearchService {

    private Logger log = LoggerFactory.getLogger(SearchService.class);

    private ElasticsearchTemplate elasticsearchTemplate;

    private CarMapper mapper;

    private SearchQueryBuilder searchQueryBuilder;

    private IndexedCarRepository indexedCarRepository ;

    public SearchService(ElasticsearchTemplate elasticsearchTemplate, CarMapper mapper, SearchQueryBuilder searchQueryBuilder,
                         IndexedCarRepository indexedCarRepository) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.mapper = mapper;
        this.searchQueryBuilder = searchQueryBuilder;
        this.indexedCarRepository = indexedCarRepository;
    }

    public List<CarResultDTO> searchCars(SearchParam params) {

        log.info("Searching : ", params.toString());

        SearchQuery globalQuery = searchQueryBuilder.buildQuery(params);

        List<IndexedCar> result = elasticsearchTemplate.queryForList(globalQuery, IndexedCar.class);

        return result.stream().map(car -> mapper.fromIndexedCarToCarResultDTO(car)).collect(Collectors.toList());

    }



    public List<CarResultDTO> findByName(String name) {

        log.info("Searching car with name : '{}'", name);

        SearchQuery globalQuery = searchQueryBuilder.buildSearchQueryWithName(name) ;

        List<IndexedCar> result = elasticsearchTemplate.queryForList(globalQuery, IndexedCar.class);

        return result.stream().map(car -> mapper.fromIndexedCarToCarResultDTO(car)).collect(Collectors.toList());
    }


    public List<CarResultDTO> getMostRecentCars() {

        log.info("Getting most recent cars");

        SearchQuery globalQuery = searchQueryBuilder.buildMostRecentCarQuery() ;

        List<IndexedCar> result = elasticsearchTemplate.queryForList(globalQuery, IndexedCar.class);

        return result.stream().map(car -> mapper.fromIndexedCarToCarResultDTO(car)).collect(Collectors.toList());
    }
}
