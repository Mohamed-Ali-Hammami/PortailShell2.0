package com.tn.shell.model.transport;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "facturepassager")
public class Facturepassager {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	 
	private Integer numero;
	  
	private Date date=null;
	 private String codes;
	 private Integer code;
	 
	@Transient
	private String dates="";	
	private String sommes;
 
	private double totalht;
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "vheculeid")
	private Vhecule vhecule;
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "chauffeurid")
	private Chauffeur chauffeur;
	 
	private String vhecules;
	private double timbres;
	private double totalttc;
   
	@Transient
	private List<Lignecommandepass> listelc;
	@Transient
	private String totalhts;
	 
	private double totaltva;
	@Transient
	private String totaltvas;	 
	@Transient
	private String totalttcs;
	
	@Transient
	private String timbress;
	 
	@Enumerated(EnumType.STRING)
	private Status status;
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF;
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "clientid")
	private Client client;	 
	// lien one to many avec lignecommandes
		@OneToMany(mappedBy = "facturepassager", cascade = { CascadeType.MERGE,
				CascadeType.REMOVE, CascadeType.REFRESH })
		private List<Lignecommandepass> lignecommandes;
 	 
	private String banque;
	private String numerocheck;
 
	 


	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	 

	public double getTotalht() {
		return totalht;
	}

	public void setTotalht(double totalht) {
		this.totalht = totalht;
	}

	public double getTotaltva() {
		return totaltva;
	}

	public void setTotaltva(double totaltva) {
		this.totaltva = totaltva;
	}

	public double getTimbres() {
		 
		return timbres;
	}

	public void setTimbres(double timbres) {
		this.timbres = timbres;
	}

	public double getTotalttc() {

		return totalttc;
	}

	public void setTotalttc(double totalttc) {
		this.totalttc = totalttc;
	}

 
	 

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getDates() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		if(date!=null)return s.format(date);
		else return ""+date;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	   

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public String getSommes() {
		
		return sommes;
	}

	public void setSommes(String sommes) {
		this.sommes = sommes;
	}

	 

	public String getTotaltvas() {
		DecimalFormat df=new DecimalFormat("0.000");
		totaltvas=df.format(totaltva);
		return totaltvas;
	}

	public void setTotaltvas(String totaltvas) {
		this.totaltvas = totaltvas;
	}
	

	public String getTimbress() {
		DecimalFormat df=new DecimalFormat("0.000");
		timbress=df.format(timbres);
		return timbress;
	}

	public void setTimbress(String timbress) {
		this.timbress = timbress;
	}

	public String getTotalttcs() {
		DecimalFormat df=new DecimalFormat("0.000");
		totalttcs=df.format(totalttc);
		return totalttcs;
	}

	public void setTotalttcs(String totalttcs) {
		this.totalttcs = totalttcs;
	}

	  
	public String getTotalhts() {
		DecimalFormat df=new DecimalFormat("0.000");
		totalhts=df.format(totalht);
		return totalhts;
	}

	public void setTotalhts(String totalhts) {
		this.totalhts = totalhts;
	}

	 
	 

	public List<Lignecommandepass> getListelc() {
		return listelc;
	}

	public void setListelc(List<Lignecommandepass> listelc) {
		this.listelc = listelc;
	}

	public String getBanque() {
		return banque;
	}

	public void setBanque(String banque) {
		this.banque = banque;
	}

	public String getNumerocheck() {
		return numerocheck;
	}

	public void setNumerocheck(String numerocheck) {
		this.numerocheck = numerocheck;
	}

	public String getCodes() {
		return codes;
	}

	public void setCodes(String codes) {
		this.codes = codes;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Vhecule getVhecule() {
		return vhecule;
	}

	public void setVhecule(Vhecule vhecule) {
		this.vhecule = vhecule;
	}

	public Chauffeur getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(Chauffeur chauffeur) {
		this.chauffeur = chauffeur;
	}

	 

	public String getVhecules() {
		return vhecules;
	}

	public void setVhecules(String vhecules) {
		this.vhecules = vhecules;
	}

	public List<Lignecommandepass> getLignecommandes() {
		return lignecommandes;
	}

	public void setLignecommandes(List<Lignecommandepass> lignecommandes) {
		this.lignecommandes = lignecommandes;
	}

	 

	 
 

}
