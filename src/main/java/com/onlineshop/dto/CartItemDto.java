package com.onlineshop.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CartItemDto {
    private Integer cartItemId;
    private Integer productId;
    private Integer quantity;
}
