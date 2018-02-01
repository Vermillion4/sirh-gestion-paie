package dev.paie.config;

import javax.sql.DataSource;

import java.util.ResourceBundle;

import javax.persistence.EntityManagerFactory;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import dev.paie.service.CotisationService;
import dev.paie.service.CotisationServiceJpa;

@EnableTransactionManagement
@Configuration
public class JpaConfig {
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(emf);
		return txManager;
	}
	
	@Bean 
	public CotisationService cotisationService() {
		return new CotisationServiceJpa();
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		String sqlServer=ResourceBundle.getBundle("app").getString("jdbc.sqlServer");
		dataSource.setUrl("jdbc:mysql://"+sqlServer+":3306/bwnpa8fim");
		dataSource.setUsername("ut97yq5xeo9kqp6u");
		dataSource.setPassword("Q5P4fZ0ErEhpAbvPnCZ");
		return dataSource;
	}
	
	@Bean
	// Cette configuration nécessite une source de données configurée.
	// Elle s'utilise donc en association avec un autre fichier de configuration définissant un bean DataSource.
	public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		// activer les logs SQL
		vendorAdapter.setShowSql(true);
		LocalContainerEntityManagerFactoryBean factory = new
		LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		// alternative au persistence.xml
		factory.setPackagesToScan("dev.paie.entite");
		factory.setDataSource(dataSource);
		factory.afterPropertiesSet();
		return factory.getObject();
	}
	
}
