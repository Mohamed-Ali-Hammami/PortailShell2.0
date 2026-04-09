package com.tn.shell.service.gestat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.gestat.JournalDAO;
import com.tn.shell.model.gestat.TransactionCredit;
 

 

@Service("ServiceJournal")
public class ServiceJournal {
	@Autowired
	JournalDAO  journalDAO;
	
	 
		public void save(TransactionCredit c){
			journalDAO.save(c);
		}
		
		public List<TransactionCredit> getjournalNonAffecter(){
			return journalDAO.getjournalNonAffecter();
		}
		public List<TransactionCredit> getjournalbytarticle(String article,int id) {
			return journalDAO.getjournalbytarticle(article, id);
		}
		public List<TransactionCredit> getJournalbydates(String dates){
			return journalDAO.getJournalbydates(dates);
		}
		public List<TransactionCredit> getAll(){
			return  journalDAO.getAll();
		}
		public TransactionCredit getmaxjournal(String d) {
			return journalDAO.getmaxjournal(d);
		}
		public TransactionCredit getjournalbytipe(String time,String d) {
			return journalDAO.getjournalbytipe(time, d);
		}
		 
		public List<TransactionCredit> getJournalbetweendate(Date d1,Date d2){
			return journalDAO.getJournalbetweendate(d1, d2);
		}
		 
		 public void update(TransactionCredit c){
			journalDAO.update(c);
		} 
		 public List<TransactionCredit> getjournalbytarticle(int id) {
			 return journalDAO.getjournalbytarticle(id);
		 }
		 public void update2(TransactionCredit c){
			 journalDAO.update(c);
			} 
		public void delete(TransactionCredit c){
			journalDAO.delete(c);
		} 
		
		 
}
