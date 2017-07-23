package com.show.car.service;

import com.show.car.dto.SearchParam;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * This service is used to accumulate all the params and build an elastic search query.
 */
@Service
public class SearchQueryBuilder {

    private Logger log = LoggerFactory.getLogger(SearchQueryBuilder.class);


    public SearchQuery buildQuery(SearchParam params){
        BoolQueryBuilder builder = boolQuery();

        //Check for brand criteria
        if(params.isBrandParamExists()){
            builder.must(matchQuery("brand",params.getBrand()));
        }

        //Check for model criteria
        if(params.isModelParamExists()){
            builder.must(matchQuery("model",params.getModel()));
        }

        //Check for miles criteria
        if(params.isNameParamExists()){
            builder.must(matchQuery("miles",params.getName()));
        }

        //Check for origin criteria
        if(params.isNameParamExists()){
            builder.must(matchQuery("origin",params.getOrigin()));
        }

        //Check for gearbox criteria
        if(params.isGearBoxExists()){
            builder.must(matchQuery("gearbox",params.getGearbox().toString()));
        }

        //Check for miles min  criteria
        if(params.isMilesMinParamExists() && !params.isMilesMaxParamExists()){
            builder.must(QueryBuilders.rangeQuery("miles").gte(params.getMilesMin()));
        }

        //Check for miles max  criteria
        if(!params.isMilesMinParamExists() && params.isMilesMaxParamExists()){
            builder.must(QueryBuilders.rangeQuery("miles").lte(params.getMilesMax()));
        }

        //Check for both miles max and min criteria
        if(!params.isMilesMinParamExists() && params.isMilesMaxParamExists()){
            builder.must(QueryBuilders.rangeQuery("miles")
                    .gte(params.getMilesMin())
                    .lte(params.getMilesMax()));
        }

        //Check for year min  criteria
        if(params.isYearMinParamExists() && !params.isYearMaxParamExists()){
            builder.must(QueryBuilders.rangeQuery("year").gte(params.getYearMin()));
        }

        //Check for miles max  criteria
        if(!params.isYearMinParamExists() && params.isYearMaxParamExists()){
            builder.must(QueryBuilders.rangeQuery("year").lte(params.getYearMax()));
        }

        //Check for both miles max and min criteria
        if(params.isYearMinParamExists() && params.isYearMaxParamExists()){
            builder.must(QueryBuilders.rangeQuery("year")
                    .gte(params.getYearMin())
                    .lte(params.getYearMax()));
        }

        SearchQuery globalQuery = new NativeSearchQueryBuilder()
                .withQuery(builder)
                .withPageable(new PageRequest(0, 100))
                .build();

        log.info("Request built: ",globalQuery.toString());

        return  globalQuery ;
    }

    public  SearchQuery buildSearchQueryWithName(String name){
        BoolQueryBuilder builder = boolQuery();
        String nameLower = name.toLowerCase() ;

        //Check if the descriptions contains the words.
        QueryBuilder descriptionQuery = wildcardQuery("name", "*" + nameLower + "*");

        //Check if the name contains the words.
        QueryBuilder nameQuery = wildcardQuery("name", "*" + nameLower + "*");

        //Check if the user has misspelled the name
        QueryBuilder fuzzyNameQuery = fuzzyQuery("name", nameLower).fuzziness(Fuzziness.AUTO);

        builder.should(descriptionQuery).should(nameQuery).should(fuzzyNameQuery);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(builder)
                .withPageable(new PageRequest(0, 20))
                .build();

        log.info("Request search by name was built: ",searchQuery.toString());

        return  searchQuery;
    }

    public  SearchQuery buildMostRecentCarQuery(){

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withPageable(new PageRequest(0, 20))
                .withSort(SortBuilders.fieldSort("creationDate").order(SortOrder.DESC).ignoreUnmapped(true))
                .build();

        log.info("Request search by name was built: ",searchQuery.toString());

        return  searchQuery;
    }
}
