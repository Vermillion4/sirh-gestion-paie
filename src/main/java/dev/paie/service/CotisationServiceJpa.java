package dev.paie.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;

@Service
@Transactional
public class CotisationServiceJpa implements CotisationService {

	// injecté une instance d'EntityManager
	@PersistenceContext private EntityManager em;
	
	@Override
	public void sauvegarder(Cotisation nouvelleCotisation) {
		// TODO Auto-generated method stub
		em.persist(nouvelleCotisation);
	}

	@Override
	public void mettreAJour(Cotisation cotisation) {
		// TODO Auto-generated method stub
		String updateQuery = "UPDATE Cotisation SET Libelle='"+cotisation.getLibelle()+"',"
				+"TauxSalarial = '"+cotisation.getTauxSalarial()+"', TauxPatronal= '"+cotisation.getTauxPatronal()+"'";
		em.createQuery(updateQuery);
	}

	@Override
	public List<Cotisation> lister() {
		Query query = em.createQuery("select c from Cotisation c",Cotisation.class);
		List<Cotisation> resultats=query.getResultList();
		return resultats;
	}

}
