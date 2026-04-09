package com.tn.shell.ui.gestat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.tn.shell.model.gestat.*;
import com.tn.shell.model.shop.Cahierstock;
import com.tn.shell.model.shop.Lignealimentation;
import com.tn.shell.model.shop.Lignevente;
import com.tn.shell.model.shop.Produit;
import com.tn.shell.service.gestat.*;
import com.tn.shell.service.gestat.ServicePompe;
import com.tn.shell.service.shop.ServiceLigneAlimentation;
import com.tn.shell.service.shop.ServiceLignevente;
import com.tn.shell.service.shop.ServiceProduit;

@ManagedBean(name = "ProduitgestatBean")
@SessionScoped
public class ProduitgestatBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{ServiceProduit}")
	ServiceProduit serviceProduit;
	@ManagedProperty(value = "#{ServicePompe}")
	ServicePompe servicePompe;
	@ManagedProperty(value = "#{ServiceLignevente}")
	ServiceLignevente serviceLignevente;
	private double quantiteinitial;
	private double quantitefinal;
	private String totalvente;
	private String totaltransfert;
	@ManagedProperty(value = "#{ServiceArticleCarburant}")
	ServiceArticleCarburant serviceArticleCarburant;

	@ManagedProperty(value = "#{ServiceLigneAlimentation}")
	ServiceLigneAlimentation serviceLignetransfert;

	private List<Articlecarburant> listArticlecarburant;
	private List<Produit> listProduit;
	private List<Produit> listProduit2;
	private List<String> listcredits;
	private List<Pompe> listpompe;
	private String libelle;
	private Articlecarburant articlecarburant;
	private Pompe pompe;

	public String miseAjourpompe() {
		listpompe = new ArrayList<Pompe>();
		listpompe = servicePompe.getAll();
		if(listpompe.size()>0)
		articlecarburant = listpompe.get(0).getArticlecarburant();
		else articlecarburant=new Articlecarburant();
		listArticlecarburant = new ArrayList<Articlecarburant>();
		listArticlecarburant = serviceArticleCarburant.getAll();
		return SUCCESS;
	}

	public void getarticlebynom(AjaxBehaviorEvent event) {
		articlecarburant = serviceArticleCarburant.Findbydes(libelle);
	}

	private Date date1;
	private Date date2;
 

	public String verfificateurcahierStock() {
		
		listProduit2 = new ArrayList<Produit>();

		date1 = new Date();
		date2 = new Date();
		date1.setDate(1);
		date1.setHours(0);
		date1.setMinutes(0);
		 date1.setSeconds(0);

		for (Produit p : listProduit) {
			double quantitefinal=0;
			 
		  
		 

			quantitefinal = (p.getStockinitial() -  serviceLignevente.getAllventeparDateandproduit2(date1,date2, p)) +
					serviceLignetransfert.getlisttransferbydateandproduit2(date1,date2, p);
			p.setQuantiteprevu(quantitefinal);

			if (quantitefinal !=  p.getQuantite())
				listProduit2.add(p);
		}
		return SUCCESS;
	}

	public String initialiserStock() {
		SimpleDateFormat f = new SimpleDateFormat("dd-MM");
		String d=f.format(new Date() );		
		for (Produit p : listProduit) {			
			p.setStockinitial(p.getQuantitedepot() + p.getQuantitestock());
			if(d.equals("31-12"))
				p.setStockfinannee(p.getStockinitial());
			serviceProduit.update(p);
		}
		return SUCCESS;
	}

	public String cahierstock() {
		listProduit = new ArrayList<Produit>();
		listProduit = serviceProduit.getAlls();
		listcredits = new ArrayList<String>();
		return SUCCESS;

	}

	public String getcahierstock() {
		 
		Produit p = new Produit();
		listProduit.add(p);
		listProduit.add(p);
		listProduit.add(p);
		listProduit.add(p);
		listProduit.add(p);
		listProduit.add(p);
		listProduit.add(p);
		listProduit.add(p);
		listProduit.add(p);
		listProduit.add(p);
		listcredits = new ArrayList<String>();
		for (int i = 0; i <= 30; i++) {
			listcredits.add("" + (i + 1));
		}
		return SUCCESS;
	}

	public String modifierPome() {
		servicePompe.update(pompe);
		return SUCCESS;
	}

	public ServiceProduit getServiceProduit() {
		return serviceProduit;
	}

	public void setServiceProduit(ServiceProduit serviceProduit) {
		this.serviceProduit = serviceProduit;
	}

	public List<Produit> getListProduit() {
		return listProduit;
	}

	public void setListProduit(List<Produit> listProduit) {
		this.listProduit = listProduit;
	}

	public List<String> getListcredits() {
		return listcredits;
	}

	public void setListcredits(List<String> listcredits) {
		this.listcredits = listcredits;
	}

	public ServicePompe getServicePompe() {
		return servicePompe;
	}

	public void setServicePompe(ServicePompe servicePompe) {
		this.servicePompe = servicePompe;
	}

	public List<Pompe> getListpompe() {
		return listpompe;
	}

	public void setListpompe(List<Pompe> listpompe) {
		this.listpompe = listpompe;
	}

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

	public String getLibelle() {
		return libelle;
	}

	public Pompe getPompe() {
		return pompe;
	}

	public void setPompe(Pompe pompe) {
		this.pompe = pompe;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Articlecarburant getArticlecarburant() {
		return articlecarburant;
	}

	public void setArticlecarburant(Articlecarburant articlecarburant) {
		this.articlecarburant = articlecarburant;
	}

	public ServiceLignevente getServiceLignevente() {
		return serviceLignevente;
	}

	public void setServiceLignevente(ServiceLignevente serviceLignevente) {
		this.serviceLignevente = serviceLignevente;
	}

	public double getQuantiteinitial() {
		return quantiteinitial;
	}

	public void setQuantiteinitial(double quantiteinitial) {
		this.quantiteinitial = quantiteinitial;
	}

	public double getQuantitefinal() {
		return quantitefinal;
	}

	public void setQuantitefinal(double quantitefinal) {
		this.quantitefinal = quantitefinal;
	}

	public String getTotalvente() {
		return totalvente;
	}

	public void setTotalvente(String totalvente) {
		this.totalvente = totalvente;
	}

	public String getTotaltransfert() {
		return totaltransfert;
	}

	public void setTotaltransfert(String totaltransfert) {
		this.totaltransfert = totaltransfert;
	}

	public ServiceLigneAlimentation getServiceLignetransfert() {
		return serviceLignetransfert;
	}

	public void setServiceLignetransfert(ServiceLigneAlimentation serviceLignetransfert) {
		this.serviceLignetransfert = serviceLignetransfert;
	}

	public List<Produit> getListProduit2() {
		return listProduit2;
	}

	public void setListProduit2(List<Produit> listProduit2) {
		this.listProduit2 = listProduit2;
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

}
