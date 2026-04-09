package com.tn.shell.model.shop;

import javax.persistence.Transient;

import com.tn.shell.model.paie.Employee;

public class Etatrendement {
	@Transient
	private Employee employer;
	@Transient
	private double nbtotal;
	@Transient
	private String dates;	
	@Transient
	private double nbsemi;
	@Transient
	private double nbvoiture;
	
}
