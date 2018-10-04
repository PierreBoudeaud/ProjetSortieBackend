package fr.eni.projetsortie.conf;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import static org.hibernate.cfg.Environment.*;

@Configuration
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("fr.eni.projetsortie.dao"),
        @ComponentScan("fr.eni.projetsortie.service") })
@PropertySource(value = {"classpath:hibernate.properties"})
public class HibernateConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        Properties props = new Properties();
        // Setting JDBC properties
        props.put(DRIVER, env.getProperty("mssql.driver"));
        props.put(URL, env.getProperty("mssql.url"));
        props.put(USER, env.getProperty("mssql.user"));
        props.put(PASS, env.getProperty("mssql.password"));

        // Setting Hibernate properties
        props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
        props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));

        // Setting C3P0 properties
        props.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
        props.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
        props.put(C3P0_ACQUIRE_INCREMENT,
                env.getProperty("hibernate.c3p0.acquire_increment"));
        props.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
        props.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));

        factoryBean.setHibernateProperties(props);
        factoryBean.setPackagesToScan("fr.eni.projetsortie.model");

        return factoryBean;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("mssql.driver"));
        dataSource.setUrl(env.getProperty("mssql.url"));
        dataSource.setUsername(env.getProperty("mssql.user"));
        dataSource.setPassword(env.getProperty("mssql.password"));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new          LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "fr.eni.projetsortie.model" });
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        return em;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}
