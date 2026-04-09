package com.tn.shell.dao.gestat;

import java.util.List;

import com.tn.shell.model.gestat.*;
import com.tn.shell.model.shop.Fournisseur;

 

 
 

public interface AchatcaisseDAO {
public List<Achatcaisse> getAll();
public void save(Achatcaisse c);
public Integer getmaxcode();
public void update(Achatcaisse c);
public Achatcaisse getAchatcaissebyid(Integer id);
public List<Achatcaisse> getachatyCaisse(Caisse c);
public List<Achatcaisse> getachatydate(String c);
public List<Achatcaisse> getArticlebyfournisseur(Fournisseur f);
public List<Achatcaisse> getAchatcaissebystatusfacture(Status status,Fournisseur f);
public List<Achatcaisse> getAchatcaissebyfacture(Fournisseur f);
}
