package com.tn.shell.model.gestat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;

import com.tn.shell.model.shop.Produit;

 
public class Ligneventecredit {
	/*@Id
	@GeneratedValue(strategy = GenerationType.AUTO)*/
	private Integer id;
	private Integer tva;
	private double pu;
	private Date date;
	@Enumerated(EnumType.STRING)
	private Statut statut = Statut.ACTIF;
	private String dates;
	private double quantite;
	@Transient
	private String quantites;
	private double montant;
	@Transient
	private String montants;
	/*@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "creditid")
	private Credit credit;  
     

	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "produitid")
	private Produit produit;*/
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getMontants() {
		DecimalFormat df=new DecimalFormat("0.000");
		montants=df.format(montant);
		return montants;
	}
	public void setMontants(String montants) {
		this.montants = montants;
	}

	public String getDates() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
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

	public double getQuantite() {
		return quantite;
	}

	public void setQuantite(double quqntite) {
		this.quantite = quqntite;
	}
	@Transient
private String pus;
	
	public Integer getTva() {
		return tva;
	}

	public void setTva(Integer tva) {
		this.tva = tva;
	}

	public double getPu() {
		return pu;
	}

	public void setPu(double pu) {
		this.pu = pu;
	}

	public String getPus() {
		DecimalFormat df=new DecimalFormat("0.000");
		pus=df.format(pu);
		return pus;
	}

	public void setPus(String pus) {
		this.pus = pus;
	}

	public String getQuantites() {
		DecimalFormat df=new DecimalFormat("0.000");
		quantites=df.format(quantite);
		return quantites;
	}

	public void setQuantites(String quantites) {
		this.quantites = quantites;
	}

	/*public Credit getCredit() {
		return credit;
	}

	public void setCredit(Credit credit) {
		this.credit = credit;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	 */
	


	 
 

}
