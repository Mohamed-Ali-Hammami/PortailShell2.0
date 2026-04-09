package com.tn.shell.dao.gestat;


import java.util.Date;
import java.util.List;

import com.tn.shell.model.gestat.TransactionDepense;
 
 
public interface JournalDepenseDAO {
public void save(TransactionDepense TransactionDepense);
public List<TransactionDepense> getAll();
public void update(TransactionDepense TransactionDepense);
public void update2(TransactionDepense TransactionDepense);
public void delete(TransactionDepense TransactionDepense);
public TransactionDepense getjournalbytipe(String time,String d);
public TransactionDepense getmaxjournal(String d);
public List<TransactionDepense> getJournalbydates(String dates); 
public List<TransactionDepense> getJournalbetweendate(Date d1,Date d2);
public List<TransactionDepense> getjournalbytarticle(String article,int id) ;
public List<TransactionDepense> getjournalbytarticle(int id) ;
public List<TransactionDepense> getjournalNonAffecter() ;
}
