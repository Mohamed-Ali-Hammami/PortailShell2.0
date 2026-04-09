package com.tn.shell.service.paie;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tn.shell.dao.paie.*;
import com.tn.shell.model.paie.*;
 

@Service("ServicePret")
public class ServicePret {
	@Autowired
	PretDAO pretDAO;
	
	public void save(Pret pret){
    pretDAO.save(pret);
	}
	 
	public List<Pret> getAll(){
		return pretDAO.getAll();
	}
	
	public void update(Pret pret){
		pretDAO.update(pret);
	}
	public void delete(Pret pret){
		pretDAO.detele(pret);
	}
	 
	public Pret getPretencours(Employee e){
		return pretDAO.getPretencours(e);
	}
}
