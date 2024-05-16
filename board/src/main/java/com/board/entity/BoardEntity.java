package com.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
@SequenceGenerator(name = "a_seq", sequenceName = "article_seq", allocationSize = 1,initialValue = 1)
public class BoardEntity {
    @Id  // 대표값을 지정  like a 주민등록번호
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "a_seq")  // 1, 2, 3, 자동생성 어노테이션
    private Long id;

    @Column
    private String title;

    //@Lob
    @Column
    private String content;

    //    @ManyToOne         // 해당 댓글 엔티티 여러개가, 하나의 Article에 연관된다!
    //    @JoinColumn(name = "username")
    //    private User user;
    @Column
    private String username;
}