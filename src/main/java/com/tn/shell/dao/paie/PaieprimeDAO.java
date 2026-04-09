package com.tn.shell.dao.paie;

 
import java.util.List;

import com.tn.shell.model.paie.*;
 


public interface PaieprimeDAO {
public List<Paieprime> getAll();
public void save(Paieprime c);
public void update(Paieprime c);
public void detele(Paieprime c) ;
public List<Paieprime> getPaieNondeclareByAnneeAndMois(Integer annee,Integer mois);
public List<Paieprime> getPaieByAnnee(Integer annee);
public List<Paieprime> getPaieByAnneeAndMois(Integer annee,Integer mois);
public double getPaieByAnneeAndMois2(Integer annee,Integer mois);
public List<Paieprime> getPaieByAnneeAndMoisEmployee(Employee e,Integer annee,Integer mois);
public List<Paieprime> getPaieByAnneeAndEmployee(Employee e,Integer annee);
public List<Paieprime> getPaieByEmployee(Employee e);
 
}
