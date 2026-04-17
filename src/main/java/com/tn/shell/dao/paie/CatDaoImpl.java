package com.tn.shell.dao.paie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.paie.*;
@Repository
public class CatDaoImpl implements CatDAO {

	 @PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Cat annee) {
		  
	em.persist(annee);
		
	}
	 @Transactional
	public List<Cat> getAll() {
		List<Cat> result = em.createQuery("SELECT a FROM Cat a  ", Cat.class).getResultList();
	    return result;
	}
	public List<Cat> findbyDesignation(TypeCat designation) {
		 List<Cat> AnneeListem=em.createQuery("SELECT u FROM  Cat u where u.cat = :designation",Cat.class).setParameter("designation", designation).getResultList();
	        
	        if (AnneeListem.size() > 0){
	            return AnneeListem;}
	        else{
	            return null;}   
	}
	@Transactional
	public void update(Cat c) {
		Cat a=em.find(Cat.class, c.getId());
	     a.setValeur(c.getValeur());
	     a.setCat(c.getCat());
		 em.merge(a);
		 

		
	}
	public Cat findbyid(Integer id) {
		Cat a=em.find(Cat.class, id);
		return a;
	}
	 

}
