package com.tn.shell.model.paie;

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
@Table(name = "Pointage")
public class Pointage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private double nb_jour;
	private Integer annee;
	private Integer mois;
	@Transient
	private double nb_absence;
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "employeeid")
	private Employee employee;
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF;

	/*
	 * Getter
	 * And
	 * Setter
	 * */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getNb_jour() {
		return nb_jour;
	}

	public void setNb_jour(double nb_jour) {
		this.nb_jour = nb_jour;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	public Integer getMois() {
		return mois;
	}

	public void setMois(Integer mois) {
		this.mois = mois;
	}

	public double getNb_absence() {
		return nb_absence;
	}

	public void setNb_absence(double nb_absence) {
		this.nb_absence = nb_absence;
	}
	
	
	

}
