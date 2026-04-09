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
@Table(name = "Creditpassation ")
public class Creditpassation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private double montant;	
	private Poste poste;
	private String dates;
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF; 
	
	@Transient
	private String montants;
	@ManyToOne(cascade = { CascadeType.MERGE })
//	@JoinColumn(name = "clientid")
	private Clientpassation clientpassation;	
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	private Credit credit;
	
	
	@OneToMany(mappedBy = "creditpassation", cascade = { CascadeType.MERGE,
			CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Historiquepayement> historiquess;
	
	private Integer numBon;
	
	
	
	public Integer getNumBon() {
		return numBon;
	}
	public void setNumBon(Integer numBon) {
		this.numBon = numBon;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Clientpassation getClientpassation() {
		return clientpassation;
	}
	public void setClientpassation(Clientpassation clientpassation) {
		this.clientpassation = clientpassation;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public Poste getPoste() {
		return poste;
	}
	public void setPoste(Poste poste) {
		this.poste = poste;
	}
	public Credit getCredit() {
		return credit;
	}
	public void setCredit(Credit credit) {
		this.credit = credit;
	}
	public List<Historiquepayement> getHistoriquess() {
		return historiquess;
	}
	public void setHistoriquess(List<Historiquepayement> historiquess) {
		this.historiquess = historiquess;
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
