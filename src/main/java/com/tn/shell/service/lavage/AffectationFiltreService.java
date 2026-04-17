package com.tn.shell.service.lavage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.lavage.AffectationDAO;
import com.tn.shell.model.lavage.AffectationFiltre;
import com.tn.shell.model.lavage.Model;

 

@Service("AffectationFiltreService")
public class AffectationFiltreService {
	
	@Autowired
	AffectationDAO AffectationFiltreDAO;
	
	public List<AffectationFiltre> getAll(){
		return AffectationFiltreDAO.getAll();
	}
	public void save(AffectationFiltre c) {
		AffectationFiltreDAO.save(c);
	}
	public Integer getmaxcode() {
		return AffectationFiltreDAO.getmaxcode();
	}
	public void update(AffectationFiltre c) {
		AffectationFiltreDAO.update(c);
	}
	public AffectationFiltre getAffectationFiltrebyid(Integer id) {
		return AffectationFiltreDAO.getAffectationFiltrebyid(id);
	}
	public List<AffectationFiltre> getAffectationFiltrebyModel(Model f){
		if (f == null || f.getId() == null) {
			return new ArrayList<AffectationFiltre>();
		}
		return AffectationFiltreDAO.getAffectationFiltrebyModel(f);
	}
	 
}
