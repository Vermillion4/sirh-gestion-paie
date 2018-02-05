package dev.paie.config;

import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceMySQLConfig {
	
	
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

 
	
}
