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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.primefaces.event.CellEditEvent;

import com.tn.shell.model.gestat.Utilisateur;
import com.tn.shell.model.paie.*;
import com.tn.shell.service.gestat.UserService;
import com.tn.shell.service.paie.*;
  

@ManagedBean(name = "PretBean")
@SessionScoped
public class PretBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{ServiceAnnee}")
	ServiceAnnee serviceAnnee;
	@ManagedProperty(value = "#{ServicePret}")
	ServicePret servicePret;
	@ManagedProperty(value = "#{ServiceEmployee}")
	ServiceEmployee serviceEmployee;
	@ManagedProperty(value = "#{ServicePaie}")
	ServicePaie servicePaie;
	@ManagedProperty(value = "#{ServicePointage}")
	ServicePointage servicePointage;
	@ManagedProperty(value = "#{ServiceTrace}")
	ServiceTrace serviceTrace;
	@ManagedProperty(value = "#{UserService}")
	UserService userservice;
	private double montant_pret;
	private double taux;
	private Integer nbmois;
	private Date date_debut;
	private Integer nb_jour_travail;
	private List<Pret> listpret;
	private Pret selectedPret;
	private List<Pret> filtredPret;
	private List<String> listeEmployee;
	private String nom;
	private Employee employee;
	private Pret pret;
    private List<Pointage> listpointage;
	/* * les champs du pointagre * * */
	private double nb_jour = 20;
	private Pointage pointage;
	private List<String> listannee;
	private Integer annee;
	private Integer mois;
	private String moi;
	private List<String> listMois = new ArrayList<String>();
	private String dates;
	private String annees;
	private List<Employee> listeEmployees;
    private Employee selectedEmp;
	/*****
	 * nouvelle avance
	 * *****/

	@PostConstruct
	public void init() {
		mois();
		Annee();
		moi = getMoisbyIntger(resolveDefaultPretMonth());
	}

	public String nouveauPret() {
		listeEmployee = new ArrayList<String>();
		listeEmployees= new ArrayList<Employee>();
		listeEmployees = serviceEmployee.getAll();
		  

		return SUCCESS;
	}
	private Integer getMoisbyString(String moi) {
		Integer m = 0;
		if (moi.equals("Janvier") )
			m =1 ;
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

	public String nouvauxPrets() {
		pret = new Pret();
		pret.setEmployee(employee);
		pret.setMontant_pret(montant_pret);
		pret.setDate_debut(date_debut);
		pret.setNbmois(nbmois);
		pret.setTaux(taux);
		pret.setDeductionParmois(montant_pret/nbmois);
		servicePret.save(pret);
		listpret = new ArrayList<Pret>();
		listpret = servicePret.getAll();
		return SUCCESS;
	}

	public String getAllpret() {

		listpret = new ArrayList<Pret>();
		listpret = servicePret.getAll();
		return SUCCESS;
	}

	public void getEmployeeByNom(AjaxBehaviorEvent event) {
		 
		employee = serviceEmployee.getEmployeeByNom(nom);
		if(employee==null){
			String message="Veuillez choisir un employe";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(message));
		}
			
	}
	private Integer codes;
	public void updatepontage(AjaxBehaviorEvent event) {
		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
		Pointage p=listpointage.get(codes);
		listpointage.set(codes, p);
		p.setNb_jour(nb_jour);
		nb_jour=20;
	}
	public String listPretEncours() {

		return SUCCESS;
	}

	/**
	 * Les methodes retalif aux pointage
	 * ***/

	public String Pointage() {
		listpointage=new  ArrayList<Pointage>();
		 
		List<Employee> l = new ArrayList<Employee>();
		l = serviceEmployee.getAll5();
		for (Employee e : l)
		{
			
			Pointage p=new Pointage();
			p.setEmployee(e);
			p.setNb_jour(20);
			listpointage.add(p);
		}
		Annee();
		mois();
		moi=getMoisbyIntger(resolveDefaultPretMonth());
		return SUCCESS;
	}
	public void onCellEdit(CellEditEvent event) {
		 double oldValue=  (Double) event.getOldValue();
		 double newValue=  (Double) event.getNewValue();
		 Pointage e=listpointage.get(event.getRowIndex());
		
		   	e.setNb_jour(newValue);	   
		    
		    
		       
	}
	public String ValiderPointage(){
		for(Pointage p:listpointage){			 
			p.setAnnee(annee);
			p.setMois(getMoisbyString(moi));		 
			servicePointage.save(p);
		}
		
		Utilisateur user=userservice.getCurrentUser();
		Tracepaie  t=new Tracepaie();
		t.setAction("validation pointage"+moi+annee+ " par"+user.getNom());
		t.setDate(new Date());
		t.setUtilisateur(user);
		serviceTrace.save(t);
		return SUCCESS;
	}
	public void GetMoisByAnnee(AjaxBehaviorEvent event) {
		listMois = new ArrayList<String>();
		List<Pointage> lp = new ArrayList<Pointage>();
		lp = servicePointage.getPointageByAnnee(annee);
		for (Pointage a : lp)
			listMois.add(getMoisbyIntger(a.getMois()));
		listMois.add(getMoisbyIntger(resolveDefaultPretMonth()));
	}

	private String getMoisbyIntger(Integer moi) {
		String m = "";
		if (moi == 1)
			m = "Janvier";
		else if (moi == 2)
			m = "Fevrier";
		else if (moi == 3)
			m = "Mars";
		else if (moi == 4)
			m = "Avril";
		else if (moi == 5)
			m = "Mai";
		else if (moi == 6)
			m = "Juin";
		else if (moi == 7)
			m = "Juillet";
		else if (moi == 8)
			m = "aout";
		else if (moi == 9)
			m = "Septembre";
		else if (moi == 10)
			m = "Octobre";
		else if (moi == 11)
			m = "Novembre";
		else if (moi == 12)
			m = "Decembre";
		return m;
	}

	public void mois() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, resolveDefaultPretYear());
		calendar.set(Calendar.MONTH, resolveDefaultPretMonth() - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(calendar.getTime());
		listMois = new ArrayList<String>();
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
		mois = resolveDefaultPretMonth();
	}

	private void Annee() {
		List<Annee> l = new ArrayList<Annee>();
		listannee = new ArrayList<String>();
		annees = String.valueOf(resolveDefaultPretYear());
		Annee a = serviceAnnee.findbyDesignation(annees);
		if (a == null) {
			Annee e = new Annee();
			e.setAnnee(annees);
			serviceAnnee.save(e);
		}
		l = serviceAnnee.getAll();
		for (Annee aa : l) {
			listannee.add(aa.getAnnee());
		}
		Integer aa = Integer.parseInt(annees);
		listannee.add(aa + 1 + "");
		annee = Integer.parseInt(annees);
	}

	private int resolveDefaultPretYear() {
		Pointage latestPointage = servicePointage.getMaxPointage();
		if (latestPointage != null && latestPointage.getAnnee() != null) {
			return latestPointage.getAnnee();
		}
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	private int resolveDefaultPretMonth() {
		Pointage latestPointage = servicePointage.getMaxPointage();
		if (latestPointage != null && latestPointage.getMois() != null) {
			return latestPointage.getMois();
		}
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	/*
	 * setter and getter
	 * 
	 * **
	 */

	public ServiceAnnee getServiceAnnee() {
		return serviceAnnee;
	}

	public void setServiceAnnee(ServiceAnnee serviceAnnee) {
		this.serviceAnnee = serviceAnnee;
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

	 

	public ServicePret getServicePret() {
		return servicePret;
	}

	public void setServicePret(ServicePret servicePret) {
		this.servicePret = servicePret;
	}

	public double getMontant_pret() {
		return montant_pret;
	}

	public void setMontant_pret(double montant_pret) {
		this.montant_pret = montant_pret;
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}
	
	

	public Integer getCodes() {
		return codes;
	}
	public void setCodes(Integer codes) {
		this.codes = codes;
	}
	public Integer getNbmois() {
		return nbmois;
	}

	public void setNbmois(Integer nbmois) {
		this.nbmois = nbmois;
	}

	public Date getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}

	public List<Pret> getListpret() {
		return listpret;
	}

	public void setListpret(List<Pret> listpret) {
		this.listpret = listpret;
	}

	public Pret getSelectedPret() {
		return selectedPret;
	}

	public void setSelectedPret(Pret selectedPret) {
		this.selectedPret = selectedPret;
	}

	public List<Pret> getFiltredPret() {
		return filtredPret;
	}

	public void setFiltredPret(List<Pret> filtredPret) {
		this.filtredPret = filtredPret;
	}

	public List<String> getListeEmployee() {
		return listeEmployee;
	}

	public void setListeEmployee(List<String> listeEmployee) {
		this.listeEmployee = listeEmployee;
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

	public Pret getPret() {
		return pret;
	}

	public void setPret(Pret pret) {
		this.pret = pret;
	}

	public double getNb_jour() {
		return nb_jour;
	}

	public void setNb_jour(double nb_jour) {
		this.nb_jour = nb_jour;
	}

	public Pointage getPointage() {
		return pointage;
	}

	public void setPointage(Pointage pointage) {
		this.pointage = pointage;
	}

	public List<String> getListannee() {
		return listannee;
	}

	public void setListannee(List<String> listannee) {
		this.listannee = listannee;
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

	public List<Employee> getListeEmployees() {
		return listeEmployees;
	}

	public void setListeEmployees(List<Employee> listeEmployees) {
		this.listeEmployees = listeEmployees;
	}

	public Integer getNb_jour_travail() {
		return nb_jour_travail;
	}

	public void setNb_jour_travail(Integer nb_jour_travail) {
		this.nb_jour_travail = nb_jour_travail;
	}

	public Employee getSelectedEmp() {
		return selectedEmp;
	}

	public void setSelectedEmp(Employee selectedEmp) {
		this.selectedEmp = selectedEmp;
	}
	public List<Pointage> getListpointage() {
		return listpointage;
	}
	public void setListpointage(List<Pointage> listpointage) {
		this.listpointage = listpointage;
	}
	private Pointage selectedPointage;
	public Pointage getSelectedPointage() {
		return selectedPointage;
	}
	public void setSelectedPointage(Pointage selectedPointage) {
		this.selectedPointage = selectedPointage;
	}
	public ServiceTrace getServiceTrace() {
		return serviceTrace;
	}
	public void setServiceTrace(ServiceTrace serviceTrace) {
		this.serviceTrace = serviceTrace;
	}
	public UserService getUserservice() {
		return userservice;
	}
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

}
