package com.tn.shell.model.gestat;

import java.math.BigDecimal;
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

@Entity
@Table(name = "DepenseCheque")
public class DepenseCheque {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private BigDecimal montant;
	private String libelle;
	private Date date;
	private String dates;
	private String banque;
	private String reference;
	private String typeDepense;
	@Enumerated(EnumType.STRING)
	private Statut statut = Statut.ACTIF;
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "familledepenseid")
	private Familledepensegestat familledepense;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getMontant() {
		return montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
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

	public String getBanque() {
		return banque;
	}

	public void setBanque(String banque) {
		this.banque = banque;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Familledepensegestat getFamilledepense() {
		return familledepense;
	}

	public void setFamilledepense(Familledepensegestat familledepense) {
		this.familledepense = familledepense;
	}

	public String getTypeDepense() {
		return typeDepense;
	}

	public void setTypeDepense(String typeDepense) {
		this.typeDepense = typeDepense;
	}

}
