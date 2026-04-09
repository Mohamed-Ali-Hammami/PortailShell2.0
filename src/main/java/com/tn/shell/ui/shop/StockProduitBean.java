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
import com.tn.shell.model.shop.Produit;
import com.tn.shell.service.gestat.UserService;
import com.tn.shell.service.shop.*;

@ManagedBean(name = "StockProduitBean")
@SessionScoped
public class StockProduitBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{ServiceProduit}")
	ServiceProduit serviceProduit;
	 

	@ManagedProperty(value = "#{ServiceTrace}")
	ServiceTraceshop serviceTrace;

	@ManagedProperty(value = "#{UserService}")
	UserService userService;
	@ManagedProperty(value = "#{ServiceFamilleaticle}")
	ServiceFamilleaticle serviceFamilleaticle;

	@ManagedProperty(value = "#{ServiceParamettre}")
	ServiceParamettre serviceParamettre;
	@ManagedProperty(value = "#{ServiceTva}")
	ServiceTva serviceTva;

	private Produit produit;
	private List<String> listBanque;
	private List<String> listMois;
	private Date date;

 
	private Famillearticle selectedFamillearticle;
	private List<Famillearticle> filterFamillearticles;
	private String codefamille;
	private List<Famillearticle> listFamille;
    private List<Produit >listProduit;
    private List<Produit >filterproduits;
    private Produit selectedProduit;
	@PostConstruct
	public void init() {
		date = new Date();
		listBanque = new ArrayList<String>();
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

	public String getlistarticleParfamille() {
		listFamille = new ArrayList<Famillearticle>();
		listFamille = serviceFamilleaticle.getAll();

		return SUCCESS;
	}

	public String majstock() {

		return SUCCESS;
	}

	public void getArticlebyfamille(AjaxBehaviorEvent event) {

		 listProduit = new ArrayList<Produit>();
		 Produit p= serviceProduit.getProduitbydesignation(codefamille);
		listProduit.add(p);
		 

	}

	public String getAllarticle() {
		 listProduit = new ArrayList<Produit>();
		listProduit = serviceProduit.getAll(); 
		return SUCCESS;
	}

	public void onRowEdit(RowEditEvent event) {

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "fournisseur changé",
				((Produit) event.getObject()).getNom());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		updateProduit((Produit) event.getObject());

	}

	public String updateProduit(Produit produit) {
		try {
			//getServiceProduit().update(produit);
			return SUCCESS;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	 
	 
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ServiceFamilleaticle getServiceFamilleaticle() {
		return serviceFamilleaticle;
	}

	public void setServiceFamilleaticle(ServiceFamilleaticle serviceFamilleaticle) {
		this.serviceFamilleaticle = serviceFamilleaticle;
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

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
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

	public String getCodefamille() {
		return codefamille;
	}

	public void setCodefamille(String codefamille) {
		this.codefamille = codefamille;
	}

	public List<Famillearticle> getListFamille() {
		return listFamille;
	}

	public void setListFamille(List<Famillearticle> listFamille) {
		this.listFamille = listFamille;
	}

	public ServiceProduit getServiceProduit() {
		return serviceProduit;
	}

	public void setServiceProduit(ServiceProduit serviceProduit) {
		this.serviceProduit = serviceProduit;
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

	public ServiceTraceshop getServiceTrace() {
		return serviceTrace;
	}

	public void setServiceTrace(ServiceTraceshop serviceTrace) {
		this.serviceTrace = serviceTrace;
	}
	

}
