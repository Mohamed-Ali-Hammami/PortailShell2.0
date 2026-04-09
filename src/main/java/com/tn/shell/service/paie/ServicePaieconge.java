package com.tn.shell.service.paie;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.paie.*;
import com.tn.shell.model.paie.*;
 

@Service("ServicePaieconge")
public class ServicePaieconge {
	@Autowired
	PaiecongeDAO paieDAO;
	
	public void save(Paieconge paie){
    paieDAO.save(paie);
	}
	
	public double getPaieByAnneeAndMois2(Integer annee,Integer mois) {
		return paieDAO.getPaieByAnneeAndMois2(annee, mois);
	}
	 public List<Paieconge> getPaieNondeclareByAnneeAndMois(Integer annee,Integer mois){
		 return paieDAO.getPaieNondeclareByAnneeAndMois(annee, mois);
	 }
	public List<Paieconge> getAll(){
		return paieDAO.getAll();
	}
	public List<Paieconge> getPaieByEmployee(Employee e){
		return paieDAO.getPaieByEmployee(e);
	}
	public void update(Paieconge paie){
		paieDAO.update(paie);
	}
	public void delete(Paieconge paie){
		paieDAO.detele(paie);
	}
	 public List<Paieconge> getPaieByAnnee(Integer annee){
		 return paieDAO.getPaieByAnnee(annee);
	 }
	 public List<Paieconge> getPaieByAnneeAndMois(Integer annee,Integer mois){
		 return paieDAO.getPaieByAnneeAndMois(annee, mois);
	 }
	 public List<Paieconge> getPaieByAnneeAndMoisEmployee(Employee e,Integer annee,Integer mois){
		 return paieDAO.getPaieByAnneeAndMoisEmployee(e, annee, mois);
	 }
	 public List<Paieconge> getPaieByAnneeAndEmployee(Employee e,Integer annee){
		 return paieDAO.getPaieByAnneeAndEmployee(e, annee);
	 }
}
