package com.tn.shell.ui.gestat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.springframework.dao.DataAccessException;

import com.tn.shell.model.gestat.Carteclient;
import com.tn.shell.model.gestat.Clientgestat;
import com.tn.shell.model.gestat.Statut;
import com.tn.shell.service.gestat.ServiceCarteclient;
import com.tn.shell.service.gestat.ServiceClientgestat;

@ManagedBean(name = "CarteClientBean")
@SessionScoped
public class CarteClientBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{ServiceCarteClient}")
	ServiceCarteclient serviceCarteclient;

	@ManagedProperty(value = "#{ServiceClientgestat}")
	ServiceClientgestat serviceClientgestat;

	private Integer id;
	private Integer codeclient;
	private String chauffeur;
	private String vehicule;
	private double plafond;
	private Date dateaffectation;
	private boolean affectee;
	private String dates;
	private String nomclient;

	private Carteclient carte;
	private Carteclient carte2;
	private List<Carteclient> listcartes;
	private List<Carteclient> listhistorique;
	private List<Carteclient> filtercartes;
	private List<Clientgestat> listclients;

	public String getcarteClient() {
		listcartes = new ArrayList<Carteclient>();
		listcartes = serviceCarteclient.getAll();
		listclients = new ArrayList<Clientgestat>();
		listclients = serviceClientgestat.getAll();
		return SUCCESS;
	}

	public String addcarteClient() {
		id = null;
		codeclient = null;
		chauffeur = "";
		vehicule = "";
		plafond = 0;
		affectee = false;
		dateaffectation = new Date();
		listclients = new ArrayList<Clientgestat>();
		listclients = serviceClientgestat.getAll();
		return SUCCESS;
	}

	public String affecterCarte() {
		listcartes = new ArrayList<Carteclient>();
		listcartes = serviceCarteclient.getAll();
		listclients = new ArrayList<Clientgestat>();
		listclients = serviceClientgestat.getAll();
		if (dateaffectation == null) {
			dateaffectation = new Date();
		}
		return SUCCESS;
	}

	public String historiquecarteClient() {
		listhistorique = new ArrayList<Carteclient>();
		listhistorique = serviceCarteclient.getAllhistory();
		listclients = new ArrayList<Clientgestat>();
		listclients = serviceClientgestat.getAll();
		return SUCCESS;
	}

	public String savecarteClient() {
		if (codeclient == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation", "Client obligatoire"));
			return ERROR;
		}
		if (plafond <= 0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation", "Plafond doit etre strictement positif"));
			return ERROR;
		}
		if (dateaffectation == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation", "Date d'affectation obligatoire"));
			return ERROR;
		}

		Carteclient c = new Carteclient();
		Clientgestat client = serviceClientgestat.Findbycode(codeclient);
		if (client == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Client introuvable"));
			return ERROR;
		}
		c.setClient(client);
		c.setPlafond(plafond);
		c.setChauffeur(chauffeur);
		c.setVehicule(vehicule);
		c.setDateaffectation(dateaffectation);
		c.setAffectee(affectee || (chauffeur != null && !chauffeur.trim().isEmpty())
				|| (vehicule != null && !vehicule.trim().isEmpty()));
		serviceCarteclient.save(c);
		return SUCCESS;
	}

	public void saveAffectation() {
		if (id == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation", "Carte obligatoire"));
			return;
		}
		if (isBlank(chauffeur)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation", "Chauffeur obligatoire"));
			return;
		}
		if (isBlank(vehicule)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation", "Vehicule obligatoire"));
			return;
		}
		if (dateaffectation == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation", "Date d'affectation obligatoire"));
			return;
		}

		Carteclient current = serviceCarteclient.Findbycode(id);
		if (current == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Carte introuvable"));
			return;
		}
		if (codeclient != null) {
			Clientgestat client = serviceClientgestat.Findbycode(codeclient);
			current.setClient(client);
		}
		current.setChauffeur(chauffeur);
		current.setVehicule(vehicule);
		current.setDateaffectation(dateaffectation == null ? new Date() : dateaffectation);
		current.setAffectee(true);
		serviceCarteclient.update(current);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes", "Affectation enregistree"));
		listcartes = serviceCarteclient.getAll();
	}

	public String filterHistoriqueByClient() {
		if (listhistorique == null) {
			listhistorique = new ArrayList<Carteclient>();
		}
		if (codeclient == null) {
			listhistorique = serviceCarteclient.getAllhistory();
			return SUCCESS;
		}
		Clientgestat client = serviceClientgestat.Findbycode(codeclient);
		if (client == null) {
			listhistorique = serviceCarteclient.getAllhistory();
			return SUCCESS;
		}
		listhistorique = serviceCarteclient.getByClient(client);
		return SUCCESS;
	}

	public String updateCarte(Carteclient c) {
		try {
			if (c.getPlafond() < 0) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Validation", "Plafond doit etre superieur ou egal a 0"));
				return ERROR;
			}
			boolean hasChauffeur = !isBlank(c.getChauffeur());
			boolean hasVehicule = !isBlank(c.getVehicule());
			if (hasChauffeur ^ hasVehicule) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Validation", "Chauffeur et vehicule doivent etre renseignes ensemble"));
				return ERROR;
			}
			c.setAffectee((c.getChauffeur() != null && !c.getChauffeur().trim().isEmpty())
					|| (c.getVehicule() != null && !c.getVehicule().trim().isEmpty()));
			serviceCarteclient.update(c);
			return SUCCESS;
		} catch (DataAccessException e) {
			return ERROR;
		}
	}

	public void onRowEdit(RowEditEvent event) {
		Carteclient updated = (Carteclient) event.getObject();
		String outcome = updateCarte(updated);
		FacesMessage msg;
		if (SUCCESS.equals(outcome)) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Carte modifiee", String.valueOf(updated.getId()));
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Modification refusee", String.valueOf(updated.getId()));
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String deletecarteClient() {
		if (carte2 != null) {
			carte2.setStatut(Statut.DEACTIF);
			serviceCarteclient.delete(carte2);
		}
		listcartes = new ArrayList<Carteclient>();
		listcartes = serviceCarteclient.getAll();
		return SUCCESS;
	}

	public String getDates() {
		if (dateaffectation == null) {
			return "";
		}
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(dateaffectation);
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public ServiceCarteclient getServiceCarteclient() {
		return serviceCarteclient;
	}

	public void setServiceCarteclient(ServiceCarteclient serviceCarteclient) {
		this.serviceCarteclient = serviceCarteclient;
	}

	public ServiceClientgestat getServiceClientgestat() {
		return serviceClientgestat;
	}

	public void setServiceClientgestat(ServiceClientgestat serviceClientgestat) {
		this.serviceClientgestat = serviceClientgestat;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodeclient() {
		return codeclient;
	}

	public void setCodeclient(Integer codeclient) {
		this.codeclient = codeclient;
	}

	public String getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(String chauffeur) {
		this.chauffeur = chauffeur;
	}

	public String getVehicule() {
		return vehicule;
	}

	public void setVehicule(String vehicule) {
		this.vehicule = vehicule;
	}

	public double getPlafond() {
		return plafond;
	}

	public void setPlafond(double plafond) {
		this.plafond = plafond;
	}

	public Date getDateaffectation() {
		return dateaffectation;
	}

	public void setDateaffectation(Date dateaffectation) {
		this.dateaffectation = dateaffectation;
	}

	public boolean isAffectee() {
		return affectee;
	}

	public void setAffectee(boolean affectee) {
		this.affectee = affectee;
	}

	public String getNomclient() {
		return nomclient;
	}

	public void setNomclient(String nomclient) {
		this.nomclient = nomclient;
	}

	public Carteclient getCarte() {
		return carte;
	}

	public void setCarte(Carteclient carte) {
		this.carte = carte;
	}

	public Carteclient getCarte2() {
		return carte2;
	}

	public void setCarte2(Carteclient carte2) {
		this.carte2 = carte2;
	}

	public List<Carteclient> getListcartes() {
		if (listcartes == null) {
			listcartes = new ArrayList<Carteclient>();
			listcartes = serviceCarteclient.getAll();
		}
		return listcartes;
	}

	public void setListcartes(List<Carteclient> listcartes) {
		this.listcartes = listcartes;
	}

	public List<Carteclient> getListhistorique() {
		if (listhistorique == null) {
			listhistorique = new ArrayList<Carteclient>();
			listhistorique = serviceCarteclient.getAllhistory();
		}
		return listhistorique;
	}

	public void setListhistorique(List<Carteclient> listhistorique) {
		this.listhistorique = listhistorique;
	}

	public List<Carteclient> getFiltercartes() {
		return filtercartes;
	}

	public void setFiltercartes(List<Carteclient> filtercartes) {
		this.filtercartes = filtercartes;
	}

	public List<Clientgestat> getListclients() {
		if (listclients == null) {
			listclients = new ArrayList<Clientgestat>();
			listclients = serviceClientgestat.getAll();
		}
		return listclients;
	}

	public void setListclients(List<Clientgestat> listclients) {
		this.listclients = listclients;
	}

	private boolean isBlank(String value) {
		return value == null || value.trim().isEmpty();
	}
}
