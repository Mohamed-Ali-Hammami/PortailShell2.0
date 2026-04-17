package com.tn.shell.ui.paie;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import com.tn.shell.dao.paie.LigneGestiondao;
import com.tn.shell.model.gestat.Avancegestat;
import com.tn.shell.model.gestat.Utilisateur;
import com.tn.shell.model.paie.*;
import com.tn.shell.service.gestat.ServiceAvancegestat;
import com.tn.shell.service.gestat.UserService;
import com.tn.shell.service.paie.*;
import com.tn.shell.service.shop.ServiceRendement;

@ManagedBean(name = "PaisBeans")
@SessionScoped
public class PaisBeans {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{ServiceAnnee}")
	ServiceAnnee serviceAnnee;
	@ManagedProperty(value = "#{ServiceTrace}")
	ServiceTrace serviceTrace;
	@ManagedProperty(value = "#{UserService}")
	UserService userservice;

	@ManagedProperty(value = "#{ServiceSociete}")
	ServiceSociete serviceSociete;
	@ManagedProperty(value = "#{ServiceAvancegestat}")
	ServiceAvancegestat serviceAvance;
	@ManagedProperty(value = "#{ServiceEmployee}")
	ServiceEmployee serviceEmployee;
	@ManagedProperty(value = "#{ServicePaie}")
	ServicePaie servicePaie;
	@ManagedProperty(value = "#{ServicePointage}")
	ServicePointage servicePointage;
	@ManagedProperty(value = "#{ServicePret}")
	ServicePret servicePret;
	@ManagedProperty(value = "#{ServiceNote}")
	ServiceNote serviceNote;
	@ManagedProperty(value = "#{ServiceGestion}")
	ServiceGestion serviceGestion;
	@ManagedProperty(value = "#{ServiceCategorie}")
	ServiceCategorie serviceCategorie;
	@ManagedProperty(value = "#{ServiceLigneGestion}")
	ServiceLigneGestion ligneGestion;
	@ManagedProperty(value = "#{ServiceLigneGestionpaie}")
	ServiceLigneGestionpaie serviceLigneGestionpaie;
	@ManagedProperty(value = "#{ServiceRendement}")
	ServiceRendement servicerendement;
	private List<Lignepaiegestion> listpaieGestion;
	private Societe societe;
	private List<String> listannee;
	private Integer annee;
	private String mois;
	private List<String> listMois = new ArrayList<String>();
	private String dates;
	private String annees;
	private Employee employee;
	private Paie selectedPai;
	private List<Paie> listPaie;
	// private List<Lignegestion> listGestion;
	private List<Employee> listEmployee;
	private List<String> listEmployees;
	private List<String> listPrime;
	private String employer;
	private double taux = 0.0918;
	private boolean tous;
	private String test;
	private List<Avancegestat> listAvenc;
	private Avancegestat selectedAvenc;
	private List<Avancegestat> filtredAvenc;
	private Avancegestat selectedAvance;

	private double nbjour;
	private double avance;
	private Pointage pontage;
	private DecimalFormat df = new DecimalFormat("0.000");
	private Recapulatif recap;

	@PostConstruct
	public void init() {
		try {
			societe = serviceSociete.getAll().get(0);
			applyDefaultPeriod();
		} catch (Exception e) {
			System.err.println("PaisBeans.init(): " + e.getMessage());
		}
	}

	private Pointage resolveLatestPointage() {
		return servicePointage.getMaxPointage();
	}

	private int resolveDefaultPaieYear() {
		Pointage pointage = resolveLatestPointage();
		if (pointage != null && pointage.getAnnee() != null) {
			return pointage.getAnnee();
		}
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	private int resolveDefaultPaieMonth() {
		Pointage pointage = resolveLatestPointage();
		if (pointage != null && pointage.getMois() != null) {
			return pointage.getMois();
		}
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	private void applyDefaultPeriod() {
		annees = String.valueOf(resolveDefaultPaieYear());
		mois = getMoisbyIntger(resolveDefaultPaieMonth());
	}

	private Pointage getLatestPointageOrMessage() {
		Pointage pointage = resolveLatestPointage();
		if (pointage == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Aucun pointage disponible", ""));
		}
		return pointage;
	}

	public String etat() {
		Annee();
		mois();
		applyDefaultPeriod();
		return SUCCESS;
	}

	public String initializeEtatAvance() {
		Annee();
		mois();
		applyDefaultPeriod();
		refreshEtatAvance();
		return SUCCESS;
	}
	
	public String etatPresence() {
		 
		societe = serviceSociete.getAll().get(0);
		Annee();
		applyDefaultPeriod();
		return SUCCESS;
	}
   public void getetatPresence(AjaxBehaviorEvent  event) {
	   listEmployee = new ArrayList<Employee>();
		listEmployee = serviceEmployee.getEmployeeparstats(Status.Declare);
		if(listEmployee!=null)
		for(Employee e:listEmployee) {
			double nbjouannuel = servicePointage.getsumPointageByEmployeeandannee(e,Integer.parseInt(annees));
		      e.setNbjouannuel(nbjouannuel);
		}
	    
   }

	public String modifierfiche() {
		avance = 0;
		Pointage p = getLatestPointageOrMessage();
		if (p == null) {
			return ERROR;
		}
		if (employer == null || employer.trim().isEmpty()) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "chooisir un employer ", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return ERROR;
		}
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, p.getMois() - 1);
		cal.set(Calendar.YEAR, p.getAnnee());
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date date1 = new Date(p.getAnnee() - 1900, p.getMois() - 1, 1, 0, 0, 0);
		Date date2 = new Date(p.getAnnee() - 1900, p.getMois() - 1, maxDay, 23, 0, 0);

		societe = serviceSociete.getAll().get(0);
		Annee();
		Employee e = serviceEmployee.getEmployeeByNom(employer);
		if (e == null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "chooisir un employer ", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return ERROR;
		}
		List<Avancegestat> lista = serviceAvance.getAvancesByEmployeebetweendate(e, date1, date2);
		for (Avancegestat a : lista) {
			avance = avance + a.getMontant_avance();
		}
		listMois = new ArrayList<String>();

		listPaie = new ArrayList<Paie>();
		annees = "" + p.getAnnee();
		mois = getMoisbyIntger(p.getMois());
		// listPaie = servicePaie.getPaieByAnneeAndMois(p.getAnnee(),
		// p.getMois());

		return SUCCESS;
	}

	public String etatannuel() {

		mois();

		Annee();
		listPai = new ArrayList<Recapulatif>();

		listPaie = new ArrayList<Paie>();
		return SUCCESS;
	}

	public String getetatannuel() {
		listpaieGestion = new ArrayList<Lignepaiegestion>();
		listPai = new ArrayList<Recapulatif>();

		listPaie = new ArrayList<Paie>();
		// listPaie = servicePaie.getPaieByAnnee(annee);
		try {
			double salD = 0;
			double salimp = 0;
			double salbrut = 0;
			double cnss = 0;
			double impot = 0;
			double redevance = 0;
			double salnet = 0;
			double accompte = 0;
			double reste = 0;
			double sommeprimes = 0;
			double netp = 0;
			double nbh = 0;
			List<Employee> liste = new ArrayList<Employee>();
			List<Paie> ls = new ArrayList<Paie>();
			ls = servicePaie.getPaieByAnnee(annee);

			if (ls != null)
				for (Paie e : ls) {
					if (!liste.contains(e.getEmployee()))
						if (e.getFormulaire_Paie().getRetenue_cnss() != 0)
							liste.add(e.getEmployee());
				}
			if (liste != null)
				for (Employee em : liste) {
					List<Paie> ls2 = servicePaie.getPaieByAnneeAndEmployee(em, annee);

					salD = 0;
					netp = 0;
					salbrut = 0;
					cnss = 0;
					salnet = 0;
					accompte = 0;
					reste = 0;
					redevance = 0;
					impot = 0;
					salimp = 0;
					nbh = 0;
					Formule_Paie f = new Formule_Paie();
					Paie p = new Paie();
					List<Lignepaiegestion> listpaieGestiont = new ArrayList<Lignepaiegestion>();
					List<Lignepaiegestion> listpaieGestiont2 = new ArrayList<Lignepaiegestion>();
					if (ls2 != null)
						for (Paie e : ls2) {

							e.getEmployee().setLignegestion(ligneGestion.getlistbyemplyee(e.getEmployee()));
							salD = salD + e.getFormulaire_Paie().getSalairedebase();
							netp = netp + e.getFormulaire_Paie().getNet_apayer();
							salbrut = salbrut + e.getFormulaire_Paie().getSalaire_brut();
							cnss = cnss + e.getFormulaire_Paie().getRetenue_cnss();
							salnet = salnet + e.getFormulaire_Paie().getSalaire_net();
							accompte = accompte + e.getFormulaire_Paie().getAvance();
							reste = reste + e.getFormulaire_Paie().getNet_apayer();
							redevance = redevance + e.getFormulaire_Paie().getRedevance();
							impot = impot + e.getFormulaire_Paie().getIrpp();
							salimp = salimp + e.getFormulaire_Paie().getSalaire_imposable();
							nbh = nbh + e.getFormulaire_Paie().getNb_heure();
							e.setListGestion(serviceLigneGestionpaie.getlistbypaie(e));
							if (e.getListGestion() != null)
								for (Lignepaiegestion ge : e.getListGestion()) {
									listpaieGestiont.add(ge);
								}

						}
					List<Gestion> lg = new ArrayList<Gestion>();

					lg = serviceGestion.getAll();
					for (Gestion l : lg) {
						double g = 0;
						if (listpaieGestiont != null)
							for (Lignepaiegestion ge : listpaieGestiont) {
								if (ge.getLignegestion().getGestion().getId() == l.getId()) {
									g = g + ge.getValeurdeprimeafficher();
								}
							}

						Lignepaiegestion gg = new Lignepaiegestion();
						gg.setValeurdeprimeafficher(g);
						gg.setValeurdeprime(l.getValeurdeprime());
						listpaieGestiont2.add(gg);
					}
					f.setSalairedebase(salD);
					f.setNet_apayer(netp);
					f.setSalaire_brut(salbrut);
					f.setRetenue_cnss(cnss);
					f.setSalaire_net(salnet);
					f.setNet_apayer(reste);
					f.setSalaire_imposable(salimp);
					f.setIrpp(impot);
					f.setNb_heure(nbh);
					f.setRedevance(redevance);
					p.setListGestion(listpaieGestiont2);
					p.setFormulaire_Paie(f);
					p.setEmployee(em);
					listPaie.add(p);
				}

		} catch (Exception e) {
			addMessage("erreur de chargement!!");
		}

		return SUCCESS;

	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void validermodificationPaie(AjaxBehaviorEvent event) {
		pontage.setNb_jour(nbjour);
		servicePointage.update(pontage);
		Employee e = serviceEmployee.getEmployeeByNom(employer);
		Pointage p = servicePointage.getMaxPointage();

		listPaie = new ArrayList<Paie>();

		Pointage po = servicePointage.getPointageByEmployee(e, p.getAnnee(), p.getMois());
		Paie pp = servicePaie.getPaieByAnneeAndMoisEmployee(e, po.getAnnee(), po.getMois());
		if (pp != null) {

			double sommeprimes = 0;

			double irpp = 0;

			Formule_Paie f = new Formule_Paie();
			if (po != null) {
				f.setNb_heure(nbjour);
				/*
				 * if (e.getFonction().equals("FEMME DE MENAGE"))
				 * f.setSalairedebase(e.getSalaire_journalier() * 7 * f.getNb_heure()); else
				 */
				f.setSalairedebase(e.getSalaire_journalier() * 8 * f.getNb_heure());
				e.setLignegestion(ligneGestion.getlistbyemplyee(e));

				f.setSalaire_brut(f.getSalairedebase() + sommeprimes);
				f.setRetenue_cnss(f.getSalaire_brut() * taux);
				f.setSalaire_imposable(f.getSalaire_brut() - f.getRetenue_cnss());
				// a voire
				double irpp1 = 0;
				double irpp2 = 0;
				double irpp3 = 0;
				double irpp4 = 0;
				double irpp5 = 0;
				double irpp6 = 0;
				double ir = 0;
				ir = f.getSalaire_imposable() * 12;
				irpp1 = 0.1 * ir;
				if (e.getEtat().equals(Etat.Mariee)) {
					irpp2 = 300.000;

					if (e.getNb_enfant_enCharge() == 1)
						irpp3 = 100.000;
					if (e.getNb_enfant_enCharge() == 2)
						irpp4 = 100.000;
					if (e.getNb_enfant_enCharge() == 3)
						irpp5 = 100.000;
				}
				if (e.getEtat().equals(Etat.Celebataire)) {
					irpp6 = 0;
				}
				irpp = ir - irpp1 - irpp2 - irpp3 - irpp4 - irpp5 - irpp6;
				double irs = calculIRPP(irpp);
				f.setIrpp(irs);
				f.setAvance(avance);

				double r = f.getSalaire_brut() * 12;
				if (p.getAnnee() == 2020 && r > 5000.000) {
					double red = ((r - 5000.000) * 0.01) / 12;
					f.setRedevance(red);
				}
				f.setSalaire_net(f.getSalaire_imposable() - f.getIrpp() - f.getRedevance());

				f.setNet_apayer(f.getSalaire_net() - avance);

				List<Lignegestion> l1 = e.getLignegestion();
				listpaieGestion = new ArrayList<Lignepaiegestion>();
				for (Lignegestion g : l1) {
					Lignepaiegestion lg = new Lignepaiegestion();
					lg.setPaie(pp);
					lg.setLignegestion(g);
					lg.setValeurdeprime(g.getGestion().getValeurdeprime());
					/*
					 * if (e.getFonction().equals("FEMME DE MENAGE"))
					 * lg.setValeurdeprimeafficher(lg.getLignegestion().getGestion().
					 * getValeurdeprime() * pp.getFormulaire_Paie().getNb_heure() * 7); else
					 */
					lg.setValeurdeprimeafficher(lg.getLignegestion().getGestion().getValeurdeprime()
							* pp.getFormulaire_Paie().getNb_heure() * 8);
					serviceLigneGestionpaie.save(lg);
					listpaieGestion.add(lg);
				}
				pp.setListGestion(listpaieGestion);
				listPaie.add(pp);
				pp.setFormulaire_Paie(f);
				pp.setAnnee(p.getAnnee());
				pp.setMois(p.getMois());
				pp.setEmployee(e);
				servicePaie.update(pp);
				listPaie.add(pp);
			}
		}
		Utilisateur user = userservice.getCurrentUser();
		Tracepaie t = new Tracepaie();
		t.setAction("acces au modification de la fiche de" + p.getEmployee().getNom() + " du mois " + p.getMois()
				+ p.getAnnee() + " par" + user.getNom());
		t.setDate(new Date());
		t.setUtilisateur(user);
		serviceTrace.save(t);
	}

	public void etat2(AjaxBehaviorEvent event) {
		refreshEtatAvance();
	}

	private void refreshEtatAvance() {
		if (annees == null || annees.trim().isEmpty()) {
			annees = String.valueOf(resolveDefaultPaieYear());
		}
		if (mois == null || mois.trim().isEmpty()) {
			mois = getMoisbyIntger(resolveDefaultPaieMonth());
		}
		int selectedYear = Integer.parseInt(annees);
		int selectedMonth = getMoisbyString(mois);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, selectedYear);
		cal.set(Calendar.MONTH, selectedMonth - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date date1 = new Date(selectedYear - 1900, selectedMonth - 1, 1, 0, 0, 0);
		Date date2 = new Date(selectedYear - 1900, selectedMonth - 1, maxDay, 23, 59, 59);
		listAvenc = serviceAvance.getAvancesBybetweendate(date1, date2);
		if (listAvenc == null) {
			listAvenc = new ArrayList<Avancegestat>();
		}
	}

	public String Visualiser() {
		return SUCCESS;
	}

	public String virement() {
		listPaie = new ArrayList<Paie>();
		listEmployee = new ArrayList<Employee>();
		listEmployee = serviceEmployee.getEmployeeparnature("Virement");
		Pointage p = getLatestPointageOrMessage();
		if (p == null) {
			return ERROR;
		}
		annees = p.getAnnee() + "";
		mois = getMoisbyIntger(p.getMois());
		for (Employee e : listEmployee) {
			Paie ee = servicePaie.getPaieByAnneeAndMoisEmployee(e, p.getAnnee(), p.getMois());
			if (ee != null) {
				listPaie.add(ee);
			}

		}
		return SUCCESS;
	}

	public List<Avancegestat> getavancebetween(Pointage p, Employee e) {
		// Date date1=new Date();
		// Date date2=new Date();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, p.getMois() - 1);
		cal.set(Calendar.YEAR, p.getAnnee());
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		/*
		 * .setYear(cal.getWeekYear()+1900); date2.setYear(cal.getWeekYear()+1900);
		 */
		/*
		 * date1.setSeconds(0); date1.setMinutes(0); date1.setHours(0);
		 * date1.setMonth(p.getMois()-1); date2.setMonth(p.getMois()-1);
		 * date1.setDate(1); date2.setDate(maxDay); date2.setHours(23);
		 */
		Date date1 = new Date(p.getAnnee() - 1900, p.getMois() - 1, 1, 0, 0, 0);
		Date date2 = new Date(p.getAnnee() - 1900, p.getMois() - 1, maxDay, 23, 0, 0);
		listAvenc = new ArrayList<Avancegestat>();
		listAvenc = serviceAvance.getAvancesByEmployeebetweendate(e, date1, date2);

		for (Avancegestat a : listAvenc) {
		}
		if (listAvenc != null)
			return listAvenc;
		return null;
	}

	public String getAllPai() {
		societe = serviceSociete.getAll().get(0);
		Annee();
		listMois = new ArrayList<String>();

		Pointage p = getLatestPointageOrMessage();
		if (p == null) {
			listPaie = new ArrayList<Paie>();
			test = "notok";
			return ERROR;
		}

		listPaie = new ArrayList<Paie>();
		annees = "" + p.getAnnee();
		mois = getMoisbyIntger(p.getMois());

		listPaie = servicePaie.getPaieByAnneeAndMois(p.getAnnee(), p.getMois());
		if (listPaie != null) {
			test = "ok";
			for (Paie p1 : listPaie) {
				p1.getEmployee().setLignegestion(ligneGestion.getlistbyemplyee(p1.getEmployee()));

				p1.setListGestion(serviceLigneGestionpaie.getlistbypaie(p1));
			}
		} else {
			listPaie = new ArrayList<Paie>();
			test = "notok";
			listEmployee = new ArrayList<Employee>();
			listEmployee = serviceEmployee.getEmployeeparstats(Status.Declare);
			for (Employee e : listEmployee) {
				e.setLignegestion(ligneGestion.getlistbyemplyee(e));
				Pointage po = servicePointage.getPointageByEmployee(e, p.getAnnee(), p.getMois());
				// if (servicePaie.getPaieByAnneeAndMoisEmployee(e, po.getAnnee(), po.getMois())
				// != null) {
				try {
					if (nbOfMonthsBetweenTwoDates(e.getContrat().getDate(), new Date()) == 24) {
						if (e.getContrat() != null) {
							Integer cat = e.getContrat().getCat().getValeur();
							Integer degree = e.getContrat().getDegree().getValeur();
							double diff = 0;
							// cat=cat+1;
							if (degree < 15)
								degree = degree + 1;
							else if (degree == 15) {
								cat = cat + 1;
								degree = 1;
								diff = serviceCategorie
										.findbydegreeandcat(e.getContrat().getCat().getValeur(),
												e.getContrat().getDegree().getValeur())
										.getPrix_heur()
										- serviceCategorie.findbydegreeandcat(cat, degree).getPrix_heur();
								while (diff > 0) {
									degree = degree + 1;
									diff = serviceCategorie
											.findbydegreeandcat(e.getContrat().getCat().getValeur(),
													e.getContrat().getDegree().getValeur())
											.getPrix_heur()
											- serviceCategorie.findbydegreeandcat(cat, degree).getPrix_heur();

								}

							}
							Categorie c = serviceCategorie.findbydegreeandcat(cat, degree + 1);
							e.setContrat(c);

							e.setSalaire_journalier(c.getPrix_heur());
							serviceEmployee.update(e);
						}

					}
				} catch (Exception ee) {
				}

				double sommeprimes = 0;

				double irpp = 0;

				double avances = 0;
				try {
					List<Avancegestat> a = this.getavancebetween(p, e);
					if (a != null) {
						for (Avancegestat aa : a)
							avances = avances + aa.getMontant_avance();
					} else
						avances = 0;
				} catch (Exception ee) {
				}
				Formule_Paie f = new Formule_Paie();
				if (po != null) {

					f.setNb_heure(po.getNb_jour());
					if (e.getFonction().equals("FEMME DE MENAGE"))
						f.setSalairedebase(e.getSalaire_journalier() * 7 * f.getNb_heure());
					else
						f.setSalairedebase(e.getSalaire_journalier() * 8 * f.getNb_heure());

					List<Lignegestion> l1 = e.getLignegestion();
					listpaieGestion = new ArrayList<Lignepaiegestion>();
					for (Lignegestion g : l1) {
						Lignepaiegestion lg = new Lignepaiegestion();

						lg.setLignegestion(g);
						lg.setValeurdeprime(g.getGestion().getValeurdeprime());
						if (e.getFonction().equals("FEMME DE MENAGE"))
							lg.setValeurdeprimeafficher(lg.getValeurdeprime() * f.getNb_heure() * 7);
						else
							lg.setValeurdeprimeafficher(lg.getValeurdeprime() * f.getNb_heure() * 8);
						// serviceLigneGestionpaie.save(lg);
						sommeprimes = sommeprimes + lg.getValeurdeprimeafficher();
						listpaieGestion.add(lg);
					}
					double supp=e.getSalaire_sup()*f.getNb_heure()*8;
					f.setSalairesup(supp);
					f.setSalaire_brut(f.getSalairedebase() + sommeprimes+supp);
					f.setRetenue_cnss(f.getSalaire_brut() * taux);
					f.setSalaire_imposable(f.getSalaire_brut() - f.getRetenue_cnss());
					// a voire
					double irpp1 = 0;
					double irpp2 = 0;
					double irpp3 = 0;
					double irpp4 = 0;
					double irpp5 = 0;
					double irpp6 = 0;
					double irpp7 = 0;
					double ir = 0;
					ir = f.getSalaire_imposable() * 12;
					 
					if(ir*0.1<2000)
						irpp1 = 0.1 * ir;
					else 
						irpp1=2000;
					if (e.getEtat().equals(Etat.Mariee)) {
						irpp2 = 300.000;

						if (e.getNb_enfant_enCharge() == 1)
							irpp3 = 100.000;
						if (e.getNb_enfant_enCharge() == 2)
							irpp4 = 200.000;
						if (e.getNb_enfant_enCharge() == 3)
							irpp5 = 300.000; 						
						if (e.getNb_enfant_enCharge() == 4)
							irpp7 = 400.000;
						
						
					}
					if (e.getEtat().equals(Etat.Celebataire)) {
						irpp6 = 0;
					}
					irpp = ir - irpp1 - irpp2 - irpp3 - irpp4 - irpp5 - irpp6-irpp7;
					 
					double irs = calculIRPP(irpp);
					f.setIrpp(irs);
				 
						double red = (irpp  * 0.01) / 12;
						f.setRedevance(red);
					
					f.setSalaire_net(f.getSalaire_imposable() - f.getIrpp() - f.getRedevance());
					f.setAvance(avances);
					f.setNet_apayer(f.getSalaire_net() - avances);
					Paie p2 = new Paie();

					p2.setFormulaire_Paie(f);
					p2.setAnnee(p.getAnnee());
					p2.setMois(p.getMois());
					p2.setEmployee(e);

					p2.setListGestion(listpaieGestion);

					listPaie.add(p2);
				}

			}
		}

		Utilisateur user = userservice.getCurrentUser();
		Tracepaie t = new Tracepaie();
		t.setAction("creation du paie" + p.getMois() + p.getAnnee() + " par" + user.getNom());
		t.setDate(new Date());
		t.setUtilisateur(user);
		serviceTrace.save(t);
		return SUCCESS;

	}

	public int nbOfMonthsBetweenTwoDates(Date date1, Date date2) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// Date date1 = sdf.parse(dateString1);
		GregorianCalendar gc1 = new GregorianCalendar();
		gc1.setTime(date1);
		// Date date2 = sdf.parse(dateString2);
		GregorianCalendar gc2 = new GregorianCalendar();
		gc2.setTime(date2);
		int gap = 0;
		gc1.add(GregorianCalendar.MONTH, 1);
		while (gc1.compareTo(gc2) <= 0) {
			gap++;
			gc1.add(GregorianCalendar.MONTH, 1);
		}
		return gap;
	}

	public String getAllPai2() {
		annees = "";
		mois = "";
		societe = serviceSociete.getAll().get(0);
		Annee();
		listPaie = new ArrayList<Paie>();

		return SUCCESS;
	}

	/*
	 * 
	 * 
	 * else { listPaie = new ArrayList<Paie>(); test = "notok"; listEmployee = new
	 * ArrayList<Employee>(); listEmployee =
	 * serviceEmployee.getEmployeeparstats(Status.Declare); for (Employee e :
	 * listEmployee) { e.setLignegestion(ligneGestion.getlistbyemplyee(e)); Pointage
	 * po = servicePointage.getPointageByEmployee(e, p.getAnnee(), p.getMois()); //
	 * if (servicePaie.getPaieByAnneeAndMoisEmployee(e, po.getAnnee(), po.getMois())
	 * != null) { try { if (nbOfMonthsBetweenTwoDates(e.getContrat().getDate(), new
	 * Date()) == 24) { if (e.getContrat() != null) { Integer cat =
	 * e.getContrat().getCat().getValeur(); Integer degree =
	 * e.getContrat().getDegree().getValeur(); double diff = 0; // cat=cat+1; if
	 * (degree < 15) degree = degree + 1; else if (degree == 15) { cat = cat + 1;
	 * degree = 1; diff = serviceCategorie
	 * .findbydegreeandcat(e.getContrat().getCat().getValeur(),
	 * e.getContrat().getDegree().getValeur()) .getPrix_heur() -
	 * serviceCategorie.findbydegreeandcat(cat, degree).getPrix_heur(); while (diff
	 * > 0) { degree = degree + 1; diff = serviceCategorie
	 * .findbydegreeandcat(e.getContrat().getCat().getValeur(),
	 * e.getContrat().getDegree().getValeur()) .getPrix_heur() -
	 * serviceCategorie.findbydegreeandcat(cat, degree).getPrix_heur();
	 * 
	 * }
	 * 
	 * } Categorie c = serviceCategorie.findbydegreeandcat(cat, degree + 1);
	 * e.setContrat(c);
	 * 
	 * e.setSalaire_journalier(c.getPrix_heur()); serviceEmployee.update(e); }
	 * 
	 * 
	 * double sommeprimes = 0;
	 * 
	 * double irpp = 0;
	 * 
	 * double avances = 0; List<Avance> a = serviceAvance.getAvancesByEmployee(e,
	 * p.getAnnee(), p.getMois()); if (a != null) { for (Avance aa : a) avances =
	 * avances + aa.getMontant_avance(); }
	 * 
	 * Formule_Paie f = new Formule_Paie(); if (po != null) {
	 * 
	 * f.setNb_heure(po.getNb_jour()); if
	 * (e.getFonction().equals("FEMME DE MENAGE"))
	 * f.setSalairedebase(e.getSalaire_journalier() * 6 * f.getNb_heure()); else
	 * f.setSalairedebase(e.getSalaire_journalier() * 8 * f.getNb_heure());
	 * 
	 * 
	 * f.setSalaire_brut(f.getSalairedebase() + sommeprimes);
	 * f.setRetenue_cnss(f.getSalaire_brut() * taux);
	 * f.setSalaire_imposable(f.getSalaire_brut() - f.getRetenue_cnss()); // a voire
	 * double irpp1 = 0; double irpp2 = 0; double irpp3 = 0; double irpp4 = 0;
	 * double irpp5 = 0; double irpp6 = 0; double ir = 0; ir =
	 * f.getSalaire_imposable() * 12; irpp1 = 0.1 * ir; if
	 * (e.getEtat().equals(Etat.Mariee)) { irpp2 = 150.000;
	 * 
	 * if (e.getNb_enfant_enCharge() == 1) irpp3 = 90.000; if
	 * (e.getNb_enfant_enCharge() == 2) irpp4 = 75.000; if
	 * (e.getNb_enfant_enCharge() == 3) irpp5 = 60.000; } if
	 * (e.getEtat().equals(Etat.Celebataire)) { irpp6 = 0; } irpp = ir - irpp1 -
	 * irpp2 - irpp3 - irpp4 - irpp5 - irpp6; double irs = calculIRPP(irpp);
	 * f.setIrpp(irs); double r = f.getSalaire_brut() * 12; if (p.getAnnee() == 2018
	 * && r > 5000.000) { double red = ((r - 5000.000) * 0.01) / 12;
	 * f.setRedevance(red); } f.setSalaire_net(f.getSalaire_imposable() -
	 * f.getIrpp() - f.getRedevance()); f.setAvance(avances);
	 * f.setNet_apayer(f.getSalaire_net() - avances); Paie p2 = new Paie();
	 * 
	 * p2.setFormulaire_Paie(f); p2.setAnnee(p.getAnnee()); p2.setMois(p.getMois());
	 * p2.setEmployee(e);
	 * 
	 * List<Lignegestion> l1=e.getLignegestion(); listpaieGestion = new
	 * ArrayList<Lignepaiegestion>(); for(Lignegestion g:l1) { Lignepaiegestion
	 * lg=new Lignepaiegestion(); lg.setPaie(p2); lg.setLignegestion(g);
	 * lg.setValeurdeprime(g.getGestion().getValeurdeprime()); if
	 * (e.getFonction().equals("FEMME DE MENAGE"))
	 * lg.setValeurdeprimeafficher(lg.getValeurdeprime()*p2.getFormulaire_Paie().
	 * getNb_heure()*6); else
	 * lg.setValeurdeprimeafficher(lg.getValeurdeprime()*p2.getFormulaire_Paie().
	 * getNb_heure()*8); serviceLigneGestionpaie.save(lg); listpaieGestion.add(lg);
	 * } p2.setListGestion(listpaieGestion); listPaie.add(p2); }
	 * 
	 * } }
	 */

	public void traixiemeMois(AjaxBehaviorEvent evant) {

		societe = serviceSociete.getAll().get(0);
		listPaie = new ArrayList<Paie>();
		listPaie = servicePaie.getPaieByAnneeAndMois(Integer.parseInt(annees), 13);
		if (listPaie == null) {

			listPaie = new ArrayList<Paie>();
			listEmployee = new ArrayList<Employee>();
			listEmployee = serviceEmployee.getEmployeeparstats(Status.Declare);
			for (Employee e : listEmployee) {
				List<Lignegestion> listGestion = new ArrayList<Lignegestion>();
				listGestion = ligneGestion.getlistbyemplyee(e);
				double nb_heure = 0;
				double irpp = 0;
				double sommeprimes = 0;
				nb_heure = 26;
				Formule_Paie f = new Formule_Paie();
				f.setNb_heure(nb_heure);
				/*
				 * if (e.getFonction().equals("FEMME DE MENAGE"))
				 * f.setSalairedebase(e.getSalaire_journalier() * 7 * nb_heure); else
				 */
				f.setSalairedebase(e.getSalaire_journalier() * 8 * nb_heure);
				for (Lignegestion g : listGestion) {
					g.setValeurdeprimeafficher(nb_heure * g.getGestion().getValeurdeprime() * 8);
					sommeprimes = sommeprimes + g.getValeurdeprimeafficher();
				}
				Note n = serviceNote.getNotesByEmployee(e, Integer.parseInt(annees));
				f.setSalaire_brut(f.getSalairedebase() + sommeprimes);
				// f.setTraixieme(f.getSalaire_brut()+n);
				f.setRetenue_cnss(f.getSalaire_brut() * taux);
				f.setSalaire_imposable(f.getSalaire_brut() - f.getRetenue_cnss());
				// a voire
				if (e.getEtat().equals(Etat.Mariee)) {

					if (e.getNb_enfant_enCharge() == 0)
						irpp = (((f.getSalaire_imposable() * 12) - 150 - (0.1 * (f.getSalaire_imposable() * 12))));
					if (e.getNb_enfant_enCharge() == 1)
						irpp = (((f.getSalaire_imposable() * 12) - 240 - (0.1 * (f.getSalaire_imposable() * 12))));
					if (e.getNb_enfant_enCharge() == 2)
						irpp = (((f.getSalaire_imposable() * 12) - 315 - (0.1 * (f.getSalaire_imposable() * 12))));
					if (e.getNb_enfant_enCharge() == 3)
						irpp = (((f.getSalaire_imposable() * 12) - 150 - 90 - 75 - 60
								- (0.1 * (f.getSalaire_imposable() * 12))));
				} else if (e.getEtat().equals(Etat.Celebataire))
					irpp = (((f.getSalaire_imposable() * 12) - (0.1 * (f.getSalaire_imposable() * 12))));

				double ir = calculIRPP(irpp);
				f.setIrpp(ir);
				f.setSalaire_net(f.getSalaire_brut() - f.getRetenue_cnss() - f.getIrpp());

				f.setNet_apayer(f.getSalaire_net() * n.getNote() / 20);

				Paie p = new Paie();
				p.setMois(13);
				p.setFormulaire_Paie(f);
				p.setEmployee(e);
				p.setAnnee(Integer.parseInt(annees));
				servicePaie.save(p);
				listPaie.add(p);
			}
		}

	}

	public void getMoisByAnnees(AjaxBehaviorEvent event) {
		listMois = new ArrayList<String>();
		List<Pointage> l = new ArrayList<Pointage>();
		l = servicePointage.getPointageByAnnee(Integer.parseInt(annees));
		if (l != null)
			for (Pointage p : l)
				if (!listMois.contains(getMoisbyIntger(p.getMois())))
					listMois.add(getMoisbyIntger(p.getMois()));
		listPaie = new ArrayList<Paie>();
		mois = "";
	}

	public void getPaiBymoiAndAnnee(AjaxBehaviorEvent event) {
		listPaie = new ArrayList<Paie>();

		listPaie = servicePaie.getPaieByAnneeAndMois(Integer.parseInt(annees), getMoisbyString(mois));

		if (listPaie != null) {
			test = "ok";
			for (Paie p1 : listPaie) {
				List<Lignegestion> listGestion = new ArrayList<Lignegestion>();
				listGestion = ligneGestion.getlistbyemplyee(p1.getEmployee());
				p1.setListGestion(serviceLigneGestionpaie.getlistbypaie(p1));
			}
		} else {
			test = "notok";
			listPaie = new ArrayList<Paie>();
			listEmployee = new ArrayList<Employee>();
			listEmployee = serviceEmployee.getEmployeeparstats(Status.Declare);

			for (Employee e : listEmployee) {
				Pointage po = servicePointage.getPointageByEmployee(e, Integer.parseInt(annees), getMoisbyString(mois));
				double sommeprimes = 0;
				List<Lignegestion> listGestion = new ArrayList<Lignegestion>();
				listGestion = ligneGestion.getlistbyemplyee(e);
				double irpp = 0;
				double avances = 0;
				List<Avancegestat> a = this.getavancebetween(po, e);
				if (a != null) {
					for (Avancegestat aa : a)
						avances = avances + aa.getMontant_avance();
				}

				Formule_Paie f = new Formule_Paie();

				f.setNb_heure(po.getNb_jour());
				/*
				 * if (e.getFonction().equals("FEMME DE MENAGE"))
				 * f.setSalairedebase(e.getSalaire_journalier() * 7 * f.getNb_heure()); else
				 */
				f.setSalairedebase(e.getSalaire_journalier() * 8 * f.getNb_heure());
				for (Lignegestion g : listGestion) {
					g.setValeurdeprimeafficher(f.getNb_heure() * g.getGestion().getValeurdeprime() * 8);
					sommeprimes = sommeprimes + g.getValeurdeprimeafficher();
				}

				f.setSalaire_brut(f.getSalairedebase() + sommeprimes);
				f.setRetenue_cnss(f.getSalaire_brut() * taux);
				f.setSalaire_imposable(f.getSalaire_brut() - f.getRetenue_cnss());
				// a voire
				if (e.getEtat().equals(Etat.Mariee)) {

					if (e.getNb_enfant_enCharge() == 0)
						irpp = (((f.getSalaire_imposable() * 12) - 150 - (0.1 * (f.getSalaire_imposable() * 12))));
					if (e.getNb_enfant_enCharge() == 1)
						irpp = (((f.getSalaire_imposable() * 12) - 240 - (0.1 * (f.getSalaire_imposable() * 12))));
					if (e.getNb_enfant_enCharge() == 2)
						irpp = (((f.getSalaire_imposable() * 12) - 315 - (0.1 * (f.getSalaire_imposable() * 12))));
					if (e.getNb_enfant_enCharge() == 3)
						irpp = (((f.getSalaire_imposable() * 12) - 150 - 90 - 75 - 60
								- (0.1 * (f.getSalaire_imposable() * 12))));
				} else if (e.getEtat().equals(Etat.Celebataire))
					irpp = (((f.getSalaire_imposable() * 12) - (0.1 * (f.getSalaire_imposable() * 12))));

				double ir = calculIRPP(irpp);
				f.setIrpp(ir);
				double r = f.getSalaire_brut() * 12;
				if (Integer.parseInt(annees) == 2020 && r > 5000.000) {
					double red = ((r - 5000.000) * 0.01) / 12;
					f.setRedevance(red);
				}
				f.setSalaire_net(f.getSalaire_imposable() - f.getIrpp() - f.getRedevance());
				f.setAvance(avances);
				f.setAvance(avances);
				f.setNet_apayer(f.getSalaire_net() - avances);

				Paie p = new Paie();

				p.setFormulaire_Paie(f);
				p.setAnnee(Integer.parseInt(annees));
				p.setMois(getMoisbyString(mois));
				p.setEmployee(e);
				List<Lignegestion> l1 = e.getLignegestion();
				listpaieGestion = new ArrayList<Lignepaiegestion>();
				for (Lignegestion g : l1) {
					Lignepaiegestion lg = new Lignepaiegestion();
					lg.setPaie(p);
					lg.setLignegestion(g);
					lg.setValeurdeprime(g.getGestion().getValeurdeprime());
					if (e.getFonction().equals("FEMME DE MENAGE"))
						lg.setValeurdeprimeafficher(lg.getValeurdeprime() * p.getFormulaire_Paie().getNb_heure() * 7);
					else
						lg.setValeurdeprimeafficher(lg.getValeurdeprime() * p.getFormulaire_Paie().getNb_heure() * 8);
					serviceLigneGestionpaie.save(lg);
					listpaieGestion.add(lg);
				}
				p.setListGestion(listpaieGestion);
				listPaie.add(p);
			}

		}
	}

	public void getPaiBymoiAndAnnees(AjaxBehaviorEvent event) {
		listPaie = new ArrayList<Paie>();
		listPaie = servicePaie.getPaieNondeclareByAnneeAndMois(Integer.parseInt(annees), getMoisbyString(mois));

		if (listPaie != null) {
			test = "ok";

		} else {
			test = "notok";
			listPaie = new ArrayList<Paie>();

			listEmployee = new ArrayList<Employee>();
			listEmployee = serviceEmployee.getEmployeeparstats(Status.NonDeclaree);
			for (Employee e : listEmployee) {
				Pointage po = servicePointage.getPointageByEmployee(e, Integer.parseInt(annees), getMoisbyString(mois));

				double avances = 0;
				List<Avancegestat> a = this.getavancebetween(po, e);
				if (a == null)
					avances = 0;
				else {
					for (Avancegestat aa : a)
						avances = avances + aa.getMontant_avance();
				}
				Formule_Paie f = new Formule_Paie();
				f.setNb_heure(po.getNb_jour());
				f.setSalairedebase(e.getSalaire_journalier() * f.getNb_heure());
				f.setAvance(avances);
				f.setNet_apayer(f.getSalairedebase() - avances);
				Paie p = new Paie();
				p.setFormulaire_Paie(f);
				p.setAnnee(Integer.parseInt(annees));
				p.setMois(getMoisbyString(mois));
				p.setEmployee(e);
				listPaie.add(p);
			}

		}
	}

	public String getAllFicchePaie() {
		employer = null;
		societe = serviceSociete.getAll().get(0);
		Annee();
		mois();
		applyDefaultPeriod();
		listEmployees = new ArrayList<String>();
		listEmployee = new ArrayList<Employee>();
		listEmployee = serviceEmployee.getEmployeeparstats(Status.Declare);

		tous = true;
		listPaie = new ArrayList<Paie>();
		Pointage p = resolveLatestPointage();
		if (p != null) {
			listPaie = servicePaie.getPaieByAnneeAndMois(p.getAnnee(), p.getMois());
			annees = String.valueOf(p.getAnnee());
			mois = getMoisbyIntger(p.getMois());
		}
		if (listPaie == null) {
			listPaie = servicePaie.getPaieByAnnee(Integer.parseInt(annees));
		}
		if (listPaie == null) {
			listPaie = new ArrayList<Paie>();
		}

		for (Paie p1 : listPaie) {
			p1.getEmployee().setLignegestion(ligneGestion.getlistbyemplyee(p1.getEmployee()));
			p1.setListGestion(serviceLigneGestionpaie.getlistbypaie(p1));
		}
		mois = null;
		return SUCCESS;
	}

	/***********************************
	 * traixiemeMoi
	 ****************************************/
	public String traixiemeMoi() {
		listPai = new ArrayList<Recapulatif>();
		societe = serviceSociete.getAll().get(0);
		Annee();
		applyDefaultPeriod();
		listPaie = new ArrayList<Paie>();
		return SUCCESS;
	}

public String journal() {
		try {
			listPai = new ArrayList<Recapulatif>();
			societe = serviceSociete.getAll().get(0);
			Annee();
			applyDefaultPeriod();
			listPaie = new ArrayList<Paie>();
			listMois = new ArrayList<String>();
			return SUCCESS;
		} catch (Exception e) {
			System.err.println("Error in PaisBeans.journal(): " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public String journaltotal() {
		listPai = new ArrayList<Recapulatif>();
		societe = serviceSociete.getAll().get(0);
		Annee();
		applyDefaultPeriod();
		listPaie = new ArrayList<Paie>();
		listMois = new ArrayList<String>();
		return SUCCESS;
	}

	public void journal2(AjaxBehaviorEvent event) {
		listPaie = new ArrayList<Paie>();
		List<Paie> l = new ArrayList<Paie>();
		l = servicePaie.getPaieByAnneeAndMois(Integer.parseInt(annees), getMoisbyString(mois));
		listpaieGestion = new ArrayList<Lignepaiegestion>();
		for (Paie p1 : l) {

			if (p1.getFormulaire_Paie().getRetenue_cnss() != 0
					|| (p1.getFormulaire_Paie().getAvance() != 0 && p1.getFormulaire_Paie().getNb_heure() == 0)) {
				// if(p1.getFormulaire_Paie().getAvance()!=0 &&
				// p1.getEmployee().getStatus().equals(Status.Declare))
				p1.getEmployee().setLignegestion(ligneGestion.getlistbyemplyee(p1.getEmployee()));
				p1.setListGestion(serviceLigneGestionpaie.getlistbypaie(p1));
				for (Lignepaiegestion pp : p1.getListGestion())
					listpaieGestion.add(pp);
				listPaie.add(p1);

			}
		}

		listPai = new ArrayList<Recapulatif>();
		Formule_Paie f = new Formule_Paie();
		double irpp = 0;

		double avances = 0;
		double sb = 0;
		double sbr = 0;
		double si = 0;
		double sn = 0;
		double rtn = 0;
		double nap = 0;
		double nb_h = 0;
		double red = 0;
		for (Paie e : listPaie) {

			nb_h = nb_h + e.getFormulaire_Paie().getNb_heure();
			avances = avances + e.getFormulaire_Paie().getAvance();
			sb = sb + e.getFormulaire_Paie().getSalairedebase();
			red = red + e.getFormulaire_Paie().getRedevance();
			sbr = sbr + e.getFormulaire_Paie().getSalaire_brut();
			rtn = rtn + e.getFormulaire_Paie().getRetenue_cnss();
			si = si + e.getFormulaire_Paie().getSalaire_brut();
			irpp = irpp + e.getFormulaire_Paie().getIrpp();
			sn = sn + e.getFormulaire_Paie().getSalaire_net();
			nap = nap + e.getFormulaire_Paie().getNet_apayer();
		}
		f.setNb_heure(nb_h);
		f.setRedevance(red);
		f.setAvance(avances);

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
		r.setMois(getMoisbyString(mois));
		r.setFormul(f);
		List<Lignepaiegestion> lg2 = new ArrayList<Lignepaiegestion>();
		List<Gestion> lg = new ArrayList<Gestion>();
		lg = serviceGestion.getAll();
		DecimalFormat df = new DecimalFormat("0.000");
		for (Gestion ll : lg) {
			double total = 0;
			for (Lignepaiegestion e : listpaieGestion) {
				if (e.getLignegestion().getGestion().getId().equals(ll.getId())) {
					total = total + e.getValeurdeprimeafficher();

				}
			}
			Lignepaiegestion lgg = new Lignepaiegestion();
			lgg.setTotalaffichers(df.format(total));
			lg2.add(lgg);
		}
		r.setListGestion(lg2);

		listPai.add(r);

	}

	private Recapulatif recap1;
	private Recapulatif recap2;

	public void journal3(AjaxBehaviorEvent event) {
		listPaie = new ArrayList<Paie>();
		listPaie = servicePaie.getPaieByAnneeAndMois(Integer.parseInt(annees), getMoisbyString(mois));
		Formule_Paie f = new Formule_Paie();
		double avances = 0;
		double sn = 0;
		double nap = 0;
		for (Paie e : listPaie) {
			avances = avances + e.getFormulaire_Paie().getAvance();
			sn = sn + e.getFormulaire_Paie().getSalaire_net();
			nap = nap + e.getFormulaire_Paie().getNet_apayer();
		}
		f.setAvance(avances);
		f.setSalaire_net(sn);
		f.setNet_apayer(nap);
		recap1 = new Recapulatif();
		recap1.setAnnee(Integer.parseInt(annees));
		recap1.setMois(getMoisbyString(mois));

		listPaie = new ArrayList<Paie>();
		listPaie = servicePaie.getPaieNondeclareByAnneeAndMois(Integer.parseInt(annees), getMoisbyString(mois));
		Formule_Paie f2 = new Formule_Paie();
		double avance = 0;
		double sn2 = 0;
		double nap2 = 0;
		for (Paie e : listPaie) {
			avance = avance + e.getFormulaire_Paie().getAvance();
			sn2 = sn2 + e.getFormulaire_Paie().getSalairedebase();
			nap2 = nap2 + e.getFormulaire_Paie().getNet_apayer();
		}
		f2.setAvance(avance);
		f2.setNet_apayer(nap2);
		f2.setSalaire_net(sn2);
		recap2 = new Recapulatif();
		recap2.setAnnee(Integer.parseInt(annees));
		recap2.setMois(getMoisbyString(mois));
		f.setIrpp(f.getSalaire_net() + f2.getSalaire_net());
		f.setRetenue_cnss(f.getAvance() + f2.getAvance());
		f.setSalaire_brut(f.getNet_apayer() + f2.getNet_apayer());
		recap2.setFormul(f2);
		recap1.setFormul(f);

	}

	public String journalNondeclare() {
		listPai = new ArrayList<Recapulatif>();
		societe = serviceSociete.getAll().get(0);
		Annee();
		applyDefaultPeriod();
		listPaie = new ArrayList<Paie>();
		listMois = new ArrayList<String>();
		return SUCCESS;
	}

	public String journalNondeclareparrendement() {
		listPai = new ArrayList<Recapulatif>();
		societe = serviceSociete.getAll().get(0);
		Annee();
		applyDefaultPeriod();
		listPaie = new ArrayList<Paie>();
		listMois = new ArrayList<String>();
		return SUCCESS;
	}
	public void getPaiBymoiAndAnnees2(AjaxBehaviorEvent event) {
		listPaie = new ArrayList<Paie>();
		listPaie = servicePaie.getPaieNondeclare2ByAnneeAndMois(Integer.parseInt(annees), getMoisbyString(mois));
	 
		Pointage pp = resolveLatestPointage();
		int selectedMonth = getMoisbyString(mois);
		if (selectedMonth == 0) {
			selectedMonth = resolveDefaultPaieMonth();
		}
		String d1= "01-"+selectedMonth+"-"+annees;
		String d2=getNbJourbyString(selectedMonth)+"-"+selectedMonth+"-"+annees;
		if (listPaie != null) {
			test = "ok";

		} else {
			test = "notok";
			listPaie = new ArrayList<Paie>();

			listEmployee = new ArrayList<Employee>();
			listEmployee = serviceEmployee.getEmployeeparstats(Status.ParVoiture);
			if (listEmployee != null)
				for (Employee e : listEmployee) {
					Pointage po = new Pointage();
					po.setEmployee(e);
					po.setNb_jour(servicerendement.getnbvbetwendate(d1, d2, e));
					po.setAnnee(Integer.parseInt(annees));
					po.setMois(selectedMonth);
					 servicePointage.save(po);
					// Pointage po = servicePointage.getPointageByEmployee(e,
					// Integer.parseInt(annees), getMoisbyString(mois));

					double avances = 0;
					List<Avancegestat> a = this.getavancebetween(po, e);
					if (a == null)
						avances = 0;
					else {
						for (Avancegestat aa : a)
							avances = avances + aa.getMontant_avance();
					}
					Formule_Paie f = new Formule_Paie();
					f.setNb_heure(po.getNb_jour());
					f.setSalairedebase(avances);
					f.setAvance(avances);
					f.setNet_apayer(0);
					Paie p = new Paie();
					p.setFormulaire_Paie(f);
					p.setAnnee(Integer.parseInt(annees));
					p.setMois(getMoisbyString(mois));
					p.setEmployee(e);
					listPaie.add(p);
				}

		}
	}
	

	public String getAllPai3() {
		test = "notok";
		societe = serviceSociete.getAll().get(0);
		Annee();
		applyDefaultPeriod();
		listPaie = new ArrayList<Paie>();

		return SUCCESS;
	}

	public void getPaiBymoiAndAnnee4(AjaxBehaviorEvent event) {
		listPaie = new ArrayList<Paie>();

		listPaie = servicePaie.getPaieNondeclare2ByAnneeAndMois(Integer.parseInt(annees), getMoisbyString(mois));

		listPai = new ArrayList<Recapulatif>();
		Formule_Paie f = new Formule_Paie();

		double avances = 0;
		double sb = 0;
		double sbr = 0;
		double si = 0;
		double sn = 0;

		double nap = 0;
		if (listPaie != null)
			for (Paie e : listPaie) {

				avances = avances + e.getFormulaire_Paie().getAvance();
				sb = sb + e.getFormulaire_Paie().getSalairedebase();

				sn = sn + e.getFormulaire_Paie().getSalaire_net();
				nap = nap + e.getFormulaire_Paie().getNet_apayer();
			}
		f.setAvance(avances);

		f.setNet_apayer(nap);
		f.setSalairedebase(sb);
		f.setSalaire_net(sn);
		Recapulatif r = new Recapulatif();
		r.setAnnee(Integer.parseInt(annees));
		r.setMois(getMoisbyString(mois));
		r.setFormul(f);
		listPai.add(r);

	}

	public void getPaiBymoiAndAnnee3(AjaxBehaviorEvent event) {
		listPaie = new ArrayList<Paie>();

		listPaie = servicePaie.getPaieNondeclareByAnneeAndMois(Integer.parseInt(annees), getMoisbyString(mois));

		listPai = new ArrayList<Recapulatif>();
		Formule_Paie f = new Formule_Paie();

		double avances = 0;
		double sb = 0;
		double sbr = 0;
		double si = 0;
		double sn = 0;

		double nap = 0;
		if (listPaie != null)
			for (Paie e : listPaie) {

				avances = avances + e.getFormulaire_Paie().getAvance();
				sb = sb + e.getFormulaire_Paie().getSalairedebase();

				sn = sn + e.getFormulaire_Paie().getSalaire_net();
				nap = nap + e.getFormulaire_Paie().getNet_apayer();
			}
		f.setAvance(avances);

		f.setNet_apayer(nap);
		f.setSalairedebase(sb);
		f.setSalaire_net(sn);
		Recapulatif r = new Recapulatif();
		r.setAnnee(Integer.parseInt(annees));
		r.setMois(getMoisbyString(mois));
		r.setFormul(f);
		listPai.add(r);

	}

	public void getPaiebyEmployer(AjaxBehaviorEvent event) {
		avance = 0;
		employee = serviceEmployee.getEmployeeByNom(employer);
		Pointage p = resolveLatestPointage();

		listPaie = new ArrayList<Paie>();
		if (employee == null) {
			String message = "Veuillez choisir un employe";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
			return;
		}

		int targetYear = p != null ? p.getAnnee() : Calendar.getInstance().get(Calendar.YEAR);
		if (annees != null && !annees.trim().isEmpty()) {
			try {
				targetYear = Integer.parseInt(annees);
			} catch (NumberFormatException ex) {
				// Keep fallback year from latest pointage/current year.
			}
		}

		if (mois != null && !mois.trim().isEmpty()) {
			Paie pp = servicePaie.getPaieByAnneeAndMoisEmployee(employee, targetYear, getMoisbyString(mois));
			if (pp != null) {
				listPaie.add(pp);
			}
		}
		if (mois == null || mois.trim().isEmpty()) {
			listPaie = servicePaie.getPaieByAnneeAndEmployee(employee, targetYear);
			if (listPaie == null) {
				listPaie = new ArrayList<Paie>();
			}
		}
		for (Paie p1 : listPaie) {
			p1.getEmployee().setLignegestion(ligneGestion.getlistbyemplyee(p1.getEmployee()));
			p1.setListGestion(serviceLigneGestionpaie.getlistbypaie(p1));

		}

	}

	public void getPaiebyEmployers(AjaxBehaviorEvent event) {

		listPaie = new ArrayList<Paie>();

		Pointage p = resolveLatestPointage();
		if (p != null) {
			listPaie = servicePaie.getPaieByAnneeAndMois(p.getAnnee(), p.getMois());
		}
		if (listPaie == null) {
			listPaie = servicePaie.getPaieByAnnee(Integer.parseInt(annees));
		}
		if (listPaie == null) {
			listPaie = new ArrayList<Paie>();
		}

		for (Paie p1 : listPaie) {

			p1.getEmployee().setLignegestion(ligneGestion.getlistbyemplyee(p1.getEmployee()));
			p1.setListGestion(serviceLigneGestionpaie.getlistbypaie(p1));

		}

	}

	public String recapulatif() {
		societe = serviceSociete.getAll().get(0);
		Annee();
		applyDefaultPeriod();
		listPaie = new ArrayList<Paie>();
		listMois = new ArrayList<String>();
		return SUCCESS;
	}

	private List<Recapulatif> listPai;

	public void getPaiBymoiAndAnnee2(AjaxBehaviorEvent event) {
		listPaie = new ArrayList<Paie>();

		listPai = new ArrayList<Recapulatif>();
		listPaie = servicePaie.getPaieByAnneeAndMois(Integer.parseInt(annees), getMoisbyString(mois));

		Formule_Paie f = new Formule_Paie();
		double irpp = 0;

		double avances = 0;
		double sb = 0;
		double sbr = 0;
		double si = 0;
		double sn = 0;
		double rtn = 0;
		double nap = 0;
		if (listPaie != null)
			for (Paie e : listPaie) {

				avances = avances + e.getFormulaire_Paie().getAvance();
				sb = sb + e.getFormulaire_Paie().getSalairedebase();

				sbr = sbr + e.getFormulaire_Paie().getSalaire_brut();
				rtn = rtn + e.getFormulaire_Paie().getRetenue_cnss();
				si = si + e.getFormulaire_Paie().getSalaire_brut();
				irpp = irpp + e.getFormulaire_Paie().getIrpp();
				sn = sn + e.getFormulaire_Paie().getSalaire_net();
				nap = nap + e.getFormulaire_Paie().getNet_apayer();
			}
		f.setAvance(avances);
		f.setIrpp(irpp);
		f.setNet_apayer(nap);

		f.setRetenue_cnss(rtn);
		f.setSalaire_imposable(si);
		f.setSalaire_brut(sbr);
		f.setSalairedebase(sb);
		f.setSalaire_net(sn);
		Recapulatif r = new Recapulatif();
		r.setAnnee(Integer.parseInt(annees));
		r.setMois(getMoisbyString(mois));
		r.setFormul(f);
		listPai.add(r);
	}

	public String historique_empl() {
		employer = "";
		listEmployees = new ArrayList<String>();
		listEmployee = new ArrayList<Employee>();
		listEmployee = serviceEmployee.getEmployeeparstats(Status.Declare);

		return SUCCESS;
	}

	public void gethistPaiebyEmployer(AjaxBehaviorEvent event) {

		Date d = new Date();
		Integer a = d.getYear() + 1900;
		Integer m = d.getMonth() + 1;
		listPaie = new ArrayList<Paie>();
		try {

			employee = serviceEmployee.getEmployeeByNom(employer);
			if (employee == null)
				employee = serviceEmployee.getEmployeeByNom(employer);
		} catch (Exception e) {

		}
		listPaie = servicePaie.getPaieByEmployee(employee);
		for (Paie p1 : listPaie) {
			p1.getEmployee().setLignegestion(ligneGestion.getlistbyemplyee(p1.getEmployee()));
			p1.setListGestion(serviceLigneGestionpaie.getlistbypaie(p1));

		}

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
	
	private Integer getNbJourbyString(Integer moi) {
		Integer m = 0;
		if (moi==1)
			m = 30;
		  if (moi==2)
			m = 28;
		  if (moi==3)
			m = 31;
		  if (moi==4)
			m = 30;
		  if (moi==5)
			m = 31;
		  if (moi==6)
			m = 30;
		  if (moi==7)
			m = 31;
		  if (moi==8)
			m = 31;
		  if (moi==9)// Septembre
			m = 30;
		  if (moi==10)
			m = 31;
		  if (moi==11)
			m = 30;
		  if (moi==12)
			m = 31;
		return m;
	}

	public String validerPaie() {
		/*
		 * List<Paie> l = new ArrayList<Paie>(); l =
		 * servicePaie.getPaieByAnneeAndMois(Integer.parseInt(annees),
		 * getMoisbyString(mois)); if (l) {
		 */
		for (Paie p : listPaie) {

			servicePaie.save(p);

		}

		for (Paie p : listPaie) {
			List<Lignepaiegestion> lg = p.getListGestion();
			for (Lignepaiegestion g : lg) {
				g.setPaie(p);
				serviceLigneGestionpaie.save(g);
			}

		}
		/*
		 * } else { String message = "Paie deja enregistre";
		 * FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage(message)); }
		 */
		return SUCCESS;
	}

	public String validerPaie2() {
		for (Paie p : listPaie) {
			servicePaie.save(p);
		}
		return SUCCESS;
	}

	public String supprimerpaie() {
		if (test.equals("notok"))
			listPaie = new ArrayList<Paie>();

		else {
			for (Paie p : listPaie) {
				p.setStatut(Statut.DEACTIF);
				servicePaie.delete(p);

			}
			/*
			 * List<Pointage> ptg = servicePointage.getPointageByMoiannee(
			 * Integer.parseInt(annees), getMoisbyString(mois)); for (Pointage p : ptg) {
			 * p.setStatut(Statut.DEACTIF); servicePointage.delete(p); }
			 */
		}
		return SUCCESS;
	}

	public String supprimerpaie2() {
		if (test.equals("notok"))
			listPaie = new ArrayList<Paie>();

		else {
			for (Paie p : listPaie) {
				p.setStatut(Statut.DEACTIF);
				servicePaie.delete(p);

			}
			List<Pointage> ptg = servicePointage.getPointageByMoiannee(Integer.parseInt(annees), getMoisbyString(mois));
			for (Pointage p : ptg) {
				p.setStatut(Statut.DEACTIF);
				servicePointage.delete(p);
			}
		}
		return SUCCESS;
	}

	private double calculIRPP(double salaire_brut) {
		double irpp1 = 0;
		double irpp2 = 0;
		double irpp3 = 0;
		double irpp4 = 0;
		double irpp5 = 0;
		if (salaire_brut >= 0 && salaire_brut <= 5000.000)
			irpp1 = (salaire_brut * 0);
		if (salaire_brut > 5000.000) {
			if (salaire_brut > 5000.000 && salaire_brut <= 20000.000)
				irpp2 = ((salaire_brut - 5000.000) * 0.26);
			 
		
		 
			if (salaire_brut > 20000.000 && salaire_brut <= 30000.000)
				irpp3 = 3900.000+((salaire_brut - 20000.000) * 0.28);
			  
		if (salaire_brut > 30000.000) {
			if (salaire_brut > 30000.000 && salaire_brut <= 50000.000)
				irpp4 = 6700.000+((salaire_brut - 30000.000) * 0.32);
		 
		}

		if (salaire_brut > 50000.000)
			irpp5 = 13100.000+((salaire_brut - 5000.000) * 0.35);
		}
		return (irpp1 + irpp2 + irpp3 + irpp4 + irpp5) / 12;
	}

	public void mois() {
		listMois = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, resolveDefaultPaieYear());
		calendar.set(Calendar.MONTH, resolveDefaultPaieMonth() - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(calendar.getTime());
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
	}

	private void Annee() {
		List<Annee> l = new ArrayList<Annee>();
		listannee = new ArrayList<String>();
		String defaultYear = String.valueOf(resolveDefaultPaieYear());
		Annee a = serviceAnnee.findbyDesignation(defaultYear);
		if (a == null) {
			Annee e = new Annee();
			e.setAnnee(defaultYear);
			serviceAnnee.save(e);
		}
		l = serviceAnnee.getAll();
		for (Annee aa : l)
			listannee.add(aa.getAnnee());
		if (annees == null || annees.trim().isEmpty()) {
			annees = defaultYear;
		}
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

	public Recapulatif getRecap1() {
		return recap1;
	}

	public void setRecap1(Recapulatif recap1) {
		this.recap1 = recap1;
	}

	public Recapulatif getRecap2() {
		return recap2;
	}

	public void setRecap2(Recapulatif recap2) {
		this.recap2 = recap2;
	}

	public void setServiceTrace(ServiceTrace serviceTrace) {
		this.serviceTrace = serviceTrace;
	}

	public ServiceSociete getServiceSociete() {
		return serviceSociete;
	}

	public void setServiceSociete(ServiceSociete serviceSociete) {
		this.serviceSociete = serviceSociete;
	}

	public ServiceAvancegestat getServiceAvance() {
		return serviceAvance;
	}

	public void setServiceAvance(ServiceAvancegestat serviceAvance) {
		this.serviceAvance = serviceAvance;
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

	public List<String> getListannee() {
		return listannee;
	}

	public void setListannee(List<String> listannee) {
		this.listannee = listannee;
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

	public Paie getSelectedPai() {
		return selectedPai;
	}

	public void setSelectedPai(Paie selectedPai) {
		this.selectedPai = selectedPai;
	}

	public List<Paie> getListPaie() {
		return listPaie;
	}

	public void setListPaie(List<Paie> listPaie) {
		this.listPaie = listPaie;
	}

	public ServiceGestion getServiceGestion() {
		return serviceGestion;
	}

	public void setServiceGestion(ServiceGestion serviceGestion) {
		this.serviceGestion = serviceGestion;
	}

	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	public ServiceLigneGestionpaie getServiceLigneGestionpaie() {
		return serviceLigneGestionpaie;
	}

	public void setServiceLigneGestionpaie(ServiceLigneGestionpaie serviceLigneGestionpaie) {
		this.serviceLigneGestionpaie = serviceLigneGestionpaie;
	}

	public List<Lignepaiegestion> getListpaieGestion() {
		return listpaieGestion;
	}

	public void setListpaieGestion(List<Lignepaiegestion> listpaieGestion) {
		this.listpaieGestion = listpaieGestion;
	}

	public List<Employee> getListEmployee() {
		return listEmployee;
	}

	public void setListEmployee(List<Employee> listEmployee) {
		this.listEmployee = listEmployee;
	}

	public List<String> getListPrime() {
		return listPrime;
	}

	public void setListPrime(List<String> listPrime) {
		this.listPrime = listPrime;
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	public List<String> getListEmployees() {
		return listEmployees;
	}

	public void setListEmployees(List<String> listEmployees) {
		this.listEmployees = listEmployees;
	}

	public boolean isTous() {
		return tous;
	}

	public void setTous(boolean tous) {
		this.tous = tous;
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

	public ServicePret getServicePret() {
		return servicePret;
	}

	public void setServicePret(ServicePret servicePret) {
		this.servicePret = servicePret;
	}

	public Societe getSociete() {
		return societe;
	}

	public List<Avancegestat> getListAvenc() {
		return listAvenc;
	}

	public void setListAvenc(List<Avancegestat> listAvenc) {
		this.listAvenc = listAvenc;
	}

	public Avancegestat getSelectedAvenc() {
		return selectedAvenc;
	}

	public void setSelectedAvenc(Avancegestat selectedAvenc) {
		this.selectedAvenc = selectedAvenc;
	}

	public List<Avancegestat> getFiltredAvenc() {
		return filtredAvenc;
	}

	public void setFiltredAvenc(List<Avancegestat> filtredAvenc) {
		this.filtredAvenc = filtredAvenc;
	}

	public Avancegestat getSelectedAvance() {
		return selectedAvance;
	}

	public void setSelectedAvance(Avancegestat selectedAvance) {
		this.selectedAvance = selectedAvance;
	}

	public void setSociete(Societe societe) {
		this.societe = societe;
	}

	public List<Recapulatif> getListPai() {
		return listPai;
	}

	public void setListPai(List<Recapulatif> listPai) {
		this.listPai = listPai;
	}

	public ServiceNote getServiceNote() {
		return serviceNote;
	}

	public void setServiceNote(ServiceNote serviceNote) {
		this.serviceNote = serviceNote;
	}

	public ServiceCategorie getServiceCategorie() {
		return serviceCategorie;
	}

	public void setServiceCategorie(ServiceCategorie serviceCategorie) {
		this.serviceCategorie = serviceCategorie;
	}

	public Pointage getPontage() {
		return pontage;
	}

	public void setPontage(Pointage pontage) {
		this.pontage = pontage;
	}

	public double getNbjour() {
		return nbjour;
	}

	public void setNbjour(double nbjour) {
		this.nbjour = nbjour;
	}

	private String avances;

	public double getAvance() {
		return avance;
	}

	public void setAvance(double avance) {
		this.avance = avance;
	}

	public String getAvances() {
		return avances;
	}

	public void setAvances(String avances) {
		DecimalFormat df = new DecimalFormat("0.000");
		avances = df.format(avance);
		this.avances = avances;
	}

	public DecimalFormat getDf() {
		return df;
	}

	public void setDf(DecimalFormat df) {
		this.df = df;
	}

	public Recapulatif getRecap() {
		return recap;
	}

	public void setRecap(Recapulatif recap) {
		this.recap = recap;
	}

	public ServiceLigneGestion getLigneGestion() {
		return ligneGestion;
	}

	public void setLigneGestion(ServiceLigneGestion ligneGestion) {
		this.ligneGestion = ligneGestion;
	}

	public UserService getUserservice() {
		return userservice;
	}

	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	public ServiceRendement getServicerendement() {
		return servicerendement;
	}

	public void setServicerendement(ServiceRendement servicerendement) {
		this.servicerendement = servicerendement;
	}

}
