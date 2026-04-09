package com.tn.shell.ui.banque;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.tn.shell.model.banque.Banque;
import com.tn.shell.model.banque.Compte;
import com.tn.shell.model.banque.Enumcheque;
import com.tn.shell.model.banque.Reglement;
import com.tn.shell.model.banque.Transaction;
import com.tn.shell.model.banque.TypeTransaction;
import com.tn.shell.service.banque.ServiceBanque;
import com.tn.shell.service.banque.ServiceCompte;
import com.tn.shell.service.banque.ServiceTransaction;

@ManagedBean(name = "BanqueBean")
@SessionScoped
public class BanqueBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	@ManagedProperty(value = "#{ServiceBanque}")
	ServiceBanque serviceBanque;
	@ManagedProperty(value = "#{ServiceCompte}")
	ServiceCompte serviceCompte;
	@ManagedProperty(value = "#{ServiceTransaction}")
	ServiceTransaction serviceTransaction;
    private Compte compte;
	private Banque banque;
	private String nom;
	private String agence;
	private String adresse;
	private List<Banque> listBanque;
	private List<Compte> listCompte;
	private Date date1;
	private  Date date;
	private Date date2;
	private Date date3;
	private Reglement[] reglements;
	private Reglement reglement;
	private TypeTransaction[] types;
	private Enumcheque[] etatcheques;
	private Enumcheque etatcheque;
	private TypeTransaction type;
	private String reference;
	//info compte
	private String numerodecompte;
	private BigDecimal solde;
	private BigDecimal montant;
	private BigDecimal soleinitial;
	private BigDecimal soldecredit;
	private BigDecimal soldedebit;
	private BigDecimal soldeReelBiat;
	private BigDecimal totalChequeEncours;
	private BigDecimal totalChequeImpayeeBiat;
	private BigDecimal totalChequeAntidateBiat;
	private BigDecimal totalChequeEncoursBiat;	
	private BigDecimal totalChequeImpayeeBZ;
	private BigDecimal totalChequeAntidateBZ;
	private BigDecimal totalChequeEncoursBZ;
	private BigDecimal  totalChequePreavisBiat;
	private List<Transaction> listtransaction;
	private Transaction selectedTranaction;
	private String dates;
	private String description;
	
	private List<Transaction> listtChequeCirculationBiat;
	private List<Transaction> listtChequeCirculationBZ;
	
	private List<Transaction> listtChequeImpayeeBiat;
	private List<Transaction> listtChequeImpayeeBZ;
	
	
	private List<Transaction> listtChequeAntidateeBiat;
	private List<Transaction> listtChequePreavisBiat;
	private List<Transaction> listtChequeAntidateeBZ;
	
	private Compte compteBiat;
	private Compte compteBZ;
public String ajouterTransaction() {
	 description=null; 
	 montant=null;
	 reglement=null;
	 type=null;	 
	 reference=null;
	listtransaction=serviceTransaction.findbyMonth(date1.getMonth()+1,compte);
		return SUCCESS;
	}
	public String success() {
		
		return SUCCESS;
	}
	public String extrait() { 
		reference="";
		date=new Date();
		totalChequeEncours=new BigDecimal(0);
		totalChequeImpayeeBiat=new BigDecimal(0);
		totalChequePreavisBiat=new BigDecimal(0);
		totalChequeImpayeeBZ=new BigDecimal(0);
		totalChequeAntidateBZ=new BigDecimal(0);
		totalChequeAntidateBiat=new BigDecimal(0);
		soldeReelBiat=new BigDecimal(0);
		totalChequeEncoursBZ=new BigDecimal(0);
	    totalChequeEncoursBiat=new BigDecimal(0);
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		dates=f.format(date);
		listtChequeImpayeeBiat=new ArrayList<Transaction>();
		listtChequeImpayeeBZ=new ArrayList<Transaction>();
		listtChequeAntidateeBiat=new ArrayList<Transaction>();
		listtChequeAntidateeBZ=new ArrayList<Transaction>();
		listtChequeCirculationBiat=new ArrayList<Transaction>();
		listtChequeCirculationBZ=new ArrayList<Transaction>();
		compteBZ=serviceCompte.Findbynom("08081023082000051554");
		compteBiat=serviceCompte.Findbynom("080810230810000495");
		
		listtChequePreavisBiat=new ArrayList<Transaction>();
		listtChequePreavisBiat=serviceTransaction.findByEnumarationCeque(Enumcheque.Preavis, compteBiat);
		 if(listtChequePreavisBiat!=null)
				for(Transaction t:listtChequePreavisBiat)
					totalChequePreavisBiat=totalChequePreavisBiat.add(t.getMontant());
		 
		
		 
		listtChequeImpayeeBiat=serviceTransaction.findByEnumarationCeque(Enumcheque.Impayes, compteBiat);		
        if(listtChequeImpayeeBiat!=null)
		for(Transaction t:listtChequeImpayeeBiat)
			totalChequeImpayeeBiat=totalChequeImpayeeBiat.add(t.getMontant());
    	listtChequeAntidateeBiat=serviceTransaction.findByEnumarationCeque(Enumcheque.Antidate, compteBiat);
		if(listtChequeAntidateeBiat!=null)
			for(Transaction t:listtChequeAntidateeBiat)
				totalChequeAntidateBiat=totalChequeAntidateBiat.add(t.getMontant());
		
		listtChequeCirculationBiat=serviceTransaction.findByEnumarationCeque(Enumcheque.EnCirculation, compteBiat);
		if(listtChequeCirculationBiat!=null)
			for(Transaction t:listtChequeCirculationBiat)
				totalChequeEncoursBiat=totalChequeEncoursBiat.add(t.getMontant());
        
        if(compteBZ!=null) {
		listtChequeImpayeeBZ=serviceTransaction.findByEnumarationCeque(Enumcheque.Impayes, compteBZ);
		 if(listtChequeImpayeeBZ!=null)
				for(Transaction t:listtChequeImpayeeBZ)
					totalChequeImpayeeBZ=totalChequeImpayeeBZ.add(t.getMontant());
		 
	
		
		listtChequeAntidateeBZ=serviceTransaction.findByEnumarationCeque(Enumcheque.Antidate, compteBZ);
		if(listtChequeAntidateeBZ!=null)
			for(Transaction t:listtChequeAntidateeBZ)
				totalChequeAntidateBZ=totalChequeAntidateBZ.add(t.getMontant());
		
	
		
		listtChequeCirculationBZ=serviceTransaction.findByEnumarationCeque(Enumcheque.EnCirculation, compteBZ);
		if(listtChequeCirculationBZ!=null)
			for(Transaction t:listtChequeCirculationBZ)
				totalChequeEncoursBZ=totalChequeEncoursBZ.add(t.getMontant());
        }
    	List<Transaction> listtChequeEncoursBiat=new ArrayList<Transaction>();
		listtChequeEncoursBiat=serviceTransaction.findByEnumarationCeque(Enumcheque.Encours, compteBiat);
		if(listtChequeEncoursBiat!=null)
			for(Transaction t:listtChequeEncoursBiat)				 
				totalChequeEncours.add(t.getMontant());
		soldeReelBiat=compteBiat.getSolde().subtract(totalChequeEncoursBiat);
		 return SUCCESS;
	}
	
	
	public String getchequeImpayees() {		
		listtransaction=new ArrayList<Transaction>();
		listtransaction=serviceTransaction.findByEnumarationCeque(Enumcheque.Impayes,compte);
		return SUCCESS;
	}
	
	public String getchequeEntree() {
		listtransaction=new ArrayList<Transaction>();
		listtransaction=serviceTransaction.findByEnumarationCeque(Enumcheque.Entree,compte);
		return SUCCESS;
	}
	
	public String getchequePreavis() {
		listtransaction=new ArrayList<Transaction>();
		listtransaction=serviceTransaction.findByEnumarationCeque(Enumcheque.Preavis,compte);
		return SUCCESS;
	}
	
	
	public String getchequeAntidatee() {		
		listtransaction=new ArrayList<Transaction>();
		listtransaction=serviceTransaction.findByEnumarationCeque(Enumcheque.Antidate,compte);
		return SUCCESS;
	}
	
	public String getchequeCierculations() {		
		listtransaction=new ArrayList<Transaction>();
		listtransaction=serviceTransaction.findByEnumarationCeque(Enumcheque.EnCirculation,compte);
		return SUCCESS;
	}
	
	
	

	public String getchequeencours() {		
		listtransaction=new ArrayList<Transaction>();
		listtransaction=serviceTransaction.findByEnumarationCeque(Enumcheque.Encours,compte);
		return SUCCESS;
	}
	
	
	
	public String valider() {
		
		try {
		banque=serviceBanque.Findbynom(nom);
		compte=banque.getCompte();
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		listtransaction=new ArrayList<Transaction>();
		date1=new Date();
		date2=new Date();
		date3=new Date();
		dates=f.format(date2);
		listtransaction=serviceTransaction.findbyMonth(date1.getMonth()+1,compte);
			types=TypeTransaction.values();
			reglements=Reglement.values();
			etatcheques=Enumcheque.values();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String updateoperation() {
		//compte=serviceCompte.Findbycode(banque.getCompte().getId());
		selectedTranaction.setEtatcheque(etatcheque);
		type=selectedTranaction.getTypetransaction();
		if(etatcheque.equals(Enumcheque.Payes)) {
			 
			if(type.equals(TypeTransaction.Credit)) {
				compte.setSolde(compte.getSolde().add(selectedTranaction.getMontant()));
				compte.setSoldecredit(compte.getSoldecredit().add(selectedTranaction.getMontant()));
			}
			else
			{
				compte.setSolde(compte.getSolde().subtract(selectedTranaction.getMontant()));
				compte.setSoldedebit(compte.getSoldedebit().subtract(selectedTranaction.getMontant()));
			 }
			
			
			
			
		}
		selectedTranaction.setDateOperation(date3);
		serviceTransaction.update(selectedTranaction);
		serviceCompte.update(compte);
		return SUCCESS;
	
	}
	public String saveoperation() {
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		Transaction t=new  Transaction();
		t.setDescription(description);
		t.setCompte(compte);
		t.setMontant(montant);
		t.setReglement(reglement);
		t.setTypetransaction(type);
		t.setDate(new Date());
		t.setReference(reference);
		t.setDates(f.format(new Date()));	 
		if(reglement.equals(Reglement.Cheque))
			t.setEtatcheque(Enumcheque.EnCirculation);
		else {
		if(type.equals(TypeTransaction.Credit)) {
			compte.setSolde(compte.getSolde().add(montant));
			compte.setSoldecredit(compte.getSoldecredit().add(montant));
		}
		else
		{
			compte.setSolde(compte.getSolde().subtract(montant));
			compte.setSoldedebit(compte.getSoldedebit().subtract(montant));
		 }
		
		serviceCompte.update(compte);
		}
		
		 
		
     	serviceTransaction.save(t);
     	listtransaction=serviceTransaction.findbyMonth(date1.getMonth()+1,compte);
		return SUCCESS;
	}
	public String recherchertransaction() {
		 
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		listtransaction=new ArrayList<Transaction>();
	 
		if(f.format(date1).equals(f.format(date2))) {
			listtransaction=serviceTransaction.findbyMonth(date1.getMonth()+1,compte);
			
		}
		else {
			listtransaction=serviceTransaction.findbyDate(date1, date2,compte);
		}
		return SUCCESS;
	}
	
	
	public String getAll() {
		listBanque=new ArrayList<Banque>();
		listBanque=serviceBanque.getAll();
		return SUCCESS;
	}
	public String nouveauBanque() {
		nom=null;
		agence=null;
		adresse=null;
		return SUCCESS;
	}
	public String saveBanque() {
		banque=new Banque();
		banque.setAdresse(adresse);
		banque.setNom(nom);
		banque.setAgence(agence);
		serviceBanque.save(banque);
		listBanque=new ArrayList<Banque>();
		listBanque=serviceBanque.getAll();
		numerodecompte=null;
		solde=new BigDecimal(0);
		soleinitial=new BigDecimal(0);
		soldecredit=new BigDecimal(0);
		soldedebit=new BigDecimal(0);
		return SUCCESS;
	}
	
	public String saveCompte() {		 
		compte=new Compte();	
		 compte.setNumerodecompte(numerodecompte);
		compte.setSolde(solde);
		compte.setSoleinitial(soleinitial);
		compte.setSoldecredit(soldecredit);
		compte.setSoldedebit(soldedebit);
		serviceCompte.save(compte);
		banque.setCompte(compte);
		serviceBanque.update(banque);
		listCompte=new ArrayList<Compte>();
		listCompte=serviceCompte.getAll();
		return SUCCESS;
	}
	
	
	
	public String nouveaunouveauTransaction() {
		return SUCCESS;
	}
	
	
	
	
	
	public ServiceBanque getServiceBanque() {
		return serviceBanque;
	}
	public void setServiceBanque(ServiceBanque serviceBanque) {
		this.serviceBanque = serviceBanque;
	}
	public Banque getBanque() {
		return banque;
	}
	public void setBanque(Banque banque) {
		this.banque = banque;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAgence() {
		return agence;
	}
	public void setAgence(String agence) {
		this.agence = agence;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public List<Banque> getListBanque() {
		return listBanque;
	}
	public void setListBanque(List<Banque> listBanque) {
		this.listBanque = listBanque;
	}


	public List<Compte> getListCompte() {
		return listCompte;
	}


	public void setListCompte(List<Compte> listCompte) {
		this.listCompte = listCompte;
	}


	public Compte getCompte() {
		return compte;
	}


	public void setCompte(Compte compte) {
		this.compte = compte;
	}


	public ServiceCompte getServiceCompte() {
		return serviceCompte;
	}


	public void setServiceCompte(ServiceCompte serviceCompte) {
		this.serviceCompte = serviceCompte;
	}


	public String getNumerodecompte() {
		return numerodecompte;
	}


	public void setNumerodecompte(String numerodecompte) {
		this.numerodecompte = numerodecompte;
	}


	public BigDecimal getSolde() {
		return solde;
	}


	public void setSolde(BigDecimal solde) {
		this.solde = solde;
	}


	public BigDecimal getSoleinitial() {
		return soleinitial;
	}


	public void setSoleinitial(BigDecimal soleinitial) {
		this.soleinitial = soleinitial;
	}


	public BigDecimal getSoldecredit() {
		return soldecredit;
	}


	public void setSoldecredit(BigDecimal soldecredit) {
		this.soldecredit = soldecredit;
	}


	public BigDecimal getSoldedebit() {
		return soldedebit;
	}


	public void setSoldedebit(BigDecimal soldedebit) {
		this.soldedebit = soldedebit;
	}


	public ServiceTransaction getServiceTransaction() {
		return serviceTransaction;
	}


	public void setServiceTransaction(ServiceTransaction serviceTransaction) {
		this.serviceTransaction = serviceTransaction;
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


	public List<Transaction> getListtransaction() {
		return listtransaction;
	}


	public void setListtransaction(List<Transaction> listtransaction) {
		this.listtransaction = listtransaction;
	}

	public Transaction getSelectedTranaction() {
		return selectedTranaction;
	}

	public void setSelectedTranaction(Transaction selectedTranaction) {
		this.selectedTranaction = selectedTranaction;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Reglement[] getReglements() {
		return reglements;
	}

	public void setReglements(Reglement[] reglements) {
		this.reglements = reglements;
	}

	public Reglement getReglement() {
		return reglement;
	}

	public void setReglement(Reglement reglement) {
		this.reglement = reglement;
	}

	public TypeTransaction[] getTypes() {
		return types;
	}

	public void setTypes(TypeTransaction[] types) {
		this.types = types;
	}

	public TypeTransaction getType() {
		return type;
	}

	public void setType(TypeTransaction type) {
		this.type = type;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public BigDecimal getMontant() {
		return montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}
	public Enumcheque[] getEtatcheques() {
		return etatcheques;
	}
	public void setEtatcheques(Enumcheque[] etatcheques) {
		this.etatcheques = etatcheques;
	}
	public Enumcheque getEtatcheque() {
		return etatcheque;
	}
	public void setEtatcheque(Enumcheque etatcheque) {
		this.etatcheque = etatcheque;
	}
	public Date getDate3() {
		return date3;
	}
	public void setDate3(Date date3) {
		this.date3 = date3;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public List<Transaction> getListtChequeCirculationBiat() {
		return listtChequeCirculationBiat;
	}


	public void setListtChequeCirculationBiat(List<Transaction> listtChequeCirculationBiat) {
		this.listtChequeCirculationBiat = listtChequeCirculationBiat;
	}


	public List<Transaction> getListtChequeCirculationBZ() {
		return listtChequeCirculationBZ;
	}


	public void setListtChequeCirculationBZ(List<Transaction> listtChequeCirculationBZ) {
		this.listtChequeCirculationBZ = listtChequeCirculationBZ;
	}


	public List<Transaction> getListtChequeImpayeeBiat() {
		return listtChequeImpayeeBiat;
	}


	public void setListtChequeImpayeeBiat(List<Transaction> listtChequeImpayeeBiat) {
		this.listtChequeImpayeeBiat = listtChequeImpayeeBiat;
	}


	public List<Transaction> getListtChequeImpayeeBZ() {
		return listtChequeImpayeeBZ;
	}


	public void setListtChequeImpayeeBZ(List<Transaction> listtChequeImpayeeBZ) {
		this.listtChequeImpayeeBZ = listtChequeImpayeeBZ;
	}


	public List<Transaction> getListtChequeAntidateeBiat() {
		return listtChequeAntidateeBiat;
	}


	public void setListtChequeAntidateeBiat(List<Transaction> listtChequeAntidateeBiat) {
		this.listtChequeAntidateeBiat = listtChequeAntidateeBiat;
	}


	public List<Transaction> getListtChequeAntidateeBZ() {
		return listtChequeAntidateeBZ;
	}


	public void setListtChequeAntidateeBZ(List<Transaction> listtChequeAntidateeBZ) {
		this.listtChequeAntidateeBZ = listtChequeAntidateeBZ;
	}


	public Compte getCompteBiat() {
		return compteBiat;
	}


	public void setCompteBiat(Compte compteBiat) {
		this.compteBiat = compteBiat;
	}


	public Compte getCompteBZ() {
		return compteBZ;
	}


	public void setCompteBZ(Compte compteBZ) {
		this.compteBZ = compteBZ;
	}


	public BigDecimal getTotalChequeImpayeeBiat() {
		return totalChequeImpayeeBiat;
	}


	public void setTotalChequeImpayeeBiat(BigDecimal totalChequeImpayeeBiat) {
		this.totalChequeImpayeeBiat = totalChequeImpayeeBiat;
	}


	public BigDecimal getTotalChequeAntidateBiat() {
		return totalChequeAntidateBiat;
	}


	public void setTotalChequeAntidateBiat(BigDecimal totalChequeAntidateBiat) {
		this.totalChequeAntidateBiat = totalChequeAntidateBiat;
	}


	public BigDecimal getTotalChequeEncoursBiat() {
		return totalChequeEncoursBiat;
	}


	public void setTotalChequeEncoursBiat(BigDecimal totalChequeEncoursBiat) {
		this.totalChequeEncoursBiat = totalChequeEncoursBiat;
	}


	public BigDecimal getTotalChequeImpayeeBZ() {
		return totalChequeImpayeeBZ;
	}


	public void setTotalChequeImpayeeBZ(BigDecimal totalChequeImpayeeBZ) {
		this.totalChequeImpayeeBZ = totalChequeImpayeeBZ;
	}


	public BigDecimal getTotalChequeAntidateBZ() {
		return totalChequeAntidateBZ;
	}


	public void setTotalChequeAntidateBZ(BigDecimal totalChequeAntidateBZ) {
		this.totalChequeAntidateBZ = totalChequeAntidateBZ;
	}


	public BigDecimal getTotalChequeEncoursBZ() {
		return totalChequeEncoursBZ;
	}


	public void setTotalChequeEncoursBZ(BigDecimal totalChequeEncoursBZ) {
		this.totalChequeEncoursBZ = totalChequeEncoursBZ;
	}


	public BigDecimal getSoldeReelBiat() {
		return soldeReelBiat;
	}


	public void setSoldeReelBiat(BigDecimal soldeReelBiat) {
		this.soldeReelBiat = soldeReelBiat;
	}


	public List<Transaction> getListtChequePreavisBiat() {
		return listtChequePreavisBiat;
	}


	public void setListtChequePreavisBiat(List<Transaction> listtChequePreavisBiat) {
		this.listtChequePreavisBiat = listtChequePreavisBiat;
	}


	public BigDecimal getTotalChequePreavisBiat() {
		return totalChequePreavisBiat;
	}


	public void setTotalChequePreavisBiat(BigDecimal totalChequePreavisBiat) {
		this.totalChequePreavisBiat = totalChequePreavisBiat;
	}
	public BigDecimal getTotalChequeEncours() {
		return totalChequeEncours;
	}
	public void setTotalChequeEncours(BigDecimal totalChequeEncours) {
		this.totalChequeEncours = totalChequeEncours;
	} 
	
	
	 
}
