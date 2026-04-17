package com.tn.shell.ui.shop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import com.tn.shell.model.shop.Status;
import com.tn.shell.model.shop.*;
import com.tn.shell.service.shop.*;

 

@ManagedBean(name = "TicketBean")
@SessionScoped
public class TicketBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	
	@ManagedProperty(value = "#{ServiceLignevente}")
	ServiceLignevente serviceLignevente;

	@ManagedProperty(value = "#{serviceticket}")
	ServiceTicket serviceticket;
	
	private Integer numticket;
	private Status[] status;
	private Status statu;
	private Ticket ticket;
	private Ticket selectedTicket;
	private List<Lignevente> listelignevente;
	private Poste[] postes;
	private Poste poste;
	private List<Ticket> listticket;
	private List<Lignevente> listticketnegtif;
	private Date date;
	private String dates;
	private Integer totalquantite;
	private double totalrecus;
	private double totalrendus;
	private String totalht;
	private String totaltva;
	private double totalttc;
	
	
	public String listticket() {
		listticket=new ArrayList<Ticket>();
		date=new Date();
		totalttc=0;
		listelignevente=new ArrayList<Lignevente>();
		return SUCCESS;
	}
	public String AnnulationTicket() {
		date=new Date();
		numticket=null;
		listelignevente=new ArrayList<Lignevente>();
		return SUCCESS;
	}
	
	public void getTicketbynum(AjaxBehaviorEvent event) {
		listelignevente=new ArrayList<Lignevente>();
		ticket=serviceticket.getbyvaleur(numticket);
		date=ticket.getDate();
		listelignevente=serviceLignevente.getAllbyticket(ticket);
	}
	
	public String validerAnnulation() {
		if(!ticket.getGen().equals(TypeGeneration.Cloture)) {
		ticket.setStatut(Statut.DEACTIF);
		serviceticket.update(ticket);
		for(Lignevente l:listelignevente) {
			l.setStatut(Statut.DEACTIF);
			serviceLignevente.update(l);
		}
		}
		else {
			String message = "Journée cloturé Impossible de supprimer ce Ticket";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(message));
		}
		
		return SUCCESS;
	}
public String listeAnnulation() {
	date=new Date();
	
	return SUCCESS;
}

public void getTicketbydate(AjaxBehaviorEvent event) {	
	  totalttc=0;
	  listticket=new ArrayList<Ticket>();
	  listticket=serviceticket.getticketbydate(dates);	 
	  for(Ticket t:listticket) {
		  totalttc=totalttc+t.getTotal_vente();
	  }
}

public void getVentebyTicket(AjaxBehaviorEvent event) { 
	  listelignevente=new ArrayList<Lignevente>();
	listelignevente=serviceLignevente.getAllbyticket(selectedTicket);
}

	public ServiceLignevente getServiceLignevente() {
		return serviceLignevente;
	}

	public void setServiceLignevente(ServiceLignevente serviceLignevente) {
		this.serviceLignevente = serviceLignevente;
	}

	public ServiceTicket getServiceticket() {
		return serviceticket;
	}

	public void setServiceticket(ServiceTicket serviceticket) {
		this.serviceticket = serviceticket;
	}

	public Integer getNumticket() {
		return numticket;
	}

	public void setNumticket(Integer numticket) {
		this.numticket = numticket;
	}

	public Status[] getStatus() {
		return status;
	}

	public void setStatus(Status[] status) {
		this.status = status;
	}

	public Status getStatu() {
		return statu;
	}

	public void setStatu(Status statu) {
		this.statu = statu;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public List<Lignevente> getListelignevente() {
		return listelignevente;
	}

	public void setListelignevente(List<Lignevente> listelignevente) {
		this.listelignevente = listelignevente;
	}

	public Poste[] getPostes() {
		return postes;
	}

	public void setPostes(Poste[] postes) {
		this.postes = postes;
	}

	public Poste getPoste() {
		return poste;
	}

	public void setPoste(Poste poste) {
		this.poste = poste;
	}

	public List<Ticket> getListticket() {
		return listticket;
	}

	public void setListticket(List<Ticket> listticket) {
		this.listticket = listticket;
	}

	public List<Lignevente> getListticketnegtif() {
		return listticketnegtif;
	}

	public void setListticketnegtif(List<Lignevente> listticketnegtif) {
		this.listticketnegtif = listticketnegtif;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public Integer getTotalquantite() {
		return totalquantite;
	}

	public void setTotalquantite(Integer totalquantite) {
		this.totalquantite = totalquantite;
	}

	public double getTotalrecus() {
		return totalrecus;
	}

	public void setTotalrecus(double totalrecus) {
		this.totalrecus = totalrecus;
	}

	public double getTotalrendus() {
		return totalrendus;
	}

	public void setTotalrendus(double totalrendus) {
		this.totalrendus = totalrendus;
	}

	public String getTotalht() {
		return totalht;
	}

	public void setTotalht(String totalht) {
		this.totalht = totalht;
	}

	public String getTotaltva() {
		return totaltva;
	}

	public void setTotaltva(String totaltva) {
		this.totaltva = totaltva;
	}

	public double getTotalttc() {
		return totalttc;
	}

	public void setTotalttc(double totalttc) {
		this.totalttc = totalttc;
	}

	public Ticket getSelectedTicket() {
		return selectedTicket;
	}

	public void setSelectedTicket(Ticket selectedTicket) {
		this.selectedTicket = selectedTicket;
	}
	
	
	
}
