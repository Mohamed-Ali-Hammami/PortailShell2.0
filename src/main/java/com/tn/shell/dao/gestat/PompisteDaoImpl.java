package com.tn.shell.dao.gestat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.*;

@Repository
public class PompisteDaoImpl implements PompisteDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<Pompiste> getAll() {
		List<Pompiste> result = em.createQuery("SELECT a FROM Pompiste a  where a.statut = :statut", Pompiste.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public void save(Pompiste c) {
		em.persist(c);
	}
	  
	@Transactional
	public Pompiste getPompistebydate(String date,Poste poste) {
		List<Pompiste> result = em.createQuery("SELECT a FROM Pompiste a  where a.statut = :statut and a.dates = :date and a.poste = :poste", Pompiste.class)
				.setParameter("statut", Statut.ACTIF).setParameter("date", date).setParameter("poste", poste).getResultList();
		if (result.size() > 0) {
		return result.get(0);}
		else return null;
	}
	
	 
	@Transactional
	public Integer getmaxcode() {
		List<Pompiste> result = em
				.createQuery("SELECT a FROM Pompiste a  where a.statut = :statut ORDER BY a.id DESC LIMIT 1", Pompiste.class)
				.setParameter("statut", Statut.ACTIF)

				.getResultList();
		if (result.size() > 0) {
			return result.get(0).getId();
		} else {
			return 0;
		}
	}
	@Transactional
	public Pompiste getPompistebyid(Integer id) {
		return em.find(Pompiste.class, id);
	}
	@Transactional
	public void update(Pompiste c) {
		Pompiste Pompiste = em.find(Pompiste.class, c.getId());
		 
		em.merge(Pompiste);

	}

 
	 
}
