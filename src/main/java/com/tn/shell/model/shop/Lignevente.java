package com.tn.shell.model.shop;

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
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tn.shell.model.paie.Employee;

@Entity
@Table(name = "Lignevente")
public class Lignevente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private double quantite;
	private Date date;
	private double montantht;
	private double totaltva;
	private double totalttc;
	private double prixachat;
	@Transient
	List<Rendement > listrendement;
	@Transient
	private String prixttc;
	@Transient
	private String totalttcs;
	@Transient
	private String quantites;
	@Transient
	private double quantite2;
	private String dates;
	@Transient
	private Employee employee;
	@Transient
	private String libelle;
	private Integer tva;
	private double prix;
	@Transient
	private String prixs;
	private double mantantht;
	@Transient
	private String mantanthts;
	@Enumerated(EnumType.STRING)
	private Statut statut = Statut.ACTIF;

	@Enumerated(EnumType.STRING)
	private Poste poste;

	@Enumerated(EnumType.STRING)
	private TypeGeneration generation;

	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "produitsid")
	private Produit produit;

	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "ticketid")
	private Ticket ticket;

	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "venteid")
	private Vente vente;

	private double tauxtva;

	public double getTauxtva() {
		return tauxtva;
	}

	public void setTauxtva(double tauxtva) {
		this.tauxtva = tauxtva;
	}

	public Lignevente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getQuantite() {
		return quantite;
	}

	public void setQuantite(double quantite) {
		this.quantite = quantite;
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
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		this.dates = dates;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Vente getVente() {
		return vente;
	}

	public void setVente(Vente vente) {
		this.vente = vente;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Integer getTva() {
		return tva;
	}

	public void setTva(Integer tva) {
		this.tva = tva;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Poste getPoste() {
		return poste;
	}

	public void setPoste(Poste poste) {
		this.poste = poste;
	}

	public double getMontantht() {
		return montantht;
	}

	public void setMontantht(double montantht) {
		this.montantht = montantht;
	}

	public double getTotaltva() {
		return totaltva;
	}

	public void setTotaltva(double totaltva) {
		this.totaltva = totaltva;
	}

	public double getTotalttc() {
		return totalttc;
	}

	public void setTotalttc(double totalttc) {
		this.totalttc = totalttc;
	}

	public TypeGeneration getGeneration() {
		return generation;
	}

	public void setGeneration(TypeGeneration generation) {
		this.generation = generation;
	}

	public double getMantantht() {
		return mantantht;
	}

	public void setMantantht(double mantantht) {
		this.mantantht = mantantht;
	}

	public String getMantanthts() {
		DecimalFormat df = new DecimalFormat("0.000");
		mantanthts = df.format(mantantht);
		return mantanthts;
	}

	public String getQuantites() {
		DecimalFormat df = new DecimalFormat("0");
		if (quantite2 != 0)
			return df.format(quantite2);

		return quantites;
	}

	public void setQuantites(String quantites) {
		this.quantites = quantites;
	}

	public String getPrixs() {
		DecimalFormat df = new DecimalFormat("0.000");
		prixs = df.format(prix);
		return prixs;
	}

	public void setPrixs(String prixs) {
		this.prixs = prixs;
	}

	public void setTotalttcs(String totalttcs) {
		this.totalttcs = totalttcs;
	}

	public String getTotalttcs() {
		DecimalFormat df = new DecimalFormat("0.000");
		totalttcs = df.format(totalttc);
		return totalttcs;
	}

	public String getPrixttc() {
		DecimalFormat df = new DecimalFormat("0.000");
		prixttc = df.format(totalttc / quantite);
		return prixttc;
	}

	public void setPrixttc(String prixttc) {
		this.prixttc = prixttc;
	}

	public void setMantanthts(String mantanthts) {
		this.mantanthts = mantanthts;
	}

	public double getPrixachat() {
		return prixachat;
	}

	public void setPrixachat(double prixachat) {
		this.prixachat = prixachat;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getQuantite2() {
		return quantite2;
	}

	public void setQuantite2(double quantite2) {
		this.quantite2 = quantite2;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Rendement> getListrendement() {
		return listrendement;
	}

	public void setListrendement(List<Rendement> listrendement) {
		this.listrendement = listrendement;
	}
	
	

}
