package com.tn.shell.ui.transport;

import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
 

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.Transient;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.dao.DataAccessException;

import com.tn.shell.model.shop.Famillearticle;
import com.tn.shell.model.shop.Produit;
import com.tn.shell.model.shop.Tva;
 
import com.tn.shell.service.*;
import com.tn.shell.service.shop.*;
import com.tn.shell.service.transport.*;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;

import java.io.File;
import java.io.IOException;

@ManagedBean(name = "ProduitBean")
@SessionScoped
public class ProduitBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	 
	@ManagedProperty(value = "#{ServiceProduit}")
	ServiceProduit serviceProduit;
	@ManagedProperty(value = "#{ServiceTva}")
	ServiceTva serviceTva;
 
	
	@ManagedProperty(value = "#{ServiceFamilleaticle}")
	ServiceFamilleaticle serviceFamilleaticle;
	private Famillearticle famille;
	private Produit produit;
	private Produit produits;
	private double prix_vente;
	private List<Tva> listtvas;
 
	private List<Produit> listprosuit = new ArrayList<Produit>();
	private List<Produit> listprosuits = new ArrayList<Produit>();
	private List<Produit> selectedProduits = new ArrayList<Produit>();
 
	private List<Produit> filtredProduit;

	private String date;
	private Date dat = null;

	private Integer qte;
	private String designation;
	private Integer tva;
	private String codess;
	private String nom;
	private double achat;
	private double prixvente;
	private List<Integer> listtva;
	private double marge;
	private double ventes;
	private Integer qtemin;
	private String codefamille;
	private List<Famillearticle>listfamile;

	private String dates = "";

 

	@PostConstruct
	public void init() {
		dat = new Date();

	}
	public String supprimerlist() {
		for(Produit p:selectedProduits)
			//serviceProduit.delete(p);
		
		listprosuits = new ArrayList<Produit>();
		listprosuits = serviceProduit.getAll();
		selectedProduits=null;
		return SUCCESS;
	}
	public String nouveauProduit() {
		listtva = new ArrayList<Integer>();
		listtvas = new ArrayList<Tva>();
		listtvas=serviceTva.getAll();
		for(Tva t:listtvas)
		listtva.add(t.getValeur());
		 
		listfamile = new ArrayList<Famillearticle>();
		listfamile = serviceFamilleaticle.getAll();
		designation = null;
		tva = 0;
		prix_vente = 0.000;
		return SUCCESS;
	}

	public String getallProduit() {
		listprosuits = new ArrayList<Produit>();
		listprosuits = serviceProduit.getAll();
		 
		return SUCCESS;
	}

	public String ajouterProduit() {

		produit = new Produit();
		famille = serviceFamilleaticle.findbyDesignation(codefamille);
		 
		produit.setNom(nom);
		produit.setMarge(marge);
		produit.setQtemin(qtemin);
		produit.setAchat(achat);
		produit.setCode(codess);
		produit.setFamille(famille);
		produit.setTva(tva);
		produit.setVente(ventes);
		serviceProduit.save(produit);

		listprosuit = serviceProduit.getAll();
		listprosuit = serviceProduit.getAll();

		return SUCCESS;
	}

	 

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Depense changÃ©", ((Produit) event.getObject()).getNom());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		updateproduit((Produit) event.getObject());

	}

	 

	public String updateproduit(Produit produit) {
		try {
			serviceProduit.update(produit);

			return SUCCESS;
		} catch (DataAccessException e) {
		}
		return ERROR;
	}
	
	public String deleteproduit() {
		//produit.setStatut(Statut.DEACTIF);
		 
		// serviceProduit.delete(produits);
		 listprosuits=new ArrayList<Produit>();
		listprosuits = serviceProduit.getAll();
		 
		return SUCCESS;
	}

	public void modifier() {

	}

	public ServiceProduit getServiceProduit() {
		return serviceProduit;
	}

	public void setServiceProduit(ServiceProduit serviceProduit) {
		this.serviceProduit = serviceProduit;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public List<Produit> getListprosuit() {
		return listprosuit;
	}

	public void setListprosuit(List<Produit> listprosuit) {
		this.listprosuit = listprosuit;
	}

	public ServiceTva getServiceTva() {
		return serviceTva;
	}

	public void setServiceTva(ServiceTva serviceTva) {
		this.serviceTva = serviceTva;
	}

	public List<Tva> getListtvas() {
		return listtvas;
	}

	public void setListtvas(List<Tva> listtvas) {
		this.listtvas = listtvas;
	}

	 



	public List<Produit> getSelectedProduits() {
		return selectedProduits;
	}

	public void setSelectedProduits(List<Produit> selectedProduits) {
		this.selectedProduits = selectedProduits;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getQte() {
		return qte;
	}

	public Integer getTva() {
		return tva;
	}

	public void setQte(Integer qte) {
		this.qte = qte;
	}

	public void setTva(Integer tva) {
		this.tva = tva;
	}

	public List<Integer> getListtva() {
		return listtva;
	}

	public void setListtva(List<Integer> listtva) {
		this.listtva = listtva;
	}

	 
  
	public Date getDat() {
		return dat;
	}

	public void setDat(Date dat) {
		this.dat = dat;
	}

	public void onrowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Bl Selected", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		selectedProduits.add((Produit) event.getObject());
	}

	public void onrowUnSelect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("Bl Unselected", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		selectedProduits.remove((Produit) event.getObject());
	}

 

	public String getDates() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		if (dat != null)
			dates = s.format(dat);
		else
			dates = "";
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}
 

	public List<Produit> getFiltredProduit() {
		return filtredProduit;
	}

	public void setFiltredProduit(List<Produit> filtredProduit) {
		this.filtredProduit = filtredProduit;
	}

	public double getPrix_vente() {
		return prix_vente;
	}

	public void setPrix_vente(double prix_vente) {
		this.prix_vente = prix_vente;
	}

	

	public String getCodess() {
		return codess;
	}

	public void setCodess(String codess) {
		this.codess = codess;
	}

	public double getAchat() {
		return achat;
	}

	public void setAchat(double achat) {
		this.achat = achat;
	}

	public double getMarge() {
		return marge;
	}

	public void setMarge(double marge) {
		this.marge = marge;
	}

	public double getVentes() {
		return ventes;
	}

	public void setVentes(double ventes) {
		this.ventes = ventes;
	}

	public Integer getQtemin() {
		return qtemin;
	}

	public void setQtemin(Integer qtemin) {
		this.qtemin = qtemin;
	}

	public String getCodefamille() {
		return codefamille;
	}

	public void setCodefamille(String codefamille) {
		this.codefamille = codefamille;
	}

	public List<Famillearticle> getListfamile() {
		return listfamile;
	}

	public void setListfamile(List<Famillearticle> listfamile) {
		this.listfamile = listfamile;
	}

	public double getPrixvente() {
		return prixvente;
	}

	public void setPrixvente(double prixvente) {
		this.prixvente = prixvente;
	}

	public Produit getProduits() {
		return produits;
	}

	public void setProduits(Produit produits) {
		this.produits = produits;
	}

	public List<Produit> getListprosuits() {
		return listprosuits;
	}

	public void setListprosuits(List<Produit> listprosuits) {
		this.listprosuits = listprosuits;
	}

	public ServiceFamilleaticle getServiceFamilleaticle() {
		return serviceFamilleaticle;
	}

	public void setServiceFamilleaticle(ServiceFamilleaticle serviceFamilleaticle) {
		this.serviceFamilleaticle = serviceFamilleaticle;
	}

	public Famillearticle getFamille() {
		return famille;
	}

	public void setFamille(Famillearticle famille) {
		this.famille = famille;
	}
	
	

}
