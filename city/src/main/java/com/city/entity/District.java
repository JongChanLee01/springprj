package com.city.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45)
    private String districtName;

    private Integer population;
    private Integer area;
}
