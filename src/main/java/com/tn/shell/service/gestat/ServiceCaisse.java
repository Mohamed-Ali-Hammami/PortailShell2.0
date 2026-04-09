package com.tn.shell.service.gestat;

import java.util.Date;
import java.util.List;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*;
import com.tn.shell.model.shop.Fournisseur; 

 

@Service("ServiceCaisse")
public class ServiceCaisse {
	@Autowired
	CaisseDAO CaisseDAO;
	
	public List<Caisse> getAll(){
		return CaisseDAO.getAll();
	}
	
	public Caisse getCaissebydates(String date) {
		return  CaisseDAO.getCaissebydates(date);
	}
	public List<Caisse> getbetwendates(Date d1,Date d2){
		return CaisseDAO.getbetwendates(d1, d2);
	}
	public List<Caisse> getCaissebystatusfacture(Status status,Fournisseur f){
		return CaisseDAO.getCaissebystatusfacture(status,f);
	}
	public void save(Caisse c){
		CaisseDAO.save(c);
	}
	public Caisse getCaissebydate(String date,Poste poste) {
		return CaisseDAO.getCaissebydate(date, poste);
	}
	public void update(Caisse c){
		CaisseDAO.update(c);
	}
	 
	public Caisse getmaxcode() {		 
		return CaisseDAO.getmaxcode();
	}
	public List<Caisse> getCaissebyfacture(Fournisseur f){
		return CaisseDAO.getCaissebyfacture( f);
	}
	public List<Caisse> getArticlebyfournisseur(Fournisseur f){
		return CaisseDAO.getArticlebyfournisseur(f);
	}
	public Caisse getCaissebyid(Integer id) {
		return CaisseDAO.getCaissebyid(id);
	}
}
