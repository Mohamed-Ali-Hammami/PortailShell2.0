package com.tn.shell.model.paie;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Degree")
public class Degree {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;
	private Integer periode;
	private Integer valeur;
	
	
	@Transient
	private List<Categorie> listcategories;
	// lien one to many avec Categorie
		@OneToMany(mappedBy = "degree", cascade = { CascadeType.MERGE,
				CascadeType.REMOVE, CascadeType.REFRESH })
		private List<Categorie> categories;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPeriode() {
		return periode;
	}
	public void setPeriode(Integer periode) {
		this.periode = periode;
	}
	public Integer getValeur() {
		return valeur;
	}
	public void setValeur(Integer valeur) {
		this.valeur = valeur;
	}
	public List<Categorie> getCategories() {
		return categories;
	}
	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}
	public List<Categorie> getListcategories() {
		return listcategories;
	}
	public void setListcategories(List<Categorie> listcategories) {
		this.listcategories = listcategories;
	}
	
	
	
}
