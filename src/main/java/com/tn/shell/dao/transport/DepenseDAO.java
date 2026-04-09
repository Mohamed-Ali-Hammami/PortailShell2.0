package com.tn.shell.dao.transport;


import java.util.Date;
import java.util.List;

import com.tn.shell.model.transport.*;
 
public interface DepenseDAO {
public void save(Depense depense);
public List<Depense> getAll();
public void update(Depense depense);
public void delete(Depense depense);
public double getdepensebyvehicule(Vhecule v,Date d1,Date d2);
public double getdepenseautrebyvehicule(Vhecule v,Date d1,Date d2) ;
public List<Depense> getdepensebydates(String dates);
public double sumdepense(Date d1,Date d2);
public List<Depense> getdepensebetweendate(Date d1,Date d2);
public double sumdepensebyfamille(int famille,Date d1,Date d2);
public Depense getdepensebyid(Integer id,String libelle,String lib,String dates);
}
