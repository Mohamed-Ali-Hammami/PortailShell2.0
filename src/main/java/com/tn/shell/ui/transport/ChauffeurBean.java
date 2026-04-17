package com.tn.shell.ui.transport;

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

import com.tn.shell.model.paie.*;
import com.tn.shell.model.transport.*;
import com.tn.shell.model.transport.Statut;
import com.tn.shell.service.transport.*;

 
 

@ManagedBean(name="ChauffeurBean")
@SessionScoped
public class ChauffeurBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";	
	 

	@ManagedProperty(value="#{ServiceChauffeur}") 
    ServiceChauffeur serviceChauffeur;
 
	 
	@ManagedProperty(value = "#{ServiceTracetransport}")
	ServiceTracetransport serviceTrace;
	private double montantjeton;
	private double montantretenuHor;
	private double honoraire;
	private double montantbrut;
	private double montantbrut2;
	private String montantbruts2;
	private List<Chauffeur> filterchauffeurs;
	private String mf;
	private String code;
	private String categorie;
	private String etab;
	private String mois;
	private List<String> listMois = new ArrayList<String>();
	private List<String> listAnnee = new ArrayList<String>();
	private String montantjetons;
	private String montantretenuHors;
	private String honoraires;
	private String montantbruts;
 	private Societe societe;
	private String matricule;
	private String libelle;
	private String adresse;
 
	private Date date2;
	private String total1;
	private String total2;
	private String total3;
	private double retenu;
		private double montant;
	private String date;
	private String dates;
	 
	private String year;
	 
	private String cin;
	private String num;
	private List<Chauffeur> listchauffeur;

	private String nom;
	private String prenom; 
	private Chauffeur chauffeur;
	private Chauffeur chauffeur2;

	 
	



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
	
	public String getchauffeur() {

		listchauffeur=new ArrayList<Chauffeur>();
		listchauffeur = serviceChauffeur.getAll();
		return SUCCESS;
		
	}
	
	public String nouvauchauffeur(){
		nom=null;
		prenom=null;
		
		
		return SUCCESS;
	}
		public String savechauffeur() {
			//client = serviceClient.Findbymf(matriculefiscal);
		//	if (client == null) {			 
				chauffeur = new Chauffeur();
				chauffeur.setNompenom(nom);
				chauffeur.setNumtel(num);
				serviceChauffeur.save(chauffeur);
				listchauffeur = new ArrayList<Chauffeur>();
				listchauffeur = serviceChauffeur.getAll();
								 
				return SUCCESS;
		//	} else {
				//String message = "client existe";
				//FacesContext.getCurrentInstance().addMessage(null,
					//	new FacesMessage(message));

				//return ERROR;

		//	}
		}
	
	public String updateChauffeur(Chauffeur chauffeur) {
        try {
            getServiceChauffeur().update(chauffeur);
            return SUCCESS;       
        } catch (DataAccessException e) {
        }    
        return ERROR;
    } 
 
	public void onRowEdit(RowEditEvent event){
					 
			FacesMessage msg=new FacesMessage(FacesMessage.SEVERITY_INFO,"chauffeur changÃ©",((Chauffeur)event.getObject()).getId()+"");
		     FacesContext.getCurrentInstance().addMessage(null, msg);
		     updateChauffeur((Chauffeur)event.getObject());
		    
		 
	}
	
	
	
	 
	
	public void getchauffeurbylibelle(AjaxBehaviorEvent event){
		 
		try{
		 
		  mf=matricule.substring(0, matricule.length()-7);		 
		  code=matricule.substring(matricule.length()-6, matricule.length()-5);		 
		  categorie=matricule.substring(matricule.length()-4, matricule.length()-3); 
		  etab=matricule.substring(matricule.length()-3, matricule.length());		 
		}catch(Exception e){
			matricule="";
		}
		
	}
	
	public void getchauffeurbylibelle2(AjaxBehaviorEvent event){
		 
		try{
		 
		  mf=matricule.substring(0, matricule.length()-7);		 
		  code=matricule.substring(matricule.length()-6, matricule.length()-5);		 
		  categorie=matricule.substring(matricule.length()-4, matricule.length()-3); 
		  etab=matricule.substring(matricule.length()-3, matricule.length());		 
		}catch(Exception e){
		}
	}
	 
	
	public String modifier(){
		return SUCCESS;
	}
	 
	 
	public String deletechauffeur() {
		chauffeur2.setStatut(Statut.DEACTIF);
		
		  
		serviceChauffeur.delete(chauffeur2);
		listchauffeur = new ArrayList<Chauffeur>();
		listchauffeur = serviceChauffeur.getAll();
		return SUCCESS;
	}
	
	
	public ServiceChauffeur getServiceChauffeur() {
		return serviceChauffeur;
	}
	public void setServiceChauffeur(ServiceChauffeur serviceChauffeur) {
		this.serviceChauffeur = serviceChauffeur;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	 
	 
 
	public String getTotal1() {
		return total1;
	}



	public void setTotal1(String total1) {
		this.total1 = total1;
	}



	public String getTotal2() {
		return total2;
	}



	public void setTotal2(String total2) {
		this.total2 = total2;
	}



	public String getTotal3() {
		return total3;
	}



	public Societe getSociete() {
		return societe;
	}
	public void setSociete(Societe societe) {
		this.societe = societe;
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
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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
	 
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	 
	public Date getDate2() {
		return date2;
	}
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	public double getMontantjeton() {
		return montantjeton;
	}
	public void setMontantjeton(double montantjeton) {
		this.montantjeton = montantjeton;
	}
	public double getMontantretenuHor() {
		return montantretenuHor;
	}
	public void setMontantretenuHor(double montantretenuHor) {
		this.montantretenuHor = montantretenuHor;
	}
	public double getHonoraire() {
		return honoraire;
	}
	public void setHonoraire(double honoraire) {
		this.honoraire = honoraire;
	}
	 
 
	 
	public void setTotal3(String total3) {
		this.total3 = total3;
	}
	public String getMontantjetons() {
		DecimalFormat df=new DecimalFormat("0.000");
		montantjetons=df.format(montantjeton);
		return montantjetons;
	}
	public void setMontantjetons(String montantjetons) {
		this.montantjetons = montantjetons;
	}
	public String getMontantretenuHors() {
		DecimalFormat df=new DecimalFormat("0.000");
		montantretenuHors=df.format(montantretenuHor);
		return montantretenuHors;
	}
	public void setMontantretenuHors(String montantretenuHors) {
		this.montantretenuHors = montantretenuHors;
	}
	public String getHonoraires() {
		DecimalFormat df=new DecimalFormat("0.000");
		honoraires=df.format(honoraire);
		return honoraires;
	}
	
	public void setHonoraires(String honoraires) {
		this.honoraires = honoraires;
	}
	public String getMontantbruts() {
		DecimalFormat df=new DecimalFormat("0.000");
		montantbruts=df.format(montantbrut);
		return montantbruts;
	}
	public void setMontantbruts(String montantbruts) {
		this.montantbruts = montantbruts;
	}
	 
	public ServiceTracetransport getServiceTrace() {
		return serviceTrace;
	}
	public void setServiceTrace(ServiceTracetransport serviceTrace) {
		this.serviceTrace = serviceTrace;
	}
	public String getMf() {
		return mf;
	}
	public void setMf(String mf) {
		this.mf = mf;
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
	public String getMontantbruts2() {
		DecimalFormat df=new DecimalFormat("0.000");
		montantbruts2=df.format(montantbrut2);
		 
		return montantbruts2;
	}
	public void setMontantbruts2(String montantbruts2) {
		this.montantbruts2 = montantbruts2;
	}
	 
	public List<Chauffeur> getFilterchauffeurs() {
		return filterchauffeurs;
	}
	public void setFilterchauffeurs(List<Chauffeur> filterchauffeurs) {
		this.filterchauffeurs = filterchauffeurs;
	}
	 
	public Chauffeur getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(Chauffeur chauffeur) {
		this.chauffeur = chauffeur;
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



	public Chauffeur getChauffeur2() {
		return chauffeur2;
	}



	public void setChauffeur2(Chauffeur chauffeur2) {
		this.chauffeur2 = chauffeur2;
	}



	public List<Chauffeur> getListchauffeur() {
		return listchauffeur;
	}



	public void setListchauffeur(List<Chauffeur> listchauffeur) {
		this.listchauffeur = listchauffeur;
	}	
	
	
	
}
