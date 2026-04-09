package com.tn.shell.model.gestat;

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

@Entity
@Table(name = "Pompe")
public class Pompe {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String libelle;
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF;
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "articlecarburantid")
	private Articlecarburant articlecarburant;
	
	
	@OneToMany(mappedBy = "pompe", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Retourcuve> retourcuves;

	 @OneToMany(mappedBy="pompe",cascade={CascadeType.MERGE,CascadeType.REMOVE,CascadeType.REFRESH})
	 private List<Ligneindex> ligneindexs;
	public List<Retourcuve> getRetourcuves() {
		return retourcuves;
	}

	public void setRetourcuves(List<Retourcuve> retourcuves) {
		this.retourcuves = retourcuves;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Articlecarburant getArticlecarburant() {
		return articlecarburant;
	}

	public void setArticlecarburant(Articlecarburant articlecarburant) {
		this.articlecarburant = articlecarburant;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Ligneindex> getLigneindexs() {
		return ligneindexs;
	}

	public void setLigneindexs(List<Ligneindex> ligneindexs) {
		this.ligneindexs = ligneindexs;
	}
	
	 
}
