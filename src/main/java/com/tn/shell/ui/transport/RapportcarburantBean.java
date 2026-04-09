package com.tn.shell.ui.transport;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.BubbleChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.LinearAxis;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.OhlcChartModel;
import org.primefaces.model.chart.OhlcChartSeries;
import org.primefaces.model.chart.PieChartModel;

import com.tn.shell.model.gestat.Utilisateur;
import com.tn.shell.model.paie.Employee;
import com.tn.shell.model.paie.Paie;
import com.tn.shell.model.shop.Produit;
import com.tn.shell.model.transport.Bonlivraison;
import com.tn.shell.model.transport.Chauffeur;
import com.tn.shell.model.transport.Depense;
import com.tn.shell.model.transport.Facture;
import com.tn.shell.model.transport.Lignecommande;
import com.tn.shell.model.transport.RapportCarburant;
import com.tn.shell.model.transport.RapportChauffeur;
import com.tn.shell.model.transport.Rapportcamion;
import com.tn.shell.model.transport.Tracetransport;
import com.tn.shell.service.gestat.UserService;
import com.tn.shell.service.paie.ServiceEmployee;
import com.tn.shell.service.paie.ServicePaie;
import com.tn.shell.service.shop.ServiceLigneAlimentation;
import com.tn.shell.service.shop.ServiceProduit;
import com.tn.shell.service.transport.ServiceBonLivraison;
import com.tn.shell.service.transport.ServiceChauffeur;
import com.tn.shell.service.transport.ServiceClient;
import com.tn.shell.service.transport.ServiceDepense;
import com.tn.shell.service.transport.ServiceFacture;
import com.tn.shell.service.transport.ServiceLigneCommande;
import com.tn.shell.service.transport.ServiceTracetransport;
import com.tn.shell.service.transport.ServiceVhecule;

@ManagedBean(name = "RapportcarburantBean")
@SessionScoped
public class RapportcarburantBean implements Serializable { 

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private List<Facture> factureventesbypayement;
	// @ManagedProperty(value = "#{ServiceSociete}")
	// ServiceSociete serviceSociete;

	@ManagedProperty(value = "#{ServiceLigneCommande}")
	ServiceLigneCommande servicelignecommande;
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
	@ManagedProperty(value = "#{ServiceEmployee}")
	ServiceEmployee serviceEmployee;
	@ManagedProperty(value = "#{ServicePaie}")
	ServicePaie servicePaie;
	@ManagedProperty(value = "#{UserService}")
	UserService userService;
	@ManagedProperty(value = "#{ServiceTracetransport}")
	ServiceTracetransport serviceTrace;
	private Date date1;
	private Date date2;
	
	private Date date3;
	private Date date4;
	private String date1s;
	private String date2s;
	private List<RapportCarburant> listproduits;
	private List<Produit> listproduit;
	private Integer size;
	private double totalmarge;

	public String ventecarburant() {
		date1 = new Date();
		date2 = new Date();
		listproduits = new ArrayList<RapportCarburant>();
		mois = getMoisbyIntger(date1.getMonth()+1);
		annee = date1.getYear()+1900;

		 
		/*    List<Lignecommande>ligneindex = servicelignecommande.getAll();
		   SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy"); for(Lignecommande
		  b:ligneindex) {
		 
		  b.setDate(b.getBl().getDate()); servicelignecommande.update(b);}*/
		 

		return SUCCESS;
	}

	private double totalpjpurs;
	private String mois;
	private Integer annee;
	private String totaltransport;
	private String totaldepense;

	public String getrendementcarburant() {
		  double totaltransport=0; double totaldepense=0;
		totaltransport=0;totaldepense=0;
		List<String> ld = new ArrayList<String>();
		mois=getMoisbyIntger(date1.getMonth()+1);
		Integer moi=date1.getMonth()+1;
		DecimalFormat df=new DecimalFormat("0.000");
		DecimalFormat dfs=new DecimalFormat("0");
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
		 
		  Produit p1=serviceProduit.Findbycode(126);
		  Produit p2=serviceProduit.Findbycode(684);
		  Produit p3=serviceProduit.Findbycode(31);
		 
	 
		 
		if (ld.size() > 0)			
			
					listproduits = new ArrayList<RapportCarburant>();					
					 double ttr=0;double trectte=0;double tdepense=0;double tmarge=0;
					 double tqteg=0;double tqteg50=0;double tqtep=0;
					 double b=0;
					for (String s : ld) {
						RapportCarburant e= new RapportCarburant();	
						e.setDates(s);
						double recette=0;double marge=0;
						double depense=0;double tr=0;
						double qteg=0;	double qteg50=0;	double qtep=0;
						 List<Lignecommande>	ligneindex = new ArrayList<Lignecommande>();
						ligneindex = servicelignecommande.getAllventepardate(s);
						if(ligneindex!=null  ) {
						 
								
							 for(Lignecommande l:ligneindex) {
								 if(l.getProduit()!=null ) {
									 if(l.getProduit().getId()==126) {
										 qteg=qteg+l.getQuantite();
									 }
									 else  if(l.getProduit().getId()==684 ||l.getProduit().getId()==127 ) {
										 qteg50=qteg50+l.getQuantite();
									 }
									 else if(l.getProduit().getId()==31){
										 qtep=qtep+l.getQuantite();
									 }
							 }
								 else if(l.getTransport()!=0) {
									 tr=tr+l.getTransport();
								 }
							 }
							 
							 
						List<Depense> listd=new ArrayList<Depense>();
						listd=serviceDepense.getdepensebydates(s);
						if(listd!=null) {
							for(Depense d:listd)
								depense=depense+d.getMontant();
						}
						  
						  
					 e.setQuantiteg(dfs.format(qteg));
					 e.setQuantiteg50(dfs.format(qteg50));
					 e.setQuantitepetrole(dfs.format(qtep));
						 e.setTotaltransport(df.format(tr));
						 e.setTotaldepense(df.format(depense));
						   recette=qteg*p1.getVente()+qteg50*p2.getVente()+qtep*p3.getVente();
						 e.setTotalrecette(df.format(recette));
						   marge=(qteg*p1.getVente()-qteg*p1.getAchat())+(qteg50*p2.getVente()-qteg50*p2.getAchat())+(qtep*p3.getVente()-qtep*p3.getAchat());
					    e.setMarge(df.format(marge));
					    e.setBenifice(df.format(marge+tr-depense));
						 listproduits.add(e);
						 
					}
						tqteg=tqteg+qteg;
						tqteg50=tqteg50+qteg50;
						tqtep=tqtep+qtep;
						ttr=ttr+tr;
						tdepense=tdepense+depense;
						trectte=trectte+recette;
						tmarge=tmarge+marge;
						
					}
				 
					RapportCarburant e= new RapportCarburant();					
					e.setQuantiteg(dfs.format(tqteg));
					e.setQuantiteg50(dfs.format(tqteg50));
					e.setQuantitepetrole(dfs.format(tqtep));
					e.setTotaltransport(df.format(ttr));
					e.setTotaldepense(df.format(tdepense));
					e.setTotalrecette(df.format(trectte));
					e.setMarge(df.format(tmarge));
					
					e.setDates("Total");
					
					 List<Employee> le=serviceEmployee.getEmployeeByFonction("chauffeur");
					 if(le.size()>0)
					 for(Employee ee:le) {
						 Paie p=servicePaie.getPaieByAnneeAndMoisEmployee(ee, annee, moi);
						 if(p!=null)
						 totaltransport=totaltransport+p.getFormulaire_Paie().getSalaire_net();
					 }
					 this.totaltransport=df.format(totaltransport) ;
					 b=(tmarge+ttr)- tdepense;
					 e.setBenifice(df.format(b));
					totaldepense=b-totaltransport;
					this.totaldepense=df.format(totaldepense);
					 listproduits.add(e);
					 
					 Utilisateur user=userService.getCurrentUser();
				       Tracetransport t=new Tracetransport();
				       t.setAction(user.getNom() +"a visualiser le rapport de vente carburant"+" a "+new Date());
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

	public ServiceLigneCommande getServicelignecommande() {
		return servicelignecommande;
	}

	public void setServicelignecommande(ServiceLigneCommande servicelignecommande) {
		this.servicelignecommande = servicelignecommande;
	}

	public List<RapportCarburant> getListproduits() {
		return listproduits;
	}

	public void setListproduits(List<RapportCarburant> listproduits) {
		this.listproduits = listproduits;
	}

	public List<Produit> getListproduit() {
		return listproduit;
	}

	public void setListproduit(List<Produit> listproduit) {
		this.listproduit = listproduit;
	}

	public double getTotalpjpurs() {
		return totalpjpurs;
	}

	public void setTotalpjpurs(double totalpjpurs) {
		this.totalpjpurs = totalpjpurs;
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

	public String getMois() {
		return mois;
	}

	public void setMois(String mois) {
		this.mois = mois;
	}

	public Integer getAnnee() {
		return annee;
	}

	public String getTotaltransport() {
		return totaltransport;
	}

	public void setTotaltransport(String totaltransport) {
		this.totaltransport = totaltransport;
	}

	public String getTotaldepense() {
		return totaldepense;
	}

	public void setTotaldepense(String totaldepense) {
		this.totaldepense = totaldepense;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public double getTotalmarge() {
		return totalmarge;
	}

	public void setTotalmarge(double totalmarge) {
		this.totalmarge = totalmarge;
	}

	public ServiceEmployee getServiceEmployee() {
		return serviceEmployee;
	}

	public void setServiceEmployee(ServiceEmployee serviceEmployee) {
		this.serviceEmployee = serviceEmployee;
	}

	public ServicePaie getServicePaie() {
		return servicePaie;
	}

	public void setServicePaie(ServicePaie servicePaie) {
		this.servicePaie = servicePaie;
	}
 
	    private BarChartModel barModel;
	    
	 
	   
	    
	     
	    public void itemSelect(ItemSelectEvent event) {
	        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
	                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
	 
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	 
	     
	 
	    public BarChartModel getBarModel() {
	        return barModel;
	    }
	  
	    @PostConstruct
	    public void init() {
	    	//createBarModel();
	    }
	    private BarChartModel initBarModel() {
	        BarChartModel model = new BarChartModel();
	        int m=date1.getMonth()+1;
	        int m0=0;
	        int annee=date1.getYear()+1900;
	        int annee0=0;
	        if(m==1) {
	        	m0=12;
	        annee0=annee-1;
	        }
	         
	        else { m0=m-1;
	        annee0=annee;
	        
	        }
	        date3=new Date();
	        date4 =new Date();
	        date3.setDate(date1.getDate());
	        date3.setMonth(m0);
	        date3.setYear(annee0);
	        date4.setDate(date2.getDate());
	        date4.setMonth(m0);
	        date4.setYear(annee0);
	        ChartSeries dep = new ChartSeries();
	        dep.setLabel("Depense");
	       /* dep.set("Decembre", serviceDepense.sumdepense(date3, date4));
	        dep.set("Janvier", serviceDepense.sumdepense(date1, date2));
	        */
	        
	        dep.set("Decembre", 1500);
	        dep.set("Janvier", 200);
	 
	        ChartSeries trs = new ChartSeries();
	        trs.setLabel("Transport");
	        /*trs.set("Decembre", servicelignecommande.sumdtransport(date3, date4));
	        trs.set("Janvier",  servicelignecommande.sumdtransport(date1, date2));*/
	       
	        trs.set("Decembre", 500);
	        trs.set("Janvier",  600);
	        model.addSeries(dep);
	        model.addSeries(trs);
	 
	        return model;
	    }
	 
	    private void createBarModels() {
	        createBarModel();
	        
	    }
	 
	    private void createBarModel() {
	        barModel = initBarModel();
	 
	        barModel.setTitle("RAPPORT TANSPORT/DEPENSE" +mois+annee);
	        barModel.setLegendPosition("ne");
	 
	        Axis xAxis = barModel.getAxis(AxisType.X);
	        xAxis.setLabel("Mois");
	 
	        Axis yAxis = barModel.getAxis(AxisType.Y);
	        yAxis.setLabel(" ");
	        yAxis.setMin(0);
	        yAxis.setMax(4);
	    }

		public Date getDate3() {
			return date3;
		}

		public void setDate3(Date date3) {
			this.date3 = date3;
		}

		public Date getDate4() {
			return date4;
		}

		public void setDate4(Date date4) {
			this.date4 = date4;
		}

		public void setBarModel(BarChartModel barModel) {
			this.barModel = barModel;
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
