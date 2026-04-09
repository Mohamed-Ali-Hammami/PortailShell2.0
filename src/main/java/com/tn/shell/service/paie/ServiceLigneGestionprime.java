package com.tn.shell.service.paie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.paie.LigneGestiondao;
import com.tn.shell.dao.paie.LigneGestionpaiedao;
import com.tn.shell.dao.paie.LigneGestionpaieprimedao;
import com.tn.shell.model.paie.*;

 

@Service("ServiceLigneGestionprime")
public class ServiceLigneGestionprime {
	@Autowired
	LigneGestionpaieprimedao lignegestiondao;
	
	public void save(Lignepaiegestionprime c){
		lignegestiondao.save(c);
	}
	 
	public void update(Lignepaiegestionprime c){
		lignegestiondao.update(c); 
	}
	
	 
	
	public List<Lignepaiegestionprime> getAll(){
		return lignegestiondao.getAll();
	}
	
	 public List<Lignepaiegestionprime> getlistbypaie(Paieprime e){
		 return lignegestiondao.getlistbypaie(e);
	 }
	 public  Lignepaiegestionprime getlignebygestion(Lignegestion e){
		 return lignegestiondao.getlignebygestion(e);
	 }
}
