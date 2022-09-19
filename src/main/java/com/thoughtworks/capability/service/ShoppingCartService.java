package com.thoughtworks.capability.service;

import com.thoughtworks.capability.repository.ProductRepository;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ShoppingCartService {
    private ProductRepository productRepository;

    public ShoppingCartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ShoppingCartResponse getShoppingCart() {
        return new ShoppingCartResponse(productRepository.findAll(), BigDecimal.ZERO);
    }
}
