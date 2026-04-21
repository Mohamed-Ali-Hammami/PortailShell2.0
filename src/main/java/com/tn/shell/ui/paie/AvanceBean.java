package com.tn.shell.ui.paie;

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

import com.tn.shell.model.gestat.Avancegestat;
import com.tn.shell.model.paie.*;
import com.tn.shell.service.gestat.ServiceAvancegestat;
import com.tn.shell.service.paie.*;
import com.tn.shell.ui.gestat.EmployeeBean;
@ManagedBean(name = "AvanceBean")
@SessionScoped
public class AvanceBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{ServiceAnnee}")
	ServiceAnnee serviceAnnee;
	@ManagedProperty(value = "#{ServiceTrace}")
	ServiceTrace serviceTrace;
	@ManagedProperty(value = "#{ServiceSociete}")
	ServiceSociete serviceSociete;
 
	@ManagedProperty(value = "#{ServiceAvancegestat}")
	ServiceAvancegestat serviceavanceg;
	@ManagedProperty(value = "#{ServiceEmployee}")
	ServiceEmployee serviceEmployee;
	@ManagedProperty(value = "#{ServicePaie}")
	ServicePaie servicePaie;
	@ManagedProperty(value = "#{ServicePointage}")
	ServicePointage servicePointage;
	 
	 
    
	private List<String> listannee;
	private Integer annee;
	private Integer mois;
	private String moi;
	private List<String> listMois = new ArrayList<String>();
	private String dates;
	private String annees;
	  
	 
	private List<Employee> listeEmployee;
	private double montant;
	private String nom;
	private Employee employee;
	private List<Avancegestat> listAvenc;
	private List<Avancegestat> selectedAvenc;
	private List<Avancegestat> filtredAvenc;
	private Avancegestat selectedAvance;
	private Avancegestat avance;
	 
	
	
	 
	@PostConstruct
	public void init() {
		Annee();
		mois();
		moi = getMoisbyIntger(resolveDefaultAvanceMonth());
		refreshAvancesForCurrentPeriod();
	}

	public void mois() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, resolveDefaultAvanceYear());
		calendar.set(Calendar.MONTH, resolveDefaultAvanceMonth() - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(calendar.getTime());
		listMois=new ArrayList<String>();
		listMois.add("Janvier");
		listMois.add("Fevrier");
		listMois.add("Mars");
		listMois.add("Avril");
		listMois.add("Mai");
		listMois.add("Juin");
		listMois.add("Juillet");
		listMois.add("aout");
		listMois.add("Septembre");
		listMois.add("Octobre");
		listMois.add("Novembre");
		listMois.add("Decembre");
		mois = resolveDefaultAvanceMonth();
		moi = getMoisbyIntger(mois);
		 
	}
    
	 
	public String getListavanceByEmployer(){
		 refreshAvancesForCurrentPeriod();
		 return SUCCESS;
	 }

	public String getAllavance() {
		refreshAvancesForCurrentPeriod();
		return SUCCESS;
	}
	private Integer getMoisbyString(String moi) {
			if (moi == null || moi.trim().isEmpty()) {
				return resolveDefaultAvanceMonth();
			}
			Integer m = 0;
			if (moi.equals("Janvier"))
				m = 1;
			else if (moi.equals("Fevrier"))
				m = 2;
			else if (moi.equals("Mars"))
				m = 3;
			else if (moi.equals("Avril"))
				m = 4;
			else if (moi.equals("Mai"))
				m = 5;
			else if (moi.equals("Juin"))
				m = 6;
			else if (moi.equals("Juillet"))
				m = 7;
			else if (moi.equals("aout"))
				m = 8;
			else if (moi.equals("Septembre"))
				m = 9;
			else if (moi.equals("Octobre"))
				m = 10;
			else if (moi.equals("Novembre"))
				m = 11;
			else if (moi.equals("Decembre"))
				m = 12;
			return m;
		}
	 
  
	private void Annee() {
		List<Annee> l = new ArrayList<Annee>();
		listannee = new ArrayList<String>();
		  annees = String.valueOf(resolveDefaultAvanceYear());
		Annee a = serviceAnnee.findbyDesignation(annees);
		if (a == null) {
			Annee e = new Annee();
			e.setAnnee(annees);
			serviceAnnee.save(e);
		}
		l = serviceAnnee.getAll();
		for (Annee aa : l){
			listannee.add(aa.getAnnee());		     
		}
		Integer aa=Integer.parseInt(annees);
		listannee.add(aa+1+"");
		 annee=Integer.parseInt(annees);
	}

	
	/***
	 * editer
	 * avance
	 * */
	
	public void onRowEdit(CellEditEvent event) { 
		
	}
	/*****
	 * nouvelle avance
	 * *****/
	 

	public String nouvelleAvance() {
		nom="";
		montant=0;
		listeEmployee=new ArrayList<Employee>();
		 
		 
		listeEmployee=serviceEmployee.getAll();
		 
		Annee();
		mois();
		moi = getMoisbyIntger(resolveDefaultAvanceMonth());
		 
		return SUCCESS;
	}

	public String nouvellesAvances() {
		if (employee == null) {
			employee = serviceEmployee.getEmployeeByNom(nom);
		}
		if (employee == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Veuillez choisir un employe"));
			return ERROR;
		}
		avance = new Avancegestat();
		avance.setEmployee(employee);
		avance.setMontant_avance(montant);
		avance.setMois(mois == null ? resolveDefaultAvanceMonth() : mois);
		avance.setAnnee(annee == null ? resolveDefaultAvanceYear() : annee);
		avance.setDate(new Date());
		serviceavanceg.save(avance);
		refreshAvancesForCurrentPeriod();
		montant = 0;
		nom = "";
		employee = null;
		return SUCCESS;
	}

	private void refreshAvancesForCurrentPeriod() {
		if (annees == null || annees.trim().isEmpty()) {
			annees = String.valueOf(resolveDefaultAvanceYear());
		}
		if (moi == null || moi.trim().isEmpty()) {
			moi = getMoisbyIntger(resolveDefaultAvanceMonth());
		}
		int selectedYear = Integer.parseInt(annees);
		int selectedMonth = getMoisbyString(moi);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, selectedYear);
		calendar.set(Calendar.MONTH, selectedMonth - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date date1 = new Date(selectedYear - 1900, selectedMonth - 1, 1, 0, 0, 0);
		Date date2 = new Date(selectedYear - 1900, selectedMonth - 1, maxDay, 23, 59, 59);
		listAvenc = serviceavanceg.getAvancesBybetweendate(date1, date2);
		if (listAvenc == null) {
			listAvenc = new ArrayList<Avancegestat>();
		}
	}

	public void getAllavances(AjaxBehaviorEvent event) {
		refreshAvancesForCurrentPeriod();
	}

	public String Visualiser() {
		refreshAvancesForCurrentPeriod();
		return SUCCESS;
	}

	private int resolveDefaultAvanceYear() {
		Pointage latestPointage = servicePointage.getMaxPointage();
		if (latestPointage != null && latestPointage.getAnnee() != null) {
			return latestPointage.getAnnee();
		}
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	private int resolveDefaultAvanceMonth() {
		Pointage latestPointage = servicePointage.getMaxPointage();
		if (latestPointage != null && latestPointage.getMois() != null) {
			return latestPointage.getMois();
		}
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	 

	 
	 
	 
	private String getMoisbyIntger(Integer moi){
		if (moi == null) {
			return "";
		}
		String m="";
		if(moi==1) m="Janvier";
		else if(moi==2) m="Fevrier";
		else if(moi==3) m="Mars";
		else if(moi==4) m="Avril";
		else if(moi==5) m="Mai";
		else if(moi==6) m="Juin";
		else if(moi==7) m="Juillet";
		else if(moi==8) m="aout";
		else if(moi==9) m="Septembre";
		else if(moi==10) m="Octobre";
		else if(moi==11) m="Novembre";
		else if(moi==12) m="Decembre";
		return m;
	}
	
	
	private String getAnneeByIntger(Integer annee){
		String annees="2"+annee;
		
		return annees;
	}
	public void getEmployeeByNom(AjaxBehaviorEvent event) {
		 
		 employee=serviceEmployee.getEmployeeByNom(nom);
			if(employee==null){
				String message="Veuillez choisir un employe";
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(message));
			}
	}
	
	public void getMoisBymoi(AjaxBehaviorEvent event) {
		if(moi.equals("Janvier")) mois=1;
		else if(moi.equals("Fevrier")) mois=2;
		else if(moi.equals("Mars")) mois=3;
		else if(moi.equals("Avril")) mois=4;
		else if(moi.equals("Mai")) mois=5;
		else if(moi.equals("Juin")) mois=6;
		else if(moi.equals("Juillet")) mois=7;
		else if(moi.equals("aout")) mois=8;
		else if(moi.equals("Septembre")) mois=9;
		else if(moi.equals("Octobre")) mois=10;
		else if(moi.equals("Novembre")) mois=11;
		else if(moi.equals("Decembre")) mois=12;
		 
	}
	
	public Integer getMoisBynbjour(String moi) {
		mois=null;
		if(moi.equals("Janvier")) mois=31;
		else if(moi.equals("Fevrier")) mois=28;
		else if(moi.equals("Mars")) mois=31;
		else if(moi.equals("Avril")) mois=30;
		else if(moi.equals("Mai")) mois=31;
		else if(moi.equals("Juin")) mois=30;
		else if(moi.equals("Juillet")) mois=31;
		else if(moi.equals("aout")) mois=31;
		else if(moi.equals("Septembre")) mois=30;
		else if(moi.equals("Octobre")) mois=31;
		else if(moi.equals("Novembre")) mois=30;
		else if(moi.equals("Decembre")) mois=31;
		 return mois;
	}
	public Integer getMoisBymoi2(String moi) {
		if(moi.equals("Janvier")) mois=1;
		else if(moi.equals("Fevrier")) mois=2;
		else if(moi.equals("Mars")) mois=3;
		else if(moi.equals("Avril")) mois=4;
		else if(moi.equals("Mai")) mois=5;
		else if(moi.equals("Juin")) mois=6;
		else if(moi.equals("Juillet")) mois=7;
		else if(moi.equals("aout")) mois=8;
		else if(moi.equals("Septembre")) mois=9;
		else if(moi.equals("Octobre")) mois=10;
		else if(moi.equals("Novembre")) mois=11;
		else if(moi.equals("Decembre")) mois=12;
		 return mois;
	}
	
	public void getAnneeByAnnees(AjaxBehaviorEvent event) {
		annee=Integer.parseInt(annees);
		
	}
	
	 public String listAvenceEncours(){
		  refreshAvancesForCurrentPeriod();
		   return SUCCESS;
	   }
	   
	   

	/*
	 * setter and getter
	 * 
	 * **
	 */
	 
	 

	public ServiceAnnee getServiceAnnee() {
		return serviceAnnee;
	}

	public List<Employee> getListeEmployee() {
		return listeEmployee;
	}

	public void setListeEmployee(List<Employee> listeEmployee) {
		this.listeEmployee = listeEmployee;
	}

	public void setServiceAnnee(ServiceAnnee serviceAnnee) {
		this.serviceAnnee = serviceAnnee;
	}

	public ServiceTrace getServiceTrace() {
		return serviceTrace;
	}

	public void setServiceTrace(ServiceTrace serviceTrace) {
		this.serviceTrace = serviceTrace;
	}

	public ServiceSociete getServiceSociete() {
		return serviceSociete;
	}

	public void setServiceSociete(ServiceSociete serviceSociete) {
		this.serviceSociete = serviceSociete;
	}

	 

	public ServiceEmployee getServiceEmployee() {
		return serviceEmployee;
	}

	public void setServiceEmployee(ServiceEmployee serviceEmployee) {
		this.serviceEmployee = serviceEmployee;
	}

	public ServicePaie getServicePaie() {
		return servicePaie;
	}

	public void setServicePaie(ServicePaie servicePaie) {
		this.servicePaie = servicePaie;
	}

	public ServicePointage getServicePointage() {
		return servicePointage;
	}

	public void setServicePointage(ServicePointage servicePointage) {
		this.servicePointage = servicePointage;
	}

	 

	public List<String> getListannee() {
		return listannee;
	}

	public void setListannee(List<String> listannee) {
		this.listannee = listannee;
	}

	 
 

	public List<String> getListMois() {
		return listMois;
	}

	public void setListMois(List<String> listMois) {
		this.listMois = listMois;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public String getAnnees() {
		return annees;
	}

	public void setAnnees(String annees) {
		this.annees = annees;
	}
 

	public Integer getAnnee() {
		return annee;
	}



	public void setAnnee(Integer annee) {
		this.annee = annee;
	}



	public Integer getMois() {
		return mois;
	}



	public void setMois(Integer mois) {
		this.mois = mois;
	}



	public String getMoi() {
		return moi;
	}



	public void setMoi(String moi) {
		this.moi = moi;
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



	public Employee getEmployee() {
		return employee;
	}



	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

 
	public ServiceAvancegestat getServiceavanceg() {
		return serviceavanceg;
	}

	public void setServiceavanceg(ServiceAvancegestat serviceavanceg) {
		this.serviceavanceg = serviceavanceg;
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

}
