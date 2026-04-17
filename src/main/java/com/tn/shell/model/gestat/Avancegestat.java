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

import com.tn.shell.model.paie.Employee;

@Entity
@Table(name = "Avancegestat")
public class Avancegestat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Integer id;
private double montant_avance;

private Integer annee;
private Date date;
 
@ManyToOne(cascade = { CascadeType.MERGE })
@JoinColumn(name = "caisseid")
private Caisse caisse;
 

private String dates;
private Integer mois;
@Transient
private String moi;
@Transient
private String montant_avances;


@Enumerated(EnumType.STRING)
private Statut statut= Statut.ACTIF;

@ManyToOne(cascade = { CascadeType.MERGE })
@JoinColumn(name = "employeeid")
private Employee  employee;

@Enumerated(EnumType.STRING)
private Poste poste;
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
public double getMontant_avance() {
	return montant_avance;
}
public void setMontant_avance(double montant_avance) {
	this.montant_avance = montant_avance;
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
public Employee  getEmployee() {
	return employee;
}
public void setEmployee(Employee  employee) {
	this.employee = employee;
} 

public String getMoi() {
	return getMoisbyIntger(mois);
}




public Poste getPoste() {
	return poste;
}
public void setPoste(Poste poste) {
	this.poste = poste;
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
public String getMontant_avances() {
	DecimalFormat df=new DecimalFormat("#,###.000");
	montant_avances=df.format(montant_avance);
	return montant_avances;
}
public void setMontant_avances(String montant_avances) {
	this.montant_avances = montant_avances;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getDates() {
	SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
	if(date != null)
		dates=s.format(date);
	else dates="";
	return dates;
}
public void setDates(String dates) {
	this.dates = dates;
}

public Caisse getCaisse() {
	return caisse;
}
public void setCaisse(Caisse caisse) {
	this.caisse = caisse;
}
	 

}
