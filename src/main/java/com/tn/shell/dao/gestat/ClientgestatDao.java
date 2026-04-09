package com.tn.shell.dao.gestat;

import java.util.List;

import com.tn.shell.model.gestat.*;
 

public interface ClientgestatDao {
public void save(Clientgestat client);
public List<Clientgestat> getAll();
public List<String> getAllnom();
public Clientgestat Findbynom(String nom);
public Clientgestat Findbycode(Integer code);
public Clientgestat Findbymf(String nom);
public void update(Clientgestat client);
public void delete(Clientgestat client);
public List<Clientgestat> getclientendepassement();
public List<Clientgestat> getclientenAvance();
public double getAllmontantcredit() ;
}
