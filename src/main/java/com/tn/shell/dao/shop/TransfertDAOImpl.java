package com.tn.shell.dao.shop;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.shop.Transfert;

 
 
@Repository
public class TransfertDAOImpl  implements TransfertDAO{
	@PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public List<Transfert> getAll() {
		 List<Transfert> result = em.createQuery("SELECT p FROM Transfert p  ", Transfert.class).getResultList();
		    return result;
	}
	  
	 @Transactional
	public void update(Transfert t) {
		Transfert v=em.find(Transfert.class, t.getId());
		 
		em.merge(v);
	}
	  
	 @Transactional
	public void save(Transfert t) {
		 em.persist(t);
		
	}
	 

}
