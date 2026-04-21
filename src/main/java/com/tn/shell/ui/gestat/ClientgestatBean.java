package com.tn.shell.ui.gestat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.dao.DataAccessException;

import com.tn.shell.model.gestat.Clientgestat;
import com.tn.shell.model.gestat.Credit;
import com.tn.shell.model.gestat.Creditanterieur;
import com.tn.shell.model.gestat.Efactureaction;
import com.tn.shell.model.gestat.Factureclient;
import com.tn.shell.model.gestat.Statut;
import com.tn.shell.model.shop.Paramettre;
import com.tn.shell.model.shop.Produit;
import com.tn.shell.model.transport.Facture;
import com.tn.shell.model.transport.Facturepassager;
import com.tn.shell.model.transport.Lignecommande;
import com.tn.shell.service.gestat.ServiceClientgestat;
import com.tn.shell.service.gestat.ServiceCreditanterieur;
import com.tn.shell.service.gestat.ServiceCreditclient;
import com.tn.shell.service.shop.ServiceParamettre;
import com.tn.shell.service.shop.ServiceProduit;
import com.tn.shell.service.transport.ServiceBonLivraison;
import com.tn.shell.service.transport.ServiceFacture;
import com.tn.shell.service.transport.ServiceFacturepassager;
import com.tn.shell.service.transport.ServiceLigneCommande;
import com.tn.shell.ui.transport.Convert;

@ManagedBean(name = "ClientgestatBean")
@SessionScoped
public class ClientgestatBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	private static final Logger LOG = Logger.getLogger(ClientgestatBean.class.getName());

	@ManagedProperty(value = "#{ServiceClientgestat}")
	ServiceClientgestat serviceClientgestat;
	@ManagedProperty(value = "#{ServiceCreditanterieur}")
	ServiceCreditanterieur serviceCreditanterieur;
	 
	@ManagedProperty(value = "#{ServiceFacturepassager}")
	ServiceFacturepassager serviceFacturepassager;
	@ManagedProperty(value = "#{ServiceCreditclient}")
	ServiceCreditclient sevicecreditclient;
	private List<Clientgestat> filterclients;
	private Facturepassager facture;
	private List<Lignecommande> listelignefacture;
	@ManagedProperty(value = "#{ServiceFacture}")
	private ServiceFacture  servicefacture;
	@ManagedProperty(value = "#{ServiceLigneCommande}")
	private ServiceLigneCommande serviceLigneCommande;
	@ManagedProperty(value = "#{ServiceBonLivraison}")
	private ServiceBonLivraison servicebonlivraison;
	@ManagedProperty(value = "#{ServiceParamettre}")
	ServiceParamettre serviceParamettre;
	@ManagedProperty(value = "#{ServiceProduit}")
	ServiceProduit serviceProduit;
	private Factureclient factureclient;
	private String nom;
	private String tel;
	private String prenom;
	private double ca;
	private double reste;
	private Date date;
	private double plafond;
	private Clientgestat client;
	private Clientgestat client2;
	private List<Clientgestat> listclient;
	private List<Clientgestat> listclient2;
	private List<Clientgestat> listclient3;
	private List<Clientgestat> listclient4;
	private List<String> listcredits;
	private Date date1;
	private Date date2;
	private Integer numfacture;
	private Integer codeclient;
	private Efactureaction[] efactureactions;
	private Efactureaction efactureaction;
	private List<Factureclient> listfacture;
	private List<Factureclient> selectedsfacture;
	private double total;
	private double totalant;
	private String totals;
	private String totalants;
	private String dates1;
	private String dates2;
	List<Credit> listcredit;

	public String facturation() {
		date1 = new Date();
		prenom = "";
		date2 = new Date();
		efactureactions = Efactureaction.values();
		listclient = new ArrayList<Clientgestat>();
		listclient = serviceClientgestat.getAll();
		Facturepassager fp = serviceFacturepassager.getMaxfacture();
		if(fp!=null)
		numfacture = fp.getCode() + 1;
		return SUCCESS;
	}
	private String total2;
	private String totalants2;
	public void onrowSelect(SelectEvent event) {
		double total=0;double totalant=0;
		DecimalFormat df = new DecimalFormat("#,###.000");
		 
		for(Factureclient f:selectedsfacture) {
			if(f.getCredit()!=null)
		total = total+f.getCredit().getMontant();
		if(f.getCreditant()!=null)
		totalant=totalant+f.getCreditant().getMontant();
		}
		total2 = df.format(total);
		totalants2 = df.format(totalant);
		
		FacesMessage msg = new FacesMessage("facture Selectionnee",
				"" + ((Factureclient) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public String validerfacturation() {
		Convert C = new Convert();
		date = new Date();
		client = serviceClientgestat.Findbynom(nom);
		codeclient = client.getCode();
		DecimalFormat df = new DecimalFormat("#,###.000");

		/*
		 * List<Ligneventecredit> l1 =
		 * serviceLigneventeboncredit.getAllcreditbyclientanddate(client);
		 * List<Ligneventecredit> l2 = new ArrayList<Ligneventecredit>();
		 * List<Ligneventecredit> l3 = new ArrayList<Ligneventecredit>();
		 */

		List<String> ld = new ArrayList<String>();
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");

		listfacture = new ArrayList<Factureclient>();
		for (int i = date1.getDate(); i <= date2.getDate(); i++) {
			Date d = new Date();
			d.setDate(i);
			d.setMonth(date1.getMonth());
			d.setYear(date1.getYear());
			String ds = sf.format(d);
			ld.add(ds);

		}
		total = 0;
		totalant = 0;
		if (efactureaction.equals(Efactureaction.FacturationParMouvement)) {
			int j=0;
			if (ld.size() > 0)
				for (String s : ld) {

					List<Credit> l2 = new ArrayList<Credit>();
					List<Creditanterieur> l3 = new ArrayList<Creditanterieur>();
					l2 = sevicecreditclient.getListcreditdateandclient(s, client);
					l3 = serviceCreditanterieur.getCreditanterieurbyclient(client, s);
					
					double qte = 0;
					double qtet = 0;
					
					if (l2 != null)
						if (l2 != null) {
							
							for (Credit l : l2) {
								Factureclient f = new Factureclient();
								  f.setId(j);
								total = total + l.getMontant();
								f.setCredit(l);
								f.setDates(s);
								listfacture.add(f);
								j=j+1;
							}}
					if (l3 != null) {
						 
						for (Creditanterieur l : l3) {
							Factureclient f = new Factureclient();
                            f.setId(j);
							totalant = totalant + l.getMontant();
							f.setCreditant(l);
							f.setDates(s);
							listfacture.add(f);
							j=j+1;
						}
					}
				}
			totals = df.format(total);
			totalants = df.format(totalant);
		}
		if (efactureaction.equals(Efactureaction.FacturationParMontantGlobal)) {
			try {
				double total = sevicecreditclient.getAllcreditbyclientanddates(date1, date2, client);

				SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
				//Bonlivraison bl = new Bonlivraison();
				date = new Date();
			//	bl.setDate(date);
				//bl.setDates(fr.format(date));
				//Bonlivraison f2;
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				//bl.setDates(dateFormat.format(bl.getDate()));
				//f2 = servicebonlivraison.getMaxbl();

				/*if (f2 == null) {

					bl.setCode(1);

				} else if (f2.getDate().getYear() == bl.getDate().getYear() && f2 != null) {

					bl.setCode(f2.getCode() + 1);
				}

				else if (bl.getDate().getYear() > f2.getDate().getYear()) {

					bl.setCode(1);
				}
				 
				Integer codes = bl.getCode();
				bl.setCodes(codes + "/" + bl.getDates().substring(6));
				bl.setMontant(total);
				bl.setTotaltva(0);
				bl.setTotaltva(0);
				servicebonlivraison.save(bl);*/
				facture = new Facturepassager();
				 

				facture.setDates(fr.format(date));
				facture.setDate(date);

				 
				Facturepassager f;
				f = serviceFacturepassager.getMaxfacture();
				Facture f2 = servicefacture.getMaxfacture();
				if (f == null) {
					facture.setCode(1);

				} else if (f.getDate().getYear() == facture.getDate().getYear() && f != null) {

					facture.setCode(f.getCode() + 1);
				}

				else if (facture.getDate().getYear() > f.getDate().getYear()) {

					facture.setCode(1);
				}
				Integer codef = facture.getCode();
				if(f2!=null)
				if(f2.getCode()>=codef)
					codef=f2.getCode()+1;
				facture.setCode(codef);
				facture.setCodes(codef + "/" + facture.getDates().substring(6));
				Paramettre p = serviceParamettre.getAll().get(0);

				facture.setTimbres(p.getTimbre());

				facture.setTotaltva(0);
				facture.setTotalht(total);
				facture.setTotalttc(facture.getTotalht() + facture.getTimbres());
				String s = "";
				double ss = facture.getTotalttc();
				int e = new Float(ss).intValue();
				int cs = facture.getTotalttcs().indexOf(",");
				s = facture.getTotalttcs().substring(cs + 1, facture.getTotalttcs().length());
				facture.setSommes(C.convertt(e) + " dinars et  " + s + " millimes");
				//servicefacture.save(facture);
				listelignefacture=new ArrayList<Lignecommande>();
				Lignecommande l = new Lignecommande();
				//l.setBl(bl);
				l.setDate(date);
				l.setDates(fr.format(date));
				Produit pr = serviceProduit.Findbycode(126);
				l.setProduit(pr);
				l.setQuantite(total / pr.getVente());
				l.setMantantht(total);
				l.setPrix(pr.getVente());
				l.setTauxtva(0);
				l.setTva(0);
				serviceLigneCommande.save(l);
				listelignefacture.add(l);

			} catch (Exception e) {
			} 
		}

		return SUCCESS;
	}

	public boolean verifcreditant(Creditanterieur c, int i) {
		if (i == 0)
			return false;
		if (i != 0 && listfacture.get(i - 1).getCreditant().getId() == c.getId())
			return true;
		else
			;
		return false;
	}

	public void etat(AjaxBehaviorEvent event) {
		listclient = new ArrayList<Clientgestat>();
		listclient2 = new ArrayList<Clientgestat>();
		listclient3 = new ArrayList<Clientgestat>();
		listclient4 = new ArrayList<Clientgestat>();

		for (Clientgestat c : listclient) {
			if (c.getReste() > 0)
				listclient4.add(c);
			else if (c.getReste() < 0)
				listclient3.add(c);
			else if (c.getReste() == 0)
				listclient2.add(c);

		}

	}

	public String getclient() {
		LOG.log(Level.INFO, "Gestat.ClientgestatBean#getclient start serviceClientgestatNull={0}", serviceClientgestat == null);
		DecimalFormat df = new DecimalFormat("#,###.000");
		totalant = 0;
		total = 0;
		date1 = new Date();
		listclient = new ArrayList<Clientgestat>();
		listclient = serviceClientgestat.getclientendepassement();
		if (listclient!=null)
			for (Clientgestat c : listclient)
				total = total + c.getReste();
		listclient2 = new ArrayList<Clientgestat>();
		listclient2 = serviceClientgestat.getclientenAvance();
		if (listclient2!=null)
			for (Clientgestat c : listclient2)
				totalant = totalant + c.getReste();
		totals = df.format(total);
		totalants = df.format(totalant);
		LOG.log(Level.INFO,
				"Gestat.ClientgestatBean#getclient done depassement={0} avance={1} totalDep={2} totalAv={3}",
				new Object[] { listclient == null ? -1 : listclient.size(), listclient2 == null ? -1 : listclient2.size(), totals, totalants });
		return SUCCESS;
	}

	public String cahiercredit() {
		listclient = new ArrayList<Clientgestat>();
		listclient = serviceClientgestat.getAll();

		listcredits = new ArrayList<String>();
		for (int i = 0; i <= 30; i++) {
			listcredits.add("" + (i + 1));
		}
		return SUCCESS;
	}

	public String nouvauclient() {
		plafond = 0;
		reste = 0;
		prenom = null;
		ca = 0;
		nom = null;
		return SUCCESS;
	}

	public String saveclient() {
		ca = 0;
		client = new Clientgestat();
		client.setNom(nom);
		client.setTel(tel);
		client.setPrenom(prenom);
		client.setPlafond(0);
		client.setReste(0);
		client.setChiffreaffaire(ca);
		serviceClientgestat.save(client);
		listclient = new ArrayList<Clientgestat>();
		listclient = serviceClientgestat.getAll();
		nom = null;

		return SUCCESS;

	}

	public String updateClient(Clientgestat client) {
		try {
			serviceClientgestat.update(client);
			return SUCCESS;
		} catch (DataAccessException e) {
		}
		return ERROR;
	}

	public void onRowEdit(RowEditEvent event) {

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "client change",
				((Clientgestat) event.getObject()).getNom());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		updateClient((Clientgestat) event.getObject());

	}

	public String deleteclient() {
		client2.setStatut(Statut.DEACTIF);

		serviceClientgestat.delete(client2);
		listclient = new ArrayList<Clientgestat>();
		listclient = serviceClientgestat.getAll();
		return SUCCESS;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public double getCa() {
		return ca;
	}

	public void setCa(double ca) {
		this.ca = ca;
	}

	public double getReste() {
		return reste;
	}

	public void setReste(double reste) {
		this.reste = reste;
	}

	public double getPlafond() {
		return plafond;
	}

	public void setPlafond(double plafond) {
		this.plafond = plafond;
	}

	public List<String> getListcredits() {
		return listcredits;
	}

	public void setListcredits(List<String> listcredits) {
		this.listcredits = listcredits;
	}

	public ServiceClientgestat getServiceClientgestat() {
		return serviceClientgestat;
	}

	public void setServiceClientgestat(ServiceClientgestat serviceClientgestat) {
		this.serviceClientgestat = serviceClientgestat;
	}

	public List<Clientgestat> getFilterclients() {
		return filterclients;
	}

	public void setFilterclients(List<Clientgestat> filterclients) {
		this.filterclients = filterclients;
	}

	public Clientgestat getClient() {
		return client;
	}

	public void setClient(Clientgestat client) {
		this.client = client;
	}

	public Clientgestat getClient2() {
		return client2;
	}

	public void setClient2(Clientgestat client2) {
		this.client2 = client2;
	}

	public List<Clientgestat> getListclient() {
		return listclient;
	}

	public void setListclient(List<Clientgestat> listclient) {
		this.listclient = listclient;
	}

	public List<Clientgestat> getListclient2() {
		return listclient2;
	}

	public void setListclient2(List<Clientgestat> listclient2) {
		this.listclient2 = listclient2;
	}

	public List<Clientgestat> getListclient3() {
		return listclient3;
	}

	public void setListclient3(List<Clientgestat> listclient3) {
		this.listclient3 = listclient3;
	}

	public List<Clientgestat> getListclient4() {
		return listclient4;
	}

	public void setListclient4(List<Clientgestat> listclient4) {
		this.listclient4 = listclient4;
	}

	public Factureclient getFactureclient() {
		return factureclient;
	}

	public void setFactureclient(Factureclient factureclient) {
		this.factureclient = factureclient;
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

	public Integer getCodeclient() {
		return codeclient;
	}

	public void setCodeclient(Integer codeclient) {
		this.codeclient = codeclient;
	}

	public Efactureaction[] getEfactureactions() {
		return efactureactions;
	}

	public void setEfactureactions(Efactureaction[] efactureactionss) {
		efactureactions = efactureactionss;
	}

	public Efactureaction getEfactureaction() {
		return efactureaction;
	}

	public void setEfactureaction(Efactureaction efactureactions) {
		efactureaction = efactureactions;
	}

	public ServiceCreditanterieur getServiceCreditanterieur() {
		return serviceCreditanterieur;
	}

	public void setServiceCreditanterieur(ServiceCreditanterieur serviceCreditanterieur) {
		this.serviceCreditanterieur = serviceCreditanterieur;
	}

	public List<Factureclient> getListfacture() {
		return listfacture;
	}

	public void setListfacture(List<Factureclient> listfacture) {
		this.listfacture = listfacture;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getTotals() {
		return totals;
	}

	public void setTotals(String totals) {
		this.totals = totals;
	}

	public List<Credit> getListcredit() {
		return listcredit;
	}

	public void setListcredit(List<Credit> listcredit) {
		this.listcredit = listcredit;
	}

	public String getDates1() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates1 = s.format(date1);
		return dates1;
	}

	public void setDates1(String dates1) {
		this.dates1 = dates1;
	}

	public String getDates2() {
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
		dates2 = s.format(date2);
		return dates2;
	}

	public void setDates2(String dates2) {
		this.dates2 = dates2;
	}

	public double getTotalant() {
		return totalant;
	}

	public void setTotalant(double totalant) {
		this.totalant = totalant;
	}

	public String getTotalants() {
		return totalants;
	}

	public void setTotalants(String totalants) {
		this.totalants = totalants;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getNumfacture() {
		return numfacture;
	}

	public void setNumfacture(Integer numfacture) {
		this.numfacture = numfacture;
	}

	public ServiceFacturepassager getServiceFacturepassager() {
		return serviceFacturepassager;
	}

	public void setServiceFacturepassager(ServiceFacturepassager serviceFacturepassager) {
		this.serviceFacturepassager = serviceFacturepassager;
	}

	public ServiceCreditclient getSevicecreditclient() {
		return sevicecreditclient;
	}

	public void setSevicecreditclient(ServiceCreditclient sevicecreditclient) {
		this.sevicecreditclient = sevicecreditclient;
	}

	 

	public Facturepassager getFacture() {
		return facture;
	}

	public void setFacture(Facturepassager facture) {
		this.facture = facture;
	}

	public List<Lignecommande> getListelignefacture() {
		return listelignefacture;
	}

	public void setListelignefacture(List<Lignecommande> listelignefacture) {
		this.listelignefacture = listelignefacture;
	}

	public ServiceFacture getServicefacture() {
		return servicefacture;
	}

	public void setServicefacture(ServiceFacture servicefacture) {
		this.servicefacture = servicefacture;
	}

	public ServiceLigneCommande getServiceLigneCommande() {
		return serviceLigneCommande;
	}

	public void setServiceLigneCommande(ServiceLigneCommande serviceLigneCommande) {
		this.serviceLigneCommande = serviceLigneCommande;
	}

	public ServiceBonLivraison getServicebonlivraison() {
		return servicebonlivraison;
	}

	public void setServicebonlivraison(ServiceBonLivraison servicebonlivraison) {
		this.servicebonlivraison = servicebonlivraison;
	}

	public ServiceParamettre getServiceParamettre() {
		return serviceParamettre;
	}

	public void setServiceParamettre(ServiceParamettre serviceParamettre) {
		this.serviceParamettre = serviceParamettre;
	}

	public ServiceProduit getServiceProduit() {
		return serviceProduit;
	}

	public void setServiceProduit(ServiceProduit serviceProduit) {
		this.serviceProduit = serviceProduit;
	}

	public List<Factureclient> getSelectedsfacture() {
		return selectedsfacture;
	}

	public void setSelectedsfacture(List<Factureclient> selectedsfacture) {
		this.selectedsfacture = selectedsfacture;
	}

	public String getTotal2() {
		return total2;
	}

	public void setTotal2(String total2) {
		this.total2 = total2;
	}

	public String getTotalants2() {
		return totalants2;
	}

	public void setTotalants2(String totalants2) {
		this.totalants2 = totalants2;
	}

}
