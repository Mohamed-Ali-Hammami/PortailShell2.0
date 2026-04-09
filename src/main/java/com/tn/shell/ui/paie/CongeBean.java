package com.tn.shell.ui.paie;

import java.text.DecimalFormat;
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

import com.tn.shell.model.paie.*;
import com.tn.shell.service.paie.*;

@ManagedBean(name = "CongeBean")
@SessionScoped
public class CongeBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{ServicePointageconge}")
	ServicePointageconge servicePointageconge;
	@ManagedProperty(value = "#{ServiceEmployee}")
	ServiceEmployee serviceEmployee;
	@ManagedProperty(value = "#{ServiceSociete}")
	ServiceSociete serviceSociete;
	@ManagedProperty(value = "#{ServicePaieconge}")
	ServicePaieconge servicePaieconge;
	@ManagedProperty(value = "#{ServiceGestion}")
	ServiceGestion serviceGestion;
	@ManagedProperty(value = "#{ServiceLigneGestion}")
	ServiceLigneGestion ligneGestion;
	@ManagedProperty(value = "#{ServiceLigneGestionpaie}")
	ServiceLigneGestionpaie serviceLigneGestionpaie;
	private Societe societe;
	private List<Paieconge> listPaie;
	private List<Lignegestion> listGestion;
	private List<Employee> listEmployee;
	private List<String> listEmployees;
	private Integer nb_jour = 20;
	private Pointageconge pointageconge;
	private Integer annee;
	private Integer mois;
	private String moi;
	private String employer;
	private List<String> listMois = new ArrayList<String>();
	private List<Employee> listeEmployees;
	private List<String> listeEmployee;
	private List<Pointageconge> listpointage;
	private List<String> listannee;
	private List<Recapulatif> listPai;
	@ManagedProperty(value = "#{ServiceAnnee}")
	ServiceAnnee serviceAnnee;
	private String dates;
	private String annees;
	private String test;
	private double taux = 0.0918;
   
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
		else if (moi.equals("Septembre"))
			m = 9;
		else if (moi.equals("Octobre"))
			m = 10;
		else if (moi.equals("Novembre"))
			m = 11;
		else if (moi.equals("Decembre"))
			m = 12;
		return m;
	}

	public String etatPointage() {
		test = "";
		employer = null;
		annee = null;
		Date d = new Date();
		listPaie = new ArrayList<Paieconge>();
		listEmployees = new ArrayList<String>();
		listEmployee = new ArrayList<Employee>();
		listEmployee = serviceEmployee.getEmployeeparstats(Status.Declare);
		 
		Annee();
		annees = "";
		societe = serviceSociete.getAll().get(0);
		Annee();
		Pointageconge p = servicePointageconge.getMaxPointage();

		listPaie = servicePaieconge.getPaieByAnnee(p.getAnnee());
		if (listPaie != null) {
			for (Paieconge p1 : listPaie) {
				test = "notok";
				listGestion = new ArrayList<Lignegestion>();
				listGestion = ligneGestion.getlistbyemplyee(p1.getEmployee());
				for (Lignegestion g : listGestion) {
					g.setValeurdeprimeafficher(p1.getFormulaire_Paie().getNb_heure() * g.getGestion().getValeurdeprime() * 8);
					p1.setListGestion(listGestion);
				}
			}
		}

		/*
		 * else { test="ok"; listPaie = new ArrayList<Paieconge>(); listEmployee = new
		 * ArrayList<Employee>(); listEmployee =
		 * serviceEmployee.getEmployeeparstats(Status.Declare); for (Employee e :
		 * listEmployee) { listGestion = new ArrayList<Lignegestion>(); listGestion =
		 * serviceGestion.getAll(); double sommeprimes = 0; double nb_heure = 0; double
		 * irpp = 0;
		 * 
		 * 
		 * 
		 * Pointageconge po = servicePointageconge.getMaxPointage(); Formule_Paie f =
		 * new Formule_Paie(); if(po!=null){ f.setNb_heure(po.getNb_jour());
		 * if(e.getFonction().equals("FEMME DE MENAGE"))
		 * f.setSalairedebase(e.getSalaire_journalier() * 6 * f.getNb_heure()); else
		 * f.setSalairedebase(e.getSalaire_journalier() * 8 * f.getNb_heure()); for
		 * (Gestion g : listGestion) { g.setValeurdeprimeafficher(po.getNb_jour() *
		 * g.getValeurdeprime()* 8); sommeprimes = sommeprimes +
		 * g.getValeurdeprimeafficher(); }
		 * 
		 * f.setSalaire_brut(f.getSalairedebase() + sommeprimes);
		 * f.setRetenue_cnss(f.getSalaire_brut() * taux);
		 * f.setSalaire_imposable(f.getSalaire_brut() - f.getRetenue_cnss()); // a voire
		 * if (e.getEtat().equals(Etat.Mariee)) {
		 * 
		 * if (e.getNb_enfant_enCharge() == 0) irpp = (((f.getSalaire_brut() * 12) - 150
		 * - (0.1 * (f .getSalaire_brut() * 12)))); if (e.getNb_enfant_enCharge() == 1)
		 * irpp = (((f.getSalaire_brut() * 12) - 240 - (0.1 * (f .getSalaire_brut() *
		 * 12)))); if (e.getNb_enfant_enCharge() == 2) irpp = (((f.getSalaire_brut() *
		 * 12) - 315 - (0.1 * (f .getSalaire_brut() * 12)))); if
		 * (e.getNb_enfant_enCharge() == 3) irpp = (((f.getSalaire_brut() * 12) - 150 -
		 * 90 - 75 - 60 - (0.1 * (f.getSalaire_brut() * 12)))); } else if
		 * (e.getEtat().equals(Etat.Celebataire)) irpp = 0;
		 * 
		 * double ir = calculIRPP(irpp); f.setIrpp(ir);
		 * f.setSalaire_net(f.getSalaire_brut() - f.getRetenue_cnss() - f.getIrpp());
		 * 
		 * f.setNet_apayer(f.getSalaire_net());
		 * 
		 * Paieconge p2 = new Paieconge(); p2.setListGestion(listGestion);
		 * p2.setFormulaire_Paie(f); p2.setAnnee(p.getAnnee()); p2.setMois(p.getMois());
		 * p2.setEmployee(e); listPaie.add(p2); } }}
		 */
		return SUCCESS;
	}

	public void getPaiebyEmployer(AjaxBehaviorEvent event) {
		test = "notok";

		Date d = new Date();
		Integer a = d.getYear() + 1900;
		Integer m = d.getMonth() + 1;
		listPaie = new ArrayList<Paieconge>();
		// try {
		 
		Employee employee = serviceEmployee.getEmployeeByNom(employer);
		if (employee == null) {
			String message = "Veuillez choisir un employe";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
		}

		System.out.println("\n\n\n" + employee.getNom() + "\n\n\n");

		Pointageconge p = servicePointageconge.getMaxPointage();

		listPaie = servicePaieconge.getPaieByAnneeAndEmployee(employee, p.getAnnee());
		 double nbjour=0;
		  
		  double sdb=0;
		  double sb=0;
		  double cnss=0;
		  double si=0;
		  double ns=0;
		  double sn=0;
		for (Paieconge p1 : listPaie) { 
			sdb=sdb+p1.getFormulaire_Paie().getSalairedebase();
			sb=sb+p1.getFormulaire_Paie().getSalaire_brut();
			cnss=cnss+p1.getFormulaire_Paie().getRetenue_cnss();
			si=si+p1.getFormulaire_Paie().getSalaire_imposable();
			ns=ns+p1.getFormulaire_Paie().getSalaire_net();
			sn=sn+p1.getFormulaire_Paie().getNet_apayer();
			nbjour=nbjour+p1.getFormulaire_Paie().getNb_heure();
		}
		  Paieconge pc=new Paieconge();
		  pc.setAnnee(p.getAnnee());
		  pc.setEmployee(employee);		  
		  listPaie=new ArrayList<Paieconge>();
		  Formule_Paie ff =new Formule_Paie();
		  ff.setNb_heure(nbjour);
		  ff.setSalairedebase(sdb);
		  ff.setSalaire_brut(sb);
		  ff.setRetenue_cnss(cnss);
		  ff.setSalaire_imposable(si);
		  ff.setNet_apayer(sn);
		  ff.setSalaire_net(ns);
		  pc.setFormulaire_Paie(ff);
		  listGestion = new ArrayList<Lignegestion>();
			listGestion = ligneGestion.getlistbyemplyee(employee);
			for (Lignegestion g : listGestion) {
				g.setValeurdeprimeafficher(pc.getFormulaire_Paie().getNb_heure() * g.getGestion().getValeurdeprime() * 8);
				pc.setListGestion(listGestion);
			}
		  listPaie.add(pc);
		  
		// } catch (Exception e) { }
	}

	public void getpaiecongeByannee(AjaxBehaviorEvent event) {
		test = "notok";
		listPaie = new ArrayList<Paieconge>();
		listMois = new ArrayList<String>();

		 
		listPaie = new ArrayList<Paieconge>();

		listPaie = servicePaieconge.getPaieByAnnee(Integer.parseInt(annees));

		if (listPaie != null) {
			test = "notok";
			for (Paieconge p1 : listPaie) {
				listGestion = new ArrayList<Lignegestion>();
				listGestion = ligneGestion.getlistbyemplyee(p1.getEmployee());
				for (Lignegestion g : listGestion) {
					g.setValeurdeprimeafficher(p1.getFormulaire_Paie().getNb_heure() * g.getGestion().getValeurdeprime());
					p1.setListGestion(listGestion);
				}
			}
		}

	}

	private double calculIRPP(double salaire_brut) {
		double irpp = 0;
		if (salaire_brut >= 0 && salaire_brut <= 5000.000)
			irpp = (salaire_brut * 0);
		else if (salaire_brut > 5000.000 && salaire_brut <= 20000.000)
			irpp = ((salaire_brut - 5000.000) * 0.26) / 12;
		else if (salaire_brut > 20000.000 && salaire_brut <= 30000.000)
			irpp = ((salaire_brut - 5000.000) * 0.28) / 12;

		else if (salaire_brut > 30000.000 && salaire_brut <= 50000.000)
			irpp = ((salaire_brut - 5000.000) * 0.32) / 12;
		else if (salaire_brut > 50000.000)
			irpp = ((salaire_brut - 5000.000) * 0.35) / 12;
		else
			irpp = 0;
		return irpp;
	}

	public String Pointageconge() {
		listpointage = new ArrayList<Pointageconge>();
		listeEmployee = new ArrayList<String>();
		listEmployee = new ArrayList<Employee>();
		listEmployee= serviceEmployee.getEmployeeparstats(Status.Declare);
		 
		Annee();
		mois();
		Date d = new Date();
		moi = getMoisbyIntger(d.getMonth() + 1);
		annee = null;
		employer = null;
		nb_jour = null;
		return SUCCESS;

	}

	public void verifierpointage(AjaxBehaviorEvent event) {
		 

		Employee employee = serviceEmployee.getEmployeeByNom(employer);
		Pointageconge p = servicePointageconge.getPointageByEmployee(employee, annee);
		if (p != null) {
			String message = "vous avez deja pointer " + employer + " "  + " pour l'annee " + annee
					+ "!!!!!!!!!!!!!!";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
		}
	}

	public String ValiderPointageconge() {
		 
		Employee employee = serviceEmployee.getEmployeeByNom(employer);
		if (employee == null) {
			String message = "Veuillez choisir un employe";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
		}

		System.out.println("\n\n\n" + employee.getNom() + "\n\n\n");
		Pointageconge p = new Pointageconge();
		p.setAnnee(annee);
		p.setEmployee(employee);
		p.setMois(getMoisbyString(moi));
		p.setNb_jour(nb_jour);

		servicePointageconge.save(p);
		Paieconge p1 = new Paieconge();
		p1.setAnnee(annee);
		p1.setEmployee(employee);

		listGestion = new ArrayList<Lignegestion>();
		listGestion =ligneGestion.getlistbyemplyee(employee);
		double sommeprimes = 0;
		double nb_heure = 0;
		double irpp = 0;

		Pointageconge po = servicePointageconge.getPointageByEmployee(employee, p.getAnnee());
		Formule_Paie f = new Formule_Paie();
		if (po != null) {
			f.setNb_heure(po.getNb_jour());
			if (employee.getFonction().equals("FEMME DE MENAGE"))
				f.setSalairedebase(employee.getSalaire_journalier() * 6 * f.getNb_heure());
			else
				f.setSalairedebase(employee.getSalaire_journalier() * 8 * f.getNb_heure());
			for (Lignegestion g : listGestion) {
				g.setValeurdeprimeafficher(po.getNb_jour() * g.getGestion().getValeurdeprime() * 8);
				sommeprimes = sommeprimes + g.getValeurdeprimeafficher();
			}

			f.setSalaire_brut(f.getSalairedebase() + sommeprimes);
			f.setRetenue_cnss(f.getSalaire_brut() * taux);
			f.setSalaire_imposable(f.getSalaire_brut() - f.getRetenue_cnss());
			// a voire
			if (employee.getEtat().equals(Etat.Mariee)) {

				if (employee.getNb_enfant_enCharge() == 0)
					irpp = (((f.getSalaire_brut() * 12) - 300 - (0.1 * (f.getSalaire_brut() * 12))));
				if (employee.getNb_enfant_enCharge() == 1)
					irpp = (((f.getSalaire_brut() * 12) - 400 - (0.1 * (f.getSalaire_brut() * 12))));
				if (employee.getNb_enfant_enCharge() == 2)
					irpp = (((f.getSalaire_brut() * 12) - 500 - (0.1 * (f.getSalaire_brut() * 12))));
				if (employee.getNb_enfant_enCharge() == 3)
					irpp = (((f.getSalaire_brut() * 12) - 300 - 100 - 100 - 100 - (0.1 * (f.getSalaire_brut() * 12))));
			} else if (employee.getEtat().equals(Etat.Celebataire))
				irpp = 0;

			double ir = calculIRPP(irpp);
			f.setIrpp(ir);
			f.setSalaire_net(f.getSalaire_brut() - f.getRetenue_cnss() - f.getIrpp());

			f.setNet_apayer(f.getSalaire_net());

			/*
			 * Paieconge p2 = new Paieconge(); p2.setListGestion(listGestion);
			 * p2.setFormulaire_Paie(f); p2.setAnnee(p.getAnnee()); p2.setMois(p.getMois());
			 * p2.setEmployee(employee); listPaie.add(p2);
			 */
		}

		p1.setFormulaire_Paie(f);
		servicePaieconge.save(p1);
		Annee();

		societe = serviceSociete.getAll().get(0);
		Annee();

		return SUCCESS;
	}

	public void GetMoisByAnnee(AjaxBehaviorEvent event) {
		Date date = new Date();
		listMois = new ArrayList<String>();
		List<Pointageconge> lp = new ArrayList<Pointageconge>();
		lp = servicePointageconge.getPointageByAnnee(annee);
		for (Pointageconge a : lp)
			listMois.add(getMoisbyIntger(a.getMois()));
		listMois.add(getMoisbyIntger(date.getMonth() + 1));
	}

	public void onCellEdit(CellEditEvent event) {
		double oldValue = (Double) event.getOldValue();
		double newValue = (Double) event.getNewValue();
		Pointageconge e = listpointage.get(event.getRowIndex());

		e.setNb_jour(newValue);

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

	public void mois() {
		Date d = new Date();
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(d);
		listMois = new ArrayList<String>();
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
		mois = d.getMonth() + 1;
	}

	public String journalconge() {

		annees = "";
		societe = serviceSociete.getAll().get(0);
		Annee();
		listPaie = new ArrayList<Paieconge>();
		listMois = new ArrayList<String>();
		return SUCCESS;
	}

	public void journalconge2(AjaxBehaviorEvent event) {
		listPai = new ArrayList<Recapulatif>();
		 
		listPaie = new ArrayList<Paieconge>();
		listPaie = servicePaieconge.getPaieByAnnee(Integer.parseInt(annees));
		List<Lignepaiegestion> listpaieGestionG= new ArrayList<Lignepaiegestion>();			 
		if (listPaie!= null) {
			/*for (Paieconge p1 : listPaie) {
				listGestion = new ArrayList<Lignegestion>();
				listGestion = ligneGestion.getlistbyemplyee(p1.getEmployee());
				for (Lignegestion g : listGestion) {
					g.setValeurdeprimeafficher(p1.getFormulaire_Paie().getNb_heure() * g.getGestion().getValeurdeprime() * 8);
					p1.setListGestion(serviceLigneGestionpaie.getlistbypaie(p1));
					 for(Lignepaiegestion pp:p1.getListGestion())
					 for(Lignepaiegestion pp:p1.getListGestion())
						 listpaieGestion.add(pp);
				}
			}
			
			for (Paieconge p1 : listPaie1) {
				List<Lignepaiegestion> listpaieGestion = new ArrayList<Lignepaiegestion>();			 
					p1.getEmployee().setLignegestion(ligneGestion.getlistbyemplyee(p1.getEmployee()));
					     Lignepaiegestion pp=new Lignepaiegestion();					 	 
							for (Lignegestion g : p1.getListGestion()) {
								g.setValeurdeprimeafficher(p1.getFormulaire_Paie().getNb_heure() * g.getGestion().getValeurdeprime() * 8);
								p1.setListGestion(p1.getListGestion());								
								pp.setLignegestion(g);
								pp.setValeurdeprime(g.getValeurdeprimeafficher());								 
								if (p1.getEmployee().getFonction().equals("FEMME DE MENAGE"))
									pp.setValeurdeprimeafficher(g.getValeurdeprimeafficher()* p1.getFormulaire_Paie().getNb_heure() * 7);
								else
									pp.setValeurdeprimeafficher(g.getValeurdeprimeafficher()* p1.getFormulaire_Paie().getNb_heure() * 8);
							}
						   listpaieGestion.add(pp);
						   listpaieGestionG.add(pp);
						  p1.setListPaieGestion(listpaieGestion);
					      listPaie.add(p1);
					
				 
			}
			*/

			Formule_Paie f = new Formule_Paie();
			double irpp = 0;

			double sb = 0;
			double sbr = 0;
			double si = 0;
			double sn = 0;
			double rtn = 0;
			double nap = 0;
			double nb_h = 0;
			for (Paieconge e : listPaie) {
				nb_h = nb_h + e.getFormulaire_Paie().getNb_heure();

				sb = sb + e.getFormulaire_Paie().getSalairedebase();

				sbr = sbr + e.getFormulaire_Paie().getSalaire_brut();
				rtn = rtn + e.getFormulaire_Paie().getRetenue_cnss();
				si = si + e.getFormulaire_Paie().getSalaire_brut();
				irpp = irpp + e.getFormulaire_Paie().getIrpp();
				sn = sn + e.getFormulaire_Paie().getSalaire_net();
				nap = nap + e.getFormulaire_Paie().getNet_apayer();
			}
			f.setNb_heure(nb_h);

			f.setIrpp(irpp);
			f.setSalaire_net(sn);
			f.setNet_apayer(nap);
			f.setRetenue_cnss(rtn);
			f.setSalaire_imposable(si);
			f.setSalaire_brut(sbr);
			f.setSalairedebase(sb);
			f.setSalairedebase(sb);
			Recapulatif r = new Recapulatif();
			r.setAnnee(Integer.parseInt(annees));
			List<Gestion> lg = new ArrayList<Gestion>();
			lg = serviceGestion.getAll();
			r.setFormul(f);
			
			/*for (Recapulatif p1 : listPai) {
				 
				for (Gestion g : lg)
					g.setSommeprime(p1.getFormul().getNb_heure() * 8 * g.getValeurdeprime());
				p1.setListGestion(lg);
			}
			
			List<Lignepaiegestion> lg2 = new ArrayList<Lignepaiegestion>();
			DecimalFormat df=new DecimalFormat("0.000");
			for (Gestion ll : lg) {
				double total=0;
				for (Lignepaiegestion e : listpaieGestionG) {
					if (e.getLignegestion().getGestion().getId().equals(ll.getId())) {					 	  
						 total=total+e.getValeurdeprimeafficher();
						 
					}
				}
				Lignepaiegestion lgg=new Lignepaiegestion();
				lgg.setTotalaffichers(df.format(total));
				lg2.add(lgg);
			}
			r.setListGestion(lg2);*/
			 
			listPai.add(r);
		}

	}

	public String validerpaieconge() {
		if (listPaie != null)
			for (Paieconge p : listPaie) {
				servicePaieconge.save(p);
			}
		return SUCCESS;
	}

	private void Annee() {
		List<Annee> l = new ArrayList<Annee>();
		listannee = new ArrayList<String>();
		Date d = new Date();
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		annees = s.format(d).substring(6);
		Annee a = serviceAnnee.findbyDesignation(annees);
		if (a == null) {
			Annee e = new Annee();
			e.setAnnee(annees);
			serviceAnnee.save(e);
		}
		l = serviceAnnee.getAll();
		for (Annee aa : l) {
			listannee.add(aa.getAnnee());
		}
		Integer aa = Integer.parseInt(annees);
		listannee.add(aa + 1 + "");
		annee = Integer.parseInt(annees);
	}

	public ServicePointageconge getServicePointageconge() {
		return servicePointageconge;
	}

	public void setServicePointageconge(ServicePointageconge servicePointageconge) {
		this.servicePointageconge = servicePointageconge;
	}

	public ServiceEmployee getServiceEmployee() {
		return serviceEmployee;
	}

	public void setServiceEmployee(ServiceEmployee serviceEmployee) {
		this.serviceEmployee = serviceEmployee;
	}

	public ServiceSociete getServiceSociete() {
		return serviceSociete;
	}

	public void setServiceSociete(ServiceSociete serviceSociete) {
		this.serviceSociete = serviceSociete;
	}

	public ServicePaieconge getServicePaieconge() {
		return servicePaieconge;
	}

	public void setServicePaieconge(ServicePaieconge servicePaieconge) {
		this.servicePaieconge = servicePaieconge;
	}

	public ServiceGestion getServiceGestion() {
		return serviceGestion;
	}

	public void setServiceGestion(ServiceGestion serviceGestion) {
		this.serviceGestion = serviceGestion;
	}

	public Societe getSociete() {
		return societe;
	}

	public void setSociete(Societe societe) {
		this.societe = societe;
	}

	public List<Paieconge> getListPaie() {
		return listPaie;
	}

	public void setListPaie(List<Paieconge> listPaie) {
		this.listPaie = listPaie;
	}

	public List<Lignegestion> getListGestion() {
		return listGestion;
	}

	public void setListGestion(List<Lignegestion> listGestion) {
		this.listGestion = listGestion;
	}

	public List<Employee> getListEmployee() {
		return listEmployee;
	}

	public void setListEmployee(List<Employee> listEmployee) {
		this.listEmployee = listEmployee;
	}

	public List<String> getListEmployees() {
		return listEmployees;
	}

	public void setListEmployees(List<String> listEmployees) {
		this.listEmployees = listEmployees;
	}

	public Integer getNb_jour() {
		return nb_jour;
	}

	public void setNb_jour(Integer nb_jour) {
		this.nb_jour = nb_jour;
	}

	public Pointageconge getPointageconge() {
		return pointageconge;
	}

	public void setPointageconge(Pointageconge pointageconge) {
		this.pointageconge = pointageconge;
	}

	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	public Integer getMois() {
		return mois;
	}

	public void setMois(Integer mois) {
		this.mois = mois;
	}

	public String getMoi() {
		return moi;
	}

	public void setMoi(String moi) {
		this.moi = moi;
	}

	public List<String> getListMois() {
		return listMois;
	}

	public void setListMois(List<String> listMois) {
		this.listMois = listMois;
	}

	public List<Employee> getListeEmployees() {
		return listeEmployees;
	}

	public void setListeEmployees(List<Employee> listeEmployees) {
		this.listeEmployees = listeEmployees;
	}

	public List<String> getListeEmployee() {
		return listeEmployee;
	}

	public void setListeEmployee(List<String> listeEmployee) {
		this.listeEmployee = listeEmployee;
	}

	public List<Pointageconge> getListpointage() {
		return listpointage;
	}

	public void setListpointage(List<Pointageconge> listpointage) {
		this.listpointage = listpointage;
	}

	public List<String> getListannee() {
		return listannee;
	}

	public void setListannee(List<String> listannee) {
		this.listannee = listannee;
	}

	public ServiceAnnee getServiceAnnee() {
		return serviceAnnee;
	}

	public void setServiceAnnee(ServiceAnnee serviceAnnee) {
		this.serviceAnnee = serviceAnnee;
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

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	public List<Recapulatif> getListPai() {
		return listPai;
	}

	public void setListPai(List<Recapulatif> listPai) {
		this.listPai = listPai;
	}

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
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

	 
	

}
