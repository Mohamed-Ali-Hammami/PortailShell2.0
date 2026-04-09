package com.tn.shell.service.shop;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tn.shell.dao.shop.*;
import com.tn.shell.model.lavage.TypeLavage;
import com.tn.shell.model.paie.Employee;
import com.tn.shell.model.shop.*;


@Service("ServiceRendement")
public class ServiceRendement {
	@Autowired
	rendementDAO ligneTransfertdao;
	public double getnbvbetwendate(String d1, String d2, Employee e) {
		return ligneTransfertdao.getnbvbetwendate(d1, d2, e);
	}
	public double getMontantvbetwendate(String d1, String d2, Employee e) {
		return ligneTransfertdao.getMontantvbetwendate(d1,d2,e);
	}
	
	public double getnbvbetwendates(String d1, String d2,TypeLavage typelavage) {
		return ligneTransfertdao.getnbvbetwendates(d1, d2, typelavage);
	}
	 public double getRecettebetwendates(String d1, String d2,TypeLavage typelavage) {
		 return ligneTransfertdao.getRecettebetwendates(d1, d2, typelavage);
	 }
	public void save(Rendement typearticle) {
		ligneTransfertdao.save(typearticle);
	}
	public double getnbvBydate2(String d1,Poste poste,TypeLavage typelavage) {
		return ligneTransfertdao.getnbvBydate2(d1,poste, typelavage);
	}
	public double getmontantvBydate2(String d1,Poste poste,TypeLavage typelavage) {
		return ligneTransfertdao.getmontantvBydate2(d1,poste, typelavage);
	}
	public double getnbvBydate(String d1, Employee e) { 
		return ligneTransfertdao.getnbvBydate(d1, e);
	}
	public double getmontantvBydate(String d1, Employee e) {
		return ligneTransfertdao.getmontantvBydate(d1, e);
	}
	public List<Rendement> getAllbymatricule(String d){
		return ligneTransfertdao.getAllbymatricule(d);
	}
	public List<Rendement> getAllventeparDateandmployee(String d,Employee e){
		return ligneTransfertdao.getAllventeparDateandmployee(d, e);
	}
	public Rendement findByid(Integer id) {
		return ligneTransfertdao.findByid(id);
	}
	public List<Rendement> getAllventeparDate3(String d, Employee e) {
		return ligneTransfertdao.getAllventeparDate3(d, e);
	}
	public List<Rendement> getbetwendatesAndtypelavageAndPoste(Date d1,Date d2,TypeLavage typelavage ,Poste poste){
		return ligneTransfertdao.getbetwendatesAndtypelavageAndPoste(d1,d2,typelavage,poste);
	}
	public List<Rendement> getbetwendatesAndtypelavage(Date d1,Date d2,TypeLavage typelavage){
		return ligneTransfertdao.getbetwendatesAndtypelavage(d1, d2, typelavage);
	}
	 public List<Rendement> getAllventeparposteandDate(String d ){
		 return ligneTransfertdao.getAllventeparposteandDate( d );
	 }
	 public List<Produit>   getProduitbetwendatesAndPoste(Date d1, Date d2, Poste poste, TypeLavage typelavage){
		 return ligneTransfertdao.getProduitbetwendatesAndPoste(d1, d2, poste, typelavage);
	 }
		public List<Employee>   getEmployeebetwendatesAndPoste(Date d1, Date d2, Poste poste, TypeLavage typelavage){
			return ligneTransfertdao.getEmployeebetwendatesAndPoste(d1, d2, poste, typelavage);
		}
	 public List<Rendement> getAllventeparDateAndEmployeeAndposte(String d, Employee e,Poste poste){
		 return ligneTransfertdao.getAllventeparDateAndEmployeeAndposte(d,e,poste);
	 }
	 
	 public List<Rendement> getAllventeparDateAndServiceAndposte(String d, Produit e,Poste poste){
		 return ligneTransfertdao.getAllventeparDateAndServiceAndposte(d,e,poste);
	 }
	 public List<String> totalheureParlaveurAndposte(String d,Employee e,Poste poste){
		 return ligneTransfertdao.totalheureParlaveurAndposte(d,e,poste);
	 }
		public List<String> totalheureParServiceaNDpOSTE(String d,Produit e,Poste poste){
			return ligneTransfertdao.totalheureParServiceaNDpOSTE(d,e,poste);
		}
	 
	 public List<Rendement> getAllventeparDate3AndProduit(String d, Produit e) {
		 return ligneTransfertdao.getAllventeparDate3AndProduit(d, e);
	 }
	 public List<String> totalheureParlaveur(String d,Employee e){
		 return ligneTransfertdao.totalheureParlaveur(d, e);
	 }
		public List<String> totalheureParService(String d,Produit e){
			return ligneTransfertdao.totalheureParService(d, e);
		}
	 
	 public List<Rendement> getAllbyStatuss(Statuss statuss,TypeLavage typelavage){
		 return ligneTransfertdao.getAllbyStatuss(statuss, typelavage);
	 }
	 public List<Rendement> getbetwendateandemployee(Date d1,Date d2,Employee e){
		 return ligneTransfertdao.getbetwendateandemployee(d1, d2, e);
	 }
	 public List<Rendement> getbetwendates(Date d1,Date d2,TypeLavage typelavage,Statuss statuss){
		 return ligneTransfertdao.getbetwendates(d1, d2,typelavage,statuss);
	 }
	 public List<Rendement> getbetwendatesAndPoste(Date d1,Date d2,Poste poste,TypeLavage typelavage,Statuss statuss){
		 return ligneTransfertdao.getbetwendatesAndPoste(d1, d2, poste,typelavage,statuss);
	 }
	 public List<Rendement> getbetwendate(Date d1,Date d2,Employee e){
		 return ligneTransfertdao.getbetwendate(d1, d2,e);
	 }
		public Rendement findByStatusAndGeneration(TypeLavage typelavage)  {
			return ligneTransfertdao.findByStatusAndGeneration(typelavage);
		}
		
		 public List<Rendement> getRendementPoste( TypeLavage typelavage,Statuss statuss){
			 return ligneTransfertdao.getRendementPoste( typelavage, statuss);
		 }
	 public List<Rendement> getAllventeparDateandemployee(String d,Employee e){
		 return ligneTransfertdao.getAllventeparDateandemployee( d, e);
	 }
	 public List<Rendement> getAllventeparDate(String d){
		 return ligneTransfertdao.getAllventeparDate(d);
	 }
	public List<Rendement> getAll() {
		return ligneTransfertdao.getAll();
	}
	public List<Rendement> getAllventeparposteandDate33(String d ,Poste poste){
		return ligneTransfertdao.getAllventeparposteandDate33(d,poste);
	}
	 
	public List<Rendement> getAllventeparposteandDate33(String f ,String d ,Poste poste){
		return ligneTransfertdao.getAllventeparposteandDate33(f ,  d ,  poste);
	}
	 
	 
	 public Rendement getmaxRendement() {
		 return ligneTransfertdao.getmaxRendement();
	 }
	 public List<Rendement> getAllventeparposteandDate2(String d ,Poste poste){
		 return ligneTransfertdao.getAllventeparposteandDate2(d, poste);
	 }
	 public List<Rendement> getAllventeparposteandDate3(String d ,Poste poste){
		 return ligneTransfertdao.getAllventeparposteandDate3(d, poste);
	 }
	public List<Rendement> getAllventeparposte(String d ) {
		return ligneTransfertdao.getAllventeparposte(d);
	}
	 

	public void update(Rendement typearticle) {
		ligneTransfertdao.update(typearticle);
	}

	public void delete(Rendement typearticle) {
		ligneTransfertdao.delete(typearticle);

	}
	public List<Rendement> getAllventeparposteandDate(String d ,Poste poste){
		 return ligneTransfertdao.getAllventeparposteandDate(d, poste);
	}
	 

	 
}
