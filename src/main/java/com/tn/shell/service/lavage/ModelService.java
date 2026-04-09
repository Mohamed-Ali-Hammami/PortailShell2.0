package com.tn.shell.service.lavage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.lavage.ModelDAO;
import com.tn.shell.model.lavage.Marque;
import com.tn.shell.model.lavage.Model;
import com.tn.shell.model.shop.Produit;

@Service("ModelService")
public class ModelService {
	
	@Autowired
	ModelDAO modelDAO;
	
	public List<Model> getAll(){
		return modelDAO.getAll();
	}
	public void save(Model c) {
		modelDAO.save(c);
	}
	public Integer getmaxcode() {
		return modelDAO.getmaxcode();
	}
	public void update(Model c) {
		modelDAO.update(c);
	}
	public Model getModelbyid(Integer id) {
		return modelDAO.getModelbyid(id);
	}
	public List<Model> getModelbyMarque(Marque f){
		return modelDAO.getModelbyMarque(f);
	}
	public List<Model> getModelbyArticle(Produit f){
		return modelDAO.getModelbyArticle(f);
	}
}
