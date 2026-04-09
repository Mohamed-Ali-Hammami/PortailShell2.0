package com.tn.shell.service.gestat;

import java.util.Date;
import java.util.List;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.gestat.AchatcarburantDAO;
import com.tn.shell.model.gestat.*; 
import com.tn.shell.model.shop.Fournisseur;

 
 

 

@Service("ServiceAchatcarburant")
public class ServiceAchatcarburant {
	@Autowired
	AchatcarburantDAO achatDAO;
	
	public List<Achatcarburant> getAll(){
		return achatDAO.getAll();
	}
	public double getTotalAchatbyDate(Date date) {
		return achatDAO.getTotalAchatbyDate(date);
	}
	public List<Achatcarburant> getAchatbystatusfacture(Status status,Fournisseur f){
		return achatDAO.getAchatbystatusfacture(status,f);
	}
	public void save(Achatcarburant c){
		achatDAO.save(c);
	}
	public List<Achatcarburant> getAchatbyDate(String dates){
		return achatDAO.getAchatbyDate(dates);
	}
	public void update(Achatcarburant c){
		achatDAO.update(c);
	}
	public List<Achatcarburant> getArticlebyfacture(Factureachatcarburant f){
		return achatDAO.getArticlebyfacture(f);
	}
	public Integer getmaxcode() {		 
		return achatDAO.getmaxcode();
	}
	public List<Achatcarburant> getAchatbyfacture(Fournisseur f){
		return achatDAO.getAchatbyfacture( f);
	}
	public List<Achatcarburant> getArticlebyfournisseur(Fournisseur f){
		return achatDAO.getArticlebyfournisseur(f);
	}
	public Achatcarburant getAchatbyid(Integer id) {
		return achatDAO.getAchatbyid(id);
	}
}
