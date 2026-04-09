package com.tn.shell.dao.paie;

 
import java.util.Date;
import java.util.List;

import com.tn.shell.model.paie.*;
 


public interface EmployeeDAO {
public List<Employee> getAll();
public void save(Employee c);
public void update(Employee c);
public void detele(Employee c) ;
public List<Employee> getEmployeeparetat(Statut  statut );
public List<Employee> getEmployeefincontrat(Date   date );
public List<Employee> getEmployeeparnature(String  nature );
public List<Employee> getEmployeeparstats(Status  status );
public Employee getEmployeeByNom(String nom);
public Employee getEmployeeById(Integer nom) ;
public Employee getEmployeeBycode(Integer nom) ;
public List<Employee> getEmployeeparfonction(String nature);
public List<Employee> getEmployeeByFonction(String nom);
public List<Employee> getEmployeeByCategorie(Categorie c);
public List<Employee> getAll5() ;
}
