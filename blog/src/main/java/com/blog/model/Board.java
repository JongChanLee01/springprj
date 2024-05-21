package com.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Data //Getter Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity // User 클래스가 MySQL에 테이블이 생성
public class Board {
    @Id // primary key
    @GeneratedValue(strategy= GenerationType.IDENTITY) // 프로젝트에서 연결된
    private int id; // 시퀀스 , auto_increment

    @Column(nullable=false,length=100)
    private String title;

    @Lob // 대용량 데이터
    @Column(columnDefinition = "longblob")
    private String content; // 섬머노트 라이브러리 <html>태그

    @ColumnDefault("0")
    private int count; //조회수

    @ManyToOne // Many=Board, User=One , 연관관계를 설정
    @JoinColumn(name="userId") // DB에는 userId가 들어감
    private User user; // DB는 오브젝트를 저장할 수 없음. FK, 자바는 오브젝트를 저장할 수 있음
    // 자바와 데이터베이스가 충돌남


    // 관계주인 -> board
    // 연관주인 -> reply
    //@JoinColumn(name="replyId")
    // @OneToMany(mappedBy="board", fetch=FetchType.EAGER)
    // mappedBy 연관관계의 주인이 아니다(난 FK가아니다) DB에 컬럼을 만들지않는다.
    // 나는 그냥 Board를 select 할때 join문을 통해 값을 얻기위한 것이다.
    // board는 reply에 있는 board를 적어줌.  기본값은 LAZY
    // board select user와 reply정보를 다 들고와야 함
    // private List<Reply> reply;
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"board"})
    @OrderBy("id desc")
    @ToString.Exclude
    private List<Reply> replies;


    @CreationTimestamp
    private Timestamp createDate;
}