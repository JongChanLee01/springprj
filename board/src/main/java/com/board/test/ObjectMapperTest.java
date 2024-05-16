package com.board.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ObjectMapperTest {
    public static void main(String[] args) throws JsonProcessingException {
        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = new ObjectMapper();

        // Java 객체 생성
        Person person = new Person("John Doe", 30);

        // Java 객체를 JSON 문자열로 변환
        String jsonString = objectMapper.writeValueAsString(person);
        System.out.println(jsonString);


        //JSOn 문자열 생성
        // String json = "{\"name\":\"Mike Lee\",\"age\":30}";
        ObjectNode jsonString2 = objectMapper.createObjectNode();
        jsonString2.put("name","맥도날드");
        jsonString2.put("age",50);

        String json=jsonString2.toString();


        // JSON 문자열을 Java 객체로 변환
        Person person2 = objectMapper.readValue(json, Person.class);
        System.out.println("Name: " + person2.getName());
        System.out.println("Age: " + person2.getAge());
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Person {
    private String name;
    private int age;

    // public Person(String name, int age) {
    //     this.name = name;
    //     this.age = age;
    // }
}
