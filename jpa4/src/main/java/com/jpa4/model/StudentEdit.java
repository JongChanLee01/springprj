package com.jpa4.model;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StudentEdit {
    int id;

    @NotBlank @NotEmpty
    @Size(min = 8, max = 12)
    String studentNo;

    @NotEmpty @NotBlank
    @Size(min=2, max=20)
    String name;

    @NotEmpty @NotBlank
    @Pattern(regexp="010-[0-9]{3,4}-[0-9]{4}",
        message = "휴대폰 번호를 입력하세요 ex)010-000-0000")
    String phone;

    @NotEmpty @Email
    String email;

    @NotEmpty @NotBlank
    @Pattern(regexp="남|여", message = "남, 여 중 하나를 입력하세요.")
    String sex;

    int departmentId;

}
