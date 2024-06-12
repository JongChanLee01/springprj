package com.city.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45)
    private String name;

    private Integer population;
    private Integer area;

    @ManyToOne
    @JoinColumn(name = "districtId")
    private District district;
}
