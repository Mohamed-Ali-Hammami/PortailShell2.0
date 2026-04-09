package com.tn.shell.dao.gestat;


import java.util.Date;
import java.util.List;

import com.tn.shell.model.gestat.TransactionCredit;
import com.tn.shell.model.transport.*;
 
public interface JournalDAO {
public void save(TransactionCredit TransactionCredit);
public List<TransactionCredit> getAll();
public void update(TransactionCredit TransactionCredit);
public void update2(TransactionCredit TransactionCredit);
public void delete(TransactionCredit TransactionCredit);
public TransactionCredit getjournalbytipe(String time,String d);
public TransactionCredit getmaxjournal(String d);
public List<TransactionCredit> getJournalbydates(String dates); 
public List<TransactionCredit> getJournalbetweendate(Date d1,Date d2);
public List<TransactionCredit> getjournalbytarticle(String article,int id) ;
public List<TransactionCredit> getjournalbytarticle(int id) ;
public List<TransactionCredit> getjournalNonAffecter() ;
}
