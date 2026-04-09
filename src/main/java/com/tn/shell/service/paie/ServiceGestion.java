package com.tn.shell.service.paie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.paie.GestionDAO;
import com.tn.shell.model.paie.Gestion;

 


@Service("ServiceGestion")
public class ServiceGestion {
	@Autowired
	GestionDAO gestionDAO;
	
	public List<Gestion> getAll(){
		return gestionDAO.getAll();
	}
	public void save(Gestion c){
		gestionDAO.save(c);
	}
	public void update(Gestion c){
		gestionDAO.update(c);
	}
	
	public void delete(Gestion c){
		gestionDAO.detele(c);
	}
	
	 public Gestion getGestionbyLibelle(String libelle){
		 return gestionDAO.getGestionbyLibelle(libelle);
	 }
	 }
