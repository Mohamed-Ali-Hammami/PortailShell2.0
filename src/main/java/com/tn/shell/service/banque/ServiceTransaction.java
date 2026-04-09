package com.tn.shell.service.banque;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
 
 import com.tn.shell.dao.banque.TransactionDao;
import com.tn.shell.model.banque.Compte;
import com.tn.shell.model.banque.Enumcheque;
import com.tn.shell.model.banque.Transaction;

@Service("ServiceTransaction")
public class ServiceTransaction {
	@Autowired
	 TransactionDao transactionDao;
	 
	public void save(Transaction transaction){
		transactionDao.save(transaction);
	}
 
	public List<Transaction> getAll(Compte compte){
		return transactionDao.getAll(compte);
	}
	 
	public List<Transaction> Findbynom(String nom) {
		return transactionDao.Findbynom(nom);
	}
	public void update(Transaction transaction){
		transactionDao.update(transaction);
	}
	public void delete(Transaction transaction){
		transactionDao.delete(transaction);
	}
	 
	public Transaction Findbycode(String codeTransaction) {
		// TODO Auto-generated method stub
		return transactionDao.Findbycode(codeTransaction);
	}
	
	public List<Transaction> findbyDate(Date date1, Date date2,Compte compte){
		return transactionDao.findbyDate(date1, date2,compte);
	}
	
	public List<Transaction> findbyMonth(int monht,Compte compte) {
		return transactionDao.findbyMonth(monht,compte);
	}
	
	public List<Transaction> findByEnumarationCeque(Enumcheque cheque,Compte compte){
		return transactionDao.findByEnumarationCeque(cheque,compte);
	}
}
