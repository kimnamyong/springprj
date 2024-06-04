package com.shop2.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop2.constant.ItemSellStatus;
import com.shop2.dto.ItemSearchDto;
import com.shop2.dto.MainItemDto;
import com.shop2.dto.QMainItemDto;
import com.shop2.entity.Item;
import com.shop2.entity.QItem;
import com.shop2.entity.QItemImg;
import jakarta.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.shop2.entity.QItem.item;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom{


 private JPAQueryFactory queryFactory;

 public ItemRepositoryCustomImpl(EntityManager em){
  this.queryFactory = new JPAQueryFactory(em);
 }

 private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus){
  return searchSellStatus == null ? null : item.itemSellStatus.eq(searchSellStatus);
 }

 private BooleanExpression regDtsAfter(String searchDateType){

  LocalDateTime dateTime = LocalDateTime.now();

  if(StringUtils.equals("all", searchDateType) || searchDateType == null){
   return null;
  } else if(StringUtils.equals("1d", searchDateType)){
   dateTime = dateTime.minusDays(1);
  } else if(StringUtils.equals("1w", searchDateType)){
   dateTime = dateTime.minusWeeks(1);
  } else if(StringUtils.equals("1m", searchDateType)){
   dateTime = dateTime.minusMonths(1);
  } else if(StringUtils.equals("6m", searchDateType)){
   dateTime = dateTime.minusMonths(6);
  }

  return item.regTime.after(dateTime);
 }


 // 등록자와 상품명 조회
 private BooleanExpression searchByLike(String searchBy, String searchQuery){

  if(StringUtils.equals("itemNm", searchBy)){
   return item.itemNm.like("%" + searchQuery + "%");
  } else if(StringUtils.equals("createdBy", searchBy)){
   return item.createdBy.like("%" + searchQuery + "%");
  }

  return null;
 }

 @Override
 public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {

  List<Item> content = queryFactory
          .selectFrom(item)
          .where(regDtsAfter(itemSearchDto.getSearchDateType()),
                  searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
                  searchByLike(itemSearchDto.getSearchBy(),  itemSearchDto.getSearchQuery()))
          .orderBy(item.id.desc())
          .offset(pageable.getOffset())
          .limit(pageable.getPageSize())
          .fetch();

  long total = queryFactory.select(Wildcard.count).from(item)
          .where(regDtsAfter(itemSearchDto.getSearchDateType()),
                  searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
                  searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
          .fetchOne()
          ;   return new PageImpl<>(content, pageable, total);
 }

 private BooleanExpression itemNmLike(String searchQuery){
  return StringUtils.isEmpty(searchQuery) ? null : item.itemNm.like("%" + searchQuery + "%");
 }

 private BooleanExpression itemCategoryLike(String searchQuery){
  return StringUtils.isEmpty(searchQuery) ? null : QItem.item.itemCategory.like("%" + searchQuery + "%");
 }


 @Override
 public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
 QItemImg itemImg = QItemImg.itemImg;

 List<MainItemDto> content = queryFactory
         .select(
                 new QMainItemDto(
                         item.id,
                         item.itemNm,
                         item.itemCategory,
                         item.itemDetail,
                         itemImg.imgUrl,
                         item.price)
         )
         .from(itemImg)
         .join(itemImg.item, item)
         .where(itemImg.repimgYn.eq("Y"))  // 대표이미지
         .where(itemNmLike(itemSearchDto.getSearchQuery())
         .or(itemCategoryLike(itemSearchDto.getSearchQuery()))) //추가된 부분
         .orderBy(item.id.desc())
         .offset(pageable.getOffset())
         .limit(pageable.getPageSize())
         .fetch();

 long total = queryFactory
         .select(Wildcard.count)
         .from(itemImg)
         .join(itemImg.item, item)
         .where(itemImg.repimgYn.eq("Y"))
         .where(itemNmLike(itemSearchDto.getSearchQuery())
          .or(itemCategoryLike(itemSearchDto.getSearchQuery()))
         )
         .fetchOne()
         ;

         return new PageImpl<>(content, pageable, total);
 }
}
