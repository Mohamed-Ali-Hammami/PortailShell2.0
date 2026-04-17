package com.tn.shell.dao.paie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.paie.*;
 
 
@Repository
public class AnneeDaoImpl implements AnneeDAO {

	 @PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Annee annee) {
		  
	em.persist(annee);
		
	}
	 @Transactional
	public List<Annee> getAll() {
		List<Annee> result = em.createQuery("SELECT a FROM Annee a  ", Annee.class).getResultList();
	    return result;
	}
	public Annee findbyDesignation(String designation) {
		 List<Annee> AnneeListem=em.createQuery("SELECT u FROM  Annee u where u.annee = :designation",Annee.class).setParameter("designation", designation).getResultList();
	        
	        if (AnneeListem.size() > 0){
	            return AnneeListem.get(0);}
	        else{
	            return null;}   
	}
	@Transactional
	public void update(Annee annee) {
		Annee a=em.find(Annee.class, annee.getId());
	     a.setAnnee(annee.getAnnee());
		 
		 em.merge(a);
		 
	}
	public Annee findbyid(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	 
	 

}
