package com.tn.shell.model.transport;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "bonlivraison")
public class Bonlivraison {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer numero;
	private String codes;
	private Integer code;
	private Date date;
	private boolean affectee;
	@Transient
	Calendar now = Calendar.getInstance();
	private String heure = (now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));
//=date.getHours()+":"+date.getMinutes()
	private double totaltva;
	private double transport;
	@Transient
	private String totaltvas;
	private String dates;
	private double montant;
	@Transient
	private String montants;
	@Transient
	private List<Lignecommande> listeligne;

	@Enumerated(EnumType.STRING)
	private Statut statut = Statut.ACTIF;

	@Enumerated(EnumType.STRING)
	private Status status = Status.NonFacturee;

	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "chauffeurid")
	private Chauffeur chauffeur;

	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "vheculeid")
	private Vhecule vhecule;

	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "clientid")
	private Client client;

	@Transient
	private List<Lignecommande> listelc;

	// lien one to many avec lignecommandes
	@OneToMany(mappedBy = "bl", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Lignecommande> lignecommandes;

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setMontants(String montants) {
		this.montants = montants;
	}

	public List<Lignecommande> getListeligne() {
		return listeligne;
	}

	public void setListeligne(List<Lignecommande> listeligne) {
		this.listeligne = new ArrayList<Lignecommande>();
		this.listeligne = listeligne;

	}

	public double getTotaltva() {
		return totaltva;
	}

	public void setTotaltva(double totaltva) {
		this.totaltva = totaltva;
	}

	public String getTotaltvas() {
		DecimalFormat df = new DecimalFormat("0.000");
		totaltvas = df.format(totaltva);
		return totaltvas;
	}

	public void setTotaltvas(String totaltvas) {
		this.totaltvas = totaltvas;
	}

	public Chauffeur getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(Chauffeur chauffeur) {
		this.chauffeur = chauffeur;
	}

	public Vhecule getVhecule() {
		return vhecule;
	}

	public void setVhecule(Vhecule vhecule) {
		this.vhecule = vhecule;
	}

	public String getMontants() {
		return montants;
	}

	public String getHeure() {
		Calendar now = Calendar.getInstance();

		return heure;
	}

	public void setHeure(String heure) {
		this.heure = heure;
	}

	public double getTransport() {
		return transport;
	}

	public void setTransport(double transport) {
		this.transport = transport;
	}

	public Calendar getNow() {
		return now;
	}

	public void setNow(Calendar now) {
		this.now = now;
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

	@Transient
	private double gasoil;
	@Transient
	private double petrole;
	@Transient
	private double ssp;
	@Transient
	private double g50;

	public double getGasoil() {
		return gasoil;
	}

	public void setGasoil(double gasoil) {
		this.gasoil = gasoil;
	}

	public double getPetrole() {
		return petrole;
	}

	public void setPetrole(double petrole) {
		this.petrole = petrole;
	}

	public double getSsp() {
		return ssp;
	}

	public void setSsp(double ssp) {
		this.ssp = ssp;
	}

	public double getG50() {
		return g50;
	}

	public void setG50(double g50) {
		this.g50 = g50;
	}

	public List<Lignecommande> getListelc() {
		return listelc;
	}

	public void setListelc(List<Lignecommande> listelc) {
		this.listelc = listelc;
	}

	public List<Lignecommande> getLignecommandes() {
		return lignecommandes;
	}

	public void setLignecommandes(List<Lignecommande> lignecommandes) {
		this.lignecommandes = lignecommandes;
	}

	public boolean isAffectee() {
		return affectee;
	}

	public void setAffectee(boolean affectee) {
		this.affectee = affectee;
	}

}
