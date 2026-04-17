package com.tn.shell.ui.shop;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.Depensegestat;
import com.tn.shell.model.gestat.EProfil;
import com.tn.shell.model.gestat.Employeegestat;
import com.tn.shell.model.gestat.Md5;
import com.tn.shell.model.gestat.Utilisateur;
import com.tn.shell.model.shop.*;
import com.tn.shell.model.transport.Bonlivraison;
import com.tn.shell.service.gestat.UserService;
import com.tn.shell.service.shop.*;
import com.tn.shell.service.transport.ServiceBonLivraison;
import com.tn.shell.ui.common.UiDateDefaults;

@ManagedBean(name = "VenteBean")
@SessionScoped
public class VenteBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{ServiceProduit}")
	ServiceProduit serviceProduit;
	@ManagedProperty(value = "#{ServiceTraceshop}")
	ServiceTraceshop servicetrace;
	@ManagedProperty(value = "#{UserService}")
	UserService userservice;
	@ManagedProperty(value = "#{ServiceFournisseur}")
	ServiceFournisseur serviceFournisseur;
	@ManagedProperty(value = "#{ServiceBonLivraison}")
    ServiceBonLivraison serviceBonlivraison;
	@ManagedProperty(value = "#{ServiceFamilleaticle}")
	ServiceFamilleaticle serviceFamilleaticle;

	@ManagedProperty(value = "#{ServiceVente}")
	ServiceVente servicevente;

	@ManagedProperty(value = "#{ServiceParamettre}")
	ServiceParamettre serviceParamettre;

	@ManagedProperty(value = "#{ServiceTva}")
	ServiceTva serviceTva;
	@ManagedProperty(value = "#{ServiceTransfert}")
	ServiceTransfert serviceTransfert;

	@ManagedProperty(value = "#{ServiceLigneAlimentation}")
	ServiceLigneAlimentation serviceLigneAlimentation;

	@ManagedProperty(value = "#{ServiceLignevente}")
	ServiceLignevente serviceLignevente;

	@ManagedProperty(value = "#{ServiceTicket}")
	ServiceTicket serviceticket;

	@ManagedProperty(value = "#{ServiceLignetransfert}")
	ServiceLignetransfert serviceLignetransfert;
	private String userName;
	private String password;
	private Utilisateur user;
	@ManagedProperty(value = "#{authenticationManager}")
	private AuthenticationManager authenticationManager = null;

	@ManagedProperty(value = "#{UserService}")
	UserService userService;

	private List<Fournisseur> listfournisseur;
	private List<String> listProduits;
	private String numFacture;
	private Integer numvente;
	private Date date;
	private List<Produit> listProduit;
	private List<Produit> listproduit;
	private Integer code;
	private Vente vente;
	private String nomfournissuer;
	private Fournisseur fourniseur;
	private Produit produit;
	private Integer codes;
	private Integer codeproduit;
	private String libelle;
	private Integer qte;
	private double totalquantite;
	private double totalrecus;
	private double totalrendus;
	private String banque;
	private String numcheque;
	private TypePayement[] typepayements;
	private Date echeance;
	private String typepayement;
	private List<Vente> listvente;
	private String dates;
	private List<Lignealimentation> listeLigne;

	private Integer codeshop;
	private String totalhtachat;
	private String totaltvaachat;
	private String totalttcsachat;
	private String totalht;
	private String totaltva;
	private String totalttcs;
	private double totalttc;
	private Integer numticket;
	private Status[] status;
	private Status statu;
	private Ticket ticket;
	private List<Lignevente> listelignevente;
	private Poste[] postes;
	private Poste poste;
	private List<Ticket> listticket;
	private List<Lignevente> listticketnegtif;
	private List<Lignetransert> listtransfert;
	private List<Lignetransert> listtransfertnegatif;
	private TypeGeneration gen;
	private double totalqteTicknegatif;
	private double totalqteTransnegatif;
	private List<Tva> listtva;
	private Lignevente lignevente;
	private Lignetransert lignetransfert;
	private List<Famillearticle> listfamile;
	private List<Produit> selectedsproduit;
	private String codess;
	private String nom;
	private Integer quantite;
	private double achat;
	private Integer tva;
	private double marge;
	private double ventes;
	private String prixvente;
	private Integer qtemin;
	private List<String> listBanque;
	private List<String> listMois;
	private List<Produit> filteredProduit;
	private Famillearticle famille;
	private String codefamille;
	private List<Produit> filterproduits;
	private Produit selectedProduit;
	private Stock stock;
	private Stock[] stocks;
	private List<String> selectedfamilles;
	private List<Famillearticle> listFamille;

	/*
	 * Impression inventaire
	 **/
	@PostConstruct
	public void init() {
		listProduit = new ArrayList<Produit>();
		listProduit = serviceProduit.getAll();
		initializeShopBusinessDate();

	}

	public void chargerproduit(ActionEvent actionEvent) {
		listProduit = new ArrayList<Produit>();
		listProduit = serviceProduit.getAll();
	}
	
	public String importerTrnsport() {
		List<Bonlivraison> listBL=new ArrayList<Bonlivraison>();
		 String time=null;
		try {
		BufferedReader IN = new BufferedReader(new FileReader("M:/FTP_E00/num.txt"));
		Integer s = Integer.parseInt(IN.readLine());
		IN.close();
		String ch=null;
		
		BufferedReader IN2 = new BufferedReader(new FileReader("M:/FTP_E00/000" + (s) + ".P"));
		String ligne;
		while ((ligne = IN2.readLine()) != null) {

			if (ligne.contains("FDATI")) {
				 ch=ligne.substring(14);
				 String chaine=ch.substring(0,4);
				 Integer sch=Integer.parseInt(chaine.substring(0,2))-1;
				 time=sch+":"+chaine.substring(2);	
			 break;
			}
		}
		} catch (IOException ex) {

		}

		listBL=serviceBonlivraison.getBLNonAffectee();
		 
		if(listBL !=null) {
			double totalTTC=0;
			Integer qte=0;
			listelignevente=new ArrayList<Lignevente>();
			for(Bonlivraison bl:listBL) {		 
				Timestamp timebl=convertTime(bl.getHeure());
				Timestamp  timeCloture=convertTime(time);
				
				if(timebl.before(timeCloture)) {
					bl.setAffectee(true);
					serviceBonlivraison.update(bl);
                    Lignevente l=new Lignevente();
                    qte = qte + 1;
					l.setDate(new Date());
					Produit p=serviceProduit.getProduitbyCodeAbare("T"+bl.getTransport());
					if(p!=null) {
					l.setProduit(p);
					l.setQuantite(1);
					l.setTva(l.getProduit().getTva());
					l.setPrix(l.getProduit().getVente() / (100 + l.getTva()) * 100);
					l.setMantantht(l.getQuantite() * l.getPrix());
					l.setTauxtva(l.getMantantht() * l.getTva() * 0.01);
					//l.setVente(vente);
					l.setPrixachat(l.getProduit().getAchat());
					l.setDates("");
					//l.setTicket(ticket);
					l.setTotaltva(l.getTauxtva());
					l.setGeneration(TypeGeneration.NonSauver);
					l.setTotalttc(l.getQuantite() * l.getProduit().getVente());
					totalttc=totalttc+l.getTotalttc();
					listelignevente.add(l);
					} 
				}
			
			}
			
			date = new Date();
			SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
			dates = s.format(date);
			ticket = new Ticket();
			ticket.setDates(dates);
			ticket.setDate(date);
			DecimalFormat df = new DecimalFormat("0.000");
			DecimalFormat df2 = new DecimalFormat("0");
			ticket.setTotal_vente(totalttc);
			ticket.setGen(TypeGeneration.Sauver);
			ticket.setTotal_ventes(df.format(totalttc));
			totalrecus = totalttc;
			totalrendus = totalrecus - totalttc;
			ticket.setTotal_recu(totalrecus);
			ticket.setTotal_rendu(totalrendus);
			serviceticket.save(ticket);
			vente = new Vente();
			vente.setDate(date);
			vente.setTicket(ticket);
			servicevente.save(vente);
			for(Lignevente l:listelignevente) {
				l.setTicket(ticket);
				l.setVente(vente);
				serviceLignevente.save(l);
			}
			ticket.setVentes(listelignevente);
			ticket.setTotalVente(df2.format(qte));
			
			PageFormat format = new PageFormat();
			Paper paper = new Paper();

			double paperWidth = 3.25;
			double paperHeight = 11.69;
			double leftMargin = 0.19;
			double rightMargin = 0.25;
			double topMargin = 0;
			double bottomMargin = 0.01;

			paper.setImageableArea(leftMargin * 72.0, topMargin * 72.0, (paperWidth - leftMargin - rightMargin) * 72.0,
					(paperHeight - topMargin - bottomMargin) * 72.0);

			format.setPaper(paper);
			PrintService[] printServices = PrinterJob.lookupPrintServices();

			PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
			aset.add(OrientationRequested.PORTRAIT);
			aset.add(MediaSizeName.INVOICE);
			PrinterJob printerJob = PrinterJob.getPrinterJob();

			for (PrintService printService : printServices) {
				if (printService.getName().contains("PETIT")) {
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, printService.getName(), "");
					this.codefamille = printService.getName();
					FacesContext.getCurrentInstance().addMessage(null, msg);
					// printerJob.setPrintService( printService);
				}

			}
			Printable printable = new PrintRectangle(ticket);
			// format = printerJob.validatePage(format);
			printerJob.setPrintable(printable);
			try {
				printerJob.print(aset);

			} catch (Exception e) {
				codefamille = "\n\n\n" + e.getMessage() + "\n\n\n" + e.getStackTrace();
				for (PrintService printService : printServices) {
					codefamille = codefamille + printService.getName();

				}

				// return ERROR;
			}
		}
		return SUCCESS;
	}
	
	private Timestamp convertTime(String ch) {
		 
		  try {
			    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
			    Date parsedDate = dateFormat.parse(ch);
			    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			    return timestamp;
			} catch(Exception e) { //this generic but you can control another types of exception
			   return null;
			}
	}

	public String stock() {
		/*
		 * listproduit = new ArrayList<Produit>(); listproduit =
		 * serviceProduit.getAll();
		 */
		return SUCCESS;
	}

	public String connect() {
		try {

			Md5 var = new Md5(this.getPassword());
			Authentication request = new UsernamePasswordAuthenticationToken(this.getUserName(), var.getCode());
			Authentication result = authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
			Collection<? extends GrantedAuthority> auths = result.getAuthorities();
			user = userService.getCurrentUser();

		} catch (AuthenticationException e) {
			String message = "Login ou mot de passe incorrecte";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
			return "incorrect";
		}
		if (user.isCaissier()) {
			testmoins = false;
			return "incorrect";
		} else {
			testmoins = true;
			savevente();
			return "correct";
		}

	}

	public String impressioninventaire() {
		listfamile = new ArrayList<Famillearticle>();
		listfamile = serviceFamilleaticle.getAll();
		initializeShopBusinessDate();
		stocks = Stock.values();
		listProduit = new ArrayList<Produit>();
		totalht = "0.000";
		totaltva = "0.000";
		totalttcs = "0.000";
		totalhtachat = "0.000";
		totaltvaachat = "0.000";
		totalttcsachat = "0.000";
		totalttc = 0;
		return SUCCESS;
	}

	public void inventaire() {
		DecimalFormat df = new DecimalFormat("0.000");
		this.totalhtachat = df.format(0);
		this.totaltvaachat = df.format(0);
		this.totalttcsachat = df.format(0);

		this.totalht = df.format(0);
		this.totaltva = df.format(0);
		this.totalttcs = df.format(0);

		date = new Date();
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);

		DecimalFormat df2 = new DecimalFormat("0");
		listProduit = new ArrayList<Produit>();
		double totalquantite = 0;
		if (stock.equals(Stock.Shop)) {
			totalquantites = df2.format(totalquantite);
			double totalachatht = 0;
			double totaltvaachat = 0;
			double totalachatttc = 0;
			double totalventeht = 0;
			double totaltvavente = 0;
			double totalventettc = 0;
			if (selectedfamilles.get(0).equals("TOUTES LES FAMILLES)"))
				listProduit = serviceProduit.getAll();
			else
				listProduit = serviceProduit.getAllbyfamilles(selectedfamilles.get(0));
			for (Produit p : listProduit) {
				p.setMontantv(p.getQuantitestock() * p.getVente());
				p.setMontantvs(df.format(p.getMontant()));
				p.setMontant(p.getQuantitestock() * p.getAchat());
				p.setMontants(df.format(p.getMontant()));
				totalquantite = totalquantite + p.getQuantitestock();

				totalachatttc = totalachatttc + p.getMontant() + (p.getMontant() * p.getTva() / 100);
				totalachatht = totalachatht + (p.getMontant());
				totaltvaachat = totalachatttc - totalachatht;
				totalventettc = totalventettc + p.getMontantv();
				totalventeht = totalventeht + p.getMontantv() - (p.getMontantv() * p.getTva() / 100);
				totaltvavente = totalventettc - totalventeht;

			}

			this.totalhtachat = df.format(totalachatht);
			this.totaltvaachat = df.format(totaltvaachat);
			this.totalttcsachat = df.format(totalachatttc);
			this.totalquantites = df2.format(totalquantite);
			this.totalht = df.format(totalventeht);
			this.totaltva = df.format(totaltvavente);
			this.totalttcs = df.format(totalventettc);
		} else if (stock.equals(Stock.Depot)) {
			double totalachatht = 0;
			double totaltvaachat = 0;
			double totalachatttc = 0;
			double totalventeht = 0;
			double totaltvavente = 0;
			double totalventettc = 0;
			if (selectedfamilles.get(0).equals("TOUTES LES FAMILLES)"))
				listProduit = serviceProduit.getAll();
			else
				listProduit = serviceProduit.getAllbyfamille(selectedfamilles.get(0));
			for (Produit p : listProduit) {
				p.setMontant(p.getQuantitedepot() * p.getAchat());
				p.setMontants(df.format(p.getMontant()));
				p.setMontantv(p.getQuantitedepot() * p.getVente());
				p.setMontantvs(df.format(p.getMontantv()));
				totalquantite = totalquantite + p.getQuantitedepot();

				totalachatttc = totalachatttc + p.getMontant() + (p.getMontant() * p.getTva() / 100);
				totalachatht = totalachatht + p.getMontant();
				totaltvaachat = totalachatttc - totalachatht;
				totalventettc = totalventettc + p.getMontantv();
				totalventeht = totalventeht + p.getMontantv() - (p.getMontantv() * p.getTva() / 100);
				totaltvavente = totalventettc - totalventeht;

			}
			this.totalhtachat = df.format(totalachatht);
			this.totaltvaachat = df.format(totaltvaachat);
			this.totalttcsachat = df.format(totalachatttc);
			this.totalquantites = df2.format(totalquantite);
			this.totalht = df.format(totalventeht);
			this.totaltva = df.format(totaltvavente);
			this.totalttcs = df.format(totalventettc);
		} else {
			double totalachatht = 0;
			double totaltvaachat = 0;
			double totalachatttc = 0;
			double totalventeht = 0;
			double totaltvavente = 0;
			double totalventettc = 0;
			if (selectedfamilles.get(0).equals("TOUTES LES FAMILLES)"))
				listProduit = serviceProduit.getAll();
			else
				listProduit = serviceProduit.getAllbyfamille(selectedfamilles.get(0));

			for (Produit p : listProduit) {
				p.setMontant((p.getQuantitedepot() + p.getQuantitestock()) * p.getAchat());
				p.setMontants(df.format(p.getMontant()));
				p.setMontantv((p.getQuantitedepot() + p.getQuantitestock()) * p.getVente());
				p.setMontantvs(df.format(p.getMontantv()));
				totalquantite = totalquantite + p.getQuantitedepot() + p.getQuantitestock();

				totalachatttc = totalachatttc + p.getMontant() + (p.getMontant() * p.getTva() / 100);
				totalachatht = totalachatht + (p.getAchat() * (p.getQuantitestock() + p.getQuantitedepot()));
				totaltvaachat = totalachatttc - totalachatht;
				totalventettc = totalventettc + (p.getMontantv());
				totalventeht = totalventeht + p.getMontantv() - (p.getMontantv() * p.getTva() / 100);
				totaltvavente = totalventettc - totalventeht;
			}

			this.totalhtachat = df.format(totalachatht);
			this.totaltvaachat = df.format(totaltvaachat);
			this.totalttcsachat = df.format(totalachatttc);
			this.totalquantites = df2.format(totalquantite);
			this.totalht = df.format(totalventeht);
			this.totaltva = df.format(totaltvavente);
			this.totalttcs = df.format(totalventettc);
		}

	}

	public void getproduitbyfamille(AjaxBehaviorEvent e) {
		listProduit = new ArrayList<Produit>();

		if (selectedfamilles.get(0).equals("TOUTES LES FAMILLES)"))
			listProduit = serviceProduit.getAll();
		else
			listProduit = serviceProduit.getAllbyfamille(selectedfamilles.get(0));
	}

	public void getproduit(ActionEvent event) {
 		listProduit = new ArrayList<Produit>();
        listProduit = serviceProduit.getAll();
	}

	/** nouveau vente */
	public String nouveauvente() {
		totalquantite = 0;
		testmoins = false;
		totalrendus = 0;
		totalrecus = 0;
		totalttc = 0;
		totalquantite = 0;
		totalttc = 0;
		totalttcs = "";
		typepayements = TypePayement.values();
		typepayement = TypePayement.espece.toString();
		code = null;
		nomfournissuer = null;

		numvente = servicevente.getmaxcode() + 1;
		// numvente = servicevente.getmaxcode() + 1;
		fourniseur = null;

		listelignevente = new ArrayList<Lignevente>();
		for (int i = 1; i <= 75; i++) {
			Lignevente p = new Lignevente();
			p.setProduit(null);
			p.setQuantite(0);
			 
			listelignevente.add(p);
		}
		date = new Date();
		echeance = new Date();
		numFacture = null;

		libelle = null;
		codeproduit = null;

		return SUCCESS;
	}

	public String majarticle() {
		listtva = new ArrayList<Tva>();
		listtva = serviceTva.getAll();
		listfamile = new ArrayList<Famillearticle>();
		listfamile = serviceFamilleaticle.getAll();
		marge = 0;
		tva = 0;
		nom = null;
		codeshop = 0;
		code = null;
		ventes = 0;
		achat = 0;
		return SUCCESS;
	}

	public String majstock() {
		listProduit = new ArrayList<Produit>();
		listProduit = serviceProduit.getAll();

		return SUCCESS;
	}

	public String remiseazeoStock() {
		return SUCCESS;
	}

	public String validerremiseazeoStock() {
		listProduit = new ArrayList<Produit>();
		listProduit = serviceProduit.getAll();
		for (Produit p : listProduit) {
			p.setQuantitedepot(0);
			p.setQuantitestock(0);
			serviceProduit.update(p);
		}
		listProduit = new ArrayList<Produit>();
		listProduit = serviceProduit.getAll();
		return SUCCESS;
	}

	public String savearticle() {
		try {
			famille = serviceFamilleaticle.findbyDesignation(codefamille);
			produit = serviceProduit.Findbycodes(codess);
			if (produit != null) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "code a barre du produit exist deja",
						codess);

				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				if (prixvente.contains(".")) {
					ventes = Double.parseDouble(prixvente);
					 
				} else
					ventes = parsedouble(prixvente);
				produit = new Produit();
				DecimalFormat df = new DecimalFormat("0.000");
				produit.setNom(nom);
				produit.setMarge(parsedouble(marges));
				produit.setQtemin(qtemin);
				produit.setAchat(achat);
				produit.setCode(codess);
				produit.setFamille(famille);
				produit.setCodeshop(codeshop);
				produit.setTva(tva);
				produit.setVente(ventes);
				serviceProduit.save(produit);
			}
			nom = null;

			marge = 0;
			qtemin = 0;
			achat = 0;
			codess = null;
			famille = null;
			tva = 0;
			ventes = 0;
		} catch (Exception e) {
		}
		return SUCCESS;
	}

	private String marges;

	public void getlistproduitbyfamille(AjaxBehaviorEvent event) {
		List<Produit> p = new ArrayList<Produit>();
		p = serviceProduit.getAllbyfamille2(codefamille);
		codeshop = p.get(p.size() - 1).getCodeshop() + 1;
	}

	public void getmargebyvente(AjaxBehaviorEvent event) {
		Double tv = (double) tva / 100 + 1;
		marges = null;
		// double m = (parsedouble(prixvente) / (achat * tv))*100;
		DecimalFormat df = new DecimalFormat("0.000");
		// marges= df.format(m/10);
		double v = 0;
		double m = 0;
		if (prixvente.contains(".")) {
			v = Double.parseDouble(prixvente);
			m = v / (achat * tv);
		} else
			m = parsedouble(prixvente) / (achat * tv);
		marges = df.format((m - 1) * 100);

	}

	public double parsedouble(String prixvente) {
		NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
		Number number = 0;
		try {
			number = format.parse(prixvente);
		} catch (ParseException e) {
		}
		return number.doubleValue();
	}

	public void getventebymarge(AjaxBehaviorEvent event) {
		Double tv = (double) tva / 100 + 1;
		double x = achat + (achat * tva / 100);
		ventes = x + (x * parsedouble(marges) / 100);
		DecimalFormat df = new DecimalFormat("0.000");
		prixvente = df.format(ventes);
	}

	public void updatevente(AjaxBehaviorEvent event) {
		achat = selectedProduit.getAchat();
		DecimalFormat df = new DecimalFormat("0.000");
		marge = selectedProduit.getMarge();
		// marges=selectedProduit.getMarges();
		tva = selectedProduit.getTva();

		double x = achat + (achat * tva / 100);
		ventes = x + (x * marge / 100);

		selectedProduit.setVente(ventes);
		selectedProduit.setPrixventes(df.format(ventes));
		// selectedProduit.setMarges(df.format(marge));
		updateProduit(selectedProduit);
	}

	public void updatetva(AjaxBehaviorEvent event) {

		achat = selectedProduit.getAchat();
		ventes = selectedProduit.getVente();
		tva = selectedProduit.getTva();
		Double tv = (double) tva / 100 + 1;
		double m = ventes / (achat * tv);

		DecimalFormat df = new DecimalFormat("0.000");
		selectedProduit.setPrixventes(df.format(ventes));
		marge = (m - 1) * 100;
		selectedProduit.setMarge(marge);
		selectedProduit.setMarges(df.format(marge));
		updateProduit(selectedProduit);

	}
	public void updateqtemin(AjaxBehaviorEvent event) {
		 
		 
		updateProduit(selectedProduit);
	}
	public void updatemarge(AjaxBehaviorEvent event) {
		achat = selectedProduit.getAchat();
		ventes = selectedProduit.getVente();
		tva = selectedProduit.getTva();
		Double tv = (double) tva / 100 + 1;

		double m = ventes / (achat * tv);

		DecimalFormat df = new DecimalFormat("0.000");
		selectedProduit.setPrixventes(df.format(ventes));
		marge = (m - 1) * 100;
		selectedProduit.setMarge(marge);
		selectedProduit.setMarges(df.format(marge));
		updateProduit(selectedProduit);
	}

	public String getAllarticle() {
		listProduit = new ArrayList<Produit>();
		listProduit = serviceProduit.getAll();
		return SUCCESS;
	}

	@Transactional
	public String savevente() {
		// generation de ticket
		totalrendus = 0;
		totalrecus = 0;
		date = new Date();
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		ticket = new Ticket();
		ticket.setDates(dates);
		ticket.setDate(date);
		DecimalFormat df = new DecimalFormat("0.000");
		ticket.setTotal_vente(totalttc);
		ticket.setGen(TypeGeneration.Sauver);
		ticket.setTotal_ventes(df.format(totalttc));
		totalrecus = totalttc;
		totalrendus = totalrecus - totalttc;
		ticket.setTotal_recu(totalrecus);
		ticket.setTotal_rendu(totalrendus);
		serviceticket.save(ticket);
		vente = new Vente();
		vente.setDate(date);
		vente.setTicket(ticket);
		servicevente.save(vente);
		for (Lignevente l : listelignevente) {
			if (l.getProduit() != null) {

				l.setDate(date);
				l.setTva(l.getProduit().getTva());
				l.setPrix(l.getProduit().getVente() / (100 + l.getTva()) * 100);
				l.setMantantht(l.getQuantite() * l.getPrix());
				l.setTauxtva(l.getMantantht() * l.getTva() * 0.01);
				l.setVente(vente);
				l.setPrixachat(l.getProduit().getAchat());
				l.setDates("");
				l.setTicket(ticket);
				l.setTotaltva(l.getTauxtva());
				l.setGeneration(TypeGeneration.NonSauver);
				l.setTotalttc(l.getQuantite() * l.getProduit().getVente());
				serviceLignevente.save(l);
				l.getProduit().setQuantitestock(l.getProduit().getQuantitestock() - l.getQuantite());
				serviceProduit.update(l.getProduit());

			}
		}
		ticket.setVentes(listelignevente);

		PageFormat format = new PageFormat();
		Paper paper = new Paper();

		double paperWidth = 3.25;
		double paperHeight = 11.69;
		double leftMargin = 0.19;
		double rightMargin = 0.25;
		double topMargin = 0;
		double bottomMargin = 0.01;

		paper.setImageableArea(leftMargin * 72.0, topMargin * 72.0, (paperWidth - leftMargin - rightMargin) * 72.0,
				(paperHeight - topMargin - bottomMargin) * 72.0);

		format.setPaper(paper);
		PrintService[] printServices = PrinterJob.lookupPrintServices();

		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		aset.add(OrientationRequested.PORTRAIT);
		aset.add(MediaSizeName.INVOICE);
		PrinterJob printerJob = PrinterJob.getPrinterJob();

		for (PrintService printService : printServices) {
			if (printService.getName().contains("PETIT")) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, printService.getName(), "");
				this.codefamille = printService.getName();
				FacesContext.getCurrentInstance().addMessage(null, msg);
				// printerJob.setPrintService( printService);
			}

		}
		Printable printable = new PrintRectangle(ticket);
		// format = printerJob.validatePage(format);
		printerJob.setPrintable(printable);
		try {
			printerJob.print(aset);

		} catch (Exception e) {
			codefamille = "\n\n\n" + e.getMessage() + "\n\n\n" + e.getStackTrace();
			for (PrintService printService : printServices) {
				codefamille = codefamille + printService.getName();

			}

		}
		listelignevente = new ArrayList<Lignevente>();
		for (int i = 0; i < 75; i++) {
			Lignevente p = new Lignevente();
			p.setProduit(null);
			p.setQuantite(0);
			listelignevente.add(p);
		}

		totalrendus = 0;
		totalquantite = 0;
		totalrecus = 0;
		codes = 0;
		totalttc = 0;
		libelle = null;
		codeproduit = null;
		numvente = servicevente.getmaxcode() + 1;

		return SUCCESS;
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void updateTotalrecus(AjaxBehaviorEvent event) {
		totalrendus = totalrecus - totalttc;
		ticket.setTotal_recu(totalrecus);
		ticket.setTotal_rendu(totalrendus);
		serviceticket.update(ticket);

	}

	public void getCodefocus(AjaxBehaviorEvent event) {
		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");

	}
	public void saveselection(ActionEvent event) {  
		Integer index = verifierarticle(selectedProduit, codes);
		 	DecimalFormat df = new DecimalFormat("0.000");
		if (index == codes) {
			lignevente = listelignevente.get(codes);
			if(lignevente.getProduit()==null) {
			lignevente.setLibelle(selectedProduit.getCode());
			
			}
			 if(lignevente.getProduit()!=null && index == codes) {
				lignevente=new Lignevente();
				lignevente.setLibelle(selectedProduit.getCode());
			}
			    Produit p = serviceProduit.Findbycodes(lignevente.getLibelle());
				lignevente.setProduit(p);
				lignevente.setQuantite(1);				
				lignevente.setTotalttc(lignevente.getQuantite() * p.getVente());
				lignevente.setTotalttcs(df.format(lignevente.getTotalttc()));
			listelignevente.set(codes, lignevente);
		}  if (index != codes) {
			lignevente = listelignevente.get(index);
			lignevente.setQuantite(lignevente.getQuantite() + 1);
			 				
				lignevente.setTotalttc(lignevente.getQuantite() *lignevente.getProduit().getVente());
				lignevente.setTotalttcs(df.format(lignevente.getTotalttc()));
			listelignevente.set(index, lignevente);
		}
		selectedProduit = null;
		 
		produit = null;
		lignevente = null;

	}
	public void updateCode(AjaxBehaviorEvent event) {
		UIComponent component = event.getComponent();
		DecimalFormat df = new DecimalFormat("0.000");
		codes = (Integer) component.getAttributes().get("test");
		lignevente = listelignevente.get(codes);
		Produit p = serviceProduit.Findbycodes(lignevente.getLibelle());
		if(p.getFamille().getCode()!=4 && p.getFamille().getCode()!=5 && p.getFamille().getCode()!=9 && p.getQuantitestock()>0) {
		
		Integer index = verifierarticle(p, codes);
		if (index == codes) {	 
			lignevente.setProduit(p);
			lignevente.setQuantite(1);			
			lignevente.setTotalttc(lignevente.getQuantite() * p.getVente());
			lignevente.setTotalttcs(df.format(lignevente.getTotalttc()));
			listelignevente.set(codes, lignevente);
		} else {
			lignevente.setLibelle(null);
			lignevente = listelignevente.get(index);
			lignevente.setQuantite(lignevente.getQuantite() + 1);
			lignevente.setTotalttc(lignevente.getQuantite() * p.getVente());
			lignevente.setTotalttcs(df.format(lignevente.getTotalttc()));			
			listelignevente.set(index, lignevente);
		   }
		totalttc = 0;
		totalquantite = 0;
		totalrecus = 0;
		}
		 
		
		else if(p.getFamille().getCode()==4 || p.getFamille().getCode()==5 || p.getFamille().getCode()==9) {
			
			Integer index = verifierarticle(p, codes);
			if (index == codes) {	
				lignevente.setProduit(p);
				lignevente.setQuantite(1);			
				lignevente.setTotalttc(lignevente.getQuantite() * p.getVente());
				lignevente.setTotalttcs(df.format(lignevente.getTotalttc()));
				listelignevente.set(codes, lignevente);
			} else {
				lignevente.setLibelle(null);
				lignevente = listelignevente.get(index);
				lignevente.setQuantite(lignevente.getQuantite() + 1);
				lignevente.setTotalttc(lignevente.getQuantite() * p.getVente());
				lignevente.setTotalttcs(df.format(lignevente.getTotalttc()));
				
				listelignevente.set(index, lignevente);
				 
			}
			totalttc = 0;
			totalquantite = 0;
			totalrecus = 0;
			}
		else lignevente.setLibelle(null);
		for (Lignevente d : listelignevente) {
			totalttc = totalttc + d.getTotalttc();
			totalquantite = totalquantite + d.getQuantite();
		}

		totalttcs = df.format(totalttc);
		lignevente = null;
		totalrecus = totalttc;

	}
	
	 
	public void updatequantite(AjaxBehaviorEvent event) {
		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");

		Produit p = serviceProduit.Findbycodes(libelle);
		DecimalFormat df = new DecimalFormat("0.000");
		p.setMontant(p.getQuantites() * p.getVente());
		p.setMontants(df.format(p.getMontant()));
		listproduit.set(codes, p);
	}

	private boolean testmoins;

	public void updatetotalvente(AjaxBehaviorEvent event) {
		totalrecus = 0;
		totalttc = 0;
		totalquantite = 0;
		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");

		lignevente = listelignevente.get(codes);
		DecimalFormat df = new DecimalFormat("0.000");

		lignevente.setTotalttc(lignevente.getQuantite() * lignevente.getProduit().getVente());
		lignevente.setTotalttcs(df.format(lignevente.getTotalttc()));
		listelignevente.set(codes, lignevente);
		for (Lignevente p : listelignevente) {
			totalquantite = totalquantite + p.getQuantite();
			totalttc = totalttc + p.getTotalttc();
		}
		totalttcs = df.format(totalttc);
		totalrecus = totalttc;

	}

	private Integer verifierarticle(Produit libelle, Integer i) {
		
		for (int j = 0;j < listelignevente.size();j++) {
			if (listelignevente.get(j).getProduit() != null)
					if( libelle.getCode().equals( listelignevente.get(j).getProduit().getCode()))
				return j; 
		}
		return i;
	}
	
private Integer verifierarticle2(Produit libelle, Integer i) {
		
		for (int j = 0;j < listLignetransfert.size();j++) {
			if (listLignetransfert.get(j).getProduit() != null)
					if( libelle.getCode().equals( listLignetransfert.get(j).getProduit().getCode()))
				return j; 
		}
		return i;
	}

	public void save(ActionEvent actionEvent) {

		selectedProduit = null;
	}

	public void handleChanges(ValueChangeEvent event) {

		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
	}

	

	public void updatenoms(AjaxBehaviorEvent event) {
		updateProduit(selectedProduit);
	}

	public void updatenom(AjaxBehaviorEvent event) {
		Produit produit2 = serviceProduit.Findbydes(libelle);
		produit.setVente(produit2.getVente());
		produit.setId(produit2.getId());
		produit.setTva(produit2.getTva());
		produit.setNom(produit2.getNom());
		produit.setCode(produit2.getCode());
		produit.setMontant(produit.getQuantites() * produit.getVente());
		listproduit.set(codes, produit);
	}

	public void updatemontant(AjaxBehaviorEvent event) {

		produit.setMontant(produit.getVente() * produit.getQuantites());
		listproduit.set(codes, produit);
		totalttc = 0;
		totalquantite = 0;
		for (Produit produit : listproduit) {
			if (produit.getCode() != null) {
				totalttc = totalttc + (produit.getQuantites() * produit.getVente());
				totalquantite = totalquantite + produit.getQuantites();
			}
		}

	}

	
	 

	public void onCellEdit(CellEditEvent event) {
		produit = listproduit.get(event.getRowIndex());
		codes = event.getRowIndex();
		listproduit.set(codes, produit);
		updateProduit(produit);
	}

	public String impressionVente() {
		listelignevente = new ArrayList<Lignevente>();
		listtransfert = new ArrayList<Lignetransert>();
		listtransfertnegatif = new ArrayList<Lignetransert>();

		initializeShopBusinessDate();
		postes = Poste.values();
		return SUCCESS;
	}

	public String impressionVente2() {
		listelignevente = new ArrayList<Lignevente>();
		listtransfert = new ArrayList<Lignetransert>();
		listtransfertnegatif = new ArrayList<Lignetransert>();

		initializeShopBusinessDate();
		postes = Poste.values();
		return SUCCESS;
	}

	public String etatVenteParFamille() {
		initializeShopBusinessDate();
		postes = Poste.values();
		listelignevente = new ArrayList<Lignevente>();
		listfamile = new ArrayList<Famillearticle>();
		totalttc = 0;
		totalttcs = "";
		totalquantite = 0;
		return SUCCESS;
	}

	public String getetatventeparDate2() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);

		totalttc = 0;
		totalttcs = "";
		totalquantite = 0;
		listelignevente = new ArrayList<Lignevente>();
		DecimalFormat df = new DecimalFormat("0.000");
		List<Lignevente> lvente22 = new ArrayList<Lignevente>();
		List<Produit> lp = new ArrayList<Produit>();
		lvente22 = serviceLignevente.getAllventeparposteandDate33(dates, poste);
		for (Lignevente l : lvente22) {
			if (!lp.contains(l.getProduit()))
				lp.add(l.getProduit());
		}
		try {
			listfamile = new ArrayList<Famillearticle>();
			listfamile = serviceFamilleaticle.getAll();

			for (Famillearticle f : listfamile) {
				lp = serviceProduit.getAllbyfamille(f.getNom());

				double qteparp = 0;
				double mtparp = 0;

				for (Lignevente lv : lvente22) {
					if (lv.getProduit().getFamille().getCode() == f.getCode()) {
						qteparp = qteparp + lv.getQuantite();
						mtparp = mtparp + lv.getTotalttc();

					}

				}
				f.setQuantite(qteparp);
				f.setMontant(df.format(mtparp));

				f.setMontants(mtparp);

			}

		} catch (Exception e) {
		}
		for (Famillearticle f : listfamile) {
			totalquantite = totalquantite + f.getQuantite();
			totalttc = totalttc + f.getMontants();
		}
		totalttcs = df.format(totalttc);
		DecimalFormat df2 = new DecimalFormat("0");
		totalquantites = df2.format(totalquantite);
		return SUCCESS;
	}

	private String totalquantites;

	 

	public String imprimeretatventeparDate() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		double qte = 0;
		double m = 0;
		totalttc = 0;
		double totalht = 0;
		double totaltva = 0;
		totalquantite = 0;
		double totalventemagazin = 0;
		listelignevente = new ArrayList<Lignevente>();
		listticketnegtif = new ArrayList<Lignevente>();
		DecimalFormat df = new DecimalFormat("0.000");
		List<Lignevente> lvente2 = new ArrayList<Lignevente>();
		List<Produit> lp = new ArrayList<Produit>();
		lvente2 = serviceLignevente.getAllventeparposteandDate3(dates, poste);
		for (Lignevente l : lvente2) {
			if (!lp.contains(l.getProduit()))
				lp.add(l.getProduit());
		}
		for (Produit p : lp) {
			Lignevente lvv = new Lignevente();
			Lignevente lvvn = new Lignevente();
			double qteparp = 0;
			double mtparp = 0;
			double prvente = 0;
			double qteparpn = 0;
			double mtparpn = 0;
			double prventen = 0;
			Vente v = null;
			boolean test = false;

			for (Lignevente lv : lvente2) {
				if (lv.getProduit().getId() == p.getId()) {
					if (lv.getQuantite() > 0) {
						totalventemagazin = totalventemagazin + lv.getTotalttc();
						qteparp = qteparp + lv.getQuantite();
						mtparp = mtparp + lv.getTotalttc();
						prvente = lv.getPrix();
					} else {
						test = true;
						totalqteTicknegatif = totalqteTicknegatif + lv.getQuantite();
						qteparpn = qteparpn + lv.getQuantite();
						mtparpn = mtparpn + lv.getTotalttc();
						prventen = lv.getPrix();
						v = lv.getVente();
					}

				}
			}

			if (test == true) {
				lvvn.setProduit(p);
				lvvn.setQuantite(qteparpn);
				lvvn.setPrix(prventen);
				lvvn.setTotalttc(mtparpn);
				lvvn.setVente(v);
				listticketnegtif.add(lvvn);
			}
			lvv.setProduit(p);
			lvv.setQuantite(qteparp + qteparpn);
			lvv.setPrix(prvente);
			lvv.setTotalttc(mtparp + mtparpn);
			if (lvv.getQuantite() != 0) {
				totalht = totalht + lvv.getMantantht();
				totaltva = totaltva + lvv.getTauxtva();
				totalttc = totalttc + lvv.getTotalttc();
				listelignevente.add(lvv);
			}

		}

		listtransfert = new ArrayList<Lignetransert>();
		listtransfert = serviceLignetransfert.getAllbydate(dates);

		totalttcs = df.format(totalttc);
		this.totalht = df.format(totalht);
		this.totaltva = df.format(totaltva);
		return SUCCESS;
	}

	public String imprimeretatventeparDate33() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		double qte = 0;
		double m = 0;
		totalttc = 0;
		double totalht = 0;
		double totaltva = 0;
		totalquantite = 0;
		double totalventemagazin = 0;
		listelignevente = new ArrayList<Lignevente>();
		listticketnegtif = new ArrayList<Lignevente>();
		DecimalFormat df = new DecimalFormat("0.000");
		List<Lignevente> lvente2 = new ArrayList<Lignevente>();
		List<Produit> lp = new ArrayList<Produit>();
		lvente2 = serviceLignevente.getAllventeparposteandDate33(dates, poste);
		for (Lignevente l : lvente2) {
			if (!lp.contains(l.getProduit()))
				lp.add(l.getProduit());
		}
		for (Produit p : lp) {
			Lignevente lvv = new Lignevente();
			Lignevente lvvn = new Lignevente();
			double qteparp = 0;
			double mtparp = 0;
			double prvente = 0;
			double qteparpn = 0;
			double mtparpn = 0;
			double prventen = 0;
			Vente v = null;
			boolean test = false;

			for (Lignevente lv : lvente2) {
				if (lv.getProduit().getId() == p.getId()) {
					if (lv.getQuantite() > 0) {
						totalventemagazin = totalventemagazin + lv.getTotalttc();
						qteparp = qteparp + lv.getQuantite();
						mtparp = mtparp + lv.getTotalttc();
						prvente = lv.getPrix();
					} else {
						test = true;
						totalqteTicknegatif = totalqteTicknegatif + lv.getQuantite();
						qteparpn = qteparpn + lv.getQuantite();
						mtparpn = mtparpn + lv.getTotalttc();
						prventen = lv.getPrix();
						v = lv.getVente();
					}

				}
			}

			if (test == true) {
				lvvn.setProduit(p);
				lvvn.setQuantite(qteparpn);
				lvvn.setPrix(prventen);
				lvvn.setTotalttc(mtparpn);
				lvvn.setVente(v);
				listticketnegtif.add(lvvn);
			}
			lvv.setProduit(p);
			lvv.setQuantite(qteparp + qteparpn);
			lvv.setPrix(prvente);
			lvv.setTotalttc(mtparp + mtparpn);
			if (lvv.getQuantite() != 0) {
				totalht = totalht + lvv.getMantantht();
				totaltva = totaltva + lvv.getTauxtva();
				totalttc = totalttc + lvv.getTotalttc();
				listelignevente.add(lvv);
			}

		}

		listtransfert = new ArrayList<Lignetransert>();
		listtransfert = serviceLignetransfert.getAllbydate(dates);

		totalttcs = df.format(totalttc);
		this.totalht = df.format(totalht);
		this.totaltva = df.format(totaltva);
		return SUCCESS;
	}

	public String imprimeretatventeparDates() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		double qte = 0;
		double m = 0;
		totalttc = 0;
		double totalht = 0;
		double totaltva = 0;
		totalquantite = 0;
		listelignevente = new ArrayList<Lignevente>();
		listticketnegtif = new ArrayList<Lignevente>();
		DecimalFormat df = new DecimalFormat("0.000");
		List<Lignevente> lvente2 = new ArrayList<Lignevente>();
		List<Produit> lp = new ArrayList<Produit>();
		lvente2 = serviceLignevente.getAllventeparposteandDate3(dates, poste);
		for (Lignevente l : lvente2) {
			lp.add(l.getProduit());
		}
		try {
			listfamile = new ArrayList<Famillearticle>();
			// listfamile=serviceFamilleaticle.getAll();
			// List<Produit> lp=new ArrayList<Produit>();
			double totalventemagazin = 0;
			// for (Famillearticle f : listfamile) {
			// lp=serviceProduit.getAllbyfamille(f.getNom());
			qte = 0;
			m = 0;
			for (Produit p : lp) {

				Lignevente lvv = new Lignevente();
				Lignevente lvvn = new Lignevente();
				double qteparp = 0;
				double mtparp = 0;
				double prvente = 0;
				double qteparpn = 0;
				double mtparpn = 0;
				double prventen = 0;
				Vente v = null;

				boolean test = false;
				for (Lignevente lv : lvente2) {
					if (lv.getQuantite() > 0) {
						totalventemagazin = totalventemagazin + lv.getTotalttc();

						qteparp = qteparp + lv.getQuantite();
						mtparp = mtparp + lv.getTotalttc();
						prvente = lv.getPrix();
						p = lv.getProduit();
					} else {

						test = true;
						totalqteTicknegatif = totalqteTicknegatif + lv.getQuantite();
						qteparpn = qteparpn + lv.getQuantite();
						mtparpn = mtparpn + lv.getTotalttc();
						prventen = lv.getPrix();
						v = lv.getVente();
						p = lv.getProduit();
					}

					qte = qte + lv.getQuantite();
					m = m + lv.getTotalttc();

				}
				if (test == true) {
					lvvn.setProduit(p);
					lvvn.setQuantite(qteparpn);
					lvvn.setPrix(prventen);
					lvvn.setTotalttc(mtparpn);
					lvvn.setVente(v);
					listticketnegtif.add(lvvn);
				}
				lvv.setProduit(p);
				lvv.setQuantite(qteparp + qteparpn);
				lvv.setPrix(prvente);
				lvv.setTotalttc(mtparp + mtparpn);
				if (lvv.getQuantite() != 0) {
					totalht = totalht + lvv.getMantantht();
					totaltva = totaltva + lvv.getTauxtva();
					totalttc = totalttc + lvv.getTotalttc();
					listelignevente.add(lvv);
				}

				// }

			}

		} catch (Exception e) {
		}

		listtransfert = new ArrayList<Lignetransert>();
		listtransfert = serviceLignetransfert.getAllbydate(dates);
		listtransfertnegatif = new ArrayList<Lignetransert>();
		// listtransfertnegatif = serviceLignetransfert.getAllbydatemoins(dates);

		totalttcs = df.format(totalttc);
		this.totalht = df.format(totalht);
		this.totaltva = df.format(totaltva);
		// totalht=0
		return SUCCESS;
	}

	public void getetatventeparDate2(AjaxBehaviorEvent event) {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		double qte = 0;
		double m = 0;
		totalttc = 0;
		totalquantite = 0;
		listelignevente = new ArrayList<Lignevente>();
		listticketnegtif = new ArrayList<Lignevente>();
		DecimalFormat df = new DecimalFormat("0.000");
		try {
			listfamile = new ArrayList<Famillearticle>();
			listfamile = serviceFamilleaticle.getAll();
			List<Produit> lp = new ArrayList<Produit>();

			double totalventemagazin = 0;
			for (Famillearticle f : listfamile) {
				lp = serviceProduit.getAllbyfamille(f.getNom());
				qte = 0;
				m = 0;
				List<Lignevente> lvente2 = new ArrayList<Lignevente>();

				for (Produit p : lp) {
					lvente2 = serviceLignevente.getAllventeparparfamille2(p.getNom(), dates, poste);
					if (lvente2.size() > 0) {
						Lignevente lvv = new Lignevente();
						Lignevente lvvn = new Lignevente();
						double qteparp = 0;
						double mtparp = 0;
						double prvente = 0;

						double qteparpn = 0;
						double mtparpn = 0;
						double prventen = 0;
						Vente v = null;
						boolean test = false;
						for (Lignevente lv : lvente2) {
							if (lv.getQuantite() > 0) {
								totalventemagazin = totalventemagazin + lv.getTotalttc();

								qteparp = qteparp + lv.getQuantite();
								mtparp = mtparp + lv.getTotalttc();
								prvente = lv.getPrix();
							} else {
								test = true;
								totalqteTicknegatif = totalqteTicknegatif + lv.getQuantite();
								qteparpn = qteparpn + lv.getQuantite();
								mtparpn = mtparpn + lv.getTotalttc();
								prventen = lv.getPrix();
								v = lv.getVente();
							}

							qte = qte + lv.getQuantite();
							m = m + lv.getTotalttc();

						}
						totalventemagazin = qteparp + mtparpn;
						totalquantite = qteparp + qteparpn;
						if (test == true) {
							lvvn.setProduit(p);
							lvvn.setQuantite(qteparpn);
							lvvn.setPrix(prventen);
							lvvn.setTotalttc(mtparpn);
							lvvn.setVente(v);
							listticketnegtif.add(lvvn);
						}
						lvv.setProduit(p);
						lvv.setQuantite(qteparp);
						lvv.setPrix(prvente);
						lvv.setTotalttc(mtparp);
						listelignevente.add(lvv);
					}
					f.setQuantite(qte);
					f.setMontant(df.format(m));

				}
			}

		} catch (Exception e) {
		}

		listtransfert = new ArrayList<Lignetransert>();
		listtransfert = serviceLignetransfert.getAllbydate(dates);
		listtransfertnegatif = new ArrayList<Lignetransert>();
		listtransfertnegatif = serviceLignetransfert.getAllbydatemoins(dates);

		totalttcs = df.format(totalttc);

		// totalht=0
	}

	public void getetatventeparDate3(AjaxBehaviorEvent event) {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");

		dates = s.format(date);
		double qte = 0;
		double m = 0;
		totalttc = 0;
		totalquantite = 0;
		listfamile = new ArrayList<Famillearticle>();
		listfamile = serviceFamilleaticle.getAll();
		listelignevente = new ArrayList<Lignevente>();
		listelignevente = serviceLignevente.getAllventeparposteandDate3(dates, poste);
		DecimalFormat df = new DecimalFormat("0.000");
		for (Famillearticle f : listfamile) {
			qte = 0;
			m = 0;
			for (Lignevente l : listelignevente) {
				if (l.getProduit().getFamille() != null && l.getProduit().getFamille().getNom().equals(f.getNom())) {
					qte = qte + l.getQuantite();
					m = m + l.getTotalttc();
				}
			}

			f.setQuantite(qte);
			f.setMontant(df.format(m));
			totalttc = totalttc + m;
			totalquantite = totalquantite + qte;
		}
		totalttcs = df.format(totalttc);
		totaltvaachat = df.format(totalquantite);
		// totalht=0
	}

	public void getetatventeparDate(AjaxBehaviorEvent event) {
		DecimalFormat df = new DecimalFormat("0.000");
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		dates = f.format(date);
		totalttc = 0;
		totalttcs = df.format(totalttc);
		listelignevente = new ArrayList<Lignevente>();
		listelignevente = serviceLignevente.getAllventeparposteandDate2(dates, poste);
		for (Lignevente l : listelignevente) {
			/*
			 * l.getProduit().setQuantitestock(l.getProduit().getQuantitestock()-l.
			 * getQuantite()); serviceProduit.update(l.getProduit());
			 */
			totalttc = totalttc + l.getTotalttc();
			/*
			 * totalht = df.format(serviceLignevente.getSommeht(date, poste)); totaltva =
			 * df.format(serviceLignevente.getSommetva(date, poste));
			 */
		}
		try {

			listticketnegtif = new ArrayList<Lignevente>();
			listticketnegtif = serviceLignevente.getAllventeparposteNegatif(dates, poste);

		} catch (Exception e) {
		}
	}

	public String impressionTicket() {
		numticket = null;
		ticket = null;
		listelignevente = new ArrayList<Lignevente>();
		return SUCCESS;
	}

	public void getticketbyId(AjaxBehaviorEvent event) {

		ticket = serviceticket.getbyvaleur(numticket);
		if (ticket != null) {
			listelignevente = new ArrayList<Lignevente>();
			listelignevente = serviceLignevente.getAllbyticket(ticket);
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"le numero ticket " + numticket + " n'exite pas", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public String regenerationDesVentes() {
		initializeShopBusinessDate();
		postes = Poste.values();
		totalttc = 0;
		listelignevente = new ArrayList<Lignevente>();
		return SUCCESS;
	}

	public String generationDesVentes() {
		initializeShopBusinessDate();
		DecimalFormat df = new DecimalFormat("0.000");
		totalttc = 0;

		postes = Poste.values();

		totalttc = 0;
		listelignevente = new ArrayList<Lignevente>();
		try {
			listelignevente = serviceLignevente.getAllventeparposte(dates);
			Lignevente v = serviceLignevente.getlignebyid(listelignevente.get(0).getId() - 1);
			if (v != null && v.getGeneration().equals(TypeGeneration.Cloture)) {
				if (v.getPoste().equals(Poste.Poste1))
					poste = Poste.Poste2;
				else
					poste = Poste.Poste1;
			} else
				poste = v.getPoste();
			if (listelignevente.size() > 0)
				for (Lignevente l : listelignevente) {
					totalttc = totalttc + l.getTotalttc();
					if (!l.getDates().equals(dates)) {
						l.setDates(dates);
						serviceLignevente.update(l);
						l.setDate(date);
					}
				}
			totalttcs = df.format(totalttc);
		} catch (Exception e) {
		}
		return SUCCESS;
	}

	public String generer() {
		DecimalFormat df = new DecimalFormat("0.000");
		totalttc = 0;

		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		date = new Date();
		dates = s.format(date);
		// listelignevente = new ArrayList<Lignevente>();
		// listelignevente = serviceLignevente.getAllventeparposte(dates);

		for (Lignevente l : listelignevente) {
			l.setPoste(poste);
			l.setGeneration(TypeGeneration.Sauver);
			serviceLignevente.update(l);
			totalttc = totalttc + l.getTotalttc();
		}
		listelignevente = new ArrayList<Lignevente>();
		totalttcs = "";
		return SUCCESS;
	}

	public String clotureCaisse() {
		initializeShopBusinessDate();
		Lignevente v = serviceLignevente.getmaxlignevente();
		if (v != null && v.getPoste().equals(Poste.Poste1))
			poste = Poste.Poste2;
		else
			poste = Poste.Poste1;
		postes = Poste.values();
		double qte = 0;
		double m = 0;
		totalttc = 0;
		DecimalFormat df = new DecimalFormat("0.000");
		DecimalFormat df2 = new DecimalFormat("0");
		totalquantite = 0;
		listfamile = new ArrayList<Famillearticle>();
		listfamile = serviceFamilleaticle.getAll();
		listelignevente = new ArrayList<Lignevente>();
		try {
			listelignevente = serviceLignevente.getAllventeparposteandDate(dates, poste);

			for (Famillearticle f : listfamile) {
				qte = 0;
				m = 0;
				if (listelignevente != null)
					for (Lignevente l : listelignevente) {
						if (l.getProduit().getFamille() != null
								&& l.getProduit().getFamille().getNom().equals(f.getNom())) {
							qte = qte + l.getQuantite();
							m = m + l.getTotalttc();
						}
					}

				f.setQuantite(qte);
				f.setMontant(df.format(m));
				totalttc = totalttc + m;
				totalquantite = totalquantite + qte;
			}
		} catch (Exception e) {
		}
		totalttcs = df.format(totalttc);
		totalquantites = df2.format(totalquantite);
		return SUCCESS;
	}

	private void initializeShopBusinessDate() {
		Date latestBusinessDate = resolveLatestShopBusinessDate();
		date = UiDateDefaults.startOfDay(date == null ? latestBusinessDate : date);
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
	}

	private Date resolveLatestShopBusinessDate() {
		Date latestBusinessDate = null;
		Lignevente latestLignevente = serviceLignevente.getmaxlignevente();
		if (latestLignevente != null && latestLignevente.getDate() != null) {
			latestBusinessDate = latestLignevente.getDate();
		}

		List<Ticket> tickets = serviceticket.getAll();
		if (tickets != null) {
			for (Ticket ticketCourant : tickets) {
				if (ticketCourant != null && ticketCourant.getDate() != null) {
					latestBusinessDate = UiDateDefaults.latest(latestBusinessDate, ticketCourant.getDate());
				}
			}
		}

		List<Vente> ventes = servicevente.getAll();
		if (ventes != null) {
			for (Vente venteCourante : ventes) {
				if (venteCourante != null && venteCourante.getDate() != null) {
					latestBusinessDate = UiDateDefaults.latest(latestBusinessDate, venteCourante.getDate());
				}
			}
		}

		return latestBusinessDate == null ? new Date() : latestBusinessDate;
	}

	public String ValiderclotureCaisse() {
		for (Lignevente l : listelignevente) {
			l.setGeneration(TypeGeneration.Cloture);
			serviceLignevente.update(l);

		}

		// ajouter Etat de profil par famille
		return SUCCESS;
	}

	public void getgenerationVentebyDate(AjaxBehaviorEvent event) {
		DecimalFormat df = new DecimalFormat("0.000");
		listelignevente = new ArrayList<Lignevente>();
		listelignevente = serviceLignevente.getAllventeparposte(dates);

		totalttcs = df.format(serviceLignevente.getTotalTCtbytDate(dates));
	}

	public String regenerer() {
		for (Lignevente l : listelignevente) {
			l.setGeneration(TypeGeneration.Sauver);
			l.setGeneration(TypeGeneration.Cloture);
			l.setPoste(poste);
			serviceLignevente.update(l);
		}
		return SUCCESS;
	}

	/*
	 * getter and setter
	 * 
	 */

	public Integer getCodeproduit() {
		return codeproduit;
	}

	public double getTotalquantite() {
		return totalquantite;
	}

	public void setTotalquantite(Integer totalquantite) {
		this.totalquantite = totalquantite;
	}

	public void setCodeproduit(Integer codeproduit) {
		this.codeproduit = codeproduit;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public ServiceProduit getServiceProduit() {
		return serviceProduit;
	}

	public void setServiceProduit(ServiceProduit serviceProduit) {
		this.serviceProduit = serviceProduit;
	}

	public ServiceFournisseur getServiceFournisseur() {
		return serviceFournisseur;
	}

	public void setServiceFournisseur(ServiceFournisseur serviceFournisseur) {
		this.serviceFournisseur = serviceFournisseur;
	}

	public ServiceFamilleaticle getServiceFamilleaticle() {
		return serviceFamilleaticle;
	}

	public void setServiceFamilleaticle(ServiceFamilleaticle serviceFamilleaticle) {
		this.serviceFamilleaticle = serviceFamilleaticle;
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

	public List<Fournisseur> getListfournisseur() {
		return listfournisseur;
	}

	public void setListfournisseur(List<Fournisseur> listfournisseur) {
		this.listfournisseur = listfournisseur;
	}

	public List<String> getListProduits() {
		return listProduits;
	}

	public void setListProduits(List<String> listProduits) {
		this.listProduits = listProduits;
	}

	public String getNumFacture() {
		return numFacture;
	}

	public void setNumFacture(String numFacture) {
		this.numFacture = numFacture;
	}

	public Integer getNumvente() {
		return numvente;
	}

	public void setNumvente(Integer numvente) {
		this.numvente = numvente;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Produit> getListProduit() {
		return listProduit;
	}

	public void setListProduit(List<Produit> listProduit) {
		this.listProduit = listProduit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getNomfournissuer() {
		return nomfournissuer;
	}

	public void setNomfournissuer(String nomfournissuer) {
		this.nomfournissuer = nomfournissuer;
	}

	public Fournisseur getFourniseur() {
		return fourniseur;
	}

	public void setFourniseur(Fournisseur fourniseur) {
		this.fourniseur = fourniseur;
	}

	public Integer getCodes() {
		return codes;
	}

	public void setCodes(Integer codes) {
		this.codes = codes;
	}

	public String getTotalttcs() {

		return totalttcs;
	}

	public void setTotalttcs(String totalttcs) {
		this.totalttcs = totalttcs;
	}

	public ServiceVente getServicevente() {
		return servicevente;
	}

	public void setServicevente(ServiceVente servicevente) {
		this.servicevente = servicevente;
	}

	public ServiceLigneAlimentation getServiceLigneAlimentation() {
		return serviceLigneAlimentation;
	}

	public void setServiceLigneAlimentation(ServiceLigneAlimentation serviceLigneAlimentation) {
		this.serviceLigneAlimentation = serviceLigneAlimentation;
	}

	public Vente getVente() {
		return vente;
	}

	public void setVente(Vente vente) {
		this.vente = vente;
	}

	public Integer getQte() {
		return qte;
	}

	public void setQte(Integer qte) {
		this.qte = qte;
	}

	public String getBanque() {
		return banque;
	}

	public void setBanque(String banque) {
		this.banque = banque;
	}

	public String getNumcheque() {
		return numcheque;
	}

	public void setNumcheque(String numcheque) {
		this.numcheque = numcheque;
	}

	public TypePayement[] getTypepayements() {
		return typepayements;
	}

	public void setTypepayements(TypePayement[] typepayements) {
		this.typepayements = typepayements;
	}

	public Date getEcheance() {
		return echeance;
	}

	public void setEcheance(Date echeance) {
		this.echeance = echeance;
	}

	public String getTypepayement() {
		return typepayement;
	}

	public void setTypepayement(String typepayement) {
		this.typepayement = typepayement;
	}

	public List<Vente> getListvente() {
		return listvente;
	}

	public void setListvente(List<Vente> listvente) {
		this.listvente = listvente;
	}

	public String getDates() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		return dates;
	}

	public List<Lignealimentation> getListeLigne() {
		return listeLigne;
	}

	public void setListeLigne(List<Lignealimentation> listeLigne) {
		this.listeLigne = listeLigne;
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

	public Produit getProduit() {
		return produit;
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

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public double getTotalttc() {
		return totalttc;
	}

	public void setTotalttc(double totalttc) {
		this.totalttc = totalttc;
	}

	public Integer getNumticket() {
		return numticket;
	}

	public void setNumticket(Integer numticket) {
		this.numticket = numticket;
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

	public TypeGeneration getGen() {
		return gen;
	}

	public void setGen(TypeGeneration gen) {
		this.gen = gen;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public List<Lignetransert> getListtransfert() {
		return listtransfert;
	}

	public void setListtransfert(List<Lignetransert> listtransfert) {
		this.listtransfert = listtransfert;
	}

	public List<Lignetransert> getListtransfertnegatif() {
		return listtransfertnegatif;
	}

	public void setListtransfertnegatif(List<Lignetransert> listtransfertnegatif) {
		this.listtransfertnegatif = listtransfertnegatif;
	}

	public ServiceLignetransfert getServiceLignetransfert() {
		return serviceLignetransfert;
	}

	public void setServiceLignetransfert(ServiceLignetransfert serviceLignetransfert) {
		this.serviceLignetransfert = serviceLignetransfert;
	}

	public double getTotalqteTicknegatif() {
		return totalqteTicknegatif;
	}

	public void setTotalqteTicknegatif(double totalqteTicknegatif) {
		this.totalqteTicknegatif = totalqteTicknegatif;
	}

	public double getTotalqteTransnegatif() {
		return totalqteTransnegatif;
	}

	public void setTotalqteTransnegatif(double totalqteTransnegatif) {
		this.totalqteTransnegatif = totalqteTransnegatif;
	}

	public List<Tva> getListtva() {
		return listtva;
	}

	public void setListtva(List<Tva> listtva) {
		this.listtva = listtva;
	}

	public List<Famillearticle> getListfamile() {
		return listfamile;
	}

	public void setListfamile(List<Famillearticle> listfamile) {
		this.listfamile = listfamile;
	}

	public String getCodess() {
		return codess;
	}

	public void setCodess(String codess) {
		this.codess = codess;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public double getAchat() {
		return achat;
	}

	public void setAchat(double achat) {
		this.achat = achat;
	}

	public Integer getTva() {
		return tva;
	}

	public void setTva(Integer tva) {
		this.tva = tva;
	}

	public double getMarge() {
		return marge;
	}

	public void setMarge(double marge) {
		this.marge = marge;
	}

	public double getVentes() {
		return ventes;
	}

	public void setVentes(double ventes) {
		this.ventes = ventes;
	}

	public Integer getQtemin() {
		return qtemin;
	}

	public void setQtemin(Integer qtemin) {
		this.qtemin = qtemin;
	}

	public Famillearticle getFamille() {
		return famille;
	}

	public void setFamille(Famillearticle famille) {
		this.famille = famille;
	}

	public String getCodefamille() {
		return codefamille;
	}

	public void setCodefamille(String codefamille) {
		this.codefamille = codefamille;
	}

	public List<String> getListBanque() {
		return listBanque;
	}

	public void setListBanque(List<String> listBanque) {
		this.listBanque = listBanque;
	}

	public List<String> getListMois() {
		return listMois;
	}

	public void setListMois(List<String> listMois) {
		this.listMois = listMois;
	}

	public List<Produit> getFilterproduits() {
		return filterproduits;
	}

	public void setFilterproduits(List<Produit> filterproduits) {
		this.filterproduits = filterproduits;
	}

	public Produit getSelectedProduit() {
		return selectedProduit;
	}

	public void setSelectedProduit(Produit selectedProduit) {
		this.selectedProduit = selectedProduit;
	}

	public String getTotalhtachat() {
		return totalhtachat;
	}

	public void setTotalhtachat(String totalhtachat) {
		this.totalhtachat = totalhtachat;
	}

	public String getTotaltvaachat() {
		return totaltvaachat;
	}

	public void setTotaltvaachat(String totaltvaachat) {
		this.totaltvaachat = totaltvaachat;
	}

	public String getTotalttcsachat() {
		return totalttcsachat;
	}

	public void setTotalttcsachat(String totalttcsachat) {
		this.totalttcsachat = totalttcsachat;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Stock[] getStocks() {
		return stocks;
	}

	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}

	public List<String> getSelectedfamilles() {
		return selectedfamilles;
	}

	public void setSelectedfamilles(List<String> selectedfamilles) {
		this.selectedfamilles = selectedfamilles;
	}

	public List<Famillearticle> getListFamille() {
		return listFamille;
	}

	public void setListFamille(List<Famillearticle> listFamille) {
		this.listFamille = listFamille;
	}

	public void onRowEdit2(RowEditEvent event) {

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"produit changÃ©" + " " + ((Produit) event.getObject()).getNom(),
				((Produit) event.getObject()).getNom());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		updateProduit((Produit) event.getObject());

	}

	public void onCEdit(RowEditEvent event) {

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "produit changÃ©",
				((Produit) event.getObject()).getNom());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		updateProduit((Produit) event.getObject());

	}

	public String updateProduit(Produit produit) {
		try {
			getServiceProduit().update(produit);
			Utilisateur user = userservice.getCurrentUser();
			Traceshop t = new Traceshop();
			t.setAction("modificaion produit" + produit.getNom() + " par" + user.getNom());
			t.setDate(new Date());
			t.setUtilisateur(user);
			servicetrace.save(t);
			return SUCCESS;
		} catch (DataAccessException e) {
		}

		return ERROR;
	}

	public List<Produit> getSelectedsproduit() {
		return selectedsproduit;
	}

	public void setSelectedsproduit(List<Produit> selectedsproduit) {
		this.selectedsproduit = selectedsproduit;
	}

	public void setTotalquantite(double totalquantite) {
		this.totalquantite = totalquantite;
	}

	public List<Produit> getFilteredProduit() {
		return filteredProduit;
	}

	public void setFilteredProduit(List<Produit> filteredProduit) {
		this.filteredProduit = filteredProduit;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public boolean isTestmoins() {
		return testmoins;
	}

	public void setTestmoins(boolean testmoins) {
		this.testmoins = testmoins;
	}

	public Integer getCodeshop() {
		return codeshop;
	}

	public void setCodeshop(Integer codeshop) {
		this.codeshop = codeshop;
	}

	public String getMarges() {

		return marges;
	}

	public void setMarges(String marges) {
		this.marges = marges;
	}

	public List<Produit> getListproduit() {
		return listproduit;
	}

	public void setListproduit(List<Produit> listproduit) {
		this.listproduit = listproduit;
	}

	public String getTotalquantites() {
		return totalquantites;
	}

	public void setTotalquantites(String totalquantites) {
		this.totalquantites = totalquantites;
	}

	private Famillearticle selectedFamillearticle;
	private List<Famillearticle> filterFamillearticles;

	private Transfert transfert;

	private List<Lignetransert> listLignetransfert;

	public String nouveautransfer() {
		transfert = new Transfert();
		libelle = null;
		codefamille = null;
		date = new Date();
		codes = 0;
	
		listLignetransfert = new ArrayList<Lignetransert>();
		for (int i = 0; i < 100; i++) {
			Lignetransert p = new Lignetransert();
			p.setProduit(null);
			p.setQuantite(0);

			listLignetransfert.add(p);
		}
		user = userService.getCurrentUser();
		return SUCCESS;
	}

	@Transactional
	public String saveTransfert() {
		
		  user = userservice.getCurrentUser();
		transfert = new Transfert();
	
		transfert.setUtilisateur(user);
		transfert.setDate(date);
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		serviceTransfert.save(transfert);
		int i=0;
		for (Lignetransert t : listLignetransfert) {			 
			if (t.getProduit() != null && t.getQuantite() > 0) {
              if(t.getProduit().getQuantitedepot()>0) {
               
				t.setTransfert(transfert);
				t.setDates(dates);
				t.setDate(date);
				serviceLignetransfert.save(t);
				t.getProduit().setQuantitedepot(t.getProduit().getQuantitedepot() - t.getQuantite());
				t.getProduit().setQuantitestock(t.getProduit().getQuantitestock() + t.getQuantite());
				serviceProduit.update(t.getProduit());
              } 
			}
			else  listLignetransfert.set(i, null);
			
			i=i+1;
		}

		return SUCCESS;

	}
	public void updatecodes(AjaxBehaviorEvent event) {
		UIComponent component = event.getComponent();
		  codes = (Integer) component.getAttributes().get("test");
        Lignetransert t=listLignetransfert.get(codes);
         
        	listLignetransfert.set(codes, t);
        
       
	 }
	
	public void updateqte(ValueChangeEvent event) {
		UIComponent component = event.getComponent();
		  codes = (Integer) component.getAttributes().get("test");
        Lignetransert t=listLignetransfert.get(codes);
          t.setQuantite(Double.parseDouble(t.getQuantites()));
        	listLignetransfert.set(codes, t);
        
       
	 }

	public void updatenom111(AjaxBehaviorEvent event) {
 		UIComponent component = event.getComponent();
 	   
//		Lignetransert t = listLignetransfert.get(codes);
//		Produit p = serviceProduit.Findbycodes(codefamille);
//		t.setProduit(p);
//		listLignetransfert.set(codes, t);
//		codefamille = null;
//		codes = codes + 1;
 	  codes = (Integer) component.getAttributes().get("test");
		lignetransfert = listLignetransfert.get(codes);
		Produit p = serviceProduit.Findbycodes(lignetransfert.getLibelle());
		if(p.getFamille().getCode()!=4 && p.getFamille().getCode()!=5 && p.getFamille().getCode()!=9 && p.getQuantitedepot()>0) {
		
		Integer index = verifierarticle2(p, codes);
		
		if (index == codes) {	 
			lignetransfert.setProduit(p);
			  
			listLignetransfert.set(codes, lignetransfert);
		} else {
			lignetransfert.setLibelle(null);
			lignetransfert= listLignetransfert.get(index);
			lignetransfert.setQuantite(lignetransfert.getQuantite() + 1);
			 		
			listLignetransfert.set(index, lignetransfert);
		   }
		totalttc = 0;
		totalquantite = 0;
		totalrecus = 0;
		}
		 
		
		
		else lignetransfert.setLibelle(null);
		for (Lignetransert d : listLignetransfert) {
			 
			totalquantite = totalquantite + d.getQuantite();
		}

		 
		lignetransfert = null;
		 
	}

	public void saveselection2(ActionEvent event) {
		
		 
		Integer index = verifierarticle2(selectedProduit, codes);
	 	DecimalFormat df = new DecimalFormat("0.000");
	if (index == codes) {
		
		lignetransfert = listLignetransfert.get(codes);
		if(lignetransfert.getProduit()==null) {
		 lignetransfert.setLibelle(selectedProduit.getCode());
		
		}
		 if( lignetransfert.getProduit()!=null && index == codes) {
			 lignetransfert=new Lignetransert();
			 lignetransfert.setLibelle(selectedProduit.getCode());
		}
		    Produit p = serviceProduit.Findbycodes( lignetransfert.getLibelle());
		    lignetransfert.setProduit(p);
		    			
		    
		listLignetransfert.set(codes,  lignetransfert);
	}  if (index != codes) {
		 lignetransfert = listLignetransfert.get(index);
		 lignetransfert.setQuantite( lignetransfert.getQuantite() + 1);
		 				
			 
		listLignetransfert.set(index,  lignetransfert);
	}
	selectedProduit = null;
	 
	produit = null;
	 lignetransfert = null;

	}

	public Famillearticle getSelectedFamillearticle() {
		return selectedFamillearticle;
	}

	public void setSelectedFamillearticle(Famillearticle selectedFamillearticle) {
		this.selectedFamillearticle = selectedFamillearticle;
	}

	public List<Famillearticle> getFilterFamillearticles() {
		return filterFamillearticles;
	}

	public void setFilterFamillearticles(List<Famillearticle> filterFamillearticles) {
		this.filterFamillearticles = filterFamillearticles;
	}

	public Transfert getTransfert() {
		return transfert;
	}

	public void setTransfert(Transfert transfert) {
		this.transfert = transfert;
	}

	public List<Lignetransert> getListLignetransfert() {
		return listLignetransfert;
	}

	public void setListLignetransfert(List<Lignetransert> listLignetransfert) {
		this.listLignetransfert = listLignetransfert;
	}

	public ServiceTransfert getServiceTransfert() {
		return serviceTransfert;
	}

	public void setServiceTransfert(ServiceTransfert serviceTransfert) {
		this.serviceTransfert = serviceTransfert;
	}

	public Lignevente getLignevente() {
		return lignevente;
	}

	public void setLignevente(Lignevente lignevente) {
		this.lignevente = lignevente;
	}

	public ServiceTraceshop getServicetrace() {
		return servicetrace;
	}

	public void setServicetrace(ServiceTraceshop servicetrace) {
		this.servicetrace = servicetrace;
	}

	public UserService getUserservice() {
		return userservice;
	}

	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	public String getPrixvente() {
		return prixvente;
	}

	public void setPrixvente(String prixvente) {
		this.prixvente = prixvente;
	}

	public Lignetransert getLignetransfert() {
		return lignetransfert;
	}

	public void setLignetransfert(Lignetransert lignetransfert) {
		this.lignetransfert = lignetransfert;
	}

	public ServiceBonLivraison getServiceBonlivraison() {
		return serviceBonlivraison;
	}

	public void setServiceBonlivraison(ServiceBonLivraison serviceBonlivraison) {
		this.serviceBonlivraison = serviceBonlivraison;
	}

}
