package com.tn.shell.dao.banque;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.banque.Banque;
import com.tn.shell.model.gestat.*;

@Repository
public class BanqueDaoImpl implements BanqueDao {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Banque banque) {
		em.persist(banque);

	}

	@Transactional
	public List<Banque> getAll() {
		List<Banque> result = em.createQuery("SELECT c FROM Banque c where c.statut = :statut ", Banque.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public Banque Findbynom(String nom) {
		List<Banque> BanqueListem = em
				.createQuery("SELECT c FROM  Banque c where c.nom = :nom and c.statut = :statut", Banque.class)
				.setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();

		if (BanqueListem.size() > 0) {
			return BanqueListem.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	public Banque Findbycode(Integer nom) {
		List<Banque> BanqueListem = em
				.createQuery("SELECT c FROM  Banque c where c.id = :nom and c.statut = :statut", Banque.class)
				.setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();

		if (BanqueListem.size() > 0) {
			return BanqueListem.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	public void update(Banque d) {
		Banque c = em.find(Banque.class, d.getId());
		c = d;

		em.merge(c);
	}

	@Transactional
	public void delete(Banque Banque) {
		Banque c = em.find(Banque.class, Banque.getId());

		c.setStatut(Statut.DEACTIF);
		em.merge(c);

	}

}
