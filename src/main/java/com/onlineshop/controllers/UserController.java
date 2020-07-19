package com.onlineshop.controllers;

import com.onlineshop.models.Cart;
import com.onlineshop.models.Order;
import com.onlineshop.models.User;
import com.onlineshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Get all existing users
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    //Get user using the username
    @GetMapping("/{username}")
    public User getUserByName(@PathVariable String username) {
        return userService.getByUsername(username);
    }

    //Get carts for user with username = {username}
    @GetMapping("/order/{username}")
    public Set<Order> getOrder(@PathVariable String username){
        User currentUser = userService.getByUsername(username);
        return currentUser.getOrders();
    }
}
