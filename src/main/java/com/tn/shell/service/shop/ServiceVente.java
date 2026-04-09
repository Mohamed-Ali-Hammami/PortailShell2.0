package com.tn.shell.service.shop;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.shop.*;
import com.tn.shell.model.shop.*;


 

@Service("ServiceVente")
public class ServiceVente {
	@Autowired
	VenteDAO VenteDAO;
	
	public List<Vente> getAll(){
		return VenteDAO.getAll();
	}
	 
	public void save(Vente c){
		VenteDAO.save(c);
	}
	public void update(Vente c){
		VenteDAO.update(c);
	}
	 
	public Integer getmaxcode() {		 
		return VenteDAO.getmaxcode();
	}
	 
	public Vente getVentebyid(Integer id) {
		return VenteDAO.getVentebyid(id);
	}
}
