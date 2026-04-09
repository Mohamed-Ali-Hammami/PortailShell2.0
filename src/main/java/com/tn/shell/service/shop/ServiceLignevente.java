package com.tn.shell.service.shop;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tn.shell.dao.shop.*;
import com.tn.shell.model.shop.*;


@Service("ServiceLignevente")
public class ServiceLignevente {
	@Autowired
	LigneVenteDAO ligneTransfertdao;

	public void save(Lignevente typearticle) {
		ligneTransfertdao.save(typearticle);
	}
	public List<Lignevente> getAllbyNumticket(Integer ticket){
		return ligneTransfertdao.getAllbyNumticket(ticket);
	}
	 public double getmontantbetwendate(Date d1,Date d2) {
		 return ligneTransfertdao.getmontantbetwendate(d1, d2);
	 }
	 public double getAllventeparDateandproduit2(Date d,Date d2, Produit p) {
		 return ligneTransfertdao.getAllventeparDateandproduit2(d, d2, p);
	 }
	 public double getProfilBrutParFamillebetwendate2(Date d1,Date d2,int f ) {
		 return ligneTransfertdao.getProfilBrutParFamillebetwendate2(d1, d2, f);
	 }
	 public double getmargebetwendate(Date d1,Date d2) {
		 return ligneTransfertdao.getmargebetwendate( d1,  d2);
	 }
	public Lignevente getlignebyid(Integer id) {
		return ligneTransfertdao.getlignebyid(id);
	}
	public double getAllventeparDateandproduit(String d,Produit p) {
		return ligneTransfertdao.getAllventeparDateandproduit(d, p);
	}
	 public List<Lignevente> getAllventeparposteandDate(String d ){
		 return ligneTransfertdao.getAllventeparposteandDate( d );
	 }
	 public List<Lignevente> getbetwendates(Date d1,Date d2,Produit p){
		 return ligneTransfertdao.getbetwendates(d1, d2,p);
	 }
	 public List<Lignevente> getbetwendate(Date d1,Date d2){
		 return ligneTransfertdao.getbetwendate(d1, d2);
	 }
	 public List<Lignevente> getAllventeparDateandp(String d,Produit p){
		 return ligneTransfertdao.getAllventeparDateandp(d, p);
	 }
	 public List<Lignevente> getAllventeparDate(String d){
		 return ligneTransfertdao.getAllventeparDate(d);
	 }
	public List<Lignevente> getAll() {
		return ligneTransfertdao.getAll();
	}
	public List<Lignevente> getAllventeparposteandDate33(String d ,Poste poste){
		return ligneTransfertdao.getAllventeparposteandDate33(d,poste);
	}
	public List<Lignevente> getAllventeparparfamille3(String f ,String d ,Poste poste){
		return ligneTransfertdao.getAllventeparparfamille3( f ,  d ,  poste);
	}
	public List<Lignevente> getAllventeparposteandDate33(String f ,String d ,Poste poste){
		return ligneTransfertdao.getAllventeparposteandDate33(f ,  d ,  poste);
	}
	public List<Lignevente> getAllventeparparfamille(String f ,String d ){
		return ligneTransfertdao.getAllventeparparfamille(f, d);
	}
	public List<Lignevente> getAllventeparparfamille2(String f ,String d,Poste poste ){
		return ligneTransfertdao.getAllventeparparfamille2(f, d,poste);
	}
	 
	 public Lignevente getmaxlignevente() {
		 return ligneTransfertdao.getmaxlignevente();
	 }
	 public List<Lignevente> getAllventeparposteandDate2(String d ,Poste poste){
		 return ligneTransfertdao.getAllventeparposteandDate2(d, poste);
	 }
	 public List<Lignevente> getAllventeparposteandDate3(String d ,Poste poste){
		 return ligneTransfertdao.getAllventeparposteandDate3(d, poste);
	 }
	public List<Lignevente> getAllventeparposte(String d ) {
		return ligneTransfertdao.getAllventeparposte(d);
	}
	 public List<Lignevente> getAllbyticket(Ticket ticket){
		 return ligneTransfertdao.getAllbyticket(ticket);
	 }

	public void update(Lignevente typearticle) {
		ligneTransfertdao.update(typearticle);
	}

	public void delete(Lignevente typearticle) {
		ligneTransfertdao.delete(typearticle);

	}
	public List<Lignevente> getAllventeparposteandDate(String d ,Poste poste){
		 return ligneTransfertdao.getAllventeparposteandDate(d, poste);
	}
	public List<Lignevente> getAllventeparposteNegatif(String d, Poste poste) {
		return ligneTransfertdao.getAllventeparposteNegatif(d, poste);
	}
	public List<Lignevente> getAllbyProduit(Produit p){
		return ligneTransfertdao.getAllbyProduit(p);
	}

	
	 
	 public double getProfilBrutParFamillebetwendate(Date d1,Date d2,Famillearticle f ){
		return ligneTransfertdao.getProfilBrutParFamillebetwendate(  d1, d2, f );
	}

	 public double getTotalttcParFamillebetwendate(Date d1,Date d2,Famillearticle f ) {
		return ligneTransfertdao.getTotalttcParFamillebetwendate(d1, d2,  f );
	}

	 public double getQuantiteParFamillebetwendate(Date d1,Date d2,Famillearticle f ) {
		return ligneTransfertdao.getQuantiteParFamillebetwendate(  d1, d2,  f );
	}
	 public double getTotalTCtbytDate(String d2) {
		 return ligneTransfertdao.getTotalTCtbytDate(d2);
	 }
}
