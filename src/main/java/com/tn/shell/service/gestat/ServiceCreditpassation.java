package com.tn.shell.service.gestat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*; 
 

@Service("ServiceCreditpassation")
public class ServiceCreditpassation {
	@Autowired
	CreditpassationDao creditpassationclientDao;
	
	public Creditpassation Findbymf(String nom){
		return creditpassationclientDao.Findbymf(nom);
	}
	public Creditpassation findbyCredit(Credit c) {
		return creditpassationclientDao.findbyCredit(c);
	}
	public void save(Creditpassation creditpassationclient){
		creditpassationclientDao.save(creditpassationclient);
	}
	
	public  List<Creditpassation> findbyClient(Clientpassation c){
		return creditpassationclientDao.findbyClient(c);
	}
	public Creditpassation getCreditpassationbyid(Integer id) {
	return 	creditpassationclientDao.getcreditbyid(id);
	}
	public List<Creditpassation> getListCreditpassationdateandclient(String date,Clientpassation c){
		return creditpassationclientDao.getListcreditdateandclient(date, c); 
	}
	public List<Creditpassation> getAll(){
		return creditpassationclientDao.getAll();
	}
	 public Creditpassation getCreditpassationclientbyCaisseandclient(Creditpassation cl){
		 return creditpassationclientDao.getCreditclientbyCaisseandclient( cl);
	 }
	public List<Creditpassation> getListCreditpassationdate(String date){
		return creditpassationclientDao.getListcreditdate(date);
	}
	 public List<Creditpassation> getCreditpassationclientbyCaisse(Caisse c){
			return creditpassationclientDao.getCreditclientbyCaisse(c);
	 }
	public List<Creditpassation> Findbynom(String nom) {
		return creditpassationclientDao.Findbynom(nom);
	}
	public void update(Creditpassation Creditpassationclient){
		creditpassationclientDao.update(Creditpassationclient);
	}
	public void delete(Creditpassation Creditpassationclient){
		creditpassationclientDao.delete(Creditpassationclient);
	}
	public Creditpassation Findbycode(Integer codeCreditpassationclient) {
		// TODO Auto-generated method stub
		return creditpassationclientDao.Findbycode(codeCreditpassationclient);
	}
}
