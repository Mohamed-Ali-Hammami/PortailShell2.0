package com.tn.shell.dao.paie;

import java.util.List;

import com.tn.shell.model.paie.*;

 

public interface LigneGestionpaieprimedao {
public void save(Lignepaiegestionprime c);
public void update(Lignepaiegestionprime c);
 
public List<Lignepaiegestionprime> getAll();
public List<Lignepaiegestionprime> getlistbypaie(Paieprime e);
public  Lignepaiegestionprime getlignebygestion(Lignegestion e); 
}
