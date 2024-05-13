package com.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor // 디폴트 생성자 추가
@Getter
@SequenceGenerator(name = "a_seq", sequenceName = "article_seq", allocationSize = 1,initialValue = 1)
public class Article {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "a_seq")
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

    public void patch(Article article){
        // 저장할때는 null이 아니면 저장하게끔 해야함
        if(article.title != null) this.title = article.title;
        if(article.content != null) this.content = article.content;
    }
}
