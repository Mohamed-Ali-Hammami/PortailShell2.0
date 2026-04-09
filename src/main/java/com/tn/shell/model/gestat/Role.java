package com.tn.shell.model.gestat;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;



 
 
@Entity
@Table(name="Role") 
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private Integer id;

@Enumerated(EnumType.STRING)
private EProfil role;

/**
 * lien one to many avec utilisateur
 * 
 * */
 
@OneToMany(mappedBy="role",cascade={CascadeType.MERGE,CascadeType.REMOVE,CascadeType.REFRESH})
	private List<Utilisateur> utilisateurs;


/*
 * Constructor
 * */
public Role() {	 

}
 
/*
 * getters
 * and
 * setters
 * */
 


public Integer getId() {
	return id;
}


public void setId(Integer id) {
	this.id = id;
}



public EProfil getRole() {
	return role;
}

public void setRole(EProfil role) {
	this.role = role;
}

public List<Utilisateur> getUtilisateurs() {
	return utilisateurs;
}

public void setUtilisateurs(List<Utilisateur> utilisateurs) {
	this.utilisateurs = utilisateurs;
}



}
