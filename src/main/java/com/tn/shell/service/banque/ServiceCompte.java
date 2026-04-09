package com.tn.shell.service.banque;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
 
 import com.tn.shell.dao.banque.CompteDao;
import com.tn.shell.model.banque.Compte;

@Service("ServiceCompte")
public class ServiceCompte {
	@Autowired
	 CompteDao compteDao;
	 
	public void save(Compte compte){
		compteDao.save(compte);
	}
 
	public List<Compte> getAll(){
		return compteDao.getAll();
	}
	 
	public  Compte Findbynom(String nom) {
		return compteDao.Findbynom(nom);
	}
	public void update(Compte compte){
		compteDao.update(compte);
	}
	public void delete(Compte compte){
		compteDao.delete(compte);
	}
	 
	public Compte Findbycode(Integer codeCompte) {
		// TODO Auto-generated method stub
		return compteDao.Findbycode(codeCompte);
	}
}
