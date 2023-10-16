package com.talentsprint.cycleshop;

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
public class CycleRestControllerTest {

    @Autowired
    private MockMvc mockMvc;


    private final String authToken = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJiaWJodSIsInNjb3BlIjoiUk9MRV9VU0VSIiwiaXNzIjoic2VsZiIsImV4cCI6MTY5NzEzMjY3NSwidXNlclJvbGUiOiJhZG1pbiIsImlhdCI6MTY5NzEyOTA3NX0.rh14dYlxug8NUYGc9a32wuVR7gPqPh7WV-8UU_sq5OgHWQf52uLH4ikOplZ8eLKc1Gjv9WoZowDwRY-zpHZta0OmIICrwisJeQp29OIgzTQn6Y2lgXmQPt7TTXKCeWGO-LgaFkTTGhwSFs4YAHQudpDhi2wgzrVNQfbkljl-apCqCdD6xsvQP_glLDAWBBcikyqBLS9Yh2drR7pf769jlJzffpfA20OKUQS52IpZRs10EHeKjT-Vks9R9hbo-5tFXbA584Ot0FdOJCSmfKkj1hBD8CeMl6kTpzjSERiZw0Q5LDMK5dAEdCxeZsfGyDW3sj2UD2F6MJt4XbT4AmXpvA";
    
    @BeforeEach
    void setUp() {
    }

    @Test
    @WithMockUser
    @Sql(statements = {
        "INSERT INTO Cycle (brand, stock, numBorrowed, price) VALUES ('HERO', 50, 0, 100);"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testGetAllCycles() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cycles/list")
                .header("Authorization", "Bearer " + authToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].brand").value("Hero"))
                .andExpect(jsonPath("$[0].stock").value(50))
                .andExpect(jsonPath("$[0].numBorrowed").value(0))
                .andExpect(jsonPath("$[0].price").value(100))
                .andExpect(jsonPath("$[0].numAvailable").value(50));
    }

    
}
