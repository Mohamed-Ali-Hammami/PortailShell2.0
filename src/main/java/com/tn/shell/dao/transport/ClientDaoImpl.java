package com.tn.shell.dao.transport;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tn.shell.model.transport.*;
 
@Repository
public class ClientDaoImpl implements ClientDao {
	@PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Client client) {
	em.persist(client);
		
	}
	 @Transactional
	 public List<String> getAllnom(){
		 List<String> l=new ArrayList<String>();
		 List<Client> result = em.createQuery("SELECT c  FROM Client c where c.statut = :statut ", Client.class)
					.setParameter("statut", Statut.ACTIF) 
					.getResultList();
		if(result!=null) for(Client c:result) l.add(c.getNom());
		    return l  ;
	 }
	 @Transactional
	public List<Client> getAll() {
		List<Client> result = em.createQuery("SELECT c FROM Client c where c.statut = :statut ", Client.class)
				.setParameter("statut", Statut.ACTIF)
				.getResultList();
	    return result;
	}
	 @Transactional
	public Client Findbynom(String nom) {
		 List<Client> ClientListem=em.createQuery("SELECT c FROM  Client c where c.nom = :nom and c.statut = :statut",Client.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
	        
	        if (ClientListem.size() > 0){
	            return ClientListem.get(0);}
	        else{
	            return null;}   
	}
	 
	 @Transactional
		public Client Findbycode(Integer nom) {
			 List<Client> ClientListem=em.createQuery("SELECT c FROM  Client c where c.code = :nom and c.statut = :statut",Client.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
		        
		        if (ClientListem.size() > 0){
		            return ClientListem.get(0);}
		        else{
		            return null;}   
		}
	 
	 @Transactional
	public Client Findbymf(String nom) {
		 List<Client> ClientListem=em.createQuery("SELECT c FROM  Client c where c.mf = :nom and c.statut = :statut",Client.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
	        
	        if (ClientListem.size() > 0){
	            return ClientListem.get(0);}
	        else{
	            return null;}   
	}
	 @Transactional
	public void update(Client client) {
		Client c=em.find(Client.class, client.getCode());
		c.setMf(client.getMf());
		c.setAdresse(client.getAdresse());
		c.setNom(client.getNom());
		c.setStatut(client.getStatut());
		c.setTransport(client.getTransport());
		em.merge(c);
	}
	 @Transactional
	public void delete(Client client) {
		 Client c=em.find(Client.class, client.getCode());
		 
		 c.setStatut(client.getStatut());
		 em.merge(c);
		
	}

}
