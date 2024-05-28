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
// 기본적인 정보를 관리하는 기능, 엔티티 생성여부 확인

 @CreatedBy  // 생성
 @Column(updatable = false)
 private String createdBy;

 @LastModifiedBy  // 수정
 private String modifiedBy;

}
