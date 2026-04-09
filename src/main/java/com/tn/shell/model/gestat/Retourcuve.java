package com.tn.shell.model.gestat;

import java.text.DecimalFormat;
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
@Table(name = "Retourcuve")
 
public class Retourcuve {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer quantite;
	
	@Enumerated(EnumType.STRING)
	private Statut statut = Statut.ACTIF;
	private double montant;
	@Transient
	private String montants;
	private Date date;
	private String dates;
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "pompeid")
	private Pompe pompe;
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "caisseid")
	private Caisse caisse;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuantite() {
		return quantite;
	}
	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getMontants() {
		DecimalFormat df=new DecimalFormat("#,###.000");
		montants=df.format(montant);
		return montants;
	}
	public void setMontants(String montants) {
		this.montants = montants;
	}
	 
	public Pompe getPompe() {
		return pompe;
	}
	public void setPompe(Pompe pompe) {
		this.pompe = pompe;
	}
	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	public Caisse getCaisse() {
		return caisse;
	}
	public void setCaisse(Caisse caisse) {
		this.caisse = caisse;
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
	
	
}
