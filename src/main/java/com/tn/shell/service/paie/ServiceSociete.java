package com.tn.shell.service.paie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.paie.*;
import com.tn.shell.model.paie.*;
@Service("ServiceSociete")
public class ServiceSociete {
	@Autowired
	SocieteDAO societeDAO;
	
	public void save(Societe s){
		societeDAO.save(s);
	} 
	public void update(Societe s){
		societeDAO.update(s);
	}
	public List<Societe> getAll(){
		return societeDAO.getAll();
	}
}
