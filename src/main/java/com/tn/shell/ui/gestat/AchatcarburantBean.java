package com.tn.shell.ui.gestat;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.CellEditEvent;

import com.tn.shell.model.banque.Compte;
import com.tn.shell.model.banque.Enumcheque;
import com.tn.shell.model.banque.Reglement;
import com.tn.shell.model.banque.Transaction;
import com.tn.shell.model.banque.TypeTransaction;
import com.tn.shell.model.gestat.Achatcaisse;
import com.tn.shell.model.gestat.Achatcarburant;
import com.tn.shell.model.gestat.Articlecarburant;
import com.tn.shell.model.gestat.Caisse;
import com.tn.shell.model.gestat.Cheque;
import com.tn.shell.model.gestat.Chequereglement;
import com.tn.shell.model.gestat.Depensegestat;
import com.tn.shell.model.gestat.Diverachat;
import com.tn.shell.model.gestat.Factureachatcarburant;
import com.tn.shell.model.gestat.Familledepensegestat;
import com.tn.shell.model.gestat.Lignealimentationcar;
import com.tn.shell.model.gestat.Ligneventecredit;
import com.tn.shell.model.gestat.Poste;
import com.tn.shell.model.gestat.Status;
import com.tn.shell.model.gestat.Tracegestat;
import com.tn.shell.model.gestat.TypePayement;
import com.tn.shell.model.gestat.Utilisateur;
import com.tn.shell.model.shop.Factureachat;
import com.tn.shell.model.shop.Famillearticle;
import com.tn.shell.model.shop.Fournisseur;
import com.tn.shell.model.shop.Produit;
import com.tn.shell.service.banque.ServiceCompte;
import com.tn.shell.service.banque.ServiceTransaction;
import com.tn.shell.service.gestat.ServiceAchatcarburant;
import com.tn.shell.service.gestat.ServiceArticleCarburant;
import com.tn.shell.service.gestat.ServiceCheque;
import com.tn.shell.service.gestat.ServiceChequereglement;
import com.tn.shell.service.gestat.ServiceFactureAchatcar;
import com.tn.shell.service.gestat.ServiceLigneAlimentationcar;
import com.tn.shell.service.gestat.ServiceTracegestat;
import com.tn.shell.service.gestat.UserService;
import com.tn.shell.service.shop.ServiceFournisseur;
import com.tn.shell.service.shop.ServiceProduit;

@ManagedBean(name = "AchatcarburantBean")
@SessionScoped
public class AchatcarburantBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{ServiceProduit}")
	ServiceProduit serviceProduit;
	@ManagedProperty(value = "#{ServiceCompte}")
	ServiceCompte serviceCompte;
	@ManagedProperty(value = "#{ServiceTransaction}")
	ServiceTransaction serviceTransaction;
	@ManagedProperty(value = "#{ServiceFournisseur}")
	ServiceFournisseur serviceFournisseur;
	private String resteMontantCheque;
	@ManagedProperty(value = "#{ServiceAchatcarburant}")
	ServiceAchatcarburant serviceAchat;
	@ManagedProperty(value = "#{ServiceCheque}")
	ServiceCheque serviceCheque;
	@ManagedProperty(value = "#{ServiceTracegestat}")
	ServiceTracegestat servicetrace;
	@ManagedProperty(value = "#{UserService}")
	UserService userservice;

	/*
	 * Utilisateur user=userservice.getCurrentUser(); Tracegestat t=new
	 * Tracegestat(); t.setAction("update avance"+avance.getId()+
	 * " de "+avance.getEmployee().getNom()+" par"+user.getNom()); t.setDate(new
	 * Date()); t.setUtilisateur(user); servicetrace.save(t);
	 */
	@ManagedProperty(value = "#{ServiceFactureAchatcar}")
	ServiceFactureAchatcar serviceFactureAchat;
	@ManagedProperty(value = "#{ServiceLigneAlimentationcar}")
	ServiceLigneAlimentationcar serviceLigneAlimentation;
	@ManagedProperty(value = "#{ServiceArticleCarburant}")
	ServiceArticleCarburant serviceArticleCarburant;

	@ManagedProperty(value = "#{ServiceChequereglement}")
	ServiceChequereglement servicechequereg;

	private List<Fournisseur> listfournisseur;
	private List<String> listfours;
	private String numFacture;
	private Integer numAchat;
	private Date date;
	private List<Factureachatcarburant> listfactureachat;
	private List<Articlecarburant> listproduit;
	private Integer code;
	private Achatcarburant achat;
	private String nomfournissuer;
	private Fournisseur fourniseur;
	private Produit produit;
	private Integer codes;
	private Integer codeproduit;
	private String libelle;
	private Integer qte;
	private String totalttcs;
	private double totalquantite;
	private Factureachatcarburant factureAchat;
	private String banque;
	private String numcheque;
	private TypePayement[] typepayements;
	private Date echeance;
	private TypePayement typepayement;
	private List<Achatcarburant> listAchat;
	private String dates;
	private List<Lignealimentationcar> listeLigne;
	private List<Lignealimentationcar> listeLigne2;
	private Lignealimentationcar seelctedLigne;
	private String totalht;
	private String totaltva;
	private double totalttc;
	private double totalachat;
	private double totalmarge;
	private Articlecarburant articlecarburant;
	private String totalachats;
	private String totalmarges;
	private Status[] status;
	private Status statu;
	private List<Achatcaisse> listAchatESP;
	private List<Depensegestat> listDepense;
	private String matricule;
	private String numavoir;
	private double montantavoir;
	private Date date1;
	private Date date2;
	private String dates1;
	private String dates2;
	private List<Produit> listProduit;
	private String totalCA;
	private String totalProfil;
	private String totaCA;

	private String caAvoir;
	private String tvaAvoir;
	private String caAchat;
	private String tvaAchat;
	private String totalProfilBrut;
	private List<Famillearticle> listFamille;
	private List<String> selectedfamilles;
	private List<Famillearticle> listfamile;
	private List<Ligneventecredit> listelignevente;
	private List<Familledepensegestat> familledepense;
	private Poste[] postes;
	private Poste poste;

	private Fournisseur fournisseur;
	private String nomfour;
	private List<Fournisseur> listefour;
	private List<Cheque> listecheque;
	private double montantcheque;
	private String montantcheques;
	private List<Caisse> listcaisse;
	private Caisse caisse;

	private List<Achatcarburant> listachat;

	public String nouveauachat() {
		totalquantite = 0;
		// DecimalFormat df = new DecimalFormat("#,###.000");
		totalttc = 0;
		totalachat = 0;
		qte = null;
		totalmarge = 0;
		typepayements = TypePayement.values();
		code = null;
		nomfournissuer = null;
		numAchat = null;
		fourniseur = null;
		listfournisseur = new ArrayList<Fournisseur>();
		listfournisseur = serviceFournisseur.getAll();

		listproduit = new ArrayList<Articlecarburant>();
		listproduit = serviceArticleCarburant.getAll();
		// listproduit.remove(serviceArticleCarburant.Findbycode(5));
		if(listproduit!=null)
		for (Articlecarburant p : listproduit) {
			p.setQte(0);
		}

		typepayement = null;
		date = new Date();
		echeance = new Date();
		numFacture = null;
		return SUCCESS;
	}
	private double remise;
	public void updateremise5(AjaxBehaviorEvent event) {
		// produit.setQuantites(qte);
		DecimalFormat df = new DecimalFormat("#,###.000");
		articlecarburant = listproduit.get(4);
		codes = articlecarburant.getId() - 1;
		articlecarburant.setRemise(remise);
		articlecarburant.setMontant(articlecarburant.getMontant() - (articlecarburant.getQte() / 1000 * remise));
		articlecarburant.setMontants(df.format(articlecarburant.getMontant()));
		listproduit.set(codes, articlecarburant);
		totalttc = 0;
		totalquantite = 0;
		totalachat = 0.6;
		totalmarge = 0;
		for (Articlecarburant produit : listproduit) {
			// if(!produit.getNom().equals(null))
			totalttc = totalttc + (produit.getQte() * produit.getVente());
			totalquantite = totalquantite + produit.getQte();
			totalachat = totalachat + (produit.getMontant());

		}
		totalmarge = totalttc - totalachat;
		remise = 0;
	}
	
	public void updateremise3(AjaxBehaviorEvent event) {
		// produit.setQuantites(qte);
		DecimalFormat df = new DecimalFormat("#,###.000");
		articlecarburant = listproduit.get(2);
		codes = articlecarburant.getId() - 1;
		articlecarburant.setRemise(remise);
		articlecarburant.setMontant(articlecarburant.getMontant() - (articlecarburant.getQte() / 1000 * remise));
		articlecarburant.setMontants(df.format(articlecarburant.getMontant()));
		listproduit.set(codes, articlecarburant);
		totalttc = 0;
		totalquantite = 0;
		totalachat = 0.6;
		totalmarge = 0;
		for (Articlecarburant produit : listproduit) {
			// if(!produit.getNom().equals(null))
			totalttc = totalttc + (produit.getQte() * produit.getVente());
			totalquantite = totalquantite + produit.getQte();
			totalachat = totalachat + (produit.getMontant());

		}
		totalmarge = totalttc - totalachat;
		remise = 0;
	}
	
	public void updateremise4(AjaxBehaviorEvent event) {
		// produit.setQuantites(qte);
		DecimalFormat df = new DecimalFormat("#,###.000");
		articlecarburant = listproduit.get(3);
		codes = articlecarburant.getId() - 1;
		articlecarburant.setRemise(remise);
		articlecarburant.setMontant(articlecarburant.getMontant() - (articlecarburant.getQte() / 1000 * remise));
		articlecarburant.setMontants(df.format(articlecarburant.getMontant()));
		listproduit.set(codes, articlecarburant);
		totalttc = 0;
		totalquantite = 0;
		totalachat = 0.6;
		totalmarge = 0;
		for (Articlecarburant produit : listproduit) {
			// if(!produit.getNom().equals(null))
			totalttc = totalttc + (produit.getQte() * produit.getVente());
			totalquantite = totalquantite + produit.getQte();
			totalachat = totalachat + (produit.getMontant());

		}
		totalmarge = totalttc - totalachat;
		remise = 0;
	}
	
 

	
	public void updateremise2(AjaxBehaviorEvent event) {
		// produit.setQuantites(qte);
		DecimalFormat df = new DecimalFormat("#,###.000");
		articlecarburant = listproduit.get(1);
		codes = articlecarburant.getId() - 1;
		articlecarburant.setRemise(remise);
		articlecarburant.setMontant(articlecarburant.getMontant() - (articlecarburant.getQte() / 1000 * remise));
		articlecarburant.setMontants(df.format(articlecarburant.getMontant()));
		listproduit.set(codes, articlecarburant);
		totalttc = 0;
		totalquantite = 0;
		totalachat = 0.6;
		totalmarge = 0;
		for (Articlecarburant produit : listproduit) {
			// if(!produit.getNom().equals(null))
			totalttc = totalttc + (produit.getQte() * produit.getVente());
			totalquantite = totalquantite + produit.getQte();
			totalachat = totalachat + (produit.getMontant());

		}
		totalmarge = totalttc - totalachat;
		remise = 0;
	}
	public void updateremise(AjaxBehaviorEvent event) {
		// produit.setQuantites(qte);
		DecimalFormat df = new DecimalFormat("#,###.000");
		articlecarburant = listproduit.get(0);
		codes = articlecarburant.getId() - 1;
		articlecarburant.setRemise(remise);
		articlecarburant.setMontant(articlecarburant.getMontant() - (articlecarburant.getQte() / 1000 * remise));
		articlecarburant.setMontants(df.format(articlecarburant.getMontant()));
		listproduit.set(codes, articlecarburant);
		totalttc = 0;
		totalquantite = 0;
		totalachat = 0.6;
		totalmarge = 0;
		for (Articlecarburant produit : listproduit) {
			// if(!produit.getNom().equals(null))
			totalttc = totalttc + (produit.getQte() * produit.getVente());
			totalquantite = totalquantite + produit.getQte();
			totalachat = totalachat + (produit.getMontant());

		}
		totalmarge = totalttc - totalachat;
		remise = 0;
	}

	public void verifnumfact(AjaxBehaviorEvent event) {
		Factureachatcarburant f = serviceFactureAchat.getbycode(numFacture);

		if (f != null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "code facture existe deja ", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public String saveAchat() {
		listeLigne = new ArrayList<Lignealimentationcar>();
		totalquantite = 0;
		totalttc = 0;
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		double totatalacht = 0;
		double totalmarge = 0;
		double totalht = 0;
		double totaltva = 0;
		Factureachatcarburant f = serviceFactureAchat.getbycode(numFacture);
		if (f == null) {
			for (Articlecarburant p : listproduit) {
				totalquantite = totalquantite + p.getQte();
				if (p.getQte() != 0) {
					Lignealimentationcar l = new Lignealimentationcar();
					l.setDate(date);
					l.setDates(dates);
					l.setQuantite(p.getQte());
					l.setMontant(p.getQte() * p.getAchat());
					l.setArticlecarburant(p);
					l.setTva(p.getTvaachat());
					l.setPrix(p.getAchat() / (100 + l.getTva()) * 100);
					l.setMantantht(l.getQuantite() * l.getPrix());
					l.setTauxtva(l.getMantantht() * l.getTva() * 0.01);
					listeLigne.add(l);
					p.setQuantite(p.getQuantite() + p.getQte());
					serviceArticleCarburant.update(p);
				}
			}

			for (Lignealimentationcar c : listeLigne) {
				totalht = totalht + c.getMantantht();
				totaltva = totaltva + (c.getTauxtva());
			}
			totalttc = totalht + totaltva;
			DecimalFormat df = new DecimalFormat("#,###.000");

			factureAchat = new Factureachatcarburant();
			factureAchat.setCode(numFacture);
			factureAchat.setDate(date);
			factureAchat.setDates(dates);
			factureAchat.setTotalht(totalht);
			factureAchat.setTotaltva(totaltva);
			factureAchat.setTotalttc(totalht + totaltva);
			// if (!typepayement.equals(TypePayement.cheque) &&
			// !typepayement.equals(TypePayement.espece)) {
			if (typepayement != null) {
				if (typepayement.equals(TypePayement.cheque)) {					 
					factureAchat.setNumerocheck(numcheque);
					factureAchat.setBanque(banque);
					factureAchat.setEcheance(echeance);
					factureAchat.setEcheances(s.format(echeance));
					factureAchat.setDatepayement(date);
					Compte compte=serviceCompte.Findbynom("080810230810000495"); 
					if(compte!=null) {
					SimpleDateFormat f2 = new SimpleDateFormat("dd-MM-yyyy");
					Transaction t=new  Transaction();		
					t.setDescription(fourniseur.getNom());
					t.setCompte(compte);
					t.setMontant(new  BigDecimal(montantcheque));
					t.setReglement(Reglement.Cheque);
					t.setTypetransaction(TypeTransaction.Debit);
					t.setDate(new Date());
					t.setReference(numcheque);
					t.setDates(f2.format(new Date()));	 
					t.setEtatcheque(Enumcheque.EnCirculation);	  
			     	serviceTransaction.save(t);
					}
				}
				if (typepayement.equals(TypePayement.cheque_avoir)) {				 
					factureAchat.setMontantavoir(montantavoir);
					factureAchat.setNumavoir(numavoir);					 
					factureAchat.setNumerocheck(numcheque);
					factureAchat.setBanque(banque);
					factureAchat.setEcheance(echeance);
					factureAchat.setEcheances(s.format(echeance));
					factureAchat.setDatepayement(date);
					Compte compte=serviceCompte.Findbynom("080810230810000495"); 
					if(compte!=null) {
					SimpleDateFormat f2 = new SimpleDateFormat("dd-MM-yyyy");
					Transaction t=new  Transaction();		
					t.setDescription(fourniseur.getNom());
					t.setCompte(compte);
					t.setMontant(new  BigDecimal(resteMontantCheque));
					t.setReglement(Reglement.Cheque);
					t.setTypetransaction(TypeTransaction.Debit);
					t.setDate(new Date());
					t.setReference(numcheque);
					t.setDates(f2.format(new Date()));	 
					t.setEtatcheque(Enumcheque.Encours);	  
			     	serviceTransaction.save(t);
					}
				}

				factureAchat.setTypepayement(typepayement);
				factureAchat.setStatus(Status.Payee);

			} else {
				factureAchat.setEcheance(null);
				factureAchat.setStatus(Status.NonPayee);
			}
			serviceFactureAchat.save(factureAchat);
			achat = new Achatcarburant();
			achat.setDate(date);
			achat.setFournisseur(fourniseur);
			achat.setMontant(totalttc);
			achat.setFactureachat(factureAchat);
			serviceAchat.save(achat);
			
			
			
			for (Lignealimentationcar l : listeLigne) {
				l.setAchat(achat);
				serviceLigneAlimentation.save(l);
			}
			totalttcs = df.format(totalttc);
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "code facture existe deja ", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		Utilisateur user = userservice.getCurrentUser();
		Tracegestat t = new Tracegestat();
		t.setAction("ajout nouveau facture achat" + numFacture + " par" + user.getNom());
		t.setDate(new Date());
		t.setUtilisateur(user);
		servicetrace.save(t);
		return SUCCESS;
	}

	public void getAllFournisseur(AjaxBehaviorEvent event) {
		listfournisseur = new ArrayList<Fournisseur>();
		listfournisseur = serviceFournisseur.getAll();
	}

	public void getFournisseurbyname(AjaxBehaviorEvent event) {
		fourniseur = serviceFournisseur.getbyname(nomfournissuer);
		listfactureachat = new ArrayList<Factureachatcarburant>();
		listfactureachat = serviceFactureAchat.getfacturebyStatus(Status.NonPayee);

	}

	public void getFournisseurbyid(AjaxBehaviorEvent event) {
		fourniseur = serviceFournisseur.getbyid(code);
		if (fourniseur != null) {
			nomfournissuer = fourniseur.getNom();
			// listfactureachat = new ArrayList<Factureachatcarburant>();
			// listfactureachat = serviceFactureAchat.getfacturebyStatus(Status.NonPayee);
		}
	}

	public void getfactureachatbynom(AjaxBehaviorEvent event) {
		factureAchat = serviceFactureAchat.getbycode(numFacture);
	}

	public void getFournisseurbyname2(AjaxBehaviorEvent event) {
		fourniseur = serviceFournisseur.getbyname(nomfournissuer);
		listAchat = new ArrayList<Achatcarburant>();
		listAchat = serviceAchat.getArticlebyfournisseur(fourniseur);
		numAchat = fourniseur.getCode();
	}

	public String getAllfournisseur() {
		listfournisseur = new ArrayList<Fournisseur>();
		listfournisseur = serviceFournisseur.getAll();
		return SUCCESS;
	}

	public void getFacturebyFour(AjaxBehaviorEvent event) {
		Fournisseur f = serviceFournisseur.getbyname(listfours.get(0));
		listAchat = new ArrayList<Achatcarburant>();
		listAchat = serviceAchat.getArticlebyfournisseur(f);
	}

	public void updateCode(AjaxBehaviorEvent event) {
		// produit = serviceProduit.Findbycode(codeproduit);
		listproduit.set(codes, articlecarburant);
		codeproduit = null;
	}

	public void updatenom(AjaxBehaviorEvent event) {
		// produit = serviceProduit.Findbydes(libelle);
		// produit.setMontant(produit.getQuantites()*produit.getAchat());
		listproduit.set(codes, articlecarburant);
		libelle = null;
	}

	public void updatemontant(AjaxBehaviorEvent event) {
		// produit.setQuantites(qte);
		DecimalFormat df = new DecimalFormat("#,###.000");
		articlecarburant = listproduit.get(0);
		codes = articlecarburant.getId() - 1;
		articlecarburant.setQte(qte);
		articlecarburant.setMontant(articlecarburant.getAchat() * articlecarburant.getQte());
		articlecarburant.setMontants(df.format(articlecarburant.getMontant()));
		listproduit.set(codes, articlecarburant);
		totalttc = 0;
		totalquantite = 0;
		totalachat = 0.6;
		totalmarge = 0;
		for (Articlecarburant produit : listproduit) {
			// if(!produit.getNom().equals(null))
			totalttc = totalttc + (produit.getQte() * produit.getVente());
			totalquantite = totalquantite + produit.getQte();
			totalachat = totalachat + (produit.getQte() * produit.getAchat());

		}
		totalmarge = totalttc - totalachat;
		qte = 0;
	}

	public void updatemontant2(AjaxBehaviorEvent event) {
		// produit.setQuantites(qte);
		DecimalFormat df = new DecimalFormat("#,###.000");
		articlecarburant = listproduit.get(1);
		codes = articlecarburant.getId() - 1;
		articlecarburant.setQte(qte);
		articlecarburant.setMontant(articlecarburant.getAchat() * articlecarburant.getQte());
		articlecarburant.setMontants(df.format(articlecarburant.getMontant()));
		listproduit.set(codes, articlecarburant);
		totalttc = 0;
		totalquantite = 0;
		totalachat = 0.6;
		totalmarge = 0;
		for (Articlecarburant produit : listproduit) {
			// if(!produit.getNom().equals(null))
			totalttc = totalttc + (produit.getQte() * produit.getVente());
			totalquantite = totalquantite + produit.getQte();
			totalachat = totalachat + (produit.getQte() * produit.getAchat());

		}
		totalmarge = totalttc - totalachat;
		qte = 0;
	}

	public void updatemontant3(AjaxBehaviorEvent event) {
		// produit.setQuantites(qte);
		DecimalFormat df = new DecimalFormat("#,###.000");
		articlecarburant = listproduit.get(2);
		codes = articlecarburant.getId() - 1;
		articlecarburant.setQte(qte);
		articlecarburant.setMontant(articlecarburant.getAchat() * articlecarburant.getQte());
		articlecarburant.setMontants(df.format(articlecarburant.getMontant()));
		listproduit.set(codes, articlecarburant);
		totalttc = 0;
		totalquantite = 0;
		totalachat = 0.6;
		totalmarge = 0;
		for (Articlecarburant produit : listproduit) {
			// if(!produit.getNom().equals(null))
			totalttc = totalttc + (produit.getQte() * produit.getVente());
			totalquantite = totalquantite + produit.getQte();
			totalachat = totalachat + (produit.getQte() * produit.getAchat());

		}
		totalmarge = totalttc - totalachat;
		qte = 0;
	}

	public void updatemontant4(AjaxBehaviorEvent event) {
		// produit.setQuantites(qte);
		DecimalFormat df = new DecimalFormat("#,###.000");
		articlecarburant = listproduit.get(3);
		codes = articlecarburant.getId() - 1;
		articlecarburant.setQte(qte);
		articlecarburant.setMontant(articlecarburant.getAchat() * articlecarburant.getQte());
		articlecarburant.setMontants(df.format(articlecarburant.getMontant()));
		listproduit.set(codes, articlecarburant);
		totalttc = 0;
		totalquantite = 0;
		totalachat = 0.6;
		totalmarge = 0;
		for (Articlecarburant produit : listproduit) {
			// if(!produit.getNom().equals(null))
			totalttc = totalttc + (produit.getQte() * produit.getVente());
			totalquantite = totalquantite + produit.getQte();
			totalachat = totalachat + (produit.getQte() * produit.getAchat());

		}
		totalmarge = totalttc - totalachat;
		qte = 0;
	}

	public void updatemontant5(AjaxBehaviorEvent event) {
		// produit.setQuantites(qte);
		DecimalFormat df = new DecimalFormat("#,###.000");
		articlecarburant = listproduit.get(5);
		codes = articlecarburant.getId() - 1;
		articlecarburant.setQte(qte);
		articlecarburant.setMontant(articlecarburant.getAchat() * articlecarburant.getQte());
		articlecarburant.setMontants(df.format(articlecarburant.getMontant()));
		listproduit.set(codes, articlecarburant);
		totalttc = 0;
		totalquantite = 0;
		totalachat = 0.6;
		totalmarge = 0;
		for (Articlecarburant produit : listproduit) {
			// if(!produit.getNom().equals(null))
			totalttc = totalttc + (produit.getQte() * produit.getVente());
			totalquantite = totalquantite + produit.getQte();
			totalachat = totalachat + (produit.getQte() * produit.getAchat());

		}
		totalmarge = totalttc - totalachat;
		qte = 0;
	}

	public void getetatFactureAchat2(AjaxBehaviorEvent event) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dates1 = dateFormat.format(date1);
		dates2 = dateFormat.format(date2);
		double total = 0;
		double total2 = 0;
		double total3 = 0;
		listachat = new ArrayList<Achatcarburant>();
		List<Achatcarburant> l = new ArrayList<Achatcarburant>();
		List<Achatcarburant> l2 = new ArrayList<Achatcarburant>();
		l = serviceAchat.getAll();
		try {
			for (Achatcarburant f : l) {
				if (date1 != null) {
					Date d1 = dateFormat.parse(dateFormat.format(date1));

					if (d1.compareTo(dateFormat.parse(dateFormat.format(f.getDate()))) <= 0) {
						l2.add(f);
					}
				}

				if (date1 == null)
					l2.add(f);
			}
			for (Achatcarburant f : l2) {
				if (date2 != null) {
					Date d2 = dateFormat.parse(dateFormat.format(date2));

					if (d2.compareTo(dateFormat.parse(dateFormat.format(f.getDate()))) >= 0) {
						listachat.add(f);

					}
				}
				if (date2 == null)
					listachat.add(f);
			}

		} catch (ParseException e1) {
			// TODO Autof-generated catch block
			e1.printStackTrace();
		}
		DecimalFormat df = new DecimalFormat("#,###.000");
		totaltva = df.format(0);
		totaCA = df.format(0);
		totalProfil = df.format(0);
		total = 0;
		total2 = 0;
		total3 = 0;
		for (Achatcarburant f : listachat) {

			total = total + f.getFactureachat().getTotalht();
			total2 = total2 + f.getFactureachat().getTotaltva();
			total3 = total3 + f.getFactureachat().getTotalttc();
		}
		totaltva = df.format(total2);
		totaCA = df.format(total);
		totalProfil = df.format(total3);
	}

	public void onCellEdit(CellEditEvent event) {
		articlecarburant = listproduit.get(event.getRowIndex());
		codes = event.getRowIndex();
		// listproduit.set(codes, produit);
	}

	private void updatetotal() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalttc = 0;// df.format(0)
		totalquantite = 0;
		for (Articlecarburant p : listproduit) {
			if (p.getQte() != 0) {
				// totalttc = df.format(Double.parseDouble(totalttc) + p.getMontant());
				totalquantite = totalquantite + p.getQte();
			}
		}
	}

	public void getAchatbynum(AjaxBehaviorEvent event) {
		Achatcarburant achat = serviceAchat.getAchatbyid(numAchat);
		nomfournissuer = achat.getFournisseur().getNom();
		numFacture = achat.getFactureachat().getCode();
		factureAchat = achat.getFactureachat();
		// totalttc = factureAchat.getTotalttcs();
	}

	public String validerreglement() {
		factureAchat.setTypepayement(typepayement);
		factureAchat.setNumerocheck(numcheque);
		factureAchat.setBanque(banque);
		factureAchat.setEcheance(echeance);
		factureAchat.setStatus(Status.Payee);
		factureAchat.setDatepayement(new Date());
		serviceFactureAchat.update(factureAchat);
		Cheque c = new Cheque();
		c.setNumero(numcheque);
		c.setBanque(banque);
		c.setMontant(totalttc);
		// c.setPoste(Poste.valueOf(Poste.Poste1));
		c.setDate(new Date());
		// c.setCodeOuvrier1(codeOuvrier1); charger le curent user
		serviceCheque.save(c);
		Utilisateur user = userservice.getCurrentUser();
		Tracegestat t = new Tracegestat();
		t.setAction("regelemnt da la facture " + factureAchat.getCode() + " par" + user.getNom());
		t.setDate(new Date());
		t.setUtilisateur(user);
		servicetrace.save(t);
		return SUCCESS;
	}

	public String reglementFacture() {
		listfournisseur = new ArrayList<Fournisseur>();
		listfournisseur = serviceFournisseur.getAll();
		return SUCCESS;
	}

	public String etatfactureAchat() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		date1 = new Date();
		date2 = new Date();
		dates1 = dateFormat.format(date1);
		dates2 = dateFormat.format(date2);
		listachat = new ArrayList<Achatcarburant>();
		return SUCCESS;
	}

	public String etatFactureachat() {
		listfournisseur = new ArrayList<Fournisseur>();
		listfournisseur = serviceFournisseur.getAll();
		status = Status.values();
		nomfournissuer = null;
		statu = null;
		date = new Date();
		listAchat = new ArrayList<Achatcarburant>();
		return SUCCESS;
	}

	public void getEtatFacturebystatus(AjaxBehaviorEvent event) {
		listAchat = new ArrayList<Achatcarburant>();
		if (statu.equals(Status.Les2))
			listAchat = serviceAchat.getAchatbyfacture(fourniseur);
		else
			listAchat = serviceAchat.getAchatbystatusfacture(statu, fourniseur);
	}

	public String historiqueCarburant() {
		listeLigne = new ArrayList<Lignealimentationcar>();
		listeLigne = serviceLigneAlimentation.getAll();
		listAchat = new ArrayList<Achatcarburant>();
		listfactureachat = new ArrayList<Factureachatcarburant>();
		return SUCCESS;
	}

	public String historiqueParfournisseur() {
		listfournisseur = new ArrayList<Fournisseur>();
		listfournisseur = serviceFournisseur.getAll();
		listAchat = new ArrayList<Achatcarburant>();
		fourniseur = null;
		return SUCCESS;
	}

	public void getFacturebyFournisseur(AjaxBehaviorEvent event) {
		listAchat = new ArrayList<Achatcarburant>();
		listAchat = serviceAchat.getAchatbyfacture(fourniseur);
	}

	public void getligneFacturebyAchat(AjaxBehaviorEvent event) {
		totalquantite = 0;
		totalttcs = null;
		double m = 0;
		
		listeLigne2 = new ArrayList<Lignealimentationcar>();
		listeLigne2 = serviceLigneAlimentation.getLignebyachat(achat);		
		for (Lignealimentationcar l : listeLigne2) {
			totalquantite = totalquantite + l.getQuantite();
			l.setMontant(l.getQuantite() * l.getPrix()+(l.getQuantite() * l.getPrix()*l.getTva()/100));
		}
			
		totalttcs = String.format("%3f", achat.getFactureachat().getTotalttc());
	}

	public void getFacturebyAchat(AjaxBehaviorEvent event) {
		listeLigne2 = new ArrayList<Lignealimentationcar>();
		listeLigne2 = serviceLigneAlimentation.getLignebyproduit(seelctedLigne);
	}
	/*
	 * getter and setter
	 * 
	 */

	public Integer getCodeproduit() {
		return codeproduit;
	}

	public List<Lignealimentationcar> getListeLigne2() {
		return listeLigne2;
	}

	public void setListeLigne2(List<Lignealimentationcar> listeLigne2) {
		this.listeLigne2 = listeLigne2;
	}

	public Lignealimentationcar getSeelctedLigne() {
		return seelctedLigne;
	}

	public void setSeelctedLigne(Lignealimentationcar seelctedLigne) {
		this.seelctedLigne = seelctedLigne;
	}

	public double getTotalquantite() {
		return totalquantite;
	}

	public void setTotalquantite(double totalquantite) {
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

	public ServiceAchatcarburant getServiceAchat() {
		return serviceAchat;
	}

	public void setServiceAchat(ServiceAchatcarburant serviceAchat) {
		this.serviceAchat = serviceAchat;
	}

	public List<Fournisseur> getListfournisseur() {
		return listfournisseur;
	}

	public void setListfournisseur(List<Fournisseur> listfournisseur) {
		this.listfournisseur = listfournisseur;
	}

	public String getNumFacture() {
		return numFacture;
	}

	public void setNumFacture(String numFacture) {
		this.numFacture = numFacture;
	}

	public Integer getNumAchat() {
		return numAchat;
	}

	public void setNumAchat(Integer numAchat) {
		this.numAchat = numAchat;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Achatcarburant getAchat() {
		return achat;
	}

	public void setAchat(Achatcarburant achat) {
		this.achat = achat;
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
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalttcs = df.format(totalttc);
		return totalttcs;
	}

	public void setTotalttcs(String totalttcs) {
		this.totalttcs = totalttcs;
	}

	public List<Articlecarburant> getListproduit() {
		return listproduit;
	}

	public void setListproduit(List<Articlecarburant> listproduit) {
		this.listproduit = listproduit;
	}

	public double getTotalachat() {
		return totalachat;
	}

	public void setTotalachat(double totalachat) {
		this.totalachat = totalachat;
	}

	public double getTotalmarge() {
		return totalmarge;
	}

	public void setTotalmarge(double totalmarge) {
		this.totalmarge = totalmarge;
	}

	public String getTotalachats() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalachats = df.format(totalachat);
		return totalachats;
	}

	public void setTotalachats(String totalachats) {
		this.totalachats = totalachats;
	}

	public String getTotalmarges() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalmarges = df.format(totalmarge);
		return totalmarges;
	}

	public void setTotalmarges(String totalmarges) {
		this.totalmarges = totalmarges;
	}

	public ServiceFactureAchatcar getServiceFactureAchat() {
		return serviceFactureAchat;
	}

	public void setServiceFactureAchat(ServiceFactureAchatcar serviceFactureAchat) {
		this.serviceFactureAchat = serviceFactureAchat;
	}

	public Factureachatcarburant getFactureAchat() {
		return factureAchat;
	}

	public void setFactureAchat(Factureachatcarburant factureAchat) {
		this.factureAchat = factureAchat;
	}

	public ServiceLigneAlimentationcar getServiceLigneAlimentation() {
		return serviceLigneAlimentation;
	}

	public void setServiceLigneAlimentation(ServiceLigneAlimentationcar serviceLigneAlimentation) {
		this.serviceLigneAlimentation = serviceLigneAlimentation;
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

	public TypePayement getTypepayement() {
		return typepayement;
	}

	public void setTypepayement(TypePayement typepayement) {
		this.typepayement = typepayement;
	}

	public List<Achatcarburant> getListAchat() {
		return listAchat;
	}

	public void setListAchat(List<Achatcarburant> listAchat) {
		this.listAchat = listAchat;
	}

	public String getDates() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
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

	public List<Lignealimentationcar> getListeLigne() {
		return listeLigne;
	}

	public void setListeLigne(List<Lignealimentationcar> listeLigne) {
		this.listeLigne = listeLigne;
	}

	public double getTotalttc() {
		return totalttc;
	}

	public void setTotalttc(double totalttc) {
		this.totalttc = totalttc;
	}

	public Integer getQte() {
		return qte;
	}

	public void setQte(Integer qte) {
		this.qte = qte;
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

	public List<String> getListfours() {
		return listfours;
	}

	public void setListfours(List<String> listfours) {
		this.listfours = listfours;
	}

	public List<Articlecarburant> getlistproduit() {
		return listproduit;
	}

	public void setlistproduit(List<Articlecarburant> listproduit) {
		this.listproduit = listproduit;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public ServiceArticleCarburant getServiceArticleCarburant() {
		return serviceArticleCarburant;
	}

	public void setServiceArticleCarburant(ServiceArticleCarburant serviceArticleCarburant) {
		this.serviceArticleCarburant = serviceArticleCarburant;
	}

	public Articlecarburant getArticlecarburant() {
		return articlecarburant;
	}

	public void setArticlecarburant(Articlecarburant articlecarburant) {
		this.articlecarburant = articlecarburant;
	}

	public List<Factureachatcarburant> getListfactureachat() {
		return listfactureachat;
	}

	public void setListfactureachat(List<Factureachatcarburant> listfactureachat) {
		this.listfactureachat = listfactureachat;
	}

	public ServiceCheque getServiceCheque() {
		return serviceCheque;
	}

	public void setServiceCheque(ServiceCheque serviceCheque) {
		this.serviceCheque = serviceCheque;
	}

	public List<Achatcaisse> getListAchatESP() {
		return listAchatESP;
	}

	public void setListAchatESP(List<Achatcaisse> listAchatESP) {
		this.listAchatESP = listAchatESP;
	}

	public List<Depensegestat> getListDepense() {
		return listDepense;
	}

	public void setListDepense(List<Depensegestat> listDepense) {
		this.listDepense = listDepense;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
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

	public List<Produit> getListProduit() {
		return listProduit;
	}

	public void setListProduit(List<Produit> listProduit) {
		this.listProduit = listProduit;
	}

	public String getTotalCA() {
		return totalCA;
	}

	public void setTotalCA(String totalCA) {
		this.totalCA = totalCA;
	}

	public String getTotalProfil() {
		return totalProfil;
	}

	public void setTotalProfil(String totalProfil) {
		this.totalProfil = totalProfil;
	}

	public String getTotaCA() {
		return totaCA;
	}

	public void setTotaCA(String totaCA) {
		this.totaCA = totaCA;
	}

	public String getCaAvoir() {
		return caAvoir;
	}

	public void setCaAvoir(String caAvoir) {
		this.caAvoir = caAvoir;
	}

	public String getTvaAvoir() {
		return tvaAvoir;
	}

	public void setTvaAvoir(String tvaAvoir) {
		this.tvaAvoir = tvaAvoir;
	}

	public String getCaAchat() {
		return caAchat;
	}

	public void setCaAchat(String caAchat) {
		this.caAchat = caAchat;
	}

	public String getTvaAchat() {
		return tvaAchat;
	}

	public void setTvaAchat(String tvaAchat) {
		this.tvaAchat = tvaAchat;
	}

	public String getTotalProfilBrut() {
		return totalProfilBrut;
	}

	public void setTotalProfilBrut(String totalProfilBrut) {
		this.totalProfilBrut = totalProfilBrut;
	}

	public List<Famillearticle> getListFamille() {
		return listFamille;
	}

	public void setListFamille(List<Famillearticle> listFamille) {
		this.listFamille = listFamille;
	}

	public List<String> getSelectedfamilles() {
		return selectedfamilles;
	}

	public void setSelectedfamilles(List<String> selectedfamilles) {
		this.selectedfamilles = selectedfamilles;
	}

	public List<Famillearticle> getListfamile() {
		return listfamile;
	}

	public void setListfamile(List<Famillearticle> listfamile) {
		this.listfamile = listfamile;
	}

	public List<Ligneventecredit> getListelignevente() {
		return listelignevente;
	}

	public void setListelignevente(List<Ligneventecredit> listelignevente) {
		this.listelignevente = listelignevente;
	}

	public List<Familledepensegestat> getFamilledepense() {
		return familledepense;
	}

	public void setFamilledepense(List<Familledepensegestat> familledepense) {
		this.familledepense = familledepense;
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

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public String getNomfour() {
		return nomfour;
	}

	public void setNomfour(String nomfour) {
		this.nomfour = nomfour;
	}

	public List<Fournisseur> getListefour() {
		return listefour;
	}

	public void setListefour(List<Fournisseur> listefour) {
		this.listefour = listefour;
	}

	public List<Cheque> getListecheque() {
		return listecheque;
	}

	public void setListecheque(List<Cheque> listecheque) {
		this.listecheque = listecheque;
	}

 

	public void setMontantcheque(double montantcheque) {
		this.montantcheque = montantcheque;
	}

	public String getMontantcheques() {
		return montantcheques;
	}

	public void setMontantcheques(String montantcheques) {
		this.montantcheques = montantcheques;
	}

	public List<Caisse> getListcaisse() {
		return listcaisse;
	}

	public void setListcaisse(List<Caisse> listcaisse) {
		this.listcaisse = listcaisse;
	}

	public Caisse getCaisse() {
		return caisse;
	}

	public void setCaisse(Caisse caisse) {
		this.caisse = caisse;
	}

	public List<Achatcarburant> getListachat() {
		return listachat;
	}

	public void setListachat(List<Achatcarburant> listachat) {
		this.listachat = listachat;
	}

	public ServiceChequereglement getServicechequereg() {
		return servicechequereg;
	}

	public void setServicechequereg(ServiceChequereglement servicechequereg) {
		this.servicechequereg = servicechequereg;
	}

	public ServiceTracegestat getServicetrace() {
		return servicetrace;
	}

	public void setServicetrace(ServiceTracegestat servicetrace) {
		this.servicetrace = servicetrace;
	}

	public UserService getUserservice() {
		return userservice;
	}

	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	public String getNumavoir() {
		return numavoir;
	}

	public void setNumavoir(String numavoir) {
		this.numavoir = numavoir;
	}

	public double getMontantavoir() {
		return montantavoir;
	}

	public void setMontantavoir(double montantavoir) {
		this.montantavoir = montantavoir;
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

	public double getRemise() {
		return remise;
	}

	public void setRemise(double remise) {
		this.remise = remise;
	}
	public double getMontantcheque() {
		return montantcheque;
	}
	public void getmontantcheque(AjaxBehaviorEvent event) {
		double resteMontantCheques = totalachat - montantavoir;
		DecimalFormat df = new DecimalFormat("0.000");
		resteMontantCheque = df.format(resteMontantCheques);
	}

	public String getResteMontantCheque() {
		return resteMontantCheque;
	}

	public void setResteMontantCheque(String resteMontantCheque) {
		this.resteMontantCheque = resteMontantCheque;
	}

	 

}
