package com.thoughtworks.capability.service;

import com.thoughtworks.capability.domain.Product;
import com.thoughtworks.capability.repository.ProductRepository;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShoppingCartServiceTest {

    private final ProductRepository productRepository = mock(ProductRepository.class);

    private ShoppingCartService shoppingCartService;

    @BeforeEach
    void setUp() {
        shoppingCartService = new ShoppingCartService(productRepository);
    }

    @Test
    void should_return_empty_shopping_cart() {
        when(productRepository.findAll()).thenReturn(emptyList());

        ShoppingCartResponse response = shoppingCartService.getShoppingCart();

        assertEquals(0, response.getProducts().size());
        assertEquals(BigDecimal.ZERO, response.getTotalPrice());
    }

    @Test
    void should_return_shopping_cart_with_2_items() {
        List<Product> products = List.of(new Product(1L, "coke", BigDecimal.valueOf(12.0), 1),
                new Product(2L, "hershey", BigDecimal.valueOf(15.5), 2));
        when(productRepository.findAll()).thenReturn(products);

        ShoppingCartResponse response = shoppingCartService.getShoppingCart();

        assertEquals(response.getTotalPrice(), BigDecimal.valueOf(43.0));
    }
}
