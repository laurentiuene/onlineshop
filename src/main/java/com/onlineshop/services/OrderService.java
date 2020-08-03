package com.onlineshop.services;

import com.onlineshop.client.PaymentApi;
import com.onlineshop.client.dto.PayerDto;
import com.onlineshop.client.dto.ResponseStatusDto;
import com.onlineshop.dto.OrderDto;
import com.onlineshop.models.CartItem;
import com.onlineshop.models.Order;
import com.onlineshop.models.OrderItem;
import com.onlineshop.repositories.OrderRepository;
import com.onlineshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PaymentApi paymentApi;

    public OrderDto getOrderFromCart(Integer cartId, Integer userId){
        Order newOrder = new Order();
        newOrder.setStatus("PENDING");

        newOrder.setUser(userRepository.findById(userId).get());

        newOrder.setOrderItems(cartService.getCartById(cartId).getCartItems()
                .stream()
                .map(cartItem -> cartItemToOrderItem(newOrder, cartItem))
                .collect(Collectors.toSet()));

        //TODO: redo stock without city + change the map from line 52
        newOrder.getOrderItems().stream()
                .map(orderItem -> orderItem.getProduct().getStock()
                        .stream()
                        .map(stock -> stock.getStockCity().matches(newOrder.getUser().getCity()))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());


        orderRepository.saveAndFlush(newOrder);
        return mapOrderToOrderDto(newOrder);
    }

    public OrderItem cartItemToOrderItem(Order order, CartItem cartItem){
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(cartItem.getProduct());
        orderItem.setQuantity(cartItem.getQuantity());
        return orderItem;
    }

    public OrderDto mapOrderToOrderDto(Order order){
        return modelMapper.map(order, OrderDto.class);
    }

    //TODO: get another type of DTO that has cardNumber, cardExpirationDate and cardCvv as fields
    public ResponseStatusDto validatePayment(PayerDto payerDto){
        return paymentApi.paymentValidation(payerDto);
    }
}
