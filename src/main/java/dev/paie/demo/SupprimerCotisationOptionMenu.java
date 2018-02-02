/**
 * 
 */
package dev.paie.demo;

import static dev.paie.demo.Demo.VUE_CLIENT;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.paie.entite.Avantage;
import dev.paie.entite.Cotisation;
import dev.paie.repository.AvantageRepository;
import dev.paie.repository.CotisationRepository;

/**
 * @author ETY12
 *
 */
class SupprimerCotisationOptionMenu extends OptionMenu{
	
	@Autowired
	CotisationRepository cotisationRep;
	
	/**
	 * Le menu de suppression de pizza.
	 */
	public SupprimerCotisationOptionMenu(Scanner scanner) {
		super.setLibelle("3. Supprimer une cotisation");
		scannerReference=scanner;
	}

	/**
	 * Execute la demande de suppression d'une pizza de la liste.
	 */
	@Override
	public boolean execute() {

		VUE_CLIENT.info("Veuillez choisir la cotisation a supprimer.\n\n");

		String code=scannerReference.next();
		int id=-1;
		for(Cotisation cot:cotisationRep.findAll()) {
			if(cot.getCode().equals(code)) {
				id=cot.getId();
				break;
			}
		}
		if(id==-1) {
			return false;
		}
		cotisationRep.deleteById(id);
		return true;

	}

}
