package com.tn.shell.service.transport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.transport.VheculeDao;
import com.tn.shell.model.transport.*;
 

@Service("ServiceVhecule")
public class ServiceVhecule {
	@Autowired
	VheculeDao vheculeDao;
	
	public Vhecule Findbymf(String nom){
		return vheculeDao.Findbymf(nom);
	}
	public List<String> getAllnom(){
		return vheculeDao.getAllnom();
	}
	public void save(Vhecule client){
		vheculeDao.save(client);
	}
	public List<Vhecule> getAll(){
		return vheculeDao.getAll();
	}
	public Vhecule Findbynom(String nom) {
		return vheculeDao.Findbynom(nom);
	}
	public void update(Vhecule client){
		vheculeDao.update(client);
	}
	public void delete(Vhecule client){
		vheculeDao.delete(client);
	}
	public Vhecule Findbycode(Integer codevhecule) {
		// TODO Auto-generated method stub
		return vheculeDao.Findbycode(codevhecule);
	}
}
