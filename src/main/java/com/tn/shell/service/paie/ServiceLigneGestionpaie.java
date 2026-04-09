package com.tn.shell.service.paie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.paie.LigneGestiondao;
import com.tn.shell.dao.paie.LigneGestionpaiedao;
import com.tn.shell.model.paie.*;

 

@Service("ServiceLigneGestionpaie")
public class ServiceLigneGestionpaie {
	@Autowired
	LigneGestionpaiedao lignegestiondao;
	
	public void save(Lignepaiegestion c){
		lignegestiondao.save(c);
	}
	 
	public void update(Lignepaiegestion c){
		lignegestiondao.update(c); 
	}
	
	 
	
	public List<Lignepaiegestion> getAll(){
		return lignegestiondao.getAll();
	}
	
	 public List<Lignepaiegestion> getlistbypaie(Paie e){
		 return lignegestiondao.getlistbypaie(e);
	 }
	 public  Lignepaiegestion getlignebygestion(Lignegestion e){
		 return lignegestiondao.getlignebygestion(e);
	 }
}
