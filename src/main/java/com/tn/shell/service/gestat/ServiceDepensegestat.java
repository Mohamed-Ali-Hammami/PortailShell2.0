package com.tn.shell.service.gestat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*; 

 

@Service("ServiceDepensegestat")
public class ServiceDepensegestat {
	@Autowired
	DepensegestatDAO depenseDAO;
	
	 
		public void save(Depensegestat c){
			depenseDAO.save(c);
		}
		public List<Depensegestat> getdepensebetweendateAndFamille(Date d1,Date d2,int f){
			return depenseDAO.getdepensebetweendateAndFamille(d1, d2, f);
		}
		public double getsummontantbydateandfamille( Date d1,Date d2,int f) {
			return depenseDAO.getsummontantbydateandfamille(d1, d2, f);
		}
		public List<Depensegestat> getdepensebetweendate(Date d1,Date d2){
			return depenseDAO.getdepensebetweendate(d1, d2);
		}
		public List<Depensegestat> getAll(){
			return  depenseDAO.getAll();
		}
		public double getsummontantbydate( Date d1,Date d2) {
			return  depenseDAO. getsummontantbydate(  d1,d2);
		}
		 public void update(Depensegestat c){
			depenseDAO.update(c);
		} 
		public void delete(Depensegestat c){
			depenseDAO.delete(c);
		}public List<Depensegestat> getdepensebyCaisse(Caisse c){
			return  depenseDAO. getdepensebyCaisse(c);
		}
		public List<Depensegestat> getdepensebydate(String c){
			return  depenseDAO.getdepensebydate(c);
		}
		public Depensegestat getdepensebyid(Depensegestat d) {
			return  depenseDAO. getdepensebyid(d);
		}
		public Depensegestat getmaxdepense() {
			return depenseDAO.getmaxdepense();
		}
}
