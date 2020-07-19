package com.onlineshop.controllers;

import com.onlineshop.dto.CartDto;
import com.onlineshop.dto.CartItemDto;
import com.onlineshop.models.CartItem;
import com.onlineshop.services.CartService;
import com.onlineshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    //Get carts using the id
    @GetMapping("/{id}")
    public CartDto getCartById(@PathVariable Integer id){
        return cartService.getCartToCartDto(id);
    }

    //Update cart or create it
    @PutMapping
    public CartDto updateCart(@RequestHeader(required = false) Integer id, @RequestBody CartItemDto cartItemDto){
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(cartItemDto.getQuantity());
        cartItem.setProduct(productService.getById(cartItemDto.getProductId()));
        return id == null ? cartService.createCart(cartItem) : cartService.updateCart(id, cartItem);
    }

    //Delete cartItem from cart using the id
    @DeleteMapping("/item/{id}")
    public void deleteProductFromCart(@PathVariable(value = "id") Integer cartItemId){
        cartService.deleteItemFromCart(cartItemId);
    }

    //Delete cart using the id
    @DeleteMapping("{id}")
    public void deleteCart(@PathVariable(value = "id") Integer cartId){
        cartService.deleteCart(cartId);
    }
}
