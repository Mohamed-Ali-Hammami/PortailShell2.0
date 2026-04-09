package com.tn.shell.model.shop;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tn.shell.model.gestat.Utilisateur;

@Entity
@Table(name="Traceshop")
public class Traceshop {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String action;
	private Date date;
	private String dates;
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="utilisateurid")
	private Utilisateur utilisateur;
	
	 
	
	 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDates() {
		SimpleDateFormat s = new SimpleDateFormat();
		dates = s.format(date);
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	 

	 
 
	
	
}
