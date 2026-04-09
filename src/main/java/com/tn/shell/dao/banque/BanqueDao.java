package com.tn.shell.dao.banque;

import java.util.List;

import com.tn.shell.model.banque.Banque;
 
 
 

public interface BanqueDao {
public void save(Banque banque);
public List<Banque> getAll();
 
public  Banque Findbynom(String nom);
public Banque Findbycode(Integer code);
 
public void update(Banque banque);
public void delete(Banque banque); 
 
}
