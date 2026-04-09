package com.tn.shell.dao.paie;

import java.util.List;

import com.tn.shell.model.paie.*;

 

public interface LigneGestionpaierappeldao {
public void save(Lignepaiegestionrappel c);
public void update(Lignepaiegestionrappel c);
 
public List<Lignepaiegestionrappel> getAll();
public List<Lignepaiegestionrappel> getlistbypaie(Rappel e);
public  Lignepaiegestionrappel getlignebygestion(Lignegestion e); 
}
