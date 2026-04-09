package com.tn.shell.ui.gestat;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import org.springframework.dao.DataAccessException;

import com.tn.shell.model.gestat.*;
import com.tn.shell.model.paie.Employee;
import com.tn.shell.model.paie.Pointage;
import com.tn.shell.service.gestat.*;
import com.tn.shell.service.paie.ServiceEmployee;

 

@ManagedBean(name = "AvancegestatBean")
@SessionScoped
public class AvancegestatBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{ServiceAvancegestat}")
	ServiceAvancegestat serviceAvance;
	@ManagedProperty(value = "#{ServiceEmployee}")
	ServiceEmployee serviceEmployee;
	
	@ManagedProperty(value = "#{ServiceTracegestat}")
    ServiceTracegestat servicetrace;
	@ManagedProperty(value = "#{UserService}")
	UserService userservice;
	
 
	private String dates;
	private List<Avancegestat> listAvenc;
	private List<Avancegestat> selectedAvenc;
	private List<Avancegestat> filtredAvenc;
	private List<Employee> listeEmployee;
	private double montant;
	private String nom;
	private Employee employee;
	private Avancegestat selectedAvance;
	private Avancegestat avance;
    private Date date1;
    private String dates1;
    private Date date2;
    private String dates2;
	public String getListavanceByEmployer() {

		return SUCCESS;
	}

	public void onRowEdit(RowEditEvent event) {

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "avance change",
				((Avancegestat) event.getObject()).getId() + "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		updateavance((Avancegestat) event.getObject());

	}

	public String updateavance(Avancegestat avance) {
		try {
			getServiceAvance().update(avance);
			return SUCCESS;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		Utilisateur user=userservice.getCurrentUser();
		Tracegestat t=new Tracegestat();
		t.setAction("update avance"+avance.getId()+ " de "+avance.getEmployee().getNom()+" par"+user.getNom());
		t.setDate(new Date());
		t.setUtilisateur(user);
		servicetrace.save(t);
		return ERROR;
	}
	
	public String getall() {
		
		listAvenc=new ArrayList<Avancegestat>();
		listAvenc=serviceAvance.getAll();
		return SUCCESS;
	}
	
	public String etatAvance()
	{		
		listAvenc=new ArrayList<Avancegestat>();
		date1=new Date();
		date2=new Date();
		listeEmployee = new ArrayList<Employee>();
		listeEmployee = serviceEmployee.getAll();
		return SUCCESS;
	}
	private String total;
	public void getEmployeeByNom2(AjaxBehaviorEvent event) throws ParseException {
		Date d = new Date();double t=0;
		employee = serviceEmployee.getEmployeeByNom(nom);
		if (employee == null) {
			String message = "Veuillez choisir un employe";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
		}
		listAvenc=new ArrayList<Avancegestat>();
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		String d1=s.format(date1);
		String d2=s.format(date2);
		//date1=s.parse(d1);
		//date2=s.parse(d2);
		System.out.println("date format "+date1+"   "+date2);
		listAvenc=serviceAvance.getAvancebDate(date1, date2, employee);
		if(listAvenc.size()>0)
		for(Avancegestat a:listAvenc)t=t+a.getMontant_avance();
		
		DecimalFormat df = new DecimalFormat("#,###.000");
		total=df.format(t);
	}
	public List<Avancegestat> getavancebetween(Pointage p){
		Date date1=new Date();
		Date date2=new Date();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, p.getMois()-1);
		cal.set(Calendar.YEAR, p.getAnnee());
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		date1.setYear(+1900);
		date2.setYear(cal.getWeekYear()+1900);
		date1.setMonth(p.getMois()-1);
		date2.setMonth(p.getMois()-1);
		date1.setDate(1);
		date2.setDate(maxDay);
		listAvenc = new ArrayList<Avancegestat>();
		listAvenc = serviceAvance.getAvancesByEmployeebetweendate(p.getEmployee(), date1, date2);
				 
		return 	listAvenc;
	}
	
	public String getAllavance() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		String d1=s.format(date1);
		String d2=s.format(date2);
		Date d = new Date();
	 	listAvenc = new ArrayList<Avancegestat>();
		/*listAvenc = serviceAvance.getAll(); 
		
		List<Employee> l=new ArrayList<Employee>();
		l=serviceEmployee.getAll();
		for(Employee e:l) {
			listAvenc = new ArrayList<Avancegestat>();
			listAvenc=serviceAvance.getAvancebDate(date1, date2, employee);
		}*/
		return SUCCESS;
	}
	
	public void validergetEtatbyDate(AjaxBehaviorEvent event) {
		listAvenc=new ArrayList<Avancegestat>();
		listAvenc=serviceAvance.getAvancebDate(date1, date2, employee);
		 
	}
	/***
	 * editer avance
	 */

	public void onRowEdit(CellEditEvent event) {

	}

	/*****
	 * nouvelle avance
	 *****/

	public String nouvelleAvance() {
		nom = "";
		montant = 0;
		listeEmployee = new ArrayList<Employee>();
		listeEmployee = serviceEmployee.getAll();
		return SUCCESS;
	}

	public String nouvellesAvances() {
		Date d = new Date();
		avance = new Avancegestat();
		avance.setEmployee(employee);
		avance.setMontant_avance(montant);
		avance.setMois(d.getMonth()+1);
		avance.setAnnee(d.getYear()+1900);
		avance.setDate(d);
		serviceAvance.save(avance);
		listAvenc = new ArrayList<Avancegestat>();
		 
		montant = 0;
		nom = "";
		return SUCCESS;

	}

	

	public void getAllavances(AjaxBehaviorEvent event) {
		Date d = new Date();
		listAvenc = new ArrayList<Avancegestat>();
		listAvenc = serviceAvance.getAll();

	}

	private String getAnneeByIntger(Integer annee) {
		String annees = "2" + annee;

		return annees;
	}

	public void getEmployeeByNom(AjaxBehaviorEvent event) {
		Date d = new Date();
		employee = serviceEmployee.getEmployeeByNom(nom);
		if (employee == null) {
			String message = "Veuillez choisir un employe";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
		}
		else {
		listAvenc = new ArrayList<Avancegestat>();
		listAvenc=serviceAvance.getAvancesByEmployee(employee, d.getYear()+1900, d.getMonth()+1);
		}
	}

	public String listAvenceEncours() {

		return SUCCESS;
	}

	/*
	 * setter and getter
	 * 
	 * **
	 */

	 

	 

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	 
 
 

	public ServiceAvancegestat getServiceAvance() {
		return serviceAvance;
	}

	public void setServiceAvance(ServiceAvancegestat serviceAvance) {
		this.serviceAvance = serviceAvance;
	}

	public List<Avancegestat> getListAvenc() {
		return listAvenc;
	}

	public void setListAvenc(List<Avancegestat> listAvenc) {
		this.listAvenc = listAvenc;
	}

	public List<Avancegestat> getSelectedAvenc() {
		return selectedAvenc;
	}

	public void setSelectedAvenc(List<Avancegestat> selectedAvenc) {
		this.selectedAvenc = selectedAvenc;
	}

	public List<Avancegestat> getFiltredAvenc() {
		return filtredAvenc;
	}

	public void setFiltredAvenc(List<Avancegestat> filtredAvenc) {
		this.filtredAvenc = filtredAvenc;
	}

	 

	public List<Employee> getListeEmployee() {
		return listeEmployee;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	 
	public Avancegestat getSelectedAvance() {
		return selectedAvance;
	}

	public void setSelectedAvance(Avancegestat selectedAvance) {
		this.selectedAvance = selectedAvance;
	}

	public Avancegestat getAvance() {
		return avance;
	}

	public void setAvance(Avancegestat avance) {
		this.avance = avance;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public String getDates1() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates1=s.format(date1);
		return dates1;
	}

	public void setDates1(String dates1) {
		this.dates1 = dates1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public String getDates2() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates2=s.format(date2);
		return dates2;
	}

	public void setDates2(String dates2) {
		this.dates2 = dates2;
	}

	public ServiceEmployee getServiceEmployee() {
		return serviceEmployee;
	}

	public void setServiceEmployee(ServiceEmployee serviceEmployee) {
		this.serviceEmployee = serviceEmployee;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setListeEmployee(List<Employee> listeEmployee) {
		this.listeEmployee = listeEmployee;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public ServiceTracegestat getServicetrace() {
		return servicetrace;
	}

	public void setServicetrace(ServiceTracegestat servicetrace) {
		this.servicetrace = servicetrace;
	}

	public UserService getUserservice() {
		return userservice;
	}

	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	 

}
