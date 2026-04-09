package com.tn.shell.model.gestat;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
 
public class Etatprofil {
@Transient
private Articlecarburant article;
@Transient
private String quantite;
@Transient
private double quantites;
@Transient
private double totalventes;
@Transient
private String totalvente;
@Transient
private String marge;
@Transient
private Double marges;
@Transient
private String pourcent;
@Transient
private double pourcents;
 
@Transient
private String quantiter;
@Transient
private String totalventer;
@Transient
private String marger;
@Transient
private Double margesr;
@Transient
private String pourcentr;
@Transient
private double pourcentsr;







public Articlecarburant getArticle() {
	return article;
}
public void setArticle(Articlecarburant article) {
	this.article = article;
}
public String getQuantite() {
	return quantite;
}
public void setQuantite(String quantite) {
	this.quantite = quantite;
}
public String getTotalvente() {
	return totalvente;
}
public void setTotalvente(String totalvente) {
	this.totalvente = totalvente;
}
public String getMarge() {
	return marge;
}
public void setMarge(String marge) {
	this.marge = marge;
}
public String getPourcent() {
	return pourcent;
}
public void setPourcent(String pourcent) {
	this.pourcent = pourcent;
}
public Double getMarges() {
	return marges;
}
public void setMarges(Double marges) {
	this.marges = marges;
}
public double getPourcents() {
	return pourcents;
}
public void setPourcents(double pourcents) {
	this.pourcents = pourcents;
}
public String getQuantiter() {
	return quantiter;
}
public void setQuantiter(String quantiter) {
	this.quantiter = quantiter;
}
public String getTotalventer() {
	return totalventer;
}
public void setTotalventer(String totalventer) {
	this.totalventer = totalventer;
}
public String getMarger() {
	return marger;
}
public void setMarger(String marger) {
	this.marger = marger;
}
public Double getMargesr() {
	return margesr;
}
public void setMargesr(Double margesr) {
	this.margesr = margesr;
}
public String getPourcentr() {
	return pourcentr;
}
public void setPourcentr(String pourcentr) {
	this.pourcentr = pourcentr;
}
public double getPourcentsr() {
	return pourcentsr;
}
public void setPourcentsr(double pourcentsr) {
	this.pourcentsr = pourcentsr;
}
public double getQuantites() {
	return quantites;
}
public void setQuantites(double quantites) {
	this.quantites = quantites;
}
public double getTotalventes() {
	return totalventes;
}
public void setTotalventes(double totalventes) {
	this.totalventes = totalventes;
}




}
