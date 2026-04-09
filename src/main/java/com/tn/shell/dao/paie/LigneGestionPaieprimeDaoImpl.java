package com.tn.shell.dao.paie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.paie.*;

 
@Repository
public class LigneGestionPaieprimeDaoImpl  implements LigneGestionpaieprimedao {
	
	 @PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Lignepaiegestionprime c) {
		em.persist(c);
		
	}
	 
	 @Transactional	 
	 public List<Lignepaiegestionprime> getAll(){
		 List<Lignepaiegestionprime> result = em.createQuery("SELECT c FROM Lignepaiegestionprime c where c.statut = :statut", Lignepaiegestionprime.class).setParameter("statut", Statut.ACTIF).getResultList();
		 return result;
	 }
	 @Transactional
	public void update(Lignepaiegestionprime c) {
		 Lignepaiegestionprime lc=em.find(Lignepaiegestionprime.class, c.getId());
		 
		em.merge(lc);
		
	}
	 @Transactional
	 public List<Lignepaiegestionprime> getlistbypaie(Paieprime e){
		 List<Lignepaiegestionprime> result = em.createQuery("SELECT c FROM Lignepaiegestionprime c where c.statut = :statut and c.paieprime.id = :matricule", Lignepaiegestionprime.class)
				 .setParameter("statut", Statut.ACTIF)
				 .setParameter("matricule", e.getId())
				 .getResultList();
		 return result;
	 }
	 @Transactional
	 public  Lignepaiegestionprime getlignebygestion(Lignegestion e){
		 List<Lignepaiegestionprime> result = em.createQuery("SELECT c FROM Lignepaiegestionprime c where c.statut = :statut and c.lignegestion.id = :matricule", Lignepaiegestionprime.class)
				 .setParameter("statut", Statut.ACTIF)
				 .setParameter("matricule", e.getId())
				 .getResultList();
		 return result.get(0);
		 }

	 

	 
	 

}
