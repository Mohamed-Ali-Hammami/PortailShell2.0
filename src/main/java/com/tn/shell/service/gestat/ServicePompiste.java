package com.tn.shell.service.gestat;

import java.util.List;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*;
import com.tn.shell.model.gestat.Poste;
 

 

@Service("ServicePompiste")
public class ServicePompiste {
	@Autowired
	PompisteDAO pompisteDAO;
	
	public List<Pompiste> getAll(){
		return pompisteDAO.getAll();
	 
	 }
	public void save(Pompiste c){
		pompisteDAO.save(c);
	}
	public Pompiste getPompistebydate(String date,Poste poste) {
		return pompisteDAO.getPompistebydate(date, poste);
	}
	public void update(Pompiste c){
		pompisteDAO.update(c);
	}
	 
	public Integer getmaxcode() {		 
		return pompisteDAO.getmaxcode();
	}
	 
	public Pompiste getPompistebyid(Integer id) {
		return pompisteDAO.getPompistebyid(id);
	}
}
