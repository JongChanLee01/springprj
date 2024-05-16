package com.board.dto;

import com.board.entity.UserEntity;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor // 기본 생성자를 만들어주는 것
@ToString
@Getter
@Setter
public class UserForm {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
    private Timestamp createDate;

    public UserEntity toEntity() {
        return new UserEntity(id, username, password, email, role, createDate);
    }
}