package com.onlineshop.services;

import com.onlineshop.dto.ReviewDto;
import com.onlineshop.models.Review;
import com.onlineshop.repositories.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Review getById(Integer id){
        return reviewRepository.findById(id).get();
    }

    public Set<ReviewDto> mapReviewToReviewDto(Set<Review> reviews){

      return reviews.stream()
                .map(review -> modelMapper.map(review, ReviewDto.class))
                .collect(Collectors.toSet());
    }
}

