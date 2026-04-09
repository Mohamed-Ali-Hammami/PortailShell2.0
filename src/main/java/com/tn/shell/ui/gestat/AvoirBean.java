package com.tn.shell.ui.gestat;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.CellEditEvent;

import com.tn.shell.model.gestat.*;
import com.tn.shell.service.gestat.*;

@ManagedBean(name = "AvoirBean")
@SessionScoped
public class AvoirBean {

	private static final String SUCCESS = "success";
	@ManagedProperty(value = "#{ServiceAvoir}")
	ServiceAvoir serviceAvoir;
	@ManagedProperty(value = "#{ServiceAvoirbanq}")
	ServiceAvoirbanq serviceAvoirbanq;
	@ManagedProperty(value = "#{ServiceSoldetpe}")
     ServiceSoldetpe serviceSoldetpe;
	@ManagedProperty(value = "#{ServiceCaisse}")
	ServiceCaisse serviceCaisse;
	@ManagedProperty(value = "#{ServiceTracegestat}")
    ServiceTracegestat servicetrace;
	@ManagedProperty(value = "#{UserService}")
	UserService userservice;
	private Date date;
	private Date date2;
	private String dates2;
	private String dates;
	private List<Avoir> listavoir;
	private List<Avoirbancaire> listavoirs;
	private List<Caisse> listpcaisses;
	private Avoir avoir;
	private Avoirbancaire avoirbanq;
	private BigDecimal montant;
	private Integer codes;
	private Soldetpe solde;
	private String soldes;
   private double litrage;
   
   
   public String saisieAvoirbanue() {
	   date = new Date();
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		montant=new BigDecimal(0);
		avoirbanq=null;
	   return SUCCESS;
   }
   
   
   public String validersaisieAvoirbanue() {
	    date = new Date();
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		solde=serviceSoldetpe.getmaxcode();
		avoirbanq=new Avoirbancaire();
		avoirbanq.setDate(date);
		avoirbanq.setMontant(montant);
		avoirbanq.setDates(dates);
		serviceAvoirbanq.save(avoirbanq);
		solde.setSolecartebancaire(solde.getSolecartebancaire().subtract(montant));
		serviceSoldetpe.update(solde);
		montant=new BigDecimal(0);
		avoirbanq=null;
	   return SUCCESS;
   }
	public String saisieAvoir() {
		date = new Date();
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		listavoir = new ArrayList<Avoir>();
		listavoir = serviceAvoir.getavoirbydates(dates);
		if (listavoir.size() == 0) {
			listavoir = new ArrayList<Avoir>();
			for (int i = 0; i < 75; i++) {
				Avoir c = new Avoir();
				c.setMontant(0);
				c.setNumero(null);
				listavoir.add(c);
			}
		}

		else {
			for (int i = listavoir.size(); i < 75; i++) {
				Avoir c = new Avoir();
				c.setMontant(0);
				c.setNumero(null);
				listavoir.add(c);
			}
		}
		return SUCCESS;
	}

	public String validersaisieavoir() {
       solde=serviceSoldetpe.getmaxcode();
		date = new Date();
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		for (Avoir d : listavoir) {
			if (serviceAvoir.getAvoirbyid(d.getId()) == null) {
				if (d.getMontant() != 0) {
					d.setDate(date);
					d.setDates(dates);
					serviceAvoir.save(d);
					solde.setSolde(solde.getSolde()-d.getMontant());
					serviceSoldetpe.update(solde);
				}
			} else {
				serviceAvoir.update(d);
				
			}
		}
		 
		 
		 
		listavoir = new ArrayList<Avoir>();
		 
		 
			listavoir = new ArrayList<Avoir>();
			for (int i = 0; i < 75; i++) {
				Avoir c = new Avoir();
				c.setMontant(0);
				c.setNumero(null);
				listavoir.add(c);
			} 
			
			Utilisateur user=userservice.getCurrentUser();
			Tracegestat t=new Tracegestat();
			t.setAction("saisie des  avaoir du"+dates+  " par "+user.getNom());
			t.setDate(new Date());
			t.setUtilisateur(user);
			servicetrace.save(t);
		return SUCCESS;
	}

	public String etatcarteshell() {
		date = new Date();
		date2 = new Date();
		solde = serviceSoldetpe.getmaxcode();
		listpcaisses = new ArrayList<Caisse>();
		listavoir = new ArrayList<Avoir>();
		return SUCCESS;
	}

	
	public String etatcartebancaires() {
		date = new Date();
		date2 = new Date();
		solde = serviceSoldetpe.getmaxcode();
		listpcaisses = new ArrayList<Caisse>();
		listavoirs = new ArrayList<Avoirbancaire>();
		return SUCCESS;
	}
	
	public void getavorirbancairebydate(AjaxBehaviorEvent event) {
		listpcaisses = new ArrayList<Caisse>();
		 
		solde = serviceSoldetpe.getmaxcode();
		date2.setHours(23);
		listpcaisses=serviceCaisse.getbetwendates(date, date2);
		 
 
		listavoirs = new ArrayList<Avoirbancaire>();
		 

		listavoirs=serviceAvoirbanq.getBETWENNDATES(date, date2);
		 
	}

	public void getavorirbydate(AjaxBehaviorEvent event) {
		listpcaisses = new ArrayList<Caisse>();
		List<Caisse> l = new ArrayList<Caisse>();
		double s1 = 0;
		double s2 = 0;
		solde = serviceSoldetpe.getmaxcode();
		date2.setHours(23);
						listpcaisses=serviceCaisse.getbetwendates(date, date2);
						 
		listavoir = new ArrayList<Avoir>();
		listavoir=serviceAvoir.getBETWENNDATES(date, date2);
		 
	}

	public void onCellEdit(CellEditEvent event) {
		avoir = listavoir.get(event.getRowIndex());
		codes = event.getRowIndex();

	}

	/*
	 * getter and setter
	 **/
	public String getDates() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		return dates;
	}

	public String getDates2() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates2 = s.format(date2);
		return dates;
	}

	public ServiceAvoir getServiceAvoir() {
		return serviceAvoir;
	}

	public void setServiceAvoir(ServiceAvoir serviceAvoir) {
		this.serviceAvoir = serviceAvoir;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public void setDates2(String dates2) {
		this.dates2 = dates2;
	}

	public List<Avoir> getListavoir() {
		return listavoir;
	}

	public void setListavoir(List<Avoir> listavoir) {
		this.listavoir = listavoir;
	}

	public Avoir getAvoir() {
		return avoir;
	}

	public void setAvoir(Avoir avoir) {
		this.avoir = avoir;
	}

	public Integer getCodes() {
		return codes;
	}

	public void setCodes(Integer codes) {
		this.codes = codes;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	 

	public Soldetpe getSolde() {
		return solde;
	}

	public void setSolde(Soldetpe solde) {
		this.solde = solde;
	}

	public String getSoldes() {
		DecimalFormat df = new DecimalFormat("0.000");
		soldes = df.format(solde);

		return soldes;
	}

	public void setSoldes(String soldes) {
		this.soldes = soldes;
	}

	public ServiceCaisse getServiceCaisse() {
		return serviceCaisse;
	}

	public void setServiceCaisse(ServiceCaisse serviceCaisse) {
		this.serviceCaisse = serviceCaisse;
	}

	public List<Caisse> getListpcaisses() {
		return listpcaisses;
	}

	public void setListpcaisses(List<Caisse> listpcaisses) {
		this.listpcaisses = listpcaisses;
	}

	public ServiceSoldetpe getServiceSoldetpe() {
		return serviceSoldetpe;
	}

	public void setServiceSoldetpe(ServiceSoldetpe serviceSoldetpe) {
		this.serviceSoldetpe = serviceSoldetpe;
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

	public double getLitrage() {
		return litrage;
	}

	public void setLitrage(double litrage) {
		this.litrage = litrage;
	}
	public List<Avoirbancaire> getListavoirs() {
		return listavoirs;
	}
	public void setListavoirs(List<Avoirbancaire> listavoirs) {
		this.listavoirs = listavoirs;
	}
	public Avoirbancaire getAvoirbanq() {
		return avoirbanq;
	}
	public void setAvoirbanq(Avoirbancaire avoirbanq) {
		this.avoirbanq = avoirbanq;
	}
	public BigDecimal getMontant() {
		return montant;
	}
	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}


	public ServiceAvoirbanq getServiceAvoirbanq() {
		return serviceAvoirbanq;
	}


	public void setServiceAvoirbanq(ServiceAvoirbanq serviceAvoirbanq) {
		this.serviceAvoirbanq = serviceAvoirbanq;
	}
	
	

}
