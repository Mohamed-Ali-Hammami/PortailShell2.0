package com.tn.shell.model.gestat;

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
@Table(name = "Ligneindex")
public class Ligneindex {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private double quantite;	 
	private Date date;

	private String dates;
	private double indexfermuture;
	private double indexouverture;
	private double mantant;
	@Transient
	private String quantites;
	@Transient
	private String mantants;
	@Transient
    private String indexfermutures;
	@Transient
	private String indexouvertures;
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF;
	
	@Enumerated(EnumType.STRING)
	private Poste poste;
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "pompeid")
	private Pompe pompe;
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "caisseid")
	private Caisse caisse;
	
	private double prixachat; //=this.pompe.getArticlecarburant().getAchat();

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

	public double getIndexfermuture() {
		return indexfermuture;
	}

	public void setIndexfermuture(double indexfermuture) {
		this.indexfermuture = indexfermuture;
	}

	public double getIndexouverture() {
		return indexouverture;
	}

	public void setIndexouverture(double indexouverture) {
		this.indexouverture = indexouverture;
	}

	 

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Poste getPoste() {
		return poste;
	}

	public void setPoste(Poste poste) {
		this.poste = poste;
	}

	public Pompe getPompe() {
		return pompe;
	}

	public void setPompe(Pompe pompe) {
		this.pompe = pompe;
	}

	public Caisse getCaisse() {
		return caisse;
	}

	public void setCaisse(Caisse caisse) {
		this.caisse = caisse;
	}

	public double getMantant() {
		return mantant;
	}

	public void setMantant(double mantant) {
		this.mantant = mantant;
	}

	public String getMantants() {
		DecimalFormat df=new DecimalFormat("#,###.000");
		if(mantant>=1) {
			df=new DecimalFormat("#,###.000");
		mantants=df.format(mantant);
		}
		else if(mantant<1) {
			df=new DecimalFormat("0.000");
			mantants=df.format(mantant);
		}
		else mantants="";
		return mantants;
	}

	public void setMantants(String mantants) {
		this.mantants = mantants;
	}

	public String getIndexfermutures() {
		DecimalFormat df=new DecimalFormat("#,###.000");
		if(indexfermuture>0)
		indexfermutures=df.format(indexfermuture);
		else indexfermutures="";
		return indexfermutures;
	}

	public void setIndexfermutures(String indexfermutures) {
		this.indexfermutures = indexfermutures;
	}

	public String getIndexouvertures() {
		DecimalFormat df=new DecimalFormat("#,###.000");
		if(indexouverture>0)
		indexouvertures=df.format(indexouverture);
		else indexouvertures="";
		return indexouvertures;
	}

	public void setIndexouvertures(String indexouvertures) {
		this.indexouvertures = indexouvertures;
	}

	public String getQuantites() {
		DecimalFormat df=new DecimalFormat("#,###.000");
		if(quantite>0)
		quantites=df.format(quantite);
		else quantites="";
		return quantites;
	}

	public void setQuantites(String quantites) {
		this.quantites = quantites;
	}

	public double getPrixachat() {
		return prixachat;
	}

	public void setPrixachat(double prixachat) {
		this.prixachat = prixachat;
	}
	
	
}
