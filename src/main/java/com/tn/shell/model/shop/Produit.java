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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tn.shell.model.lavage.AffectationFiltre;
import com.tn.shell.model.lavage.AffectationHuile;

@Entity
@Table(name = "Produit")

public class Produit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String code;
	private String nom;
	private String duree;
	private double stockfinannee = 0;
    private Integer codeshop;
    @Transient
    private String margebrut;
	private double stockinitial = 0;
	private double quantitestock = 0;
	@Transient
	private double prixachattc;
	@Transient
	private double prixventeht;
	@Transient
	private double quantiteprevu;
	@Transient
	private String quantitestocks;
	private double quantitedepot = 0;
	@Transient
	private String quantitedepots;
	private double quantite;
	@Transient
	private double quantitetotal;
	 
	private double achat;
	private Integer tva;
	private double marge;
	private double vente;
	@Transient
	private String pourcent;
	private Integer qtemin;
	@Transient
	private double quantites;
    @Transient
    private String quantitetr;
    @Transient
    private String quantitetv;
    @Transient
	private double montant;
    @Transient
   	private double montantv;
	@Transient
	private String montants;
	@Transient
	private String montantvs;
	@Enumerated(EnumType.STRING)
	private Statut statut = Statut.ACTIF;
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "codefamille")
	private Famillearticle famille;
	@Transient
	private String prixventes;

	@Transient
	private String prixachats;
	@Transient
	private String marges;

	@OneToMany(mappedBy = "produit", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Lignealimentation> Lignealimentations;

	@OneToMany(mappedBy = "produit", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Lignevente> Ligneventes;

	@OneToMany(mappedBy = "produit", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Lignetransert> lignetransferts;

	@OneToMany(mappedBy = "produit", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<AffectationFiltre> affectationFiltres;
	@OneToMany(mappedBy = "produit", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<AffectationHuile> affectationHuiles;

	@Transient
	private Integer nbAvoir;
	@Transient
	private Integer nbAchat;
	@Transient
	private Integer Numfac;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getAchat() {
		return achat;
	}

	public void setAchat(double achat) {
		this.achat = achat;
	}

	public double getVente() {
		return vente;
	}

	public void setVente(double vente) {
		this.vente = vente;
	}

	public Integer getQtemin() {
		return qtemin;
	}

	public void setQtemin(Integer qtemin) {
		this.qtemin = qtemin;
	}

	public double getQuantites() {
		
		return quantites;
	}

	public void setQuantites(double quantites) {
		this.quantites = quantites;
	}

	public void setQuantitetotal(Integer quantitetotal) {
		this.quantitetotal = quantitetotal;
	}

	public void setQuantites(Integer quantites) {
		this.quantites = quantites;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTva() {
		return tva;
	}

	public void setTva(Integer tva) {
		this.tva = tva;
	}

	public double getMarge() {
		return marge;
	}

	public void setMarge(double marge) {
		this.marge = marge;
	}

	public Famillearticle getFamille() {
		return famille;
	}

	public void setFamille(Famillearticle famille) {
		this.famille = famille;
	}

	public String getPrixventes() {
		DecimalFormat df = new DecimalFormat("0.000");
		prixventes = df.format(vente);
		return prixventes;
	}

	public void setPrixventes(String prixventes) {
		this.prixventes = prixventes;
	}

	public String getPrixachats() {
		DecimalFormat df = new DecimalFormat("0.000");
		prixachats = df.format(achat);
		return prixachats;
	}

	public void setPrixachats(String prixachats) {
		this.prixachats = prixachats;
	}

	public String getMarges() {
		DecimalFormat df = new DecimalFormat("0.000");
		marges = df.format(marge);
		return marges;
	}

	public void setMarges(String marges) {
		this.marges = marges;
	}

	public List<Lignealimentation> getLignealimentations() {
		return Lignealimentations;
	}

	public void setLignealimentations(List<Lignealimentation> lignealimentations) {
		Lignealimentations = lignealimentations;
	}

	public double getQuantitestock() {
		return quantitestock;
	}

	public void setQuantitestock(double quantitestock) {
		this.quantitestock = quantitestock;
	}

	public double getQuantitedepot() {
		return quantitedepot;
	}

	public void setQuantitedepot(double quantitedepot) {
		this.quantitedepot = quantitedepot;
	}

	public double getQuantite() {
		quantite=quantitedepot+quantitestock;
		return quantite;
	}

	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}

	public double getMontant() {
	 
		return montant;
	}

	public void setMontant(double montant) {
		
		this.montant = montant;
	}

	public String getMontants() {
		DecimalFormat df = new DecimalFormat("0.000");
		montants = df.format(montant);
		return montants;
	}

	public void setMontants(String montants) {
		this.montants = montants;
	}

	public List<Lignetransert> getLignetransferts() {
		return lignetransferts;
	}

	public void setLignetransferts(List<Lignetransert> lignetransferts) {
		this.lignetransferts = lignetransferts;
	}

	public List<Lignevente> getLigneventes() {
		return Ligneventes;
	}

	public void setLigneventes(List<Lignevente> ligneventes) {
		Ligneventes = ligneventes;
	}

	public Integer getNbAvoir() {
		return nbAvoir;
	}

	public void setNbAvoir(Integer nbAvoir) {
		this.nbAvoir = nbAvoir;
	}

	public Integer getNbAchat() {
		return nbAchat;
	}

	public void setNbAchat(Integer nbAchat) {
		this.nbAchat = nbAchat;
	}

	public Integer getNumfac() {
		return Numfac;
	}

	public void setNumfac(Integer numfac) {
		Numfac = numfac;
	}

	public double getQuantitetotal() {
		quantitetotal = quantitedepot + quantitestock;
		return quantitetotal;
	}

	/*public List<Ligneventecredit> getLigneventeboncredits() {
		return ligneventeboncredits;
	}

	public void setLigneventecredits(List<Ligneventecredit> ligneventeboncredits) {
		this.ligneventeboncredits = ligneventeboncredits;
	}*/

	public void setQuantitetotal(double quantitetotal) {
		this.quantitetotal = quantitetotal;
	}

	public Integer getCodeshop() {
		return codeshop;
	}

	public void setCodeshop(Integer codeshop) {
		this.codeshop = codeshop;
	}

	public String getQuantitestocks() {
		DecimalFormat df = new DecimalFormat("0");
		quantitestocks=df.format(quantitestock);
		return quantitestocks;
	}

	public void setQuantitestocks(String quantitestocks) {
		
		this.quantitestocks = quantitestocks;
	}

	public String getQuantitedepots() {
		DecimalFormat df = new DecimalFormat("0");
		quantitedepots=df.format(quantitedepot);
		return quantitedepots;
	}

	public void setQuantitedepots(String quantitedepots) {
		
		this.quantitedepots = quantitedepots;
	}

	public String getQuantitetr() {
		DecimalFormat df = new DecimalFormat("0");
		double q=quantitedepot+quantitestock;
		quantitetr=df.format(q);
		return quantitetr;
	}

	public double getMontantv() {
	 
		return montantv;
	}

	public void setMontantv(double montantv) {
		this.montantv = montantv;
	}

	public String getMontantvs() {
		return montantvs;
	}

	public void setMontantvs(String montantvs) {
		DecimalFormat df = new DecimalFormat("0.000");
		montantvs = df.format(montantv);
		this.montantvs = montantvs;
	}

	public void setQuantitetr(String quantitetr) {
		this.quantitetr = quantitetr;
	}

	public double getPrixachattc() {
		return prixachattc;
	}

	public void setPrixachattc(double prixachattc) {
		this.prixachattc = prixachattc;
	}

	public double getPrixventeht() {
		return prixventeht;
	}

	public void setPrixventeht(double prixventeht) {
		this.prixventeht = prixventeht;
	}

	public String getQuantitetv() {
		return quantitetv;
	}

	public void setQuantitetv(String quantitetv) {
		this.quantitetv = quantitetv;
	}

	public String getMargebrut() {
		return margebrut;
	}

	public void setMargebrut(String margebrut) {
		this.margebrut = margebrut;
	}

	public String getPourcent() {
		return pourcent;
	}

	public void setPourcent(String pourcent) {
		this.pourcent = pourcent;
	}

	public double getStockinitial() {
		return stockinitial;
	}

	public void setStockinitial(double stockinitial) {
		this.stockinitial = stockinitial;
	}

	public double getQuantiteprevu() {
		return quantiteprevu;
	}

	public void setQuantiteprevu(double quantiteprevu) {
		this.quantiteprevu = quantiteprevu;
	}

	public List<AffectationFiltre> getAffectationFiltres() {
		return affectationFiltres;
	}

	public void setAffectationFiltres(List<AffectationFiltre> affectationFiltres) {
		this.affectationFiltres = affectationFiltres;
	}

	public List<AffectationHuile> getAffectationHuiles() {
		return affectationHuiles;
	}

	public void setAffectationHuiles(List<AffectationHuile> affectationHuiles) {
		this.affectationHuiles = affectationHuiles;
	}

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

	public double getStockfinannee() {
		return stockfinannee;
	}

	public void setStockfinannee(double stockfinannee) {
		this.stockfinannee = stockfinannee;
	}
	
	

	 

}
