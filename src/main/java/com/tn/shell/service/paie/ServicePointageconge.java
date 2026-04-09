package com.tn.shell.service.paie;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.paie.*;
import com.tn.shell.model.paie.*;
 

@Service("ServicePointageconge")
public class ServicePointageconge {
	@Autowired
	PointagecongeDAO pointagecongeDAO;
	
	public void save(Pointageconge pointage){
		pointagecongeDAO.save(pointage);
	}
	 public Pointageconge getMaxPointage(){
		 return pointagecongeDAO.getMaxPointageconge();
	 }
	 public List<Pointageconge> getPointageByEmployer(Employee e){
		 return pointagecongeDAO.getPointagecongeByEmployer(e) ;
	 }
	public List<Pointageconge> getAll(){
		return pointagecongeDAO.getAll();
	}
	public List<Pointageconge> getPointageByAnnee( Integer annee){
		return pointagecongeDAO.getPointagecongeByAnnee(annee);
	}
	public List<Pointageconge> getPointageByMoiannee(Integer annee,Integer mois){
		return pointagecongeDAO.getPointagecongeByMoiannee(annee, mois);
	}
	public void update(Pointageconge pointage){
		pointagecongeDAO.update(pointage);
	}
	public void delete(Pointageconge pointage){
		pointagecongeDAO.detele(pointage);
	}
	public Pointageconge getPointageByEmployee(Employee e, Integer annee){
		return pointagecongeDAO.getPointagecongeByEmployee(e, annee);
	}
	 
}
