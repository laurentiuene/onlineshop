package com.onlineshop.controllers;

import com.onlineshop.dto.CardDetailsDto;
import com.onlineshop.dto.OrderDto;
import com.onlineshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //Create order from cart using cartId
    @PostMapping("/{cartId}")
    public OrderDto createOrderFromCart(@PathVariable(value = "cartId") Integer cartId, @RequestParam(value = "userId", required = false) Integer userId, @RequestBody CardDetailsDto cardDetailsDto){
        if(orderService.validatePayment(cardDetailsDto, userId, cartId).getStatus().matches("SUCCESS")){
            return orderService.getOrderFromCart(cartId, userId);
        }
        //TODO:In case of not enough balance, display the message on the frontend
        System.out.println(orderService.validatePayment(cardDetailsDto, userId, cartId).getStatus());
        return null;
    }
}
