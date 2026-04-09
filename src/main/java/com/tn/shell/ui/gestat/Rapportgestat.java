package com.tn.shell.ui.gestat;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.tn.shell.model.gestat.Articlecarburant;
import com.tn.shell.model.gestat.DepenseCheque;
import com.tn.shell.model.gestat.Depensegestat;
import com.tn.shell.model.gestat.Etatprofil;
import com.tn.shell.model.gestat.Familledepensegestat;
import com.tn.shell.model.gestat.Lignealimentationcar;
import com.tn.shell.model.gestat.Ligneindex;
import com.tn.shell.model.gestat.Rapportpiste;
import com.tn.shell.model.gestat.Tracegestat;
import com.tn.shell.model.gestat.Utilisateur;
import com.tn.shell.model.paie.Paie;
import com.tn.shell.model.paie.Paieconge;
import com.tn.shell.model.paie.Paieprime;
import com.tn.shell.model.shop.Famillearticle;
import com.tn.shell.model.shop.Lignevente;
import com.tn.shell.service.gestat.ServiceAchatcaisse;
import com.tn.shell.service.gestat.ServiceAchatcarburant;
import com.tn.shell.service.gestat.ServiceArticleCarburant;
import com.tn.shell.service.gestat.ServiceCaisse;
import com.tn.shell.service.gestat.ServiceCheque;
import com.tn.shell.service.gestat.ServiceClientgestat;
import com.tn.shell.service.gestat.ServiceDepensecheque;
import com.tn.shell.service.gestat.ServiceDepensegestat;
import com.tn.shell.service.gestat.ServiceFactureAchatcar;
import com.tn.shell.service.gestat.ServiceFamilleDepensegestat;
import com.tn.shell.service.gestat.ServiceLigneAlimentationcar;
import com.tn.shell.service.gestat.ServiceLigneindex;
import com.tn.shell.service.gestat.ServiceTracegestat;
import com.tn.shell.service.gestat.UserService;
import com.tn.shell.service.paie.ServicePaie;
import com.tn.shell.service.paie.ServicePaieconge;
import com.tn.shell.service.paie.Servicepaieprime;
import com.tn.shell.service.shop.ServiceFamilleaticle;
import com.tn.shell.service.shop.ServiceFournisseur;
import com.tn.shell.service.shop.ServiceLignevente;
import com.tn.shell.service.shop.ServiceProduit;

@ManagedBean(name = "Rapportgestat")
@SessionScoped
public class Rapportgestat {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	@ManagedProperty(value = "#{UserService}")
	UserService userservice;
	@ManagedProperty(value = "#{ServiceDepensecheque}")
	ServiceDepensecheque serviceDepensecheque;
	private List<DepenseCheque> listDepense;
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
	@ManagedProperty(value = "#{ServiceFamilleDepensegestat}")
	ServiceFamilleDepensegestat servicefamillegestat;
	@ManagedProperty(value = "#{ServicePaie}")
	ServicePaie servicepaie;
	@ManagedProperty(value = "#{Servicepaieprime}")
	Servicepaieprime servicepaieprime;
	@ManagedProperty(value = "#{ServicePaieconge}")
	ServicePaieconge servicePaieconge;
	@ManagedProperty(value = "#{ServiceTracegestat}")
	ServiceTracegestat servicetrace;
	private Date date1;
	private Date date2;
	private List<Articlecarburant> listarticlecarburant;
	private List<Etatprofil> listetatprofil;
	private String benificnet;
	private String totaldepense;
	private double parking;
	private double spontex;
	private double huile;
	private List<Famillearticle> listfamile;
	private List<Familledepensegestat> listfamilledepense;
	private List<Rapportpiste> rapportpistes;
	private Rapportpiste rapportpiste;
	private String mois;
	private Integer annee;
	private String totaCA;
	private String autredepense;
	private List<Depensegestat> listDepenseFathi;
	private List<Depensegestat> listDepenseStation;

	public String rapportgestat() {
		date1 = new Date();
		date2 = new Date();
		rapportpiste = new Rapportpiste();
		rapportpistes = new ArrayList<Rapportpiste>();
		return SUCCESS;
	}

	public String getrapportgestat() {
		rapportpistes = new ArrayList<Rapportpiste>();
		rapportpiste = new Rapportpiste();
		mois = getMoisbyIntger(date1.getMonth() + 1);
		annee = date1.getYear() + 1900;
		DecimalFormat df = new DecimalFormat("#,###.000");
		double margecarburant = 0;
		List<Articlecarburant> listarticle = new ArrayList<Articlecarburant>();
		listarticle = serviceArticleCarburant.getAll();
		date2.setHours(23);
        date1.setHours(0);
        date1.setMinutes(0);
        date1.setSeconds(0);
        System.out.println(date1+"    "+date2);
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
		try {

			for (Articlecarburant a : listarticle) {
				double prixachatttc = 0;				 
				double totalqte = 0;
				double montant = 0;
				double marge = 0;
				double qte = 0;
				double m = 0;
				double mgs =0;	
				double prix=0;
				double motantachat=0;
				for (String s : ld) {
					 qte=serviceLigneindex.getquantitebyarticledates( a,s);
					 m=serviceLigneindex. getAllventepardatearticlequantitepardate(a,s);
					 List<Ligneindex> l=serviceLigneindex.getAllventepardate(s,a);
					 if(l!=null) 
						 prix=l.get(0).getPrixachat();
					 if(prix!=0)
						  prixachatttc=prix+(prix*a.getTvaachat()/100);
					 motantachat =motantachat+ (qte * prix);
						montant=montant+m;
						totalqte=totalqte+qte;
				}
		
		 //m=serviceLigneindex.getAllventepardatearticle( a,date1,date2);						  
			//  prix=serviceLigneAlimentation.getprixbydate(date1, date2, a) ;
			  
		 
			
				 
			
				 
				 mgs = montant - motantachat;
				System.out.println("_n\n\n  mgs"+mgs +" "+a.getNom());
				//marge = marge + mgs;
				margecarburant = margecarburant + mgs;
				System.out.println("_n\n\n  margecarburant"+margecarburant +" "+margecarburant);
			}
			rapportpiste.setMatgecarburant(df.format(margecarburant));
			listfamile = new ArrayList<Famillearticle>();
			listfamile = serviceFamilleaticle.getAll();

			double dmaison = 0;
			double dfethi = 0;
			double daide = 0;
			double dstation = 0;
			double stade = 0;
			double dcafee = 0;
			double douvriers = 0;
			 
		    double dmaisonCheque=0;	 
			double dfethiCheque = 0;
			double daideCheque = 0;
			double dstationCheque = 0;			 
			double stadeCheque = 0;
			double dcafeeCheque = 0;
			double douvriersCheque = 0;
			     dfethiCheque=dfethiCheque+serviceDepensecheque.getdepensebetweendateandfamille2(date1, date2, 1);
			     daideCheque=daideCheque+serviceDepensecheque.getdepensebetweendateandfamille2(date1, date2, 5);
			     dstationCheque=dstationCheque+serviceDepensecheque.getdepensebetweendateandfamille2(date1, date2, 2)
			     +serviceDepensecheque.getdepensebetweendateandfamille2(date1, date2, 3)
			      ;
			     stadeCheque=stadeCheque+serviceDepensecheque.getdepensebetweendateandfamille2(date1, date2, 7);
			     dcafeeCheque=dcafeeCheque+serviceDepensecheque.getdepensebetweendateandfamille2(date1, date2, 9);
			     douvriersCheque=douvriersCheque+serviceDepensecheque.getdepensebetweendateandfamille2(date1, date2, 8)+
			    		 serviceDepensecheque.getdepensebetweendateandfamille2(date1, date2, 4);
				 dmaisonCheque=dmaisonCheque+serviceDepensecheque.getdepensebetweendateandfamille2(date1, date2, 6);
		 
			dmaison = dmaison + serviceDepense.getsummontantbydateandfamille(date1, date2, 6)+dmaisonCheque;
			// if(d.getFamilledepense().getId()==1)
			dfethi = dfethi + serviceDepense.getsummontantbydateandfamille(date1, date2, 1)+dfethiCheque;
			// if(d.getFamilledepense().getId()==14)
			daide = daide + serviceDepense.getsummontantbydateandfamille(date1, date2, 5)+daideCheque;
			// if(d.getFamilledepense().getId()==2 ||d.getFamilledepense().getId()==11 ||
			// d.getFamilledepense().getId()==21 || d.getFamilledepense().getId()==22)
			dstation = dstation + serviceDepense.getsummontantbydateandfamille(date1, date2, 2)
					+ serviceDepense.getsummontantbydateandfamille(date1, date2, 3)+dstationCheque
		         ;
			// nouvelstation=serviceDepense.getsummontantbydateandfamille(date1, date2,26);
			stade = serviceDepense.getsummontantbydateandfamille(date1, date2, 7)+stadeCheque;
			// if(d.getFamilledepense().getId()==13)
			dcafee = dcafee + serviceDepense.getsummontantbydateandfamille(date1, date2, 9)+dcafeeCheque;
			// if(d.getFamilledepense().getId()==9)
			douvriers = douvriers + serviceDepense.getsummontantbydateandfamille(date1, date2, 8)
			+ serviceDepense.getsummontantbydateandfamille(date1, date2, 4)+douvriersCheque;

			listDepense = new ArrayList<DepenseCheque>();
			listDepense = serviceDepensecheque.getdepensebetweendate(date1, date2);
			/*BigDecimal totaldepCheque = serviceDepensecheque.getsummontantbydate(date1, date2);
			  if(  totaldepCheque ==null )
	        	     totaldepCheque = new BigDecimal(0);*/
			//rapportpiste.setDepenseCheque(totaldepCheque);
          
			listDepenseFathi = new ArrayList<Depensegestat>();
			listDepenseStation = new ArrayList<Depensegestat>();
			listDepenseFathi = serviceDepense.getdepensebetweendateAndFamille(date1, date2, 1);
			listDepenseStation = serviceDepense.getdepensebetweendateAndFamille(date1, date2, 2);

			rapportpiste.setDepaide(df.format(daide));
			rapportpiste.setDepcafe(df.format(dcafee));
			// rapportpiste.setDepensevoituretransport(df.format(dvoituretr));
			rapportpiste.setDepfethi(df.format(dfethi));
			rapportpiste.setDepmaison(df.format(dmaison));
			// rapportpiste.setDepvoiturefamille(df.format(dvoiturefamille));
			rapportpiste.setDepstation(df.format(dstation));
			// rapportpiste.setDepnouvelelstation(df.format(nouvelstation));
			rapportpiste.setStade(df.format(stade));
			douvriers = douvriers + servicepaie.getPaieByAnneeAndMoiss(annee, date1.getMonth() + 1);
			douvriers = douvriers
					+ servicepaieprime.getPaieByAnneeAndMois2((date1.getYear() - 1) + 1900, date1.getMonth() + 1);
			douvriers = douvriers
					+ servicePaieconge.getPaieByAnneeAndMois2((date1.getYear() - 1) + 1900, date1.getMonth() + 1);
			rapportpiste.setDepouvriers(df.format(douvriers));
			double mlub = 0;
			double mfil = 0;
			double macc = 0;
			double mlav = 0;
			double mtr = 0;
			double mboiss = 0;
			double mali = 0;
			double mcaf = 0;			 
			double mgaz = 0;
			double mcartt = 0;
			double mvid = 0;
			mlub = serviceLignevente.getProfilBrutParFamillebetwendate2(date1, date2, 1);
			mfil = serviceLignevente.getProfilBrutParFamillebetwendate2(date1, date2, 2);
			macc = serviceLignevente.getProfilBrutParFamillebetwendate2(date1, date2, 3);
			mlav = serviceLignevente.getProfilBrutParFamillebetwendate2(date1, date2, 10);
			mvid = serviceLignevente.getProfilBrutParFamillebetwendate2(date1, date2, 11);
			mcartt = serviceLignevente.getProfilBrutParFamillebetwendate2(date1, date2, 4);
			mboiss = serviceLignevente.getProfilBrutParFamillebetwendate2(date1, date2, 5);
			mali = serviceLignevente.getProfilBrutParFamillebetwendate2(date1, date2, 6);

			mcaf = serviceLignevente.getProfilBrutParFamillebetwendate2(date1, date2, 7);

			mgaz = serviceLignevente.getProfilBrutParFamillebetwendate2(date1, date2, 8);

			rapportpiste.setMargeaccessoire(df.format(macc));
			rapportpiste.setMargeboisson(df.format(mboiss));
			rapportpiste.setMargecafeteria(df.format(mcaf));
			rapportpiste.setMargecartetel(df.format(mcartt));
			rapportpiste.setMargefiltre(df.format(mfil));
			rapportpiste.setMargegaz(df.format(mgaz));
			rapportpiste.setMargelavage(df.format(mlav));
			rapportpiste.setMargevidange(df.format(mvid));

			rapportpiste.setMargetransport(df.format(mtr));
			rapportpiste.setMargelubrifianr(df.format(mlub));
			rapportpiste.setMargeproduitalimentaire(df.format(mali));

			double benifice = 0;
			benifice = mlub + mfil + macc + mlav + mtr + mboiss + mali + mgaz + mcartt + margecarburant + mcaf+mvid;
			double depense = 0;
			depense = dmaison + dfethi + daide + dstation + douvriers + dcafee  + stade;
			rapportpiste.setTotalbenifice(df.format(benifice));
			rapportpiste.setTotaldepense(df.format(depense));

			rapportpiste.setTotalbenifices(benifice);
			rapportpiste.setTotaldepenses(depense);
			rapportpiste.setBenificenet(benifice - depense);
			rapportpiste.setBenificenets(df.format(rapportpiste.getBenificenet()));
			rapportpistes.add(rapportpiste);

			Utilisateur user = userservice.getCurrentUser();
			Tracegestat t = new Tracegestat();
			t.setAction("extraire rapport gestat du mois" + mois + " de " + annee + " par" + user.getNom());
			t.setDate(new Date());
			t.setUtilisateur(user);
			servicetrace.save(t);
	 	} catch (Exception l) {
			System.out.println("ereur dans ligne index");
		}
		return SUCCESS;
	}

	public void updatepardepense(AjaxBehaviorEvent event) {
		DecimalFormat df = new DecimalFormat("#,###.000");
		rapportpiste.setTotaldepenses(rapportpiste.getTotaldepenses() + parking);
		rapportpiste.setDepfinance(parking);
		rapportpiste.setDepfinances(df.format(parking));
		rapportpiste.setTotaldepense(df.format(rapportpiste.getTotaldepenses()));
		// rapportpiste.setTotalbenifices(rapportpiste.getTotalbenifices()-parking);
		// rapportpiste.setTotalbenifice(df.format(rapportpiste.getTotalbenifices()));
		rapportpiste.setBenificenet(rapportpiste.getBenificenet() - parking);
		rapportpiste.setBenificenets(df.format(rapportpiste.getBenificenet()));
		rapportpistes.set(0, rapportpiste);

	}

	public void updateautredepense(AjaxBehaviorEvent event) {
		rapportpiste.setDepcnsss(autredepense);
	}

	public void updatehuile(AjaxBehaviorEvent event) {
		DecimalFormat df = new DecimalFormat("#,###.000");
		rapportpiste.setDepcnsss(df.format(huile));
		rapportpiste.setTotaldepenses(rapportpiste.getTotaldepenses() + huile);
		rapportpiste.setTotaldepense(df.format(rapportpiste.getTotaldepenses()));
		rapportpiste.setDepcnss(huile);
		// rapportpiste.setTotalbenifices(rapportpiste.getTotalbenifices()-huile);
		// rapportpiste.setTotalbenifice(df.format(rapportpiste.getTotalbenifices()));
		rapportpiste.setBenificenet(rapportpiste.getBenificenet() - huile);
		rapportpiste.setBenificenets(df.format(rapportpiste.getBenificenet()));
	}

	public void updatespontex(AjaxBehaviorEvent event) {
		DecimalFormat df = new DecimalFormat("#,###.000");
		rapportpiste.setSpontex(spontex);
		rapportpiste.setMargespontexs(df.format(spontex));
		rapportpiste.setTotalbenifices(rapportpiste.getTotalbenifices() + spontex);
		rapportpiste.setTotalbenifice(df.format(rapportpiste.getTotalbenifices()));
		rapportpiste.setBenificenet(rapportpiste.getBenificenet() + spontex);
		rapportpiste.setBenificenets(df.format(rapportpiste.getBenificenet()));
	}

	private String getMoisbyIntger(Integer moi) {
		String m = "";
		if (moi == 1)
			m = "Janvier";
		else if (moi == 2)
			m = "Fevrier";
		else if (moi == 3)
			m = "Mars";
		else if (moi == 4)
			m = "Avril";
		else if (moi == 5)
			m = "Mai";
		else if (moi == 6)
			m = "Juin";
		else if (moi == 7)
			m = "Juillet";
		else if (moi == 8)
			m = "aout";
		else if (moi == 9)
			m = "Septembre";
		else if (moi == 10)
			m = "Octobre";
		else if (moi == 11)
			m = "Novembre";
		else if (moi == 12)
			m = "Decembre";
		return m;
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

	public ServiceArticleCarburant getServiceArticleCarburant() {
		return serviceArticleCarburant;
	}

	public void setServiceArticleCarburant(ServiceArticleCarburant serviceArticleCarburant) {
		this.serviceArticleCarburant = serviceArticleCarburant;
	}

	public ServiceAchatcarburant getServiceAchat() {
		return serviceAchat;
	}

	public void setServiceAchat(ServiceAchatcarburant serviceAchat) {
		this.serviceAchat = serviceAchat;
	}

	public ServiceFamilleaticle getServiceFamilleaticle() {
		return serviceFamilleaticle;
	}

	public void setServiceFamilleaticle(ServiceFamilleaticle serviceFamilleaticle) {
		this.serviceFamilleaticle = serviceFamilleaticle;
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

	public ServiceFamilleDepensegestat getServicefamilledepense() {
		return servicefamilledepense;
	}

	public void setServicefamilledepense(ServiceFamilleDepensegestat servicefamilledepense) {
		this.servicefamilledepense = servicefamilledepense;
	}

	public ServiceClientgestat getServiceclientgestat() {
		return serviceclientgestat;
	}

	public void setServiceclientgestat(ServiceClientgestat serviceclientgestat) {
		this.serviceclientgestat = serviceclientgestat;
	}

	public ServiceFamilleDepensegestat getServicefamillegestat() {
		return servicefamillegestat;
	}

	public void setServicefamillegestat(ServiceFamilleDepensegestat servicefamillegestat) {
		this.servicefamillegestat = servicefamillegestat;
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

	public List<Articlecarburant> getListarticlecarburant() {
		return listarticlecarburant;
	}

	public void setListarticlecarburant(List<Articlecarburant> listarticlecarburant) {
		this.listarticlecarburant = listarticlecarburant;
	}

	public List<Etatprofil> getListetatprofil() {
		return listetatprofil;
	}

	public void setListetatprofil(List<Etatprofil> listetatprofil) {
		this.listetatprofil = listetatprofil;
	}

	public String getBenificnet() {
		return benificnet;
	}

	public void setBenificnet(String benificnet) {
		this.benificnet = benificnet;
	}

	public String getTotaldepense() {
		return totaldepense;
	}

	public void setTotaldepense(String totaldepense) {
		this.totaldepense = totaldepense;
	}

	public double getParking() {
		return parking;
	}

	public void setParking(double parking) {
		this.parking = parking;
	}

	public double getSpontex() {
		return spontex;
	}

	public void setSpontex(double spontex) {
		this.spontex = spontex;
	}

	public double getHuile() {
		return huile;
	}

	public void setHuile(double huile) {
		this.huile = huile;
	}

	public List<Famillearticle> getListfamile() {
		return listfamile;
	}

	public void setListfamile(List<Famillearticle> listfamile) {
		this.listfamile = listfamile;
	}

	public List<Familledepensegestat> getListfamilledepense() {
		return listfamilledepense;
	}

	public void setListfamilledepense(List<Familledepensegestat> listfamilledepense) {
		this.listfamilledepense = listfamilledepense;
	}

	public Rapportpiste getRapportpiste() {
		return rapportpiste;
	}

	public void setRapportpiste(Rapportpiste rapportpiste) {
		this.rapportpiste = rapportpiste;
	}

	public List<Rapportpiste> getRapportpistes() {
		return rapportpistes;
	}

	public void setRapportpistes(List<Rapportpiste> rapportpistes) {
		this.rapportpistes = rapportpistes;
	}

	public String getMois() {
		return mois;
	}

	public void setMois(String mois) {
		this.mois = mois;
	}

	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	public ServicePaie getServicepaie() {
		return servicepaie;
	}

	public void setServicepaie(ServicePaie servicepaie) {
		this.servicepaie = servicepaie;
	}

	public Servicepaieprime getServicepaieprime() {
		return servicepaieprime;
	}

	public void setServicepaieprime(Servicepaieprime servicepaieprime) {
		this.servicepaieprime = servicepaieprime;
	}

	public ServicePaieconge getServicePaieconge() {
		return servicePaieconge;
	}

	public void setServicePaieconge(ServicePaieconge servicePaieconge) {
		this.servicePaieconge = servicePaieconge;
	}

	public String getAutredepense() {
		return autredepense;
	}

	public void setAutredepense(String autredepense) {
		this.autredepense = autredepense;
	}

	public UserService getUserservice() {
		return userservice;
	}

	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	public ServiceTracegestat getServicetrace() {
		return servicetrace;
	}

	public void setServicetrace(ServiceTracegestat servicetrace) {
		this.servicetrace = servicetrace;
	}

	public ServiceDepensecheque getServiceDepensecheque() {
		return serviceDepensecheque;
	}

	public void setServiceDepensecheque(ServiceDepensecheque serviceDepensecheque) {
		this.serviceDepensecheque = serviceDepensecheque;
	}

	public List<DepenseCheque> getListDepense() {
		return listDepense;
	}

	public void setListDepense(List<DepenseCheque> listDepense) {
		this.listDepense = listDepense;
	}

	public List<Depensegestat> getListDepenseFathi() {
		return listDepenseFathi;
	}

	public void setListDepenseFathi(List<Depensegestat> listDepenseFathi) {
		this.listDepenseFathi = listDepenseFathi;
	}

	public List<Depensegestat> getListDepenseStation() {
		return listDepenseStation;
	}

	public void setListDepenseStation(List<Depensegestat> listDepenseStation) {
		this.listDepenseStation = listDepenseStation;
	}

	public String getTotaCA() {
		return totaCA;
	}

	public void setTotaCA(String totaCA) {
		this.totaCA = totaCA;
	}

}
