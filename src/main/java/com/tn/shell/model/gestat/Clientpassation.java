package com.tn.shell.model.gestat;

import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Clientpassation")
public class Clientpassation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;	
	private String tel; 	 
	private String nom; 
	private double reste;
	private double avance;
	private String dernierdates;
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF;
	
	@Transient
	private String restes;
	@OneToMany(mappedBy = "clientpassation", cascade = { CascadeType.MERGE,
			CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Creditpassation> creditpassationclients;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public double getReste() {
		return reste;
	}
	public void setReste(double reste) {
		this.reste = reste;
	}
	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	public List<Creditpassation> getCreditpassationclients() {
		return creditpassationclients;
	}
	public void setCreditpassationclients(List<Creditpassation> creditpassationclients) {
		this.creditpassationclients = creditpassationclients;
	}
	public String getDernierdates() {
		return dernierdates;
	}
	public void setDernierdates(String dernierdates) {
		this.dernierdates = dernierdates;
	}
	public double getAvance() {
		return avance;
	}
	public void setAvance(double avance) {
		this.avance = avance;
	}
	public String getRestes() {
		DecimalFormat df=new DecimalFormat("#,###.000");
		restes=df.format(reste);
		return restes;
	}
	public void setRestes(String restes) {
		this.restes = restes;
	}
	
	
	

}
