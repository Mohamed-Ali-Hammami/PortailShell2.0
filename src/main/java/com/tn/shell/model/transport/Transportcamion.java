package com.tn.shell.model.transport;

import java.util.List;

import javax.persistence.Transient;

public class Transportcamion {

	 
@Transient
private String dates;
 
@Transient
private List<Vhecule> vhecules;
@Transient
private double totaltransportparjour;



public String getDates() {
	return dates;
}
public void setDates(String dates) {
	this.dates = dates;
}
public List<Vhecule> getVhecules() {
	return vhecules;
}
public void setVhecules(List<Vhecule> vhecules) {
	this.vhecules = vhecules;
}
public double getTotaltransportparjour() {
	return totaltransportparjour;
}
public void setTotaltransportparjour(double totaltransportparjour) {
	this.totaltransportparjour = totaltransportparjour;
}




}
