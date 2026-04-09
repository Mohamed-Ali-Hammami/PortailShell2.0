package com.tn.shell.dao.paie;

import java.util.Date;
import java.util.List;

import com.tn.shell.model.paie.Gestion;
import com.tn.shell.model.paie.Gestionrappel;

 
 


public interface GestionrappelDAO {
public List<Gestionrappel> getAll();
public void save(Gestionrappel c);
public void update(Gestionrappel c);
public void detele(Gestionrappel c) ;
 public Gestionrappel getGestionbyLibelle(Integer annee,String libelle);
}
