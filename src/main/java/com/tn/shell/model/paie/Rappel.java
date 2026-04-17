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
@Table(name = "Rappel")
public class Rappel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private Integer id;
	private Integer annee;
	 
	 
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF;
	 
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "employeeid")
	@NotFound(action = NotFoundAction.IGNORE)
	private Employee employee;
	@Embedded
	private Formule_Paie formulaire_Paie;	
	
	
	 @OneToMany( targetEntity=Lignepaiegestionrappel.class,mappedBy="rappelle" ,
			 cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Lignepaiegestionrappel> listlignepaiegestion;
	
	 @Transient
	private List<Lignepaiegestionrappel> listGestion;


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


	public Statut getStatut() {
		return statut;
	}


	public void setStatut(Statut statut) {
		this.statut = statut;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public Formule_Paie getFormulaire_Paie() {
		return formulaire_Paie;
	}


	public void setFormulaire_Paie(Formule_Paie formulaire_Paie) {
		this.formulaire_Paie = formulaire_Paie;
	}


	public List<Lignepaiegestionrappel> getListGestion() {
		return listGestion;
	}


	public void setListGestion(List<Lignepaiegestionrappel> listGestion) {
		this.listGestion = listGestion;
	}


	public List<Lignepaiegestionrappel> getListlignepaiegestion() {
		return listlignepaiegestion;
	}


	public void setListlignepaiegestion(List<Lignepaiegestionrappel> listlignepaiegestions) {
		this.listlignepaiegestion = listlignepaiegestions;
	}	 
	
	 
	 
	 
	 
}
