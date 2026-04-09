package com.tn.shell.service.gestat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.gestat.JournalDepenseDAO;
import com.tn.shell.model.gestat.TransactionDepense;
 

 

@Service("ServiceJournalDep")
public class ServiceJournalDep {
	@Autowired
	JournalDepenseDAO  journalDAO;
	
	 
		public void save(TransactionDepense c){
			journalDAO.save(c);
		}
		
		public List<TransactionDepense> getjournalNonAffecter(){
			return journalDAO.getjournalNonAffecter();
		}
		public List<TransactionDepense> getjournalbytarticle(String article,int id) {
			return journalDAO.getjournalbytarticle(article, id);
		}
		public List<TransactionDepense> getJournalbydates(String dates){
			return journalDAO.getJournalbydates(dates);
		}
		public List<TransactionDepense> getAll(){
			return  journalDAO.getAll();
		}
		public TransactionDepense getmaxjournal(String d) {
			return journalDAO.getmaxjournal(d);
		}
		public TransactionDepense getjournalbytipe(String time,String d) {
			return journalDAO.getjournalbytipe(time, d);
		}
		 
		public List<TransactionDepense> getJournalbetweendate(Date d1,Date d2){
			return journalDAO.getJournalbetweendate(d1, d2);
		}
		 
		 public void update(TransactionDepense c){
			journalDAO.update(c);
		} 
		 public List<TransactionDepense> getjournalbytarticle(int id) {
			 return journalDAO.getjournalbytarticle(id);
		 }
		 public void update2(TransactionDepense c){
			 journalDAO.update(c);
			} 
		public void delete(TransactionDepense c){
			journalDAO.delete(c);
		} 
		
		 
}
