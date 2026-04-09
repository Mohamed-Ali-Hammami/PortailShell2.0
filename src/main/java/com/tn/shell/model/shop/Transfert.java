package com.tn.shell.model.shop;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.tn.shell.model.gestat.Utilisateur;

@Entity
@Table(name = "Transfert")
public class Transfert {
	 @Id
	@GeneratedValue(strategy = GenerationType.AUTO)	 
	private Integer id;
	 
	 	 
	private Date date;
	@Transient
	private String dates;
	 
	
	@Transient
	 Calendar now = Calendar.getInstance();
	private String heure=(now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));
	 
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF;
	 
	 
	
	 
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="utilisateurid")
	private Utilisateur utilisateur;
	
	
	@OneToMany(mappedBy = "transfert", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Lignetransert> lignetransferts;
	 
	  
	public Transfert() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	 

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDates() {
		SimpleDateFormat s=new SimpleDateFormat("dd-MM-yyyy");
		dates=s.format(date);
		return dates;
		 
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	 
	 
	 

	public Calendar getNow() {
		return now;
	}

	public void setNow(Calendar now) {
		this.now = now;
	}

	public String getHeure() {
		return heure;
	}

	public void setHeure(String heure) {
		this.heure = heure;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<Lignetransert> getLignetransferts() {
		return lignetransferts;
	}

	public void setLignetransferts(List<Lignetransert> lignetransferts) {
		this.lignetransferts = lignetransferts;
	}

 

}
