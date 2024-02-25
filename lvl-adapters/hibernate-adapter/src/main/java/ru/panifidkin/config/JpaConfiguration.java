package ru.panifidkin.config;

import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.PostgreSQL10Dialect;
import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.jdbc.DataSourceHealthIndicator;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = {"ru.panifidkin.lvlproject"})
@EnableJpaRepositories(basePackages = "ru.panifidkin.lvlproject",
        entityManagerFactoryRef = "entityManagerFactoryBean",
        transactionManagerRef = "annotationDriverTransactionManager")
public class JpaConfiguration {

    @Bean
    @LiquibaseDataSource
    @ConfigurationProperties(prefix = "db.lvl-project.postgresql")
    public DataSource postgresqlHibernateDataSource(JdbcProperties properties) {
        return DataSourceBuilder.create()
                .driverClassName(PostgreSQL10Dialect.class.getName())
                .url(properties.getUrl())
                .username(properties.getUsername())
                .password(properties.getPassword())
                .build();
    }

    @Bean
    public DataSourceHealthIndicator postgresqlHibernateDataSourceHealth(@Qualifier("postgresqlHibernateDataSource") DataSource dataSource) {
        return new DataSourceHealthIndicator(dataSource);
    }

    @Bean(name = "entityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean configureEntityManagerFactory(@Qualifier("postgresqlHibernateDataSource") DataSource dataSource,
                                                                                JpaProperties jpaProperties) {
        final var properties = new Properties();
        properties.setProperty(Environment.IMPLICIT_NAMING_STRATEGY, "component-path");
        properties.setProperty(Environment.DRIVER, Driver.class.getName());
        properties.setProperty(Environment.DIALECT, PostgreSQL10Dialect.class.getName());
        properties.setProperty(Environment.HBM2DDL_AUTO, jpaProperties.getDdlAuto());
        properties.setProperty(Environment.SHOW_SQL, jpaProperties.getShowSql());
        properties.setProperty(Environment.FORMAT_SQL, jpaProperties.getFormatSql());

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
    public SpringLiquibase liquibase(@Qualifier("postgresqlHibernateDataSource") DataSource dataSource) {
        final var liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/changelog-master.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }
}
