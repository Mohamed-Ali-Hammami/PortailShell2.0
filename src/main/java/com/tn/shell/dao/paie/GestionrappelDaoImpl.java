package com.tn.shell.dao.paie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.paie.*;

 
 
 
@Repository
public class GestionrappelDaoImpl  implements GestionrappelDAO{

	 @PersistenceContext
	 private EntityManager em;
	 
	  
	 @Transactional
	public List<Gestionrappel> getAll() {
		 List<Gestionrappel> result = em.createQuery("SELECT a FROM Gestionrappel a  where a.statut = :statut ", Gestionrappel.class).setParameter("statut", Statut.ACTIF).getResultList();
		    return result;
	}
	 @Transactional
	public void save(Gestionrappel c) {
		em.persist(c);
	}
	 @Transactional
	 public Gestionrappel getGestionbyLibelle(Integer annee,String libelle){
		 List<Gestionrappel> FournisseurListem=em.createQuery("SELECT u FROM  Gestionrappel u where u.annee = :annee and u.libelle = :libelle  and u.statut=:statut",Gestionrappel.class).setParameter("annee", annee).setParameter("libelle",libelle).setParameter("statut", Statut.ACTIF).getResultList();
	        
	        if (FournisseurListem.size() > 0){
	            return FournisseurListem.get(0);}
	        else
	        {
	            return null;
	            
	        } 
	 }
	 @Transactional
	public void update(Gestionrappel c) {
		 Gestionrappel a=em.find(Gestionrappel.class, c.getId());
		 a.setLibelle(c.getLibelle());
		 
		em.merge(a);
		
	}
	 
	 @Transactional
		public void detele(Gestionrappel c) {
		 Gestionrappel a=em.find(Gestionrappel.class, c.getId());
			 a.setStatut(c.getStatut());
			em.merge(a);
			
		}
	  
}
