package com.tn.shell.ui.gestat;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.CellEditEvent;

import com.tn.shell.model.paie.Societe;
import com.tn.shell.model.shop.Paramettre;
import com.tn.shell.model.shop.Produit;
import com.tn.shell.model.transport.Client;
import com.tn.shell.model.transport.Facture;
import com.tn.shell.model.transport.Facturepassager;
import com.tn.shell.model.transport.Lignecommande;
import com.tn.shell.model.transport.Lignecommandepass;
import com.tn.shell.service.gestat.UserService;
import com.tn.shell.service.shop.ServiceParamettre;
import com.tn.shell.service.shop.ServiceProduit;
import com.tn.shell.service.shop.ServiceTva;
import com.tn.shell.service.transport.ServiceBonLivraison;
import com.tn.shell.service.transport.ServiceClient;
import com.tn.shell.service.transport.ServiceFacture;
import com.tn.shell.service.transport.ServiceLigneCommande;
import com.tn.shell.ui.transport.Convert;

@ManagedBean(name = "DevisBena")
@SessionScoped
public class DevisBena {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{ServiceFacture}")
	ServiceFacture servicefacture;
	@ManagedProperty(value = "#{ServiceBonLivraison}")
	ServiceBonLivraison serviceBonLivraison;
	@ManagedProperty(value = "#{ServiceProduit}")
	ServiceProduit serviceProduit;
	@ManagedProperty(value = "#{ServiceClient}")
	ServiceClient serviceclients;
	@ManagedProperty(value = "#{ServiceLigneCommande}")
	ServiceLigneCommande serviceLigneCommande;
	@ManagedProperty(value = "#{ServiceParamettre}")
	ServiceParamettre serviceParamettre;
	@ManagedProperty(value = "#{ServiceTva}")
	ServiceTva serviceTva;
	@ManagedProperty(value = "#{UserService}")
	UserService userService;
	private List<Lignecommandepass> listelignefacturepass;
	private Societe societe;
	private List<Produit> listeProduits;
	private List<Produit> listeProduit;
	private Date date;
	private Facture facture;
	private String nom;
	private Client client;
	List<Lignecommande> listeligne;
	List<Client> listeClients;
	private String code;
	private String mf;
	private int codeproduit;
	private double totalttc = 0;
	private String codeclient;
	private double transport = 0;
	private Produit produit;
	private Facturepassager facturepassager;
	private int codes;
	private String totalttcs;
	@PostConstruct
	public void init() {
		listeProduit = new ArrayList<Produit>();
		listeProduit = serviceProduit.getAll();
		listeClients = new ArrayList<Client>(); 
	}

	public String nouveaufacure() {
		/*List<Paramettre> listP= serviceParamettre.getAll();
		Paramettre p=null;
		if(listP.size()>0)
		  p=listP.get(0);*/
		code = null;
		mf = null;
		codeproduit = 0;
		date = null;
		// listeChauffeurs = serviceChauffeur.getAll();
		listeClients = new ArrayList<Client>();
		//listeClients = serviceclients.getAll();
		// listproduits = serviceProduit.getAll();
		listeProduits = new ArrayList<Produit>();
		for (int i = 0; i < 30; i++) {
			Produit p1 = new Produit();
			p1.setQuantites(0);
			listeProduits.add(p1);
		}
		totalttc = 0;
		codeclient = null;
		transport = 0;
		return SUCCESS;
	}
	public void getdates2(AjaxBehaviorEvent event) {
		listeClients = new ArrayList<Client>();
		listeClients = serviceclients.getAll();
	}
	public void getTransportbyclient2(AjaxBehaviorEvent event) {
		client = serviceclients.Findbynom(codeclient);
		mf = client.getMf();
		transport = client.getTransport();
	}
	public void updateCode(AjaxBehaviorEvent event) {
		produit = serviceProduit.Findbycode(codeproduit);
		produit.setQuantites(1);
		listeProduits.set(codes, produit);
	}
	
	public void onCellEdit2(CellEditEvent event) {
		produit = listeProduits.get(event.getRowIndex());
		codes = event.getRowIndex();
		listeProduits.set(codes, produit);

	}

	public void updatenom(AjaxBehaviorEvent event) {
		Produit produit2 = serviceProduit.Findbydes(code);
		produit.setVente(produit2.getVente());
		produit.setId(produit2.getId());
		produit.setTva(produit2.getTva());
		produit.setNom(produit2.getNom());
		produit.setCode(produit2.getCode());
		produit.setQuantites(1);
		listeProduits.set(codes, produit);
		code = null;
		codes = 0;
		totalttc = 0;
		for (Produit produit : listeProduits) {
			if (produit.getCode() != null) {
				totalttc = totalttc + (produit.getQuantites() * produit.getVente());

			}
		}
	}
	public void updatemontant(AjaxBehaviorEvent event) {
		listeProduits.set(codes, produit);
		totalttc = 0;
		for (Produit produit : listeProduits) {
			if (produit.getCode() != null) {
				totalttc = totalttc + (produit.getQuantites() * produit.getVente());

			}
		}

	}
	
	
	
	
	public String savefacture() {
		try { 
		listelignefacturepass=new ArrayList<Lignecommandepass>();
			double mh = 0;
			double totaltva = 0;
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
			for (Lignecommandepass c : listelignefacturepass) {
				mh = mh + c.getMantantht();
				totaltva = totaltva + c.getTauxtva();
			}
			if (!mf.equals(null) || !mf.equals(""))
				client.setMf(mf);
			Paramettre p = serviceParamettre.getAll().get(0);
			facturepassager = new Facturepassager();
			facturepassager.setDate(date);
			facturepassager.setClient(client);			 
		 
			Convert C = new Convert();
			facturepassager.setDate(date);
			facturepassager.setTimbres(0); 
			facturepassager.setTotaltva(totaltva);
			facturepassager.setTotalht(mh);
			facturepassager.setTotalttc(
					facturepassager.getTotalht() + facturepassager.getTotaltva() );
			String s = "";
			double ss = facturepassager.getTotalttc();
			int e = new Float(ss).intValue();
			int cs = facturepassager.getTotalttcs().indexOf(",");
			s = facturepassager.getTotalttcs().substring(cs + 1, facturepassager.getTotalttcs().length());
			facturepassager.setSommes(C.convertt(e) + " dinars et  " + s + " millimes");
			 
			 
		} catch (Exception e) {
			System.out.println("erreur");
		}
		return SUCCESS;
	}

	public ServiceFacture getServicefacture() {
		return servicefacture;
	}

	public void setServicefacture(ServiceFacture servicefacture) {
		this.servicefacture = servicefacture;
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

	public ServiceClient getServiceclients() {
		return serviceclients;
	}

	public void setServiceclients(ServiceClient serviceclients) {
		this.serviceclients = serviceclients;
	}

	public ServiceLigneCommande getServiceLigneCommande() {
		return serviceLigneCommande;
	}

	public void setServiceLigneCommande(ServiceLigneCommande serviceLigneCommande) {
		this.serviceLigneCommande = serviceLigneCommande;
	}

	public ServiceParamettre getServiceParamettre() {
		return serviceParamettre;
	}

	public void setServiceParamettre(ServiceParamettre serviceParamettre) {
		this.serviceParamettre = serviceParamettre;
	}

	public ServiceTva getServiceTva() {
		return serviceTva;
	}

	public void setServiceTva(ServiceTva serviceTva) {
		this.serviceTva = serviceTva;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Societe getSociete() {
		return societe;
	}

	public void setSociete(Societe societe) {
		this.societe = societe;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
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

	public List<Lignecommande> getListeligne() {
		return listeligne;
	}

	public void setListeligne(List<Lignecommande> listeligne) {
		this.listeligne = listeligne;
	}

	public List<Client> getListeClients() {
		return listeClients;
	}

	public void setListeClients(List<Client> listeClients) {
		this.listeClients = listeClients;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMf() {
		return mf;
	}

	public void setMf(String mf) {
		this.mf = mf;
	}

	public int getCodeproduit() {
		return codeproduit;
	}

	public void setCodeproduit(int codeproduit) {
		this.codeproduit = codeproduit;
	}

	public double getTotalttc() {
		return totalttc;
	}

	public void setTotalttc(double totalttc) {
		this.totalttc = totalttc;
	}

	public String getCodeclient() {
		return codeclient;
	}

	public void setCodeclient(String codeclient) {
		this.codeclient = codeclient;
	}

	public double getTransport() {
		return transport;
	}

	public void setTransport(double transport) {
		this.transport = transport;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getCodes() {
		return codes;
	}

	public void setCodes(int codes) {
		this.codes = codes;
	}

	public List<Lignecommandepass> getListelignefacturepass() {
		return listelignefacturepass;
	}

	public void setListelignefacturepass(List<Lignecommandepass> listelignefacturepass) {
		this.listelignefacturepass = listelignefacturepass;
	}

	public Facturepassager getFacturepassager() {
		return facturepassager;
	}

	public void setFacturepassager(Facturepassager facturepassager) {
		this.facturepassager = facturepassager;
	}


	public String getTotalttcs() {
		DecimalFormat df = new DecimalFormat("0.000");
		totalttcs = df.format(totalttc);

		return totalttcs;
	}

	public void setTotalttcs(String totalttcs) {
		this.totalttcs = totalttcs;
	}

	public List<Produit> getListeProduit() {
		return listeProduit;
	}

	public void setListeProduit(List<Produit> listeProduit) {
		this.listeProduit = listeProduit;
	}
	
	
	
}
