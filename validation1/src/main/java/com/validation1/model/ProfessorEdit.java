package com.validation1.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ProfessorEdit {
    int id;

    @NotEmpty @NotBlank
    String name;

    @NotEmpty @NotBlank
    @Pattern(regexp = "010-[0-9]{3,4}-[0-9]{4}")
    String phone;

    @NotEmpty @Email
    String email;

    @NotEmpty @NotBlank
    String office;

    int departmentId;
}
