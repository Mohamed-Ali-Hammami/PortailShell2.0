package com.tn.shell.dao.paie;

import java.util.Date;
import java.util.List;

import com.tn.shell.model.paie.Gestion;

 
 


public interface GestionDAO {
public List<Gestion> getAll();
public void save(Gestion c);
public void update(Gestion c);
public void detele(Gestion c) ;
 public Gestion getGestionbyLibelle(String libelle);
}
