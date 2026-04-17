package com.tn.shell.dao.gestat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.Carteclient;
import com.tn.shell.model.gestat.Clientgestat;
import com.tn.shell.model.gestat.Statut;

@Repository
public class CarteclientDaoImpl implements CarteclientDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void save(Carteclient carte) {
		em.persist(carte);
	}

	@Override
	@Transactional
	public List<Carteclient> getAll() {
		return em.createQuery("SELECT c FROM Carteclient c where c.statut = :statut order by c.id desc", Carteclient.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
	}

	@Override
	@Transactional
	public List<Carteclient> getAllhistory() {
		return em.createQuery("SELECT c FROM Carteclient c order by c.id desc", Carteclient.class).getResultList();
	}

	@Override
	@Transactional
	public List<Carteclient> getByClient(Clientgestat client) {
		return em.createQuery(
				"SELECT c FROM Carteclient c where c.statut = :statut and c.client = :client order by c.id desc",
				Carteclient.class).setParameter("statut", Statut.ACTIF).setParameter("client", client).getResultList();
	}

	@Override
	@Transactional
	public Carteclient Findbycode(Integer id) {
		List<Carteclient> result = em
				.createQuery("SELECT c FROM Carteclient c where c.id = :id and c.statut = :statut", Carteclient.class)
				.setParameter("id", id).setParameter("statut", Statut.ACTIF).getResultList();
		if (result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void update(Carteclient carte) {
		Carteclient c = em.find(Carteclient.class, carte.getId());
		c.setClient(carte.getClient());
		c.setPlafond(carte.getPlafond());
		c.setChauffeur(carte.getChauffeur());
		c.setVehicule(carte.getVehicule());
		c.setDateaffectation(carte.getDateaffectation());
		c.setAffectee(carte.isAffectee());
		c.setStatut(carte.getStatut());
		em.merge(c);
	}

	@Override
	@Transactional
	public void delete(Carteclient carte) {
		Carteclient c = em.find(Carteclient.class, carte.getId());
		c.setStatut(carte.getStatut());
		em.merge(c);
	}
}
