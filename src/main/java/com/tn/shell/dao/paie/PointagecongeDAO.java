package com.tn.shell.dao.paie;

 
import java.util.List;

import com.tn.shell.model.paie.*;
 


public interface PointagecongeDAO {
public List<Pointageconge> getAll();
public void save(Pointageconge c);
public void update(Pointageconge c);
public void detele(Pointageconge c) ;
public Pointageconge getPointagecongeByEmployee(Employee e, Integer annee);
public List<Pointageconge> getPointagecongeByEmployer(Employee e);
public List<Pointageconge> getPointagecongeByMoiannee(Integer annee,Integer mois);
public List<Pointageconge> getPointagecongeByAnnee( Integer annee);
public Pointageconge getMaxPointageconge();
 
}
