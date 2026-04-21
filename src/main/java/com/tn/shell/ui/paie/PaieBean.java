package com.tn.shell.ui.paie;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.dao.DataAccessException;

import com.tn.shell.model.gestat.Utilisateur;
import com.tn.shell.model.paie.*;
import com.tn.shell.service.gestat.ServiceEmployeegestat;
import com.tn.shell.service.gestat.UserService;
import com.tn.shell.service.paie.*;
import com.tn.shell.service.vetement.VetementService;

@ManagedBean(name = "PaieBean")
@SessionScoped
public class PaieBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	private double salaire_sup=0;
	@ManagedProperty(value = "#{ServiceAnnee}")
	ServiceAnnee serviceAnnee;
	@ManagedProperty(value = "#{ServiceTrace}")
	ServiceTrace serviceTrace;

	@ManagedProperty(value = "#{UserService}")
	UserService userservice;
	@ManagedProperty(value = "#{ServiceSociete}")
	ServiceSociete serviceSociete;
	@ManagedProperty(value = "#{VetementService}")
	VetementService vetementService;
	@ManagedProperty(value = "#{ServiceEmployee}")
	ServiceEmployee serviceEmployee;
	@ManagedProperty(value = "#{ServiceEmployeegestat}")
	ServiceEmployeegestat serviceEmployeegestat;
	@ManagedProperty(value = "#{ServicePaie}")
	ServicePaie servicePaie;
	@ManagedProperty(value = "#{ServicePointage}")
	ServicePointage servicePointage;
	@ManagedProperty(value = "#{ServiceNote}")
	ServiceNote serviceNote;
	@ManagedProperty(value = "#{ServiceGestion}")
	ServiceGestion serviceGestion;

	@ManagedProperty(value = "#{ServiceCategorie}")
	ServiceCategorie serviceCategorie;
	@ManagedProperty(value = "#{ServiceCat}")
	ServiceCat serviceCat;

	@ManagedProperty(value = "#{ServiceDegree}")
	ServiceDegree serviceDegree;
	@ManagedProperty(value = "#{ServiceLigneGestionpaie}")
	ServiceLigneGestionpaie serviceLigneGestionpaie;
	@ManagedProperty(value = "#{Servicepaieprime}")
	Servicepaieprime servicepaieprime;
	@ManagedProperty(value = "#{ServiceLigneGestionprime}")
	ServiceLigneGestionprime serviceLigneGestionpaieprime;
	@ManagedProperty(value = "#{ImageEmployeeService}")
	ImageEmployeeService imageEmployeeService;
	private Integer codes;
	private Integer note;
	private Societe societe;
	
	private List<Lignepaiegestion> listpaieGestion;
	private boolean inactif;
	private List<String> listgestions;
	private boolean actif = true;
	private List<String> listannee;
	private String annee;
	private String mois;
	private List<String> listMois = new ArrayList<String>();
	private String dates;
	private String annees;
	private String typedecontrat;
	private List<String> natures;
	/* Champ employee */
	private double montant_avance;
	private Nature[] naturess;
	private Employee employee;
	private String cin;
	private String code_cnss;
	private String nom;
	private String prenom;
	private Etat etat;
	private String contrat;
	private String adresse;
	private Date date_naissance;
	private String fonction;
	private Date date_embauche;
	private double salaire_journalier;
	private Integer nb_enfant = 0;
	private Integer nb_enfant_enCharge = 0;
	private String RIB;
	private double nb_jour = 26;
	private String nature;
	private String banque;
	private Gestion selectedgestion;
	private String agence;
	private List<Employee> listEmp;
	private List<Paie> listPaie;
	private Employee selectedEmp;
	private List<Employee> filtredEmp;
	private String libelle;
	private Signe[] signes;
	private TypeCat[] typeCats;
	private TypeCat typeCat;
	private Signe signe;
	private Status[] status;
	private Status statu;
	private double valeurdeprime;
	private Gestion gestion;
	private List<Gestion> listGestion;
private List<Lignegestion> listGestionprime;
private boolean pompiste;
private boolean laveur;
private boolean vidangeur;
private boolean chefDePiste;
private boolean femmeDemenage;
private boolean caissier;
private boolean agentAdministratif;
private boolean responsableShop;
	
	private List<Paieprime> listpaie;
	private boolean declaree = true;
	private boolean nondeclaree;
	private boolean parrendement;
	private List<Note> listNotes;
	private List<Categorie> listCategorie;
	private List<Categorie> listCategorie2;
	private List<Cat> listCat;
	private List<Cat> listCat2;
	private List<Cat> listCat3;
	private List<Cat> listCat4;
	private List<Degree> listDegree;
	private Categorie selectedCat;
	private double prixheure;
	private double taux = 0.0918;
	private List<Integer> listCategoris;
	private List<Integer> listDegrees;
	private Integer cat;
	private Integer degree;
	private Date datefincontrat;
	private Pointage pointage;
	private String moi;
	private Lignegestion selectedLigneGestion;
	private Paie paie;
	private Integer code;
	private Nature naturee;
	private String tel;
	private String dateembauche;
	private List<String>   listtypedecontrat;
	@ManagedProperty(value = "#{ServiceLigneGestion}")
	ServiceLigneGestion ligneGestion;

@PostConstruct
	public void init() {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, resolveDefaultPaieYear());
			calendar.set(Calendar.MONTH, resolveDefaultPaieMonth() - 1);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
			dates = s.format(calendar.getTime());
			mois();
			Annee();
			annee = String.valueOf(resolveDefaultPaieYear());
			moi = getMoisbyIntger(resolveDefaultPaieMonth());
		} catch (Exception e) {
			System.err.println("Error in PaieBean.init(): " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String impressionPhotos(){
		listEmp=new ArrayList<Employee>();
		return SUCCESS;
	}
	
 
	public String getimpressionPhotos() {
		listEmp=new ArrayList<Employee>();
		if(laveur==true) {
		listEmp=serviceEmployee.getEmployeeByFonction("LAVEUR");
		fonction="LAVEURS";
		}
		if(vidangeur==true) {
			listEmp.addAll(serviceEmployee.getEmployeeByFonction("VIDANGEUR"));
			fonction="VIDANGEURS";
		}
		if(pompiste==true) {
			listEmp.addAll(serviceEmployee.getEmployeeByFonction("Pompiste"));
			fonction="Pompistes";
		}
		
		if(chefDePiste==true) {
			listEmp.addAll(serviceEmployee.getEmployeeByFonction("chef de piste"));
			fonction="chefs de piste";
		}
		
		if(femmeDemenage==true) {
			listEmp.addAll(serviceEmployee.getEmployeeByFonction("Femme De Menage"));
			fonction="Femmes De Menage";
		}
		
		if(caissier==true) {
			listEmp.addAll(serviceEmployee.getEmployeeByFonction("caissier"));
			fonction="caissiers";
		}
		
		if(agentAdministratif==true) {
			listEmp.addAll(serviceEmployee.getEmployeeByFonction("AGENT ADMINISTRATIF"));
			fonction="AGENT ADMINISTRATIF";
		}
		
		if(responsableShop==true) {
			listEmp.addAll(serviceEmployee.getEmployeeByFonction("RESPONSABLE SHOP"));
			fonction="RESPONSABLE SHOP";
		}
		
		for(Employee e:listEmp) {	
			LigneImageEmployee l= imageEmployeeService.getImagebyEmployeeandpositions(e, "photos");
			
			if (l!=null)
			 e.setPhotos(Base64.getMimeEncoder().encodeToString(
					l.getImage().getImage()));
			l= imageEmployeeService.getImagebyEmployeeandpositions(e, "cin");
	         if(l!=null)
			 e.setCins(Base64.getMimeEncoder().encodeToString(
					 l.getImage().getImage()));
		   
		 }
		return SUCCESS;
	}

	public String index() {
		return SUCCESS;
	}

	private double prime;

	public String pointageemployee() { 
		pointage = new Pointage();
		List<Employee> l = new ArrayList<Employee>();
		l = serviceEmployee.getAll();
		pointage = new Pointage();
		pointage.setEmployee(selectedEmp);
		pointage.setNb_jour(26);

		Annee();
		mois();
		annee = String.valueOf(resolveDefaultPaieYear());
		moi = getMoisbyIntger(resolveDefaultPaieMonth());
		return SUCCESS;
	}

	public String validerpointageemployee() {
		pointage.setNb_jour(nb_jour);
		pointage.setAnnee(Integer.parseInt(annee));
		pointage.setMois(getMoisbyString(moi));
		// servicePointage.save(pointage);
		paie = new Paie();

		try {
			if (nbOfMonthsBetweenTwoDates(selectedEmp.getContrat().getDate(), new Date()) == 24) {
				if (selectedEmp.getContrat() != null) {
					Integer cat = selectedEmp.getContrat().getCat().getValeur();
					Integer degree = selectedEmp.getContrat().getDegree().getValeur();

					// cat=cat+1;
					if (degree <= 15)
						degree = degree + 1;
					else if (degree == 15) {
						cat = cat + 1;
						degree = 1;
					}
					Categorie c = serviceCategorie.findbydegreeandcat(cat, degree);
					selectedEmp.setContrat(c);

					selectedEmp.setSalaire_journalier(c.getPrix_heur());
					serviceEmployee.update(selectedEmp);
				}

			}
		} catch (Exception ee) {
		}
		listPaie = new ArrayList<Paie>();
		List<Lignegestion> listGestion = new ArrayList<Lignegestion>();
		listGestion = ligneGestion.getlistbyemplyee(selectedEmp);
		double sommeprimes = 0;
		double nb_heure = 0;
		double irpp = 0;

		double avances = 0;
		/*List<Avance> a = serviceAvance.getAvancesByEmployee(selectedEmp, pointage.getAnnee(), pointage.getMois());
		if (a != null) {
			for (Avance aa : a)
				avances = avances + aa.getMontant_avance();
		}*/

		Formule_Paie f = new Formule_Paie();

		f.setNb_heure(nb_jour);
		if (selectedEmp.getFonction().equals("FEMME DE MENAGE"))
			f.setSalairedebase(selectedEmp.getSalaire_journalier() * 7 * f.getNb_heure());
		else
			f.setSalairedebase(selectedEmp.getSalaire_journalier() * 8 * f.getNb_heure());
		for (Lignegestion g : listGestion) {
			g.setValeurdeprimeafficher(nb_jour * g.getGestion().getValeurdeprime() * 8);
			sommeprimes = sommeprimes + g.getValeurdeprimeafficher();
		}

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
		if (selectedEmp.getEtat().equals(Etat.Mariee)) {
			irpp2 = 300.000;

			if (selectedEmp.getNb_enfant_enCharge() == 1)
				irpp3 = 100.000;
			if (selectedEmp.getNb_enfant_enCharge() == 2)
				irpp4 = 100.000;
			if (selectedEmp.getNb_enfant_enCharge() == 3)
				irpp5 = 100.000;
		}
		if (selectedEmp.getEtat().equals(Etat.Celebataire)) {
			irpp6 = 0;
		}
		irpp = ir - irpp1 - irpp2 - irpp3 - irpp4 - irpp5 - irpp6;
		double irs = calculIRPP(irpp);
		f.setIrpp(irs);
		f.setSalaire_net(f.getSalaire_imposable() - f.getIrpp());
		f.setAvance(avances);
		f.setNet_apayer(f.getSalaire_net() - avances);

		Paie p2 = new Paie();

		p2.setFormulaire_Paie(f);
		p2.setAnnee(pointage.getAnnee());
		p2.setMois(pointage.getMois());
		p2.setEmployee(selectedEmp);
	/*	Avance aa = new Avance();
		aa.setEmployee(selectedEmp);
		aa.setAnnee(pointage.getAnnee());
		aa.setMois(pointage.getMois());
		if (p2.getFormulaire_Paie().getNet_apayer() < 0)
			aa.setMontant_avance(0);
		else
			aa.setMontant_avance(p2.getFormulaire_Paie().getNet_apayer());
		aa.setDate(new Date());
		serviceAvance.save(aa);*/
		List<Lignegestion> l1 = selectedEmp.getLignegestion();
		listpaieGestion = new ArrayList<Lignepaiegestion>();
		for (Lignegestion g : l1) {
			Lignepaiegestion lg = new Lignepaiegestion();
			lg.setPaie(p2);
			lg.setLignegestion(g);
			lg.setValeurdeprime(g.getGestion().getValeurdeprime());
			if (selectedEmp.getFonction().equals("FEMME DE MENAGE"))
				lg.setValeurdeprimeafficher(lg.getValeurdeprime() * p2.getFormulaire_Paie().getNb_heure() * 6);
			else
				lg.setValeurdeprimeafficher(lg.getValeurdeprime() * p2.getFormulaire_Paie().getNb_heure() * 8);
			// serviceLigneGestionpaie.save(lg);
			listpaieGestion.add(lg);
		}
		p2.setListGestion(listpaieGestion);
		listPaie.add(p2);

		return SUCCESS;
	}

	public String validerGestion() {
		Lignegestion listg = new Lignegestion();
		gestion = serviceGestion.getGestionbyLibelle(libelle);
		listg.setEmployee(selectedEmp);
		listg.setGestion(gestion);
		listg.setNature(naturee);
		ligneGestion.save(listg);
		selectedEmp.setLignegestion(ligneGestion.getlistbyemplyee(selectedEmp));
		 
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
				irpp3 = 3900.000 + ((salaire_brut - 20000.000) * 0.28);

			if (salaire_brut > 30000.000) {
				if (salaire_brut > 30000.000 && salaire_brut <= 50000.000)
					irpp4 = 6700.000 + ((salaire_brut - 30000.000) * 0.32);

			}

			if (salaire_brut > 50000.000)
				irpp5 = 13100.000 + ((salaire_brut - 5000.000) * 0.35);
		}
		return (irpp1 + irpp2 + irpp3 + irpp4 + irpp5) / 12;
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
		moi = getMoisbyIntger(resolveDefaultPaieMonth());
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

	public String modifiertableau() {
		typeCats = TypeCat.values();
		listCategorie2 = new ArrayList<Categorie>();
		listCategorie2 = serviceCategorie.findbyDesignation(TypeCat.Implementation);
		typeCat = TypeCat.Implementation;

		listCat = new ArrayList<Cat>();
		listCat = serviceCat.findbyDesignation(typeCat);
		listCategoris = new ArrayList<Integer>();
		for (Cat c : listCat)
			listCategoris.add(c.getValeur());
		return SUCCESS;
	}

	public String validerModification() {

		for (Categorie c : listCategorie2) {
			c.setPrix_heur(c.getPrix_heur() + prixheure);
			c.setDate(new Date());
			serviceCategorie.update(c);
			listEmp = new ArrayList<Employee>();
			listEmp = serviceEmployee.getEmployeeByCategorie(c);
			if (listEmp != null)
				for (Employee e : listEmp) {
					e.setSalaire_journalier(c.getPrix_heur());
					serviceEmployee.update(e);
				}

		}
		prixheure = 0;

		return SUCCESS;
	}

	public void getcatbytypcat(AjaxBehaviorEvent event) {
		listCat = new ArrayList<Cat>();
		listCat = serviceCat.findbyDesignation(typeCat);
		listCategoris = new ArrayList<Integer>();
		for (Cat c : listCat)
			listCategoris.add(c.getValeur());
	}

	public void getcatbytypcat2(AjaxBehaviorEvent event) {
		listCategorie2 = new ArrayList<Categorie>();
		listCategorie2 = serviceCategorie.getcategoriebycat(cat);
	}

	public String tableauSalaires() {
		listCategorie = new ArrayList<Categorie>();
		listCategorie = serviceCategorie.getAll();

		listCat = new ArrayList<Cat>();
		listCat = serviceCat.getAll();

		listCat2 = new ArrayList<Cat>();
		listCat2 = serviceCat.findbyDesignation(TypeCat.Implementation);
		for (Cat d : listCat2) {
			d.setListcategories(serviceCategorie.getcategoriebydegre(d));
		}
		listCat3 = new ArrayList<Cat>();
		listCat3 = serviceCat.findbyDesignation(TypeCat.Administratif);
		for (Cat d : listCat3) {
			d.setListcategories(serviceCategorie.getcategoriebydegre(d));
		}
		listCat4 = new ArrayList<Cat>();
		listCat4 = serviceCat.findbyDesignation(TypeCat.Cadre);
		for (Cat d : listCat4) {
			d.setListcategories(serviceCategorie.getcategoriebydegre(d));
		}

		listDegree = new ArrayList<Degree>();
		listDegree = serviceDegree.getAll();

		return SUCCESS;
	}

	public String nouvelleGestion() {
		signes = Signe.values();
		libelle = null;
		signe = null;
		prime = 0;
		return SUCCESS;
	}

	public String ajouterGestion() {
		gestion = new Gestion();
		gestion.setLibelle(libelle);
		gestion.setSigne(signe);
		gestion.setValeurdeprime(prime);
		serviceGestion.save(gestion);
		listGestion = new ArrayList<Gestion>();
		listGestion = serviceGestion.getAll();
		return SUCCESS;
	}

	public String allGestions() {

		listGestion = new ArrayList<Gestion>();
		listGestion = serviceGestion.getAll();
		return SUCCESS;
	}

	private void Annee() {
		List<Annee> l = new ArrayList<Annee>();
		listannee = new ArrayList<String>();
		annees = String.valueOf(resolveDefaultPaieYear());
		Annee a = serviceAnnee.findbyDesignation(annees);
		if (a == null) {
			Annee e = new Annee();
			e.setAnnee(annees);
			serviceAnnee.save(e);
		}
		l = serviceAnnee.getAll();
		for (Annee aa : l)
			listannee.add(aa.getAnnee());
		annee = annees;
	}

	public void getMoisByAnnees(AjaxBehaviorEvent event) {
		listMois = new ArrayList<String>();
		listPaie = new ArrayList<Paie>();
		if (annees == null || annees.trim().isEmpty()) {
			return;
		}
		List<Pointage> pointages = servicePointage.getPointageByAnnee(Integer.parseInt(annees));
		if (pointages != null) {
			for (Pointage pointage : pointages) {
				String monthLabel = getMoisbyIntger(pointage.getMois());
				if (monthLabel != null && !monthLabel.isEmpty() && !listMois.contains(monthLabel)) {
					listMois.add(monthLabel);
				}
			}
		}
		mois = null;
	}

	public void getPaiBymoiAndAnnee(AjaxBehaviorEvent event) {
		listPaie = new ArrayList<Paie>();
		if (annees == null || annees.trim().isEmpty()) {
			return;
		}
		int selectedYear = Integer.parseInt(annees);
		int selectedMonth = 0;
		if (mois != null && !mois.trim().isEmpty()) {
			selectedMonth = getMoisbyString(mois);
		}
		if (selectedMonth > 0) {
			listPaie = servicePaie.getPaieByAnneeAndMois(selectedYear, selectedMonth);
		} else {
			listPaie = servicePaie.getPaieByAnnee(selectedYear);
		}
		if (listPaie == null) {
			listPaie = new ArrayList<Paie>();
			return;
		}
		for (Paie paie : listPaie) {
			paie.setListGestion(serviceLigneGestionpaie.getlistbypaie(paie));
		}
	}

	private int resolveDefaultPaieYear() {
		Pointage latestPointage = servicePointage.getMaxPointage();
		if (latestPointage != null && latestPointage.getAnnee() != null) {
			return latestPointage.getAnnee();
		}
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	private int resolveDefaultPaieMonth() {
		Pointage latestPointage = servicePointage.getMaxPointage();
		if (latestPointage != null && latestPointage.getMois() != null) {
			return latestPointage.getMois();
		}
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	/***
	 * editer gestion
	 */

	public void onRowEdit(RowEditEvent event) {

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "avance change",
				((Gestion) event.getObject()).getId() + "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		updategestion((Gestion) event.getObject());

	}

	public String updategestion(Gestion avance) {
		try {
			getServiceGestion().update(avance);
			return SUCCESS;
		} catch (DataAccessException e) {
		}
		return ERROR;
	}

	/*****
	 * nouveau employee
	 *****/
	private Contrat[] contrats;
	private Etat[] etats;

	public void getsalairebycategorir() {
		if (cat != null && degree != null) {
			Categorie c = serviceCategorie.findbydegreeandcat(cat, degree);
			salaire_journalier = c.getPrix_heur();
		} else
			salaire_journalier = 0;
	}

	public String nouvemployee() {
		listCategoris = new ArrayList<Integer>();
		listDegrees = new ArrayList<Integer>();
		listCat = new ArrayList<Cat>();
		listCat = serviceCat.getAll();
		listDegree = new ArrayList<Degree>();
		listDegree = serviceDegree.getAll();
		for (Cat c : listCat)
			listCategoris.add(c.getValeur());
		for (Degree d : listDegree)
			listDegrees.add(d.getValeur());
		typeCats = TypeCat.values();
		etats = Etat.values();
		status = Status.values();
		listtypedecontrat=new ArrayList<String>();
		listtypedecontrat.add("CIVP");
		listtypedecontrat.add("CDD");
		listtypedecontrat.add("CDI");
		listtypedecontrat.add("CIAP");
		natures = new ArrayList<String>();
		natures.add("Virement");
		natures.add("Espece");
		nom = null;
		prenom = null;
		cin = null;

		RIB = null;
		nb_enfant_enCharge = 0;
		nb_enfant = 0;
		nature = null;
		agence = null;
		banque = null;
		code_cnss = null;
		contrat = null;
		etat = null;
		salaire_sup=0;
		fonction = null;
		salaire_journalier = 0;
		return SUCCESS;
	}

	public void getvaleurPrime(AjaxBehaviorEvent event) {
		gestion = serviceGestion.getGestionbyLibelle(libelle);
		prime = gestion.getValeurdeprime();
	}

	public String succes() {
		etats = Etat.values();
		status = Status.values();
		naturess = Nature.values();
		listtypedecontrat=new ArrayList<String>();
		listtypedecontrat.add("CIVP");
		listtypedecontrat.add("CDD");
		listtypedecontrat.add("CDI");
		listtypedecontrat.add("CIAP");
		natures = new ArrayList<String>();
		natures.add("Virement");
		listgestions = new ArrayList<String>();
		for (Gestion g : listGestion)
			listgestions.add(g.getLibelle());

		natures.add("Espece");
		listCategoris = new ArrayList<Integer>();
		listDegrees = new ArrayList<Integer>();
		listCat = new ArrayList<Cat>();
		listCat = serviceCat.getAll();
		listDegree = new ArrayList<Degree>();
		listDegree = serviceDegree.getAll();
		for (Cat c : listCat)
			listCategoris.add(c.getValeur());
		for (Degree d : listDegree)
			listDegrees.add(d.getValeur());
		nom = selectedEmp.getNom();

		cin = selectedEmp.getCin();
		if (selectedEmp.getContrat() != null) {
			cat = selectedEmp.getContrat().getCat().getValeur();
			degree = selectedEmp.getContrat().getDegree().getValeur();
		}
		adresse = selectedEmp.getAdresse();
		RIB = selectedEmp.getRIB();
		nb_enfant_enCharge = selectedEmp.getNb_enfant_enCharge();
		nb_enfant = selectedEmp.getNb_enfant();
		nature = selectedEmp.getNature();
		agence = selectedEmp.getAgence();
		typedecontrat=selectedEmp.getTypedecontrat();
		code = selectedEmp.getCode();
		banque = selectedEmp.getBanque();
		dateembauche=selectedEmp.getDateembauche();
		datefincontrat=selectedEmp.getDatecontrat();
		tel=selectedEmp.getTel();
		code_cnss = selectedEmp.getCode_cnss();
		if (selectedEmp.getContrat() != null)
			contrat = "C" + selectedEmp.getContrat().getCat().getValeur() + "E"
					+ selectedEmp.getContrat().getDegree().getValeur();
		etat = selectedEmp.getEtat();
		statu = selectedEmp.getStatus();
		fonction = selectedEmp.getFonction();
		salaire_journalier = selectedEmp.getSalaire_journalier();
		  salaire_sup=selectedEmp.getSalaire_sup();
		selectedEmp.setLignegestion(ligneGestion.getlistbyemplyee(selectedEmp));
		//selectedEmp.setImagess(imageEmployeeService.getImagetbyEmployee(selectedEmp));
LigneImageEmployee l= imageEmployeeService.getImagebyEmployeeandpositions(selectedEmp, "photos");
		
		if (l!=null)
		 selectedEmp.setPhotos(Base64.getMimeEncoder().encodeToString(
				l.getImage().getImage()));
		l= imageEmployeeService.getImagebyEmployeeandpositions(selectedEmp, "cin");
         if(l!=null)
		 selectedEmp.setCins(Base64.getMimeEncoder().encodeToString(
				 l.getImage().getImage()));
		return SUCCESS;
	}

	public String modifierEmployer() {
		if (cat != null && degree != null) {
			Categorie c = serviceCategorie.findbydegreeandcat(cat, degree);
			selectedEmp.setContrat(c);
		}
		selectedEmp.setCode(code);
		selectedEmp.setNom(nom);
		selectedEmp.setTel(tel);
		selectedEmp.setDateembauche(dateembauche);
		selectedEmp.setDatecontrat(datefincontrat);
		selectedEmp.setTypedecontrat(typedecontrat);
		selectedEmp.setAdresse(adresse);
		selectedEmp.setCin(cin);
		selectedEmp.setCode_cnss(code_cnss);
		selectedEmp.setStatus(statu);
		selectedEmp.setEtat(etat);
		selectedEmp.setNb_enfant(nb_enfant);
		selectedEmp.setFonction(fonction);
		selectedEmp.setSalaire_journalier(salaire_journalier);
		selectedEmp.setContrat(serviceCategorie.findbydegreeandcat(cat, degree));
		selectedEmp.setRIB(RIB);
		selectedEmp.setNature(nature);
		selectedEmp.setNb_enfant_enCharge(nb_enfant_enCharge);
		selectedEmp.setSalaire_sup(salaire_sup);
		serviceEmployee.update(selectedEmp);
		Utilisateur user = userservice.getCurrentUser();
		Tracepaie t = new Tracepaie();
		t.setAction("modification sur l employer" + selectedEmp.getNom() + " par" + user.getNom());
		t.setDate(new Date());
		t.setUtilisateur(user);
		serviceTrace.save(t);
		nom = null;
		prenom = null;
		cin = null;
		adresse = null;
		tel=null;
		dateembauche=null;
		RIB = null;
		nb_enfant_enCharge = 0;
		nb_enfant = 0;
		nature = null;
		agence = null;
		banque = null;
		code_cnss = null;
		code = 1;
		contrat = null;
		etat = null;
		date_embauche = null;
		date_naissance = null;
		fonction = null;
		cat = null;
		degree = null;
		salaire_journalier = 0;
		listEmp = new ArrayList<Employee>();
		listEmp = serviceEmployee.getEmployeeparstats(Status.Declare);
		return SUCCESS;
	}

	public String nouveauemployee() {
		
		employee=serviceEmployee.getEmployeeBycode(code);
		if(employee!=null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "code employee existe deja ", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return ERROR;
		}
		
		employee = new Employee();
		if (cat != null && degree != null) {
			Categorie c = serviceCategorie.findbydegreeandcat(cat, degree);
			employee.setContrat(c);
		} else
			employee.setContrat(null);
        
		employee.setNom(nom);
		employee.setAdresse(adresse);
		employee.setCin(cin);
		employee.setCode_cnss(code_cnss);
		employee.setDatecontrat(datefincontrat);
		employee.setTel(tel);
		employee.setTypedecontrat(typedecontrat);
		employee.setDateembauche(dateembauche);
		employee.setCode(code);
		employee.setEtat(etat);
		employee.setNb_enfant(nb_enfant);
		employee.setFonction(fonction);
		employee.setSalaire_journalier(salaire_journalier);
		employee.setSalaire_sup(salaire_sup);
		employee.setRIB(RIB);
		employee.setStatus(statu);
		employee.setNature(nature);
		employee.setNb_enfant_enCharge(nb_enfant_enCharge);
		serviceEmployee.save(employee);
		Utilisateur user = userservice.getCurrentUser();
		Tracepaie t = new Tracepaie();
		t.setAction("creation sur l employer" + employee.getNom() + " par" + user.getNom());
		t.setDate(new Date());
		t.setUtilisateur(user);
		serviceTrace.save(t);
		/*
		 * Employeegestat e=new Employeegestat(); e.setNom(nom); e.setAdresse(adresse);
		 * e.setCin(cin); e.setSalaire_journalier(salaire_journalier*8*26);
		 * e.setTel(""); serviceEmployeegestat.save(e);
		 */
		listEmp = new ArrayList<Employee>();
		if(statu!=null && statu.equals(Status.Declare))
		listEmp = serviceEmployee.getEmployeeparstats(Status.Declare);
		if(statu!=null && statu.equals(Status.ParVoiture))
			listEmp = serviceEmployee.getEmployeeparstats(Status.Declare);
		if(statu!=null && statu.equals(Status.ParVoiture))
			listEmp = serviceEmployee.getEmployeeparstats(Status.ParVoiture);
		return SUCCESS;
	}

	public String supprimerGestion() {
		/// Lignegestion listg=ligneGestion.getlignebygestion(selectedGestion);
		ligneGestion.delete(selectedLigneGestion);
		selectedEmp.setLignegestion(ligneGestion.getlistbyemplyee(selectedEmp));
		return SUCCESS;
	}

	public String getAllEmployee() {
		inactif = false;
		actif = true;
		declaree = true;
		listGestion = new ArrayList<Gestion>();
		listGestion = serviceGestion.getAll();
		nondeclaree = false;parrendement=false;
		listEmp = new ArrayList<Employee>();
		listEmp = serviceEmployee.getEmployeeparstats(Status.Declare);
		if (listEmp == null || listEmp.isEmpty()) {
			listEmp = serviceEmployee.getAll();
		}
		/*
		 * for(Employee e:listEmp) { for(Gestion g:listGestion) { Lignegestion l=new
		 * Lignegestion(); l.setEmployee(e); l.setGestion(g);
		 * l.setNature(Nature.NBR_JOURS); l.setStatut(Statut.ACTIF); l.setGestion(g);
		 * ligneGestion.save(l); }
		 * 
		 * }
		 */
		code = 0;
		nom = null;
		prenom = null;
		cin = null;
		adresse = null;
		RIB = null;
		nb_enfant_enCharge = 0;
		nb_enfant = 0;
		nature = null;
		agence = null;
		banque = null;
		code_cnss = null;
		contrat = null;
		etat = null;
		date_embauche = null;
		date_naissance = null;
		fonction = null;
		salaire_journalier = 0;
		return SUCCESS;
	}

	public String supprimerEmployer() {
		selectedEmp.setStatut(Statut.DEACTIF);
		serviceEmployee.delete(selectedEmp);
		listEmp = new ArrayList<Employee>();
		listEmp = serviceEmployee.getAll();
		return SUCCESS;
	}

	public String activerEmployer() {
		selectedEmp.setStatut(Statut.ACTIF);
		serviceEmployee.update(selectedEmp);
		listEmp = new ArrayList<Employee>();
		listEmp = serviceEmployee.getAll();
		return SUCCESS;
	}

	private List<Historique> listHist;
	private Historique historique;
	
   public String historiquevetement() {
	   selectedEmp.setVetements(vetementService.getVetementbyEmployee(selectedEmp));
	   return SUCCESS;
   }
	public String historique() {
		double abs = 0;
		double pres = 0;
		listPaie = new ArrayList<Paie>();
		listPaie = servicePaie.getPaieByEmployee(selectedEmp);
		listHist = new ArrayList<Historique>();
		historique = new Historique();
		List<Pointage> l = new ArrayList<Pointage>();
		l = servicePointage.getPointageByEmployer(selectedEmp);
		List<Note> n = new ArrayList<Note>();
		n = serviceNote.getNotesByEmployer(selectedEmp);

		if (n != null)
			historique.setListNote(n);
		for (Paie p : listPaie) {
			if (p != null) {
				p.setNb_absence(26 - p.getFormulaire_Paie().getNb_heure());
				abs = abs + (26 - p.getFormulaire_Paie().getNb_heure());
				pres = pres + p.getFormulaire_Paie().getNb_heure();
			}
		}
		historique.setPointage(l);
		historique.setEmployee(selectedEmp);
		historique.setNbAbsence(abs);
		historique.setNbPresence(pres);
		historique.setPaie(listPaie);
		return SUCCESS;
	}

	public String listemployeractif() {
		inactif = false;
		filtredEmp = null;
		actif = true;
		listEmp = new ArrayList<Employee>();
		listEmp = serviceEmployee.getEmployeeparetat(Statut.ACTIF);
		return SUCCESS;
	}

	public String listemployerinactif() {
		inactif = true;
		filtredEmp = null;
		actif = false;
		listEmp = new ArrayList<Employee>();
		listEmp = serviceEmployee.getEmployeeparetat(Statut.DEACTIF);
		return SUCCESS;
	}

	public String listemployerdeclaree() {
		declaree = true;
		filtredEmp = null;
		nondeclaree = false;parrendement=false;
		listEmp = new ArrayList<Employee>();
		listEmp = serviceEmployee.getEmployeeparstats(Status.Declare);
		return SUCCESS;

	}

	public String listemployernondeclaree() {
		nondeclaree = true;
		declaree = false;parrendement=false;
		filtredEmp = null;
		listEmp = new ArrayList<Employee>();
		listEmp = serviceEmployee.getEmployeeparstats(Status.NonDeclaree);
		return SUCCESS;

	}

	public String   listemployernondeclareeend() {
		nondeclaree = false;
		declaree = false;parrendement=true;
		filtredEmp = null;
		listEmp = new ArrayList<Employee>();
		listEmp = serviceEmployee.getEmployeeparstats(Status.ParVoiture);
		return SUCCESS;

	}

	public String retourhist() {
		return SUCCESS;
	}

	public String note() {
		listNotes = new ArrayList<Note>();
		Date d = new Date();
		Annee();
		/*listEmp = new ArrayList<Employee>();
		listEmp = serviceEmployee.getEmployeeparstats(Status.Declare);
		for (Employee e : listEmp) {			 
			Note n = new Note();
			n.setEmployee(e);
			n.setMois(d.getMonth() + 1);
			n.setAnnee(d.getYear() + 1900);
			n.setNote(10);
			listNotes.add(n);
		}*/
		return SUCCESS;
	}
	
	public void getEmployeeBynote(AjaxBehaviorEvent event) {
		List<Employee> listEmp1 = new ArrayList<Employee>();
		listEmp = new ArrayList<Employee>();
		listEmp1 = serviceEmployee.getEmployeeparstats(Status.Declare);
		for (Employee e : listEmp1) {			 
			if(serviceNote.getNotesByEmployee(e, Integer.parseInt(annee))==null)
				listEmp.add(e)	;
		}
		Date d = new Date();
		for (Employee e : listEmp) {			 
			Note n = new Note();
			n.setEmployee(e);
			n.setMois(d.getMonth() + 1);
			n.setAnnee(Integer.parseInt(annee));
			n.setNote(10);
			listNotes.add(n);
		}
	}

	public void onCellEdit(CellEditEvent event) {
		Integer oldValue = (Integer) event.getOldValue();
		Integer newValue = (Integer) event.getNewValue();
		Note e = listNotes.get(event.getRowIndex());

		e.setNote(newValue);

	}

	
	public void updatenote(AjaxBehaviorEvent event) {
		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
		Note e = listNotes.get(codes);
		listNotes.set(codes, e);
		e.setNote(note);
		note = 10;
	}

	public String validernote() {
		for (Note p : listNotes) {
			serviceNote.save(p);
			Paieprime p1 = new Paieprime();
			p1.setAnnee(Integer.parseInt(annee));
			p1.setEmployee(p.getEmployee());
			p1.setNote(p);
			listGestionprime = new ArrayList<Lignegestion>();
			listGestionprime = ligneGestion.getlistbyemplyee(p.getEmployee());
			double sommeprimes = 0;
			double nb_heure = 0;
			double irpp = 0;
			Formule_Paie f = new Formule_Paie();
			List<Lignepaiegestionprime> llg = new ArrayList<Lignepaiegestionprime>();
			f.setNb_heure(26);
			if (p.getEmployee().getFonction().equals("FEMME DE MENAGE"))
				f.setSalairedebase((p.getEmployee().getSalaire_journalier()+p.getEmployee().getSalaire_sup()) * 7 * f.getNb_heure());
			else
				f.setSalairedebase((p.getEmployee().getSalaire_journalier()+p.getEmployee().getSalaire_sup()) * 8 * f.getNb_heure());
			for (Lignegestion g : listGestionprime) {
				Lignepaiegestionprime lg = new Lignepaiegestionprime();
				if (p.getEmployee().getFonction().equals("FEMME DE MENAGE"))
					lg.setValeurdeprimeafficher(g.getGestion().getValeurdeprime() * 6 * f.getNb_heure());
				else
					lg.setValeurdeprimeafficher(g.getGestion().getValeurdeprime() * 8 * f.getNb_heure());
				lg.setValeurdeprime(g.getGestion().getValeurdeprime());
				lg.setPaieprime(p1);
				lg.setLignegestions(g);
				llg.add(lg);

				sommeprimes = sommeprimes + lg.getValeurdeprimeafficher();
			}
			p1.setListlignepaiegestions(llg);
			// double prim = f.getSalairedebase() * p.getNote() / 20;
			double nbjouannuel = servicePointage.getsumPointageByEmployeeandannee(p.getEmployee(),
					Integer.parseInt(annee));
			double prim = (f.getSalairedebase() + sommeprimes) * nbjouannuel / 312 * p.getNote() / 20;

			f.setNb_heure(nbjouannuel);
			f.setSalaire_brut(prim);
			f.setRetenue_cnss(f.getSalaire_brut() * taux);
			f.setSalaire_imposable(f.getSalaire_brut() - f.getRetenue_cnss());
			double irpp1 = 0;
			double irpp2 = 0;
			double irpp3 = 0;
			double irpp4 = 0;
			double irpp5 = 0;
			double irpp6 = 0;
			double irpp7 = 0;
			double ir = 0;
			ir = f.getSalaire_imposable() * 12;
			irpp1 = 0.1 * ir;
			if (p.getEmployee().getEtat().equals(Etat.Mariee)) {
				irpp2 = 300.000;

				if (p.getEmployee().getNb_enfant_enCharge() == 1)
					irpp3 = 100.000;
				if (p.getEmployee().getNb_enfant_enCharge() == 2)
					irpp4 = 200.000;
				if (p.getEmployee().getNb_enfant_enCharge() == 3)
					irpp5 = 300.000;
				if (p.getEmployee().getNb_enfant_enCharge() == 4)
					irpp7 = 400.000;
			}
			if (p.getEmployee().getEtat().equals(Etat.Celebataire)) {
				irpp6 = 0;
			}
			irpp = ir - irpp1 - irpp2 - irpp3 - irpp4 - irpp5 - irpp6- irpp7;
			double irs = calculIRPP(irpp);
			f.setIrpp(irs);

			f.setSalaire_net(f.getSalaire_imposable() - f.getIrpp()

			); 
			double red = (irpp  * 0.01) / 12;
			f.setRedevance(red); 
				 
			f.setNet_apayer(f.getSalaire_net() - f.getRedevance());
			p1.setFormulaire_Paie(f);
			servicepaieprime.save(p1);
			for (Lignepaiegestionprime g : llg) {
				// serviceLigneGestionpaieprime.save(g);
			}
		}
		Annee();

		societe = serviceSociete.getAll().get(0);
		Annee();

		return SUCCESS;
	}
	/*
	 * setter and getter
	 * 
	 * **
	 */

	public ServiceAnnee getServiceAnnee() {
		return serviceAnnee;
	}

	public boolean isDeclaree() {
		return declaree;
	}

	public void setDeclaree(boolean declaree) {
		this.declaree = declaree;
	}

	public boolean isNondeclaree() {
		return nondeclaree;
	}

	public List<Note> getListNotes() {
		return listNotes;
	}

	public Integer getCodes() {
		return codes;
	}

	public void setCodes(Integer codes) {
		this.codes = codes;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public List<Paie> getListPaie() {
		return listPaie;
	}

	public void setListPaie(List<Paie> listPaie) {
		this.listPaie = listPaie;
	}

	public List<Historique> getListHist() {
		return listHist;
	}

	public void setListHist(List<Historique> listHist) {
		this.listHist = listHist;
	}

	public Historique getHistorique() {
		return historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public ServiceNote getServiceNote() {
		return serviceNote;
	}

	public void setServiceNote(ServiceNote serviceNote) {
		this.serviceNote = serviceNote;
	}

	public void setListNotes(List<Note> listNotes) {
		this.listNotes = listNotes;
	}

	public void setNondeclaree(boolean nondeclaree) {
		this.nondeclaree = nondeclaree;
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

	public ServiceSociete getServiceSociete() {
		return serviceSociete;
	}

	public void setServiceSociete(ServiceSociete serviceSociete) {
		this.serviceSociete = serviceSociete;
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

	public double getMontant_avance() {
		return montant_avance;
	}

	public void setMontant_avance(double montant_avance) {
		this.montant_avance = montant_avance;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getCode_cnss() {
		return code_cnss;
	}

	public void setCode_cnss(String code_cnss) {
		this.code_cnss = code_cnss;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public String getContrat() {
		return contrat;
	}

	public void setContrat(String contrat) {
		this.contrat = contrat;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Date getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public Date getDate_embauche() {
		return date_embauche;
	}

	public void setDate_embauche(Date date_embauche) {
		this.date_embauche = date_embauche;
	}

	public double getSalaire_journalier() {
		return salaire_journalier;
	}

	public void setSalaire_journalier(double salaire_journalier) {
		this.salaire_journalier = salaire_journalier;
	}

	public Integer getNb_enfant() {
		return nb_enfant;
	}

	public void setNb_enfant(Integer nb_enfant) {
		this.nb_enfant = nb_enfant;
	}

	public String getRIB() {
		return RIB;
	}

	public void setRIB(String rIB) {
		RIB = rIB;
	}

	public double getNb_jour() {
		return nb_jour;
	}

	public void setNb_jour(double nb_jour) {
		this.nb_jour = nb_jour;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getBanque() {
		return banque;
	}

	public void setBanque(String banque) {
		this.banque = banque;
	}

	public String getAgence() {
		return agence;
	}

	public void setAgence(String agence) {
		this.agence = agence;
	}

	public Contrat[] getContrats() {
		return contrats;
	}

	public void setContrats(Contrat[] contrats) {
		this.contrats = contrats;
	}

	public Etat[] getEtats() {
		return etats;
	}

	public void setEtats(Etat[] etats) {
		this.etats = etats;
	}

	public List<Employee> getListEmp() {
		return listEmp;
	}

	public void setListEmp(List<Employee> listEmp) {
		this.listEmp = listEmp;
	}

	public Employee getSelectedEmp() {
		return selectedEmp;
	}

	public void setSelectedEmp(Employee selectedEmp) {
		this.selectedEmp = selectedEmp;
	}

	public List<Employee> getFiltredEmp() {
		return filtredEmp;
	}

	public void setFiltredEmp(List<Employee> filtredEmp) {
		this.filtredEmp = filtredEmp;
	}

	public boolean isInactif() {
		return inactif;
	}

	public void setInactif(boolean inactif) {
		this.inactif = inactif;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public Integer getNb_enfant_enCharge() {
		return nb_enfant_enCharge;
	}

	public void setNb_enfant_enCharge(Integer nb_enfant_enCharge) {
		this.nb_enfant_enCharge = nb_enfant_enCharge;
	}

	public List<String> getNatures() {
		return natures;
	}

	public ServiceGestion getServiceGestion() {
		return serviceGestion;
	}

	public void setServiceGestion(ServiceGestion serviceGestion) {
		this.serviceGestion = serviceGestion;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Signe[] getSignes() {
		return signes;
	}

	public void setSignes(Signe[] signes) {
		this.signes = signes;
	}

	public Signe getSigne() {
		return signe;
	}

	public void setSigne(Signe signe) {
		this.signe = signe;
	}

	public double getValeurdeprime() {
		return valeurdeprime;
	}

	public void setValeurdeprime(double valeurdeprime) {
		this.valeurdeprime = valeurdeprime;
	}

	public Gestion getGestion() {
		return gestion;
	}

	public void setGestion(Gestion gestion) {
		this.gestion = gestion;
	}

	public List<Gestion> getListGestion() {
		return listGestion;
	}

	public void setListGestion(List<Gestion> listGestion) {
		this.listGestion = listGestion;
	}

	public void setNatures(List<String> natures) {
		this.natures = natures;
	}

	public Status[] getStatus() {
		return status;
	}

	public void setStatus(Status[] status) {
		this.status = status;
	}

	public Status getStatu() {
		return statu;
	}

	public void setStatu(Status statu) {
		this.statu = statu;
	}

	public Gestion getSelectedgestion() {
		return selectedgestion;
	}

	public void setSelectedgestion(Gestion selectedgestion) {
		this.selectedgestion = selectedgestion;
	}

	public ServiceCategorie getServiceCategorie() {
		return serviceCategorie;
	}

	public void setServiceCategorie(ServiceCategorie serviceCategorie) {
		this.serviceCategorie = serviceCategorie;
	}

	public ServiceCat getServiceCat() {
		return serviceCat;
	}

	public void setServiceCat(ServiceCat serviceCat) {
		this.serviceCat = serviceCat;
	}

	public ServiceDegree getServiceDegree() {
		return serviceDegree;
	}

	public void setServiceDegree(ServiceDegree serviceDegree) {
		this.serviceDegree = serviceDegree;
	}

	public List<Categorie> getListCategorie() {
		return listCategorie;
	}

	public void setListCategorie(List<Categorie> listCategorie) {
		this.listCategorie = listCategorie;
	}

	public List<Cat> getListCat() {
		return listCat;
	}

	public void setListCat(List<Cat> listCat) {
		this.listCat = listCat;
	}

	public List<Degree> getListDegree() {
		return listDegree;
	}

	public void setListDegree(List<Degree> listDegree) {
		this.listDegree = listDegree;
	}

	public List<Cat> getListCat2() {
		return listCat2;
	}

	public void setListCat2(List<Cat> listCat2) {
		this.listCat2 = listCat2;
	}

	public List<Cat> getListCat3() {
		return listCat3;
	}

	public void setListCat3(List<Cat> listCat3) {
		this.listCat3 = listCat3;
	}

	public List<Cat> getListCat4() {
		return listCat4;
	}

	public void setListCat4(List<Cat> listCat4) {
		this.listCat4 = listCat4;
	}

	public TypeCat[] getTypeCats() {
		return typeCats;
	}

	public List<Categorie> getListCategorie2() {
		return listCategorie2;
	}

	public void setListCategorie2(List<Categorie> listCategorie2) {
		this.listCategorie2 = listCategorie2;
	}

	public void setTypeCats(TypeCat[] typeCats) {
		this.typeCats = typeCats;
	}

	public TypeCat getTypeCat() {
		return typeCat;
	}

	public void setTypeCat(TypeCat typeCat) {
		this.typeCat = typeCat;
	}

	public Categorie getSelectedCat() {
		return selectedCat;
	}

	public void setSelectedCat(Categorie selectedCat) {
		this.selectedCat = selectedCat;
	}

	public double getPrixheure() {
		return prixheure;
	}

	public void setPrixheure(double prixheure) {
		this.prixheure = prixheure;
	}

	public List<Integer> getListCategoris() {
		return listCategoris;
	}

	public void setListCategoris(List<Integer> listCategoris) {
		this.listCategoris = listCategoris;
	}

	public List<Integer> getListDegrees() {
		return listDegrees;
	}

	public void setListDegrees(List<Integer> listDegrees) {
		this.listDegrees = listDegrees;
	}

	public Integer getCat() {
		return cat;
	}

	public void setCat(Integer cat) {
		this.cat = cat;
	}

	public Integer getDegree() {
		return degree;
	}

	public void setDegree(Integer degree) {
		this.degree = degree;
	}

	public ServiceEmployeegestat getServiceEmployeegestat() {
		return serviceEmployeegestat;
	}

	public void setServiceEmployeegestat(ServiceEmployeegestat serviceEmployeegestat) {
		this.serviceEmployeegestat = serviceEmployeegestat;
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	public Pointage getPointage() {
		return pointage;
	}

	public void setPointage(Pointage pointage) {
		this.pointage = pointage;
	}

	public String getMoi() {
		return moi;
	}

	public void setMoi(String moi) {
		this.moi = moi;
	}

	public Paie getPaie() {
		return paie;
	}

	public void setPaie(Paie paie) {
		this.paie = paie;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public ServiceLigneGestion getLigneGestion() {
		return ligneGestion;
	}

	public void setLigneGestion(ServiceLigneGestion ligneGestion) {
		this.ligneGestion = ligneGestion;
	}

	public double getPrime() {
		return prime;
	}

	public void setPrime(double prime) {
		this.prime = prime;
	}

	public List<String> getListgestions() {
		return listgestions;
	}

	public void setListgestions(List<String> listgestions) {
		this.listgestions = listgestions;
	}

	public Nature getNaturee() {
		return naturee;
	}

	public void setNaturee(Nature naturee) {
		this.naturee = naturee;
	}

	public Nature[] getNaturess() {
		return naturess;
	}

	public void setNaturess(Nature[] naturess) {
		this.naturess = naturess;
	}

	public Lignegestion getSelectedLigneGestion() {
		return selectedLigneGestion;
	}

	public void setSelectedLigneGestion(Lignegestion selectedLigneGestion) {
		this.selectedLigneGestion = selectedLigneGestion;
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

	public UserService getUserservice() {
		return userservice;
	}

	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	public Servicepaieprime getServicepaieprime() {
		return servicepaieprime;
	}

	public void setServicepaieprime(Servicepaieprime servicepaieprime) {
		this.servicepaieprime = servicepaieprime;
	}

	public Societe getSociete() {
		return societe;
	}

	public void setSociete(Societe societe) {
		this.societe = societe;
	}

	public List<Lignegestion> getListGestionprime() {
		return listGestionprime;
	}

	public void setListGestionprime(List<Lignegestion> listGestionprime) {
		this.listGestionprime = listGestionprime;
	}

	public List<Paieprime> getListpaie() {
		return listpaie;
	}

	public void setListpaie(List<Paieprime> listpaie) {
		this.listpaie = listpaie;
	}

	public ServiceLigneGestionprime getServiceLigneGestionpaieprime() {
		return serviceLigneGestionpaieprime;
	}

	public void setServiceLigneGestionpaieprime(ServiceLigneGestionprime serviceLigneGestionpaieprime) {
		this.serviceLigneGestionpaieprime = serviceLigneGestionpaieprime;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDateembauche() {
		return dateembauche;
	}

	public void setDateembauche(String dateembauche) {
		this.dateembauche = dateembauche;
	}

	public VetementService getVetementService() {
		return vetementService;
	}

	public void setVetementService(VetementService vetementService) {
		this.vetementService = vetementService;
	}

	public Date getDatefincontrat() {
		return datefincontrat;
	}

	public void setDatefincontrat(Date datefincontrat) {
		this.datefincontrat = datefincontrat;
	}

	public String getTypedecontrat() {
		return typedecontrat;
	}

	public void setTypedecontrat(String typedecontrat) {
		this.typedecontrat = typedecontrat;
	}

	public List<String> getListtypedecontrat() {
		return listtypedecontrat;
	}

	public void setListtypedecontrat(List<String> listtypedecontrat) {
		this.listtypedecontrat = listtypedecontrat;
	}

	public boolean isParrendement() {
		return parrendement;
	}

	public void setParrendement(boolean parrendement) {
		this.parrendement = parrendement;
	}

	public double getSalaire_sup() {
		return salaire_sup;
	}

	public void setSalaire_sup(double salaire_sup) {
		this.salaire_sup = salaire_sup;
	}

	public ImageEmployeeService getImageEmployeeService() {
		return imageEmployeeService;
	}

	public void setImageEmployeeService(ImageEmployeeService imageEmployeeService) {
		this.imageEmployeeService = imageEmployeeService;
	}

	public boolean isPompiste() {
		return pompiste;
	}

	public void setPompiste(boolean pompiste) {
		this.pompiste = pompiste;
	}

	public boolean isLaveur() {
		return laveur;
	}

	public void setLaveur(boolean laveur) {
		this.laveur = laveur;
	}

	public boolean isVidangeur() {
		return vidangeur;
	}

	public void setVidangeur(boolean vidangeur) {
		this.vidangeur = vidangeur;
	}

	public boolean isChefDePiste() {
		return chefDePiste;
	}

	public void setChefDePiste(boolean chefDePiste) {
		this.chefDePiste = chefDePiste;
	}

	public boolean isFemmeDemenage() {
		return femmeDemenage;
	}

	public void setFemmeDemenage(boolean femmeDemenage) {
		this.femmeDemenage = femmeDemenage;
	}

	public boolean isCaissier() {
		return caissier;
	}

	public void setCaissier(boolean caissier) {
		this.caissier = caissier;
	}

	public boolean isAgentAdministratif() {
		return agentAdministratif;
	}

	public void setAgentAdministratif(boolean agentAdministratif) {
		this.agentAdministratif = agentAdministratif;
	}

	public boolean isResponsableShop() {
		return responsableShop;
	}

	public void setResponsableShop(boolean responsableShop) {
		this.responsableShop = responsableShop;
	}
	
	

}
