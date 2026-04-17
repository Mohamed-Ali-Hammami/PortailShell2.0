package com.tn.shell.dao.paie;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.paie.*;
@Repository
public class NoteDaoImpl  implements NoteDAO{

	 @PersistenceContext
	 private EntityManager em;
	 
	  
	 @Transactional
	public List<Note> getAll(Integer annee,Integer mois) {
		 List<Note> result1=new ArrayList<Note>();
		 List<Note> result = em.createQuery("SELECT a FROM Note a  where a.statut = :statut and a.annee = :annee and a.mois = :mois", Note.class).
				 setParameter("statut", Statut.ACTIF)
				  .setParameter("annee", annee) //
				 .setParameter("mois", mois) //
				 .getResultList();
		 
		 if (result.size() > 0){
	        	 
	            return result;}
	        else{
	            return null;}
		     
	}
	 
	 @Transactional
	 public Note getMaxPointageconge(){
List<Note> result = em.createQuery("SELECT a FROM Note a  where a.statut = :statut ORDER BY a.id DESC LIMIT 1", Note.class).setParameter("statut", Statut.ACTIF)
				 
				 
				 .getResultList();
		 if (result.size() > 0){
	         return result.get(0);}
	     else{
	         return null;} 
		 }
	 @Transactional
	 public Note getNotesByEmployee(Employee e,Integer annee){
		 List<Note> result = em.createQuery("SELECT a FROM Note a  where a.statut = :statut and a.employee.matricule = :matricule and a.annee = :annee ", Note.class)
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
	 public List<Note> getNotesByEmployer(Employee e){
		 List<Note> result = em.createQuery("SELECT a FROM Note a  where a.statut = :statut and a.employee.matricule = :matricule ", Note.class)
				 .setParameter("statut", Statut.ACTIF)
				  .setParameter("matricule", e.getMatricule())
				 
				 .getResultList();
		 if (result.size() > 0){
	            return result;}
	        else{
	            return null;}  
	 }
	 @Transactional
	public void save(Note c) {
		em.persist(c);
	}
	 @Transactional
	public void update(Note c) {
		Note a=em.find(Note.class, c.getId());
		 a.setAnnee(c.getAnnee());
		 a.setNote(c.getNote());
		 a.setMois(c.getMois());
		 a.setEmployee(c.getEmployee());
		 a.setStatut(c.getStatut());
		 
		em.merge(a);
		
	}
	 
	 @Transactional
		public void detele(Note c) {
			Note a=em.find(Note.class, c.getId());
			 a.setStatut(Statut.DEACTIF);
			em.merge(a);
			
		}
	  
}
