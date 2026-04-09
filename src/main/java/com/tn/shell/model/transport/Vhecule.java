package com.tn.shell.model.transport;

import java.text.DecimalFormat;
import java.util.*;

import javax.persistence.*;
 

@Entity
@Table(name="Vhecule")
 
public class Vhecule  {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private Integer id;	 
	 private String matricule;
	 private String numserie;
	 @Transient
	 private String chauf;
	 
	 @Transient
	 private double salchauf;
	 @Enumerated(EnumType.STRING)
		private Statut statut= Statut.ACTIF;
	 @Embedded
	 private Paramettrevehicule paramettrevehicule;
	// lien one to many avec bl
		@OneToMany(mappedBy = "vhecule", cascade = { CascadeType.MERGE,
				CascadeType.REMOVE, CascadeType.REFRESH })
		private List<Bonlivraison> bonlivraison;
	 
	
	 
		
		@OneToMany(mappedBy = "vhecule", cascade = { CascadeType.MERGE,
				CascadeType.REMOVE, CascadeType.REFRESH })
		private List<Facturepassager> facturep;
	
	
	// lien one to many avec lignecommandes
		@OneToMany(mappedBy = "vhecule", cascade = { CascadeType.MERGE,
				CascadeType.REMOVE, CascadeType.REFRESH })
		private List<Depense> depenses;
	 
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
 
	/*public List<Lignecommande> getLignecommandes() {
		return lignecommandes;
	}
	public void setLignecommandes(List<Lignecommande> lignecommandes) {
		this.lignecommandes = lignecommandes;
	}*/
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	 
	public List<Bonlivraison> getBonlivraison() {
		return bonlivraison;
	}
	public void setBonlivraison(List<Bonlivraison> bonlivraison) {
		this.bonlivraison = bonlivraison;
	}
	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	public List<Depense> getDepenses() {
		return depenses;
	}
	public void setDepenses(List<Depense> depenses) {
		this.depenses = depenses;
	}
	public Paramettrevehicule getParamettrevehicule() {
		return paramettrevehicule;
	}
	public void setParamettrevehicule(Paramettrevehicule paramettrevehicule) {
		this.paramettrevehicule = paramettrevehicule;
	}
	
	public String getChauf() {
		return chauf;
	}
	public void setChauf(String chauf) {
		this.chauf = chauf;
	}
	public String getNumserie() {
		return numserie;
	}
	public void setNumserie(String numserie) {
		this.numserie = numserie;
	}
 
	public List<Facturepassager> getFacturep() {
		return facturep;
	}
	public void setFacturep(List<Facturepassager> facturep) {
		this.facturep = facturep;
	}
	public double getSalchauf() {
		return salchauf;
	}
	public void setSalchauf(double salchauf) {
		this.salchauf = salchauf;
	}
	  
	  
	
	 
	 
	
}
