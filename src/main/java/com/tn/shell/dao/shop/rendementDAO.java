package com.tn.shell.dao.shop;

import java.util.Date;
import java.util.List;

import com.tn.shell.model.lavage.TypeLavage;
import com.tn.shell.model.paie.Employee;
import com.tn.shell.model.shop.*;

public interface rendementDAO {
	public void save(Rendement c);
	public void update(Rendement c);
	public void delete(Rendement c);
	public List<Rendement> getAll();
	public Rendement findByid(Integer id);
	public List<Rendement> getAllventeparposte(String d);
	public List<Rendement> getAllventeparposteandDate(String d, Poste poste);
	public List<Rendement> getAllbyStatuss(Statuss statuss,TypeLavage typelavage);
	public List<Rendement> getbetwendate(Date d1, Date d2, Employee e);
	public double getMontantvbetwendate(String d1, String d2, Employee e) ;
	public double getnbvbetwendate(String d1, String d2, Employee e);
	public List<Rendement> getbetwendates(Date d1,Date d2,TypeLavage typelavage,Statuss statuss);
	public List<Rendement> getbetwendatesAndtypelavage(Date d1,Date d2,TypeLavage typelavage);
	public List<Rendement> getbetwendatesAndtypelavageAndPoste(Date d1,Date d2,TypeLavage typelavage ,Poste poste);
	 public List<Rendement> getbetwendatesAndPoste(Date d1,Date d2,Poste poste,TypeLavage typelavage,Statuss statuss);
	public List<Produit>   getProduitbetwendatesAndPoste(Date d1, Date d2, Poste poste, TypeLavage typelavage);
	public List<Employee>   getEmployeebetwendatesAndPoste(Date d1, Date d2, Poste poste, TypeLavage typelavage);
	 public List<Rendement> getAllventeparDateandmployee(String d,Employee e);
	 public double getRecettebetwendates(String d1, String d2,TypeLavage typelavage);
	 public double getnbvbetwendates(String d1, String d2,TypeLavage typelavage);
		public double getnbvBydate(String d1, Employee e) ;
		public double getmontantvBydate(String d1, Employee e) ;
		public double getnbvBydate2(String d1,Poste poste,TypeLavage typelavage) ;
		public double getmontantvBydate2(String d1,Poste poste,TypeLavage typelavage);
		public List<Rendement> getAllbymatricule(String d);
	public List<Rendement>   getRendementPoste( TypeLavage typelavage,Statuss statuss);
	public List<Rendement> getbetwendateandemployee(Date d1, Date d2, Employee e);
	public Rendement getmaxRendement();
	Rendement findByStatusAndGeneration(TypeLavage typelavage)  ;
	public List<Rendement> getAllventeparposteandDate2(String d, Poste poste);
	public List<Rendement> getAllventeparDate(String d);
	public List<Rendement> getAllventeparDateandemployee(String d, Employee e);
	public List<Rendement> getAllventeparDateAndEmployeeAndposte(String d, Employee e,Poste poste);
	public List<Rendement> getAllventeparDateAndServiceAndposte(String d, Produit e,Poste poste);
	public List<Rendement> getAllventeparposteandDate3(String d, Poste poste);
	public List<Rendement> getAllventeparposteandDate(String d);
	public List<Rendement> getAllventeparposteandDate33(String f, String d, Poste poste);
	public List<Rendement> getAllventeparposteandDate33(String d, Poste poste);
	public List<Rendement> getAllventeparDate3(String d, Employee e);
	public List<Rendement> getAllventeparDate3AndProduit(String d, Produit e);
	public List<String> totalheureParlaveur(String d,Employee e);	                     
	public List<String> totalheureParService(String d,Produit e);
	public List<String> totalheureParlaveurAndposte(String d,Employee e,Poste poste);	                     
	public List<String> totalheureParServiceaNDpOSTE(String d,Produit e,Poste poste);
}
