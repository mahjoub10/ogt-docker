package com.show.car.rest;

import com.show.car.dto.CarResultDTO;
import com.show.car.dto.SearchParam;
import com.show.car.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/car")
@RestController
public class SearchResource {

    private Logger log = LoggerFactory.getLogger(SearchResource.class);

    private SearchService searchService ;

    public SearchResource(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/search")
    public ResponseEntity<?> SearchCars(@RequestBody SearchParam params){

        log.info("Request to Search for cars");
        List<CarResultDTO> result = searchService.searchCars(params);
        return  ResponseEntity.ok(result);
    }

    @GetMapping("/search/name")
    public  ResponseEntity<?> searchByName(@RequestParam("name") String name){
        log.info("Request to find car with name : '{}'",name);

        List<CarResultDTO> result = searchService.findByName(name);
        return  ResponseEntity.ok(result);
    }

    @GetMapping("/search/recent")
    public  ResponseEntity<?> getMostRecentCars(){
        log.info("Request to get the most recent cars");
        List<CarResultDTO> cars = searchService.getMostRecentCars();
        return  ResponseEntity.ok(cars);
    }
 }
