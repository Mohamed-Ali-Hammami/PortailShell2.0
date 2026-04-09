package com.tn.shell.ui.lavage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.tn.shell.model.lavage.AffectationFiltre;
import com.tn.shell.model.lavage.AffectationHuile;
import com.tn.shell.model.lavage.LigneImagerendement;
import com.tn.shell.model.lavage.Marque;
import com.tn.shell.model.lavage.Model;
import com.tn.shell.model.lavage.TypeFiltre;
import com.tn.shell.model.lavage.TypeLavage;
import com.tn.shell.model.paie.Employee;
import com.tn.shell.model.paie.LigneImageEmployee;
import com.tn.shell.model.shop.Poste;
import com.tn.shell.model.shop.Produit;
import com.tn.shell.model.shop.Rendement;
import com.tn.shell.model.shop.Rendement2;
import com.tn.shell.model.shop.Statuss;
import com.tn.shell.model.shop.Statut;
import com.tn.shell.service.lavage.AffectationFiltreService;
import com.tn.shell.service.lavage.AffectationHuileService;
import com.tn.shell.service.lavage.LigneImageRendementService;
import com.tn.shell.service.lavage.MarqueService;
import com.tn.shell.service.lavage.ModelService;
import com.tn.shell.service.paie.ImageEmployeeService;
import com.tn.shell.service.paie.ServiceEmployee;
import com.tn.shell.service.shop.ServiceProduit;
import com.tn.shell.service.shop.ServiceRendement;

@ManagedBean(name = "ServiceLavageBean")
@SessionScoped
public class ServiceLavageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	@ManagedProperty(value = "#{ServiceProduit}")
	transient ServiceProduit serviceProduit;
	@ManagedProperty(value = "#{ServiceEmployee}")
	transient ServiceEmployee serviceEmployee;
	@ManagedProperty(value = "#{MarqueService}")
	transient MarqueService serviceMarque;
	@ManagedProperty(value = "#{ServiceRendement}")
	transient ServiceRendement serviceRendement;
	@ManagedProperty(value = "#{ModelService}")
	transient ModelService serviceModel;
	@ManagedProperty(value = "#{AffectationFiltreService}")
	transient AffectationFiltreService affectationFiltreService;
	@ManagedProperty(value = "#{AffectationHuileService}")
	transient AffectationHuileService affectationHuileService;
	@ManagedProperty(value = "#{LigneImageRendementService}")
	transient LigneImageRendementService ligneImageRendementService;
	@ManagedProperty(value = "#{ImageEmployeeService}")	
	transient ImageEmployeeService imageEmployeeService;
	private BarChartModel barModellageur;
	private BarChartModel barModelservice;
	private List<Marque> listMarque;
	private String matricule2;
	private List<Model> listModel;
	private String model;
	private String marque;
	private String filtre;
	private List<Produit> listProduit;
	private List<Employee> listEmployee;
	private List<AffectationFiltre> listAffectaionFiltre;
	private List<AffectationHuile> listAffectaionhuile;
	private TypeFiltre[] typefiltres;
	private TypeFiltre typefiltre;
	private Marque selectedMarque;
	private Model selctedModel;
	private String emplacement;
	private String fournisseur;
	private BigDecimal metrage;
	private BigDecimal nbvidange;
	private Date date1;
	private Date date2;
	private Poste[] postes;
	private Poste poste;
	private String typerecherche;
	private TypeLavage typalavage;
	private String dates1;
	private TypeLavage[] typalavages;
	private List<Rendement> listRendement;
	private List<Rendement> listRendementService;
	private List<Rendement2> listRendement2;
	private List<Rendement> listRendementLav;
	private Rendement selectedRendement;
	private String laveur;

	private String service;
	private String client;
	private String tel;
	private Integer kmArrivee;
	private String matricule;
	private String licence;
	private String total;
	private String heure_entree;
	private String heure_sortie;
	private Statuss[] statusss;
	private List<AffectationHuile> listHuiles;
	private List<AffectationFiltre> listFiltres;
	private Statuss statuss;
	private Integer nblavage;
	private Integer nbservice;
	private String nbheure;
	private String nbheureservice;
	private String totalMontant;
	private String totalMontantService;
	private String totalMyenne;
	private String totalPoste1;
	private String totalPoste2;

	@PostConstruct
	public void init() {
		createBarModels();
	}

	public String imprimer() {
		return SUCCESS;
	}
	

	public String imprimer2() {
		return SUCCESS;
	}

	public String getListClient() {
		return SUCCESS;
	}

	public String rapport() {
		if (FacesContext.getCurrentInstance() != null && FacesContext.getCurrentInstance().isPostback()) {
			return SUCCESS;
		}
		postes = Poste.values();
		typalavages = TypeLavage.values();
		poste = null;
		typalavage = null;
		listRendement = new ArrayList<Rendement>();
		listRendementLav = new ArrayList<Rendement>();
		listProduit = new ArrayList<Produit>();

		return SUCCESS;
	}

	public String getrapport() {
		//try {
			date1.setHours(0);
			date2.setHours(23);
			date2.setMinutes(59);
			double totalPoste1s = 0;
			double totalPoste2s = 0;
			nblavage = 0;
			nbservice = 0;
			nbheure = null;
			nbheureservice = null;
			totalMontant = null;
			totalMontantService = null;
			totalMyenne = null;
			DecimalFormat dfs = new DecimalFormat("0");
			DecimalFormat dfss = new DecimalFormat("0.000");
			SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
			dates1 = s.format(date1);
			listEmployee = new ArrayList<Employee>();
			listProduit = new ArrayList<Produit>();

			listEmployee = serviceRendement.getEmployeebetwendatesAndPoste(date1, date2, poste, typalavage);
			listProduit = serviceRendement.getProduitbetwendatesAndPoste(date1, date2, poste, typalavage);
			listRendementLav = new ArrayList<Rendement>();
			listRendement = new ArrayList<Rendement>();
			listRendement2 = new ArrayList<Rendement2>();
			double Tmontant = 0;
			double Tmoyenne = 0;
			List<Rendement> ll;
			long nbh = 0;
			long nbm = 0;
			if (listEmployee != null)
				for (Employee e : listEmployee) {
					List<String> heure = new ArrayList<String>();
					String Totalheure = null;
					long th = 0;
					long tm = 0;
					String dates = s.format(date1);

					if (typerecherche.equals("Par Date")) {
						poste = null;
						ll = new ArrayList<Rendement>();
						ll = serviceRendement.getAllventeparDate3(dates, e);

						heure = serviceRendement.totalheureParlaveur(dates1, e);
					} else {
						ll = serviceRendement.getAllventeparDateAndEmployeeAndposte(dates, e, poste);
						heure = serviceRendement.totalheureParlaveurAndposte(dates, e, poste);
					}
					try {
						if (heure != null)
							System.out.println("\n\n size" + heure.size());
						if (heure.size() > 0)
							for (String sh : heure) {
								// if(!s.equals("") ||s.equals(null) ) {
								int h = Integer.parseInt(sh.substring(0, sh.indexOf(":")));
								System.out.println("\n\n\n" + h);
								th = th + h;
								int m = Integer.parseInt(sh.substring(sh.indexOf(":") + 1));
								tm = tm + m;
								// }
							}
						if (tm > 59)
							th = th + (tm / 60);
						tm = tm % 60;

						nbh = nbh + th;
						nbm = nbm + tm;
						Totalheure = th + "H" + tm + "M";
					} catch (Exception exc) {

					}
					Integer qtes = 0;
					double montant = 0;
					double moyenne = 0;
					Rendement r = new Rendement();
					Rendement2 r2 = new Rendement2();
					r.setLaveur(e.getNom());
					r.setHeure_entree(Totalheure);
					r2.setLaveur(e.getNom());
					r2.setHeure_entree(Totalheure);

					if (ll != null)
						for (Rendement rr : ll) {
							qtes = qtes + 1;
							r.setEmployee(rr.getEmployee());
							r2.setEmployee(rr.getEmployee());
							montant = montant + rr.getMontantv() + rr.getMontants();
							moyenne = moyenne + (rr.getMontantv() / 4);

						}

					r.setNbvoitures(dfs.format(qtes));
					r.setNbvoiture(qtes);
					r.setMontantv(montant);
					r.setParouvrier(dfs.format(moyenne));

					r2.setNbvoitures(dfs.format(qtes));
					r2.setNbvoiture(qtes);
					r2.setMontantv(montant);
					r2.setParouvrier(dfs.format(moyenne));
					nblavage = nblavage + qtes;
					Tmontant = Tmontant + montant;
					Tmoyenne = Tmoyenne + moyenne;
					if (qtes > 0) {
						listRendementLav.add(r);
						listRendement2.add(r2);
					}
				}

			if (nbm > 59)
				nbh = nbh + (nbm / 60);
			nbm = nbm % 60;
			nbheure = nbh + "H" + nbm + "M";
			nbh = 0;
			nbm = 0;
			totalMontant = dfss.format(Tmontant);
			totalMyenne = dfss.format(Tmoyenne);
			Tmontant = 0;
			Tmoyenne = 0;
			if (listProduit != null)
				for (Produit e : listProduit) {
					List<String> heure = null;
					String Totalheure = null;
					long th = 0;
					long tm = 0;
					String dates = s.format(date1);
					ll = new ArrayList<Rendement>();
					if (typerecherche.equals("Par Date")) {
						poste = null;
						ll = serviceRendement.getAllventeparDate3AndProduit(dates, e);
						heure = serviceRendement.totalheureParService(dates1, e);
					} else {
						ll = serviceRendement.getAllventeparDateAndServiceAndposte(dates, e, poste);
						heure = serviceRendement.totalheureParServiceaNDpOSTE(dates, e, poste);
					}
					try {
						if (heure.size() > 0)
							for (String sh : heure) {
								// if(!s.equals("") ||s.equals(null) ) {
								int h = Integer.parseInt(sh.substring(0, sh.indexOf(":")));
								th = th + h;
								int m = Integer.parseInt(sh.substring(sh.indexOf(":") + 1));
								tm = tm + m;
								// }
							}

						if (tm > 59)
							th = th + (tm / 60);
						tm = tm % 60;

						nbh = nbh + th;
						nbm = nbm + tm;
						Totalheure = th + "H" + tm + "M";
					} catch (Exception exc) {

					}
					Integer qtes = 0;
					double montant = 0;
					double moyenne = 0;
					Rendement r = new Rendement();
					r.setHeure_entree(Totalheure);

					// ll = serviceRendement.getAllventeparDate3AndProduit(dates, e);

					if (ll != null)
						for (Rendement rr : ll) {
							qtes = qtes + 1;
							r.setService(rr.getLignevente().getProduit().getNom());
							montant = montant + rr.getMontantv() + rr.getMontants();
							moyenne = moyenne + (rr.getMontantv() / 4);
						}

					r.setNbvoitures(dfs.format(qtes));
					r.setNbvoiture(qtes);
					r.setMontantv(montant);
					r.setService(e.getNom());
					r.setParouvrier(dfs.format(moyenne));
					nbservice = nbservice + qtes;
					Tmontant = Tmontant + montant;
					Tmoyenne = Tmoyenne + moyenne;
					if (qtes > 0)
						listRendement.add(r);

				}

			totalMontantService = dfss.format(Tmontant);

			if (nbm > 59)
				nbh = nbh + (nbm / 60);
			nbm = nbm % 60;
			nbheureservice = nbh + "H" + nbm + "M";

			Collections.sort(listRendement);
			Collections.reverse(listRendement);

			Collections.sort(listRendementLav);
			Collections.reverse(listRendementLav);

			Collections.sort(listRendement2);
			Collections.reverse(listRendement2);
			createBarModels();

			date1.setHours(0);
			date2.setHours(23);
			listRendementService = new ArrayList<Rendement>();
			if (typerecherche.contentEquals("Par Date")) {
				listRendementService = new ArrayList<Rendement>();
				listRendementService = serviceRendement.getbetwendates(date1, date2, typalavage, Statuss.Cloture);

			} else {
				if (poste != null) {
					listRendementService = new ArrayList<Rendement>();
					listRendementService = serviceRendement.getbetwendatesAndPoste(date1, date2, poste, typalavage,
							Statuss.Cloture);
				} else {
					listRendementService = new ArrayList<Rendement>();
					listRendementService = serviceRendement.getbetwendates(date1, date2, typalavage, Statuss.Cloture);
				}
			}
			double total = 0;
			if (listRendementService != null && ! typerecherche.contentEquals("Par Date"))
				for (Rendement r : listRendementService) {
					total = total + r.getMontantv() + r.getMontants();
					LigneImagerendement l = ligneImageRendementService.findByRendementIdAndPosition(r.getId(), "avant");

					if (l != null)
						r.setImage(l.getImage());
					l = ligneImageRendementService.findByRendementIdAndPosition(r.getId(), "deriere");
					if (l != null)
					r.setAriere(Base64.getMimeEncoder().encodeToString(l.getImage().getImage()));
					l = ligneImageRendementService.findByRendementIdAndPosition(r.getId(), "droit");
					if (l != null)
					r.setDroit(Base64.getMimeEncoder().encodeToString(l.getImage().getImage()));
					 
					l = ligneImageRendementService.findByRendementIdAndPosition(r.getId(), "gauche");
					if (l != null)
					r.setGauche(Base64.getMimeEncoder().encodeToString(l.getImage().getImage()));
				}
			
			if (typerecherche.equals("Par Date")) {
				if (listRendementService != null)
					for (Rendement r : listRendementService) {

						if (r.getPoste().equals(Poste.Poste1))
							totalPoste1s = totalPoste1s + r.getMontants() + r.getMontantv();
						else
							totalPoste2s = totalPoste2s + r.getMontants() + r.getMontantv();

					}
				totalPoste1 = null;
				totalPoste2 = null;
				totalPoste1 = dfss.format(totalPoste1s);
				totalPoste2 = dfss.format(totalPoste2s);
			}
			this.total = dfss.format(total);
		
		/*} catch (Exception ee) {

		}*/
		return SUCCESS;
	}

	private void createBarModels() {
		createBarModellaveur();
		createBarModelMontant();

	}

	private void createBarModellaveur() {
		barModellageur = initBarModel();

		barModellageur.setTitle(typalavage + " par personnel");
		barModellageur.setLegendPosition("ne");
		barModellageur.setShowPointLabels(true);
		Axis xAxis = barModellageur.getAxis(AxisType.X);
		// xAxis.setLabel("Nbre Lavage");
		xAxis.setTickAngle(-30);
		barModellageur.setSeriesColors("0f056b, 77933c");
		Axis yAxis = barModellageur.getAxis(AxisType.Y);
		// yAxis.setLabel("Nbre Lavage");
		yAxis.setMin(0);
		yAxis.setMax(14);
	}

	private void createBarModelMontant() {
		barModelservice = initBarModelService();
		barModelservice.setTitle("Montant par personnel");
		barModelservice.setLegendPosition("ne");
		barModelservice.setShowPointLabels(true);
		barModelservice.setSeriesColors("FF0000, 77933c");
		Axis xAxis = barModelservice.getAxis(AxisType.X);
		xAxis.setTickAngle(-30);
		// xAxis.setLabel("Montant");
		Axis yAxis = barModelservice.getAxis(AxisType.Y);
		// yAxis.setLabel("Montant");
		yAxis.setMin(0);
		yAxis.setMax(100);
	}

	public void itemSelect(ItemSelectEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
				"Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();

		ChartSeries boys = new ChartSeries();
		boys.setLabel("Nbre Lavage");
		if (listRendementLav != null)
			for (Rendement r : listRendementLav) {
				boys.set(r.getLaveur(), r.getNbvoiture());
			}

		/*
		 * boys.set("2005", 100); boys.set("2006", 44); boys.set("2007", 150);
		 * boys.set("2008", 25);
		 */

		model.addSeries(boys);

		return model;
	}

	private BarChartModel initBarModelService() {
		BarChartModel model = new BarChartModel();

		ChartSeries boys = new ChartSeries();
		boys.setLabel("Montant");
		if (listRendement2 != null)
			for (Rendement2 r : listRendement2) {
				boys.set(r.getLaveur(), r.getMontantv());
			}

		model.addSeries(boys);

		return model;
	}

	public String updateHitoric() {
		try {
			statusss = Statuss.values();
			if (selectedRendement != null) {
				LigneImagerendement l = ligneImageRendementService
						.findByRendementIdAndPosition(selectedRendement.getId(), "avant");
               LigneImageEmployee l2= imageEmployeeService.getImagebyEmployeeandpositions(selectedRendement.getEmployee(), "cin");
				 if (l2 != null)
					selectedRendement.getEmployee().setPhotos(Base64.getMimeEncoder().encodeToString(
							l2.getImage().getImage()));
				if (l != null)
					selectedRendement.setImage(l.getImage());
				l = ligneImageRendementService.findByRendementIdAndPosition(selectedRendement.getId(), "deriere");
				if (l != null)
				selectedRendement.setAriere(Base64.getMimeEncoder().encodeToString(l.getImage().getImage()));
				l = ligneImageRendementService.findByRendementIdAndPosition(selectedRendement.getId(), "droit");
				if (l != null)
				selectedRendement.setDroit(Base64.getMimeEncoder().encodeToString(l.getImage().getImage()));
				l = ligneImageRendementService.findByRendementIdAndPosition(selectedRendement.getId(), "gauche");
				if (l != null)
				selectedRendement.setGauche(Base64.getMimeEncoder().encodeToString(l.getImage().getImage()));

				laveur = selectedRendement.getLaveur();
				service = selectedRendement.getService();
				client = selectedRendement.getClient();
				tel = selectedRendement.getTel();
				kmArrivee = selectedRendement.getKmArrivee();
				licence = selectedRendement.getLicence();
				heure_entree = selectedRendement.getHeure_entree();
				marque = selectedRendement.getMarque();
				statuss = selectedRendement.getStatuss();
				model = selectedRendement.getModel();
				matricule = selectedRendement.getMatricule();
				heure_sortie = selectedRendement.getHeure_sortie();
				if (selectedRendement.getTypelavage().equals(TypeLavage.Lavage)) {
					listProduit = new ArrayList<Produit>();
					listProduit = serviceProduit.getAllbyfamille2("LAVAGE");
					listEmployee = new ArrayList<Employee>();
					listEmployee = serviceEmployee.getEmployeeByFonction("LAVEUR");
				} else {
					listProduit = new ArrayList<Produit>();
					listProduit = serviceProduit.getAllbyfamille2("VIDANGE");
					listEmployee = new ArrayList<Employee>();
					listEmployee = serviceEmployee.getEmployeeByFonction("VIDANGEUR");
				}
			}
		} catch (Exception ee) {

		}
		return SUCCESS;
	}
	private Timestamp convertTime(String ch) {
		 
		  try {
			    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
			    Date parsedDate = dateFormat.parse(ch);
			    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			    System.out.println("time"+timestamp);
			    return timestamp;
			} catch(Exception e) { //this generic but you can control another types of exception
			   System.out.println(e.getMessage());
			   return null;
			   
			}
	}

	public String getLavageEcours() {
		try {
			listRendement = new ArrayList<Rendement>();
			listRendement = serviceRendement.getAllbyStatuss(Statuss.Encours, TypeLavage.Vidange);
			listRendementLav = new ArrayList<Rendement>();
			listRendementLav = serviceRendement.getAllbyStatuss(Statuss.Encours, TypeLavage.Lavage);
		 for (Rendement r : listRendementLav) {				 
				Produit p=serviceProduit.Findbydes(r.getService());
				 Date uDate = new Date();
				 Date dateFin = new Date ();
				 Timestamp timeentrre=convertTime(r.getHeure_entree());
				 uDate.setHours(timeentrre.getHours());
				 uDate.setMinutes(timeentrre.getMinutes());
				 uDate.setSeconds(timeentrre.getSeconds());	 				 
				  Date duree = new Date (); //Pour calculer la difference
				  duree.setTime (dateFin.getTime () - uDate.getTime ());				  
				  long secondes = duree.getTime () / 1000;
				  System.out.println("\n\n seconds"+secondes);
				 long min = secondes / 60;
				 long heures = min / 60;		 
				 secondes %= 60;
				  if(min<60)
				 r.setDuree( heures + ":" + min  );
				 else 
					 r.setDuree( heures + ":" + secondes );				      
				 uDate=new Date();
				 dateFin = new Date ();
				 uDate.setHours((int) heures);
				 uDate.setMinutes((int) min);
				 uDate.setSeconds(0);				 
				 if(p!=null) {
				 String duration=p.getDuree();			 
				 dateFin.setHours( Integer.parseInt(duration.substring(0,2)));
				 dateFin.setMinutes(Integer.parseInt(duration.substring(3)));
				 dateFin.setSeconds(0); 
				 duree.setTime (  uDate.getTime ()-dateFin.getTime ()); 
				 long Secart= duree.getTime () / 1000;
				 long Mecart = Secart / 60;
				 long Hecart = Mecart / 60;		 
				 secondes %= 60;
                
				  if(Hecart>0 || Mecart>0) {
					   r.setTestDuree(true);
				  } 
			}
				 
			}
			
			if (listRendement != null)
				for (Rendement r : listRendement) {
					LigneImageEmployee l2= imageEmployeeService.getImagebyEmployeeandpositions(r.getEmployee(), "cin");
					if(l2!=null)
					r.getEmployee().setPhotos(Base64.getMimeEncoder().encodeToString(
								l2.getImage().getImage()));
					LigneImagerendement l = ligneImageRendementService.findByRendementIdAndPosition(r.getId(), "avant");

					if (l != null)
						r.setImage(l.getImage());

				}

			if (listRendementLav != null)
				for (Rendement r : listRendementLav) {
					LigneImageEmployee l2= imageEmployeeService.getImagebyEmployeeandpositions(r.getEmployee(), "cin");
					if(l2!=null)
					r.getEmployee().setPhotos(Base64.getMimeEncoder().encodeToString(
								l2.getImage().getImage()));

					LigneImagerendement l = ligneImageRendementService.findByRendementIdAndPosition(r.getId(), "avant");

					if (l != null)
						r.setImage(l.getImage());

				}
		} catch (Exception ee) {

		}
		return SUCCESS;
	}

	public String supprimerRendement() {
		serviceRendement.delete(selectedRendement);
		listRendement = new ArrayList<Rendement>();
		listRendement = serviceRendement.getbetwendates(date1, date2, typalavage, Statuss.Ferme);
		return SUCCESS;
	}

	public String saveUpdate() {
		
		Employee e = serviceEmployee.getEmployeeByNom(laveur);
		Produit p = serviceProduit.Findbydes(service);
		
		selectedRendement.setClient(client);
		selectedRendement.setTel(tel);
		selectedRendement.setKmArrivee(kmArrivee);
		selectedRendement.setLicence(licence);
		selectedRendement.setHeure_entree(heure_entree);
		
		selectedRendement.setMarque(marque);
		selectedRendement.setModel(model);
		selectedRendement.setStatuss(statuss);
		// selectedRendement.setHeure_sortie(heure_sortie);
		selectedRendement.setMatricule(matricule);
		selectedRendement.setLaveur(laveur);
		if (e != null)
			selectedRendement.setEmployee(e);
		if (selectedRendement.getStatuss().equals(Statuss.Encours)) {
			
			if (p != null) {
				selectedRendement.setService(p.getNom());
				if (!p.getNom().contains("S")) {
					selectedRendement.setMontantv(p.getVente());
					selectedRendement.setMontants(0);
				} else {
					selectedRendement.setMontantv(0);
					selectedRendement.setMontants(p.getVente());
				}
			}
		}
		
		serviceRendement.update(selectedRendement);
		return SUCCESS;
	}
	 
	public void onClose(CloseEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed",
				"Closed panel id:'" + event.getComponent().getId() + "'");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void onToggle(ToggleEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled",
				"Status:" + event.getVisibility().name());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String historiqueServices() {
		if (FacesContext.getCurrentInstance() != null && FacesContext.getCurrentInstance().isPostback()) {
			return SUCCESS;
		}
		postes = Poste.values();
		date1 = new Date();
		date2 = new Date();
		typalavages = TypeLavage.values();
		poste = null;
		typalavage = null;
		listRendement = new ArrayList<Rendement>();
		listProduit = new ArrayList<Produit>();
		listProduit = serviceProduit.getAllbyfamille2("LAVAGE");
		return SUCCESS;
	}
	
	public String  historiqueDuJour() {
		if (FacesContext.getCurrentInstance() != null && FacesContext.getCurrentInstance().isPostback()) {
			return SUCCESS;
		}
		postes = Poste.values();
		date1 = new Date();
		date2 = new Date();
		typalavages = TypeLavage.values();
		poste = null;
		typalavage = null;
		listRendement = new ArrayList<Rendement>();
		listProduit = new ArrayList<Produit>();
		listProduit = serviceProduit.getAllbyfamille2("LAVAGE");
		return SUCCESS;
	}
	
	public String gethistoriqueDuJour() {
		double totals=0;
		if (date1 == null) {
			date1 = new Date();
		}
		if (date2 == null) {
			date2 = new Date();
		}
		date1.setHours(0);
		date1.setMinutes(0);
		date1.setSeconds(0);
		date2.setHours(23);
		date2.setMinutes(59);
		date2.setSeconds(59);
		listRendement=new ArrayList<Rendement>();
		listRendement=serviceRendement.getbetwendates(date1, date2, typalavage, Statuss.Ferme);
	if (listRendement != null)
		for (Rendement r : listRendement) {
		 
			LigneImagerendement l = ligneImageRendementService.findByRendementIdAndPosition(r.getId(), "avant");

			if (l != null)
				r.setImage(l.getImage());
			l = ligneImageRendementService.findByRendementIdAndPosition(r.getId(), "deriere");
			if (l != null)
			r.setAriere(Base64.getMimeEncoder().encodeToString(l.getImage().getImage()));
			l = ligneImageRendementService.findByRendementIdAndPosition(r.getId(), "droit");
			if (l != null)
			r.setDroit(Base64.getMimeEncoder().encodeToString(l.getImage().getImage()));
			l = ligneImageRendementService.findByRendementIdAndPosition(r.getId(), "gauche");
			if (l != null)
			r.setGauche(Base64.getMimeEncoder().encodeToString(l.getImage().getImage()));
		}
	if (listRendement != null)
		for (Rendement r : listRendement) {
			totals = totals + r.getMontantv() + r.getMontants();
		}
	
	DecimalFormat dfss = new DecimalFormat("0.000");
	total=dfss.format(totals);
	return SUCCESS;
	}
	
	public String gethistoriqueServices() {
		total = "";
		double total = 0;
		date1.setHours(0);
		date2.setHours(23);
		date2.setMinutes(59);
		DecimalFormat dfss = new DecimalFormat("0.000");
		
	
      try {
		if(typerecherche.equals("Par Date")) {
			listRendement = new ArrayList<Rendement>();
			listRendement = serviceRendement.getbetwendatesAndtypelavage(date1,date2,typalavage);
		 }
		else {			
			listRendement = new ArrayList<Rendement>();
			if(poste!=null)
			listRendement=serviceRendement.getbetwendatesAndtypelavageAndPoste(date1, date2, typalavage, poste);
		}
		
		if((matricule!=null && matricule2!=null)&&(matricule.length()>0 && matricule2.length()>0) ) {
			listRendement = new ArrayList<Rendement>();
			listRendement =  serviceRendement.getAllbymatricule(matricule+" TU "+matricule2);
		}
	
		if (listRendement != null)
			for (Rendement r : listRendement) {
				total = total + r.getMontantv() + r.getMontants();
				/*LigneImagerendement l = ligneImageRendementService.findByRendementIdAndPosition(r.getId(), "avant");

				if (l != null)
					r.setImage(l.getImage());
				l = ligneImageRendementService.findByRendementIdAndPosition(r.getId(), "deriere");
				if (l != null)
				r.setAriere(Base64.getMimeEncoder().encodeToString(l.getImage().getImage()));
				l = ligneImageRendementService.findByRendementIdAndPosition(r.getId(), "droit");
				if (l != null)
				r.setDroit(Base64.getMimeEncoder().encodeToString(l.getImage().getImage()));
				l = ligneImageRendementService.findByRendementIdAndPosition(r.getId(), "gauche");
				if (l != null)
				r.setGauche(Base64.getMimeEncoder().encodeToString(l.getImage().getImage()));*/
			}
		
}
catch(Exception ee) {
			
		}
      
this.total = dfss.format(total);
		return SUCCESS;
	}

	public ServiceProduit getServiceProduit() {
		return serviceProduit;
	}

	public void setServiceProduit(ServiceProduit serviceProduit) {
		this.serviceProduit = serviceProduit;
	}

	public MarqueService getServiceMarque() {
		return serviceMarque;
	}

	public void setServiceMarque(MarqueService serviceMarque) {
		this.serviceMarque = serviceMarque;
	}

	public ModelService getServiceModel() {
		return serviceModel;
	}

	public void setServiceModel(ModelService serviceModel) {
		this.serviceModel = serviceModel;
	}

	public List<Marque> getListMarque() {
		return listMarque;
	}

	public void setListMarque(List<Marque> listMarque) {
		this.listMarque = listMarque;
	}

	public List<Model> getListModel() {
		return listModel;
	}

	public void setListModel(List<Model> listModel) {
		this.listModel = listModel;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public List<Produit> getListProduit() {
		return listProduit;
	}

	public void setListProduit(List<Produit> listProduit) {
		this.listProduit = listProduit;
	}

	public String getFiltre() {
		return filtre;
	}

	public void setFiltre(String filtre) {
		this.filtre = filtre;
	}

	public TypeFiltre[] getTypefiltres() {
		return typefiltres;
	}

	public void setTypefiltres(TypeFiltre[] typefiltres) {
		this.typefiltres = typefiltres;
	}

	public TypeFiltre getTypefiltre() {
		return typefiltre;
	}

	public void setTypefiltre(TypeFiltre typefiltre) {
		this.typefiltre = typefiltre;
	}

	public Marque getSelectedMarque() {
		return selectedMarque;
	}

	public void setSelectedMarque(Marque selectedMarque) {
		this.selectedMarque = selectedMarque;
	}

	public Model getSelctedModel() {
		return selctedModel;
	}

	public void setSelctedModel(Model selctedModel) {
		this.selctedModel = selctedModel;
	}

	public String getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	public String getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}

	public AffectationFiltreService getAffectationFiltreService() {
		return affectationFiltreService;
	}

	public void setAffectationFiltreService(AffectationFiltreService affectationFiltreService) {
		this.affectationFiltreService = affectationFiltreService;
	}

	public List<AffectationFiltre> getListAffectaionFiltre() {
		return listAffectaionFiltre;
	}

	public void setListAffectaionFiltre(List<AffectationFiltre> listAffectaionFiltre) {
		this.listAffectaionFiltre = listAffectaionFiltre;
	}

	public AffectationHuileService getAffectationHuileService() {
		return affectationHuileService;
	}

	public void setAffectationHuileService(AffectationHuileService affectationHuileService) {
		this.affectationHuileService = affectationHuileService;
	}

	public List<AffectationHuile> getListAffectaionhuile() {
		return listAffectaionhuile;
	}

	public void setListAffectaionhuile(List<AffectationHuile> listAffectaionhuile) {
		this.listAffectaionhuile = listAffectaionhuile;
	}

	public BigDecimal getMetrage() {
		return metrage;
	}

	public void setMetrage(BigDecimal metrage) {
		this.metrage = metrage;
	}

	public BigDecimal getNbvidange() {
		return nbvidange;
	}

	public void setNbvidange(BigDecimal nbvidange) {
		this.nbvidange = nbvidange;
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

	public String getTyperecherche() {
		return typerecherche;
	}

	public void setTyperecherche(String typerecherche) {
		this.typerecherche = typerecherche;
	}

	public List<Rendement> getListRendement() {
		return listRendement;
	}

	public void setListRendement(List<Rendement> listRendement) {
		this.listRendement = listRendement;
	}

	public ServiceRendement getServiceRendement() {
		return serviceRendement;
	}

	public void setServiceRendement(ServiceRendement serviceRendement) {
		this.serviceRendement = serviceRendement;
	}

	public TypeLavage getTypalavage() {
		return typalavage;
	}

	public void setTypalavage(TypeLavage typalavage) {
		this.typalavage = typalavage;
	}

	public TypeLavage[] getTypalavages() {
		return typalavages;
	}

	public void setTypalavages(TypeLavage[] typalavages) {
		this.typalavages = typalavages;
	}

	public Rendement getSelectedRendement() {
		return selectedRendement;
	}

	public void setSelectedRendement(Rendement selectedRendement) {
		this.selectedRendement = selectedRendement;
	}

	public String getLaveur() {
		return laveur;
	}

	public void setLaveur(String laveur) {
		this.laveur = laveur;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getKmArrivee() {
		return kmArrivee;
	}

	public void setKmArrivee(Integer kmArrivee) {
		this.kmArrivee = kmArrivee;
	}

	public List<AffectationHuile> getListHuiles() {
		return listHuiles;
	}

	public void setListHuiles(List<AffectationHuile> listHuiles) {
		this.listHuiles = listHuiles;
	}

	public List<AffectationFiltre> getListFiltres() {
		return listFiltres;
	}

	public void setListFiltres(List<AffectationFiltre> listFiltres) {
		this.listFiltres = listFiltres;
	}

	public Statuss getStatuss() {
		return statuss;
	}

	public void setStatuss(Statuss statuss) {
		this.statuss = statuss;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public String getHeure_entree() {
		return heure_entree;
	}

	public void setHeure_entree(String heure_entree) {
		this.heure_entree = heure_entree;
	}

	public String getHeure_sortie() {
		return heure_sortie;
	}

	public void setHeure_sortie(String heure_sortie) {
		this.heure_sortie = heure_sortie;
	}

	public Statuss[] getStatusss() {
		return statusss;
	}

	public void setStatusss(Statuss[] statusss) {
		this.statusss = statusss;
	}

	public List<Employee> getListEmployee() {
		return listEmployee;
	}

	public void setListEmployee(List<Employee> listEmployee) {
		this.listEmployee = listEmployee;
	}

	public ServiceEmployee getServiceEmployee() {
		return serviceEmployee;
	}

	public void setServiceEmployee(ServiceEmployee serviceEmployee) {
		this.serviceEmployee = serviceEmployee;
	}

	public List<Rendement> getListRendementLav() {
		return listRendementLav;
	}

	public void setListRendementLav(List<Rendement> listRendementLav) {
		this.listRendementLav = listRendementLav;
	}

	public String getDates1() {
		return dates1;
	}

	public void setDates1(String dates1) {
		this.dates1 = dates1;
	}

	public Integer getNblavage() {
		return nblavage;
	}

	public void setNblavage(Integer nblavage) {
		this.nblavage = nblavage;
	}

	public Integer getNbservice() {
		return nbservice;
	}

	public void setNbservice(Integer nbservice) {
		this.nbservice = nbservice;
	}

	public String getNbheure() {
		return nbheure;
	}

	public void setNbheure(String nbheure) {
		this.nbheure = nbheure;
	}

	public String getNbheureservice() {
		return nbheureservice;
	}

	public void setNbheureservice(String nbheureservice) {
		this.nbheureservice = nbheureservice;
	}

	public String getTotalMontant() {
		return totalMontant;
	}

	public void setTotalMontant(String totalMontant) {
		this.totalMontant = totalMontant;
	}

	public String getTotalMontantService() {
		return totalMontantService;
	}

	public void setTotalMontantService(String totalMontantService) {
		this.totalMontantService = totalMontantService;
	}

	public String getTotalMyenne() {
		return totalMyenne;
	}

	public void setTotalMyenne(String totalMyenne) {
		this.totalMyenne = totalMyenne;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public BarChartModel getBarModellageur() {
		return barModellageur;
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

	public LigneImageRendementService getLigneImageRendementService() {
		return ligneImageRendementService;
	}

	public void setLigneImageRendementService(LigneImageRendementService ligneImageRendementService) {
		this.ligneImageRendementService = ligneImageRendementService;
	}

	public void setBarModellageur(BarChartModel barModellageur) {
		this.barModellageur = barModellageur;
	}

	public BarChartModel getBarModelservice() {
		return barModelservice;
	}

	public void setBarModelservice(BarChartModel barModelservice) {
		this.barModelservice = barModelservice;
	}

	public List<Rendement2> getListRendement2() {
		return listRendement2;
	}

	public void setListRendement2(List<Rendement2> listRendement2) {
		this.listRendement2 = listRendement2;
	}

	public List<Rendement> getListRendementService() {
		return listRendementService;
	}

	public void setListRendementService(List<Rendement> listRendementService) {
		this.listRendementService = listRendementService;
	}

	public String getMatricule2() {
		return matricule2;
	}

	public void setMatricule2(String matricule2) {
		this.matricule2 = matricule2;
	}

	public ImageEmployeeService getImageEmployeeService() {
		return imageEmployeeService;
	}

	public void setImageEmployeeService(ImageEmployeeService imageEmployeeService) {
		this.imageEmployeeService = imageEmployeeService;
	}
	 

}
