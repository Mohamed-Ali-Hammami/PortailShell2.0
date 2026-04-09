package com.tn.shell.dao.gestat;

import java.util.Date;
import java.util.List;
import com.tn.shell.model.gestat.*;
import com.tn.shell.model.shop.Fournisseur;

 
 

public interface CaisseDAO {
public List<Caisse> getAll();
public void save(Caisse c);
public Caisse getmaxcode();
public void update(Caisse c);
public List<Caisse> getbetwendates(Date d1,Date d2);
public Caisse getCaissebyid(Integer id);
public Caisse getCaissebydate(String date,Poste poste);
public Caisse getCaissebydates(String date);
public List<Caisse> getArticlebyfournisseur(Fournisseur f);
public List<Caisse> getCaissebystatusfacture(Status status,Fournisseur f);
public List<Caisse> getCaissebyfacture(Fournisseur f);
}
