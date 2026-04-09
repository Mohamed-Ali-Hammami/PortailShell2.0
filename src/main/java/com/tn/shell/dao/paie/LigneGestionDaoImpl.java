package com.tn.shell.dao.paie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.paie.*;

 
@Repository
public class LigneGestionDaoImpl implements LigneGestiondao {
	
	 @PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Lignegestion c) {
		em.persist(c);
		
	}
	 
	 @Transactional	 
	 public List<Lignegestion> getAll(){
		 List<Lignegestion> result = em.createQuery("SELECT c FROM Lignegestion c where c.statut = :statut", Lignegestion.class).setParameter("statut", Statut.ACTIF).getResultList();
		 return result;
	 }
	 @Transactional
	public void update(Lignegestion c) {
		 Lignegestion lc=em.find(Lignegestion.class, c.getId());
		 
		 lc.setGestion(c.getGestion());
		 lc.setEmployee(c.getEmployee());
		em.merge(lc);
		
	}
	 @Transactional
	 public List<Lignegestion> getlistbyemplyee(Employee e){
		 List<Lignegestion> result = em.createQuery("SELECT c FROM Lignegestion c where c.statut = :statut and c.employee.matricule = :matricule", Lignegestion.class)
				 .setParameter("statut", Statut.ACTIF)
				 .setParameter("matricule", e.getMatricule())
				 .getResultList();
		 return result;
	 }
	 @Transactional
	 public  Lignegestion getlignebygestion(Gestion e){
		 List<Lignegestion> result = em.createQuery("SELECT c FROM Lignegestion c where c.statut = :statut and c.gestion.id = :matricule", Lignegestion.class)
				 .setParameter("statut", Statut.ACTIF)
				 .setParameter("matricule", e.getId())
				 .getResultList();
		 return result.get(0);
		 }
	 @Transactional
		public void delete(Lignegestion c) {
		 Lignegestion lc=em.find(Lignegestion.class, c.getId());
			lc.setStatut(Statut.DEACTIF);			 
			em.merge(lc);
			
		}
	 

}
