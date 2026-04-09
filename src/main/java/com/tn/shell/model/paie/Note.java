package com.tn.shell.model.paie;

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

@Entity
@Table(name = "Note")
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Integer id;
	private Integer note;	
	private Integer annee;
	private Integer mois;
   
@Enumerated(EnumType.STRING)
private Statut statut= Statut.ACTIF;

@ManyToOne(cascade = { CascadeType.MERGE })
@JoinColumn(name = "employeeid")
private Employee employee;

// lien one to many avec Paie
@OneToMany(mappedBy = "note", cascade = { CascadeType.MERGE,
		CascadeType.REMOVE, CascadeType.REFRESH })
private List<Paieprime> paies;

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public Integer getNote() {
	return note;
}

public void setNote(Integer note) {
	this.note = note;
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

public List<Paieprime> getPaies() {
	return paies;
}

public void setPaies(List<Paieprime> paies) {
	this.paies = paies;
}




}
