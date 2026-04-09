package com.tn.shell.service.gestat;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*; 
 
 

@Service("ServiceEmployeegestat")
public class ServiceEmployeegestat {
	@Autowired
	EmployeegestatDAO employeeDAO;
	
	public void save(Employeegestat employee){
    employeeDAO.save(employee);
	}
	 public List<Employeegestat> getEmployeeparstats(Status  status ){
		return  employeeDAO.getEmployeeparstats(status);
	 }
	 
	 public Employeegestat getEmployeeById(Integer nom) {
		 return employeeDAO.getEmployeeById(nom);
	 }
	 public List<Employeegestat> getEmployeeparetat(Statut statut){
		 return employeeDAO.getEmployeeparetat(statut);
	 }
	public List<Employeegestat> getAll(){
		return employeeDAO.getAll();
	}
	 public Employeegestat getEmployeeByNom(String nom){
		 return employeeDAO.getEmployeeByNom(nom);
	 }
	 public List<Employeegestat> getEmployeeparnature(String  nature ){
		 return employeeDAO.getEmployeeparnature(nature);
	 }
	public void update(Employeegestat employee){
		employeeDAO.update(employee);
	}
	public void delete(Employeegestat employee){
		employeeDAO.detele(employee);
	}
	 
}
