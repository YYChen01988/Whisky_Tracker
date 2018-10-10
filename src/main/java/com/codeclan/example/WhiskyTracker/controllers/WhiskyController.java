package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/whiskies")
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;


    @GetMapping(value = "/year/{year}")
    public List<Whisky> findAllWhiskiesInCertainYear(@PathVariable int year){
        return whiskyRepository.getWhiskiesFromCertainYear(year);
    }

    @GetMapping(value = "/region/{region}")
    public List<Whisky> findAllWhiskiesInCertainRegion(@PathVariable String region){
        //List<Distillery> distilleries = distilleryRepository.getDistilleryByRegion(region);
        return whiskyRepository.getWhiskiesFromCertainRegion(region);
    }

}
