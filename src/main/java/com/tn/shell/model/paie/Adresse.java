package com.tn.shell.model.paie;

import javax.persistence.*;

@Embeddable
public class Adresse {
	
	
	
	private String num="";	
	
	private String rue="";	
	
	private String ville="";
	
	
	public Adresse(){}	


	public Adresse(String num, String rue, String ville) {
		super();
		this.num = num;
		this.rue = rue;
		this.ville = ville;
		
	} 


	//getters et setters
	 
	public String getRue() {
		return rue;
	}
	public String getNum() {
		return num;
	}


	public void setNum(String num) {
		this.num = num;
	}


	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	
}
