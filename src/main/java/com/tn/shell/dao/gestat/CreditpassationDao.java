package com.tn.shell.dao.gestat;

import java.util.List;
import com.tn.shell.model.gestat.*;

 
 

public interface CreditpassationDao {
public Creditpassation findbyCredit(Credit c);
public void save(Creditpassation Creditclient);
public List<Creditpassation> getAll();
public List<Creditpassation> getCreditclientbyCaisse(Caisse c);
public Creditpassation getCreditclientbyCaisseandclient(Creditpassation cl);
public List<Creditpassation> Findbynom(String nom);
public Creditpassation Findbycode(Integer code);
public Creditpassation Findbymf(String nom);
public void update(Creditpassation creditclient);
public Creditpassation  getcreditbyid(Integer id);
public void delete(Creditpassation creditclient);
public List<Creditpassation> getListcreditdate(String date);
public List<Creditpassation> getListcreditdateandclient(String date,Clientpassation c);
public  List<Creditpassation> findbyClient(Clientpassation c);
}
