package com.tn.shell.ui.gestat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import com.tn.shell.model.gestat.Caisse;
import com.tn.shell.model.gestat.Clientgestat;
import com.tn.shell.model.gestat.Clientpassation;
import com.tn.shell.model.gestat.Credit;
import com.tn.shell.model.gestat.Creditanterieur;
import com.tn.shell.model.gestat.Creditpassation;
import com.tn.shell.model.gestat.Historiquepayement;
import com.tn.shell.model.gestat.Poste;
import com.tn.shell.model.gestat.Retourcuve;
import com.tn.shell.model.gestat.Statut;
import com.tn.shell.model.paie.Pointage;
import com.tn.shell.service.gestat.ServiceAchatcaisse;
import com.tn.shell.service.gestat.ServiceArticleCarburant;
import com.tn.shell.service.gestat.ServiceAvancegestat;
import com.tn.shell.service.gestat.ServiceCaisse;
import com.tn.shell.service.gestat.ServiceCheque;
import com.tn.shell.service.gestat.ServiceClientgestat;
import com.tn.shell.service.gestat.ServiceClientpassation;
import com.tn.shell.service.gestat.ServiceCreditanterieur;
import com.tn.shell.service.gestat.ServiceCreditclient;
import com.tn.shell.service.gestat.ServiceCreditpassation;
import com.tn.shell.service.gestat.ServiceDepensegestat;
import com.tn.shell.service.gestat.ServiceFamilleDepensegestat;
import com.tn.shell.service.gestat.ServiceHistorique;
import com.tn.shell.service.gestat.ServiceLigneindex;
import com.tn.shell.service.gestat.ServicePompe;
import com.tn.shell.service.gestat.ServicePompiste;
import com.tn.shell.service.gestat.ServiceRetourcuve;
import com.tn.shell.service.gestat.ServiceSoldetpe;
import com.tn.shell.service.gestat.ServiceTracegestat;
import com.tn.shell.service.gestat.UserService;
 
import com.tn.shell.service.paie.ServiceEmployee;
import com.tn.shell.service.shop.ServiceProduit;
import com.tn.shell.service.transport.ServiceDepense;
import com.tn.shell.service.transport.ServiceFamilleDepense;
import com.tn.shell.service.transport.ServiceVhecule;

@ManagedBean(name = "CreditPassationBean")
@SessionScoped
public class CreditPassationBean {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{ServiceCaisse}")
	ServiceCaisse serviceCaisse;
	@ManagedProperty(value = "#{ServiceHistorique}")
	ServiceHistorique servicehistorique;

	@ManagedProperty(value = "#{ServiceTracegestat}")
	ServiceTracegestat servicetrace;
	@ManagedProperty(value = "#{UserService}")
	UserService userservice;

	@ManagedProperty(value = "#{ServiceCreditanterieur}")
	ServiceCreditanterieur serviceCreditanterieur;
	@ManagedProperty(value = "#{ServiceCreditclient}")
	ServiceCreditclient serviceCreditclient;

	@ManagedProperty(value = "#{ServiceClientgestat}")
	ServiceClientgestat serviceClient;

	@ManagedProperty(value = "#{ServiceCreditpassation}")
	ServiceCreditpassation servicecreditpassation;

	@ManagedProperty(value = "#{ServiceClientpassation}")
	ServiceClientpassation serviceclientpassation;
	private Caisse caisse;
	private Date date;
	private String dates;
	private Poste poste;
	private Clientpassation clientpassation;

	private Poste[] postes;
	private List<Credit> listCredit;
	private List<Creditpassation> listcreditpassation;
	private Integer codes;
	private String nom;
	private String tel;
	private double montant;
	private double avance;
	private Creditpassation selectioncreditpasstion;
    private List<Creditanterieur>listCreditan;
    private List<Creditpassation>selectdeCredit;
	private List<Clientpassation> listclientpassation;
	private List<Clientpassation> listclientpassations;
    private String total;
    private String totalc;
    private List<Historiquepayement> listcreditanteirur;
	public String accesPassation() {
		nom = null;
		codes = 0;
		avance = 0;
		caisse = serviceCaisse.getmaxcode();
		if(caisse!=null) 
		date = caisse.getDate();
		else date=new Date();
		postes = Poste.values();
		//poste = caisse.getPoste();
		listcreditpassation=new ArrayList<Creditpassation>();
		listclientpassation = new ArrayList<Clientpassation>();
		listclientpassations = new ArrayList<Clientpassation>();
		listclientpassations = serviceclientpassation.finByReste();
		double total=0;
		if(listclientpassations!=null)
        for(Clientpassation c:listclientpassations) {
        	total=total+c.getReste();
        }
        DecimalFormat df=new DecimalFormat("#,###.000");
        this.total=df.format(total);
		montant = 0;
		String dates="";
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		this.dates=f.format(new Date());
		dates=f.format(date);
		listcreditanteirur=new ArrayList<Historiquepayement>();
		listcreditanteirur=servicehistorique.getListcreditdate(dates);
		  total=0;
		  if(listcreditanteirur!=null)
		for(Historiquepayement h:listcreditanteirur)
			total=total+h.getMontant();
        this.totalc=df.format(total);
		
      return  SUCCESS;
	}

	public String payer() {
         caisse = serviceCaisse.getmaxcode();		
		 date = caisse.getDate();
		 montant = selectioncreditpasstion.getMontant();
		 listCreditan = new ArrayList<Creditanterieur>();
		Clientgestat c = serviceClient.Findbycode(44);
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		dates = f.format(date);
		listCreditan = serviceCreditanterieur.getCreditanterieurbyclient(c, dates);
		selectdeCredit=null;
		return SUCCESS;

	}
	
	public String payementMutiple() {
		montant=0;
		if(selectdeCredit.size()>0) {
			for(Creditpassation c:selectdeCredit) {
				montant=montant+c.getMontant();
			}
		}
		
		 caisse = serviceCaisse.getmaxcode();		
			date = caisse.getDate();
		 listCreditan = new ArrayList<Creditanterieur>();
			Clientgestat c = serviceClient.Findbycode(44);
			SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
			dates = f.format(date);
			listCreditan = serviceCreditanterieur.getCreditanterieurbyclient(c, dates);
			selectioncreditpasstion=null;
		return SUCCESS;
	}
 
	public String validerpayer() {
		
		if(selectioncreditpasstion!=null) {
			Historiquepayement h = new Historiquepayement();
			h.setCreditpassation(selectioncreditpasstion);
			SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
			h.setDates(f.format(date));
	 if(montant==avance) {
		h.setMontant(montant);
		h.setUtilisateur(userservice.getCurrentUser());
		servicehistorique.save(h);
		selectioncreditpasstion.setMontant(selectioncreditpasstion.getMontant()-montant);
		if (selectioncreditpasstion.getMontant() == 0) {
			selectioncreditpasstion.setStatut(Statut.DEACTIF);
			servicecreditpassation.delete(selectioncreditpasstion);
		}
		else {
			servicecreditpassation.update(selectioncreditpasstion);
		}
		clientpassation.setReste(clientpassation.getReste() - montant);
		serviceclientpassation.update(clientpassation);
		listcreditpassation = new ArrayList<Creditpassation>();
		listcreditpassation = servicecreditpassation.findbyClient(clientpassation);
 
		montant = 0;
		return SUCCESS;
	 }
		}
		if(selectdeCredit!=null) {
			if(montant==avance) {
				
			for (Creditpassation p:selectdeCredit) {
				Historiquepayement h = new Historiquepayement();
				h.setCreditpassation(p);
				SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
				h.setDates(f.format(date));
						h.setMontant(p.getMontant());
						h.setUtilisateur(userservice.getCurrentUser());
						servicehistorique.save(h);
						p.setMontant(0);
						if (p.getMontant() == 0) {
							p.setStatut(Statut.DEACTIF);
							servicecreditpassation.delete(p);
						}
						 
						
					 }
			
			clientpassation.setReste(clientpassation.getReste() - montant);
			serviceclientpassation.update(clientpassation);
			listcreditpassation = new ArrayList<Creditpassation>();
			listcreditpassation = servicecreditpassation.findbyClient(clientpassation);
	 
			montant = 0;
			
			return SUCCESS;
			}
		}
	 
		return ERROR;
	}

	public String suivant() {

		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		if (clientpassation == null) {
			clientpassation = new Clientpassation();
			clientpassation.setNom(nom);
			clientpassation.setTel(tel);
			
			clientpassation.setDernierdates(selectioncreditpasstion.getDates());
			clientpassation.setReste(selectioncreditpasstion.getMontant());
			serviceclientpassation.save(clientpassation);

		}

		else {
			
			if (  clientpassation.getAvance() > 0
					&& selectioncreditpasstion.getMontant() < clientpassation.getAvance()) {
				clientpassation.setTel(tel);
				clientpassation.setAvance(clientpassation.getAvance() - selectioncreditpasstion.getMontant());
				clientpassation.setReste(clientpassation.getReste() -selectioncreditpasstion.getMontant());
				clientpassation.setDernierdates(selectioncreditpasstion.getDates());
				serviceclientpassation.update(clientpassation);
			}  
			else {
				clientpassation.setTel(tel);
			clientpassation.setDernierdates(selectioncreditpasstion.getDates());
			clientpassation.setReste(clientpassation.getReste() + selectioncreditpasstion.getMontant());
			serviceclientpassation.update(clientpassation);
			}
		}

		
			 												
			selectioncreditpasstion.setClientpassation(clientpassation);
			servicecreditpassation.save(selectioncreditpasstion);
			listcreditpassation.remove(selectioncreditpasstion);
			selectioncreditpasstion = null;
		

			listclientpassations = new ArrayList<Clientpassation>();
			listclientpassations = serviceclientpassation.finByReste();
			double total=0;
	        for(Clientpassation c:listclientpassations) {
	        	total=total+c.getReste();
	        }
	        DecimalFormat df=new DecimalFormat("#,###.000");
	        this.total=df.format(total);
			nom = null;
			tel = null;
			clientpassation = null;
		 
		return SUCCESS;
	}

	public String visualise2() {
		listcreditpassation = new ArrayList<Creditpassation>();
		listcreditpassation = servicecreditpassation.findbyClient(clientpassation);
		return SUCCESS;
	}

	public String visualise() {
		listclientpassation = new ArrayList<Clientpassation>();
		listclientpassation = serviceclientpassation.getAll();
		tel =selectioncreditpasstion.getCredit().getTels();
		return SUCCESS;
	}

	public void getclientpasationByname(AjaxBehaviorEvent event) {
		clientpassation = serviceclientpassation.Findbynom(nom);
		if(clientpassation!=null) {
		tel = clientpassation.getTel();
		avance=clientpassation.getAvance();
		}
		 
		tel =selectioncreditpasstion.getCredit().getTels();
	}

	public String getCreditPassationByCaisseanddates() {
		listCredit = new ArrayList<Credit>();
		Clientgestat c = serviceClient.Findbycode(44);
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		dates = f.format(date);
		listCredit = serviceCreditclient.getListcreditdateandclient(dates, c);
		listcreditpassation = new ArrayList<Creditpassation>();
		for (Credit cl : listCredit) {
			Creditpassation crp = servicecreditpassation.findbyCredit(cl);
			if (crp == null) {
				crp = new Creditpassation();
				Clientpassation cp = new Clientpassation();
				cp.setNom("");
				cp.setTel(cl.getTels());
				crp.setNumBon(cl.getNumbon());
				crp.setDates(cl.getDates());
				crp.setPoste(cl.getCaisse().getPoste());
				crp.setMontant(cl.getMontant());
				crp.setClientpassation(cp);
				crp.setClientpassation(null);
				crp.setCredit(cl);
				listcreditpassation.add(crp);
			}
		}

		listclientpassations = new ArrayList<Clientpassation>();
		listclientpassations = serviceclientpassation.finByReste();
		
		listclientpassation = new ArrayList<Clientpassation>();
		listclientpassation = serviceclientpassation.getAll();
		
		double total=0;
        for(Clientpassation cc:listclientpassations) {
        	total=total+cc.getReste();
        }
        DecimalFormat df=new DecimalFormat("#,###.000");
        this.total=df.format(total);
		return SUCCESS;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Creditpassation getSelectioncreditpasstion() {
		return selectioncreditpasstion;
	}

	public void setSelectioncreditpasstion(Creditpassation selectioncreditpasstion) {
		this.selectioncreditpasstion = selectioncreditpasstion;
	}

	public List<Clientpassation> getListclientpassation() {
		return listclientpassation;
	}

	public void setListclientpassation(List<Clientpassation> listclientpassation) {
		this.listclientpassation = listclientpassation;
	}

	public ServiceCaisse getServiceCaisse() {
		return serviceCaisse;
	}

	public void setServiceCaisse(ServiceCaisse serviceCaisse) {
		this.serviceCaisse = serviceCaisse;
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

	public ServiceCreditanterieur getServiceCreditanterieur() {
		return serviceCreditanterieur;
	}

	public void setServiceCreditanterieur(ServiceCreditanterieur serviceCreditanterieur) {
		this.serviceCreditanterieur = serviceCreditanterieur;
	}

	public ServiceCreditclient getServiceCreditclient() {
		return serviceCreditclient;
	}

	public void setServiceCreditclient(ServiceCreditclient serviceCreditclient) {
		this.serviceCreditclient = serviceCreditclient;
	}

	public ServiceClientgestat getServiceClient() {
		return serviceClient;
	}

	public void setServiceClient(ServiceClientgestat serviceClient) {
		this.serviceClient = serviceClient;
	}

	public ServiceCreditpassation getServicecreditpassation() {
		return servicecreditpassation;
	}

	public void setServicecreditpassation(ServiceCreditpassation servicecreditpassation) {
		this.servicecreditpassation = servicecreditpassation;
	}

	public ServiceClientpassation getServiceclientpassation() {
		return serviceclientpassation;
	}

	public void setServiceclientpassation(ServiceClientpassation serviceclientpassation) {
		this.serviceclientpassation = serviceclientpassation;
	}

	public Caisse getCaisse() {
		return caisse;
	}

	public void setCaisse(Caisse caisse) {
		this.caisse = caisse;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
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

	public List<Credit> getListCredit() {
		return listCredit;
	}

	public void setListCredit(List<Credit> listCredit) {
		this.listCredit = listCredit;
	}

	public List<Creditpassation> getListcreditpassation() {
		return listcreditpassation;
	}

	public void setListcreditpassation(List<Creditpassation> listcreditpassation) {
		this.listcreditpassation = listcreditpassation;
	}

	public Integer getCodes() {
		return codes;
	}

	public void setCodes(Integer codes) {
		this.codes = codes;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Clientpassation getClientpassation() {
		return clientpassation;
	}

	public void setClientpassation(Clientpassation clientpassation) {
		this.clientpassation = clientpassation;
	}

	public List<Clientpassation> getListclientpassations() {
		return listclientpassations;
	}

	public void setListclientpassations(List<Clientpassation> listclientpassations) {
		this.listclientpassations = listclientpassations;
	}

	public ServiceHistorique getServicehistorique() {
		return servicehistorique;
	}

	public void setServicehistorique(ServiceHistorique servicehistorique) {
		this.servicehistorique = servicehistorique;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public double getAvance() {
		return avance;
	}

	public void setAvance(double avance) {
		this.avance = avance;
	}

	public List<Creditanterieur> getListCreditan() {
		return listCreditan;
	}

	public void setListCreditan(List<Creditanterieur> listCreditan) {
		this.listCreditan = listCreditan;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<Historiquepayement> getListcreditanteirur() {
		return listcreditanteirur;
	}

	public void setListcreditanteirur(List<Historiquepayement> listcreditanteirur) {
		this.listcreditanteirur = listcreditanteirur;
	}

	public String getTotalc() {
		return totalc;
	}

	public void setTotalc(String totalc) {
		this.totalc = totalc;
	}

	public List<Creditpassation> getSelectdeCredit() {
		return selectdeCredit;
	}

	public void setSelectdeCredit(List<Creditpassation> selectdeCredit) {
		this.selectdeCredit = selectdeCredit;
	}
	
	

}
