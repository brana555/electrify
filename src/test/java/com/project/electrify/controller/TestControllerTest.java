package com.project.electrify.controller;

import com.project.electrify.service.OpenChargeMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(TestController.class)
public class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OpenChargeMapService openChargeMapService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetChargingPoints() throws Exception {
        // Define test data and mock behavior
        double latitude = 51.54660;
        double longitude = -0.47803;
        int distance = 10;
        String key = "test-api-key";
        String mockResponse = "Mocked response from OpenChargeMap API";

        // Mock the service method
        Mockito.when(openChargeMapService.fetchChargingPoints(latitude, longitude, distance, key)).thenReturn(mockResponse);

        // Perform GET request and verify the response
        mockMvc.perform(get("/v1/api/charging-points")
                        .param("latitude", String.valueOf(latitude))
                        .param("longitude", String.valueOf(longitude))
                        .param("distance", String.valueOf(distance))
                        .param("key", key))
                .andExpect(status().isOk())
                .andExpect(content().string(mockResponse));
    }
}