package com.tn.shell.service.paie;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.paie.*;
import com.tn.shell.model.paie.*;
 

@Service("Servicepaieprime")
public class Servicepaieprime {
	@Autowired
	PaieprimeDAO paieDAO;
	
	public void save(Paieprime paie){
    paieDAO.save(paie);
	}
	 public List<Paieprime> getPaieNondeclareByAnneeAndMois(Integer annee,Integer mois){
		 return paieDAO.getPaieNondeclareByAnneeAndMois(annee, mois);
	 }
	 
	 public double getPaieByAnneeAndMois2(Integer annee, Integer mois) {
		 return paieDAO.getPaieByAnneeAndMois2(annee, mois);
	 }
	public List<Paieprime> getAll(){
		return paieDAO.getAll();
	}
	public List<Paieprime> getPaieByEmployee(Employee e){
		return paieDAO.getPaieByEmployee(e);
	}
	public void update(Paieprime paie){
		paieDAO.update(paie);
	}
	public void delete(Paieprime paie){
		paieDAO.detele(paie);
	}
	 public List<Paieprime> getPaieByAnnee(Integer annee){
		 return paieDAO.getPaieByAnnee(annee);
	 }
	 public List<Paieprime> getPaieByAnneeAndMois(Integer annee,Integer mois){
		 return paieDAO.getPaieByAnneeAndMois(annee, mois);
	 }
	 public List<Paieprime> getPaieByAnneeAndMoisEmployee(Employee e,Integer annee,Integer mois){
		 return paieDAO.getPaieByAnneeAndMoisEmployee(e, annee, mois);
	 }
	 public List<Paieprime> getPaieByAnneeAndEmployee(Employee e,Integer annee){
		 return paieDAO.getPaieByAnneeAndEmployee(e, annee);
	 }
}
