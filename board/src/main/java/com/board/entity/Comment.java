package com.board.entity;

import com.board.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne       // 해당 댓글 엔티티 여러개가, 하나의 Article에 연관된다!
    @JoinColumn(name = "article_id")
    private Article article;  // 댓글의 부모 게시글


    @Column
    private String nickname;


    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        return new Comment(dto.getId(), article, dto.getNickname(), dto.getBody());
    }
}
