package com.onlineshop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class OrderDto {
    private Integer orderId;
    private OrderStatus status;
    private Integer userId;
    private Set<OrderItemDto> orderItems;
}
