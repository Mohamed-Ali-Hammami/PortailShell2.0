package com.tn.shell.dao.paie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.paie.*;
@Repository
public class PointageDaoImpl  implements PointageDAO{

	 @PersistenceContext
	 private EntityManager em;
	 
	  
	 @Transactional
	public List<Pointage> getAll() {
		 List<Pointage> result = em.createQuery("SELECT a FROM Pointage a  where a.statut = :statut", Pointage.class).setParameter("statut", Statut.ACTIF).getResultList();
		    return result;
	}
	 
	 public double getsumPointageByEmployeeandannee(Employee e, Integer annee) {
		 Query q= em.createQuery("SELECT SUM(a.nb_jour) FROM Pointage a  where a.statut = :statut and a.employee.matricule = :matricule and a.annee = :annee ")
				 .setParameter("statut", Statut.ACTIF)
				 .setParameter("matricule", e.getMatricule())
				 .setParameter("annee", annee)
				 
	             ;
		 try {
				Double result = (Double) q.getSingleResult();

				return result;
			} catch (Exception e2) {
				return 0;
			}
 
	 }
	 @Transactional
	 public Pointage getMaxPointage(){
List<Pointage> result = em.createQuery("SELECT a FROM Pointage a  where a.statut = :statut ORDER BY a.id DESC LIMIT 1", Pointage.class).setParameter("statut", Statut.ACTIF)
				 
				 
				 .getResultList();
		 if (result.size() > 0){
	         return result.get(0);}
	     else{
	         return null;} 
		 }
	 
	 @Transactional
	 public List<Pointage> getPointageByMoiannee(Integer annee,Integer mois){
		 List<Pointage> result = em.createQuery("SELECT a FROM Pointage a  where a.statut = :statut  and a.annee = :annee and a.mois = :mois", Pointage.class).setParameter("statut", Statut.ACTIF)
				 
				 .setParameter("annee", annee)
				 .setParameter("mois",mois)
				 .getResultList();
		 if (result.size() > 0){
	         return result;}
	     else{
	         return null;} 
		 }
	 @Transactional
	 public Pointage getPointageByEmployee(Employee e ,Integer annee, Integer mois){
	 List<Pointage> result = em.createQuery("SELECT a FROM Pointage a  where a.statut = :statut and a.employee.matricule = :matricule and a.annee = :annee and a.mois = :mois", Pointage.class)
			 .setParameter("statut", Statut.ACTIF)
			 .setParameter("matricule", e.getMatricule())
			 .setParameter("annee", annee)
			 .setParameter("mois",mois)
			 .getResultList();
	 if (result.size() > 0){
         return result.get(0);}
     else{
         return null;} 
	 }
	 @Transactional
	 public double getsumPointageByEmployee(Employee e, Integer a,Integer mois1,Integer mois2) {
		
		 Query q= em.createQuery("SELECT SUM(a.nb_jour) FROM Pointage a  where a.statut = :statut and a.employee.matricule = :matricule and a.annee = :annee and a.mois between :mois and :mois2")
				 .setParameter("statut", Statut.ACTIF)
				 .setParameter("matricule", e.getMatricule())
				 .setParameter("annee", a)
				 .setParameter("mois", mois1)
				 .setParameter("mois2", mois2)
	             ;
		 try {
				Double result = (Double) q.getSingleResult();

				return result;
			} catch (Exception e2) {
				return 0;
			}

	 } 
	 @Transactional
	 public List<Pointage> getPointageByEmployer(Employee e){
		 List<Pointage> result = em.createQuery("SELECT a FROM Pointage a  where a.statut = :statut and a.employee.matricule = :matricule  ", Pointage.class).setParameter("statut", Statut.ACTIF)
				 .setParameter("matricule", e.getMatricule())
				  
				 .getResultList();
		 if (result.size() > 0){
	         return result;}
	     else{
	         return null;} 
		 
	 }
	 @Transactional
	 public List<Pointage> getPointageByAnnee( Integer annee){
		 List<Pointage> result = em.createQuery("SELECT a FROM Pointage a  where a.statut = :statut  and a.annee = :annee ", Pointage.class).setParameter("statut", Statut.ACTIF)
				  
				 .setParameter("annee", annee)
				  
				 .getResultList();
		 if (result.size() > 0){
	         return result;}
	     else{
	         return null;} 
	 }
	 @Transactional
	public void save(Pointage c) {
		em.persist(c);
	}
	 @Transactional
	public void update(Pointage c) {
		Pointage a=em.find(Pointage.class, c.getId());
		  a.setNb_jour(c.getNb_jour());
		  a.setEmployee(c.getEmployee());
		  a.setMois(c.getMois());
		  a.setAnnee(c.getAnnee());
		 a.setStatut(c.getStatut());		  
		em.merge(a);
		
	}
	 
	 @Transactional
		public void detele(Pointage c) {
			Pointage a=em.find(Pointage.class, c.getId());
			 a.setStatut(c.getStatut());
			em.merge(a);
			
		}
	  
}
