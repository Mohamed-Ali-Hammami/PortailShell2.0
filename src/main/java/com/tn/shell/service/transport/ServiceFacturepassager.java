package com.tn.shell.service.transport;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.transport.FacturepassagerDAO;
import com.tn.shell.model.transport.Facturepassager;

 

@Service("ServiceFacturepassager")
public class ServiceFacturepassager {
	@Autowired
	FacturepassagerDAO FacturepassagerDAO;
	
	public void save(Facturepassager Facturepassager){
    FacturepassagerDAO.save(Facturepassager);
	}
	 public Facturepassager getMaxfacture() {
		 return FacturepassagerDAO.getMaxfacture();
	 }
	public Facturepassager getfacturebycode(String code){
		return FacturepassagerDAO.getfacturebycode(code);
	}
	public Facturepassager getBLbycodes(String  code){
		return FacturepassagerDAO.getBLbycodes(code);
	}
	public List<Facturepassager> getfacturebetwenndate(Date d1,Date d2){
		return FacturepassagerDAO.getbydate(d1, d2);
	}
	public Facturepassager getBLbycode(Integer code){
		return FacturepassagerDAO.getBLbycode(code);
	}
	public List<Facturepassager> getAll(){
		return FacturepassagerDAO.getAll();
	}
	public List<Facturepassager> getAllPasager(){
		return FacturepassagerDAO.getAllPasager();
	}
	public List<Facturepassager> getAllTransport(){
		return FacturepassagerDAO.getAllTransport();
	}
	public void update(Facturepassager Facturepassager){
		FacturepassagerDAO.update(Facturepassager);
	}
	public void delete(Facturepassager Facturepassager){
		FacturepassagerDAO.delete(Facturepassager);
	}
	public List<Facturepassager> getbydate(Date d1, Date d2){
		return FacturepassagerDAO.getbydate(d1, d2);
	}
}
