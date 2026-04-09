package com.tn.shell.service.gestat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*; 
 

@Service("ServiceCheque")
public class ServiceCheque {
	@Autowired
	ChequeDao chequeDao;
	
	public Cheque Findbymf(String nom){
		return chequeDao.Findbymf(nom);
	}
	public void save(Cheque Cheque){
		chequeDao.save(Cheque);
	}
	 public List<Cheque> getListchequebetweendate(Date date1,Date date2){
		 return chequeDao.getListchequebetweendate(date1, date2);
	 }
	 public List<Cheque> getListchequedate(String date){
		 return chequeDao.getListchequedate(date);
	 }
	 public List<Cheque> getListchequeetatandbanque(Enumcheque etat,String banque){
		 return chequeDao.getListchequeetatandbanque(etat, banque);
	 }
	public List<Cheque> getAll(){
		return chequeDao.getAll();
	}
	public List<Cheque> getListchequeetat(Enumcheque etat){
		return chequeDao.getListchequeetat(etat);
	}
	 
	public List<Cheque> Findbynom(String nom) {
		return chequeDao.Findbynom(nom);
	}
	public void update(Cheque Cheque){
		chequeDao.update(Cheque);
	}
	public void delete(Cheque Cheque){
		chequeDao.delete(Cheque);
	}
	 public List<Cheque> getChequebyCaisse(Caisse c){
		 return chequeDao.getChequebyCaisse(c) ;
	 }
	public Cheque Findbycode(Integer codeCheque) {
		// TODO Auto-generated method stub
		return chequeDao.Findbycode(codeCheque);
	}
}
