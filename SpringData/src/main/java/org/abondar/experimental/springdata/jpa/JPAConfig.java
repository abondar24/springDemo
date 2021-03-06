package org.abondar.experimental.springdata.jpa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by abondar on 18.07.16.
 */

@Configuration
@PropertySource("classpath:jpa.properties")
@EnableJpaRepositories
@EnableTransactionManagement
@ComponentScan(basePackages = {"org.abondar.experimental.springdata.jpa"})
public class JPAConfig {

    @Value("${hibernate.max_fetch_depth}")
    public Integer maxFetchDepth;

    @Value("${hibernate.jdbc.fetch_size}")
    public Integer fetchSize;

    @Value("${hibernate.jdbc.batch_size}")
    public Integer batchSize;

    @Value("${hibernate.show_sql}")
    public String showSQL;

    @Bean
    public Properties properties() {
        Properties jpaProps = new Properties();
        jpaProps.put("hibernate.max_fetch_depth", maxFetchDepth);
        jpaProps.put("hibernate.jdbc.fetch_size", fetchSize);
        jpaProps.put("hibernate.jdbc.batch_size", batchSize);
        jpaProps.put("hibernate.jdbc.show_sql",Boolean.valueOf(showSQL));

        return jpaProps;
    }

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:car_table.sql")
                .build();
    }

    @Bean
    JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory());
        return transactionManager;
    }

    @Bean
    EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(dataSource());
        em.setJpaVendorAdapter(jpaVendorAdapter());
        em.setPackagesToScan("org.abondar.experimental.springdata.jpa");
        em.setJpaProperties(properties());
        em.afterPropertiesSet();
        return em.getNativeEntityManagerFactory();
    }

    @Bean
    HibernateJpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }
}
