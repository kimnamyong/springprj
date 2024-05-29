package com.shop2.dto;

import com.shop2.entity.ItemImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ItemImgDto {

 private Long id;
 private String imgName;
 private String oriImgName;
 private String imgUrl;
 private String repImgYn;
 private static ModelMapper modelMapper = new ModelMapper();

 // Entity -> Dto
 // 팩토리패턴
 public static ItemImgDto of(ItemImg itemImg) {
  return modelMapper.map(itemImg,ItemImgDto.class);
 }
}