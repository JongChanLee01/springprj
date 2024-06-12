package com.city.service;

import com.city.entity.District;
import com.city.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {
    @Autowired
    public DistrictRepository districtRepository;

    public List<District> findAll() {
        return districtRepository.findAll();
    }
}
