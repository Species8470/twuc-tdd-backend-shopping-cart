package com.thoughtworks.capability.web;

import com.thoughtworks.capability.service.ShoppingCartService;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingCartControllerTest {

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
}
