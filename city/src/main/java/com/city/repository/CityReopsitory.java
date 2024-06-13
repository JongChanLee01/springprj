package com.city.repository;

import com.city.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityReopsitory extends JpaRepository<City, Integer> {
    List<City> findAll();
    City findByName(String name);

    // District엔티티로 접근하는 방법은 -> DistrictDistrictName
    // District에 DistrictName으로 접근한다는 뜻
    Page<City> findByNameStartsWithOrDistrictDistrictNameStartsWith(
            String name, String districtDistrictName,Pageable pageable
    );

    // 위에를 아래 쿼리로 풀어써서 해도됨
    // like :name은 파라미터 String name 여기 name
    @Query(value = "select c.id,c.name, c.area,c.population, c.district_id " +
            "from city c join district on c.district_id = district.id where c.name like :name% " +
            "or district.district_name like :districtName%", nativeQuery = true)
    Page<City> 도시명과지역명검색(@Param("name") String name, @Param("districtName") String districtName, Pageable pageable);

}
