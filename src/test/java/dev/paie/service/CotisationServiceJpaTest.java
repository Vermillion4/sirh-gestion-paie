package dev.paie.service;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.JpaConfig;
import dev.paie.config.ServicesConfigSpe;
import dev.paie.entite.Cotisation;


@ContextConfiguration(classes = { ServicesConfigSpe.class,JpaConfig.class })
//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du test
@RunWith(SpringRunner.class)
public class CotisationServiceJpaTest {
	
	@Autowired private CotisationService cotisationService;
	
	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
	//TODO sauvegarder une nouvelle cotisation
		Cotisation nouvelleCotisation=new Cotisation();
		nouvelleCotisation.setCode("E01");
		nouvelleCotisation.setLibelle("URSSAF MALADIE - MATERNITE - INVALIDITE");
		nouvelleCotisation.setTauxSalarial(new BigDecimal("0.007500"));
		nouvelleCotisation.setTauxPatronal(new BigDecimal("0.128000"));
		cotisationService.sauvegarder(nouvelleCotisation);
		
	//TODO vérifier qu'il est possible de récupérer la nouvelle cotisation via la méthode lister
		
		assertTrue(cotisationService.lister().get(cotisationService.lister().size()-1).getLibelle().equals("URSSAF MALADIE - MATERNITE - INVALIDITE"));
		
	//TODO modifier une cotisation
		nouvelleCotisation.setTauxPatronal(new BigDecimal("0.127500"));
		cotisationService.mettreAJour(nouvelleCotisation);
	//TODO vérifier que les modifications sont bien prises en compte via la méthode lister
		//System.out.println(nouvelleCotisation.getTauxPatronal());
		//System.out.println(cotisationService.lister().stream().filter(cotisation->cotisation.getCode().equals(nouvelleCotisation.getCode())).findFirst().get().getTauxPatronal());
		//La base tronque les taux, ainsi l'operation equals ne fonctionne qu'en prenant en considération cette transformation.
		assertTrue(
			cotisationService.lister().stream().filter(cotisation->cotisation.getCode().equals(nouvelleCotisation.getCode())).findFirst().get().getTauxPatronal().equals(new BigDecimal("0.13"))
		);
	}
	
}