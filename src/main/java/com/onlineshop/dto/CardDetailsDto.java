package com.onlineshop.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class CardDetailsDto {

    @NotNull(message = "'cardNumber' must not be null!")
    @Size(min = 16, max = 16)
    private String cardNumber;

    @NotNull(message = "'cardExpiringDate' must not be null!")
    private LocalDate cardExpiringDate;

    @NotNull(message = "'cardCvv' must not be null!")
    @Size(min = 3, max = 3)
    private String cardCvv;
}
