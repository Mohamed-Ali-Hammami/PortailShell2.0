package com.tn.shell.ui.gestat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.dao.DataAccessException;

import com.tn.shell.model.gestat.Depensegestat;
import com.tn.shell.model.gestat.Familledepensegestat;
import com.tn.shell.model.gestat.Statut;
import com.tn.shell.service.gestat.ServiceDepensegestat;
import com.tn.shell.service.gestat.ServiceFamilleDepensegestat;

 

@ManagedBean(name = "FamilledepenseBean")
@SessionScoped
public class FamilledepenseBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{ServiceFamilleDepensegestat}")
	ServiceFamilleDepensegestat serviceFamilledepense;

	@ManagedProperty(value = "#{ServiceDepensegestat}")
	ServiceDepensegestat serviceDepense;

	private Date date1;
	private Date date2;
	private Integer codes;
	private List<Familledepensegestat> filterfamilledepenses;
	private List<Depensegestat> filterdepenses;
	private List<String> listesdepenses;
	private List<String> listesvhecules;
	private String libelle;
	private double montant;
	private String libelledepense;
	private Integer quantite;
	private Date date;

	private Familledepensegestat familledepense;
	private Familledepensegestat familledepense2;
	private List<Familledepensegestat> listfamilledepense;

	private Depensegestat depense;
	private Depensegestat depense2;
	private List<Depensegestat> listdepense;

	private String matricule;

	public String getfamilledepense() {
		listfamilledepense = new ArrayList<Familledepensegestat>();
		listfamilledepense = serviceFamilledepense.getAll();
		return SUCCESS;
	}

	public String getdepense() {
		List<Familledepensegestat> l = new ArrayList<Familledepensegestat>();
		listesdepenses = new ArrayList<String>();
		l = serviceFamilledepense.getAll();
		if (l != null)
			for (Familledepensegestat d : l)
				listesdepenses.add(d.getLibelle());

		listdepense = new ArrayList<Depensegestat>();
		listdepense = serviceDepense.getAll();
		return SUCCESS;
	}

	public String nouvaufamilledepense() {
		libelle = null;

		return SUCCESS;
	}

	public void onCellEdit1(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			Familledepensegestat d = listfamilledepense.get(event.getRowIndex());
			d.setLibelle((String) newValue);
			serviceFamilledepense.update(d);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
					"Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			System.out.println("newValue"+newValue);
		}

	}

	public String nouvaudepense() {
		List<Familledepensegestat> l = new ArrayList<Familledepensegestat>();
		listesdepenses = new ArrayList<String>();
		l = serviceFamilledepense.getAll();
		if (l != null)
			for (Familledepensegestat d : l)
				listesdepenses.add(d.getLibelle());

		montant = 0;
		libelledepense = null;
		quantite = 0;
		libelle = null;
		matricule = null;
		libelle = null;
		date = new Date();
		date.setDate(date.getDate() - 1);

		return SUCCESS;
	}

	public String savedepense() {
		familledepense = serviceFamilledepense.getFamilebyeibelle(libelle);

		depense = new Depensegestat();
		depense.setLibelle(libelle);
		depense.setDate(date);
		depense.setMontant(montant);
		if (familledepense != null)
			depense.setFamilledepense(familledepense);

		serviceDepense.save(depense);
		listdepense = new ArrayList<Depensegestat>();
		listdepense = serviceDepense.getAll();

		return SUCCESS;

	}

	public String updateDepense(Depensegestat depense) {
		try {
			getServiceDepense().update(depense);
			return SUCCESS;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	public void onRowEdit(RowEditEvent event) {

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Depense change",
				((Depensegestat) event.getObject()).getLibelle());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		updateDepense((Depensegestat) event.getObject());

	}

	public String savefamilledepense() {

		// if (familledepense == null) {
		familledepense = new Familledepensegestat();
		familledepense.setLibelle(libelle);
		serviceFamilledepense.save(familledepense);
		listfamilledepense = new ArrayList<Familledepensegestat>();
		listfamilledepense = serviceFamilledepense.getAll();

		return SUCCESS;

	}
	/*
	 * 
	 * public String updateVhecule(Familledepense familledepense) { try {
	 * getServiceFamilledepense().update(familledepense); return SUCCESS; } catch
	 * (DataAccessException e) { e.printStackTrace(); } return ERROR; }
	 * 
	 * public void onRowEdit(RowEditEvent event) {
	 * 
	 * FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
	 * "Famille depense change", ((Familledepense) event.getObject()).getLibelle());
	 * FacesContext.getCurrentInstance().addMessage(null, msg);
	 * updateFamilledepense((Familledepense) event.getObject());
	 * 
	 * }
	 */

	public String deletedepense() {
		depense2.setStatut(Statut.DEACTIF);
		serviceDepense.delete(depense2);
		listdepense = new ArrayList<Depensegestat>();
		listdepense = serviceDepense.getAll();
		return SUCCESS;
	}

	 
 
	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getLibelledepense() {
		return libelledepense;
	}

	public void setLibelledepense(String libelledepense) {
		this.libelledepense = libelledepense;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

 
 

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	 

	 

	public ServiceFamilleDepensegestat getServiceFamilledepense() {
		return serviceFamilledepense;
	}

	public void setServiceFamilledepense(ServiceFamilleDepensegestat serviceFamilledepense) {
		this.serviceFamilledepense = serviceFamilledepense;
	}

	public ServiceDepensegestat getServiceDepense() {
		return serviceDepense;
	}

	public void setServiceDepense(ServiceDepensegestat serviceDepense) {
		this.serviceDepense = serviceDepense;
	}

	public List<Familledepensegestat> getFilterfamilledepenses() {
		return filterfamilledepenses;
	}

	public void setFilterfamilledepenses(List<Familledepensegestat> filterfamilledepenses) {
		this.filterfamilledepenses = filterfamilledepenses;
	}

	public List<Depensegestat> getFilterdepenses() {
		return filterdepenses;
	}

	public void setFilterdepenses(List<Depensegestat> filterdepenses) {
		this.filterdepenses = filterdepenses;
	}

	public Familledepensegestat getFamilledepense() {
		return familledepense;
	}

	public void setFamilledepense(Familledepensegestat familledepense) {
		this.familledepense = familledepense;
	}

	public Familledepensegestat getFamilledepense2() {
		return familledepense2;
	}

	public void setFamilledepense2(Familledepensegestat familledepense2) {
		this.familledepense2 = familledepense2;
	}

	public List<Familledepensegestat> getListfamilledepense() {
		return listfamilledepense;
	}

	public void setListfamilledepense(List<Familledepensegestat> listfamilledepense) {
		this.listfamilledepense = listfamilledepense;
	}

	public Depensegestat getDepense() {
		return depense;
	}

	public void setDepense(Depensegestat depense) {
		this.depense = depense;
	}

	public Depensegestat getDepense2() {
		return depense2;
	}

	public void setDepense2(Depensegestat depense2) {
		this.depense2 = depense2;
	}

	public List<Depensegestat> getListdepense() {
		return listdepense;
	}

	public void setListdepense(List<Depensegestat> listdepense) {
		this.listdepense = listdepense;
	}

	public List<String> getListesdepenses() {
		return listesdepenses;
	}

	public void setListesdepenses(List<String> listesdepenses) {
		this.listesdepenses = listesdepenses;
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

	public List<String> getListesvhecules() {
		return listesvhecules;
	}

	public void setListesvhecules(List<String> listesvhecules) {
		this.listesvhecules = listesvhecules;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public Integer getCodes() {
		return codes;
	}

	public void setCodes(Integer codes) {
		this.codes = codes;
	}

}
