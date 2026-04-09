package com.tn.shell.service.paie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.paie.LigneGestiondao;
import com.tn.shell.dao.paie.LigneGestionpaiedao;
import com.tn.shell.dao.paie.LigneGestionpaierappeldao;
import com.tn.shell.model.paie.*;

 

@Service("ServiceLigneGestionpaierappel")
public class ServiceLigneGestionpaierappel {
	@Autowired
	LigneGestionpaierappeldao lignegestiondao;
	
	public void save(Lignepaiegestionrappel c){
		lignegestiondao.save(c);
	}
	 
	public void update(Lignepaiegestionrappel c){
		lignegestiondao.update(c); 
	}
	
	 
	
	public List<Lignepaiegestionrappel> getAll(){
		return lignegestiondao.getAll();
	}
	
	 public List<Lignepaiegestionrappel> getlistbypaie(Rappel e){
		 return lignegestiondao.getlistbypaie(e);
	 }
	 public  Lignepaiegestionrappel getlignebygestion(Lignegestion e){
		 return lignegestiondao.getlignebygestion(e);
	 }
}
