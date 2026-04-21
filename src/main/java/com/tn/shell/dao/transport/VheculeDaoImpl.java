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
	private static final String ACTIVE_STATUS_SQL = "(f.statut is null or trim(f.statut) = '' or lower(trim(f.statut)) = 'actif')";

	@Transactional
	public void save(Vhecule Vhecule) {
		em.persist(Vhecule);

	}

	@Transactional
	public List<String> getAllnom() {
		List<String> l = new ArrayList<String>();
		List<Vhecule> result = getAll();
		if (result != null)
			for (Vhecule c : result)
				l.add(c.getMatricule());
		return l;
	}

	@Transactional
	public List<Vhecule> getAll() {
		try {
			List<Vhecule> result = em.createQuery("SELECT c FROM Vhecule c  where c.statut = :statut", Vhecule.class)
					.setParameter("statut", Statut.ACTIF).getResultList();
			if (result != null && !result.isEmpty()) {
				return result;
			}
		} catch (RuntimeException ex) {
		}
		List<Object[]> rows = em.createNativeQuery(
				"SELECT id,matricule,numserie,statut FROM vhecule WHERE " + ACTIVE_STATUS_SQL
						+ " ORDER BY CAST(id AS UNSIGNED) DESC")
				.getResultList();
		List<Vhecule> vhecules = new ArrayList<Vhecule>();
		for (Object[] row : rows) {
			Vhecule vhecule = new Vhecule();
			vhecule.setId(TransportTsvMapper.asInteger(row[0]));
			vhecule.setMatricule(TransportTsvMapper.asString(row[1]));
			vhecule.setNumserie(TransportTsvMapper.asString(row[2]));
			vhecule.setStatut(TransportTsvMapper.asStatut(row[3]));
			vhecules.add(vhecule);
		}
		return vhecules;
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
