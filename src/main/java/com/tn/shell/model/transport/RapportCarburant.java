package com.tn.shell.model.transport;

import java.util.List;

import javax.persistence.Transient;

import com.tn.shell.model.shop.Produit;

public class RapportCarburant {
	 
	private String dates;
	 private String quantiteg;
	 private String quantiteg50;
	 private String quantitepetrole;
	 private String marge;
	 private String benifice;
	 private String totalrecette; 	  
	private String totaldepense;	  
	private String totaltransport;
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public String getQuantiteg() {
		return quantiteg;
	}
	public void setQuantiteg(String quantiteg) {
		this.quantiteg = quantiteg;
	}
	public String getQuantiteg50() {
		return quantiteg50;
	}
	public void setQuantiteg50(String quantiteg50) {
		this.quantiteg50 = quantiteg50;
	}
	public String getQuantitepetrole() {
		return quantitepetrole;
	}
	public void setQuantitepetrole(String quantitepetrole) {
		this.quantitepetrole = quantitepetrole;
	}
	public String getMarge() {
		return marge;
	}
	public void setMarge(String marge) {
		this.marge = marge;
	}
	public String getTotalrecette() {
		return totalrecette;
	}
	public void setTotalrecette(String totalrecette) {
		this.totalrecette = totalrecette;
	}
	public String getTotaldepense() {
		return totaldepense;
	}
	public void setTotaldepense(String totaldepense) {
		this.totaldepense = totaldepense;
	}
	public String getTotaltransport() {
		return totaltransport;
	}
	public void setTotaltransport(String totaltransport) {
		this.totaltransport = totaltransport;
	}
	public String getBenifice() {
		return benifice;
	}
	public void setBenifice(String benifice) {
		this.benifice = benifice;
	} 
	
}
