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
	private static final String ACTIVE_STATUS_SQL = "(f.statut is null or trim(f.statut) = '' or lower(trim(f.statut)) = 'actif')";
	 
	 @Transactional
	public void save(Client client) {
	em.persist(client);
		
	}
	 @Transactional
	 public List<String> getAllnom(){
		 List<String> l=new ArrayList<String>();
		 List<Client> result = getAll();
		if(result!=null) for(Client c:result) l.add(c.getNom());
		    return l  ;
	 }
	@Transactional
	public List<Client> getAll() {
		try {
			List<Client> result = em.createQuery("SELECT c FROM Client c where c.statut = :statut ", Client.class)
					.setParameter("statut", Statut.ACTIF)
					.getResultList();
			if (result != null && !result.isEmpty()) {
				return result;
			}
		} catch (RuntimeException ex) {
		}
		List<Object[]> rows = em.createNativeQuery(
				"SELECT code,mf,tel,adresse,compteur,ca,nom,nbre,transport,statut "
						+ "FROM client WHERE " + ACTIVE_STATUS_SQL + " ORDER BY CAST(code AS UNSIGNED) DESC")
				.getResultList();
		List<Client> clients = new ArrayList<Client>();
		for (Object[] row : rows) {
			Client client = new Client();
			client.setCode(TransportTsvMapper.asInteger(row[0]));
			client.setMf(TransportTsvMapper.asString(row[1]));
			client.setTel(TransportTsvMapper.asString(row[2]));
			client.setAdresse(TransportTsvMapper.asString(row[3]));
			client.setCompteur(TransportTsvMapper.asInteger(row[4]));
			client.setCa(TransportTsvMapper.asString(row[5]));
			client.setNom(TransportTsvMapper.asString(row[6]));
			client.setNbre(TransportTsvMapper.asString(row[7]));
			client.setTransport(TransportTsvMapper.asDouble(row[8]));
			client.setStatut(TransportTsvMapper.asStatut(row[9]));
			clients.add(client);
		}
		return clients;
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
