package com.onlineshop.controllers;

import com.onlineshop.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;
}
