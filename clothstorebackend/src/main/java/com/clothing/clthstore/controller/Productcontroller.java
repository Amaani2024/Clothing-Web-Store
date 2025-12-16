package com.clothing.clthstore.controller;

import com.clothing.clthstore.model.product;
import com.clothing.clthstore.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000") // optional for React frontend
public class Productcontroller {

    private final ProductRepository productRepository;

    public Productcontroller(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping
    public product createProduct(@RequestBody product product) {
        return productRepository.save(product);
    }
}
