package com.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Timestamp;
import java.util.List;

// ORM -> Java Object를 테이블로 맵핑
@Entity  // User 클래스가 MySQL에 테이블이 생성
@Data  //Getter Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder  // 빌더 패턴
@DynamicInsert // insert시 null 인 필드를 제외
public class User {
    @Id  // primary key
    @GeneratedValue(strategy= GenerationType.IDENTITY) // 프로젝트에서 연결된
    private int id; // 시퀀스 , auto_increment

    @Column(nullable=false,length=30, unique = true) // unique = true 개발자 입장의 중복검사 동일한 아이디가 들어올 수 없다.
    private String username; // 아이디

    @Column(nullable=false,length=100) // 123456=>해쉬(비밀번호 암호화)
    private String password;  //

    @Column(nullable=false,length=50)
    private String email;

    // DB는 RoleType이라는 게 없다.
    // @ColumnDefault("'user'") // 그래서 처음에는 일단 이거로 시작
    // private String role;
    @Enumerated(EnumType.STRING) // 나중에 이거로 바꿈
    private RoleType role;  // Enum을 쓰는게 좋다. admin, user, maneger

    @CreationTimestamp  // 시간이 자동 입력
    private Timestamp createDate;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"user"})
    @ToString.Exclude
    @OrderBy("id desc")
    private List<Board> board;
}