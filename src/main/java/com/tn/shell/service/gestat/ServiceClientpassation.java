package com.tn.shell.service.gestat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*; 
 

@Service("ServiceClientpassation")
public class ServiceClientpassation {
	@Autowired
	ClientpassationDao  clientDao;
	
	public Clientpassation Findbymf(String nom){
		return clientDao.Findbymf(nom);
	}
	public void save(Clientpassation client){
		clientDao.save(client);
	}
	public List<Clientpassation> finByReste(){
		return clientDao.finByReste();
	}
 
	public List<Clientpassation> getAll(){
		return clientDao.getAll();
	}
	public List<String> getAllnom(){
		return clientDao.getAllnom();
	}
	public List<Clientpassation> getclientenAvance(){
		return clientDao.getclientenAvance();
	}
	public List<Clientpassation> getclientendepassement(){
		return clientDao.getclientendepassement();
	}
	public Clientpassation Findbynom(String nom) {
		return clientDao.Findbynom(nom);
	}
	public void update(Clientpassation client){
		clientDao.update(client);
	}
	public void delete(Clientpassation client){
		clientDao.delete(client);
	}
	public Clientpassation Findbycode(Integer codeclient) {
		// TODO Auto-generated method stub
		return clientDao.Findbycode(codeclient);
	}
}
