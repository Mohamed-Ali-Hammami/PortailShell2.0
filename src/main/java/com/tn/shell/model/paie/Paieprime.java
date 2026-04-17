package com.tn.shell.model.paie;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "Paieprime")
public class Paieprime {
	
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
	@NotFound(action = NotFoundAction.IGNORE)
	private Employee employee;
	@Embedded
	private Formule_Paie formulaire_Paie;
	 
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "noteid")
	@NotFound(action = NotFoundAction.IGNORE)
    private Note note;
	

	 @Transient
	private List<Lignepaiegestionprime> listGestion;	 
	
	
	  @OneToMany( targetEntity=Lignepaiegestionprime.class,mappedBy="paieprime" ,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Lignepaiegestionprime> listlignepaiegestions; 
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
			m = "Séptembre";
		else if (moi == 10)
			m = "Octobre";
		else if (moi == 11)
			m = "Novembre";
		else if (moi == 12)
			m = "Décembre";
		return m;
	}


	public Note getNote() {
		return note;
	}


	public void setNote(Note note) {
		this.note = note;
	}


	public List<Lignepaiegestionprime> getListGestion() {
		return listGestion;
	}


	public void setListGestion(List<Lignepaiegestionprime> listGestion) {
		this.listGestion = listGestion;
	}


 	public List<Lignepaiegestionprime> getListlignepaiegestions() {
		return listlignepaiegestions;
	}


	public void setListlignepaiegestions(List<Lignepaiegestionprime> listlignepaiegestions) {
		this.listlignepaiegestions = listlignepaiegestions;
	}

  
	
	
	
}
