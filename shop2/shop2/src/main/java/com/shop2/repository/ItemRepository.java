package com.shop2.repository;

import com.shop2.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long>, QuerydslPredicateExecutor<Item>,ItemRepositoryCustom {

 List<Item> findByItemNm(String itemNm);
 List<Item> findByPriceLessThan(Integer price);
 List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);
 List<Item> findByItemNmOrItemDetail(String itemNm,String itemDetail);
 List<Item> findByItemDetail(String itemDetail);

}