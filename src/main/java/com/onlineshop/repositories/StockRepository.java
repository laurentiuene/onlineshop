package com.onlineshop.repositories;

import com.onlineshop.models.Product;
import com.onlineshop.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    Stock findByProduct(Product product);
}
