package com.tn.shell.model.paie;

import java.text.DecimalFormat;

import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Lignegestion")
public class Lignegestion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Enumerated(EnumType.STRING)
	private Statut statut = Statut.ACTIF;

	@Enumerated(EnumType.STRING)
	private Nature nature;
	@Transient
	private double valeurdeprimeafficher;
	@Transient
	private String Valeurdeprimeaffichers;

	@Transient
	private double sommeprime = 0;
	@Transient
	private String sommeprimes;

	 @ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "gestionid")
	private Gestion gestion;

 
	 @ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "employeeid")
	private Employee employee; 

	 @OneToMany(targetEntity = Lignepaiegestion.class, mappedBy = "lignegestion", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Lignepaiegestion> listGestions; 
	 
	  @OneToMany(mappedBy = "lignegestions")
		private List<Lignepaiegestionprime> lignepaiegestionprime; 
 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	 public Gestion getGestion() {
		return gestion;
	}

	public void setGestion(Gestion gestion) {
		this.gestion = gestion;
	}
 public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	} 

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Nature getNature() {
		return nature;
	}

	public void setNature(Nature nature) {
		this.nature = nature;
	}

	public double getValeurdeprimeafficher() {
		return valeurdeprimeafficher;
	}

	public void setValeurdeprimeafficher(double valeurdeprimeafficher) {
		this.valeurdeprimeafficher = valeurdeprimeafficher;
	}

	public String getValeurdeprimeaffichers() {
		DecimalFormat df = new DecimalFormat("0.000");
		Valeurdeprimeaffichers = df.format(valeurdeprimeafficher);
		return Valeurdeprimeaffichers;
	}

	public void setValeurdeprimeaffichers(String valeurdeprimeaffichers) {
		Valeurdeprimeaffichers = valeurdeprimeaffichers;
	}

	public double getSommeprime() {
		return sommeprime;
	}

	public void setSommeprime(double sommeprime) {
		this.sommeprime = sommeprime;
	}

	public String getSommeprimes() {
		return sommeprimes;
	}

	public void setSommeprimes(String sommeprimes) {
		this.sommeprimes = sommeprimes;
	}

	 public List<Lignepaiegestion> getListGestions() {
		return listGestions;
	}

	public void setListGestions(List<Lignepaiegestion> listGestions) {
		this.listGestions = listGestions;
	}
	 
	public List<Lignepaiegestionprime> getLignepaiegestionprime() {
		return lignepaiegestionprime;
	}

	public void setLignepaiegestionprime(List<Lignepaiegestionprime> lignepaiegestionprime) {
		this.lignepaiegestionprime = lignepaiegestionprime;
	}
 
}
