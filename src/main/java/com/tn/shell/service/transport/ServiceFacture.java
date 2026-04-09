package com.tn.shell.service.transport;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.transport.FactureDAO;
import com.tn.shell.model.transport.Facture;

 

@Service("ServiceFacture")
public class ServiceFacture {
	@Autowired
	FactureDAO factureDAO;
	
	public void save(Facture facture){
    factureDAO.save(facture);
	}
	 public List<Facture> getfacturebetwenndate(Date d1,Date d2){
		 return factureDAO.getbydate(d1, d2);
	 }
	 public Facture getMaxfacture() {
		 return factureDAO.getMaxfacture();
	 }
	public Facture getfacturebycode(String code){
		return factureDAO.getfacturebycode(code);
	}
	public Facture getBLbycodes(String  code){
		return factureDAO.getBLbycodes(code);
	}
	 
	public Facture getBLbycode(Integer code){
		return factureDAO.getBLbycode(code);
	}
	public List<Facture> getAll(){
		return factureDAO.getAll();
	}
	public List<Facture> getAllPasager(){
		return factureDAO.getAllPasager();
	}
	public List<Facture> getAllTransport(){
		return factureDAO.getAllTransport();
	}
	public void update(Facture facture){
		factureDAO.update(facture);
	}
	public void delete(Facture facture){
		factureDAO.delete(facture);
	}
	public List<Facture> getbydate(Date d1, Date d2){
		return factureDAO.getbydate(d1, d2);
	}
}
