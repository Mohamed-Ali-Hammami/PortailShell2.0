package com.tn.shell.model.gestat;

import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Articlecarburant")
public class Articlecarburant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nom;
	@Transient
	private double qte;
	private double quantite;
	@Transient
	private String quantites;
	private double remise;
	private double achat;
	private double vente;
	private Integer tvaachat;
	private double  qtemin;
	private double stockinitial;
	private Integer tvavente;
	@Transient
	List<Cumulcarburant> listcumil;
	@Transient
	private double montant;
	@Transient
	private String qtes;
	@Transient
	private double valeur;
	@Transient
	private String montants;
	@Enumerated(EnumType.STRING)
	private Statut statut = Statut.ACTIF;
	@Transient
	private String prixventes;
	@Transient
	private String prixachats;

	@OneToMany(mappedBy = "articlecarburant", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Pompe> pompes;
	
	@OneToMany(mappedBy = "articlecarburant", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Lignealimentationcar> lignealimentations;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

	public List<Lignealimentationcar> getLignealimentations() {
		return lignealimentations;
	}

	public void setLignealimentations(List<Lignealimentationcar> lignealimentations) {
		this.lignealimentations = lignealimentations;
	}

	public List<Pompe> getPompes() {
		return pompes;
	}

	public void setPompes(List<Pompe> pompes) {
		this.pompes = pompes;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getQuantite() {
		return quantite;
	}

	public void setQuantite(double quantite) {
		this.quantite = quantite;
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

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getMontants() {
		return montants;
	}

	public void setMontants(String montants) {
		this.montants = montants;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public String getPrixventes() {
		DecimalFormat df = new DecimalFormat("0.000");
		 
		prixventes = df.format(vente);
		 
		return prixventes;
	}

	public String getQtes() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		qtes = df.format(qte);
		return qtes;
	}

	public void setQtes(String qtes) {
		this.qtes = qtes;
	}

	public void setPrixventes(String prixventes) {
		this.prixventes = prixventes;
	}

	public String getPrixachats() {
		DecimalFormat df = new DecimalFormat("0.000");
		prixachats = df.format(achat);
		return prixachats;
	}

	public void setPrixachats(String prixachats) {
		this.prixachats = prixachats;
	}

	public double getQtemin() {
		return qtemin;
	}

	public void setQtemin(double qtemin) {
		this.qtemin = qtemin;
	}

	public double getQte() {
		return qte;
	}

	public void setQte(double qte) {
		this.qte = qte;
	}

	public Integer getTvaachat() {
		return tvaachat;
	}

	public void setTvaachat(Integer tvaachat) {
		this.tvaachat = tvaachat;
	}

	public Integer getTvavente() {
		return tvavente;
	}

	public void setTvavente(Integer tvavente) {
		this.tvavente = tvavente;
	}

	public double getValeur() {
		return valeur;
	}

	public void setValeur(double valeur) {
		this.valeur = valeur;
	}

	public String getQuantites() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		if(quantite<=1) {
			 df = new DecimalFormat("0.000");
		quantites = df.format(quantite);
		}
		else  
			 
			quantites = df.format(quantite);
		return quantites;
	}

	public void setQuantites(String quantites) {
		this.quantites = quantites;
	}

	public List<Cumulcarburant> getListcumil() {
		return listcumil;
	}

	public void setListcumil(List<Cumulcarburant> listcumil) {
		this.listcumil = listcumil;
	}

	public double getStockinitial() {
		return stockinitial;
	}

	public void setStockinitial(double stockinitial) {
		this.stockinitial = stockinitial;
	}

	public double getRemise() {
		return remise;
	}

	public void setRemise(double remise) {
		this.remise = remise;
	}

 

}
