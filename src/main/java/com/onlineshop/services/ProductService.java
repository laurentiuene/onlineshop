package com.onlineshop.services;

import com.onlineshop.models.Product;
import com.onlineshop.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getByName(String name) {
        if (name == null)
            return productRepository.findAll();
        else
            return productRepository.findByProductNameContainingIgnoreCase(name);
    }

    public Product getById(Integer id){
        return productRepository.findById(id).get();
    }

    public void deleteProduct(Integer id){
        productRepository.deleteById(id);
    }

    public Product createProduct(Product product){
        return productRepository.saveAndFlush(product);
    }

    public Product updateProductStock(Integer id, Product product){
        Product existingProduct = getById(id);
        BeanUtils.copyProperties(product, existingProduct);
        return productRepository.saveAndFlush(product);
    }
}
