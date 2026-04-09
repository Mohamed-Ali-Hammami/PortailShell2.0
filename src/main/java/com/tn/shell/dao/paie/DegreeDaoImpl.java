package com.tn.shell.dao.paie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.paie.*;
@Repository
public class DegreeDaoImpl implements DegreeDAO {

	 @PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Degree annee) {
		  
	em.persist(annee);
		
	}
	 @Transactional
	public List<Degree> getAll() {
		List<Degree> result = em.createQuery("SELECT a FROM Degree a  ", Degree.class).getResultList();
	    return result;
	}
	public Degree findbyDesignation(Integer designation) {
		 List<Degree> AnneeListem=em.createQuery("SELECT u FROM  Degree u where u.valeur = :designation",Degree.class).setParameter("designation", designation).getResultList();
	        
	        if (AnneeListem.size() > 0){
	        	System.out.println("objet trouvé\n");
	            return AnneeListem.get(0);}
	        else{
	        	System.out.println("l  objet n exsite pas");
	            return null;}   
	}
	@Transactional
	public void update(Degree c) {
		Degree a=em.find(Degree.class, c.getId());
	     a.setValeur(c.getValeur());
	     a.setPeriode(c.getPeriode());
		 em.merge(a);
		 

		
	}
	public Degree findbyid(Integer id) {
		Degree a=em.find(Degree.class, id);
		return a;
	}
	 

}
