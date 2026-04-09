package com.tn.shell.ui.shop;

import java.text.DecimalFormat;
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

import com.tn.shell.model.gestat.Achatcarburant;
import com.tn.shell.model.gestat.Etatprofil;
import com.tn.shell.model.gestat.Lignealimentationcar;
import com.tn.shell.model.shop.*;
import com.tn.shell.service.shop.*;
 
@ManagedBean(name = "EtatshopBean")
@SessionScoped
public class EtatshopBean {
	
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	
	@ManagedProperty(value = "#{ServiceProduit}")
	ServiceProduit serviceProduit;
	 
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
	@ManagedProperty(value = "#{ServiceLignevente}")
	ServiceLignevente serviceLignevente;
	 
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
    private List<Famillearticle> listFamille;
    private List<String> selectedfamilles;
	private List<Famillearticle> listfamile;
	private List<Lignevente> listelignevente;
	private Poste[] postes;
	private Poste poste;
	private String dates;
	private Date date;
    private List<Fournisseur> listfournissuers;
    private List<Factureachat> listfactureAchat;
    private List<Achat> listAchat;
    private Fournisseur fournisseur;
    private Achat achat;
    private Factureachat factureachat;
    
	public String etatdeprofil() {	
		
		totaCA=("0.000");
		totalProfilBrut=("0.000");
		date1=new Date();
		date2=new Date();
		listProduit=new ArrayList<Produit>();
		return SUCCESS;
	}
	
public String etatdeprofil2() {	
		
		totaCA=("0.000");
		totalProfilBrut=("0.000");
		date1=new Date();
		date2=new Date();
		listProduit=new ArrayList<Produit>();
		return SUCCESS;
	}
	
	public String calculeretatdeprofil() {	
		 
		DecimalFormat df=new DecimalFormat("0.000");
		totaCA=df.format(0);
		totalProfilBrut=df.format(0);totaltva="";
		listFamille = new ArrayList<Famillearticle>();
		listFamille = serviceFamilleaticle.getAll();
		 
		date2.setHours(23);
        double totalca=0; 
			totalca=serviceLignevente.getmontantbetwendate(date1, date2);
		 
		try {
			listfamile = new ArrayList<Famillearticle>();
			listfamile = serviceFamilleaticle.getAll();
			

		 double pbruts=0;
		 double tptalpro=0;
			for (Famillearticle f : listfamile) {				 
				 		double qteparp = 0;
						double mtparp = 0; 
                        double pbrut=0;
                        double porcentca=0;
                        double pro=0;
                        qteparp = serviceLignevente.getQuantiteParFamillebetwendate(date1, date2, f);
						 mtparp = serviceLignevente.getTotalttcParFamillebetwendate(date1, date2, f);
						 pbrut=serviceLignevente.getProfilBrutParFamillebetwendate(date1, date2, f);
					f.setQuantite(qteparp);
					f.setMontant(df.format(mtparp));
                    f.setProfilbrut(df.format(pbrut));
					f.setMontants(mtparp);
					porcentca=mtparp/totalca*100;
					 f.setPourcentCAffTotal(df.format(porcentca));
					pbruts=pbruts+ pbrut;
					pro=pbrut/mtparp*100;
					f.setPourcenPBTotal(df.format(pro));
					tptalpro=tptalpro+pro;
				}
			
			totaCA=df.format(totalca);
			 totalProfil=df.format(pbruts);
               totaltva=df.format(pbruts/totalca*100);
		} catch (Exception e) {
			System.out.println("erreur de chargement vente");
		}
		 
		 
		return SUCCESS;
	}
	
	public String calculeretatdeprofil2() {	
		 
		DecimalFormat df=new DecimalFormat("0.000");
		totaCA=df.format(0);
		totalProfilBrut=df.format(0);totaltva="";
		 
		List<String> ld = new ArrayList<String>();
		date2.setDate(date2.getDate()+1);
        double totalca=0;
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		List<Lignevente> lvente = new ArrayList<Lignevente>();
		lvente=serviceLignevente.getbetwendate(date1, date2);
		for (Lignevente lv : lvente) {
			totalca=totalca+lv.getTotalttc();
		}
		 
		try {
			listfamile = new ArrayList<Famillearticle>();
			listfamile = serviceFamilleaticle.getAll2();
			

		 double pbruts=0;
		 double tptalpro=0;
			for (Famillearticle f : listfamile) {
				 
				 		double qteparp = 0;
						double mtparp = 0; 
                        double pbrut=0;
                        double porcentca=0;
                        double pro=0;
						for (Lignevente lv : lvente) {
							if (  lv.getProduit().getFamille().getCode()==f.getCode()){
								qteparp = qteparp + lv.getQuantite();
								mtparp = mtparp + lv.getTotalttc();
								double prixachatttc=lv.getProduit().getAchat()+(lv.getProduit().getAchat()*lv.getProduit().getTva()/100);
								pbrut=pbrut+(lv.getQuantite()*lv.getProduit().getVente()-lv.getQuantite()*prixachatttc);
						}
						 
					}
					f.setQuantite(qteparp);
					f.setMontant(df.format(mtparp));
                    f.setProfilbrut(df.format(pbrut));
					f.setMontants(mtparp);
					porcentca=mtparp/totalca*100;
					 f.setPourcentCAffTotal(df.format(porcentca));
					pbruts=pbruts+ pbrut;
					pro=pbrut/mtparp*100;
					f.setPourcenPBTotal(df.format(pro));
					tptalpro=tptalpro+pro;
				}
			
			totaCA=df.format(totalca);
			 totalProfil=df.format(pbruts);
               totaltva=df.format(pbruts/totalca*100);
		} catch (Exception e) {
			System.out.println("erreur de chargement vente");
		}
		 
		
		return SUCCESS;
	}
	
	private Integer codes;
	public void handleChange1(ValueChangeEvent event) {
		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
		System.out.println("\n\n codes" + codes + "\n\n");
		Famillearticle e = listfamile.get(codes);
		DecimalFormat dfs = new DecimalFormat("0");
		DecimalFormat df = new DecimalFormat("0.000");		  
		 e.setMontants((Double) event.getNewValue());  
		 e.setMontant(df.format(e.getMontants()));
		 listfamile.set(codes, e);
	}
	
	
	public void handleChange2(ValueChangeEvent event) {
		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
		System.out.println("\n\n codes" + codes + "\n\n");
		Famillearticle e = listfamile.get(codes);
		DecimalFormat dfs = new DecimalFormat("0");
		DecimalFormat df = new DecimalFormat("0.000");		  
		 e.setProfilbruts((Double) event.getNewValue());  
		 e.setProfilbrut(df.format(e.getProfilbruts()));
		 listfamile.set(codes, e);
	}
	
	public String validercalculeretatdeprofil2() {
		DecimalFormat df = new DecimalFormat("0.000");		  
		 double cf=0;
		 double tptalpro=0; 
		for(Famillearticle f:listfamile) {
			cf=cf+f.getMontants();
			tptalpro=tptalpro+f.getProfilbruts();
		}
		for(Famillearticle f:listfamile) {		 
			 f.setPourcentCAffTotal(df.format(f.getMontants()/cf*100)); 		 
			 f.setPourcenPBTotals(f.getProfilbruts()/f.getMontants()*100);	
			 f.setPourcenPBTotal(df.format(f.getPourcenPBTotals()));
			 
		}
	
	totaCA=df.format(cf);
	 totalProfil=df.format(tptalpro);
      totaltva=df.format(tptalpro/cf*100);
		return SUCCESS;
	}
	public String articleEnAlerteDeStock() {
		listProduit=new ArrayList<Produit>();
		listProduit=serviceProduit.getAllQtenegatif();
		return SUCCESS;
	}
	
	public String etatFiscal() {
		totaCA=("0.000");
		totalProfilBrut=("0.000");
		date1=new Date();
		date2=new Date();
		listProduit=new ArrayList<Produit>();
		return SUCCESS;
	}
	public String calculeretatfiscal() {
		
		DecimalFormat df=new DecimalFormat("0.000");
		totaCA=df.format(0);
		totalProfilBrut=df.format(0);
		listFamille = new ArrayList<Famillearticle>();
		listFamille = serviceFamilleaticle.getAll();
		
		for(Famillearticle f:listFamille) {
		 	double cf=0;//serviceLignevente.getchiffreAffaire(f);
			 double pb=0;//serviceLignevente.getProfilBrut(f);
			double cfAchat=serviceLigneAlimentation.getchiffreAffaireAchat(f);
			double tvaAchat=serviceLigneAlimentation.gettvaAchat(f);
			 
			  
			f.setChiffre_affaires(df.format(cf));
			f.setProfilbrut(df.format(pb));
			 
			f.setTvaAvoir(df.format(tvaAvoir));
			f.setCaAchat(df.format(cfAchat));
			f.setTvaAchat(df.format(tvaAchat));
			totaCA=df.format(Double.parseDouble(totaCA)+cf);
			totalProfilBrut=df.format(Double.parseDouble(totaCA)+pb);
			//totaltva=df.format(Double.parseDouble();
		}
		
		
		for(Famillearticle f:listFamille) {
			f.setPourcenPBTotal(df.format(Double.parseDouble(f.getChiffre_affaires())/Double.parseDouble(totaCA)));
			f.setPourcenPBTotal(df.format(Double.parseDouble(f.getProfilbrut())/Double.parseDouble(totalProfilBrut)));
		}
		
		
		return SUCCESS;
	}
	
	public String articleNonVendu() {
		listfamile = new ArrayList<Famillearticle>();
		listfamile = serviceFamilleaticle.getAll();
		date1 = new Date();
		date2=new Date();
		 
		listProduit = new ArrayList<Produit>();
		return SUCCESS;
	}
	
	public String getarticleNonVendu() {
		 
		listProduit = new ArrayList<Produit>();
		List<Produit> listProduit1 = new ArrayList<Produit>();
		if (selectedfamilles.get(0).equals("TOUTES LES FAMILLES)"))
			listProduit1 = serviceProduit.getAll();
		
		else
			listProduit1 = serviceProduit.getAllbyfamilles0(selectedfamilles.get(0));
		if(listProduit1!=null)
		for(Produit p:listProduit1) {
			List<Lignevente> lv=new ArrayList<Lignevente>();
			lv=serviceLignevente.getbetwendates(date1, date2, p);
			if(lv==null) {
				listProduit.add(p);
			}
			
		}
		return SUCCESS;
	}
	
	public String articleVendu() {
		listfamile = new ArrayList<Famillearticle>();
		listfamile = serviceFamilleaticle.getAll();
		date1 = new Date();
		date2 = new Date();
		listProduit = new ArrayList<Produit>();
		return SUCCESS;
	}
	private String totalquantite;
	public String getarticleVendu() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates1 = s.format(date1);
		dates2 = s.format(date2);
		DecimalFormat df=new DecimalFormat("0.000");
		DecimalFormat df3=new DecimalFormat("0.00");
		DecimalFormat df2=new DecimalFormat("0");
		date2.setDate(date2.getDate()+1);
		double totalqte=0;double total=0;double totalmarge=0;
		listProduit = new ArrayList<Produit>();
		List<Produit> listProduit1 = new ArrayList<Produit>();
		if (selectedfamilles.get(0).equals("TOUTES LES FAMILLES)"))
			listProduit1 = serviceProduit.getAll();
		
		else
			listProduit1 = serviceProduit.getAllbyfamilles0(selectedfamilles.get(0));
		if(listProduit1!=null)
		for(Produit p:listProduit1) {
			double qte=0;
			double marge=0;
			double pour=0;
			double m=0;
			List<Lignevente> lv=new ArrayList<Lignevente>();
			lv=serviceLignevente.getbetwendates(date1, date2, p);
			if(lv!=null) {
				Double tv = (double) lv.get(0).getTva() / 100 + 1;
				double prixventettc1=lv.get(0).getPrix()+(lv.get(0).getPrix()*lv.get(0).getTva()/100);				 
				double ms  = prixventettc1 / (p.getAchat() * tv);		  
				pour=p.getMarge();
				for(Lignevente v:lv) {
					qte=qte+v.getQuantite();
					m=m+v.getTotalttc();
					double prixachatttc=v.getProduit().getAchat()+(v.getProduit().getAchat()*v.getProduit().getTva()/100);
					double prixventettc=v.getPrix()+(v.getPrix()*v.getTva()/100);
					marge=marge+v.getQuantite()*(prixventettc-prixachatttc);
				}
				 
				p.setQuantitetv(df2.format(qte));
				p.setMontantv(m);
				p.setMargebrut(df.format(marge));
				p.setMontantvs(df.format(m));
				p.setPourcent(df3.format(pour)+"%");
				listProduit.add(p);
				totalqte=totalqte+qte;
				total=total+m;totalmarge=totalmarge+marge;
				
			}
			
			
		}
		totalquantite=df2.format(totalqte);
		totalProfil=df.format(total);
		this. caAchat=df.format(totalmarge);
		date2.setDate(date2.getDate()-1);
		return SUCCESS;
	}
	 
	public String etatVenteParFamille() {
		date = new Date();
		postes = Poste.values();
		listelignevente = new ArrayList<Lignevente>();
		 
		return SUCCESS;
	}
	public String getetatventeparDate(AjaxBehaviorEvent event) {
		listelignevente = new ArrayList<Lignevente>();
		listelignevente = serviceLignevente.getAllventeparposteandDate(dates,poste);
		listfamile=new ArrayList<Famillearticle>();		
		return SUCCESS;
	}
	public String FactureAchatEtAvoir() {
       listfournissuers=new ArrayList<Fournisseur>();
       listfournissuers=serviceFournisseur.getAll();
       listAchat=new ArrayList<Achat>();
       achat=null;fournisseur=null;
	   return SUCCESS;
	 
	}
	
	public void getFacturebyFournisseur(AjaxBehaviorEvent event) {
		listAchat = new ArrayList<Achat>();
		listAchat = serviceAchat.getArticlebyfournisseur(fournisseur);
	}
	
private String totalttcs ;
private List<Lignealimentation> listeLigne2;
	public void getligneFacturebyAchat(AjaxBehaviorEvent event) {
		totalquantite = null;
		totalttcs = null;
		double m = 0;
		
		listeLigne2 = new ArrayList<Lignealimentation>();
		listeLigne2 = serviceLigneAlimentation.getLignebyachat(achat);		
		for (Lignealimentation l : listeLigne2) {
			totalquantite = totalquantite + l.getQuantite();
			l.setMontant(l.getMantantht()+(l.getMantantht()*l.getTva()/100));
			l.setMontants(String.format("%3f",l.getMontant()) );
		}
			
		totalttcs = String.format("%3f", achat.getFactureachat().getTotalttc());
	}

	
	public String  FacturationDesTickets() {
		return SUCCESS;
	}
	public String EtatVentesParVenduers() {
		return SUCCESS;
	}
	public String etatdesArticles(){
		return SUCCESS;
	}
	/*getter
	 * 
	 * and
	 * 
	 * setter*/

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

	public Integer getCodes() {
		return codes;
	}

	public void setCodes(Integer codes) {
		this.codes = codes;
	}

	public String getTotalquantite() {
		return totalquantite;
	}

	public void setTotalquantite(String totalquantite) {
		this.totalquantite = totalquantite;
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

	public ServiceFactureAchat getServiceFactureAchat() {
		return serviceFactureAchat;
	}

	public void setServiceFactureAchat(ServiceFactureAchat serviceFactureAchat) {
		this.serviceFactureAchat = serviceFactureAchat;
	}

	public ServiceLigneAlimentation getServiceLigneAlimentation() {
		return serviceLigneAlimentation;
	}

	public void setServiceLigneAlimentation(ServiceLigneAlimentation serviceLigneAlimentation) {
		this.serviceLigneAlimentation = serviceLigneAlimentation;
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

	public ServiceLignevente getServiceLignevente() {
		return serviceLignevente;
	}

	public void setServiceLignevente(ServiceLignevente serviceLignevente) {
		this.serviceLignevente = serviceLignevente;
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
		dates1=s.format(date1);
		return dates1;
	}

	public void setDates1(String dates1) {
		this.dates1 = dates1;
	}

	public String getDates2() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates2=s.format(date2);
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

	public String getDates() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates=s.format(date);
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

	public List<Fournisseur> getListfournissuers() {
		return listfournissuers;
	}

	public void setListfournissuers(List<Fournisseur> listfournissuers) {
		this.listfournissuers = listfournissuers;
	}

	public List<Factureachat> getListfactureAchat() {
		return listfactureAchat;
	}

	public void setListfactureAchat(List<Factureachat> listfactureAchat) {
		this.listfactureAchat = listfactureAchat;
	}

	public List<Achat> getListAchat() {
		return listAchat;
	}

	public void setListAchat(List<Achat> listAchat) {
		this.listAchat = listAchat;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public Achat getAchat() {
		return achat;
	}

	public void setAchat(Achat achat) {
		this.achat = achat;
	}

	public Factureachat getFactureachat() {
		return factureachat;
	}

	public void setFactureachat(Factureachat factureachat) {
		this.factureachat = factureachat;
	}

	public String getTotalttcs() {
		return totalttcs;
	}

	public void setTotalttcs(String totalttcs) {
		this.totalttcs = totalttcs;
	}

	public List<Lignealimentation> getListeLigne2() {
		return listeLigne2;
	}

	public void setListeLigne2(List<Lignealimentation> listeLigne2) {
		this.listeLigne2 = listeLigne2;
	}

	 
	
}
