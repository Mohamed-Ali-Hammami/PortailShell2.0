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
@Table(name = "Gestion")
 public class Gestion {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
		private String libelle;
		@Enumerated(EnumType.STRING)
		private Statut statut= Statut.ACTIF;
		
		
		
		@Enumerated(EnumType.STRING)
		private Signe signe;
	    
		private double valeurdeprime;
		@Transient
		private double valeurdeprimeafficher;
		@Transient
		private String  valeurdeprimeaffichers;
		@Transient
		private double sommeprime=0;
		@Transient
		private String sommeprimes;
		
 @OneToMany(mappedBy = "gestion", cascade = { CascadeType.MERGE,
				CascadeType.REMOVE, CascadeType.REFRESH })
		private List<Lignegestion> lignegestions; 
		
		
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

		public Signe getSigne() {
			return signe;
		}

		public void setSigne(Signe signe) {
			this.signe = signe;
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
			valeurdeprimeaffichers=df.format(valeurdeprimeafficher);
			return valeurdeprimeaffichers;
		}

		public void setValeurdeprimeaffichers(String valeurdeprimeaffichers) {
			this.valeurdeprimeaffichers = valeurdeprimeaffichers;
		}

		public double getSommeprime() {
			return sommeprime;
		}

		public void setSommeprime(double sommeprime) {
			this.sommeprime = sommeprime;
		}

		public String getSommeprimes() {
			DecimalFormat df=new DecimalFormat("0.000");
			sommeprimes=df.format(sommeprime);
			return sommeprimes;
		}

		public void setSommeprimes(String sommeprimes) {
			this.sommeprimes = sommeprimes;
		}

	 public List<Lignegestion> getLignegestions() {
			return lignegestions;
		}

		public void setLignegestions(List<Lignegestion> lignegestions) {
			this.lignegestions = lignegestions;
		}
		 
	
	

}
