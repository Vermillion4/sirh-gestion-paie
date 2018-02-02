/**
 * 
 */
package dev.paie.demo;

import static dev.paie.demo.Demo.VUE_CLIENT;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

import dev.paie.entite.Cotisation;

/**
 * @author ETY12
 *
 */
class AjouterCotisationOptionMenu extends OptionMenu{

	/**
	 * Constructeur de l'option pizza.
	 */
	public AjouterCotisationOptionMenu(Scanner scanner) {
		libelle="2. Ajouter une nouvelle pizza";
		scannerReference=scanner;
	}

	/**
	 * Propose l'ajout d'une pizza a la liste.
	 */
	@Override
	public boolean execute(){
		
		VUE_CLIENT.info("Veuillez saisir le code : \n");
		String code=scannerReference.next();
		
		String libelle=null;
		VUE_CLIENT.info("Veuillez saisir le libelle : \n");
		//menu.useDelimiter(Pattern.compile("\\d+"));
		try{
			
			VUE_CLIENT.info(scannerReference.next(".+;"));
		}
		catch(InputMismatchException input) {
			//N'interromps pas le fonctionnement, propage le scanner, et permet de voir la ligne compl�te et non une partie jusqu'a l'espace.
		}
		finally{
			//Utilise la ligne en memoire du scanner ayant genere une erreur.
			code=scannerReference.nextLine();
		}

		VUE_CLIENT.info("Veuillez saisir le Taux Salarial : \n");
		BigDecimal tauxSalarial=new BigDecimal("0");
		tauxSalarial=scannerReference.nextBigDecimal();
		
		VUE_CLIENT.info("Veuillez saisir le Taux Patronal :\n");
		BigDecimal tauxPatronal=new BigDecimal("0");
		tauxPatronal=scannerReference.nextBigDecimal();

		//Tout est Ok, la pizza est ajout� avec la m�thode du dao, et une cat�gorie d�finie avec viande (l'utilisateur a l'air affam�).
		Cotisation nouvelleCotisation=new Cotisation();
		nouvelleCotisation.setCode(code);
		nouvelleCotisation.setLibelle(libelle);
		nouvelleCotisation.setTauxSalarial(tauxSalarial);
		nouvelleCotisation.setTauxPatronal(tauxPatronal);
		
		return true;
		
	}

}
