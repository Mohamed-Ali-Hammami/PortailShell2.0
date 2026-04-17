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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "Pointageconge")
public class Pointageconge {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private double nb_jour;
	private Integer annee;
	private Integer mois;
	private double avance=0;
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "employeeid")
	@NotFound(action = NotFoundAction.IGNORE)
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

	public double getAvance() {
		return avance;
	}

	public void setAvance(double avance) {
		this.avance = avance;
	}

	 
	
	
	

}
