package com.thoughtworks.capability.service;

import com.thoughtworks.capability.repository.ProductRepository;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShoppingCartServiceTest {

    private final ProductRepository productRepository = mock(ProductRepository.class);

    @Test
    void should_return_empty_shopping_cart() {
        when(productRepository.findAll()).thenReturn(emptyList());
        ShoppingCartService shoppingCartService = new ShoppingCartService(productRepository);

        ShoppingCartResponse response = shoppingCartService.getShoppingCart();

        assertEquals(0, response.getProducts().size());
        assertEquals(BigDecimal.ZERO, response.getTotalPrice());
    }
}
