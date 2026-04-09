package com.tn.shell.dao.vetement;

import java.util.List;

import com.tn.shell.model.paie.Employee;
import com.tn.shell.model.vetement.Vetement;

 

public interface VetementDAO {
	public List<Vetement> getAll();
	public void save(Vetement c);
	public Integer getmaxcode();
	 
	public Vetement getVetementbyid(Integer id);
	public List<Vetement> getVetementbyEmployee(Employee f);
 
	 
}
