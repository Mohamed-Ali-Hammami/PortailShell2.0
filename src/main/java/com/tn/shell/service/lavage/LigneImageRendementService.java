package com.tn.shell.service.lavage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.lavage.LigneImageeRendementDAO;
import com.tn.shell.model.lavage.LigneImagerendement;
import com.tn.shell.model.lavage.Marque;
import com.tn.shell.model.shop.Produit;

@Service("LigneImageRendementService")
public class LigneImageRendementService {
	
	@Autowired
	LigneImageeRendementDAO ligneImagerendementDAO;
	
	public List<LigneImagerendement> getAll(){
		return ligneImagerendementDAO.getAll();
	}
	public void save(LigneImagerendement c) {
		ligneImagerendementDAO.save(c);
	}
	public Integer getmaxcode() {
		return ligneImagerendementDAO.getmaxcode();
	}
	
	public LigneImagerendement findByRendementIdAndPosition(Integer id, String position) {
		return ligneImagerendementDAO.findByRendementIdAndPosition(id, position);
	}
	public void update(LigneImagerendement c) {
		ligneImagerendementDAO.update(c);
	}
	public LigneImagerendement getLigneImagerendementbyid(Integer id) {
		return ligneImagerendementDAO.getLigneImagerendementbyid(id);
	}
	public List<LigneImagerendement> getLigneImagerendementbyMarque(Marque f){
		return ligneImagerendementDAO.getLigneImagerendementbyMarque(f);
	}
	public List<LigneImagerendement> getLigneImagerendementbyArticle(Produit f){
		return ligneImagerendementDAO.getLigneImagerendementbyArticle(f);
	}
}
