package com.tn.shell.model.gestat;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tn.shell.model.shop.Ticket;
import com.tn.shell.model.transport.Facture;
@Entity
@Table(name = "Transactionrecdit")
public class TransactionCredit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	 
	private Integer id;
	@Transient
	private Integer id2;
	 
	@Transient
	private String requete;
private String time;
private String article;
private String pompe;
private double quantite;
@Transient
private double quantites;
@Transient
private String montants;
private double montant;
private String chauffeur;
@Transient
private Facture facture;
private String vhecule;
private String pistolet;
private String prix;
private String tel;
private double pris;
private double totalttac;
private boolean affecte=false;
private Date date;
private String dates;

@ManyToOne(cascade = { CascadeType.MERGE })
@JoinColumn(name = "clientid")
private Clientgestat client;


@OneToMany(mappedBy = "transaction", cascade = { CascadeType.MERGE,
		CascadeType.REMOVE, CascadeType.REFRESH })
private List<Credit> credits;

@ManyToOne(cascade = { CascadeType.MERGE })
@JoinColumn(name = "ticketid")
private Ticket ticket;



public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public String getArticle() {
	return article;
}
public void setArticle(String article) {
	this.article = article;
}
public String getPompe() {
	return pompe;
}
public void setPompe(String pompe) {
	this.pompe = pompe;
}
public double getQuantite() {
	return quantite;
}
public void setQuantite(double quantite) {
	this.quantite = quantite;
}
public double getMontant() {
	return montant;
}
public void setMontant(double montant) {
	this.montant = montant;
}
public String getPrix() {
	return prix;
}
public void setPrix(String prix) {
	this.prix = prix;
}
public String getPistolet() {
	return pistolet;
}
public void setPistolet(String pistolet) {
	this.pistolet = pistolet;
}
public boolean isAffecte() {
	return affecte;
}
public void setAffecte(boolean affecte) {
	this.affecte = affecte;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getDates() {
	return dates;
}
public void setDates(String dates) {
	this.dates = dates;
}
  
public double getPris() {
	return pris;
}
public void setPris(double pris) {
	this.pris = pris;
} 
public String getRequete() {
	return requete;
}
public void setRequete(String requete) {
	this.requete = requete;
}
public double getQuantites() {
	return quantites;
}
public void setQuantites(double quantites) {
	this.quantites = quantites;
}
public String getMontants() {
	DecimalFormat df = new DecimalFormat("0.000");
	montants=df.format(montant);
	return montants;
}
public void setMontants(String montants) {
	this.montants = montants;
}
public Clientgestat getClient() {
	return client;
}
public void setClient(Clientgestat client) {
	this.client = client;
}
public Ticket getTicket() {
	return ticket;
}
public void setTicket(Ticket ticket) {
	this.ticket = ticket;
}
public Integer getId2() {
	return id2;
}
public void setId2(Integer id2) {
	this.id2 = id2;
}
public String getChauffeur() {
	return chauffeur;
}
public void setChauffeur(String chauffeur) {
	this.chauffeur = chauffeur;
}
public String getVhecule() {
	return vhecule;
}
public void setVhecule(String vhecule) {
	this.vhecule = vhecule;
}
public List<Credit> getCredits() {
	return credits;
}
public void setCredits(List<Credit> credits) {
	this.credits = credits;
}
public double getTotalttac() {
	return totalttac;
}
public void setTotalttac(double totalttac) {
	this.totalttac = totalttac;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public Facture getFacture() {
	return facture;
}
public void setFacture(Facture facture) {
	this.facture = facture;
} 



   
}
