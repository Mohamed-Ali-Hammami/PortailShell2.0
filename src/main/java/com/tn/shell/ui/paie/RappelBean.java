package com.tn.shell.ui.paie;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.AjaxBehaviorEvent;

import com.tn.shell.model.paie.Annee;
 
import com.tn.shell.model.paie.Employee;
import com.tn.shell.model.paie.Formule_Paie;
import com.tn.shell.model.paie.Gestion;
import com.tn.shell.model.paie.Gestionrappel;
import com.tn.shell.model.paie.Lignegestion;
import com.tn.shell.model.paie.Lignepaiegestion;
import com.tn.shell.model.paie.Lignepaiegestionrappel;
import com.tn.shell.model.paie.Paie;
import com.tn.shell.model.paie.Pointage;
import com.tn.shell.model.paie.Rappel;
import com.tn.shell.model.paie.Recapulatif;
import com.tn.shell.model.paie.Status;
 
import com.tn.shell.service.gestat.UserService;
import com.tn.shell.service.paie.ServiceAnnee;
import com.tn.shell.service.paie.ServiceCategorie;
import com.tn.shell.service.paie.ServiceEmployee;
import com.tn.shell.service.paie.ServiceGestion;
import com.tn.shell.service.paie.ServiceGestionrappel;
import com.tn.shell.service.paie.ServiceLigneGestion;
import com.tn.shell.service.paie.ServiceLigneGestionpaie;
import com.tn.shell.service.paie.ServiceLigneGestionpaierappel;
import com.tn.shell.service.paie.ServicePaie;
import com.tn.shell.service.paie.ServicePointage;
import com.tn.shell.service.paie.ServiceRappel;
import com.tn.shell.service.paie.ServiceTrace;

@ManagedBean(name = "RappelBean")
@SessionScoped
public class RappelBean {

	
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{ServiceAnnee}")
	ServiceAnnee serviceAnnee;
	@ManagedProperty(value = "#{ServiceTrace}")
	ServiceTrace serviceTrace;
	@ManagedProperty(value = "#{UserService}")
	UserService userservice;
	@ManagedProperty(value = "#{ServiceRappel}")
	 ServiceRappel serviceRappel;
	 
	@ManagedProperty(value = "#{ServiceEmployee}")
	ServiceEmployee serviceEmployee;
	@ManagedProperty(value = "#{ServicePaie}")
	ServicePaie servicePaie;
	@ManagedProperty(value = "#{ServicePointage}")
	ServicePointage servicePointage;
	@ManagedProperty(value = "#{ServiceGestionrappel}")
     ServiceGestionrappel serviceGestionrappel;
	@ManagedProperty(value = "#{ServiceGestion}")
	ServiceGestion serviceGestion;
	@ManagedProperty(value = "#{ServiceCategorie}")
	ServiceCategorie serviceCategorie;
	@ManagedProperty(value = "#{ServiceLigneGestion}")
	ServiceLigneGestion ligneGestion;
	@ManagedProperty(value = "#{ServiceLigneGestionpaie}")
	ServiceLigneGestionpaie serviceLigneGestionpaie;
	
	@ManagedProperty(value = "#{ServiceLigneGestionpaierappel}")
	ServiceLigneGestionpaierappel serviceLigneGestionpaierappel;
	
	private List<Lignepaiegestionrappel> listpaieGestion;
	private List<Annee> listannee;
	private List<Annee> listannee2;
	private String  annee;
	private String annee2;
	private String mois;
    private String mois2;
	private List<String> listMois ;
	private List<String> listMois2 ;
	private List<String> listMoiajouters ;
	private String dates;
	private String annees;
	private Employee employee;
 
	// private List<Lignegestion> listGestion;
	private List<Employee> listEmployee;
	private List<Rappel> listRappel;
	private String employer;
	private double taux = 0.0918;
    
 
 
	private double nbjour;
	 
	private Pointage pontage;
	private DecimalFormat df = new DecimalFormat("0.000");
	
	
	
	public String accesRappel() {
		nbjour=0;
		 listMois = new ArrayList<String>();
		 listMois2 = new ArrayList<String>();
		 listMoiajouters = new ArrayList<String>();
		 listRappel=new ArrayList<Rappel>();
		 listEmployee=new ArrayList<Employee>();
		 listEmployee=serviceEmployee.getEmployeeparstats(Status.Declare);
		 listannee=new ArrayList<Annee>();
		 listannee=serviceAnnee.getAll();
		 listannee2=new ArrayList<Annee>();
		 listannee2=serviceAnnee.getAll();
		    listMois.add("Janvier");
			listMois.add("Fevrier");
			listMois.add("Mars");
			listMois.add("Avril");
			listMois.add("Mai");
			listMois.add("Juin");
			listMois.add("Juillet");
			listMois.add("aout");
			listMois.add("Septembre");
			listMois.add("Octobre");
			listMois.add("Novembre");
			listMois.add("Decembre");
			listMois2 =listMois;
			annee = String.valueOf(resolveDefaultRappelYear());
			annee2 = annee;
			mois = getMoisbyIntger(resolveDefaultRappelMonth());
			mois2 = mois;
		return SUCCESS;
	}
	private Integer codes;
	public void updatepontage(AjaxBehaviorEvent event) {
		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
		Employee p=listEmployee.get(codes);
		listEmployee.set(codes, p);
		 
		p.setSalaire_journalier(nbjour);
		nbjour=20;
	}
	public void updatedate(AjaxBehaviorEvent event) {
 		nbjour=servicePointage.getsumPointageByEmployee(serviceEmployee.getEmployeeById(7),
 				Integer.parseInt(annee),getMoisbyString(mois),getMoisbyString(mois2));
	}
	
	public String validerRappel() {
		for(Employee e:listEmployee) {
			Rappel r =new Rappel();
			Formule_Paie f = new Formule_Paie();
			f.setSalaire_brut(1);
			f.setRetenue_cnss(1);
			f.setSalaire_imposable(1);
			f.setSalaire_net(1);
	     	 f.setAvance(1);
	     	 f.setIrpp(1);
			f.setNet_apayer(1);
			r.setFormulaire_Paie(f);			
			r.setAnnee(Integer.parseInt(annee));			 
			r.setEmployee(e);	
			
			e.setLignegestion(ligneGestion.getlistbyemplyee(e));
			e.setLignegestion(ligneGestion.getlistbyemplyee(e));
			double sommeprimes = 0;
			double totalpointagebyemplyee=0;

		if(annee.equals(annee2)) {
			/*for(int i=getMoisbyString(mois);i<=getMoisbyString(mois2);i++) {
				Pointage p=servicePointage.getPointageByEmployee(e, annee,i);
				if(p!=null)
				totalpointagebyemplyee=totalpointagebyemplyee+p.getNb_jour();
			}*/
			totalpointagebyemplyee=servicePointage.getsumPointageByEmployee(e,Integer.parseInt(annee),getMoisbyString(mois),getMoisbyString(mois2));
		}
		else  {
			/*for(int i=getMoisbyString(mois);i<=12;i++) {
				Pointage p=servicePointage.getPointageByEmployee(e, annee,i);
				if(p!=null)
				totalpointagebyemplyee=totalpointagebyemplyee+p.getNb_jour();
			}*/
			totalpointagebyemplyee=servicePointage.getsumPointageByEmployee(e,Integer.parseInt(annee),getMoisbyString(mois),12)+
					servicePointage.getsumPointageByEmployee(e,Integer.parseInt(annee2),1,getMoisbyString(mois2)) ;
	   /* for(int i=1;i<=getMoisbyString(mois2);i++) {
	    	Pointage p=servicePointage.getPointageByEmployee(e, annee2,i);
			if(p!=null)
			totalpointagebyemplyee=totalpointagebyemplyee+p.getNb_jour();	
			}*/
		}
		if(totalpointagebyemplyee!=0) {
			serviceRappel.save(r);
		f.setNb_heure(totalpointagebyemplyee);
		if (e.getFonction().equals("FEMME DE MENAGE"))
			f.setSalairedebase( e.getSalaire_journalier() * 6 * f.getNb_heure());
		else
			f.setSalairedebase(e.getSalaire_journalier()* 8 * f.getNb_heure());
		
		List<Lignegestion> l1=e.getLignegestion();
		
		
		List<Lignepaiegestionrappel>listpaieGestion = new ArrayList<Lignepaiegestionrappel>();
		for(Lignegestion g:l1) {
			
			 if(g.getGestion().getId()!=3) {
				 Lignepaiegestionrappel lg=new Lignepaiegestionrappel();
			 Gestionrappel gestion=serviceGestionrappel.getGestionbyAnnee(Integer.parseInt(annee),g.getGestion().getLibelle());
			lg.setLignegestion(g);
			lg.setValeurdeprime(gestion.getValeurdeprime());
			  if (e.getFonction().equals("FEMME DE MENAGE"))
			lg.setValeurdeprimeafficher(lg.getValeurdeprime()*f.getNb_heure()*7);
			  else lg.setValeurdeprimeafficher(lg.getValeurdeprime()*f.getNb_heure()*8);
			 lg.setRappelle(r);
			  sommeprimes=sommeprimes+lg.getValeurdeprimeafficher();
			  serviceLigneGestionpaierappel.save(lg);
			listpaieGestion.add(lg);
			 }
		}
		
		f.setSalaire_brut(f.getSalairedebase() + sommeprimes);
		f.setRetenue_cnss(f.getSalaire_brut() * taux);
		f.setSalaire_imposable(f.getSalaire_brut() - f.getRetenue_cnss());
		f.setSalaire_net(f.getSalaire_imposable() );
	 f.setIrpp(0);
		f.setNet_apayer(f.getSalaire_net());
		
					
		 
		
		 r.setListGestion(listpaieGestion);
		  serviceRappel.update(r);
		 listRappel.add(r);
		}
	}
		return SUCCESS;
	}
//	public String validerRappel() {
//		
//		for(Employee e:listEmployee) {
//			Rappel r =new Rappel();
//			Formule_Paie f = new Formule_Paie();
//			f.setSalaire_brut(1);
//			f.setRetenue_cnss(1);
//			f.setSalaire_imposable(1);
//			f.setSalaire_net(1);
//	     	 f.setAvance(1);
//	     	 f.setIrpp(1);
//			f.setNet_apayer(1);
//			r.setFormulaire_Paie(f);			
//			r.setAnnee(annee);			 
//			r.setEmployee(e);	
//			
//			e.setLignegestion(ligneGestion.getlistbyemplyee(e));
//			double sommeprimes = 0;
//			double totalpointagebyemplyee=0;
//		for(String s:listMoiajouters) {
//			int mois=getMoisbyString(s);
//			Pointage p=servicePointage.getPointageByEmployee(e, annee, mois);
//			if(p!=null)
//			totalpointagebyemplyee=totalpointagebyemplyee+p.getNb_jour();
//		}
//		if(totalpointagebyemplyee!=0) {
//			serviceRappel.save(r);
//		f.setNb_heure(totalpointagebyemplyee);
//		if (e.getFonction().equals("FEMME DE MENAGE"))
//			f.setSalairedebase( e.getSalaire_journalier() * 6 * f.getNb_heure());
//		else
//			f.setSalairedebase(e.getSalaire_journalier()* 8 * f.getNb_heure());
//		
//		List<Lignegestion> l1=e.getLignegestion();
//		
//		
//		List<Lignepaiegestionrappel>listpaieGestion = new ArrayList<Lignepaiegestionrappel>();
//		for(Lignegestion g:l1) {
//			
//			 if(g.getGestion().getId()!=3) {
//				 Lignepaiegestionrappel lg=new Lignepaiegestionrappel();
//			 Gestionrappel gestion=serviceGestionrappel.getGestionbyAnnee(annee,g.getGestion().getLibelle());
//			lg.setLignegestion(g);
//			lg.setValeurdeprime(gestion.getValeurdeprime());
//			  if (e.getFonction().equals("FEMME DE MENAGE"))
//			lg.setValeurdeprimeafficher(lg.getValeurdeprime()*f.getNb_heure()*7);
//			  else lg.setValeurdeprimeafficher(lg.getValeurdeprime()*f.getNb_heure()*8);
//			 lg.setRappelle(r);
//			  sommeprimes=sommeprimes+lg.getValeurdeprimeafficher();
//			  serviceLigneGestionpaierappel.save(lg);
//			listpaieGestion.add(lg);
//			 }
//		}
//		
//		f.setSalaire_brut(f.getSalairedebase() + sommeprimes);
//		f.setRetenue_cnss(f.getSalaire_brut() * taux);
//		f.setSalaire_imposable(f.getSalaire_brut() - f.getRetenue_cnss());
//		f.setSalaire_net(f.getSalaire_imposable() );
//	 f.setIrpp(0);
//		f.setNet_apayer(f.getSalaire_net());
//		
//					
//		 
//		
//		 r.setListGestion(listpaieGestion);
//		  serviceRappel.update(r);
//		 listRappel.add(r);
//		}
//		}
//		
//		
//		return SUCCESS;
//	}
//	
	
	public String journal() {
		listannee=new ArrayList<Annee>();
		listannee=serviceAnnee.getAll();
		annee = String.valueOf(resolveDefaultRappelYear());
		listRappel=new ArrayList<Rappel>();
		listPai = new ArrayList<Recapulatif>();
		return SUCCESS;
	}
	private List<Recapulatif> listPai ;

	private int resolveDefaultRappelYear() {
		Rappel latestRappel = serviceRappel.getMaxPointageconge();
		if (latestRappel != null && latestRappel.getAnnee() != null) {
			return latestRappel.getAnnee();
		}
		Pointage latestPointage = servicePointage.getMaxPointage();
		if (latestPointage != null && latestPointage.getAnnee() != null) {
			return latestPointage.getAnnee();
		}
		return new java.util.Date().getYear() + 1900;
	}

	private int resolveDefaultRappelMonth() {
		Pointage latestPointage = servicePointage.getMaxPointage();
		if (latestPointage != null && latestPointage.getMois() != null) {
			return latestPointage.getMois();
		}
		return new java.util.Date().getMonth() + 1;
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
	public void getRappelbyannee(AjaxBehaviorEvent event) {
		listRappel=new ArrayList<Rappel>();
		listRappel=serviceRappel.getAll(Integer.parseInt(annee));
		
		listPai = new ArrayList<Recapulatif>();
		double sb = 0;
		double sbr = 0;
		double si = 0;
		double sn = 0;
		double rtn = 0;
		double nap = 0;
		double nb_h = 0;
		Formule_Paie f = new Formule_Paie();
		 listpaieGestion=new ArrayList<Lignepaiegestionrappel>();
		for (Rappel e : listRappel) {

			nb_h = nb_h + e.getFormulaire_Paie().getNb_heure();
			 
			sb = sb + e.getFormulaire_Paie().getSalairedebase();
			 
			sbr = sbr + e.getFormulaire_Paie().getSalaire_brut();
			rtn = rtn + e.getFormulaire_Paie().getRetenue_cnss();
			si = si + e.getFormulaire_Paie().getSalaire_brut();
			 
			sn = sn + e.getFormulaire_Paie().getSalaire_net();
			nap = nap + e.getFormulaire_Paie().getNet_apayer();
			
			e.getEmployee().setLignegestion(ligneGestion.getlistbyemplyee(e.getEmployee()));
			e.setListGestion(serviceLigneGestionpaierappel.getlistbypaie(e));
			 for(Lignepaiegestionrappel pp:e.getListGestion())
				 listpaieGestion.add(pp);
		}
		f.setNb_heure(nb_h);
		 
		f.setIrpp(0);
		f.setSalaire_net(sn);
		f.setNet_apayer(nap);
		f.setRetenue_cnss(rtn);
		f.setSalaire_imposable(si);
		f.setSalaire_brut(sbr);
		f.setSalairedebase(sb);
		f.setSalairedebase(sb);

		Recapulatif r = new Recapulatif();
		r.setAnnee(Integer.parseInt(annee));
		 
		r.setFormul(f);
		List<Lignepaiegestionrappel> lg2 = new ArrayList<Lignepaiegestionrappel>();
		List<Gestion> lg = new ArrayList<Gestion>();
		lg = serviceGestion.getAll();
		DecimalFormat df=new DecimalFormat("0.000");
		if(listpaieGestion.size()>0)
		for (Gestion ll : lg) {
			 
			double total=0;
			for (Lignepaiegestionrappel e : listpaieGestion) {
				if (e.getLignegestion().getGestion().getId()==ll.getId() && ll.getId()!=3) {					 	  
					 total=total+e.getValeurdeprimeafficher();
					 
				}
			}
			Lignepaiegestionrappel lgg=new Lignepaiegestionrappel();
			lgg.setTotalaffichers(df.format(total));
			lg2.add(lgg);
		}
		if(lg2.size()!=0)
		r.setListGestions(lg2);
         
		listPai.add(r);
		
	}
	private Integer getMoisbyString(String moi) {
		Integer m = 0;
		if (moi.equals("Janvier"))
			m = 1;
		else if (moi.equals("Fevrier"))
			m = 2;
		else if (moi.equals("Mars"))
			m = 3;
		else if (moi.equals("Avril"))
			m = 4;
		else if (moi.equals("Mai"))
			m = 5;
		else if (moi.equals("Juin"))
			m = 6;
		else if (moi.equals("Juillet"))
			m = 7;
		else if (moi.equals("aout"))
			m = 8;
		else if (moi.equals("Septembre"))// Septembre
			m = 9;
		else if (moi.equals("Octobre"))
			m = 10;
		else if (moi.equals("Novembre"))
			m = 11;
		else if (moi.equals("Decembre"))
			m = 12;
		return m;
	}


	public ServiceAnnee getServiceAnnee() {
		return serviceAnnee;
	}


	public void setServiceAnnee(ServiceAnnee serviceAnnee) {
		this.serviceAnnee = serviceAnnee;
	}


	public ServiceTrace getServiceTrace() {
		return serviceTrace;
	}


	public void setServiceTrace(ServiceTrace serviceTrace) {
		this.serviceTrace = serviceTrace;
	}


	public UserService getUserservice() {
		return userservice;
	}


	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
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


	public ServicePointage getServicePointage() {
		return servicePointage;
	}


	public void setServicePointage(ServicePointage servicePointage) {
		this.servicePointage = servicePointage;
	}


	public ServiceGestionrappel getServiceGestionrappel() {
		return serviceGestionrappel;
	}


	public void setServiceGestionrappel(ServiceGestionrappel serviceGestionrappel) {
		this.serviceGestionrappel = serviceGestionrappel;
	}


	public ServiceGestion getServiceGestion() {
		return serviceGestion;
	}


	public void setServiceGestion(ServiceGestion serviceGestion) {
		this.serviceGestion = serviceGestion;
	}


	public ServiceCategorie getServiceCategorie() {
		return serviceCategorie;
	}


	public void setServiceCategorie(ServiceCategorie serviceCategorie) {
		this.serviceCategorie = serviceCategorie;
	}


	public ServiceLigneGestion getLigneGestion() {
		return ligneGestion;
	}


	public void setLigneGestion(ServiceLigneGestion ligneGestion) {
		this.ligneGestion = ligneGestion;
	}


	public ServiceLigneGestionpaie getServiceLigneGestionpaie() {
		return serviceLigneGestionpaie;
	}


	public void setServiceLigneGestionpaie(ServiceLigneGestionpaie serviceLigneGestionpaie) {
		this.serviceLigneGestionpaie = serviceLigneGestionpaie;
	}

 


	public ServiceLigneGestionpaierappel getServiceLigneGestionpaierappel() {
		return serviceLigneGestionpaierappel;
	}

	public void setServiceLigneGestionpaierappel(ServiceLigneGestionpaierappel serviceLigneGestionpaierappel) {
		this.serviceLigneGestionpaierappel = serviceLigneGestionpaierappel;
	}

	public List<Lignepaiegestionrappel> getListpaieGestion() {
		return listpaieGestion;
	}

	public void setListpaieGestion(List<Lignepaiegestionrappel> listpaieGestion) {
		this.listpaieGestion = listpaieGestion;
	}

	public List<Recapulatif> getListPai() {
		return listPai;
	}

	public void setListPai(List<Recapulatif> listPai) {
		this.listPai = listPai;
	}

	public List<Annee> getListannee() {
		return listannee;
	}


	public void setListannee(List<Annee> listannee) {
		this.listannee = listannee;
	}


	public String getAnnee() {
		return annee;
	}


	public void setAnnee(String annee) {
		this.annee = annee;
	}


	public String getMois() {
		return mois;
	}


	public void setMois(String mois) {
		this.mois = mois;
	}


	public List<String> getListMois() {
		return listMois;
	}


	public void setListMois(List<String> listMois) {
		this.listMois = listMois;
	}


	public List<String> getListMoiajouters() {
		return listMoiajouters;
	}


	public void setListMoiajouters(List<String> listMoiajouters) {
		this.listMoiajouters = listMoiajouters;
	}


	public String getDates() {
		return dates;
	}


	public void setDates(String dates) {
		this.dates = dates;
	}


	public String getAnnees() {
		return annees;
	}


	public void setAnnees(String annees) {
		this.annees = annees;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public List<Employee> getListEmployee() {
		return listEmployee;
	}


	public void setListEmployee(List<Employee> listEmployee) {
		this.listEmployee = listEmployee;
	}


	public List<Rappel> getListRappel() {
		return listRappel;
	}


	public void setListRappel(List<Rappel> listRappel) {
		this.listRappel = listRappel;
	}


	public String getEmployer() {
		return employer;
	}


	public void setEmployer(String employer) {
		this.employer = employer;
	}


	public double getTaux() {
		return taux;
	}


	public void setTaux(double taux) {
		this.taux = taux;
	}


	public double getNbjour() {
		return nbjour;
	}


	public void setNbjour(double nbjour) {
		this.nbjour = nbjour;
	}


	public Pointage getPontage() {
		return pontage;
	}


	public void setPontage(Pointage pontage) {
		this.pontage = pontage;
	}


	public DecimalFormat getDf() {
		return df;
	}


	public void setDf(DecimalFormat df) {
		this.df = df;
	}

	public ServiceRappel getServiceRappel() {
		return serviceRappel;
	}

	public void setServiceRappel(ServiceRappel serviceRappel) {
		this.serviceRappel = serviceRappel;
	}

	public Integer getCodes() {
		return codes;
	}

	public void setCodes(Integer codes) {
		this.codes = codes;
	}


	public String getAnnee2() {
		return annee2;
	}


	public void setAnnee2(String annee2) {
		this.annee2 = annee2;
	}


	public String getMois2() {
		return mois2;
	}


	public void setMois2(String mois2) {
		this.mois2 = mois2;
	}
	public List<Annee> getListannee2() {
		return listannee2;
	}
	public void setListannee2(List<Annee> listannee2) {
		this.listannee2 = listannee2;
	}
	public List<String> getListMois2() {
		return listMois2;
	}
	public void setListMois2(List<String> listMois2) {
		this.listMois2 = listMois2;
	}
	
	
	
	
}
