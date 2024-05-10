package com.shop.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import com.shop.entity.QItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.Assert;
import org.springframework.util.Assert;
import org.thymeleaf.util.StringUtils;
//import org.thymeleaf.util.StringUtils;
//import static org.junit.Assert;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ItemRepositoryTest {

 @Autowired
 ItemRepository itemRepository;


 @PersistenceContext
 EntityManager em;

 @Test
 @DisplayName("상품 Querydsl 조회 테스트 2")
 public void queryDslTest2(){

  this.createItemList();

  BooleanBuilder booleanBuilder = new BooleanBuilder();

  QItem item = QItem.item;
  String itemDetail = "테스트 상품 상세 설명";
  int price = 10003;
  String itemSellStat = "SELL";

  booleanBuilder.and(item.itemDetail.like("%" + itemDetail + "%"));
  booleanBuilder.and(item.price.gt(price));

  System.out.println(ItemSellStatus.SELL);
  if(StringUtils.equals(itemSellStat, ItemSellStatus.SELL)){
   booleanBuilder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
  }

  Pageable pageable = PageRequest.of(2, 5);
  //PageRequest.ofSize(0);
  Page<Item> itemPagingResult = itemRepository.findAll(booleanBuilder, pageable);
  System.out.println("total elements : " + itemPagingResult. getTotalElements ());

  List<Item> resultItemList = itemPagingResult.getContent();
  for(Item resultItem: resultItemList){
   System.out.println(resultItem.toString());
  }

  Assertions.assertEquals(null,resultItemList);
 }


 @Test
 @DisplayName(" Querydsl 조회 테스트 1")
 public void queryDslTest1(){
  //this.createItemList2();
  JPAQueryFactory queryFactory=new JPAQueryFactory(em);
  QItem qItem = QItem.item;

  JPAQuery<Item> query = queryFactory.selectFrom(qItem)
          .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
          .where(qItem.itemDetail.like("%"+"설명5"+"%"))
          .orderBy(qItem.price.desc());

  List<Item> itemList=query.fetch();

  for(Item item: itemList){
   System.out.println(item.toString());
  }

  assertEquals("null",itemList.toString());
 }





 @Test
 @DisplayName("상품 저장 테스트")
 //@Transactional   저장테스트만 하고 다시 롤백한다.
 public void createItemTest() {
  Item item = new Item();
  item.setItemNm("테스트 상품");
  item.setPrice(15000);
  item.setItemDetail("테스트 상품 상세 설명");
  item.setItemSellStatus(ItemSellStatus.SELL);
  item.setStockNumber(1003);
  item.setRegTime(LocalDateTime.now());
  item.setUpdateTime(LocalDateTime.now());

  Item savedItem = itemRepository.save(item);
// save메소드는 저장된 엔티티를 리턴한다.

  System.out.println(savedItem.toString());
 }


 @Test
 @DisplayName("상품명 조회 테스트")
 public void findByItemNmTest() {

  this.createItemList();
  List<Item> itemList = itemRepository.findByItemNm("테스트 상품2");

  for (Item item : itemList) {
   //System.out.println(item);
   log.info(item.toString());
  }

  assertEquals("null",itemList);

 }

 public void createItemList(){
  for(int i=1;i<=10;i++){
   Item item = new Item();
   item.setItemNm("테스트 상품" + i);
   item.setPrice(10000 + i);
   item.setItemDetail("테스트 상품 상세 설명" + i);
   item.setItemSellStatus(ItemSellStatus.SELL);
   item.setStockNumber(100); item.setRegTime(LocalDateTime.now());
   item.setUpdateTime(LocalDateTime.now());
   Item savedItem = itemRepository.save(item);
  }
 }

 @Test
 @DisplayName("상품명, 상품상세설명 or 테스트")
 public void findByItemNmOrItemDetailTest(){
  //this.createItemList();
  List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품 상세 설명5");
  for(Item item : itemList){
   //System.out.println(item.toString());
   log.info(item.toString());
  }
  assertEquals("null",itemList);
 }


 @Test
 @DisplayName("가격 LessThan 테스트")
 @Transactional
 public void findByPriceLessThanTest(){
  //this.createItemList();
  List<Item> itemList = itemRepository.findByPriceLessThan(10005);
  for(Item item : itemList){
   System.out.println(item.toString());
  }
  assertEquals(8,itemList.stream().count());
 }

 @Test
 @DisplayName("가격 내림차순 조회 테스트")
 @Transactional
 public void findByPriceLessThanOrderByPriceDesc(){
  //this.createItemList();
  List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
  for(Item item : itemList){
   //System.out.println(item.toString());
   log.info("$ : " +item.toString());
  }
  assertEquals("결과치",itemList);
 }


 @Test
 @DisplayName("@Native Query를 이용한 상품 조회 테스트")
 public void findByItemDetailByNativeTest(){  ;
  List<Item> itemList = itemRepository.findByItemDetailByNative("설명2");
  for(Item item : itemList){
   System.out.println(item.toString());
  }
 }




} ///