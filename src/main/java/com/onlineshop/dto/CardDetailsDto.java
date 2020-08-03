package com.onlineshop.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CardDetailsDto {

    private String cardNumber;
    private LocalDate cardExpiringDate;
    private String cardCvv;
}
