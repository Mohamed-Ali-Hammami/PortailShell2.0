package com.tn.shell.service.transport;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.transport.*;
import com.tn.shell.model.transport.*;

 

@Service("ServiceDepense")
public class ServiceDepense {
	@Autowired
	DepenseDAO  depenseDAO;
	
	 
		public void save(Depense c){
			depenseDAO.save(c);
		}
		public double getdepenseautrebyvehicule(Vhecule v,Date d1,Date d2) {
			return 	depenseDAO.getdepenseautrebyvehicule(v, d1, d2);
		}
		public List<Depense> getdepensebydates(String dates){
			return depenseDAO.getdepensebydates(dates);
		}
		public List<Depense> getAll(){
			return  depenseDAO.getAll();
		}
		public Depense getdepensebyid(Integer id,String libelle,String lib,String dates) {
			return depenseDAO.getdepensebyid(id, libelle, lib, dates);
		}
		public List<Depense> getdepensebetweendate(Date d1,Date d2){
			return depenseDAO.getdepensebetweendate(d1, d2);
		}
		public double getdepensebyvehicule(Vhecule v,Date d1,Date d2) {
			return  depenseDAO.getdepensebyvehicule(v, d1, d2);
		}
		 public void update(Depense c){
			depenseDAO.update(c);
		} 
		public void delete(Depense c){
			depenseDAO.delete(c);
		} 
		
		public double sumdepense(Date d1,Date d2) {
			return depenseDAO.sumdepense(d1, d2);
		}
		public double sumdepensebyfamille(int famille,Date d1,Date d2) {
			return depenseDAO.sumdepensebyfamille(famille, d1, d2);
		}
}
