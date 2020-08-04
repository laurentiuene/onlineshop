package com.onlineshop.services;

import com.onlineshop.client.PaymentApi;
import com.onlineshop.dto.CardDetailsDto;
import com.onlineshop.client.dto.PayerDto;
import com.onlineshop.client.dto.ResponseStatusDto;
import com.onlineshop.dto.OrderDto;
import com.onlineshop.dto.OrderStatus;
import com.onlineshop.exception.InsufficientStockException;
import com.onlineshop.models.*;
import com.onlineshop.repositories.CartRepository;
import com.onlineshop.repositories.OrderRepository;
import com.onlineshop.repositories.StockRepository;
import com.onlineshop.repositories.UserRepository;
import feign.FeignException;
import javassist.NotFoundException;
import org.aspectj.weaver.ast.Not;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PaymentApi paymentApi;

    public OrderDto getOrderFromCart(Integer cartId, Integer userId) throws InsufficientStockException {
        Order newOrder = new Order();
        newOrder.setUser(userRepository.findById(userId).get());

        newOrder.setStatus(OrderStatus.PENDING.toString());

        newOrder.setOrderItems(cartService.getCartById(cartId).getCartItems()
                .stream()
                .map(cartItem -> cartItemToOrderItem(newOrder, cartItem))
                .collect(Collectors.toSet()));

        for (OrderItem orderItem : newOrder.getOrderItems()) {
            Stock currentStock = stockRepository.findByProduct(orderItem.getProduct());
            if (currentStock.getQuantity() < orderItem.getQuantity()) {
                throw new InsufficientStockException("Insufficient stock available!");
            }
            currentStock.setQuantity(currentStock.getQuantity() - orderItem.getQuantity());
            stockRepository.saveAndFlush(currentStock);
        }
        orderRepository.saveAndFlush(newOrder);
        return mapOrderToOrderDto(newOrder);
    }

    public OrderItem cartItemToOrderItem(Order order, CartItem cartItem) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(cartItem.getProduct());
        orderItem.setQuantity(cartItem.getQuantity());
        return orderItem;
    }

    public OrderDto mapOrderToOrderDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }

    public ResponseStatusDto validatePayment(CardDetailsDto cardDetailsDto, Integer userId, Integer cartId) throws NotFoundException {
        Optional<User> currentUser = userRepository.findById(userId);

        if (!currentUser.isPresent()) {
            throw new NotFoundException("User not found!");
        }
        PayerDto payerDto = new PayerDto();
        payerDto.setFirstName(currentUser.get().getFirstName());
        payerDto.setLastName(currentUser.get().getLastName());
        payerDto.setCardNumber(cardDetailsDto.getCardNumber());
        payerDto.setCardExpiringDate(cardDetailsDto.getCardExpiringDate());
        payerDto.setCardCvv(cardDetailsDto.getCardCvv());

        Optional<Cart> currentCart = cartRepository.findById(cartId);

        if (!currentCart.isPresent()) {
            throw new NotFoundException("Cart not found!");
        }
        Float cartPrice = cartService.getCartById(cartId).getCartItems().stream()
                .map(cartItem -> cartItem.getProduct().getProductPrice())
                .reduce((float) 0.0, Float::sum);

        payerDto.setOrderValue(cartPrice);
        return paymentApi.paymentValidation(payerDto);
    }
}
