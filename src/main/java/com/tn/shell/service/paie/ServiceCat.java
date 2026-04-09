package com.tn.shell.service.paie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.paie.*;
import com.tn.shell.model.paie.*;

@Service("ServiceCat")
public class ServiceCat {
	@Autowired
	CatDAO CatDAO;
	
	public void save(Cat annee){
		CatDAO.save(annee);
	}
	
	public List<Cat> getAll(){
		return CatDAO.getAll();
	}
	public List<Cat>  findbyDesignation(TypeCat designation) {
		return CatDAO.findbyDesignation(designation);
	}
	public void update(Cat annee){
		CatDAO.update(annee);
	}
	public Cat findbyid(Integer id) {
		return CatDAO.findbyid(id);
	}
}
