package com.tn.shell.dao.paie;

import java.util.List;

import com.tn.shell.model.paie.Employee;
import com.tn.shell.model.vetement.Vetement;
import com.tn.shell.model.paie.LigneImageEmployee;

public interface ImageEmployeeDAO {
	 
	public List<LigneImageEmployee> getImagebyEmployee(Employee f);
	public  LigneImageEmployee getImagebyEmployeeandpositions(Employee f,String position) ;
}
