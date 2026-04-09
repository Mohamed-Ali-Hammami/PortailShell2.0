package com.tn.shell.model.transport;

import java.text.DecimalFormat;

public class Rapportcamion {
	private Integer id;
	private String matricule;
	private String chauffeur;
	private String depensecarburants;
	private String autredepenses;
	private double salairchauffeur;
	private String salairchauffeurs;
	private String totaldepenses;
	private String totaltransports;
	private String dfs;
	
	 
	private double depensecarburant;
	private double autredepense;  
	private double totaldepense;
	private double totaltransport;
	private double df;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	 
	public double getSalairchauffeur() {
		return salairchauffeur;
	}
	public void setSalairchauffeur(double salairchauffeur) {
		this.salairchauffeur = salairchauffeur;
	}
	public String getSalairchauffeurs() {
		DecimalFormat df=new DecimalFormat("0.000");
		salairchauffeurs=df.format(salairchauffeur);
		return salairchauffeurs;
	}
	public void setSalairchauffeurs(String salairchauffeurs) {
		this.salairchauffeurs = salairchauffeurs;
	}
	public String getDepensecarburants() {
		DecimalFormat df=new DecimalFormat("0.000");
		depensecarburants=df.format(depensecarburant);
		return depensecarburants;
	}
	public void setDepensecarburants(String depensecarburants) {
		this.depensecarburants = depensecarburants;
	}
	public String getAutredepenses() {
		DecimalFormat df=new DecimalFormat("0.000");
		autredepenses=df.format(autredepense);
		return autredepenses;
	}
	public void setAutredepenses(String autredepenses) {
		this.autredepenses = autredepenses;
	}
	public String getTotaldepenses() {
		DecimalFormat df=new DecimalFormat("0.000");
		totaldepenses=df.format(totaldepense);
		return totaldepenses;
	}
	public void setTotaldepenses(String totaldepenses) {
		this.totaldepenses = totaldepenses;
	}
	public String getTotaltransports() {
		DecimalFormat df=new DecimalFormat("0.000");
		totaltransports=df.format(totaltransport);
		return totaltransports;
	}
	public void setTotaltransports(String totaltransports) {
		this.totaltransports = totaltransports;
	}
	public String getDfs() {
		DecimalFormat df=new DecimalFormat("0.000");
		dfs=df.format(df);
		return dfs;
	}
	public void setDfs(String dfs) {
		this.dfs = dfs;
	}
	public double getDepensecarburant() {
		return depensecarburant;
	}
	public void setDepensecarburant(double depensecarburant) {
		this.depensecarburant = depensecarburant;
	}
	public double getAutredepense() {
		return autredepense;
	}
	public void setAutredepense(double autredepense) {
		this.autredepense = autredepense;
	}
	public double getTotaldepense() {
		return totaldepense;
	}
	public void setTotaldepense(double totaldepense) {
		this.totaldepense = totaldepense;
	}
	public double getTotaltransport() {
		return totaltransport;
	}
	public void setTotaltransport(double totaltransport) {
		this.totaltransport = totaltransport;
	}
	public double getDf() {
		return df;
	}
	public void setDf(double df) {
		this.df = df;
	}
	public String getChauffeur() {
		return chauffeur;
	}
	public void setChauffeur(String chauffeur) {
		this.chauffeur = chauffeur;
	}
	 
	 
	
	
}
