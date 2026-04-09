package com.tn.shell.model.lavage;

import java.math.BigDecimal;

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

import com.tn.shell.model.shop.Produit;
import com.tn.shell.model.shop.Statut;

@Entity
@Table(name = "Affectationhuile")
public class AffectationHuile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
 
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "produitid")
	private Produit produit;
	@Enumerated(EnumType.STRING)
	private Statut statut = Statut.ACTIF;
	private BigDecimal metrage;
	private BigDecimal nbvidange;
 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	 
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	public BigDecimal getMetrage() {
		return metrage;
	}
	public void setMetrage(BigDecimal kilometrages) {
		metrage = kilometrages;
	}
	public BigDecimal getNbvidange() {
		return nbvidange;
	}
	public void setNbvidange(BigDecimal nbvidange) {
		this.nbvidange = nbvidange;
	}
	  
	
	
	 
}
