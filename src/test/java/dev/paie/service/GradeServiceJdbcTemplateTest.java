package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.DataSourceMySQLConfig;
import dev.paie.config.JpaConfig;
import dev.paie.entite.Grade;
import dev.paie.util.PaieUtils;

@ContextConfiguration(classes = { DataSourceMySQLConfig.class, GradeServiceJdbcTemplate.class })
//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du test
@RunWith(SpringRunner.class)
public class GradeServiceJdbcTemplateTest {
	
	@Autowired private GradeService gradeService;
	
	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
	//TODO sauvegarder un nouveau grade
		Grade gradeTest=new Grade();
		gradeTest.setCode("G01");
		gradeTest.setNbHeuresBase(new BigDecimal("7.4"));
		gradeTest.setTauxBase(new BigDecimal(PaieUtils.formaterBigDecimal(new BigDecimal("0.0575"))));
		gradeService.sauvegarder(gradeTest);
	//TODO vérifier qu'il est possible de récupérer le nouveau grade via la méthode lister
		Grade gradeBase=gradeService.consulter("G01");
		areEquivalentTest(gradeTest,gradeBase);
	//TODO modifier un grade
		Grade nouveauGrade=new Grade();
		nouveauGrade.setCode("G01");
		nouveauGrade.setNbHeuresBase(new BigDecimal("21"));
		nouveauGrade.setTauxBase(new BigDecimal(PaieUtils.formaterBigDecimal(new BigDecimal("0.0575"))));
		gradeService.mettreAJour(nouveauGrade);
	//TODO vérifier que les modifications sont bien prises en compte via la méthode lister
		gradeBase=gradeService.consulter("G01");
		areEquivalentTest(nouveauGrade,gradeBase);
	}
	
	/**
	 * Operation de test sur des grades.
	 * @param gradeTest Le grade generique
	 * @param gradeBase Le grade generer de la base distante.
	 * @return
	 */
	public void areEquivalentTest(Grade gradeTest,Grade gradeBase) {
		assertThat(gradeTest.getCode()).isEqualTo(gradeBase.getCode());
		assertThat(gradeTest.getNbHeuresBase()).isEqualByComparingTo(gradeBase.getNbHeuresBase());
		assertThat(gradeTest.getTauxBase()).isEqualByComparingTo(gradeBase.getTauxBase());
	}
}
