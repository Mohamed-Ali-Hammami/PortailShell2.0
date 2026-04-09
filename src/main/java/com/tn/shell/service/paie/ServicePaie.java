package com.tn.shell.service.paie;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.paie.*;
import com.tn.shell.model.paie.*;
 

@Service("ServicePaie")
public class ServicePaie {
	@Autowired
	PaieDAO paieDAO;
	
	public void save(Paie paie){
    paieDAO.save(paie);
	}
	
	public double  getTotalPayement(String fonction,Integer annee,Integer mois) {
		return paieDAO.getTotalPayement(fonction, annee, mois);
	}
	public double  getTotalCnss(String fonction,Integer annee,Integer mois) {
		return paieDAO.getTotalCnss(fonction, annee, mois);
	}
	
	public List<Paie> getPaieNondeclare2ByAnneeAndMois(Integer annee,Integer mois) {
		return paieDAO.getPaieNondeclare2ByAnneeAndMois( annee, mois) ;
	}
	 public List<Paie> getPaieNondeclareByAnneeAndMois(Integer annee,Integer mois){
		 return paieDAO.getPaieNondeclareByAnneeAndMois(annee, mois);
	 }
	 public List<Paie> getPaieByAnneeAndMois2(Integer annee,Integer mois){
		 return paieDAO.getPaieByAnneeAndMois2(annee, mois) ;
	 }
	 public double getPaieByAnneeAndMoiss(Integer annee, Integer mois) {
		 return paieDAO.getPaieByAnneeAndMoiss(annee, mois);
	 }
	public List<Paie> getAll(){
		return paieDAO.getAll();
	}
	public List<Paie> getPaieByEmployee(Employee e){
		return paieDAO.getPaieByEmployee(e);
	}
	public  double getMinSalaireByAnneeAndMoisEmployee(Employee e,Integer annee) {
		return  paieDAO.getMinSalaireByAnneeAndMoisEmployee(e, annee);
	}
	public void update(Paie paie){
		paieDAO.update(paie);
	}
	public void delete(Paie paie){
		paieDAO.detele(paie);
	}
	 public List<Paie> getPaieByAnnee(Integer annee){
		 return paieDAO.getPaieByAnnee(annee);
	 }
	 public List<Paie> getPaieByAnneeAndMois(Integer annee,Integer mois){
		 return paieDAO.getPaieByAnneeAndMois(annee, mois);
	 }
	 public  Paie  getPaieByAnneeAndMoisEmployee(Employee e,Integer annee,Integer mois){
		 return paieDAO.getPaieByAnneeAndMoisEmployee(e, annee, mois);
	 }
	 public List<Paie> getPaieByAnneeAndEmployee(Employee e,Integer annee){
		 return paieDAO.getPaieByAnneeAndEmployee(e, annee);
	 }
}
