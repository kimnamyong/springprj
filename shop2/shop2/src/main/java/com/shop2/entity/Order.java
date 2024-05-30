package com.shop2.entity;

import com.shop2.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

 @Id
 @GeneratedValue
 @Column(name = "order_id")
 private Long id;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "member_id")
 private Member member;

 private LocalDateTime orderDate; //주문일

 @Enumerated(EnumType.STRING)
 private OrderStatus orderStatus; //주문상태

 private LocalDateTime regTime;
 private LocalDateTime updateTime;

 @OneToMany(mappedBy = "order", cascade = CascadeType.ALL
         , orphanRemoval = true, fetch = FetchType.EAGER)
 private List<OrderItem> orderItems = new ArrayList<>();

 public void addOrderItem(OrderItem orderItem) {
  orderItems.add(orderItem);
  orderItem.setOrder(this);
 }

 public static Order createOrder(Member member, List<OrderItem> orderItemList) {
  Order order = new Order();
  order.setMember(member);

  for(OrderItem orderItem : orderItemList) {
   order.addOrderItem(orderItem);
  }

  order.setOrderStatus(OrderStatus.ORDER);
  order.setOrderDate(LocalDateTime.now());
  return order;
 }

 // 총주문금액
 public int getTotalPrice() {
  int totalPrice = 0;
  for(OrderItem orderItem : orderItems){
   totalPrice += orderItem.getTotalPrice();
  }
  return totalPrice;
 }

 // 취소한 경우
 public void cancelOrder() {
  this.orderStatus = OrderStatus.CANCEL;
  for (OrderItem orderItem : orderItems) {
   orderItem.cancel();
  }
 }

}