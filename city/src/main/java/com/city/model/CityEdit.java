package com.city.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class CityEdit {
    int id;

    @NotEmpty @NotBlank
    @Size(min=2, max=20)
    String name;

    @Min(value=1, message="도를 선택하세요.")
    int districtId;

    @NotNull
    @Range(min=1, message = "인구를 선택하세요.")
    int population;

    @NotNull
    @Range(min=1, message = "면적을 선택하세요.")
    int area;
}
