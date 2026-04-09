package com.tn.shell.dao.shop;

import java.util.List;

import com.tn.shell.model.shop.Vente;

 
 

public interface VenteDAO {
public List<Vente> getAll();
public void save(Vente c);
public Integer getmaxcode();
public void update(Vente c);
public Vente getVentebyid(Integer id);
 
 
}
