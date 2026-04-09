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

import com.tn.shell.model.gestat.Certificat;
import com.tn.shell.model.paie.Annee;
import com.tn.shell.model.shop.Fournisseur;
import com.tn.shell.service.gestat.UserService;
import com.tn.shell.service.paie.ServiceAnnee;
import com.tn.shell.service.shop.ServiceFournisseur;
 

 

 

@ManagedBean(name="CerteficatBean")
@SessionScoped
public class CerteficatBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";	
	 

	@ManagedProperty(value="#{ServiceFournisseur}") 
    ServiceFournisseur serviceFournisseur;
	 
	@ManagedProperty(value = "#{UserService}")
	UserService userService;	
	@ManagedProperty(value = "#{ServiceAnnee}")
	ServiceAnnee serviceAnnee;
	private double montantjeton;
	private double montantretenuHor;
	private double honoraire;
	private double montantbrut;
	private double montantbrut2;
	private String montantbruts2;
	private List<Fournisseur> filterfournisseurs;
	private String mf;
	private String code;
	private String categorie;
	private String etab;
	private String mois;
	private List<String> listMois = new ArrayList<String>();
	private List<String> listAnnee  ;
	private String montantjetons;
	private String montantretenuHors;
	private String honoraires;
	private String montantbruts;
  
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
	private Certificat certif1;
	private Certificat certif2;
	private Certificat certif3;
	private Certificat certif4;
	private Certificat certif5;
	private String year;
	private Fournisseur fournisseur;
	private Fournisseur fournisseur2;
	private String cin;
	private String num;
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
	 
		d.setYear(d.getYear()+1);
		year=f.format(d).substring(6);
		 
		
	 }
	public void certificat1(AjaxBehaviorEvent event){
		 
		certif1.setDate(new Date());
		certif1.setMontantbrut(honoraire);
		certif1.setRetenue((double) 15*honoraire/100);
		certif1.setMontant(honoraire-certif1.getRetenue());
		honoraire=0;
	}
	
	public void certificat2(AjaxBehaviorEvent event){
		
		  
		certif2.setDate(new Date());
		certif2.setMontantbrut(montantjeton);
		certif2.setRetenue((double) 20*montantjeton/100);
		certif2.setMontant(montantjeton-certif2.getRetenue());
		montantjeton=0;
	}
	
	public void certificat3(AjaxBehaviorEvent event){
		 
		certif3.setDate(new Date());
		certif3.setMontantbrut(montantretenuHor);
		certif3.setRetenue((double) 5*montantretenuHor/100);
		certif3.setMontant(montantretenuHor-certif3.getRetenue());
		montantretenuHor=0;
	}
	
	public void certificat4(AjaxBehaviorEvent event){
		 
		certif4.setDate(new Date());
		certif4.setMontantbrut(montantbrut);
		certif4.setRetenue((double) 1*montantbrut/100);
		certif4.setMontant(montantbrut-certif4.getRetenue());
		DecimalFormat df=new DecimalFormat("#,###.000");
		 
	}
	
	public void certificat5(AjaxBehaviorEvent event){
		 
		certif5.setDate(new Date());
		certif5.setMontantbrut(montantbrut2);
		certif5.setRetenue((double) 2.5*montantbrut2/100);
		certif5.setMontant(montantbrut2-certif5.getRetenue());
		DecimalFormat df=new DecimalFormat("#,###.000");
		 
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
 
	 
	
	
	
	public String certif(){
		total1=null; total2=null; total2=null;
		Date date=new Date();
		listAnnee = new ArrayList<String>();
		List<Annee> la=new ArrayList<Annee>();
				la=serviceAnnee.getAll(); 
		for(Annee a:la)listAnnee.add(a.getAnnee());
		SimpleDateFormat f=new SimpleDateFormat("dd-MM-yyyy");
		dates=f.format(date);
		List<Fournisseur> l2 = new ArrayList<Fournisseur>();
		listfournisseurs=new ArrayList<String>();
		l2=serviceFournisseur.getAll();
		if(l2!=null){
		for (Fournisseur four : l2) {
			listfournisseurs.add(four.getNom());
		}
		}
		 certif1=new Certificat();
		 certif2=new Certificat();
		 certif3=new Certificat();
		 certif4=new Certificat();
		 certif5=new Certificat();
		 
		return SUCCESS;
	}
	
	public void getfournisseurbylibelle(AjaxBehaviorEvent event){
		fournisseur=serviceFournisseur.getbyname(libelle);
		matricule=fournisseur.getMatriculfisacl();
		try{
		 
		  mf=matricule.substring(0, matricule.length()-7);		 
		  code=matricule.substring(matricule.length()-6, matricule.length()-5);		 
		  categorie=matricule.substring(matricule.length()-4, matricule.length()-3); 
		  etab=matricule.substring(matricule.length()-3, matricule.length());		 
		}catch(Exception e){
			matricule="";
			System.out.println("");
		}
		
	}
	
	public void getfournisseurbylibelle2(AjaxBehaviorEvent event){
		 
		try{
		 
		  mf=matricule.substring(0, matricule.length()-7);		 
		  code=matricule.substring(matricule.length()-6, matricule.length()-5);		 
		  categorie=matricule.substring(matricule.length()-4, matricule.length()-3); 
		  etab=matricule.substring(matricule.length()-3, matricule.length());		 
		}catch(Exception e){
			System.out.println("");
		}
	}
	public void verifFournisseur(AjaxBehavior event){
		fournisseur=serviceFournisseur.getbyname(libelle);
	}
	
	public String getAllfournisseur(){
		listfournisseur=serviceFournisseur.getAll();
		return SUCCESS;
	}
 
	public String modifier(){
		return SUCCESS;
	}
 
	public ServiceFournisseur getServiceFournisseur() {
		return serviceFournisseur;
	}
	public void setServiceFournisseur(ServiceFournisseur serviceFournisseur) {
		this.serviceFournisseur = serviceFournisseur;
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
	 
	public Certificat getCertif1() {
		return certif1;
	}
	public void setCertif1(Certificat certif1) {
		this.certif1 = certif1;
	}
	public Certificat getCertif2() {
		return certif2;
	}
	public void setCertif2(Certificat certif2) {
		this.certif2 = certif2;
	}
	public Certificat getCertif3() {
		return certif3;
	}
	public void setCertif3(Certificat certif3) {
		this.certif3 = certif3;
	}
	public Certificat getCertif4() {
		return certif4;
	}
	public void setCertif4(Certificat certif4) {
		this.certif4 = certif4;
	}
	public String getTotal1() {
		DecimalFormat df=new DecimalFormat("#,###.000");		 
		total1=df.format(certif1.getMontantbrut()+certif2.getMontantbrut()+certif3.getMontantbrut()+certif4.getMontantbrut()+certif5.getMontantbrut());
		return total1;
	}
	public void setTotal1(String total1) {
		this.total1 = total1;
	}
	public String getTotal2() {
		DecimalFormat df=new DecimalFormat("#,###.000");	
		total2=df.format(certif1.getRetenue()+certif2.getRetenue()+certif3.getRetenue()+certif4.getRetenue()+certif5.getRetenue());
		return total2;
	}
	public void setTotal2(String total2) {
		this.total2 = total2;
	}
	public String getTotal3() {
		DecimalFormat df=new DecimalFormat("#,###.000");	
		total3=df.format(certif1.getMontant()+certif2.getMontant()+certif3.getMontant()+certif4.getMontant()+certif5.getMontant());
		return total3;
	}
	public void setTotal3(String total3) {
		this.total3 = total3;
	}
	public String getMontantjetons() {
		DecimalFormat df=new DecimalFormat("#,###.000");
		montantjetons=df.format(montantjeton);
		return montantjetons;
	}
	public void setMontantjetons(String montantjetons) {
		this.montantjetons = montantjetons;
	}
	public String getMontantretenuHors() {
		DecimalFormat df=new DecimalFormat("#,###.000");
		montantretenuHors=df.format(montantretenuHor);
		return montantretenuHors;
	}
	public void setMontantretenuHors(String montantretenuHors) {
		this.montantretenuHors = montantretenuHors;
	}
	public String getHonoraires() {
		DecimalFormat df=new DecimalFormat("#,###.000");
		honoraires=df.format(honoraire);
		return honoraires;
	}
	
	public void setHonoraires(String honoraires) {
		this.honoraires = honoraires;
	}
	public String getMontantbruts() {
		DecimalFormat df=new DecimalFormat("#,###.000");
		montantbruts=df.format(montantbrut);
		return montantbruts;
	}
	public void setMontantbruts(String montantbruts) {
		this.montantbruts = montantbruts;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
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
		DecimalFormat df=new DecimalFormat("#,###.000");
		montantbruts2=df.format(montantbrut2);
		 
		return montantbruts2;
	}
	public void setMontantbruts2(String montantbruts2) {
		this.montantbruts2 = montantbruts2;
	}
	public Certificat getCertif5() {
		return certif5;
	}
	public void setCertif5(Certificat certif5) {
		this.certif5 = certif5;
	}
	public List<Fournisseur> getFilterfournisseurs() {
		return filterfournisseurs;
	}
	public void setFilterfournisseurs(List<Fournisseur> filterfournisseurs) {
		this.filterfournisseurs = filterfournisseurs;
	}
	public ServiceAnnee getServiceAnnee() {
		return serviceAnnee;
	}
	public void setServiceAnnee(ServiceAnnee serviceAnnee) {
		this.serviceAnnee = serviceAnnee;
	}
	  
}
