package dev.paie.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.IntStream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;

@Transactional	
@Service
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	// injecté une instance d'EntityManager
	@PersistenceContext private EntityManager em;
	
	@Autowired 
	AnnotationConfigWebApplicationContext context;
	
	@Override
	public void initialiser() {
		
		/* Ajout des entreprises */
		context.getBeansOfType(Entreprise.class).forEach((beanEntId,entreprise)->em.persist(entreprise));
		
		/*
		 * Ajout des cotisations
		 */
		context.getBeansOfType(Cotisation.class).forEach((beanEntId,cotisation)->em.persist(cotisation));
		
		/* Ajout des grades */
		context.getBeansOfType(Grade.class).forEach((beanGradId,grade)->em.persist(grade));
		
		/* Ajout des employes */
		context.getBeansOfType(ProfilRemuneration.class).forEach((beanProfId,profil)->em.persist(profil));
		
		IntStream.range(1,12).forEach(mois->{
				int year=LocalDate.now().getYear();
				LocalDate startDateInclusive=LocalDate.of(year, mois, 1);
				LocalDate endDateExclusive=startDateInclusive.with(TemporalAdjusters.lastDayOfMonth());
				Period periode= Period.between(startDateInclusive, endDateExclusive);
				em.persist(periode);
			}
		);
		
	}

}
