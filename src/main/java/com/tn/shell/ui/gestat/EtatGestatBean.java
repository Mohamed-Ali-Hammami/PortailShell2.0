package com.tn.shell.ui.gestat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.tn.shell.model.gestat.Achatcaisse;
import com.tn.shell.model.gestat.Achatcarburant;
import com.tn.shell.model.gestat.Caisse;
import com.tn.shell.model.gestat.Cheque;
import com.tn.shell.model.gestat.Depensegestat;
import com.tn.shell.model.gestat.Diverachat;
import com.tn.shell.model.gestat.Factureachatcarburant;
import com.tn.shell.model.gestat.Familledepensegestat;
import com.tn.shell.model.gestat.Ligneventecredit;
import com.tn.shell.model.gestat.Poste;
import com.tn.shell.model.shop.Famillearticle;
import com.tn.shell.model.shop.Fournisseur;
import com.tn.shell.model.shop.Produit;

@ManagedBean(name = "EtatGestatBean")
@SessionScoped
public class EtatGestatBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	
	private List<Achatcaisse> listAchatESP;
	private List<Depensegestat> listDepense;
	private String matricule;
	private List<Factureachatcarburant> listfactureachat;
	private Date date1;
	private Date date2;
	private String dates1;
	private String dates2;
	private List<Produit> listProduit;
	private String totalCA;
	private String totalProfil;
	private String totaCA;
	private String totaltva;
	private String caAvoir;
	private String tvaAvoir;
	private String caAchat;
	private String tvaAchat;
	private String totalProfilBrut;
	private List<Famillearticle> listFamille;
	private List<String> selectedfamilles;
	private List<Famillearticle> listfamile;
	private List<Ligneventecredit> listelignevente;
	private List<Familledepensegestat> familledepense;
	private Poste[] postes;
	private Poste poste;
	private String dates;
	private Date date;
	private Fournisseur fournisseur;
	private String nomfour;
	private List<Fournisseur> listefour;
	private List<Cheque> listecheque;
	private double montantcheque;
	private String montantcheques;
	private List<Caisse> listcaisse;
	private Caisse caisse;
	private Diverachat listAchat;
	private List<Achatcarburant> listachat;

	public String etatdebanque() {
		date1 = new Date();
		date2 = new Date();
		return SUCCESS;
	}
	
	public String etatfactureAchat() {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		date1 = new Date();
		date2 = new Date();
		dates1 = dateFormat.format(date1);
		dates2 = dateFormat.format(date2);
		listachat = new ArrayList<Achatcarburant>(); 
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	

	public List<Achatcaisse> getListAchatESP() {
		return listAchatESP;
	}

	public void setListAchatESP(List<Achatcaisse> listAchatESP) {
		this.listAchatESP = listAchatESP;
	}

	public List<Depensegestat> getListDepense() {
		return listDepense;
	}

	public void setListDepense(List<Depensegestat> listDepense) {
		this.listDepense = listDepense;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public List<Factureachatcarburant> getListfactureachat() {
		return listfactureachat;
	}

	public void setListfactureachat(List<Factureachatcarburant> listfactureachat) {
		this.listfactureachat = listfactureachat;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public String getDates1() {
		return dates1;
	}

	public void setDates1(String dates1) {
		this.dates1 = dates1;
	}

	public String getDates2() {
		return dates2;
	}

	public void setDates2(String dates2) {
		this.dates2 = dates2;
	}

	public List<Produit> getListProduit() {
		return listProduit;
	}

	public void setListProduit(List<Produit> listProduit) {
		this.listProduit = listProduit;
	}

	public String getTotalCA() {
		return totalCA;
	}

	public void setTotalCA(String totalCA) {
		this.totalCA = totalCA;
	}

	public String getTotalProfil() {
		return totalProfil;
	}

	public void setTotalProfil(String totalProfil) {
		this.totalProfil = totalProfil;
	}

	public String getTotaCA() {
		return totaCA;
	}

	public void setTotaCA(String totaCA) {
		this.totaCA = totaCA;
	}

	public String getTotaltva() {
		return totaltva;
	}

	public void setTotaltva(String totaltva) {
		this.totaltva = totaltva;
	}

	public String getCaAvoir() {
		return caAvoir;
	}

	public void setCaAvoir(String caAvoir) {
		this.caAvoir = caAvoir;
	}

	public String getTvaAvoir() {
		return tvaAvoir;
	}

	public void setTvaAvoir(String tvaAvoir) {
		this.tvaAvoir = tvaAvoir;
	}

	public String getCaAchat() {
		return caAchat;
	}

	public void setCaAchat(String caAchat) {
		this.caAchat = caAchat;
	}

	public String getTvaAchat() {
		return tvaAchat;
	}

	public void setTvaAchat(String tvaAchat) {
		this.tvaAchat = tvaAchat;
	}

	public String getTotalProfilBrut() {
		return totalProfilBrut;
	}

	public void setTotalProfilBrut(String totalProfilBrut) {
		this.totalProfilBrut = totalProfilBrut;
	}

	public List<Famillearticle> getListFamille() {
		return listFamille;
	}

	public void setListFamille(List<Famillearticle> listFamille) {
		this.listFamille = listFamille;
	}

	public List<String> getSelectedfamilles() {
		return selectedfamilles;
	}

	public void setSelectedfamilles(List<String> selectedfamilles) {
		this.selectedfamilles = selectedfamilles;
	}

	public List<Famillearticle> getListfamile() {
		return listfamile;
	}

	public void setListfamile(List<Famillearticle> listfamile) {
		this.listfamile = listfamile;
	}

	public List<Ligneventecredit> getListelignevente() {
		return listelignevente;
	}

	public void setListelignevente(List<Ligneventecredit> listelignevente) {
		this.listelignevente = listelignevente;
	}

	public List<Familledepensegestat> getFamilledepense() {
		return familledepense;
	}

	public void setFamilledepense(List<Familledepensegestat> familledepense) {
		this.familledepense = familledepense;
	}

	public Poste[] getPostes() {
		return postes;
	}

	public void setPostes(Poste[] postes) {
		this.postes = postes;
	}

	public Poste getPoste() {
		return poste;
	}

	public void setPoste(Poste poste) {
		this.poste = poste;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public String getNomfour() {
		return nomfour;
	}

	public void setNomfour(String nomfour) {
		this.nomfour = nomfour;
	}

	public List<Fournisseur> getListefour() {
		return listefour;
	}

	public void setListefour(List<Fournisseur> listefour) {
		this.listefour = listefour;
	}

	public List<Cheque> getListecheque() {
		return listecheque;
	}

	public void setListecheque(List<Cheque> listecheque) {
		this.listecheque = listecheque;
	}

	public double getMontantcheque() {
		return montantcheque;
	}

	public void setMontantcheque(double montantcheque) {
		this.montantcheque = montantcheque;
	}

	public String getMontantcheques() {
		return montantcheques;
	}

	public void setMontantcheques(String montantcheques) {
		this.montantcheques = montantcheques;
	}

	public List<Caisse> getListcaisse() {
		return listcaisse;
	}

	public void setListcaisse(List<Caisse> listcaisse) {
		this.listcaisse = listcaisse;
	}

	public Caisse getCaisse() {
		return caisse;
	}

	public void setCaisse(Caisse caisse) {
		this.caisse = caisse;
	}

	public Diverachat getListAchat() {
		return listAchat;
	}

	public void setListAchat(Diverachat listAchat) {
		this.listAchat = listAchat;
	}

	public List<Achatcarburant> getListachat() {
		return listachat;
	}

	public void setListachat(List<Achatcarburant> listachat) {
		this.listachat = listachat;
	}
	
	
	
	
}
