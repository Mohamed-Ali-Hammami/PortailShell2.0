package com.tn.shell.service.paie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.paie.LigneGestiondao;
import com.tn.shell.model.paie.*;

 

@Service("ServiceLigneGestion")
public class ServiceLigneGestion {
	@Autowired
	LigneGestiondao lignegestiondao;
	
	public void save(Lignegestion c){
		lignegestiondao.save(c);
	}
	 
	public void update(Lignegestion c){
		lignegestiondao.update(c); 
	}
	
	public void delete(Lignegestion c){
		lignegestiondao.delete(c);
	}
	
	public List<Lignegestion> getAll(){
		return lignegestiondao.getAll();
	}
	
	 public List<Lignegestion> getlistbyemplyee(Employee e){
		 return lignegestiondao.getlistbyemplyee(e);
	 }
	 public  Lignegestion getlignebygestion(Gestion e){
		 return lignegestiondao.getlignebygestion(e);
	 }
}
