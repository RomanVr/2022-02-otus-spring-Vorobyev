package ru.homework.parse;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "parsetest")
public class ConfigProperties {
    private String delimiter;
    @Value("${parsetest.filename}")
    private String filename;
    private String fileNameExt;
}
