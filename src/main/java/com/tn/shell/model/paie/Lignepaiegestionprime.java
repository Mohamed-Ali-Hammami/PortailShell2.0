package com.tn.shell.model.paie;

import java.text.DecimalFormat;

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
@Table(name = "Lignepaiegestionprime")
public class Lignepaiegestionprime {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF; 
	 private double valeurdeprime;
	  
		private double valeurdeprimeafficher;
		@Transient
		private String Valeurdeprimeaffichers;
		
		
  
     @ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "paieprimeid")
	private Paieprime paieprime; 
 
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "lignegestionid")
	private Lignegestion lignegestions; 
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	 
	 
	public Paieprime getPaieprime() {
		return paieprime;
	}
	public void setPaieprime(Paieprime paieprime) {
		this.paieprime = paieprime;
	}
	public Lignegestion getLignegestions() {
		return lignegestions;
	}
	public void setLignegestions(Lignegestion lignegestion) {
		this.lignegestions = lignegestion;
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
	 
	public double getValeurdeprimeafficher() {
		return valeurdeprimeafficher;
	}
	public void setValeurdeprimeafficher(double valeurdeprimeafficher) {
		this.valeurdeprimeafficher = valeurdeprimeafficher;
	}
	public String getValeurdeprimeaffichers() {
		DecimalFormat df=new DecimalFormat("0.000");
		Valeurdeprimeaffichers=df.format(valeurdeprimeafficher);
		return Valeurdeprimeaffichers;
	}
	public void setValeurdeprimeaffichers(String valeurdeprimeaffichers) {
		Valeurdeprimeaffichers = valeurdeprimeaffichers;
	}
	 

}
