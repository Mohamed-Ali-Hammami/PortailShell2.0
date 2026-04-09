package com.tn.shell.dao.vetement;

import java.util.List;

import com.tn.shell.model.paie.Employee;
import com.tn.shell.model.vetement.Vetement;

public interface ImageVetementDAO {
	 
	public List<Vetement> getVetementbyEmployee(Employee f);
	 
}
