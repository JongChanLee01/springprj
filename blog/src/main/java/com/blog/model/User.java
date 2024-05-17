package com.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

// ORM -> Java Object를 테이블로 맵핑
@Entity  // User 클래스가 MySQL에 테이블이 생성
@Data  //Getter Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder  // 빌더 패턴
//@DynamicInsert  insert시 null 인 필드를 제외
public class User {
    @Id  // primary key
    @GeneratedValue(strategy= GenerationType.IDENTITY) // 프로젝트에서 연결된
    private int id; // 시퀀스 , auto_increment

    @Column(nullable=false,length=30)
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
}