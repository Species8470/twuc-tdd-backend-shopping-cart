package com.thoughtworks.capability.web.dto;

import com.thoughtworks.capability.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartResponse {
    private List<Product> products;
    private BigDecimal totalPrice;

}
