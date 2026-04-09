package com.tn.shell.model.transport;

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

@Entity
@Table(name = "Familledepensetransport")
public class Familledepensetransport {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String libelle;
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF;
	
	// lien one to many avec depense
		@OneToMany(mappedBy = "familledepense", cascade = { CascadeType.MERGE,
				CascadeType.REMOVE, CascadeType.REFRESH })
		private List<Depense> depenses;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
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
	 
	
	
}
