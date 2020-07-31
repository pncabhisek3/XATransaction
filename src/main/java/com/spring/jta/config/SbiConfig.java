package com.spring.jta.config;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "sbiFA", transactionManagerRef = "sbiTM", basePackages = {"com.spring.jta"})
@Primary
public class SbiConfig {

	// There are two databases.
	// So there will be two DataSources, PlatFormTransactionManager, EntityManagers

	// 1. Incase any property is to get from application.properties file.
	@Autowired
	private Environment env;

	// 2. Datasource configuration
	@Bean("sbiDS")
	public DataSource sbiDs() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUrl(env.getProperty("spring.datasource.sbi.url"));
		ds.setUsername(env.getProperty("spring.datasource.sbi.username"));
		ds.setPassword(env.getProperty("spring.datasource.sbi.password"));
		return ds;
	}

	// 3. EntityManager configuration.
	@Bean("sbiFA")
	public LocalContainerEntityManagerFactoryBean sbiFA() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(sbiDs());
		em.setPackagesToScan(new String[] { "com.spring" });
		em.setPersistenceUnitName("sbiPU");

		// Below properties are mandatory for jpaTransaction.
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.show_sql", "spring.jpa.show-sql");
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Bean("sbiEM")
	public EntityManager entityManager(@Qualifier("sbiFA") EntityManagerFactory entityManagerFactory) {
		return entityManagerFactory.createEntityManager();
	}

	// 4. TransactionManager configuration
	@Bean("sbiTM")
	public PlatformTransactionManager sbiTM() {
		return new JpaTransactionManager(sbiFA().getObject());
	}

}
