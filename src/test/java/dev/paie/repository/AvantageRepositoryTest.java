package dev.paie.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfigSpe;
import dev.paie.entite.Avantage;

@ContextConfiguration(classes = { ServicesConfigSpe.class })
//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du test
@RunWith(SpringRunner.class)
public class AvantageRepositoryTest {
	
	@Autowired private AvantageRepository avantageRepository;
	
	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {

	//TODO sauvegarder un nouvel avantage
		Avantage avantage=new Avantage();
		avantage.setCode("AvSuperWorld");
		avantage.setNom("M.SuperWorld");
		avantage.setMontant(new BigDecimal("4.13"));
		avantageRepository.save(avantage);
		
		/*
		 * Sauvegarde de l'element en base.
		 */
		
	//TODO vérifier qu'il est possible de récupérer le nouvel avantage via la méthode findOne
	//TODO modifier un avantage
	//TODO vérifier que les modifications sont bien prises en compte via la méthode findOne
		Optional<Avantage> avantageExemple =avantageRepository.findOne(Example.of(avantage));
		assertThat(avantageExemple.isPresent()).isTrue();
		
	}
	
}
