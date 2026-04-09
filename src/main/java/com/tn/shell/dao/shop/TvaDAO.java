package com.tn.shell.dao.shop;

import java.util.List;

import com.tn.shell.model.shop.Tva;

 
 

public interface TvaDAO {
public List<Tva> getAll();
public void update(Tva t);
public void save(Tva t);
public Tva getbyvaleur(Integer t);
public List<Integer> getAllvaleur();
public Tva getbyid(Integer t);
}
