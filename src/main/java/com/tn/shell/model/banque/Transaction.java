package com.tn.shell.model.banque;

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

import com.tn.shell.model.gestat.Cheque;
import com.tn.shell.model.gestat.Chequereglement;
import com.tn.shell.model.gestat.Statut;

@Entity
@Table(name="Transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id; 
	
	private Date date;
	private String dates;
	private String description;
	private String reference;
	private Date dateOperation;
	@Enumerated(EnumType.STRING)
	private Statut statut = Statut.ACTIF;
	@Enumerated(EnumType.STRING)
	private TypeTransaction typetransaction ;
	@Enumerated(EnumType.STRING)
	private Reglement reglement;
	@Enumerated(EnumType.STRING)
	private Enumcheque etatcheque ;
	private BigDecimal montant;
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "compteid")
	private Compte compte;
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "chequereglementid")
	private Chequereglement  chequereglement;
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "chequeid")
	private Cheque cheque;
	
	
	
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
	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	public TypeTransaction getTypetransaction() {
		return typetransaction;
	}
	public void setTypetransaction(TypeTransaction typetransaction) {
		this.typetransaction = typetransaction;
	}
	public Reglement getReglement() {
		return reglement;
	}
	public void setReglement(Reglement reglement) {
		this.reglement = reglement;
	}
	public BigDecimal getMontant() {
		return montant;
	}
	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	public Chequereglement getChequereglement() {
		return chequereglement;
	}
	public void setChequereglement(Chequereglement chequereglement) {
		this.chequereglement = chequereglement;
	}
	public Cheque getCheque() {
		return cheque;
	}
	public void setCheque(Cheque cheque) {
		this.cheque = cheque;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Enumcheque getEtatcheque() {
		return etatcheque;
	}
	public void setEtatcheque(Enumcheque etatcheque) {
		this.etatcheque = etatcheque;
	}
	public Date getDateOperation() {
		return dateOperation;
	}
	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

	 
	
	
	
	
	
}
