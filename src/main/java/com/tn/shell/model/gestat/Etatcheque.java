package com.tn.shell.model.gestat;

import java.text.DecimalFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "Etatcheque")
public class Etatcheque {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
@Transient
private String totalchequeimpaye;
@Transient
private String totalchequeanidate;
@Transient
private String totalchequepreavis;
 
private double totalcrediteurzitouna;
@Transient
private String totalcrediteurzitounas;
private double totalsoldejazira;
private double totalsoldebiat;
@Transient
private String totalsoldejaziras;
@Transient
private String totalchequeencours;
@Transient
private String soldenet;
@Transient
private String totalsoldebiats;
@Transient
private String soldetotal;




public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getTotalchequeimpaye() {
	return totalchequeimpaye;
}
public void setTotalchequeimpaye(String totalchequeimpaye) {
	this.totalchequeimpaye = totalchequeimpaye;
}
public String getTotalchequeanidate() {
	return totalchequeanidate;
}
public void setTotalchequeanidate(String totalchequeanidate) {
	this.totalchequeanidate = totalchequeanidate;
}
public double getTotalcrediteurzitouna() {
	return totalcrediteurzitouna;
}
public void setTotalcrediteurzitouna(double totalcrediteurzitouna) {
	this.totalcrediteurzitouna = totalcrediteurzitouna;
}
public String getTotalcrediteurzitounas() {
	DecimalFormat df = new DecimalFormat("0.000");
	totalcrediteurzitounas=df.format(totalcrediteurzitouna);
	return totalcrediteurzitounas;
}
public void setTotalcrediteurzitounas(String totalcrediteurzitounas) {
	this.totalcrediteurzitounas = totalcrediteurzitounas;
}
public double getTotalsoldejazira() {
	return totalsoldejazira;
}
public void setTotalsoldejazira(double totalsoldejazira) {
	this.totalsoldejazira = totalsoldejazira;
}
public String getTotalsoldejaziras() {
	return totalsoldejaziras;
}
public void setTotalsoldejaziras(String totalsoldejaziras) {
	this.totalsoldejaziras = totalsoldejaziras;
}
public String getTotalchequeencours() {
	return totalchequeencours;
}
public void setTotalchequeencours(String totalchequeencours) {
	this.totalchequeencours = totalchequeencours;
}
public String getSoldenet() {
	return soldenet;
}
public void setSoldenet(String soldenet) {
	this.soldenet = soldenet;
}
public double getTotalsoldebiat() {
	return totalsoldebiat;
}
public void setTotalsoldebiat(double totalsoldebiat) {
	this.totalsoldebiat = totalsoldebiat;
}
public String getTotalchequepreavis() {
	return totalchequepreavis;
}
public void setTotalchequepreavis(String totalchequepreavis) {
	this.totalchequepreavis = totalchequepreavis;
}
public String getSoldetotal() {
	return soldetotal;
}
public void setSoldetotal(String soldetotal) {
	this.soldetotal = soldetotal;
}
public String getTotalsoldebiats() {
	DecimalFormat df = new DecimalFormat("0.000");
	totalsoldebiats = df.format(totalsoldebiat);
	return totalsoldebiats;
}
public void setTotalsoldebiats(String totalsoldebiats) {
	this.totalsoldebiats = totalsoldebiats;
}




}
