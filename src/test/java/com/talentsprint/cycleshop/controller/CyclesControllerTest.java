package com.talentsprint.cycleshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;

import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.context.jdbc.Sql;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest

@AutoConfigureMockMvc

class CyclesControllerTest {

    @Autowired

    private MockMvc mockMvc;

    @Autowired

    private ObjectMapper objectMapper;

    private final String authToken = "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiUnVjaGEiLCJleHAiOjE2OTcxMTMwNzQsImlhdCI6MTY5NzEwOTQ3NCwic2NvcGUiOiJST0xFX1VTRVIifQ.ttPnxLOF1vOgI_pqifWc5n9ONxPgbRtLfeZYb5vrONFfs8YKagfNJFfA5ywZU32RWZx4DBiLilxCrvD3ZpsML9MfoC6fjl3ndoYxAOdrWEa0JW7_SfgZM2oBgsFvuuXaxa6EnLrSffFyEXYKJA5tUMpvPA82FKP_8YutC5JJPKFkEW55gH7DpZGqH2geoaUDcQMyVigJ-UYEkJPSY17hjuQxUy2yU0tLbzMp1m7Z-IhwAZZpAWCtSQV49OX70bTdDe2k8T-CZY6C02txkmtkD25s-_1TcEuaw8KhJEsQ9RQsQ0MQTPuYeHEirDhSmecAGNEqcRP5GI36mGK5WFgbVw";

    @BeforeEach

    void setUp() {

    }

    @Test

    @WithMockUser

    @Sql(statements = {

            "INSERT INTO cycle (brand, stock, numBorrowed, price) VALUES ('Hero', 10, 2, 200);",

    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

    void testGetAllCycles() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cycles/list")
                .header("Authorization", "Bearer " + authToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].brand").value("Hero"))
                .andExpect(jsonPath("$[0].stock").value(10))
                .andExpect(jsonPath("$[0].numBorrowed").value(2))
                .andExpect(jsonPath("$[0].price").value(200));
    }

    // @Test

    // @WithMockUser

    // void testAddToCart() throws Exception {

    //     AddToCartRequest request = new AddToCartRequest();

    //     request.setId(1L);

    //     request.setQuantity(1);

    //     mockMvc.perform(MockMvcRequestBuilders.post("/api/cycles/addToCart")

    //             .header("Authorization", "Bearer " + authToken)

    //             .contentType(MediaType.APPLICATION_JSON)

    //             .content(objectMapper.writeValueAsString(request)))

    //             .andExpect(MockMvcResultMatchers.status().isOk())

    //             .andExpect(jsonPath("$.responseMessage").value("Item added to the cart."));

    // }

    @Test
    @WithMockUser
    void testGetCartItems() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cycles/cart")
                .header("Authorization", "Bearer " + authToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].cycleId").value(1))
                .andExpect(jsonPath("$[0].color").value("Blue"))
                .andExpect(jsonPath("$[0].brand").value("BSA"))
                .andExpect(jsonPath("$[0].quantity").value(1))
                .andExpect(jsonPath("$[0].price").value(100));
    }

    // @Test

    // @WithMockUser
    // void testUpdateCartItemQuantity() throws Exception {
    //     CartUpdateRequest request = new CartUpdateRequest();
    //     request.setCycleId(1L);

    //     request.setNewQuantity(3);

    //     mockMvc.perform(MockMvcRequestBuilders.post("/api/cycles/updateCartItemQuantity")

    //             .header("Authorization", "Bearer " + authToken)

    //             .contentType(MediaType.APPLICATION_JSON)

    //             .content(objectMapper.writeValueAsString(request)))

    //             .andExpect(MockMvcResultMatchers.status().isOk())

    //             .andExpect(jsonPath("$.responseMessage").value("Cart item quantity updated"));

    // }

    @Test
    @WithMockUser
    void testRemoveFromCart() throws Exception {
        Long cycleId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.post("/api/cycles/removeFromCart")
                .header("Authorization", "Bearer " + authToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(cycleId.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.responseMessage").value("Item removed from the cart"));
    }

}
