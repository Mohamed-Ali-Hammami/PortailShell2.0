package com.tn.shell.dao.lavage;

import java.util.List;

import com.tn.shell.model.lavage.Marque;
import com.tn.shell.model.lavage.Model;
import com.tn.shell.model.shop.Achat;
import com.tn.shell.model.shop.Produit;
import com.tn.shell.model.shop.Status;

public interface ModelDAO {
	public List<Model> getAll();
	public void save(Model c);
	public Integer getmaxcode();
	public void update(Model c);
	public Model getModelbyid(Integer id);
	public List<Model> getModelbyMarque(Marque f);
	public List<Model> getModelbyArticle(Produit f);
	 
}
