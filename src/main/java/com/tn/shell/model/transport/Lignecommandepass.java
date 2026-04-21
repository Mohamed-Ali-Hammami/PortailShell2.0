package com.tn.shell.model.transport;

import java.text.DecimalFormat;

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

import com.tn.shell.model.shop.Produit;

@Entity
@Table(name = "lignecommandepass")
public class Lignecommandepass {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private double quantite;
	private double prix;
	@Transient
	private String prixs;
	@Transient
	private String quantites;
	 private double mantantht;
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF; 
	@Transient
	 private String mantanthts;
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "produitsid")
	private Produit produit;
	private Integer tva;
	
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "facturepassagerid")
	private Facturepassager facturepassager;
	 
	
	
	 
	
	 
	
	private double transport;
	@Transient
	private String transports;

	/*@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "transportid")
	private Transport transport;*/

	public Lignecommandepass() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
 
	private double tauxtva;
	

	public double getTauxtva() {
		return tauxtva;
	}

	public void setTauxtva(double tauxtva) {
		this.tauxtva = tauxtva;
	}

	 

	 

	  

	public String getTransports() {
		DecimalFormat df=new DecimalFormat("0");
		transports=df.format(transport);
		 
		return transports;
	}

	public void setTransports(String transports) {
		this.transports = transports;
	}

	public String getQuantites() {
		DecimalFormat df=new DecimalFormat("0.000");
		quantites=df.format(quantite);
		return quantites;
	}

	public void setQuantites(String quantites) {
		this.quantites = quantites;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	 
	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	 

	public double getTransport() {
		return transport;
	}

	public void setTransport(double transport) {
		this.transport = transport;
	}

	
	  

	public double getQuantite() {
		return quantite;
	}

	public void setQuantite(double quantite) {
		this.quantite = quantite;
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

	public Integer getTva() {
		return tva;
	}

	public void setTva(Integer tva) {
		this.tva = tva;
	}

	 
	public Facturepassager getFacturepassager() {
		return facturepassager;
	}

	public void setFacturepassager(Facturepassager facturepassager) {
		this.facturepassager = facturepassager;
	}

	 
	 
	 
	

}
