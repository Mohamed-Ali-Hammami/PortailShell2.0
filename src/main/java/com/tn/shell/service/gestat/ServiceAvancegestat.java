package com.tn.shell.service.gestat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*;
import com.tn.shell.model.paie.Employee; 
 
 

@Service("ServiceAvancegestat")
public class ServiceAvancegestat {
	@Autowired
	AvancegestatDAO avanceDAO;
	
	public void save(Avancegestat avance){
    avanceDAO.save(avance);
	}
	 public Avancegestat getavancebyid(Integer id) {
		 return avanceDAO.getavancebyid(id);
	 }
	 
		public double  getAvance(String fonction,Date d1,Date d2) {
			return avanceDAO.getAvance(fonction, d1, d2);
		}
	 
	 public List<Avancegestat> getAvancesBybetweendate(Date date1,Date date2){
		 return avanceDAO.getAvancesBybetweendate(date1, date2);
	 }
	 public List<Avancegestat> getAvancesByEmployeebetweendate(Employee e,Date date1,Date date2){
		 return avanceDAO.getAvancesByEmployeebetweendate(e, date1, date2);
	 }
	 public List<Avancegestat> getAvancebDateandemployee(String date,Employee e){
		 return avanceDAO.getAvancebDateandemployee(date, e);
	 }
	public List<Avancegestat> getAll(){
		return avanceDAO.getAll();
	}
	 public List<Avancegestat> getAvancebDate(String date){
		 return avanceDAO.getAvancebDate(date);
	 }
	public void update(Avancegestat avance){
		avanceDAO.update(avance);
	}
	public void delete(Avancegestat avance){
		avanceDAO.detele(avance);
	}
	public List<Avancegestat> getAvancesByEmployee(Employee e,Integer annee,Integer mois){
		return avanceDAO.getAvancesByEmployee(e, annee, mois);
	}
	 public List<Avancegestat> getAvancebDate(Date date1,Date d2,Employee e ){
		 return avanceDAO.getAvancebDate(date1,d2,  e);
	 }
	 
	 public List<Avancegestat> getAvancebyCaisse(Caisse c){
		 return avanceDAO.getAvancebyCaisse(c);
	 }
}
