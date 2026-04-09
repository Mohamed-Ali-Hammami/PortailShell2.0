package com.tn.shell.service.shop;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.shop.*;
import com.tn.shell.model.shop.*;

 

 

@Service("ServiceAchat")
public class ServiceAchat {
	@Autowired
	AchatDAO achatDAO;
	
	public List<Achat> getAll(){
		return achatDAO.getAll();
	}
	public List<Achat> getAchatbystatusfacture(Status status,Fournisseur f){
		return achatDAO.getAchatbystatusfacture(status,f);
	}
	public void save(Achat c){
		achatDAO.save(c);
	}
	public void update(Achat c){
		achatDAO.update(c);
	}
	public List<Achat> getArticlebyfacture(Factureachat f){
		return achatDAO.getArticlebyfacture(f);
	}
	public Integer getmaxcode() {		 
		return achatDAO.getmaxcode();
	}
	public List<Achat> getArticlebyfournisseur(Fournisseur f){
		return achatDAO.getArticlebyfournisseur(f);
	}
	public Achat getAchatbyid(Integer id) {
		return achatDAO.getAchatbyid(id);
	}
}
