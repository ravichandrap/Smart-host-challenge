package com.rooms.occupancy.manager;

import com.rooms.occupancy.manager.service.RoomManagerService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class ConfigTest {

    @Bean
    @Primary
    public RoomManagerService getService() {
        return Mockito.mock(RoomManagerService.class);
    }
}
