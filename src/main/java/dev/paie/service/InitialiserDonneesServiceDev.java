package dev.paie.service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.IntStream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.config.InitDataConfig;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.Utilisateur;
import dev.paie.entite.Utilisateur.ROLES;
import dev.paie.repository.UtilisateurRepository;

@Transactional	
@Service
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {
	
	// injecté une instance d'EntityManager
	@PersistenceContext private EntityManager em;
	
	@Lazy
	@Autowired private PasswordEncoder passwordEncoder;

	@Autowired private UtilisateurRepository utilisateurs;
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
		
		String iciUnMotDePasse = "topSecret";
		String iciMotDePasseHashe = this.passwordEncoder.encode(iciUnMotDePasse);
		Utilisateur utilisateur=new Utilisateur();
		utilisateur.setNomUtilisateur("utilisateur");
		utilisateur.setMotDePasse(iciMotDePasseHashe);
		utilisateur.setRole(ROLES.ROLE_VISITEUR);
		utilisateurs.save(utilisateur);
		
		String username="magicUser";
		String password=this.passwordEncoder.encode("outlaw");
		Utilisateur magic=new Utilisateur();
		magic.setNomUtilisateur(username);
		magic.setMotDePasse(password);
		magic.setRole(ROLES.ROLE_ADMINISTRATEUR);
		utilisateurs.save(magic);
		
		String adminName="admin";
		String passAdmin=this.passwordEncoder.encode("noAdmin");
		Utilisateur admin=new Utilisateur();
		admin.setNomUtilisateur(adminName);
		admin.setMotDePasse(passAdmin);
		admin.setRole(ROLES.ROLE_UTILISATEUR);
		utilisateurs.save(admin);
	}

}
