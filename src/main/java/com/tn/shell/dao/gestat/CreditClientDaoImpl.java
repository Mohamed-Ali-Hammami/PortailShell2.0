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
public class CreditClientDaoImpl implements CreditClientDao {
	@PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Credit Creditclient) {
	em.persist(Creditclient);
		
	}
	  
	 @Transactional
	public List<Credit> getAll() {
		List<Credit> result = em.createQuery("SELECT c FROM Credit c where c.statut = :statut ", Credit.class)
				.setParameter("statut", Statut.ACTIF)
				.getResultList();
	    return result;
	}
	 
	 @Transactional
	 public List<Credit> getListcreditdate(String date){
		 List<Credit> result=new ArrayList<Credit>();
		  result = em.createQuery("SELECT a FROM Credit a where a.statut = :statut and   a.montant > 0   and a.dates = :caiise order by a.client.code", Credit.class)
					.setParameter("statut", Statut.ACTIF) 
					.setParameter("caiise",date) 
					.getResultList();
			
			if(result.size()>0)
		    return result;
			else return result;
	 }
	 @Transactional
	 public List<Credit> getListcreditdateandclient(String date,Clientgestat c){
		 List<Credit> result=new ArrayList<Credit>();
		  result = em.createQuery("SELECT a FROM Credit a where a.statut = :statut   and a.dates = :caiise and a.client.code = :code", Credit.class)
					.setParameter("statut", Statut.ACTIF) 
					.setParameter("caiise",date)
					.setParameter("code",c.getCode()) 
					.getResultList();
			
			if(result.size()>0)
		    return result;
			else return result; 
	 }
	 @Transactional
	 public double  getAllcreditbyclientanddates(Date d1,Date d2,Clientgestat c){
		 Query q= em.createQuery("SELECT SUM(montant) FROM Credit a where a.statut = :statut   and a.date between :d1 and :d2 and a.client.code = :code")
					.setParameter("statut", Statut.ACTIF) 
					.setParameter("d1",d1).setParameter("d2",d2)
					.setParameter("code",c.getCode()) 
					;
			
		Double result = (Double) q.getSingleResult ();
		if(result !=null)
		return result;
		else return  (double) 0;
	 }
	 @Transactional
	public  List<Credit> Findbynom(String nom) {
		 List<Credit> CreditclientListem=em.createQuery("SELECT c FROM  Credit c where c.numero = :nom and c.statut = :statut",Credit.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
	        
	        if (CreditclientListem.size() > 0){
	        	System.out.println("objet trouvé "+"\n\n\n");
	            return CreditclientListem;}
	        else{
	        	System.out.println("\n\nl  objet Creditclient n exsite pas\n\n");
	            return null;}   
	}
	 @Transactional
	 public List<Credit> getCreditclientbyCaisse(Caisse c){
		 List<Credit> result=new ArrayList<Credit>();
		  result = em.createQuery("SELECT a FROM Credit a where a.statut = :statut   and a.caisse.id = :caiise and a.montant != 0 order by a.client.code", Credit.class)
					.setParameter("statut", Statut.ACTIF) 
					.setParameter("caiise",c.getId()) 
					.getResultList();
			
			if(result.size()>0) {
				System.out.println(""+result.get(0).getClient().getNom());
				  return result;
			}
		  
			else {
				System.out.println("\n\npas de crdit"+c.getId());
				return result;}
	 }
	 @Transactional
	 public Credit getCreditclientbyCaisseandclient(Credit cl){
		 List<Credit> result=new ArrayList<Credit>();
		  result = em.createQuery("SELECT a FROM Credit a where a.statut = :statut     and a.id = :code", Credit.class)
					.setParameter("statut", Statut.ACTIF) 
					 
					.setParameter("code",cl.getId()) 
					.getResultList();
			
			if(result.size()>0) {
				System.out.println(""+result.get(0).getClient().getNom());
				  return result.get(0);
			}
		  
			else {
				System.out.println("\n\npas de crdit"+cl.getId());
				return null;}
	 }
	 @Transactional
		public Credit Findbycode(Integer nom) {
			 List<Credit> CreditclientListem=em.createQuery("SELECT c FROM  Credit c where c.code = :nom and c.statut = :statut",Credit.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
		        
		        if (CreditclientListem.size() > 0){
		        	System.out.println("objet trouvé "+"\n\n\n");
		            return CreditclientListem.get(0);}
		        else{
		        	System.out.println("\n\nl  objet Creditclient n exsite pas\n\n");
		            return null;}   
		}
	 
	 @Transactional
	public Credit Findbymf(String nom) {
		 List<Credit> CreditclientListem=em.createQuery("SELECT c FROM  Credit c where c.mf = :nom and c.statut = :statut",Credit.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
	        
	        if (CreditclientListem.size() > 0){
	        	System.out.println("objet trouvé "+"\n\n\n");
	            return CreditclientListem.get(0);}
	        else{
	        	System.out.println("\n\nl  objet Creditclient n exsite pas\n\n");
	            return null;}   
	}
	 @Transactional
	public void update(Credit a) {
		Credit c=em.find(Credit.class, a.getId());		 
		 c.setMontant(a.getMontant());
		 c.setDates(a.getDates());
		 c.setDate(a.getDate());
		 c.setClient(a.getClient());
		 c.setNumbon(a.getNumbon());
		 c.setTels(a.getTels());
		em.merge(c);
	}
	 @Transactional
	public void delete(Credit Creditclient) {
		 Credit c=em.find(Credit.class, Creditclient.getId());
		 
		 
		 em.merge(c);
		
	}

	public Credit getcreditbyid(Integer id) {
		 List<Credit > result=new ArrayList<Credit >();
		  result = em.createQuery("SELECT a FROM Credit  a where a.statut = :statut   and a.id = :caiise", Credit .class)
					.setParameter("statut", Statut.ACTIF) 
					.setParameter("caiise",id) 
					.getResultList();
			
			if(result.size()>0)
		    return result.get(0);
			else return null;
	}

}
