package com.tn.shell.service.banque;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.banque.BanqueDao;
import com.tn.shell.model.banque.Banque;
 
 

@Service("ServiceBanque")
public class ServiceBanque {
	@Autowired
	BanqueDao banqueDao;
	
	 
	public void save(Banque Banque){
		banqueDao.save(Banque);
	}
 
	public List<Banque> getAll(){
		return banqueDao.getAll();
	}
	 
	public  Banque Findbynom(String nom) {
		return banqueDao.Findbynom(nom);
	}
	public void update(Banque Banque){
		banqueDao.update(Banque);
	}
	public void delete(Banque Banque){
		banqueDao.delete(Banque);
	}
	 
	public Banque Findbycode(Integer codeBanque) {
		// TODO Auto-generated method stub
		return banqueDao.Findbycode(codeBanque);
	}
}
