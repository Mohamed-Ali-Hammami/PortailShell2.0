package com.tn.shell.service.gestat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*;
import com.tn.shell.model.shop.Produit;
 

@Service("ServicePompe")
public class ServicePompe {
	@Autowired
	PompeDao PompeDao;
	
	public Pompe Findbymf(String nom){
		return PompeDao.Findbymf(nom);
	}
	public void save(Pompe Pompe){
		PompeDao.save(Pompe);
	}
	public List<Pompe> getAll(){
		return PompeDao.getAll();
	}
	public List<String> getAllnom(){
		return PompeDao.getAllnom();
	}
	public Pompe Findbynom(String nom) {
		return PompeDao.Findbynom(nom);
	}
	public void update(Pompe Pompe){
		PompeDao.update(Pompe);
	}
	public void delete(Pompe Pompe){
		PompeDao.delete(Pompe);
	}
	public Pompe Findbycode(Integer codePompe) {
		// TODO Auto-generated method stub
		return PompeDao.Findbycode(codePompe);
	}
}
