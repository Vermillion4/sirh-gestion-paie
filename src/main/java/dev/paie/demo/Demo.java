package dev.paie.demo;

import java.util.InputMismatchException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dev.paie.config.DataSourceMySQLConfig;

public class Demo {

	/**
	 * Le logger des utilisateurs. Utilise comme constante.
	 */
	public static final Logger VUE_CLIENT= LoggerFactory.getLogger(Demo.class);
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(DataSourceMySQLConfig.class);
		System.out.println(context.getBeanDefinitionNames()[0].toString());
		Menu menu=new Menu();
		int option=0;
		
		mainLoop:
		while(true) {
			
			menu.afficher();
			try{
				option=menu.getScanner().nextInt();
			}
			catch(InputMismatchException input){
				VUE_CLIENT.error("Veuillez entrer un nombre entier pour activer nos options.\n");
				menu.getScanner().next();
				continue;
			}
			switch(option){
				case 1:
				case 2:
				case 3:
					if(menu.getActions().get(option-1).execute()==false) {
						break;
					}
				case 99:
					VUE_CLIENT.info("Au revoir !\n");
					break mainLoop;
				default:VUE_CLIENT.error("Erreur une commande inattendue est demandee.\n");
					break;
					
			}
		}
		context.close();
	}

}
