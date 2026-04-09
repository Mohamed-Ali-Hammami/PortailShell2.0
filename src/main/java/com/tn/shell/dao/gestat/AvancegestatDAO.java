package com.tn.shell.dao.gestat;

 
import java.util.Date;
import java.util.List;

import com.tn.shell.model.gestat.*;
import com.tn.shell.model.paie.Employee;

 





public interface AvancegestatDAO {
public List<Avancegestat> getAll();
public void save(Avancegestat c);
public void update(Avancegestat c);
public void detele(Avancegestat c) ;
public List<Avancegestat> getAvancesByEmployee(Employee e,Integer annee,Integer mois); 
public List<Avancegestat> getAvancebDate(Date date1,Date date2,Employee e);
public List<Avancegestat> getAvancebyCaisse(Caisse c);
public Avancegestat getavancebyid(Integer id);
public List<Avancegestat> getAvancebDate(String date);
public List<Avancegestat> getAvancebDateandemployee(String date,Employee e);
public List<Avancegestat> getAvancesByEmployeebetweendate(Employee e,Date date1,Date date2);
public List<Avancegestat> getAvancesBybetweendate(Date date1,Date date2);
public double  getAvance(String fonction,Date d1,Date d2) ;
}
