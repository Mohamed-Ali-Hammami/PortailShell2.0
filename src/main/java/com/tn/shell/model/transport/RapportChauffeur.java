package com.tn.shell.model.transport;

import java.util.List;

import javax.persistence.Transient;

public class RapportChauffeur {
	
 
 
@Transient
private String dates;
@Transient
private Integer totalnbvoyage;

@Transient
private Integer nbtotalnbvoyage;
@Transient
private Integer total;
@Transient
private List<Chauffeur> listchauffeur;
@Transient
private Chauffeur chauffeur;
 

public Integer getTotalnbvoyage() {
	return totalnbvoyage;
}

public void setTotalnbvoyage(Integer totalnbvoyage) {
	this.totalnbvoyage = totalnbvoyage;
}

public String getDates() {
	return dates;
}

public void setDates(String dates) {
	this.dates = dates;
}

public List<Chauffeur> getListchauffeur() {
	return listchauffeur;
}

public void setListchauffeur(List<Chauffeur> listchauffeur) {
	this.listchauffeur = listchauffeur;
}

public Chauffeur getChauffeur() {
	return chauffeur;
}

public void setChauffeur(Chauffeur chauffeur) {
	this.chauffeur = chauffeur;
}

public Integer getNbtotalnbvoyage() {
	return nbtotalnbvoyage;
}

public void setNbtotalnbvoyage(Integer nbtotalnbvoyage) {
	this.nbtotalnbvoyage = nbtotalnbvoyage;
}

public Integer getTotal() {
	return total;
}

public void setTotal(Integer total) {
	this.total = total;
}



}
