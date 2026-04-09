package com.tn.shell.dao.paie;

 
import java.math.BigDecimal;
import java.util.List;

import com.tn.shell.model.paie.*;
 


public interface PaieDAO {
public List<Paie> getAll();
public void save(Paie c);
public void update(Paie c);
public void detele(Paie c) ;
public List<Paie> getPaieNondeclareByAnneeAndMois(Integer annee,Integer mois);
public List<Paie> getPaieNondeclare2ByAnneeAndMois(Integer annee,Integer mois) ;
public List<Paie> getPaieByAnnee(Integer annee);
public List<Paie> getPaieByAnneeAndMois(Integer annee,Integer mois);
public List<Paie> getPaieByAnneeAndMois2(Integer annee,Integer mois);
public  Paie getPaieByAnneeAndMoisEmployee(Employee e,Integer annee,Integer mois);
public List<Paie> getPaieByAnneeAndEmployee(Employee e,Integer annee);
public List<Paie> getPaieByEmployee(Employee e);
public double getPaieByAnneeAndMoiss(Integer annee, Integer mois);
public double  getMinSalaireByAnneeAndMoisEmployee(Employee e,Integer annee);
public double  getTotalCnss(String fonction,Integer annee,Integer mois);
public double  getTotalPayement(String fonction,Integer annee,Integer mois);
}
