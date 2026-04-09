package com.tn.shell.dao.paie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.paie.*;
@Repository
public class TraceDaoImpl implements TraceDAO {
	@PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Tracepaie trace) {
		em.persist(trace);
		
	}
@Transactional
	public List<Tracepaie> getAll() {
	List<Tracepaie> result = em.createQuery("SELECT a FROM Tracepaie a order by a.id Desc", Tracepaie.class).getResultList();
    return result;
	}

}
