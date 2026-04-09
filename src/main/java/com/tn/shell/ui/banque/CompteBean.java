package com.tn.shell.ui.banque;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.tn.shell.model.banque.Banque;
import com.tn.shell.model.banque.Compte;
import com.tn.shell.service.banque.ServiceBanque;
import com.tn.shell.service.banque.ServiceCompte;
 

@ManagedBean(name = "CompteBean")
@SessionScoped
public class CompteBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	@ManagedProperty(value = "#{ServiceCompte}")
	ServiceCompte serviceCompte;
	@ManagedProperty(value = "#{ServiceBanque}")
	ServiceBanque serviceBanque;
	private Long numerodecompte;
	private BigDecimal solde;
	private BigDecimal soleinitial;
	private BigDecimal soldecredit;
	private BigDecimal soldedebit;
	private Compte compte;
	 
	private List<Compte> listCompte;
	private List<Banque> listBanque;
	private String banque;
 
	
	
	
	
	
	
	public ServiceBanque getServiceBanque() {
		return serviceBanque;
	}
	public void setServiceBanque(ServiceBanque serviceBanque) {
		this.serviceBanque = serviceBanque;
	}
	public Long getNumerodecompte() {
		return numerodecompte;
	}
	public void setNumerodecompte(Long numerodecompte) {
		this.numerodecompte = numerodecompte;
	}
	public BigDecimal getSolde() {
		return solde;
	}
	public void setSolde(BigDecimal solde) {
		this.solde = solde;
	}
	public BigDecimal getSoleinitial() {
		return soleinitial;
	}
	public void setSoleinitial(BigDecimal soleinitial) {
		this.soleinitial = soleinitial;
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
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	public List<Compte> getListCompte() {
		return listCompte;
	}
	public void setListCompte(List<Compte> listCompte) {
		this.listCompte = listCompte;
	}
	public List<Banque> getListBanque() {
		return listBanque;
	}
	public void setListBanque(List<Banque> listBanque) {
		this.listBanque = listBanque;
	}
	public String getBanque() {
		return banque;
	}
	public void setBanque(String banque) {
		this.banque = banque;
	}
	public ServiceCompte getServiceCompte() {
		return serviceCompte;
	}
	public void setServiceCompte(ServiceCompte serviceCompte) {
		this.serviceCompte = serviceCompte;
	}
	 
}
