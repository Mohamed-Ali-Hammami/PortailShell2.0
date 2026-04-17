package com.tn.shell.ui.shop;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.joda.time.LocalDate;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.tn.shell.model.gestat.Factureclient;
import com.tn.shell.model.lavage.Rapport;
import com.tn.shell.model.lavage.TypeLavage;
import com.tn.shell.model.paie.Employee;
import com.tn.shell.model.paie.Pointage;
import com.tn.shell.model.shop.Poste;
import com.tn.shell.model.shop.Rendement;
import com.tn.shell.service.gestat.ServiceAvancegestat;
import com.tn.shell.service.gestat.ServiceDepensecheque;
import com.tn.shell.service.gestat.ServiceDepensegestat;
import com.tn.shell.service.paie.ServiceEmployee;
import com.tn.shell.service.paie.ServicePaie;
import com.tn.shell.service.shop.ServiceRendement;

@ManagedBean(name = "RendementBean")
@SessionScoped
public class RendementBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{ServiceEmployee}")
	ServiceEmployee serviceEmployee;
	@ManagedProperty(value = "#{ServiceRendement}")
	ServiceRendement serviceRendement;
	@ManagedProperty(value = "#{ServiceAvancegestat}")
	ServiceAvancegestat serviceavance;
	@ManagedProperty(value = "#{ServicePaie}")
	ServicePaie servicePaie;
	@ManagedProperty(value = "#{ServiceDepensegestat}")
	ServiceDepensegestat  servicedepenseGestat;
	@ManagedProperty(value = "#{ServiceDepensecheque}")
	 ServiceDepensecheque serviceDepenseheaue;
	private String nom;
	private List<Rapport> listRappoert;
	private BarChartModel transportModel;	 
	private Rapport rapport;
	private Employee employee;
	private Date date;
	private List<Employee> listemployee;
	private Poste[] postes;

	private Poste poste;
	private Date date1;
	private Date date2;
	private String dates;
	private String date1s;
	private String lavagvidange;
	private String date2s;
	private double nbvoiture;
	private double nbsemi;
	private List<Rendement> listrendement;
	private List<Rendement> selectedrendement;
	private Integer codes;
	
	@PostConstruct
	public void init() {
		date1 = new Date();
		date2 = new Date();
		  createBarModels();
	       
		 
	}
	
	 private void createBarModels() {
	 createBarModel();
	  } 
	    private void createBarModel() {
	    	rapport=new Rapport();
			 rapport.setBenefices(1000);
			 rapport.setTotalDepenses(500);
	        transportModel = initBarModel();
	        transportModel.setTitle("ETUDE DE RENTABILITEE LAVAGE");
	        transportModel.setLegendPosition("ne");
	        Axis xAxis = transportModel.getAxis(AxisType.X);
	        xAxis.setLabel("Depense+Recette");
	        Axis yAxis = transportModel.getAxis(AxisType.Y);
	        yAxis.setLabel("Montant");
	        yAxis.setMin(0);
	        yAxis.setMax(25000);
	    }
	    private BarChartModel initBarModel() {
	    	 
	        BarChartModel model = new BarChartModel();
	        ChartSeries boys = new ChartSeries();
	    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	        LocalDate  d1=LocalDate.parse(sf.format(date1));
			 LocalDate  d2=LocalDate.parse(sf.format(date2));
	        boys.setLabel("RECETTE");
	        boys.set(getMoisbyIntger(d1.getMonthOfYear()), rapport.getBenefices());
	         
	        ChartSeries girls = new ChartSeries();
	        girls.setLabel("DEPENSE");
	        girls.set(getMoisbyIntger(d1.getMonthOfYear()), rapport.getTotalDepenses());
	      
	        ChartSeries df = new ChartSeries();
	        df.setLabel("DF");
	        df.set(getMoisbyIntger(d1.getMonthOfYear()), rapport.getBenefices()-rapport.getTotalDepenses());
	        model.addSeries(boys);
	        model.addSeries(girls);
	        model.addSeries(df);
	        model.setSeriesColors("149C14, 160A79,C70629");
	        return model;
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
				m = "SÃ©ptembre";
			else if (moi == 10)
				m = "Octobre";
			else if (moi == 11)
				m = "Novembre";
			else if (moi == 12)
				m = "DÃ©cembre";
			return m;
		}
	 
	 public String rapportLavage() {
		 date1=new  Date();
		 date2=new Date();
		 
		 return SUCCESS;
	 }
	public String getrapportLavage() {		
		date1.setHours(0);date1.setMinutes(0);
		date1.setSeconds(0);
		listRappoert=new ArrayList<Rapport>();
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat s2 = new SimpleDateFormat("yyyy-MM-dd");
		date1s=s.format(date1);
		date2s=s.format(date2);
		//Pointage p=servicePointage.getMaxPointage();		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate  d1=LocalDate.parse(sf.format(date1));
		 
		DecimalFormat df = new DecimalFormat("0.000");
		DecimalFormat df2 = new DecimalFormat("0"); 
		double totalv = serviceRendement.getnbvbetwendates(s2.format(date1), s2.format(date2),TypeLavage.Lavage);
		rapport.setTotalvs(totalv);
		rapport.setTotalv(df2.format(totalv));
		 
		double totalrecette = serviceRendement.getRecettebetwendates(s2.format(date1), s2.format(date2),TypeLavage.Lavage);
		rapport.setBenefices(totalrecette);
		rapport.setRecette(df.format(totalrecette));	
		double declarationCnss = servicePaie.getTotalCnss("LAVEUR", d1.getYear(),d1.getMonthOfYear());
		rapport.setDeclarationCnss(df.format(declarationCnss));
		double masseSarial=servicePaie.getTotalPayement("LAVEUR", d1.getYear(), d1.getMonthOfYear());
		rapport.setMasseSalarial(df.format(masseSarial));
		double depLavage=servicedepenseGestat.getsummontantbydateandfamille(date1,date2,3);
		rapport.setDepenseLavage(df.format(depLavage));
		double totalavance=serviceavance.getAvance("LAVEUR", date1, date2);
		double OuvLavage=servicedepenseGestat.getsummontantbydateandfamille(date1,date2,4)+totalavance;
		rapport.setOuvrierlavage(df.format(OuvLavage));		
		double steg=serviceDepenseheaue.getDepenseBylibelle("STEG",date1, date2);
		rapport.setSteg(df.format(steg/3));
		double sonede=serviceDepenseheaue.getDepenseBylibelle("sonede", date1, date2);
		rapport.setSonede(df.format(sonede/3));
		double benefice=totalrecette-(declarationCnss+masseSarial+depLavage+OuvLavage+steg/6+sonede/3);
		rapport.setBenefice(df.format(benefice));
		rapport.setTotalDepense(df.format((declarationCnss+masseSarial+depLavage+OuvLavage+steg/6+sonede/3)));
		rapport.setTotalDepenses((declarationCnss+masseSarial+depLavage+OuvLavage+steg/6+sonede/3));
		listRappoert.add(rapport);
		  transportModel = initBarModel();
	        transportModel.setTitle("ETUDE DE RENTABILITEE LAVAGE");
	        transportModel.setLegendPosition("ne");
	        Axis xAxis = transportModel.getAxis(AxisType.X);
	        xAxis.setLabel("Depense+Recette");
	        Axis yAxis = transportModel.getAxis(AxisType.Y);
	        yAxis.setLabel("Montant");
	        yAxis.setMin(0);
	        yAxis.setMax(25000);
		return SUCCESS;
	}

	public String rapportVidange() {
		date1 = new Date();
		date2 = new Date();
		return SUCCESS;
	}
	
	public String getrapportVidange() {
		rapport=new Rapport();
		listRappoert=new ArrayList<Rapport>();
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat s2 = new SimpleDateFormat("yyyy-MM-dd");
		date1s=s.format(date1);
		date2s=s.format(date2);
		//Pointage p=servicePointage.getMaxPointage();
		DecimalFormat df = new DecimalFormat("0.000");
		DecimalFormat df2 = new DecimalFormat("0");
		double totalv = serviceRendement.getnbvbetwendates(s2.format(date1), s2.format(date2),TypeLavage.Vidange);
		rapport.setTotalv(df2.format(totalv));
		 
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate  d1=LocalDate.parse(sf.format(date1));
		 
		double totalrecette = serviceRendement.getRecettebetwendates(s2.format(date1), s2.format(date2),TypeLavage.Vidange);
		rapport.setRecette(df.format(totalrecette));		 
		double declarationCnss = servicePaie.getTotalCnss("VIDANGEUR", d1.getYear(),d1.getMonthOfYear());
		rapport.setDeclarationCnss(df.format(declarationCnss));
		double masseSarial=servicePaie.getTotalPayement("VIDANGEUR" , d1.getYear(),d1.getMonthOfYear());
		rapport.setMasseSalarial(df.format(masseSarial));
		double depLavage=servicedepenseGestat.getsummontantbydateandfamille(date1,date2,22);
		rapport.setDepenseLavage(df.format(depLavage));
		double totalavance=serviceavance.getAvance("VIDANGEUR",  date1,  date2); 
		double steg=serviceDepenseheaue.getDepenseBylibelle("STEG", date1, date2);
		rapport.setSteg(df.format(steg/6));
	//	double sonede=serviceDepenseheaue.getDepenseBylibelle("sonede",s.format(date1), s.format(date2));
		//rapport.setSonede(df.format(sonede/3));
		
		double benefice=totalrecette-(declarationCnss+masseSarial+depLavage+steg/6);
		rapport.setBenefice(df.format(benefice));
		rapport.setTotalDepense(df.format((declarationCnss+masseSarial+depLavage+steg/6)));
		listRappoert.add(rapport);
		return SUCCESS;
	}


	public String rendementlavage() {
		date = new Date();
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		postes = Poste.values();
		poste = null;
		listrendement = new ArrayList<Rendement>();
		nbsemi = 0;
		nbsemi = 0;
		listemployee = new ArrayList<Employee>();
		listemployee = serviceEmployee.getEmployeeparfonction("LAVEUR");
		for (Employee e : listemployee) {

			Rendement p = new Rendement();
			p.setEmployee(e);
			p.setNbvoiture(0);
			p.setNbsemi(0);
			listrendement.add(p);
		}
		return SUCCESS;

	}

	public void getdates(AjaxBehaviorEvent event) {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
	}

	public void getdates1(AjaxBehaviorEvent event) {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		date1s = s.format(date1);
	}

	public void getdates2(AjaxBehaviorEvent event) {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		date2s = s.format(date2);
	}

	public String rendementvidange() {

		date = new Date();

		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		postes = Poste.values();
		poste = null;
		listrendement = new ArrayList<Rendement>();
		nbsemi = 0;
		nbsemi = 0;
		listemployee = new ArrayList<Employee>();
		listemployee = serviceEmployee.getEmployeeparfonction("VIDANGEUR");
		for (Employee e : listemployee) {

			Rendement p = new Rendement();
			p.setEmployee(e);
			p.setNbvoiture(0);
			p.setNbsemi(0);
			listrendement.add(p);
		}
		return SUCCESS;

	}

	public void updatepontage1(AjaxBehaviorEvent event) {
		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
		Rendement p = listrendement.get(codes);
		listrendement.set(codes, p);
		p.setNbvoiture(nbvoiture);
		nbvoiture = 0;
	}

	public void updatepontage2(AjaxBehaviorEvent event) {
		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
		Rendement p = listrendement.get(codes);
		listrendement.set(codes, p);
		p.setNbsemi(nbsemi);
		nbsemi = 0;
	}

	public String validerrendementlavage() {

		for (Rendement p : listrendement) {
			if (p.getNbvoiture() > 0 || p.getNbsemi() > 0) {
				p.setDate(date);
				p.setPoste(poste);
				p.setDates(dates);
				serviceRendement.save(p);
			}
		}
		rendementlavage();
		return SUCCESS;
	}

	public String validerrendementvidange() {

		for (Rendement p : listrendement) {
			if (p.getNbvoiture() > 0) {
				p.setDate(date);
				p.setPoste(poste);
				p.setDates(dates);
				serviceRendement.save(p);
			}
		}
		rendementvidange();
		return SUCCESS;
	}

	public String etatrendementlavage() {
		listrendement = new ArrayList<Rendement>();
		date1 = new Date();
		date2 = new Date();
		nom = null;
		employee = null;
		listemployee = new ArrayList<Employee>();
		listemployee = serviceEmployee.getEmployeeparfonction("LAVEUR");
		if(listemployee!=null)
		listemployee.addAll(serviceEmployee.getEmployeeparfonction("VIDANGEUR"));
		return SUCCESS;
	}
 
	public String getetatrendementlavage() {		 
		 List<String> ld = new ArrayList<String>();
		DecimalFormat df = new DecimalFormat("0");		 
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		for (int i = date1.getDate(); i <= date2.getDate(); i++) {
			Date d = new Date();
			d.setDate(i);
			d.setMonth(date1.getMonth());
			d.setYear(date1.getYear());
			String ds = sf.format(d);
			ld.add(ds); 
		} 
		employee=serviceEmployee.getEmployeeByNom(nom);	
		
		if(employee==null) { 
			 try {
			listemployee = new ArrayList<Employee>();		 
			if (lavagvidange.equals("Lavage"))
				listemployee = serviceEmployee.getEmployeeparfonction("LAVEUR");
			else
				listemployee = serviceEmployee.getEmployeeparfonction("VIDANGEUR");
			
			for (Employee e : listemployee) {				
				double totalv=0;
				double totals=0;
				double totalmv=0;
				listrendement = new ArrayList<Rendement>();
				int i=1;
				for (String s : ld) {				 
					Rendement r = new Rendement();				 
					double qte=0;					 
					double montantv=0;					 
					 qte=serviceRendement.getnbvBydate(s, e);
					 montantv=serviceRendement.getmontantvBydate(s, e);	 
	                   if(qte==0 )
	                	   r.setTest("true");
						r.setNbvoitures(df.format(qte));						  
						r.setNbvoiture(qte);						 
						r.setMontantv( montantv);					  
						r.setDates(i+"");				 
					listrendement.add(r);
					totalv=totalv+qte;					 
					totalmv=totalmv+montantv;				 
					i=i+1;
				}
				Rendement r = new Rendement();
				r.setNbvoitures(df.format(totalv));
				r.setNbsemis(df.format(totals));
				r.setNbvoiture(totalv);			 
				r.setDates("Total");			  
				r.setMontantv( totalmv); 
				listrendement.add(r);
				//if(totalv>0 || totals>0) {
					//listemployee.add(e);
					e.setListrendement(listrendement);
				    
				//}
					
			}
			Collections.sort(listemployee);
			Collections.reverse(listemployee);
		}
		catch (Exception e) {
		}
		}
		if(employee!=null){
			double totalv=0;
			double totalmv=0;
			double totalms=0;
			double totals=0;
			listrendement = new ArrayList<Rendement>();
			int i=1;
			for (String s : ld) {
				List<Rendement>l = new ArrayList<Rendement>();
				Rendement r = new Rendement();
				r.setId(i);
				l = serviceRendement.getAllventeparDateandmployee(s, employee);
				double qte=0;
				double qte2=0;
				double montantv=0;
				double montants=0;
				if (l != null) {
                   for(Rendement rr:l) {
                	   qte=qte+rr.getNbvoiture();
                       qte2=qte2+rr.getNbsemi();
                   if(rr.getNbvoiture()!=0)
                	   if(rr.getLignevente()!=null)
                	   montantv=  montantv+rr.getLignevente().getTotalttc();
                	   else 
                		   montantv=  montantv+(8.000*rr.getNbvoiture());
                   if(rr.getNbsemi()!=0)
                	   if(rr.getLignevente()!=null)
                	   montants=  montants+rr.getLignevente().getTotalttc();
                	   else 
                		   montants=  montants+(30.000*rr.getNbsemi());
                   }
                   if(qte==0 && qte2==0)
                	  r.setTest("true");
					r.setNbvoitures(df.format(qte));
					r.setNbsemis(df.format(qte2));
					r.setNbvoiture(qte);
					r.setNbsemi(qte2);				
					 //montantv=qte*(double)8.000;
					r.setMontantv( montantv);					 
					r.setMontants(montants);
					 
					r.setDates(i+"");
				} else {
					r.setNbvoiture(0);
					r.setMontants(0);
					r.setMontantv(0);
				}

				listrendement.add(r);
				totalv=totalv+qte;
				totals=totals+qte2;
				totalms=totalms+montants;
				totalmv=totalmv+montantv;
				i=i+1;
			}
			Rendement r = new Rendement();
			r.setId(i+1);
			r.setNbvoitures(df.format(totalv));
			r.setNbsemis(df.format(totals));
			r.setNbvoiture(totalv);
			r.setNbsemi(totals);			  
			r.setMontantv(totalmv);			 
			r.setMontants(totalms);			
			r.setDates("Total");
			listrendement.add(r);
			employee.setListrendement(listrendement);
		}
		
		
		return SUCCESS;
	}
	private double totalv;	private double totals;
	
	
	public void onrowSelect(SelectEvent event) {
		totalv=0;totals=0;
		DecimalFormat df = new DecimalFormat("#.000");
		
		for(Rendement f:selectedrendement) {
		totalv = totalv+f.getNbvoiture();		 
		totals=totals+f.getNbsemi();		 
		}
		
		FacesMessage msg = new FacesMessage("date Selectionnee",
				"" + ((Rendement) event.getObject()).getDates());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowSelect(SelectEvent event) {
		onrowSelect(event);
	}
	public ServiceEmployee getServiceEmployee() {
		return serviceEmployee;
	}

	public void setServiceEmployee(ServiceEmployee serviceEmployee) {
		this.serviceEmployee = serviceEmployee;
	}

	public ServiceRendement getServiceRendement() {
		return serviceRendement;
	}

	public void setServiceRendement(ServiceRendement serviceRendement) {
		this.serviceRendement = serviceRendement;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Employee> getListemployee() {
		return listemployee;
	}

	public void setListemployee(List<Employee> listemployee) {
		this.listemployee = listemployee;
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
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public double getNbvoiture() {
		return nbvoiture;
	}

	public void setNbvoiture(double nbvoiture) {
		this.nbvoiture = nbvoiture;
	}

	public double getNbsemi() {
		return nbsemi;
	}

	public void setNbsemi(double nbsemi) {
		this.nbsemi = nbsemi;
	}

	public List<Rendement> getListrendement() {
		return listrendement;
	}

	public void setListrendement(List<Rendement> listrendement) {
		this.listrendement = listrendement;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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

	public Integer getCodes() {
		return codes;
	}

	public void setCodes(Integer codes) {
		this.codes = codes;
	}

	public String getLavagvidange() {
		return lavagvidange;
	}

	public void setLavagvidange(String lavagvidange) {
		this.lavagvidange = lavagvidange;
	}

	public List<Rendement> getSelectedrendement() {
		return selectedrendement;
	}

	public void setSelectedrendement(List<Rendement> selectedrendement) {
		this.selectedrendement = selectedrendement;
	}

	public double getTotalv() {
		return totalv;
	}

	public void setTotalv(double totalv) {
		this.totalv = totalv;
	}

	public double getTotals() {
		return totals;
	}

	public void setTotals(double totals) {
		this.totals = totals;
	}

	public ServiceAvancegestat getServiceavance() {
		return serviceavance;
	}

	public void setServiceavance(ServiceAvancegestat serviceavance) {
		this.serviceavance = serviceavance;
	}

	public ServicePaie getServicePaie() {
		return servicePaie;
	}

	public void setServicePaie(ServicePaie servicePaie) {
		this.servicePaie = servicePaie;
	}

	public ServiceDepensegestat getServicedepenseGestat() {
		return servicedepenseGestat;
	}

	public void setServicedepenseGestat(ServiceDepensegestat servicedepenseGestat) {
		this.servicedepenseGestat = servicedepenseGestat;
	}

	public ServiceDepensecheque getServiceDepenseheaue() {
		return serviceDepenseheaue;
	}

	public void setServiceDepenseheaue(ServiceDepensecheque serviceDepenseheaue) {
		this.serviceDepenseheaue = serviceDepenseheaue;
	}

	public List<Rapport> getListRappoert() {
		return listRappoert;
	}

	public void setListRappoert(List<Rapport> listRappoert) {
		this.listRappoert = listRappoert;
	}

	public BarChartModel getTransportModel() {
		return transportModel;
	}

	public void setTransportModel(BarChartModel transportModel) {
		this.transportModel = transportModel;
	}

	public Rapport getRapport() {
		return rapport;
	}

	public void setRapport(Rapport rapport) {
		this.rapport = rapport;
	}

}
