package com.tn.shell.service.paie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.paie.ImageEmployeeDAO;
import com.tn.shell.dao.vetement.ImageVetementDAO;
import com.tn.shell.model.paie.Employee;
import com.tn.shell.model.vetement.Vetement;
import com.tn.shell.model.paie.LigneImageEmployee;

@Service("ImageEmployeeService")
public class ImageEmployeeService {
	
	@Autowired
	ImageEmployeeDAO imagevetementDAO;
	

	public  LigneImageEmployee getImagebyEmployeeandpositions(Employee f,String position) {
		return imagevetementDAO.getImagebyEmployeeandpositions(f,position);
	}
	public List<LigneImageEmployee> getImagetbyEmployee(Employee f){
		return imagevetementDAO.getImagebyEmployee(f);
	}
	 
}
