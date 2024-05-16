package com.board.dto;

import com.board.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Data
@NoArgsConstructor
public class BoardDto {
    // 폼데이터를 받아올 그릇이다.
    private Long id;
    private String title;
    private String content;
    private String username;

    public BoardEntity toEntity() {
        return new BoardEntity(id, title, content, username);
    }
}
