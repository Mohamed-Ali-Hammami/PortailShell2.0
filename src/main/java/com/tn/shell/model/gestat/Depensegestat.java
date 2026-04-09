package com.tn.shell.model.gestat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

@Entity
@Table(name = "Depensegestat")
public class Depensegestat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private double montant=0;
	private String libelle;
	 
    private Integer idfamille=0;
	private Date date;
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF;
	 
	private String dates;
	@Transient
	private Integer  familedepvoiture=0;
	@Transient
	private String montants;
	

	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "transactiondepid")
	private TransactionDepense transaction;
	 
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "familledepenseid")
	private Familledepensegestat familledepense;
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "caisseid")
	private Caisse caisse;
	
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
	 
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDates() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
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
	public String getMontants() {
		DecimalFormat df=new DecimalFormat("#,###.000");
		montants=df.format(montant);
		return montants;
	}
	public void setMontants(String montants) {
		this.montants = montants;
	}
	 
	public Familledepensegestat getFamilledepense() {
		return familledepense;
	}
	public void setFamilledepense(Familledepensegestat familledepense) {
		this.familledepense = familledepense;
	}
	public Caisse getCaisse() {
		return caisse;
	}
	public void setCaisse(Caisse caisse) {
		this.caisse = caisse;
	}
	public Integer getIdfamille() {
		return idfamille;
	}
	public void setIdfamille(Integer idfamille) {
		this.idfamille = idfamille;
	}
	public TransactionDepense getTransaction() {
		return transaction;
	}
	public void setTransaction(TransactionDepense transaction) {
		this.transaction = transaction;
	}
	public Integer getFamiledepvoiture() {
		return familedepvoiture;
	}
	public void setFamiledepvoiture(Integer familedepvoiture) {
		this.familedepvoiture = familedepvoiture;
	}
	 
	 
	 
	

}
