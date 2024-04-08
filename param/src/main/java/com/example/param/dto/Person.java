package com.example.param.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@ToString

public class Person {
    String name;
    double weight;
    Date birthday;

    public Person() {

    }

    public Person(String name, double weight, Date birthday) {
        this.name = name;
        this.weight = weight;
        this.birthday = birthday;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
