package com.tn.shell.service.paie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tn.shell.dao.paie.*;
import com.tn.shell.model.paie.*;
@Service("ServiceDegree")
public class ServiceDegree {
	@Autowired
	DegreeDAO DegreeDAO;
	
	public void save(Degree annee){
		DegreeDAO.save(annee);
	}
	
	public List<Degree> getAll(){
		return DegreeDAO.getAll();
	}
	public Degree findbyDesignation(Integer designation) {
		return DegreeDAO.findbyDesignation(designation);
	}
	public void update(Degree annee){
		DegreeDAO.update(annee);
	}
	public Degree findbyid(Integer id) {
		return DegreeDAO.findbyid(id);
	}
}
