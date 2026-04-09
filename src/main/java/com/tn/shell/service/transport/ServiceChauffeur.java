package com.tn.shell.service.transport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.transport.*;
import com.tn.shell.model.transport.*;

 

@Service("ServiceChauffeur")
public class ServiceChauffeur {
	@Autowired
	ChauffeurDao chauffeurDao;
	public List<String> getAllnom(){
		return chauffeurDao.getAllnom();
	}
	 
	public Chauffeur Findbymf(String nom){
		return chauffeurDao.Findbymf(nom);
	}
	public void save(Chauffeur client){
		chauffeurDao.save(client);
	}
	public List<Chauffeur> getAll(){
		return chauffeurDao.getAll();
	}
	public Chauffeur Findbynom(String nom) {
		return chauffeurDao.Findbynom(nom);
	}
	public void update(Chauffeur client){
		chauffeurDao.update(client);
	}
	public void delete(Chauffeur client){
		chauffeurDao.delete(client);
	}
	public Chauffeur Findbycode(Integer codechaffeur) {
		// TODO Auto-generated method stub
		return chauffeurDao.Findbycode(codechaffeur);
	}
}
