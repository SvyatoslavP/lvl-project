package ru.panifidkin.lvlproject;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Primary
@Configuration
@EnableConfigurationProperties
@SpringBootConfiguration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ru.panifidkin.lvlproject",
        entityManagerFactoryRef = "entityManagerFactoryBean",
        transactionManagerRef = "annotationDriverTransactionManager")
@ComponentScan(basePackages = {
        "ru.panifidkin.lvlproject",
        "ru.panifidkin.config"
})
public class TestLvlProjectConfiguration {
}
