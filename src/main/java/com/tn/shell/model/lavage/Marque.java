package com.tn.shell.model.lavage;

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

import com.tn.shell.model.shop.Statut;

@Entity
@Table(name = "Marque")
public class Marque {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String marque;
	@Enumerated(EnumType.STRING)
	private Statut statut = Statut.ACTIF;
	 @OneToMany(mappedBy="marque",cascade={CascadeType.MERGE,CascadeType.REMOVE,CascadeType.REFRESH})
	 private List<Model> listModel;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	public List<Model> getListModel() {
		return listModel;
	}
	public void setListModel(List<Model> listModel) {
		this.listModel = listModel;
	}
	
	
	
	
	
	
}
