package com.tn.shell.ui.gestat;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DecimalFormat;
import java.text.ParseException;
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
import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.tn.shell.model.gestat.Clientgestat;
import com.tn.shell.model.gestat.TransactionCredit;
import com.tn.shell.model.gestat.TransactionDepense;
import com.tn.shell.model.shop.Lignevente;
import com.tn.shell.model.shop.Produit;
import com.tn.shell.model.shop.Ticket;
import com.tn.shell.model.transport.Facture;
import com.tn.shell.model.transport.Vhecule;
import com.tn.shell.service.gestat.ServiceClientgestat;
import com.tn.shell.service.gestat.ServiceJournal;
import com.tn.shell.service.gestat.ServiceJournalDep;
import com.tn.shell.service.shop.ServiceLignevente;
import com.tn.shell.service.shop.ServiceTicket;
import com.tn.shell.service.transport.ServiceFacture;
import com.tn.shell.service.transport.ServiceLigneCommande;
import com.tn.shell.service.transport.ServiceVhecule;

@ManagedBean(name = "CrediClientBean")
@SessionScoped
public class CrediClientBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	@ManagedProperty(value = "#{ServiceClientgestat}")
	ServiceClientgestat serviceClient;
	@ManagedProperty(value = "#{ServiceJournal}")
	ServiceJournal servicejornal;
	@ManagedProperty(value = "#{ServiceLignevente}")
	ServiceLignevente serviceLignevente;
	@ManagedProperty(value = "#{ServiceTicket}")
	ServiceTicket serviceTicket;
	@ManagedProperty(value = "#{ServiceFacture}")
	ServiceFacture serviceFacture;
	@ManagedProperty(value = "#{ServiceVhecule}")
    ServiceVhecule serviceVhecule;
	@ManagedProperty(value = "#{ServiceJournalDep}")
	ServiceJournalDep serviceJournalDep;
	private List<Clientgestat> listclients;
	private Clientgestat client;
	private List<TransactionCredit> listjournal;
	private List<TransactionDepense>  listjournaldep;
	private double totalttc;
	private Integer numTicket;
	private double totalquantite;
	private Date date;
	private String chauffeur;
	private String groupe;
	private String vhecule;
	private String codeclient;
	private String tel;
	private String kilometrage;
	private Date date11;
	private Date date12;
	private List<Lignevente> listelignevente;
	private String totalttcs;
	private List<String> listpompes;
	private List<Facture> listFacture;
	private List<String> selectedspompes;
	private TransactionCredit selectedlistjournal;
	private TransactionDepense selectedlistjournaldep;
	private double total;
	private String codeFacture;
	private Facture facture;
	private String depense;
	private String typecredit; 
	private List<Vhecule> listVehecule;
	
public String saisieDepense() {	
	
	listjournaldep = new ArrayList<TransactionDepense>();
	listVehecule=new ArrayList<Vhecule>();
	listVehecule=serviceVhecule.getAll();
	date11 = null;
	chauffeur = null;
	vhecule = null;
	date12 = null;
	codeclient = null;
	selectedspompes = new ArrayList<String>();
 
	numTicket = 0;
	listelignevente = new ArrayList<Lignevente>();
	totalquantite = 0;
	totalttcs = null;
	listpompes = new ArrayList<String>();
	listpompes.add("PMP:1");
	listpompes.add("PMP:2");
	listpompes.add("PMP:3");
	listpompes.add("PMP:4");
	listpompes.add("PMP:5");
	listpompes.add("PMP:6");

	listpompes.add("PMP:7");
	listpompes.add("PMP:8");
	listpompes.add("PMP:9");
	listpompes.add("PMP:10");
	listpompes.add("PMP:11");
	listpompes.add("PMP:12");
		return SUCCESS;
	}
	public String saisiecredit() {
		listjournal = new ArrayList<TransactionCredit>();
		listclients = new ArrayList<Clientgestat>();
		listclients = serviceClient.getAll();
		date11 = null;
		chauffeur = null;
		vhecule = null;
		date12 = null;
		codeclient = null;
		selectedspompes = new ArrayList<String>();
		listjournal = new ArrayList<TransactionCredit>();
		numTicket = 0;
		listelignevente = new ArrayList<Lignevente>();
		totalquantite = 0;
		totalttcs = null;
		listpompes = new ArrayList<String>();

		listpompes.add("PMP:1");
		listpompes.add("PMP:2");
		listpompes.add("PMP:3");
		listpompes.add("PMP:4");
		listpompes.add("PMP:5");
		listpompes.add("PMP:6");

		listpompes.add("PMP:7");
		listpompes.add("PMP:8");
		listpompes.add("PMP:9");
		listpompes.add("PMP:10");
		listpompes.add("PMP:11");
		listpompes.add("PMP:12");

		return SUCCESS;
	}

	public  void getFactureByCode(AjaxBehaviorEvent event) {
		listFacture=new ArrayList<Facture>();
		facture=serviceFacture.getBLbycodes(codeFacture);
		 if(facture!=null)
			 listFacture.add(facture);
	}

	public void getVenteByTicket(AjaxBehaviorEvent event) {
		listelignevente = new ArrayList<Lignevente>();
		listelignevente = serviceLignevente.getAllbyNumticket(numTicket);
		DecimalFormat df = new DecimalFormat("0.000");
		total = 0;
		if (listelignevente != null)
			for (Lignevente l : listelignevente) {
				total = total + l.getTotalttc();
				totalquantite = totalquantite + l.getQuantite();
			}

		totalttcs = df.format(total);
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Ligne selectionnee");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("Ligne deselectionnee");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String getTransactionBetweenTime() throws ParseException, ClassNotFoundException {
		ConnextionFTP f = new ConnextionFTP();
		DecimalFormat df = new DecimalFormat("0.000");
		 
		f.connexionFTP2(date);
		SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
		listjournal = new ArrayList<TransactionCredit>();
		if (selectedspompes != null)
			listjournal = f.selectAlljournal(date, sf.format(date11), sf.format(date12), selectedspompes,
					servicejornal);

		totalttc = 0;
		totalquantite = 0;

		return SUCCESS;

	}
	
	
	public String getTransactionBetweenTime2() throws ParseException, ClassNotFoundException {
		ConnextionFTP f = new ConnextionFTP();
		DecimalFormat df = new DecimalFormat("0.000");
		 
		f.connexionFTP2(date);
		SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
		listjournal = new ArrayList<TransactionCredit>();
		if (selectedspompes != null)
			listjournaldep = f.selectAlljournalDepense(date, sf.format(date11), sf.format(date12), selectedspompes,
					serviceJournalDep);

		totalttc = 0;
		totalquantite = 0;

		return SUCCESS;

	}
public String validersaisieDepense() { 
	SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
	DecimalFormat df = new DecimalFormat("0.000");	
	// if(selectedlistjournal!=null) {
	// servicejornal.save(selectedlistjournal);
	if(typecredit.equals("passager")) {
	if(chauffeur!=null)
	selectedlistjournaldep.setChauffeur(chauffeur);
	if(vhecule!=null)
		selectedlistjournaldep.setVhecule(vhecule);
	
	
	double total = 0;
	double totalMonatnt = 0;
	selectedlistjournaldep.setTotalttac(selectedlistjournaldep.getMontant());
	
    PageFormat format = new PageFormat();
	Paper paper = new Paper();
	double paperWidth = 3.25;
	double paperHeight = 11.69;
	double leftMargin = 0.19;
	double rightMargin = 0.25;
	double topMargin = 0;
	double bottomMargin = 0.01;
	paper.setImageableArea(leftMargin * 72.0, topMargin * 72.0, (paperWidth - leftMargin - rightMargin) * 72.0,
			(paperHeight - topMargin - bottomMargin) * 72.0);
	format.setPaper(paper);
	PrintService[] printServices = PrinterJob.lookupPrintServices();
	PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
	aset.add(OrientationRequested.PORTRAIT);
	aset.add(MediaSizeName.INVOICE);
	PrinterJob printerJob = PrinterJob.getPrinterJob();
	PrintService service = null; 
	for (int index = 0; service == null && index < printServices.length; index++) {

		if (printServices[index].getName().toLowerCase().indexOf("IMPCREDIT".toLowerCase()) >= 0
				|| printServices[index].getName().toLowerCase().indexOf("impcredit".toLowerCase()) >= 0) {
			service = printServices[index];
		}
	}	 
	try {
		printerJob.setPrintService(service);
	} catch (PrinterException e) {
		FacesMessage msg2 = new FacesMessage(FacesMessage.SEVERITY_INFO, service.getName(), "");

		FacesContext.getCurrentInstance().addMessage(null, msg2);
	}		
	Printable printable = new PrintDepense(selectedlistjournaldep);
	printerJob.setPrintable(printable);
 
	try {
		printerJob.print(aset);
		//printerJob.print(aset);
	} catch (Exception e) {
		String codefamille = "\n\n\n" + e.getMessage() + "\n\n\n" + e.getStackTrace();
		for (PrintService printService : printServices) {
			codefamille = codefamille + printService.getName();
		}			 
	}
	}
	else {
		Vhecule v=serviceVhecule.Findbynom(codeclient);
		if(v!=null) {
			selectedlistjournaldep.setVhicule(v);
			selectedlistjournaldep.setVhecule(codeclient);
		}
		if(chauffeur!=null)
			selectedlistjournaldep.setChauffeur(chauffeur);
			 
			double total = 0;
			double totalMonatnt = 0;
			selectedlistjournaldep.setTotalttac(selectedlistjournaldep.getMontant());
			if(groupe.equals("kilometrage")) {
				selectedlistjournaldep.setKilometrage(kilometrage);
			}
			else
				selectedlistjournaldep.setKilometrage("groupe");
			
		 	
		 	Integer res=0;
		 	Integer oldKil=0;
		 	Integer newKil=0;
		 	TransactionDepense maxTr=serviceJournalDep.getmaxjournal(codeclient);
		 	if(maxTr !=null && kilometrage!=null) {
		 		try {
		 		  oldKil=Integer.parseInt(maxTr.getKilometrage());
		 		  newKil=Integer.parseInt(kilometrage);
		 		  res=newKil-oldKil; 
		 		if(res>0) {
		 		selectedlistjournaldep.setTrajet(res);
		 		selectedlistjournaldep.setPourcentage(selectedlistjournaldep.getQuantite() / res * 100);
		 		}
		 		else 
		 			selectedlistjournaldep.setTrajet(newKil);
		 		
		 		 
		 		}
		 		catch(Exception e) {
		 			addMessage("ce n'est pas un kilometrage");
		 			return ERROR;
		 		}
		 		
		 	}
		 	serviceJournalDep.save(selectedlistjournaldep);		
			
		    PageFormat format = new PageFormat();
			Paper paper = new Paper();
			double paperWidth = 3.25;
			double paperHeight = 11.69;
			double leftMargin = 0.19;
			double rightMargin = 0.25;
			double topMargin = 0;
			double bottomMargin = 0.01;
			paper.setImageableArea(leftMargin * 72.0, topMargin * 72.0, (paperWidth - leftMargin - rightMargin) * 72.0,
					(paperHeight - topMargin - bottomMargin) * 72.0);
			format.setPaper(paper);
			PrintService[] printServices = PrinterJob.lookupPrintServices();
			PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
			aset.add(OrientationRequested.PORTRAIT);
			aset.add(MediaSizeName.INVOICE);
			PrinterJob printerJob = PrinterJob.getPrinterJob();
			PrintService service = null; 
			for (int index = 0; service == null && index < printServices.length; index++) {

				if (printServices[index].getName().toLowerCase().indexOf("IMPCREDIT".toLowerCase()) >= 0
						|| printServices[index].getName().toLowerCase().indexOf("impcredit".toLowerCase()) >= 0) {
					service = printServices[index];
				}
			}	 
			try {
				printerJob.setPrintService(service);
			} catch (PrinterException e) {
				FacesMessage msg2 = new FacesMessage(FacesMessage.SEVERITY_INFO, service.getName(), "");

				FacesContext.getCurrentInstance().addMessage(null, msg2);
			}		
			selectedlistjournaldep.setTotalttac(selectedlistjournaldep.getMontant());
			Printable printable = new PrintDepense(selectedlistjournaldep);
			printerJob.setPrintable(printable);
			 
			try {
				printerJob.print(aset);
				 
			} catch (Exception e) {
				String codefamille = "\n\n\n" + e.getMessage() + "\n\n\n" + e.getStackTrace();
				for (PrintService printService : printServices) {
					codefamille = codefamille + printService.getName();
				}			 
			}
	}
	 
	selectedlistjournaldep = null;
	date11 = null;
	chauffeur = null;
	vhecule = null;

	date12 = null;
	codeclient = null;
	selectedspompes = new ArrayList<String>();
	listjournaldep = new ArrayList<TransactionDepense>();
	numTicket = 0;
	listelignevente = new ArrayList<Lignevente>();
	totalquantite = 0;
	 
	totalttcs = null;
	return SUCCESS;
}

public void addMessage(String summary) {
	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
	FacesContext.getCurrentInstance().addMessage(null, message);
}
	public String savecredit() {
		// selectedlistjournal=new TransactionCredit(); // supprimer
		Clientgestat client = serviceClient.Findbynom(codeclient);
		Ticket ticket = null;
		double totalMonatnt = 0;
		double total = 0;
		if(selectedlistjournal!=null) {	
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
		DecimalFormat df = new DecimalFormat("0.000");	
		selectedlistjournal.setFacture(null);
		// if(selectedlistjournal!=null) {
		// servicejornal.save(selectedlistjournal);
		if(chauffeur!=null)
		selectedlistjournal.setChauffeur(chauffeur);
		if(vhecule!=null)
		selectedlistjournal.setVhecule(vhecule);
		if(tel!=null)
		selectedlistjournal.setTel(tel); 
		if(client!=null)
		selectedlistjournal.setClient(client);
	
		if (numTicket != 0) {
			ticket = serviceTicket.getbyvaleur(numTicket);
		
		if (ticket != null) {
			for (Lignevente l : listelignevente)
				total = total + l.getTotalttc();
			totalMonatnt = selectedlistjournal.getMontant() + total;
			selectedlistjournal.setTicket(ticket);
			selectedlistjournal.setTotalttac(totalMonatnt);
			ticket.setVentes(listelignevente);
		} 
		
		
		}
		else 
			selectedlistjournal.setTotalttac(selectedlistjournal.getMontant());
		}
		else {
 
			selectedlistjournal=new TransactionCredit();
			if (client != null)
				selectedlistjournal.setClient(client);
			selectedlistjournal.setFacture(facture);
			selectedlistjournal.setClient(client);
			selectedlistjournal.setDate(new Date());
			  SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
			selectedlistjournal.setDates(f.format(selectedlistjournal.getDate()));			 
			selectedlistjournal.setMontant(facture.getTotalttc());
			selectedlistjournal.setTel(client.getTel());
			selectedlistjournal.setTotalttac(facture.getTotalttc());
			selectedlistjournal.setTime(selectedlistjournal.getDate().getHours()+":"+selectedlistjournal.getDate().getMinutes());
		
			if (numTicket != 0) {
			 	ticket = serviceTicket.getbyvaleur(numTicket);
			
			if (ticket != null) {
				for (Lignevente l : listelignevente)
					total = total + l.getTotalttc();
				totalMonatnt = selectedlistjournal.getMontant() + total;
				selectedlistjournal.setTicket(ticket);
				selectedlistjournal.setTotalttac(totalMonatnt);
				ticket.setVentes(listelignevente);
			}
		}
		}
		
		servicejornal.save(selectedlistjournal);		
		
		
	    PageFormat format = new PageFormat();
		Paper paper = new Paper();
		double paperWidth = 3.25;
		double paperHeight = 11.69;
		double leftMargin = 0.19;
		double rightMargin = 0.25;
		double topMargin = 0;
		double bottomMargin = 0.01;
		paper.setImageableArea(leftMargin * 72.0, topMargin * 72.0, (paperWidth - leftMargin - rightMargin) * 72.0,
				(paperHeight - topMargin - bottomMargin) * 72.0);
		format.setPaper(paper);
		PrintService[] printServices = PrinterJob.lookupPrintServices();
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		aset.add(OrientationRequested.PORTRAIT);
		aset.add(MediaSizeName.INVOICE);
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		PrintService service = null; 
		for (int index = 0; service == null && index < printServices.length; index++) {

			if (printServices[index].getName().toLowerCase().indexOf("IMPCREDIT".toLowerCase()) >= 0
					|| printServices[index].getName().toLowerCase().indexOf("impcredit".toLowerCase()) >= 0) {
				service = printServices[index];
			}
		}	 
		//System.out.println("\n\n\n service" + service.getName());
		try {
			printerJob.setPrintService(service);
		} catch (PrinterException e) {
			FacesMessage msg2 = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "");

			FacesContext.getCurrentInstance().addMessage(null, msg2);
		}		
		Printable printable = new PrintRectangle(selectedlistjournal);
		printerJob.setPrintable(printable);
		
		try {
              
			
			 if(selectedlistjournal.getFacture()==null) {
					printerJob.print(aset);
					printerJob.print(aset);
		           }
		           else printerJob.print(aset);
			 
		} catch (Exception e) {
			String codefamille = "\n\n\n" + e.getMessage() + "\n\n\n" + e.getStackTrace();
			for (PrintService printService : printServices) {
				codefamille = codefamille + printService.getName();
			}			 
		}
		
		 
		 


		 
		selectedlistjournal = null;
		date11 = null;
		chauffeur = null;
		vhecule = null;
		codeFacture=null;
     facture=null;
     listFacture=new ArrayList<Facture>();
		date12 = null;
		codeclient = null;
		selectedspompes = new ArrayList<String>();
		listjournal = new ArrayList<TransactionCredit>();
		numTicket = 0;
		listelignevente = new ArrayList<Lignevente>();
		totalquantite = 0;
		 typecredit=null;
		totalttcs = null;
		return SUCCESS;
	}

	public void getClients(AjaxBehaviorEvent event) {
		client = serviceClient.Findbynom(codeclient);
		if (client != null)
			tel = client.getTel();
	}

	public ServiceClientgestat getServiceClient() {
		return serviceClient;
	}

	public void setServiceClient(ServiceClientgestat serviceClient) {
		this.serviceClient = serviceClient;
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

	public double getTotalttc() {
		return totalttc;
	}

	public void setTotalttc(double totalttc) {
		this.totalttc = totalttc;
	}

	public double getTotalquantite() {
		return totalquantite;
	}

	public void setTotalquantite(double totalquantite) {
		this.totalquantite = totalquantite;
	}

	public Date getDate11() {
		return date11;
	}

	public void setDate11(Date date11) {
		this.date11 = date11;
	}

	public Date getDate12() {
		return date12;
	}

	public void setDate12(Date date12) {
		this.date12 = date12;
	}

	public List<String> getListpompes() {
		return listpompes;
	}

	public void setListpompes(List<String> listpompes) {
		this.listpompes = listpompes;
	}

	public List<String> getSelectedspompes() {
		return selectedspompes;
	}

	public void setSelectedspompes(List<String> selectedspompes) {
		this.selectedspompes = selectedspompes;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCodeclient() {
		return codeclient;
	}

	public void setCodeclient(String codeclient) {
		this.codeclient = codeclient;
	}

	public TransactionCredit getSelectedlistjournal() {
		return selectedlistjournal;
	}

	public void setSelectedlistjournal(TransactionCredit selectedlistjournal) {
		this.selectedlistjournal = selectedlistjournal;
	}

	public Integer getNumTicket() {
		return numTicket;
	}

	public void setNumTicket(Integer numTicket) {
		this.numTicket = numTicket;
	}

	public List<Lignevente> getListelignevente() {
		return listelignevente;
	}

	public void setListelignevente(List<Lignevente> listelignevente) {
		this.listelignevente = listelignevente;
	}

	public String getTotalttcs() {
		return totalttcs;
	}

	public void setTotalttcs(String totalttcs) {
		this.totalttcs = totalttcs;
	}

	public ServiceLignevente getServiceLignevente() {
		return serviceLignevente;
	}

	public void setServiceLignevente(ServiceLignevente serviceLignevente) {
		this.serviceLignevente = serviceLignevente;
	}

	public ServiceTicket getServiceTicket() {
		return serviceTicket;
	}

	public void setServiceTicket(ServiceTicket serviceTicket) {
		this.serviceTicket = serviceTicket;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(String chauffeur) {
		this.chauffeur = chauffeur;
	}

	public String getVhecule() {
		return vhecule;
	}

	public void setVhecule(String vhecule) {
		this.vhecule = vhecule;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCodeFacture() {
		return codeFacture;
	}

	public void setCodeFacture(String codeFacture) {
		this.codeFacture = codeFacture;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	public ServiceFacture getServiceFacture() {
		return serviceFacture;
	}

	public void setServiceFacture(ServiceFacture serviceFacture) {
		this.serviceFacture = serviceFacture;
	}

	public List<Facture> getListFacture() {
		return listFacture;
	}

	public void setListFacture(List<Facture> listFacture) {
		this.listFacture = listFacture;
	}
	public String getTypecredit() {
		return typecredit;
	}
	public void setTypecredit(String typecredit) {
		this.typecredit = typecredit;
	}
	public ServiceVhecule getServiceVhecule() {
		return serviceVhecule;
	}
	public void setServiceVhecule(ServiceVhecule serviceVhecule) {
		this.serviceVhecule = serviceVhecule;
	}
 
	public ServiceJournalDep getServiceJournalDep() {
		return serviceJournalDep;
	}
	public void setServiceJournalDep(ServiceJournalDep serviceJournalDep) {
		this.serviceJournalDep = serviceJournalDep;
	}
	public List<TransactionDepense> getListjournaldep() {
		return listjournaldep;
	}
	public void setListjournaldep(List<TransactionDepense> listjournaldep) {
		this.listjournaldep = listjournaldep;
	}
	public TransactionDepense getSelectedlistjournaldep() {
		return selectedlistjournaldep;
	}
	public void setSelectedlistjournaldep(TransactionDepense selectedlistjournaldep) {
		this.selectedlistjournaldep = selectedlistjournaldep;
	}
	public List<Vhecule> getListVehecule() {
		return listVehecule;
	}
	public void setListVehecule(List<Vhecule> listVehecule) {
		this.listVehecule = listVehecule;
	}
	public String getKilometrage() {
		return kilometrage;
	}
	public void setKilometrage(String kilometrage) {
		this.kilometrage = kilometrage;
	}
	public String getDepense() {
		return depense;
	}
	public void setDepense(String depense) {
		this.depense = depense;
	}
	public String getGroupe() {
		return groupe;
	}
	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

 
	
	

}
