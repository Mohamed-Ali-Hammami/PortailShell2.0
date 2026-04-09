package com.tn.shell.dao.shop;

import java.util.List;

import com.tn.shell.model.shop.*;
 
 

public interface AchatDAO {
public List<Achat> getAll();
public void save(Achat c);
public Integer getmaxcode();
public void update(Achat c);
public Achat getAchatbyid(Integer id);
public List<Achat> getArticlebyfacture(Factureachat f);
public List<Achat> getArticlebyfournisseur(Fournisseur f);
public List<Achat> getAchatbystatusfacture(Status status,Fournisseur f);
}
