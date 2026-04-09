package com.tn.shell.dao.gestat;

import java.util.List;

import com.tn.shell.model.gestat.*;
 

public interface ClientpassationDao {
public void save(Clientpassation client);
public List<Clientpassation> getAll();
public List<String> getAllnom();
public Clientpassation Findbynom(String nom);
public Clientpassation Findbycode(Integer code);
public Clientpassation Findbymf(String nom);
public void update(Clientpassation client);
public void delete(Clientpassation client);
public List<Clientpassation> getclientendepassement();
public List<Clientpassation> getclientenAvance();
public List<Clientpassation> finByReste();
}
