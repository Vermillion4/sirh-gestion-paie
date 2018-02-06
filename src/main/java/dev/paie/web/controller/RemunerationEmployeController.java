package dev.paie.web.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.CotisationRepository;
import dev.paie.repository.EmployeRepository;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {
	
	@Autowired private CotisationRepository cotisationRepository;
	@Autowired private EntrepriseRepository entrepriseRepository;
	@Autowired private GradeRepository gradeRepository;
	@Autowired private ProfilRepository profilRepository;
	@Autowired private EmployeRepository employeRepository;
	
	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		mv.addObject("prefixMatricule","M00");
		
		/**
		 * L'insantce de l'employe que nous voulons récupérer.
		 */
		RemunerationEmploye employe = new RemunerationEmploye();
		mv.addObject("employe", employe);
		List<Entreprise> listeEntreprises=entrepriseRepository.findAll();
		List<Grade> listeGrades=gradeRepository.findAll();
		List<ProfilRemuneration> listeProfils=profilRepository.findAll();
		/*listeEntreprises.addAll(context.getBeansOfType(Entreprise.class).values());
		listeProfils.addAll(context.getBeansOfType(ProfilRemuneration.class).values());
		listeGrades.addAll(context.getBeansOfType(Grade.class).values());
		try {
			getEntreprises();
			listeProfils=getProfils();
			listeGrades=getGrades();
		} catch (SAXException e) {
			
		}*/
		
		mv.addObject("entreprises",listeEntreprises);
		mv.addObject("grades",listeGrades);
		mv.addObject("profils",listeProfils);
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	public String ajouterEmploye(@ModelAttribute("employe") RemunerationEmploye employe) {
		ModelAndView mv = new ModelAndView();
		employe.setDate(LocalDateTime.now());
		employeRepository.save(employe);
		return "redirect:/mvc/employes/lister";
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public ModelAndView afficherEmployes() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("employes",employeRepository.findAll());
		mv.setViewName("employes/listerEmployes");
		return mv;
	}
	/**
	 * 
	 * @return la liste des entreprises/
	 * @throws SAXException : une eception de parsing XML, non utilise actuellement.
	 */
	public List<Entreprise> getEntreprises() throws SAXException{
		List<Entreprise> entreprises=new ArrayList<>();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder docBuilder;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			return entreprises;
		}
	    try{
	    	URL url = getClass().getResource("/entreprises.xml");
	    	Document document = docBuilder.parse(new File(url.getPath()));
	    	document.normalize();
	    	System.out.println(document.getNodeValue());
	    	entreprises= getBean(document.getDocumentElement(),new Entreprise());
	    }
	    catch(IOException e) {
	    	System.out.println(e.getMessage());
	    	return entreprises;
	    }
	   return entreprises;
	}
	
	/**
	 * 
	 * @return la liste de profils remuneration.
	 * @throws SAXException : une eception de parsing XML, non utilise actuellement.
	 */
	public List<ProfilRemuneration> getProfils() throws SAXException{
		List<ProfilRemuneration> profils=new ArrayList<>();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder docBuilder;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			return profils;
		}
	    try{
	    	Document document = docBuilder.parse(new File("/resources/profils-remuneration.xml"));
	    	 profils= getBean(document.getDocumentElement(),new ProfilRemuneration());
	    }
	    catch(IOException e) {
	    	return profils;
	    }
	    return profils;
	}
	
	public List<Grade> getGrades() throws SAXException{
		List<Grade> grades=new ArrayList<>();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder docBuilder;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			return grades;
		}
	    try{
	    	Document document = docBuilder.parse(new File("/resources/grades.xml"));
	    	 grades= getBean(document.getDocumentElement(),new Grade());
	    }
	    catch(IOException e) {
	    	return grades;
	    }
		return grades;
	}
	
	public static <BeanType> List<BeanType> getBean(Element element,BeanType classe) {

	    NodeList nodeList = element.getChildNodes();

	    List<BeanType> listeElements=new ArrayList<>();
	    for (int i = 0; i < nodeList.getLength(); i++) {
	        Node currentNode = nodeList.item(i);
	        if (currentNode.getNodeName().equals("Bean") && currentNode.getAttributes().getNamedItem("id").getNodeValue().matches(classe.getClass().getName().toLowerCase())) {
	            //calls this method for all the children which is Element
	        	listeElements.add((BeanType) currentNode);
	            
	        }
	    }
	    return listeElements;
	}
}
