package com.xadmin.springboothibernate.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@PropertySource(value = {"classpath:application.properties"})
@Configuration
public class DBConfig {
	
	@Value("${jdbc.driverClassName}")
	private String driverClass;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;
	@Value("${hibernate.dialect}")
	private String dialect;
	
	@Bean
	public DataSource getDatasource()
	{
		DriverManagerDataSource datasource = new DriverManagerDataSource(url, username, password);
		datasource.setDriverClassName(driverClass);
		return datasource;
	
	}
	
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		return properties;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory()
	{
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		factory.setDataSource(getDatasource());
		factory.setHibernateProperties(hibernateProperties());
		factory.setPackagesToScan(new String[] {"com.xadmin.springboothibernate.model"});
		return factory;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory factory)
	{
		HibernateTransactionManager transactionManger = new HibernateTransactionManager();
		transactionManger.setSessionFactory(factory);
		return transactionManger;
		
	}
	
	
	
	
	

}
