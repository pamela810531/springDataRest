package com.springDataRest.configurations;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.springDataRest.db2", entityManagerFactoryRef = "db2EntityManager", transactionManagerRef = "db2TransactionManager")
@Configuration
public class Db2AutoConfiguration {

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlAuto;
	@Value("${db2.jpa.database-platform}")
	private String db2Dialect;
	@Value("${spring.jpa.show-sql}")
	private boolean showSql;

	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean db2EntityManager() {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(db2DataSource());
		em.setPackagesToScan("com.springDataRest.db2");

		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);

		final HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
		properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
		properties.put("hibernate.hbm2ddl.auto", ddlAuto);
		properties.put("hibernate.dialect", db2Dialect);
		properties.put("hibernate.show_sql", showSql);
		em.setJpaPropertyMap(properties);

		return em;
	}

	@ConfigurationProperties(prefix = "db2.datasource")
	@Bean
	public DataSource db2DataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean
	public PlatformTransactionManager db2TransactionManager() {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(db2EntityManager().getObject());
		return transactionManager;
	}

}
