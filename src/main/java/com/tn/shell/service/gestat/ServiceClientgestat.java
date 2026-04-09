package com.tn.shell.service.gestat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*; 
 

@Service("ServiceClientgestat")
public class ServiceClientgestat {
	@Autowired
	ClientgestatDao  clientDao;
	
	public Clientgestat Findbymf(String nom){
		return clientDao.Findbymf(nom);
	}
	public void save(Clientgestat client){
		clientDao.save(client);
	}
	public double getAllmontantcredit() {
		return clientDao.  getAllmontantcredit() ;
	}
	public List<Clientgestat> getAll(){
		return clientDao.getAll();
	}
	public List<String> getAllnom(){
		return clientDao.getAllnom();
	}
	public List<Clientgestat> getclientenAvance(){
		return clientDao.getclientenAvance();
	}
	public List<Clientgestat> getclientendepassement(){
		return clientDao.getclientendepassement();
	}
	public Clientgestat Findbynom(String nom) {
		return clientDao.Findbynom(nom);
	}
	public void update(Clientgestat client){
		clientDao.update(client);
	}
	public void delete(Clientgestat client){
		clientDao.delete(client);
	}
	public Clientgestat Findbycode(Integer codeclient) {
		// TODO Auto-generated method stub
		return clientDao.Findbycode(codeclient);
	}
}
