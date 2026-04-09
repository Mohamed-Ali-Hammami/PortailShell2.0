package com.tn.shell.model.gestat;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Caisse")
public class Caisse {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Enumerated(EnumType.STRING)
	private Poste poste;
	private Date date;
	private String pompiste1;
	private String pompiste2;
	private String remarques;
	private String dates;
	@Transient
	private String totalAchatcarburant;
    
	@Enumerated(EnumType.STRING)
	private Statut statut = Statut.ACTIF;
	@OneToMany(mappedBy = "caisse", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Credit> creditclient;

	@OneToMany(mappedBy = "caisse", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Pompiste> pompiste;

	@OneToMany(mappedBy = "caisse", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Ligneindex> ligneindexs;

	@OneToMany(mappedBy = "caisse", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Creditanterieur> creditanterieur;

	@OneToMany(mappedBy = "caisse", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Depensegestat> depense;

	@OneToMany(mappedBy = "caisse", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Retourcuve> listretourcuve;

	@OneToMany(mappedBy = "caisse", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Achatcaisse> achatcaisse;

	@OneToMany(mappedBy = "caisse", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Cheque> Cheque;

	@OneToMany(mappedBy = "caisse", cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	private List<Avancegestat> Avance;

	private double totalventecarburant;
	private double totalventesaccessoire;
	private double totalventeslubrifiant;
	private double totalventesfiltre;
	private double totallavage;
	private double Totalventesmagasin;
	private double totalcreditanterieur;
	private double totalespece;
	private double totalcredit;
	private double totalcheque;
	private double totalbonstation;
	private double totaldepense;
	private double retourcuve;
	private double carteshell;
	private double cartebanquaire;
	private double totalmanque;
	private double totalachat;
	private double observation;
	private double totalcaisse;
	private double totallitrage;
	private double shop;
	private double totalespececomptable;
	private double totalespeceshop;
	private double totalespeceq1;
	private double totalespeceq2;
	private double totalespeceq3;
	private double totalventemagasin;

	@Transient
	private String totalventeMagasins;
	@Transient
	private String totalespeceqs1;
	@Transient
	private String totalespeceqs2;
	@Transient
	private String totalespeceqs3;
	@Transient
	private String totalespeceshops;
	@Transient
	private String totalespececomptables;
	@Transient
	private String observations;
	@Transient
	private String totalventeCarburants;
	@Transient
	private String totalVentesAccessoires;
	@Transient
	private String totalVentesLubrifiants;
	@Transient
	private String totalVentesFiltres;
	@Transient
	private String totalLavages;
	@Transient
	private String TotalVentesMagasins;
	@Transient
	private String totalCreditAnterieurs;
	@Transient
	private String totalespeces;
	@Transient
	private String totalCredits;
	@Transient
	private String totalCheques;
	@Transient
	private String totalBonStations;
	@Transient
	private String totalDepenses;
	@Transient
	private String retourCuves;
	@Transient
	private String carteShells;
	@Transient
	private String carteBanquaires;
	@Transient
	private String totalManques;
	@Transient
	private String totalcaisses;
	@Transient
	private String totalachats;
	@Transient
	private String totallitrages;
	@Transient
	private String shops;
	@Transient
	String total;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDates() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public String getRemarques() {

		return remarques;
	}

	public String getPompiste1() {
		return pompiste1;
	}

	public void setPompiste1(String pompiste1) {
		this.pompiste1 = pompiste1;
	}

	public String getPompiste2() {
		return pompiste2;
	}

	public void setPompiste2(String pompiste2) {
		this.pompiste2 = pompiste2;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Poste getPoste() {
		return poste;
	}

	public void setPoste(Poste poste) {
		this.poste = poste;
	}

	public List<Credit> getCreditclient() {
		return creditclient;
	}

	public void setCreditclient(List<Credit> creditclient) {
		this.creditclient = creditclient;
	}

	public List<Creditanterieur> getCreditanterieur() {
		return creditanterieur;
	}

	public void setCreditanterieur(List<Creditanterieur> creditanterieur) {
		this.creditanterieur = creditanterieur;
	}

	public List<Depensegestat> getDepense() {
		return depense;
	}

	public String getTotalcaisses() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalcaisses = df.format(totalcaisse);
		return totalcaisses;
	}

	public void setTotalcaisses(String totalcaisses) {
		this.totalcaisses = totalcaisses;
	}

	public void setRemarques(String remarques) {
		this.remarques = remarques;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public void setTotalventeCarburants(String totalventeCarburants) {
		this.totalventeCarburants = totalventeCarburants;
	}

	public void setTotalVentesAccessoires(String totalVentesAccessoires) {
		this.totalVentesAccessoires = totalVentesAccessoires;
	}

	public void setTotalVentesLubrifiants(String totalVentesLubrifiants) {
		this.totalVentesLubrifiants = totalVentesLubrifiants;
	}

	public void setTotalVentesFiltres(String totalVentesFiltres) {
		this.totalVentesFiltres = totalVentesFiltres;
	}

	public void setTotalLavages(String totalLavages) {
		this.totalLavages = totalLavages;
	}

	public void setTotalVentesMagasins(String totalVentesMagasins) {
		TotalVentesMagasins = totalVentesMagasins;
	}

	public void setTotalCreditAnterieurs(String totalCreditAnterieurs) {
		this.totalCreditAnterieurs = totalCreditAnterieurs;
	}

	public void setTotalespeces(String totalespeces) {
		this.totalespeces = totalespeces;
	}

	public void setTotalCredits(String totalCredits) {
		this.totalCredits = totalCredits;
	}

	public void setTotalCheques(String totalCheques) {
		this.totalCheques = totalCheques;
	}

	public void setTotalBonStations(String totalBonStations) {
		this.totalBonStations = totalBonStations;
	}

	public void setTotalDepenses(String totalDepenses) {
		this.totalDepenses = totalDepenses;
	}

	public void setRetourCuves(String retourCuves) {
		this.retourCuves = retourCuves;
	}

	public void setCarteShells(String carteShells) {
		this.carteShells = carteShells;
	}

	public void setCarteBanquaires(String carteBanquaires) {
		this.carteBanquaires = carteBanquaires;
	}

	public void setTotalManques(String totalManques) {
		this.totalManques = totalManques;
	}

	public List<Pompiste> getPompiste() {
		return pompiste;
	}

	public void setPompiste(List<Pompiste> pompiste) {
		this.pompiste = pompiste;
	}

	public void setDepense(List<Depensegestat> depense) {
		this.depense = depense;
	}

	public List<Achatcaisse> getAchatcaisse() {
		return achatcaisse;
	}

	public void setAchatcaisse(List<Achatcaisse> achatcaisse) {
		this.achatcaisse = achatcaisse;
	}

	public List<Cheque> getCheque() {
		return Cheque;
	}

	public void setCheque(List<Cheque> cheque) {
		Cheque = cheque;
	}

	public List<Avancegestat> getAvance() {
		return Avance;
	}

	public void setAvance(List<Avancegestat> avance) {
		Avance = avance;
	}

	public double getTotalLavage() {
		return totallavage;
	}

	public void setTotalLavage(double totallavage) {
		this.totallavage = totallavage;
	}

	public double getTotalespece() {
		return totalespece;
	}

	public void setTotalespece(double totalespece) {
		this.totalespece = totalespece;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public double getTotalachat() {
		return totalachat;
	}

	public void setTotalachat(double totalachat) {
		this.totalachat = totalachat;
	}

	public double getObservation() {

		return observation;
	}

	public void setObservation(double observation) {
		this.observation = observation;
	}

	public double getTotalcaisse() {

		return totalcaisse;
	}

	public void setTotalcaisse(double totalcaisse) {
		this.totalcaisse = totalcaisse;
	}

	public String getTotalventeCarburants() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalventeCarburants = df.format(totalventecarburant);
		return totalventeCarburants;
	}

	public String getTotalVentesAccessoires() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalVentesAccessoires = df.format(totalventesaccessoire);
		return totalVentesAccessoires;
	}

	public String getTotalVentesLubrifiants() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalVentesLubrifiants = df.format(totalventeslubrifiant);
		return totalVentesLubrifiants;
	}

	public String getTotalVentesFiltres() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalVentesFiltres = df.format(totalventesfiltre);
		return totalVentesFiltres;
	}

	public String getTotalachats() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalachats = df.format(totalachat);
		return totalachats;
	}

	public void setTotalachats(String totalachats) {
		this.totalachats = totalachats;
	}
	

	public String getTotallavages() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalLavages = df.format(totallavage);
		return totalLavages;
	}

	public String getTotalVentesMagasins() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		TotalVentesMagasins = df.format(Totalventesmagasin);
		return TotalVentesMagasins;
	}

	public String getTotalCreditAnterieurs() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalCreditAnterieurs = df.format(totalcreditanterieur);
		return totalCreditAnterieurs;
	}

	public String getTotalespeces() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalespeces = df.format(totalespece);
		return totalespeces;
	}

	public String getTotalCredits() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalCredits = df.format(totalcredit);
		return totalCredits;
	}

	public String getTotalCheques() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalCheques = df.format(totalcheque);
		return totalCheques;
	}

	public String getTotalBonStations() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalBonStations = df.format(totalbonstation);
		return totalBonStations;
	}

	public String getTotalDepenses() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalDepenses = df.format(totaldepense);
		return totalDepenses;
	}

	public String getRetourCuves() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		retourCuves = df.format(retourcuve);
		return retourCuves;
	}

	public String getCarteShells() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		carteShells = df.format(carteshell);
		return carteShells;
	}

	public String getCarteBanquaires() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		carteBanquaires = df.format(cartebanquaire);
		return carteBanquaires;
	}

	public String getTotalManques() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalManques = df.format(totalmanque);
		return totalManques;
	}

	public String getObservations() {
		DecimalFormat df = new DecimalFormat("0.000");
	 	observations = df.format(observation);
		return observations;
	}

	public double getTotallitrage() {
		return totallitrage;
	}

	public void setTotallitrage(double totallitrage) {
		this.totallitrage = totallitrage;
	}

	public String getTotallitrages() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totallitrages = df.format(totallitrage);
		return totallitrages;
	}

	public double getShop() {
		 
		return shop;
	}

	public void setShop(double shop) {
		this.shop = shop;
	}

	public String getShops() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		shops = df.format(shop);
		return shops;
	}

	public void setShops(String shops) {
		this.shops = shops;
	}

	public void setTotallitrages(String totallitrages) {
		this.totallitrages = totallitrages;
	}

	public double getTotalventecarburant() {

		return totalventecarburant;
	}

	public void setTotalventecarburant(double totalventecarburant) {
		this.totalventecarburant = totalventecarburant;
	}

	public double getTotalventesaccessoire() {
		return totalventesaccessoire;
	}

	public void setTotalventesaccessoire(double totalventesaccessoire) {
		this.totalventesaccessoire = totalventesaccessoire;
	}

	public double getTotalventeslubrifiant() {
		return totalventeslubrifiant;
	}

	public void setTotalventeslubrifiant(double totalventeslubrifiant) {
		this.totalventeslubrifiant = totalventeslubrifiant;
	}

	public double getTotalventesfiltre() {
		return totalventesfiltre;
	}

	public void setTotalventesfiltre(double totalventesfiltre) {
		this.totalventesfiltre = totalventesfiltre;
	}

	public double getTotallavage() {
		return totallavage;
	}

	public void setTotallavage(double totallavage) {
		this.totallavage = totallavage;
	}

	public double getTotalventesmagasin() {
		return Totalventesmagasin;
	}

	public void setTotalventesmagasin(double totalventesmagasin) {
		Totalventesmagasin = totalventesmagasin;
	}

	public double getTotalcreditanterieur() {
		return totalcreditanterieur;
	}

	public void setTotalcreditanterieur(double totalcreditanterieur) {
		this.totalcreditanterieur = totalcreditanterieur;
	}

	public double getTotalcredit() {
		return totalcredit;
	}

	public void setTotalcredit(double totalcredit) {
		this.totalcredit = totalcredit;
	}

	public double getTotalcheque() {
		return totalcheque;
	}

	public void setTotalcheque(double totalcheque) {
		this.totalcheque = totalcheque;
	}

	public double getTotalbonstation() {
		return totalbonstation;
	}

	public void setTotalbonstation(double totalbonstation) {
		this.totalbonstation = totalbonstation;
	}

	public double getTotaldepense() {
		return totaldepense;
	}

	public void setTotaldepense(double totaldepense) {
		this.totaldepense = totaldepense;
	}

	public double getRetourcuve() {
		return retourcuve;
	}

	public void setRetourcuve(double retourcuve) {
		this.retourcuve = retourcuve;
	}

	public double getCarteshell() {
		return carteshell;
	}

	public void setCarteshell(double carteshell) {
		this.carteshell = carteshell;
	}

	public double getCartebanquaire() {
		return cartebanquaire;
	}

	public void setCartebanquaire(double cartebanquaire) {
		this.cartebanquaire = cartebanquaire;
	}

	public double getTotalmanque() {
		return totalmanque;
	}

	public void setTotalmanque(double totalmanque) {
		this.totalmanque = totalmanque;
	}

	public String getTotalLavages() {
		return totalLavages;
	}

	public List<Retourcuve> getListretourcuve() {
		return listretourcuve;
	}

	public void setListretourcuve(List<Retourcuve> listretourcuve) {
		this.listretourcuve = listretourcuve;
	}

	public List<Ligneindex> getLigneindexs() {
		return ligneindexs;
	}

	public void setLigneindexs(List<Ligneindex> ligneindexs) {
		this.ligneindexs = ligneindexs;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public double getTotalespececomptable() {
		return totalespececomptable;
	}

	public void setTotalespececomptable(double totalespececomptable) {
		this.totalespececomptable = totalespececomptable;
	}

	public double getTotalespeceshop() {
		return totalespeceshop;
	}

	public void setTotalespeceshop(double totalespeceshop) {
		this.totalespeceshop = totalespeceshop;
	}

	public double getTotalespeceq1() {
		return totalespeceq1;
	}

	public void setTotalespeceq1(double totalespeceq1) {
		this.totalespeceq1 = totalespeceq1;
	}

	public double getTotalespeceq2() {
		return totalespeceq2;
	}

	public void setTotalespeceq2(double totalespeceq2) {
		this.totalespeceq2 = totalespeceq2;
	}

	public double getTotalespeceq3() {
		return totalespeceq3;
	}

	public void setTotalespeceq3(double totalespeceq3) {
		this.totalespeceq3 = totalespeceq3;
	}

	public String getTotalespeceqs1() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalespeceqs1 = df.format(totalespeceq1);
		return totalespeceqs1;
	}

	public void setTotalespeceqs1(String totalespeceqs1) {
		this.totalespeceqs1 = totalespeceqs1;
	}

	public String getTotalespeceqs2() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalespeceqs2 = df.format(totalespeceq2);
		return totalespeceqs2;
	}

	public void setTotalespeceqs2(String totalespeceqs2) {
		this.totalespeceqs2 = totalespeceqs2;
	}

	public String getTotalespeceqs3() {
		 
		DecimalFormat df = new DecimalFormat("#,###.000");
		/*totalespeceqs3 = df.format(totalespeceq3);*/
		//return totalespeceqs3;
		if(totalespeceq3>=1) {
			df=new DecimalFormat("#,###.000");
			totalespeceqs3=df.format(totalespeceq3);
		}
		else if(totalespeceq3<1) {
			df=new DecimalFormat("0.000");
			totalespeceqs3=df.format(totalespeceq3);
		}
		else totalespeceqs3="";
		return totalespeceqs3;
	}

	public void setTotalespeceqs3(String totalespeceqs3) {
		this.totalespeceqs3 = totalespeceqs3;
	}

	public String getTotalespeceshops() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalespeceshops = df.format(totalespeceshop);
		return totalespeceshops;
	}

	public void setTotalespeceshops(String totalespeceshops) {
		this.totalespeceshops = totalespeceshops;
	}

	public String getTotalespececomptables() {
		 
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalespececomptables = df.format(totalespececomptable);
		return totalespececomptables;
	}

	public void setTotalespececomptables(String totalespececomptables) {
		this.totalespececomptables = totalespececomptables;
	}

	public double getTotalventemagasin() {
		return totalventemagasin;
	}

	public void setTotalventemagasin(double totalventemagasin) {
		this.totalventemagasin = totalventemagasin;
	}

	public String getTotalventeMagasins() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalventeMagasins = df.format(totalventemagasin);
		return totalventeMagasins;
	}

	public void setTotalventeMagasins(String totalventeMagasins) {
		this.totalventeMagasins = totalventeMagasins;
	}

	public String getTotalAchatcarburant() {
		return totalAchatcarburant;
	}

	public void setTotalAchatcarburant(String totalAchatcarburant) {
		this.totalAchatcarburant = totalAchatcarburant;
	}

	 
	
	

}
