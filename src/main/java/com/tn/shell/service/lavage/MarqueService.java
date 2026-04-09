package com.tn.shell.service.lavage;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tn.shell.dao.lavage.MarqueDAO;
import com.tn.shell.model.lavage.Marque;
 

@Service("MarqueService")
public class MarqueService {
	
	@Autowired
	MarqueDAO marqueDAO;
	public Marque getMarquebyNom(String nom) {
		return marqueDAO.getMarquebyNom(nom);
	}
	public List<Marque> getAll(){
		return marqueDAO.getAll();
	}
	public void save(Marque c) {
		marqueDAO.save(c);
	}
	public Integer getmaxcode() {
		return marqueDAO.getmaxcode();
	}
	public void update(Marque c) {
		marqueDAO.update(c);
	}
	public Marque getMarquebyid(Integer id) {
		return marqueDAO.getMarquebyid(id);
	}
	 
}
