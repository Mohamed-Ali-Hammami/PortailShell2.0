package com.tn.shell.dao.paie;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.paie.*;
 
 
@Repository
public class PretDaoImpl  implements PretDAO{

	 @PersistenceContext
	 private EntityManager em;
	 
	  
	 @Transactional
	public List<Pret> getAll() {
		 List<Pret> result = em.createQuery("SELECT a FROM Pret a  where a.statut = :statut and a.reste_mois != :mois", Pret.class).setParameter("statut", Statut.ACTIF)
				 .setParameter("mois", 0).getResultList();
		    return result;
	}
	 @Transactional
	public void save(Pret c) {
		em.persist(c);
	}
	 @Transactional
	 public Pret getPretencours(Employee e){
		 List<Pret> result = em.createQuery("SELECT a FROM Pret a  where a.statut = :statut and a.employee.matricule = :matricule and a.reste_mois != :mois", Pret.class)
				 .setParameter("statut", Statut.ACTIF)
				  .setParameter("matricule", e.getMatricule())
				   .setParameter("mois", 0)
				 .getResultList();
		 if (result.size() > 0){
			 Date d=new Date();
		     	System.out.println("objet trouvé "+"\n\n\n");
		     	//if(result.get(0).getDate_debut().getMonth()+1+result.get(0).getNbmois()<=d.getMonth()+1)
		         return result.get(0);
		     	}
		     else{
		     	System.out.println("\n\nl  objet pointage n exsite pas\n\n");
		         return null;} 
	 }
	 @Transactional
	public void update(Pret c) {
		Pret a=em.find(Pret.class, c.getId());
		 a.setMontant_pret(c.getMontant_pret());
		 a.setEmployee(c.getEmployee());
		 a.setStatut(c.getStatut());
		  a.setDate_debut(c.getDate_debut());
		  a.setNbmois(c.getNbmois());
		  a.setDeductionParmois(c.getDeductionParmois());
		  a.setReste_deduction(c.getReste_deduction());
		  a.setReste_mois(c.getReste_mois());
		em.merge(a);
		
	}
	 
	 @Transactional
		public void detele(Pret c) {
			Pret a=em.find(Pret.class, c.getId());
			 a.setStatut(c.getStatut());
			em.merge(a);
			
		}
	  
}
