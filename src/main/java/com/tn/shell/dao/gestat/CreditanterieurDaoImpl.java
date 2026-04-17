package com.tn.shell.dao.gestat;

 
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.*;
 
 
@Repository
public class CreditanterieurDaoImpl implements CreditanterieurDao {
	@PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Creditanterieur Creditanterieur) {
	em.persist(Creditanterieur);
		
	}
	  
	 @Transactional
	public List<Creditanterieur> getAll() {
		List<Creditanterieur> result = em.createQuery("SELECT c FROM Creditanterieur c where c.statut = :statut ", Creditanterieur.class)
				.setParameter("statut", Statut.ACTIF)
				.getResultList();
	    return result;
	}
	 
	 @Transactional
	 public List<Creditanterieur> getCreditanterieurbyCaisse(Caisse c){
		 List<Creditanterieur> result=new ArrayList<Creditanterieur>();
		  result = em.createQuery("SELECT a FROM Creditanterieur a where a.statut = :statut   and a.caisse.id = :caiise and a.montant != 0 order by a.client.code", Creditanterieur.class)
					.setParameter("statut", Statut.ACTIF) 
					.setParameter("caiise",c.getId()) 
					.getResultList();
			
			if(result.size()>0)
		    return result;
			else return result;
	 }
	 @Transactional
	public List<Creditanterieur> getCreditanterieurbyclient(Clientgestat c ,String dates){
		 List<Creditanterieur> result=new ArrayList<Creditanterieur>();
		 result = em.createQuery("SELECT a FROM Creditanterieur a where a.statut = :statut   and a.client.code = :client and a.dates = :dates", Creditanterieur.class)
					.setParameter("statut", Statut.ACTIF) 
					.setParameter("client",c.getCode()) 
					.setParameter("dates",dates) 
					.getResultList();
		 if(result.size()>0)
			    return result;
				else return null;
	 }
	 @Transactional
	 public  Creditanterieur getCreditclientbyCaisseandclient(Creditanterieur cl){
		 List<Creditanterieur> result=new ArrayList<Creditanterieur>();
		 result = em.createQuery("SELECT a FROM Creditanterieur a where a.statut = :statut   and a.id = :client", Creditanterieur.class)
					.setParameter("statut", Statut.ACTIF) 
					.setParameter("client",cl.getId())
					 
					.getResultList();
		 if(result.size()>0)
			    return result.get(0);
				else return null; 
	 }
	 
	 @Transactional
	 public List<Creditanterieur> getListcreditdate(String date){
		 List<Creditanterieur> result=new ArrayList<Creditanterieur>();
		  result = em.createQuery("SELECT a FROM Creditanterieur a where a.statut = :statut   and a.dates = :caiise order by a.client.code", Creditanterieur.class)
					.setParameter("statut", Statut.ACTIF) 
					.setParameter("caiise",date) 
					.getResultList();
			
			if(result.size()>0)
		    return result;
			else return result;
	 }
	 @Transactional
	public  List<Creditanterieur> Findbynom(String nom) {
		 List<Creditanterieur> CreditanterieurListem=em.createQuery("SELECT c FROM  Creditanterieur c where c.numero = :nom and c.statut = :statut",Creditanterieur.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
	        
	        if (CreditanterieurListem.size() > 0){
	            return CreditanterieurListem;}
	        else{
	            return null;}   
	}
	 
	 @Transactional
		public Creditanterieur Findbycode(Integer nom) {
			 List<Creditanterieur> CreditanterieurListem=em.createQuery("SELECT c FROM  Creditanterieur c where c.code = :nom and c.statut = :statut",Creditanterieur.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
		        
		        if (CreditanterieurListem.size() > 0){
		            return CreditanterieurListem.get(0);}
		        else{
		            return null;}   
		}
	 
	 @Transactional
	public Creditanterieur Findbymf(String nom) {
		 List<Creditanterieur> CreditanterieurListem=em.createQuery("SELECT c FROM  Creditanterieur c where c.mf = :nom and c.statut = :statut",Creditanterieur.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
	        
	        if (CreditanterieurListem.size() > 0){
	            return CreditanterieurListem.get(0);}
	        else{
	            return null;}   
	}
	 @Transactional
	public void update(Creditanterieur a) {
		Creditanterieur c=em.find(Creditanterieur.class, a.getId());		 
		 
		 c.setMontant(a.getMontant());
		 c.setDate(a.getDate());
		 c.setDates(a.getDates());
		 c.setClient(a.getClient());
		em.merge(c);
	}
	 @Transactional
	public void delete(Creditanterieur Creditanterieur) {
		 Creditanterieur c=em.find(Creditanterieur.class, Creditanterieur.getId());
		 
		 
		 em.merge(c);
		
	}
	 @Transactional
	 public Creditanterieur getcreditbyid(Integer id) {
		 List<Creditanterieur> result=new ArrayList<Creditanterieur>();
		  result = em.createQuery("SELECT a FROM Creditanterieur a where a.statut = :statut   and a.id = :caiise", Creditanterieur.class)
					.setParameter("statut", Statut.ACTIF) 
					.setParameter("caiise",id) 
					.getResultList();
			
			if(result.size()>0)
		    return result.get(0);
			else return null;
	 }

}
