package ru.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.homework.ioService.IOService;
import ru.homework.ioService.IOServiceStreams;

@Configuration
public class ConfigIOService {
    @Bean
    IOService ioService() {
        return new IOServiceStreams(System.out, System.in);
    }
}
