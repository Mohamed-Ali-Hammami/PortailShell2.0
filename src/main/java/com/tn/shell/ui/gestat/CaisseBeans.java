package com.tn.shell.ui.gestat;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.faces.model.DataModel;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;

import com.tn.shell.gestat.serviceftp.FTPConnectionLogin;
import com.tn.shell.model.gestat.*;
 
import com.tn.shell.model.paie.Employee;
import com.tn.shell.model.shop.Produit;
import com.tn.shell.model.transport.Depense;
import com.tn.shell.model.transport.Vhecule;
import com.tn.shell.service.gestat.*;
 
import com.tn.shell.service.paie.ServiceEmployee;
import com.tn.shell.service.shop.ServiceProduit;
import com.tn.shell.service.transport.ServiceDepense;
import com.tn.shell.service.transport.ServiceFamilleDepense;
import com.tn.shell.service.transport.ServiceVhecule;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;

@ManagedBean(name = "CaisseBeans")
@SessionScoped
public class CaisseBeans {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	@ManagedProperty(value = "#{ServiceJournal}")
	ServiceJournal servicejornal;
	@ManagedProperty(value = "#{ServiceJournalDep}")
    ServiceJournalDep serviceJournaldep;
	@ManagedProperty(value = "#{ServiceCaisse}")
	ServiceCaisse serviceCaisse;
	@ManagedProperty(value = "#{ServiceVhecule}")
	ServiceVhecule serviceVehecule;
 
	@ManagedProperty(value = "#{ServiceEmployee}")
	ServiceEmployee serviceemployees;
	@ManagedProperty(value = "#{ServiceFamilleDepensegestat}")
	ServiceFamilleDepensegestat serviceFamilleDepense;
	@ManagedProperty(value = "#{ServiceDepensegestat}")
	ServiceDepensegestat serviceDepense;
	@ManagedProperty(value = "#{ServiceDepense}")
	ServiceDepense servicedepense;

	@ManagedProperty(value = "#{ServiceTracegestat}")
	ServiceTracegestat servicetrace;
	@ManagedProperty(value = "#{UserService}")
	UserService userservice;
	/*
	 * @ManagedProperty(value = "#{ServiceEmployeegestat}") ServiceEmployeegestat
	 * serviceEmployee;
	 */
	@ManagedProperty(value = "#{ServicePompiste}")
	ServicePompiste servicePompiste;
	@ManagedProperty(value = "#{ServiceCheque}")
	ServiceCheque serviceCheque;
	@ManagedProperty(value = "#{ServiceAvancegestat}")
	ServiceAvancegestat serviceAvance;
	@ManagedProperty(value = "#{ServiceAchatcaisse}")
	ServiceAchatcaisse serviceAchatcaisse;
	@ManagedProperty(value = "#{ServiceCreditanterieur}")
	ServiceCreditanterieur serviceCreditanterieur;
	@ManagedProperty(value = "#{ServiceCreditclient}")
	ServiceCreditclient serviceCreditclient;
	@ManagedProperty(value = "#{ServiceFamilleDepense}")
	ServiceFamilleDepense serviceFamilleDepenset;
	@ManagedProperty(value = "#{ServiceClientgestat}")
	ServiceClientgestat serviceClient;

	@ManagedProperty(value = "#{ServiceProduit}")
	ServiceProduit serviceProduit;
	/*
	 * @ManagedProperty(value = "#{ServiceLigneventeboncredit}")
	 * ServiceLigneventeboncredit serviceLignevente;
	 */
	@ManagedProperty(value = "#{ServiceRetourcuve}")
	ServiceRetourcuve serviceRetourcuve;
	@ManagedProperty(value = "#{ServicePompe}")
	ServicePompe servicepompe;
	@ManagedProperty(value = "#{ServiceLigneindex}")
	ServiceLigneindex serviceLigneindex;
	@ManagedProperty(value = "#{ServiceArticleCarburant}")
	ServiceArticleCarburant serviceArticleCarburant;
	 
	private Retourcuve retourcuve;
	

	@ManagedProperty(value = "#{ServiceSoldetpe}")
	ServiceSoldetpe serviceSoldetpe;
	private Soldetpe soldetpe;
	private List<Clientgestat> filteredClients;
	private List<Retourcuve> retourcuves;
	private List<Pompe> pompes;
	private Pompe pompe;

	private List<Produit> listproduits;
	private Produit produit;
	private List<Ligneventecredit> ligneventes;
	private Ligneventecredit lignevente;
	private Caisse caisse;
	private Date date;
	private String dates;
	private Poste poste;
	private Poste[] postes;
	private Depensegestat depense;
	private Depensegestat depense1;
	private Integer codes;
	private List<Depensegestat> listdepense;
	private Familledepensegestat familledepense;
	private String libelle;
	private List<Familledepensegestat> listfamilledepense;
	private double total;
	private String totals;
	private Integer id;
	private double montant;
	private String nom;
	private Employee employee;
	private List<Employee> listeEmployee;
	private List<Cheque> listcheque;
	private Cheque cheque;
	private List<Avancegestat> listAvances;
	private Avancegestat avance;
	private Achatcaisse achatcaisse;
	private List<Achatcaisse> listachatcaisse;
	private String totalcaisse;
	private String observations;
	private Creditanterieur creditanterieur;
	private List<Creditanterieur> listCreditanterieur;

	private Credit credit;
	private List<TransactionCredit> listjournal;
	private List<Clientgestat> listclients;
	private Clientgestat client;
	private double totalcarteshell;
	private double totalcartebanqaire;
	private List<Ligneindex> ligneindex;
	private Ligneindex lignindex;
	private double EL1;
	private double EL2;
	private double EL3;
	private double EL4;
	private double EL5;
	private double totalLitrage;
	private double totalventecarburant;

	private String ELs1;
	private String ELs2;
	private String ELs3;
	private String ELs4;
	private String ELs5;
	private String totalLitrages;
	private String totalventecarburants;
	private double totalesece;

	public String miseajourindex() {
		postes = Poste.values();

		// Ligneindex l =
		// serviceLigneindex.getAllventepardateandpompeandposte2(servicepompe.Findbycode(1),serviceCaisse.getmaxcode());
		ligneindex = new ArrayList<Ligneindex>();
		ligneindex = serviceLigneindex.getAllparposte(serviceCaisse.getmaxcode());
		if(ligneindex.size()>0) {
		date = ligneindex.get(0).getDate();
		poste = ligneindex.get(0).getPoste();
		}
		else {
			date=new Date();
			poste=Poste.Poste1;
		}
		return SUCCESS;
	}

	

	public String validermiseajourindex() {
		double l = 0;
		double q = 0;
		for (Ligneindex x : ligneindex) {
			x.setQuantite(x.getIndexfermuture()-x.getIndexouverture());
			x.setMantant(x.getQuantite()*x.getPompe().getArticlecarburant().getVente());
			l = l + x.getMantant();
			q = q + x.getQuantite();
			serviceLigneindex.update(x);

		}
		// caisse=serviceCaisse.getCaissebydate(dates, poste);
		caisse = ligneindex.get(0).getCaisse();
		caisse.setTotalventecarburant(l);
		caisse.setTotallitrage(q);
		double tc = 0;
		double tcs = 0;
		if (caisse.getTotalcreditanterieur() != 0)
			tc = caisse.getTotalventecarburant() + caisse.getTotalcreditanterieur();
		else
			tc = caisse.getTotalventecarburant();

		tcs = caisse.getTotalachat() + caisse.getTotalcheque() + caisse.getTotalcredit() + caisse.getCartebanquaire()
				+ caisse.getTotaldepense() + caisse.getTotalmanque() + caisse.getRetourcuve() + caisse.getCarteshell();
		caisse.setTotalcaisse(tc - tcs);
		caisse.setObservation(caisse.getTotalespece() - caisse.getTotalcaisse());
		if (caisse.getObservation() > 0) {
			caisse.setRemarques("UN PLUS");
		} else
			caisse.setRemarques("UN MANQUE");
		serviceCaisse.update(caisse);
		totalcaisse = caisse.getTotalcaisses();
		observations = caisse.getObservations();
		poste = caisse.getPoste();
		return SUCCESS;

	}

	public void getindechargerboncreditxbydateandposte(AjaxBehaviorEvent event) {
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		dates = f.format(date);
		ligneindex = new ArrayList<Ligneindex>(ligneindex);
		ligneindex = serviceLigneindex.getAllventeparposte(dates, poste);

	}

	public void updatemontantindex2(ValueChangeEvent event) {
		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
		Ligneindex x = ligneindex.get(codes);
		x.setIndexfermuture((Double) event.getNewValue());
		serviceLigneindex.update(x);
	}

	public String validerindex() {
		if (ligneindex != null) {
			try {
				BufferedReader IN = new BufferedReader(new FileReader("M:/FTP_E00/num.txt"));
				Integer s = Integer.parseInt(IN.readLine());
				IN.close();
				File fichier2 = new File("M:/FTP_E00/num.txt"); // definir l'arborescence
				fichier2.createNewFile();
				FileWriter ffw = new FileWriter(fichier2);
				s = s + 1;
				String ch = s + "";
				ffw.write(ch);
				ffw.close();

			} catch (IOException ex) {
				System.out.println("erreur de lecture du fichier");
				ex.printStackTrace();

			}/*

		 	List<Articlecarburant> la = new ArrayList<Articlecarburant>();

			la = serviceArticleCarburant.getAll();
			if(la!=null)
			for (Articlecarburant a : la) {
				double qte = 0;
				for (Ligneindex x : ligneindex) {
					if (x.getPompe().getArticlecarburant().getId() == a.getId())
						qte = qte + x.getQuantite();
				}
				a.setQte(qte);
			}*/

			for (Ligneindex x : ligneindex) {

				SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
				dates = f.format(date);
				x.setDates(dates);
				x.setPrixachat(x.getPompe().getArticlecarburant().getAchat());
				x.setCaisse(caisse);
				x.setDate(caisse.getDate());
				x.setPoste(caisse.getPoste());
				serviceLigneindex.save(x);

			}
		 	List<Articlecarburant> listArticlecarburants = new ArrayList<Articlecarburant>();
			listArticlecarburants = serviceArticleCarburant.getAll();
			if(listArticlecarburants!=null)
			for (Articlecarburant a : listArticlecarburants) {
				double qte = 0;
				for (Ligneindex x : ligneindex) {
					if (a.getId().equals(x.getPompe().getArticlecarburant().getId()))
						qte = qte + x.getQuantite();
				}
				a.setQte(qte);
				a.setQuantite(a.getQuantite() - a.getQte());
				serviceArticleCarburant.update(a);
				 

			}

		 
				
			caisse.setTotalventecarburant(totalventecarburant);
			caisse.setTotallitrage(totalLitrage);
			double tc = 0;
			double tcs = 0;
			if (caisse.getTotalcreditanterieur() != 0)
				tc = caisse.getTotalventecarburant() + caisse.getTotalcreditanterieur();
			else
				tc = caisse.getTotalventecarburant();

			tcs = caisse.getTotalachat() + caisse.getTotalcheque() + caisse.getTotalcredit()
					+ caisse.getCartebanquaire() + caisse.getTotaldepense() + caisse.getTotalmanque()
					+ caisse.getRetourcuve() + caisse.getCarteshell();
			caisse.setTotalcaisse(tc - tcs);
			caisse.setObservation(caisse.getTotalespece() - caisse.getTotalcaisse());
			if (caisse.getObservation() > 0) {
				caisse.setRemarques("UN PLUS");
			} else
				caisse.setRemarques("UN MANQUE");
			serviceCaisse.update(caisse);
		}
		ligneindex = null;
		poste = caisse.getPoste();
		totalcaisse = caisse.getTotalcaisses();
		observations = caisse.getObservations();

		/*Utilisateur user = userservice.getCurrentUser();
		Tracegestat t = new Tracegestat();
		t.setAction("validation index de la poste" + caisse.getPoste() + " de " + caisse.getDates() + " par"
				+ user.getNom());
		t.setDate(new Date());
		t.setUtilisateur(user);
		servicetrace.save(t);*/
		return SUCCESS;

	}

	public void updatemontantindex(AjaxBehaviorEvent event) {
		lignindex.setQuantite(lignindex.getIndexfermuture() - lignindex.getIndexouverture());
		DecimalFormat df = new DecimalFormat("0.000");
		lignindex.setQuantites(df.format(lignindex.getQuantite()));
		lignindex.setMantant(lignindex.getQuantite() * lignindex.getPompe().getArticlecarburant().getVente());
		lignindex.setMantants(df.format(lignindex.getMantant()));
		ligneindex.set(codes, lignindex);
		codes = null;
		totalLitrage = 0;
		totalventecarburant = 0;
		EL4 = 0;
		EL1 = 0;
		EL2 = 0;
		EL3 = 0;
		for (Ligneindex x : ligneindex) {
			totalLitrage = totalLitrage + x.getQuantite();
			totalventecarburant = totalventecarburant + x.getMantant();
			if (x.getPompe().getLibelle().contains("B") )
				EL2 = EL2 + x.getMantant();

		}
		totalventecarburants = df.format(totalventecarburant);
		totalLitrages = df.format(totalLitrage);
		ELs1 = df.format(EL1);
		ELs2 = df.format(EL2);
		ELs3 = df.format(EL3);
		ELs4 = df.format(EL4);
	}

	public void getindexbydateandposte(AjaxBehaviorEvent event) {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		ligneindex = new ArrayList<Ligneindex>();
		ligneindex = serviceLigneindex.getAllventeparposte(dates, poste);
	}

	//ActionEvent actionEvent
	public void chargerindex() {
		totalLitrage = 0;
		totalventecarburant = 0;
		System.out.println("\\n\\n\\nchargement index\n\n\n");
		EL1 = 0;
		EL2 = 0;
		EL3 = 0;
		EL4 = 0;
		EL5 = 0;
		ligneindex = new ArrayList<Ligneindex>();		
		try {
			BufferedReader IN = new BufferedReader(new FileReader("M:/FTP_E00/num.txt"));
			Integer s = Integer.parseInt(IN.readLine());
			System.out.println("s" +s);
			IN.close();
			System.out.println("\n\n\n row"+s);
			BufferedReader csvReader = new BufferedReader(new FileReader("M:/FTP_E00/" + (s) + ".csv"));
			try {
				int i =0;
				String row = null;
				while ((row = csvReader.readLine()) != null) {
					System.out.println("\n\n\n row"+row);
					if (i >=6  && i <= 41) {
						String[] data = row.split(",");
						Pompe p =null;
						if (data != null) {
							System.out.println("\n\n\n data[0]"+data[0]);
							 	
									if(Integer.parseInt(data[0])==34 || Integer.parseInt(data[0])==36) 
										p=null;
									else {
										 p = servicepompe.Findbycode(Integer.parseInt(data[0]));
										if(Integer.parseInt(data[0])==35)
											  p = servicepompe.Findbycode(34);									
										if(Integer.parseInt(data[0])==37)
										  p = servicepompe.Findbycode(35);	
									 									  
									Ligneindex x = new Ligneindex();
									x.setPompe(p);
									Ligneindex pel = serviceLigneindex.getAllventepardateandpompeandposte2(p,
											serviceLigneindex.getmaxcode().getCaisse());
									if (pel != null) {
										System.out.println("\n\n\n" + p.getLibelle() + "\n\n\n");
										x.setIndexouverture(pel.getIndexfermuture());
									}
									x.setIndexfermuture(Double.parseDouble(data[2]));
									//x.setIndexouverture(Double.parseDouble(data[1]));
									x.setQuantite(Double.parseDouble(data[3]));
									x.setMantant(x.getQuantite()*p.getArticlecarburant().getVente());
								    ligneindex.add(x); 
									}
										
									
						} 
					}
					i = i + 1;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				csvReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// IN2.close();
		} catch (Exception ex) {// IOException
			System.out.println("erreur de lecture du fichier");
			ex.printStackTrace();

		}
		
		totalLitrage = 0;
		totalventecarburant = 0;
     EL1=0;EL2=0;EL3=0;EL4=0;EL5=0;
		for (Ligneindex x : ligneindex) {
			totalLitrage = totalLitrage + x.getQuantite();
			totalventecarburant = totalventecarburant + x.getMantant();

		
		 
		if (x.getPompe().getLibelle().contains("A1") || x.getPompe().getLibelle().contains("A2"))
				 
			EL1 = EL1 + x.getMantant();
		if (x.getPompe().getLibelle().contains("B1") || x.getPompe().getLibelle().contains("B2")
				)
			EL2 = EL2 + x.getMantant();
		if (x.getPompe().getLibelle().contains("C1") || x.getPompe().getLibelle().contains("C2")
				)
			EL3 = EL3 + x.getMantant();
		
		if (x.getPompe().getLibelle().contains("D1") ||  x.getPompe().getLibelle().contains("D2")
				)
			EL4 = EL4 + x.getMantant();
		
		if (x.getPompe().getLibelle().contains("GND") 
				)
			EL5 = EL5 + x.getMantant();
		
		}
		DecimalFormat df = new DecimalFormat("0.000");
		totalventecarburants = df.format(totalventecarburant);
		totalLitrages = df.format(totalLitrage);
		ELs1 = df.format(EL1);
		ELs2 = df.format(EL2);
		ELs3 = df.format(EL3);
		ELs4 = df.format(EL4);
		ELs5 = df.format(EL5);

	//	pompes = new ArrayList<Pompe>();
		//pompes = servicepompe.getAll();

		// A supprimer les commentaires
		//connexionFTP();
		//FTPConnectionLogin ftp = new FTPConnectionLogin();
		//try {
			/*BufferedReader IN = new BufferedReader(new FileReader("M:/FTP_E00/num.txt"));
			Integer s = Integer.parseInt(IN.readLine());
			IN.close();
			System.out.println(s);
			BufferedReader IN2 = new BufferedReader(new FileReader("M:/FTP_E00/000" + (s) + ".P"));
			System.out.println("M:/FTP_E00/000" + (s) + ".P");
			String ligne;
			String l2;
			String l3;
			String moto = "";
			String motf = "";
			String[] indexouvrtures;
			String[] indexfermetures;
			while ((ligne = IN2.readLine()) != null) {

				if (ligne.equals("[START_TOTAL_CALCULATOR_TOTES]")) {
					while ((l2 = IN2.readLine()) != null && !l2.equals("[END_START_TOTAL_CALCULATOR_TOTES]")) {
						motf = motf + l2 + ";";
					}
				}

				if (ligne.equals("[TOTAL_CALCULATOR_TOTES]")) {
					while ((l3 = IN2.readLine()) != null && !l3.equals("[END_TOTAL_CALCULATOR_TOTES]")) {
						moto = moto + l3 + ";";
					}
				}
			}
			indexouvrtures = moto.split(";");
			indexfermetures = motf.split(";");*/
			
			
			
			/*
			for (Pompe p : pompes) {
				Ligneindex x = new Ligneindex();
				x.setPompe(p);
				int i = 0;
				Ligneindex pel = serviceLigneindex.getAllventepardateandpompeandposte2(p,
						serviceLigneindex.getmaxcode().getCaisse());
				if (pel != null) {
					System.out.println("\n\n\n" + p.getLibelle() + "\n\n\n");
					x.setIndexouverture(pel.getIndexfermuture());
				}
				boolean test = false;*/
			/*	while (i < indexouvrtures.length) {
					if (indexouvrtures[i].contains("QUA")) {
						if (returnindex(p.getLibelle())
								.equals(indexouvrtures[i].substring(0, indexouvrtures[i].indexOf("=")))) {// mots[i]
							test = true;

							// x.setIndexouverture(Double.parseDouble(indexfermetures[i]
							// .substring(indexfermetures[i].indexOf("=") + 1,
							// indexfermetures[i].length())));// update

							x.setIndexfermuture(Double.parseDouble(indexouvrtures[i]
									.substring(indexouvrtures[i].indexOf("=") + 1, indexouvrtures[i].length())));

							x.setDate(date);
							x.setDates(dates);
							x.setPoste(poste);

							x.setQuantite(x.getIndexfermuture() - x.getIndexouverture());

							x.setMantant(x.getQuantite() * x.getPompe().getArticlecarburant().getVente());
							ligneindex.add(x);
							totalLitrage = totalLitrage + x.getQuantite();
							totalventecarburant = totalventecarburant + x.getMantant();
							if (x.getPompe().getLibelle().contains("A1") || x.getPompe().getLibelle().contains("A2")
									|| x.getPompe().getLibelle().contains("1D")
									|| x.getPompe().getLibelle().contains("2D"))
								EL1 = EL1 + x.getMantant();
							if (x.getPompe().getLibelle().contains("B")
									|| x.getPompe().getLibelle().contains("MELANGE"))
								EL2 = EL2 + x.getMantant();
							if (x.getPompe().getLibelle().contains("C") || x.getPompe().getLibelle().contains("V 11")
									|| x.getPompe().getLibelle().contains("V 12")
									|| x.getPompe().getLibelle().contains("PETROLE"))
								EL3 = EL3 + x.getMantant();

						}
						i = i + 1;
					} else
						i = i + 1;
				}*/
			//	if (test == false) {

					/*x.setPoste(poste);
					x.setQuantite(0);
					x.setMantant(0);
					x.setDate(date);
					x.setDates(dates);*/
				/*	if (p.getId().equals(30) || p.getId().equals(31)) {
						Ligneindex pel2 = serviceLigneindex.getAllventepardateandpompeandposte2(p,
								serviceLigneindex.getmaxcode().getCaisse());
						if (pel2 != null) {
							System.out.println("\n\n\n" + p.getLibelle() + "\n\n\n");
							x.setIndexouverture(pel2.getIndexfermuture());// pel.getIndexfermuture()
							x.setIndexfermuture(0);
						} else {
							x.setIndexouverture(0);
							x.setIndexfermuture(0);// update la methode serviceLigneindex.getmaxcode(x.getPompe())
						}
					} else {*/
						// x.setIndexouverture(0);
						//x.setIndexfermuture(0);// update la methode serviceLigneindex.getmaxcode(x.getPompe())
					//}

					//ligneindex.add(x);
				//}
		//	}

			//IN2.close();
		/*} catch (Exception ex) {//IOException
			System.out.println("erreur de lecture du fichier");
			ex.printStackTrace();

		}*/

	}

	public String avantCaisse() {

		listeEmployee = new ArrayList<Employee>();
		listeEmployee = serviceemployees.getAll();
		listclients = new ArrayList<Clientgestat>();
		listclients = serviceClient.getAll();
		/*
		 * for(Employeegestat e:listeEmployee) {e.setNom(e.getNom()+" "+e.getPrenom());
		 * serviceEmployee.update(e); }
		 */
		caisse = serviceCaisse.getmaxcode();
		postes = Poste.values();
		if(caisse!=null) {
		date = caisse.getDate();
		poste = caisse.getPoste();
		}
		else {date=new Date();
		
		poste=Poste.Poste1;
		}
		id = null;
		depense = null;
		libelle = null;
		montant = 0;
		totalcartebanqaire = 0;
		totalcarteshell = 0;
		totalesece = 0;
		/*
		 * if (caisse != null) { poste = caisse.getPoste(); date = caisse.getDate(); }
		 * else { date = new Date(); poste = null; }
		 */
		return "success?faces-redirect=true";
	}

	public String caisse() {
		chargerindex();
		poste = null;
		Ligneindex x = serviceLigneindex.getmaxcode();
		if (x.getPoste().equals(Poste.Poste1)) {
			poste = Poste.Poste2;
			date = x.getDate();
		}

		else {
			poste = Poste.Poste1;
			date = new Date();
		}

		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		caisse = serviceCaisse.getCaissebydate(dates, poste);
		if (caisse == null) {
			caisse = new Caisse();
			caisse.setDate(date);
			caisse.setDates(dates);
			caisse.setObservation(0);
			caisse.setTotalachat(0);
			caisse.setTotalcaisse(0);
			caisse.setTotalcheque(0);
			caisse.setTotalcredit(0);
			caisse.setCartebanquaire(0);
			caisse.setTotalcreditanterieur(0);
			caisse.setTotaldepense(0);
			caisse.setTotalespece(0);
			caisse.setTotalmanque(0);
			caisse.setPompiste1("");
			caisse.setPompiste2("");
			caisse.setTotalventecarburant(0);
			caisse.setRetourcuve(0);
			caisse.setPoste(poste);
			caisse.setRemarques("");
			caisse.setCarteshell(0);
			serviceCaisse.save(caisse);

		} else {

			totalcaisse = caisse.getTotalcaisses();
			observations = caisse.getObservations();
			poste = caisse.getPoste();
			date = caisse.getDate();
		}
		Utilisateur user = userservice.getCurrentUser();
		Tracegestat t = new Tracegestat();
		t.setAction("acces au index" + caisse.getPoste() + " de " + caisse.getDates() + " par" + user.getNom());
		t.setDate(new Date());
		t.setUtilisateur(user);
		servicetrace.save(t);

		return "success?faces-redirect=true";
	}

	public String modificationCaisse() {
		listeEmployee = new ArrayList<Employee>();
		listeEmployee = serviceemployees.getAll();
		postes = Poste.values();
		date = new Date();
		poste = null;
		caisse = null;
		/*
		 * if (caisse != null) { poste = caisse.getPoste(); date = caisse.getDate(); }
		 * else { date = new Date(); poste = null; }
		 */
		return SUCCESS;
	}

	public void getdates(AjaxBehaviorEvent event) {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		poste = null;
		caisse = null;
	}

	public void getCaissebydate(AjaxBehaviorEvent event) {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);

		caisse = serviceCaisse.getCaissebydate(dates, poste);

		if (caisse == null) {
			addMessage("creation de la caisse !!");
			caisse = new Caisse();
			caisse.setDate(date);
			caisse.setDates(dates);
			caisse.setObservation(0);
			caisse.setTotalachat(0);
			caisse.setTotalcaisse(0);
			caisse.setTotalcheque(0);
			caisse.setTotalcredit(0);
			caisse.setCartebanquaire(0);
			caisse.setTotalcreditanterieur(0);
			caisse.setTotaldepense(0);
			caisse.setTotalespece(0);
			caisse.setTotalmanque(0);
			caisse.setPompiste1("");
			caisse.setPompiste2("");
			caisse.setTotalventecarburant(0);
			caisse.setRetourcuve(0);
			caisse.setPoste(poste);
			caisse.setRemarques("");
			caisse.setCarteshell(0);
			serviceCaisse.save(caisse);
			System.out.println(" nouvelle caisse" + caisse.getId());

		} else {
			totalcaisse = caisse.getTotalcaisses();
			observations = caisse.getObservations();
		}
		Utilisateur user = userservice.getCurrentUser();
		Tracegestat t = new Tracegestat();
		t.setAction("creation de la caisse" + caisse.getPoste() + " de " + caisse.getDates() + " par" + user.getNom());
		t.setDate(new Date());
		t.setUtilisateur(user);
		servicetrace.save(t);
	}

	public void onCellEdit7(CellEditEvent event) {
		lignevente = ligneventes.get(event.getRowIndex());
		codes = event.getRowIndex();

	}

	public void onCellEdit8(CellEditEvent event) {
		retourcuve = retourcuves.get(event.getRowIndex());
		codes = event.getRowIndex();

	}

	public void onCellEdit9(CellEditEvent event) {
		lignindex = ligneindex.get(event.getRowIndex());
		codes = event.getRowIndex();

	}

	public void onCellEdit4(CellEditEvent event) {
		achatcaisse = listachatcaisse.get(event.getRowIndex());
		codes = event.getRowIndex();

	}

	public void onCellEdit2(CellEditEvent event) {
		cheque = listcheque.get(event.getRowIndex());
		codes = event.getRowIndex();

	}

	public void chargerespece(ActionEvent actionEvent) {
		totalcarteshell = 0;
		totalcartebanqaire = 0;
		totalesece = 0;

	}

	public void chargertpe(ActionEvent actionEvent) {
		totalcarteshell = 0;
		totalcartebanqaire = 0;
		totalesece = 0;
	}

	public void chargerclient(ActionEvent actionEvent) {

		total = 0;
		id = null;
		listCreditanterieur = new ArrayList<Creditanterieur>();
		listCreditanterieur = serviceCreditanterieur.getCreditanterieurbyCaisse(caisse);
		if (listCreditanterieur.size() == 0) {
			listCreditanterieur = new ArrayList<Creditanterieur>();
			for (int i = 0; i < 75; i++) {
				Creditanterieur d = new Creditanterieur();
				d.setMontant(0);
				d.setCaisse(caisse);

				listCreditanterieur.add(d);
			}
		}

		else {
			for (Creditanterieur c : listCreditanterieur)
				total = total + c.getMontant();
			for (int i = listCreditanterieur.size(); i < 75; i++) {
				Creditanterieur d = new Creditanterieur();
				d.setMontant(0);
				d.setCaisse(caisse);
				listCreditanterieur.add(d);
			}
		}
	}

	private List<Credit> listcredit;

	public void chargerclient2(ActionEvent actionEvent) {
		listjournal = new ArrayList<TransactionCredit>();
		listjournal = servicejornal.getjournalNonAffecter();

		total = 0;
		id = null;
		listcredit = serviceCreditclient.getCreditclientbyCaisse(caisse);
		if (listcredit.size() == 0) {
			listcredit = new ArrayList<Credit>();

			if (listjournal != null) {
				for (TransactionCredit journal : listjournal) {
					Credit d = new Credit();
					d.setCaisse(caisse);
					d.setMontant(journal.getTotalttac());
					d.setClient(journal.getClient());
					d.setDate(new Date());
					d.setPoste(caisse.getPoste());
					d.setTels(journal.getTel());
					d.setNumbon(journal.getId());
					d.setTransaction(journal);
					listcredit.add(d);
				}
			}
			for (int i = 0; i < 100; i++) {
				Credit d = new Credit();

				// d.setMontant();
				d.setCaisse(caisse);
				listcredit.add(d);
			}
		} else {

			if (listjournal != null) {
				for (TransactionCredit journal : listjournal) {
					Credit d = new Credit();
					d.setCaisse(caisse);
					d.setMontant(journal.getTotalttac());
					d.setClient(journal.getClient());
					d.setDate(new Date());
					d.setPoste(caisse.getPoste());
					d.setTels(journal.getTel());
					d.setNumbon(journal.getId());
					d.setTransaction(journal);
					listcredit.add(d);
				}
			}

			for (int i = listcredit.size(); i < 100; i++) {
				Credit d = new Credit();

				// d.setMontant();
				d.setCaisse(caisse);
				listcredit.add(d);
			}
		}
		for (Credit c : listcredit)
			total = total + c.getMontant();
		/*
		 * try { listclients = new ArrayList<Clientgestat>(); listclients =
		 * serviceClient.getAll(); total = 0; listcredit = new ArrayList<Credit>();
		 * listcredit = serviceCreditclient.getCreditclientbyCaisse(caisse); if
		 * (listcredit.size() == 0) { listcredit= new ArrayList<Credit>(); for (int i =
		 * 0; i < 15; i++) { Credit d = new Credit(); d.setMontant(0);
		 * d.setCaisse(caisse);
		 * 
		 * listcredit.add(d); } }
		 * 
		 * else { for (Credit c : listcredit) total = total + c.getMontant(); for (int i
		 * = listcredit.size(); i < 15; i++) { Credit d = new Credit(); d.setMontant(0);
		 * d.setCaisse(caisse); listcredit.add(d); } }
		 * }catch(ExceptionInInitializerError e) {
		 * 
		 * }
		 */
	}

	public void chargerpompe(ActionEvent actionEvent) {
		pompes = new ArrayList<Pompe>();
		pompes = servicepompe.getAll();

		total = 0;
		retourcuves = new ArrayList<Retourcuve>();
		// retourcuves = serviceRetourcuve.getRetourcuvebyCaisse(caisse);

		for (Pompe p : pompes) {
			Retourcuve r = new Retourcuve();
			r.setPompe(p);

			r.setMontant(0);
			r.setCaisse(caisse);
			retourcuves.add(r);
		}
		int i = 0;
		for (Retourcuve r : retourcuves) {
			Retourcuve r1 = serviceRetourcuve.getretpourbypompe(r.getPompe(), caisse);
			if (r1 != null) {

				retourcuves.set(i, r1);
				total = total + r1.getMontant();
			}
			i = i + 1;
		}

	}

	public void updateCode(AjaxBehaviorEvent event) {
		produit = serviceProduit.Findbycode(id);
		// lignevente.setProduit(produit);
		ligneventes.set(codes, lignevente);
		codes = null;
		id = null;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * a verifier apres l 'update
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	public void chargerboncredit(AjaxBehaviorEvent event) {
		/*
		 * total = 0; listproduits = new ArrayList<Produit>(); listproduits =
		 * serviceProduit.getAll();
		 * 
		 * ligneventes = new ArrayList<Ligneventecredit>(); for (int i = 0; i < 15; i++)
		 * { Ligneventecredit c = new Ligneventecredit(); c.setQuantite(0); c.setId(i);
		 * //c.setCreditclient(credit); ligneventes.add(c); } /* }
		 * 
		 * else {
		 * 
		 * for (int i = 0; i <ligneventes.size(); i++) { Ligneventecredit c = new
		 * Ligneventecredit(); c.setQuantite(0); c.setId(i); c.setCreditclient(credit);
		 * ligneventes.add(c); } }
		 */
	}

	public void chargercheque(ActionEvent actionEvent) {
		total = 0;
		listcheque = new ArrayList<Cheque>();
		listcheque = serviceCheque.getChequebyCaisse(caisse);
		if (listcheque.size() == 0) {
			listcheque = new ArrayList<Cheque>();
			for (int i = 0; i < 50; i++) {
				Cheque c = new Cheque();

				c.setCaisse(caisse);

				listcheque.add(c);
			}
		}

		else {
			for (Cheque c : listcheque)
				total = total + c.getMontant();
			for (int i = listcheque.size(); i < 50; i++) {
				Cheque c = new Cheque();

				c.setCaisse(caisse);
				listcheque.add(c);
			}
		}
	}

	public void chargerachat(ActionEvent actionEvent) {
		total = 0;
		listachatcaisse = new ArrayList<Achatcaisse>();
		listachatcaisse = serviceAchatcaisse.getachatyCaisse(caisse);
		if (listachatcaisse.size() == 0) {
			listachatcaisse = new ArrayList<Achatcaisse>();
			for (int i = 0; i < 50; i++) {
				Achatcaisse d = new Achatcaisse();
				d.setMontant(0);
				d.setCaisse(caisse);

				listachatcaisse.add(d);
			}
		}

		else {
			for (Achatcaisse a : listachatcaisse)
				total = total + a.getMontant();
			for (int i = listachatcaisse.size(); i < 50; i++) {
				Achatcaisse d = new Achatcaisse();
				d.setMontant(0);
				d.setCaisse(caisse);
				listachatcaisse.add(d);
			}
		}
	}

	public void chargeremployee(ActionEvent actionEvent) {
		listeEmployee = new ArrayList<Employee>();
		id = null;
		listeEmployee = serviceemployees.getAll();
		total = 0;

		listAvances = new ArrayList<Avancegestat>();
		listAvances = serviceAvance.getAvancebyCaisse(caisse);
		if (listAvances.size() == 0) {
			listAvances = new ArrayList<Avancegestat>();
			for (int i = 0; i < 100; i++) {
				Avancegestat d = new Avancegestat();
				d.setMontant_avance(0);
				d.setCaisse(caisse);
				listAvances.add(d);
			}
		}

		else {
			for (Avancegestat a : listAvances)
				total = total + a.getMontant_avance();
			for (int i = listAvances.size(); i < 100; i++) {
				Avancegestat d = new Avancegestat();
				d.setMontant_avance(0);
				d.setCaisse(caisse);
				listAvances.add(d);
			}
		}
	}

	public void chargerfamilledepense(ActionEvent actionEvent) {
		total = 0;
		id = null;
		depense = null;
		familledepense = null;
		listdepense = new ArrayList<Depensegestat>();
        List<TransactionDepense> listTransaction=new ArrayList<TransactionDepense>();
        listTransaction=serviceJournaldep.getjournalNonAffecter();
        
		listdepense = serviceDepense.getdepensebyCaisse(caisse);

		if (listdepense.size() == 0) {
			codes = 0;
			listdepense = new ArrayList<Depensegestat>();
			if(listTransaction!=null) {
				for(TransactionDepense journal:listTransaction) {
					Depensegestat d = new Depensegestat();
                    d.setCaisse(caisse);
                    d.setMontant(journal.getTotalttac());     
                    d.setLibelle(journal.getKilometrage());
                    d.setDate(new Date());
                    d.setFamilledepense(serviceFamilleDepense.getFamilebyeibelle(journal.getVhecule()));   
                    d.setFamiledepvoiture(2);
                    d.setTransaction(journal);
                    listdepense.add(d);
				}
			}
			for (int i = 1; i < 76; i++) {
				Depensegestat d = new Depensegestat();
				d.setMontants("");
				d.setMontant(0);
				// d.setId(dd.getId()+i);
				d.setCaisse(caisse);
				listdepense.add(d);
			}
		}

		/*
		 * else { if (listdepense.size()>0) for (Depensegestat d : listdepense) { total
		 * = total + d.getMontant(); d.setIdfamille(d.getFamilledepense().getId());
		 * serviceDepense.update(d); }
		 */
		else { // listdepense = new ArrayList<Depensegestat>();
			codes = listdepense.size() + 1;
			if(listTransaction!=null) {
				for(TransactionDepense journal:listTransaction) {
					Depensegestat d = new Depensegestat();
                    d.setCaisse(caisse);
                    d.setLibelle(journal.getKilometrage());
                    d.setFamiledepvoiture(2);
                    d.setMontant(journal.getTotalttac());                    
                    d.setDate(new Date());
                   d.setFamilledepense(serviceFamilleDepense.getFamilebyeibelle(journal.getVhecule()));   
                   // d.setFamilledepense(serviceFamilleDepense.getFamilebyeid(2));
                    d.setTransaction(journal);
                    listdepense.add(d);
				}
			}
			for (int i = listdepense.size(); i < 75; i++) {
				Depensegestat d = new Depensegestat();
				d.setMontant(0);
				// d.setId(dd.getId()+i+1);
				d.setCaisse(caisse);
				listdepense.add(d);
			}
		}
		addMessage("chargement des familles depense !!");
	}

	public void getEmployeeByNom(AjaxBehaviorEvent event) {
		try {
			employee = serviceemployees.getEmployeeByNom(nom);
			Pompiste p = new Pompiste();
			p.setCaisse(caisse);
			p.setEmployee(employee);
			servicePompiste.save(p);

			if (caisse.getPompiste1().equals(""))
				caisse.setPompiste1(employee.getNom());
			else if (caisse.getPompiste2().equals(""))
				caisse.setPompiste2(employee.getNom());
			else if (!caisse.getPompiste1().equals("") && !caisse.getPompiste2().equals("")
					&& caisse.getPompiste().equals(employee.getNom()))
				caisse.setPompiste1(employee.getNom());
			else
				caisse.setPompiste2(employee.getNom());
			serviceCaisse.update(caisse);
			totalcaisse = caisse.getTotalcaisses();
			observations = caisse.getObservations();
		} catch (Exception e) {

		}
	}

	public void getEmployeeByNom2(ActionEvent event) {
		DecimalFormat df = new DecimalFormat("0.000");
		Integer index = verifierEmployee(employee, codes);

		if (index == codes) {

			if (listAvances.get(codes).getId() == null) {
				listAvances.set(codes, avance);
			} else {
				codes = codes + 1;
				listAvances.set(codes, avance);
			}

		}

	}

	private Integer verifierEmployee(Employee libelle, Integer i) {

		for (int j = 0; j < listAvances.size(); j++) {
			if (libelle.getCode().equals(listAvances.get(j).getId()) && j != i) {
				System.out.println("j" + j);
				return j;

			}
		}
		return i;
	}

	public void updatemontantboncredit(AjaxBehaviorEvent event) {
		total = 0;

	}

	public void updatemontant(ValueChangeEvent event) {
		UIComponent component = event.getComponent();
		DecimalFormat df = new DecimalFormat("0.000");
		codes = (Integer) component.getAttributes().get("test");
		System.out.println("\n\n\n " + codes + "new value" + event.getNewValue());
		depense = listdepense.get(codes);
		total = 0;
		try {
			String t = (String) event.getNewValue();
			Double tt = Double.parseDouble(t);
			depense.setMontant(tt);
			depense.setMontants(df.format(tt));

			System.out.println("\n\n\n " + "new value en double" + t + "  s");

			listdepense.set(codes, depense);

			for (Depensegestat d : listdepense)
				total = total + d.getMontant();

		} catch (Exception e) {
			System.out.println("\n\n\n " + "erreur dans montantrs");
		}
		totals = df.format(total);
		System.out.println("total " + total);
	}

	public void updatemontantcredit1(ValueChangeEvent event) {
		UIComponent component = event.getComponent();
		DecimalFormat df = new DecimalFormat("0.000");
		codes = (Integer) component.getAttributes().get("test");

		credit = listcredit.get(codes);
		client = credit.getClient();

		System.out.println("\n\n\n " + "old value en double" + credit.getMontants() + "  s");
		// client.setReste(client.getReste()-credit.getMontant());

		total = 0;
		try {
			// credit.getClient().setReste(credit.getClient().getReste()-Double.parseDouble((String)
			// event.getOldValue()));
			String t = (String) event.getNewValue();
			Double tt = Double.parseDouble(t);
			credit.setMontant(tt);
			credit.setMontants(df.format(tt));
			// client.setReste(client.getReste()+credit.getMontant());
			System.out.println("\n\n\n " + "new value en double" + t + "  s");

			listcredit.set(codes, credit);

			for (Credit d : listcredit)
				total = total + d.getMontant();

		} catch (Exception e) {
			System.out.println("\n\n\n " + "erreur dans montantrs");
		}
		// serviceClient.update(client);
		// serviceCreditclient.update(credit);
		totals = df.format(total);
		System.out.println("total " + total);
	}

	public void updatemontantcreditant1(ValueChangeEvent event) {
		UIComponent component = event.getComponent();
		DecimalFormat df = new DecimalFormat("0.000");
		codes = (Integer) component.getAttributes().get("test");

		creditanterieur = listCreditanterieur.get(codes);
		client = creditanterieur.getClient();
		// client.setReste(client.getReste() + creditanterieur.getMontant());
		total = 0;
		try {
			String t = (String) event.getNewValue();
			Double tt = Double.parseDouble(t);
			creditanterieur.setMontant(tt);
			creditanterieur.setMontants(df.format(tt));
			// client.setReste(client.getReste() - creditanterieur.getMontant());
			System.out.println("\n\n\n " + "new value en double" + t + "  s");
			listCreditanterieur.set(codes, creditanterieur);

			for (Creditanterieur d : listCreditanterieur)
				total = total + d.getMontant();

		} catch (Exception e) {
			System.out.println("\n\n\n " + "erreur dans montantrs");
		}
		totals = df.format(total);
		// serviceClient.update(client);
		// serviceCreditanterieur.update(creditanterieur);
	}

	public void updatemontantcheque1(ValueChangeEvent event) {
		UIComponent component = event.getComponent();
		DecimalFormat df = new DecimalFormat("0.000");
		codes = (Integer) component.getAttributes().get("test");
		System.out.println("\n\n\n " + codes + "new value" + event.getNewValue());
		cheque = listcheque.get(codes);
		total = 0;
		try {
			String t = (String) event.getNewValue();
			Double tt = Double.parseDouble(t);
			cheque.setMontant(tt);
			cheque.setMontants(df.format(tt));
			System.out.println("\n\n\n " + "new value en double" + t + "  s");
			listcheque.set(codes, cheque);
			for (Cheque d : listcheque)
				total = total + d.getMontant();

		} catch (Exception e) {
			System.out.println("\n\n\n " + "erreur dans montantrs");
		}
		totals = df.format(total);
		System.out.println("total " + total);

	}

	public void updatemontantachat1(ValueChangeEvent event) {
		UIComponent component = event.getComponent();
		DecimalFormat df = new DecimalFormat("0.000");
		codes = (Integer) component.getAttributes().get("test");
		System.out.println("\n\n\n " + codes + "new value" + event.getNewValue());
		achatcaisse = listachatcaisse.get(codes);
		total = 0;
		try {
			String t = (String) event.getNewValue();
			Double tt = Double.parseDouble(t);
			achatcaisse.setMontant(tt);
			achatcaisse.setMontants(df.format(tt));

			System.out.println("\n\n\n " + "new value en double" + t + "  s");

			listachatcaisse.set(codes, achatcaisse);

			for (Achatcaisse d : listachatcaisse)
				total = total + d.getMontant();

		} catch (Exception e) {
			System.out.println("\n\n\n " + "erreur dans montantrs");
		}
		totals = df.format(total);
		System.out.println("total " + total);
	}

	public void updatemontantaccompte(ValueChangeEvent event) {
		UIComponent component = event.getComponent();
		DecimalFormat df = new DecimalFormat("0.000");
		codes = (Integer) component.getAttributes().get("test");
		System.out.println("\n\n\n " + codes + "new value" + event.getNewValue());
		avance = listAvances.get(codes);
		total = 0;
		try {
			String t = (String) event.getNewValue();
			Double tt = Double.parseDouble(t);
			avance.setMontant_avance(tt);
			avance.setMontant_avances(df.format(tt));
			serviceAvance.update(avance);
			System.out.println("\n\n\n " + "new value en double" + t + "  s");

			listAvances.set(codes, avance);

			for (Avancegestat d : listAvances)
				total = total + d.getMontant_avance();

		} catch (Exception e) {
			System.out.println("\n\n\n " + "erreur dans montantrs");
		}
		totals = df.format(total);
		System.out.println("total " + total);
	}

	private List<Employee> filteredEmployee;

	public List<Employee> getFilteredEmployee() {
		return filteredEmployee;
	}

	public void setFilteredEmployee(List<Employee> filteredEmployee) {
		this.filteredEmployee = filteredEmployee;
	}

	public void saveselection(ActionEvent event) {
		DecimalFormat df = new DecimalFormat("0.000");
		id = employee.getCode();
		employee = null;

	}

	public void updatemontant2(AjaxBehaviorEvent event) {

		total = 0;
		DecimalFormat df = new DecimalFormat("0.000");

		for (Depensegestat d : listdepense) {
			if (d.getMontant() != 0)
				d.setMontants(df.format(d.getMontant()));
			total = total + d.getMontant();

		}

		totals = df.format(total);
		System.out.println("total " + total);
	}

	public void updatemontantcheque(AjaxBehaviorEvent event) {
		total = 0;
		DecimalFormat df = new DecimalFormat("0.000");
		for (Cheque d : listcheque)
			total = total + d.getMontant();
		totals = df.format(total);
		System.out.println("total " + total);
	}

	public void updatemontantachat(AjaxBehaviorEvent event) {
		total = 0;
		for (Achatcaisse d : listachatcaisse)
			total = total + d.getMontant();
		System.out.println("total " + total);
		DecimalFormat df = new DecimalFormat("0.000");
		totals = df.format(total);
	}

	public void updatemontantretour(AjaxBehaviorEvent event) {
		total = 0;
		retourcuve.setMontant(retourcuve.getPompe().getArticlecarburant().getVente() * retourcuve.getQuantite());
		DecimalFormat df = new DecimalFormat("0.000");
		retourcuve.setMontants(df.format(retourcuve.getMontant()));

		for (Retourcuve d : retourcuves)
			total = total + d.getMontant();
		totals = df.format(total);

	}

	public void getCodefocus(ValueChangeEvent event) {

		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
	}

	public void handleChangeret(ValueChangeEvent event) {

		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
		System.out.println("\n\n codes" + codes + "\n\n");
		total = 0;
		retourcuve = retourcuves.get(codes);
		retourcuve.setQuantite((Integer) event.getNewValue());
		retourcuve.setMontant(retourcuve.getPompe().getArticlecarburant().getVente() * (Integer) event.getNewValue());

		DecimalFormat df = new DecimalFormat("0.000");
		retourcuve.setMontants(df.format(retourcuve.getMontant()));
		retourcuves.set(codes, retourcuve);
		for (Retourcuve d : retourcuves)
			total = total + d.getMontant();
		totals = df.format(total);
	}

	public void updatemontantmanque(AjaxBehaviorEvent event) {
		total = 0;
		for (Avancegestat d : listAvances)
			total = total + d.getMontant_avance();

		System.out.println("total " + total);
		DecimalFormat df = new DecimalFormat("0.000");
		totals = df.format(total);
	}

	public void updatemontantcreditant(AjaxBehaviorEvent event) {
		total = 0;
		UIComponent component = event.getComponent();
		DecimalFormat df = new DecimalFormat("0.000");

		codes = (Integer) component.getAttributes().get("test");
		creditanterieur = listCreditanterieur.get(codes);
		creditanterieur.setMontants(df.format(creditanterieur.getMontant()));
		client = creditanterieur.getClient();
		// client.setReste(client.getReste() - creditanterieur.getMontant());
		// serviceClient.update(client);
		listCreditanterieur.set(codes, creditanterieur);
		for (Creditanterieur d : listCreditanterieur)
			total = total + d.getMontant();

		totals = df.format(total);
		System.out.println("total " + total);
	}

	public void updatemontantcredit(AjaxBehaviorEvent event) {
		total = 0;
		DecimalFormat df = new DecimalFormat("0.000");
		UIComponent component = event.getComponent();

		codes = (Integer) component.getAttributes().get("test");
		credit = listcredit.get(codes);
		client = credit.getClient();
		// client.setReste(client.getReste() + credit.getMontant());
		credit.setMontants(df.format(credit.getMontant()));
		// serviceClient.update(client);
		listcredit.set(codes, credit);
		System.out.println("reste " + client.getReste());
		for (Credit d : listcredit) {
			total = total + d.getMontant();

		}

		totals = df.format(total);
		System.out.println("total " + total);
	}

	public void updatenumbon(AjaxBehaviorEvent event) {
		System.out.println("num bon " + credit.getNumbon());
	}

	public void buttonAction(ActionEvent actionEvent) {
		System.out.println("ooooooooooook");
		addMessage("Welcome to Primefaces!!");
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void updatenom1(AjaxBehaviorEvent event) {
		caisse.setPompiste1("");
		serviceCaisse.update(caisse);
	}

	public void updatenom2(AjaxBehaviorEvent event) {
		caisse.setPompiste2("");
		serviceCaisse.update(caisse);
	}

	public void updatenoms(AjaxBehaviorEvent event) {
		produit = serviceProduit.Findbydes(libelle);
		// lignevente.setProduit(produit);
		ligneventes.set(codes, lignevente);
		libelle = null;
	}

	public void updatenom(AjaxBehaviorEvent event) {

		Familledepensegestat familledepense = serviceFamilleDepense.getFamilebyeibelle(libelle);
		depense.setFamilledepense(familledepense);
		listdepense.set(codes, depense);
		System.out.println(depense.getFamilledepense().getLibelle());
		libelle = null;
		codes = null;
	}

	private HtmlDataTable depenses;

	public void execute(ActionEvent actionEvent) {
		HtmlCommandButton thisButton = (HtmlCommandButton) actionEvent.getComponent();
		Depensegestat cdto = (Depensegestat) (depenses.getRowData());
		cdto.getFamilledepense()
				.setLibelle(serviceFamilleDepense.getFamilebyeid(cdto.getFamilledepense().getId()).getLibelle());
		thisButton.setValue(cdto);
		addMessage("famille depense \n" + cdto.getFamilledepense().getId());
		System.out.println("famille depense \n" + cdto.getFamilledepense().getId());
	}

	public HtmlDataTable getDepenses() {
		return depenses;
	}

	public void setDepenses(HtmlDataTable depenses) {
		this.depenses = depenses;
	}

	public void updatenomclient(AjaxBehaviorEvent event) {
		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
		credit = listcredit.get(codes);
		System.out.println("\n\n codes" + codes + "\n\n");
		client = serviceClient.Findbycode(id);
		credit.setClient(client);
		credit.setTels(client.getTel());
		listcredit.set(codes, credit);
		if (serviceCreditclient.getcreditbyid(credit.getId()) != null)
			serviceCreditclient.update(credit);
		id = null;

	}

	public void handleChangeclient(ValueChangeEvent event) {
		System.out.println("here " + event.getNewValue());
		// System.out.println("statut "+depense.getId());
		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
		System.out.println("\n\n codes" + codes + "\n\n");
		client = serviceClient.Findbycode((Integer) event.getNewValue());
		credit = listcredit.get(codes);
		credit.setClient(client);
		credit.setTels(client.getTel());
		listcredit.set(codes, credit);
		if (serviceCreditclient.getcreditbyid(credit.getId()) != null)
			serviceCreditclient.update(credit);
	}

	public void updatenomclient2(AjaxBehaviorEvent event) {
		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
		creditanterieur = listCreditanterieur.get(codes);
		System.out.println("\n\n codes" + codes + "\n\n");
		client = serviceClient.Findbycode(id);
		creditanterieur.setClient(client);
		listCreditanterieur.set(codes, creditanterieur);
		if (serviceCreditanterieur.getcreditbyid(creditanterieur.getId()) != null)
			serviceCreditanterieur.update(creditanterieur);
		id = null;

	}

	public void handleChangeclient2(ValueChangeEvent event) {
		System.out.println("here " + event.getNewValue());
		// System.out.println("statut "+depense.getId());
		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
		System.out.println("\n\n codes" + codes + "\n\n");
		client = serviceClient.Findbycode((Integer) event.getNewValue());
		creditanterieur = listCreditanterieur.get(codes);
		creditanterieur.setClient(client);
		listCreditanterieur.set(codes, creditanterieur);
		serviceCreditanterieur.update(creditanterieur);
	}

	public void updatenomemployee(AjaxBehaviorEvent event) {
		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
		avance = listAvances.get(codes);
		System.out.println("\n\n codes" + codes + "\n\n");
		employee = serviceemployees.getEmployeeBycode(id);
		avance.setEmployee(employee);
		listAvances.set(codes, avance);
		if (serviceAvance.getavancebyid(avance.getId()) != null)
			serviceAvance.update(avance);
		id = null;

	}

	public void handleChangeemployee(ValueChangeEvent event) {
		System.out.println("here " + event.getNewValue());
		// System.out.println("statut "+depense.getId());
		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
		System.out.println("\n\n codes" + codes + "\n\n");
		employee = serviceemployees.getEmployeeBycode((Integer) event.getNewValue());
		avance = listAvances.get(codes);
		avance.setEmployee(employee);
		listAvances.set(codes, avance);
		serviceAvance.update(avance);
	}

	public void updatenom111(AjaxBehaviorEvent event) {
		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
		depense = listdepense.get(codes);
		System.out.println("\n\n codes" + codes + "\n\n");
		familledepense = serviceFamilleDepense.getFamilebyeid(id);
		depense.setFamilledepense(familledepense);
		listdepense.set(codes, depense);

		id = null;

	}

	public void handleChange(ValueChangeEvent event) {
		System.out.println("here " + event.getNewValue());

		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
		System.out.println("\n\n codes" + codes + "\n\n");
		familledepense = serviceFamilleDepense.getFamilebyeid((Integer) event.getNewValue());
		depense = listdepense.get(codes);
		depense.setFamilledepense(familledepense);
		listdepense.set(codes, depense);
		if (serviceDepense.getdepensebyid(depense) != null)
			serviceDepense.update(depense);
	}

	public void handleChangeindex2(ValueChangeEvent event) {
		UIComponent component = event.getComponent();
		codes = (Integer) component.getAttributes().get("test");
		System.out.println("\n\n codes" + codes + "\n\n");
		lignindex = ligneindex.get(codes);
		lignindex.setIndexfermuture((Double) event.getNewValue());
		lignindex.setQuantite(lignindex.getIndexfermuture() - lignindex.getIndexouverture());
		DecimalFormat df = new DecimalFormat("0.000");
		lignindex.setQuantites(df.format(lignindex.getQuantite()));
		lignindex.setMantant(lignindex.getQuantite() * lignindex.getPompe().getArticlecarburant().getVente());
		lignindex.setMantants(df.format(lignindex.getMantant()));

		ligneindex.set(codes, lignindex);
		codes = null;
		totalLitrage = 0;
		totalventecarburant = 0;
     EL1=0;EL2=0;EL3=0;EL4=0;EL5=0;
		for (Ligneindex x : ligneindex) {
			totalLitrage = totalLitrage + x.getQuantite();
			totalventecarburant = totalventecarburant + x.getMantant();

		
		 
		if (x.getPompe().getLibelle().contains("A1") || x.getPompe().getLibelle().contains("A2"))
				 
			EL1 = EL1 + x.getMantant();
		if (x.getPompe().getLibelle().contains("B1") || x.getPompe().getLibelle().contains("B2")
				)
			EL2 = EL2 + x.getMantant();
		if (x.getPompe().getLibelle().contains("C1") || x.getPompe().getLibelle().contains("C2")
				)
			EL3 = EL3 + x.getMantant();
		
		if (x.getPompe().getLibelle().contains("D1") ||  x.getPompe().getLibelle().contains("D2")
				)
			EL4 = EL4 + x.getMantant();
		
		if (x.getPompe().getLibelle().contains("GND") 
				)
			EL5 = EL5 + x.getMantant();
		
		}
		totalventecarburants = df.format(totalventecarburant);
		totalLitrages = df.format(totalLitrage);
		ELs1 = df.format(EL1);
		ELs2 = df.format(EL2);
		ELs3 = df.format(EL3);
		ELs4 = df.format(EL4);
		ELs5 = df.format(EL5);
	}

	public void updatelibelledepense(AjaxBehaviorEvent event) {

		depense.setLibelle(libelle);
		listdepense.set(codes, depense);
		// id = null;
		// codes=codes+1;
	}

	public void updatenomdepense(AjaxBehaviorEvent event) {
		// codes=codes+1;
		// depense=new Depensegestat();
		// familledepense = serviceFamilleDepense.getFamilebyeibelle(libelle);
		depense.setLibelle(libelle);
		libelle = null;
	}

	public void updatenom3(AjaxBehaviorEvent event) {
		employee = serviceemployees.getEmployeeByNom(nom);
		avance.setEmployee(employee);
		listeEmployee.set(codes, employee);
		nom = null;
	}

	public void updatenom33(AjaxBehaviorEvent event) {
		employee = serviceemployees.getEmployeeById(id);
		avance.setEmployee(employee);
		listeEmployee.set(codes, employee);
		id = null;
	}

	public void updatenom6(AjaxBehaviorEvent event) {
		pompe = servicepompe.Findbymf(nom);
		retourcuve.setPompe(pompe);
		retourcuves.set(codes, retourcuve);
		nom = null;
	}

	public void updatenom66(AjaxBehaviorEvent event) {
		pompe = servicepompe.Findbycode(id);
		retourcuve.setPompe(pompe);
		retourcuves.set(codes, retourcuve);
		id = null;
	}

	public void updatenom4(AjaxBehaviorEvent event) {
		client = serviceClient.Findbynom(nom);
		creditanterieur.setClient(client);
		listCreditanterieur.set(codes, creditanterieur);
		nom = null;
	}

	public void updatenom44(AjaxBehaviorEvent event) {
		client = serviceClient.Findbycode(id);
		creditanterieur.setClient(client);
		listCreditanterieur.set(codes, creditanterieur);
		id = null;
	}

	public void updatenom5(AjaxBehaviorEvent event) {
		client = serviceClient.Findbynom(nom);
		credit.setClient(client);
		listcredit.set(codes, credit);
	}

	public void updatenom55(AjaxBehaviorEvent event) {
		client = serviceClient.Findbycode(id);
		credit.setClient(client);
		listcredit.set(codes, credit);
		id = null;
		codes = null;
	}

	public String saveespece() {
		caisse.setTotalespece(caisse.getTotalespece() + totalesece);

		double tc = 0;
		double tcs = 0;
		if (caisse.getTotalcreditanterieur() != 0)
			tc = caisse.getTotalventecarburant() + caisse.getTotalcreditanterieur();
		else
			tc = caisse.getTotalventecarburant();

		tcs = caisse.getTotalachat() + caisse.getTotalcheque() + caisse.getTotalcredit() + caisse.getCartebanquaire()
				+ caisse.getTotaldepense() + caisse.getTotalmanque() + caisse.getRetourcuve() + caisse.getCarteshell();
		caisse.setTotalcaisse(tc - tcs);
		caisse.setObservation(caisse.getTotalespece() - caisse.getTotalcaisse());
		if (caisse.getObservation() > 0) {
			caisse.setRemarques("UN PLUS");
		} else
			caisse.setRemarques("UN MANQUE");
		serviceCaisse.update(caisse);
		totalcaisse = caisse.getTotalcaisses();
		observations = caisse.getObservations();
		poste = caisse.getPoste();
		totalesece = 0;
		totalcartebanqaire = 0;
		totalcarteshell = 0;
		return SUCCESS;
	}

	public String savecreditant() {

		total = 0;

		Date dat = caisse.getDate();
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(dat);
		List<Creditanterieur> lc = new ArrayList<Creditanterieur>();
		List<Clientgestat> lc2 = new ArrayList<Clientgestat>();
		for (Creditanterieur d : listCreditanterieur) {
			if (serviceCreditanterieur.getcreditbyid(d.getId()) == null) {
				if (d.getMontant() != 0) {
					d.setDate(dat);
					d.setDates(dates);
					if (!lc2.contains(d.getClient()))
						lc2.add(d.getClient());
					lc.add(d);

					serviceCreditanterieur.save(d);
				}
			} else {
				Creditanterieur lcr = serviceCreditanterieur.getCreditclientbyCaisseandclient(d);
				Clientgestat c = d.getClient();
				c.setReste(c.getReste() + lcr.getMontant());
				c.setReste(c.getReste() - d.getMontant());
				serviceClient.update(c);
				serviceCreditanterieur.update(d);
			}
		}

		System.out.println("save credit");
		for (Clientgestat c : lc2) {
			double mnt = 0;
			for (Creditanterieur cr : lc)
				if (cr.getClient().getCode() == c.getCode())
					mnt = mnt + cr.getMontant();
			c.setReste(c.getReste() - mnt);
			serviceClient.update(c);

		}
		for (Creditanterieur d : serviceCreditanterieur.getCreditanterieurbyCaisse(caisse))
			total = total + d.getMontant();

		caisse.setTotalcreditanterieur(total);
		double tc = 0;
		double tcs = 0;
		if (caisse.getTotalcreditanterieur() != 0)
			tc = caisse.getTotalventecarburant() + caisse.getTotalcreditanterieur();
		else
			tc = caisse.getTotalventecarburant();

		tcs = caisse.getTotalachat() + caisse.getTotalcheque() + caisse.getTotalcredit() + caisse.getCartebanquaire()
				+ caisse.getTotaldepense() + caisse.getTotalmanque() + caisse.getRetourcuve() + caisse.getCarteshell();
		caisse.setTotalcaisse(tc - tcs);
		caisse.setObservation(caisse.getTotalespece() - caisse.getTotalcaisse());
		if (caisse.getObservation() > 0) {
			caisse.setRemarques("UN PLUS");
		} else
			caisse.setRemarques("UN MANQUE");
		serviceCaisse.update(caisse);

		poste = caisse.getPoste();
		totalcaisse = caisse.getTotalcaisses();
		observations = caisse.getObservations();
		return SUCCESS;
	}

	public String savecredit() {
		total = 0;
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		List<Clientgestat> lc = new ArrayList<Clientgestat>();
		List<Credit> lc2 = new ArrayList<Credit>();
		for (Credit d : listcredit) {
			if (d.getMontant() != 0) {
				if (d.getClient() == null)
					return ERROR;

				else {
					if (d.getClient().getCode() == 44 && d.getTels().equals("0")) {
						return ERROR;
					}

					/*
					 * if(d.getClient().getPlafond() !=0 &&
					 * (d.getClient().getReste()+d.getMontant())>d.getClient().getPlafond()) return
					 * ERROR;
					 */
				}
			}
		}
		for (Credit d : listcredit) {
			total = total + d.getMontant();
			if (serviceCreditclient.getcreditbyid(d.getId()) == null) {
				if (d.getMontant() != 0) {
					d.setDate(date);
					lc2.add(d);
					if (!lc.contains(d.getClient()))
						lc.add(d.getClient());
					d.setDates(dates);
					if (d.getTransaction() != null) {
						d.getTransaction().setAffecte(true);
						servicejornal.update(d.getTransaction());
					}
					serviceCreditclient.save(d);
					/*
					 * Ligneventecredit lv = new Ligneventecredit(); lv.setCredit(d); Produit p =
					 * serviceProduit.Findbycode(126); lv.setProduit(p);
					 * lv.setDate(caisse.getDate()); lv.setDates(dates);
					 * lv.setQuantite(d.getMontant() / p.getVente()); lv.setMontant(d.getMontant());
					 * lv.setPu(p.getVente()); lv.setTva(p.getTva()); serviceLignevente.save(lv);
					 */
				}
			} else {
				Clientgestat c1 = d.getClient();
				Credit lcr = serviceCreditclient.getCreditclientbyCaisseandclient(d);
				c1.setReste(c1.getReste() - lcr.getMontant());
				c1.setReste(c1.getReste() + d.getMontant());
				serviceClient.update(c1);
				serviceCreditclient.update(d);
			}
		}
		System.out.println("save credit");
		for (Clientgestat c : lc) {
			double mnt = 0;
			for (Credit cr : lc2)
				if (cr.getClient().getCode() == c.getCode())
					mnt = mnt + cr.getMontant();
			c.setReste(c.getReste() + mnt);
			serviceClient.update(c);

		}
		caisse.setTotalcredit(total);
		double tc = 0;
		double tcs = 0;
		if (caisse.getTotalcreditanterieur() != 0)
			tc = caisse.getTotalventecarburant() + caisse.getTotalcreditanterieur();
		else
			tc = caisse.getTotalventecarburant();

		tcs = caisse.getTotalachat() + caisse.getTotalcheque() + caisse.getTotalcredit() + caisse.getCartebanquaire()
				+ caisse.getTotaldepense() + caisse.getTotalmanque() + caisse.getRetourcuve() + caisse.getCarteshell();
		caisse.setTotalcaisse(tc - tcs);
		caisse.setObservation(caisse.getTotalespece() - caisse.getTotalcaisse());
		if (caisse.getObservation() > 0) {
			caisse.setRemarques("UN PLUS");
		} else
			caisse.setRemarques("UN MANQUE");
		serviceCaisse.update(caisse);

		poste = caisse.getPoste();

		return SUCCESS;

	}

	public String saveachat() {
		total = 0;
		Date dat = caisse.getDate();
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		for (Achatcaisse d : listachatcaisse) {
			if (serviceAchatcaisse.getAchatcaissebyid(d.getId()) == null) {
				if (d.getMontant() != 0) {
					d.setDate(dat);
					d.setDates(dates);

					serviceAchatcaisse.save(d);
				}
			} else
				serviceAchatcaisse.update(d);
		}
		for (Achatcaisse d : serviceAchatcaisse.getachatyCaisse(caisse))
			total = total + d.getMontant();
		caisse.setTotalachat(total);
		double tc = 0;
		double tcs = 0;
		if (caisse.getTotalcreditanterieur() != 0)
			tc = caisse.getTotalventecarburant() + caisse.getTotalcreditanterieur();
		else
			tc = caisse.getTotalventecarburant();

		tcs = caisse.getTotalachat() + caisse.getTotalcheque() + caisse.getTotalcredit() + caisse.getCartebanquaire()
				+ caisse.getTotaldepense() + caisse.getTotalmanque() + caisse.getRetourcuve() + caisse.getCarteshell();
		caisse.setTotalcaisse(tc - tcs);
		caisse.setObservation(caisse.getTotalespece() - caisse.getTotalcaisse());
		if (caisse.getObservation() > 0) {
			caisse.setRemarques("UN PLUS");
		} else
			caisse.setRemarques("UN MANQUE");
		serviceCaisse.update(caisse);

		poste = caisse.getPoste();
		totalcaisse = caisse.getTotalcaisses();
		observations = caisse.getObservations();
		return SUCCESS;
	}

	public String saveretourcuve2() {
		double total = 0;
		Date dat = caisse.getDate();
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);

		for (Retourcuve d : retourcuves) {
			if (serviceRetourcuve.getRetourcuvebyid(d.getId()) == null) {
				if (d.getMontant() != 0) {
					d.setDate(dat);
					d.setDates(dates);
					serviceRetourcuve.save(d);
					caisse.setRetourcuve(caisse.getRetourcuve() + d.getMontant());
					Articlecarburant a = d.getPompe().getArticlecarburant();

					a.setQuantite(d.getQuantite() + a.getQuantite());
					serviceArticleCarburant.update(a);

					serviceCaisse.update(caisse);
				} else
					serviceRetourcuve.update(d);
			}
		}

		total = 0;
		for (Retourcuve d : retourcuves) {
			if (serviceRetourcuve.getRetourcuvebyid(d.getId()) != null) {
				System.out.println("\n\n\n" + d.getMontant() + "\n\n\n");
				total = total + d.getMontant();
			}
		}

		System.out.println("\n\n\n" + total + "\n\n\n");
		caisse.setRetourcuve(total);
		double tc = 0;
		double tcs = 0;
		if (caisse.getTotalcreditanterieur() != 0)
			tc = caisse.getTotalventecarburant() + caisse.getTotalcreditanterieur();
		else
			tc = caisse.getTotalventecarburant();

		tcs = caisse.getTotalachat() + caisse.getTotalcheque() + caisse.getTotalcredit() + caisse.getCartebanquaire()
				+ caisse.getTotaldepense() + caisse.getTotalmanque() + caisse.getRetourcuve() + caisse.getCarteshell();
		caisse.setTotalcaisse(tc - tcs);
		caisse.setObservation(caisse.getTotalespece() - caisse.getTotalcaisse());
		if (caisse.getObservation() > 0) {
			caisse.setRemarques("UN PLUS");
		} else
			caisse.setRemarques("UN MANQUE");
		serviceCaisse.update(caisse);
		totalcaisse = caisse.getTotalcaisses();
		observations = caisse.getObservations();
		poste = caisse.getPoste();
		return SUCCESS;
	}

	public String saveretourcuve() {
		total = 0;
		Date dat = caisse.getDate();
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(dat);
		try {
			for (Retourcuve d : retourcuves) {
				if (serviceRetourcuve.getRetourcuvebyid(d.getId()) == null) {
					if (d.getQuantite() != 0) {
						d.setDate(dat);
						d.setDates(dates);
						d.setMontant(d.getQuantite() * d.getPompe().getArticlecarburant().getVente());
						serviceRetourcuve.save(d);
						Articlecarburant a = d.getPompe().getArticlecarburant();
						a.setQuantite(d.getQuantite() + a.getQuantite());
						serviceArticleCarburant.update(a);
					}
				} else

					serviceRetourcuve.update(d);

			}
		} catch (Exception e) {

		}
		retourcuves = new ArrayList<Retourcuve>();
		retourcuves = serviceRetourcuve.getRetourcuvebyCaisse(caisse);
		if (retourcuves != null)
			for (Retourcuve d : retourcuves)
				total = total + d.getMontant();

		caisse.setRetourcuve(total);
		double tc = 0;
		double tcs = 0;
		if (caisse.getTotalcreditanterieur() != 0)
			tc = caisse.getTotalventecarburant() + caisse.getTotalcreditanterieur();
		else
			tc = caisse.getTotalventecarburant();

		tcs = caisse.getTotalachat() + caisse.getTotalcheque() + caisse.getTotalcredit() + caisse.getCartebanquaire()
				+ caisse.getTotaldepense() + caisse.getTotalmanque() + caisse.getRetourcuve() + caisse.getCarteshell();
		caisse.setTotalcaisse(tc - tcs);
		caisse.setObservation(caisse.getTotalespece() - caisse.getTotalcaisse());
		if (caisse.getObservation() > 0) {
			caisse.setRemarques("UN PLUS");
		} else
			caisse.setRemarques("UN MANQUE");
		serviceCaisse.update(caisse);
		totalcaisse = caisse.getTotalcaisses();
		observations = caisse.getObservations();
		poste = caisse.getPoste();
		return SUCCESS;
	}

	public String savedeavnce() {
		total = 0;
		Date dat = caisse.getDate();
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		List<Avancegestat> lista = new ArrayList<Avancegestat>();
		for (Avancegestat d : listAvances) {
			if (serviceAvance.getavancebyid(d.getId()) == null) {
				if (d.getMontant_avance() != 0) {
					d.setDate(dat);
					d.setDates(dates);
					if (!lista.contains(d))
						lista.add(d);
					serviceAvance.save(d);

				}
			} else {
				serviceAvance.update(d);
				 
			}
		}
		List<Employee> liste = new ArrayList<Employee>();
		for (Avancegestat d : lista) {
			if (!liste.contains(d.getEmployee())) {
				liste.add(d.getEmployee());
			}
		}

		for (Employee e : liste) {
			double mnt = 0;
			for (Avancegestat a : lista)
				if (a.getEmployee().getMatricule() == e.getMatricule())
					mnt = mnt + a.getMontant_avance();

			 
		}
		for (Avancegestat d : serviceAvance.getAvancebyCaisse(caisse))
			total = total + d.getMontant_avance();
		caisse.setTotalmanque(total);
		double tc = 0;
		double tcs = 0;
		if (caisse.getTotalcreditanterieur() != 0)
			tc = caisse.getTotalventecarburant() + caisse.getTotalcreditanterieur();
		else
			tc = caisse.getTotalventecarburant();

		tcs = caisse.getTotalachat() + caisse.getTotalcheque() + caisse.getTotalcredit() + caisse.getCartebanquaire()
				+ caisse.getTotaldepense() + caisse.getTotalmanque() + caisse.getRetourcuve() + caisse.getCarteshell();
		caisse.setTotalcaisse(tc - tcs);
		caisse.setObservation(caisse.getTotalespece() - caisse.getTotalcaisse());
		if (caisse.getObservation() > 0) {
			caisse.setRemarques("UN PLUS");
		} else
			caisse.setRemarques("UN MANQUE");
		serviceCaisse.update(caisse);
		totalcaisse = caisse.getTotalcaisses();
		observations = caisse.getObservations();
		poste = caisse.getPoste();
		return SUCCESS;
	}

	public String savetpe() {
		caisse.setCarteshell(caisse.getCarteshell() + totalcarteshell);
		double tc = 0;
		double tcs = 0;
		if (caisse.getTotalcreditanterieur() != 0)
			tc = caisse.getTotalventecarburant() + caisse.getTotalcreditanterieur();
		else
			tc = caisse.getTotalventecarburant();

		tcs = caisse.getTotalachat() + caisse.getTotalcheque() + caisse.getTotalcredit() + caisse.getCartebanquaire()
				+ caisse.getTotaldepense() + caisse.getTotalmanque() + caisse.getRetourcuve() + caisse.getCarteshell();
		caisse.setTotalcaisse(tc - tcs);
		caisse.setObservation(caisse.getTotalespece() - caisse.getTotalcaisse());
		if (caisse.getObservation() > 0) {
			caisse.setRemarques("UN PLUS");
		} else
			caisse.setRemarques("UN MANQUE");
		serviceCaisse.update(caisse);
		soldetpe = serviceSoldetpe.getmaxcode();
		soldetpe.setSolde(soldetpe.getSolde() + totalcarteshell);
		serviceSoldetpe.update(soldetpe);
		poste = caisse.getPoste();
		totalesece = 0;
		totalcartebanqaire = 0;
		totalcarteshell = 0;
		return SUCCESS;
	}

	public String savecartebancaire() {
		caisse.setCartebanquaire(caisse.getCartebanquaire() + totalcartebanqaire);
		double tc = 0;
		double tcs = 0;
		if (caisse.getTotalcreditanterieur() != 0)
			tc = caisse.getTotalventecarburant() + caisse.getTotalcreditanterieur();
		else
			tc = caisse.getTotalventecarburant();

		tcs = caisse.getTotalachat() + caisse.getTotalcheque() + caisse.getTotalcredit() + caisse.getCartebanquaire()
				+ caisse.getTotaldepense() + caisse.getTotalmanque() + caisse.getRetourcuve() + caisse.getCarteshell();
		caisse.setTotalcaisse(tc - tcs);
		caisse.setObservation(caisse.getTotalespece() - caisse.getTotalcaisse());
		if (caisse.getObservation() > 0) {
			caisse.setRemarques("UN PLUS");
		} else
			caisse.setRemarques("UN MANQUE");
		serviceCaisse.update(caisse);
		totalcaisse = caisse.getTotalcaisses();

		soldetpe = serviceSoldetpe.getmaxcode();
		soldetpe.setSolecartebancaire(soldetpe.getSolecartebancaire().add(new BigDecimal(totalcartebanqaire)));
		serviceSoldetpe.update(soldetpe);
		totalesece = 0;
		totalcartebanqaire = 0;
		totalcarteshell = 0;
		return SUCCESS;
	}

	public String savecheque() {
		Date dat = caisse.getDate();
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		for (Cheque d : listcheque) {
			if (serviceCheque.Findbycode(d.getId()) == null) {
				if (d.getMontant() != 0) {
					d.setDate(dat);
					d.setDates(dates);
					serviceCheque.save(d);
				}
			} else
				serviceCheque.update(d);
		}
		double totals = 0;
		for (Cheque d : serviceCheque.getChequebyCaisse(caisse)) {
			totals = totals + d.getMontant();
		}

		caisse.setTotalcheque(totals);
		double tc = 0;
		double tcs = 0;
		if (caisse.getTotalcreditanterieur() != 0)
			tc = caisse.getTotalventecarburant() + caisse.getTotalcreditanterieur();
		else
			tc = caisse.getTotalventecarburant();

		tcs = caisse.getTotalachat() + caisse.getTotalcheque() + caisse.getTotalcredit() + caisse.getCartebanquaire()
				+ caisse.getTotaldepense() + caisse.getTotalmanque() + caisse.getRetourcuve() + caisse.getCarteshell();
		caisse.setTotalcaisse(tc - tcs);
		caisse.setObservation(caisse.getTotalespece() - caisse.getTotalcaisse());
		if (caisse.getObservation() > 0) {
			caisse.setRemarques("UN PLUS");
		} else
			caisse.setRemarques("UN MANQUE");
		serviceCaisse.update(caisse);
		totalcaisse = caisse.getTotalcaisses();
		observations = caisse.getObservations();
		poste = caisse.getPoste();
		return SUCCESS;
	}

	public String savedepense() {
		total = 0;
		Date date = caisse.getDate();
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		for (Depensegestat d : listdepense) {
			if (serviceDepense.getdepensebyid(d) == null) {
				if (d.getMontant() != 0) {
					if (d.getTransaction() != null) {
						d.getTransaction().setAffecte(true);
						serviceJournaldep.update(d.getTransaction());
					}
					d.setDate(date);
					d.setDates(dates);
					serviceDepense.save(d);
					// modifier selon les libelle de la fammille depense qui est a galle e nom des
					// vehicules
					if (d.getFamilledepense() != null && d.getFamilledepense().getId() == 4
							|| d.getFamilledepense().getId() == 6 || d.getFamilledepense().getId() == 7
							|| d.getFamilledepense().getId() == 8 || d.getFamilledepense().getId() == 16
							|| d.getFamilledepense().getId() == 17 || d.getFamilledepense().getId() == 18
							|| d.getFamilledepense().getId() == 19 || d.getFamilledepense().getId() == 20
							|| d.getFamilledepense().getId() == 25) {
						Depense dd = new Depense();
						Vhecule v = serviceVehecule.Findbynom(d.getFamilledepense().getLibelle());
						if (v != null)
							dd.setVhecule(v);
						else
							dd.setVhecule(null);
						dd.setDate(date);
						dd.setDates(dates);
						dd.setLibelle(d.getLibelle());
						dd.setMontant(d.getMontant());
						if (d.getFamiledepvoiture()==2)
							dd.setFamilledepense(serviceFamilleDepenset.getFamilebyeibelle("carburant"));
						else
							dd.setFamilledepense(serviceFamilleDepenset.getFamilebyeibelle("autre"));
						servicedepense.save(dd);
					}

				}
			} else {

				serviceDepense.update(d);
				if (d.getFamilledepense() != null && d.getFamilledepense().getLibelle().equals("IVECO 139")
						|| d.getFamilledepense().getLibelle().equals("DMAX 125")
						|| d.getFamilledepense().getLibelle().equals("MIT 136")
						|| d.getFamilledepense().getLibelle().equals("DMAX 120")
						|| d.getFamilledepense().getLibelle().equals("IVECO 149")
						|| d.getFamilledepense().getLibelle().equals("DMAX 122")
						|| d.getFamilledepense().getLibelle().equals("IVECO 163")
						|| d.getFamilledepense().getLibelle().equals("DMAX 115")
						|| d.getFamilledepense().getLibelle().equals("DMAX 121")) {
					Vhecule v = serviceVehecule.Findbynom(d.getFamilledepense().getLibelle());
					Depense dd = servicedepense.getdepensebyid(v.getId(), "", d.getLibelle(), dates);

					if (dd != null) {
						dd.setMontant(d.getMontant());
						dd.setLibelle(d.getLibelle());

						dd.setMontant(d.getMontant());
						if (d.getFamiledepvoiture()==2)
							dd.setFamilledepense(serviceFamilleDepenset.getFamilebyeibelle("carburant"));
						else
							dd.setFamilledepense(serviceFamilleDepenset.getFamilebyeibelle("autre"));
						
						servicedepense.update(dd);
					}
				}

			}
		}
		for (Depensegestat d : serviceDepense.getdepensebyCaisse(caisse))
			total = total + d.getMontant();
		caisse.setTotaldepense(total);
		double tc = 0;
		double tcs = 0;
		if (caisse.getTotalcreditanterieur() != 0)
			tc = caisse.getTotalventecarburant() + caisse.getTotalcreditanterieur();
		else
			tc = caisse.getTotalventecarburant();

		tcs = caisse.getTotalachat() + caisse.getTotalcheque() + caisse.getTotalcredit() + caisse.getCartebanquaire()
				+ caisse.getTotaldepense() + caisse.getTotalmanque() + caisse.getRetourcuve() + caisse.getCarteshell();
		caisse.setTotalcaisse(tc - tcs);
		caisse.setObservation(caisse.getTotalespece() - caisse.getTotalcaisse());
		if (caisse.getObservation() > 0) {
			caisse.setRemarques("UN PLUS");
		} else
			caisse.setRemarques("UN MANQUE");
		serviceCaisse.update(caisse);
		totalcaisse = caisse.getTotalcaisses();
		observations = caisse.getObservations();
		poste = caisse.getPoste();
		return SUCCESS;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Poste getPoste() {
		return poste;
	}

	public void setPoste(Poste poste) {
		this.poste = poste;
	}

	public Poste[] getPostes() {
		return postes;
	}

	public void setPostes(Poste[] postes) {
		this.postes = postes;
	}

	public Caisse getCaisse() {
		return caisse;
	}

	public void setCaisse(Caisse caisse) {
		this.caisse = caisse;
	}

	public Integer getCodes() {
		return codes;
	}

	public void setCodes(Integer codes) {
		this.codes = codes;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double gettotal() {
		return total;
	}

	public void settotal(double total) {
		this.total = total;
	}

	public String gettotals() {
		DecimalFormat df = new DecimalFormat("0.000");
		totals = df.format(total);
		return totals;
	}

	public void settotals(String totals) {
		this.totals = totals;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public ServiceCaisse getServiceCaisse() {
		return serviceCaisse;
	}

	public void setServiceCaisse(ServiceCaisse serviceCaisse) {
		this.serviceCaisse = serviceCaisse;
	}

	public String getDates() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);

		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public List<Credit> getListcredit() {
		return listcredit;
	}

	public void setListcredit(List<Credit> listcredit) {
		this.listcredit = listcredit;
	}

	public ServicePompiste getServicePompiste() {
		return servicePompiste;
	}

	public void setServicePompiste(ServicePompiste servicePompiste) {
		this.servicePompiste = servicePompiste;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ServiceCheque getServiceCheque() {
		return serviceCheque;
	}

	public void setServiceCheque(ServiceCheque serviceCheque) {
		this.serviceCheque = serviceCheque;
	}

	public List<Cheque> getListcheque() {
		return listcheque;
	}

	public void setListcheque(List<Cheque> listcheque) {
		this.listcheque = listcheque;
	}

	public Cheque getCheque() {
		return cheque;
	}

	public void setCheque(Cheque cheque) {
		this.cheque = cheque;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getTotals() {
		return totals;
	}

	public void setTotals(String totals) {
		this.totals = totals;
	}

	public ServiceAchatcaisse getServiceAchatcaisse() {
		return serviceAchatcaisse;
	}

	public void setServiceAchatcaisse(ServiceAchatcaisse serviceAchatcaisse) {
		this.serviceAchatcaisse = serviceAchatcaisse;
	}

	public Achatcaisse getAchatcaisse() {
		return achatcaisse;
	}

	public void setAchatcaisse(Achatcaisse achatcaisse) {
		this.achatcaisse = achatcaisse;
	}

	public List<Achatcaisse> getListachatcaisse() {
		return listachatcaisse;
	}

	public void setListachatcaisse(List<Achatcaisse> listachatcaisse) {
		this.listachatcaisse = listachatcaisse;
	}

	public ServiceCreditanterieur getServiceCreditanterieur() {
		return serviceCreditanterieur;
	}

	public void setServiceCreditanterieur(ServiceCreditanterieur serviceCreditanterieur) {
		this.serviceCreditanterieur = serviceCreditanterieur;
	}

	public Creditanterieur getCreditanterieur() {
		return creditanterieur;
	}

	public void setCreditanterieur(Creditanterieur creditanterieur) {
		this.creditanterieur = creditanterieur;
	}

	public List<Creditanterieur> getListCreditanterieur() {
		return listCreditanterieur;
	}

	public void setListCreditanterieur(List<Creditanterieur> listCreditanterieur) {
		this.listCreditanterieur = listCreditanterieur;
	}

	public ServiceCreditclient getServiceCreditclient() {
		return serviceCreditclient;
	}

	public void setServiceCreditclient(ServiceCreditclient serviceCreditclient) {
		this.serviceCreditclient = serviceCreditclient;
	}

	public ServiceProduit getServiceProduit() {
		return serviceProduit;
	}

	public void setServiceProduit(ServiceProduit serviceProduit) {
		this.serviceProduit = serviceProduit;
	}

	public List<Produit> getListproduits() {
		return listproduits;
	}

	public void setListproduits(List<Produit> listproduits) {
		this.listproduits = listproduits;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public ServiceVhecule getServiceVehecule() {
		return serviceVehecule;
	}

	public void setServiceVehecule(ServiceVhecule serviceVehecule) {
		this.serviceVehecule = serviceVehecule;
	}

	public ServiceRetourcuve getServiceRetourcuve() {
		return serviceRetourcuve;
	}

	public void setServiceRetourcuve(ServiceRetourcuve serviceRetourcuve) {
		this.serviceRetourcuve = serviceRetourcuve;
	}

	public Retourcuve getRetourcuve() {
		return retourcuve;
	}

	public void setRetourcuve(Retourcuve retourcuve) {
		this.retourcuve = retourcuve;
	}

	public List<Retourcuve> getRetourcuves() {
		return retourcuves;
	}

	public void setRetourcuves(List<Retourcuve> retourcuves) {
		this.retourcuves = retourcuves;
	}

	public ServicePompe getServicepompe() {
		return servicepompe;
	}

	public void setServicepompe(ServicePompe servicepompe) {
		this.servicepompe = servicepompe;
	}

	public List<Pompe> getPompes() {
		return pompes;
	}

	public void setPompes(List<Pompe> pompes) {
		this.pompes = pompes;
	}

	public Pompe getPompe() {
		return pompe;
	}

	public void setPompe(Pompe pompe) {
		this.pompe = pompe;
	}

	public List<Ligneindex> getLigneindex() {
		return ligneindex;
	}

	public void setLigneindex(List<Ligneindex> ligneindex) {
		this.ligneindex = ligneindex;
	}

	public double getEL1() {
		return EL1;
	}

	public void setEL1(double eL1) {
		EL1 = eL1;
	}

	public double getEL2() {
		return EL2;
	}

	public void setEL2(double eL2) {
		EL2 = eL2;
	}

	public double getEL3() {
		return EL3;
	}

	public void setEL3(double eL3) {
		EL3 = eL3;
	}

	public double getEL4() {
		return EL4;
	}

	public void setEL4(double eL4) {
		EL4 = eL4;
	}

	public double getTotalLitrage() {
		return totalLitrage;
	}

	public void setTotalLitrage(double totalLitrage) {
		this.totalLitrage = totalLitrage;
	}

	public double getTotalventecarburant() {
		return totalventecarburant;
	}

	public void setTotalventecarburant(double totalventecarburant) {
		this.totalventecarburant = totalventecarburant;
	}

	public ServiceFamilleDepensegestat getServiceFamilleDepense() {
		return serviceFamilleDepense;
	}

	public void setServiceFamilleDepense(ServiceFamilleDepensegestat serviceFamilleDepense) {
		this.serviceFamilleDepense = serviceFamilleDepense;
	}

	public ServiceDepensegestat getServiceDepense() {
		return serviceDepense;
	}

	public void setServiceDepense(ServiceDepensegestat serviceDepense) {
		this.serviceDepense = serviceDepense;
	}

	public ServiceAvancegestat getServiceAvance() {
		return serviceAvance;
	}

	public void setServiceAvance(ServiceAvancegestat serviceAvance) {
		this.serviceAvance = serviceAvance;
	}

	public ServiceClientgestat getServiceClient() {
		return serviceClient;
	}

	public void setServiceClient(ServiceClientgestat serviceClient) {
		this.serviceClient = serviceClient;
	}

	/*
	 * public ServiceLigneventeboncredit getServiceLignevente() { return
	 * serviceLignevente; }
	 * 
	 * public void setServiceLignevente(ServiceLigneventeboncredit
	 * serviceLignevente) { this.serviceLignevente = serviceLignevente; }
	 */

	public Credit getCredit() {
		return credit;
	}

	public void setCredit(Credit credit) {
		this.credit = credit;
	}

	public List<Ligneventecredit> getLigneventes() {
		return ligneventes;
	}

	public void setLigneventes(List<Ligneventecredit> ligneventes) {
		this.ligneventes = ligneventes;
	}

	public Ligneventecredit getLignevente() {
		return lignevente;
	}

	public void setLignevente(Ligneventecredit lignevente) {
		this.lignevente = lignevente;
	}

	public Depensegestat getDepense() {
		return depense;
	}

	public void setDepense(Depensegestat depense) {
		this.depense = depense;
	}

	public Depensegestat getDepense1() {
		return depense1;
	}

	public void setDepense1(Depensegestat depense1) {
		this.depense1 = depense1;
	}

	public List<Depensegestat> getListdepense() {
		return listdepense;
	}

	public void setListdepense(List<Depensegestat> listdepense) {
		this.listdepense = listdepense;
	}

	public Familledepensegestat getFamilledepense() {
		return familledepense;
	}

	public void setFamilledepense(Familledepensegestat familledepense) {
		this.familledepense = familledepense;
	}

	public List<Familledepensegestat> getListfamilledepense() {
		return listfamilledepense;
	}

	public void setListfamilledepense(List<Familledepensegestat> listfamilledepense) {
		this.listfamilledepense = listfamilledepense;
	}

	 

	public ServiceEmployee getServiceemployees() {
		return serviceemployees;
	}

	public void setServiceemployees(ServiceEmployee serviceemployees) {
		this.serviceemployees = serviceemployees;
	}

	public ServiceDepense getServicedepense() {
		return servicedepense;
	}

	public void setServicedepense(ServiceDepense servicedepense) {
		this.servicedepense = servicedepense;
	}

	public List<Avancegestat> getListAvances() {
		return listAvances;
	}

	public void setListAvances(List<Avancegestat> listAvances) {
		this.listAvances = listAvances;
	}

	public Avancegestat getAvance() {
		return avance;
	}

	public void setAvance(Avancegestat avance) {
		this.avance = avance;
	}

	public List<Clientgestat> getListclients() {
		return listclients;
	}

	public void setListclients(List<Clientgestat> listclients) {
		this.listclients = listclients;
	}

	public Clientgestat getClient() {
		return client;
	}

	public void setClient(Clientgestat client) {
		this.client = client;
	}

	public String getELs1() {
		DecimalFormat df = new DecimalFormat("0.000");
		ELs1 = df.format(EL1);
		return ELs1;
	}

	public void setELs1(String eLs1) {
		ELs1 = eLs1;
	}

	public String getELs2() {
		DecimalFormat df = new DecimalFormat("0.000");
		ELs2 = df.format(EL2);
		return ELs2;
	}

	public void setELs2(String eLs2) {
		ELs2 = eLs2;
	}

	public String getELs3() {
		DecimalFormat df = new DecimalFormat("0.000");
		ELs3 = df.format(EL3);
		return ELs3;
	}

	public void setELs3(String eLs3) {
		ELs3 = eLs3;
	}

	public String getELs4() {
		DecimalFormat df = new DecimalFormat("0.000");
		ELs4 = df.format(EL4);
		return ELs4;
	}

	public void setELs4(String eLs4) {
		ELs4 = eLs4;
	}

	public String getTotalLitrages() {
		DecimalFormat df = new DecimalFormat("0.000");
		totalLitrages = df.format(totalLitrage);
		return totalLitrages;
	}

	public void setTotalLitrages(String totalLitrages) {
		this.totalLitrages = totalLitrages;
	}

	public String getTotalventecarburants() {
		DecimalFormat df = new DecimalFormat("0.000");
		totalventecarburants = df.format(totalventecarburant);
		return totalventecarburants;
	}

	public void setTotalventecarburants(String totalventecarburants) {
		this.totalventecarburants = totalventecarburants;
	}

	public Ligneindex getLignindex() {
		return lignindex;
	}

	public void setLignindex(Ligneindex lignindex) {
		this.lignindex = lignindex;
	}

	public ServiceLigneindex getServiceLigneindex() {
		return serviceLigneindex;
	}

	public void setServiceLigneindex(ServiceLigneindex serviceLigneindex) {
		this.serviceLigneindex = serviceLigneindex;
	}

	public ServiceFamilleDepense getServiceFamilleDepenset() {
		return serviceFamilleDepenset;
	}

	public void setServiceFamilleDepenset(ServiceFamilleDepense serviceFamilleDepenset) {
		this.serviceFamilleDepenset = serviceFamilleDepenset;
	}

	public void connexionFTP() {
		String server = "10.83.196.3";
		int port = 21;
		String username = "supervisor";
		String password = "C70D6240D";
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(server, port);
			reponseServeur(ftpClient);
			int reponse = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reponse)) {
				System.out.println("Operation echoue. Reponse Serveur: " + reponse);
				return;
			}
			boolean etat = ftpClient.login(username, password);
			reponseServeur(ftpClient);
			if (!etat) {
				System.out.println("Impossible d'acceder au serveur");
				return;

			} else {
				System.out.println("Identification reussie");

				ftpClient.enterLocalPassiveMode();
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

				// lecture du fichier num pour obtenir le dernier fichier d index
				BufferedReader IN = new BufferedReader(new FileReader("M:/FTP_E00/num.txt"));
				String ss = IN.readLine();
				IN.close();

				// mettre a jour le fichier num

				String CheminFichierDistant = "/EXPORT/FTP_E00/" + "000" + ss + ".P";
				File fichierlocal = new File("M:/FTP_E00/" + "000" + ss + ".P");

				FTPFile[] files1 = ftpClient.listFiles("/EXPORT/FTP_E00/");
				long size = 0;
				for (int i = 0; i < files1.length; i++) {
					if (files1[i].getName().equals("000" + ss + ".P"))
						// obtenir la taille du fichier
						size = files1[i].getSize();
					System.out.println(" s" + size);
				}
				OutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(fichierlocal));

				InputStream inputStream2 = ftpClient.retrieveFileStream(CheminFichierDistant);

				byte[] bytesArray = new byte[4096];
				int bytesRead = -1;

				// tant qu'on a pas atteint la fin
				int transfere = 0;
				int pourcentage = 0;
				while ((bytesRead = inputStream2.read(bytesArray)) != -1) {
					// on ecrit les octets dans l'emplacement precise
					outputStream2.write(bytesArray, 0, bytesRead);

					transfere += bytesRead;
					pourcentage = (int) (transfere * 100 / size);
					System.out.println(pourcentage + "%");
				} // outputStream3.write();

				// fermer les flux de lecture de d'ecriture
				inputStream2.close();
				outputStream2.close();

			}
		} catch (IOException ex) {
			System.out.println("Une erreur lors de la connexion a ete detecte");
			ex.printStackTrace();
		} finally {
			try {
				if (ftpClient.isConnected()) {
					// fermer la connexion FTP
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
	}

	private void reponseServeur(FTPClient ftpClient) {
		String[] reponses = ftpClient.getReplyStrings();
		if (reponses != null && reponses.length > 0) {
			for (String reponse : reponses) {
				System.out.println("SERVEUR :" + reponse);
			}
		}
	}

	public String returnPompe(String s) {

		if (s.equals("QUA1,1")) {
			return "SSP V-POWER A1";
		}
		if (s.equals("QUA1,2")) {
			return "GASOIL 50 A1";
		}

		if (s.equals("QUA1,3")) {
			return "GASOIL A1";
		}

		if (s.equals("QUA2,1")) {
			return "SUPER A2";
		}

		if (s.equals("QUA2,2")) {
			return "GASOIL 50 A2";
		}

		if (s.equals("QUA2,3")) {
			return "GASOIL A2";
		}

		if (s.equals("QUA3,1")) {
			return "SUPER B1";
		}

		if (s.equals("QUA3,2")) {
			return "GASOIL 50 B1";
		}

		if (s.equals("QUA3,3")) {
			return "GASOIL B1";
		}

		if (s.equals("QUA4,1")) {
			return "SUPER B2";
		}

		if (s.equals("QUA4,2")) {
			return "GASOIL 50 B2";
		}

		if (s.equals("QUA4,3")) {
			return "GASOIL B2";
		}

		if (s.equals("QUA5,1")) {
			return "SUPER 1C";
		}

		if (s.equals("QUA5,2")) {
			return "GASOIL 50 C1";
		}

		if (s.equals("QUA5,3")) {
			return "GASOIL 1C";
		}

		if (s.equals("QUA6,1")) {
			return "SUPER C2";
		}

		if (s.equals("QUA6,2")) {
			return "GASOIL 50 C2";
		}

		if (s.equals("QUA6,3")) {
			return "GASOIL C2";
		}

		if (s.equals("QUA7,2")) {
			return "PETROLE";
		}

		if (s.equals("QUA8,1")) {
			return "PETROLE 2";
		}

		if (s.equals("QUA9,2")) {
			return "GASOIL V11";
		}

		if (s.equals("QUA10,1")) {
			return "GASOIL V12";
		}

		if (s.equals("QUA11,2")) {
			return "GASOIL 1D";
		}
		if (s.equals("QUA12,1")) {
			return "GASOIL 2D";
		}
		return "rien";
	}

	public String returnindex(String s) {

		if (s.equals("SSP A1")) {
			return "QUA1,1";
		}
		if (s.equals("SSP V-POWER A1")) {
			return "QUA1,2";
		}

		if (s.equals("GASOIL 50 A1")) {
			return "QUA1,3";
		}

		if (s.equals("GASOIL A1")) {
			return "QUA1,4";
		}

		if (s.equals("SSP A2")) {
			return "QUA2,1";
		}

		if (s.equals("SSP V-POWER A2")) {
			return "QUA2,2";
		}

		if (s.equals("GASOIL 50 A2")) {
			return "QUA2,3";
		}

		if (s.equals("GASOIL A2")) {
			return "QUA2,4";
		}

		if (s.equals("SSP B1")) {
			return "QUA3,1";
		}

		if (s.equals("SSP V-POWER B1")) {
			return "QUA3,2";
		}

		if (s.equals("GASOIL 50 B1")) {
			return "QUA3,3";
		}

		if (s.equals("GASOIL B1")) {
			return "QUA3,4";
		}

		if (s.equals("SSP B2")) {
			return "QUA4,1";
		}

		if (s.equals("SSP V-POWER B2")) {
			return "QUA4,2";
		}

		if (s.equals("GASOIL 50 B2")) {
			return "QUA4,3";
		}

		if (s.equals("GASOIL B2")) {
			return "QUA4,4";
		}

		if (s.equals("SSP C1")) {
			return "QUA5,1";
		}

		if (s.equals("SSP V-POWER 1C")) {
			return "QUA5,2";
		}

		if (s.equals("GASOIL 50 C1")) {
			return "QUA5,3";
		}

		if (s.equals("GASOIL 1C")) {
			return "QUA5,4";
		}

		if (s.equals("SSP C2")) {
			return "QUA6,1";
		}

		if (s.equals("SSP V-POWER C2")) {
			return "QUA6,2";
		}

		if (s.equals("GASOIL 50 C2")) {
			return "QUA6,3";
		}

		if (s.equals("GASOIL C2")) {
			return "QUA6,4";
		}

		if (s.equals("PETROLE")) {
			return "QUA7,2";
		}

		if (s.equals("PETROLE 2")) {
			return "QUA8,1";
		}

		if (s.equals("GASOIL V 11")) {
			return "QUA9,2";
		}

		if (s.equals("GASOIL V 12")) {
			return "QUA10,1";
		}

		if (s.equals("GASOIL 1D")) {
			return "QUA11,2";
		}
		if (s.equals("GASOIL 2D")) {
			return "QUA12,1";
		}
		return "rien";
	}

	public Integer getId() {
		return id;
	}

	public double getTotalesece() {
		return totalesece;
	}

	public void setTotalesece(double totalesece) {
		this.totalesece = totalesece;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getTotalcarteshell() {
		return totalcarteshell;
	}

	public void setTotalcarteshell(double totalcarteshell) {
		this.totalcarteshell = totalcarteshell;
	}

	public double getTotalcartebanqaire() {
		return totalcartebanqaire;
	}

	public void setTotalcartebanqaire(double totalcartebanqaire) {
		this.totalcartebanqaire = totalcartebanqaire;
	}

	public ServiceArticleCarburant getServiceArticleCarburant() {
		return serviceArticleCarburant;
	}

	public void setServiceArticleCarburant(ServiceArticleCarburant serviceArticleCarburant) {
		this.serviceArticleCarburant = serviceArticleCarburant;
	}

	public ServiceSoldetpe getServiceSoldetpe() {
		return serviceSoldetpe;
	}

	public void setServiceSoldetpe(ServiceSoldetpe serviceSoldetpe) {
		this.serviceSoldetpe = serviceSoldetpe;
	}

	public Soldetpe getSoldetpe() {
		return soldetpe;
	}

	public void setSoldetpe(Soldetpe soldetpe) {
		this.soldetpe = soldetpe;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getListeEmployee() {
		return listeEmployee;
	}

	public void setListeEmployee(List<Employee> listeEmployee) {
		this.listeEmployee = listeEmployee;
	}

	public String getTotalcaisse() {
		return totalcaisse;
	}

	public void setTotalcaisse(String totalcaisse) {
		this.totalcaisse = totalcaisse;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public List<Clientgestat> getFilteredClients() {
		return filteredClients;
	}

	public void setFilteredClients(List<Clientgestat> filteredClients) {
		this.filteredClients = filteredClients;
	}

	public ServiceTracegestat getServicetrace() {
		return servicetrace;
	}

	public void setServicetrace(ServiceTracegestat servicetrace) {
		this.servicetrace = servicetrace;
	}

	public UserService getUserservice() {
		return userservice;
	}

	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	public ServiceJournal getServicejornal() {
		return servicejornal;
	}

	public void setServicejornal(ServiceJournal servicejornal) {
		this.servicejornal = servicejornal;
	}

	public List<TransactionCredit> getListjournal() {
		return listjournal;
	}

	public void setListjournal(List<TransactionCredit> listjournal) {
		this.listjournal = listjournal;
	}



	public ServiceJournalDep getServiceJournaldep() {
		return serviceJournaldep;
	}



	public void setServiceJournaldep(ServiceJournalDep serviceJournaldep) {
		this.serviceJournaldep = serviceJournaldep;
	}



	public double getEL5() {
		return EL5;
	}



	public void setEL5(double eL5) {
		EL5 = eL5;
	}



	public String getELs5() {
		return ELs5;
	}



	public void setELs5(String eLs5) {
		ELs5 = eLs5;
	}


 

}
