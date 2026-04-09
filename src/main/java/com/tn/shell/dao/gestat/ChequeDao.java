package com.tn.shell.dao.gestat;

import java.util.Date;
import java.util.List;
 
import com.tn.shell.model.gestat.*;
 
 

public interface ChequeDao {
public void save(Cheque Cheque);
public List<Cheque> getAll();
 
public List<Cheque> Findbynom(String nom);
public Cheque Findbycode(Integer code);
public Cheque Findbymf(String nom);
public void update(Cheque Cheque);
public void delete(Cheque Cheque);
public List<Cheque> getChequebyCaisse(Caisse c);
public List<Cheque> getListchequedate(String date);
public List<Cheque> getListchequeetat(Enumcheque etat);
public List<Cheque> getListchequeetatandbanque(Enumcheque etat,String banque);
public List<Cheque> getListchequebetweendate(Date date1,Date date2);
}
