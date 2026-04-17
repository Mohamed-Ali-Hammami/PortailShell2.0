package com.tn.shell.ui.shop;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.RowEditEvent;
import org.springframework.dao.DataAccessException;

 
import com.tn.shell.model.gestat.*;
import com.tn.shell.model.shop.TypePayement;
import com.tn.shell.model.shop.*;
import com.tn.shell.model.shop.Status;
import com.tn.shell.service.gestat.*;
import com.tn.shell.service.shop.*;

 

@ManagedBean(name = "FactureAchatBean")
@SessionScoped
public class FactureAchatshopBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	@ManagedProperty(value = "#{ServiceFactureAchat}")	 
	ServiceFactureAchat serviceFactureAchat;
	 
	
	@ManagedProperty(value = "#{ServiceAchat}")
	ServiceAchat serviceAchat;
	@ManagedProperty(value = "#{UserService}")
	UserService userService;	
	@ManagedProperty(value = "#{ServiceTraceshop}")
	ServiceTracegestat serviceTrace;
	@ManagedProperty(value = "#{ServiceFournisseur}")
	ServiceFournisseur serviceFournisseur;
	private List<Factureachat> listfactureachat ;
	private List<Factureachat> selectedFactures;
	 
	private Factureachat selectedFacture;
	private List<Factureachat> listfactureAchat;
	private List<Factureachat> filterfactureachat;
	private String typepayement;
	private boolean payes1;
	private String banque;
	private List<String> listBanque;
	private TypePayement[] typepayements;
	private String numchek;
	@PostConstruct
	public void init() {
		listBanque=new ArrayList<String>();
		listBanque.add("AMEN BANQUE");
		listBanque.add("ATB");
		listBanque.add("ATTIJARI BANQUE");
		listBanque.add("BH");
		listBanque.add("BIAT");
		listBanque.add("BFI");
		listBanque.add("BNA");
		listBanque.add("BT");
		listBanque.add("BTK");
		listBanque.add("BTS");
		listBanque.add("STB");
		listBanque.add("STUCID");
		listBanque.add("UBCI");
		listBanque.add("UIB");
		listBanque.add("ZITOUNA BANQUE");
		typepayements = TypePayement.values(); 
		
	}
	
	public String getcheque() { 
		
		return SUCCESS;
		 
	}
	private Date datepayement;
	public String visualise() {		 
			selectedFacture.setListarticle(serviceAchat.getArticlebyfacture(selectedFacture)); 
		   
		return SUCCESS;
	}
	 public String payer(){
		  selectedFacture.setStatus(Status.Payee);		 		 
			Tracegestat t=new Tracegestat();
		 	Utilisateur user = userService.getCurrentUser();
		 	t.setAction("Payement de  la facture     "+selectedFacture.getCode() );
		 	t.setDate(new Date());
		     t.setUtilisateur(user);		 	 		
		     serviceTrace.save(t);	      
			selectedFacture.setBanque(banque);		
			selectedFacture.setNumerocheck(numchek);
			selectedFacture.setTypepayement(TypePayement.valueOf(typepayement));
			selectedFacture.setDatepayement(datepayement);
			serviceFactureAchat.update(selectedFacture);
			 
			numchek=null;
			banque=null;
			typepayement=null;
			listfactureachat =new ArrayList<Factureachat>();				  		 
			  listfactureachat=serviceFactureAchat.getAll();
			   
		 return SUCCESS;
	 }
	public String getmontantrendu() {		  
		Tracegestat t=new Tracegestat();
	 	Utilisateur user = userService.getCurrentUser();
	 	t.setAction("Payement de la facture     "+selectedFacture.getCode() );
	 	t.setDate(new Date());
	    t.setUtilisateur(user);		 	 		
	  //  serviceTrace.save(t);     		     
		selectedFacture.setStatus(Status.Payee);		 
		serviceFactureAchat.update(selectedFacture);		 
		return SUCCESS;
}

		public String updateFacture(Factureachat factureachat) {
	        try {
	            getServiceFactureAchat().update(factureachat);
	            return SUCCESS;       
	        } catch (DataAccessException e) {
	        }    
	        return ERROR;
	    } 
		private String four;
		public void onRowEdit(RowEditEvent event){	
		 
				FacesMessage msg=new FacesMessage(FacesMessage.SEVERITY_INFO,"change",((Factureachat)event.getObject()).getCode());
			     FacesContext.getCurrentInstance().addMessage(null, msg);
			     Fournisseur f=serviceFournisseur.getbyname(four);
			  
			     updateFacture((Factureachat)event.getObject());
			     four=null;
			 
		}
	 
	 
	 public String supprimer(){
		 List<Achat> liste=new ArrayList<Achat>();
		 liste=this.getarticlebyfacture(selectedFacture);
		 
		 Tracegestat t=new Tracegestat();
		 	Utilisateur user = userService.getCurrentUser();
		 	t.setAction("Supression du facture d achat   "+selectedFacture.getCode() );
		 	t.setDate(new Date());
		     t.setUtilisateur(user);		 	 		
		   //  serviceTrace.save(t);
	  
		  serviceFactureAchat.delete(selectedFacture);
		  listfactureachat =new ArrayList<Factureachat>();				  		 
		  listfactureachat=serviceFactureAchat.getAll();
		 return SUCCESS;
	 }
	 private List<String> listfournisseur = new ArrayList<String>();
	public String getAllfacture(){
		listfournisseur = new ArrayList<String>();
		 listfactureachat =new ArrayList<Factureachat>();				  		 
		 listfactureachat=serviceFactureAchat.getAll();	
		 List<Fournisseur> l2 = new ArrayList<Fournisseur>();
		 l2=serviceFournisseur.getAll();
			if(l2!=null){
			for (Fournisseur four : l2) {
				listfournisseur.add(four.getNom());
			}
			}	
		 
		return SUCCESS;
	}
	private List<Achat> getarticlebyfacture(Factureachat f){
		 List<Achat> listAchat =new ArrayList<Achat>();
		 listAchat=serviceAchat.getAll(); 		 
		List<Achat> s=new ArrayList<Achat>();
		if(listAchat.size()>0){
			for(Achat a:listAchat){
				if(a.getFactureachat() !=null && a.getFactureachat().getCode().equals(f.getCode())){
				s.add(a);
				 
			}
			 
		}}
		return s;
	}
	
	public ServiceFactureAchat getServiceFactureAchat() {
		return serviceFactureAchat;
	}
	public void setServiceFactureAchat(ServiceFactureAchat serviceFactureAchat) {
		this.serviceFactureAchat = serviceFactureAchat;
	}

	public List<Factureachat> getListfactureachat() {
		return listfactureachat;
	}

	public void setListfactureachat(List<Factureachat> listfactureachat) {
		this.listfactureachat = listfactureachat;
	}

	public List<Factureachat> getSelectedFactures() {
		return selectedFactures;
	}

	public void setSelectedFactures(List<Factureachat> selectedFactures) {
		this.selectedFactures = selectedFactures;
	}

 
	public Factureachat getSelectedFacture() {
		return selectedFacture;
	}
	public void setSelectedFacture(Factureachat selectedFacture) {
		this.selectedFacture = selectedFacture;
	}

	public ServiceAchat getServiceAchat() {
		return serviceAchat;
	}

	public void setServiceAchat(ServiceAchat serviceAchat) {
		this.serviceAchat = serviceAchat;
	}

	public ServiceFournisseur getServiceFournisseur() {
		return serviceFournisseur;
	}

	public void setServiceFournisseur(ServiceFournisseur serviceFournisseur) {
		this.serviceFournisseur = serviceFournisseur;
	}


	public String getFour() {
		return four;
	}


	public void setFour(String four) {
		this.four = four;
	}


	public List<String> getListfournisseur() {
		return listfournisseur;
	}


	public void setListfournisseur(List<String> listfournisseur) {
		this.listfournisseur = listfournisseur;
	}


	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	 

	public String getTypepayement() {
		return typepayement;
	}

	public void setTypepayement(String typepayement) {
		this.typepayement = typepayement;
	}

	public boolean isPayes1() {
		return payes1;
	}

	public void setPayes1(boolean payes1) {
		this.payes1 = payes1;
	}

	public String getBanque() {
		return banque;
	}

	public void setBanque(String banque) {
		this.banque = banque;
	}

	public List<String> getListBanque() {
		return listBanque;
	}

	public void setListBanque(List<String> listBanque) {
		this.listBanque = listBanque;
	}

	public TypePayement[] getTypepayements() {
		return typepayements;
	}

	public void setTypepayements(TypePayement[] typepayements) {
		this.typepayements = typepayements;
	}

	public String getNumchek() {
		return numchek;
	}

	public void setNumchek(String numchek) {
		this.numchek = numchek;
	}

	public Date getDatepayement() {
		datepayement=new Date();
		return datepayement;
	}

	public void setDatepayement(Date datepayement) {
		this.datepayement = datepayement;
	}

	public List<Factureachat> getListfactureAchat() {
		return listfactureAchat;
	}

	public void setListfactureAchat(List<Factureachat> listfactureAchat) {
		this.listfactureAchat = listfactureAchat;
	}

	public List<Factureachat> getFilterfactureachat() {
		return filterfactureachat;
	}

	public void setFilterfactureachat(List<Factureachat> filterfactureachat) {
		this.filterfactureachat = filterfactureachat;
	}

	public ServiceTracegestat getServiceTrace() {
		return serviceTrace;
	}

	public void setServiceTrace(ServiceTracegestat serviceTrace) {
		this.serviceTrace = serviceTrace;
	}
	
	
	
}
