package com.mybatis4.dto;

import lombok.Data;

@Data
public class Book {
    int id;
    String title;
    String author;
    int categoryId;
    int price;
    String publisher;


    String bookCategory; // bookCategory
}


