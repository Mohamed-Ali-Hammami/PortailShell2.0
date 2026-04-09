package com.tn.shell.dao.gestat;

import java.util.List;
import com.tn.shell.model.gestat.*;

 
 

public interface HistoriqueDao {
public Historiquepayement findbyCredit(Creditpassation c);
public void save(Historiquepayement creditclient);
public List<Historiquepayement> getAll();
 
public Historiquepayement getCreditclientbyCaisseandclient(Historiquepayement cl);
public List<Historiquepayement> Findbynom(String nom);
public Historiquepayement Findbycode(Integer code);
public Historiquepayement Findbymf(String nom);
public void update(Historiquepayement creditclient);
public Historiquepayement  getcreditbyid(Integer id);
public void delete(Historiquepayement creditclient);
public List<Historiquepayement> getListcreditdate(String date);
public List<Historiquepayement> getListcreditdateandclient(String date,Clientpassation c);
public  List<Historiquepayement> findbyClient(Clientpassation c);
}
