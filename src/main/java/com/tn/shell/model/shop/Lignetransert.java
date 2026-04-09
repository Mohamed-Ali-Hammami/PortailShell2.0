package com.tn.shell.model.shop;

import java.text.DecimalFormat;
import java.util.Date;

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
@Table(name = "Lignetransert")
public class Lignetransert {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Integer id;
	private double quantite;	 
	private Date date;
    @Transient
    private String quantites;
	private String dates;
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF;
	 @Transient
	 private String libelle;
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "produitsid")
	private Produit produit;
    
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "transfertid")
	private Transfert transfert;
	
	
	
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

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Transfert getTransfert() {
		return transfert;
	}

	public void setTransfert(Transfert transfert) {
		this.transfert = transfert;
	}

	public String getQuantites() {
		 
			DecimalFormat df=new DecimalFormat("0");
			quantites=df.format(quantite);
			return quantites;
	 
	}

	public void setQuantites(String quantites) {
		this.quantites = quantites;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	 
}
