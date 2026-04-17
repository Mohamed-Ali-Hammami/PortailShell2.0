package com.tn.shell.ui.paie;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.springframework.dao.DataAccessException;

import com.tn.shell.model.paie.*;
import com.tn.shell.service.paie.*;
@ManagedBean(name = "ParametrageBean")
@SessionScoped
public class ParametrageBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
 
	@ManagedProperty(value = "#{ServiceSociete}")
	ServiceSociete serviceSociete;
	  	private String logine;
	private String nom;
	private String prenom;	 
	private String motdepass;   
	 
	private List<Societe> listsocietes;
	private EProfil role;
	private  EProfil[] listroles;
	private String libelle;
	private String matricuefiscale;
	private String adresse;
	private String rue;
	private String numero;
	private String tel1;
	private String tel2;
	private String codetva;
	private String banque;
	private long numcompte;
	private Societe selectedsociete;
	private Integer valeur;
	private Integer fodeq;
	private float timbre;
	 @PostConstruct
	 public void init(){
		 listroles=EProfil.values();
		 
	 }
	 public String listparamettre(){
		 logine=null;
		 motdepass=null;
		 
		 listsocietes=new ArrayList<Societe>();	
		 
		  
		 listsocietes=serviceSociete.getAll();
		 return SUCCESS;
	 }
	 public String savesociete(){
		 selectedsociete=new Societe();
		 Adresse a=new Adresse();
		 a.setNum(numero);
			a.setRue(rue);
			a.setVille(adresse);
			 selectedsociete.setAddress(a);
		 selectedsociete.setBanque(banque);
		 selectedsociete.setCodetva(codetva);
		 selectedsociete.setLibelle(libelle);
		 selectedsociete.setMatricuefiscale(matricuefiscale);
		 selectedsociete.setTel1(tel1);
		 selectedsociete.setTel1(tel1);
		 selectedsociete.setNumcompte(numcompte);
		 serviceSociete.save(selectedsociete);
		 listsocietes=new ArrayList<Societe>();
		 listsocietes=serviceSociete.getAll();
		 
		 return SUCCESS;
	 }
	 
	 
	public ServiceSociete getServiceSociete() {
		return serviceSociete;
	}
	public void setServiceSociete(ServiceSociete serviceSociete) {
		this.serviceSociete = serviceSociete;
	}
	 
	 
	 
	public String getLogine() {
		return logine;
	}
	public void setLogine(String login) {
		this.logine = login;
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
	public String getMotdepass() {
		return motdepass;
	}
	public void setMotdepass(String motdepasse) {
		this.motdepass = motdepasse;
	}
	
	 
	public List<Societe> getListsocietes() {
		return listsocietes;
	}
	public void setListsocietes(List<Societe> listsocietes) {
		this.listsocietes = listsocietes;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getMatricuefiscale() {
		return matricuefiscale;
	}
	public void setMatricuefiscale(String matricuefiscale) {
		this.matricuefiscale = matricuefiscale;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getTel2() {
		return tel2;
	}
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	public String getCodetva() {
		return codetva;
	}
	public void setCodetva(String codetva) {
		this.codetva = codetva;
	}
	public String getBanque() {
		return banque;
	}
	public void setBanque(String banque) {
		this.banque = banque;
	}
	public long getNumcompte() {
		return numcompte;
	}
	public void setNumcompte(long numcompte) {
		this.numcompte = numcompte;
	}
	 
	public Societe getSelectedsociete() {
		return selectedsociete;
	}
	public void setSelectedsociete(Societe selectedsociete) {
		this.selectedsociete = selectedsociete;
	}
	public Integer getFodeq() {
		return fodeq;
	}
	public void setFodeq(Integer fodeq) {
		this.fodeq = fodeq;
	}
	public float getTimbre() {
		return timbre;
	}
	public void setTimbre(float timbre) {
		this.timbre = timbre;
	}
	public Integer getValeur() {
		return valeur;
	}
	public void setValeur(Integer valeur) {
		this.valeur = valeur;
	}
	
	
	 
 
 

 
	

public void onRowEdit3(RowEditEvent event){				 
	FacesMessage msg=new FacesMessage(FacesMessage.SEVERITY_INFO,"societe change",((Societe)event.getObject()).getId()+"");
     FacesContext.getCurrentInstance().addMessage(null, msg);
     updateSociete((Societe)event.getObject());
 
}


public String  updateSociete(Societe p) {
try {
    getServiceSociete().update(p) ;
    return SUCCESS;       
} catch (DataAccessException e) {
}    
return ERROR;
} 
 

 
public EProfil getRole() {
	return role;
}
public void setRole(EProfil role) {
	this.role = role;
}
public EProfil[] getListroles() {
	return listroles;
}
public void setListroles(EProfil[] listroles) {
	this.listroles = listroles;
}
public String getRue() {
	return rue;
}
public void setRue(String rue) {
	this.rue = rue;
}
public String getNumero() {
	return numero;
}
public void setNumero(String numero) {
	this.numero = numero;
} 
	
	
}
