package ru.panifidkin.config;

import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.dialect.PostgreSQL10Dialect;
import org.postgresql.Driver;
import org.springframework.boot.actuate.jdbc.DataSourceHealthIndicator;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.DIALECT;
import static org.hibernate.cfg.AvailableSettings.DRIVER;
import static org.hibernate.cfg.AvailableSettings.FORMAT_SQL;
import static org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO;
import static org.hibernate.cfg.AvailableSettings.IMPLICIT_NAMING_STRATEGY;
import static org.hibernate.cfg.AvailableSettings.PHYSICAL_NAMING_STRATEGY;
import static org.hibernate.cfg.AvailableSettings.SHOW_SQL;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = {"ru.panifidkin.lvlproject"})
@EnableJpaRepositories(basePackages = "ru.panifidkin.lvlproject",
        entityManagerFactoryRef = "entityManagerFactoryBean",
        transactionManagerRef = "annotationDriverTransactionManager")
@ComponentScan(basePackages = {"ru.panifidkin.config"})
public class JpaConfiguration {

    @Bean
    @LiquibaseDataSource
    public DataSource postgresqlHibernateDataSource(JdbcProperties properties) {
        return DataSourceBuilder.create()
                .driverClassName(Driver.class.getName())
                .url(properties.getUrl())
                .username(properties.getUsername())
                .password(properties.getPassword())
                .build();
    }

    @Bean
    public DataSourceHealthIndicator postgresqlHibernateDataSourceHealth(DataSource dataSource) {
        return new DataSourceHealthIndicator(dataSource);
    }

    @Bean(name = "entityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean configureEntityManagerFactory(DataSource dataSource, JpaProperties jpaProperties) {
        final var properties = new Properties();
        properties.setProperty(IMPLICIT_NAMING_STRATEGY, "component-path");
        properties.setProperty(PHYSICAL_NAMING_STRATEGY, CamelCaseToUnderscoresNamingStrategy.class.getName());
        properties.setProperty(DRIVER, Driver.class.getName());
        properties.setProperty(DIALECT, PostgreSQL10Dialect.class.getName());
        properties.setProperty(HBM2DDL_AUTO, jpaProperties.getDdlAuto());
        properties.setProperty(SHOW_SQL, jpaProperties.getShowSql());
        properties.setProperty(FORMAT_SQL, jpaProperties.getFormatSql());

        final var entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan("ru.panifidkin.lvlproject");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(properties);
        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        final var liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/changelog-master.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }
}
