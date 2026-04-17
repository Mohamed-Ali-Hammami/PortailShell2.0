package com.tn.shell.dao.gestat;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.*;

 
 
@Repository
public class ClientpassationDaoImpl implements ClientpassationDao {
	@PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Clientpassation client) {
	em.persist(client);
		
	}
	 @Transactional
	 public List<String> getAllnom(){
		 List<String> l=new ArrayList<String>();
		 List<Clientpassation> result = em.createQuery("SELECT c  FROM Clientpassation c where c.statut = :statut ", Clientpassation.class)
					.setParameter("statut", Statut.ACTIF) 
					.getResultList();
		if(result!=null) for(Clientpassation c:result) l.add(c.getNom());
		    return l  ;
	 }
	 @Transactional
	public List<Clientpassation> getAll() {
		List<Clientpassation> result = em.createQuery("SELECT c FROM Clientpassation c where c.statut = :statut ", Clientpassation.class)
				.setParameter("statut", Statut.ACTIF)
				.getResultList();
	    return result;
	}
	 
	 @Transactional
		public List<Clientpassation> finByReste() {
			List<Clientpassation> result = em.createQuery("SELECT c FROM Clientpassation c where c.statut = :statut and c.reste != 0", Clientpassation.class)
					.setParameter("statut", Statut.ACTIF)
					.getResultList();
		    return result;
		}
	 @Transactional
	 public List<Clientpassation> getclientendepassement(){
		 List<Clientpassation> l=new ArrayList<Clientpassation>();
		 List<Clientpassation> ClientListem=em.createQuery("SELECT c FROM  Clientpassation c   where c.statut = :statut ",Clientpassation.class)
				 .setParameter("statut", Statut.ACTIF) 
				 .getResultList();
	        
	        if (ClientListem.size() > 0){
	        	for (Clientpassation c:ClientListem) {
	        		if(c.getReste()>=0)
	        			l.add(c);
	        	}
	            return l ;}
	        else{
	            return null;}   
	 }
	 @Transactional
	 public List<Clientpassation> getclientenAvance(){
		 List<Clientpassation> l=new ArrayList<Clientpassation>();
		 List<Clientpassation> ClientListem=em.createQuery("SELECT c FROM  Clientpassation c where     c.statut = :statut",Clientpassation.class).setParameter("statut", Statut.ACTIF) .getResultList();
	        
		 if (ClientListem.size() > 0){
	        	for (Clientpassation c:ClientListem) {
	        		if(c.getReste()<0)
	        			l.add(c);
	        	}
	            return l ;}
	        else{
	            return null;}   
	 }
	 @Transactional
	public Clientpassation Findbynom(String nom) {
		 List<Clientpassation> ClientListem=em.createQuery("SELECT c FROM  Clientpassation c where c.nom = :nom and c.statut = :statut",Clientpassation.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
	        
	        if (ClientListem.size() > 0){
	            return ClientListem.get(0);}
	        else{
	            return null;}   
	}
	 
	 @Transactional
		public Clientpassation Findbycode(Integer nom) {
			 List<Clientpassation> ClientListem=em.createQuery("SELECT c FROM  Clientpassation c where c.code = :nom and c.statut = :statut",Clientpassation.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
		        
		        if (ClientListem.size() > 0){
		            return ClientListem.get(0);}
		        else{
		            return null;}   
		}
	 
	 @Transactional
	public Clientpassation Findbymf(String nom) {
		 List<Clientpassation> ClientListem=em.createQuery("SELECT c FROM  Clientpassation c where c.mf = :nom and c.statut = :statut",Clientpassation.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
	        
	        if (ClientListem.size() > 0){
	            return ClientListem.get(0);}
	        else{
	            return null;}   
	}
	 @Transactional
	public void update(Clientpassation client) {
		Clientpassation c=em.find(Clientpassation.class, client.getId());		 
		 c.setAvance(client.getAvance());
		c.setReste( client.getReste());
		c.setNom(client.getNom());
		 c.setDernierdates(client.getDernierdates());
		 c.setTel( client.getTel());
		c.setStatut(client.getStatut());
		 
		em.merge(c);
	}
	 @Transactional
	public void delete(Clientpassation client) {
		 Clientpassation c=em.find(Clientpassation.class, client.getId());
		 
		 c.setStatut(client.getStatut());
		 em.merge(c);
		
	}

}
