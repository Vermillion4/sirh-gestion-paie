package dev.paie.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static dev.paie.demo.Demo.VUE_CLIENT;

/**
 * @author ETY12
 *
 */
public class Menu {
	
	String titre="***** Gestion des cotisations *****";
	private Map<Integer,OptionMenu> actions;
	Scanner scanner;
	
	public Menu() {
		scanner=new Scanner(System.in);	
		
		actions=new HashMap<Integer,OptionMenu>();
		actions.put(0,new ListerCotisationsOptionMenu(scanner));
		actions.put(1, new AjouterCotisationOptionMenu(scanner));
		actions.put(2,new SupprimerCotisationOptionMenu(scanner));
			
	}

	/**
	 * Affiche le menu principal.
	 */
	public void afficher() {
		VUE_CLIENT.info(titre+"\n");
		for(OptionMenu optionCourante:actions.values()) {
			VUE_CLIENT.info(optionCourante.getLibelle()+"\n");
		}
		VUE_CLIENT.info("99. Quitter l'application.\n");
	}
	
	/**
	 * retourne la liste des actions possibles pour l'utilisateur.
	 * @return Map<Integer:NumeroOption,OptionMenu:Une option avec execution.
	 */
	public Map<Integer,OptionMenu> getActions() {
		return actions;
	}
	
	/**
	 * @return le scanner utilise.
	 */
	public Scanner getScanner() {
		return scanner;
	}
	
}
