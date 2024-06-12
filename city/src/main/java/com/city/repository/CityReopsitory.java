package com.city.repository;

import com.city.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityReopsitory extends JpaRepository<City, Integer> {
    List<City> findAll();
    City findByName(String name);

    // District엔티티로 접근하는 방법은 -> DistrictDistrictName
    // District에 DistrictName으로 접근한다는 뜻
    Page<City> findByNameStartsWithOrDistrictDistrictNameStartsWith(
            String name, String districtDistrictName,Pageable pageable
    );
}
