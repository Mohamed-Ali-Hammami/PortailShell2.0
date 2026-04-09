package com.tn.shell.dao.lavage;

import java.util.List;

import com.tn.shell.model.lavage.Imagelavage;
import com.tn.shell.model.lavage.Marque;
import com.tn.shell.model.shop.Produit;

public interface ImagelavageDAO {
	public List<Imagelavage> getAll();
	public void save(Imagelavage c);
	public Integer getmaxcode();
	public void update(Imagelavage c);
	public Imagelavage getImagelavagebyid(Integer id);
	public List<Imagelavage> getImagelavagebyMarque(Marque f);
	public List<Imagelavage> getImagelavagebyArticle(Produit f);
	 
}
