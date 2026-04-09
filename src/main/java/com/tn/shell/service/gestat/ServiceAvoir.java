package com.tn.shell.service.gestat;

import java.util.Date;
import java.util.List;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*; 
 

 

@Service("ServiceAvoir")
public class ServiceAvoir {
	@Autowired
	AvoirDAO AvoirDAO;
	
	public List<Avoir> getAll(){
		return AvoirDAO.getAll();
	}
	public List<Avoir> getBETWENNDATES(Date d1,Date d2){
		return AvoirDAO.getBETWENNDATES(d1, d2);
	}
	public List<Avoir> getavoirbydates(String date){
		return AvoirDAO.getavoirbydates(date);
	}
	public void save(Avoir c){
		AvoirDAO.save(c);
	}
	public void update(Avoir c){
		AvoirDAO.update(c);
	}
	 
	public Integer getmaxcode() {		 
		return AvoirDAO.getmaxcode();
	}
	 
	public Avoir getAvoirbyid(Integer id) {
		return AvoirDAO.getAvoirbyid(id);
	}
}
