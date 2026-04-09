package com.tn.shell.ui.paie;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.imageio.ImageIO;
 












import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.PieChartModel;

import com.tn.shell.model.paie.*;
import com.tn.shell.service.paie.*;

import javax.annotation.PostConstruct;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean(name = "Statistiques")
@SessionScoped
public class Statistiques implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error"; 
	 
	 
	 
	 
	@ManagedProperty(value = "#{ServiceSociete}")
	ServiceSociete serviceSociete;
	 
	 
	private List<String> listProduitFini = new ArrayList<String>();
	 
	 
	 
	private Integer nbBLnpayee;
	private float montantBLnpayee;
	private String montantBLnpayees;
	private String clients;
	private Integer nbFTnpayee;
	private float montantFTnpayee;
	private String montantFTnpayees;
	 
	private String produitFini;
	private Date date1;
	private Date date;
	 
	private List<String> listeclient;
	private String dates;
	private Date date2;
	private Societe societe;
	private String dates1;
	private String dates2;
   
	
	
	 
	  
	 
	 
	 
	 
	 
	 
	public List<String> getListProduitFini() {
		return listProduitFini;
	}
	public void setListProduitFini(List<String> listProduitFini) {
		this.listProduitFini = listProduitFini;
	}
	 
	 
	public Integer getNbBLnpayee() {
		return nbBLnpayee;
	}
	public void setNbBLnpayee(Integer nbBLnpayee) {
		this.nbBLnpayee = nbBLnpayee;
	}
	public float getMontantBLnpayee() {
		return montantBLnpayee;
	}
	public void setMontantBLnpayee(float montantBLnpayee) {
		this.montantBLnpayee = montantBLnpayee;
	}
	public String getMontantBLnpayees() {
		DecimalFormat df = new DecimalFormat("0.000");
		montantBLnpayees=df.format(montantBLnpayee);
		return montantBLnpayees;
	}
	public void setMontantBLnpayees(String montantBLnpayees) {
		this.montantBLnpayees = montantBLnpayees;
	}





	public Integer getNbFTnpayee() {
		return nbFTnpayee;
	}





	public void setNbFTnpayee(Integer nbFTnpayee) {
		this.nbFTnpayee = nbFTnpayee;
	}





	public float getMontantFTnpayee() {
		return montantFTnpayee;
	}





	public void setMontantFTnpayee(float montantFTnpayee) {
		this.montantFTnpayee = montantFTnpayee;
	}





	public String getMontantFTnpayees() {
		DecimalFormat df = new DecimalFormat("0.000");
		montantFTnpayees=df.format(montantFTnpayee);
		return montantFTnpayees;
	}





	public void setMontantFTnpayees(String montantFTnpayees) {
		this.montantFTnpayees = montantFTnpayees;
	}

 
	public ServiceSociete getServiceSociete() {
		return serviceSociete;
	}


	public void setServiceSociete(ServiceSociete serviceSociete) {
		this.serviceSociete = serviceSociete;
	}


	 
 


	public String getProduitFini() {
		return produitFini;
	}


	public void setProduitFini(String produitFini) {
		this.produitFini = produitFini;
	}


	public Date getDate1() {
		return date1;
	}


	public void setDate1(Date date1) {
		this.date1 = date1;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	 

	public List<String> getListeclient() {
		return listeclient;
	}


	public void setListeclient(List<String> listeclient) {
		this.listeclient = listeclient;
	}


	public String getDates() {
		return dates;
	}


	public void setDates(String dates) {
		this.dates = dates;
	}


	public Date getDate2() {
		return date2;
	}


	public void setDate2(Date date2) {
		this.date2 = date2;
	}


	public Societe getSociete() {
		return societe;
	}


	public void setSociete(Societe societe) {
		this.societe = societe;
	}


	public String getDates1() {
		return dates1;
	}


	public void setDates1(String dates1) {
		this.dates1 = dates1;
	}


	public String getDates2() {
		return dates2;
	}


	public void setDates2(String dates2) {
		this.dates2 = dates2;
	}


	public String getClients() {
		return clients;
	}


	public void setClients(String clients) {
		this.clients = clients;
	}
 
	 
	
	
	 
}
