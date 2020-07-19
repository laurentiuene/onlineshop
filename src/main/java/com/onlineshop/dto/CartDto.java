package com.onlineshop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CartDto {
    private Integer cartId;
    private Set<CartItemDto> cartItems;
}
