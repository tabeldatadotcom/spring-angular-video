package com.training.region_api.controller;

import com.training.region_api.entity.City;
import com.training.region_api.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api-reg/city/v1")
public class CityController {
    @Autowired
    CityRepository cityRepository;

    @GetMapping("/list")
    public ResponseEntity<?> findAll(){
        List<City> list = cityRepository.findAll();
        log.info("Response City : {}", list);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestBody City data
    ){
        City city = cityRepository.save(data);
        log.info("Request Data City : {}", data);
        return new ResponseEntity<>(city, HttpStatus.CREATED);
    }

}
