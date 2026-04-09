package com.tn.shell.model.paie;

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
@Table(name = "Gestionrappel")
public class Gestionrappel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer annee;
	private String libelle;
	@Enumerated(EnumType.STRING)
	private Statut statut = Statut.ACTIF;

	private double valeurdeprime;
	@Transient
	private String valeurdeprimeaffichers;
  

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public double getValeurdeprime() {
		return valeurdeprime;
	}

	public void setValeurdeprime(double valeurdeprime) {
		this.valeurdeprime = valeurdeprime;
	}

	public void setValeurdeprimeaffichers(String valeurdeprimeaffichers) {
		this.valeurdeprimeaffichers = valeurdeprimeaffichers;
	}

	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	 

	public String getValeurdeprimeaffichers() {
		return valeurdeprimeaffichers;
	}
	
	

}
