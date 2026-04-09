package com.tn.shell.service.lavage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.lavage.AffectationHuileDAO;
import com.tn.shell.model.lavage.AffectationHuile;
import com.tn.shell.model.lavage.Model;

 

@Service("AffectationHuileService")
public class AffectationHuileService {
	
	@Autowired
	AffectationHuileDAO  affectationHuileDAO;
	
	public List<AffectationHuile> getAll(){
		return affectationHuileDAO.getAll();
	}
	public void save(AffectationHuile c) {
		affectationHuileDAO.save(c);
	}
	public Integer getmaxcode() {
		return affectationHuileDAO.getmaxcode();
	}
	public void update(AffectationHuile c) {
		affectationHuileDAO.update(c);
	}
	public AffectationHuile getAffectationHuilebyid(Integer id) {
		return affectationHuileDAO.getAffectationHuilebyid(id);
	}
	public List<AffectationHuile> getAffectationHuilebyModel(Model f){
		return affectationHuileDAO.getAffectationHuilebyModel(f);
	}
	 
}
