package dev.paie.service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.IntStream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.config.InitDataConfig;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;

@Transactional	
@Service
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	// injecté une instance d'EntityManager
	@PersistenceContext private EntityManager em;
	
	@Override
	public void initialiser() {

		/* Ajout des entreprises */
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(InitDataConfig.class);

		context.getBeansOfType(Entreprise.class).forEach((beanEntId,entreprise)->em.persist(entreprise));
		
		/*
		 * Ajout des cotisations
		 */
		context.getBeansOfType(Cotisation.class).values().stream().distinct().forEach((cotisation)->em.persist(cotisation));
		
		/* Ajout des grades */
		context.getBeansOfType(Grade.class).forEach((beanGradId,grade)->em.persist(grade));
		
		/* Ajout des employes */
		context.getBeansOfType(ProfilRemuneration.class).forEach((beanProfId,profil)->em.persist(profil));
		
		IntStream.range(1,12).forEach(mois->{
				int year=LocalDate.now().getYear();
				LocalDate dateDebut=LocalDate.of(year, mois, 1);
				LocalDate dateFin=dateDebut.with(TemporalAdjusters.lastDayOfMonth());
				Periode periodeBase=new Periode();
				periodeBase.setDateDebut(dateDebut);
				periodeBase.setDateFin(dateFin);
				em.persist(periodeBase);
			}
		);
	
		context.close();
	}

}
