package com.tn.shell.dao.gestat;

import java.util.Date;
import java.util.List;

import com.tn.shell.model.gestat.*;
import com.tn.shell.model.shop.Fournisseur;

 
 

public interface AchatcarburantDAO {
public List<Achatcarburant> getAll();
public void save(Achatcarburant c);
public Integer getmaxcode();
public void update(Achatcarburant c);
public Achatcarburant getAchatbyid(Integer id);
public List<Achatcarburant> getArticlebyfacture(Factureachatcarburant f);
public List<Achatcarburant> getArticlebyfournisseur(Fournisseur f);
public List<Achatcarburant> getAchatbystatusfacture(Status status,Fournisseur f);
public List<Achatcarburant> getAchatbyfacture(Fournisseur f);
public List<Achatcarburant> getAchatbyDate(String dates);
public double getTotalAchatbyDate(Date date);
}
