package com.tn.shell.service.gestat;

import java.util.List;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*; 
 

 

@Service("Serviceetatcheque")
public class Serviceetatcheque {
	@Autowired
	EtatchequeDAO EtatchequeDAO;
	
	public List<Etatcheque> getAll(){
		return EtatchequeDAO.getAll();
	}
	 
	public void save(Etatcheque c){
		EtatchequeDAO.save(c);
	}
	
	public void update(Etatcheque c){
		EtatchequeDAO.update(c);
	}
	 
	 
	public Etatcheque getmaxcode() {		 
		return EtatchequeDAO.getmaxcode();
	}
	 
	 
}
