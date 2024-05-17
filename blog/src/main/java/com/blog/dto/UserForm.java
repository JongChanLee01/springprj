package com.blog.dto;

import com.blog.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@AllArgsConstructor
@NoArgsConstructor
@Data
@DynamicInsert
public class UserForm {
    private String username;
    private String password;
    private String email;
    private String role;

    // public User toEntity() {
    //     return User(null, username, password, email, null, null);
    // }
}
