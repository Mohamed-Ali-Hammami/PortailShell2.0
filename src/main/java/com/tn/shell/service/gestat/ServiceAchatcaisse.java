package com.tn.shell.service.gestat;

import java.util.List;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
 

 import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*;
import com.tn.shell.model.shop.Fournisseur;

@Service("ServiceAchatcaisse")
public class ServiceAchatcaisse {
	@Autowired
	AchatcaisseDAO AchatcaisseDAO;
	
	public List<Achatcaisse> getAll(){
		return AchatcaisseDAO.getAll();
	}
	public List<Achatcaisse> getAchatcaissebystatusfacture(Status status,Fournisseur f){
		return AchatcaisseDAO.getAchatcaissebystatusfacture(status,f);
	}
	public void save(Achatcaisse c){
		AchatcaisseDAO.save(c);
	}
	public List<Achatcaisse> getachatydate(String c){
		return AchatcaisseDAO.getachatydate(c);}
	public void update(Achatcaisse c){
		AchatcaisseDAO.update(c);
	}
	public List<Achatcaisse> getachatyCaisse(Caisse c){
		return AchatcaisseDAO.getachatyCaisse(c);
	}
	public Integer getmaxcode() {		 
		return AchatcaisseDAO.getmaxcode();
	}
	public List<Achatcaisse> getAchatcaissebyfacture(Fournisseur f){
		return AchatcaisseDAO.getAchatcaissebyfacture( f);
	}
	public List<Achatcaisse> getArticlebyfournisseur(Fournisseur f){
		return AchatcaisseDAO.getArticlebyfournisseur(f);
	}
	public Achatcaisse getAchatcaissebyid(Integer id) {
		return AchatcaisseDAO.getAchatcaissebyid(id);
	}
}
