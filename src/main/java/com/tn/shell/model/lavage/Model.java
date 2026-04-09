package com.tn.shell.model.lavage;

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

import com.tn.shell.model.shop.Statut;
import com.tn.shell.model.shop.Produit;

@Entity
@Table(name = "Model")
public class Model {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String model;
	@Enumerated(EnumType.STRING)
	private Statut statut = Statut.ACTIF;
	
	@OneToMany(mappedBy="model",cascade={CascadeType.MERGE,CascadeType.REMOVE,CascadeType.REFRESH})
	private List<AffectationFiltre> listAffectationFiltres;
 
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "marqueid")
	private Marque marque;
	 
	 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	} 
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	/*public List<Produit> getListProduit() {
		return listProduit;
	}
	public void setListProduit(List<Produit> listProduit) {
		this.listProduit = listProduit;
	}*/
	public Marque getMarque() {
		return marque;
	}
	public void setMarque(Marque marque) {
		this.marque = marque;
	}
	public List<AffectationFiltre> getListAffectationFiltres() {
		return listAffectationFiltres;
	}
	public void setListAffectationFiltres(List<AffectationFiltre> listAffectationFiltres) {
		this.listAffectationFiltres = listAffectationFiltres;
	}
	 
	
	
	
}
