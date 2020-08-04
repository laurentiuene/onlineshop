package com.onlineshop.controllers;

import com.onlineshop.dto.CardDetailsDto;
import com.onlineshop.dto.OrderDto;
import com.onlineshop.exception.InsufficientBalanceException;
import com.onlineshop.exception.InsufficientStockException;
import com.onlineshop.services.OrderService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/orders")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    //Create order from cart using cartId
    @PostMapping("/{cartId}")
    public OrderDto createOrderFromCart(@PathVariable(value = "cartId") Integer cartId, @RequestParam(value = "userId", required = false) Integer userId, @RequestBody @Valid CardDetailsDto cardDetailsDto) throws InsufficientBalanceException, InsufficientStockException, NotFoundException {
        if (orderService.validatePayment(cardDetailsDto, userId, cartId).getStatus().matches("SUCCESS")) {
            return orderService.getOrderFromCart(cartId, userId);
        }
        throw new InsufficientBalanceException(orderService.validatePayment(cardDetailsDto, userId, cartId).getStatus());
    }
}
