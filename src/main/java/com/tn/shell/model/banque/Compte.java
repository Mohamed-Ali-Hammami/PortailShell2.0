package com.tn.shell.model.banque;

import java.math.BigDecimal;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tn.shell.model.gestat.Statut;

@Entity
@Table(name="Compte")
public class Compte {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id; 
	private String numerodecompte;
	private BigDecimal solde;
	private BigDecimal soleinitial;
	private BigDecimal soldecredit;
	private BigDecimal soldedebit;
	@Enumerated(EnumType.STRING)
	private Statut statut = Statut.ACTIF;
	
	@OneToMany(mappedBy = "compte", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Transaction> listtransaction;
	
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumerodecompte() {
		return numerodecompte;
	}
	public void setNumerodecompte(String numerodecompte) {
		this.numerodecompte = numerodecompte;
	}
	public BigDecimal getSolde() {
		return solde;
	}
	public void setSolde(BigDecimal solde) {
		this.solde = solde;
	}
	public BigDecimal getSoldecredit() {
		return soldecredit;
	}
	public void setSoldecredit(BigDecimal soldecredit) {
		this.soldecredit = soldecredit;
	}
	public BigDecimal getSoldedebit() {
		return soldedebit;
	}
	public void setSoldedebit(BigDecimal soldedebit) {
		this.soldedebit = soldedebit;
	}
 
	public BigDecimal getSoleinitial() {
		return soleinitial;
	}
	public void setSoleinitial(BigDecimal soleinitial) {
		this.soleinitial = soleinitial;
	}
	public List<Transaction> getListtransaction() {
		return listtransaction;
	}
	public void setListtransaction(List<Transaction> listtransaction) {
		this.listtransaction = listtransaction;
	}
	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	
	
	
	
	
	
}
