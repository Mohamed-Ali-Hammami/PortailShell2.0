package com.tn.shell.service.gestat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*; 
 

@Service("ServiceChequereglement")
public class ServiceChequereglement {
	@Autowired
	ChequeredlementDao chequeDao;
	
	public Chequereglement Findbymf(String nom){
		return chequeDao.Findbymf(nom);
	}
	public void save(Chequereglement Cheque){
		chequeDao.save(Cheque);
	}
	 public List<Chequereglement> getListchequedate(String date){
		 return chequeDao.getListchequedate(date);
	 }
	public List<Chequereglement> getAll(){
		return chequeDao.getAll();
	}
	 public List<Chequereglement> getListchequeetatandbanque(Enumcheque etat,String banque){
		 return chequeDao.getListchequeetatandbanque(etat, banque);
	 }
	public List<Chequereglement> getListchequeetat(Enumcheque etat){
		return chequeDao.getListchequeetat(etat);
	}
	 
	public List<Chequereglement> Findbynom(String nom) {
		return chequeDao.Findbynom(nom);
	}
	public void update(Chequereglement Cheque){
		chequeDao.update(Cheque);
	}
	public void delete(Chequereglement Cheque){
		chequeDao.delete(Cheque);
	}
	 public List<Chequereglement> getChequebyCaisse(Caisse c){
		 return chequeDao.getChequebyCaisse(c) ;
	 }
	public Chequereglement Findbycode(Integer codeCheque) {
		// TODO Auto-generated method stub
		return chequeDao.Findbycode(codeCheque);
	}
}
