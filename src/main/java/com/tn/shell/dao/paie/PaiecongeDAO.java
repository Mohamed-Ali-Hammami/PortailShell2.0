package com.tn.shell.dao.paie;

 
import java.util.List;

import com.tn.shell.model.paie.*;
 


public interface PaiecongeDAO {
public List<Paieconge> getAll();
public void save(Paieconge c);
public void update(Paieconge c);
public void detele(Paieconge c) ;
public List<Paieconge> getPaieNondeclareByAnneeAndMois(Integer annee,Integer mois);
public List<Paieconge> getPaieByAnnee(Integer annee);
public List<Paieconge> getPaieByAnneeAndMois(Integer annee,Integer mois);
public List<Paieconge> getPaieByAnneeAndMoisEmployee(Employee e,Integer annee,Integer mois);
public List<Paieconge> getPaieByAnneeAndEmployee(Employee e,Integer annee);
public List<Paieconge> getPaieByEmployee(Employee e);
public double getPaieByAnneeAndMois2(Integer annee,Integer mois);
}
