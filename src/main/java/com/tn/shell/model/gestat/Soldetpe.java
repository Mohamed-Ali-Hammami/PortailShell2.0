package com.tn.shell.model.gestat;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Soldetpe")
public class Soldetpe {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private double solde;
	private BigDecimal solecartebancaire=new BigDecimal(0);
	@Transient
	private String soldes;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde= solde;
	}
	 
	
	public String getSoldes() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		soldes = df.format(solde);

		return soldes;
	}

	public void setSoldes(String soldes) {
		this.soldes = soldes;
	}
	public BigDecimal getSolecartebancaire() {
		return solecartebancaire;
	}
	public void setSolecartebancaire(BigDecimal solecartebancaire) {
		this.solecartebancaire = solecartebancaire;
	}
	
}
