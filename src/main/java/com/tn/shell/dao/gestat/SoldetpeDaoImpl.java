package com.tn.shell.dao.gestat;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.Soldetpe;

@Repository
public class SoldetpeDaoImpl implements SoldetpeDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<Soldetpe> getAll() {
		return em.createQuery("SELECT a FROM Soldetpe a", Soldetpe.class).getResultList();
	}
	
	@Transactional
	public List<Soldetpe> getSoldetpebydates(String date) {
		List<Soldetpe> result = new ArrayList<Soldetpe>();
		result = em.createQuery("SELECT a FROM Soldetpe a", Soldetpe.class).getResultList();
		if (result.size() > 0) {
			return result;
		} else {
			return result;
		}
	}

	@Transactional
	public void save(Soldetpe c) {
		em.persist(c);
	}
	
	@Transactional
	public Soldetpe getmaxcode() {
		List<Soldetpe> result = em.createQuery("SELECT a FROM Soldetpe a", Soldetpe.class).getResultList();
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	public Soldetpe getSoldetpebyid(Integer id) {
		return em.find(Soldetpe.class, id);
	}

	@Transactional
	public void update(Soldetpe c) {
		Soldetpe a = em.find(Soldetpe.class, c.getId());
		a = c;
		em.merge(a);
	}
}
