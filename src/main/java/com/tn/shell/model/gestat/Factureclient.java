package com.tn.shell.model.gestat;

import java.util.Date;
import java.util.List;

public class Factureclient {
private Clientgestat clint;
private Date date;
private String dates;
private Credit credit;
private Creditanterieur creditant;
private Integer numfacture;
private Date date1;
private Date date2;
private List<Ligneventecredit> listlgneventecredit;
private double total;
private String totals;

private int id;



public Clientgestat getClint() {
	return clint;
}
public void setClint(Clientgestat clint) {
	this.clint = clint;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getDates() {
	return dates;
}
public void setDates(String dates) {
	this.dates = dates;
}
public Credit getCredit() {
	return credit;
}
public void setCredit(Credit credit) {
	this.credit = credit;
}
public Creditanterieur getCreditant() {
	return creditant;
}
public void setCreditant(Creditanterieur creditant) {
	this.creditant = creditant;
}
public Integer getNumfacture() {
	return numfacture;
}
public void setNumfacture(Integer numfacture) {
	this.numfacture = numfacture;
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
public List<Ligneventecredit> getListlgneventecredit() {
	return listlgneventecredit;
}
public void setListlgneventecredit(List<Ligneventecredit> listlgneventecredit) {
	this.listlgneventecredit = listlgneventecredit;
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
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}




}
