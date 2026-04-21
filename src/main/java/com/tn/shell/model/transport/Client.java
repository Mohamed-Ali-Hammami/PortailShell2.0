package com.tn.shell.model.transport;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
import javax.persistence.Transient;

@Entity
@Table(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer code;
	private String mf;
	private String tel; 
	private String adresse;
	 
    private Integer compteur;  
    private String ca;
	private String nom;
	@Transient
	private double chiffreaffaire;
	@Transient
	private String chiffreaffaires;
	private String nbre;
 private double transport;
	/*@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "transportid")
	private Transport transport; */
	
	@Transient
	private float total;
	@Transient
	private String totals;
	 
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF;
	
	 
	 
	@OneToMany(mappedBy = "client", cascade = { CascadeType.MERGE,
			CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Facturepassager> factureps;

	// lien one to many avec bl
	@OneToMany(mappedBy = "client", cascade = { CascadeType.MERGE,
			CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Bonlivraison> bonlivraison;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	

	public double getTransport() {
		return transport;
	}

	public void setTransport(double transport) {
		this.transport = transport;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	  

	public List<Bonlivraison> getBonlivraison() {
		return bonlivraison;
	}

	public void setBonlivraison(List<Bonlivraison> bonlivraison) {
		this.bonlivraison = bonlivraison;
	}

	public  Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getTotals() {
		DecimalFormat df = new DecimalFormat(".000");
		totals=df.format(total);
		return totals;
	}

	public void setTotals(String totals) {
		this.totals = totals;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getCompteur() {
		return compteur;
	}

	public void setCompteur(Integer compteur) {
		this.compteur = compteur;
	}
 

	public double getChiffreaffaire() {
		return chiffreaffaire;
	}

	public void setChiffreaffaire(double chiffreaffaire) {
		this.chiffreaffaire = chiffreaffaire;
	}

	public String getChiffreaffaires() {
		DecimalFormat df=new DecimalFormat("0.000");
		chiffreaffaires=df.format(chiffreaffaire);
		return chiffreaffaires;
	}

	public void setChiffreaffaires(String chiffreaffaires) {
		this.chiffreaffaires = chiffreaffaires;
	}

	public String getMf() {
		return mf;
	}

	public void setMf(String mf) {
		this.mf = mf;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String addresse) {
		this.adresse = addresse;
	}

	 

	public String getCa() {
		return ca;
	}

	public void setCa(String ca) {
		this.ca = ca;
	}

	public String getNbre() {
		return nbre;
	}

	public void setNbre(String nbre) {
		this.nbre = nbre;
	}

	public List<Facturepassager> getFactureps() {
		return factureps;
	}

	public void setFactureps(List<Facturepassager> factureps) {
		this.factureps = factureps;
	}
 
	
	

}
