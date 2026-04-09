package com.tn.shell.model.shop;

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

@Entity
@Table(name = "Vente")
public class Vente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Date date;
	@Enumerated(EnumType.STRING)
	private Statut statut = Statut.ACTIF;

	 
    
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "ticketid")
	private Ticket ticket;
	
	@OneToMany(mappedBy = "vente", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Lignevente> Ligneventes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	 

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
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

	public List<Lignevente> getLigneventes() {
		return Ligneventes;
	}

	public void setLigneventes(List<Lignevente> ligneventes) {
		Ligneventes = ligneventes;
	}
	
	

}
