package com.tn.shell.model.shop;

import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

 

@Entity
@Table(name="Fournisseur") 
public class Fournisseur {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private Integer code;
private String nom;
 private String matriculfisacl;
 private double reste;
 private String tel;
 private String address;
 private double ca;
 private String restes;
@Enumerated(EnumType.STRING)
private Statut statut= Statut.ACTIF;


 
 
	//lien one to many avec Factureachat
		/*@OneToMany(mappedBy="fournisseur",cascade={CascadeType.MERGE,CascadeType.REMOVE,CascadeType.REFRESH})
        private List<Factureachat> listfacture;*/
		
	 
		//lien one to many avec Factureachat
		@OneToMany(mappedBy="fournisseur",cascade={CascadeType.MERGE,CascadeType.REMOVE,CascadeType.REFRESH})
        private List<Achat> listachat;
		
 
		 
		
		//getter and setter
	 
		 
		  
		 
		public Statut getStatut() {
			return statut;
		}
		public double getReste() {
			return reste;
		}
		public void setReste(double reste) {
			this.reste = reste;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public Integer getCode() {
			return code;
		}
		public void setCode(Integer code) {
			this.code = code;
		}
		public void setStatut(Statut statut) {
			this.statut = statut;
		}
		public List<Achat> getListachat() {
			return listachat;
		}
		public void setListachat(List<Achat> listachat) {
			this.listachat = listachat;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		 
		public String getMatriculfisacl() {
			return matriculfisacl;
		}
		public void setMatriculfisacl(String matriculfisacl) {
			this.matriculfisacl = matriculfisacl;
		}
		public double getCa() {
			return ca;
		}
		public void setCa(double ca) {
			this.ca = ca;
		}
		public String getRestes() {
			DecimalFormat df=new DecimalFormat("0.000");
			restes=df.format(reste);
			return restes;
		}
		public void setRestes(String restes) {
			this.restes = restes;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		 
		 
		 
		 
		
		 
}
