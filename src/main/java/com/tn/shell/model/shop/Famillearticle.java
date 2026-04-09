package com.tn.shell.model.shop;

import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Famillearticle")
public class Famillearticle {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
     private Integer code;
	 private String nom;
	 @Transient
		private String chiffre_affaires;
	 @Transient
	 private double quantite;
	 
	 @Transient
		private double profilbruts;
	 @Transient
	 private String quantites;
	 
	 @Transient
	 private String montant;
	 
	 @Transient
	 private Double montants;
 
	 @Transient
		private String profilbrut;
	 @Transient
		private String pourcentCAffTotal;
	 
	 @Transient
		private double pourcenPBTotals;
	 @Transient
		private String pourcenPBTotal;
	 
	 @Transient
		private String tvaCollecte;
	 @Transient
		private String tvaCollectee;
	 @Transient
		private String caAvoir;
	 
	 @Transient
		private String tvaAvoir;
	 
	 @Transient
		private String caAchat;
	 
	 @Transient
		private String tvaAchat;
	 
	 @Enumerated(EnumType.STRING)
		private Statut statut= Statut.ACTIF;
	  
	 @OneToMany(mappedBy="famille",cascade={CascadeType.MERGE,CascadeType.REMOVE,CascadeType.REFRESH})
	 private List<Produit> listProduit;
	 
	  
	 
	 
	 
	 
	public List<Produit> getListProduit() {
		return listProduit;
	}
	public void setListProduit(List<Produit> listProduit) {
		this.listProduit = listProduit;
	}
	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	public String getChiffre_affaires() {
		 
		return chiffre_affaires;
	}
	public void setChiffre_affaires(String chiffre_affaires) {
		this.chiffre_affaires = chiffre_affaires;
	}
	
	 
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getProfilbrut() {
		return profilbrut;
	}
	public void setProfilbrut(String profilbrut) {
		this.profilbrut = profilbrut;
	}
	public String getPourcentCAffTotal() {
		return pourcentCAffTotal;
	}
	public void setPourcentCAffTotal(String pourcentCAffTotal) {
		this.pourcentCAffTotal = pourcentCAffTotal;
	}
	public String getPourcenPBTotal() {
		return pourcenPBTotal;
	}
	public void setPourcenPBTotal(String pourcenPBTotal) {
		this.pourcenPBTotal = pourcenPBTotal;
	}
	public String getTvaCollecte() {
		return tvaCollecte;
	}
	public void setTvaCollecte(String tvaCollecte) {
		this.tvaCollecte = tvaCollecte;
	}
	public String getCaAvoir() {
		return caAvoir;
	}
	public void setCaAvoir(String caAvoir) {
		this.caAvoir = caAvoir;
	}
	public String getTvaAvoir() {
		return tvaAvoir;
	}
	public void setTvaAvoir(String tvaAvoir) {
		this.tvaAvoir = tvaAvoir;
	}
	public String getCaAchat() {
		return caAchat;
	}
	public void setCaAchat(String caAchat) {
		this.caAchat = caAchat;
	}
	public String getTvaAchat() {
		return tvaAchat;
	}
	public void setTvaAchat(String tvaAchat) {
		this.tvaAchat = tvaAchat;
	}
	public String getTvaCollectee() {
		return tvaCollectee;
	}
	public void setTvaCollectee(String tvaCollectee) {
		this.tvaCollectee = tvaCollectee;
	}
	public double getQuantite() {
		return quantite;
	}
	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}
	public String getMontant() {
		return montant;
	}
	public void setMontant(String montant) {
		this.montant = montant;
	}
	public Double getMontants() {
		return montants;
	}
	public void setMontants(Double montants) {
		this.montants = montants;
	}
	public String getQuantites() {
		DecimalFormat df=new DecimalFormat("0");
		quantites=df.format(quantite);
		return quantites;
	}
	public void setQuantites(String quantites) {
		this.quantites = quantites;
	}
	public double getProfilbruts() {
		return profilbruts;
	}
	public void setProfilbruts(double profilbruts) {
		this.profilbruts = profilbruts;
	}
	public double getPourcenPBTotals() {
		return pourcenPBTotals;
	}
	public void setPourcenPBTotals(double pourcenPBTotals) {
		this.pourcenPBTotals = pourcenPBTotals;
	}
	 
	 
	 
}
