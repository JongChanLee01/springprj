package com.sbb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) // fetch를 설정을 안하면 기본은 fetch = FetchType.LAZY
    // @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Answer> answerList;

    @ManyToOne
    private SiteUser author;

    // 수정 일시
    private LocalDateTime modifyDate;

    // 추천
    @ManyToMany
    Set<SiteUser> voter;

    // 뷰
    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;
}
