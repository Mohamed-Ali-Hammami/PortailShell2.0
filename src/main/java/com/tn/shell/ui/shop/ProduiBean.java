package com.tn.shell.ui.shop;

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
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
 

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.dao.DataAccessException;

 
import com.tn.shell.model.shop.*;
import com.tn.shell.service.shop.*;

@ManagedBean(name = "ProduiBean")
@SessionScoped
public class ProduiBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{ServiceProduit}")
	ServiceProduit serviceProduit;

	@ManagedProperty(value = "#{ServiceFournisseur}")
	ServiceFournisseur serviceFournisseur;

	@ManagedProperty(value = "#{ServiceFamilleaticle}")
	ServiceFamilleaticle serviceFamilleaticle;

	@ManagedProperty(value = "#{Servicevente}")
	ServiceVente servicevente;

	@ManagedProperty(value = "#{ServiceParamettre}")
	ServiceParamettre serviceParamettre;

	@ManagedProperty(value = "#{ServiceTva}")
	ServiceTva serviceTva;

	@ManagedProperty(value = "#{ServiceLigneAlimentation}")
	ServiceLigneAlimentation serviceLigneAlimentation;

	@ManagedProperty(value = "#{ServiceLignevente}")
	ServiceLignevente serviceLignevente;

	@ManagedProperty(value = "#{serviceticket}")
	ServiceTicket serviceticket;
	
	@ManagedProperty(value = "#{ServiceLignetransfert}")
	ServiceLignetransfert serviceLignetransfert;
	
	
	private Produit produit;
	private String  code;
	private String nom;
	private Integer quantite;	 
	private double achat;
	private Integer tva;
	private double marge;
	private double vente;
	private Integer qtemin;
	private List<String> listBanque;
	private List<String> listMois;
	private Date date;
	private TypePayement[] typepayements ;  
	private Famillearticle famille;
	private String codefamille;
	private List<Tva> listtva;
	private List<Famillearticle> listfamile;  
	private List<Produit >listProduit;
	  
	@PostConstruct
	public void init() {
		date = new Date();
		listBanque=new ArrayList<String>();
		listBanque.add("AMEN BANQUE");
		listBanque.add("ATB");
		listBanque.add("ATTIJARI BANQUE");
		listBanque.add("BH");
		listBanque.add("BIAT"); 
		listBanque.add("BFI");
		listBanque.add("BNA");
		listBanque.add("BT");
		listBanque.add("BTE");
		listBanque.add("BTK");
		listBanque.add("BTL");
		listBanque.add("BTS");
		listBanque.add("STB");
		listBanque.add("STUCID");		
		listBanque.add("UBCI");
		listBanque.add("UIB");
		listBanque.add("ZITOUNA BANQUE");
		typepayements = TypePayement.values();
		 
		
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
	}
	
	 public String majarticle() {
		  /* listtva=new ArrayList<Tva>();
	   	   listtva=serviceTva.getAll();
	   	   listfamile=new ArrayList<Famillearticle>();
	   	   listfamile=serviceFamilleaticle.getAll ();*/
	   	   return SUCCESS;
	   	   
	      }


	 public String savearticle() {
	   	   famille=serviceFamilleaticle.findbyDesignation(codefamille);
	   	   produit=new Produit();
	   	   produit.setNom(nom);
	   	   produit.setMarge(marge);
	   	   produit.setQtemin(qtemin);
	   	   produit.setAchat(achat);
	   	   produit.setCode(code);
	   	   produit.setFamille(famille);
	   	   produit.setTva(tva);
	   	   produit.setVente(vente);
	   	   serviceProduit.save(produit);
	   	    
	   	   return SUCCESS;
	      }
	 public void onRowEdit2(RowEditEvent event) {

			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "fournisseur changÃ©",
					((Produit) event.getObject()).getNom());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			updateProduit((Produit) event.getObject());

		}
	     
	 public String getAllarticle() {
		 listProduit = new ArrayList<Produit>();
		listProduit = serviceProduit.getAll(); 
		return SUCCESS;
	}

	      public void onRowEdit(RowEditEvent event){			 
				FacesMessage msg=new FacesMessage(FacesMessage.SEVERITY_INFO,"produit changÃ©",((Produit)event.getObject()).getNom());
			     FacesContext.getCurrentInstance().addMessage(null, msg);
			     updateProduit((Produit)event.getObject());
			    
		}
	      public String updateProduit(Produit  produit) {
	          try {
	              getServiceProduit().update(produit);
	              return SUCCESS;       
	          } catch (DataAccessException e) {
	          }    
	          return ERROR;
	      } 
		
		

	public ServiceProduit getServiceProduit() {
		return serviceProduit;
	}



	public void setServiceProduit(ServiceProduit serviceProduit) {
		this.serviceProduit = serviceProduit;
	}



	public ServiceFournisseur getServiceFournisseur() {
		return serviceFournisseur;
	}



	public void setServiceFournisseur(ServiceFournisseur serviceFournisseur) {
		this.serviceFournisseur = serviceFournisseur;
	}



	public ServiceFamilleaticle getServiceFamilleaticle() {
		return serviceFamilleaticle;
	}



	public void setServiceFamilleaticle(ServiceFamilleaticle serviceFamilleaticle) {
		this.serviceFamilleaticle = serviceFamilleaticle;
	}



	public ServiceVente getServicevente() {
		return servicevente;
	}



	public void setServicevente(ServiceVente servicevente) {
		this.servicevente = servicevente;
	}



	public ServiceParamettre getServiceParamettre() {
		return serviceParamettre;
	}



	public void setServiceParamettre(ServiceParamettre serviceParamettre) {
		this.serviceParamettre = serviceParamettre;
	}



	public ServiceTva getServiceTva() {
		return serviceTva;
	}



	public void setServiceTva(ServiceTva serviceTva) {
		this.serviceTva = serviceTva;
	}



	public ServiceLigneAlimentation getServiceLigneAlimentation() {
		return serviceLigneAlimentation;
	}



	public void setServiceLigneAlimentation(ServiceLigneAlimentation serviceLigneAlimentation) {
		this.serviceLigneAlimentation = serviceLigneAlimentation;
	}



	public ServiceLignevente getServiceLignevente() {
		return serviceLignevente;
	}



	public void setServiceLignevente(ServiceLignevente serviceLignevente) {
		this.serviceLignevente = serviceLignevente;
	}



	public ServiceTicket getServiceticket() {
		return serviceticket;
	}



	public void setServiceticket(ServiceTicket serviceticket) {
		this.serviceticket = serviceticket;
	}



	public ServiceLignetransfert getServiceLignetransfert() {
		return serviceLignetransfert;
	}



	public void setServiceLignetransfert(ServiceLignetransfert serviceLignetransfert) {
		this.serviceLignetransfert = serviceLignetransfert;
	}



	public Produit getProduit() {
		return produit;
	}



	public void setProduit(Produit produit) {
		this.produit = produit;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public Integer getQuantite() {
		return quantite;
	}



	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}



	public double getAchat() {
		return achat;
	}



	public void setAchat(double achat) {
		this.achat = achat;
	}



	public Integer getTva() {
		return tva;
	}



	public void setTva(Integer tva) {
		this.tva = tva;
	}



	public double getMarge() {
		return marge;
	}



	public void setMarge(double marge) {
		this.marge = marge;
	}



	public double getVente() {
		return vente;
	}



	public void setVente(double vente) {
		this.vente = vente;
	}



	public Integer getQtemin() {
		return qtemin;
	}



	public void setQtemin(Integer qtemin) {
		this.qtemin = qtemin;
	}



	public List<String> getListBanque() {
		return listBanque;
	}



	public void setListBanque(List<String> listBanque) {
		this.listBanque = listBanque;
	}



	public List<String> getListMois() {
		return listMois;
	}



	public void setListMois(List<String> listMois) {
		this.listMois = listMois;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public TypePayement[] getTypepayements() {
		return typepayements;
	}



	public void setTypepayements(TypePayement[] typepayements) {
		this.typepayements = typepayements;
	}



	public Famillearticle getFamille() {
		return famille;
	}



	public void setFamille(Famillearticle famille) {
		this.famille = famille;
	}



	public String getCodefamille() {
		return codefamille;
	}



	public void setCodefamille(String codefamille) {
		this.codefamille = codefamille;
	}



	public List<Tva> getListtva() {
		return listtva;
	}



	public void setListtva(List<Tva> listtva) {
		this.listtva = listtva;
	}



	public List<Famillearticle> getListfamile() {
		return listfamile;
	}



	public void setListfamile(List<Famillearticle> listfamile) {
		this.listfamile = listfamile;
	}

	public List<Produit> getListProduit() {
		return listProduit;
	}

	public void setListProduit(List<Produit> listProduit) {
		this.listProduit = listProduit;
	}
	 
	  
}
