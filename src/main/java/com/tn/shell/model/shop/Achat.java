package com.tn.shell.model.shop;

import java.text.SimpleDateFormat;
import java.util.Date;
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
@Table(name = "Achat")
public class Achat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;	 
	@Transient
	private String montants;
	private double montant;
 
	private Date date;
	@Transient
	private String dates;
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF;
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "factureachatid")
	private Factureachat factureachat;
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "fournisseurid")
	private Fournisseur fournisseur;
	
	  
	
	@OneToMany(mappedBy = "achat", cascade = { CascadeType.MERGE,
			CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Lignealimentation > Lignealimentations;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Factureachat getFactureachat() {
		return factureachat;
	}

	public void setFactureachat(Factureachat factureachat) {
		this.factureachat = factureachat;
	}

	 

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	 

	public String getMontants() {
		return montants;
	}

	public void setMontants(String montants) {
		this.montants = montants;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

 

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDates() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates=s.format(date);
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	 

	public List<Lignealimentation> getLignealimentations() {
		return Lignealimentations;
	}

	public void setLignealimentations(List<Lignealimentation> lignealimentations) {
		Lignealimentations = lignealimentations;
	}

	 
	
	
}
