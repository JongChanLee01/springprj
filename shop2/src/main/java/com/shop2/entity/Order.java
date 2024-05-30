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

    // CascadeType.ALL: 이 값은 모든 연쇄 삭제 작업을 수행
    // 즉, 연관된 객체가 삭제되면 해당 객체와 연관된 모든 객체도 함께 삭제
    // CascadeType.REMOVE: 이 값은 연관된 객체가 삭제될 때 해당 객체만 삭제하고, 연관된 객체는 삭제하지 않음
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL
            , orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this); // this는 Order.java
        System.out.println("this:"+this);
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

    // 총 주문 금액
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