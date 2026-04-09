package com.tn.shell.dao.transport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.transport.*;

@Repository
public class FacturepassagerDaoImpl implements FacturepassagerDAO {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Facturepassager facturepassager) {
		em.persist(facturepassager);
	}

	@Transactional
	public List<Facturepassager> getAll() {
		List<Facturepassager> result = em
				.createQuery("SELECT a FROM Facturepassager a where a.statut = :statut order by a.numero Desc",
						Facturepassager.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public List<Facturepassager> getfacturebetwenndate(Date d1, Date d2) {
		List<Facturepassager> result = em
				.createQuery(
						"SELECT a FROM Facturepassager a where a.statut = :statut and a.date BETWEEN :d1 and :d2 ORDER BY a.numero DESC ",
						Facturepassager.class)
				.setParameter("statut", Statut.ACTIF).setParameter("d1", d1).setParameter("d2", d2)
				.getResultList();
		if (result.size() > 0) {
			System.out.println("objet trouve \n\n\n");
		} else {
			System.out.println("\n\nl objet facture n exsite pas\n\n");
		}
		return result;
	}

	@Transactional
	public Facturepassager getMaxfacture() {
		List<Facturepassager> result = em.createQuery(
				"SELECT a FROM Facturepassager a where a.statut = :statut and a.numero=(select MAX(b.numero) from Facturepassager b)",
				Facturepassager.class).setParameter("statut", Statut.ACTIF).getResultList();
		if (result.size() > 0) {
			System.out.println("objet trouve \n\n\n");
			return result.get(0);
		} else {
			System.out.println("\n\nl objet Pointageconge n exsite pas\n\n");
			return null;
		}
	}

	@Transactional
	public List<Facturepassager> getAllPasager() {
		List<Facturepassager> result = em
				.createQuery("SELECT a FROM Facturepassager a where a.statut = :statut ORDER BY a.numero DESC",
						Facturepassager.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		List<Facturepassager> filtered = new ArrayList<Facturepassager>();
		for (Facturepassager facture : result) {
			if (facture.getVhecule() != null) {
				filtered.add(facture);
			}
		}
		if (filtered.isEmpty()) {
			System.out.println("\n\nl objet Facturepassager n exsite pas\n\n");
		}
		return filtered;
	}

	@Transactional
	public List<Facturepassager> getAllTransport() {
		List<Facturepassager> result = em
				.createQuery(
						"SELECT a FROM Facturepassager a where a.statut = :statut and a.vhecule.id != :id ORDER BY a.numero DESC",
						Facturepassager.class)
				.setParameter("statut", Statut.ACTIF).setParameter("id", 0).getResultList();
		if (result.size() > 0) {
			System.out.println("objet trouve \n\n\n");
		} else {
			System.out.println("\n\nl objet Facturepassager n exsite pas\n\n");
		}
		return result;
	}

	@Transactional
	public void update(Facturepassager facturepassager) {
		Facturepassager c = em.find(Facturepassager.class, facturepassager.getNumero());
		c.setDate(facturepassager.getDate());
		c.setStatut(facturepassager.getStatut());
		c.setStatus(facturepassager.getStatus());
		c.setNumerocheck(facturepassager.getNumerocheck());
		c.setBanque(facturepassager.getBanque());
		c.setTotalht(facturepassager.getTotalht());
		c.setSommes(facturepassager.getSommes());
		c.setTotaltva(facturepassager.getTotaltva());
		c.setTotalttc(facturepassager.getTotalttc());
		em.merge(c);
	}

	@Transactional
	public void delete(Facturepassager facturepassager) {
		Facturepassager c = em.find(Facturepassager.class, facturepassager.getNumero());
		c.setTotalttc(0);
		c.setStatut(Statut.DEACTIF);
		em.remove(c);
	}

	public List<Facturepassager> getbydate(Date d1, Date d2) {
		List<Facturepassager> result = em
				.createQuery(
						"SELECT u FROM Facturepassager u where u.date between :d1 and :d2 and u.statut = :statut",
						Facturepassager.class)
				.setParameter("d1", d1).setParameter("d2", d2).setParameter("statut", Statut.ACTIF)
				.getResultList();
		if (result.size() > 0) {
			System.out.println("\n\n\n\nFacturepassager bydate trouve\n\n\n\n");
		} else {
			System.out.println("l objet n exsite pas");
		}
		return result;
	}

	public Facturepassager getfacturebycode(String code) {
		List<Facturepassager> result = em
				.createQuery("SELECT u FROM Facturepassager u where u.codes = :code and u.statut = :statut",
						Facturepassager.class)
				.setParameter("code", code).setParameter("statut", Statut.ACTIF).getResultList();

		if (result.size() > 0) {
			System.out.println("\n\n\n\nFacturepassager bydate trouve\n\n\n\n");
			return result.get(0);
		} else {
			System.out.println("l objet n exsite pas");
			return null;
		}
	}

	public Facturepassager getBLbycodes(String code) {
		List<Facturepassager> result = em
				.createQuery("SELECT u FROM Facturepassager u where u.codes = :code and u.statut = :statut",
						Facturepassager.class)
				.setParameter("code", code).setParameter("statut", Statut.ACTIF).getResultList();

		if (result.size() > 0) {
			System.out.println("objet trouve\n");
			return result.get(0);
		} else {
			System.out.println("l objet n exsite pas");
			return null;
		}
	}

	public Integer getmaxcode() {
		Query q = em.createQuery("SELECT MAX(b.code) FROM Facturepassager b where b.statut = :statut")
				.setParameter("statut", Statut.ACTIF);
		Integer result = (Integer) q.getSingleResult();
		return result;
	}

	public Facturepassager getBLbycode(Integer code) {
		List<Facturepassager> result = em
				.createQuery("SELECT u FROM Facturepassager u where u.code = :code and u.statut = :statut",
						Facturepassager.class)
				.setParameter("code", code).setParameter("statut", Statut.ACTIF).getResultList();

		if (result.size() > 0) {
			System.out.println("objet trouve\n");
			return result.get(0);
		} else {
			System.out.println("l objet n exsite pas");
			return null;
		}
	}
}
