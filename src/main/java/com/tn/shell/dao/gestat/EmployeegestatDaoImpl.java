package com.tn.shell.dao.gestat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tn.shell.model.gestat.*;
 
@Repository
public class EmployeegestatDaoImpl  implements EmployeegestatDAO{

	 @PersistenceContext
	 private EntityManager em;
	 
	  
	 @Transactional
	public List<Employeegestat> getAll() {
		 List<Employeegestat> result = em.createQuery("SELECT a FROM Employeegestat a  where a.statut = :statut ", Employeegestat.class).setParameter("statut", Statut.ACTIF).getResultList();
		    return result;
	}
	 @Transactional
	 public Employeegestat getEmployeeByNom(String nom){
		 List<Employeegestat> result = em.createQuery("SELECT a FROM Employeegestat a  where a.statut = :statut and a.nom = :nom", Employeegestat.class).setParameter("statut", Statut.ACTIF)
				 .setParameter("nom",nom).getResultList();
		 if (result.size() > 0){
	        	System.out.println("objet trouvé\n");
	            return result.get(0);}
	        else{
	        	System.out.println("l  objet n exsite pas");
	            return null;}  
	 }
	 @Transactional
	 public Employeegestat getEmployeeById(Integer nom) {
		 List<Employeegestat> result = em.createQuery("SELECT a FROM Employeegestat a  where a.statut = :statut and a.matricule = :nom", Employeegestat.class).setParameter("statut", Statut.ACTIF)
				 .setParameter("nom",nom).getResultList();
		 if (result.size() > 0){
	        	System.out.println("objet trouvé\n");
	            return result.get(0);}
	        else{
	        	System.out.println("l  objet n exsite pas");
	            return null;}  
	 }
	 @Transactional
	 public List<Employeegestat> getEmployeeparnature(String  nature ){
		 List<Employeegestat> result = em.createQuery("SELECT a FROM Employeegestat a  where a.statut = :statut and a.nature = :nature", Employeegestat.class).setParameter("statut", Statut.ACTIF).setParameter("nature",nature).getResultList();
		 if (result.size() > 0){
	        	System.out.println("objet trouvé\n");
	            return result;}
	        else{
	        	System.out.println("l  objet n exsite pas");
	            return null;}  
	 }
	 @Transactional
	 public List<Employeegestat> getEmployeeparetat(Statut statut){
		 List<Employeegestat> result = em.createQuery("SELECT a FROM Employeegestat a  where a.statut = :statut", Employeegestat.class).setParameter("statut", statut).getResultList();
		    return result;
	 }
	 @Transactional
	 public List<Employeegestat> getEmployeeparstats(Status  status ){
		 List<Employeegestat> result = em.createQuery("SELECT a FROM Employeegestat a  where a.statut = :statut and a.status = :status", Employeegestat.class).setParameter("statut", Statut.ACTIF).setParameter("status", status).getResultList();
		    return result;
	 }
	 @Transactional
	public void save(Employeegestat c) {
		em.persist(c);
	}
	 @Transactional
	public void update(Employeegestat c) {
		Employeegestat a=em.find(Employeegestat.class, c.getMatricule());
		 
		 a.setCin(c.getCin());
		 
		 a.setNom(c.getNom());
		   
		 a.setSalaire_journalier(c.getSalaire_journalier());
		 a.setStatut(c.getStatut());	
		 
		em.merge(a);
		
	}
	 
	 @Transactional
		public void detele(Employeegestat c) {
			Employeegestat a=em.find(Employeegestat.class, c.getMatricule());
			 a.setStatut(c.getStatut());
			em.merge(a);
			
		}
	  
}
