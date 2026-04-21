package com.tn.shell.model.shop;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

 

@Entity
@Table(name = "Lignealimentation")
public class Lignealimentation implements Comparable<Object>{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Integer id;
	private Integer tva;
	private double quantite;
	@Transient
	private String libelle;
	@Transient
	private String quantites;
	private Date date;
	 
	private String dates;
	@Transient
	private double montant;
	@Transient
	private String montants; 
	private double prix;
	@Transient
	private String prixs;	 
    private double mantantht;
    @Transient
	private String mantanthts;
    private double totaltva;
 	private double totalttc;
	
	
	
	
	 
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF;
	 
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "produitsid")
	@NotFound(action = NotFoundAction.IGNORE)
	private Produit produit; 
	
	@ManyToOne(cascade = { CascadeType.MERGE ,CascadeType.DETACH,CascadeType.REFRESH} )
	@JoinColumn(name = "achatid")
	@NotFound(action = NotFoundAction.IGNORE)
	private Achat achat; 
	
	  
	
	
	
	
    
	 
private double tauxtva;
	

	public double getTauxtva() {
		return tauxtva;
	}

	public void setTauxtva(double tauxtva) {
		this.tauxtva = tauxtva;
	}


	public Integer getTva() {
		return tva;
	}

	public void setTva(Integer tva) {
		this.tva = tva;
	}

	public Achat getAchat() {
		return achat;
	}

	public void setAchat(Achat achat) {
		this.achat = achat;
	}

	public Lignealimentation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	 

	public double getQuantite() {
		return quantite;
	}

	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDates() {
		SimpleDateFormat s=new SimpleDateFormat("dd-MM-yyyy");
		//dates=s.format(date);
		return dates;
		 
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getMontants() {
		DecimalFormat df=new DecimalFormat("0.000");
		montants=df.format(montant);
		return montants;
	}

	public void setMontants(String montants) {
		this.montants = montants;
	}

	 

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public String getPrixs() {
		DecimalFormat df=new DecimalFormat("0.000");
		prixs=df.format(prix);
		return prixs;
	}

	public void setPrixs(String prixs) {
		this.prixs = prixs;
	}

	public double getMantantht() {
		return mantantht;
	}

	public void setMantantht(double mantantht) {
		this.mantantht = mantantht;
	}

	public String getMantanthts() {
		DecimalFormat df=new DecimalFormat("0.000");
		mantanthts=df.format(mantantht);
		return mantanthts;
	}

	public void setMantanthts(String mantanthts) {
		this.mantanthts = mantanthts;
	}

	public double getTotaltva() {
		return totaltva;
	}

	public void setTotaltva(double totaltva) {
		this.totaltva = totaltva;
	}

	public double getTotalttc() {
		return totalttc;
	}

	public void setTotalttc(double totalttc) {
		this.totalttc = totalttc;
	}
 
	public String getQuantites() {
		DecimalFormat df=new DecimalFormat("0");
		quantites=df.format(quantite);
		return quantites;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public void setQuantites(String quantites) {
		this.quantites = quantites;
	}
	public int compareTo(Object o) {
		try{
		Lignealimentation f = (Lignealimentation) o; 
		 return (int) (this.getProduit().getCodeshop()-f.getProduit().getCodeshop()) ;		 
		}catch(Exception e) {
			return 0;
		}
	}

	 


}
