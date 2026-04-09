package com.tn.shell.dao.paie;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.paie.*;
@Repository
public class RappelDaoImpl  implements RappelDAO{

	 @PersistenceContext
	 private EntityManager em;
	 
	  
	 @Transactional
	public List<Rappel> getAll(Integer annee) {
		 List<Rappel> result1=new ArrayList<Rappel>();
		 List<Rappel> result = em.createQuery("SELECT a FROM Rappel a  where a.statut = :statut and a.annee = :annee ", Rappel.class).
				 setParameter("statut", Statut.ACTIF)
				  .setParameter("annee", annee) //
				 
				 .getResultList();
		 
		 if (result.size() > 0){
	        	System.out.println("objet trouvé\n");
	        	 
	            return result;}
	        else{
	        	System.out.println(" \n\n\n\n l  objet n exsite pas  " +annee+"  "+" \n\n\n\n");
	            return null;}
		     
	}
	 
	 @Transactional
	 public Rappel getMaxPointageconge(){
List<Rappel> result = em.createQuery("SELECT a FROM Rappel a  where a.statut = :statut ORDER BY a.id DESC LIMIT 1", Rappel.class).setParameter("statut", Statut.ACTIF)
				 
				 
				 .getResultList();
		 if (result.size() > 0){
	     	System.out.println("objet trouvé "+"\n\n\n");
	         return result.get(0);}
	     else{
	     	System.out.println("\n\nl  objet Rappel n exsite pas\n\n");
	         return null;} 
		 }
	 @Transactional
	 public Rappel getRappelsByEmployee(Employee e,Integer annee){
		 List<Rappel> result = em.createQuery("SELECT a FROM Rappel a  where a.statut = :statut and a.employee.matricule = :matricule and a.annee = :annee ", Rappel.class)
				 .setParameter("statut", Statut.ACTIF)
				  .setParameter("matricule", e.getMatricule())
				 .setParameter("annee", annee)
				 
				 .getResultList();
		 if (result.size() > 0){
	        	System.out.println("objet trouvé\n");
	            return result.get(0);}
	        else{
	        	System.out.println("l  objet Rappel n exsite pas"+e.getMatricule() +annee );
	            return null;}  
		     
	 }
	 @Transactional
	 public List<Rappel> getRappelsByEmployer(Employee e){
		 List<Rappel> result = em.createQuery("SELECT a FROM Rappel a  where a.statut = :statut and a.employee.matricule = :matricule ", Rappel.class)
				 .setParameter("statut", Statut.ACTIF)
				  .setParameter("matricule", e.getMatricule())
				 
				 .getResultList();
		 if (result.size() > 0){
	        	System.out.println("objet trouvé\n");
	            return result;}
	        else{
	        	System.out.println("l  objet Rappel n exsite pas"+e.getMatricule()   );
	            return null;}  
	 }
	 @Transactional
	public void save(Rappel c) {
		em.persist(c);
	}
	 @Transactional
	public void update(Rappel c) {
		Rappel a=em.find(Rappel.class, c.getId());
		  a=c;
		em.merge(a);
		
	}
	 
	 @Transactional
		public void detele(Rappel c) {
			Rappel a=em.find(Rappel.class, c.getId());
			 a.setStatut(Statut.DEACTIF);
			em.merge(a);
			
		}
	  
}
