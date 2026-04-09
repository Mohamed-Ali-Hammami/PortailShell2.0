package com.tn.shell.model.lavage;

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

import com.tn.shell.model.shop.Produit;
import com.tn.shell.model.shop.Statut;

@Entity
@Table(name = "Affectationfiltre")
public class AffectationFiltre {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
   @ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "modelid")
	private Model model;
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "produitid")
	private Produit produit;
	@Enumerated(EnumType.STRING)
	private Statut statut = Statut.ACTIF;
	private String fournisseur;
	private String emplacement;
	@Enumerated(EnumType.STRING)
	private TypeFiltre typefiltre ;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	public String getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}
	public String getEmplacement() {
		return emplacement;
	}
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}
	public TypeFiltre getTypefiltre() {
		return typefiltre;
	}
	public void setTypefiltre(TypeFiltre typefiltre) {
		this.typefiltre = typefiltre;
	}
	
	
	
	 
}
