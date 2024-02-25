package ru.panifidkin.lvlproject;

import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = ValidationAutoConfiguration.class)
@ComponentScan(basePackages = {
        "ru.panifidkin.lvlproject",
        "ru.panifidkin.config"
})
public class LvlProjectServiceApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        MDC.put("appName", "lvl-project");
        SpringApplication.run(LvlProjectServiceApplication.class, args);
    }
}
