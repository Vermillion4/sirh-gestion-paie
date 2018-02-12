package dev.paie.config;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.paie.entite.Utilisateur.ROLES;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder();
	}
	
	@Autowired private PasswordEncoder passwordEncoder; 
	@Autowired private DataSource dataSource; 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.eraseCredentials(true);
		auth.jdbcAuthentication() 
		.dataSource(dataSource) 
		.passwordEncoder(passwordEncoder)
		.usersByUsernameQuery("select NOM_UTILISATEUR, MOT_DE_PASSE, EST_ACTIF from UTILISATEUR where NOM_UTILISATEUR=?") 
		.authoritiesByUsernameQuery("select NOM_UTILISATEUR,ROLE from UTILISATEUR where NOM_UTILISATEUR = ?")
		; 
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers("/bootstrap/**").permitAll()
		// Permettre aux utilisateurs de lister les employes ou les bulletins.
		.antMatchers("**/lister").permitAll()
		.and()
		//Permettre uniquement aux utilisateurs Enregistres d'ajouter des elements.
		.authorizeRequests().antMatchers("**/creer").access("hasAnyAuthority(ROLE_UTILISATEUR,ROLE_ADMINISTRATEUR)")
		.and()
		.authorizeRequests().anyRequest().authenticated()
		.and().formLogin().loginPage("/mvc/connexion")
		.permitAll()
		;
	   //"(,ROLES.ROLE_ADMINISTRATEUR.name()); 
		// ne pas soumettre à l'authentification, la page de connexion
		
	}
	
}