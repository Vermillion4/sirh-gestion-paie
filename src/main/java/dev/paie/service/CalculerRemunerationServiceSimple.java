package dev.paie.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService {

	/*SALAIRE_BASE = GRADE.NB_HEURES_BASE * GRADE.TAUX_BASE
	SALAIRE_BRUT = SALAIRE_BASE + PRIME_EXCEPTIONNELLE
	TOTAL_RETENUE_SALARIALE = SOMME(COTISATION_NON_IMPOSABLE.TAUX_SALARIAL*SALAIRE_BRUT)
	TOTAL_COTISATIONS_PATRONALES =
	SOMME(COTISATION_NON_IMPOSABLE.TAUX_PATRONAL*SALAIRE_BRUT)
	NET_IMPOSABLE = SALAIRE_BRUT - TOTAL_RETENUE_SALARIALE
	NET_A_PAYER = NET_IMPOSABLE - SOMME(COTISATION_IMPOSABLE.TAUX_SALARIAL*SALAIRE_BRUT)
	*/
	
	@Override
	public ResultatCalculRemuneration calculer(@Qualifier("BulletinSalaire") BulletinSalaire bulletin) {
		//Dans l'ideal on creerait aussi toutes les entites d'acces si l'on souhaite ajouter du contenu : grade,entreprise...
		//pour repondre aux codes du code propre.
		final BigDecimal NB_HEURES_BASE=bulletin.getRemunerationEmploye().getGrade().getNbHeuresBase();
		final BigDecimal TAUX_BASE=bulletin.getRemunerationEmploye().getGrade().getTauxBase();
		final BigDecimal SALAIRE_BASE = NB_HEURES_BASE.multiply(TAUX_BASE);
		final BigDecimal PRIME_EXCEPTIONNELLE=bulletin.getPrimeExceptionnelle()==null?new BigDecimal("0"):bulletin.getPrimeExceptionnelle();
		final BigDecimal SALAIRE_BRUT =  SALAIRE_BASE.add(PRIME_EXCEPTIONNELLE);
		BigDecimal total_retenue_salariale = new BigDecimal("0");
		for(int index=0;index<bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables().size();index++) {
			if(bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables().get(index).getTauxSalarial()==null) {
				continue;
			}
			final BigDecimal TAUX_SALARIAL =
					bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables().get(index).getTauxSalarial();
			total_retenue_salariale=total_retenue_salariale.add(TAUX_SALARIAL.multiply(SALAIRE_BRUT));
		}
		
		BigDecimal total_cotisations_patronales = new BigDecimal("0");
		for(int index=0;index<bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables().size();index++) {
			
			if(bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables().get(index).getTauxPatronal()==null){
				continue;				
			}
			final BigDecimal TAUX_PATRONAL=bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables().get(index).getTauxPatronal();
			total_cotisations_patronales=total_cotisations_patronales.add(TAUX_PATRONAL.multiply(SALAIRE_BRUT));
		}
		System.out.println(SALAIRE_BRUT+" - "+total_retenue_salariale+"="+SALAIRE_BRUT.subtract(total_retenue_salariale));
		
		final BigDecimal NET_IMPOSABLE = SALAIRE_BRUT.subtract(new BigDecimal(PaieUtils.formaterBigDecimal(total_retenue_salariale)));
		
		BigDecimal cotisations=new BigDecimal("0");
		List<Cotisation> cotisationsImposables=bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsImposables();
		for(Cotisation cotisation:cotisationsImposables) {
			if(cotisation.getTauxSalarial()==null) {
				continue;
			}

			cotisations=(new BigDecimal(PaieUtils.formaterBigDecimal(cotisations)))
					.add(cotisation.getTauxSalarial()
							.multiply(new BigDecimal(PaieUtils.formaterBigDecimal(SALAIRE_BRUT)))
			)
			;
		}
		
		final BigDecimal NET_A_PAYER = (new BigDecimal(PaieUtils.formaterBigDecimal(NET_IMPOSABLE))).subtract(cotisations);
		ResultatCalculRemuneration resultat=new ResultatCalculRemuneration();
		
		resultat.setSalaireDeBase(PaieUtils.formaterBigDecimal(SALAIRE_BASE));
		resultat.setSalaireBrut(PaieUtils.formaterBigDecimal(SALAIRE_BRUT));
		resultat.setTotalRetenueSalarial(PaieUtils.formaterBigDecimal(total_retenue_salariale));
		resultat.setTotalCotisationsPatronales(PaieUtils.formaterBigDecimal(total_cotisations_patronales));

		resultat.setNetImposable(PaieUtils.formaterBigDecimal(NET_IMPOSABLE));
		resultat.setNetAPayer(PaieUtils.formaterBigDecimal(NET_A_PAYER));
		
		return resultat;
	}
	
}
