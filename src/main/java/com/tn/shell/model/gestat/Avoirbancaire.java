package com.tn.shell.model.gestat;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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

@Entity
@Table(name = "Avoirbancaire")
public class Avoirbancaire {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	 
	private BigDecimal montant; 
	private Date date;
	
	private String dates;
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF;
	
	 
	
	 
	
	

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

	 
 
 

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDates() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		if(date != null)
			dates=s.format(date);
		else dates="";
		 
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public BigDecimal getMontant() {
		return montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	 
	
	
}
