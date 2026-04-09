package com.tn.shell.dao.gestat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.Tracegestat;
import com.tn.shell.model.shop.Traceshop;
@Repository
public class TracegestatDaoImpl implements TracegestatDAO {
	@PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Tracegestat trace) {
		em.persist(trace);
		
	}
@Transactional
	public List<Tracegestat> getAll() {
	List<Tracegestat> result = em.createQuery("SELECT a FROM Tracegestat a order by a.id Desc", Tracegestat.class).getResultList();
    return result;
	}

}
