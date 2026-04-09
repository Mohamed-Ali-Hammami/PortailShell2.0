package com.tn.shell.model.paie;

import java.text.DecimalFormat;
import java.util.Date;
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
import javax.persistence.Transient;

@Entity
@Table(name = "Categorie")
public class Categorie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;
	
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "degreeid")
	private Degree degree;
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "catid")
	private Cat cat;
	
	@OneToMany(mappedBy = "contrat", cascade = { CascadeType.MERGE,
			CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Employee> employees;
	
	private double prix_heur;
	@Transient
	private String prixheur;
	
	private Date date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Degree getDegree() {
		return degree;
	}

	public void setDegree(Degree degree) {
		this.degree = degree;
	}

	public Cat getCat() {
		return cat;
	}

	public void setCat(Cat cat) {
		this.cat = cat;
	}

	public double getPrix_heur() {
		return prix_heur;
	}

	public void setPrix_heur(double prix_heur) {
		this.prix_heur = prix_heur;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public String getPrixheur() {
		DecimalFormat df = new DecimalFormat("0.000");
		prixheur=df.format(prix_heur);
		return prixheur;
	}

	public void setPrixheur(String prixheur) {
		this.prixheur = prixheur;
	}
	
	
	
	
}
