package com.tn.shell.model.shop;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
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

import com.tn.shell.model.lavage.TypeLavage;
import com.tn.shell.model.paie.Employee;

 
public class Rendement2 implements Comparable<Object> {
	 
	private Integer id;
	 
	 
	private Date date;
	private String dates;
	@Enumerated(EnumType.STRING)
	private Poste poste;
	private double nbvoiture;
	private double nbsemi;
	private String parouvrier;
	private double montantv;
	private String laveur;
	private String service;
	 
	@Enumerated(EnumType.STRING)
	private Statuss statuss = Statuss.Encours;
	private String heure_entree;
	private String heure_sortie; 
	private String duree; 
	private String ecart;
	 
	@Enumerated(EnumType.STRING)
	private TypeLavage typelavage;
	private double montants;
	  
	private String nbvoitures;
	 
	Produit produit;
	 
 
	private Employee employee;
	 
	private Lignevente lignevente;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Poste getPoste() {
		return poste;
	}

	public void setPoste(Poste poste) {
		this.poste = poste;
	}

	public double getNbvoiture() {
		return nbvoiture;
	}

	public void setNbvoiture(double nbvoiture) {
		this.nbvoiture = nbvoiture;
	}

	public double getNbsemi() {
		return nbsemi;
	}

	public void setNbsemi(double nbsemi) {
		this.nbsemi = nbsemi;
	}

	public String getParouvrier() {
		return parouvrier;
	}

	public void setParouvrier(String parouvrier) {
		this.parouvrier = parouvrier;
	}

	public double getMontantv() {
		return montantv;
	}

	public void setMontantv(double montantv) {
		this.montantv = montantv;
	}

	public String getLaveur() {
		return laveur;
	}

	public void setLaveur(String laveur) {
		this.laveur = laveur;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Statuss getStatuss() {
		return statuss;
	}

	public void setStatuss(Statuss statuss) {
		this.statuss = statuss;
	}

	public String getHeure_entree() {
		return heure_entree;
	}

	public void setHeure_entree(String heure_entree) {
		this.heure_entree = heure_entree;
	}

	public String getHeure_sortie() {
		return heure_sortie;
	}

	public void setHeure_sortie(String heure_sortie) {
		this.heure_sortie = heure_sortie;
	}

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

	public String getEcart() {
		return ecart;
	}

	public void setEcart(String ecart) {
		this.ecart = ecart;
	}

	public TypeLavage getTypelavage() {
		return typelavage;
	}

	public void setTypelavage(TypeLavage typelavage) {
		this.typelavage = typelavage;
	}

	public double getMontants() {
		return montants;
	}

	public void setMontants(double montants) {
		this.montants = montants;
	}

	public String getNbvoitures() {
		return nbvoitures;
	}

	public void setNbvoitures(String nbvoitures) {
		this.nbvoitures = nbvoitures;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Lignevente getLignevente() {
		return lignevente;
	}

	public void setLignevente(Lignevente lignevente) {
		this.lignevente = lignevente;
	}
	 
	public int compareTo(Object o) {
		try {
			Rendement2 f = (Rendement2) o;
			return (int) (this.montantv - f.montantv);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
 
}
