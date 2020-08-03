package com.onlineshop.client.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PayerDto {

    private String firstName;
    private String lastName;
    private String cardNumber;
    private LocalDate cardExpiringDate;
    private String cardCvv;
    private Float orderValue;
}
