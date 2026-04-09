package com.tn.shell.model.shop;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tn.shell.model.gestat.TransactionCredit;

@Entity
@Table(name = "Ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Date date;
	@Transient
	 Calendar now = Calendar.getInstance();
	private String heure=(now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));
 
	private String dates;
	  @Transient
    private String TotalVente;
	private double total_vente;
	private double total_recu;
	private double total_rendu;
	@Transient
	private String total_ventes;
	@Transient
	private String total_recus;
	@Transient
	private String total_rendus;
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF;
	@Enumerated(EnumType.STRING)
	private  TypeGeneration gen;
	@OneToMany(mappedBy = "ticket", cascade = { CascadeType.MERGE,
			CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Lignevente > ventes;
	@OneToMany(mappedBy = "ticket", cascade = { CascadeType.MERGE,
			CascadeType.REMOVE, CascadeType.REFRESH })
	private List<TransactionCredit> transactionclients;
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
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		if(date != null)
			dates=s.format(date);
		else dates="";
			 
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	 

	public double getTotal_vente() {
		return total_vente;
	}

	public void setTotal_vente(double total_vente) {
		this.total_vente = total_vente;
	}

	public double getTotal_recu() {
		return total_recu;
	}

	public void setTotal_recu(double total_recu) {
		this.total_recu = total_recu;
	}

	public double getTotal_rendu() {
		return total_rendu;
	}

	public void setTotal_rendu(double total_rendu) {
		this.total_rendu = total_rendu;
	}

	public String getTotal_ventes() {
		DecimalFormat df=new DecimalFormat("0.000");
		total_ventes=df.format(total_vente);
		return total_ventes;
	}

	public void setTotal_ventes(String total_ventes) {
		this.total_ventes = total_ventes;
	}

	public String getTotal_recus() {
		DecimalFormat df=new DecimalFormat("0.000");
		total_recus=df.format(total_recu);
		return total_recus;
	}

	public void setTotal_recus(String total_recus) {
		this.total_recus = total_recus;
	}

	public String getTotal_rendus() {
		DecimalFormat df=new DecimalFormat("0.000");
		total_rendus=df.format(total_rendu);
		return total_rendus;
	}

	public void setTotal_rendus(String total_rendus) {
		
		this.total_rendus = total_rendus;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	 

	public List<Lignevente> getVentes() {
		return ventes;
	}

	public void setVentes(List<Lignevente> ventes) {
		this.ventes = ventes;
	}

	 
	public String getHeure() {
		return heure;
	}

	public void setHeure(String heure) {
		this.heure = heure;
	}

	public TypeGeneration getGen() {
		return gen;
	}

	public void setGen(TypeGeneration gen) {
		this.gen = gen;
	}

	public List<TransactionCredit> getTransactionclients() {
		return transactionclients;
	}

	public void setTransactionclients(List<TransactionCredit> transactionclients) {
		this.transactionclients = transactionclients;
	}

	public String getTotalVente() {
		return TotalVente;
	}

	public void setTotalVente(String totalVente) {
		TotalVente = totalVente;
	}

	 
	
	
	
}
