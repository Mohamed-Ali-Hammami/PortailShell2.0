package com.tn.shell.service.gestat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*; 

 

@Service("ServiceDepensecheque")
public class ServiceDepensecheque {
	@Autowired
	DepenseChequeDAO depenseDAO;
	
	 
		public void save(DepenseCheque c){
			depenseDAO.save(c);
		}
		
		public double getdepensebetweendateandfamille2(Date d1, Date d2,int fonction){
			return depenseDAO.getdepensebetweendateandfamille2(d1, d2, fonction);
		}
		
		
		public double  getDepenseBylibelle(String fonction,Date d1,Date d2) {
			return depenseDAO.getDepenseBylibelle(fonction, d1, d2);
		}
		public double getsummontantbydateandfamille( Date d1,Date d2,int f) {
			return depenseDAO.getsummontantbydateandfamille(d1, d2, f);
		}
		public List<DepenseCheque> getdepensebetweendate(Date d1,Date d2){
			return depenseDAO.getdepensebetweendate(d1, d2);
		}
		public List<DepenseCheque> getAll(){
			return  depenseDAO.getAll();
		}
		public BigDecimal getsummontantbydate( Date d1,Date d2) {
			return  depenseDAO. getsummontantbydate(  d1,d2);
		}
		 public void update(DepenseCheque c){
			depenseDAO.update(c);
		} 
		public void delete(DepenseCheque c){
			depenseDAO.delete(c);
		}public List<DepenseCheque> getdepensebyCaisse(Caisse c){
			return  depenseDAO. getdepensebyCaisse(c);
		}
		public List<DepenseCheque> getdepensebydate(String c){
			return  depenseDAO.getdepensebydate(c);
		}
		public DepenseCheque getdepensebyid(DepenseCheque d) {
			return  depenseDAO. getdepensebyid(d);
		}
		public DepenseCheque getmaxdepense() {
			return depenseDAO.getmaxdepense();
		}
}
