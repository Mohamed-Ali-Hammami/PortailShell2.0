package com.tn.shell.ui.gestat;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.tn.shell.model.gestat.Caisse;
import com.tn.shell.model.gestat.Cheque;
import com.tn.shell.model.gestat.Chequereglement;
import com.tn.shell.model.gestat.Clientgestat;
import com.tn.shell.model.gestat.Enumcheque;
import com.tn.shell.model.gestat.Etatcheque;
import com.tn.shell.model.gestat.Poste;
import com.tn.shell.model.gestat.Utilisateur;
import com.tn.shell.service.gestat.ServiceCaisse;
import com.tn.shell.service.gestat.ServiceCheque;
import com.tn.shell.service.gestat.ServiceChequereglement;
import com.tn.shell.service.gestat.Serviceetatcheque;

@ManagedBean(name = "ChequeBean")
@SessionScoped
public class ChequeBean {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	@ManagedProperty(value = "#{ServiceCheque}")
	ServiceCheque serviceCheque;
	@ManagedProperty(value = "#{ServiceChequereglement}")
	ServiceChequereglement servicechequereg;
	@ManagedProperty(value = "#{ServiceCaisse}")
	ServiceCaisse servicecaisse;
	@ManagedProperty(value = "#{Serviceetatcheque}")
	Serviceetatcheque serviceetatcheque;
	private Cheque cheque;
	private String numcheque;
	private String banque;
	private Date date;
	private Poste poste;
	private String dates1;
	private Utilisateur user;
	private double montant;
	private List<Cheque> listcheques;
	private List<Chequereglement> listchequereglement;
	private List<Chequereglement> listchequereglementbz;
	private List<Chequereglement> listchequereglementbiat;
	private String soldenet;
	private String soldechequebz;
	private String soldechequebiat;
	private String soldechequejazira;
	private Enumcheque[] etatcheques;
	private Enumcheque etat;
	private Etatcheque etatcheque;
	private List<Cheque> listcheques2;
	private List<Cheque> listchequeimpayes;
	private List<Cheque> listchequeantidates;
	private List<Cheque> listchequemanuels;
	private List<Cheque> listchequepreavis;
	private Etatcheque selectedetat;
	private List<Etatcheque> listetat;
	private Cheque selecredcheque;
	private List<Cheque> selecredcheques;
	private Chequereglement selecredchequereg;
	private Chequereglement selectedcheque;
	private boolean value1;

	public String getchequeparjour() {
		date = new Date();
		selecredcheques=new ArrayList<Cheque>();
		etatcheques = Enumcheque.values();
		DecimalFormat df = new DecimalFormat("0.000");
		listetat = new ArrayList<Etatcheque>();
		etatcheque = serviceetatcheque.getmaxcode();
		double soldebz = etatcheque.getTotalcrediteurzitouna();
		double soldebjazira = etatcheque.getTotalsoldejazira();
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		soldechequebz = df.format(soldebz);
		soldechequejazira = df.format(soldebjazira);
		double totalimpaye = 0;
		double totalanidate = 0;
		double totalmanuel = 0;
		double totalchequeentre = 0;
		double totalchequeepreavis = 0;
		double toatlchequebiat = 0;
		double totalchequezb = 0;
		double totalchequeer = 0;
		listcheques = new ArrayList<Cheque>();
		listcheques2= new ArrayList<Cheque>();
		listchequeimpayes = new ArrayList<Cheque>();
		listchequeantidates = new ArrayList<Cheque>();
		listchequemanuels = new ArrayList<Cheque>();
		listchequepreavis = new ArrayList<Cheque>();

		listchequereglement = new ArrayList<Chequereglement>();
		listchequereglementbz = new ArrayList<Chequereglement>();
		listchequereglementbiat = new ArrayList<Chequereglement>();
		String b1 = "bz";
		String b2 = "biat";
		listchequereglement = servicechequereg.getListchequeetat(Enumcheque.Entree);
		listchequereglementbz = servicechequereg.getListchequeetatandbanque(Enumcheque.Encours, b1);
		listchequereglementbiat = servicechequereg.getListchequeetatandbanque(Enumcheque.Encours, b2);

		for (Chequereglement c : listchequereglement)
			totalchequeer = totalchequeer + c.getMontant();
		for (Chequereglement c : listchequereglementbz)
			totalchequezb = totalchequezb + c.getMontant();
		for (Chequereglement c : listchequereglementbiat)
			toatlchequebiat = toatlchequebiat + c.getMontant();

		listcheques = serviceCheque.getListchequeetat(Enumcheque.Entree);
		listchequeimpayes = serviceCheque.getListchequeetat(Enumcheque.Impayes);
		listchequeantidates = serviceCheque.getListchequeetat(Enumcheque.Antidate);
		listchequemanuels = serviceCheque.getListchequeetat(Enumcheque.Mannuel);
		listchequepreavis = serviceCheque.getListchequeetat(Enumcheque.Preavis);
		listcheques2= serviceCheque.getListchequeetat(Enumcheque.Encours);
		for (Cheque c : listchequeimpayes)
			totalimpaye = totalimpaye + c.getMontant();
		for (Cheque c : listchequeantidates)
			totalanidate = totalanidate + c.getMontant();
		for (Cheque c : listchequemanuels)
			totalmanuel = totalmanuel + c.getMontant();
		for (Cheque c : listcheques)
			totalchequeentre = totalchequeentre + c.getMontant();
		for (Cheque c : listcheques)
			totalchequeentre = totalchequeentre + c.getMontant();
		for (Cheque c : listchequepreavis)
			totalchequeepreavis = totalchequeepreavis + c.getMontant();

		etatcheque.setTotalchequeanidate(df.format(totalanidate));
		etatcheque.setTotalchequeencours(df.format(totalchequezb));
		etatcheque.setTotalchequeimpaye(df.format(totalimpaye));
		etatcheque.setTotalcrediteurzitounas(df.format(etatcheque.getTotalcrediteurzitouna()));
		etatcheque.setTotalsoldejaziras(df.format(etatcheque.getTotalsoldejazira()));
		etatcheque.setTotalchequepreavis(df.format(totalchequeepreavis));
		listetat.add(etatcheque);
		return SUCCESS;
	}

	public void onCellEdit(CellEditEvent event) {
		selectedetat = listetat.get(event.getRowIndex());

	}

	public void onCellEdit3(CellEditEvent event) {
		selecredcheque = listcheques.get(event.getRowIndex());

	}

	public String setchequeencours() {
		if(selecredcheques.size()>0) {
		for (Cheque selecredcheque : selecredcheques) {
			selecredcheque.setEtat(Enumcheque.Encours);
			serviceCheque.update(selecredcheque);
		}
		listcheques = new ArrayList<Cheque>();
		listcheques = serviceCheque.getListchequeetat(Enumcheque.Entree);
		listcheques2 = new ArrayList<Cheque>();
		listcheques2 = serviceCheque.getListchequeetat(Enumcheque.Encours);
		System.out.println("selecredcheques size"+selecredcheques.size());
		}
		else {
			System.out.println("selecredcheques size"+selecredcheques.size());
		}
		return SUCCESS;
	}
	
	public void onrowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("cheque Selected", "" + ((Cheque) event.getObject()).getNumero());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		// selectedsBl.add((Bonlivraison) event.getObject());
		 
		 
	}

	public void onrowUnSelect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("Bl Unselected", "" + ((Cheque) event.getObject()).getNumero());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		// selectedsBl.remove((Bonlivraison) event.getObject());
	}
	
	public String setchequeenpreavis() {
		for (Cheque selecredcheque : selecredcheques) {
			selecredcheque.setEtat(Enumcheque.Preavis);
			serviceCheque.update(selecredcheque);
		}
		listcheques2 = new ArrayList<Cheque>();
		listcheques2 = serviceCheque.getListchequeetat(Enumcheque.Encours);
		listchequepreavis = new ArrayList<Cheque>();
		listchequepreavis = serviceCheque.getListchequeetat(Enumcheque.Preavis);
		return SUCCESS;
	}
	
	public String setchequepayee() {
		for (Cheque selecredcheque : selecredcheques) {
			selecredcheque.setEtat(Enumcheque.Payes);
			serviceCheque.update(selecredcheque);
		}
		
		listcheques2 = new ArrayList<Cheque>();
		listcheques2 = serviceCheque.getListchequeetat(Enumcheque.Encours);
		listchequepreavis = new ArrayList<Cheque>();
		listchequepreavis = serviceCheque.getListchequeetat(Enumcheque.Preavis);
		listchequeimpayes = new ArrayList<Cheque>();
		listchequeimpayes = serviceCheque.getListchequeetat(Enumcheque.Impayes);
		return SUCCESS;
	}
	
	public String setchequeimppayee() {
		for (Cheque selecredcheque : selecredcheques) {
			selecredcheque.setEtat(Enumcheque.Impayes);
			serviceCheque.update(selecredcheque);
		}
		listcheques = new ArrayList<Cheque>();
		listcheques = serviceCheque.getListchequeetat(Enumcheque.Encours);
		listchequepreavis = new ArrayList<Cheque>();
		listchequepreavis = serviceCheque.getListchequeetat(Enumcheque.Preavis);
		listchequeimpayes = new ArrayList<Cheque>();
		listchequeimpayes = serviceCheque.getListchequeetat(Enumcheque.Impayes);
		return SUCCESS;
	}
	
	public String setchequeantidate() {
		for (Cheque selecredcheque : selecredcheques) {
			selecredcheque.setEtat(Enumcheque.Antidate);
			serviceCheque.update(selecredcheque);
		}
		listcheques = new ArrayList<Cheque>();
		listcheques = serviceCheque.getListchequeetat(Enumcheque.Encours);
		listchequepreavis = new ArrayList<Cheque>();
		listchequepreavis = serviceCheque.getListchequeetat(Enumcheque.Preavis);
		listchequeimpayes = new ArrayList<Cheque>();
		listchequeimpayes = serviceCheque.getListchequeetat(Enumcheque.Impayes);
		listchequeantidates = new ArrayList<Cheque>();
		listchequeantidates = serviceCheque.getListchequeetat(Enumcheque.Antidate);
		return SUCCESS;
	}
	
	 
	public String setchequemanuelle(){
		for (Cheque selecredcheque : selecredcheques) {
			selecredcheque.setEtat(Enumcheque.Impayes);
			serviceCheque.update(selecredcheque);
		}
		listcheques = new ArrayList<Cheque>();
		listcheques = serviceCheque.getListchequeetat(Enumcheque.Encours);
		listchequepreavis = new ArrayList<Cheque>();
		listchequepreavis = serviceCheque.getListchequeetat(Enumcheque.Preavis);
		listchequemanuels= new ArrayList<Cheque>();
		listchequemanuels = serviceCheque.getListchequeetat(Enumcheque.Mannuel);
		return SUCCESS;
	}
	public void onCellEdit2(CellEditEvent event) {
		selecredchequereg = listchequereglementbz.get(event.getRowIndex());
		servicechequereg.update(selecredchequereg);

	}

	public void updatemontantBZ(AjaxBehaviorEvent event) {
		etatcheque.setTotalcrediteurzitouna(etatcheque.getTotalcrediteurzitouna());
		serviceetatcheque.update(etatcheque);
	}

	public String supprimercheque() {
		serviceCheque.delete(selecredcheque);

		listcheques = new ArrayList<Cheque>();
		listchequeimpayes = new ArrayList<Cheque>();
		listchequeantidates = new ArrayList<Cheque>();
		listchequemanuels = new ArrayList<Cheque>();
		listcheques = serviceCheque.getListchequeetat(Enumcheque.Entree);
		listchequeimpayes = serviceCheque.getListchequeetat(Enumcheque.Impayes);
		listchequeantidates = serviceCheque.getListchequeetat(Enumcheque.Antidate);
		listchequemanuels = serviceCheque.getListchequeetat(Enumcheque.Mannuel);
		return SUCCESS;
	}

	public void onRowEdit(RowEditEvent event) {

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "cheque change",
				((Cheque) event.getObject()).getNumero());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		updatecheque((Cheque) event.getObject());

	}

	public void updatecheque(Cheque c) {
		serviceCheque.update(c);
	}

	public String supprimerchequereg() {
		servicechequereg.delete(selecredchequereg);
		listchequereglement = new ArrayList<Chequereglement>();
		listchequereglementbz = new ArrayList<Chequereglement>();
		listchequereglementbiat = new ArrayList<Chequereglement>();

		String b1 = "zb";
		String b2 = "biat";
		listchequereglement = servicechequereg.getListchequeetat(Enumcheque.Entree);
		listchequereglementbz = servicechequereg.getListchequeetatandbanque(Enumcheque.Encours, b1);
		listchequereglementbiat = servicechequereg.getListchequeetatandbanque(Enumcheque.Encours, b2);
		return SUCCESS;
	}

	public void getbynumero(AjaxBehaviorEvent event) {
		listcheques = new ArrayList<Cheque>();
		listcheques = serviceCheque.Findbynom(numcheque);
	}

	public String findCheque() {
		listcheques = new ArrayList<Cheque>();
		listcheques = serviceCheque.getAll();
		date = new Date();
		return SUCCESS;
	}

	public String validerrecherche() {
		try {
			List<Cheque> list1 = new ArrayList<Cheque>();
			List<Cheque> list2 = new ArrayList<Cheque>();
			List<Cheque> list3 = new ArrayList<Cheque>();
			List<Cheque> list4 = new ArrayList<Cheque>();
			if (listcheques.size() > 0) {
				for (Cheque c : listcheques) {
					if (!numcheque.equals(null)) {
						if (c != null && c.getNumero().equals(numcheque))
							list1.add(c);
					}

					if (numcheque == null)
						list1.add(c);
				}

				for (Cheque c : list1) {
					if (!banque.equals(null)) {
						if (c != null && c.getBanque().equals(banque))
							list2.add(c);
					}
					if (banque == null)
						list2.add(c);
				}

				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

					for (Cheque f : list2) {
						if (date != null) {
							Date d1 = dateFormat.parse(dateFormat.format(date));

							if (d1.compareTo(dateFormat.parse(dateFormat.format(f.getDate()))) <= 0) {
								list3.add(f);
							}
						}

						if (date == null)
							list3.add(f);
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				listcheques = new ArrayList<Cheque>();
				for (Cheque c : list3) {
					if (montant != 0) {
						if (c.getMontant() == montant)
							listcheques.add(c);
					}
					if (montant == 0)
						listcheques.add(c);
				}
			}
		} catch (Exception e) {
			return SUCCESS;
		}
		return SUCCESS;
	}

	/*
	 * 
	 * getter and setter
	 */
	public Cheque getCheque() {
		return cheque;
	}

	public void setCheque(Cheque cheque) {
		this.cheque = cheque;
	}

	public String getNumcheque() {
		return numcheque;
	}

	public void setNumcheque(String numcheque) {
		this.numcheque = numcheque;
	}

	public String getBanque() {
		return banque;
	}

	public void setBanque(String banque) {
		this.banque = banque;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Poste getPoste() {
		return poste;
	}

	public void setPoste(Poste poste) {
		this.poste = poste;
	}

	public List<Cheque> getListcheques2() {
		return listcheques2;
	}

	public void setListcheques2(List<Cheque> listcheques2) {
		this.listcheques2 = listcheques2;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public ServiceCheque getServiceCheque() {
		return serviceCheque;
	}

	public void setServiceCheque(ServiceCheque serviceCheque) {
		this.serviceCheque = serviceCheque;
	}

	public List<Cheque> getListcheques() {
		return listcheques;
	}

	public void setListcheques(List<Cheque> listcheques) {
		this.listcheques = listcheques;
	}

	public ServiceChequereglement getServicechequereg() {
		return servicechequereg;
	}

	public void setServicechequereg(ServiceChequereglement servicechequereg) {
		this.servicechequereg = servicechequereg;
	}

	public ServiceCaisse getServicecaisse() {
		return servicecaisse;
	}

	public void setServicecaisse(ServiceCaisse servicecaisse) {
		this.servicecaisse = servicecaisse;
	}

	public Serviceetatcheque getServiceetatcheque() {
		return serviceetatcheque;
	}

	public void setServiceetatcheque(Serviceetatcheque serviceetatcheque) {
		this.serviceetatcheque = serviceetatcheque;
	}

	public List<Chequereglement> getListchequereglement() {
		return listchequereglement;
	}

	public void setListchequereglement(List<Chequereglement> listchequereglement) {
		this.listchequereglement = listchequereglement;
	}

	public List<Chequereglement> getListchequereglementbz() {
		return listchequereglementbz;
	}

	public void setListchequereglementbz(List<Chequereglement> listchequereglementbz) {
		this.listchequereglementbz = listchequereglementbz;
	}

	public List<Chequereglement> getListchequereglementbiat() {
		return listchequereglementbiat;
	}

	public void setListchequereglementbiat(List<Chequereglement> listchequereglementbiat) {
		this.listchequereglementbiat = listchequereglementbiat;
	}

	public String getSoldenet() {
		return soldenet;
	}

	public void setSoldenet(String soldenet) {
		this.soldenet = soldenet;
	}

	public String getSoldechequebz() {
		return soldechequebz;
	}

	public void setSoldechequebz(String soldechequebz) {
		this.soldechequebz = soldechequebz;
	}

	public String getSoldechequebiat() {
		return soldechequebiat;
	}

	public void setSoldechequebiat(String soldechequebiat) {
		this.soldechequebiat = soldechequebiat;
	}

	public String getSoldechequejazira() {
		return soldechequejazira;
	}

	public void setSoldechequejazira(String soldechequejazira) {
		this.soldechequejazira = soldechequejazira;
	}

	public Etatcheque getEtatcheque() {
		return etatcheque;
	}

	public void setEtatcheque(Etatcheque etatcheque) {
		this.etatcheque = etatcheque;
	}

	public List<Cheque> getListchequeimpayes() {
		return listchequeimpayes;
	}

	public void setListchequeimpayes(List<Cheque> listchequeimpayes) {
		this.listchequeimpayes = listchequeimpayes;
	}

	public List<Cheque> getListchequeantidates() {
		return listchequeantidates;
	}

	public void setListchequeantidates(List<Cheque> listchequeantidates) {
		this.listchequeantidates = listchequeantidates;
	}

	public List<Cheque> getListchequemanuels() {
		return listchequemanuels;
	}

	public void setListchequemanuels(List<Cheque> listchequemanuels) {
		this.listchequemanuels = listchequemanuels;
	}

	public Cheque getSelecredcheque() {
		return selecredcheque;
	}

	public void setSelecredcheque(Cheque selecredcheque) {
		this.selecredcheque = selecredcheque;
	}

	public Chequereglement getSelecredchequereg() {
		return selecredchequereg;
	}

	public void setSelecredchequereg(Chequereglement selecredchequereg) {
		this.selecredchequereg = selecredchequereg;
	}

	private String dates;

	public String getDates() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates = s.format(date);
		return dates;
	}

	public List<Etatcheque> getListetat() {
		return listetat;
	}

	public void setListetat(List<Etatcheque> listetat) {
		this.listetat = listetat;
	}

	public String getDates1() {
		return dates1;
	}

	public void setDates1(String dates1) {
		this.dates1 = dates1;
	}

	public List<Cheque> getListchequepreavis() {
		return listchequepreavis;
	}

	public void setListchequepreavis(List<Cheque> listchequepreavis) {
		this.listchequepreavis = listchequepreavis;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public Etatcheque getSelectedetat() {
		return selectedetat;
	}

	public void setSelectedetat(Etatcheque selectedetat) {
		this.selectedetat = selectedetat;
	}

	public Enumcheque[] getEtatcheques() {
		return etatcheques;
	}

	public void setEtatcheques(Enumcheque[] etatcheques) {
		this.etatcheques = etatcheques;
	}

	public Enumcheque getEtat() {
		return etat;
	}

	public void setEtat(Enumcheque etat) {
		this.etat = etat;
	}

	public Chequereglement getSelectedcheque() {
		return selectedcheque;
	}

	public void setSelectedcheque(Chequereglement selectedcheque) {
		this.selectedcheque = selectedcheque;
	}

	public boolean isValue1() {
		return value1;
	}

	public void setValue1(boolean value1) {
		this.value1 = value1;
	}

	public List<Cheque> getSelecredcheques() {
		return selecredcheques;
	}

	public void setSelecredcheques(List<Cheque> selecredcheques) {
		this.selecredcheques = selecredcheques;
	}

}
