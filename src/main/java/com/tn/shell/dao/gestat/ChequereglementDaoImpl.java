package com.tn.shell.dao.gestat;

 
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.*;

 
 
@Repository
public class ChequereglementDaoImpl implements ChequeredlementDao {
	@PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Chequereglement Cheque) {
	em.persist(Cheque);
		
	}
	 @Transactional
	 public List<Chequereglement> getListchequeetatandbanque(Enumcheque etat,String banque){
		 List<Chequereglement> result=new ArrayList<Chequereglement>();
		  result = em.createQuery("SELECT a FROM Chequereglement a where a.statut = :statut   and a.etat = :caiise", Chequereglement.class)
					.setParameter("statut", Statut.ACTIF) 
					.setParameter("caiise",Enumcheque.Encours) 
					//.setParameter("b",banque)  and a.banque = :b
					.getResultList();
			
			if(result.size()>0)
		    return result;
			else {
				
				System.out.println("erreur de chargemnt list en cours");
				return result;}
	 }
	 @Transactional
	 public List<Chequereglement> getListchequedate(String date){
		 List<Chequereglement> result=new ArrayList<Chequereglement>();
		  result = em.createQuery("SELECT a FROM Chequereglement a where a.statut = :statut   and a.dates = :caiise", Chequereglement.class)
					.setParameter("statut", Statut.ACTIF) 
					.setParameter("caiise",date) 
					.getResultList();
			
			if(result.size()>0)
		    return result;
			else return result;
	 }
	 @Transactional
	 public List<Chequereglement> getChequebyCaisse(Caisse c){
	 	List<Chequereglement> result=new ArrayList<Chequereglement>();
	   result = em.createQuery("SELECT a FROM Chequereglement a where a.statut = :statut   and a.caisse.id = :caiise and a.montant != 0", Chequereglement.class)
	 			.setParameter("statut", Statut.ACTIF) 
	 			.setParameter("caiise",c.getId()) 
	 			.getResultList();
	 	
	 	if(result.size()>0)
	     return result;
	 	else return result;
	 }
	 @Transactional
	public List<Chequereglement> getAll() {
		List<Chequereglement> result = em.createQuery("SELECT c FROM Chequereglement c where c.statut = :statut ", Chequereglement.class)
				.setParameter("statut", Statut.ACTIF)
				.getResultList();
	    return result;
	}
	 @Transactional
	public  List<Chequereglement> Findbynom(String nom) {
		 List<Chequereglement> ChequeListem=em.createQuery("SELECT c FROM  Chequereglement c where c.numero = :nom and c.statut = :statut",Chequereglement.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
	        
	        if (ChequeListem.size() > 0){
	        	System.out.println("objet trouvé "+"\n\n\n");
	            return ChequeListem;}
	        else{
	        	System.out.println("\n\nl  objet Cheque n exsite pas\n\n");
	            return null;}   
	}
	 
	 @Transactional
		public Chequereglement Findbycode(Integer nom) {
			 List<Chequereglement> ChequeListem=em.createQuery("SELECT c FROM  Chequereglement c where c.id = :nom and c.statut = :statut",Chequereglement.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
		        
		        if (ChequeListem.size() > 0){
		        	System.out.println("objet trouvé "+"\n\n\n");
		            return ChequeListem.get(0);}
		        else{
		        	System.out.println("\n\nl  objet Cheque n exsite pas\n\n");
		            return null;}   
		}
 
	 @Transactional
	public Chequereglement Findbymf(String nom) {
		 List<Chequereglement> ChequeListem=em.createQuery("SELECT c FROM  Chequereglement c where c.mf = :nom and c.statut = :statut",Chequereglement.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
	        
	        if (ChequeListem.size() > 0){
	        	System.out.println("objet trouvé "+"\n\n\n");
	            return ChequeListem.get(0);}
	        else{
	        	System.out.println("\n\nl  objet Cheque n exsite pas\n\n");
	            return null;}   
	}
	 @Transactional
	public void update(Chequereglement d) {
		 Chequereglement c=em.find(Chequereglement.class, d.getId());		 
		 c.setEtat(d.getEtat());
		 c.setEcheance(d.getEcheance());
		 c.setBanque(d.getBanque());
		 c.setMontant(d.getMontant());
		 c.setNumero(d.getNumero());
		 c.setEcheance(d.getEcheance());
	 
		em.merge(c);
	}
	 @Transactional
	public void delete(Chequereglement Cheque) {
		 Chequereglement c=em.find(Chequereglement.class, Cheque.getId());
		 
		 c.setStatut(Statut.DEACTIF);
		 em.merge(c);
		
	}
	public List<Chequereglement> getListchequeetat(Enumcheque etat) {
		 List<Chequereglement> result=new ArrayList<Chequereglement>();
		  result = em.createQuery("SELECT a FROM Chequereglement a where a.statut = :statut   and a.etat = :caiise", Chequereglement.class)
					.setParameter("statut", Statut.ACTIF) 
					.setParameter("caiise",etat) 
					.getResultList();
			
			if(result.size()>0)
		    return result;
			else return result;
	}

}
