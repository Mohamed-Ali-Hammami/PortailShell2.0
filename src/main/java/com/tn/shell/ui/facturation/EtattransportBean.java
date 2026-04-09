package com.tn.shell.ui.facturation;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.Transient;

import com.tn.shell.model.gestat.Utilisateur;
import com.tn.shell.model.paie.Societe;
import com.tn.shell.model.shop.*;

import com.tn.shell.model.transport.*;
import com.tn.shell.service.gestat.UserService;
import com.tn.shell.service.shop.ServiceProduit;
import com.tn.shell.service.transport.*;

@ManagedBean(name = "EtattransportBean")
@SessionScoped
public class EtattransportBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private List<Facture> factureventesbypayement;
	// @ManagedProperty(value = "#{ServiceSociete}")
	// ServiceSociete serviceSociete;

	@ManagedProperty(value = "#{ServiceLigneCommande}")
	ServiceLigneCommande serviceLigneCommande;
	@ManagedProperty(value = "#{ServiceBonLivraison}")
	ServiceBonLivraison servicebonLivraison;
	@ManagedProperty(value = "#{ServiceFacture}")
	ServiceFacture servicefacture;
	@ManagedProperty(value = "#{ServiceClient}")
	ServiceClient serviceClient;
	@ManagedProperty(value = "#{ServiceDepense}")
	ServiceDepense serviceDepense;
	@ManagedProperty(value = "#{ServiceProduit}")
	ServiceProduit serviceProduit;
	@ManagedProperty(value = "#{ServiceChauffeur}")
	ServiceChauffeur serviceChauffeur;
	@ManagedProperty(value = "#{ServiceVhecule}")
	ServiceVhecule serviceVhecule;
	 
	@ManagedProperty(value = "#{UserService}")
	UserService userService;
	
	@ManagedProperty(value = "#{ServiceTracetransport}")
	ServiceTracetransport serviceTrace;
	private Date date1;
	private Date date;
	private Client client;
	private String clients;
	private List<String> listeclient;
	private String dates;
	private String date1s;
	private String date2s;
	private Date date2;
	private Societe societe;
	private String dates1;
	private String dates2;
	private List<Vhecule> listVhecule;
	private List<Chauffeur> listchauffeurs;
	private List<Produit> listProduit;
	private List<Depense> listDepense;
	private List<Bonlivraison> listLivraison;
	private List<Bonlivraison> listBonlivraison;

	private Paramettre param;
	List<String> listVehicules;
	List<String> listChauffeurs;
	private String matricule;
	private String chauffeur;
	private String totalhts;
	private String totaltvas;
	private String totaltimbres;
	private String totalttcs;
	private double gasoil;
	private double petrole;
	private double ssp;
	private double g50;
	private double totaltransport;
	private String totaltransports;
	private double totalht;
	private double totaltva;
	private double totaltimbre;
	private double totalttc;
	private List<Facture> factureventes;

	public void init() {
		date = new Date();

	}

	public String getetats() {
		// societe = serviceSociete.getAll().get(0);
		client = null;
		date1 = null;
		date2 = null;
		List<Client> l = new ArrayList<Client>();
		listeclient = new ArrayList<String>();
		l = serviceClient.getAll();
		if (l.size() > 0) {
			for (Client c : l)
				listeclient.add(c.getNom());
		}
		totalht = 0;
		totaltva = 0;
		totaltimbre = 0;
		totalttc = 0;
		factureventes = new ArrayList<Facture>();
		factureventes = servicefacture.getAll();
		for (Facture f : factureventes) {
			totalht = totalht + f.getTotalht();
			totaltva = totaltva + f.getTotaltva();
			totaltimbre = totaltimbre + f.getTimbres();
			totalttc = totalttc + f.getTotalttc();
		}
		return SUCCESS;
	}

	public String getetatventebydate() {
		try {
			totalht = 0;
			totaltva = 0;
			totaltimbre = 0;
			totalttc = 0;
			client = serviceClient.Findbynom(clients);
			List<Facture> factureventepclients = new ArrayList<Facture>();
			List<Facture> factureventess = new ArrayList<Facture>();
			List<Facture> facturevent = new ArrayList<Facture>();
			factureventes = new ArrayList<Facture>();
			factureventess = servicefacture.getAll();

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			for (Facture f : factureventess) {
				if (date1 != null) {
					Date d1 = dateFormat.parse(dateFormat.format(date1));

					if (d1.compareTo(dateFormat.parse(dateFormat.format(f.getDate()))) <= 0) {
						factureventepclients.add(f);
					}
				}

				if (date1 == null)
					factureventepclients.add(f);
			}
			for (Facture f : factureventepclients) {
				if (date2 != null) {
					Date d2 = dateFormat.parse(dateFormat.format(date2));

					if (d2.compareTo(dateFormat.parse(dateFormat.format(f.getDate()))) >= 0) {
						facturevent.add(f);
					}
				}
				if (date2 == null)
					facturevent.add(f);
			}

			for (Facture f : facturevent) {
				if (client != null && f.getBl().getClient().getCode().equals(client.getCode()))
					factureventes.add(f);
				if (client == null)
					factureventes.add(f);

			}

			for (Facture f : factureventes) {
				totalht = totalht + f.getTotalht();
				totaltva = totaltva + f.getTotaltva();
				totaltimbre = totaltimbre + f.getTimbres();
				totalttc = totalttc + f.getTotalttc();
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		clients = "";
		return SUCCESS;
	}

	public String getetatVehecules() {
		date1 = null;
		date2 = null;
		listVhecule = new ArrayList<Vhecule>();
		listVhecule = serviceVhecule.getAll();
		return SUCCESS;
	}

	public String getetatDepenses() {
		matricule = "";
		date1 = null;
		totaltransport = 0;
		date2 = null;
		listVehicules = new ArrayList<String>();
		List<Vhecule> l = new ArrayList<Vhecule>();
		l = serviceVhecule.getAll();
		for (Vhecule v : l) {
			listVehicules.add(v.getMatricule());
		}
		return SUCCESS;
	}

	public String getetatDepenses1() {
		listDepense = new ArrayList<Depense>();
		List<Depense> listbl = new ArrayList<Depense>();
		List<Depense> listbl1 = new ArrayList<Depense>();
		List<Depense> listbl2 = new ArrayList<Depense>();
		List<Depense> listbl3 = new ArrayList<Depense>();
		listbl = serviceDepense.getAll();
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			for (Depense bl : listbl) {
				if (date1 != null) {
					Date d1 = dateFormat.parse(dateFormat.format(date1));

					if (d1.compareTo(dateFormat.parse(dateFormat.format(bl.getDate()))) <= 0) {
						listbl1.add(bl);
					}
				}

				if (date1 == null)
					listbl1.add(bl);
			}
			for (Depense bl : listbl1) {
				if (date2 != null) {
					Date d2 = dateFormat.parse(dateFormat.format(date2));

					if (d2.compareTo(dateFormat.parse(dateFormat.format(bl.getDate()))) >= 0) {
						listbl2.add(bl);
					}
				}
				if (date2 == null)
					listbl2.add(bl);
			}

			for (Depense bl : listbl2) {

				if ((!matricule.equals("") || !matricule.equals(null))
						&& bl.getVhecule().getMatricule().equals(matricule))
					listbl3.add(bl);
				else if ((matricule.equals("") || matricule.equals(null)))
					listbl3.add(bl);

			}

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		listDepense = new ArrayList<Depense>();
		totaltransport = 0;
		for (Depense d : listbl3) {
			totaltransport = totaltransport + d.getMontant();
			listDepense.add(d);
		}

		return SUCCESS;
	}

	public String getetatProduits() {
		date1 = null;
		date2 = null;
		// entre deux dates
		listProduit = new ArrayList<Produit>();
		listProduit = serviceProduit.getAll();
		return SUCCESS;
	}

	public String getetatChauffeurs() {
		date1 = null;
		date2 = null;
		// entre deux dates
		listchauffeurs = new ArrayList<Chauffeur>();
		listchauffeurs = serviceChauffeur.getAll();
		return SUCCESS;
	}

	public String getetatLivraisons() {
		matricule = "";
		chauffeur = "";

		date1 = null;
		date2 = null;
		listVehicules = new ArrayList<String>();
		listVehicules = serviceVhecule.getAllnom();

		listChauffeurs = new ArrayList<String>();
		listChauffeurs = serviceChauffeur.getAllnom();

		listBonlivraison = new ArrayList<Bonlivraison>();
		return SUCCESS;

	}

	public String getetatclient() {

		clients = "";
		date1 = null;
		date2 = null;

		listeclient = new ArrayList<String>();
		listeclient = serviceClient.getAllnom();
		listBonlivraison = new ArrayList<Bonlivraison>();
		return SUCCESS;

	}

	public String getetatclient2() {
		totaltransport = 0;
		petrole = 0;
		gasoil = 0;
		g50 = 0;
		ssp = 0;
		List<Bonlivraison> listbl = new ArrayList<Bonlivraison>();
		List<Bonlivraison> listbl1 = new ArrayList<Bonlivraison>();
		listLivraison = new ArrayList<Bonlivraison>();

		listBonlivraison = new ArrayList<Bonlivraison>();
		listbl = servicebonLivraison.getAll();

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			for (Bonlivraison f : listbl) {
				if (date1 != null) {
					Date d1 = dateFormat.parse(dateFormat.format(date1));

					if (d1.compareTo(dateFormat.parse(dateFormat.format(f.getDate()))) <= 0) {
						listbl1.add(f);
					}
				}

				if (date1 == null)
					listbl1.add(f);
			}
			for (Bonlivraison f : listbl1) {
				if (date2 != null) {
					Date d2 = dateFormat.parse(dateFormat.format(date2));

					if (d2.compareTo(dateFormat.parse(dateFormat.format(f.getDate()))) >= 0) {
						listLivraison.add(f);
					}
				}
				if (date2 == null)
					listLivraison.add(f);
			}

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (Bonlivraison bl : listLivraison) {
			if ((!clients.equals("") || !clients.equals(null)) && bl.getClient().getNom().equals(clients))
				listBonlivraison.add(bl);
			else if (clients.equals("") || clients.equals(null))
				listBonlivraison.add(bl);

		}

		for (Bonlivraison b : listBonlivraison) {
			double petrole1 = 0;
			double gasoil1 = 0;
			double g501 = 0;
			double ssp1 = 0;
			List<Lignecommande> l = serviceLigneCommande.getLcbyBL(b);
			System.out.println("size" + l.size());
			b.setListeligne(l);
			for (Lignecommande c : l) {
				if (c.getBl() != null && c.getProduit() != null) {
					if (c.getProduit().getId() == serviceProduit.Findbycode(126).getId()) {
						gasoil1 = (gasoil1 + c.getQuantite());
					}

					if (c.getProduit().getId().equals(31)) {
						petrole1 = (petrole1 + c.getQuantite());
					}

					if (c.getProduit().getId().equals(127)) {
						ssp1 = (ssp1 + c.getQuantite());
					}

					if (c.getProduit().getId().equals(684)) {
						g501 = (g501 + c.getQuantite());
					}
				}
			}
			System.out.println(petrole1);
			b.setPetrole(petrole1);
			b.setGasoil(gasoil1);
			b.setG50(g501);
			b.setSsp(ssp1);
			totaltransport = totaltransport + b.getTransport();
			petrole = petrole + petrole1;
			gasoil = gasoil + gasoil1;
			g50 = g50 + g501;
			ssp = ssp + ssp1;
		}

		return SUCCESS;
	}

	public String getetatLivraisons3() {
		matricule = "";
		chauffeur = "";
		date1 = null;
		date2 = null;

		listBonlivraison = new ArrayList<Bonlivraison>();
		return SUCCESS;

	}

	public String getetatLivraisons1() {
		totaltransport = 0;
		petrole = 0;
		gasoil = 0;
		g50 = 0;
		ssp = 0;
		
		List<Bonlivraison> listbl = new ArrayList<Bonlivraison>();
		List<Bonlivraison> listbl1 = new ArrayList<Bonlivraison>();
		listLivraison = new ArrayList<Bonlivraison>();
		List<Bonlivraison> listbl3 = new ArrayList<Bonlivraison>();
		listBonlivraison = new ArrayList<Bonlivraison>();
		listbl = servicebonLivraison.getbonlivraisonbetween(date1, date2);
		 

		for (Bonlivraison bl : listbl) {
			if ((!matricule.equals("") || !matricule.equals(null)) && bl.getVhecule().getMatricule().equals(matricule))
				listbl3.add(bl);
			else if (matricule.equals("") || matricule.equals(null))
				listbl3.add(bl);

		}

		for (Bonlivraison bl : listbl3) {
			if ((!chauffeur.equals(null) || !chauffeur.equals("")) && bl.getChauffeur().getNompenom().equals(chauffeur))
				listBonlivraison.add(bl);

			else if (chauffeur.equals(null) || chauffeur.equals(""))
				listBonlivraison.add(bl);

		}

		for (Bonlivraison b : listBonlivraison) {
			double petrole1 = 0;
			double gasoil1 = 0;
			double g501 = 0;
			double ssp1 = 0;
			List<Lignecommande> l = serviceLigneCommande.getLcbyBL(b);
			System.out.println("size" + l.size());
			b.setListeligne(l);
			for (Lignecommande c : l) {
				if (c.getBl() != null && c.getProduit() != null) {

					if (c.getProduit().getId() == serviceProduit.Findbycode(126).getId()) {
						gasoil1 = (gasoil1 + c.getQuantite());
					}

					if (c.getProduit().getId().equals(31)) {
						petrole1 = (petrole1 + c.getQuantite());
					}

					if (c.getProduit().getId().equals(127)) {
						ssp1 = (ssp1 + c.getQuantite());
					}

					if (c.getProduit().getId().equals(684)) {
						g501 = (g501 + c.getQuantite());
					}

				}
			}
			System.out.println(petrole1);
			b.setPetrole(petrole1);
			b.setGasoil(gasoil1);
			b.setG50(g501);
			b.setSsp(ssp1);
			totaltransport = totaltransport + b.getTransport();
			petrole = petrole + petrole1;
			gasoil = gasoil + gasoil1;
			g50 = g50 + g501;
			ssp = ssp + ssp1;
		}

		return SUCCESS;
	}

	private List<Chauffeur> lc;

	public String getetatLivraisons2() {
		totaltransport = 0;
		petrole = 0;
		gasoil = 0;
		g50 = 0;
		ssp = 0;
		List<Bonlivraison> listbl = new ArrayList<Bonlivraison>();
		List<Bonlivraison> listbl1 = new ArrayList<Bonlivraison>();
		listLivraison = new ArrayList<Bonlivraison>();

		List<Bonlivraison> listbl3 = new ArrayList<Bonlivraison>();
		listBonlivraison = new ArrayList<Bonlivraison>();
		listbl = servicebonLivraison.getAll();
		lc = new ArrayList<Chauffeur>();

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			for (Bonlivraison f : listbl) {
				if (date1 != null) {
					Date d1 = dateFormat.parse(dateFormat.format(date1));

					if (d1.compareTo(dateFormat.parse(dateFormat.format(f.getDate()))) <= 0) {
						listbl1.add(f);
					}
				}

				if (date1 == null)
					listbl1.add(f);
			}
			for (Bonlivraison f : listbl1) {
				if (date2 != null) {
					Date d2 = dateFormat.parse(dateFormat.format(date2));

					if (d2.compareTo(dateFormat.parse(dateFormat.format(f.getDate()))) >= 0) {
						listbl3.add(f);
						if (!lc.contains(f.getChauffeur()))
							lc.add(f.getChauffeur());
					}
				}
				if (date2 == null)
					listbl3.add(f);
			}

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (Chauffeur f : lc) {
			double tr = 0;
			double petrole1 = 0;
			double gasoil1 = 0;
			double g501 = 0;
			double ssp1 = 0;
			for (Bonlivraison b : listbl3) {

				if (f.equals(b.getChauffeur())) {

					List<Lignecommande> l = serviceLigneCommande.getLcbyBL(b);
					System.out.println("size" + l.size());
					b.setListeligne(l);
					for (Lignecommande c : l) {
						if (c.getBl() != null && c.getProduit() != null) {
							if (c.getProduit().getId() == serviceProduit.Findbycode(126).getId()) {
								gasoil1 = (gasoil1 + c.getQuantite());
							}

							if (c.getProduit().getId().equals(31)) {
								petrole1 = (petrole1 + c.getQuantite());
							}

							if (c.getProduit().getId().equals(127)) {
								ssp1 = (ssp1 + c.getQuantite());
							}

							if (c.getProduit().getId().equals(684)) {
								g501 = (g501 + c.getQuantite());
							}

						}
					}
					System.out.println(petrole1);
					b.setPetrole(petrole1);
					b.setGasoil(gasoil1);
					b.setG50(g501);
					b.setSsp(ssp1);
					tr=tr+b.getTransport();
				}

			}
			Bonlivraison bl = new Bonlivraison();
			bl.setChauffeur(f);
			bl.setTransport(tr);
			bl.setPetrole(petrole1);
			bl.setGasoil(gasoil1);
			bl.setG50(g501);
			bl.setSsp(ssp1);
			listBonlivraison.add(bl);
		}
		
		for(Bonlivraison bl:listBonlivraison) {
			totaltransport = totaltransport + bl.getTransport();
			petrole = petrole + bl.getPetrole();
			gasoil = gasoil + bl.getGasoil();
			g50 = g50 + bl.getG50();
			ssp = ssp + bl.getSsp();
		}
		return SUCCESS;
	}

	public List<Facture> getFactureventesbypayement() {
		return factureventesbypayement;
	}

	public void setFactureventesbypayement(List<Facture> factureventesbypayement) {
		this.factureventesbypayement = factureventesbypayement;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Chauffeur> getLc() {
		return lc;
	}

	public void setLc(List<Chauffeur> lc) {
		this.lc = lc;
	}

	public ServiceLigneCommande getServiceLigneCommande() {
		return serviceLigneCommande;
	}

	public void setServiceLigneCommande(ServiceLigneCommande serviceLigneCommande) {
		this.serviceLigneCommande = serviceLigneCommande;
	}

	public ServiceBonLivraison getServicebonLivraison() {
		return servicebonLivraison;
	}

	public void setServicebonLivraison(ServiceBonLivraison servicebonLivraison) {
		this.servicebonLivraison = servicebonLivraison;
	}

	public ServiceFacture getServicefacture() {
		return servicefacture;
	}

	public void setServicefacture(ServiceFacture servicefacture) {
		this.servicefacture = servicefacture;
	}

	public ServiceClient getServiceClient() {
		return serviceClient;
	}

	public void setServiceClient(ServiceClient serviceClient) {
		this.serviceClient = serviceClient;
	}

	public ServiceDepense getServiceDepense() {
		return serviceDepense;
	}

	public void setServiceDepense(ServiceDepense serviceDepense) {
		this.serviceDepense = serviceDepense;
	}

	public ServiceProduit getServiceProduit() {
		return serviceProduit;
	}

	public void setServiceProduit(ServiceProduit serviceProduit) {
		this.serviceProduit = serviceProduit;
	}

	public ServiceVhecule getServiceVhecule() {
		return serviceVhecule;
	}

	public void setServiceVhecule(ServiceVhecule serviceVhecule) {
		this.serviceVhecule = serviceVhecule;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

 
	public double getGasoil() {
		return gasoil;
	}

	public void setGasoil(double gasoil) {
		this.gasoil = gasoil;
	}

	public double getPetrole() {
		return petrole;
	}

	public void setPetrole(double petrole) {
		this.petrole = petrole;
	}

	public double getSsp() {
		return ssp;
	}

	public void setSsp(double ssp) {
		this.ssp = ssp;
	}

	public double getG50() {
		return g50;
	}

	public void setG50(double g50) {
		this.g50 = g50;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getClients() {
		return clients;
	}

	public void setClients(String clients) {
		this.clients = clients;
	}

	public List<String> getListeclient() {
		return listeclient;
	}

	public void setListeclient(List<String> listeclient) {
		this.listeclient = listeclient;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public Societe getSociete() {
		return societe;
	}

	public void setSociete(Societe societe) {
		this.societe = societe;
	}

	public String getDates1() {
		return dates1;
	}

	public void setDates1(String dates1) {
		this.dates1 = dates1;
	}

	public String getDates2() {
		return dates2;
	}

	public void setDates2(String dates2) {
		this.dates2 = dates2;
	}

	public List<Vhecule> getListVhecule() {
		return listVhecule;
	}

	public void setListVhecule(List<Vhecule> listVhecule) {
		this.listVhecule = listVhecule;
	}

	public List<Chauffeur> getListchauffeurs() {
		return listchauffeurs;
	}

	public void setListchauffeurs(List<Chauffeur> listchauffeurs) {
		this.listchauffeurs = listchauffeurs;
	}

	public List<Produit> getListProduit() {
		return listProduit;
	}

	public void setListProduit(List<Produit> listProduit) {
		this.listProduit = listProduit;
	}

	public List<Depense> getListDepense() {
		return listDepense;
	}

	public void setListDepense(List<Depense> listDepense) {
		this.listDepense = listDepense;
	}

	public List<Bonlivraison> getListLivraison() {
		return listLivraison;
	}

	public void setListLivraison(List<Bonlivraison> listLivraison) {
		this.listLivraison = listLivraison;
	}

	public List<Bonlivraison> getListBonlivraison() {
		return listBonlivraison;
	}

	public void setListBonlivraison(List<Bonlivraison> listBonlivraison) {
		this.listBonlivraison = listBonlivraison;
	}

	public Paramettre getParam() {
		return param;
	}

	public void setParam(Paramettre param) {
		this.param = param;
	}

	public ServiceChauffeur getServiceChauffeur() {
		return serviceChauffeur;
	}

	public void setServiceChauffeur(ServiceChauffeur serviceChauffeur) {
		this.serviceChauffeur = serviceChauffeur;
	}

	public List<String> getListVehicules() {
		return listVehicules;
	}

	public void setListVehicules(List<String> listVehicules) {
		this.listVehicules = listVehicules;
	}

	public String getTotalhts() {
		DecimalFormat df = new DecimalFormat("0.000");
		totalhts = df.format(totalht);
		return totalhts;
	}

	public void setTotalhts(String totalhts) {
		this.totalhts = totalhts;
	}

	public String getTotaltvas() {
		DecimalFormat df = new DecimalFormat("0.000");
		totaltvas = df.format(totaltva);
		return totaltvas;
	}

	public void setTotaltvas(String totaltvas) {
		this.totaltvas = totaltvas;
	}

	public String getTotaltimbres() {
		DecimalFormat df = new DecimalFormat("0.000");
		totaltimbres = df.format(totaltimbre);
		return totaltimbres;
	}

	public void setTotaltimbres(String totaltimbres) {
		this.totaltimbres = totaltimbres;
	}

	public String getTotalttcs() {
		DecimalFormat df = new DecimalFormat("0.000");
		totalttcs = df.format(totalttc);
		return totalttcs;
	}

	public void setTotalttcs(String totalttcs) {
		this.totalttcs = totalttcs;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getDate1s() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		if (date1 != null)
			date1s = s.format(date1);
		else
			date1s = "";
		return date1s;
	}

	public void setDate1s(String date1s) {
		this.date1s = date1s;
	}

	public String getDate2s() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		if (date2 != null)
			date2s = s.format(date2);
		else
			date2s = "";
		return date2s;
	}

	public void setDate2s(String date2s) {
		this.date2s = date2s;
	}

	public List<String> getListChauffeurs() {
		return listChauffeurs;
	}

	public void setListChauffeurs(List<String> listChauffeurs) {
		this.listChauffeurs = listChauffeurs;
	}

	public String getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(String chauffeur) {
		this.chauffeur = chauffeur;
	}

	public void setGasoil(Integer gasoil) {
		this.gasoil = gasoil;
	}

	public void setPetrole(Integer petrole) {
		this.petrole = petrole;
	}

	public void setSsp(Integer ssp) {
		this.ssp = ssp;
	}

	public double getTotaltransport() {
		return totaltransport;
	}

	public void setTotaltransport(double totaltransport) {
		this.totaltransport = totaltransport;
	}

	public String getTotaltransports() {
		DecimalFormat df = new DecimalFormat("0.000");
		totaltransports = df.format(totaltransport);
		return totaltransports;
	}

	public void setTotaltransports(String totaltransports) {
		this.totaltransports = totaltransports;
	}

	public double getTotalht() {
		return totalht;
	}

	public void setTotalht(double totalht) {
		this.totalht = totalht;
	}

	public double getTotaltva() {
		return totaltva;
	}

	public void setTotaltva(double totaltva) {
		this.totaltva = totaltva;
	}

	public double getTotaltimbre() {
		return totaltimbre;
	}

	public void setTotaltimbre(double totaltimbre) {
		this.totaltimbre = totaltimbre;
	}

	public double getTotalttc() {
		return totalttc;
	}

	public void setTotalttc(double totalttc) {
		this.totalttc = totalttc;
	}

	public ServiceTracetransport getServiceTrace() {
		return serviceTrace;
	}

	public void setServiceTrace(ServiceTracetransport serviceTrace) {
		this.serviceTrace = serviceTrace;
	}

	public List<Facture> getFactureventes() {
		return factureventes;
	}

	public void setFactureventes(List<Facture> factureventes) {
		this.factureventes = factureventes;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	

}
