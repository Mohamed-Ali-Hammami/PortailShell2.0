package com.tn.shell.ui.gestat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.tn.shell.model.gestat.Articlecarburant;
import com.tn.shell.model.gestat.Tracegestat;
import com.tn.shell.model.gestat.Utilisateur;
import com.tn.shell.service.gestat.*;
 

@ManagedBean(name = "ArticleBean")
@SessionScoped
public class ArticleBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{ServiceArticleCarburant}")
	ServiceArticleCarburant serviceArticleCarburant;
    
	@ManagedProperty(value = "#{ServiceTracegestat}")
    ServiceTracegestat servicetrace;
	@ManagedProperty(value = "#{UserService}")
	UserService userservice;
	private List<Articlecarburant> listArticlecarburant;
	private Articlecarburant articlecarburant;
    private double total;
    private String totals;
    private Integer codes;
    private Date date;
    private String dates;
 
    
    public void onCellEdit(CellEditEvent event) {
		articlecarburant = listArticlecarburant.get(event.getRowIndex());
		codes = event.getRowIndex();

	}
    
     
    public void updatemontant(AjaxBehaviorEvent event) {
		total = 0;
		articlecarburant.setMontant(articlecarburant.getQte()-articlecarburant.getQuantite());
		articlecarburant.setValeur(articlecarburant.getMontant()*articlecarburant.getVente());
		articlecarburant.setQuantite(articlecarburant.getQte());
		serviceArticleCarburant.update(articlecarburant);
		for (Articlecarburant d : listArticlecarburant)
			total = total +  (d.getValeur());

	}
    public String regulationinventaire() {
    	date=new Date();
    	listArticlecarburant = new ArrayList<Articlecarburant>();
		listArticlecarburant = serviceArticleCarburant.getAll();
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		dates=f.format(date);
    	return SUCCESS;
    }

	public String validerregulationinventaire() {
		if (listArticlecarburant != null) {
			for (Articlecarburant article : listArticlecarburant) {
				serviceArticleCarburant.update(article);
			}
		}
		return SUCCESS;
	}
    public void onRowEdit(RowEditEvent event) {

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"client change", ((Articlecarburant) event.getObject()).getNom());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		serviceArticleCarburant.update((Articlecarburant) event.getObject());
		 

	}
	public String ficheArticle() {
		listArticlecarburant = new ArrayList<Articlecarburant>();
		listArticlecarburant = serviceArticleCarburant.getAll();
       
		return SUCCESS;
	}
	
	public String modifierArticle() {
		serviceArticleCarburant.update(articlecarburant);
		return SUCCESS;
	}
public void getarticlecarburant(ActionEvent actionEvent) {
	
}
	public String inventairecarburant() {
		total=0;
		listArticlecarburant = new ArrayList<Articlecarburant>();
		listArticlecarburant = serviceArticleCarburant.getAll();
		DecimalFormat df=new DecimalFormat("#,###.000");
		if(listArticlecarburant!=null)
		for(Articlecarburant a:listArticlecarburant) {
			a.setMontant(a.getVente()*a.getQuantite());
			if(a.getMontant()<1)
				df=new DecimalFormat("0.000");
			else 
				df=new DecimalFormat("#,###.000");
			a.setMontants(df.format(a.getMontant()));
			
			total=total+a.getMontant();
		}
		date=new Date();
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		dates=f.format(date);
		totals=df.format(total);
		return SUCCESS;
	}

	/*
	 * getter and setter
	 **/

	public ServiceArticleCarburant getServiceArticleCarburant() {
		return serviceArticleCarburant;
	}

	public void setServiceArticleCarburant(ServiceArticleCarburant serviceArticleCarburant) {
		this.serviceArticleCarburant = serviceArticleCarburant;
	}

	public List<Articlecarburant> getListArticlecarburant() {
		return listArticlecarburant;
	}

	public void setListArticlecarburant(List<Articlecarburant> listArticlecarburant) {
		this.listArticlecarburant = listArticlecarburant;
	}

	public Articlecarburant getArticlecarburant() {
		return articlecarburant;
	}

	public void setArticlecarburant(Articlecarburant articlecarburant) {
		this.articlecarburant = articlecarburant;
	}
	public double gettotal() {
		return total;
	}

	public void settotal(double total) {
		this.total = total;
	}

	public String gettotals() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totals = df.format(total);
		return totals;
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

	public Integer getCodes() {
		return codes;
	}

	public void setCodes(Integer codes) {
		this.codes = codes;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDates() {
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		 dates=f.format(date);
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
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
	

}
