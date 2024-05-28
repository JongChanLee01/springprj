package com.shop2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass // 테이블과 매핑된 수퍼클래스
@Getter
public abstract class BaseEntity extends BaseTimeEntity{
    // 기본적인 정보를 관리하는 기능, 엔티티 생성 여부 확인


    // @CreatedBy는 엔티티의 생성자를 추적하기 위한 어노테이션.
    // 엔티티가 생성될 때, 해당 엔티티를 생성한 사용자의 정보를
    // 저장하는 데 사용.
    @CreatedBy // 생성
    @Column(updatable = false)
    private String createdBy; // 생성했을때의 시간관리

    @LastModifiedBy // 수정
    private String modifiedBy; // 수정됐을때의 시간관리

}