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
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import com.tn.shell.model.paie.Employee;
import com.tn.shell.model.shop.*;
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
		System.out.println("Il y a 6 mois, nous étions le: "+sdf.format(calendar.getTime()));
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
	
	public String lavage() {
		return SUCCESS;
	}
	public String cahierdestock() {
		date1=new Date();
		date1.setDate(1);
		date2=new Date();		
		 
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
		liststock=new ArrayList<Cahierstock>();
		
		double tv=0;;
		double  si=quantiteinitial;
		double tt=0;
		SimpleDateFormat sf=new SimpleDateFormat("dd-MM-yyyy");
		DecimalFormat df=new DecimalFormat("0");
		List<String> ld=new ArrayList<String>();
	 
		 for(int i=date1.getDate();i<=date2.getDate();i++) {
			 Date d=new Date();
			 d.setDate(i);
			 String ds=sf.format(d);
			 ld.add(ds);
			 
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
