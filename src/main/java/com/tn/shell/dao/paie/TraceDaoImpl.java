package com.tn.shell.dao.paie;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.paie.*;
@Repository
public class TraceDaoImpl implements TraceDAO {
	private static final Logger LOGGER = Logger.getLogger(TraceDaoImpl.class.getName());
	@PersistenceContext
	 private EntityManager em;
	 
	 @Transactional(propagation = Propagation.REQUIRES_NEW)
	public void save(Tracepaie trace) {
		try {
			em.persist(trace);
		} catch (RuntimeException ex) {
			// Do not fail functional screens when audit trace persistence is broken.
			LOGGER.log(Level.WARNING, "Tracepaie persistence failed, continuing without audit insert", ex);
		}
		
	}
@Transactional
	public List<Tracepaie> getAll() {
	List<Tracepaie> result = em.createQuery("SELECT a FROM Tracepaie a order by a.id Desc", Tracepaie.class).getResultList();
    return result;
	}

}
