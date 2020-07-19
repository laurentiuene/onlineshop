package com.onlineshop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
    private Integer orderItemId;
    private Integer productId;
    private Integer quantity;
}
