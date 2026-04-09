package com.tn.shell.model.lavage;

import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notif_smsing")
public class NotifSmsing {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	@Column(name = "id_notif_smsing")
	private BigInteger idNotifmsing;
 
	private Date date;
	private String heure;
 
	private String sujet;
	@Column(name = "n_tel")
	private String ntel;
	@Column(name = "message")
	private String message;
	private String etat;
	@Column(name = "heure_envoie")
	private String heureEnvoie;
	 
	public BigInteger getIdNotifmsing() {
		return idNotifmsing;
	}
	public void setIdNotifmsing(BigInteger idNotifmsing) {
		this.idNotifmsing = idNotifmsing;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	 
	 
	public String getSujet() {
		return sujet;
	}
	public void setSujet(String sujet) {
		this.sujet = sujet;
	}
	public String getNtel() {
		return ntel;
	}
	public void setNtel(String ntel) {
		this.ntel = ntel;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getHeure() {
		return heure;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}
	public String getHeureEnvoie() {
		return heureEnvoie;
	}
	public void setHeureEnvoie(String heureEnvoie) {
		this.heureEnvoie = heureEnvoie;
	}
	 
	
	 
	
	

}
