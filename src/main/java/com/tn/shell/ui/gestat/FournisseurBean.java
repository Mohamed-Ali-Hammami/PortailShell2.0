package com.tn.shell.ui.gestat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.RowEditEvent;
import org.springframework.dao.DataAccessException;

import com.tn.shell.model.shop.Fournisseur;
import com.tn.shell.model.shop.Statut;
import com.tn.shell.service.shop.ServiceFournisseur;

 
 
@ManagedBean(name="FournisseursBean")
@SessionScoped
public class FournisseurBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";	
	 

	@ManagedProperty(value="#{ServiceFournisseur}") 
    ServiceFournisseur serviceFournisseur;
	 
	 
 
	private double honoraire;
	private double montantbrut;
	private double montantbrut2;
	private String montantbruts2;
	private List<Fournisseur> filterfournisseurs;
	private String prenom;
	private String code;
	private String categorie;
	 private String matriculfisacl;
	private String etab;
	private String mois;
	private List<String> listMois = new ArrayList<String>();
	private List<String> listAnnee = new ArrayList<String>();
	 
 	 private String tel;
 	 private double ca;
 	 private double reste;
	private String nom;
	 private String restes;
	private Date date2;
	 
	private double retenu;
	private double montant;
	private String date;
	private String dates;
	 
	private String year;
	private Fournisseur fournisseur;
	private Fournisseur fournisseur2;
	 
	private List<String> listfournisseurs = new ArrayList<String>();
	private List<Fournisseur> listfournisseur =new ArrayList<Fournisseur>();
	
	 
	@PostConstruct
	 public void init(){
		listMois.add("Janvier");
		listMois.add("Fevrier");
		listMois.add("Mars");
		listMois.add("Avril");	 
		listMois.add("Mai");
		listMois.add("Juin");
		listMois.add("Juillet");
		listMois.add("Aout");
		listMois.add("Septembre");
		listMois.add("Octobre");
		listMois.add("Novembre");
		listMois.add("Decembre");
		Date d=new Date();
		SimpleDateFormat f=new SimpleDateFormat("dd-MM-yyyy");
		year=f.format(d).substring(6);
		listAnnee.add(year);
		d.setYear(d.getYear()+1);
		year=f.format(d).substring(6);
		listAnnee.add(year);
		
	 }
	 
	
	   
	public void getdate(AjaxBehaviorEvent event){
		SimpleDateFormat f=new SimpleDateFormat("dd-MM-yyyy");
		year=f.format(date2).substring(6); 
		dates=f.format(date2);
		Integer m=date2.getMonth(); 		 
		switch(m){
		case 0:{date="Janvier"; break;}
		case 1:{date="Fevrier"; break;}
		case 2:{date="Mars"; break;}
		case 3:{date="Avril"; break;}
		case 4:{date="Mai"; break;}
		case 5:{date="Juin"; break;}
		case 6:{date="Juillet"; break;}
		case 7:{date="aout"; break;}
		case 8:{date="Septembre"; break;}
		case 9:{date="Octobre"; break;}
		case 10:{date="Novembre"; break;}
		case 11:{date="Decembre"; break;}
		}
		 
	}
	
	public String updateFournisseur(Fournisseur fournisseur) {
        try {
            getServiceFournisseur().update(fournisseur);
            return SUCCESS;       
        } catch (DataAccessException e) {
            e.printStackTrace();       
        }    
        return ERROR;
    } 
 
	public void onRowEdit(RowEditEvent event){
					 
			FacesMessage msg=new FacesMessage(FacesMessage.SEVERITY_INFO,"fournisseur change",((Fournisseur)event.getObject()).getNom());
		     FacesContext.getCurrentInstance().addMessage(null, msg);
		     updateFournisseur((Fournisseur)event.getObject());
		    
		 
	}
	
	
	 
	 
	
	 
	public void verifFournisseur(AjaxBehavior event){
		fournisseur=serviceFournisseur.getbyname(nom);
	}
	
	public String getAllfournisseur(){
		listfournisseur=new ArrayList<Fournisseur>();
		listfournisseur=serviceFournisseur.getAll();
		
		return SUCCESS;
	}
	public String savefournisseur(){
		fournisseur=serviceFournisseur.getbyname(nom);
		if(fournisseur==null){ 
		fournisseur=new Fournisseur();
		fournisseur.setNom(nom);
		fournisseur.setMatriculfisacl(matriculfisacl);	 
		fournisseur.setReste(reste);
		fournisseur.setTel(tel);
		fournisseur.setCa(ca);
		serviceFournisseur.save(fournisseur);
		listfournisseur=new ArrayList<Fournisseur>();
		listfournisseur=serviceFournisseur.getAll();
		nom=null; 
		prenom=null;tel=null;ca=0;reste=0;
		 
		return SUCCESS;
		}
		else {
			String message="Fournisseur existe";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(message));

			return ERROR; 
		 
		}
	}
	public String modifier(){
		return SUCCESS;
	}
	 
	 
	public String deletefournisseur(){
		fournisseur2.setStatut(Statut.DEACTIF); 
		 serviceFournisseur.delete(fournisseur2);
		 listfournisseur=new ArrayList<Fournisseur>();
		 listfournisseur=serviceFournisseur.getAll();
		return SUCCESS;
	}
	public ServiceFournisseur getServiceFournisseur() {
		return serviceFournisseur;
	}
	public void setServiceFournisseur(ServiceFournisseur serviceFournisseur) {
		this.serviceFournisseur = serviceFournisseur;
	}
	 
	 
	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}

 

 


	public Fournisseur getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}
	public Fournisseur getFournisseur2() {
		return fournisseur2;
	}
	public void setFournisseur2(Fournisseur fournisseur2) {
		this.fournisseur2 = fournisseur2;
	}
	public List<Fournisseur> getListfournisseur() {
		return listfournisseur;
	}
	public void setListfournisseur(List<Fournisseur> listFournisseur) {
		listfournisseur = listFournisseur;
	}
	 
	public double getMontantbrut() {
		return montantbrut;
	}
	public void setMontantbrut(double montantbrut) {
		this.montantbrut = montantbrut;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	 
	public double getRetenu() {
		return retenu;
	}
	public void setRetenu(double retenu) {
		this.retenu = retenu;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	 
	 
	 
	public List<String> getListfournisseurs() {
		return listfournisseurs;
	}
	public void setListfournisseurs(List<String> listfournisseurs) {
		this.listfournisseurs = listfournisseurs;
	}
	public Date getDate2() {
		return date2;
	}
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	 
	public double getHonoraire() {
		return honoraire;
	}
	public void setHonoraire(double honoraire) {
		this.honoraire = honoraire;
	}
	  
	 
 
	 
	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getTel() {
		return tel;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}



	public double getCa() {
		return ca;
	}



	public void setCa(double ca) {
		this.ca = ca;
	}



	public double getReste() {
		return reste;
	}



	public void setReste(double reste) {
		this.reste = reste;
	}



	public String getYear() {
		return year;
	}



	public void setYear(String year) {
		this.year = year;
	}



	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public String getEtab() {
		return etab;
	}
	public void setEtab(String etab) {
		this.etab = etab;
	}
	public String getMois() {
		return mois;
	}
	public void setMois(String mois) {
		this.mois = mois;
	}
	public List<String> getListMois() {
		return listMois;
	}
	public void setListMois(List<String> listMois) {
		this.listMois = listMois;
	}
	public List<String> getListAnnee() {
		return listAnnee;
	}
	public void setListAnnee(List<String> listAnnee) {
		this.listAnnee = listAnnee;
	}
	public double getMontantbrut2() {
		return montantbrut2;
	}
	public void setMontantbrut2(double montantbrut2) {
		this.montantbrut2 = montantbrut2;
	}
	
	
	public String getRestes() {
		DecimalFormat df=new DecimalFormat("0.000");
		restes=df.format(reste);
		return restes;
	}



	public void setRestes(String rstes) {
		this.restes = rstes;
	}



	public String getMontantbruts2() {
		DecimalFormat df=new DecimalFormat("0.000");
		montantbruts2=df.format(montantbrut2);
		 
		return montantbruts2;
	}
	public void setMontantbruts2(String montantbruts2) {
		this.montantbruts2 = montantbruts2;
	}
	 
	public List<Fournisseur> getFilterfournisseurs() {
		return filterfournisseurs;
	}
	public void setFilterfournisseurs(List<Fournisseur> filterfournisseurs) {
		this.filterfournisseurs = filterfournisseurs;
	}



	public String getMatriculfisacl() {
		return matriculfisacl;
	}



	public void setMatriculfisacl(String matriculfisacl) {
		this.matriculfisacl = matriculfisacl;
	}
	 
	
	 
	
	
}
