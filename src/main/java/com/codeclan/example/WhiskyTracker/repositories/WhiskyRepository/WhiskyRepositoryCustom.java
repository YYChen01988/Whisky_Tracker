package com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;

import java.util.List;

public interface WhiskyRepositoryCustom {
    List<Whisky> getWhiskiesFromCertainYear(int age);

    List<Whisky> getWhiskiesFromCertainRegion(String region);

}
