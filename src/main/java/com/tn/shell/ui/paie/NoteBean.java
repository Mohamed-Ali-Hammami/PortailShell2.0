package com.tn.shell.ui.paie;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import com.tn.shell.model.paie.*;
import com.tn.shell.service.paie.*;

/**
 * @author MOUSSI
 *
 */
@ManagedBean(name = "NoteBean")
@SessionScoped
public class NoteBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	private Societe societe;
	@ManagedProperty(value = "#{ServiceAnnee}")
	ServiceAnnee serviceAnnee;

	@ManagedProperty(value = "#{ServiceSociete}")
	ServiceSociete serviceSociete;
	@ManagedProperty(value = "#{ServiceLigneGestion}")
	ServiceLigneGestion ligneGestion;
	@ManagedProperty(value = "#{ServiceEmployee}")
	ServiceEmployee serviceEmployee;
	@ManagedProperty(value = "#{ServicePaie}")
	ServicePaie servicePaie;
	@ManagedProperty(value = "#{ServicePointage}")
	ServicePointage servicePointage;

	@ManagedProperty(value = "#{ServiceNote}")
	ServiceNote serviceNote;
	@ManagedProperty(value = "#{ServiceGestion}")
	ServiceGestion serviceGestion;
	@ManagedProperty(value = "#{Servicepaieprime}")
	Servicepaieprime servicepaieprime;
	@ManagedProperty(value = "#{ServiceLigneGestionprime}")
	ServiceLigneGestionprime serviceLigneGestionpaie;
	private List<Lignepaiegestionprime> listpaieGestion;
	private List<Employee> listEmployee;
	private List<String> listEmployees;
	private Employee employee;
	private String employer;
	private List<String> listannee;
	private Integer annee;
	private Integer mois;
	private String moi;
	private Integer note;
	private List<String> listMois = new ArrayList<String>();
	private String dates;
	private String test;
	private String annees;
	private List<Recapulatif> listPai;
	private List<Lignegestion> listGestion;
	private List<String> listeEmployee;
	private List<Paieprime> listpaie;
	private double taux = 0.0918;
	private Note notes;

	@PostConstruct
	public void init() {
		try {
			Annee();
			mois();
			moi = getMoisbyIntger(resolveDefaultNoteMonth());
		} catch (Exception e) {
			System.err.println("NoteBean.init(): " + e.getMessage());
		}
	}

	public String note() {

		listeEmployee = new ArrayList<String>();
		listEmployee = new ArrayList<Employee>();
		listEmployee = serviceEmployee.getEmployeeparstats(Status.Declare);

		Annee();
		mois();
		moi = getMoisbyIntger(resolveDefaultNoteMonth());
		annee = resolveDefaultNoteYear();
		employer = null;
		note = 0;
		return SUCCESS;
	}

	public String Validernote() {

		Employee employee = serviceEmployee.getEmployeeByNom(employer);
		if (employee == null) {
			String message = "Veuillez choisir un employe";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
		}

		Note p = new Note();
		p.setAnnee(annee);
		p.setEmployee(employee);
		p.setMois(getMoisbyString(moi));
		p.setNote(note);

		serviceNote.save(p);
		Paieprime p1 = new Paieprime();
		p1.setAnnee(annee);
		p1.setEmployee(employee);
		p1.setNote(p);
		listGestion = new ArrayList<Lignegestion>();
		listGestion = ligneGestion.getlistbyemplyee(employee);
		double sommeprimes = 0;
		double nb_heure = 0;
		double irpp = 0;

		Formule_Paie f = new Formule_Paie();
List<Lignepaiegestionprime> llg=new ArrayList<Lignepaiegestionprime>();
		f.setNb_heure(26);
		if (employee.getFonction().equals("FEMME DE MENAGE"))
			f.setSalairedebase(employee.getSalaire_journalier() * 6 * f.getNb_heure());
		else
			f.setSalairedebase(employee.getSalaire_journalier() * 8 * f.getNb_heure());
		for (Lignegestion g : listGestion) {
			Lignepaiegestionprime lg = new Lignepaiegestionprime();
			if (employee.getFonction().equals("FEMME DE MENAGE"))
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
		double prim = f.getSalairedebase() * note / 20;

		f.setSalaire_brut(prim+sommeprimes);
		f.setRetenue_cnss(f.getSalaire_brut() * taux);
		f.setSalaire_imposable(f.getSalaire_brut() - f.getRetenue_cnss());

		double irpp1 = 0;
		double irpp2 = 0;
		double irpp3 = 0;
		double irpp4 = 0;
		double irpp5 = 0;
		double irpp6 = 0;
		double ir = 0;
		ir = f.getSalaire_imposable() * 12;
		irpp1 = 0.1 * ir;
		if (employee.getEtat().equals(Etat.Mariee)) {
			irpp2 = 150.000;

			if (employee.getNb_enfant_enCharge() == 1)
				irpp3 = 90.000;
			if (employee.getNb_enfant_enCharge() == 2)
				irpp4 = 75.000;
			if (employee.getNb_enfant_enCharge() == 3)
				irpp5 = 60.000;
		}
		if (employee.getEtat().equals(Etat.Celebataire)) {
			irpp6 = 0;
		}
		irpp = ir - irpp1 - irpp2 - irpp3 - irpp4 - irpp5 - irpp6;
		double irs = calculIRPP(irpp);
		f.setIrpp(irs);

		f.setSalaire_net(f.getSalaire_imposable() - f.getIrpp()

		);

		f.setNet_apayer(f.getSalaire_net());
         f.setRedevance(0);
		p1.setFormulaire_Paie(f);
		servicepaieprime.save(p1);
		for(Lignepaiegestionprime g:llg) {
			serviceLigneGestionpaie.save(g);
		}
		Annee();

		societe = serviceSociete.getAll().get(0);
		Annee();

		return SUCCESS;
	}

	public void verifierpointage(AjaxBehaviorEvent event) {
		Employee employee = serviceEmployee.getEmployeeByNom(employer);
		if (employee == null) {
			return;
		}
		Note noteExistante = serviceNote.getNotesByEmployee(employee, annee);
		if (noteExistante != null) {
			String message = "vous avez deja noter " + employer + " pour l'annee " + annee + "!!!!!!!!!!!!!!";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
		}
	}

	public String supprimerpaie() {
		Note nn = serviceNote.getMaxPointageconge();
		for (Paieprime p : listpaie) {
			p.setStatut(Statut.DEACTIF);
			servicepaieprime.delete(p);
			Note n = serviceNote.getNotesByEmployee(p.getEmployee(), p.getAnnee());
			serviceNote.delete(n);
		}
		listpaie = new ArrayList<Paieprime>();
		if (nn != null)
			listpaie = servicepaieprime.getPaieByAnnee(nn.getAnnee());

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
			else
				irpp2 = 5000.000 * 0.26;
		}
		if (salaire_brut > 20000.000) {
			if (salaire_brut > 20000.000 && salaire_brut <= 30000.000)
				irpp3 = ((salaire_brut - 20000.000) * 0.28);
			else
				irpp3 = 20000.000 * 0.28;
		}
		if (salaire_brut > 30000.000) {
			if (salaire_brut > 30000.000 && salaire_brut <= 50000.000)
				irpp4 = ((salaire_brut - 30000.000) * 0.32);
			else
				irpp4 = 30000.000 * 0.32;
		}

		if (salaire_brut > 50000.000)
			irpp5 = ((salaire_brut - 5000.000) * 0.35);

		return (irpp1 + irpp2 + irpp3 + irpp4 + irpp5) / 12;
	}

	public void getPaiebyEmployer(AjaxBehaviorEvent event) {
		test = "notok";
		listpaie = new ArrayList<Paieprime>();
		// try {

		Employee employee = serviceEmployee.getEmployeeByNom(employer);
		if (employee == null) {
			String message = "Veuillez choisir un employe";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
			return;
		}

		Note p = serviceNote.getMaxPointageconge();
		int targetYear = p != null && p.getAnnee() != null ? p.getAnnee() : resolveDefaultNoteYear();
		listpaie = servicepaieprime.getPaieByAnneeAndEmployee(employee, targetYear);
		if (listpaie != null)
			for (Paieprime p1 : listpaie) {
				p1.getEmployee().setLignegestion(ligneGestion.getlistbyemplyee(p1.getEmployee()));
				p1.setListlignepaiegestions(serviceLigneGestionpaie.getlistbypaie(p1));
			}
		// } catch (Exception e) { }
	}

	public void getpaiecongeByannee(AjaxBehaviorEvent event) {
		test = "notok";
		listpaie = new ArrayList<Paieprime>();
		listMois = new ArrayList<String>();

		listpaie = new ArrayList<Paieprime>();

		listpaie = servicepaieprime.getPaieByAnnee(Integer.parseInt(annees));

		if (listpaie != null) {
			test = "notok";
			for (Paieprime p1 : listpaie) {
				listGestion = new ArrayList<Lignegestion>();
				listGestion = ligneGestion.getlistbyemplyee(p1.getEmployee());

			}
		}

	}

	public String journalprime() {

		annees = String.valueOf(resolveDefaultNoteYear());
		societe = serviceSociete.getAll().get(0);
		Annee();
		listpaie = new ArrayList<Paieprime>();
		listMois = new ArrayList<String>();
		return SUCCESS;
	}

	public void journalconge2(AjaxBehaviorEvent event) {
		listPai = new ArrayList<Recapulatif>();
		listpaie = new ArrayList<Paieprime>();

		listpaie = servicepaieprime.getPaieByAnnee(Integer.parseInt(annees));
		if (listpaie != null) {

			for (Paieprime p1 : listpaie) {

				p1.getEmployee().setLignegestion(ligneGestion.getlistbyemplyee(p1.getEmployee()));
				p1.setListlignepaiegestions(serviceLigneGestionpaie.getlistbypaie(p1));

			}

			Formule_Paie f = new Formule_Paie();

			double rp = 0;
			double sb = 0;
			double sbr = 0;
			double si = 0;
			double sn = 0;
			double rtn = 0;
			double nap = 0;
			double nb_h = 0;
			listPai = new ArrayList<Recapulatif>();
			for (Paieprime e : listpaie) {
				nb_h = nb_h + e.getFormulaire_Paie().getNb_heure();

				sb = sb + e.getFormulaire_Paie().getSalairedebase();
				rp = rp + e.getFormulaire_Paie().getIrpp();
				sbr = sbr + e.getFormulaire_Paie().getSalaire_brut();
				rtn = rtn + e.getFormulaire_Paie().getRetenue_cnss();
				si = si + e.getFormulaire_Paie().getSalaire_brut();

				sn = sn + e.getFormulaire_Paie().getSalaire_net();
				nap = nap + e.getFormulaire_Paie().getNet_apayer();
			}
			f.setNb_heure(nb_h);

			f.setIrpp(rp);
			f.setSalaire_net(sn);
			f.setNet_apayer(nap);
			f.setRetenue_cnss(rtn);
			f.setSalaire_imposable(si);
			f.setSalaire_brut(sbr);
			f.setSalairedebase(sb);

			Recapulatif r = new Recapulatif();
			r.setAnnee(Integer.parseInt(annees));

			r.setFormul(f);
			listPai.add(r);
			List<Gestion> lg = new ArrayList<Gestion>();
			lg = serviceGestion.getAll();
			for (Gestion l : lg) {
				for (Lignepaiegestionprime e : listpaieGestion) {
					if (e.getLignegestions().getGestion().equals(l)) {
						e.setValeurdeprimeafficher(e.getValeurdeprimeafficher() + e.getValeurdeprime());
					}
				}
			}

			r.setListGestionprime(listpaieGestion);
		}

	}

	public String etatprime() {
		test = "";
		employer = null;
		annee = null;
		listpaie = new ArrayList<Paieprime>();
		listEmployees = new ArrayList<String>();
		listEmployee = new ArrayList<Employee>();
		listEmployee = serviceEmployee.getEmployeeparstats(Status.Declare);

		Annee();
		annees = String.valueOf(resolveDefaultNoteYear());
		societe = serviceSociete.getAll().get(0);
		listpaie = servicepaieprime.getAll();

		Note p = serviceNote.getMaxPointageconge();
		if (p != null) {
			listpaie = servicepaieprime.getPaieByAnnee(p.getAnnee());
			if (listpaie != null) {
				for (Paieprime p1 : listpaie) {

					p1.getEmployee().setLignegestion(ligneGestion.getlistbyemplyee(p1.getEmployee()));
					p1.setListlignepaiegestions(serviceLigneGestionpaie.getlistbypaie(p1));

				}
			}
		}

		return SUCCESS;
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
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, resolveDefaultNoteYear());
		calendar.set(Calendar.MONTH, resolveDefaultNoteMonth() - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(calendar.getTime());
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
		mois = resolveDefaultNoteMonth();
	}

	private void Annee() {
		List<Annee> l = new ArrayList<Annee>();
		listannee = new ArrayList<String>();
		annees = String.valueOf(resolveDefaultNoteYear());
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

	private int resolveDefaultNoteYear() {
		Note latestNote = serviceNote.getMaxPointageconge();
		if (latestNote != null && latestNote.getAnnee() != null) {
			return latestNote.getAnnee();
		}
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	private int resolveDefaultNoteMonth() {
		Note latestNote = serviceNote.getMaxPointageconge();
		if (latestNote != null && latestNote.getMois() != null) {
			return latestNote.getMois();
		}
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	public ServiceAnnee getServiceAnnee() {
		return serviceAnnee;
	}

	public void setServiceAnnee(ServiceAnnee serviceAnnee) {
		this.serviceAnnee = serviceAnnee;
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

	public ServiceNote getServiceNote() {
		return serviceNote;
	}

	public void setServiceNote(ServiceNote serviceNote) {
		this.serviceNote = serviceNote;
	}

	public ServiceGestion getServiceGestion() {
		return serviceGestion;
	}

	public void setServiceGestion(ServiceGestion serviceGestion) {
		this.serviceGestion = serviceGestion;
	}

	public Servicepaieprime getServicepaieprime() {
		return servicepaieprime;
	}

	public void setServicepaieprime(Servicepaieprime servicepaieprime) {
		this.servicepaieprime = servicepaieprime;
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

	public Societe getSociete() {
		return societe;
	}

	public void setSociete(Societe societe) {
		this.societe = societe;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public Note getNotes() {
		return notes;
	}

	public void setNotes(Note notes) {
		this.notes = notes;
	}

	public List<String> getListannee() {
		return listannee;
	}

	public void setListannee(List<String> listannee) {
		this.listannee = listannee;
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

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
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

	public List<Lignegestion> getListGestion() {
		return listGestion;
	}

	public void setListGestion(List<Lignegestion> listGestion) {
		this.listGestion = listGestion;
	}

	public List<String> getListeEmployee() {
		return listeEmployee;
	}

	public void setListeEmployee(List<String> listeEmployee) {
		this.listeEmployee = listeEmployee;
	}

	public List<Paieprime> getListpaie() {
		return listpaie;
	}

	public void setListpaie(List<Paieprime> listpaie) {
		this.listpaie = listpaie;
	}

	public double getTaux() {
		return taux;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public List<Recapulatif> getListPai() {
		return listPai;
	}

	public void setListPai(List<Recapulatif> listPai) {
		this.listPai = listPai;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	public ServiceLigneGestion getLigneGestion() {
		return ligneGestion;
	}

	public void setLigneGestion(ServiceLigneGestion ligneGestion) {
		this.ligneGestion = ligneGestion;
	}

	public ServiceLigneGestionprime getServiceLigneGestionpaie() {
		return serviceLigneGestionpaie;
	}

	public void setServiceLigneGestionpaie(ServiceLigneGestionprime serviceLigneGestionpaie) {
		this.serviceLigneGestionpaie = serviceLigneGestionpaie;
	}

	public List<Lignepaiegestionprime> getListpaieGestion() {
		return listpaieGestion;
	}

	public void setListpaieGestion(List<Lignepaiegestionprime> listpaieGestion) {
		this.listpaieGestion = listpaieGestion;
	}

 
}
