package com.tn.shell.dao.paie;

 
import java.util.List;

import com.tn.shell.model.paie.*;
 


public interface RappelDAO {
public List<Rappel> getAll(Integer annee);
public void save(Rappel c);
public void update(Rappel c);
public void detele(Rappel c) ;
public Rappel getRappelsByEmployee(Employee e,Integer annee);
public List<Rappel> getRappelsByEmployer(Employee e);
public Rappel getMaxPointageconge();
 
}
