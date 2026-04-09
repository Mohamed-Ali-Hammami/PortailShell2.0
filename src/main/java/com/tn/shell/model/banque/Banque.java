package com.tn.shell.model.banque;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tn.shell.model.gestat.Statut;

@Entity
@Table(name="Banque")
public class Banque {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id; 
	private String nom;
	private String agence;
	private String adresse;
	@Enumerated(EnumType.STRING)
	private Statut statut = Statut.ACTIF;
	@OneToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "compteid")
	private Compte compte;	
 
	

	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAgence() {
		return agence;
	}
	public void setAgence(String agence) {
		this.agence = agence;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
 
	
	
	
	
	
}
