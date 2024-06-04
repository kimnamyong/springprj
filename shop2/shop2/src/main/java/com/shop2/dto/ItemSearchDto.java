package com.shop2.dto;

import com.shop2.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemSearchDto {
 private String searchDateType;
 private ItemSellStatus searchSellStatus;
 private String searchBy;
 private String searchQuery = "";
}
