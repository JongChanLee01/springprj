package com.city.repository;

import com.city.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityReopsitory extends JpaRepository<City, Integer> {
    List<City> findAll();
    City findByName(String name);

    Page<City> findByNameStartsWith(
            String name,  Pageable pageable
    );
}
