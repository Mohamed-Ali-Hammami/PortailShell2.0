package com.tn.shell.ui.paie;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import com.tn.shell.model.paie.Employee;
import com.tn.shell.model.paie.Status;
import com.tn.shell.model.shop.*;
import com.tn.shell.ui.common.UiDateDefaults;
import com.tn.shell.service.paie.ServiceEmployee;
import com.tn.shell.service.shop.ServiceLigneAlimentation;
import com.tn.shell.service.shop.ServiceLignetransfert;
import com.tn.shell.service.shop.ServiceLignevente;
import com.tn.shell.service.shop.ServiceProduit;

@ManagedBean(name = "AccueilBean")
@SessionScoped
public class Accueil {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	private Date date1;
	private Date date2;
	
	private Integer code;
	private String nom;
	private double quantiteinitial;
	private double quantitefinal;
	private String totalvente;
	private String totaltransfert;
	private String currentView = "/shellpaie/fragments/home.xhtml";
	private List<Cahierstock> liststock;
	private Produit produit;
	private List<Produit> listproduits;
	private List<Employee> listemployee;
	@ManagedProperty(value = "#{ServiceProduit}")
	ServiceProduit serviceProduit;
	@ManagedProperty(value = "#{ServiceLignevente}")
	ServiceLignevente serviceLignevente;
	@ManagedProperty(value = "#{ServiceEmployee}")
	ServiceEmployee  serviceEmployee;
	@ManagedProperty(value = "#{ServiceLigneAlimentation}")
	ServiceLigneAlimentation serviceLignetransfert;
	
	
	public String gestat() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null && facesContext.isPostback()) {
			return SUCCESS;
		}
		  Calendar calendar = Calendar.getInstance();
		  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		  
		calendar.add(Calendar.DATE, 7);
		listemployee=new ArrayList<Employee>();
		List<Employee> employees = serviceEmployee.getEmployeefincontrat(calendar.getTime());
		if (employees != null) {
			listemployee = employees;
		}
		return SUCCESS;
	}
	
	public String paie() {
		return SUCCESS;
	}

	public void bootstrapPaieView(ComponentSystemEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext == null || facesContext.isPostback()) {
			return;
		}
		UIViewRoot viewRoot = facesContext.getViewRoot();
		if (viewRoot == null) {
			return;
		}
		String view = facesContext.getExternalContext().getRequestParameterMap().get("view");
		if (view == null || view.trim().isEmpty()) {
			gestat();
			return;
		}
		switch (view) {
		case "employees":
		case "listemplyee":
		case "test":
			initializeEmployeeDirectory();
			break;
		case "avances":
		case "gestion_des_avances":
		case "fiche_de_avance":
			{
				AvanceBean b = bean("#{AvanceBean}", AvanceBean.class);
				if (b != null) b.init();
			}
			break;
		case "nouvelleAvance":
			{
				AvanceBean b = bean("#{AvanceBean}", AvanceBean.class);
				if (b != null) b.nouvelleAvance();
			}
			break;
		case "gestion_des_prets":
			{
				PretBean b = bean("#{PretBean}", PretBean.class);
				if (b != null) b.getAllpret();
			}
			break;
		case "nouvelPret":
			{
				PretBean b = bean("#{PretBean}", PretBean.class);
				if (b != null) b.nouveauPret();
			}
			break;
		case "pointage":
			{
				PretBean b = bean("#{PretBean}", PretBean.class);
				if (b != null) b.Pointage();
			}
			break;
		case "paie":
		case "paie-declaree":
		case "fiche-paie":
		case "fiche_de_paie":
			{
				PaisBeans b = bean("#{PaisBeans}", PaisBeans.class);
				if (b != null) b.etat();
			}
			break;
		case "paie-non-declaree":
		case "paieNondeclaree":
		case "journal-non-declare":
		case "journalNondeclaree":
			{
				PaisBeans b = bean("#{PaisBeans}", PaisBeans.class);
				if (b != null) b.journalNondeclare();
			}
			break;
		case "paieNondeclareeRendement":
		case "journalNondeclaree2":
			{
				PaisBeans b = bean("#{PaisBeans}", PaisBeans.class);
				if (b != null) b.journalNondeclareparrendement();
			}
			break;
		case "edition":
		case "journal":
		case "journal-declare":
		case "ordre-virement":
		case "ordre_virement":
			{
				PaisBeans b = bean("#{PaisBeans}", PaisBeans.class);
				if (b != null) b.journal();
			}
			break;
		case "journal-total":
		case "journaltotal":
			{
				PaisBeans b = bean("#{PaisBeans}", PaisBeans.class);
				if (b != null) b.journaltotal();
			}
			break;
		case "EtatPresence":
			{
				PaisBeans b = bean("#{PaisBeans}", PaisBeans.class);
				if (b != null) b.etatPresence();
			}
			break;
		case "etatannuel":
			{
				PaisBeans b = bean("#{PaisBeans}", PaisBeans.class);
				if (b != null) b.etatannuel();
			}
			break;
		case "traixiememois":
			{
				PaisBeans b = bean("#{PaisBeans}", PaisBeans.class);
				if (b != null) b.traixiemeMoi();
			}
			break;
		case "etat_avance":
			{
				PaisBeans b = bean("#{PaisBeans}", PaisBeans.class);
				if (b != null) b.initializeEtatAvance();
			}
			break;
		case "gestion-elements":
		case "gestion_des_elements_de_la_paie":
			{
				PaieBean b = bean("#{PaieBean}", PaieBean.class);
				if (b != null) b.allGestions();
			}
			break;
		case "ajouter_element_de_la_paie":
			{
				PaieBean b = bean("#{PaieBean}", PaieBean.class);
				if (b != null) b.nouvelleGestion();
			}
			break;
		case "administration":
		case "categorie":
			{
				PaieBean b = bean("#{PaieBean}", PaieBean.class);
				if (b != null) b.tableauSalaires();
			}
			break;
		case "nouveauEmployee":
			{
				PaieBean b = bean("#{PaieBean}", PaieBean.class);
				if (b != null) b.nouvemployee();
			}
			break;
		case "pointageemployee":
			{
				PaieBean b = bean("#{PaieBean}", PaieBean.class);
				if (b != null) b.pointageemployee();
			}
			break;
		case "impressionphotos":
			{
				PaieBean b = bean("#{PaieBean}", PaieBean.class);
				if (b != null) b.impressionPhotos();
			}
			break;
		case "historique":
			{
				PaisBeans b = bean("#{PaisBeans}", PaisBeans.class);
				if (b != null) b.historique_empl();
			}
			break;
		case "historique_employee":
			initializeEmployeeHistory();
			break;
		case "historiquevetement":
			initializeEmployeeVetementHistory();
			break;
		case "fiche_de_paieemployee":
			initializeEmployeeFichePaie();
			break;
		case "conge-pointage":
		case "pointageconge":
			{
				CongeBean b = bean("#{CongeBean}", CongeBean.class);
				if (b != null) b.Pointageconge();
			}
			break;
		case "conge-etat":
		case "etatconge":
			{
				CongeBean b = bean("#{CongeBean}", CongeBean.class);
				if (b != null) b.etatPointage();
			}
			break;
		case "conge-journal":
		case "journalconge":
			{
				CongeBean b = bean("#{CongeBean}", CongeBean.class);
				if (b != null) b.journalconge();
			}
			break;
		case "prime-note":
		case "note_annuaire":
			{
				NoteBean b = bean("#{NoteBean}", NoteBean.class);
				if (b != null) b.note();
			}
			break;
		case "prime-etat":
		case "etatprime":
			{
				NoteBean b = bean("#{NoteBean}", NoteBean.class);
				if (b != null) b.etatprime();
			}
			break;
		case "prime-journal":
		case "journalprime":
			{
				NoteBean b = bean("#{NoteBean}", NoteBean.class);
				if (b != null) b.journalprime();
			}
			break;
		case "rappel":
			{
				RappelBean b = bean("#{RappelBean}", RappelBean.class);
				if (b != null) b.accesRappel();
			}
			break;
		case "modifierEmployee":
			initializeEmployeeEditor();
			break;
		case "modifiertableauSalaire":
			{
				PaieBean b = bean("#{PaieBean}", PaieBean.class);
				if (b != null) b.modifiertableau();
			}
			break;
		case "note_employee":
			{
				PaieBean b = bean("#{PaieBean}", PaieBean.class);
				if (b != null) b.note();
			}
			break;
		case "modifierPaie":
			initializePaieEditor();
			break;
		case "validation":
			break;
		case "journalrappel":
		case "validerrappel":
			{
				RappelBean b = bean("#{RappelBean}", RappelBean.class);
				if (b != null) b.journal();
			}
			break;
		default:
			break;
		}

	}

	private void initializeEmployeeDirectory() {
		PaieBean bean = bean("#{PaieBean}", PaieBean.class);
		if (bean != null) {
			bean.getAllEmployee();
		}
	}

	private void initializeEmployeeEditor() {
		PaieBean bean = bean("#{PaieBean}", PaieBean.class);
		if (bean == null) {
			return;
		}
		bean.getAllEmployee();
		Employee employee = getDefaultEmployee();
		if (employee != null) {
			bean.setSelectedEmp(employee);
			bean.succes();
		}
	}

	private void initializeEmployeeHistory() {
		PaieBean bean = bean("#{PaieBean}", PaieBean.class);
		if (bean == null) {
			return;
		}
		bean.getAllEmployee();
		Employee employee = getDefaultEmployee();
		if (employee != null) {
			bean.setSelectedEmp(employee);
			bean.historique();
		}
	}

	private void initializeEmployeeVetementHistory() {
		PaieBean bean = bean("#{PaieBean}", PaieBean.class);
		if (bean == null) {
			return;
		}
		bean.getAllEmployee();
		Employee employee = getDefaultEmployee();
		if (employee != null) {
			bean.setSelectedEmp(employee);
			bean.historiquevetement();
		}
	}

	private void initializeEmployeeFichePaie() {
		PaieBean bean = bean("#{PaieBean}", PaieBean.class);
		if (bean == null) {
			return;
		}
		bean.getAllEmployee();
		bean.note();
		Employee employee = getDefaultEmployee();
		if (employee != null) {
			bean.setSelectedEmp(employee);
			bean.historique();
		}
	}

	private void initializePaieEditor() {
		PaisBeans bean = bean("#{PaisBeans}", PaisBeans.class);
		if (bean == null) {
			return;
		}
		Employee employee = getDefaultEmployee();
		if (employee != null) {
			bean.setEmployer(employee.getNom());
			bean.modifierfiche();
		}
	}

	private Employee getDefaultEmployee() {
		if (serviceEmployee == null) {
			return null;
		}
		List<Employee> employees = serviceEmployee.getEmployeeparstats(Status.Declare);
		if (employees == null || employees.isEmpty()) {
			employees = serviceEmployee.getAll();
		}
		if (employees == null || employees.isEmpty()) {
			return null;
		}
		return employees.get(0);
	}

	private <T> T bean(String expression, Class<T> beanClass) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return facesContext == null ? null : facesContext.getApplication().evaluateExpressionGet(facesContext, expression, beanClass);
	}

	private String show(String view) {
		currentView = view;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null) {
			facesContext.getExternalContext().getSessionMap().put("paieCurrentView", view);
		}
		return null;
	}

	public String getCurrentView() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null) {
			Object sessionView = facesContext.getExternalContext().getSessionMap().get("paieCurrentView");
			if (sessionView instanceof String && !((String) sessionView).trim().isEmpty()) {
				currentView = (String) sessionView;
			}
		}
		if (currentView == null || currentView.trim().isEmpty()) {
			currentView = "/shellpaie/fragments/home.xhtml";
		}
		return currentView;
	}

	public void setCurrentView(String currentView) {
		this.currentView = currentView;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null) {
			facesContext.getExternalContext().getSessionMap().put("paieCurrentView", currentView);
		}
	}

	public String showHome() {
		return show("/shellpaie/fragments/home.xhtml");
	}

	public String showEmployees() {
		return show("/shellpaie/fragments/listemplyee.xhtml");
	}

	public String showAvances() {
		return show("/shellpaie/fragments/gestion_des_avances.xhtml");
	}

	public String showPointage() {
		return show("/shellpaie/fragments/pointage.xhtml");
	}

	public String showPaieDeclaree() {
		return show("/shellpaie/fragments/paie.xhtml");
	}

	public String showPaieNonDeclaree() {
		return show("/shellpaie/fragments/paieNondeclaree.xhtml");
	}

	public String showFichePaie() {
		return show("/shellpaie/fragments/fiche_de_paie.xhtml");
	}

	public String showGestionElements() {
		return show("/shellpaie/fragments/gestion_des_elements_de_la_paie.xhtml");
	}

	public String showEdition() {
		return show("/shellpaie/fragments/edition.xhtml");
	}

	public String showHistorique() {
		return show("/shellpaie/fragments/historique.xhtml");
	}

	public String showJournalDeclare() {
		return show("/shellpaie/fragments/journal.xhtml");
	}

	public String showJournalNonDeclare() {
		return show("/shellpaie/fragments/journalNondeclaree.xhtml");
	}

	public String showJournalTotal() {
		return show("/shellpaie/fragments/journaltotal.xhtml");
	}

	public String showOrdreVirement() {
		return show("/shellpaie/fragments/ordre_virement.xhtml");
	}

	public String showAdministration() {
		return show("/shellpaie/fragments/categorie.xhtml");
	}

	public String showPointageConge() {
		return show("/shellpaie/fragments/pointageconge.xhtml");
	}

	public String showEtatConge() {
		return show("/shellpaie/fragments/etatconge.xhtml");
	}

	public String showJournalConge() {
		return show("/shellpaie/fragments/journalconge.xhtml");
	}

	public String showNoteAnnuaire() {
		return show("/shellpaie/fragments/note_annuaire.xhtml");
	}

	public String showEtatPrime() {
		return show("/shellpaie/fragments/etatprime.xhtml");
	}

	public String showJournalPrime() {
		return show("/shellpaie/fragments/journalprime.xhtml");
	}
	
	public String lavage() {
		return SUCCESS;
	}
	public String cahierdestock() {
		initializeStockDates();
		 
		code=null;
		quantitefinal=0;
		DecimalFormat df=new DecimalFormat("0");
		SimpleDateFormat s=new SimpleDateFormat("dd-MM-yyyy");
		quantiteinitial=0;
		totaltransfert=df.format(0);
		totalvente=df.format(0);
		nom=null;
		liststock=new ArrayList<Cahierstock>();
		/*List<Lignealimentation > lt=new ArrayList<Lignealimentation>();
		lt=serviceLignetransfert.getAll();
		for(Lignealimentation l:lt) {
			l.setDates(s.format(l.getDate()));
			serviceLignetransfert.update(l);
		}*/
		
		return SUCCESS;
		
	}
	
	public void getproduitbycode(AjaxBehaviorEvent event) {
		produit=serviceProduit.Findbycodeshop(code);
		if(produit!=null)
			nom=produit.getNom();
	}
	
	public void gethistoriquevente(AjaxBehaviorEvent event) {
		ensureStockFilters();
		if (produit == null) {
			liststock = new ArrayList<Cahierstock>();
			quantitefinal = quantiteinitial;
			totaltransfert = "0";
			totalvente = "0";
			return;
		}
		
		double tv=0;;
		double  si=quantiteinitial;
		double tt=0;
		SimpleDateFormat sf=new SimpleDateFormat("dd-MM-yyyy");
		DecimalFormat df=new DecimalFormat("0");
		List<String> ld=new ArrayList<String>();
		Calendar cursor = Calendar.getInstance();
		cursor.setTime(UiDateDefaults.startOfDay(date1));
		Calendar end = Calendar.getInstance();
		end.setTime(UiDateDefaults.startOfDay(date2));
	 
		 while(!cursor.after(end)) {
			 ld.add(sf.format(cursor.getTime()));
			 cursor.add(Calendar.DATE, 1);
		 }
	 
		 
		if(ld.size()>0)
		for(String s:ld) {
			Cahierstock cs=new Cahierstock();
			double qte=0;
			double qtet=0;
		 
		   qte= serviceLignevente.getAllventeparDateandproduit(s, produit);
			
	 
		tv=tv+qte;
		
		si=si-qte;
		 
		 
		qtet=serviceLignetransfert.getlisttransferbydateandproduit(s, produit);
		 
		tt=tt+qtet;
		 si=si+qtet;
		cs.setDate(s);
		cs.setEntree(df.format(qtet));
		cs.setVente(df.format(qte));
		cs.setStock(df.format(si));
		liststock.add(cs);
		
		}
		quantitefinal=si;
		totaltransfert=df.format(tt);
		totalvente=df.format(tv);
		 
	}

	private void ensureStockFilters() {
		if (liststock == null) {
			liststock = new ArrayList<Cahierstock>();
		}
		if (date1 == null || date2 == null) {
			initializeStockDates();
		}
	}

	private void initializeStockDates() {
		Date latestBusinessDate = resolveLatestStockDate();
		Calendar start = Calendar.getInstance();
		start.setTime(latestBusinessDate);
		start.set(Calendar.DAY_OF_MONTH, 1);
		date1 = UiDateDefaults.startOfDay(start.getTime());
		date2 = UiDateDefaults.endOfDay(latestBusinessDate);
	}

	private Date resolveLatestStockDate() {
		Date latest = null;
		Lignevente latestSale = serviceLignevente.getmaxlignevente();
		if (latestSale != null) {
			latest = UiDateDefaults.latest(latest, latestSale.getDate());
		}
		List<Lignealimentation> alimentations = serviceLignetransfert.getAll();
		if (alimentations != null) {
			for (Lignealimentation alimentation : alimentations) {
				latest = UiDateDefaults.latest(latest, alimentation.getDate());
			}
		}
		return UiDateDefaults.coalesce(latest, new Date());
	}

	
	public String shop() {
		
		return SUCCESS;
	}
	
	public String transport() {
		return SUCCESS;
	}
	
	public String banque() {
		return SUCCESS;
	}
	public Date getDate1() {
		return date1;
	}
	public void setDate1(Date date1) {
		this.date1 = date1;
	}
	public Date getDate2() {
		return date2;
	}
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	 
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public double getQuantiteinitial() {
		return quantiteinitial;
	}
	public void setQuantiteinitial(double quantiteinitial) {
		this.quantiteinitial = quantiteinitial;
	}
	public double getQuantitefinal() {
		return quantitefinal;
	}
	public void setQuantitefinal(double quantitefinal) {
		this.quantitefinal = quantitefinal;
	}
	public List<Cahierstock> getListstock() {
		return liststock;
	}
	public void setListstock(List<Cahierstock> liststock) {
		this.liststock = liststock;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public ServiceLignevente getServiceLignevente() {
		return serviceLignevente;
	}
	public void setServiceLignevente(ServiceLignevente serviceLignevente) {
		this.serviceLignevente = serviceLignevente;
	}
	public ServiceProduit getServiceProduit() {
		return serviceProduit;
	}
	public void setServiceProduit(ServiceProduit serviceProduit) {
		this.serviceProduit = serviceProduit;
	}
	 
	public ServiceLigneAlimentation getServiceLignetransfert() {
		return serviceLignetransfert;
	}
	public void setServiceLignetransfert(ServiceLigneAlimentation serviceLignetransfert) {
		this.serviceLignetransfert = serviceLignetransfert;
	}
	public String getTotalvente() {
		return totalvente;
	}
	public void setTotalvente(String totalvente) {
		this.totalvente = totalvente;
	}
	public String getTotaltransfert() {
		return totaltransfert;
	}
	public void setTotaltransfert(String totaltransfert) {
		this.totaltransfert = totaltransfert;
	}
	public List<Produit> getListproduits() {
		return listproduits;
	}
	public void setListproduits(List<Produit> listproduits) {
		this.listproduits = listproduits;
	}

	public List<Employee> getListemployee() {
		if (listemployee == null) {
			listemployee = new ArrayList<Employee>();
		}
		return listemployee;
	}

	public void setListemployee(List<Employee> listemployee) {
		this.listemployee = listemployee;
	}

	public ServiceEmployee getServiceEmployee() {
		return serviceEmployee;
	}

	public void setServiceEmployee(ServiceEmployee serviceEmployee) {
		this.serviceEmployee = serviceEmployee;
	}
	 
	
	
	
}
