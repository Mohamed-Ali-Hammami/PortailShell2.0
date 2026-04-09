package com.tn.shell.ui.lavage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import com.tn.shell.model.lavage.AffectationFiltre;
import com.tn.shell.model.lavage.AffectationHuile;
import com.tn.shell.model.lavage.Marque;
import com.tn.shell.model.lavage.Model;
import com.tn.shell.model.lavage.TypeFiltre;
import com.tn.shell.model.shop.Produit;
import com.tn.shell.model.shop.TypePayement;
import com.tn.shell.service.lavage.AffectationFiltreService;
import com.tn.shell.service.lavage.AffectationHuileService;
import com.tn.shell.service.lavage.MarqueService;
import com.tn.shell.service.lavage.ModelService;
import com.tn.shell.service.shop.ServiceProduit;

@ManagedBean(name = "LavageBean")
@SessionScoped
public class LavageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	@ManagedProperty(value = "#{ServiceProduit}")
	transient ServiceProduit serviceProduit;	
	@ManagedProperty(value = "#{MarqueService}")
	transient MarqueService serviceMarque;
	@ManagedProperty(value = "#{ModelService}")
	transient ModelService serviceModel;
	@ManagedProperty(value = "#{AffectationFiltreService}")
	transient AffectationFiltreService affectationFiltreService;
	@ManagedProperty(value = "#{AffectationHuileService}")
	transient AffectationHuileService affectationHuileService;
	
	private List<Marque> listMarque;	
	private List<Model> listModel;
	private String model;
	private String marque;
	private String filtre;
	private List<Produit> listProduit;
	private List<AffectationFiltre> listAffectaionFiltre;
	private List<AffectationHuile> listAffectaionhuile;
	private TypeFiltre[] typefiltres;
	private TypeFiltre  typefiltre;
	private Marque selectedMarque;
	private Model selctedModel;
	private String emplacement;
	private String fournisseur;
	private BigDecimal metrage;
	private BigDecimal nbvidange;
	private String huileAvailabilityMessage;
	
	public String affecterFilterToModel() {
		listProduit=new ArrayList<Produit>();
		listProduit=serviceProduit.getAllbyfamilles0("FILTRE");
		typefiltres=TypeFiltre.values();		
		listAffectaionFiltre=new ArrayList<AffectationFiltre>();
		listAffectaionFiltre=affectationFiltreService.getAffectationFiltrebyModel(selctedModel);
		return SUCCESS;
	}
	public String saveaffecterFilterToModel() {
		 Produit p=serviceProduit.Findbydes(filtre);	
		 AffectationFiltre aff=new AffectationFiltre();
		 aff.setModel(selctedModel);
		 aff.setProduit(p);
		 aff.setEmplacement(emplacement);
		 aff.setFournisseur(fournisseur);
		 aff.setTypefiltre(typefiltre);
		 affectationFiltreService.save(aff);
		 listAffectaionFiltre=new ArrayList<AffectationFiltre>();
		 listAffectaionFiltre=affectationFiltreService.getAffectationFiltrebyModel(selctedModel);
		 fournisseur=null;emplacement=null;
		 typefiltre=null;
		 return SUCCESS;
	}	
	
	public String saveaffecterHuileToModel() {
		 if (listProduit == null || listProduit.isEmpty()) {
		 	addWarn("Aucun lubrifiant disponible", "Ajoutez d'abord un produit dans la famille LUBRIFIANTS.");
		 	return null;
		 }
		 if (filtre == null || filtre.trim().isEmpty()) {
		 	addWarn("Huile obligatoire", "Sélectionnez une huile avant de valider.");
		 	return null;
		 }
		 Produit p=serviceProduit.Findbydes(filtre);	
		 if (p == null) {
		 	addWarn("Huile introuvable", "Le produit sélectionné n'existe plus ou n'est plus actif.");
		 	return null;
		 }
		 AffectationHuile aff=new AffectationHuile();
		// aff.setModel(selctedModel);
		 aff.setProduit(p);
		 aff.setMetrage(metrage);
		 aff.setNbvidange(nbvidange);
		 affectationHuileService.save(aff);
		 listAffectaionhuile =new ArrayList<AffectationHuile>();
		 listAffectaionhuile=affectationHuileService.getAll();
		 metrage=null;
		 nbvidange=null;
		 filtre=null;
		 addInfo("Affectation enregistrée", "La fiche huile a été ajoutée.");
		 return SUCCESS;
	}	
	
	
	public String affecterHuileToModel() {
		listProduit=new ArrayList<Produit>();
		listProduit=serviceProduit.getAllbyfamilles0("LUBRIFIANTS");
		listAffectaionhuile =new ArrayList<AffectationHuile>();
		listAffectaionhuile=affectationHuileService.getAll();
		filtre=null;
		metrage=null;
		nbvidange=null;
		if (listProduit == null) {
			listProduit = new ArrayList<Produit>();
		}
		if (listProduit.isEmpty()) {
			huileAvailabilityMessage = "Aucun produit actif de la famille LUBRIFIANTS n'est disponible. Ajoutez un produit lubrifiant avec un code shop supérieur à 0 pour activer cette fiche.";
		} else {
			huileAvailabilityMessage = null;
		}
		typefiltres=TypeFiltre.values();
		return SUCCESS;
	}
	
	public String nouveauMarque() {
		marque=null;
		return SUCCESS;
	}
	public String nouveauModel() {
		model=null;
		listModel=new ArrayList<Model>();
	//	Marque m=serviceMarque.getMarquebyNom(se);
		listModel=serviceModel.getModelbyMarque(selectedMarque);
		selctedModel=null;
		return SUCCESS;
	}
	
	public String saveMarque() {
		Marque m=new Marque();
		m.setMarque(marque);
		serviceMarque.save(m);
		listMarque=new ArrayList<Marque>();
		listMarque=serviceMarque.getAll();
      return SUCCESS;
	}
	public String saveModel() {
		Model m=new Model();
		//Marque mar=serviceMarque.getMarquebyNom(marque);
		m.setMarque(selectedMarque);
		m.setModel(model);
		serviceModel.save(m);
		listModel=new ArrayList<Model>();
		//	Marque m=serviceMarque.getMarquebyNom(se);
			listModel=serviceModel.getModelbyMarque(selectedMarque);
			selectedMarque=null;
		return SUCCESS;
	}
	public String getAllMArque() {
		listMarque=new ArrayList<Marque>();
		listMarque=serviceMarque.getAll();
		selectedMarque=null;
		return SUCCESS;
	}
	public void getModelBymarque(AjaxBehaviorEvent event) {
		listModel=new ArrayList<Model>();
		Marque m=serviceMarque.getMarquebyNom(marque);
		listModel=serviceModel.getModelbyMarque(m);
	}
	
	public ServiceProduit getServiceProduit() {
		return serviceProduit;
	}
	public void setServiceProduit(ServiceProduit serviceProduit) {
		this.serviceProduit = serviceProduit;
	}
	public MarqueService getServiceMarque() {
		return serviceMarque;
	}
	public void setServiceMarque(MarqueService serviceMarque) {
		this.serviceMarque = serviceMarque;
	}
	 
	public ModelService getServiceModel() {
		return serviceModel;
	}
	public void setServiceModel(ModelService serviceModel) {
		this.serviceModel = serviceModel;
	}
	public List<Marque> getListMarque() {
		return listMarque;
	}
	public void setListMarque(List<Marque> listMarque) {
		this.listMarque = listMarque;
	}
	public List<Model> getListModel() {
		return listModel;
	}
	public void setListModel(List<Model> listModel) {
		this.listModel = listModel;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public List<Produit> getListProduit() {
		return listProduit;
	}
	public void setListProduit(List<Produit> listProduit) {
		this.listProduit = listProduit;
	}
	public String getFiltre() {
		return filtre;
	}
	public void setFiltre(String filtre) {
		this.filtre = filtre;
	}
	public TypeFiltre[] getTypefiltres() {
		return typefiltres;
	}
	public void setTypefiltres(TypeFiltre[] typefiltres) {
		this.typefiltres = typefiltres;
	}
	public TypeFiltre getTypefiltre() {
		return typefiltre;
	}
	public void setTypefiltre(TypeFiltre typefiltre) {
		this.typefiltre = typefiltre;
	}
	public Marque getSelectedMarque() {
		return selectedMarque;
	}
	public void setSelectedMarque(Marque selectedMarque) {
		this.selectedMarque = selectedMarque;
	}
	public Model getSelctedModel() {
		return selctedModel;
	}
	public void setSelctedModel(Model selctedModel) {
		this.selctedModel = selctedModel;
	}
	public String getEmplacement() {
		return emplacement;
	}
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}
	public String getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}
	public AffectationFiltreService getAffectationFiltreService() {
		return affectationFiltreService;
	}
	public void setAffectationFiltreService(AffectationFiltreService affectationFiltreService) {
		this.affectationFiltreService = affectationFiltreService;
	}
	public List<AffectationFiltre> getListAffectaionFiltre() {
		return listAffectaionFiltre;
	}
	public void setListAffectaionFiltre(List<AffectationFiltre> listAffectaionFiltre) {
		this.listAffectaionFiltre = listAffectaionFiltre;
	}
	public AffectationHuileService getAffectationHuileService() {
		return affectationHuileService;
	}
	public void setAffectationHuileService(AffectationHuileService affectationHuileService) {
		this.affectationHuileService = affectationHuileService;
	}
	public List<AffectationHuile> getListAffectaionhuile() {
		return listAffectaionhuile;
	}
	public void setListAffectaionhuile(List<AffectationHuile> listAffectaionhuile) {
		this.listAffectaionhuile = listAffectaionhuile;
	}
	public BigDecimal getMetrage() {
		return metrage;
	}
	public void setMetrage(BigDecimal  metrage) {
	    this.metrage = metrage;
	}
	
	
	public BigDecimal getNbvidange() {
		return nbvidange;
	}
	public void setNbvidange(BigDecimal nbvidange) {
		this.nbvidange = nbvidange;
	}
	
	public boolean isHuileDisponible() {
		return listProduit != null && !listProduit.isEmpty();
	}
	
	public String getHuileAvailabilityMessage() {
		return huileAvailabilityMessage;
	}
	
	public void setHuileAvailabilityMessage(String huileAvailabilityMessage) {
		this.huileAvailabilityMessage = huileAvailabilityMessage;
	}
	
	private void addWarn(String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail));
	}
	
	private void addInfo(String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
	}
	
	
}
