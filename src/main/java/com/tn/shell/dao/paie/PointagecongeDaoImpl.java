package com.tn.shell.dao.paie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.paie.*;
@Repository
public class PointagecongeDaoImpl  implements PointagecongeDAO{

	 @PersistenceContext
	 private EntityManager em;
	 
	  
	 @Transactional
	public List<Pointageconge> getAll() {
		 List<Pointageconge> result = em.createQuery("SELECT a FROM Pointageconge a  where a.statut = :statut", Pointageconge.class).setParameter("statut", Statut.ACTIF).getResultList();
		    return result;
	}
	 @Transactional
	 public Pointageconge getMaxPointageconge(){
List<Pointageconge> result = em.createQuery("SELECT a FROM Pointageconge a  where a.statut = :statut ORDER BY a.id DESC LIMIT 1", Pointageconge.class).setParameter("statut", Statut.ACTIF)
				 
				 
				 .getResultList();
		 if (result.size() > 0){
	         return result.get(0);}
	     else{
	         return null;} 
		 }
	 
	 @Transactional
	 public List<Pointageconge> getPointagecongeByMoiannee(Integer annee,Integer mois){
		 List<Pointageconge> result = em.createQuery("SELECT a FROM Pointageconge a  where a.statut = :statut  and a.annee = :annee and a.mois = :mois", Pointageconge.class).setParameter("statut", Statut.ACTIF)
				 
				 .setParameter("annee", annee)
				 .setParameter("mois",mois)
				 .getResultList();
		 if (result.size() > 0){
	         return result;}
	     else{
	         return null;} 
		 }
	 @Transactional
	 public Pointageconge getPointagecongeByEmployee(Employee e ,Integer annee){
	 List<Pointageconge> result = em.createQuery("SELECT a FROM Pointageconge a  where a.statut = :statut and a.employee.matricule = :matricule and a.annee = :annee", Pointageconge.class)
			 .setParameter("statut", Statut.ACTIF)
			 .setParameter("matricule", e.getMatricule())
			 .setParameter("annee", annee)
			 
			 .getResultList();
	 if (result.size() > 0){
         return result.get(0);}
     else{
         return null;} 
	 }
	 @Transactional
	 public List<Pointageconge> getPointagecongeByEmployer(Employee e){
		 List<Pointageconge> result = em.createQuery("SELECT a FROM Pointageconge a  where a.statut = :statut and a.employee.matricule = :matricule  ", Pointageconge.class).setParameter("statut", Statut.ACTIF)
				 .setParameter("matricule", e.getMatricule())
				  
				 .getResultList();
		 if (result.size() > 0){
	         return result;}
	     else{
	         return null;} 
		 
	 }
	 @Transactional
	 public List<Pointageconge> getPointagecongeByAnnee( Integer annee){
		 List<Pointageconge> result = em.createQuery("SELECT a FROM Pointageconge a  where a.statut = :statut  and a.annee = :annee ", Pointageconge.class).setParameter("statut", Statut.ACTIF)
				  
				 .setParameter("annee", annee)
				  
				 .getResultList();
		 if (result.size() > 0){
	         return result;}
	     else{
	         return null;} 
	 }
	 @Transactional
	public void save(Pointageconge c) {
		em.persist(c);
	}
	 @Transactional
	public void update(Pointageconge c) {
		Pointageconge a=em.find(Pointageconge.class, c.getId());
		  a.setNb_jour(c.getNb_jour());
		  a.setEmployee(c.getEmployee());
		  a.setMois(c.getMois());
		  a.setAnnee(c.getAnnee());
		 a.setStatut(c.getStatut());		  
		em.merge(a);
		
	}
	 
	 @Transactional
		public void detele(Pointageconge c) {
			Pointageconge a=em.find(Pointageconge.class, c.getId());
			 a.setStatut(c.getStatut());
			em.merge(a);
			
		}
	  
}
