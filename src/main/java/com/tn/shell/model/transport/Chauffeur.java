package com.tn.shell.model.transport;

import java.text.DecimalFormat;
import java.util.Date;
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
@Table(name = "chauffeur")
public class Chauffeur {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id; 
	private String nompenom;
 
	 private String cin;
	 private String numtel;
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF;
	private double salaire;
	@Transient
	private String salaires;
	@Transient
	private Integer nbvoyage;
	
	@Transient
	private Integer totalnbvoyage=0;
	@Transient
	private List<RapportChauffeur> rendement;
	// lien one to many avec bl
	@OneToMany(mappedBy = "chauffeur", cascade = { CascadeType.MERGE,
			CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Bonlivraison> bonlivraison;
 
	
	@OneToMany(mappedBy = "chauffeur", cascade = { CascadeType.MERGE,
			CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Facturepassager> facturep;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	 

	public String getNompenom() {
		return nompenom;
	}

	public void setNompenom(String nompenom) {
		this.nompenom = nompenom;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public List<Bonlivraison> getBonlivraison() {
		return bonlivraison;
	}

	public void setBonlivraison(List<Bonlivraison> bonlivraison) {
		this.bonlivraison = bonlivraison;
	}

	public String getNumtel() {
		return numtel;
	}

	public void setNumtel(String numtel) {
		this.numtel = numtel;
	}

	 

	public double getSalaire() {
		return salaire;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}

	public String getSalaires() {
		DecimalFormat df=new DecimalFormat("0.000");
		salaires=df.format(salaire);
		return salaires;
	}

	public void setSalaires(String salaires) {
		this.salaires = salaires;
	}

	public List<Facturepassager> getFacturep() {
		return facturep;
	}

	public void setFacturep(List<Facturepassager> facturep) {
		this.facturep = facturep;
	}

	public Integer getNbvoyage() {
		return nbvoyage;
	}

	public void setNbvoyage(Integer nbvoyage) {
		this.nbvoyage = nbvoyage;
	}

	public Integer getTotalnbvoyage() {
		return totalnbvoyage;
	}

	public void setTotalnbvoyage(Integer totalnbvoyage) {
		this.totalnbvoyage = totalnbvoyage;
	}

	public List<RapportChauffeur> getRendement() {
		return rendement;
	}

	public void setRendement(List<RapportChauffeur> rendement) {
		this.rendement = rendement;
	}

 

	 

}
