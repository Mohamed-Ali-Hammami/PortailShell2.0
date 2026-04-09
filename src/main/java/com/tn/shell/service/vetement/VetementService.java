package com.tn.shell.service.vetement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.vetement.VetementDAO;
import com.tn.shell.model.paie.Employee;
import com.tn.shell.model.vetement.Vetement;

 

@Service("VetementService")
public class VetementService {
	
	@Autowired
	VetementDAO vetementDAO;
	
	public List<Vetement> getAll(){
		return vetementDAO.getAll();
	}
	public void save(Vetement c) {
		vetementDAO.save(c);
	}
	public Integer getmaxcode() {
		return vetementDAO.getmaxcode();
	}
	 
	public Vetement getVetementbyid(Integer id) {
		return vetementDAO.getVetementbyid(id);
	}
	public List<Vetement> getVetementbyEmployee(Employee e){
		return vetementDAO.getVetementbyEmployee(e);
	}
	 
}
