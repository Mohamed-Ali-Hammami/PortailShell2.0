package com.tn.shell.service.transport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.transport.LigneCommandedao;
import com.tn.shell.dao.transport.LigneCommandepassdao;
import com.tn.shell.model.transport.Bonlivraison;
import com.tn.shell.model.transport.Facture;
import com.tn.shell.model.transport.Facturepassager;
import com.tn.shell.model.transport.Lignecommande;
import com.tn.shell.model.transport.Lignecommandepass;
 
@Service("ServiceLigneCommandepass")
public class ServiceLigneCommandepass {
	@Autowired
	LigneCommandepassdao ligneCommandedao;
	
	public void save(Lignecommandepass c){
		ligneCommandedao.save(c);
	}
	 public List<Lignecommandepass> getLcbyBL(Bonlivraison bl){
		 return ligneCommandedao.getLcbyBL(bl);
	 }
	 public Lignecommandepass getLignecommandebyproduit(Integer p,Facture f) {
		 return ligneCommandedao.getLignecommandebyproduit(p,f);
	 }
	 public List<Lignecommandepass> getLcbyf(Facturepassager bl){
		 return ligneCommandedao.getLcbyf(bl);
	 }
	public void update(Lignecommandepass c){
		ligneCommandedao.update(c); 
	}
	
	public void delete(Lignecommandepass c){
		ligneCommandedao.delete(c);
	}
	
	public List<Lignecommandepass> getAll(){
		return ligneCommandedao.getAll();
	}
	 
}
