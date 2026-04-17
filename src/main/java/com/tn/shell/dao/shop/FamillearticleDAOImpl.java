package com.tn.shell.dao.shop;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.shop.*;

 
 
@Repository
public class FamillearticleDAOImpl implements FamillearticleDAO {
	 @PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Famillearticle typearticle) {
		em.persist(typearticle);
		
	}
	 @Transactional
	public List<Famillearticle> getAll() {
		 List<Famillearticle> result = em.createQuery("SELECT a FROM Famillearticle a  where a.statut = :statut", Famillearticle.class).setParameter("statut", Statut.ACTIF).getResultList();
		    return result;
	}
	 
	 @Transactional
		public List<Famillearticle> getAll2() {
			 List<Famillearticle> result = em.createQuery("SELECT a FROM Famillearticle a  where a.statut = :statut and a.code !=10", Famillearticle.class).setParameter("statut", Statut.ACTIF).getResultList();
			    return result;
		}
	 @Transactional
	 public  List<String> getAllnom(){
		 List<String> l=new ArrayList<String>();
		 List<Famillearticle> result = em.createQuery("SELECT c  FROM Famillearticle c where c.statut = :statut ", Famillearticle.class)
					.setParameter("statut", Statut.ACTIF) 
					.getResultList();
		if(result!=null) for(Famillearticle c:result) l.add(c.getNom());
		    return l  ;
	 }
	 @Transactional
	public Famillearticle findbyDesignation(String designation) {
		   List<Famillearticle> FamillearticleListem=em.createQuery("SELECT u FROM  Famillearticle u where u.nom = :designation and u.statut = :statut",Famillearticle.class).setParameter("designation", designation).setParameter("statut", Statut.ACTIF).getResultList();
	        
	        if (FamillearticleListem.size() > 0){
	            return FamillearticleListem.get(0);}
	        else{
	            return null;}   
	}
	 @Transactional
	public void update(Famillearticle typearticle) {
		 Famillearticle t=em.find(Famillearticle.class, typearticle.getCode());
		t.setNom(typearticle.getNom());
		em.merge(t);
		
	}
	 @Transactional
	public void delete(Famillearticle typearticle) {
		 Famillearticle t=em.find(Famillearticle.class, typearticle.getCode());
		 t.setStatut(Statut.DEACTIF);
		em.merge(t);
		
	}

}
