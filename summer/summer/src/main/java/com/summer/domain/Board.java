package com.summer.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Board {
 @Id  // 대표값을 지정!  like a 주민등록번호
 @GeneratedValue   // 1, 2, 3, 자동생성 어노테이션
 private Long id;

 @Column
 private String boardName;

}
