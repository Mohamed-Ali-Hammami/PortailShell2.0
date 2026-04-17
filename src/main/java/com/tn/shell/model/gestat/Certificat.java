package com.tn.shell.model.gestat;
import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

//@Entity
//@Table(name="Certificat")
@Embeddable
public class Certificat {
/*	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)*/
private Integer num=0;
	
	private double montant=0;
	//@Transient
	private String montants;
	 
	private double retenue=0;
	//@Transient
	private String retenues;
	private double montantbrut=0;
	//@Transient
	private String montantbruts;
	private String cin;
	//@Transient
	private String dates;
	private Date date=null;
	/*@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "fournisseurid")
	private Fournisseur fournisseur;*/
	 
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getDates() {
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
	/*public Fournisseur getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}*/
	 
	public double getRetenue() {
		return retenue;
	}
	 
	public void setRetenue(double retenue) {
		this.retenue = retenue;
	}
	public String getRetenues() {
		DecimalFormat df=new DecimalFormat("0.000");
		if(retenue!=0) retenues=df.format(retenue);
		else retenues=null;
		return retenues;
	}
	public void setRetenues(String retenues) {
		this.retenues = retenues;
	}
	public double getMontantbrut() {
		return montantbrut;
	}
	public void setMontantbrut(double montantbrut) {
		this.montantbrut = montantbrut;
	}
	public String getMontantbruts() {
		DecimalFormat df=new DecimalFormat("0.000");
		if(montantbrut!=0)montantbruts=df.format(montantbrut);
		else montantbruts=null;
		return montantbruts;
	}
	public void setMontantbruts(String montantbruts) {
		this.montantbruts = montantbruts;
	}
	public String getMontants() {
		DecimalFormat df=new DecimalFormat("0.000");
		if(montant!=0)montants=df.format(montant);
		else montants=null;
		return montants;
	}
	public void setMontants(String montants) {
		this.montants = montants;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	
	
}
