package com.tn.shell.dao.gestat;

import java.util.Date;
import java.util.List;
import com.tn.shell.model.gestat.*;

 
 

public interface CreditClientDao {
public void save(Credit Creditclient);
public List<Credit> getAll();
public List<Credit> getCreditclientbyCaisse(Caisse c);
public Credit getCreditclientbyCaisseandclient( Credit cl);
public List<Credit> Findbynom(String nom);
public Credit Findbycode(Integer code);
public Credit Findbymf(String nom);
public void update(Credit creditclient);
public Credit  getcreditbyid(Integer id);
public void delete(Credit creditclient);
public List<Credit> getListcreditdate(String date);
public List<Credit> getListcreditdateandclient(String date,Clientgestat c);
public double  getAllcreditbyclientanddates(Date d1,Date d2,Clientgestat c);
}
