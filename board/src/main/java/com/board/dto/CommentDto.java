package com.board.dto;

import com.board.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;

    @JsonProperty("article_id")
    private Long articleId;

    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment c) {
        return new CommentDto(c.getId(), c.getArticle().getId(), c.getNickname(), c.getBody());
    }
}