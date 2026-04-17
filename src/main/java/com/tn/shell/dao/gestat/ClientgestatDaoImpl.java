package com.tn.shell.dao.gestat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.*;

 
 
@Repository
public class ClientgestatDaoImpl implements ClientgestatDao {
	@PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Clientgestat client) {
	em.persist(client);
		
	}
	 
	 public double getAllmontantcredit() {
			Query q = em.createQuery(
					"SELECT SUM(c.reste) FROM Clientgestat c where c.statut = :statut and c.reste > 0  "
					).setParameter("statut", Statut.ACTIF) ;
					 
			try {
				Double result = (Double) q.getSingleResult();

				return result;
			} catch (Exception e) {
				return 0;
			}

		}

	 @Transactional
	 public List<String> getAllnom(){
		 List<String> l=new ArrayList<String>();
		 List<Clientgestat> result = em.createQuery("SELECT c  FROM Clientgestat c where c.statut = :statut ", Clientgestat.class)
					.setParameter("statut", Statut.ACTIF) 
					.getResultList();
		if(result!=null) for(Clientgestat c:result) l.add(c.getNom());
		    return l  ;
	 }
	 @Transactional
	public List<Clientgestat> getAll() {
		List<Clientgestat> result = em.createQuery("SELECT c FROM Clientgestat c where c.statut = :statut ", Clientgestat.class)
				.setParameter("statut", Statut.ACTIF)
				.getResultList();
	    return result;
	}
	 @Transactional
	 public List<Clientgestat> getclientendepassement(){
		 List<Clientgestat> l=new ArrayList<Clientgestat>();
		 List<Clientgestat> ClientListem=em.createQuery("SELECT c FROM  Clientgestat c   where c.statut = :statut ",Clientgestat.class)
				 .setParameter("statut", Statut.ACTIF) 
				 .getResultList();
	        
	        if (ClientListem.size() > 0){
	        	for (Clientgestat c:ClientListem) {
	        		if(c.getReste()>=0)
	        			l.add(c);
	        	}
	            return l ;}
	        else{
	            return null;}   
	 }
	 @Transactional
	 public List<Clientgestat> getclientenAvance(){
		 List<Clientgestat> l=new ArrayList<Clientgestat>();
		 List<Clientgestat> ClientListem=em.createQuery("SELECT c FROM  Clientgestat c where     c.statut = :statut",Clientgestat.class).setParameter("statut", Statut.ACTIF) .getResultList();
	        
		 if (ClientListem.size() > 0){
	        	for (Clientgestat c:ClientListem) {
	        		if(c.getReste()<0)
	        			l.add(c);
	        	}
	            return l ;}
	        else{
	            return null;}   
	 }
	 @Transactional
	public Clientgestat Findbynom(String nom) {
		 List<Clientgestat> ClientListem=em.createQuery("SELECT c FROM  Clientgestat c where c.nom = :nom and c.statut = :statut",Clientgestat.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
	        
	        if (ClientListem.size() > 0){
	            return ClientListem.get(0);}
	        else{
	            return null;}   
	}
	 
	 @Transactional
		public Clientgestat Findbycode(Integer nom) {
			 List<Clientgestat> ClientListem=em.createQuery("SELECT c FROM  Clientgestat c where c.code = :nom and c.statut = :statut",Clientgestat.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
		        
		        if (ClientListem.size() > 0){
		            return ClientListem.get(0);}
		        else{
		            return null;}   
		}
	 
	 @Transactional
	public Clientgestat Findbymf(String nom) {
		 List<Clientgestat> ClientListem=em.createQuery("SELECT c FROM  Clientgestat c where c.mf = :nom and c.statut = :statut",Clientgestat.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
	        
	        if (ClientListem.size() > 0){
	            return ClientListem.get(0);}
	        else{
	            return null;}   
	}
	 @Transactional
	public void update(Clientgestat client) {
		Clientgestat c=em.find(Clientgestat.class, client.getCode());		 
		c.setPlafond( client.getPlafond());
		c.setChiffreaffaire( client.getChiffreaffaire());
		c.setReste( client.getReste());
		c.setNom(client.getNom());
		c.setPrenom( client.getPrenom());
		c.setTel( client.getTel());
		c.setStatut(client.getStatut());
		 
		em.merge(c);
	}
	 @Transactional
	public void delete(Clientgestat client) {
		 Clientgestat c=em.find(Clientgestat.class, client.getCode());
		 
		 c.setStatut(client.getStatut());
		 em.merge(c);
		
	}

}
