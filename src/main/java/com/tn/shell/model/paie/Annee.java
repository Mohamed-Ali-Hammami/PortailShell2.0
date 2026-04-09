package com.tn.shell.model.paie;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Annee")
public class Annee {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private Integer id; 
	
private String annee;

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getAnnee() {
	return annee;
}

public void setAnnee(String annee) {
	this.annee = annee;
}


}
