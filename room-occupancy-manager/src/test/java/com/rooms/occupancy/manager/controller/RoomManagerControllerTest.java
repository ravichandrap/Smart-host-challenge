package com.rooms.occupancy.manager.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@SpringBootTest(classes = RoomManagerController.class)
@WebAppConfiguration
class RoomManagerControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void bookingForecast() throws Exception {
        mockMvc.perform(
                get("/api/room")
                        .param("premiumRooms", "3")
                        .param("economyRooms", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.allocatedPremiumRooms", is(3)))
                .andExpect(jsonPath("$.allocatedEconomyRooms", is(3)))
                .andExpect(jsonPath("$.totalPremiumPrice", is(738)))
                .andExpect(jsonPath("$.totalEconomyPrice", is(167)));
    }

}