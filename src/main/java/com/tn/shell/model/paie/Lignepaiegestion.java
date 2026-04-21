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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "lignepaiegestion")
public class Lignepaiegestion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF; 
	 private double valeurdeprime;
	  
		private double valeurdeprimeafficher;
		@Transient
		private String Valeurdeprimeaffichers;
		
		
		@Transient
		private String totalaffichers;
     @ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "paieid")
	@NotFound(action = NotFoundAction.IGNORE)
	private Paie paie; 
 
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "lignegestionid")
	private Lignegestion lignegestion; 
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	 
	 public Paie getPaie() {
		return paie;
	}
	public void setPaie(Paie paie) {
		this.paie = paie;
	}
	
	 
	public Lignegestion getLignegestion() {
		return lignegestion;
	}
	public void setLignegestion(Lignegestion lignegestion) {
		this.lignegestion = lignegestion;
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
	
	
	public String getTotalaffichers() {
		return totalaffichers;
	}
	public void setTotalaffichers(String totalaffichers) {
		this.totalaffichers = totalaffichers;
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
