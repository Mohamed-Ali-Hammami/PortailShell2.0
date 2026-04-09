package com.tn.shell.dao.transport;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.transport.*;

 
@Repository
public class LigneCommandepassDaoImpl implements LigneCommandepassdao {
	
	 @PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Lignecommandepass c) {
		em.persist(c);
		
	}
	 @Transactional
	 public List<Lignecommandepass> getLcbyBL(Bonlivraison bl){
		 List<Lignecommandepass> result = em.createQuery("SELECT c FROM Lignecommandepass c where c.statut = :statut and c.bl.numero = :numero and c.bl.statut = :statut2", Lignecommandepass.class)
				 .setParameter("statut", Statut.ACTIF)
				 .setParameter("numero", bl.getNumero())
				 .setParameter("statut2", Statut.ACTIF)
				  
				 .getResultList();
		 return result;
	 }
	 @Transactional
	 public Lignecommandepass getLignecommandebyproduit(Integer p ,Facture f) {
		 List<Lignecommandepass> result = em.createQuery("SELECT c FROM Lignecommandepass c where c.statut = :statut "
		 		+ "and c.produit.id = :id and c.facture.numero = :numero", Lignecommandepass.class)
				 .setParameter("statut", Statut.ACTIF)
				 .setParameter("id", p ) 
				 .setParameter("numero", f.getNumero()).getResultList();
		 
		 if (result.size() > 0){
	        	System.out.println("objet trouvé "+result.get(0).getId()+"\n\n\n");
	            return result.get(0);}
	        else{
	        	System.out.println("\n\nl  objet lignecoamanse n exsite pas\n\n");
	            return null;}   
		 
		   
	       
	 }
	 @Transactional
	 public List<Lignecommandepass> getLcbyf(Facturepassager bl){
		 List<Lignecommandepass> result = em.createQuery("SELECT c FROM Lignecommandepass c where c.statut = :statut and c.facturepassager.numero = :numero", Lignecommandepass.class).setParameter("statut", Statut.ACTIF).setParameter("numero", bl.getNumero()).getResultList();
		 return result;
	 }
	 @Transactional	 
	 public List<Lignecommandepass> getAll(){
		 List<Lignecommandepass> result = em.createQuery("SELECT c FROM Lignecommandepass c where c.statut = :statut", Lignecommandepass.class).setParameter("statut", Statut.ACTIF).getResultList();
		 return result;
	 }
	 @Transactional
	public void update(Lignecommandepass c) {
		Lignecommandepass lc=em.find(Lignecommandepass.class, c.getId());
		 
		 
		lc.setTauxtva(c.getTauxtva());
		em.merge(lc);
		
	}
	 
	 @Transactional
		public void delete(Lignecommandepass c) {
			Lignecommandepass lc=em.find(Lignecommandepass.class, c.getId());
			 			 
			em.remove(lc);
			
		}
	 

}
