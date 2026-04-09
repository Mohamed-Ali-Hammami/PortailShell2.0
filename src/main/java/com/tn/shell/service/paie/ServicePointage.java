package com.tn.shell.service.paie;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.paie.*;
import com.tn.shell.model.paie.*;

@Service("ServicePointage")
public class ServicePointage {
	@Autowired
	PointageDAO pointageDAO;
	
	public void save(Pointage pointage){
    pointageDAO.save(pointage);
	}
	 public Pointage getMaxPointage(){
		 return pointageDAO.getMaxPointage();
	 }
	 public List<Pointage> getPointageByEmployer(Employee e){
		 return pointageDAO.getPointageByEmployer(e) ;
	 }
	 public double getsumPointageByEmployeeandannee(Employee e, Integer annee) {
		 return pointageDAO.getsumPointageByEmployeeandannee(e,annee);
	 }
	 public double getsumPointageByEmployee(Employee e, Integer a,Integer mois1,Integer mois2) {
		 return pointageDAO.getsumPointageByEmployee(e,a,mois1,mois2);
	 }
	public List<Pointage> getAll(){
		return pointageDAO.getAll();
	}
	public List<Pointage> getPointageByAnnee( Integer annee){
		return pointageDAO.getPointageByAnnee(annee);
	}
	public List<Pointage> getPointageByMoiannee(Integer annee,Integer mois){
		return pointageDAO.getPointageByMoiannee(annee, mois);
	}
	public void update(Pointage pointage){
		pointageDAO.update(pointage);
	}
	public void delete(Pointage pointage){
		pointageDAO.detele(pointage);
	}
	public Pointage getPointageByEmployee(Employee e, Integer annee,Integer mois){
		return pointageDAO.getPointageByEmployee(e, annee, mois);
	}
	 
}
