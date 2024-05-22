package com.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data //Getter Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity
public class Reply {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 시퀀스 , auto_increment

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne // Many ->Board, One -> Reply
    @JoinColumn(name = "boardId")
    private Board board;
    // 하나의 게시글에는 여러개의 답변이 있다.

    @ManyToOne // Many ->Board, One -> Reply
    @JoinColumn(name = "userId")
    private User user;
    // 한 사용자가 여러개의 답변을 만들 수 있다.

    @CreationTimestamp
    private Timestamp createDate;

    // DTO에서 넘어온 데이터를 엔티티(Model)에 수정
    public void update(User user, Board board, String content){
        setUser(user);
        setBoard(board);
        setContent(content);
    }
}