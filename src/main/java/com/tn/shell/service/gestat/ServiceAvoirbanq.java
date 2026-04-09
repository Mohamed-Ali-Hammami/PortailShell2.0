package com.tn.shell.service.gestat;

import java.util.Date;
import java.util.List;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*; 
 

 

@Service("ServiceAvoirbanq")
public class ServiceAvoirbanq {
	@Autowired
	AvoirbanqDAO avoirbancaireDAO;
	
	public List<Avoirbancaire> getAll(){
		return avoirbancaireDAO.getAll();
	} 
	public List<Avoirbancaire> getAvoibydates(String date){
		return avoirbancaireDAO.getavoirbydates(date);
	}
	public void save(Avoirbancaire c){
		avoirbancaireDAO.save(c);
	}
	public List<Avoirbancaire> getBETWENNDATES(Date d1,Date d2){
		return avoirbancaireDAO.getBETWENNDATES(d1, d2);
	}
	public void update(Avoirbancaire c){
		avoirbancaireDAO.update(c);
	}
	 
	public Integer getmaxcode() {		 
		return avoirbancaireDAO.getmaxcode();
	}
	 
	public Avoirbancaire getAvoirbancairebyid(Integer id) {
		return avoirbancaireDAO.getAvoirbyid(id);
	}
}
