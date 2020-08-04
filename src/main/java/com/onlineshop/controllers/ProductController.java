package com.onlineshop.controllers;

import com.onlineshop.models.Product;
import com.onlineshop.models.Stock;
import com.onlineshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //Get a product using the name
    @GetMapping
    public List<Product> getByName(@RequestParam(value = "name", required = false) String name) {
        return productService.getByName(name);
    }

    //Get a product using the id
    @GetMapping("/{id}")
    public Product getById(@PathVariable Integer id){
        return productService.getById(id);
    }

    //Delete a product
    @DeleteMapping(value = {"{id}"})
    public void deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
    }

    //Create an instance of a product
    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    //Get stock for a product using the id
    @GetMapping("/stock/{id}")
    public Stock getProductStock(@PathVariable Integer id){
        Product product = productService.getById(id);
        return product.getStock();
    }

    //Update stock for a product using the id
    @PutMapping("/{id}")
    public Product updateStock(@PathVariable Integer id, @RequestBody Product product){
        return productService.updateProductStock(id, product);
    }
}
