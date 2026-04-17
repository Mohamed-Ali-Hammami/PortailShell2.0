package com.tn.shell.model.gestat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
@Table(name = "carteclient")
public class Carteclient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private boolean affectee;
	private String chauffeur;
	private Date dateaffectation;
	private String vehicule;
	private double plafond;

	@Enumerated(EnumType.STRING)
	private Statut statut = Statut.ACTIF;

	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "clientid")
	private Clientgestat client;

	@Transient
	private String plafonds;

	@Transient
	private String dateaffectations;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isAffectee() {
		return affectee;
	}

	public void setAffectee(boolean affectee) {
		this.affectee = affectee;
	}

	public String getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(String chauffeur) {
		this.chauffeur = chauffeur;
	}

	public Date getDateaffectation() {
		return dateaffectation;
	}

	public void setDateaffectation(Date dateaffectation) {
		this.dateaffectation = dateaffectation;
	}

	public String getVehicule() {
		return vehicule;
	}

	public void setVehicule(String vehicule) {
		this.vehicule = vehicule;
	}

	public double getPlafond() {
		return plafond;
	}

	public void setPlafond(double plafond) {
		this.plafond = plafond;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Clientgestat getClient() {
		return client;
	}

	public void setClient(Clientgestat client) {
		this.client = client;
	}

	public String getPlafonds() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		plafonds = df.format(plafond);
		return plafonds;
	}

	public void setPlafonds(String plafonds) {
		this.plafonds = plafonds;
	}

	public String getDateaffectations() {
		if (dateaffectation == null) {
			return "";
		}
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dateaffectations = s.format(dateaffectation);
		return dateaffectations;
	}

	public void setDateaffectations(String dateaffectations) {
		this.dateaffectations = dateaffectations;
	}
}
