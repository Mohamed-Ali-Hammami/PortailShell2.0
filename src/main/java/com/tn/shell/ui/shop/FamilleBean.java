package com.tn.shell.ui.shop;

import java.text.DecimalFormat;
import java.text.ParseException;
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

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.dao.DataAccessException;
 
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.tn.shell.model.shop.*;
import com.tn.shell.service.shop.*;

@ManagedBean(name = "FamilleBean")
@SessionScoped
public class FamilleBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{ServiceFamilleaticle}")
	ServiceFamilleaticle serviceFamilleaticle;
	@ManagedProperty(value = "#{ServiceProduit}")
	ServiceProduit serviceProduit;
	
	@ManagedProperty(value = "#{ServiceLignevente}")
	ServiceLignevente serviceLignevente;
	
	private Famillearticle familleArticle;
	private String famille;
	private Famillearticle famillearticle;
 
	private List<Famillearticle> listFamille;
	private Famillearticle selectedFamillearticle;
	private List<Famillearticle> filterFamillearticles; 
	private String codefamille;	 
    private List<Produit >listProduit;
    private List<Produit >filterproduits;
    private Produit selectedProduit;
    private List<String> selectedfamilles;
	private List<Lignevente> listelignevente;
	@PostConstruct
	public void init() {
	}

	
	public String mouvementArticle() {
		listFamille = new ArrayList<Famillearticle>();
		listFamille = serviceFamilleaticle.getAll();
		listProduit=new ArrayList<Produit>();
		return SUCCESS;
	}
	
	public void getArticlebyfamilles(AjaxBehaviorEvent event) {	       
			 listProduit = new ArrayList<Produit>();
			 listProduit=serviceProduit.getAllbyfamille(selectedfamilles.get(0));	     
		}
	
	public void getArticlebyfamilles2(AjaxBehaviorEvent event) {	       
		 listProduit = new ArrayList<Produit>();
		 listProduit=serviceProduit.getAllbyfamille(selectedFamillearticle.getNom());	 
		 listelignevente = new ArrayList<Lignevente>();
	}
	
	public void getVentebyarticle(AjaxBehaviorEvent event) {	       
		 listelignevente = new ArrayList<Lignevente>();
		 listelignevente=serviceLignevente.getAllbyProduit(selectedProduit);
	}
	
	public String saveFamille() {
		famillearticle = new Famillearticle();
		famillearticle.setNom(famille);
		serviceFamilleaticle.save(familleArticle);
		listFamille = new ArrayList<Famillearticle>();
		listFamille = serviceFamilleaticle.getAll();
		return SUCCESS;
	}

	public String getAllFamillearticle() {
		listFamille = new ArrayList<Famillearticle>();
		listFamille = serviceFamilleaticle.getAll();
		return SUCCESS;
	}
	
	public String getlistarticleParfamille() {
		listFamille = new ArrayList<Famillearticle>();
		listFamille = serviceFamilleaticle.getAll();

		return SUCCESS;
	}
	public void getArticlebyfamille(AjaxBehaviorEvent event) {
       // for(String s:selectedfamilles) {
		 listProduit = new ArrayList<Produit>();
		 listProduit=serviceProduit.getAllbyfamille(selectedfamilles.get(0));
     //   }
		 
		 

	}
	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "fournisseur change",
				((Produit) event.getObject()).getNom());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		updateFamillearticle((Famillearticle) event.getObject());
	}

	public String updateFamillearticle(Famillearticle produit) {
		try {
			getServiceFamilleaticle().update(produit);
			return SUCCESS;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	public String deletefamille() {
		selectedFamillearticle.setStatut(Statut.DEACTIF);
		serviceFamilleaticle.delete(selectedFamillearticle);
		listFamille = new ArrayList<Famillearticle>();
		listFamille = serviceFamilleaticle.getAll();
		return SUCCESS;
	}

	public ServiceFamilleaticle getServiceFamilleaticle() {
		return serviceFamilleaticle;
	}

	public void setServiceFamilleaticle(ServiceFamilleaticle serviceFamilleaticle) {
		this.serviceFamilleaticle = serviceFamilleaticle;
	}

	public Famillearticle getFamilleArticle() {
		return familleArticle;
	}

	public void setFamilleArticle(Famillearticle familleArticle) {
		this.familleArticle = familleArticle;
	}

	public List<Famillearticle> getListFamille() {
		return listFamille;
	}

	public void setListFamille(List<Famillearticle> listFamille) {
		this.listFamille = listFamille;
	}

	public Famillearticle getSelectedFamillearticle() {
		return selectedFamillearticle;
	}

	public void setSelectedFamillearticle(Famillearticle selectedFamillearticle) {
		this.selectedFamillearticle = selectedFamillearticle;
	}

	public List<Famillearticle> getFilterFamillearticles() {
		return filterFamillearticles;
	}

	public void setFilterFamillearticles(List<Famillearticle> filterFamillearticles) {
		this.filterFamillearticles = filterFamillearticles;
	}

	public String getFamille() {
		return famille;
	}

	public void setFamille(String famille) {
		this.famille = famille;
	}

	public Famillearticle getFamillearticle() {
		return famillearticle;
	}

	public void setFamillearticle(Famillearticle famillearticle) {
		this.famillearticle = famillearticle;
	}

	public String getCodefamille() {
		return codefamille;
	}

	public void setCodefamille(String codefamille) {
		this.codefamille = codefamille;
	}

	public List<Produit> getListProduit() {
		return listProduit;
	}

	public void setListProduit(List<Produit> listProduit) {
		this.listProduit = listProduit;
	}

	public List<Produit> getFilterproduits() {
		return filterproduits;
	}

	public void setFilterproduits(List<Produit> filterproduits) {
		this.filterproduits = filterproduits;
	}

	public Produit getSelectedProduit() {
		return selectedProduit;
	}

	public void setSelectedProduit(Produit selectedProduit) {
		this.selectedProduit = selectedProduit;
	}

	public ServiceProduit getServiceProduit() {
		return serviceProduit;
	}

	public void setServiceProduit(ServiceProduit serviceProduit) {
		this.serviceProduit = serviceProduit;
	}

	public List<String> getSelectedfamilles() {
		return selectedfamilles;
	}

	public void setSelectedfamilles(List<String> selectedfamilles) {
		this.selectedfamilles = selectedfamilles;
	}


	public ServiceLignevente getServiceLignevente() {
		return serviceLignevente;
	}


	public void setServiceLignevente(ServiceLignevente serviceLignevente) {
		this.serviceLignevente = serviceLignevente;
	}


	public List<Lignevente> getListelignevente() {
		return listelignevente;
	}


	public void setListelignevente(List<Lignevente> listelignevente) {
		this.listelignevente = listelignevente;
	}
	
	

}
