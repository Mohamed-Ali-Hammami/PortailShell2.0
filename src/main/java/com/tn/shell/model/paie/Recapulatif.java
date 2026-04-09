package com.tn.shell.model.paie;

import java.util.List;

import javax.persistence.Transient;

public class Recapulatif {

	 private Formule_Paie formul;
     private Integer annee;
     private Integer mois;
    
 	private List<Lignepaiegestion> listGestion;
 	private List<Lignepaiegestionprime> listGestionprime;
 	private List<Lignepaiegestionrappel> listGestions;
	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	public Integer getMois() {
		return mois;
	}

	public void setMois(Integer mois) {
		this.mois = mois;
	}

	public Formule_Paie getFormul() {
		return formul;
	}

	public void setFormul(Formule_Paie formul) {
		this.formul = formul;
	}

	public List<Lignepaiegestion> getListGestion() {
		return listGestion;
	}

	public void setListGestion(List<Lignepaiegestion> listGestion) {
		this.listGestion = listGestion;
	}

	public List<Lignepaiegestionprime> getListGestionprime() {
		return listGestionprime;
	}

	public void setListGestionprime(List<Lignepaiegestionprime> listGestionprime) {
		this.listGestionprime = listGestionprime;
	}

	public List<Lignepaiegestionrappel> getListGestions() {
		return listGestions;
	}

	public void setListGestions(List<Lignepaiegestionrappel> listGestions) {
		this.listGestions = listGestions;
	}

	 
	 

}
