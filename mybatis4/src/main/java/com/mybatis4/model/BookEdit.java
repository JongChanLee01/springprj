package com.mybatis4.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookEdit {
    int id;

    @NotEmpty @NotBlank
    String title;

    @NotEmpty @NotBlank
    String author;

    @NotNull
    int categoryId;

    @NotNull
    int price;

    @NotEmpty @NotBlank
    String publisher;

    @NotEmpty @NotBlank
    String bookCategory; // bookCategory
}
