package com.tn.shell.service.paie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.paie.*;
import com.tn.shell.model.paie.*;

@Service("ServiceCategorie")
public class ServiceCategorie {
	@Autowired
	CategorieDAO categorieDAO;
	
	public void save(Categorie annee){
		categorieDAO.save(annee);
	}
	public List<Categorie> getcategoriebycat(Integer d){

		return categorieDAO.getcategoriebycat(d);
	}
	
	public List<Categorie> getAll(){
		return categorieDAO.getAll();
	}
	public  List<Categorie> findbyDesignation(TypeCat designation) {
		return categorieDAO.findbyDesignation(designation);
	}
	public void update(Categorie annee){
		categorieDAO.update(annee);
	}
	public Categorie findbyid(Integer id) {
		return categorieDAO.findbyid(id);
	}
	public List<Categorie> getcategoriebydegre(Cat d){
		return categorieDAO.getcategoriebydegre(d);
	}
	public Categorie findbydegreeandcat(Integer cat,Integer degree) {
		return categorieDAO.findbydegreeandcat(cat, degree);
		
	}
}
