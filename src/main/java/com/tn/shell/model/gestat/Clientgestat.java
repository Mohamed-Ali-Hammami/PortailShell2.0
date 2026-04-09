package com.tn.shell.model.gestat;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
@Table(name = "Clientgestat")
public class Clientgestat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer code;	 
	private String tel;  
	private String prenom;
	private String nom;
	 
	 
	private double chiffreaffaire=0;
	@Transient
	private String chiffreaffaires;
	private double reste;
	@Transient
	private String restes;
	private double plafond;
	 
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF;
	
	// lien one to many avec creditclient
	@OneToMany(mappedBy = "client", cascade = { CascadeType.MERGE,
			CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Credit> creditclients;
	 
	@OneToMany(mappedBy = "client", cascade = { CascadeType.MERGE,
			CascadeType.REMOVE, CascadeType.REFRESH })
	private List<TransactionCredit> transactionclients;

	 

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	 
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
 

	public  Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	 

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	 
 

	public String getRestes() {
		DecimalFormat df=new DecimalFormat("#,###.000");
		/*restes=df.format(reste);
		return restes;*/
		if(reste>=1) {
			df=new DecimalFormat("#,###.000");
			restes=df.format(reste);
		}
		else if(reste<1 && reste>=0) {
			df=new DecimalFormat("0.000");
			restes=df.format(reste);
		}
		
		else if(  reste<0) {
			  if(reste>-1) {
				  df=new DecimalFormat("0.000");
					restes=df.format(reste);
			  }
			  else {
			df=new DecimalFormat("#,###.000");
			restes=df.format(reste);
			  }
			 
		}
		else restes="";
		return restes;
	}

	public void setRestes(String restes) {
		this.restes = restes;
	}

	public double getChiffreaffaire() {
		return chiffreaffaire;
	}

	public void setChiffreaffaire(double chiffreaffaire) {
		this.chiffreaffaire = chiffreaffaire;
	}

	public String getChiffreaffaires() {
		DecimalFormat df=new DecimalFormat("#,###.000");
		chiffreaffaires=df.format(chiffreaffaire);
		return chiffreaffaires;
	}

	public void setChiffreaffaires(String chiffreaffaires) {
		this.chiffreaffaires = chiffreaffaires;
	}

 
	public double getReste() {
		return reste;
	}

	public void setReste(double reste) {
		this.reste = reste;
	}

	public double getPlafond() {
		return plafond;
	}

	public void setPlafond(double plafond) {
		this.plafond = plafond;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public List<Credit> getCreditclients() {
		return creditclients;
	}

	public void setCreditclients(List<Credit> creditclients) {
		this.creditclients = creditclients;
	}

	public List<TransactionCredit> getTransactionclients() {
		return transactionclients;
	}

	public void setTransactionclients(List<TransactionCredit> transactionclients) {
		this.transactionclients = transactionclients;
	}

	 
  

}
