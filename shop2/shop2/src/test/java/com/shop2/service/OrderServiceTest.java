package com.shop2.service;

import com.shop2.constant.ItemSellStatus;
import com.shop2.constant.OrderStatus;
import com.shop2.dto.OrderDto;
import com.shop2.entity.Item;
import com.shop2.entity.Member;
import com.shop2.entity.Order;
import com.shop2.entity.OrderItem;
import com.shop2.repository.ItemRepository;
import com.shop2.repository.MemberRepository;
import com.shop2.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
//@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class OrderServiceTest {

 @Autowired
 private OrderService orderService;
 @Autowired
 private OrderRepository orderRepository;
 @Autowired
 ItemRepository itemRepository;
 @Autowired
 MemberRepository memberRepository;

 public Item saveItem(){
  Item item = new Item();
  item.setItemNm("테스트 상품");
  item.setPrice(10000);
  item.setItemDetail("테스트 상품 상세 설명");
  item.setItemSellStatus(ItemSellStatus.SELL);
  item.setStockNumber(100);
  return itemRepository.save(item);
 }

 public Member saveMember(){
  Member member = new Member();
  member.setEmail("te5s2t555@test.com");
  return memberRepository.save(member);
 }

 @Test
 @DisplayName("주문 테스트")
 public void order(){
  Item item = saveItem();
  Member member = saveMember();

  OrderDto orderDto = new OrderDto();
  orderDto.setCount(10);
  orderDto.setItemId(item.getId());

  Long orderId = orderService.order(orderDto, member.getEmail());

  Order order = orderRepository.findById(orderId)
          .orElseThrow(EntityNotFoundException::new);

  List<OrderItem> orderItems = order.getOrderItems();

  int totalPrice = orderDto.getCount()*item.getPrice();

  assertEquals(totalPrice, order.getTotalPrice()+1);
 }

 @Test
 @DisplayName("주문 취소 테스트")
 public void cancelOrder(){
  Item item = saveItem();
  Member member = saveMember();

  OrderDto orderDto = new OrderDto();
  orderDto.setCount(10);
  orderDto.setItemId(item.getId());
  Long orderId = orderService.order(orderDto, member.getEmail());

  Order order = orderRepository.findById(orderId)
  .orElseThrow(EntityNotFoundException::new);

  orderService.cancelOrder(orderId);

  log.info("orderId:{}",orderId);

    assertEquals(OrderStatus.CANCEL, order.getOrderStatus());
    assertEquals(100, item.getStockNumber());
  }

}