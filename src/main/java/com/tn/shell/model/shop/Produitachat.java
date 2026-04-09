package com.tn.shell.model.shop;

import java.text.DecimalFormat;

 

public class Produitachat {

	private Integer id;
	private String code;
	private String nom;
	
 
	 
	private double achat;
	private Integer tva;
	private double marge;
	private double vente;
	private Integer qtemin;
	 
	private Integer quantites;
	 
	private double montant;
 
	private String montants;
	 
	 
	private String prixventes;
 

	 
	private String prixachats;
	 
	private String marges;
 

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

	 

	public double getAchat() {
		return achat;
	}

	public void setAchat(double achat) {
		this.achat = achat;
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
	

	public Integer getQuantites() {
		return quantites;
	}

	public void setQuantites(Integer quantites) {
		this.quantites = quantites;
	}

	 
	 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	 
	public String getPrixventes() {
		DecimalFormat df=new DecimalFormat("0.000");
		prixventes=df.format(vente);
		return prixventes;
	}

	public void setPrixventes(String prixventes) {
		this.prixventes = prixventes;
	}

	 

	public String getPrixachats() {
		DecimalFormat df=new DecimalFormat("0.000");
		prixachats=df.format(achat);
		return prixachats;
	}

	public void setPrixachats(String prixachats) {
		this.prixachats = prixachats;
	}

	public String getMarges() {
		DecimalFormat df=new DecimalFormat("0.000");
		marges=df.format(marge);
		return marges;
	}

	public void setMarges(String marges) {
		this.marges = marges;
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
	
	
	
	

}
