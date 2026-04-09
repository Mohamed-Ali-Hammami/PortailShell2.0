package com.tn.shell.ui.transport;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.tn.shell.model.gestat.Utilisateur;
import com.tn.shell.model.transport.Bonlivraison;
import com.tn.shell.model.transport.Chauffeur;
import com.tn.shell.model.transport.Depense;
import com.tn.shell.model.transport.Facture;
import com.tn.shell.model.transport.RapportChauffeur;
import com.tn.shell.model.transport.Tracetransport;
import com.tn.shell.model.transport.Vhecule;
import com.tn.shell.service.gestat.UserService;
import com.tn.shell.service.shop.ServiceProduit;
import com.tn.shell.service.transport.ServiceBonLivraison;
import com.tn.shell.service.transport.ServiceChauffeur;
import com.tn.shell.service.transport.ServiceClient;
import com.tn.shell.service.transport.ServiceDepense;
import com.tn.shell.service.transport.ServiceFacture;
import com.tn.shell.service.transport.ServiceLigneCommande;
import com.tn.shell.service.transport.ServiceTracetransport;
import com.tn.shell.service.transport.ServiceVhecule;

@ManagedBean(name = "RapportBean")
@SessionScoped
public class RapportBean {

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
	private Date date2;
	private String date1s;
	private String date2s;
	private List<RapportChauffeur> listchauffeurs;
    private List<Chauffeur> listchauffeur;
    private List<Depense> listdepense;
    private Integer size; 
    private String totalautredep;
    private String totaldepcar;
    private String totalsal;
    private String totaldep;
    private String totaltransport;
    private String totaldf;
	public String rendementchauffeur() {
		date1 = new Date();
		date2 = new Date();
		listchauffeurs = new ArrayList<RapportChauffeur>();
		mois = null;
		annee = null;
		 /*List<Bonlivraison>	ligneindex = new ArrayList<Bonlivraison>();
		ligneindex = servicebonLivraison.getAll();
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		for(Bonlivraison b:ligneindex) {
			b.setDates(sf.format(b.getDate()));
			servicebonLivraison.update(b);
		} */
		return SUCCESS;
	}
	
	public String totaldepense() {
		date1 = new Date();
		date2 = new Date();
		 
		mois = null;
		annee = null;		 
		return SUCCESS;
	}
	
	public String gettotaldepense() { 
		//try {
		double totalautredep=0;
		double totaldepcar=0;
		double totalsal=0;
		double totaldep=0;
		double totaltransport=0;
		double totaldf=0;
		mois=getMoisbyIntger(date1.getMonth()+1);
		Integer moi=date1.getMonth()+1;
		DecimalFormat df=new DecimalFormat("0.000");
		DecimalFormat dfs=new DecimalFormat("0");
		annee=date1.getYear()+1900;
	//	date2.setDate(date2.getDate()+1);
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		List<Depense> ld=new ArrayList<Depense>();
		  listdepense=new ArrayList<Depense>();
		ld=serviceDepense.getdepensebetweendate(date1, date2);
		List<Vhecule> lv=new ArrayList<Vhecule>();
		lv=serviceVhecule.getAll();
		for(Vhecule v:lv) {
			if(v.getMatricule().contains("139")) {
				v.setChauf("MOHAMED");
				v.setSalchauf(550.000);
				totalsal=totalsal+v.getSalchauf();
			}
			else if(v.getMatricule().contains("122")) {
				v.setChauf("MONDHER");
				v.setSalchauf(550.000);
				totalsal=totalsal+v.getSalchauf();
			}
			
			else if(v.getMatricule().contains("121")) {
				v.setChauf("SOFIENNE");
				v.setSalchauf(550.000);
				totalsal=totalsal+v.getSalchauf();
			}
			
			else if(v.getMatricule().contains("115")) {
				v.setChauf("ZOHAIR");
				v.setSalchauf(550.000);
				totalsal=totalsal+v.getSalchauf();
			}
			else {
				v.setChauf(" ");
				v.setSalchauf(0);
				totalsal=totalsal+v.getSalchauf();
			}
			Depense dep=new Depense();
			double m=0;
			double tdep=0;
			Double ttr=(double) 0;
			double autre=0;
		for(Depense d:ld) {
			if(d.getVhecule().getId()==v.getId()) {
			if(d.getFamilledepense().getId()==1 ) 
				autre=autre+d.getMontant();			 
			else m=m+d.getMontant();
		}
		}
		tdep=m+autre;
		ttr=servicebonLivraison.sumdtransportv(date1, date2, v.getId());
		dep.setMontant(m);
		//dep.setMontants(df.format(m));
		if(autre>0)
		dep.setMontantsautre(df.format(autre));
		else 
			dep.setMontantsautre(null);
		dep.setVhecule(v);
		if(ttr>0)
		dep.setTotaltrans(df.format(ttr));
		else 
			dep.setTotaltrans(null);
		if(tdep>0)
		dep.setTotaldep(df.format(tdep));
		else 
			dep.setTotaldep(null);
		if((ttr-tdep)>0)
		dep.setDf(df.format(ttr-tdep));
		else 
			dep.setDf(null);
		totaldepcar=totaldepcar+m;
		totalautredep=totalautredep+autre;
		totaldep=totaldepcar+totalautredep;
		totaltransport=totaltransport+ttr;
		if(dep.getVhecule().getSalchauf()==0) {		
		
		}
		totaldf=totaltransport-totaldep;
		if(tdep>0 || ttr>0)
		listdepense.add(dep);
		}
		
		this.totalautredep=df.format(totalautredep);
		this.totaldep=df.format(totaldep);
		this.totaltransport=df.format(totaltransport);
		this.totalsal=df.format(totalsal);
		this.totaldepcar=df.format(totaldepcar);
		this.totaldf=df.format(totaldf);
		/*}catch(Exception e) {
			e.printStackTrace();
		}*/
		
		Utilisateur user=userService.getCurrentUser();
	       Tracetransport t=new Tracetransport();
	       t.setAction(user.getNom() +"a visualiser la rapport des depenses"+" a "+new Date());
	       t.setDate(new Date());
	        serviceTrace.save(t);
		return SUCCESS;
	}
	
	public String Ventecarburant() {
		date1 = new Date();
		date2 = new Date();
		listchauffeurs = new ArrayList<RapportChauffeur>();
		mois = null;
		annee = null;
		 List<Bonlivraison>	ligneindex = new ArrayList<Bonlivraison>();
		ligneindex = servicebonLivraison.getAll();
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		for(Bonlivraison b:ligneindex) {
			b.setDates(sf.format(b.getDate()));
			servicebonLivraison.update(b);
		} 
		return SUCCESS;
	}

	private Integer totalpjpurs;
	private String mois;
	private Integer annee;

	public String getrendementchauffeur() {
		List<String> ld = new ArrayList<String>();
		mois=getMoisbyIntger(date1.getMonth()+1);
		annee=date1.getYear()+1900;
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
		List<Chauffeur>  listf = new ArrayList<Chauffeur>();
		  listchauffeur = new ArrayList<Chauffeur>();
		  listf = serviceChauffeur.getAll();
		
		double totalmarges = 0;
		try {
		if (ld.size() > 0)
			
				for (Chauffeur a : listf) {
					listchauffeurs = new ArrayList<RapportChauffeur>();					
					Integer tparmois=0;
					int i=1;
					for (String s : ld) {
						RapportChauffeur e = new RapportChauffeur();						
						Integer qte=0;
						 List<Bonlivraison>	ligneindex = new ArrayList<Bonlivraison>();
						ligneindex = servicebonLivraison.getAllventepardatearticle(a,s);
						if(ligneindex!=null  )
							  qte=ligneindex.size();
						tparmois=tparmois+qte;						 
						e.setTotalnbvoyage(qte);
						e.setDates(s);
						listchauffeurs.add(e);
						i=i+1;
					}
				if(tparmois>0  ) {
				a.setRendement(listchauffeurs);	 
				a.setNbvoyage(tparmois);
				listchauffeur.add(a);
				}
			}
		int i=0;
		totalpjpurs=0;
		for(String s:ld) {
			Integer tpatj=0;
			
		for(Chauffeur a:listchauffeur) {
		for(RapportChauffeur r:a.getRendement()) {			
				if(r.getDates().equals(s)) 
					tpatj=tpatj+r.getTotalnbvoyage();
			 
			}	
		a.getRendement().get(i).setNbtotalnbvoyage(tpatj);
		
		}
		totalpjpurs=totalpjpurs+tpatj;
		i=i+1;
		}
		
		size=listchauffeur.get(listchauffeur.size()-1).getId();
		}catch(Exception e) {
				System.out.println("ereur dans ligne index");
			}
		
		Utilisateur user=userService.getCurrentUser();
	       Tracetransport t=new Tracetransport();
	       t.setAction(user.getNom() +"a visualiser la rapport rendement des chaufeurs"+" a "+new Date());
	       t.setDate(new Date());
	        serviceTrace.save(t);
		return SUCCESS;
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
			m = "Séptembre";
		else if (moi == 10)
			m = "Octobre";
		else if (moi == 11)
			m = "Novembre";
		else if (moi == 12)
			m = "Décembre";
		return m;
	}

	/*
	 * private Integer verifiervoyage(Produit libelle, Integer i) {
	 * 
	 * for (int j = 0; j < listproduit.size(); j++) { if
	 * (libelle.getCode().equals(listproduit.get(j).getCode()) && j != i) {
	 * System.out.println("j" + j); return j;
	 * 
	 * } } return i; }
	 */
	public List<Facture> getFactureventesbypayement() {
		return factureventesbypayement;
	}

	public void setFactureventesbypayement(List<Facture> factureventesbypayement) {
		this.factureventesbypayement = factureventesbypayement;
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

	public String getDate1s() {
		return date1s;
	}

	public void setDate1s(String date1s) {
		this.date1s = date1s;
	}

	public String getDate2s() {
		return date2s;
	}

	public void setDate2s(String date2s) {
		this.date2s = date2s;
	}

	public List<RapportChauffeur> getListchauffeurs() {
		return listchauffeurs;
	}

	public void setListchauffeurs(List<RapportChauffeur> listchauffeurs) {
		this.listchauffeurs = listchauffeurs;
	}

	public Integer getTotalpjpurs() {
		return totalpjpurs;
	}

	public void setTotalpjpurs(Integer totalpjpurs) {
		this.totalpjpurs = totalpjpurs;
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

	public List<Chauffeur> getListchauffeur() {
		return listchauffeur;
	}

	public void setListchauffeur(List<Chauffeur> listchauffeur) {
		this.listchauffeur = listchauffeur;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public List<Depense> getListdepense() {
		return listdepense;
	}

	public void setListdepense(List<Depense> listdepense) {
		this.listdepense = listdepense;
	}

	public String getTotalautredep() {
		return totalautredep;
	}

	public void setTotalautredep(String totalautredep) {
		this.totalautredep = totalautredep;
	}

	public String getTotaldepcar() {
		return totaldepcar;
	}

	public void setTotaldepcar(String totaldepcar) {
		this.totaldepcar = totaldepcar;
	}

	public String getTotalsal() {
		return totalsal;
	}

	public void setTotalsal(String totalsal) {
		this.totalsal = totalsal;
	}

	public String getTotaldep() {
		return totaldep;
	}

	public void setTotaldep(String totaldep) {
		this.totaldep = totaldep;
	}

	public String getTotaltransport() {
		return totaltransport;
	}

	public void setTotaltransport(String totaltransport) {
		this.totaltransport = totaltransport;
	}

	public String getTotaldf() {
		return totaldf;
	}

	public void setTotaldf(String totaldf) {
		this.totaldf = totaldf;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ServiceTracetransport getServiceTrace() {
		return serviceTrace;
	}

	public void setServiceTrace(ServiceTracetransport serviceTrace) {
		this.serviceTrace = serviceTrace;
	}
	
	

}
