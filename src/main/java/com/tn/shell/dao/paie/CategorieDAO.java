package com.tn.shell.dao.paie;

import java.util.List;

import com.tn.shell.model.paie.*;

public interface  CategorieDAO  {
public void save( Categorie  categorie);
public List<Categorie> getAll();
public List<Categorie> getcategoriebydegre(Cat d);
public List<Categorie> getcategoriebycat(Integer d);
 public  List<Categorie> findbyDesignation(TypeCat designation);
 public void update(Categorie categorie); 
  
 public Categorie findbyid(Integer id);
 public Categorie findbydegreeandcat(Integer cat,Integer degree);
}
