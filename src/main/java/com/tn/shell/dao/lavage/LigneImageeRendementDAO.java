package com.tn.shell.dao.lavage;

import java.util.List;

import com.tn.shell.model.lavage.LigneImagerendement;
import com.tn.shell.model.lavage.Marque;
import com.tn.shell.model.shop.Produit;

public interface LigneImageeRendementDAO {
	public List<LigneImagerendement> getAll();
	public void save(LigneImagerendement c);
	public Integer getmaxcode();
	public void update(LigneImagerendement c);
	public LigneImagerendement getLigneImagerendementbyid(Integer id);
	public List<LigneImagerendement> getLigneImagerendementbyMarque(Marque f);
	public List<LigneImagerendement> getLigneImagerendementbyArticle(Produit f);
	
	  LigneImagerendement findByRendementIdAndPosition(Integer id,String position);
	 
}
