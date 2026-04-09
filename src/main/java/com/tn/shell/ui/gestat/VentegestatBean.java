package com.tn.shell.ui.gestat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.dao.DataAccessException;

import com.tn.shell.model.gestat.Lignealimentationcar;
import com.tn.shell.model.gestat.Ligneventecredit;
import com.tn.shell.model.gestat.Poste;
import com.tn.shell.model.gestat.Status;
import com.tn.shell.model.gestat.TypePayement;
 
import com.tn.shell.model.shop.Fournisseur;
import com.tn.shell.model.shop.Produit;
import com.tn.shell.service.gestat.ServiceLigneAlimentationcar;
import com.tn.shell.service.gestat.ServiceLigneventeboncredit;
 
import com.tn.shell.service.shop.ServiceFournisseur;
import com.tn.shell.service.shop.ServiceProduit;

 

 

@ManagedBean(name = "VentegestatBean")
@SessionScoped
public class VentegestatBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{ServiceProduit}")
	ServiceProduit serviceProduit;

	@ManagedProperty(value = "#{ServiceFournisseur}")
	ServiceFournisseur serviceFournisseur;

	 
 
 

	 
	@ManagedProperty(value = "#{ServiceLigneAlimentationcar}")
	ServiceLigneAlimentationcar serviceLigneAlimentation;

	@ManagedProperty(value = "#{ServiceLigneventeboncredit}")
	ServiceLigneventeboncredit serviceLignevente; 

	private List<Fournisseur> listfournisseur;
	private List<String> listProduits;
	private String numFacture;
	private Integer numvente;
	private Date date;
  
	private List<Produit> listproduit;
	private Integer code;
	//private Ventebonclient vente;
	private String nomfournissuer;
	private Fournisseur fourniseur;
	private Produit produit;
	private Integer codes;
	private Integer codeproduit;
	private String libelle;
	private Integer qte;
	private Integer totalquantite;
	private double totalrecus;
	private double totalrendus;
	private String banque;
	private String numcheque;
	private TypePayement[] typepayements;
	private Date echeance;
	private String typepayement;
//	private List<Ventebonclient> listvente;
	private String dates;
	private List<Lignealimentationcar> listeLigne;
	private String totalht;
	private String totaltva;
	private String totalttcs;
	private String totalhtachat;
	private String totaltvaachat;
	private String totalttcsachat;
	private double totalttc;
	private Integer numticket;
	private Status[] status;
	private Status statu;
	 
	private List<Ligneventecredit> listelignevente;
	private Poste[] postes;
	private Poste poste;
	 
	private List<Ligneventecredit> listticketnegtif;
	 
	 
	private double totalqteTicknegatif;
	private double totalqteTransnegatif;
	 
	 

	private String codess;
	private String nom;
	private Integer quantite;
	private double achat;
	private Integer tva;
	private double marge;
	private double ventes;
	private Integer qtemin;
	private List<String> listBanque;
	private List<String> listMois;
	 
	private String codefamille;	 
	private List<String> selectedfamilles;

	
	
	
	
	/*
	 * Impression inventaire
	 **/
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

	  
	 

	public ServiceLigneAlimentationcar getServiceLigneAlimentation() {
		return serviceLigneAlimentation;
	}

	public void setServiceLigneAlimentation(ServiceLigneAlimentationcar serviceLigneAlimentation) {
		this.serviceLigneAlimentation = serviceLigneAlimentation;
	}

	public ServiceLigneventeboncredit getServiceLignevente() {
		return serviceLignevente;
	}

	public void setServiceLignevente(ServiceLigneventeboncredit serviceLignevente) {
		this.serviceLignevente = serviceLignevente;
	}

	 

	public List<Lignealimentationcar> getListeLigne() {
		return listeLigne;
	}

	public void setListeLigne(List<Lignealimentationcar> listeLigne) {
		this.listeLigne = listeLigne;
	}

	public List<Ligneventecredit> getListelignevente() {
		return listelignevente;
	}

	public void setListelignevente(List<Ligneventecredit> listelignevente) {
		this.listelignevente = listelignevente;
	}

	public List<Ligneventecredit> getListticketnegtif() {
		return listticketnegtif;
	}

	public void setListticketnegtif(List<Ligneventecredit> listticketnegtif) {
		this.listticketnegtif = listticketnegtif;
	}

	public List<Fournisseur> getListfournisseur() {
		return listfournisseur;
	}

	public void setListfournisseur(List<Fournisseur> listfournisseur) {
		this.listfournisseur = listfournisseur;
	}

	public List<String> getListProduits() {
		return listProduits;
	}

	public void setListProduits(List<String> listProduits) {
		this.listProduits = listProduits;
	}

	public String getNumFacture() {
		return numFacture;
	}

	public void setNumFacture(String numFacture) {
		this.numFacture = numFacture;
	}

	public Integer getNumvente() {
		return numvente;
	}

	public void setNumvente(Integer numvente) {
		this.numvente = numvente;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	 

	public List<Produit> getListproduit() {
		return listproduit;
	}

	public void setListproduit(List<Produit> listproduit) {
		this.listproduit = listproduit;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	 

	public String getNomfournissuer() {
		return nomfournissuer;
	}

	public void setNomfournissuer(String nomfournissuer) {
		this.nomfournissuer = nomfournissuer;
	}

	public Fournisseur getFourniseur() {
		return fourniseur;
	}

	public void setFourniseur(Fournisseur fourniseur) {
		this.fourniseur = fourniseur;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Integer getCodes() {
		return codes;
	}

	public void setCodes(Integer codes) {
		this.codes = codes;
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

	public Integer getQte() {
		return qte;
	}

	public void setQte(Integer qte) {
		this.qte = qte;
	}

	public Integer getTotalquantite() {
		return totalquantite;
	}

	public void setTotalquantite(Integer totalquantite) {
		this.totalquantite = totalquantite;
	}

	public double getTotalrecus() {
		return totalrecus;
	}

	public void setTotalrecus(double totalrecus) {
		this.totalrecus = totalrecus;
	}

	public double getTotalrendus() {
		return totalrendus;
	}

	public void setTotalrendus(double totalrendus) {
		this.totalrendus = totalrendus;
	}

	public String getBanque() {
		return banque;
	}

	public void setBanque(String banque) {
		this.banque = banque;
	}

	public String getNumcheque() {
		return numcheque;
	}

	public void setNumcheque(String numcheque) {
		this.numcheque = numcheque;
	}

	public TypePayement[] getTypepayements() {
		return typepayements;
	}

	public void setTypepayements(TypePayement[] typepayements) {
		this.typepayements = typepayements;
	}

	public Date getEcheance() {
		return echeance;
	}

	public void setEcheance(Date echeance) {
		this.echeance = echeance;
	}

	public String getTypepayement() {
		return typepayement;
	}

	public void setTypepayement(String typepayement) {
		this.typepayement = typepayement;
	}

	 
	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}
 

	public String getTotalht() {
		return totalht;
	}

	public void setTotalht(String totalht) {
		this.totalht = totalht;
	}

	public String getTotaltva() {
		return totaltva;
	}

	public void setTotaltva(String totaltva) {
		this.totaltva = totaltva;
	}

	public String getTotalttcs() {
		return totalttcs;
	}

	public void setTotalttcs(String totalttcs) {
		this.totalttcs = totalttcs;
	}

	public String getTotalhtachat() {
		return totalhtachat;
	}

	public void setTotalhtachat(String totalhtachat) {
		this.totalhtachat = totalhtachat;
	}

	public String getTotaltvaachat() {
		return totaltvaachat;
	}

	public void setTotaltvaachat(String totaltvaachat) {
		this.totaltvaachat = totaltvaachat;
	}

	public String getTotalttcsachat() {
		return totalttcsachat;
	}

	public void setTotalttcsachat(String totalttcsachat) {
		this.totalttcsachat = totalttcsachat;
	}

	public double getTotalttc() {
		return totalttc;
	}

	public void setTotalttc(double totalttc) {
		this.totalttc = totalttc;
	}

	public Integer getNumticket() {
		return numticket;
	}

	public void setNumticket(Integer numticket) {
		this.numticket = numticket;
	}

	public Status[] getStatus() {
		return status;
	}

	public void setStatus(Status[] status) {
		this.status = status;
	}

	public Status getStatu() {
		return statu;
	}

	public void setStatu(Status statu) {
		this.statu = statu;
	}

	 

	public Poste[] getPostes() {
		return postes;
	}

	public void setPostes(Poste[] postes) {
		this.postes = postes;
	}

	public Poste getPoste() {
		return poste;
	}

	public void setPoste(Poste poste) {
		this.poste = poste;
	}

	 
	public double getTotalqteTicknegatif() {
		return totalqteTicknegatif;
	}

	public void setTotalqteTicknegatif(double totalqteTicknegatif) {
		this.totalqteTicknegatif = totalqteTicknegatif;
	}

	public double getTotalqteTransnegatif() {
		return totalqteTransnegatif;
	}

	public void setTotalqteTransnegatif(double totalqteTransnegatif) {
		this.totalqteTransnegatif = totalqteTransnegatif;
	}

	public String getCodess() {
		return codess;
	}

	public void setCodess(String codess) {
		this.codess = codess;
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

	public String getCodefamille() {
		return codefamille;
	}

	public void setCodefamille(String codefamille) {
		this.codefamille = codefamille;
	}

	public List<String> getSelectedfamilles() {
		return selectedfamilles;
	}

	public void setSelectedfamilles(List<String> selectedfamilles) {
		this.selectedfamilles = selectedfamilles;
	}
    

	

 
	
	 
}
