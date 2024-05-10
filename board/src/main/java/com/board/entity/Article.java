package com.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor // 디폴트 생성자 추가
public class Article {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    // public Article(Long id, String title, String content) {
    //     this.id = id;
    //     this.title = title;
    //     this.content = content;
    // }
    //
    // @Override
    // public String toString() {
    //     return "Article{" +
    //             "id=" + id +
    //             ", title='" + title + '\'' +
    //             ", content='" + content + '\'' +
    //             '}';
    // }
}
