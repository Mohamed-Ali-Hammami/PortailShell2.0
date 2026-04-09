package com.tn.shell.model.paie;

import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Formule_Paie {

	  
	 private double salairedebase=0;
	 private double salaire_brut=0;
	 private double retenue_cnss=0;
	 private double salairesup=0;
	 private double salaire_imposable=0;
	 private double irpp=0;
	 private double salaire_net=0;
	 private double net_apayer=0;
	 private double avance=0;
	 private double redevance;
	 
	 @Transient 
	 private String redevances;
	 private double nb_heure;
	 
	 @Transient 
	 private String salairedebases;
	 @Transient
	 private String salaire_bruts;
	 @Transient
	 private String retenue_cnsss;
	 @Transient
	 private String salaire_imposables;
	 @Transient
	 private String irpps;
	 @Transient
	 private String salaire_nets;
	 @Transient
	 private String net_apayers;
	 @Transient
	 private String avances;
	  
	 
	public double getSalairedebase() {
		return salairedebase;
	}
	public void setSalairedebase(double salairedebase) {
		this.salairedebase = salairedebase;
	}
	public double getSalaire_brut() {
		return salaire_brut;
	}
	public void setSalaire_brut(double salaire_brut) {
		this.salaire_brut = salaire_brut;
	}
	public double getRetenue_cnss() {
		return retenue_cnss;
	}
	public void setRetenue_cnss(double retenue_cnss) {
		this.retenue_cnss = retenue_cnss;
	}
	public double getSalaire_imposable() {
		return salaire_imposable;
	}
	public void setSalaire_imposable(double salaire_imposable) {
		this.salaire_imposable = salaire_imposable;
	}
	public double getIrpp() {
		return irpp;
	}
	public void setIrpp(double irpp) {
		this.irpp = irpp;
	}
	public double getSalaire_net() {
		return salaire_net;
	}
	public void setSalaire_net(double salaire_net) {
		this.salaire_net = salaire_net;
	}
	public double getNet_apayer() {
		return net_apayer;
	}
	public void setNet_apayer(double net_apayer) {
		this.net_apayer = net_apayer;
	}
	public double getAvance() {
		return avance;
	}
	public void setAvance(double avance) {
		this.avance = avance;
	}
	public double getNb_heure() {
		return nb_heure;
	}
	public void setNb_heure(double nb_heure) {
		this.nb_heure = nb_heure;
	}
	
	

	 
	
	
	
	
	
	
	
	
	

	public String getSalairedebases() {
		DecimalFormat df=new DecimalFormat("0.000");
		salairedebases=df.format(salairedebase);
		return salairedebases;
	}
	public void setSalairedebases(String salairedebases) {
		this.salairedebases = salairedebases;
	}
	public String getSalaire_bruts() {
		DecimalFormat df=new DecimalFormat("0.000");
		salaire_bruts=df.format(salaire_brut);
		return salaire_bruts;
	}
	public void setSalaire_bruts(String salaire_bruts) {
		this.salaire_bruts = salaire_bruts;
	}
	public String getRetenue_cnsss() {
		DecimalFormat df=new DecimalFormat("0.000");
		retenue_cnsss=df.format(retenue_cnss);
		return retenue_cnsss;
	}
	public void setRetenue_cnsss(String retenue_cnsss) {
		this.retenue_cnsss = retenue_cnsss;
	}
	public String getSalaire_imposables() {
		DecimalFormat df=new DecimalFormat("0.000");
		salaire_imposables=df.format(salaire_imposable);
		return salaire_imposables;
	}
	public void setSalaire_imposables(String salaire_imposables) {
		this.salaire_imposables = salaire_imposables;
	}
	public String getIrpps() {
		DecimalFormat df=new DecimalFormat("0.000");
		irpps=df.format(irpp);
		return irpps;
	}
	public void setIrpps(String irpps) {
		this.irpps = irpps;
	}
	public String getSalaire_nets() {
		DecimalFormat df=new DecimalFormat("0.000");
		salaire_nets=df.format(salaire_net);
		return salaire_nets;
	}
	public void setSalaire_nets(String salaire_nets) {
		this.salaire_nets = salaire_nets;
	}
	public String getNet_apayers() {
		DecimalFormat df=new DecimalFormat("0.000");
		net_apayers=df.format(net_apayer);
		return net_apayers;
	}
	public void setNet_apayers(String net_apayers) {
		this.net_apayers = net_apayers;
	}
	public String getAvances() {
		DecimalFormat df=new DecimalFormat("0.000");
		avances=df.format(avance);
		return avances;
	}
	public void setAvances(String avances) {
		this.avances = avances;
	}
	public double getRedevance() {
		return redevance;
	}
	public void setRedevance(double redevance) {
		this.redevance = redevance;
	}
	public String getRedevances() {
		DecimalFormat df=new DecimalFormat("0.000");
		redevances=df.format(redevance);
		return redevances;
	}
	public void setRedevances(String redevances) {
		this.redevances = redevances;
	}
	public double getSalairesup() {
		return salairesup;
	}
	public void setSalairesup(double salairesup) {
		this.salairesup = salairesup;
	}
	 
	 
	 
	 
	 
}
