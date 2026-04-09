package com.tn.shell.service.gestat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*; 
 

@Service("ServiceCreditclient")
public class ServiceCreditclient {
	@Autowired
	CreditClientDao CreditclientDao;
	
	public Credit Findbymf(String nom){
		return CreditclientDao.Findbymf(nom);
	}
	public void save(Credit Creditclient){
		CreditclientDao.save(Creditclient);
	}
	 public double  getAllcreditbyclientanddates(Date d1,Date d2,Clientgestat c){
		 return CreditclientDao.getAllcreditbyclientanddates(d1, d2, c);
	 }
	public Credit getcreditbyid(Integer id) {
	return 	CreditclientDao.getcreditbyid(id);
	}
	public List<Credit> getListcreditdateandclient(String date,Clientgestat c){
		return CreditclientDao.getListcreditdateandclient(date, c); 
	}
	public List<Credit> getAll(){
		return CreditclientDao.getAll();
	}
	 public Credit getCreditclientbyCaisseandclient(Credit cl){
		 return CreditclientDao.getCreditclientbyCaisseandclient( cl);
	 }
	public List<Credit> getListcreditdate(String date){
		return CreditclientDao.getListcreditdate(date);
	}
	 public List<Credit> getCreditclientbyCaisse(Caisse c){
			return CreditclientDao.getCreditclientbyCaisse(c);
	 }
	public List<Credit> Findbynom(String nom) {
		return CreditclientDao.Findbynom(nom);
	}
	public void update(Credit Creditclient){
		CreditclientDao.update(Creditclient);
	}
	public void delete(Credit Creditclient){
		CreditclientDao.delete(Creditclient);
	}
	public Credit Findbycode(Integer codeCreditclient) {
		// TODO Auto-generated method stub
		return CreditclientDao.Findbycode(codeCreditclient);
	}
}
