package com.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Timestamp;

@Entity // User 클래스가 MySQL에 테이블이 생성이 된다.
@AllArgsConstructor
@ToString
@NoArgsConstructor   // 디폴트 생성자 추가
@Getter
@DynamicInsert  // null인 필드 제외
public class UserEntity {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된
    private Long id; // 시퀀스 , auto_increment

    @Column(nullable = false, length = 30)
    // @Column
    private String username; // 아이디

    @Column(nullable = false, length = 100) // 123456=>해쉬(비밀번호 암호화)
    //@Column
    private String password; //

    @Column(nullable = true, length = 100)
    //@Column
    private String email;

    @ColumnDefault("'user'")
    private String role; // Enum을 쓰는게 좋다. admin, user, maneger
    //@Enumerated(EnumType.STRING)
    //private RoleType role;

    @CreationTimestamp // 시간이 자동 입력
    private Timestamp createDate;
}