package com.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplySaveRequestDto {
    private int userId;
    private int boardId;
    private String content;
}
