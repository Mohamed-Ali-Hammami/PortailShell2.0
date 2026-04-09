package com.tn.shell.service.transport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.transport.*;
import com.tn.shell.model.transport.*;

 

@Service("ServiceClient")
public class ServiceClient {
	@Autowired
	ClientDao clientDao;
	
	public Client Findbymf(String nom){
		return clientDao.Findbymf(nom);
	}
	public void save(Client client){
		clientDao.save(client);
	}
	public List<Client> getAll(){
		return clientDao.getAll();
	}
	public List<String> getAllnom(){
		return clientDao.getAllnom();
	}
	public Client Findbynom(String nom) {
		return clientDao.Findbynom(nom);
	}
	public void update(Client client){
		clientDao.update(client);
	}
	public void delete(Client client){
		clientDao.delete(client);
	}
	public Client Findbycode(Integer codeclient) {
		// TODO Auto-generated method stub
		return clientDao.Findbycode(codeclient);
	}
}
