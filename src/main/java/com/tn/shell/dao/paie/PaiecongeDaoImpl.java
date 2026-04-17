package com.tn.shell.dao.paie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.paie.*;
 
@Repository
public class PaiecongeDaoImpl  implements PaiecongeDAO{

	 @PersistenceContext
	 private EntityManager em;
	 
	  
	 @Transactional
	public List<Paieconge> getAll() {
		 List<Paieconge> result = em.createQuery("SELECT a FROM Paieconge a  where a.statut = :statut", Paieconge.class).setParameter("statut", Statut.ACTIF).getResultList();
		    return result;
	}
	 @Transactional
	 public List<Paieconge> getPaieByEmployee(Employee e){
		 List<Paieconge> result = em.createQuery("SELECT a FROM Paieconge a  where a.statut = :statut and a.employee.matricule = :matricule ", Paieconge.class)
				 .setParameter("statut", Statut.ACTIF)
				 
				 .setParameter("matricule", e.getMatricule())
				 
				 .getResultList();
		 if (result.size() > 0){
		        return result;}
		    else{
		        return null;} 
	 }
	  
		 @Transactional
	 public List<Paieconge> getPaieByAnnee(Integer annee){
		 List<Paieconge> result = em.createQuery("SELECT a FROM Paieconge a  where a.statut = :statut and a.annee = :annee and a.employee.status = :status", Paieconge.class)
				 .setParameter("statut", Statut.ACTIF)
				 .setParameter("annee", annee)
				 .setParameter("status", Status.Declare)
				 .getResultList();
		 if (result.size() > 0){
	            return result;}
	        else{
	            return null;} 
	 }
		 @Transactional
	public List<Paieconge> getPaieByAnneeAndMois(Integer annee,Integer mois){
		 List<Paieconge> result = em.createQuery("SELECT a FROM Paieconge a  where a.statut = :statut and a.annee = :annee and a.mois = :mois and a.employee.status = :status", Paieconge.class)
		 .setParameter("statut", Statut.ACTIF)
		 .setParameter("annee", annee)
		 .setParameter("mois", mois)
		 .setParameter("status", Status.Declare)
		 .getResultList();
 if (result.size() > 0){
        return result;}
    else{
        return null;} 
}
		 
		 
		 @Transactional
			public double getPaieByAnneeAndMois2(Integer annee,Integer mois){
			 Query q= em.createQuery("SELECT SUM(a.formulaire_Paie.salaire_net) FROM Paieconge a  where a.statut = :statut and a.annee = :annee and a.mois = :mois and a.employee.status = :status")
				 .setParameter("statut", Statut.ACTIF)
				 .setParameter("annee", annee)
				 .setParameter("mois", mois)
				 .setParameter("status", Status.Declare);
				  
			 try {
					Double result = (Double) q.getSingleResult();

					return result;
				} catch (Exception e) {
					return 0;
				}
		}
		 
		 @Transactional
		 public List<Paieconge> getPaieNondeclareByAnneeAndMois(Integer annee,Integer mois){
		 List<Paieconge> result = em.createQuery("SELECT a FROM Paieconge a  where a.statut = :statut and a.annee = :annee and a.mois = :mois and a.employee.status = :status", Paieconge.class)
		 .setParameter("statut", Statut.ACTIF)
		 .setParameter("annee", annee)
		 .setParameter("mois", mois)
		 .setParameter("status", Status.NonDeclaree)
		 .getResultList();
 if (result.size() > 0){
        return result;}
    else{
        return null;} 
}
		 @Transactional	 
 public List<Paieconge> getPaieByAnneeAndMoisEmployee(Employee e,Integer annee,Integer mois){
			 List<Paieconge> result = em.createQuery("SELECT a FROM Paieconge a  where a.statut = :statut and a.employee.matricule = :matricule and a.annee = :annee and a.mois = :mois ", Paieconge.class)
					 .setParameter("statut", Statut.ACTIF)
					 
					 .setParameter("matricule", e.getMatricule())
					 .setParameter("annee", annee)
					 .setParameter("mois", mois)
					 .getResultList();
			 if (result.size() > 0){
			        return result;}
			    else{
			        return null;} 
		 }
		 @Transactional	
		 public List<Paieconge> getPaieByAnneeAndEmployee(Employee e,Integer annee){
			 List<Paieconge> result = em.createQuery("SELECT a FROM Paieconge a  where a.statut = :statut and a.employee.matricule = :matricule and a.annee = :annee ", Paieconge.class)
					 .setParameter("statut", Statut.ACTIF)
					 
					 .setParameter("matricule", e.getMatricule())
					 .setParameter("annee", annee)
					 
					 .getResultList();
			 if (result.size() > 0){
			        return result;}
			    else{
			        return null;} 
		 }
	 @Transactional
	public void save(Paieconge c) {
		em.persist(c);
	}
	 @Transactional
	public void update(Paieconge c) {
		Paie a=em.find(Paie.class, c.getId());
		  a.setAnnee(c.getAnnee());
		  a.setMois(c.getMois());
		  a.setEmployee(c.getEmployee());
		 a.setStatut(c.getStatut());		  
		em.merge(a);
		
	}
	 
	 @Transactional
		public void detele(Paieconge c) {
			Paie a=em.find(Paie.class, c.getId());
			 a.setStatut(c.getStatut());
			em.merge(a);
			
		}
	  
}
