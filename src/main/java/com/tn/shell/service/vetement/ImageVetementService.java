package com.tn.shell.service.vetement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.vetement.ImageVetementDAO;
import com.tn.shell.model.paie.Employee;
import com.tn.shell.model.vetement.Vetement;

@Service("ImagevetementService")
public class ImageVetementService {
	
	@Autowired
	ImageVetementDAO imagevetementDAO;
	
	 
	public List<Vetement> getVetementbyEmployee(Employee f){
		return imagevetementDAO.getVetementbyEmployee(f);
	}
	 
}
