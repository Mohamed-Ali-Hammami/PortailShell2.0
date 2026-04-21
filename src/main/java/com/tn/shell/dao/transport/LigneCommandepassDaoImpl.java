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
	 private static final String ACTIVE_STATUS_SQL = "(lp.statut is null or trim(lp.statut) = '' or lower(trim(lp.statut)) = 'actif')";
	 
	 @Transactional
	public void save(Lignecommandepass c) {
		em.persist(c);
		
	}
	 @Transactional
	 public List<Lignecommandepass> getLcbyBL(Bonlivraison bl){
		 try {
			 return em.createQuery(
					 "SELECT c FROM Lignecommandepass c where c.statut = :statut and c.facturepassager.numero = :numero",
					 Lignecommandepass.class)
					 .setParameter("statut", Statut.ACTIF)
					 .setParameter("numero", bl.getNumero())
					 .getResultList();
		 } catch (RuntimeException ex) {
			 return em.createNativeQuery(
					 "SELECT * FROM lignecommandepass lp WHERE " + ACTIVE_STATUS_SQL + " AND lp.facturepassagerid = :numero ORDER BY lp.id DESC",
					 Lignecommandepass.class)
					 .setParameter("numero", bl.getNumero())
					 .getResultList();
		 }
	 }
	 @Transactional
	 public Lignecommandepass getLignecommandebyproduit(Integer p ,Facture f) {
		 List<Lignecommandepass> result = em.createQuery("SELECT c FROM Lignecommandepass c where c.statut = :statut "
		 		+ "and c.produit.id = :id and c.facturepassager.numero = :numero", Lignecommandepass.class)
				 .setParameter("statut", Statut.ACTIF)
				 .setParameter("id", p ) 
				 .setParameter("numero", f.getNumero()).getResultList();
		 
		 if (result.size() > 0){
	            return result.get(0);}
	        else{
	            return null;}   
		 
		   
	       
	 }
	 @Transactional
	 public List<Lignecommandepass> getLcbyf(Facturepassager bl){
		 try {
			 return em.createQuery("SELECT c FROM Lignecommandepass c where c.statut = :statut and c.facturepassager.numero = :numero", Lignecommandepass.class)
					 .setParameter("statut", Statut.ACTIF).setParameter("numero", bl.getNumero()).getResultList();
		 } catch (RuntimeException ex) {
			 return em.createNativeQuery(
					 "SELECT * FROM lignecommandepass lp WHERE " + ACTIVE_STATUS_SQL + " AND lp.facturepassagerid = :numero ORDER BY lp.id DESC",
					 Lignecommandepass.class)
					 .setParameter("numero", bl.getNumero())
					 .getResultList();
		 }
	 }
	 @Transactional	 
	 public List<Lignecommandepass> getAll(){
		 try {
			 return em.createQuery("SELECT c FROM Lignecommandepass c where c.statut = :statut", Lignecommandepass.class).setParameter("statut", Statut.ACTIF).getResultList();
		 } catch (RuntimeException ex) {
			 return em.createNativeQuery(
					 "SELECT * FROM lignecommandepass lp WHERE " + ACTIVE_STATUS_SQL + " ORDER BY lp.id DESC",
					 Lignecommandepass.class)
					 .getResultList();
		 }
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
