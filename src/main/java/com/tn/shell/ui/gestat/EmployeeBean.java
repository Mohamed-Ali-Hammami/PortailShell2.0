package com.tn.shell.ui.gestat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.dao.DataAccessException;

import com.tn.shell.model.gestat.Employeegestat;
import com.tn.shell.model.gestat.Statut;
import com.tn.shell.model.paie.Employee;
import com.tn.shell.service.gestat.*;
import com.tn.shell.service.paie.ServiceEmployee;
 

@ManagedBean(name = "EmployeeBean")
@SessionScoped
public class EmployeeBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	private static final Logger LOG = Logger.getLogger(EmployeeBean.class.getName());

	@ManagedProperty(value = "#{ServiceAvancegestat}")
	ServiceAvancegestat serviceAvance;
	@ManagedProperty(value = "#{ServiceEmployeegestat}")
	ServiceEmployeegestat serviceEmployee;
	@ManagedProperty(value = "#{ServiceEmployee}")
	ServiceEmployee serviceEmployees;

	/* Champ employee */
	private Employeegestat employee;
	private String cin;
	private String nom;
	private String prenom;
	private String adresse;
	private double salaire_journalier;
	private List<Employeegestat> listEmp;
	private List<Employee> listEmp2;
	private Employeegestat selectedEmp;
	private List<Employeegestat> filtredEmp;
	private String tel;
    private List<String> listcredits;
	@PostConstruct
	public void init() {

	}

	
	public String cahieremployee() {
		LOG.log(Level.INFO, "Gestat.EmployeeBean#cahieremployee start serviceEmployeesNull={0}", serviceEmployees == null);
		listEmp2 = new ArrayList<Employee>();
		listEmp2 = serviceEmployees.getAll();
		Employee e1=new Employee();
		e1.setNom(" ");
		
		Employee e2=new Employee();
		e1.setNom(" ");
		
		Employee e3=new Employee();
		e3.setNom(" ");listEmp2.add(e1);
		listEmp2.add(e2);listEmp2.add(e3);
		listEmp2.add(e3);listEmp2.add(e3);listEmp2.add(e3);listEmp2.add(e3);
		  listEmp2.add(e1); listEmp2.add(e1); listEmp2.add(e1);
		listEmp2.add(e2);listEmp2.add(e3);
		listcredits=new ArrayList<String>();
		for(int i=0;i<=30;i++){
			listcredits.add(""+(i+1));
		}
		LOG.log(Level.INFO, "Gestat.EmployeeBean#cahieremployee loaded listEmp2={0}", listEmp2 == null ? -1 : listEmp2.size());
		return SUCCESS;
	}
	
	
	public String index() {
		return SUCCESS;
	}

	/*****
	 * nouveau employee
	 *****/

	public String nouvemployee() {

		nom = null;
		prenom = null;
		cin = null;
		salaire_journalier = 0;
		adresse = null;
		tel = null;
		return SUCCESS;
	}

	public String succes() {

		nom = selectedEmp.getNom();
		 
		cin = selectedEmp.getCin();
		adresse = selectedEmp.getAdresse();
		tel = selectedEmp.getTel();

		salaire_journalier = selectedEmp.getSalaire_journalier();
		return SUCCESS;
	}

	public String modifierEmployer() {
		 
		selectedEmp.setTel(tel);
		selectedEmp.setNom(nom);
		selectedEmp.setAdresse(adresse);
		selectedEmp.setCin(cin);
		 
		selectedEmp.setSalaire_journalier(salaire_journalier);

	 
		serviceEmployee.update(selectedEmp);

		nom = null;
		prenom = null;
		cin = null;
		adresse = null;
		 
		salaire_journalier = 0;
		listEmp = new ArrayList<Employeegestat>();
		listEmp = serviceEmployee.getAll();
		return SUCCESS;
	}

	public String nouveauemployee() {
		employee = new Employeegestat();
		 

		employee.setNom(nom);
		employee.setAdresse(adresse);
		employee.setCin(cin);
		 
		employee.setTel(tel);
		employee.setSalaire_journalier(salaire_journalier);
 
		serviceEmployee.save(employee);
		listEmp = new ArrayList<Employeegestat>();
		listEmp = serviceEmployee.getAll();
		return SUCCESS;
	}

	public String getAllEmployee() {
		 
		LOG.log(Level.INFO, "Gestat.EmployeeBean#getAllEmployee start serviceEmployeeNull={0}", serviceEmployee == null);
		listEmp = new ArrayList<Employeegestat>();
		listEmp = serviceEmployee.getAll();

		nom = null;
		prenom = null;
		cin = null;
		adresse = null;
		tel=null; 
		salaire_journalier = 0;
		LOG.log(Level.INFO, "Gestat.EmployeeBean#getAllEmployee loaded listEmp={0}", listEmp == null ? -1 : listEmp.size());
		return SUCCESS;
	}

	public String supprimerEmployer() {
		selectedEmp.setStatut(Statut.DEACTIF);
		serviceEmployee.delete(selectedEmp);
		listEmp = new ArrayList<Employeegestat>();
		listEmp = serviceEmployee.getAll();
		return SUCCESS;
	}

	public String updateEmployer(Employeegestat client) {
		try {
			getServiceEmployee().update(client);
			return SUCCESS;
		} catch (DataAccessException e) {
		}
		return ERROR;
	}

	public void onRowEdit(RowEditEvent event) {

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"client change", ((Employeegestat) event.getObject()).getNom());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		updateEmployer((Employeegestat) event.getObject());

	}

	/*
	 * setter and getter
	 * 
	 * **
	 */

	public ServiceAvancegestat getServiceAvance() {
		return serviceAvance;
	}

	public void setServiceAvance(ServiceAvancegestat serviceAvance) {
		this.serviceAvance = serviceAvance;
	}

	public ServiceEmployeegestat getServiceEmployee() {
		return serviceEmployee;
	}

	public void setServiceEmployee(ServiceEmployeegestat serviceEmployee) {
		this.serviceEmployee = serviceEmployee;
	}

	 

	public Employeegestat getEmployee() {
		return employee;
	}

	public void setEmployee(Employeegestat employee) {
		this.employee = employee;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public double getSalaire_journalier() {
		return salaire_journalier;
	}

	public void setSalaire_journalier(double salaire_journalier) {
		this.salaire_journalier = salaire_journalier;
	}

	public List<Employeegestat> getListEmp() {
		LOG.log(Level.INFO, "Gestat.EmployeeBean#getListEmp getter size={0}", listEmp == null ? -1 : listEmp.size());
		return listEmp;
	}

	public void setListEmp(List<Employeegestat> listEmp) {
		this.listEmp = listEmp;
	}

	public Employeegestat getSelectedEmp() {
		return selectedEmp;
	}

	public void setSelectedEmp(Employeegestat selectedEmp) {
		this.selectedEmp = selectedEmp;
	}

	public List<Employeegestat> getFiltredEmp() {
		return filtredEmp;
	}

	public void setFiltredEmp(List<Employeegestat> filtredEmp) {
		this.filtredEmp = filtredEmp;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}


	public List<String> getListcredits() {
		return listcredits;
	}


	public void setListcredits(List<String> listcredits) {
		this.listcredits = listcredits;
	}


	public ServiceEmployee getServiceEmployees() {
		return serviceEmployees;
	}


	public void setServiceEmployees(ServiceEmployee serviceEmployees) {
		this.serviceEmployees = serviceEmployees;
	}


	public List<Employee> getListEmp2() {
		return listEmp2;
	}


	public void setListEmp2(List<Employee> listEmp2) {
		this.listEmp2 = listEmp2;
	}
 
 

 
	  
}
