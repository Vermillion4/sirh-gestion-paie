/**
 * 
 */
package dev.paie.demo;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import dev.paie.entite.Cotisation;
import dev.paie.repository.CotisationRepository;

import static dev.paie.demo.Demo.VUE_CLIENT;

/**
 * @author ETY12
 *
 */
class ListerCotisationsOptionMenu extends OptionMenu{

	@Autowired
	CotisationRepository cotisationRep;
	
	/**
	 * 
	 */
	public ListerCotisationsOptionMenu(Scanner scanner) {
		libelle="1. Lister les cotisations";
		scannerReference = scanner;
	}

	@Override
	public boolean execute() {
		
		List<Cotisation> cotisations=cotisationRep.findAll();
		for(Cotisation cotisation:cotisations) {
			VUE_CLIENT.info(cotisation.getCode()+"->"+cotisation.getLibelle()+"("+cotisation.getTauxSalarial()+", "+cotisation.getTauxPatronal()+")\n");
		}
		VUE_CLIENT.info("");
		return false;
		
	}

}
