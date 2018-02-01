package dev.paie.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { ServicesConfig.class })
//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du test
@RunWith(SpringRunner.class)
public class CalculerRemunerationServiceSimpleTest {
	
	@Autowired 
	private CalculerRemunerationService remunerationService;
	
	private ClassPathXmlApplicationContext context;
	
	@Before
	public void context() {
		this.context=new ClassPathXmlApplicationContext("jdd-config.xml");
	}
	
	@Test
	public void test_calculer() {
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("jdd-config.xml");
		BulletinSalaire bulletin=(BulletinSalaire)context.getBean("bulletin1");
		ResultatCalculRemuneration resultat = remunerationService.calculer(bulletin);
		assertThat(resultat.getSalaireBrut(), equalTo("2683.30"));
		assertThat(resultat.getTotalRetenueSalarial(), equalTo("517.08"));
		assertThat(resultat.getTotalCotisationsPatronales(), equalTo("1096.13"));
		assertThat(resultat.getNetImposable(), equalTo("2166.22"));
		assertThat(resultat.getNetAPayer(), equalTo("2088.41"));
	}
	
	
	@After
	public void close_context() {
		
		this.context.close();
		
	}
	
}		