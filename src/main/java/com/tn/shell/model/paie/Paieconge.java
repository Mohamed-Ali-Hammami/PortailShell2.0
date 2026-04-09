package com.tn.shell.model.paie;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
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
@Table(name = "Paieconge")
public class Paieconge {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private Integer id;
	private Integer annee;
	private Integer mois;
	
	@Transient
	private String moi;
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF;
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "employeeid")
	private Employee employee;
	@Embedded
	private Formule_Paie formulaire_Paie;
	@Transient
	private List<Lignegestion> listGestion;

	 @Transient
	private List<Lignepaiegestion> listPaieGestion;	
    
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


	public Formule_Paie getFormulaire_Paie() {
		return formulaire_Paie;
	}


	public void setFormulaire_Paie(Formule_Paie formulaire_Paie) {
		this.formulaire_Paie = formulaire_Paie;
	}


	public List<Lignegestion> getListGestion() {
		return listGestion;
	}


	public void setListGestion(List<Lignegestion> listGestion) {
		this.listGestion = listGestion;
	}


	public String getMoi() {
		return getMoisbyIntger(mois);
	}


	public void setMoi(String moi) {
		this.moi = moi;
	}
	
	private String getMoisbyIntger(Integer moi) {
		String m = "";
		if (moi == 1)
			m = "Janvier";
		else if (moi == 2)
			m = "Fevrier";
		else if (moi == 3)
			m = "Mars";
		else if (moi == 4)
			m = "Avril";
		else if (moi == 5)
			m = "Mai";
		else if (moi == 6)
			m = "Juin";
		else if (moi == 7)
			m = "Juillet";
		else if (moi == 8)
			m = "aout";
		else if (moi == 9)
			m = "Septembre";
		else if (moi == 10)
			m = "Octobre";
		else if (moi == 11)
			m = "Novembre";
		else if (moi == 12)
			m = "Decembre";
		return m;
	}


	public List<Lignepaiegestion> getListPaieGestion() {
		return listPaieGestion;
	}


	public void setListPaieGestion(List<Lignepaiegestion> listPaieGestion) {
		this.listPaieGestion = listPaieGestion;
	}


	 

	
	
	
}
