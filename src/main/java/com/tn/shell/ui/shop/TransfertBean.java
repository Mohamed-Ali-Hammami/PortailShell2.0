package com.tn.shell.ui.shop;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.CellEditEvent;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.*;
import com.tn.shell.model.shop.*;
import com.tn.shell.service.gestat.*;
import com.tn.shell.service.shop.*;

@ManagedBean(name = "TransfertBean")
@SessionScoped
public class TransfertBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	@ManagedProperty(value = "#{ServiceTraceshop}")
    ServiceTraceshop servicetrace;
	@ManagedProperty(value = "#{UserService}")
	UserService userservice;
	@ManagedProperty(value = "#{ServiceProduit}")
	ServiceProduit serviceProduit;
	@ManagedProperty(value = "#{ServiceFamilleaticle}")
	ServiceFamilleaticle serviceFamilleaticle;
	@ManagedProperty(value = "#{ServiceParamettre}")
	ServiceParamettre serviceParamettre;
	@ManagedProperty(value = "#{ServiceTva}")
	ServiceTva serviceTva;
	@ManagedProperty(value = "#{ServiceTransfert}")
	ServiceTransfert serviceTransfert;
	@ManagedProperty(value = "#{UserService}")
	UserService userService;
	@ManagedProperty(value = "#{ServiceLignetransfert}")
	ServiceLignetransfert serviceLignetransfert;
	private Utilisateur user;
	private Produit produit;
	private List<String> listBanque;
	private List<String> listMois;
	private Date date;
	private List<Produit> listProduit;
	private List<Produit> listproduit;
	private Produit selectedProduit;
	private List<Produit> filteredProduit;
	private Famillearticle selectedFamillearticle;
	private List<Famillearticle> filterFamillearticles;
	private String codefamille;
	private List<Famillearticle> listFamille;
	private Transfert transfert;
	private double totalttc;
	private double totalquantite = 0;
	private String totalttcs;
	private Integer codes;
	private Integer codeproduit;
	private String dates;
    
	private String libelle;
	private List<Lignetransert> listLignetransfert;

	public String nouveautransfer() {
		transfert = new Transfert();
		libelle = null;codefamille=null;
		date = new Date();
		 codes=0;
		listproduit = new ArrayList<Produit>();
		listproduit = serviceProduit.getAll();
		listLignetransfert = new ArrayList<Lignetransert>();
		for (int i = 0; i < 100; i++) {
			Lignetransert p = new Lignetransert();
			p.setProduit(null);
			p.setQuantite(0);
		 
			listLignetransfert.add(p);
		}
		user = userService.getCurrentUser();
		return SUCCESS;
	}

	@Transactional
	public String saveTransfert() {		 
		transfert = new Transfert();
		transfert.setUtilisateur(user);
		transfert.setDate(date);
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		serviceTransfert.save(transfert);
		for (Lignetransert t : listLignetransfert) {
			if (t.getProduit() != null 
					&& t.getQuantite() != 0) {			 
				t.setTransfert(transfert);
				t.setDates(dates);
				t.setDate(date);
				serviceLignetransfert.save(t);
				t.getProduit().setQuantitedepot(t.getProduit().getQuantitedepot() - t.getQuantite());
				t.getProduit().setQuantitestock(t.getProduit().getQuantitestock() + t.getQuantite());
				serviceProduit.update(t.getProduit());
				 
			}
		}
		Utilisateur user=userservice.getCurrentUser();
		Traceshop t=new Traceshop();
		t.setAction("save transfet"+transfert.getId()+ " de "+transfert.getDates()+" par"+user.getNom());
		t.setDate(new Date());
		t.setUtilisateur(user);
		servicetrace.save(t);
		return SUCCESS;
	}

	 
	
	public void handleChange(ValueChangeEvent event) {
		 
		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
		produit = serviceProduit.Findbycodes((String)event.getNewValue());
		Lignetransert t = listLignetransfert.get(codes);
		t.setProduit(produit);
		listLignetransfert.set(codes, t);
		 
	}
	 
	public void updatenom111(AjaxBehaviorEvent event) {
		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
		Lignetransert t = listLignetransfert.get(codes);		 
		Produit p = serviceProduit.Findbycodes(codefamille);
		t.setProduit(p);
		listLignetransfert.set(codes, t);
		codefamille = null;
		codes=codes+1;

	}

	public void updateCode(AjaxBehaviorEvent event) {
		updatenom111(event);
	}

	public void updatenom(AjaxBehaviorEvent event) {
		updatenom111(event);
	}
	  public void saveselection(ActionEvent event) {
			  DecimalFormat df = new DecimalFormat("0.000");	
			  codefamille=selectedProduit.getCode();
			  
			  Lignetransert t = listLignetransfert.get(codes);
			  t.setProduit(selectedProduit);
			  listLignetransfert.set(codes, t);
			  codefamille=null; selectedProduit=null;
			  codes=codes+1;
			  filteredProduit=null;
	  }

	private Integer verifierarticle(Lignetransert libelle, Integer i) {

		for (int j = 0; j < listLignetransfert.size(); j++) {
			if (libelle.getProduit().getCode().equals(listLignetransfert.get(j).getProduit().getCode()) && i != j) {
				return j;

			}
		}
		return i;
	}

	public String etatTransfert() {
		date = new Date();
		listLignetransfert = new ArrayList<Lignetransert>();
		return SUCCESS;
	}

	public void gettrasfertbydate(AjaxBehaviorEvent event) {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		listLignetransfert = new ArrayList<Lignetransert>();
		listLignetransfert = serviceLignetransfert.getAllbydate(dates);
	}

	/*
	 * getter and setter
	 */

	public ServiceProduit getServiceProduit() {
		return serviceProduit;
	}

	public void setServiceProduit(ServiceProduit serviceProduit) {
		this.serviceProduit = serviceProduit;
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

	public ServiceTransfert getServiceTransfert() {
		return serviceTransfert;
	}

	public void setServiceTransfert(ServiceTransfert serviceTransfert) {
		this.serviceTransfert = serviceTransfert;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ServiceLignetransfert getServiceLignetransfert() {
		return serviceLignetransfert;
	}

	public void setServiceLignetransfert(ServiceLignetransfert serviceLignetransfert) {
		this.serviceLignetransfert = serviceLignetransfert;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
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

	public List<Produit> getListProduit() {
		return listProduit;
	}

	public void setListProduit(List<Produit> listProduit) {
		this.listProduit = listProduit;
	}

	public Produit getSelectedProduit() {
		return selectedProduit;
	}

	public void setSelectedProduit(Produit selectedProduit) {
		this.selectedProduit = selectedProduit;
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

	public Transfert getTransfert() {
		return transfert;
	}

	public void setTransfert(Transfert transfert) {
		this.transfert = transfert;
	}

	 

	public Integer getCodes() {
		return codes;
	}

	public void setCodes(Integer codes) {
		this.codes = codes;
	}

	public List<Produit> getFilteredProduit() {
		return filteredProduit;
	}

	public void setFilteredProduit(List<Produit> filteredProduit) {
		this.filteredProduit = filteredProduit;
	}

	public Integer getCodeproduit() {
		return codeproduit;
	}

	public void setCodeproduit(Integer codeproduit) {
		this.codeproduit = codeproduit;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Lignetransert> getListLignetransfert() {
		return listLignetransfert;
	}

	public void setListLignetransfert(List<Lignetransert> listLignetransfert) {
		this.listLignetransfert = listLignetransfert;
	}

	public List<Produit> getListproduit() {
		return listproduit;
	}

	public void setListproduit(List<Produit> listproduit) {
		this.listproduit = listproduit;
	}

	public double getTotalttc() {
		return totalttc;
	}

	public void setTotalttc(double totalttc) {
		this.totalttc = totalttc;
	}

	public double getTotalquantite() {
		return totalquantite;
	}

	public void setTotalquantite(double totalquantite) {
		this.totalquantite = totalquantite;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public String getTotalttcs() {
		DecimalFormat df = new DecimalFormat("0.000");
		totalttcs = df.format(totalttc);
		return totalttcs;
	}

	public void setTotalttcs(String totalttcs) {
		this.totalttcs = totalttcs;
	}

	public ServiceTraceshop getServicetrace() {
		return servicetrace;
	}

	public void setServicetrace(ServiceTraceshop servicetrace) {
		this.servicetrace = servicetrace;
	}

	public UserService getUserservice() {
		return userservice;
	}

	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}
	

}
