package com.pranhirefy.employee.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FallbackController.class)
class FallbackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testFallbackEndpointReturns404AndMessage() throws Exception {
        mockMvc.perform(get("/non-existing-url"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is("failed")))
                .andExpect(jsonPath("$.message", is("Invalid base URL. Please check the API endpoint.")));
    }
}
