package com.tn.shell.model.gestat;

import java.text.DecimalFormat;
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

import com.tn.shell.model.banque.Transaction;

@Entity
@Table(name="Cheque")
public class Cheque {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private Integer id; 
	
	private String numero;
	private String banque;
	 
	private String dates;
	private Date date;
	
	private String datesecheance;
	private Date echeance;
	 
	 private String client;
	private double montant;
	@Transient
	private String montants;
	private String libelle;
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "caisseid")
	private Caisse caisse;
	
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF;
	
	@Enumerated(EnumType.STRING)
	private Enumcheque etat= Enumcheque.Entree;
	
	@OneToMany(mappedBy = "cheque", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Transaction> listtransaction;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	 
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBanque() {
		return banque;
	}
	public void setBanque(String banque) {
		this.banque = banque;
	}
	public String getDates() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates=s.format(date);
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	  
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getMontants() {
		DecimalFormat df=new DecimalFormat("#,###.000");
		montants=df.format(montant);
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
	public Caisse getCaisse() {
		return caisse;
	}
	public void setCaisse(Caisse caisse) {
		this.caisse = caisse;
	}
	public String getDatesecheance() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		if(echeance!=null)
		datesecheance=s.format(echeance);
		else datesecheance=null;
		return datesecheance;
	}
	public void setDatesecheance(String datesecheance) {
		this.datesecheance = datesecheance;
	}
	public Date getEcheance() {
		
		return echeance;
	}
	public void setEcheance(Date echeance) {
		this.echeance = echeance;
	}
	public Enumcheque getEtat() {
		return etat;
	}
	public void setEtat(Enumcheque etat) {
		this.etat = etat;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public List<Transaction> getListtransaction() {
		return listtransaction;
	}
	public void setListtransaction(List<Transaction> listtransaction) {
		this.listtransaction = listtransaction;
	}
	
	
	
	
}
