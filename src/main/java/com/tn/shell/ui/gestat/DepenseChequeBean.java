package com.tn.shell.ui.gestat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.tn.shell.model.banque.Compte;
import com.tn.shell.model.banque.Enumcheque;
import com.tn.shell.model.banque.Reglement;
import com.tn.shell.model.banque.Transaction;
import com.tn.shell.model.banque.TypeTransaction;
import com.tn.shell.model.gestat.DepenseCheque;
import com.tn.shell.model.gestat.Familledepensegestat;

import com.tn.shell.service.banque.ServiceCompte;
import com.tn.shell.service.banque.ServiceTransaction;

import com.tn.shell.service.gestat.ServiceDepensecheque;
import com.tn.shell.service.gestat.ServiceFamilleDepensegestat;

@ManagedBean(name = "DepenseChequeBean")
@SessionScoped
public class DepenseChequeBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{ServiceDepensecheque}")
	ServiceDepensecheque serviceDepensecheque;
	@ManagedProperty(value = "#{ServiceFamilleDepensegestat}")
	ServiceFamilleDepensegestat serviceFamilleDepense;

	@ManagedProperty(value = "#{ServiceCompte}")
	ServiceCompte serviceCompte;
	@ManagedProperty(value = "#{ServiceTransaction}")
	ServiceTransaction serviceTransaction;
	private DepenseCheque depenseCheque;

	private BigDecimal montant;
	private String libelle;
	private Date date;
	private Date date1;
	private String famille;
	private Date date2;
	private String dates;
	private String banque;
	private String reference;
	private List<DepenseCheque> listDepense;
	private List<String> listtypeDepense;
	private String typeDepense;

	private List<Familledepensegestat> listesdepense;

	public String addDepense() {
		montant = new BigDecimal(0);
		libelle = null;
		date = new Date();
		banque = null;
		dates = null;
		return SUCCESS;
	}

	public String saveDepense() {
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		depenseCheque = new DepenseCheque();
		depenseCheque.setBanque(banque);
		depenseCheque.setTypeDepense(typeDepense);
		Familledepensegestat fam = serviceFamilleDepense.getFamilebyeibelle(famille);
		depenseCheque.setDate(date);
		depenseCheque.setDates(f.format(date));
		depenseCheque.setLibelle(libelle);
		if (fam != null)
			depenseCheque.setFamilledepense(fam);

		depenseCheque.setMontant(montant);
		depenseCheque.setReference(reference);
		serviceDepensecheque.save(depenseCheque);
		  Compte compte=serviceCompte.Findbynom("080810230810000495"); 
		if (compte != null) {
			SimpleDateFormat f2 = new SimpleDateFormat("dd-MM-yyyy");
			Transaction t = new Transaction();
			t.setDescription(libelle);
			t.setCompte(compte);
			t.setMontant(montant);
			t.setReglement(Reglement.Cheque);
			t.setTypetransaction(TypeTransaction.Debit);
			t.setDate(new Date());
			t.setReference(reference);
			t.setDates(f.format(new Date()));
			t.setEtatcheque(Enumcheque.EnCirculation);
			serviceTransaction.save(t);
		}

		getDepenseCheque();
		return SUCCESS;
	}

	public String getDepenseCheque() {
		date1 = new Date();
		date1.setDate(1);
		listtypeDepense = new ArrayList<String>();
		listtypeDepense.add("DF BANQUE");
		listtypeDepense.add("Carte Bancaire");
		listtypeDepense.add("Cheque");
		date2 = new Date();
		montant = new BigDecimal(0);
		libelle = null;
		date = new Date();
		banque = null;
		dates = null;
		listDepense = new ArrayList<DepenseCheque>();
		listDepense = serviceDepensecheque.getdepensebetweendate(date1, date2);
		listesdepense = new ArrayList<Familledepensegestat>();
		listesdepense = serviceFamilleDepense.getAll();
		return SUCCESS;

	}

	public String getByDate() {
		montant = new BigDecimal(0);
		libelle = null;
		date = new Date();
		banque = null;
		dates = null;
		listDepense = new ArrayList<DepenseCheque>();
		listDepense = serviceDepensecheque.getdepensebetweendate(date1, date2);
		return SUCCESS;
	}

	public ServiceDepensecheque getServiceDepensecheque() {
		return serviceDepensecheque;
	}

	public void setServiceDepensecheque(ServiceDepensecheque serviceDepensecheque) {
		this.serviceDepensecheque = serviceDepensecheque;
	}

	public BigDecimal getMontant() {
		return montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public String getBanque() {
		return banque;
	}

	public void setBanque(String banque) {
		this.banque = banque;
	}

	public List<DepenseCheque> getListDepense() {
		return listDepense;
	}

	public void setListDepense(List<DepenseCheque> listDepense) {
		this.listDepense = listDepense;
	}

	public void setDepenseCheque(DepenseCheque depenseCheque) {
		this.depenseCheque = depenseCheque;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getFamille() {
		return famille;
	}

	public void setFamille(String famille) {
		this.famille = famille;
	}

	public List<Familledepensegestat> getListesdepense() {
		return listesdepense;
	}

	public void setListesdepense(List<Familledepensegestat> listesdepense) {
		this.listesdepense = listesdepense;
	}

	public ServiceFamilleDepensegestat getServiceFamilleDepense() {
		return serviceFamilleDepense;
	}

	public void setServiceFamilleDepense(ServiceFamilleDepensegestat serviceFamilleDepense) {
		this.serviceFamilleDepense = serviceFamilleDepense;
	}

	public ServiceCompte getServiceCompte() {
		return serviceCompte;
	}

	public void setServiceCompte(ServiceCompte serviceCompte) {
		this.serviceCompte = serviceCompte;
	}

	public ServiceTransaction getServiceTransaction() {
		return serviceTransaction;
	}

	public void setServiceTransaction(ServiceTransaction serviceTransaction) {
		this.serviceTransaction = serviceTransaction;
	}

	public List<String> getListtypeDepense() {
		return listtypeDepense;
	}

	public void setListtypeDepense(List<String> listtypeDepense) {
		this.listtypeDepense = listtypeDepense;
	}

	public String getTypeDepense() {
		return typeDepense;
	}

	public void setTypeDepense(String typeDepense) {
		this.typeDepense = typeDepense;
	}

}