package com.tn.shell.service.paie;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tn.shell.dao.paie.*;
import com.tn.shell.model.paie.*;
 

@Service("ServiceEmployee")
public class ServiceEmployee {
	@Autowired
	EmployeeDAO employeeDAO;
	
	public void save(Employee employee){
    employeeDAO.save(employee);
	}
	
	 public List<Employee> getEmployeeparstats(Status  status ){
		return  employeeDAO.getEmployeeparstats(status);
	 }
	 public List<Employee> getAll5() {
		 return employeeDAO.getAll5();
	 }
	 
	 public List<Employee> getEmployeefincontrat(Date   date ){
		 return employeeDAO.getEmployeefincontrat(date);
	 }
		public List<Employee> getEmployeeByCategorie(Categorie c){
			return employeeDAO.getEmployeeByCategorie(c);
		}
	 public List<Employee> getEmployeeparfonction(String nature){
		 return employeeDAO.getEmployeeparfonction(nature);
	 }
	 public List<Employee> getEmployeeparetat(Statut statut){
		 return employeeDAO.getEmployeeparetat(statut);
	 }
	 public List<Employee> getEmployeeByFonction(String nom){
		 return employeeDAO.getEmployeeByFonction(nom);
	 }
	 public Employee getEmployeeById(Integer nom) {
		 return employeeDAO.getEmployeeById(nom);
	 }
	 public Employee getEmployeeBycode(Integer nom) {
		 return employeeDAO.getEmployeeBycode(nom);
	 }
	public List<Employee> getAll(){
		return employeeDAO.getAll();
	}
	 public Employee getEmployeeByNom(String nom){
		 return employeeDAO.getEmployeeByNom(nom);
	 }
	 public List<Employee> getEmployeeparnature(String  nature ){
		 return employeeDAO.getEmployeeparnature(nature);
	 }
	public void update(Employee employee){
		employeeDAO.update(employee);
	}
	public void delete(Employee employee){
		employeeDAO.detele(employee);
	}
	 
}
