package com.tn.shell.dao.gestat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.*;

@Repository
public class AvoirDaoImpl implements AvoirDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<Avoir> getAll() {
		List<Avoir> result = em.createQuery("SELECT a FROM Avoir a  where a.statut = :statut", Avoir.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}
	public List<Avoir> getBETWENNDATES(Date d1,Date d2){
		List<Avoir> result =new ArrayList<Avoir>();
		   result = em
				.createQuery("SELECT a FROM Avoir a  where a.statut = :statut  and a.date BETWEEN :d1 and :d2 ORDER BY a.id DESC", Avoir.class)
				.setParameter("statut", Statut.ACTIF)
				.setParameter("d1",d1)
				.setParameter("d2",d2)
				.getResultList();
		if (result.size() > 0) {
			return result;
		} else {
			return result;
		}
	}
	@Transactional
	 public List<Avoir> getavoirbydates(String date){
		List<Avoir> result =new ArrayList<Avoir>();
		   result = em
				.createQuery("SELECT a FROM Avoir a  where a.statut = :statut  and a.dates = :dates ORDER BY a.id DESC", Avoir.class)
				.setParameter("statut", Statut.ACTIF)
				.setParameter("dates",date)
				.getResultList();
		if (result.size() > 0) {
			return result;
		} else {
			return result;
		}
	}
	@Transactional
	public void save(Avoir c) {
		em.persist(c);
	}
	 
	
	
	 	@Transactional
	public Integer getmaxcode() {
		List<Avoir> result = em
				.createQuery("SELECT a FROM Avoir a  where a.statut = :statut ORDER BY a.id DESC LIMIT 1", Avoir.class)
				.setParameter("statut", Statut.ACTIF)

				.getResultList();
		if (result.size() > 0) {
			return result.get(0).getId();
		} else {
			return 0;
		}
	}
	@Transactional
	public Avoir getAvoirbyid(Integer id) {
		List<Avoir> result = em
				.createQuery("SELECT a FROM Avoir a  where a.statut = :statut and a.id = :id", Avoir.class)
				.setParameter("statut", Statut.ACTIF)
				.setParameter("id", id)
				.getResultList();
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}
	@Transactional
	public void update(Avoir c) {
		Avoir a = em.find(Avoir.class, c.getId());
	 a.setMontant(c.getMontant());
	 a.setNumero(c.getNumero());
		em.merge(a);

	}

	 
}
