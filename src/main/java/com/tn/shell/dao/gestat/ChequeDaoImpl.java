package com.tn.shell.dao.gestat;

 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.*;

 
 
@Repository
public class ChequeDaoImpl implements ChequeDao {
	@PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Cheque Cheque) {
	em.persist(Cheque);
		
	}
	 
	 @Transactional
	 public List<Cheque> getListchequebetweendate(Date date1,Date date2){
		 List<Cheque> result=new ArrayList<Cheque>();
		  result = em.createQuery("SELECT a FROM Cheque a where a.statut = :statut   and a.date between :date1 and :date2", Cheque.class)
					.setParameter("statut", Statut.ACTIF) 
					.setParameter("date1",date1) .setParameter("date2",date2) 
					.getResultList();
			
			if(result.size()>0)
		    return result;
			else return result;
	 }
	 @Transactional
	 public List<Cheque> getListchequeetatandbanque(Enumcheque etat,String banque){
		 List<Cheque> result=new ArrayList<Cheque>();
		  result = em.createQuery("SELECT a FROM Cheque a where a.statut = :statut   and a.etat = :caiise and a.banque = :b", Cheque.class)
					.setParameter("statut", Statut.ACTIF) 
					.setParameter("caiise",etat) 
					.setParameter("b",banque) 
					.getResultList();
			
			if(result.size()>0)
		    return result;
			else return result;
	 }
	 @Transactional
	 public List<Cheque> getListchequeetat(Enumcheque etat){
		 List<Cheque> result=new ArrayList<Cheque>();
		  result = em.createQuery("SELECT a FROM Cheque a where a.statut = :statut   and a.etat = :caiise", Cheque.class)
					.setParameter("statut", Statut.ACTIF) 
					.setParameter("caiise",etat) 
					.getResultList();
			
			if(result.size()>0)
		    return result;
			else {
				System.out.println("chargement erreur cheque entre");
				return result;
			}
			 
	 }
	 @Transactional
	 public List<Cheque> getListchequedate(String date){
		 List<Cheque> result=new ArrayList<Cheque>();
		  result = em.createQuery("SELECT a FROM Cheque a where a.statut = :statut   and a.dates = :caiise", Cheque.class)
					.setParameter("statut", Statut.ACTIF) 
					.setParameter("caiise",date) 
					.getResultList();
			
			if(result.size()>0)
		    return result;
			else return result;
	 }
	 @Transactional
	 public List<Cheque> getChequebyCaisse(Caisse c){
	 	List<Cheque> result=new ArrayList<Cheque>();
	   result = em.createQuery("SELECT a FROM Cheque a where a.statut = :statut   and a.caisse.id = :caiise and a.montant != 0", Cheque.class)
	 			.setParameter("statut", Statut.ACTIF) 
	 			.setParameter("caiise",c.getId()) 
	 			.getResultList();
	 	
	 	if(result.size()>0)
	     return result;
	 	else return result;
	 }
	 @Transactional
	public List<Cheque> getAll() {
		List<Cheque> result = em.createQuery("SELECT c FROM Cheque c where c.statut = :statut ", Cheque.class)
				.setParameter("statut", Statut.ACTIF)
				.getResultList();
	    return result;
	}
	 @Transactional
	public  List<Cheque> Findbynom(String nom) {
		 List<Cheque> ChequeListem=em.createQuery("SELECT c FROM  Cheque c where c.numero = :nom and c.statut = :statut",Cheque.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
	        
	        if (ChequeListem.size() > 0){
	        	System.out.println("objet trouvé "+"\n\n\n");
	            return ChequeListem;}
	        else{
	        	System.out.println("\n\nl  objet Cheque n exsite pas\n\n");
	            return null;}   
	}
	 
	 @Transactional
		public Cheque Findbycode(Integer nom) {
			 List<Cheque> ChequeListem=em.createQuery("SELECT c FROM  Cheque c where c.id = :nom and c.statut = :statut",Cheque.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
		        
		        if (ChequeListem.size() > 0){
		        	System.out.println("objet trouvé "+"\n\n\n");
		            return ChequeListem.get(0);}
		        else{
		        	System.out.println("\n\nl  objet Cheque n exsite pas\n\n");
		            return null;}   
		}
 
	 @Transactional
	public Cheque Findbymf(String nom) {
		 List<Cheque> ChequeListem=em.createQuery("SELECT c FROM  Cheque c where c.mf = :nom and c.statut = :statut",Cheque.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
	        
	        if (ChequeListem.size() > 0){
	        	System.out.println("objet trouvé "+"\n\n\n");
	            return ChequeListem.get(0);}
	        else{
	        	System.out.println("\n\nl  objet Cheque n exsite pas\n\n");
	            return null;}   
	}
	 @Transactional
	public void update(Cheque d) {
		Cheque c=em.find(Cheque.class, d.getId());		 
		 c.setEtat(d.getEtat());
		 c.setBanque(d.getBanque());
		 c.setMontant(d.getMontant());
		 c.setNumero(d.getNumero());
		 c.setEcheance(d.getEcheance());
	 
		em.merge(c);
	}
	 @Transactional
	public void delete(Cheque Cheque) {
		 Cheque c=em.find(Cheque.class, Cheque.getId());
		 
		 c.setStatut(Statut.DEACTIF);
		 em.merge(c);
		
	}

}
