package com.tn.shell.ui.gestat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.tn.shell.model.gestat.Achatcaisse;
import com.tn.shell.model.gestat.Articlecarburant;
import com.tn.shell.model.gestat.Avancegestat;
import com.tn.shell.model.gestat.Caisse;
import com.tn.shell.model.gestat.Cheque;
import com.tn.shell.model.gestat.Clientgestat;
import com.tn.shell.model.gestat.Credit;
import com.tn.shell.model.gestat.Creditanterieur;
import com.tn.shell.model.gestat.Cumulcarburant;
import com.tn.shell.model.gestat.Depensegestat;
import com.tn.shell.model.gestat.Diverachat;
import com.tn.shell.model.gestat.Ligneindex;
import com.tn.shell.model.gestat.Pompe;
import com.tn.shell.model.gestat.Poste;
import com.tn.shell.model.gestat.Retourcuve;
import com.tn.shell.model.gestat.Tracegestat;
import com.tn.shell.model.gestat.Utilisateur;
import com.tn.shell.model.paie.Employee;
import com.tn.shell.model.shop.Famillearticle;
import com.tn.shell.model.shop.Lignevente;
import com.tn.shell.model.shop.Produit;
import com.tn.shell.model.shop.Rendement;
import com.tn.shell.service.gestat.ServiceAchatcaisse;
import com.tn.shell.service.gestat.ServiceAchatcarburant;
import com.tn.shell.service.gestat.ServiceArticleCarburant;
import com.tn.shell.service.gestat.ServiceAvancegestat;
import com.tn.shell.service.gestat.ServiceCaisse;
import com.tn.shell.service.gestat.ServiceCheque;
import com.tn.shell.service.gestat.ServiceClientgestat;
import com.tn.shell.service.gestat.ServiceCreditanterieur;
import com.tn.shell.service.gestat.ServiceCreditclient;
import com.tn.shell.service.gestat.ServiceDepensegestat;
import com.tn.shell.service.gestat.ServiceLigneAlimentationcar;
import com.tn.shell.service.gestat.ServiceLigneindex;
import com.tn.shell.service.gestat.ServicePompe;
import com.tn.shell.service.gestat.ServiceRetourcuve;
import com.tn.shell.service.gestat.ServiceTracegestat;
import com.tn.shell.service.gestat.UserService;
import com.tn.shell.service.paie.ServiceEmployee;
import com.tn.shell.service.shop.ServiceFamilleaticle;
import com.tn.shell.service.shop.ServiceLignevente;
import com.tn.shell.service.shop.ServiceProduit;
import com.tn.shell.service.shop.ServiceRendement;

@ManagedBean(name = "SituationBean")
@SessionScoped
public class SituationBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{ServiceCreditanterieur}")
	ServiceCreditanterieur serviceCreditanterieur;
	@ManagedProperty(value = "#{ServiceCreditclient}")
	ServiceCreditclient serviceCreditclient;
	@ManagedProperty(value = "#{ServiceArticleCarburant}")
	ServiceArticleCarburant serviceArticleCarburant;
	@ManagedProperty(value = "#{ServiceAchatcarburant}")
	ServiceAchatcarburant serviceAchat;
	@ManagedProperty(value = "#{ServiceFamilleaticle}")
	ServiceFamilleaticle serviceFamilleaticle;
	@ManagedProperty(value = "#{ServiceLigneAlimentationcar}")
	ServiceLigneAlimentationcar serviceLigneAlimentation;
	@ManagedProperty(value = "#{ServiceLignevente}")
	ServiceLignevente serviceLignevente;
	@ManagedProperty(value = "#{ServiceCheque}")
	ServiceCheque serviceCheque;
	@ManagedProperty(value = "#{ServiceCaisse}")
	ServiceCaisse serviceCaisse;
	@ManagedProperty(value = "#{ServiceLigneindex}")
	ServiceLigneindex serviceLigneindex;
	@ManagedProperty(value = "#{ServiceAchatcaisse}")
	ServiceAchatcaisse serviceAchatcaisse;
	@ManagedProperty(value = "#{ServiceDepensegestat}")
	ServiceDepensegestat serviceDepense;
	@ManagedProperty(value = "#{ServiceAvancegestat}")
	ServiceAvancegestat serviceAvance;
	@ManagedProperty(value = "#{ServiceClientgestat}")
	ServiceClientgestat serviceClient;
	@ManagedProperty(value = "#{ServiceRetourcuve}")
	ServiceRetourcuve serviceRetourcuve;
	@ManagedProperty(value = "#{ServicePompe}")
	ServicePompe servicePompe;
	@ManagedProperty(value = "#{ServiceProduit}")
	ServiceProduit serviceproduit;
	@ManagedProperty(value = "#{ServiceTracegestat}")
	ServiceTracegestat servicetrace;
	@ManagedProperty(value = "#{UserService}")
	UserService userservice;

	private List<Cheque> listcheque;
	private List<Credit> listCreditclient;
	private Date date1;
	private Date date2;
	private String dates1;
	private String dates2;
	private List<Creditanterieur> listCreditanterieur;
	private Diverachat listAchat;
	private List<Ligneindex> ligneindex;
	private double totalLitrage;
	private double totalventecarburant;
	private String totalLitrages;
	private String totalventecarburants;
	private List<Articlecarburant> listArticlecarburants;
	private Caisse caisse;
	private String test;
	private List<Achatcaisse> listachatcaisse;
	private List<Depensegestat> listdepense;
	private List<Avancegestat> listAvances;
	private List<Clientgestat> listclients;
	private List<Retourcuve> listretour;
	private  Poste poste;
	private Poste[] postes;
	private String totaldepense;
	private String totalcredit;
	private String totalachat;
	private String totalcheque;
	private String totalcreditant;
	private String totalretour;
	private String totalaccompte;
	private String totalstock;
	private List<Cumulcarburant> listcumul;
	private Date date;

	public String impressionCaisse() {
		postes = Poste.values();
		date1 = new Date();
		totalLitrage = 0;
		totalventecarburant = 0;
		return SUCCESS;
	}

	public String cahierlitrage() {
		date1 = new Date();
		date2 = new Date();
		date = new Date();
		listcumul = new ArrayList<Cumulcarburant>();
		return SUCCESS;
	}

	public String cahierlitrageAvecRetour() {
		date1 = new Date();
		date2 = new Date();
		date = new Date();
		listcumul = new ArrayList<Cumulcarburant>();
		return SUCCESS;
	}

	public String validercahierlitrage() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dates1 = dateFormat.format(date1);
		DecimalFormat df = new DecimalFormat("0");
		dates2 = dateFormat.format(date2);

		List<String> ld = new ArrayList<String>();
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		for (int i = date1.getDate(); i <= date2.getDate(); i++) {
			Date d = new Date();
			d.setDate(i);
			d.setMonth(date1.getMonth());
			d.setYear(date1.getYear());
			String ds = sf.format(d);
			ld.add(ds);

		}
		List<Cumulcarburant> ls = new ArrayList<Cumulcarburant>();

		try {
			listArticlecarburants = new ArrayList<Articlecarburant>();
			listArticlecarburants = serviceArticleCarburant.getAll();

			for (Articlecarburant a : listArticlecarburants) {
				listcumul = new ArrayList<Cumulcarburant>();
				for (String s : ld) {
					Cumulcarburant c = new Cumulcarburant();
					double qte = 0;
					double ret = 0;
					double cumul = 0;
					ligneindex = new ArrayList<Ligneindex>();

					// ligneindex = serviceLigneindex.getAllventepardatearticle(s, a); a
					// verifier/*************************************/
//					if(ligneindex!=null)
//					for(Ligneindex l:ligneindex) {
//						 if(s.equals(l.getDates())) {			   
//						     qte=qte+l.getQuantite();
//						
//					}
				    qte = serviceLigneindex.getquantitebyarticledates(a, s);
					//ret = serviceRetourcuve.getquantitebyarticledates(a, s);
					c.setQuantite(qte);
					c.setQuantites(df.format(c.getQuantite()));

					c.setDates(s);
					c.setArticle(a);

					listcumul.add(c);

				}
				listcumul.get(0).setCumuls(listcumul.get(0).getQuantite());
				listcumul.get(0).setCumul(df.format(listcumul.get(0).getCumuls()));
				for (int j = 1; j < listcumul.size(); j++) {
					int i = 0;
					double q = 0;
					while (i <= j) {
						q = q + listcumul.get(i).getQuantite();
						i = i + 1;
					}
					listcumul.get(j).setCumuls(q);
					listcumul.get(j).setCumul(df.format(listcumul.get(j).getCumuls()));
				}

				a.setListcumil(listcumul);
			}

			int j = 0;
			for (int i = 0; i < ld.size(); i++) {
				double tpatj = 0;

				for (Articlecarburant a : listArticlecarburants)
					tpatj = tpatj + a.getListcumil().get(i).getQuantite();

				for (Articlecarburant a : listArticlecarburants) {
					a.getListcumil().get(i).setTotaljournee(tpatj);
					a.getListcumil().get(i).setTotaljournees(df.format(tpatj));

				}
			}

			for (Articlecarburant a : listArticlecarburants) {
				a.getListcumil().get(0).setCumuljournee(a.getListcumil().get(0).getTotaljournee());
				a.getListcumil().get(0).setCumuljournees(df.format(a.getListcumil().get(0).getTotaljournee()));
			}
			for (Articlecarburant a : listArticlecarburants) {
				for (int jj = 1; jj < a.getListcumil().size(); jj++) {
					int i = 0;
					double q = 0;
					while (i <= jj) {
						q = q + a.getListcumil().get(i).getTotaljournee();
						i = i + 1;
					}
					a.getListcumil().get(jj).setCumuljournee(q);
					a.getListcumil().get(jj).setCumuljournees(df.format(a.getListcumil().get(jj).getCumuljournee()));
				}
			}

		} catch (Exception e) {

			return SUCCESS;
		}

		Utilisateur user = userservice.getCurrentUser();
		Tracegestat t = new Tracegestat();
		t.setAction("cahier de litrage de" + dates1 + " par" + user.getNom());
		t.setDate(new Date());
		t.setUtilisateur(user);
		servicetrace.save(t);
		return SUCCESS;
	}

	public String validercahierlitrageAvecretour() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dates1 = dateFormat.format(date1);
		DecimalFormat df = new DecimalFormat("0");
		dates2 = dateFormat.format(date2);

		List<String> ld = new ArrayList<String>();
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		for (int i = date1.getDate(); i <= date2.getDate(); i++) {
			Date d = new Date();
			d.setDate(i);
			d.setMonth(date1.getMonth());
			d.setYear(date1.getYear());
			String ds = sf.format(d);
			ld.add(ds);

		}
		List<Cumulcarburant> ls = new ArrayList<Cumulcarburant>();

		//try {
			listArticlecarburants = new ArrayList<Articlecarburant>();
			listArticlecarburants = serviceArticleCarburant.getAll();

			for (Articlecarburant a : listArticlecarburants) {
				listcumul = new ArrayList<Cumulcarburant>();
				for (String s : ld) {
					Cumulcarburant c = new Cumulcarburant();
					double qte = 0;
					long ret =   0;
					double cumul = 0;
					ligneindex = new ArrayList<Ligneindex>();
					// ligneindex = serviceLigneindex.getAllventepardatearticle(s, a); a
					// verifier/*************************************/
//					if(ligneindex!=null)
//					for(Ligneindex l:ligneindex) {
//						 if(s.equals(l.getDates())) {			   
//						     qte=qte+l.getQuantite();
//						
//					}
					qte = serviceLigneindex.getquantitebyarticledates(a, s);
					ret = serviceRetourcuve.getquantitebyarticledates(a, s);
					
					c.setQuantite(qte-ret);
					c.setQuantites(df.format(c.getQuantite()));

					c.setDates(s);
					c.setArticle(a);
					listcumul.add(c);
				}
				listcumul.get(0).setCumuls(listcumul.get(0).getQuantite());
				listcumul.get(0).setCumul(df.format(listcumul.get(0).getCumuls()));
				for (int j = 1; j < listcumul.size(); j++) {
					int i = 0;
					double q = 0;
					while (i <= j) {
						q = q + listcumul.get(i).getQuantite();
						i = i + 1;
					}
					listcumul.get(j).setCumuls(q);
					listcumul.get(j).setCumul(df.format(listcumul.get(j).getCumuls()));
				}

				a.setListcumil(listcumul);
			}

			int j = 0;
			for (int i = 0; i < ld.size(); i++) {
				double tpatj = 0;

				for (Articlecarburant a : listArticlecarburants)
					tpatj = tpatj + a.getListcumil().get(i).getQuantite();

				for (Articlecarburant a : listArticlecarburants) {
					a.getListcumil().get(i).setTotaljournee(tpatj);
					a.getListcumil().get(i).setTotaljournees(df.format(tpatj));

				}
			}

			for (Articlecarburant a : listArticlecarburants) {
				a.getListcumil().get(0).setCumuljournee(a.getListcumil().get(0).getTotaljournee());
				a.getListcumil().get(0).setCumuljournees(df.format(a.getListcumil().get(0).getTotaljournee()));
			}
			for (Articlecarburant a : listArticlecarburants) {
				for (int jj = 1; jj < a.getListcumil().size(); jj++) {
					int i = 0;
					double q = 0;
					while (i <= jj) {
						q = q + a.getListcumil().get(i).getTotaljournee();
						i = i + 1;
					}
					a.getListcumil().get(jj).setCumuljournee(q);
					a.getListcumil().get(jj).setCumuljournees(df.format(a.getListcumil().get(jj).getCumuljournee()));
				}
			}

		/*} catch (Exception e) {

			return SUCCESS;
		}*/

		Utilisateur user = userservice.getCurrentUser();
		Tracegestat t = new Tracegestat();
		t.setAction("cahier de litrage de" + dates1 + " par" + user.getNom());
		t.setDate(new Date());
		t.setUtilisateur(user);
		servicetrace.save(t);
		return SUCCESS;
	}

	public String situationJournaliere() {
		caisse = new Caisse();
		date1 = new Date();
		totalLitrage = 0;
		totalventecarburant = 0;
		return SUCCESS;
	}

	private List<Lignevente> listelignevente;
	private List<Famillearticle> listfamile;

	private boolean verifiervente(Produit p, List<Lignevente> l) {
		int i = 0;
		while (i < l.size()) {
			if (l.get(i).getProduit().getId() == p.getId())
				return true;
		}
		return false;
	}

	public void getsituationjournaliere2(AjaxBehaviorEvent event) {//
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		ligneindex = new ArrayList<Ligneindex>();
		List<Pompe> l = new ArrayList<Pompe>();
		l = servicePompe.getAll();
		dates1 = dateFormat.format(date1);

		ligneindex = serviceLigneindex.getAllventeparposteNegatif(dates1, poste);

		listArticlecarburants = new ArrayList<Articlecarburant>();

		listArticlecarburants = serviceArticleCarburant.getAll();
		for (Articlecarburant a : listArticlecarburants) {
			double qte = 0;
			for (Ligneindex x : ligneindex) {
				if (a.equals(x.getPompe().getArticlecarburant())) {
					qte = qte + x.getQuantite();
				}
			}
			a.setQte(qte);
		}
		totalventecarburant = 0;
		totalLitrage = 0;
		for (Ligneindex x : ligneindex) {
			totalLitrage = totalLitrage + x.getQuantite();
			totalventecarburant = totalventecarburant + x.getMantant();
		}
		DecimalFormat df = new DecimalFormat("#,###.000");
		caisse = serviceCaisse.getCaissebydate(dates1, poste);
		caisse.setObservations(df.format(caisse.getObservation()));
		caisse.setTotalespececomptable(caisse.getTotalespece() - caisse.getObservation());
		caisse.setTotalcaisse(caisse.getTotalventecarburant() + caisse.getTotalcreditanterieur());
		listdepense = new ArrayList<Depensegestat>();
		listdepense = serviceDepense.getdepensebyCaisse(caisse);

		listAvances = new ArrayList<Avancegestat>();
		listAvances = serviceAvance.getAvancebyCaisse(caisse);

		listCreditclient = new ArrayList<Credit>();
		listCreditclient = serviceCreditclient.getCreditclientbyCaisse(caisse);

		listCreditanterieur = new ArrayList<Creditanterieur>();
		listCreditanterieur = serviceCreditanterieur.getCreditanterieurbyCaisse(caisse);

		listachatcaisse = new ArrayList<Achatcaisse>();
		listachatcaisse = serviceAchatcaisse.getachatyCaisse(caisse);

		listcheque = new ArrayList<Cheque>();
		listcheque = serviceCheque.getChequebyCaisse(caisse);

		listretour = new ArrayList<Retourcuve>();
		listretour = serviceRetourcuve.getRetourcuvebyCaisse(caisse);
	}private List<Rendement> listrendementlav;
	private List<Rendement> listrendementvid;
	private List<Employee> listemployeelav;
	private List<Employee> listemployeevid;
	@ManagedProperty(value = "#{ServiceEmployee}")
	ServiceEmployee serviceEmployee;
	@ManagedProperty(value = "#{ServiceRendement}")
	ServiceRendement servicerendement;
	private String totalLavage ;
	private String totalVidane;
	private String totalSLavage;	
	private double totalVLavage ;
	private double totalSemilavage;
	private double totalSVidane;
	private String totalsv;
    private String totalPoste1;
    private String totalPoste2;
	public String getsituationjournaliere() {//
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		String dates = s.format(date1);
	 
		/*	try {
			totalLavage="";
	     totalVidane="";
	       totalPoste1="";
	        totalPoste2="";
	      double totalLavagevs=  0;
	     double totalLavagess=  0;
	     double totalVidanes= 0;
	     double totalPoste1=0;
	      double   totalPoste2=0;
		  totalVLavage=  0;
		  totalSemilavage=  0;
		  totalSVidane=  0;
		 
		
		listemployeelav = new ArrayList<Employee>();
		listemployeevid = new ArrayList<Employee>();
		listemployeelav = serviceEmployee.getEmployeeparfonction("LAVEUR");		
		listemployeevid.addAll(serviceEmployee.getEmployeeparfonction("VIDANGEUR"));
	
	    listrendementlav=new ArrayList<Rendement>(); 
	    listrendementvid=new ArrayList<Rendement>(); */
		DecimalFormat dfs = new DecimalFormat("0");
		DecimalFormat dfss = new DecimalFormat("0.000");
		double totalv= 0;
		/*List<Rendement> ll ;	 
		for(Employee e:listemployeelav) {
				  ll =new ArrayList<Rendement>();	    
			 double qtes = 0;
				double qte2 = 0;
				double mv=0;double ms=0;
			 Rendement r = new Rendement();
			 ll=servicerendement.getAllventeparDate3(dates,e);
			 if (ll != null) 
			 for (Rendement rr : ll) {
					
					Produit p=serviceproduit.Findbycodeshop(Integer.parseInt(rr.getService()));
					if(p.getNom().contains("s")|| p.getNom().contains("S")) {
					qte2 = qte2 +1;
					ms=ms+rr.getMontantv();
					}
					else { 
						qtes = qtes + rr.getNbvoiture();
					    mv=mv+rr.getMontantv();
					    }
					r.setEmployee(rr.getEmployee());
					
					if(rr.getPoste().equals(com.tn.shell.model.shop.Poste.Poste1))
						totalPoste1=totalPoste1+rr.getMontantv();
					else 
						totalPoste2=totalPoste2+rr.getMontantv();
			 }
					if (qtes == 0 && qte2 == 0)
						r.setTest("true");
					else r.setTest("false");
					r.setNbvoitures(dfs.format(qtes));
					r.setNbsemis(dfs.format(qte2));
					r.setNbvoiture(qtes);
					r.setMontantv(mv);
					r.setMontants(ms);
					r.setNbsemi(qte2);
					r.setDates(dates);	
					double par=(mv/4)+(ms/8) ;
					r.setParouvrier(dfs.format(par));
					totalLavagevs=totalLavagevs+r.getMontantv();//t m lava voit
					totalLavagess=totalLavagess+r.getMontants();//t m lava semi
					totalVLavage=totalVLavage+qtes;
					totalSemilavage=totalSemilavage+r.getNbsemi();
					 if(r.getTest().equals("false"))
					listrendementlav.add(r);
				
		 }
	
		totalv=totalLavagevs+totalLavagess;
		 totalsv=dfss.format(totalv);
		
		 
		 for(Employee e:listemployeevid) {
			  ll =new ArrayList<Rendement>();	
			 double qtes = 0;
				double qte2 = 0;
				double mv=0;double ms=0;
			 Rendement r = new Rendement();
			 ll=servicerendement.getAllventeparDate3(dates,e);
			 if (ll != null) 
			 for (Rendement rr : ll) {
					qtes = qtes + rr.getNbvoiture();
					qte2 = qte2 + rr.getNbsemi();
					mv=mv+rr.getMontantv();
					ms=ms+rr.getMontants();
					r.setEmployee(rr.getEmployee());
			 }
					if (qtes == 0 && qte2 == 0)
						r.setTest("true");
					else r.setTest("false");
					r.setNbvoitures(dfs.format(qtes));
					r.setNbsemis(dfs.format(qte2));
					r.setNbvoiture(qtes);
					r.setMontantv(mv);
					r.setMontants(ms);
					r.setNbsemi(qte2);
					
					totalSVidane=totalSVidane+r.getNbvoiture();
					totalVidanes=totalVidanes+r.getMontantv();
					r.setDates(dates);	
					if(r.getTest().equals("false"))
						 listrendementvid.add(r);
				
		 }
		 Collections.sort(listrendementlav);
		 Collections.sort(listrendementvid);
		 Collections.reverse(listrendementvid);
		 Collections.reverse(listrendementlav);
		 totalLavage =dfss.format(totalLavagevs);
		 
		 totalSLavage=dfss.format(totalLavagess);
        
		 totalVidane=dfss.format(totalVidanes);
		 this.totalPoste2=dfss.format(totalPoste2);
		 this.totalPoste1=dfss.format(totalPoste1);
		}catch(Exception e) {
		}*/
		
		
		
		
		
		
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		//try {
			ligneindex = new ArrayList<Ligneindex>();
			List<Pompe> l = new ArrayList<Pompe>();
			l = servicePompe.getAll();
			dates1 = dateFormat.format(date1);

			for (Pompe p : l) {
				Ligneindex l2s = serviceLigneindex.getAllventepardateandpompeandposte(dates1, p,  Poste.Poste1);
				Ligneindex l2ss = serviceLigneindex.getAllventepardateandpompeandposte(dates1, p,  Poste.Poste2);
				Ligneindex l3 = new Ligneindex();
				if (l2s != null)
					l3.setIndexouverture(l2s.getIndexouverture());
				else
					l3.setIndexouverture(0);
				if (l2ss != null)
					l3.setIndexfermuture(l2ss.getIndexfermuture());
				else
					l3.setIndexfermuture(0);

				l3.setQuantite(l3.getIndexfermuture() - l3.getIndexouverture());
				l3.setMantant(p.getArticlecarburant().getVente() * l3.getQuantite());

				l3.setPompe(p);
				ligneindex.add(l3);
			}
			DecimalFormat df = new DecimalFormat("#,###.000");
			listArticlecarburants = new ArrayList<Articlecarburant>();
			listArticlecarburants = serviceArticleCarburant.getAll();
			double qm = 0;
			for (Articlecarburant a : listArticlecarburants) {
				double qte = 0;
				for (Ligneindex x : ligneindex) {
					if (a.getId().equals(x.getPompe().getArticlecarburant().getId()))
						qte = qte + x.getQuantite();

				}
				qm = qm + a.getQuantite();
				a.setQte(qte);
				 

			}

			totalstock = df.format(qm);
		//	Articlecarburant a1 = listArticlecarburants.get(0);
			//Articlecarburant a2 = listArticlecarburants.get(4);
			//a1.setQte(a1.getQte() + a2.getQte());
			// listArticlecarburants.set(4, null);
			for (Ligneindex x : ligneindex) {
				totalLitrage = totalLitrage + x.getQuantite();
				totalventecarburant = totalventecarburant + x.getMantant();
			}

			Caisse caisse1 = serviceCaisse.getCaissebydate(dates1,  Poste.Poste1);
			Caisse caisse2 = serviceCaisse.getCaissebydate(dates1,  Poste.Poste2);

			listelignevente = new ArrayList<Lignevente>();
			try {

				// lvente=serviceLignevente.getAllventeparposteandDate3(dates1,com.tn.shell.model.shop.Poste.Poste1);
				// lvente=serviceLignevente.getAllventeparposteandDate3(dates1,com.tn.shell.model.shop.Poste.Poste2);
				listfamile = new ArrayList<Famillearticle>();
				listfamile = serviceFamilleaticle.getAll();

				double totalventemagazin = 0;

				List<Lignevente> lvente2 = new ArrayList<Lignevente>();
				List<Produit> lp = new ArrayList<Produit>();
				lvente2 = serviceLignevente.getAllventeparposteandDate(dates1);
				for (Lignevente vv : lvente2) {
					if (!lp.contains(vv.getProduit()))
						lp.add(vv.getProduit());
				}

				totalventemagazin = 0;
				double totalht = 0;
				double totaltva = 0;
				double totalttc = 0;
				for (Produit p : lp) {
					double qteparp = 0;
					double mtparp = 0;
					double prvente = 0;

					Lignevente lvv = new Lignevente();
					for (Lignevente lv : lvente2) {
						if (lv.getProduit().getId() == p.getId()) {

							totalventemagazin = totalventemagazin + lv.getTotalttc();
							qteparp = qteparp + lv.getQuantite();
							mtparp = mtparp + lv.getTotalttc();
							prvente = lv.getPrix();
						}
					}
					lvv.setProduit(p);
					lvv.setQuantite(qteparp);
					lvv.setQuantite2(lvv.getQuantite());
					lvv.setPrix(prvente);
					lvv.setTotalttc(mtparp);
					if (lvv.getQuantite() != 0) {
						totalht = totalht + lvv.getMantantht();
						totaltva = totaltva + lvv.getTauxtva();
						totalttc = totalttc + lvv.getTotalttc();
						listelignevente.add(lvv);
					}

				}

				for (Famillearticle f : listfamile) {
					double qte = 0;
					double m = 0;
					for (Lignevente lv : lvente2) {
						if (f.getCode() == lv.getProduit().getFamille().getCode()) {
							qte = qte + lv.getQuantite();
							m = m + lv.getTotalttc();
						}

					}
					f.setQuantite(qte);
					f.setMontant(df.format(m));
				}

				caisse.setTotalVentesMagasins(df.format(totalventemagazin));
				caisse.setTotalespeceshop(totalventemagazin);
		/*	} catch (Exception e) {
			}*/

			double totalshop = 0;

			for (Lignevente vv : listelignevente) {
				totalshop = totalshop + vv.getTotalttc();
			}

			caisse.setTotalventecarburant(caisse1.getTotalventecarburant() + caisse2.getTotalventecarburant());
			caisse.setTotalventemagasin(totalshop);
			caisse.setTotalcredit(caisse1.getTotalcredit() + caisse2.getTotalcredit());
			caisse.setTotalcheque(caisse1.getTotalcheque() + caisse2.getTotalcheque());
			caisse.setTotaldepense(caisse1.getTotaldepense() + caisse2.getTotaldepense());
			caisse.setRetourcuve(caisse1.getRetourcuve() + caisse2.getRetourcuve());
			caisse.setTotalcreditanterieur(caisse1.getTotalcreditanterieur() + caisse2.getTotalcreditanterieur());
			caisse.setTotalcaisse(caisse.getTotalventecarburant() + caisse.getTotalcreditanterieur());
			caisse.setTotalmanque(caisse1.getTotalmanque() + caisse2.getTotalmanque());
			caisse.setTotalachat(caisse1.getTotalachat() + caisse2.getTotalachat());
			caisse.setCartebanquaire(caisse1.getCartebanquaire() + caisse2.getCartebanquaire());
			caisse.setCarteshell(caisse1.getCarteshell() + caisse2.getCarteshell());
			caisse.setObservation(caisse1.getObservation() + caisse2.getObservation());
			// caisse.setObservations(df.format(caisse.getObservation()));
			caisse.setTotalespece(caisse1.getTotalespece() + caisse2.getTotalespece());
			// caisse.setTotalespeceshop(
			// caisse1.getTotalespeceshop() + caisse2.getTotalespeceshop() +
			// caisse3.getTotalespeceshop());
			caisse.setTotalespececomptable(
					(caisse.getTotalventecarburant() + caisse.getTotalcreditanterieur() + totalshop)
							- (caisse.getTotalcredit() + caisse.getTotalcheque() + caisse.getTotaldepense()
									+ caisse.getRetourcuve() + caisse.getCarteshell() + caisse.getCartebanquaire()
									+ caisse.getTotalmanque() + caisse.getTotalachat()));

			caisse.setTotalespeceq1(caisse1.getTotalespece());
			caisse.setTotalespeceq2(caisse2.getTotalespece());
			caisse.setTotalespeceq3(0);
			// ligneindex = serviceLigneindex.getAllventepardate(dates1);

			// vente par famille et vente magasin

			double t = 0;

			listCreditclient = new ArrayList<Credit>();
			listCreditclient = serviceCreditclient.getListcreditdate(dates1);
			if (listCreditclient.size() > 0)
				for (Credit c : listCreditclient)
					t = t + c.getMontant();
			totalcredit = df.format(t);
			t = 0;

			listcheque = new ArrayList<Cheque>();
			listcheque = serviceCheque.getListchequedate(dates1);
			if (listcheque.size() > 0)
				for (Cheque c : listcheque)
					t = t + c.getMontant();
			totalcheque = df.format(t);
			t = 0;

			listCreditanterieur = new ArrayList<Creditanterieur>();
			listCreditanterieur = serviceCreditanterieur.getListcreditdate(dates1);
			if (listCreditanterieur.size() > 0)
				for (Creditanterieur c : listCreditanterieur)
					t = t + c.getMontant();
			totalcreditant = df.format(t);
			t = 0;

			listdepense = new ArrayList<Depensegestat>();
			listdepense = serviceDepense.getdepensebydate(dates1);
			if (listdepense.size() > 0)
				for (Depensegestat c : listdepense)
					t = t + c.getMontant();
			totaldepense = df.format(t);
			t = 0;

			listAvances = new ArrayList<Avancegestat>();
			listAvances = serviceAvance.getAvancebDate(dates1);
			if (listAvances.size() > 0)
				for (Avancegestat c : listAvances)
					t = t + c.getMontant_avance();
			totalaccompte = df.format(t);
			t = 0;

			listclients = new ArrayList<Clientgestat>();
			listclients = serviceClient.getclientendepassement();

			listachatcaisse = new ArrayList<Achatcaisse>();
			listachatcaisse = serviceAchatcaisse.getachatydate(dates1);
			if (listachatcaisse.size() > 0)
				for (Achatcaisse c : listachatcaisse)
					t = t + c.getMontant();
			totalachat = df.format(t);
			t = 0;

			listretour = new ArrayList<Retourcuve>();
			listretour = serviceRetourcuve.getRetourcuvebydate(dates1);
			if (listretour != null)
				for (Retourcuve c : listretour)
					t = t + c.getMontant();
			totalretour = df.format(t);
			t = 0;
		} catch (Exception e) {
		}
		return SUCCESS;
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

	public ServiceArticleCarburant getServiceArticleCarburant() {
		return serviceArticleCarburant;
	}

	public void setServiceArticleCarburant(ServiceArticleCarburant serviceArticleCarburant) {
		this.serviceArticleCarburant = serviceArticleCarburant;
	}

	public ServiceFamilleaticle getServiceFamilleaticle() {
		return serviceFamilleaticle;
	}

	public void setServiceFamilleaticle(ServiceFamilleaticle serviceFamilleaticle) {
		this.serviceFamilleaticle = serviceFamilleaticle;
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

	public Diverachat getListAchat() {
		return listAchat;
	}

	public void setListAchat(Diverachat listAchat) {
		this.listAchat = listAchat;
	}

	public List<Ligneindex> getLigneindex() {
		return ligneindex;
	}

	public void setLigneindex(List<Ligneindex> ligneindex) {
		this.ligneindex = ligneindex;
	}

	public double getTotalLitrage() {
		return totalLitrage;
	}

	public void setTotalLitrage(double totalLitrage) {
		this.totalLitrage = totalLitrage;
	}

	public double getTotalventecarburant() {
		return totalventecarburant;
	}

	public void setTotalventecarburant(double totalventecarburant) {
		this.totalventecarburant = totalventecarburant;
	}

	public String getTotalLitrages() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalLitrages = df.format(totalLitrage);
		return totalLitrages;
	}

	public void setTotalLitrages(String totalLitrages) {
		this.totalLitrages = totalLitrages;
	}

	public String getTotalventecarburants() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalventecarburants = df.format(totalventecarburant);
		return totalventecarburants;
	}

	public void setTotalventecarburants(String totalventecarburants) {
		this.totalventecarburants = totalventecarburants;
	}

	public List<Articlecarburant> getListArticlecarburants() {
		return listArticlecarburants;
	}

	public void setListArticlecarburants(List<Articlecarburant> listArticlecarburants) {
		this.listArticlecarburants = listArticlecarburants;
	}

	public Caisse getCaisse() {
		return caisse;
	}

	public void setCaisse(Caisse caisse) {
		this.caisse = caisse;
	}

	public ServiceCreditanterieur getServiceCreditanterieur() {
		return serviceCreditanterieur;
	}

	public void setServiceCreditanterieur(ServiceCreditanterieur serviceCreditanterieur) {
		this.serviceCreditanterieur = serviceCreditanterieur;
	}

	public ServiceCreditclient getServiceCreditclient() {
		return serviceCreditclient;
	}

	public void setServiceCreditclient(ServiceCreditclient serviceCreditclient) {
		this.serviceCreditclient = serviceCreditclient;
	}

	public ServiceAchatcaisse getServiceAchatcaisse() {
		return serviceAchatcaisse;
	}

	public void setServiceAchatcaisse(ServiceAchatcaisse serviceAchatcaisse) {
		this.serviceAchatcaisse = serviceAchatcaisse;
	}

	public List<Cheque> getListcheque() {
		return listcheque;
	}

	public void setListcheque(List<Cheque> listcheque) {
		this.listcheque = listcheque;
	}

	public List<Credit> getListCreditclient() {
		return listCreditclient;
	}

	public void setListCreditclient(List<Credit> listCreditclient) {
		this.listCreditclient = listCreditclient;
	}

	public List<Creditanterieur> getListCreditanterieur() {
		return listCreditanterieur;
	}

	public void setListCreditanterieur(List<Creditanterieur> listCreditanterieur) {
		this.listCreditanterieur = listCreditanterieur;
	}

	public List<Achatcaisse> getListachatcaisse() {
		return listachatcaisse;
	}

	public void setListachatcaisse(List<Achatcaisse> listachatcaisse) {
		this.listachatcaisse = listachatcaisse;
	}

	public ServicePompe getServicePompe() {
		return servicePompe;
	}

	public void setServicePompe(ServicePompe servicePompe) {
		this.servicePompe = servicePompe;
	}

	public ServiceAchatcarburant getServiceAchat() {
		return serviceAchat;
	}

	public void setServiceAchat(ServiceAchatcarburant serviceAchat) {
		this.serviceAchat = serviceAchat;
	}

	public ServiceLigneAlimentationcar getServiceLigneAlimentation() {
		return serviceLigneAlimentation;
	}

	public void setServiceLigneAlimentation(ServiceLigneAlimentationcar serviceLigneAlimentation) {
		this.serviceLigneAlimentation = serviceLigneAlimentation;
	}

	public ServiceDepensegestat getServiceDepense() {
		return serviceDepense;
	}

	public void setServiceDepense(ServiceDepensegestat serviceDepense) {
		this.serviceDepense = serviceDepense;
	}

	public ServiceAvancegestat getServiceAvance() {
		return serviceAvance;
	}

	public void setServiceAvance(ServiceAvancegestat serviceAvance) {
		this.serviceAvance = serviceAvance;
	}

	public ServiceClientgestat getServiceClient() {
		return serviceClient;
	}

	public void setServiceClient(ServiceClientgestat serviceClient) {
		this.serviceClient = serviceClient;
	}

	public List<Depensegestat> getListdepense() {
		return listdepense;
	}

	public void setListdepense(List<Depensegestat> listdepense) {
		this.listdepense = listdepense;
	}

	public List<Avancegestat> getListAvances() {
		return listAvances;
	}

	public void setListAvances(List<Avancegestat> listAvances) {
		this.listAvances = listAvances;
	}

	public List<Clientgestat> getListclients() {
		return listclients;
	}

	public void setListclients(List<Clientgestat> listclients) {
		this.listclients = listclients;
	}

	public   Poste getPoste() {
		return poste;
	}

	public void setPoste( Poste poste) {
		this.poste = poste;
	}

	public Poste[] getPostes() {
		return postes;
	}

	public void setPostes(Poste[] postes) {
		this.postes = postes;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public List<Retourcuve> getListretour() {
		return listretour;
	}

	public void setListretour(List<Retourcuve> listretour) {
		this.listretour = listretour;
	}

	public ServiceRetourcuve getServiceRetourcuve() {
		return serviceRetourcuve;
	}

	public void setServiceRetourcuve(ServiceRetourcuve serviceRetourcuve) {
		this.serviceRetourcuve = serviceRetourcuve;
	}

	public String getTotaldepense() {
		return totaldepense;
	}

	public void setTotaldepense(String totaldepense) {
		this.totaldepense = totaldepense;
	}

	public String getTotalcredit() {
		return totalcredit;
	}

	public void setTotalcredit(String totalcredit) {
		this.totalcredit = totalcredit;
	}

	public String getTotalachat() {
		return totalachat;
	}

	public void setTotalachat(String totalachat) {
		this.totalachat = totalachat;
	}

	public String getTotalcheque() {
		return totalcheque;
	}

	public void setTotalcheque(String totalcheque) {
		this.totalcheque = totalcheque;
	}

	public String getTotalretour() {
		return totalretour;
	}

	public void setTotalretour(String totalretour) {
		this.totalretour = totalretour;
	}

	public String getTotalaccompte() {
		return totalaccompte;
	}

	public void setTotalaccompte(String totalaccompte) {
		this.totalaccompte = totalaccompte;
	}

	public String getTotalstock() {
		return totalstock;
	}

	public void setTotalstock(String totalstock) {
		this.totalstock = totalstock;
	}

	public String getTotalcreditant() {
		return totalcreditant;
	}

	public void setTotalcreditant(String totalcreditant) {
		this.totalcreditant = totalcreditant;
	}

	public List<Cumulcarburant> getListcumul() {
		return listcumul;
	}

	public void setListcumul(List<Cumulcarburant> listcumul) {
		this.listcumul = listcumul;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ServiceLignevente getServiceLignevente() {
		return serviceLignevente;
	}

	public void setServiceLignevente(ServiceLignevente serviceLignevente) {
		this.serviceLignevente = serviceLignevente;
	}

	public ServiceProduit getServiceproduit() {
		return serviceproduit;
	}

	public void setServiceproduit(ServiceProduit serviceproduit) {
		this.serviceproduit = serviceproduit;
	}

	public List<Lignevente> getListelignevente() {
		return listelignevente;
	}

	public void setListelignevente(List<Lignevente> listelignevente) {
		this.listelignevente = listelignevente;
	}

	public List<Famillearticle> getListfamile() {
		return listfamile;
	}

	public void setListfamile(List<Famillearticle> listfamile) {
		this.listfamile = listfamile;
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

	public List<Rendement> getListrendementlav() {
		return listrendementlav;
	}

	public void setListrendementlav(List<Rendement> listrendementlav) {
		this.listrendementlav = listrendementlav;
	}

	public List<Rendement> getListrendementvid() {
		return listrendementvid;
	}

	public void setListrendementvid(List<Rendement> listrendementvid) {
		this.listrendementvid = listrendementvid;
	}

	public List<Employee> getListemployeelav() {
		return listemployeelav;
	}

	public void setListemployeelav(List<Employee> listemployeelav) {
		this.listemployeelav = listemployeelav;
	}

	public List<Employee> getListemployeevid() {
		return listemployeevid;
	}

	public void setListemployeevid(List<Employee> listemployeevid) {
		this.listemployeevid = listemployeevid;
	}

	public ServiceEmployee getServiceEmployee() {
		return serviceEmployee;
	}

	public void setServiceEmployee(ServiceEmployee serviceEmployee) {
		this.serviceEmployee = serviceEmployee;
	}

	public ServiceRendement getServicerendement() {
		return servicerendement;
	}

	public void setServicerendement(ServiceRendement servicerendement) {
		this.servicerendement = servicerendement;
	}

	public String getTotalLavage() {
		return totalLavage;
	}

	public void setTotalLavage(String totalLavage) {
		this.totalLavage = totalLavage;
	}

	public String getTotalVidane() {
		return totalVidane;
	}

	public void setTotalVidane(String totalVidane) {
		this.totalVidane = totalVidane;
	}

	public String getTotalSLavage() {
		return totalSLavage;
	}

	public void setTotalSLavage(String totalSLavage) {
		this.totalSLavage = totalSLavage;
	}

	public Double getTotalVLavage() {
		return totalVLavage;
	}

	public void setTotalVLavage(Double totalVLavage) {
		this.totalVLavage = totalVLavage;
	}

	public Double getTotalSemilavage() {
		return totalSemilavage;
	}

	public void setTotalSemilavage(Double totalSemilavage) {
		this.totalSemilavage = totalSemilavage;
	}

	public Double getTotalSVidane() {
		return totalSVidane;
	}

	public void setTotalSVidane(Double totalSVidane) {
		this.totalSVidane = totalSVidane;
	}

	public String getTotalsv() {
		return totalsv;
	}

	public void setTotalsv(String totalsv) {
		this.totalsv = totalsv;
	}

	public String getTotalPoste1() {
		return totalPoste1;
	}

	public void setTotalPoste1(String totalPoste1) {
		this.totalPoste1 = totalPoste1;
	}

	public String getTotalPoste2() {
		return totalPoste2;
	}

	public void setTotalPoste2(String totalPoste2) {
		this.totalPoste2 = totalPoste2;
	}

	public void setTotalVLavage(double totalVLavage) {
		this.totalVLavage = totalVLavage;
	}

	public void setTotalSemilavage(double totalSemilavage) {
		this.totalSemilavage = totalSemilavage;
	}

	public void setTotalSVidane(double totalSVidane) {
		this.totalSVidane = totalSVidane;
	}
	
	

}
