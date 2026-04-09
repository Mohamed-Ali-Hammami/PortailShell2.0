package com.tn.shell.dao.banque;

import java.util.Date;
import java.util.List;

import com.tn.shell.model.banque.Compte;
import com.tn.shell.model.banque.Enumcheque;
import com.tn.shell.model.banque.Transaction;
 
 
 

public interface TransactionDao {
public void save(Transaction transaction);
public List<Transaction> getAll(Compte compte);
 
public List<Transaction> Findbynom(String nom);
public Transaction Findbycode(String code);
public List<Transaction>  findbyDate(Date date1,Date date2,Compte compte);
public List<Transaction>  findbyMonth(int monht,Compte compte);
public void update(Transaction transaction);
public void delete(Transaction transaction); 
public List<Transaction> findByEnumarationCeque(Enumcheque cheque,Compte compte);
}
