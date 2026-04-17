package com.tn.shell.ui.shop;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
import javax.persistence.Transient;

import org.primefaces.event.CellEditEvent;

import com.tn.shell.model.banque.Compte;
import com.tn.shell.model.banque.Enumcheque;
import com.tn.shell.model.banque.Reglement;
import com.tn.shell.model.banque.Transaction;
import com.tn.shell.model.banque.TypeTransaction;
import com.tn.shell.model.gestat.Chequereglement;
import com.tn.shell.model.gestat.Utilisateur;
import com.tn.shell.model.shop.*;
import com.tn.shell.service.banque.ServiceCompte;
import com.tn.shell.service.banque.ServiceTransaction;
import com.tn.shell.service.gestat.ServiceChequereglement;
import com.tn.shell.service.gestat.ServiceTracegestat;
import com.tn.shell.service.gestat.UserService;
import com.tn.shell.service.shop.*;

 
 

@ManagedBean(name = "AchatshopBean")
@SessionScoped
public class AchatshopBean {
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
	@ManagedProperty(value = "#{ServiceFamilleaticle}")
	ServiceFamilleaticle serviceFamilleaticle;
	@ManagedProperty(value = "#{ServiceAchat}")
	ServiceAchat serviceAchat;
	@ManagedProperty(value = "#{ServiceParamettre}")
	ServiceParamettre serviceParamettre;
	@ManagedProperty(value = "#{ServiceTva}")
	ServiceTva serviceTva;
	@ManagedProperty(value = "#{ServiceFactureAchat}")
	ServiceFactureAchat serviceFactureAchat;
	@ManagedProperty(value = "#{ServiceLigneAlimentation}")
	ServiceLigneAlimentation serviceLigneAlimentation;
	@ManagedProperty(value = "#{ServiceTraceshop}")
    ServiceTraceshop servicetrace;
	@ManagedProperty(value = "#{UserService}")
	UserService userservice;
	@ManagedProperty(value = "#{ServiceChequereglement}")
	ServiceChequereglement servicechequereg;
	private List<Fournisseur> listfournisseur;
	private List<String> listProduits;
	private List<String> listfours;
	private String numFacture;
	private Integer numAchat;
	private Date date;
	private Produit selectedProduit;
	private List<Produit> listProduit;
	private List<Produit> listproduit;
	private Integer code;
	private Achat achat;
	private String nomfournissuer;
	private Fournisseur fourniseur;
	private Produit produit;
	private Integer codes;
	private Integer codeproduit;
	private String libelle;
	private Integer qte;
	private String totalttcs;
	private double totalquantite;
	private Factureachat factureAchat;
	private String banque;
	private Lignealimentation lignealimentation;
	private String numcheque;
	private TypePayement[] typepayements;
	private Date echeance;
	private TypePayement typepayement;
	private List<Achat> listAchat;
	private String dates;
	private List<Lignealimentation> listeLigne;
	private String totalht;
	private String totaltva;
	private double montantcheque;
	private double totalttc;
	private Status[] status;
	private Status statu;
	private List<Produit> filteredProduit;
	private List<Produit> filterproduits;
	private 	List<Factureachat> listFactureAchat;
	@PostConstruct
	public void init() {
		listProduit = new ArrayList<Produit>();
		listProduit = serviceProduit.getAll();

	}
	
	 
	public String nouveauachat() {
		totalquantite = 0;
		//DecimalFormat df = new DecimalFormat("0.000");
		totalttc = 0;
		typepayements = TypePayement.values();
		code = null;typepayement=TypePayement.NonPayer;
		nomfournissuer = null;
		numAchat = null;
		fourniseur = null;
		listfournisseur = new ArrayList<Fournisseur>();
		listfournisseur = serviceFournisseur.getAll();
		 
		listeLigne = new ArrayList<Lignealimentation>();
		for (int i = 0; i < 100; i++) {
			Lignealimentation p = new Lignealimentation();
			p.setLibelle("");
			p.setQuantite(0);			 
			listeLigne .add(p);
		}
		date = new Date();
		codes = 0;
		echeance = new Date();
		numFacture = null;
		libelle = null;
		codeproduit = null;
		return SUCCESS;
	}

	public String nouveauavoir() {
		return nouveauachat();
	}
	public void getCodefocus(AjaxBehaviorEvent event) {
		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");

	}
	
	public void saveselection(ActionEvent event) {
		  
			Integer index = verifierarticle(selectedProduit, codes);
			 	DecimalFormat df = new DecimalFormat("0.000");
			if (index == codes) {
				lignealimentation = listeLigne.get(codes);
				if(lignealimentation.getProduit()==null) {
					lignealimentation.setLibelle(selectedProduit.getCode());
				
				}
				 if(lignealimentation.getProduit()!=null && index == codes) {
					 lignealimentation=new Lignealimentation();
					 lignealimentation.setLibelle(selectedProduit.getCode());
				}
				    Produit p = serviceProduit.Findbycodes(lignealimentation.getLibelle());
				    lignealimentation.setProduit(p);	
				    lignealimentation.setQuantite(1);
				    lignealimentation .setMontant(
							lignealimentation .getQuantite()* lignealimentation .getProduit().getAchat()+ (
									(lignealimentation .getQuantite() * lignealimentation .getProduit().getAchat()*lignealimentation .getProduit().getTva() * 0.01))
							);		 
					lignealimentation.setMontants(df.format(lignealimentation.getMontant()));		 
					
				 listeLigne.set(codes, lignealimentation);
			}  if (index != codes) {
				lignealimentation = listeLigne.get(index);
				lignealimentation.setQuantite(lignealimentation.getQuantite() + 1);
				 				
				lignealimentation .setMontant(
						lignealimentation .getQuantite()* lignealimentation .getProduit().getAchat()+ (
								(lignealimentation .getQuantite() * lignealimentation .getProduit().getAchat()*lignealimentation .getProduit().getTva() * 0.01))
						);		 
				lignealimentation.setMontants(df.format(lignealimentation.getMontant()));		 
				
				listeLigne.set(index,lignealimentation);
			}
			selectedProduit = null;
			 
		 
			lignealimentation= null;

		}
			 
			 
		 
	public void getFournisseurbyid(AjaxBehaviorEvent event) {
		fourniseur = serviceFournisseur.getbyid(code);
		if(fourniseur!=null) {
			nomfournissuer=fourniseur.getNom();
		//listfactureachat = new ArrayList<Factureachatcarburant>();
		//listfactureachat = serviceFactureAchat.getfacturebyStatus(Status.NonPayee);
		}
		numAchat = serviceAchat.getmaxcode() + 1;
	}
	
	public String articleEnAlerteDeStock() {
		listproduit=new ArrayList<Produit>();
		listproduit=serviceProduit.getAllQtenegatif();
		return SUCCESS;
	}
	public void handleChange(ValueChangeEvent event) {

		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
		produit = listproduit.get(codes);

		DecimalFormat df = new DecimalFormat("0.000");
		Integer index = verifierarticle(produit, codes);

		if (index == codes) {
			produit.setQuantites(1);
			produit.setMontant(produit.getQuantites() * produit.getVente());
			produit.setMontants(df.format(produit.getMontant()));
			listproduit.set(codes, produit);
		} else {
			listproduit.get(index).setQuantites(listproduit.get(index).getQuantites() + 1);
			listproduit.get(index)
					.setMontant(listproduit.get(index).getQuantites() * listproduit.get(index).getVente());
			listproduit.get(index).setMontants(df.format(listproduit.get(index).getMontant()));
			listproduit.set(index, listproduit.get(index));

		}

	}
	public void updateCode2(AjaxBehaviorEvent event) {
//		UIComponent component = event.getComponent();
//		codes = (Integer) component.getAttributes().get("test");
//		System.out.println("\n\n codes" + codes + "\n\n");
//
//		Produit p = serviceProduit.Findbycodes(libelle);
//		DecimalFormat df = new DecimalFormat("0.000");
//		Integer index = verifierarticle(p, codes);
//
//		if (index == codes) {
//			System.out.println("\n\n not contains" + "\n\n");
//			p.setQuantites(1);
//			p.setMontant(p.getQuantites() * p.getAchat());
//			// p.setMontants(df.format(p.getMontant()));
//			listproduit.set(codes, p);
//		} else {
//			p.setQuantites(listproduit.get(index).getQuantites() + 1);
//			p.setMontant(p.getQuantites() * p.getAchat());
//			p.setMontants(df.format(p.getMontant()));
//			listproduit.set(index, p);
//		}
//		totalttc = 0;
//		totalquantite = 0;
//		 
//		for (Produit d : listproduit) {
//			totalttc = totalttc + d.getMontant();
//			totalquantite = totalquantite + d.getQuantites();
//		}
//
//		totalttcs = df.format(totalttc);
//		libelle = null; 
		
		UIComponent component = event.getComponent();
		DecimalFormat df = new DecimalFormat("0.000");
		codes = (Integer) component.getAttributes().get("test");
		lignealimentation = listeLigne.get(codes);
		Produit p = serviceProduit.Findbycodes(lignealimentation.getLibelle());
		 
		
		Integer index = verifierarticle(p, codes);
		if (index == codes) {	 
			lignealimentation.setProduit(p);
			lignealimentation.setQuantite(1);	
			lignealimentation.setTotalttc(lignealimentation.getQuantite() * p.getVente());
			 listeLigne.set(codes, lignealimentation);
		} else {
			lignealimentation.setLibelle(null);
			lignealimentation = listeLigne.get(index);
			lignealimentation.setQuantite(lignealimentation.getQuantite() + 1);
			lignealimentation.setMontant(lignealimentation.getQuantite() * p.getVente());
			lignealimentation.setMontants(df.format(lignealimentation.getMontant()));
			listeLigne.set(index, lignealimentation);
		   }
		totalttc = 0;
		totalquantite = 0;
		 
		 
		for (Lignealimentation d : listeLigne) {
			totalttc = totalttc + d.getMontant();
			totalquantite = totalquantite + d.getQuantite();
		}

		totalttcs = df.format(totalttc);
		 selectedProduit=null;

	}
	public void updateprixacht(AjaxBehaviorEvent event) {
		totalttc=0;
		totalquantite=0;
		 DecimalFormat df = new DecimalFormat("0.000");	
		 UIComponent component = event.getComponent();
		 codes = (Integer) component.getAttributes().get("test");
		 lignealimentation=listeLigne.get(codes);
		 produit = lignealimentation.getProduit();
	 
		 serviceProduit.update(produit);
		 lignealimentation.setMontant(
				 lignealimentation.getQuantite()* produit.getAchat()+ (
							( lignealimentation.getQuantite() * produit.getAchat()*produit.getTva() * 0.01))
					);		 
		 lignealimentation.setMontants(df.format( lignealimentation.getMontant()));		 
			listeLigne.set(codes,  lignealimentation);
		 
		 for(Lignealimentation p:listeLigne) {
				totalquantite=totalquantite+p.getQuantite();
				totalttc=totalttc+p.getMontant();
			}
			totalttcs = df.format(totalttc);
	}
	
	public double parsedouble(String prixvente) {
		 NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
		 Number number=0;
		try {
			number = format.parse(prixvente);
		} catch (ParseException e) {			
		}		 		 
		return number.doubleValue() ;
	}
	
	public void updateqte(ValueChangeEvent event) {
		UIComponent component = event.getComponent();
		  codes = (Integer) component.getAttributes().get("test");
        Lignealimentation t=listeLigne.get(codes);
          t.setQuantite(Double.parseDouble(t.getQuantites()));
        	listeLigne.set(codes, t);
	}
       
	public void updatetotalachat(AjaxBehaviorEvent event) {
         totalttc=0;
		totalquantite=0;
		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
		lignealimentation = listeLigne.get(codes);
		serviceProduit.update(lignealimentation.getProduit());
		DecimalFormat df = new DecimalFormat("0.000"); 
		lignealimentation .setMontant(
				lignealimentation .getQuantite()* lignealimentation .getProduit().getAchat()+ (
						(lignealimentation .getQuantite() * lignealimentation .getProduit().getAchat()*lignealimentation .getProduit().getTva() * 0.01))
				);		 
		lignealimentation.setMontants(df.format(lignealimentation.getMontant()));		 
		listeLigne.set(codes,lignealimentation);
		for(Lignealimentation p:listeLigne) {
			totalquantite=totalquantite+p.getQuantite();
			totalttc=totalttc+p.getMontant();
		}
		totalttcs = df.format(totalttc);
		 
	}
	
private Integer verifierarticle(Produit libelle, Integer i) {
		
		for (int j = 0;j < listeLigne.size();j++) {
			if (listeLigne.get(j).getProduit() != null)
					if( libelle.getCode().equals( listeLigne.get(j).getProduit().getCode()))
				return j; 
		}
		return i;
	}

	public void getFournisseurbyname(AjaxBehaviorEvent event) {
		fourniseur = serviceFournisseur.getbyname(nomfournissuer);
		numAchat = serviceAchat.getmaxcode() + 1;
		nomfournissuer=fourniseur.	getNom();	 
	}
	public void getFournisseurbyname2(AjaxBehaviorEvent event) {
		fourniseur = serviceFournisseur.getbyname(nomfournissuer);		 
		listAchat = new ArrayList<Achat>();
		listAchat = serviceAchat.getArticlebyfournisseur(fourniseur);
	}
	public String getAllfournisseur() {
		listfournisseur = new ArrayList<Fournisseur>();
		listfournisseur = serviceFournisseur.getAll();
		return SUCCESS;
	}

	public void getFacturebyFour(AjaxBehaviorEvent event) {
		Fournisseur f = serviceFournisseur.getbyname(listfours.get(0));
		listAchat = new ArrayList<Achat>();
		listAchat = serviceAchat.getArticlebyfournisseur(f);
	}
public void verifnumfact(AjaxBehaviorEvent event) {
	Factureachat f=serviceFactureAchat.getbycode(numFacture);
	 
	if(f!=null) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "code facture existe deja ", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
	public String saveAchat() {
		 if(fourniseur ==null) {
			 return ERROR;
		 }
		double totalttc = 0;
		double totalht = 0;
		double totaltva = 0;
		 
			 List<Lignealimentation> ll=new ArrayList<Lignealimentation>();
		for (Lignealimentation p : listeLigne) {
			if (p.getProduit()!=null) {
				if(
						 ((p.getProduit().getFamille().getCode()==5 || p.getProduit().getFamille().getCode()==6  || p.getProduit().getFamille().getCode()==2 || p.getProduit().getFamille().getCode()==3) && p.getProduit().getMarge()>=16.0)
						 ) {
				Lignealimentation l = new Lignealimentation();
				l.setTva(p.getProduit().getTva());				 
				l.setDate(date);
				 l.setQuantite(p.getQuantite());
				l.setMantantht(p.getQuantite() * p.getProduit().getAchat());
				l.setMontant(p.getQuantite() * p.getProduit().getAchat()+ (p.getQuantite() * p.getProduit().getAchat()*l.getTva() * 0.01));
				l.setProduit(p.getProduit());	
				SimpleDateFormat s=new SimpleDateFormat("dd-MM-yyyy");
				 l.setDates(s.format(date));
				l.setTauxtva(l.getMantantht()* l.getTva() * 0.01);				
				ll.add(l);
				p.getProduit().setQuantitedepot(p.getProduit().getQuantitedepot() + p.getQuantite());
				serviceProduit.update(p.getProduit());
			}
				else if(p.getProduit().getFamille().getCode()==4 || p.getProduit().getFamille().getCode()==1 ||p.getProduit().getFamille().getCode()==8) {
					Lignealimentation l = new Lignealimentation();
					l.setTva(p.getProduit().getTva());				 
					l.setDate(date);
					 l.setQuantite(p.getQuantite());
					l.setMantantht(p.getQuantite() * p.getProduit().getAchat());
					l.setMontant(p.getQuantite() * p.getProduit().getAchat()+ (p.getQuantite() * p.getProduit().getAchat()*l.getTva() * 0.01));
					l.setProduit(p.getProduit());	
					SimpleDateFormat s=new SimpleDateFormat("dd-MM-yyyy");
					 l.setDates(s.format(date));
					l.setTauxtva(l.getMantantht()* l.getTva() * 0.01);				
					ll.add(l);
					p.getProduit().setQuantitedepot(p.getProduit().getQuantitedepot() + p.getQuantite());
					serviceProduit.update(p.getProduit());
				}
			}
		}
		listeLigne=new ArrayList<Lignealimentation>();
		listeLigne=ll;
		for (Lignealimentation c : listeLigne) {
			totalht = totalht + c.getMantantht();
			totaltva = totaltva + (c.getTauxtva());
              totalquantite= totalquantite+c.getQuantite();
		}

		DecimalFormat df = new DecimalFormat("0.000");
		this.totalht= df.format(totalht);
		this.totalttc = totalht + totaltva;//df.format(totalht + totaltva)
		this.totaltva = df.format(totaltva);
		totalttc=totalht+totaltva;
		totalttcs=df.format(totalttc);
		factureAchat = new Factureachat();
		factureAchat.setCode(numFacture);
		factureAchat.setDate(date);
		factureAchat.setTotalht(totalht);
		factureAchat.setTotaltva(totaltva);
		factureAchat.setTotalttc(this.totalttc);
		 
		if (typepayement.equals(TypePayement.cheque) ) {			
			Chequereglement c = new Chequereglement();
			c.setNumero(numcheque);
			c.setBanque(banque);
			c.setMontant(this.totalttc);
            c.setEcheance(echeance);
            c.setDate(new Date());          
			 
			servicechequereg.save(c);
		factureAchat.setNumerocheck(numcheque);
		factureAchat.setBanque(banque);
		factureAchat.setEcheance(echeance);
		factureAchat.setDatepayement(new Date());
		factureAchat.setTypepayement(TypePayement.cheque);
		factureAchat.setStatus(Status.Payee);
		}
		else	if (typepayement.equals(TypePayement.espece) ) {		
			factureAchat.setTypepayement(TypePayement.espece);
			factureAchat.setStatus(Status.Payee);
		}
		else {
			factureAchat.setStatus(Status.NonPayee);
		}
		serviceFactureAchat.save(factureAchat);
		achat = new Achat();
		achat.setDate(date);
		achat.setFournisseur(fourniseur);
		achat.setMontant(totalttc);
		achat.setFactureachat(factureAchat);
		serviceAchat.save(achat);
         for(Lignealimentation l:listeLigne) 
        	   {
        	 l.setAchat(achat);
             serviceLigneAlimentation.save(l);
         }
         Utilisateur user=userservice.getCurrentUser();
 		Traceshop t=new Traceshop();
 		t.setAction("save achat "+numAchat+ " de "+fourniseur.getNom()+" par"+user.getNom());
 		t.setDate(new Date());
 		t.setUtilisateur(user); 		
 		Collections.sort(listeLigne);
 		servicetrace.save(t); 
         return SUCCESS;
		 
	}

	public String saveavoir() {
		return saveAchat();
	}

	public void updateCode(AjaxBehaviorEvent event) {
		produit = serviceProduit.Findbycode(codeproduit);
		listProduit.set(codes, produit);
		codeproduit=null;
	}

	public void updatenom(AjaxBehaviorEvent event) {
		produit = serviceProduit.Findbydes(libelle);
		// produit.setMontant(produit.getQuantites()*produit.getAchat());
		listProduit.set(codes, produit);
		libelle=null;
	}

	public void updatemontant(AjaxBehaviorEvent event) {
		// produit.setQuantites(qte);
		produit.setMontant(produit.getAchat() * produit.getQuantites()+(produit.getAchat()*produit.getTva()/100));
		listProduit.set(codes, produit);
		totalttc=0;totalquantite=0;		 
		for(Produit produit:listProduit) {
			if (produit.getCode() != null) {
				totalttc=totalttc+( produit.getQuantites() * produit.getAchat());
				totalquantite =totalquantite + produit.getQuantites();
			}
		}
		
		
		//updatetotal();
	}

	public void onCellEdit(CellEditEvent event) {
		produit = listProduit.get(event.getRowIndex());
		codes = event.getRowIndex();
		// listProduit.set(codes, produit);
	}

	private void updatetotal() {
		DecimalFormat df = new DecimalFormat("0.000");
		totalttc = 0;// df.format(0)
		totalquantite = 0;
		for (Produit p : listProduit) {
			if (p.getQuantites() != 0) {
			//	totalttc = df.format(Double.parseDouble(totalttc) + p.getMontant());
				totalquantite = totalquantite + p.getQuantites();
			}
		}
	}

	public void getAchatbynum(AjaxBehaviorEvent event) {
		typepayement=null;
		 
		nomfournissuer=null;
		totalttcs=null;
		banque=null;
		numcheque=null;
		echeance=null;
		  achat = serviceAchat.getAchatbyid(numAchat);
		DecimalFormat df = new DecimalFormat("0.000");
		nomfournissuer = achat.getFournisseur().getNom();
		numFacture = achat.getFactureachat().getCode();
		factureAchat = achat.getFactureachat();
		totalttc = factureAchat.getTotalttc();
		totalttcs = df.format(totalttc);
		typepayement=factureAchat.getTypepayement();
		fourniseur=achat.getFournisseur();
		if(factureAchat.getStatus().equals(Status.Payee)) {
			typepayement=factureAchat.getTypepayement();
			if(typepayement.equals(TypePayement.cheque)) {
				numcheque=factureAchat.getNumerocheck();
				banque=factureAchat.getBanque();
				echeance=factureAchat.getEcheance();
			}
		}
	}

	public String validerreglement() {
		date=new Date();		
		factureAchat.setTypepayement(typepayement);	
		factureAchat.setStatus(Status.Payee);
		factureAchat.setDatepayement(date);
		factureAchat.setDatepayement(new Date());
		if (typepayement.equals(TypePayement.cheque) ) {		
		factureAchat.setNumerocheck(numcheque);
		factureAchat.setBanque(banque);
		factureAchat.setEcheance(echeance);		 
	 
		}
		serviceFactureAchat.update(factureAchat);
		  Compte compte=serviceCompte.Findbynom("080810230810000495"); 
			if(compte!=null) {
			SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
			 
			Transaction t=serviceTransaction.Findbycode(numcheque);
			 if(t==null) {
				 t=new  Transaction();	
			t.setDescription(nomfournissuer);
			t.setCompte(compte);
			t.setMontant(new BigDecimal(montantcheque));
			t.setReglement(Reglement.Cheque);
			t.setTypetransaction(TypeTransaction.Debit);
			t.setDate(new Date());
			t.setReference(numcheque);
			t.setDates(f.format(new Date()));	 
			t.setEtatcheque(Enumcheque.EnCirculation);	  
	     	serviceTransaction.save(t);
			 }
			}
		 Utilisateur user=userservice.getCurrentUser();
	 		Traceshop t=new Traceshop();
	 		if(user !=null)
	 		t.setAction("valider regelement de la facture"+factureAchat.getCode()+ " de "+fourniseur.getNom()+" par"+user.getNom());
	 		else
	 			t.setAction("valider regelement de la facture"+factureAchat.getCode()+ " de "+fourniseur.getNom());
	 		t.setDate(new Date());
	 		t.setUtilisateur(user);
	 		servicetrace.save(t); 
		return SUCCESS;
	}

	public String reglementFacture() {
		typepayement=null;
		numAchat=null;
		nomfournissuer=null;
		totalttcs=null;
		banque=null;
		numcheque=null;
		echeance=null;
		return SUCCESS;
	}

	public String etatFactureachat() {
		listfournisseur = new ArrayList<Fournisseur>();
		listfournisseur = serviceFournisseur.getAll();
		status = Status.values();
		nomfournissuer = null;
		statu =null;
		date = new Date();
		listAchat = new ArrayList<Achat>();
		return SUCCESS;
	}

	public void getEtatFacturebystatus(AjaxBehaviorEvent event) {
		listAchat = new ArrayList<Achat>();

		listAchat = serviceAchat.getAchatbystatusfacture(statu, fourniseur);
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

	public ServiceAchat getServiceAchat() {
		return serviceAchat;
	}

	public void setServiceAchat(ServiceAchat serviceAchat) {
		this.serviceAchat = serviceAchat;
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

	public Achat getAchat() {
		return achat;
	}

	public void setAchat(Achat achat) {
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
		DecimalFormat df = new DecimalFormat("0.000");
		totalttcs = df.format(totalttc);
		return totalttcs;
	}

	public void setTotalttcs(String totalttcs) {
		this.totalttcs = totalttcs;
	}

	public ServiceFactureAchat getServiceFactureAchat() {
		return serviceFactureAchat;
	}

	public void setServiceFactureAchat(ServiceFactureAchat serviceFactureAchat) {
		this.serviceFactureAchat = serviceFactureAchat;
	}

	public Factureachat getFactureAchat() {
		return factureAchat;
	}

	public void setFactureAchat(Factureachat factureAchat) {
		this.factureAchat = factureAchat;
	}

	public ServiceLigneAlimentation getServiceLigneAlimentation() {
		return serviceLigneAlimentation;
	}

	public void setServiceLigneAlimentation(ServiceLigneAlimentation serviceLigneAlimentation) {
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

	public List<Achat> getListAchat() {
		return listAchat;
	}

	public void setListAchat(List<Achat> listAchat) {
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

	public List<Lignealimentation> getListeLigne() {
		return listeLigne;
	}

	public void setListeLigne(List<Lignealimentation> listeLigne) {
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

	public Produit getProduit() {
		return produit;
	}

	public List<Produit> getListproduit() {
		return listproduit;
	}

	public void setListproduit(List<Produit> listproduit) {
		this.listproduit = listproduit;
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


	public Produit getSelectedProduit() {
		return selectedProduit;
	}


	public void setSelectedProduit(Produit selectedProduit) {
		this.selectedProduit = selectedProduit;
	}


	public List<Produit> getFilteredProduit() {
		return filteredProduit;
	}


	public void setFilteredProduit(List<Produit> filteredProduit) {
		this.filteredProduit = filteredProduit;
	}


	public List<Produit> getFilterproduits() {
		return filterproduits;
	}


	public void setFilterproduits(List<Produit> filterproduits) {
		this.filterproduits = filterproduits;
	}


	public void setTotalquantite(double totalquantite) {
		this.totalquantite = totalquantite;
	}


	public ServiceChequereglement getServicechequereg() {
		return servicechequereg;
	}


	public void setServicechequereg(ServiceChequereglement servicechequereg) {
		this.servicechequereg = servicechequereg;
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
	public Lignealimentation getLignealimentation() {
		return lignealimentation;
	}
	public void setLignealimentation(Lignealimentation lignealimentation) {
		this.lignealimentation = lignealimentation;
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


	public List<Factureachat> getListFactureAchat() {
		return listFactureAchat;
	}


	public void setListFactureAchat(List<Factureachat> listFactureAchat) {
		this.listFactureAchat = listFactureAchat;
	}


	public double getMontantcheque() {
		return montantcheque;
	}


	public void setMontantcheque(double montantcheque) {
		this.montantcheque = montantcheque;
	}
	
	

}
