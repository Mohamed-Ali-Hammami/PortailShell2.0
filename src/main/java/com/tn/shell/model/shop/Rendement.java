package com.tn.shell.model.shop;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.primefaces.model.DefaultStreamedContent;
//import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.tn.shell.model.lavage.Imagelavage;
import com.tn.shell.model.lavage.TypeLavage;
import com.tn.shell.model.paie.Employee;

 

@Entity
@Table(name = "Rendement")
public class Rendement  implements Comparable<Object>, Serializable {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Date date;
	private String dates;
	@Enumerated(EnumType.STRING)
	private Poste poste;
	@Transient
	private String nbvoitures;	
	@Transient
	private String nbsemis;	 
	@Transient
	private String test;
	@Transient
	private boolean testDuree=false;
	private double nbvoiture;
	private double nbsemi;
	private String parouvrier;
	@Transient
	private String montantvs;
	@Transient
	private String montantss;
	private double montantv;
	private double montants;
	private String laveur;
	private String service;
	private String licence;
	private String client;
	private String tel;
	private String model;
	private String matricule;
	private String chefpiste;
	private String  marque;
 
	@Transient
	Calendar now = Calendar.getInstance();
	private String heure_entree = (now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));
	private String heure_sortie;
	private String duree;
	private String ecart;	
	@Transient
    private Imagelavage image;
	@Transient
	private String detail;
	@Transient
	private String ariere;
	@Transient
	private String droit;
	@Transient
	private String gauche;
	 private String huile;
	@Enumerated(EnumType.STRING)
	private Statut statut = Statut.ACTIF;
	@Enumerated(EnumType.STRING)
	private Statuss statuss = Statuss.Encours;
	@Enumerated(EnumType.STRING)
	private TypeLavage typelavage;
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "employeeid")
	private Employee employee;
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "ligneventeid")
	private Lignevente lignevente;
	private Integer kmArrivee;
	private Integer kmParHuile;
	private Integer kmProchain;
	private boolean rappel = false;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Poste getPoste() {
		return poste;
	}

	public void setPoste(Poste poste) {
		this.poste = poste;
	}

	public double getNbvoiture() {
		return nbvoiture;
	}

	public void setNbvoiture(double nbvoiture) {
		this.nbvoiture = nbvoiture;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	
	

	public String getMontantvs() {
		DecimalFormat dfss = new DecimalFormat("0.000");
		montantvs=dfss.format(montantv);
		return montantvs;
	}

	public void setMontantvs(String montantvs) {
		this.montantvs = montantvs;
	}

	public String getMontantss() {
		DecimalFormat dfss = new DecimalFormat("0.000");
		montantss=dfss.format(montants);
		return montantss;
	}

	public void setMontantss(String montantss) {
		this.montantss = montantss;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public double getNbsemi() {
		return nbsemi;
	}

	public void setNbsemi(double nbsemi) {
		this.nbsemi = nbsemi;
	}

	public double getMontantv() {
		return montantv;
	}

	public void setMontantv(double montant) {
		this.montantv = montant;
	}

	public double getMontants() {
		return montants;
	}

	public void setMontants(double montants) {
		this.montants = montants;
	}

	public Lignevente getLignevente() {
		return lignevente;
	}

	public void setLignevente(Lignevente lignevente) {
		this.lignevente = lignevente;
	}

	public int compareTo(Object o) {
		try {
			Rendement f = (Rendement) o;
			return (int) (this.nbvoiture - f.nbvoiture);
		} catch (Exception e) {
			return 0;
		}
	}

	public String getParouvrier() {
		return parouvrier;
	}

	public void setParouvrier(String parouvrier) {
		this.parouvrier = parouvrier;
	}

	public String getLaveur() {
		return laveur;
	}

	public void setLaveur(String laveur) {
		this.laveur = laveur;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getHeure_entree() {
		return heure_entree;
	}

	public void setHeure_entree(String heure_entree) {
		this.heure_entree = heure_entree;
	}

	public String getHeure_sortie() {
		return heure_sortie;
	}

	public void setHeure_sortie(String heure_sortie) {
		this.heure_sortie = heure_sortie;
	}

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

	public String getEcart() {
		return ecart;
	}

	public void setEcart(String ecart) {
		this.ecart = ecart;
	}

	@Override
	public String toString() {
		return "Rendement [id=" + id + ", date=" + date + ", dates=" + dates + ", poste=" + poste + ", nbvoiture="
				+ nbvoiture + ", nbsemi=" + nbsemi + ", parouvrier=" + parouvrier + ", montantv=" + montantv
				+ ", montants=" + montants + ", laveur=" + laveur + ", service=" + service + ", client=" + client
				+ ", tel=" + tel + ", model=" + model + ", matricule=" + matricule + ", heure_entree=" + heure_entree
				+ ", heure_sortie=" + heure_sortie + ", duree=" + duree + ", ecart=" + ecart + ", statut=" + statut
				+ ", statuss=" + statuss + ", employee=" + employee + ", lignevente=" + lignevente + "]";
	}

	public Statuss getStatuss() {
		return statuss;
	}

	public void setStatuss(Statuss statuss) {
		this.statuss = statuss;
	}

	 

	

	 
	
	private String convertImagetoBase64(String destinationFile) {		 
		try {

			        File file =  new File(destinationFile);
		            FileInputStream fileInputStreamReader = new FileInputStream(file);
		          //  byte[] bytes = new byte[(int)file.length()];
		           // fileInputStreamReader.read(bytes);
		            byte[] bytes = Files.readAllBytes(Paths.get(destinationFile));
		            String encodedString = Base64.getMimeEncoder().encodeToString(bytes);
		           // System.out.println(encodedString);
		            return encodedString;
		        
			 
		} catch (Exception e) {
			 
		}
		 return null;
	}

 
	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public TypeLavage getTypelavage() {
		return typelavage;
	}

	public void setTypelavage(TypeLavage typelavage) {
		this.typelavage = typelavage;
	}

	public Integer getKmArrivee() {
		return kmArrivee;
	}

	public void setKmArrivee(Integer kmArrivee) {
		this.kmArrivee = kmArrivee;
	}

	public Integer getKmParHuile() {
		return kmParHuile;
	}

	public void setKmParHuile(Integer kmParHuile) {
		this.kmParHuile = kmParHuile;
	}

	public Integer getKmProchain() {
		return kmProchain;
	}

	public void setKmProchain(Integer kmProchain) {
		this.kmProchain = kmProchain;
	}

	public boolean isRappel() {
		return rappel;
	}

	public void setRappel(boolean rappel) {
		this.rappel = rappel;
	}

	public String getChefpiste() {
		return chefpiste;
	}

	public void setChefpiste(String chefpiste) {
		this.chefpiste = chefpiste;
	}
	
	 
	public String getNbvoitures() {
		return nbvoitures;
	}

	public void setNbvoitures(String nbvoitures) {
		this.nbvoitures = nbvoitures;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getNbsemis() {
		return nbsemis;
	}

	public void setNbsemis(String nbsemis) {
		this.nbsemis = nbsemis;
	}

	public Imagelavage getImage() {
		return image;
	}

	public void setImage(Imagelavage image) {
		this.image = image;
	}
 
	 
	public String getDetail() {
		if(image!=null)
		detail=Base64.getMimeEncoder().encodeToString(image.getImage());
	 return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Calendar getNow() {
		return now;
	}

	public void setNow(Calendar now) {
		this.now = now;
	}

	public String getAriere() {
		 
		return ariere;
	}

	public void setAriere(String ariere) {
		this.ariere = ariere;
	}

	public String getHuile() {
		return huile;
	}

	public void setHuile(String huile) {
		this.huile = huile;
	}

	public String getDroit() {
		 
		return droit;
	}

	public void setDroit(String droit) {
		this.droit = droit;
	}

	public String getGauche() {
		 
		return gauche;
	}

	public void setGauche(String gauche) {
		this.gauche = gauche;
	}

	public boolean isTestDuree() {
		return testDuree;
	}

	public void setTestDuree(boolean testDuree) {
		this.testDuree = testDuree;
	}
 
 
}
