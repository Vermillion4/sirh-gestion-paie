package dev.paie.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan({"dev.paie.service","dev.paie.util"})
@ImportResource("classpath:app-config.xml")
@Import(DataSourceMySQLConfig.class)
public class ServicesConfig {
	

}

