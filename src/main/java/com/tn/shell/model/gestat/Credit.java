package com.tn.shell.model.gestat;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Creditclient")
public class Credit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;	 
	
	private double montant;
	@Transient
	private String montants;
	private Date date;
    private String tels;
	private String dates;
	@Enumerated(EnumType.STRING)
	private Poste poste;	
	
	private Integer numbon;	
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "clientid")
	private Clientgestat client;
	
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "caisseid")
	private Caisse caisse;
	
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "transactionid")
	private TransactionCredit transaction;
	
	@OneToMany(mappedBy = "credit", cascade = { CascadeType.MERGE,
			CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Creditpassation> creditpassationclients;
	
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF;


	
	
	
	
	 
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
		DecimalFormat df=new DecimalFormat("#,###.000");
		if(montant==0)
			montants="";
		else
		montants=df.format(montant);
		return montants;
	}
	public void setMontants(String montants) {
		this.montants = montants;
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
	
	public Clientgestat getClient() {
		return client;
	}
	public void setClient(Clientgestat client) {
		this.client = client;
	}
	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	 
	public Integer getNumbon() {
		return numbon;
	}
	public void setNumbon(Integer numbon) {
		this.numbon = numbon;
	}
	public Poste getPoste() {
		return poste;
	}
	public void setPoste(Poste poste) {
		this.poste = poste;
	}
	public Caisse getCaisse() {
		return caisse;
	}
	public void setCaisse(Caisse caisse) {
		this.caisse = caisse;
	}
	public String getTels() {
		return tels;
	}
	public void setTels(String tels) {
		this.tels = tels;
	}
	public List<Creditpassation> getCreditpassationclients() {
		return creditpassationclients;
	}
	public void setCreditpassationclients(List<Creditpassation> creditpassationclients) {
		this.creditpassationclients = creditpassationclients;
	}
	public TransactionCredit getTransaction() {
		return transaction;
	}
	public void setTransaction(TransactionCredit transaction) {
		this.transaction = transaction;
	}
	
	
	 
	
}
