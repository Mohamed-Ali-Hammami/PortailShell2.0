package com.tn.shell.dao.paie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.paie.*;

 
 
 
@Repository
public class GestionDaoImpl  implements GestionDAO{

	 @PersistenceContext
	 private EntityManager em;
	 
	  
	 @Transactional
	public List<Gestion> getAll() {
		 List<Gestion> result = em.createQuery("SELECT a FROM Gestion a  where a.statut = :statut ", Gestion.class).setParameter("statut", Statut.ACTIF).getResultList();
		    return result;
	}
	 @Transactional
	public void save(Gestion c) {
		em.persist(c);
	}
	 @Transactional
	 public Gestion getGestionbyLibelle(String libelle){
		 List<Gestion> FournisseurListem=em.createQuery("SELECT u FROM  Gestion u where u.libelle = :libelle and u.statut=:statut",Gestion.class).setParameter("libelle", libelle).setParameter("statut", Statut.ACTIF).getResultList();
	        
	        if (FournisseurListem.size() > 0){
	            return FournisseurListem.get(0);}
	        else
	        {
	            return null;
	            
	        } 
	 }
	 @Transactional
	public void update(Gestion c) {
		Gestion a=em.find(Gestion.class, c.getId());
		 a.setLibelle(c.getLibelle());
		 a.setSigne(c.getSigne());
		em.merge(a);
		
	}
	 
	 @Transactional
		public void detele(Gestion c) {
			Gestion a=em.find(Gestion.class, c.getId());
			 a.setStatut(c.getStatut());
			em.merge(a);
			
		}
	  
}
