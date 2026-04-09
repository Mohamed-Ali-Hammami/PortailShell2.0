package com.tn.shell.dao.paie;

 
import java.util.List;
import com.tn.shell.model.paie.*;
 


public interface PointageDAO {
public List<Pointage> getAll();
public void save(Pointage c);
public void update(Pointage c);
public void detele(Pointage c) ;
public Pointage getPointageByEmployee(Employee e, Integer Integer,Integer mois);
public double getsumPointageByEmployee(Employee e, Integer Integer,Integer mois1,Integer mois2);
public List<Pointage> getPointageByEmployer(Employee e);
public List<Pointage> getPointageByMoiannee(Integer annee,Integer mois);
public List<Pointage> getPointageByAnnee( Integer annee);
public Pointage getMaxPointage();
public double getsumPointageByEmployeeandannee(Employee e, Integer annee) ;
 
}
