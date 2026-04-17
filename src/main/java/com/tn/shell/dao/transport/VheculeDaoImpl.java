package com.tn.shell.dao.transport;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.transport.*;

 

@Repository
public class VheculeDaoImpl implements VheculeDao {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Vhecule Vhecule) {
		em.persist(Vhecule);

	}

	@Transactional
	public List<String> getAllnom() {
		List<String> l = new ArrayList<String>();
		List<Vhecule> result = em
				.createQuery("SELECT  c FROM Vhecule c where c.statut = :statut ", Vhecule.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		if (result != null)
			for (Vhecule c : result)
				l.add(c.getMatricule());
		return l;
	}

	@Transactional
	public List<Vhecule> getAll() {
		List<Vhecule> result = em.createQuery("SELECT c FROM Vhecule c  where c.statut = :statut", Vhecule.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public Vhecule Findbynom(String nom) {
		List<Vhecule> VheculeListem = em
				.createQuery("SELECT c FROM  Vhecule c where c.matricule = :nom and c.statut = :statut", Vhecule.class)
				.setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();

		if (VheculeListem.size() > 0) {
			return VheculeListem.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	public Vhecule Findbycode(Integer nom) {
		List<Vhecule> VheculeListem = em
				.createQuery("SELECT c FROM  Vhecule c where c.id = :nom and c.statut = :statut", Vhecule.class)
				.setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();

		if (VheculeListem.size() > 0) {
			return VheculeListem.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	public Vhecule Findbymf(String nom) {
		List<Vhecule> VheculeListem = em
				.createQuery("SELECT c FROM  Vhecule c where c.numserie = :nom and c.statut = :statut", Vhecule.class)
				.setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();

		if (VheculeListem.size() > 0) {
			return VheculeListem.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	public void update(Vhecule vhecule) {
		Vhecule c = em.find(Vhecule.class, vhecule.getId());
		c.setMatricule(vhecule.getMatricule());

		c.setParamettrevehicule(vhecule.getParamettrevehicule());
		em.merge(c);
	}

	@Transactional
	public void delete(Vhecule vhecule) {
		Vhecule c = em.find(Vhecule.class, vhecule.getId());
		c.setStatut(vhecule.getStatut());
		em.merge(c);

	}

}
