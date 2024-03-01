package ru.panifidkin.config;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "db.lvl-project.postgresql")
@ToString(exclude = "password")
public class JdbcProperties {

    private String url;
    private String username;
    private String password;

    @PostConstruct
    void log() {
        log.info("db.lvl-project.postgresql: {}", this);
    }
}
