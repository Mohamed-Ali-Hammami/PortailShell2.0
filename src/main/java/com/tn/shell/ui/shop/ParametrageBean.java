package com.tn.shell.ui.shop;

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

import com.tn.shell.model.gestat.*;
 
import com.tn.shell.model.shop.*;
import com.tn.shell.model.gestat.EProfil;
import com.tn.shell.service.gestat.ServiceRole;
import com.tn.shell.service.gestat.UserService;
import com.tn.shell.service.shop.*;
 

 

@ManagedBean(name = "ParametrageBean")
@SessionScoped
public class ParametrageBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	 
	@ManagedProperty(value = "#{UserService}")
	UserService userService;	 
	@ManagedProperty(value = "#{ServiceTva}")
	ServiceTva  serviceTva;
	@ManagedProperty(value = "#{ServiceParamettre}")
	ServiceParamettre serviceParamettre;
	@ManagedProperty(value = "#{ServiceRole}")
	ServiceRole serviceRole;
	private List<Utilisateur> listusers;
	private String logine;
	private String nom;
	private String prenom;	 
	private String motdepass; 
	private Utilisateur user;
	private Paramettre paramettre;
	private Tva tva;
	private List<Tva> listtvas;
	private List<Paramettre> listparamettres;
	 
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
		 listtvas=new ArrayList<Tva>();
		 
		 listtvas=serviceTva.getAll();
		 listparamettres=new ArrayList<Paramettre>();
		 listparamettres=serviceParamettre.getAll();
		 listusers=new ArrayList<Utilisateur>();
		 listusers=userService.getUsers();
		 
		 return SUCCESS;
	 }
	  
	 
	  
	public String saveuser(){
		user=userService.getUser(logine);
		if(user ==null){
			user=new Utilisateur();
			user.setLogin(logine);
			user.setMotdepasse(motdepass);
			user.setNom(nom);			 
			user.setRole(serviceRole.getRolebyprifil(role));
			userService.addUser(user);
		}
		else{
			String message="client existe";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(message));
		}
		listusers=new ArrayList<Utilisateur>();
		listusers=userService.getUsers();
		return SUCCESS;
	}
	public String deleteuser(){
		userService.deleteUser(user);
		listusers=new ArrayList<Utilisateur>();
		listusers=userService.getUsers();
		return SUCCESS;
	}
	public String getAll(){
		listusers=new ArrayList<Utilisateur>();
		listusers=userService.getUsers();
		return SUCCESS;
	}
	public String modifierfodeq(){
		if(paramettre!=null){
		 
		serviceParamettre.update(paramettre);
		}
		listparamettres=new ArrayList<Paramettre>();
		 listparamettres=serviceParamettre.getAll();
		return SUCCESS;
	}
	
	public String modifierTimbre(){
		if(paramettre!=null){
		paramettre.setTimbre(timbre); 
		serviceParamettre.update(paramettre);
		}
		listparamettres=new ArrayList<Paramettre>();
		 listparamettres=serviceParamettre.getAll();
		return SUCCESS;
	}
	 
	
	public String modifiertva(){
		if(tva!=null){
		tva.setValeur(valeur);
		serviceTva.update(tva);
		}
		 listtvas=new ArrayList<Tva>();
		 listtvas=serviceTva.getAll();		
		return SUCCESS;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	 
	public ServiceTva getServiceTva() {
		return serviceTva;
	}
	public void setServiceTva(ServiceTva serviceTva) {
		this.serviceTva = serviceTva;
	}
	public ServiceParamettre getServiceParamettre() {
		return serviceParamettre;
	}
	public void setServiceParamettre(ServiceParamettre serviceParamettre) {
		this.serviceParamettre = serviceParamettre;
	}
	public List<Utilisateur> getListusers() {
		return listusers;
	}
	public void setListusers(List<Utilisateur> listusers) {
		this.listusers = listusers;
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
	
	public Utilisateur getUser() {
		return user;
	}
	public void setUser(Utilisateur user) {
		this.user = user;
	}
	public Paramettre getParamettre() {
		return paramettre;
	}
	public void setParamettre(Paramettre paramettre) {
		this.paramettre = paramettre;
	}
	public Tva getTva() {
		return tva;
	}
	public void setTva(Tva tva) {
		this.tva = tva;
	}
	public List<Tva> getListtvas() {
		return listtvas;
	}
	public void setListtvas(List<Tva> listtvas) {
		this.listtvas = listtvas;
	}
	public List<Paramettre> getListparamettres() {
		return listparamettres;
	}
	public void setListparamettres(List<Paramettre> listparamettres) {
		this.listparamettres = listparamettres;
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
	
	
	public void onRowEdit1(RowEditEvent event){				 
		FacesMessage msg=new FacesMessage(FacesMessage.SEVERITY_INFO,"utilisateur changé",((Utilisateur)event.getObject()).getLogin());
	     FacesContext.getCurrentInstance().addMessage(null, msg);
	     System.out.println("\n\n\n" +((Utilisateur)event.getObject()).getLogin()+"n\n\n");
	     ((Utilisateur)event.getObject()).setRole(serviceRole.getRolebyprifil(role));
	     updateUtilisateur((Utilisateur)event.getObject()); 
}


public String updateUtilisateur(Utilisateur u) {
    try {
    	Utilisateur u2=userService.getUser(u.getLogin());
    	if(u2==null){
        userService.updateUser(u);
        return SUCCESS;     }  
    } catch (DataAccessException e) {
        e.printStackTrace();       
    }    
    return ERROR;
} 

public void onRowEdit2(RowEditEvent event){				 
	FacesMessage msg=new FacesMessage(FacesMessage.SEVERITY_INFO,"utilisateur changé",((Paramettre)event.getObject()).getId()+"");
     FacesContext.getCurrentInstance().addMessage(null, msg);
     updateParamettre((Paramettre)event.getObject());
 
}


public String  updateParamettre(Paramettre p) {
try {
    getServiceParamettre().update(p) ;
    return SUCCESS;       
} catch (DataAccessException e) {
    e.printStackTrace();       
}    
return ERROR;
} 
	
 
public void onRowEdit4(RowEditEvent event){				 
	FacesMessage msg=new FacesMessage(FacesMessage.SEVERITY_INFO,"utilisateur changé",((Tva)event.getObject()).getId()+"");
     FacesContext.getCurrentInstance().addMessage(null, msg);
     updateTva((Tva)event.getObject());
 
}


public String  updateTva(Tva p) {
try {
    getServiceTva().update(p) ;
    return SUCCESS;       
} catch (DataAccessException e) {
    e.printStackTrace();       
}    
return ERROR;
}
public ServiceRole getServiceRole() {
	return serviceRole;
}
public void setServiceRole(ServiceRole serviceRole) {
	this.serviceRole = serviceRole;
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
