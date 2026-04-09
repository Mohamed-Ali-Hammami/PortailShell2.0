package com.tn.shell.dao.transport;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.transport.Tracetransport;
 
@Repository
public class TracetransportDaoImpl implements TracetransportDAO {
	@PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Tracetransport trace) {
		em.persist(trace);
		
	}
@Transactional
	public List<Tracetransport> getAll() {
	List<Tracetransport> result = em.createQuery("SELECT a FROM Tracetransport a order by a.id Desc", Tracetransport.class).getResultList();
    return result;
	}

}
