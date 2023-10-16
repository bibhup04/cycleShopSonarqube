package com.talentsprint.cycleshop.controller;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.oauth2.jwt.JwtEncoder;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.talentsprint.cycleshop.service.UserService;

import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(AuthControllerTest.class)
class AuthControllerTest {

    @Autowired

    private MockMvc mockMvc;

    @MockBean

    private AuthenticationManager authenticationManager;

    @MockBean

    private UserService userService;

    @MockBean

    private PasswordEncoder passwordEncoder;

    @MockBean

    private JwtEncoder jwtEncoder;

    @BeforeEach

    void setup() {

    }

    @Test
    void testRegisterUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/cycles/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"bibhu\",\"password\":\"bibhu\",\"repeatPassword\":\"bibhu\",\"role\":\"USER\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())

                .andExpect(content().string("User registered successfully"));

    }

    @Test

    void testToken() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/token")

                .contentType(MediaType.APPLICATION_JSON)

                .content("{\"username\":\"bibhu\",\"password\":\"bibhu\"}"))

                .andExpect(MockMvcResultMatchers.status().isOk())

                .andExpect(jsonPath("$.token").exists())

                .andExpect(jsonPath("$.username").value("testuser"))

                .andExpect(jsonPath("$.roles").value("ROLE_USER"))

                .andDo(MockMvcResultHandlers.print());

    }

}
