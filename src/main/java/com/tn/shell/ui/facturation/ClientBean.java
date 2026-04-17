package com.tn.shell.ui.facturation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.dao.DataAccessException;

import com.tn.shell.model.transport.*;
import com.tn.shell.service.transport.*;

 
 

@ManagedBean(name = "ClientfacBean")
@SessionScoped
public class ClientBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	 

	@ManagedProperty(value = "#{ServiceTracetransport}")
	ServiceTracetransport serviceTrace;
	@ManagedProperty(value = "#{ServiceClient}")
	ServiceClient serviceClient;
	@ManagedProperty(value = "#{ServiceBonLivraison}")
	ServiceBonLivraison serviceBonLivraison;
    private List<Client> filterclients;
    private String matriculefiscal;
	private String tel; 
	private String address;
    private Integer compteur;
	private String nom;
	private String nombre;
	private double transport;
	private Client client;
	private Client client2;
	private List<Client> listclient;

	public String getclient() {
		listclient=new ArrayList<Client>();
		listclient = serviceClient.getAll();
		/*for(Client c:listclient){
			//c.setCompteur(serviceBonLivraison.getnbBLparclient(c.getCode()));
			//c.setChiffreaffaire(serviceBonLivraison.getchiifreaffaireparclient(c.getCode()));
			c.setStatut(Statut.ACTIF);
			serviceClient.update(c);
		}*/
		return SUCCESS;
	}
public String nouvauclient(){
	matriculefiscal=null;
	tel=null;
	address=null;
	compteur=null;
	nom=null;
	nombre=null;
	transport=0;
	
	return SUCCESS;
}
	public String saveclient() {
		//client = serviceClient.Findbymf(matriculefiscal);
		 	 
			client = new Client();
			client.setMf(matriculefiscal);
			client.setNom(nom);
			client.setAdresse(address);
			client.setCompteur(compteur);
			client.setTransport(transport);
			//client.setNombre(nombre);
			client.setTel(tel);
			serviceClient.save(client);
			listclient = new ArrayList<Client>();
			listclient = serviceClient.getAll();
			matriculefiscal = null;
			nom = null;
			address = null;			
			 
			return SUCCESS;
		 
	}

	public String updateClient(Client client) {
		try {
			getServiceClient().update(client);
			return SUCCESS;
		} catch (DataAccessException e) {
		}
		return ERROR;
	}

	public void onRowEdit(RowEditEvent event) {

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"client changÃ©", ((Client) event.getObject()).getNom());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		updateClient((Client) event.getObject());

	}

	public String deleteclient() {
		client2.setStatut(Statut.DEACTIF);
		
		  
		 	 
		serviceClient.delete(client2);
		listclient = new ArrayList<Client>();
		listclient = serviceClient.getAll();
		return SUCCESS;
	}

	public ServiceClient getServiceClient() {
		return serviceClient;
	}

	public void setServiceClient(ServiceClient serviceClient) {
		this.serviceClient = serviceClient;
	}

	 

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	 

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Client getClient2() {
		return client2;
	}

	public void setClient2(Client client2) {
		this.client2 = client2;
	}

	public List<Client> getListclient() {
		return listclient;
	}

	public void setListclient(List<Client> Listclient) {
		listclient = Listclient;
	}

	 
 

	public ServiceTracetransport getServiceTrace() {
		return serviceTrace;
	}

	public void setServiceTrace(ServiceTracetransport serviceTrace) {
		this.serviceTrace = serviceTrace;
	}

	public List<Client> getFilterclients() {
		return filterclients;
	}

	public void setFilterclients(List<Client> filterclients) {
		this.filterclients = filterclients;
	}

	public String getMatriculefiscal() {
		return matriculefiscal;
	}

	public void setMatriculefiscal(String matriculefiscal) {
		this.matriculefiscal = matriculefiscal;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCompteur() {
		return compteur;
	}

	public void setCompteur(Integer compteur) {
		this.compteur = compteur;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getTransport() {
		return transport;
	}
	public void setTransport(double transport) {
		this.transport = transport;
	}
	public ServiceBonLivraison getServiceBonLivraison() {
		return serviceBonLivraison;
	}
	public void setServiceBonLivraison(ServiceBonLivraison serviceBonLivraison) {
		this.serviceBonLivraison = serviceBonLivraison;
	}

	 

}
