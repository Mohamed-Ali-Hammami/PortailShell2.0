package com.tn.shell.service.paie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.paie.GestionDAO;
import com.tn.shell.dao.paie.GestionrappelDAO;
import com.tn.shell.model.paie.Gestion;
import com.tn.shell.model.paie.Gestionrappel;

 


@Service("ServiceGestionrappel")
public class ServiceGestionrappel {
	@Autowired
	GestionrappelDAO gestionDAO;
	
	public List<Gestionrappel> getAll(){
		return gestionDAO.getAll();
	}
	public void save(Gestionrappel c){
		gestionDAO.save(c);
	}
	public void update(Gestionrappel c){
		gestionDAO.update(c);
	}
	
	public void delete(Gestionrappel c){
		gestionDAO.detele(c);
	}
	
	 public Gestionrappel getGestionbyAnnee(Integer annee,String libelle){
		 return gestionDAO.getGestionbyLibelle(annee,libelle);
	 }
	 }
