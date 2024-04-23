package com.spring4.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    int id;
    String title;
    String author;
    int categoryId;
    String categoryName;
    int price;
    String publisher;
}
