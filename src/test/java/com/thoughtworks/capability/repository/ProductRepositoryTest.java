package com.thoughtworks.capability.repository;

import com.thoughtworks.capability.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Test
    void should_return_no_product() {
        List<Product> products = repository.findAll();

        assertEquals(0, products.size());
    }
}
