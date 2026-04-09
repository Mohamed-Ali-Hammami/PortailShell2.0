package com.tn.shell.service.paie;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.paie.*;
import com.tn.shell.model.paie.*;
 

@Service("ServiceRappel")
public class ServiceRappel {
	@Autowired
	RappelDAO RappelDAO;
	
	public void save(Rappel Rappel){
    RappelDAO.save(Rappel);
	}
	public List<Rappel> getRappelsByEmployer(Employee e){
		return RappelDAO.getRappelsByEmployer(e);
	}
	public List<Rappel> getAll(Integer annee){
		return RappelDAO.getAll(annee);
	}
	
	public void update(Rappel Rappel){
		RappelDAO.update(Rappel);
	}
	public void delete(Rappel Rappel){
		RappelDAO.detele(Rappel);
	}
	public Rappel getRappelsByEmployee(Employee e,Integer annee){
		return RappelDAO.getRappelsByEmployee(e, annee);
	}
	
	public Rappel getMaxPointageconge(){
		return RappelDAO.getMaxPointageconge();
	}
}
