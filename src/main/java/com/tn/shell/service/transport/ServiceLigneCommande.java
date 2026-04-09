package com.tn.shell.service.transport;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.transport.LigneCommandedao;
import com.tn.shell.model.shop.Produit;
import com.tn.shell.model.transport.Bonlivraison;
import com.tn.shell.model.transport.Facture;
import com.tn.shell.model.transport.Lignecommande;
 
@Service("ServiceLigneCommande")
public class ServiceLigneCommande {
	@Autowired
	LigneCommandedao ligneCommandedao;
	
	public void save(Lignecommande c){
		ligneCommandedao.save(c);
	}
	 public List<Lignecommande> getLcbyBL(Bonlivraison bl){
		 return ligneCommandedao.getLcbyBL(bl);
	 }
	 public Lignecommande getLignecommandebyproduit(Integer p,Facture f) {
		 return ligneCommandedao.getLignecommandebyproduit(p,f);
	 }
		public double sumdtransport(Date d1,Date d2) {
			return ligneCommandedao.sumdtransport(d1, d2);
		}
	 public List<Lignecommande>getAllventepardate(String s){
		 return ligneCommandedao.getAllventepardate(s);
	 }
	 public double sumdtransportv(Date d1,Date d2,int id) {
		 return ligneCommandedao.sumdtransportv(d1, d2, id);
	 }
	 public List<Lignecommande> getLcbyf(Facture bl){
		 return ligneCommandedao.getLcbyf(bl);
	 }
	public void update(Lignecommande c){
		ligneCommandedao.update(c); 
	}
	
	public void delete(Lignecommande c){
		ligneCommandedao.delete(c);
	}
	 public List<Lignecommande>getAllventepardatearticle(Produit a,String s){
		 return ligneCommandedao.getAllventepardatearticle(a, s);
	 }
	public List<Lignecommande> getAll(){
		return ligneCommandedao.getAll();
	}
	 
}
