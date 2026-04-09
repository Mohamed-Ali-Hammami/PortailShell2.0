package com.tn.shell.model.shop;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@Table(name = "Factureachat")
public class Factureachat {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;	
	private String code;	
	private Date date;
	 
	private String banque;
	private String numerocheck;
	@Enumerated(EnumType.STRING)
	private TypePayement typepayement;
	private Date datepayement=null;
	@Transient
	private String datepayements;
	@Transient
	private String dates;
	 
	private double totaltva;
	private double totalht;
	private double totalttc;
	private Date echeance;
	 
	@Transient
	private String totaltvas;
	@Transient
	private String totalhts;
	@Transient
	private List<Achat> listarticle;
	@Transient
	private String totalttcs;
 
	
	@Enumerated(EnumType.STRING)
    private Status status ;
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF;
	
 
	
	
	// lien one to many avec achats
	@OneToMany(mappedBy = "factureachat", cascade = { CascadeType.MERGE,
			CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Achat> achats;
 
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	 
	 
	 
 
	public List<Achat> getAchats() {
		return achats;
	}
	public void setAchats(List<Achat> achats) {
		this.achats = achats;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDates() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates=s.format(date);
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public double getTotaltva() {
		return totaltva;
	}
	public void setTotaltva(double totaltva) {
		this.totaltva = totaltva;
	}
	public double getTotalht() {
		return totalht;
	}
	public void setTotalht(double totalht) {
		this.totalht = totalht;
	}
	public String getTotaltvas() {
		DecimalFormat df=new DecimalFormat("0.000");
		totaltvas=df.format(totaltva);
		return totaltvas;
	}
	public void setTotaltvas(String totaltvas) {
		this.totaltvas = totaltvas;
	}
	public String getTotalhts() {
		DecimalFormat df=new DecimalFormat("0.000");
		totalhts=df.format(totalht);
		return totalhts;
	}
	public void setTotalhts(String totalhts) {
		this.totalhts = totalhts;
	}
	 
	 
	public List<Achat> getListarticle() {
		return listarticle;
	}
	public void setListarticle(List<Achat> listarticle) {
		this.listarticle =new ArrayList<Achat>();
		this.listarticle = listarticle;
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
	public TypePayement getTypepayement() {
		return typepayement;
	}
	public void setTypepayement(TypePayement typepayement) {
		this.typepayement = typepayement;
	}
	public Date getDatepayement() {
		
		return datepayement;
	}
	public void setDatepayement(Date datepayement) {
		this.datepayement = datepayement;
	}
	public String getDatepayements() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		if(datepayement != null)
			datepayements=s.format(datepayement);
		else datepayements="";
			return datepayements;
		 
	}
	public void setDatepayements(String datepayements) {
		this.datepayements = datepayements;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getTotalttc() {
		return totalttc;
	}
	public void setTotalttc(double totalttc) {
		this.totalttc = totalttc;
	}
	public String getTotalttcs() {
		DecimalFormat df=new DecimalFormat("0.000");
		totalttcs=df.format(totalttc);
		return totalttcs;
	}
	public void setTotalttcs(String totalttcs) {
		this.totalttcs = totalttcs;
	}
	public Date getEcheance() {
		return echeance;
	}
	public void setEcheance(Date echeance) {
		this.echeance = echeance;
	}
	 
	 	 
	
}
