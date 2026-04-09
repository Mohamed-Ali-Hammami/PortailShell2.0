package com.tn.shell.dao.paie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.paie.*;

 
@Repository
public class LigneGestionPaierappelDaoImpl implements LigneGestionpaierappeldao  {
	
	 @PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Lignepaiegestionrappel c) {
		em.persist(c);
		
	}
	 
	 @Transactional	 
	 public List<Lignepaiegestionrappel> getAll(){
		 List<Lignepaiegestionrappel> result = em.createQuery("SELECT c FROM Lignepaiegestionrappel c where c.statut = :statut", Lignepaiegestionrappel.class).setParameter("statut", Statut.ACTIF).getResultList();
		 return result;
	 }
	 @Transactional
	public void update(Lignepaiegestionrappel c) {
		 Lignepaiegestionrappel lc=em.find(Lignepaiegestionrappel.class, c.getId());
		 lc.setValeurdeprimeafficher(c.getValeurdeprimeafficher());
		 lc.setValeurdeprime(c.getValeurdeprime());
		 
		 lc.setLignegestion(c.getLignegestion());
		em.merge(lc);
		
	}
	 @Transactional
	 public List<Lignepaiegestionrappel> getlistbypaie(Rappel e){
		 List<Lignepaiegestionrappel> result = em.createQuery("SELECT c FROM Lignepaiegestionrappel c where c.statut = :statut and c.rappelle.id = :matricule", Lignepaiegestionrappel.class)
				 .setParameter("statut", Statut.ACTIF)
				 .setParameter("matricule", e.getId())
				 .getResultList();
		 return result;
	 }
	 @Transactional
	 public  Lignepaiegestionrappel getlignebygestion(Lignegestion e){
		 List<Lignepaiegestionrappel> result = em.createQuery("SELECT c FROM Lignepaiegestionrappel c where c.statut = :statut and c.lignegestion.id = :matricule", Lignepaiegestionrappel.class)
				 .setParameter("statut", Statut.ACTIF)
				 .setParameter("matricule", e.getId())
				 .getResultList();
		 return result.get(0);
		 }

	 
	 

}
