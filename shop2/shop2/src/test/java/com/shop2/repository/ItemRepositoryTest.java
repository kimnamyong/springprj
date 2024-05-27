package com.shop2.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop2.constant.ItemSellStatus;
import com.shop2.enitty.Item;
import com.shop2.enitty.QItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
//@DataJpaTest
@TestPropertySource(locations="classpath:application-test.properties")
class ItemRepositoryTest {
 @Autowired
 ItemRepository itemRepository;

 @Test
 @DisplayName("상품 저장 테스트")
 public void createItemTest(){
  Item item = new Item();
  item.setItemNm("테스트 상품");
  item.setPrice(10000);
  item.setItemDetail("테스트 상품 상세 설명");
  item.setItemSellStatus(ItemSellStatus.SELL);
  item.setStockNumber(100);
  item.setRegTime(LocalDateTime.now());
  item.setUpdateTime(LocalDateTime.now());
  Item savedItem = itemRepository.save(item);
  System.out.println(savedItem.toString());
 }

 @Test
 @DisplayName("상품명 조회 테스트")
 public void findByItemNmTest() {

  this.createItemList();
  List<Item> itemList = itemRepository.findByItemNm("테스트 상품2");

  for (Item item : itemList) {
   System.out.println(item);
  }
  assertEquals(itemList.toString(),itemList);
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

 public void createItemList2(){
  for(int i=1;i<=5;i++){
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
 @DisplayName("가격 LessThan 테스트")

 @Transactional
 public void findByPriceLessThanTest(){
  this.createItemList();
  List<Item> itemList = itemRepository.findByPriceLessThan(10001);

  log.info("가격 테스트");
  for(Item item : itemList){
   System.out.println(item.toString());
  }

  assertEquals(1,itemList.stream().count());
 }

 @Test
 @DisplayName("상품명, 상품상세설명 or 테스트")
 public void findByItemNmOrItemDetailTest(){
  this.createItemList();
  List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품 상세 설명5");
  for(Item item : itemList){
   System.out.println(item.toString());
  }
 }

 @Test
 @DisplayName("가격 내림차순 조회 테스트")
 public void findByPriceLessThanOrderByPriceDesc(){
  this.createItemList();
  List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
  for(Item item : itemList){
   System.out.println(item.toString());

   log.info(item.toString());
  }
  assertEquals("결과치",itemList);

 }

 @Test
 @DisplayName("@Query를 이용한 상품 조회 테스트")
 public void findByItemDetailTest(){
  this.createItemList();
  List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상세 설명1");
  for(Item item : itemList){
   System.out.println(item.toString());
  }
  assertEquals("결과치",itemList);
 }

 @PersistenceContext EntityManager em;

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
 @DisplayName("상품 Querydsl 조회 테스트 2")
 public void queryDslTest2(){

  this.createItemList2();

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

  Pageable pageable = PageRequest.of(0, 5);
  Page<Item> itemPagingResult = itemRepository.findAll(booleanBuilder, pageable);
  System.out.println("total elements : " + itemPagingResult.getTotalElements ());

  List<Item> resultItemList = itemPagingResult.getContent();
  for(Item resultItem: resultItemList){
   System.out.println(resultItem.toString());
  }
 }

} //