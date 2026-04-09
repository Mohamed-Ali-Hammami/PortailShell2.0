package com.tn.shell.service.gestat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*; 
 

@Service("ServiceCreditanterieur")
public class ServiceCreditanterieur {
	@Autowired
	CreditanterieurDao CreditanterieurDao;
	
	public Creditanterieur Findbymf(String nom){
		return CreditanterieurDao.Findbymf(nom);
	}
	 public Creditanterieur getcreditbyid(Integer id) {
		 return CreditanterieurDao.getcreditbyid(id);
	 }
	public void save(Creditanterieur creditanterieur){
		CreditanterieurDao.save(creditanterieur);
	}
	public Creditanterieur getCreditclientbyCaisseandclient(Creditanterieur cl){
		return CreditanterieurDao.getCreditclientbyCaisseandclient(  cl);
	}
	 public  List<Creditanterieur> getCreditanterieurbyclient(Clientgestat c ,String dates){
		 return CreditanterieurDao.getCreditanterieurbyclient(c, dates);
	 }
	public List<Creditanterieur> getListcreditdate(String date){
		return CreditanterieurDao.getListcreditdate(date);
	}
	public List<Creditanterieur> getAll(){
		return CreditanterieurDao.getAll();
	}
	 public List<Creditanterieur> getCreditanterieurbyCaisse(Caisse c){
		 return CreditanterieurDao.getCreditanterieurbyCaisse(c);
	 }
	public List<Creditanterieur> Findbynom(String nom) {
		return CreditanterieurDao.Findbynom(nom);
	}
	public void update(Creditanterieur creditanterieur){
		CreditanterieurDao.update(creditanterieur);
	}
	public void delete(Creditanterieur Creditanterieur){
		CreditanterieurDao.delete(Creditanterieur);
	}
	public Creditanterieur Findbycode(Integer codeCreditanterieur) {
		// TODO Auto-generated method stub
		return CreditanterieurDao.Findbycode(codeCreditanterieur);
	}
}
