package com.onlineshop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {

    private String productName;
    private String userUsername;
    private String reviewText;
}
