package com.shop2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class MemberFormDto {
    // 아래처럼 어노테이션으로 메세지를 날리려면
    // validation을 pom파일에 dependency 시켜야함
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 4, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
    private String password;

    // @NotEmpty(message = "주소는 필수 입력 값입니다.")
    // private String address;


    @NotEmpty(message = "우편번호는 필수 입력 값입니다.")
    private String zipcode;

    @NotEmpty(message = "도로명주소는 필수 입력 값입니다.")
    private String streetaddr;

    @NotEmpty(message = "상세주소를 입력해주세요.")
    private String detailaddr;

    private String role;
}