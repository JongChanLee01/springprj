package com.shop2.entity;

import com.shop2.constant.Role;
import com.shop2.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name="member")
@Getter
@Setter
@ToString
// public class Member {
public class Member extends BaseEntity{

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member(); // 이거를 생성 안하고 바로 this를 이용해서
        member.setName(memberFormDto.getName()); // this.name = memverFormDto.getName() 이렇게 해도됨
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());

        // 비밀번호 암호화
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.ADMIN);
        return member;
    }

}