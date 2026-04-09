package com.tn.shell.dao.paie;

import java.util.List;

import com.tn.shell.model.paie.*;

 

public interface  AnneeDAO  {
public void save(Annee  annee);
public List<Annee> getAll();
 public Annee findbyDesignation(String designation);
 public void update(Annee annee); 
  
 public Annee findbyid(Integer id);
}
