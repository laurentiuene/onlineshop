package com.onlineshop.client;

import com.onlineshop.client.dto.PayerDto;
import com.onlineshop.client.dto.ResponseStatusDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "PaymentApi", url = "http://localhost:8082")
public interface PaymentApi {

    @GetMapping("/payment")
    ResponseStatusDto paymentValidation(@RequestBody PayerDto payerDto);
}
