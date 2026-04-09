package com.tn.shell.dao.paie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.paie.*;

 
@Repository
public class LigneGestionPaieDaoImpl implements LigneGestionpaiedao {
	
	 @PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Lignepaiegestion c) {
		em.persist(c);
		
	}
	 
	 @Transactional	 
	 public List<Lignepaiegestion> getAll(){
		 List<Lignepaiegestion> result = em.createQuery("SELECT c FROM Lignepaiegestion c where c.statut = :statut", Lignepaiegestion.class).setParameter("statut", Statut.ACTIF).getResultList();
		 return result;
	 }
	 @Transactional
	public void update(Lignepaiegestion c) {
		 Lignepaiegestion lc=em.find(Lignepaiegestion.class, c.getId());
		 lc.setValeurdeprimeafficher(c.getValeurdeprimeafficher());
		 lc.setValeurdeprime(c.getValeurdeprime());
		 lc.setPaie(c.getPaie());
		 lc.setLignegestion(c.getLignegestion());
		em.merge(lc);
		
	}
	 @Transactional
	 public List<Lignepaiegestion> getlistbypaie(Paie e){
		 List<Lignepaiegestion> result = em.createQuery("SELECT c FROM Lignepaiegestion c where c.statut = :statut and c.paie.id = :matricule", Lignepaiegestion.class)
				 .setParameter("statut", Statut.ACTIF)
				 .setParameter("matricule", e.getId())
				 .getResultList();
		 return result;
	 }
	 @Transactional
	 public  Lignepaiegestion getlignebygestion(Lignegestion e){
		 List<Lignepaiegestion> result = em.createQuery("SELECT c FROM Lignepaiegestion c where c.statut = :statut and c.lignegestion.id = :matricule", Lignepaiegestion.class)
				 .setParameter("statut", Statut.ACTIF)
				 .setParameter("matricule", e.getId())
				 .getResultList();
		 return result.get(0);
		 }

	 

	 
	 

}
