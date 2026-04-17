package com.tn.shell.ui.transport;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.ParseException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.dao.DataAccessException;

import com.tn.shell.model.gestat.Utilisateur;
import com.tn.shell.model.paie.Societe;
import com.tn.shell.model.shop.Paramettre;
import com.tn.shell.model.shop.Produit;
import com.tn.shell.model.shop.Tva;
import com.tn.shell.model.transport.*;
import com.tn.shell.service.gestat.UserService;
import com.tn.shell.service.shop.ServiceParamettre;
import com.tn.shell.service.shop.ServiceProduit;
import com.tn.shell.service.shop.ServiceTva;
import com.tn.shell.service.transport.*;
import com.tn.shell.ui.common.UiDateDefaults;

@ManagedBean(name = "FactureBean")
@SessionScoped
public class FactureBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	@ManagedProperty(value = "#{ServiceChauffeur}")
	ServiceChauffeur serviceChauffeur;
	@ManagedProperty(value = "#{ServiceFacture}")
	ServiceFacture servicefacture;
	@ManagedProperty(value = "#{ServiceBonLivraison}")
	ServiceBonLivraison serviceBonLivraison;
	@ManagedProperty(value = "#{ServiceProduit}")
	ServiceProduit serviceProduit;
	@ManagedProperty(value = "#{ServiceTracetransport}")
	ServiceTracetransport serviceTrace;
	@ManagedProperty(value = "#{ServiceClient}")
	com.tn.shell.service.transport.ServiceClient serviceclients;
	@ManagedProperty(value = "#{ServiceVhecule}")
	ServiceVhecule serviceVhecule;
	@ManagedProperty(value = "#{ServiceLigneCommande}")
	ServiceLigneCommande serviceLigneCommande;
	@ManagedProperty(value = "#{ServiceLigneCommandepass}")
	ServiceLigneCommandepass serviceLigneCommandepass;
	@ManagedProperty(value = "#{ServiceFacture}")
	ServiceFacture serviceFacture;
	@ManagedProperty(value = "#{ServiceParamettre}")
	ServiceParamettre serviceParamettre;
	@ManagedProperty(value = "#{ServiceTva}")
	ServiceTva serviceTva;
	@ManagedProperty(value = "#{ServiceFacturepassager}")
	ServiceFacturepassager servicefacturepassager;

	@ManagedProperty(value = "#{UserService}")
	UserService userService;
	

	 
	/*
	 * @ManagedProperty(value = "#{ServiceSociete}") ServiceSociete serviceSociete;
	 */

	private Societe societe;
	private List<Produit> listprosuit = new ArrayList<Produit>();
	private List<String> listProduitFini = new ArrayList<String>();
	private Paramettre paramettre;

	private List<Facture> listfacture;
	private List<Facture> filterfactures;

	private List<Facturepassager> listfacturepass;
	private List<Facturepassager> filterfacturepass;

	private List<Bonlivraison> listbonlivraison;
	private Date date;
	private Date date2;
	private Facture facture;

	private List<Bonlivraison> filterbondelivraisons;
	private Facture selectedfacture;
	private Bonlivraison selectedBL;
	private Bonlivraison bl;
	private List<Bonlivraison> selectedsBl;
	private String nom;
	private Client client;
	List<Lignecommande> listeligne;
	private List<String> listClient;
	private Status[] etats;
	private Status statut;
	private float timbres;
	private Lignecommande ligneC;
	private boolean test;
	private List<Lignecommande> listelignefacture;
	private List<Lignecommandepass> listelignefacturepass;
	private List<Bonlivraison> listbonlivraisonbyClient;
	private List<Facture> listfacturebyClient;
	private String nompourfacture;
	private Client client2;
	private Status statutfacture;
	private String typepayement;
	private Integer codes;
	private List<String> listProduitfini = new ArrayList<String>();
	private Lignecommande selectedLignecommnade;
	private List<String> listeclient;
	private String clients;
	private String designation;
	private Integer quantite;
	private Produit produit;
	private String code;
	private Facturepassager facturepassager;
	private List<Vhecule> listeVehicules;
	private List<Chauffeur> listeChauffeurs;
	private List<Produit> listeProduits;
	private List<Produit> listproduits;
	private List<Client> listeClients;
	private Vhecule vhecule;
	private Chauffeur chauffeur;
	private double transport;
	private String codevhecule;
	private String codeclient;
	private String codechaffeur;
	private Integer codeproduit;
	private Integer qte;
	private double montantht;
	private Integer tva;
	private double pu;
	private double totalttc;
	private String totalttcs;
    
	@PostConstruct
	public void init() {
		etats = Status.values();

		listproduits = new ArrayList<Produit>();
		listproduits = serviceProduit.getAll();
		initializeTransportDateRange(resolveLatestTransportDate());
	}

	public void getProduitbydesignation() {
		produit = serviceProduit.getProduitbydesignation(designation);
	}

	public String getBLnonfacturee() {
		listbonlivraison = new ArrayList<Bonlivraison>();
		listbonlivraison = serviceBonLivraison.getBLbystatuts(Status.NonFacturee);
		return SUCCESS;
	}

	public String getBLnonfacturee2() {
		listbonlivraison = new ArrayList<Bonlivraison>();
		if (clients != null && !clients.trim().isEmpty() && !"selectionner client".equals(clients)) {
			listbonlivraison = serviceBonLivraison.getBLbystatutsandclient(Status.NonFacturee, clients);
		} else {
			listbonlivraison = serviceBonLivraison.getBLbystatuts(Status.NonFacturee);
		}
		return SUCCESS;
	}

	public String getAllPasager() {
		listeClients = new ArrayList<Client>();
		listeClients = serviceclients.getAll();
		facturepassager = null;
		listfacturepass = new ArrayList<Facturepassager>();
		initializeTransportDateRange(resolveLatestPassengerDate());
		nom = null;
		listfacturepass = servicefacturepassager.getfacturebetwenndate(date, date2);
		return SUCCESS;
	}

	public String getAllTransport() {
		selectedfacture = null;
		initializeTransportDateRange(resolveLatestTransportDate());
		nom = null;
		listeClients = new ArrayList<Client>();
		listeClients = serviceclients.getAll();
		listfacture = new ArrayList<Facture>();
		List<Facture> result = servicefacture.getfacturebetwenndate(date, date2);
		if (result != null) {
			listfacture = result;
		}
		return SUCCESS;
	}

	private void initializeTransportDateRange(Date businessDate) {
		date = UiDateDefaults.startOfDay(businessDate);
		date2 = UiDateDefaults.endOfDay(businessDate);
	}

	private Date resolveLatestTransportDate() {
		Facture latestFacture = servicefacture.getMaxfacture();
		if (latestFacture != null && latestFacture.getDate() != null) {
			return latestFacture.getDate();
		}
		return new Date();
	}

	private Date resolveLatestPassengerDate() {
		Facturepassager latestFacture = servicefacturepassager.getMaxfacture();
		if (latestFacture != null && latestFacture.getDate() != null) {
			return latestFacture.getDate();
		}
		return new Date();
	}

	public String modifierFacture() {
		listeChauffeurs = new ArrayList<Chauffeur>();
     
		listeChauffeurs = serviceChauffeur.getAll();
		listeVehicules = new ArrayList<Vhecule>();
		listeVehicules = serviceVhecule.getAll();
		listeClients = new ArrayList<Client>();
		listeClients = serviceclients.getAll();

		List<Lignecommande> l = new ArrayList<Lignecommande>();
		l = serviceLigneCommande.getLcbyf(selectedfacture);
		listeProduits = new ArrayList<Produit>();

		for (Lignecommande c : l) {
			if (c.getProduit() != null) {
				c.getProduit().setQuantites(c.getQuantite());
				listeProduits.add(c.getProduit());
			}
			if (c.getTransport() != 0)
				transport = c.getTransport();

		}
		for (int i = l.size(); i < 20; i++) {
			Produit p1 = new Produit();
			p1.setCode(null);
			p1.setNom(null);
			p1.setQuantites(0);
			listeProduits.add(p1);
		}
		if (selectedfacture.getBl() != null && selectedfacture.getBl().getChauffeur() != null)
			codechaffeur = selectedfacture.getBl().getChauffeur().getNompenom();
		date = selectedfacture.getDate();
		if (selectedfacture.getBl() != null && selectedfacture.getBl().getVhecule() != null)
			codevhecule = selectedfacture.getBl().getVhecule().getMatricule();
		if (selectedfacture.getBl() != null && selectedfacture.getBl().getClient() != null) {
			codeclient = selectedfacture.getBl().getClient().getNom();
			mf = selectedfacture.getBl().getClient().getMf();
		}
		totalttc = selectedfacture.getTotalttc();

		return SUCCESS;
	}

	public String modifierFacture2() {
		try {
			listeChauffeurs = new ArrayList<Chauffeur>();
			listeChauffeurs = serviceChauffeur.getAll();
			listeVehicules = new ArrayList<Vhecule>();
			listeVehicules = serviceVhecule.getAll();
			listeClients = new ArrayList<Client>();
			listeClients = serviceclients.getAll();
			List<Lignecommandepass> l = new ArrayList<Lignecommandepass>();
			l = serviceLigneCommandepass.getLcbyf(facturepassager);
			listeProduits = new ArrayList<Produit>();

			for (Lignecommandepass c : l) {
				if (c.getProduit() != null) {
					c.getProduit().setQuantites(c.getQuantite());
					listeProduits.add(c.getProduit());
				}
				if (c.getTransport() != 0)
					transport = c.getTransport();

			}
			for (int i = l.size(); i < 20; i++) {
				Produit p1 = new Produit();
				p1.setCode(null);
				p1.setNom(null);
				p1.setQuantites(0);
				listeProduits.add(p1);
			}
			if (facturepassager.getChauffeur() != null)
				codechaffeur = facturepassager.getChauffeur().getNompenom();
			date = facturepassager.getDate();
			if (facturepassager.getVhecule() != null)
				codevhecule = facturepassager.getVhecule().getMatricule();
			if (facturepassager.getClient() != null) {
				codeclient = facturepassager.getClient().getNom();
				mf = facturepassager.getClient().getMf();
			}
			totalttc = facturepassager.getTotalttc();
		}

		catch (Exception e) {
		}
		return SUCCESS;
	}
private String dates;

	public String getDates() {
	return dates;
}

public void setDates(String dates) {
	this.dates = dates;
}

	public void getdates(AjaxBehaviorEvent event) {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
	}

	public String validermodifierFacture() {
		client = serviceclients.Findbynom(codeclient);
		selectedBL = selectedfacture.getBl();
		List<Lignecommande> l = new ArrayList<Lignecommande>();
		l = serviceLigneCommande.getLcbyf(selectedfacture);
		for (Lignecommande c : l) {
			if (c.getBl() != null)
				serviceLigneCommande.delete(c);
		}
		listelignefacture = new ArrayList<Lignecommande>();
		double mh = 0;
		double totaltva = 0;

		for (Produit p : listeProduits) {
			if (p.getCode() != null) {
				Lignecommande c1 = new Lignecommande();
				c1.setQuantite(p.getQuantites());
				c1.setProduit(p);
				c1.setBl(selectedBL);
				c1.setTva(p.getTva());
				c1.setPrix(p.getVente() / (100 + p.getTva()) * 100);
				c1.setMantantht(c1.getQuantite() * c1.getPrix());
				c1.setTauxtva(c1.getMantantht() * c1.getTva() * 0.01);
				listelignefacture.add(c1);
			}
		}
		if (transport != 0) {
			Lignecommande c1 = new Lignecommande();
			c1.setTransport(transport);
			c1.setQuantite(1);
			c1.setBl(selectedBL);
			Tva t = serviceTva.getbyid(1);
			c1.setTva(t.getValeur());
			c1.setMantantht(transport / (100 + c1.getTva()) * 100);
			c1.setTauxtva(c1.getMantantht() * c1.getTva() * 0.01);
			listelignefacture.add(c1);
			
		}

		for (Lignecommande c : listelignefacture) {
			mh = mh + c.getMantantht();
			totaltva = totaltva + c.getTauxtva();

		}
		selectedBL.setTransport(transport);
		Paramettre p = serviceParamettre.getAll().get(0);
		selectedfacture.setDate(date);
		selectedfacture.setDates(dates);
		selectedBL.setClient(client);
		chauffeur = serviceChauffeur.Findbynom(codechaffeur);
		vhecule = serviceVhecule.Findbynom(codevhecule);
		if (chauffeur != null) {
			selectedBL.setChauffeur(chauffeur);
		}
		if (vhecule != null) {
			selectedBL.setVhecule(vhecule);
		}
		if (codevhecule != null && !codevhecule.isEmpty()) {
			selectedfacture.setVhecules(codevhecule);
		}
		selectedBL.setDate(date);
		SimpleDateFormat f= new SimpleDateFormat("dd-MM-yyyy");
		selectedBL.setDates(f.format(date));
		serviceBonLivraison.update(selectedBL);

		Convert C = new Convert();		 
		selectedfacture.setTimbres(p.getTimbre());
		selectedfacture.setTotaltva(totaltva);
		selectedfacture.setTotalht(mh);
		selectedfacture.setTotalttc(
				selectedfacture.getTotalht() + selectedfacture.getTotaltva() + selectedfacture.getTimbres());
		String s = "";
		double ss = selectedfacture.getTotalttc();
		int e = new Float(ss).intValue();
		int cs = selectedfacture.getTotalttcs().indexOf(",");
		s = selectedfacture.getTotalttcs().substring(cs + 1, selectedfacture.getTotalttcs().length());
		selectedfacture.setSommes(C.convertt(e) + " dinars et  " + s + " millimes");
		serviceFacture.update(selectedfacture);
		for (Lignecommande c : listelignefacture) {
               c.setDates(selectedBL.getDates());
               c.setDate(selectedBL.getDate());
			serviceLigneCommande.save(c);

		}
		  Utilisateur user=userService.getCurrentUser();
	       Tracetransport t=new Tracetransport();
	       t.setAction(user.getNom() +"a modefier  la facture"+selectedfacture.getCodes()+" a "+new Date());
	       t.setDate(new Date());
	        serviceTrace.save(t);
		return SUCCESS;
	}

	public String validermodifierFacture2() {
		client = serviceclients.Findbynom(codeclient);

		List<Lignecommandepass> l = new ArrayList<Lignecommandepass>();
		l = serviceLigneCommandepass.getLcbyf(facturepassager);
		for (Lignecommandepass c : l) {

			serviceLigneCommandepass.delete(c);
		}
		listelignefacturepass = new ArrayList<Lignecommandepass>();
		double mh = 0;
		double totaltva = 0;
		for (Produit p : listeProduits) {
			if (p.getCode() != null) {
				Lignecommandepass c1 = new Lignecommandepass();
				c1.setQuantite(p.getQuantites());
				c1.setProduit(p);
				c1.setFacturepassager(facturepassager);
				c1.setTva(p.getTva());
				c1.setPrix(p.getVente() / (100 + p.getTva()) * 100);
				c1.setMantantht(c1.getQuantite() * c1.getPrix());
				c1.setTauxtva(c1.getMantantht() * c1.getTva() * 0.01);
				listelignefacturepass.add(c1);
			}
		}
		if (transport != 0) {
			Lignecommandepass c1 = new Lignecommandepass();
			c1.setTransport(transport);
			c1.setQuantite(1);
			c1.setFacturepassager(facturepassager);
			Tva t = serviceTva.getbyid(1);
			c1.setTva(t.getValeur());
			c1.setMantantht(transport / (100 + c1.getTva()) * 100);
			c1.setTauxtva(c1.getMantantht() * c1.getTva() * 0.01);
			listelignefacturepass.add(c1);
		}

		for (Lignecommandepass c : listelignefacturepass) {
			mh = mh + c.getMantantht();
			totaltva = totaltva + c.getTauxtva();

		}

		Paramettre p = serviceParamettre.getAll().get(0);
		facturepassager.setDate(date);
		chauffeur = serviceChauffeur.Findbynom(codechaffeur);
		vhecule = serviceVhecule.Findbynom(codevhecule);
		if (chauffeur != null) {
			facturepassager.setChauffeur(chauffeur);
		}
		if (vhecule != null) {
			facturepassager.setVhecule(vhecule);
		}
		if (codevhecule != null && !codevhecule.isEmpty()) {
			facturepassager.setVhecules(codevhecule);
		}

		Convert C = new Convert();
		facturepassager.setDate(date);
		facturepassager.setTimbres(p.getTimbre());
		facturepassager.setTotaltva(totaltva);
		facturepassager.setTotalht(mh);
		facturepassager.setTotalttc(
				facturepassager.getTotalht() + facturepassager.getTotaltva() + facturepassager.getTimbres());
		String s = "";
		double ss = facturepassager.getTotalttc();
		int e = new Float(ss).intValue();
		int cs = facturepassager.getTotalttcs().indexOf(",");
		s = facturepassager.getTotalttcs().substring(cs + 1, facturepassager.getTotalttcs().length());
		facturepassager.setSommes(C.convertt(e) + " dinars et  " + s + " millimes");
		servicefacturepassager.update(facturepassager);
		for (Lignecommandepass c : listelignefacturepass) {

			serviceLigneCommandepass.save(c);

		}
		
		Utilisateur user=userService.getCurrentUser();
	       Tracetransport t=new Tracetransport();
	       t.setAction(user.getNom() +"a modefier  la facture"+facturepassager.getCodes()+" a "+new Date());
	       t.setDate(new Date());
	        serviceTrace.save(t);
		return SUCCESS;
	}

	public String nouveauBL() {

		date = new Date();
		code = null;
		codeproduit = null;
		mf = null;
		listeVehicules = new ArrayList<Vhecule>();

		listeVehicules = serviceVhecule.getAll();
		listeProduits = new ArrayList<Produit>();
		listelignefacture = new ArrayList<Lignecommande>(30);
		for (int i = 0; i < 30; i++) {
			Produit p1 = new Produit();
			p1.setCode(null);
			p1.setNom(null);
			p1.setQuantites(0);
			listeProduits.add(p1);
		}

		quantite = 0;
		codevhecule = null;
		codechaffeur = null;
		codeclient = null;
		produit = null;
		transport = 0;

		return SUCCESS;
	}

	public String nouveaufacure() {

		code = null;
		mf = null;
		codeproduit = null;
		date = new Date();
		listeVehicules = new ArrayList<Vhecule>();
		listeVehicules = serviceVhecule.getAll();
		// listeChauffeurs = serviceChauffeur.getAll();
		listeClients = new ArrayList<Client>();
		listeClients = serviceclients.getAll();

		// listproduits = serviceProduit.getAll();

		listeProduits = new ArrayList<Produit>();

		for (int i = 0; i < 30; i++) {
			Produit p1 = new Produit();

			p1.setQuantites(0);
			listeProduits.add(p1);
		}
		totalttc = 0.6;
		codevhecule = null;
		codechaffeur = null;
		codeclient = null;
		transport = 0;

		return SUCCESS;
	}

	public void getChauffeurs(AjaxBehaviorEvent event) {
		listeChauffeurs = new ArrayList<Chauffeur>();
		listeChauffeurs = serviceChauffeur.getAll();
	}

	public void getCliehts(AjaxBehaviorEvent event) {
		listeClients = new ArrayList<Client>();
		listeClients = serviceclients.getAll();
	}

	private Lignecommande selectedLignecommande;
	private Integer tests;

	public void onCellEdit2(CellEditEvent event) {

		produit = listeProduits.get(event.getRowIndex());
		codes = event.getRowIndex();
		listeProduits.set(codes, produit);

	}

	public void updateCode(AjaxBehaviorEvent event) {
		produit = serviceProduit.Findbycode(codeproduit);
		produit.setQuantites(0);
		listeProduits.set(codes, produit);
	}

	public void updatemontant(AjaxBehaviorEvent event) {

		listeProduits.set(codes, produit);
		totalttc = 0.6;
		for (Produit produit : listeProduits) {
			if (produit.getCode() != null) {
				totalttc = totalttc + (produit.getQuantites() * produit.getVente());

			}
		}

	}

	public void updatenom(AjaxBehaviorEvent event) {
		Produit produit2 = serviceProduit.Findbydes(code);
		produit.setVente(produit2.getVente());
		produit.setId(produit2.getId());
		produit.setTva(produit2.getTva());
		produit.setNom(produit2.getNom());
		produit.setCode(produit2.getCode());
		produit.setQuantites(0);
		listeProduits.set(codes, produit);
		code = null;
		codes = null;
	}

	private double transportht;
	private String mf;

	public void getTransportbyclient(AjaxBehaviorEvent event) {
		client = serviceclients.Findbynom(codeclient);
		transport = client.getTransport();
		mf = client.getMf();

	}

	public void getTransportbyclient2(AjaxBehaviorEvent event) {
		client = serviceclients.Findbynom(codeclient);

		mf = client.getMf();

	}

	public String savefacture() {
		listelignefacturepass = new ArrayList<Lignecommandepass>();
		double mh = 0;
		double totaltva = 0;
		/*
		 * for (Produit p : listeProduits) { if (!p.getCode().equals(null) &&
		 * p.getQuantites() == 0) { FacesMessage msg = new
		 * FacesMessage(FacesMessage.SEVERITY_INFO, "verifier la qantite du poduit  " +
		 * p.getNom(), null); FacesContext.getCurrentInstance().addMessage(null, msg);
		 * return ERROR; } }
		 */
		for (Produit p : listeProduits) {
			if (p.getCode() != null) {
				Lignecommandepass c1 = new Lignecommandepass();
				c1.setQuantite(p.getQuantites());
				c1.setProduit(p);
				c1.setTva(p.getTva());
				c1.setPrix(p.getVente() / (100 + c1.getTva()) * 100);
				c1.setMantantht(c1.getQuantite() * c1.getPrix());
				c1.setTauxtva(c1.getMantantht() * c1.getTva() * 0.01);
				listelignefacturepass.add(c1);
			}
		}
		if (transport != 0) {
			Lignecommandepass c1 = new Lignecommandepass();
			c1.setTransport(transport);
			c1.setQuantite(1);
			Tva t = serviceTva.getbyid(1);
			c1.setTva(t.getValeur());
			// c1.setMantantht(transport - (transport*0.01*c1.getTva()));
			c1.setMantantht(transport / (100 + c1.getTva()) * 100);
			c1.setTauxtva(c1.getMantantht() * c1.getTva() * 0.01);
			listelignefacturepass.add(c1);
		}

		for (Lignecommandepass c : listelignefacturepass) {
			mh = mh + c.getMantantht();
			totaltva = totaltva + c.getTauxtva();

		}

		if (client != null && mf != null && !mf.isEmpty())
		client.setMf(mf);

		Paramettre p = serviceParamettre.getAll().get(0);
		facturepassager = new Facturepassager();
		//date = new Date();

		facturepassager.setDate(date);
		facturepassager.setClient(client);
		chauffeur = serviceChauffeur.Findbynom(codechaffeur);
		vhecule = serviceVhecule.Findbynom(codevhecule);
		if (chauffeur != null)
			facturepassager.setChauffeur(chauffeur);

		if (vhecule != null)
			facturepassager.setVhecule(vhecule);
		if (codevhecule != null && !codevhecule.isEmpty())
			facturepassager.setVhecules(codevhecule);
		List<Facturepassager> fs = servicefacturepassager.getAll();
		Facturepassager f2;
		f2 = servicefacturepassager.getMaxfacture();
		if (f2 == null) {
			facturepassager.setCode(1);

		} else if (f2 != null && f2.getDate().getYear() == facturepassager.getDate().getYear()) {

			facturepassager.setCode(f2.getCode() + 1);
		}

		else if (facturepassager.getDate().getYear() > f2.getDate().getYear()) {

			facturepassager.setCode(1);
		}
		codes = facturepassager.getCode();
		facturepassager.setCodes(codes + "/" + facturepassager.getDates().substring(6));
		Convert C = new Convert();
		facturepassager.setDate(date);
		facturepassager.setTimbres(p.getTimbre());
		facturepassager.setTotaltva(totaltva);
		facturepassager.setTotalht(mh);
		facturepassager.setTotalttc(
				facturepassager.getTotalht() + facturepassager.getTotaltva() + facturepassager.getTimbres());
		String s = "";
		double ss = facturepassager.getTotalttc();
		int e = new Float(ss).intValue();
		int cs = facturepassager.getTotalttcs().indexOf(",");
		s = facturepassager.getTotalttcs().substring(cs + 1, facturepassager.getTotalttcs().length());
		facturepassager.setSommes(C.convertt(e) + " dinars et  " + s + " millimes");
		servicefacturepassager.save(facturepassager);
		for (Lignecommandepass c : listelignefacturepass) {
			c.setFacturepassager(facturepassager);
			serviceLigneCommandepass.save(c);

		}
		
		Utilisateur user=userService.getCurrentUser();
	       Tracetransport t=new Tracetransport();
	       t.setAction(user.getNom() +"a creer  la facture "+facturepassager.getCodes()+" a "+new Date());
	       t.setDate(new Date());
	        serviceTrace.save(t);
		return SUCCESS;
	}

	public String savebl() {
		bl = new Bonlivraison();
		bl.setDate(date);
		Bonlivraison f2;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		bl.setDates(dateFormat.format(bl.getDate()));
		f2 = serviceBonLivraison.getMaxbl();

		if (f2 == null) {

			bl.setCode(1);

		} else if (f2 != null && f2.getDate().getYear() == bl.getDate().getYear()) {

			bl.setCode(f2.getCode() + 1);
		}

		else if (bl.getDate().getYear() > f2.getDate().getYear()) {

			bl.setCode(1);
		}
		codes = bl.getCode();
		bl.setCodes(codes + "/" + bl.getDates().substring(6));
		double mh = 0;
		double totaltva = 0;
		/*
		 * for (Produit p : listeProduits) { if (p.getNom()!=null && p.getQuantites() ==
		 * 0) { FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
		 * "verifier la qantite du poduit  " + p.getNom(), null);
		 * FacesContext.getCurrentInstance().addMessage(null, msg); return ERROR; } }
		 */
		for (Produit p : listeProduits) {
			if (p.getCode() != null) {
				Lignecommande c1 = new Lignecommande();
				c1.setQuantite(p.getQuantites());
				c1.setProduit(p);
				c1.setTva(p.getTva());
				c1.setPrix(p.getVente() / (100 + c1.getTva()) * 100);
				c1.setMantantht(c1.getQuantite() * c1.getPrix());
				c1.setTauxtva(c1.getMantantht() * c1.getTva() * 0.01);
				c1.setDates(bl.getDates());
				listelignefacture.add(c1);
			}
		}

		if (transport != 0) {
			Lignecommande c1 = new Lignecommande();
			c1.setTransport(transport);
			c1.setQuantite(1);
			Tva t = serviceTva.getbyid(1);
			c1.setTva(t.getValeur());
			// c1.setMantantht(transport - (transport*0.01*c1.getTva()));
			c1.setMantantht(transport / (100 + c1.getTva()) * 100);
			c1.setTauxtva(c1.getMantantht() * c1.getTva() * 0.01);
			c1.setDates(bl.getDates());
			listelignefacture.add(c1);
		}

		for (Lignecommande c : listelignefacture) {
			mh = mh + c.getMantantht();
			totaltva = totaltva + c.getTauxtva();

		}
		if (client != null && mf != null && !mf.isEmpty())
		client.setMf(mf);
		client = serviceclients.Findbynom(codeclient);
		client.setTransport(transport);
		serviceclients.update(client);
		bl.setTransport(transport);
		bl.setChauffeur(serviceChauffeur.Findbynom(codechaffeur));
		bl.setClient(client);
		bl.setVhecule(serviceVhecule.Findbynom(codevhecule));

		bl.setMontant(mh);
		bl.setTotaltva(totaltva);
		chauffeur = serviceChauffeur.Findbynom(codechaffeur);
		vhecule = serviceVhecule.Findbynom(codevhecule);
		if (chauffeur != null)
			bl.setChauffeur(chauffeur);

		if (vhecule != null)
			bl.setVhecule(vhecule);
		
		serviceBonLivraison.save(bl);

		facturer();

		return SUCCESS;
	}

	public String facturer() {
		
		Paramettre p = serviceParamettre.getAll().get(0);
		facture = new Facture();
		facture.setBl(bl);
		Convert C = new Convert();
		date = new Date();
		facture.setDate(date);
		facture.getBl().setClient(bl.getClient());
		//List<Facture> fs = servicefacture.getAll();
		Facture f2;
		f2 = servicefacture.getMaxfacture();
		if (f2 == null) {
			facture.setCode(1);

		} else if (f2 != null && f2.getDate().getYear() == facture.getDate().getYear()) {

			facture.setCode(f2.getCode() + 1);
		}

		else if (facture.getDate().getYear() > f2.getDate().getYear()) {

			facture.setCode(1);
		}
		codes = facture.getCode();
		facture.setCodes(codes + "/" + facture.getDates().substring(6));
		double mht = 0;
		double ttht = 0;
		double totalttc = 0;
		double totaltva = 0;

		// facture.setBonlivraison(bl);
		facture.setDate(bl.getDate());
		facture.setTimbres(p.getTimbre());
		/*
		 * for (Lignecommande c : listelignefacture) { mht = mht + c.getMantantht();
		 * totaltva = (float) (totaltva + ((c.getMantantht()) * c.getProduit().getTva()
		 * / 100)); }
		 */
		mht = mht + bl.getTransport();
		// totalttc = mht + totaltva + facture.getTimbres();
		facture.setTotaltva(bl.getTotaltva());
		facture.setTotalht(bl.getMontant());
		facture.setTotalttc(facture.getTotalht() + facture.getTotaltva() + facture.getTimbres());
		String s = "";
		double ss = facture.getTotalttc();
		int e = new Float(ss).intValue();
		int cs = facture.getTotalttcs().indexOf(",");
		s = facture.getTotalttcs().substring(cs + 1, facture.getTotalttcs().length());
		facture.setSommes(C.convertt(e) + " dinars et  " + s + " millimes");
		serviceFacture.save(facture);

		bl.setStatus(Status.Facturee);
		serviceBonLivraison.update(bl);
		for (Lignecommande c : listelignefacture) {

			c.setBl(bl);
			c.setDates(bl.getDates());
			c.setDate(bl.getDate());
			serviceLigneCommande.save(c);
			

		}
		Utilisateur user=userService.getCurrentUser();
	       Tracetransport t=new Tracetransport();
	       t.setAction(user.getNom() +"a creer  la facture  "+facture.getCodes()+" a "+new Date());
	       t.setDate(new Date());
	        serviceTrace.save(t);
		return SUCCESS;
	}

	public String facturelistbl() {
		if (selectedsBl == null || selectedsBl.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Selectionner au moins un BL", null));
			return ERROR;
		}
		Paramettre p = serviceParamettre.getAll().get(0);
		facture = new Facture();
		bl = selectedsBl.get(0);
		facture.setBl(bl);
		facture.setListebl(new ArrayList<Bonlivraison>(selectedsBl));
		facture.setDate(bl.getDate());
		listelignefacture = new ArrayList<Lignecommande>();
		double totalht = 0;
		double totaltva = 0;
		for (Bonlivraison selected : selectedsBl) {
			List<Lignecommande> lignes = serviceLigneCommande.getLcbyBL(selected);
			if (lignes != null) {
				listelignefacture.addAll(lignes);
			}
			totalht += selected.getMontant();
			totaltva += selected.getTotaltva();
			selected.setStatus(Status.Facturee);
			serviceBonLivraison.update(selected);
		}
		Facture f2 = servicefacture.getMaxfacture();
		if (f2 == null) {
			facture.setCode(1);
		} else if (f2.getDate() != null && facture.getDate() != null
				&& f2.getDate().getYear() == facture.getDate().getYear()) {
			facture.setCode(f2.getCode() + 1);
		} else if (f2.getDate() != null && facture.getDate() != null
				&& facture.getDate().getYear() > f2.getDate().getYear()) {
			facture.setCode(1);
		}
		codes = facture.getCode();
		facture.setCodes(codes + "/" + facture.getDates().substring(6));
		facture.setTimbres(p.getTimbre());
		facture.setTotalht(totalht);
		facture.setTotaltva(totaltva);
		facture.setTotalttc(facture.getTotalht() + facture.getTotaltva() + facture.getTimbres());
		Convert converter = new Convert();
		String total = facture.getTotalttcs();
		int dinars = new Float(facture.getTotalttc()).intValue();
		int separator = total.indexOf(",");
		String millimes = separator >= 0 ? total.substring(separator + 1) : "000";
		facture.setSommes(converter.convertt(dinars) + " dinars et  " + millimes + " millimes");
		serviceFacture.save(facture);
		Utilisateur user = userService.getCurrentUser();
		if (user != null) {
			Tracetransport trace = new Tracetransport();
			trace.setAction(user.getNom() + " a creer la facture " + facture.getCodes() + " a " + new Date());
			trace.setDate(new Date());
			serviceTrace.save(trace);
		}
		return SUCCESS;
	}

	public String supprimerBL() {
		if (selectedBL == null) {
			return ERROR;
		}
		List<Lignecommande> lignes = serviceLigneCommande.getLcbyBL(selectedBL);
		if (lignes != null) {
			for (Lignecommande ligne : lignes) {
				serviceLigneCommande.delete(ligne);
			}
		}
		Integer numero = selectedBL.getNumero();
		serviceBonLivraison.delete(selectedBL);
		selectedBL = null;
		getBLnonfacturee();
		Utilisateur user = userService.getCurrentUser();
		if (user != null) {
			Tracetransport trace = new Tracetransport();
			trace.setAction(user.getNom() + " a supprimer le BL " + numero + " a " + new Date());
			trace.setDate(new Date());
			serviceTrace.save(trace);
		}
		return SUCCESS;
	}

	public String validerfacture() {
		return SUCCESS;
	}

	public void getclientbynom(AjaxBehaviorEvent event) {
		client = serviceclients.Findbynom(nom);

	}

	public void updattotalttc(AjaxBehaviorEvent event) {
		totalttc = 0.6;
		for (Produit produit : listeProduits) {
			// if (produit.getCode() != null) {
			totalttc = totalttc + (produit.getQuantites() * produit.getVente());

			// }
		}

	}

	public String supprimer() {
		
		selectedfacture.setListelc(serviceLigneCommande.getLcbyf(selectedfacture));
		for (Lignecommande c : selectedfacture.getListelc()) {
			serviceLigneCommande.delete(c);

		}
		bl=selectedfacture.getBl();
		
		serviceFacture.delete(selectedfacture);
		serviceBonLivraison.delete(bl);
		selectedfacture = null;
		listfacture = new ArrayList<Facture>();
		date2=new Date();
		date=new Date();
		nom = null;

		date2.setDate(date2.getDate() + 1);
		date.setDate(date.getDate() - 1);

		listfacture = servicefacture.getfacturebetwenndate(date, date2);

		if (listfacture == null) {
			listfacture = new ArrayList<Facture>();
		}

		date2.setDate(date2.getDate() - 1);
		date.setDate(date.getDate() + 1);
		
		Utilisateur user=userService.getCurrentUser();
	       Tracetransport t=new Tracetransport();
	       t.setAction(user.getNom() +"a supprimer  la facture"+selectedfacture.getCodes()+" a "+new Date());
	       t.setDate(new Date());
	        serviceTrace.save(t);
		return SUCCESS;
	}

	public String supprimerfacturepassager() {
		facturepassager.setListelc(serviceLigneCommandepass.getLcbyf(facturepassager));
		for (Lignecommandepass c : facturepassager.getListelc()) {
			serviceLigneCommandepass.delete(c);

		}
		servicefacturepassager.delete(facturepassager);

	 
		listfacturepass = new ArrayList<Facturepassager>();
		date2=new Date();
		date=new Date();
		nom=null;
		date2.setDate(date2.getDate()+1);
		 date.setDate(date.getDate()-1);
		listfacturepass = servicefacturepassager.getfacturebetwenndate(date, date2);
		 date2.setDate(date2.getDate()-1);
		 date.setDate(date.getDate()+1);
		
		Utilisateur user=userService.getCurrentUser();
	       Tracetransport t=new Tracetransport();
	       t.setAction(user.getNom() +"a supprimer  la facture"+facturepassager.getCodes()+" a "+new Date());
	       t.setDate(new Date());
	        serviceTrace.save(t);
		return SUCCESS;
	}

	public String getfacturebyClientandtypepayement() {

		List<Facture> lbyfacture = new ArrayList<Facture>();
		listfacture = new ArrayList<Facture>();
		List<Facture> source = servicefacture.getfacturebetwenndate(date, date2);
		if (source != null) {
			lbyfacture = source;
		}

		client = serviceclients.Findbynom(nom);
		if (client != null) {
			for (Facture f : lbyfacture) {
				if (f.getBl() != null && f.getBl().getClient() != null
						&& f.getBl().getClient().getNom().equals(client.getNom())) {
					listfacture.add(f);
				}
				f.setListelc(serviceLigneCommande.getLcbyf(f));
			}
		} else {
			for (Facture f : lbyfacture) {
				listfacture.add(f);
				f.setListelc(serviceLigneCommande.getLcbyf(f));
			}
		}

		return SUCCESS;
	}


	public String getfacturebyClientandtypepayement3() {

		List<Facturepassager> l = new ArrayList<Facturepassager>();
		List<Facturepassager> lbyfacture = new ArrayList<Facturepassager>();
		lbyfacture=servicefacturepassager.getfacturebetwenndate(date, date2);
		 
		listfacturepass = new ArrayList<Facturepassager>();
		client = serviceclients.Findbynom(nom);
		if (client!=null) {
			
			for (Facturepassager f : lbyfacture) {
				if (f.getClient().getNom().equals(client.getNom()))
					listfacturepass.add(f);
				
				f.setListelc(serviceLigneCommandepass.getLcbyf(f));
			}
		}

		if (client == null) {
			for (Facturepassager f : lbyfacture) {
				listfacturepass.add(f);
				f.setListelc(serviceLigneCommandepass.getLcbyf(f));
			}
		}
		 
			

		return SUCCESS;
	}

	public String getfacturebyClientandtypepayement2() {

		List<Facturepassager> l = new ArrayList<Facturepassager>();
		List<Facturepassager> lbyfacture = new ArrayList<Facturepassager>();

		client = serviceclients.Findbynom(nom);

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			for (Facturepassager f : listfacturepass) {
				if (date != null) {
					Date d1 = dateFormat.parse(dateFormat.format(date));

					if (d1.compareTo(dateFormat.parse(dateFormat.format(f.getDate()))) <= 0) {
						l.add(f);
					}
				}

				if (date == null)
					l.add(f);
			}
			for (Facturepassager f : l) {
				if (date2 != null) {
					Date d2 = dateFormat.parse(dateFormat.format(date2));

					if (d2.compareTo(dateFormat.parse(dateFormat.format(f.getDate()))) >= 0) {
						lbyfacture.add(f);
					}
				}
				if (date2 == null)
					lbyfacture.add(f);
			}

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
		}
		listfacturepass = new ArrayList<Facturepassager>();
		if (client != null) {
			for (Facturepassager f : lbyfacture) {
				if (f.getClient().getNom().equals(client.getNom()))
					listfacturepass.add(f);
			}

		}

		if (client == null) {
			for (Facturepassager f : lbyfacture) {
				listfacturepass.add(f);
			}
		}
		for (Facturepassager f : listfacturepass)
			f.setListelc(serviceLigneCommandepass.getLcbyf(f));

		return SUCCESS;
	}

	public String visualise() {
		listelignefacture = new ArrayList<Lignecommande>();
		selectedfacture.setListelc(serviceLigneCommande.getLcbyf(selectedfacture));
		if (selectedfacture.getBl() != null && selectedfacture.getBl().getChauffeur() != null) {
			codechaffeur = selectedfacture.getBl().getChauffeur().getNompenom();
		}
		if (selectedfacture.getBl() != null && selectedfacture.getBl().getVhecule() != null) {
			codevhecule = selectedfacture.getBl().getVhecule().getMatricule();
		}
		listelignefacture = serviceLigneCommande.getLcbyf(selectedfacture);

		return SUCCESS;
	}

	public String visualise2() {
		listelignefacturepass = new ArrayList<Lignecommandepass>();
		facturepassager.setListelc(serviceLigneCommandepass.getLcbyf(facturepassager));
		if (facturepassager.getChauffeur() != null)
			codechaffeur = facturepassager.getChauffeur().getNompenom();

		if (facturepassager.getVhecule() != null)
			codevhecule = facturepassager.getVhecule().getMatricule();

		listelignefacturepass = serviceLigneCommandepass.getLcbyf(facturepassager);

		return SUCCESS;
	}

	public String visualiserBL() {
		selectedBL.setListeligne(serviceLigneCommande.getLcbyBL(selectedBL));
		return SUCCESS;
	}

	public String listFacture() {
		selectedsBl = null;
		// societe = serviceSociete.getAll().get(0);

		listeclient = new ArrayList<String>();
		List<Client> s = new ArrayList<Client>();
		s = serviceclients.getAll();
		if (s.size() > 0)
			for (Client c : s)
				listeclient.add(c.getNom());

		typepayement = null;
		listfacture = new ArrayList<Facture>();

		listfacture = servicefacture.getAll();

		return SUCCESS;
	}

	public String listbls() {
		test = false;
		selectedsBl = new ArrayList<Bonlivraison>();
		// societe = serviceSociete.getAll().get(0);

		listeclient = new ArrayList<String>();
		List<Client> s = new ArrayList<Client>();
		s = serviceclients.getAll();
		if (s.size() > 0)
			for (Client c : s)
				listeclient.add(c.getNom());

		typepayement = null;

		listbonlivraison = new ArrayList<Bonlivraison>();

		listbonlivraison = serviceBonLivraison.getAll();

		return SUCCESS;
	}

	public void onrowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Bl Selected", "" + ((Bonlivraison) event.getObject()).getNumero());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		// selectedsBl.add((Bonlivraison) event.getObject());
		test = true;
	}

	public void onRowSelect(SelectEvent event) {
		onrowSelect(event);
	}

	public void onRowUnselect(UnselectEvent event) {
		test = selectedsBl != null && !selectedsBl.isEmpty();
	}

	public Date dat;

	public void getfacturebyClientandtypepayement(AjaxBehaviorEvent event) {
		selectedsBl = null;
		listfacture = new ArrayList<Facture>();
		List<Facture> l = servicefacture.getAll();
		List<Facture> lbyfacture = new ArrayList<Facture>();
		List<Facture> lbyfacture2 = new ArrayList<Facture>();
		if (l == null) {
			l = new ArrayList<Facture>();
		}
		client = serviceclients.Findbynom(nom);
		if (client != null) {
			for (Facture f : l) {
				if (f.getBl() != null && f.getBl().getClient() != null
						&& f.getBl().getClient().getNom().equals(client.getNom())) {
					lbyfacture.add(f);
				}
			}
		} else {
			for (Facture f : l) {
				lbyfacture.add(f);
			}
		}
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			for (Facture f : lbyfacture) {
				if (date != null) {
					Date d1 = dateFormat.parse(dateFormat.format(date));

					if (d1.compareTo(dateFormat.parse(dateFormat.format(f.getDate()))) <= 0) {
						lbyfacture2.add(f);
					}
				}

				if (date == null)
					lbyfacture2.add(f);
			}
			for (Facture f : lbyfacture2) {
				if (date2 != null) {
					Date d2 = dateFormat.parse(dateFormat.format(date2));

					if (d2.compareTo(dateFormat.parse(dateFormat.format(f.getDate()))) >= 0) {
						listfacture.add(f);
					}
				}
				if (date2 == null)
					listfacture.add(f);
			}

		} catch (ParseException e1) {
		}

	}


	public String modifier() {
		return SUCCESS;
	}

	public ServiceFacture getServicefacture() {
		return servicefacture;
	}

	public void setServicefacture(ServiceFacture servicefacture) {
		this.servicefacture = servicefacture;
	}

	public List<Facture> getListfacture() {
		return listfacture;
	}

	public void setListfacture(List<Facture> listfacture) {
		this.listfacture = listfacture;
	}

	public ServiceBonLivraison getServiceBonLivraison() {
		return serviceBonLivraison;
	}

	public void setServiceBonLivraison(ServiceBonLivraison serviceBonLivraison) {
		this.serviceBonLivraison = serviceBonLivraison;
	}

	public ServiceProduit getServiceProduit() {
		return serviceProduit;
	}

	public void setServiceProduit(ServiceProduit serviceProduit) {
		this.serviceProduit = serviceProduit;
	}

	public ServiceTracetransport getServiceTrace() {
		return serviceTrace;
	}

	public void setServiceTrace(ServiceTracetransport serviceTrace) {
		this.serviceTrace = serviceTrace;
	}

	public ServiceChauffeur getServiceChauffeur() {
		return serviceChauffeur;
	}

	public void setServiceChauffeur(ServiceChauffeur serviceChauffeur) {
		this.serviceChauffeur = serviceChauffeur;
	}

	public ServiceVhecule getServiceVhecule() {
		return serviceVhecule;
	}

	public void setServiceVhecule(ServiceVhecule serviceVhecule) {
		this.serviceVhecule = serviceVhecule;
	}

	public Bonlivraison getBl() {
		return bl;
	}

	public void setBl(Bonlivraison bl) {
		this.bl = bl;
	}

	public double getTransport() {
		return transport;
	}

	public void setTransport(double transport) {
		this.transport = transport;
	}

	public Facture getSelectedfacture() {
		return selectedfacture;
	}

	public void setSelectedfacture(Facture selectedfacture) {
		this.selectedfacture = selectedfacture;
	}

	public Bonlivraison getSelectedBL() {
		return selectedBL;
	}

	public void setSelectedBL(Bonlivraison selectedBL) {
		this.selectedBL = selectedBL;
	}

	public List<Bonlivraison> getSelectedsBl() {
		return selectedsBl;
	}

	public void setSelectedsBl(List<Bonlivraison> selectedsBl) {
		this.selectedsBl = selectedsBl;
	}

	public List<Bonlivraison> getListbonlivraison() {
		return listbonlivraison;
	}

	public void setListbonlivraison(List<Bonlivraison> listbonlivraison) {
		this.listbonlivraison = listbonlivraison;
	}

	public double getTransportht() {

		return transportht;
	}

	public void setTransportht(double transportht) {
		this.transportht = transportht;
	}

	public ServiceLigneCommande getServiceLigneCommande() {
		return serviceLigneCommande;
	}

	public void setServiceLigneCommande(ServiceLigneCommande serviceLigneCommande) {
		this.serviceLigneCommande = serviceLigneCommande;
	}

	public List<Lignecommande> getListeligne() {
		return listeligne;
	}

	public void setListeligne(List<Lignecommande> listeligne) {
		this.listeligne = listeligne;
	}

	public List<String> getListClient() {
		return listClient;
	}

	public void setListClient(List<String> listClient) {
		this.listClient = listClient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Status[] getEtats() {
		return etats;
	}

	public void setEtats(Status[] etats) {
		this.etats = etats;
	}

	public Status getStatut() {
		return statut;
	}

	public void setStatut(Status statut) {
		this.statut = statut;
	}

	public ServiceFacture getServiceFacture() {
		return serviceFacture;
	}

	public void setServiceFacture(ServiceFacture serviceFacture) {
		this.serviceFacture = serviceFacture;
	}

	public float getTimbres() {
		return timbres;
	}

	public void setTimbres(float timbres) {
		this.timbres = timbres;
	}

	public List<Lignecommande> getListelignefacture() {
		return listelignefacture;
	}

	public void setListelignefacture(List<Lignecommande> listelignefacture) {
		this.listelignefacture = listelignefacture;
	}

	public List<Bonlivraison> getListbonlivraisonbyClient() {
		return listbonlivraisonbyClient;
	}

	public void setListbonlivraisonbyClient(List<Bonlivraison> listbonlivraisonbyClient) {
		this.listbonlivraisonbyClient = listbonlivraisonbyClient;
	}

	public List<Facture> getListfacturebyClient() {
		return listfacturebyClient;
	}

	public void setListfacturebyClient(List<Facture> listfacturebyClient) {
		this.listfacturebyClient = listfacturebyClient;
	}

	public String getNompourfacture() {
		return nompourfacture;
	}

	public void setNompourfacture(String nompourfacture) {
		this.nompourfacture = nompourfacture;
	}

	public Client getClient2() {
		return client2;
	}

	public void setClient2(Client client2) {
		this.client2 = client2;
	}

	public Lignecommande getSelectedLignecommande() {
		return selectedLignecommande;
	}

	public void setSelectedLignecommande(Lignecommande selectedLignecommande) {
		this.selectedLignecommande = selectedLignecommande;
	}

	public Status getStatutfacture() {
		return statutfacture;
	}

	public void setStatutfacture(Status statutfacture) {
		this.statutfacture = statutfacture;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	public List<String> getListProduitFini() {
		return listProduitFini;
	}

	public void setListProduitFini(List<String> listProduitFini) {
		this.listProduitFini = listProduitFini;
	}

	public Paramettre getParamettre() {
		return paramettre;
	}

	public void setParamettre(Paramettre paramettre) {
		this.paramettre = paramettre;
	}

	public String getTypepayement() {
		return typepayement;
	}

	public void setTypepayement(String typepayement) {
		this.typepayement = typepayement;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Lignecommande getSelectedLignecommnade() {
		return selectedLignecommnade;
	}

	public void setSelectedLignecommnade(Lignecommande selectedLignecommnade) {
		this.selectedLignecommnade = selectedLignecommnade;
	}

	public List<Produit> getListprosuit() {
		return listprosuit;
	}

	public void setListprosuit(List<Produit> listprosuit) {
		this.listprosuit = listprosuit;
	}

	public List<String> getListProduitfini() {
		return listProduitfini;
	}

	public Integer getCodes() {
		return codes;
	}

	public void setCodes(Integer codes) {
		this.codes = codes;
	}

	public void setListProduitfini(List<String> listProduitfini) {
		this.listProduitfini = listProduitfini;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public List<String> getListeclient() {
		return listeclient;
	}

	public void setListeclient(List<String> listeclient) {
		this.listeclient = listeclient;
	}

	public String getClients() {
		return clients;
	}

	public void setClients(String clients) {
		this.clients = clients;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/*
	 * public ServiceSociete getServiceSociete() { return serviceSociete; }
	 * 
	 * public void setServiceSociete(ServiceSociete serviceSociete) {
	 * this.serviceSociete = serviceSociete; }
	 */

	public Societe getSociete() {
		return societe;
	}

	public void setSociete(Societe societe) {
		this.societe = societe;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public boolean isTest() {
		return test;
	}

	public void setTest(boolean test) {
		this.test = test;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public List<Facture> getFilterfactures() {
		return filterfactures;
	}

	public void setFilterfactures(List<Facture> filterfactures) {
		this.filterfactures = filterfactures;
	}

	public List<Bonlivraison> getFilterbondelivraisons() {
		return filterbondelivraisons;
	}

	public void setFilterbondelivraisons(List<Bonlivraison> filterbondelivraisons) {
		this.filterbondelivraisons = filterbondelivraisons;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}

	public Vhecule getVhecule() {
		return vhecule;
	}

	public void setVhecule(Vhecule vhecule) {
		this.vhecule = vhecule;
	}

	public Chauffeur getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(Chauffeur chauffeur) {
		this.chauffeur = chauffeur;
	}

	public String getCodevhecule() {
		return codevhecule;
	}

	public void setCodevhecule(String codevhecule) {
		this.codevhecule = codevhecule;
	}

	public String getCodeclient() {
		return codeclient;
	}

	public void setCodeclient(String codeclient) {
		this.codeclient = codeclient;
	}

	public String getCodechaffeur() {
		return codechaffeur;
	}

	public void setCodechaffeur(String codechaffeur) {
		this.codechaffeur = codechaffeur;
	}

	public Integer getCodeproduit() {
		return codeproduit;
	}

	public void setCodeproduit(Integer codeproduit) {
		this.codeproduit = codeproduit;
	}

	public Lignecommande getLigneC() {
		return ligneC;
	}

	public void setLigneC(Lignecommande ligneC) {
		this.ligneC = ligneC;
	}

	public Integer getQte() {
		return qte;
	}

	public void setQte(Integer qte) {
		this.qte = qte;
	}

	public double getMontantht() {
		return montantht;
	}

	public void setMontantht(double montantht) {
		this.montantht = montantht;
	}

	public Integer getTva() {
		return tva;
	}

	public void setTva(Integer tva) {
		this.tva = tva;
	}

	public double getPu() {
		return pu;
	}

	public void setPu(double pu) {
		this.pu = pu;
	}

	public Integer getTests() {
		return tests;
	}

	public void setTests(Integer tests) {
		this.tests = tests;
	}

	public String getMf() {
		return mf;
	}

	public void setMf(String mf) {
		this.mf = mf;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public ServiceTva getServiceTva() {
		return serviceTva;
	}

	public void setServiceTva(ServiceTva serviceTva) {
		this.serviceTva = serviceTva;
	}

	public ServiceParamettre getServiceParamettre() {
		return serviceParamettre;
	}

	public void setServiceParamettre(ServiceParamettre serviceParamettre) {
		this.serviceParamettre = serviceParamettre;
	}

	public Date getDat() {
		return dat;
	}

	public void setDat(Date dat) {
		this.dat = dat;
	}

	public double getTotalttc() {
		return totalttc;
	}

	public void setTotalttc(double totalttc) {
		this.totalttc = totalttc;
	}

	public String getTotalttcs() {
		DecimalFormat df = new DecimalFormat("0.000");
		totalttcs = df.format(totalttc);

		return totalttcs;
	}

	public void setTotalttcs(String totalttcs) {
		this.totalttcs = totalttcs;
	}

	public List<Vhecule> getListeVehicules() {
		return listeVehicules;
	}

	public void setListeVehicules(List<Vhecule> listeVehicules) {
		this.listeVehicules = listeVehicules;
	}

	public List<Chauffeur> getListeChauffeurs() {
		return listeChauffeurs;
	}

	public void setListeChauffeurs(List<Chauffeur> listeChauffeurs) {
		this.listeChauffeurs = listeChauffeurs;
	}

	public List<Produit> getListproduits() {
		return listproduits;
	}

	public void setListproduits(List<Produit> listproduits) {
		this.listproduits = listproduits;
	}
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<Client> getListeClients() {
		return listeClients;
	}

	public void setListeClients(List<Client> listeClients) {
		this.listeClients = listeClients;
	}

	public com.tn.shell.service.transport.ServiceClient getServiceclients() {
		return serviceclients;
	}

	public void setServiceclients(com.tn.shell.service.transport.ServiceClient serviceclients) {
		this.serviceclients = serviceclients;
	}

	public Facturepassager getFacturepassager() {
		return facturepassager;
	}

	public void setFacturepassager(Facturepassager facturepassager) {
		this.facturepassager = facturepassager;
	}

	public ServiceFacturepassager getServicefacturepassager() {
		return servicefacturepassager;
	}

	public void setServicefacturepassager(ServiceFacturepassager servicefacturepassager) {
		this.servicefacturepassager = servicefacturepassager;
	}

	public List<Facturepassager> getListfacturepass() {
		return listfacturepass;
	}

	public void setListfacturepass(List<Facturepassager> listfacturepass) {
		this.listfacturepass = listfacturepass;
	}

	public List<Facturepassager> getFilterfacturepass() {
		return filterfacturepass;
	}

	public void setFilterfacturepass(List<Facturepassager> filterfacturepass) {
		this.filterfacturepass = filterfacturepass;
	}

	public ServiceLigneCommandepass getServiceLigneCommandepass() {
		return serviceLigneCommandepass;
	}

	public void setServiceLigneCommandepass(ServiceLigneCommandepass serviceLigneCommandepass) {
		this.serviceLigneCommandepass = serviceLigneCommandepass;
	}

	public List<Lignecommandepass> getListelignefacturepass() {
		return listelignefacturepass;
	}

	public void setListelignefacturepass(List<Lignecommandepass> listelignefacturepass) {
		this.listelignefacturepass = listelignefacturepass;
	}

}
