package com.tn.shell.model.transport;

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
@Table(name = "Depense")
public class Depense {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private double montant=0;
	private String libelle;
 
	private Date date;
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF;
	
	private String dates;
	
	@Transient
	private String montants;
	@Transient
	private String montantsautre;
	@Transient
	private String totaltrans;
	@Transient
	private String df;
	@Transient
	private String totaldep;
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "vheculeid")
	private Vhecule vhecule; 
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "familledepenseid")
	private Familledepensetransport familledepense;
	
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
		DecimalFormat df=new DecimalFormat("0.000");
		montants=df.format(montant);
		return montants;
	}
	public void setMontants(String montants) {
		this.montants = montants;
	}
	public Vhecule getVhecule() {
		return vhecule;
	}
	public void setVhecule(Vhecule vhecule) {
		this.vhecule = vhecule;
	}
	public Familledepensetransport getFamilledepense() {
		return familledepense;
	}
	public void setFamilledepense(Familledepensetransport familledepense) {
		this.familledepense = familledepense;
	}
	public String getMontantsautre() {
		return montantsautre;
	}
	public void setMontantsautre(String montantsautre) {
		this.montantsautre = montantsautre;
	}
	public String getTotaltrans() {
		return totaltrans;
	}
	public void setTotaltrans(String totaltrans) {
		this.totaltrans = totaltrans;
	}
	public String getTotaldep() {
		return totaldep;
	}
	public void setTotaldep(String totaldep) {
		this.totaldep = totaldep;
	}
	public String getDf() {
		return df;
	}
	public void setDf(String df) {
		this.df = df;
	}
	 
	 
	

}
