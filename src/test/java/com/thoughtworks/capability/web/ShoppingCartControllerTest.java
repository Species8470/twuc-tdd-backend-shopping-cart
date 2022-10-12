package com.thoughtworks.capability.web;

import com.thoughtworks.capability.WebApplicationTest;
import com.thoughtworks.capability.domain.Product;
import com.thoughtworks.capability.service.ShoppingCartService;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ShoppingCartControllerTest extends WebApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingCartService shoppingCartService;

    @Test
    void should_return_empty_shopping_cart() throws Exception {
        when(shoppingCartService.getShoppingCart()).thenReturn(new ShoppingCartResponse(Lists.emptyList(), BigDecimal.ZERO));

        mockMvc.perform(MockMvcRequestBuilders.get("/shopping-cart"))
                .andExpect(jsonPath("$.products").isArray())
                .andExpect(jsonPath("$.products").isEmpty())
                .andExpect(jsonPath("$.totalPrice").value(BigDecimal.ZERO));
    }

    @Test
    void should_return_2_item() throws Exception {
        List<Product> products = List.of(new Product(1L, "coke", BigDecimal.valueOf(12.0), 1),
                new Product(2L, "hershey", BigDecimal.valueOf(15.5), 2));
        when(shoppingCartService.getShoppingCart()).thenReturn(new ShoppingCartResponse(products, BigDecimal.valueOf(43)));

        mockMvc.perform(MockMvcRequestBuilders.get("/shopping-cart").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.products").isArray())
                .andExpect(jsonPath("$.totalPrice").value(BigDecimal.valueOf(43)));
    }
}
