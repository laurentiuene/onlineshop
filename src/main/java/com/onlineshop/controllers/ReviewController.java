package com.onlineshop.controllers;

import com.onlineshop.models.Review;
import com.onlineshop.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("{id}")
    public Review getById(@PathVariable Integer id){
        return reviewService.getById(id);
    }
}
