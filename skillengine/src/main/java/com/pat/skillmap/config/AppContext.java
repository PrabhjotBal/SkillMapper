package com.pat.skillmap.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


	@Configuration
	@EnableTransactionManagement
	@ComponentScan(basePackages="com.pat.skillmap")
	public class AppContext {

		@Bean
		public DataSource dataSource() {
			
			DriverManagerDataSource dataSource=new DriverManagerDataSource();
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://localhost/testskillmapdb");
			dataSource.setUsername("root");
			dataSource.setPassword("");
			
			return dataSource;
		}
		
		public Properties getHibernateProperties() {
			Properties properties=new Properties();
			properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
			properties.setProperty("hibernate.show_sql", "true");
			properties.setProperty("hibernate.hbm2ddl.auto", "update");
			//put
			return properties;
		}
		
		@Bean(name="sessionFactory")
		@Autowired
		public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
			LocalSessionFactoryBean sessionFactory=new LocalSessionFactoryBean();
			sessionFactory.setDataSource(dataSource);
			sessionFactory.setHibernateProperties(getHibernateProperties());
			sessionFactory.setPackagesToScan("com.pat.skillmap.model");
			
			return sessionFactory;
		}
		
		@Bean(name="transactionManager")
		@Autowired
		public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
			HibernateTransactionManager transactionManager=new HibernateTransactionManager();
			transactionManager.setSessionFactory(sessionFactory);
			
			return transactionManager;
		}
		
		

}
