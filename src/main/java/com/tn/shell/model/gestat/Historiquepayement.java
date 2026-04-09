package com.tn.shell.model.gestat;

import java.text.DecimalFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Historiquepayement")
public class Historiquepayement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String dates;
	private double montant;
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF; 
	
	@ManyToOne(cascade = { CascadeType.MERGE })
//	@JoinColumn(name = "clientid")
	private Creditpassation creditpassation;
	
	@Transient
	private String montants;
	@ManyToOne(cascade = { CascadeType.MERGE })
//	@JoinColumn(name = "clientid")
	private Utilisateur utilisateur;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDates() {
		return dates;
	}


	public void setDates(String dates) {
		this.dates = dates;
	}


	public double getMontant() {
		return montant;
	}


	public void setMontant(double montant) {
		this.montant = montant;
	}


	public Statut getStatut() {
		return statut;
	}


	public void setStatut(Statut statut) {
		this.statut = statut;
	}


	public Creditpassation getCreditpassation() {
		return creditpassation;
	}


	public void setCreditpassation(Creditpassation creditpassation) {
		this.creditpassation = creditpassation;
	}


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
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
