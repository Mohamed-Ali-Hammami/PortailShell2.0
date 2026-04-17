package com.tn.shell.ui.facturation;

import java.text.DateFormat;
import java.text.DecimalFormat;
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

import org.apache.commons.dbcp.AbandonedConfig;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.dao.DataAccessException;

import com.tn.shell.model.gestat.Utilisateur;
import com.tn.shell.model.transport.*;
import com.tn.shell.service.gestat.UserService;
import com.tn.shell.service.transport.*;

 
@ManagedBean(name = "FamilledepensetransportBean")
@SessionScoped
public class FamilledepensetransportBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	
	@ManagedProperty(value = "#{ServiceFamilleDepense}")
	ServiceFamilleDepense serviceFamilledepense;

	@ManagedProperty(value = "#{ServiceDepense}")
	ServiceDepense serviceDepense;
	
	@ManagedProperty(value = "#{ServiceVhecule}")
	ServiceVhecule serviceVehecule;
	
	@ManagedProperty(value = "#{UserService}")
	UserService userService;
	@ManagedProperty(value = "#{ServiceTracetransport}")
	ServiceTracetransport serviceTrace;

 
	/*
	
	@ManagedProperty(value = "#{ServiceChauffeur}")
	ServiceChauffeur serviceChauffeur;
	
	@ManagedProperty(value = "#{ServiceBonLivraison}")
	ServiceBonLivraison serviceBonLivraison;*/
	
	private Date date1;
	private Date date2;
	private String dates2;
	private String dates1;
	private List<Familledepensetransport> filterfamilledepenses;
	private List<Depense> filterdepenses;
	private List<String> listesdepenses;
	private List<String> listesvhecules;
	private String libelle;
	private double montant;
	private String libelledepense;
	private Integer quantite;
	private Date date;
	private Familledepensetransport familledepense;
	private Familledepensetransport familledepense2;
	private List<Familledepensetransport> listfamilledepense;
	private Depense depense;
	private Depense depense2;
	private List<Depense> listdepense;
	private String matricule;
    private List<Vhecule> listesvhecule;
	private double totaltransport;
	private double totaldepense;
	private double totalcarburant;
	private double totalautrecarburant;
	private double totalsalaire;
	private double totaldf;	
	private String totaltransports;
	private String totaldepenses;	 
	private String totalcarburants;
	private String totalautrecarburants;
	private String totalsalaires;
	private String totaldfs;
	private List<Rapportcamion> listcarburant;
	
	/*public String rapportcamion() {
		listdepense=new ArrayList<Depense>();
		listesvhecule=new ArrayList<Vhecule>();
		listesvhecule=serviceVehecule.getAll();
		date1=new Date();
		date2=new Date();
		listcarburant=new ArrayList<Rapportcamion>();
		totalcarburant=0;
		totalautrecarburant=0;
		totalsalaire=0;
		totaldepense=0;
		totaltransport=0;
		totaldf=0;
		return SUCCESS;
	}
	public String calculrapportcamion() {
		listcarburant=new ArrayList<Rapportcamion>();
		totalcarburant=0;
		for(Vhecule v:listesvhecule) {
			Rapportcamion r=new Rapportcamion();
			r.setMatricule(v.getMatricule());
			r.setDepensecarburant(serviceDepense.getdepensebyvehicule(v,date1,date2));
			r.setAutredepense(serviceDepense.getdepenseautrebyvehicule(v,date1,date2));
			 
			if(v.getMatricule().equals("Iveco 139")) {
				r.setChauffeur("MONCEF");
				r.setSalairchauffeur(serviceChauffeur.Findbymf("MONCEF").getSalaire());
			}
			else if(v.getMatricule().equals("D MAX 121")) {
				r.setChauffeur("MOHAMED");
				r.setSalairchauffeur(serviceChauffeur.Findbymf("MOHAMED JRIDI").getSalaire());
			}
			
			else if(v.getMatricule().equals("MIT 136")) {
				r.setChauffeur("MONDHER");
				r.setSalairchauffeur(serviceChauffeur.Findbymf("MONDHER AOUICHI").getSalaire());
			}
			
			else if(v.getMatricule().equals("DIMAX 151")) {
				r.setChauffeur("SOFIENNE");
				r.setSalairchauffeur(serviceChauffeur.Findbymf("TAYSSIR SASSI/SOFIENNE").getSalaire());
			}
			else {
				r.setChauffeur("");
			    r.setSalairchauffeur(0);
			}
			 
			 r.setTotaldepense(r.getDepensecarburant()+r.getAutredepense()+r.getSalairchauffeur());
			 r.setTotaltransport(serviceBonLivraison.gettotaltransport(v, date1, date2));
			 r.setDf(r.getTotaltransport()-r.getTotaldepense());
			 
			listcarburant.add(r);
			totalcarburant=totalcarburant+r.getDepensecarburant();
			totalautrecarburant=totalautrecarburant+r.getAutredepense();
			totalsalaire=totalsalaire+r.getSalairchauffeur();
			totaldepense=totaldepense+r.getTotaldepense();
			totaltransport=totaltransport+r.getTotaltransport();
			totaldf=totaldf+r.getDf();
		}
		return SUCCESS;
	}*/
	public String getfamilledepense() {
		listfamilledepense = new ArrayList<Familledepensetransport>();
		listfamilledepense = serviceFamilledepense.getAll();
		return SUCCESS;
	}

	public String getdepense() {
		List<Familledepensetransport> l = new ArrayList<Familledepensetransport>();
		listesdepenses = new ArrayList<String>();
		l = serviceFamilledepense.getAll();
		if (l != null)
			for (Familledepensetransport d : l)
				listesdepenses.add(d.getLibelle());

		List<Vhecule> V = new ArrayList<Vhecule>();
		V = serviceVehecule.getAll();
		listesvhecules = new ArrayList<String>();
		for (Vhecule v : V)
			listesvhecules.add(v.getMatricule());
		listdepense = new ArrayList<Depense>();
		listdepense = serviceDepense.getAll();
		return SUCCESS;
	}

	public String nouvaufamilledepense() {
		libelle = null;

		return SUCCESS;
	}

	public String nouvaudepense() {
		List<Familledepensetransport> l = new ArrayList<Familledepensetransport>();
		listesdepenses = new ArrayList<String>();
		l = serviceFamilledepense.getAll();
		if (l != null)
			for (Familledepensetransport d : l)
				listesdepenses.add(d.getLibelle());
		List<Vhecule> V = new ArrayList<Vhecule>();
		V = serviceVehecule.getAll();
		listesvhecules = new ArrayList<String>();
		for (Vhecule v : V)
			listesvhecules.add(v.getMatricule());
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

		Vhecule v = serviceVehecule.Findbynom(matricule);

		depense = new Depense();
		depense.setLibelle(libelle);
		depense.setDate(date);
		depense.setMontant(montant);
		if (familledepense != null)
			depense.setFamilledepense(familledepense);
		depense.setVhecule(v);
		serviceDepense.save(depense);
		listdepense = new ArrayList<Depense>();
		listdepense = serviceDepense.getAll();
		Utilisateur user=userService.getCurrentUser();
	       Tracetransport t=new Tracetransport();
	       t.setAction(user.getNom() +"a ajouter une depense  de la vhecule"+matricule+" a "+new Date());
	       t.setDate(new Date());
	        serviceTrace.save(t);
		return SUCCESS;

	}

	public String updateDepense(Depense depense) {
		try {
			getServiceDepense().update(depense);
			
			Utilisateur user=userService.getCurrentUser();
		       Tracetransport t=new Tracetransport();
		       t.setAction(user.getNom() +"a modefier une depense  de la vhecule"+depense.getVhecule().getMatricule()+" a "+new Date());
		       t.setDate(new Date());
		        serviceTrace.save(t);
			return SUCCESS;
		} catch (DataAccessException e) {
		}
		return ERROR;
	}

	public void onRowEdit(RowEditEvent event) {

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Depense changÃ©",
				((Depense) event.getObject()).getLibelle());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		updateDepense((Depense) event.getObject());

	}

	public String savefamilledepense() {

		// if (familledepense == null) {
		familledepense = new Familledepensetransport();
		familledepense.setLibelle(libelle);
		serviceFamilledepense.save(familledepense);
		listfamilledepense = new ArrayList<Familledepensetransport>();
		listfamilledepense = serviceFamilledepense.getAll();
		Utilisateur user=userService.getCurrentUser();
	       Tracetransport t=new Tracetransport();
	       t.setAction(user.getNom() +"a ajouter une famille depense "+libelle+" a "+new Date());
	       t.setDate(new Date());
	        serviceTrace.save(t);
		return SUCCESS;

	}
	/*
	 * 
	 * public String updateVhecule(Familledepense familledepense) { try {
	 * getServiceFamilledepense().update(familledepense); return SUCCESS; } catch
	 * 
	 * public void onRowEdit(RowEditEvent event) {
	 * 
	 * FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
	 * "Famille depense changÃ©", ((Familledepense) event.getObject()).getLibelle());
	 * FacesContext.getCurrentInstance().addMessage(null, msg);
	 * updateFamilledepense((Familledepense) event.getObject());
	 * 
	 * }
	 */

	public String deletedepense() {
		depense2.setStatut(Statut.DEACTIF);
		serviceDepense.delete(depense2);
		listdepense = new ArrayList<Depense>();
		listdepense = serviceDepense.getAll();
		
		Utilisateur user=userService.getCurrentUser();
	       Tracetransport t=new Tracetransport();
	       t.setAction(user.getNom() +"a supprimer une depense "+depense2.getLibelle()+" a "+new Date());
	       t.setDate(new Date());
	       serviceTrace.save(t);
		return SUCCESS;
	}

	public ServiceFamilleDepense getServiceFamilledepense() {
		return serviceFamilledepense;
	}

	public void setServiceFamilledepense(ServiceFamilleDepense serviceFamilledepense) {
		this.serviceFamilledepense = serviceFamilledepense;
	}

	public List<Depense> getFilterdepenses() {
		return filterdepenses;
	}

	public void setFilterdepenses(List<Depense> filterdepenses) {
		this.filterdepenses = filterdepenses;
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

	public Depense getDepense() {
		return depense;
	}

	public void setDepense(Depense depense) {
		this.depense = depense;
	}

	public Depense getDepense2() {
		return depense2;
	}

	public void setDepense2(Depense depense2) {
		this.depense2 = depense2;
	}

	public List<Depense> getListdepense() {
		return listdepense;
	}

	public void setListdepense(List<Depense> listdepense) {
		this.listdepense = listdepense;
	}

	public ServiceTracetransport getServiceTrace() {
		return serviceTrace;
	}

	public void setServiceTrace(ServiceTracetransport serviceTrace) {
		this.serviceTrace = serviceTrace;
	}

	public List<Familledepensetransport> getFilterfamilledepenses() {
		return filterfamilledepenses;
	}

	public void setFilterfamilledepenses(List<Familledepensetransport> filterfamilledepenses) {
		this.filterfamilledepenses = filterfamilledepenses;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Familledepensetransport getFamilledepense() {
		return familledepense;
	}

	public void setFamilledepense(Familledepensetransport familledepense) {
		this.familledepense = familledepense;
	}

	public Familledepensetransport getFamilledepense2() {
		return familledepense2;
	}

	public void setFamilledepense2(Familledepensetransport familledepense2) {
		this.familledepense2 = familledepense2;
	}

	public List<Familledepensetransport> getListfamilledepense() {
		return listfamilledepense;
	}

	public void setListfamilledepense(List<Familledepensetransport> listfamilledepense) {
		this.listfamilledepense = listfamilledepense;
	}

	public ServiceDepense getServiceDepense() {
		return serviceDepense;
	}

	public void setServiceDepense(ServiceDepense serviceDepense) {
		this.serviceDepense = serviceDepense;
	}

	public List<String> getListesdepenses() {
		return listesdepenses;
	}

	public void setListesdepenses(List<String> listesdepenses) {
		this.listesdepenses = listesdepenses;
	}

	public ServiceVhecule getServiceVehecule() {
		return serviceVehecule;
	}

	public void setServiceVehecule(ServiceVhecule serviceVehecule) {
		this.serviceVehecule = serviceVehecule;
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
	public String getDates2() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates2= s.format(date2);
		 
		return dates2;
	}
	public void setDates2(String dates2) {
		this.dates2 = dates2;
	}
	public String getDates1() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates1= s.format(date1);
		 
		return dates1;
	}
	public void setDates1(String dates1) {
		this.dates1 = dates1;
	}
	public List<Vhecule> getListesvhecule() {
		return listesvhecule;
	}
	public void setListesvhecule(List<Vhecule> listesvhecule) {
		this.listesvhecule = listesvhecule;
	}
	public double getTotaltransport() {
		return totaltransport;
	}
	public void setTotaltransport(double totaltransport) {
		this.totaltransport = totaltransport;
	}
	public double getTotaldepense() {
		return totaldepense;
	}
	public void setTotaldepense(double totaldepense) {
		this.totaldepense = totaldepense;
	}
	public String getTotaltransports() {
		DecimalFormat df=new DecimalFormat("0.000");
		totaltransports=df.format(totaltransport);
		return totaltransports;
	}
	public void setTotaltransports(String totaltransports) {
		this.totaltransports = totaltransports;
	}
	public String getTotaldepenses() {
		DecimalFormat df=new DecimalFormat("0.000");
		totaldepenses=df.format(totaldepense);
		return totaldepenses;
	}
	public void setTotaldepenses(String totaldepenses) {
		this.totaldepenses = totaldepenses;
	}
	/*public ServiceBonLivraison getServiceBonLivraison() {
		return serviceBonLivraison;
	}
	public void setServiceBonLivraison(ServiceBonLivraison serviceBonLivraison) {
		this.serviceBonLivraison = serviceBonLivraison;
	}*/
	public List<Rapportcamion> getListcarburant() {
		return listcarburant;
	}
	public void setListcarburant(List<Rapportcamion> listcarburant) {
		this.listcarburant = listcarburant;
	}
	/*public ServiceChauffeur getServiceChauffeur() {
		return serviceChauffeur;
	}
	public void setServiceChauffeur(ServiceChauffeur serviceChauffeur) {
		this.serviceChauffeur = serviceChauffeur;
	}*/
	public double getTotalcarburant() {
		return totalcarburant;
	}
	public void setTotalcarburant(double totalcarburant) {
		this.totalcarburant = totalcarburant;
	}
	public double getTotalautrecarburant() {
		return totalautrecarburant;
	}
	public void setTotalautrecarburant(double totalautrecarburant) {
		this.totalautrecarburant = totalautrecarburant;
	}
	public double getTotalsalaire() {
		return totalsalaire;
	}
	public void setTotalsalaire(double totalsalaire) {
		this.totalsalaire = totalsalaire;
	}
	public double getTotaldf() {
		return totaldf;
	}
	public void setTotaldf(double totaldf) {
		this.totaldf = totaldf;
	}
	public String getTotalcarburants() {
		DecimalFormat df=new DecimalFormat("0.000");
		totalcarburants=df.format(totalcarburant);
		return totalcarburants;
	}
	public void setTotalcarburants(String totalcarburants) {
		
		this.totalcarburants = totalcarburants;
	}
	public String getTotalautrecarburants() {
		DecimalFormat df=new DecimalFormat("0.000");
		totalautrecarburants=df.format(totalautrecarburant);
		return totalautrecarburants;
	}
	public void setTotalautrecarburants(String totalautrecarburants) {
		this.totalautrecarburants = totalautrecarburants;
	}
	public String getTotalsalaires() {
		DecimalFormat df=new DecimalFormat("0.000");
		totalsalaires=df.format(totalsalaire);
		return totalsalaires;
	}
	public void setTotalsalaires(String totalsalaires) {
		this.totalsalaires = totalsalaires;
	}
	public String getTotaldfs() {
		DecimalFormat df=new DecimalFormat("0.000");
		totaldfs=df.format(totaldf);
		return totaldfs;
	}
	public void setTotaldfs(String totaldfs) {
		this.totaldfs = totaldfs;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
