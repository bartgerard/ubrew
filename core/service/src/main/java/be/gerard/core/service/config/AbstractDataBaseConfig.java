package be.gerard.core.service.config;

import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * DatabaseConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Configuration
@EnableJpaRepositories("be.gerard.core.service.dao")
@EnableTransactionManagement
public abstract class AbstractDataBaseConfig {

    @Bean
    protected abstract DataSource dataSource();

    @Bean
    public EntityManagerFactory entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("be.gerard.core.service.model");
        factory.setDataSource(dataSource());
        factory.setJpaProperties(jpaProperties());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();

        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.GENERATE_STATISTICS, false);
        properties.put(Environment.SHOW_SQL, false);

        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory());
        return transactionManager;
    }


}
