package com.tn.shell.model.paie;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Societe")
public class Societe {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String libelle;
	private String matricuefiscale;
	@Embedded
	private Adresse address;
	private String tel1;
	private String tel2;
	private String codetva;
	private String banque;
	private long numcompte;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libele) {
		libelle = libele;
	}
	public String getMatricuefiscale() {
		return matricuefiscale;
	}
	public void setMatricuefiscale(String matricuefiscale) {
		this.matricuefiscale = matricuefiscale;
	}
	 
	 
	public Adresse getAddress() {
		return address;
	}
	public void setAddress(Adresse address) {
		this.address = address;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getTel2() {
		return tel2;
	}
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	public String getCodetva() {
		return codetva;
	}
	public void setCodetva(String codetva) {
		this.codetva = codetva;
	}
	public String getBanque() {
		return banque;
	}
	public void setBanque(String banque) {
		this.banque = banque;
	}
	public long getNumcompte() {
		return numcompte;
	}
	public void setNumcompte(long numcompte) {
		this.numcompte = numcompte;
	}
	
	
	
}
