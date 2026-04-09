package com.tn.shell.dao.paie;

import java.util.List;

import com.tn.shell.model.paie.*;

 

public interface LigneGestionpaiedao {
public void save(Lignepaiegestion c);
public void update(Lignepaiegestion c);
 
public List<Lignepaiegestion> getAll();
public List<Lignepaiegestion> getlistbypaie(Paie e);
public  Lignepaiegestion getlignebygestion(Lignegestion e); 
}
