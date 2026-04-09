package com.tn.shell.model.gestat;

import java.util.List;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Utilisateur")
public class Utilisateur {

	public static enum UserStatut {
		ACTIF, DEACTIF
	}

	@Id
	private String login;
	private String nom;
	private String motdepasse;

	@Enumerated(EnumType.STRING)
	private UserStatut statut = UserStatut.ACTIF;

	@OneToOne
	@JoinTable(name = "Utilisateur_Roles", joinColumns = {
			@JoinColumn(name = "utilisateur_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private Role role;
	
	@OneToMany(mappedBy = "utilisateur", cascade = { CascadeType.MERGE,
			CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Historiquepayement> historiquess;

	/*
	 * Constructor
	 */

	public Utilisateur() {

	}

	@Column(name = "login")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "nom")
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "motdepasse")
	public String getMotdepasse() {
		return motdepasse;
	}

	public void setMotdepasse(String motdepasse) {
		Md5 pass = new Md5(motdepasse);
		this.motdepasse = pass.getCode();
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isAdmin() {
		return EProfil.Admin.equals(this.role.getRole());
	}
	
	public boolean isResponsablelavage() {
		return EProfil.Responsablelavage.equals(this.role.getRole());
	}

	public boolean isCaissier() {
		return EProfil.Caissier.equals(this.role.getRole());
	}

	public boolean isSuperviseur() {
		return EProfil.Superviseur.equals(this.role.getRole());
	}

	public boolean isResponsableshops() {
		return EProfil.Responsableshop.equals(this.role.getRole());
	}
	public boolean isAgentTransport() {
		return EProfil.AgentTransport.equals(this.role.getRole());	
	}
	public void setStatut(UserStatut statut) {
		this.statut = statut;
	}

	public UserStatut getStatut() {
		return statut;
	}

	public List<Historiquepayement> getHistoriquess() {
		return historiquess;
	}

	public void setHistoriquess(List<Historiquepayement> historiquess) {
		this.historiquess = historiquess;
	}
	
	

}
