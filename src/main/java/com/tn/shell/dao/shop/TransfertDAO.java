package com.tn.shell.dao.shop;

import java.util.List;

import com.tn.shell.model.shop.Transfert;
 
 

public interface TransfertDAO {
public List<Transfert> getAll();
public void update(Transfert t);
public void save(Transfert t);
 
 
}
