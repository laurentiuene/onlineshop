package com.onlineshop.services;

import com.onlineshop.dto.CartDto;
import com.onlineshop.models.Cart;
import com.onlineshop.models.CartItem;
import com.onlineshop.repositories.CartItemRepository;
import com.onlineshop.repositories.CartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;


@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Cart getCartById(Integer id){
        return cartRepository.findById(id).get();
    }

    public CartDto createCart(CartItem cartItem){
        Cart newCart = cartRepository.saveAndFlush(new Cart());

        cartItem.setCart(newCart);

        newCart.setCartItems(new HashSet<>());
        newCart.getCartItems().add(cartItem);

        return mapCartToCartDto(cartRepository.saveAndFlush(newCart));
    }

    public CartDto updateCart(Integer id, CartItem cartItem){

        Cart existingCart = getCartById(id);

        cartItem.setCart(existingCart);
        CartItem existingCartItems = existingCart.getCartItems().stream()
                .filter(item -> cartItem.getProduct().getProductId().equals(item.getProduct().getProductId()))
                .findFirst()
                .orElse(null);
        if(existingCartItems == null) {
            existingCart.getCartItems().add(cartItem);
        }
        else {
            existingCartItems.setQuantity(existingCartItems.getQuantity() + cartItem.getQuantity());
            existingCart.getCartItems().add(existingCartItems);
        }
        cartRepository.saveAndFlush(existingCart);
        return mapCartToCartDto(existingCart);
    }

    public CartDto getCartToCartDto(Integer id){
        Cart currentCart = getCartById(id);
        return mapCartToCartDto(currentCart);
    }

    public void deleteItemFromCart(Integer cartItemId){
        cartItemRepository.findById(cartItemId).ifPresent(c -> cartItemRepository.delete(c));
    }

    public void deleteCart(Integer cartId){
        cartRepository.delete(getCartById(cartId));
    }

    public CartDto mapCartToCartDto(Cart cart){
        return modelMapper.map(cart, CartDto.class);
    }
}
