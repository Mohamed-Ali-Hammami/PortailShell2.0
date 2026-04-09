package com.tn.shell.model.paie;

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
@Table(name = "Pret")
public class Pret {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Integer id;
private double montant_pret;
private double taux;
private Integer nbmois;
private Date date_debut;
@Enumerated(EnumType.STRING)
private Statut statut= Statut.ACTIF;
private double reste_deduction=0;
private Integer reste_mois=0;
private double deductionParmois;
@ManyToOne(cascade = { CascadeType.MERGE })
@JoinColumn(name = "employeeid")
private Employee employee;
@Transient
private double valeur_retirer;

/*
 * Getter
 * and
 * setter
 * */


public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public double getMontant_pret() {
	return montant_pret;
}
public void setMontant_pret(double montant_pret) {
	this.montant_pret = montant_pret;
}
public double getTaux() {
	return taux;
}
public void setTaux(double taux) {
	this.taux = taux;
}
public Integer getNbmois() {
	return nbmois;
}
public void setNbmois(Integer nbmois) {
	this.nbmois = nbmois;
}
public Date getDate_debut() {
	return date_debut;
}
public void setDate_debut(Date date_debut) {
	this.date_debut = date_debut;
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
public double getValeur_retirer() {
	valeur_retirer=montant_pret/nbmois;
	return valeur_retirer;
}
public void setValeur_retirer(double valeur_retirer) {
	this.valeur_retirer = valeur_retirer;
}
public double getReste_deduction() {
	return reste_deduction;
}
public void setReste_deduction(double reste_deduction) {
	this.reste_deduction = reste_deduction;
}
public double getDeductionParmois() {
	return deductionParmois;
}
public void setDeductionParmois(double deductionParmois) {
	this.deductionParmois = deductionParmois;
}
public Integer getReste_mois() {
	return reste_mois;
}
public void setReste_mois(Integer reste_mois) {
	this.reste_mois = reste_mois;
}
 


	 

}
