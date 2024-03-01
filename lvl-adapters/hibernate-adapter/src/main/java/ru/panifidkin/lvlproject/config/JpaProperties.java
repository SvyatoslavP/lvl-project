package ru.panifidkin.lvlproject.config;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "jpa.lvl-project.hibernate")
@ToString
public class JpaProperties {

    private String ddlAuto;
    private String showSql;
    private String formatSql;
    private String lockTimeOut;

    @PostConstruct
    void log() {
        log.info("jpa.lvl-project.hibernate: {}", this);
    }
}
