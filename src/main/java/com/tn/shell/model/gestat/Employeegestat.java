package com.tn.shell.model.gestat;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
  
 
@Entity
@Table(name = "Employeegestat")
public class Employeegestat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer matricule;
	private String cin;
	private String tel;
	private String nom;
	private String prenom;
	private String adresse;
	private double salaire_journalier;
	 
	@Enumerated(EnumType.STRING)
	private Statut statut = Statut.ACTIF;

	// lien one to many avec Avance
	@OneToMany(mappedBy = "employee", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Avancegestat> avances;
	
	@OneToMany(mappedBy = "employee", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Pompiste> pompistes;

	public Integer getMatricule() {
		return matricule;
	}

	public void setMatricule(Integer matricule) {
		this.matricule = matricule;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}
	

	public double getSalaire_journalier() {
		return salaire_journalier;
	}

	public void setSalaire_journalier(double salaire_journalier) {
		this.salaire_journalier = salaire_journalier;
	}

	 

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public List<Avancegestat> getAvances() {
		return avances;
	}

	public void setAvances(List<Avancegestat> avances) {
		this.avances = avances;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public List<Pompiste> getPompistes() {
		return pompistes;
	}

	public void setPompistes(List<Pompiste> pompistes) {
		this.pompistes = pompistes;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
 
} 
