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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@EnableJpaRepositories(basePackages = "com.springDataRest.msSql", entityManagerFactoryRef = "msSqlEntityManager", transactionManagerRef = "msSqlTransactionManager")
@Configuration
public class MsSqlConfiguration {

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlAuto;
	@Value("${ms-sql.jpa.database-platform}")
	private String msSqlDialect;
	@Value("${spring.jpa.show-sql}")
	private boolean showSql;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean msSqlEntityManager() {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(msSqlDataSource());
		em.setPackagesToScan("com.springDataRest.msSql", "com.springDataRest.dummyEntity");

		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		
		final HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
		properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
//		properties.put("hibernate.hbm2ddl.auto", ddlAuto);
//		properties.put("hibernate.dialect", msSqlDialect);
		properties.put("hibernate.show_sql", showSql);
		em.setJpaPropertyMap(properties);

		return em;
	}

	@ConfigurationProperties(prefix = "ms-sql.datasource")
	@Bean
	public DataSource msSqlDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public PlatformTransactionManager msSqlTransactionManager() {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(msSqlEntityManager().getObject());
		return transactionManager;
	}

}
