package com.tn.shell.ui.gestat;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.CellEditEvent;

import com.tn.shell.model.banque.Compte;
import com.tn.shell.model.banque.Enumcheque;
import com.tn.shell.model.banque.Reglement;
import com.tn.shell.model.banque.Transaction;
import com.tn.shell.model.banque.TypeTransaction;
import com.tn.shell.model.gestat.*;
import com.tn.shell.model.shop.Famillearticle;
import com.tn.shell.model.shop.Fournisseur;
import com.tn.shell.model.shop.Lignevente;
import com.tn.shell.model.shop.Produit;
import com.tn.shell.service.banque.ServiceCompte;
import com.tn.shell.service.banque.ServiceTransaction;
import com.tn.shell.service.gestat.*;
import com.tn.shell.service.shop.ServiceFournisseur;
import com.tn.shell.service.shop.ServiceLignevente;
import com.tn.shell.service.shop.ServiceProduit;
import com.tn.shell.service.shop.ServiceFamilleaticle;

@ManagedBean(name = "EtatBean")
@SessionScoped
public class EtatBean {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	@ManagedProperty(value = "#{ServiceCompte}")
	ServiceCompte serviceCompte;
	@ManagedProperty(value = "#{ServiceTransaction}")
	ServiceTransaction serviceTransaction;
	@ManagedProperty(value = "#{ServiceProduit}")
	ServiceProduit serviceProduit;
	@ManagedProperty(value = "#{ServiceFournisseur}")
	ServiceFournisseur serviceFournisseur;
	@ManagedProperty(value = "#{ServiceArticleCarburant}")
	ServiceArticleCarburant serviceArticleCarburant;
	@ManagedProperty(value = "#{ServiceAchatcarburant}")
	ServiceAchatcarburant serviceAchat;
	@ManagedProperty(value = "#{ServiceFamilleaticle}")
	ServiceFamilleaticle serviceFamilleaticle;
	@ManagedProperty(value = "#{ServiceFactureAchatcar}")
	ServiceFactureAchatcar serviceFactureAchat;
	@ManagedProperty(value = "#{ServiceLigneAlimentationcar}")
	ServiceLigneAlimentationcar serviceLigneAlimentation;
	@ManagedProperty(value = "#{ServiceLignevente}")
	ServiceLignevente serviceLignevente;
	@ManagedProperty(value = "#{ServiceDepensegestat}")
	ServiceDepensegestat serviceDepense;
	@ManagedProperty(value = "#{ServiceCheque}")
	ServiceCheque serviceCheque;
	@ManagedProperty(value = "#{ServiceCaisse}")
	ServiceCaisse serviceCaisse;
	@ManagedProperty(value = "#{ServiceLigneindex}")
	ServiceLigneindex serviceLigneindex;
	@ManagedProperty(value = "#{ServiceAchatcaisse}")
	ServiceAchatcaisse serviceAchatcaisse;
	@ManagedProperty(value = "#{ServiceFamilleDepensegestat}")
	ServiceFamilleDepensegestat servicefamilledepense;
	@ManagedProperty(value = "#{ServiceClientgestat}")
    ServiceClientgestat serviceclientgestat;
	private List<Achatcaisse> listAchatESP;
	private List<Depensegestat> listDepense;
	private String matricule;
	private List<Factureachatcarburant> listfactureachat;
																																																																																																																																																																															private Date date1;
																																																																																																																																																																															private Date date2;
	private String dates1;
	private String dates2;
	private List<Produit> listProduit;
	private String totalCA;
	private String totalProfil;
	private String totaCA;
	private String totaltva;
	private String caAvoir;
	private String tvaAvoir;
	private String caAchat;
	private String tvaAchat;
	private String totalProfilBrut;
	private String totalachatCar;
	private List<Famillearticle> listFamille;
	private List<String> selectedfamilles;
	private List<Famillearticle> listfamile;
	private List<Ligneventecredit> listelignevente;
	private List<Familledepensegestat> familledepense;
	private Poste[] postes;
	private Poste poste;
	private String dates;
	private Date date;
	private Fournisseur fournisseur;
	private String nomfour;
	private List<Fournisseur> listefour;
	private List<Cheque> listecheque;
	private double montantcheque;
	private String montantcheques;
	private List<Caisse> listcaisse;
	private Caisse caisse;
	private String designation;
	private Diverachat listAchat;
	private List<Achatcarburant> listachat;

	public String etatdebanque() {
		date1 = new Date();
		date2 = new Date();
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

	public String getetatdebanque() throws ParseException {
		montantcheque=0;
		totaCA = "";
		totalProfil = "";
		totalProfilBrut = "";
		totaltva = "";
		totalCA = "";
		try {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		List<Caisse> l = new ArrayList<Caisse>();
		List<Lignevente> lv3 = new ArrayList<Lignevente>();
		listcaisse = new ArrayList<Caisse>();
		date2.setHours(23);
		l = serviceCaisse.getbetwendates(date1, date2);
		
		double tch = 0;
		double tc = 0;
		double tcr = 0;
		double tes = 0;
		double tb = 0;
		double tcshell = 0;
		int i = 0;
		while (i < l.size()) {
			double totalshop = 0;
			lv3 = serviceLignevente.getAllventeparDate(l.get(i).getDates());

			for (Lignevente vv : lv3) {
				totalshop = totalshop + vv.getTotalttc();
			}
			l.get(i).setTotalcheque(l.get(i).getTotalcheque() + l.get(i + 1).getTotalcheque());
			l.get(i).setTotalcredit(l.get(i).getTotalcredit() + l.get(i + 1).getTotalcredit());

			l.get(i).setTotalespece(l.get(i).getTotalespece() + l.get(i + 1).getTotalespece() + totalshop);

			l.get(i).setCartebanquaire(l.get(i).getCartebanquaire() + l.get(i + 1).getCartebanquaire());

			l.get(i).setCarteshell(l.get(i).getCarteshell() + l.get(i + 1).getCarteshell());

			// 1l.set(i+1, null);

			listcaisse.add(l.get(i));
			i = i + 2;
		}
		DecimalFormat df = new DecimalFormat("#,###.000");
		double totalachatCar=0;
		for (Caisse j : listcaisse) {
			tc = tc + j.getTotalcheque();
			tcr = tcr + j.getTotalcredit();
			tes = tes + j.getTotalespece();
			tb = tb + j.getCartebanquaire();
			tcshell = tcshell + j.getCarteshell();
			double toatlAchat=serviceAchat.getTotalAchatbyDate(j.getDate());
		    j.setTotalAchatcarburant(df.format(toatlAchat));
		    totalachatCar=totalachatCar+toatlAchat;
		}
		this.totalachatCar=null;
		totaCA = df.format(tc);
		totalProfil = df.format(tcr);
		totalProfilBrut = df.format(tes);
		montantcheque=tes;
		totaltva = df.format(tb);
		totalCA = df.format(tcshell);
		this.totalachatCar=df.format(totalachatCar);
		}catch(Exception e) {
			return SUCCESS;
		}
		return SUCCESS;
	}
	  public String validerespectobanque() {
    	  Compte compte=serviceCompte.Findbynom("080810230810000495"); 
  		if(compte!=null) {
  		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
  		Transaction t=new  Transaction();		
  		t.setDescription("espece");
  		t.setCompte(compte);
  		t.setMontant(new BigDecimal(montantcheque));
  		t.setReglement(Reglement.Espece);
  		t.setTypetransaction(TypeTransaction.Credit);
  		t.setDate(new Date());  		 
  		t.setDates(f.format(new Date()));  		
  		compte.setSolde(compte.getSolde().add(new BigDecimal(montantcheque)));
  		serviceCompte.update(compte);
       	serviceTransaction.save(t);
       	
       	
        List<Cheque> listCheques=new ArrayList<Cheque>();
        listCheques= serviceCheque.getListchequebetweendate(date1, date2);
        if(listCheques!=null) {
        	for(Cheque d:listCheques) {
        		 
			  		if(compte!=null) {
			  		 
			  		  t=new  Transaction();		
			  		t.setDescription("reglement");
			  		t.setCompte(compte);
			  		t.setReference(d.getNumero());
			  		t.setMontant(new BigDecimal(d.getMontant()));
			  		t.setReglement(Reglement.Cheque);
			  		t.setEtatcheque(Enumcheque.Encours);
			  		t.setTypetransaction(TypeTransaction.Credit);
			  		t.setDate(new Date());  	
			  		t.setCheque(d);
			  		t.setDates(f.format(new Date()));  		 
			       	serviceTransaction.save(t);
			  		}
        	}
        }
  		}
    	return SUCCESS;
    }
	public String etatAchat() {
		date1 = new Date();
		date2 = new Date();
		listAchatESP = new ArrayList<Achatcaisse>();
		return SUCCESS;
	}

	public void getdates(AjaxBehaviorEvent event) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dates1 = dateFormat.format(date1);
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
		for (Achatcarburant f : listachat) {

			total = total + f.getFactureachat().getTotalht();
			total2 = total2 + f.getFactureachat().getTotaltva();
			total3 = total3 + f.getFactureachat().getTotalttc();
		}
		totaltva = df.format(total2);
		totaCA = df.format(total);
		totalProfil = df.format(total3);
	}

	public void getetatFactureAchat(AjaxBehaviorEvent event) {
		double total = 0;
		listAchatESP = new ArrayList<Achatcaisse>();
		List<Achatcaisse> l = new ArrayList<Achatcaisse>();
		List<Achatcaisse> l2 = new ArrayList<Achatcaisse>();
		l = serviceAchatcaisse.getAll();
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

			for (Achatcaisse f : l) {
				if (date1 != null) {
					Date d1 = dateFormat.parse(dateFormat.format(date1));

					if (d1.compareTo(dateFormat.parse(dateFormat.format(f.getDate()))) <= 0) {
						l2.add(f);
					}
				}

				if (date1 == null)
					l2.add(f);
			}
			for (Achatcaisse f : l2) {
				if (date2 != null) {
					Date d2 = dateFormat.parse(dateFormat.format(date2));

					if (d2.compareTo(dateFormat.parse(dateFormat.format(f.getDate()))) >= 0) {
						listAchatESP.add(f);

					}
				}
				if (date2 == null)
					listAchatESP.add(f);
			}

		} catch (ParseException e1) {
			// TODO Autof-generated catch block
			e1.printStackTrace();
		}
		for (Achatcaisse f : listAchatESP) {

			total = total + f.getMontant();

		}
		DecimalFormat df = new DecimalFormat("#,###.000");
		totaCA = df.format(total);
	}

	public void getFournisseurbynom(AjaxBehaviorEvent event) {
		fournisseur = serviceFournisseur.getbyname(nomfour);
		listfactureachat = new ArrayList<Factureachatcarburant>();
		listfactureachat = serviceFactureAchat.getfacturebyfour(fournisseur);
	}

	private List<Articlecarburant> listarticlecarburant;
	private List<Etatprofil> listetatprofil;
	private String totalquantite;
	private String totalvente;
	private String totalmarge;
	private String totalventeshop;
	private String totalmargeshop;
	private String totaldepense;
	private String pourcentdepense;
	private String margebrutmagazin;
	private String totalcredit;
	private String etatprofilbrut;
	private String totalmargebrut;
    private String pourcetprofilcar;
    private String pourcentprofilmagazin;
    private String pourcentprofilbrut;
	public String etatdeprofil() {
		listetatprofil = new ArrayList<Etatprofil>();
		totalquantite = null;
		totalvente = null;
		totalmarge = null;
		totalventeshop = null;
		totalmargeshop = null;
		totaldepense = null;
		pourcentdepense = null;
		margebrutmagazin = null;
		totalcredit = null;
		etatprofilbrut = null;
		totalmargebrut = null;
		totaCA = "#,###.000";
		totalProfilBrut = "#,###.000";
		date1 = new Date();
		date2 = new Date();

		listetatprofil = new ArrayList<Etatprofil>();

		return SUCCESS;
	}
	
	public String etatdeprofil2() {
		listetatprofil = new ArrayList<Etatprofil>();
		totalquantite = null;
		totalvente = null;
		totalmarge = null;
		totalventeshop = null;
		totalmargeshop = null;
		totaldepense = null;
		pourcentdepense = null;
		margebrutmagazin = null;
		totalcredit = null;
		etatprofilbrut = null;
		totalmargebrut = null;
		totaCA = ("#,###.000");
		totalProfilBrut = ("#,###.000");
		date1 = new Date();
		date2 = new Date();

		listetatprofil = new ArrayList<Etatprofil>();

		return SUCCESS;
	}

	public String calculeretatdeprofil() {
		
		listetatprofil = new ArrayList<Etatprofil>();
		double totalvente = 0;
		double totalmarge = 0;double totalquantite = 0;
		double totalventeshop = 0;
		double totalmargeshop = 0;
		double totaldepense = 0;
		double pourcentdepense = 0;
		double margebrutmagazin = 0;
		double totalcredit = 0;
		double porcentprofilbrut = 0;
		double porcentprofilcar = 0;
		double porcentprofilmagazin= 0;
		double totalmargebrut = 0;
		DecimalFormat df = new DecimalFormat("#,###.000");
		DecimalFormat dfs= new DecimalFormat("0");
		 
		 
		 List<Articlecarburant> listarticle = new ArrayList<Articlecarburant>();
		listarticle = serviceArticleCarburant.getAll();
		double totalmarges = 0;
		
		date2.setHours(23);
		 List<String> ld = new ArrayList<String>();
	        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			for (int i = date1.getDate(); i <= date2.getDate(); i++) {
				Date d = new Date();
				d.setDate(i);
				d.setMonth(date1.getMonth());
				d.setYear(date1.getYear());
				String ds = sf.format(d);
				System.out.println(" dates" + ds);
				ld.add(ds);
			}
			listetatprofil=new ArrayList<Etatprofil>();
		// try {
		 
				for (Articlecarburant a : listarticle) {
					Etatprofil e = new Etatprofil();
					double totalqte = 0;
					 
					double montant = 0;
					double totalachat = 0;
					double marge = 0;				 
						double qte=0;
						 double m=0;						 
							double achat=0;
							double prix=0 ;						 
							for (String s : ld) {
								 qte=serviceLigneindex.getquantitebyarticledates( a,s);
								 m=serviceLigneindex. getAllventepardatearticlequantitepardate(a,s);
								 List<Ligneindex> l=serviceLigneindex.getAllventepardate(s,a);
								 if(l.size()>0) 
									 prix=l.get(0).getPrixachat();								 
								  achat =achat+ (qte * prix);
									montant=montant+m;
									totalqte=totalqte+qte;
							} 
					
					e.setArticle(a);
					e.setQuantite(dfs.format(totalqte));
					e.setTotalvente(df.format(montant)); 
					  achat=totalqte * prix;
					marge =  montant - achat;
					e.setMarge(df.format(marge));
					e.setMarges(marge);
					
					 
					listetatprofil.add(e);
					totalmarge = totalmarge + marge;
					totalquantite = totalquantite + totalqte;
					totalvente = totalvente + montant;
					totalachat=totalachat+achat;
				
			}/*}catch(Exception e) {
				System.out.println("ereur dans ligne index");
			}*/
		 
		 for (Etatprofil e : listetatprofil) {
			e.setPourcents((e.getMarges() / totalmarge) * 100);
			e.setPourcent(df.format(e.getPourcents()));

		}
		  
		 
		 totaldepense=serviceDepense.getsummontantbydate(date1, date2)	;	
		  totalcredit=serviceclientgestat.getAllmontantcredit();
		  totalventeshop=serviceLignevente.getmontantbetwendate(date1, date2);
		 
			totalmargeshop=serviceLignevente.getmargebetwendate(date1, date2);
			 
	    margebrutmagazin=totalmargeshop-totaldepense;
		totalmargebrut=totalmarge+margebrutmagazin;
		this.totalquantite = dfs.format(totalquantite);
		 porcentprofilmagazin=(margebrutmagazin/totalmargebrut)*100;
		 porcentprofilcar=(totalmarge/totalmargebrut)*100;
		this.totalvente = df.format(totalvente);
		this.totalmarge = df.format(totalmarge);
		this.totalventeshop=df.format(totalventeshop);
		this.totaldepense=df.format(totaldepense);
		this.totalmargeshop=df.format(totalmargeshop);
		this.totalcredit=df.format(totalcredit);
		this.totalmargebrut=df.format(totalmargebrut);
		this.pourcetprofilcar=df.format(porcentprofilcar);
		this.pourcentprofilmagazin=df.format(porcentprofilmagazin);
		this.margebrutmagazin=df.format(margebrutmagazin);
		 
		return SUCCESS;
	}
	
public String calculeretatdeprofil2() {
		
		listetatprofil = new ArrayList<Etatprofil>();
		double totalvente = 0;
		double totalmarge = 0;double totalquantite = 0;
		double totalventeshop = 0;
		double totalmargeshop = 0;
		double totaldepense = 0;
		double pourcentdepense = 0;
		double margebrutmagazin = 0;
		double totalcredit = 0;
		double porcentprofilbrut = 0;
		double porcentprofilcar = 0;
		double porcentprofilmagazin= 0;
		double totalmargebrut = 0;
		DecimalFormat df = new DecimalFormat("#,###.000");
		DecimalFormat dfs= new DecimalFormat("0");
		List<String> ld = new ArrayList<String>();

		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		for (int i = date1.getDate(); i <= date2.getDate(); i++) {
			Date d = new Date();
			d.setDate(i);
			d.setMonth(date1.getMonth());
			d.setYear(date1.getYear());
			String ds = sf.format(d);
			System.out.println(" dates" +ds);
			ld.add(ds);

		}
		 List<Articlecarburant> listarticle = new ArrayList<Articlecarburant>();
		listarticle = serviceArticleCarburant.getAll();
		double totalmarges = 0;
		try {
		if (ld.size() > 0)
			
				for (Articlecarburant a : listarticle) {
					Etatprofil e = new Etatprofil();
					double totalqte = 0;
					double montant = 0;
					double marge = 0;
					for (String s : ld) {
						double qte=0;
						 double m=0;
					 		   
							     qte=serviceLigneindex.getAllventepardatearticlequantite(  a,date1,date2);
							     m=serviceLigneindex.getAllventepardatearticle(  a,date1,date2);	
							 
						 totalqte=totalqte+qte;	
						 montant=montant+m;
					}
					totalquantite = totalquantite + totalqte;
					totalvente = totalvente + montant;
					e.setArticle(a);
					e.setQuantite(dfs.format(totalqte));
					e.setTotalvente(df.format(montant));
					marge = marge + ((totalqte * a.getVente()) - (totalqte * a.getAchat()));
					e.setMarge(df.format(marge));
					e.setMarges(marge);
					totalmarge = totalmarge + marge;
					listetatprofil.add(e);
				
			}}catch(Exception e) {
				System.out.println("ereur dans ligne index");
			}
		 
		for (Etatprofil e : listetatprofil) {
			e.setPourcents((e.getMarges() / totalmarge) * 100);
			e.setPourcent(df.format(e.getPourcents()));

		}
		 
		 
		for (String s : ld) {
			double ds=0;
		List<Depensegestat> listdepense=new ArrayList<Depensegestat>();
		listdepense=serviceDepense.getdepensebydate(s);
		if(listdepense.size()>0)
		for(Depensegestat d:listdepense)
			ds=ds+d.getMontant();
		
		totaldepense=totaldepense+ds;
		}
		 
		 
		 
	   
	   
	    margebrutmagazin=totalmargeshop-totaldepense;
		totalmargebrut=totalmarge+margebrutmagazin;
		
		 porcentprofilmagazin=(margebrutmagazin/totalmargebrut)*100;
		 porcentprofilcar=(totalmarge/totalmargebrut)*100;
		this.totalvente = df.format(totalvente);
		this.totalmarge = df.format(totalmarge);
		this.totalquantite = dfs.format(totalquantite);
		this.totalventeshop=df.format(totalventeshop);
		this.totaldepense=df.format(totaldepense);
		this.totalmargeshop=df.format(totalmargeshop);
		this.totalcredit=df.format(totalcredit);
		this.totalmargebrut=df.format(totalmargebrut);
		this.pourcetprofilcar=df.format(porcentprofilcar);
		this.pourcentprofilmagazin=df.format(porcentprofilmagazin);
		this.margebrutmagazin=df.format(margebrutmagazin);
		 
		return SUCCESS;
	}
	private Integer codes;
	public void handleChange1(ValueChangeEvent event) {
		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
		System.out.println("\n\n codes" + codes + "\n\n");
		Etatprofil e = listetatprofil.get(codes);
		DecimalFormat dfs = new DecimalFormat("0");
		DecimalFormat df = new DecimalFormat("#,###.000");
		 e.setQuantites((Double) event.getNewValue());
		 e.setQuantite(dfs.format(e.getQuantites()));
		 e.setTotalventes(e.getArticle().getVente()*e.getQuantites());
		 e.setTotalvente(df.format(e.getTotalventes()));
		 e.setMarges(e.getTotalventes()-(e.getArticle().getAchat()*e.getQuantites()));	 
		 e.setMarge(df.format(e.getMarges()));
		 listetatprofil.set(codes, e);
	}
	
	public String validerProfil() {
		double totalvente = 0;
		double totalmarge = 0;double totalquantite = 0;
		DecimalFormat df = new DecimalFormat("#,###.000");
		DecimalFormat dfs = new DecimalFormat("0");
		for(Etatprofil e:listetatprofil) {
			totalvente=totalvente+e.getTotalventes();
			totalmarge=totalmarge+e.getMarges();
			totalquantite=totalquantite+e.getQuantites();
		}
		for(Etatprofil e:listetatprofil) {
			e.setPourcents((e.getMarges() / totalmarge) * 100);
			e.setPourcent(df.format(e.getPourcents()));
		}
		
		this.totalvente = df.format(totalvente);
		this.totalmarge = df.format(totalmarge);
		this.totalquantite = dfs.format(totalquantite);
		return SUCCESS;
	}
	public String getetatDepenses() {
		matricule = "";
		date1 = null;
		familledepense = new ArrayList<Familledepensegestat>();
		familledepense = servicefamilledepense.getAll();
		date2 = null;

		return SUCCESS;
	}

	public String getetatDepenses1() {
		totaCA = null;
		double total = 0;
		listDepense = new ArrayList<Depensegestat>();
		List<Depensegestat> listbl = new ArrayList<Depensegestat>();
		List<Depensegestat> listbl1 = new ArrayList<Depensegestat>();
		List<Depensegestat> listbl2 = new ArrayList<Depensegestat>();
		//date2.setDate(date2.getDate()+1);
		listbl = serviceDepense.getdepensebetweendate(date1, date2);
		 

		 
		for (Depensegestat bl : listbl) {
			if (!matricule.equals(null)) {

				if (bl.getFamilledepense().getLibelle().equals(matricule)) {
					listbl2.add(bl);
				}
			}
			
			
			if (matricule.equals(null))
				listbl2.add(bl);
		}
		for (Depensegestat d : listbl2) {
			if (!designation.equals(null)) {
			if(d.getLibelle().contains(designation)) {
				listDepense.add(d);
				total = total + d.getMontant();
			}
			}
			if (designation.equals(null)) {
				listDepense.add(d);
				total = total + d.getMontant();
			}
		}
		
		DecimalFormat df = new DecimalFormat("#,###.000");
		totaCA = df.format(total);
		 
		return SUCCESS;
	}

	public String etatCheques() {
		date1 = new Date();
		date2 = new Date();
		return SUCCESS;
	}

	public String getetatCheques() {
		montantcheque = 0;
		List<Cheque> listbl = new ArrayList<Cheque>();
		List<Cheque> listbl1 = new ArrayList<Cheque>();
		List<Cheque> listbl2 = new ArrayList<Cheque>();
		List<Cheque> listbl3 = new ArrayList<Cheque>();
		listbl = serviceCheque.getAll();
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

			for (Cheque bl : listbl) {
				if (date1 != null) {
					Date d1 = dateFormat.parse(dateFormat.format(date1));

					if (d1.compareTo(dateFormat.parse(dateFormat.format(bl.getDate()))) <= 0) {
						listbl1.add(bl);
					}
				}

				if (date1 == null)
					listbl1.add(bl);
			}
			for (Cheque bl : listbl1) {
				if (date2 != null) {
					Date d2 = dateFormat.parse(dateFormat.format(date2));

					if (d2.compareTo(dateFormat.parse(dateFormat.format(bl.getDate()))) >= 0) {
						listbl2.add(bl);
					}
				}
				if (date2 == null)
					listbl2.add(bl);
			}

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		listecheque = new ArrayList<Cheque>();

		for (Cheque d : listbl2) {

			listecheque.add(d);
			montantcheque = montantcheque + d.getMontant();
		}

		return SUCCESS;
	}

	public String articleEnAlerteDeStock() {
		listProduit = new ArrayList<Produit>();
		listProduit = serviceProduit.getAllQtenegatif();
		return SUCCESS;
	}

	public String etatFiscal() {
		totaCA = ("#,###.000");
		totalProfilBrut = ("#,###.000");
		date1 = new Date();
		date2 = new Date();
		listProduit = new ArrayList<Produit>();
		return SUCCESS;
	}

	public String calculeretatfiscal() {

		DecimalFormat df = new DecimalFormat("#,###.000");
		totaCA = df.format(0);
		totalProfilBrut = df.format(0);
		listFamille = new ArrayList<Famillearticle>();
		listFamille = serviceFamilleaticle.getAll();

		for (Famillearticle f : listFamille) {
			/*
			 * double cf=serviceLignevente.getchiffreAffaire(f); double
			 * pb=serviceLignevente.getProfilBrut(f); double
			 * cfAchat=serviceLigneAlimentation.getchiffreAffaireAchat(f); double
			 * tvaAchat=serviceLigneAlimentation.gettvaAchat(f); double
			 * cfAvoir=serviceLigneAlimentationavoir.getchiffreAffaireAvoir(f); double
			 * tvaAvoir=serviceLigneAlimentationavoir.gettvaAvoir(f);
			 * 
			 * f.setChiffre_affaires(df.format(cf)); f.setProfilbrut(df.format(pb));
			 * f.setCaAvoir(df.format(cfAvoir)); f.setTvaAvoir(df.format(tvaAvoir));
			 * f.setCaAchat(df.format(cfAchat)); f.setTvaAchat(df.format(tvaAchat));
			 * totaCA=df.format(Double.parseDouble(totaCA)+cf);
			 * totalProfilBrut=df.format(Double.parseDouble(totaCA)+pb);
			 */
			// totaltva=df.format(Double.parseDouble();
		}

		for (Famillearticle f : listFamille) {
			f.setPourcenPBTotal(df.format(Double.parseDouble(f.getChiffre_affaires()) / Double.parseDouble(totaCA)));
			f.setPourcenPBTotal(df.format(Double.parseDouble(f.getProfilbrut()) / Double.parseDouble(totalProfilBrut)));
		}

		return SUCCESS;
	}

	public String articleNonVendu() {
		return SUCCESS;
	}

	public String articleNonVenduShop() {
		return SUCCESS;
	}

	public String articleVendu() {
		return SUCCESS;
	}

	public String etatVenteParFamille() {
		date = new Date();
		postes = Poste.values();
		listelignevente = new ArrayList<Ligneventecredit>();

		return SUCCESS;
	}

	public String getetatventeparDate(AjaxBehaviorEvent event) {
		listelignevente = new ArrayList<Ligneventecredit>();
		// listelignevente = serviceLignevente.getAllventeparposteandDate(dates,poste);
		listfamile = new ArrayList<Famillearticle>();
		return SUCCESS;
	}

	public String FactureAchatEtAvoir() {
		return SUCCESS;
	}

	public String FacturationDesTickets() {
		return SUCCESS;
	}

	public String EtatVentesParVenduers() {
		return SUCCESS;
	}

	public String etatdesArticles() {
		return SUCCESS;
	}

	/*
	 * getter
	 * 
	 * and
	 * 
	 * setter
	 * 
	 */
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

	public ServiceCheque getServiceCheque() {
		return serviceCheque;
	}

	public void setServiceCheque(ServiceCheque serviceCheque) {
		this.serviceCheque = serviceCheque;
	}

	public ServiceCaisse getServiceCaisse() {
		return serviceCaisse;
	}

	public void setServiceCaisse(ServiceCaisse serviceCaisse) {
		this.serviceCaisse = serviceCaisse;
	}

	public ServiceArticleCarburant getServiceArticleCarburant() {
		return serviceArticleCarburant;
	}

	public void setServiceArticleCarburant(ServiceArticleCarburant serviceArticleCarburant) {
		this.serviceArticleCarburant = serviceArticleCarburant;
	}

	public ServiceLigneindex getServiceLigneindex() {
		return serviceLigneindex;
	}

	public void setServiceLigneindex(ServiceLigneindex serviceLigneindex) {
		this.serviceLigneindex = serviceLigneindex;
	}

	public ServiceAchatcaisse getServiceAchatcaisse() {
		return serviceAchatcaisse;
	}

	public void setServiceAchatcaisse(ServiceAchatcaisse serviceAchatcaisse) {
		this.serviceAchatcaisse = serviceAchatcaisse;
	}

	public ServiceAchatcarburant getServiceAchat() {
		return serviceAchat;
	}

	public void setServiceAchat(ServiceAchatcarburant serviceAchat) {
		this.serviceAchat = serviceAchat;
	}

	public ServiceFactureAchatcar getServiceFactureAchat() {
		return serviceFactureAchat;
	}

	public void setServiceFactureAchat(ServiceFactureAchatcar serviceFactureAchat) {
		this.serviceFactureAchat = serviceFactureAchat;
	}

	public ServiceLigneAlimentationcar getServiceLigneAlimentation() {
		return serviceLigneAlimentation;
	}

	public void setServiceLigneAlimentation(ServiceLigneAlimentationcar serviceLigneAlimentation) {
		this.serviceLigneAlimentation = serviceLigneAlimentation;
	}

	public ServiceLignevente getServiceLignevente() {
		return serviceLignevente;
	}

	public void setServiceLignevente(ServiceLignevente serviceLignevente) {
		this.serviceLignevente = serviceLignevente;
	}

	public ServiceDepensegestat getServiceDepense() {
		return serviceDepense;
	}

	public void setServiceDepense(ServiceDepensegestat serviceDepense) {
		this.serviceDepense = serviceDepense;
	}

	public ServiceFamilleDepensegestat getServicefamilledepense() {
		return servicefamilledepense;
	}

	public void setServicefamilledepense(ServiceFamilleDepensegestat servicefamilledepense) {
		this.servicefamilledepense = servicefamilledepense;
	}

	public ServiceFamilleaticle getServiceFamilleaticle() {
		return serviceFamilleaticle;
	}

	public void setServiceFamilleaticle(ServiceFamilleaticle serviceFamilleaticle) {
		this.serviceFamilleaticle = serviceFamilleaticle;
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

	public String getDates1() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates1 = s.format(date1);
		return dates1;
	}

	public void setDates1(String dates1) {
		this.dates1 = dates1;
	}

	public String getDates2() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates2 = s.format(date2);
		return dates2;
	}

	public void setDates2(String dates2) {
		this.dates2 = dates2;
	}

	public List<String> getSelectedfamilles() {
		return selectedfamilles;
	}

	public void setSelectedfamilles(List<String> selectedfamilles) {
		this.selectedfamilles = selectedfamilles;
	}

	public String getTotaltva() {
		return totaltva;
	}

	public void setTotaltva(String totaltva) {
		this.totaltva = totaltva;
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

	public List<Famillearticle> getListfamile() {
		return listfamile;
	}

	public void setListfamile(List<Famillearticle> listfamile) {
		this.listfamile = listfamile;
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

	public String getDates() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		if (date != null)
			dates = s.format(date);
		else
			dates = "";
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
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

	public double getMontantcheque() {
		return montantcheque;
	}

	public void setMontantcheque(double montantcheque) {
		this.montantcheque = montantcheque;
	}

	public String getMontantcheques() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		montantcheques = df.format(montantcheque);
		return montantcheques;
	}

	public void setMontantcheques(String montantcheques) {
		this.montantcheques = montantcheques;
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

	public List<Factureachatcarburant> getListfactureachat() {
		return listfactureachat;
	}

	public void setListfactureachat(List<Factureachatcarburant> listfactureachat) {
		this.listfactureachat = listfactureachat;
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

	public Diverachat getListAchat() {
		return listAchat;
	}

	public void setListAchat(Diverachat listAchat) {
		this.listAchat = listAchat;
	}

	public List<Achatcarburant> getListachat() {
		return listachat;
	}

	public void setListachat(List<Achatcarburant> listachat) {
		this.listachat = listachat;
	}

	public List<Articlecarburant> getListarticlecarburant() {
		return listarticlecarburant;
	}

	public void setListarticlecarburant(List<Articlecarburant> listarticlecarburant) {
		this.listarticlecarburant = listarticlecarburant;
	}

	public String getTotalquantite() {
		return totalquantite;
	}

	public void setTotalquantite(String totalquantite) {
		this.totalquantite = totalquantite;
	}

	public String getTotalvente() {
		return totalvente;
	}

	public void setTotalvente(String totalvente) {
		this.totalvente = totalvente;
	}

	public String getTotalmarge() {
		return totalmarge;
	}

	public void setTotalmarge(String totalmarge) {
		this.totalmarge = totalmarge;
	}

	public String getTotalventeshop() {
		return totalventeshop;
	}

	public void setTotalventeshop(String totalventeshop) {
		this.totalventeshop = totalventeshop;
	}

	public String getTotalmargeshop() {
		return totalmargeshop;
	}

	public void setTotalmargeshop(String totalmargeshop) {
		this.totalmargeshop = totalmargeshop;
	}

	public String getTotaldepense() {
		return totaldepense;
	}

	public void setTotaldepense(String totaldepense) {
		this.totaldepense = totaldepense;
	}

	public String getPourcentdepense() {
		return pourcentdepense;
	}

	public void setPourcentdepense(String pourcentdepense) {
		this.pourcentdepense = pourcentdepense;
	}

	public String getMargebrutmagazin() {
		return margebrutmagazin;
	}

	public void setMargebrutmagazin(String margebrutmagazin) {
		this.margebrutmagazin = margebrutmagazin;
	}

	public String getTotalcredit() {
		return totalcredit;
	}

	public void setTotalcredit(String totalcredit) {
		this.totalcredit = totalcredit;
	}

	public String getEtatprofilbrut() {
		return etatprofilbrut;
	}

	public void setEtatprofilbrut(String etatprofilbrut) {
		this.etatprofilbrut = etatprofilbrut;
	}

	public String getTotalmargebrut() {
		return totalmargebrut;
	}

	public void setTotalmargebrut(String totalmargebrut) {
		this.totalmargebrut = totalmargebrut;
	}

	public List<Etatprofil> getListetatprofil() {
		return listetatprofil;
	}

	public void setListetatprofil(List<Etatprofil> listetatprofil) {
		this.listetatprofil = listetatprofil;
	}

	 

	public ServiceClientgestat getServiceclientgestat() {
		return serviceclientgestat;
	}

	public void setServiceclientgestat(ServiceClientgestat serviceclientgestat) {
		this.serviceclientgestat = serviceclientgestat;
	}

	public String getPourcetprofilcar() {
		return pourcetprofilcar;
	}

	public void setPourcetprofilcar(String pourcetprofilcar) {
		this.pourcetprofilcar = pourcetprofilcar;
	}

	public String getPourcentprofilmagazin() {
		return pourcentprofilmagazin;
	}

	public void setPourcentprofilmagazin(String pourcentprofilmagazin) {
		this.pourcentprofilmagazin = pourcentprofilmagazin;
	}

	public String getPourcentprofilbrut() {
		return pourcentprofilbrut;
	}

	public void setPourcentprofilbrut(String pourcentprofilbrut) {
		this.pourcentprofilbrut = pourcentprofilbrut;
	}

	public Integer getCodes() {
		return codes;
	}

	public void setCodes(Integer codes) {
		this.codes = codes;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getTotalachatCar() {
		return totalachatCar;
	}

	public void setTotalachatCar(String totalachatCar) {
		this.totalachatCar = totalachatCar;
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
	
	
	

}
