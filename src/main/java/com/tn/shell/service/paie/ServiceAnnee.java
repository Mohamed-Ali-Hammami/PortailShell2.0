package com.tn.shell.service.paie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.paie.*;
import com.tn.shell.model.paie.*;

@Service("ServiceAnnee")
public class ServiceAnnee {
	@Autowired
	AnneeDAO anneeDAO;
	
	public void save(Annee annee){
		anneeDAO.save(annee);
	}
	
	public List<Annee> getAll(){
		return anneeDAO.getAll();
	}
	public Annee findbyDesignation(String designation) {
		return anneeDAO.findbyDesignation(designation);
	}
	public void update(Annee annee){
		anneeDAO.update(annee);
	}
	public Annee findbyid(Integer id) {
		return anneeDAO.findbyid(id);
	}
}
