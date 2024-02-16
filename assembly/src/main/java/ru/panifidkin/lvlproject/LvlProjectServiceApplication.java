package ru.panifidkin.lvlproject;

import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(
        exclude = {
                EmbeddedWebServerFactoryCustomizerAutoConfiguration.class,
                ErrorMvcAutoConfiguration.class,
                MultipartAutoConfiguration.class,
                SpringApplicationAdminJmxAutoConfiguration.class,
                SqlInitializationAutoConfiguration.class,
                ValidationAutoConfiguration.class,
                WebSocketServletAutoConfiguration.class
        },
        scanBasePackages = {"ru.panifidkin.lvlproject"})
public class LvlProjectServiceApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        MDC.put("appName", "lvl-project");
        SpringApplication.run(LvlProjectServiceApplication.class, args);
    }
}
