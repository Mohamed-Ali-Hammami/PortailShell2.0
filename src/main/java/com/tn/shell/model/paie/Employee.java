package com.tn.shell.model.paie;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.tn.shell.model.gestat.Avancegestat;
import com.tn.shell.model.gestat.Pompiste;
import com.tn.shell.model.shop.Rendement;
import com.tn.shell.model.vetement.Vetement;

 
@Entity
@Table(name = "Employee")
public class Employee implements Comparable<Object>{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  matricule;
	private Integer code;
	private String cin;
	private double salaire_sup=0;
	private String code_cnss;
	private String nom;
	 @Transient
	 private double nbjour;
	private Date datecontrat;
	private String pathcontrat;
	private String typedecontrat;
	@Transient
	private double nbjouannuel;
	@Transient
	List<Rendement > listrendement;
	@Transient
	private String test;
	@Enumerated(EnumType.STRING)
	private Etat etat;
	@Enumerated(EnumType.STRING)
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "categorieid")
	private Categorie contrat;
	 private Integer nb_enfant_enCharge;
	@Enumerated(EnumType.STRING)
	private Statut statut= Statut.ACTIF;
	private String banque;
	private String agence;
	@Enumerated(EnumType.STRING)
	private Status status; 
	private String adresse;
	private String tel;
	private String dateembauche;
	private String fonction;

	private double salaire_journalier;
	private Integer nb_enfant=0;
	private String RIB;
	private String nature;
	@OneToMany(mappedBy = "employee", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<LigneImageEmployee> imagess;
	   @Transient
	    private List<Lignegestion> lignegestion; 
	   
	   
	 		@OneToMany(mappedBy = "employee", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	 		private List<Vetement> vetements;
	 	
	
	@OneToMany(mappedBy = "employee", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Avancegestat> avancess;
	
	@OneToMany(mappedBy = "employee", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Pompiste> pompistes;
	
	 @OneToMany(mappedBy = "employee", cascade = { CascadeType.MERGE,
			CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Lignegestion> lignegestions; 
	
	// lien one to many avec Note
		@OneToMany(mappedBy = "employee", cascade = { CascadeType.MERGE,
				CascadeType.REMOVE, CascadeType.REFRESH })
		private List<Note> notes;
	
	  
		
		// lien one to many avec Paie
				@OneToMany(mappedBy = "employee", cascade = { CascadeType.MERGE,
						CascadeType.REMOVE, CascadeType.REFRESH })
				private List<Paie> paies;
	
	    // lien one to many avec Pointage
				@OneToMany(mappedBy = "employee", cascade = { CascadeType.MERGE,
						CascadeType.REMOVE, CascadeType.REFRESH })
				private List<Pointage> pointages;
	
	public Integer getMatricule() {
		return matricule;
	}
	public void setMatricule(Integer matricule) {
		this.matricule = matricule;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getCode_cnss() {
		return code_cnss;
	}
	public void setCode_cnss(String code_cnss) {
		this.code_cnss = code_cnss;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	 
	public Etat getEtat() {
		return etat;
	}
	public void setEtat(Etat etat) {
		this.etat = etat;
	}
	 
	 
	public Categorie getContrat() {
		return contrat;
	}
	public void setContrat(Categorie contrat) {
		this.contrat = contrat;
	}
	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	public List<Paie> getPaies() {
		return paies;
	}
	public void setPaies(List<Paie> paies) {
		this.paies = paies;
	}
	public List<Pointage> getPointages() {
		return pointages;
	}
	public void setPointages(List<Pointage> pointages) {
		this.pointages = pointages;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	 
	public String getFonction() {
		return fonction;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	 
	public double getSalaire_journalier() {
		return salaire_journalier;
	}
	public void setSalaire_journalier(double salaire_journalier) {
		this.salaire_journalier = salaire_journalier;
	}
	public Integer getNb_enfant() {
		return nb_enfant;
	}
	public void setNb_enfant(Integer nb_enfant) {
		this.nb_enfant = nb_enfant;
	}
	public String getRIB() {
		return RIB;
	}
	public void setRIB(String rIB) {
		RIB = rIB;
	}
	 
	 
	public Integer getNb_enfant_enCharge() {
		return nb_enfant_enCharge;
	}
	public void setNb_enfant_enCharge(Integer nb_enfant_enCharge) {
		this.nb_enfant_enCharge = nb_enfant_enCharge;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	 
	public String getBanque() {
		return banque;
	}
	public void setBanque(String banque) {
		this.banque = banque;
	}
	public String getAgence() {
		return agence;
	}
	public void setAgence(String agence) {
		this.agence = agence;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public List<Note> getNotes() {
		return notes;
	}
	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public List<Avancegestat> getAvancess() {
		return avancess;
	}
	public void setAvancess(List<Avancegestat> avancess) {
		this.avancess = avancess;
	}
	public List<Pompiste> getPompistes() {
		return pompistes;
	}
	public void setPompistes(List<Pompiste> pompistes) {
		this.pompistes = pompistes;
	}
	 public List<Lignegestion> getLignegestions() {
		return lignegestions;
	}
	public void setLignegestions(List<Lignegestion> lignegestions) {
		this.lignegestions = lignegestions;
	}
	public List<Lignegestion> getLignegestion() {
		return lignegestion;
	}
	public void setLignegestion(List<Lignegestion> lignegestion) {
		this.lignegestion = lignegestion;
	}
	public List<Rendement> getListrendement() {
		return listrendement;
	}
	public void setListrendement(List<Rendement> listrendement) {
		this.listrendement = listrendement;
	} 
	 
	 
	
	public int compareTo(Object o) {
		try{
		Employee f = (Employee) o; 
		double total1=0;
		double total2=0;
		if(this.getListrendement()!=null && f.getListrendement()!=null) {
		for(Rendement r:this.getListrendement())
			 total1=r.getNbsemi()+r.getNbvoiture();
		for(Rendement r:f.getListrendement())
			 total2=r.getNbsemi()+r.getNbvoiture();
	        return (int) (total1 - total2) ;
		}
		else return 0;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public Date getDatecontrat() {
		return datecontrat;
	}
	public void setDatecontrat(Date datecontrat) {
		this.datecontrat = datecontrat;
	}
	public String getPathcontrat() {
		return pathcontrat;
	}
	public void setPathcontrat(String pathcontrat) {
		this.pathcontrat = pathcontrat;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getDateembauche() {
		return dateembauche;
	}
	public void setDateembauche(String dateembauche) {
		this.dateembauche = dateembauche;
	} 
	
	public List<Vetement> getVetements() {
		return vetements;
	}
	public void setVetements(List<Vetement> vetements) {
		this.vetements = vetements;
	}
	public double getSalaire_sup() {
		return salaire_sup;
	}
	public void setSalaire_sup(double salaire_sup) {
		this.salaire_sup = salaire_sup;
	}
	public String getTypedecontrat() {
		return typedecontrat;
	}
	public void setTypedecontrat(String typedecontrat) {
		this.typedecontrat = typedecontrat;
	}
	public List<LigneImageEmployee> getImagess() {
		return imagess;
	}
	public void setImagess(List<LigneImageEmployee> imagess) {
		this.imagess = imagess;
	} 
	
	
	@Transient
	private String  cins;
	@Transient
	private String photos;
public String getCins() {
		 
		
		return cins;
	}
	public void setCins(String cins) {
		this.cins = cins;
	}
	public String getPhotos() {
		return photos;
	}
	public void setPhotos(String photos) {
		this.photos = photos;
	}
	public double getNbjouannuel() {
		return nbjouannuel;
	}
	public void setNbjouannuel(double nbjouannuel) {
		this.nbjouannuel = nbjouannuel;
	}
	
	public double getNbjour() {
		if(nbjouannuel>0)
		nbjour=nbjouannuel/26;
		else nbjour=0;
		return nbjour;
	}

	public void setNbjour(double nbjour) {
		this.nbjour = nbjour;
	}
	
	
}
