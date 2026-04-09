package com.tn.shell.dao.shop;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

 
import com.tn.shell.model.shop.Traceshop;
@Repository
public class TraceshopDaoImpl implements TraceshoDAO {
	@PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Traceshop trace) {
		em.persist(trace);
		
	}
@Transactional
	public List<Traceshop> getAll() {
	List<Traceshop> result = em.createQuery("SELECT a FROM Traceshop a order by a.id Desc", Traceshop.class).getResultList();
    return result;
	}

}
