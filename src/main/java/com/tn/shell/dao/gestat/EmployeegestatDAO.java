package com.tn.shell.dao.gestat;

 
import java.util.List;

import com.tn.shell.model.gestat.*;

 
 


public interface EmployeegestatDAO {
public List<Employeegestat> getAll();
public void save(Employeegestat c);
public void update(Employeegestat c);
public void detele(Employeegestat c) ;
public List<Employeegestat> getEmployeeparetat(Statut  statut );
public List<Employeegestat> getEmployeeparnature(String  nature );
public List<Employeegestat> getEmployeeparstats(Status  status );
public Employeegestat getEmployeeByNom(String nom);
public Employeegestat getEmployeeById(Integer nom);
 
}
