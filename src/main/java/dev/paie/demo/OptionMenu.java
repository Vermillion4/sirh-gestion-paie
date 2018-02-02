/**
 * 
 */
package dev.paie.demo;

import java.util.Scanner;

/**
 * @author ETY12
 *
 */
public abstract class OptionMenu {
	
	
	/**
	 * Le nom de l' option
	 */
	String libelle;
	
	/**
	 * Une reference vers le scanner, modifier par toutes les classes filles.
	 */
	protected Scanner scannerReference;

	/**
	 * 
	 * @return libelle:String
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * 
	 * @param libelle : le libelle de l'option.
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	/**
	 * 
	 * @return un boolean si l'execution s'est bien deroulee.
	 */
	public abstract boolean execute();
	
}
