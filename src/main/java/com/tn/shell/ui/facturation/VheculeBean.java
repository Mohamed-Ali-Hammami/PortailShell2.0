package com.tn.shell.ui.facturation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.dao.DataAccessException;

import com.tn.shell.model.transport.Paramettrevehicule;
import com.tn.shell.model.transport.Statut;
import com.tn.shell.model.transport.Vhecule;
import com.tn.shell.service.transport.ServiceTracetransport;
import com.tn.shell.service.transport.ServiceVhecule;

 
 

@ManagedBean(name = "VheculeBean")
@SessionScoped
public class VheculeBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	 
	@ManagedProperty(value = "#{ServiceTracetransport}")
	ServiceTracetransport serviceTrace;
	@ManagedProperty(value = "#{ServiceVhecule}")
	ServiceVhecule serviceVhecule;
    private List<Vhecule> filtervhecules;
    private String matricule;
	private String numserie; 
	private Date date1;
	private Date date2;
	private Date date3;
	private String dates1;
	private String dates2;
	private String dates3;
	private Vhecule vhecule;
	private Vhecule vhecule2;
	private List<Vhecule> listvhecule;
    public String succes() {
    	if(vhecule2.getParamettrevehicule()!=null) {
    	date1=vhecule2.getParamettrevehicule().getProchainedateass();
    			date2=vhecule2.getParamettrevehicule().getProchainedatequittance();
    					date3=vhecule2.getParamettrevehicule().getProchainedatevisite();
    	}
	return SUCCESS;
     }
    
    public String modifierDate() {
    	if(vhecule2.getParamettrevehicule()!=null) {
    	vhecule2.getParamettrevehicule().setProchainedateass(date1);
    	vhecule2.getParamettrevehicule().setProchainedatequittance(date2);
    	vhecule2.getParamettrevehicule().setProchainedatevisite(date3);
    	serviceVhecule.update(vhecule2);
    	}else {
    		Paramettrevehicule p=new Paramettrevehicule();
    		p.setProchainedateass(date1);
    		p.setProchainedatequittance(date2);
    		p.setProchainedatevisite(date3);
    		vhecule2.setParamettrevehicule(p);
    		serviceVhecule.update(vhecule2);
    	}
    	
    	listvhecule=new ArrayList<Vhecule>();
		listvhecule = serviceVhecule.getAll();date2=null;date1=null;date3=null;
    	return SUCCESS;
    }
	public String getvhecule() {
		listvhecule=new ArrayList<Vhecule>();
		listvhecule = serviceVhecule.getAll();date2=null;date1=null;date3=null;
		return SUCCESS;
	}
public String nouvauvhecule(){
	matricule=null;
	numserie=null;
	
	
	return SUCCESS;
}
	public String savevhecule() {
		vhecule = serviceVhecule.Findbymf(matricule);
		if (vhecule == null) {			 
			vhecule = new Vhecule();
			vhecule.setMatricule(matricule);	
			vhecule.setNumserie(numserie);
			serviceVhecule.save(vhecule);
			listvhecule = new ArrayList<Vhecule>();
			listvhecule = serviceVhecule.getAll();
				 
			return SUCCESS;
		} else {
			String message = "Vehicule existe";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(message));

			return ERROR;

		}
	}

	public String updateVhecule(Vhecule vhecule) {
		try {
			getServiceVhecule().update(vhecule);
			return SUCCESS;
		} catch (DataAccessException e) {
		}
		return ERROR;
	}

	public void onRowEdit(RowEditEvent event) {

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Vhecule change", ((Vhecule) event.getObject()).getMatricule());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		updateVhecule((Vhecule) event.getObject());

	}

	public String deletevhecule() {
		vhecule2.setStatut(Statut.DEACTIF);
		
		 
		serviceVhecule.delete(vhecule2);
		listvhecule = new ArrayList<Vhecule>();
		listvhecule = serviceVhecule.getAll();
		return SUCCESS;
	}

	 

	public ServiceTracetransport getServiceTrace() {
		return serviceTrace;
	}

	public void setServiceTrace(ServiceTracetransport serviceTrace) {
		this.serviceTrace = serviceTrace;
	}
	public ServiceVhecule getServiceVhecule() {
		return serviceVhecule;
	}
	public void setServiceVhecule(ServiceVhecule serviceVhecule) {
		this.serviceVhecule = serviceVhecule;
	}
	public List<Vhecule> getFiltervhecules() {
		return filtervhecules;
	}
	public void setFiltervhecules(List<Vhecule> filtervhecules) {
		this.filtervhecules = filtervhecules;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getNumserie() {
		return numserie;
	}
	public void setNumserie(String numserie) {
		this.numserie = numserie;
	}
	public Vhecule getVhecule() {
		return vhecule;
	}
	public void setVhecule(Vhecule vhecule) {
		this.vhecule = vhecule;
	}
	public Vhecule getVhecule2() {
		return vhecule2;
	}
	public void setVhecule2(Vhecule vhecule2) {
		this.vhecule2 = vhecule2;
	}
	public List<Vhecule> getListvhecule() {
		return listvhecule;
	}
	public void setListvhecule(List<Vhecule> listvhecule) {
		this.listvhecule = listvhecule;
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
	public Date getDate3() {
		return date3;
	}
	public void setDate3(Date date3) {
		this.date3 = date3;
	}
	public String getDates1() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		if(date1!=null)return s.format(date1);
		else return ""+dates1;
		
	}
	public void setDates1(String dates1) {
		this.dates1 = dates1;
	}
	public String getDates2() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		if(date2!=null)return s.format(date2);
		else return ""+dates2;
		 
	}
	public void setDates2(String dates2) {
		this.dates2 = dates2;
	}
	public String getDates3() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		if(date3!=null)return s.format(date3);
		else return ""+dates3;
		 
	}
	public void setDates3(String dates3) {
		this.dates3 = dates3;
	}

	

	

	
	 

}
