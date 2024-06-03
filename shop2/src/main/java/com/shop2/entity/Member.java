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

    // private String address; // 우편 번호
    private String zipcode; // 우편 번호

    private String streetaddr; // 지번 주소

    private String detailaddr; // 상세 주소

    @Enumerated(EnumType.STRING)
    private Role role;

    private String provider;
    private String providerId;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member(); // 이거를 생성 안하고 바로 this를 이용해서
        member.setName(memberFormDto.getName()); // this.name = memverFormDto.getName() 이렇게 해도됨
        member.setEmail(memberFormDto.getEmail());

        // member.setAddress(memberFormDto.getAddress());


        // member에 각 속성을 set하기 위해 memberFormDto에 추가해야할 내용 Zipcode() 우편번호 찾기
        member.setZipcode(memberFormDto.getZipcode());
        member.setDetailaddr(memberFormDto.getDetailaddr());
        member.setStreetaddr(memberFormDto.getStreetaddr());


        // 비밀번호 암호화
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.USER);
        return member;
    }

    public static Member createAdminMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        // member.setAddress(memberFormDto.getAddress());

        // member에 각 속성을 set하기 위해 memberFormDto에 추가해야할 내용 Zipcode() 우편번호 찾기
        member.setZipcode(memberFormDto.getZipcode());
        member.setDetailaddr(memberFormDto.getDetailaddr());
        member.setStreetaddr(memberFormDto.getStreetaddr());

        String password = passwordEncoder.encode(memberFormDto.getPassword());

        // 스프링 시큐리티 설정 클래스에 등록한 BCryptPasswordEncoder Bean을 파라미터로 넘겨서 비밀번호 암호화
        member.setPassword(password);
        member.setRole(Role.ADMIN); // Role 설정
        return member;
    }

    public void updatePassword(String encodePw) {
        this.setPassword(encodePw);
    }
}