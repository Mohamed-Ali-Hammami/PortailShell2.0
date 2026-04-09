package com.tn.shell.model.transport;

import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class Paramettrevehicule {
private Date prochainedateass;
private Date prochainedatevisite;
private Date prochainedatequittance;


public Date getProchainedateass() {
	return prochainedateass;
}
public void setProchainedateass(Date prochainedateass) {
	this.prochainedateass = prochainedateass;
}
public Date getProchainedatevisite() {
	return prochainedatevisite;
}
public void setProchainedatevisite(Date prochainedatevisite) {
	this.prochainedatevisite = prochainedatevisite;
}
public Date getProchainedatequittance() {
	return prochainedatequittance;
}
public void setProchainedatequittance(Date prochainedatequittance) {
	this.prochainedatequittance = prochainedatequittance;
}



}
