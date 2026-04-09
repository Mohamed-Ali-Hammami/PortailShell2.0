package com.tn.shell.dao.banque;

import java.util.List;

import com.tn.shell.model.banque.Compte;
 
 
 

public interface CompteDao {
public void save(Compte Compte);
public List<Compte> getAll();
 
public Compte Findbynom(String nom);
public Compte Findbycode(Integer code);
 
public void update(Compte Compte);
public void delete(Compte Compte); 
 
}
