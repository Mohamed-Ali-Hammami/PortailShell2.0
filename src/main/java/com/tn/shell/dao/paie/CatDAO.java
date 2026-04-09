package com.tn.shell.dao.paie;

import java.util.List;
import com.tn.shell.model.paie.*;

public interface  CatDAO  {
public void save( Cat   categorie);
public List<Cat> getAll();
 
 public List<Cat> findbyDesignation(TypeCat designation);
 public void update(Cat categorie); 
  
 public Cat findbyid(Integer id);
}
