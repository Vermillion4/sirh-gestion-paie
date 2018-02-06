package dev.paie.web.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Periode;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.EmployeRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.service.CalculerRemunerationServiceSimple;

@Controller
@RequestMapping("/bulletins")
public class BulletinSalaireController {
	

	@Autowired private EmployeRepository employeRepository;
	@Autowired private BulletinSalaireRepository bulletinSalaireRepository;
	@Autowired private PeriodeRepository periodeRepository;
	
	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/creerBulletin");
		
		/**
		 * L'insantce du bulletin que nous voulons récupérer.
		 */
		BulletinSalaire bulletin = new BulletinSalaire();
		mv.addObject("bulletin", bulletin);

		List<RemunerationEmploye> listeEmployes=employeRepository.findAll();
		List<Periode> listePeriodes=periodeRepository.findAll();		
		mv.addObject("employes",listeEmployes);
		mv.addObject("periodes",listePeriodes);
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	public String ajouterEmploye(@ModelAttribute("bulletin") BulletinSalaire bulletin) {
		bulletin.setDate(LocalDateTime.now());
		bulletinSalaireRepository.save(bulletin);
		return "redirect:/mvc/bulletins/lister";
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public ModelAndView afficherEmployes() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/listerBulletins");
		CalculerRemunerationServiceSimple centreDeCalcul=new CalculerRemunerationServiceSimple();
		Map<BulletinSalaire,ResultatCalculRemuneration> infos=
				bulletinSalaireRepository.findAll().stream().collect(
						Collectors.toMap(bul -> bul,bul->centreDeCalcul.calculer(bul)));
		mv.addObject("infos",infos);
		return mv;
	}
	
}
