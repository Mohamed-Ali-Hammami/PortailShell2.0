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
	private static final String ACTIVE_STATUS_SQL = "(f.statut is null or trim(f.statut) = '' or lower(trim(f.statut)) = 'actif')";

	@Transactional
	public void save(Facturepassager facturepassager) {
		em.persist(facturepassager);
	}

	@Transactional
	public List<Facturepassager> getAll() {
		try {
			List<Facturepassager> result = em
					.createQuery("SELECT a FROM Facturepassager a where a.statut = :statut order by a.numero Desc",
							Facturepassager.class)
					.setParameter("statut", Statut.ACTIF).getResultList();
			if (result != null && !result.isEmpty()) {
				return result;
			}
		} catch (RuntimeException ex) {
		}
		List<Object[]> rows = em.createNativeQuery(
				"SELECT f.numero,f.code,f.codes,f.date,f.totalht,f.totalttc,f.totaltva,f.timbres,f.status,f.statut,"
						+ "f.clientid,f.vheculeid,f.chauffeurid,c.nom,v.matricule,h.nompenom "
						+ "FROM facturepassager f "
						+ "LEFT JOIN client c ON c.code = f.clientid "
						+ "LEFT JOIN vhecule v ON v.id = f.vheculeid "
						+ "LEFT JOIN chauffeur h ON h.id = f.chauffeurid "
						+ "WHERE " + ACTIVE_STATUS_SQL + " ORDER BY CAST(f.numero AS UNSIGNED) DESC")
				.getResultList();
		return mapRows(rows);
	}

	@Transactional
	public List<Facturepassager> getfacturebetwenndate(Date d1, Date d2) {
		try {
			List<Facturepassager> result = em
					.createQuery(
							"SELECT a FROM Facturepassager a where a.statut = :statut and a.date BETWEEN :d1 and :d2 ORDER BY a.numero DESC ",
							Facturepassager.class)
					.setParameter("statut", Statut.ACTIF).setParameter("d1", d1).setParameter("d2", d2)
					.getResultList();
			if (result != null && !result.isEmpty()) {
				return result;
			}
		} catch (RuntimeException ex) {
		}
		List<Object[]> rows = em.createNativeQuery(
				"SELECT f.numero,f.code,f.codes,f.date,f.totalht,f.totalttc,f.totaltva,f.timbres,f.status,f.statut,"
						+ "f.clientid,f.vheculeid,f.chauffeurid,c.nom,v.matricule,h.nompenom "
						+ "FROM facturepassager f "
						+ "LEFT JOIN client c ON c.code = f.clientid "
						+ "LEFT JOIN vhecule v ON v.id = f.vheculeid "
						+ "LEFT JOIN chauffeur h ON h.id = f.chauffeurid "
						+ "WHERE " + ACTIVE_STATUS_SQL + " AND f.date BETWEEN :d1 AND :d2 "
						+ "ORDER BY CAST(f.numero AS UNSIGNED) DESC")
				.setParameter("d1", d1)
				.setParameter("d2", d2)
				.getResultList();
		return mapRows(rows);
	}

	@Transactional
	public Facturepassager getMaxfacture() {
		try {
			List<Facturepassager> result = em.createQuery(
					"SELECT a FROM Facturepassager a where a.statut = :statut and a.numero=(select MAX(b.numero) from Facturepassager b)",
					Facturepassager.class).setParameter("statut", Statut.ACTIF).getResultList();
			if (result.size() > 0) {
				return result.get(0);
			}
		} catch (RuntimeException ex) {
			List<Object[]> rows = em.createNativeQuery(
					"SELECT f.numero,f.code,f.codes,f.date,f.totalht,f.totalttc,f.totaltva,f.timbres,f.status,f.statut,"
							+ "f.clientid,f.vheculeid,f.chauffeurid,c.nom,v.matricule,h.nompenom "
							+ "FROM facturepassager f "
							+ "LEFT JOIN client c ON c.code = f.clientid "
							+ "LEFT JOIN vhecule v ON v.id = f.vheculeid "
							+ "LEFT JOIN chauffeur h ON h.id = f.chauffeurid "
							+ "WHERE " + ACTIVE_STATUS_SQL + " ORDER BY CAST(f.numero AS UNSIGNED) DESC LIMIT 1")
					.getResultList();
			List<Facturepassager> mapped = mapRows(rows);
			if (!mapped.isEmpty()) {
				return mapped.get(0);
			}
		}
		return null;
	}

	@Transactional
	public List<Facturepassager> getAllPasager() {
		List<Facturepassager> result = getAll();
		List<Facturepassager> filtered = new ArrayList<Facturepassager>();
		for (Facturepassager facture : result) {
			if (facture.getVhecule() != null) {
				filtered.add(facture);
			}
		}
		if (filtered.isEmpty()) {
		}
		return filtered;
	}

	@Transactional
	public List<Facturepassager> getAllTransport() {
		try {
			return em
					.createQuery(
							"SELECT a FROM Facturepassager a where a.statut = :statut and a.vhecule.id != :id ORDER BY a.numero DESC",
							Facturepassager.class)
					.setParameter("statut", Statut.ACTIF).setParameter("id", 0).getResultList();
		} catch (RuntimeException ex) {
			return em.createNativeQuery(
					"SELECT * FROM facturepassager f WHERE " + ACTIVE_STATUS_SQL + " AND TRIM(COALESCE(f.vheculeid,'')) <> '' ORDER BY f.numero DESC",
					Facturepassager.class)
					.getResultList();
		}
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
		try {
			return em
					.createQuery(
							"SELECT u FROM Facturepassager u where u.date between :d1 and :d2 and u.statut = :statut",
							Facturepassager.class)
					.setParameter("d1", d1).setParameter("d2", d2).setParameter("statut", Statut.ACTIF)
					.getResultList();
		} catch (RuntimeException ex) {
			List<Object[]> rows = em.createNativeQuery(
					"SELECT f.numero,f.code,f.codes,f.date,f.totalht,f.totalttc,f.totaltva,f.timbres,f.status,f.statut,"
							+ "f.clientid,f.vheculeid,f.chauffeurid,c.nom,v.matricule,h.nompenom "
							+ "FROM facturepassager f "
							+ "LEFT JOIN client c ON c.code = f.clientid "
							+ "LEFT JOIN vhecule v ON v.id = f.vheculeid "
							+ "LEFT JOIN chauffeur h ON h.id = f.chauffeurid "
							+ "WHERE " + ACTIVE_STATUS_SQL + " AND f.date BETWEEN :d1 AND :d2 "
							+ "ORDER BY CAST(f.numero AS UNSIGNED) DESC")
					.setParameter("d1", d1)
					.setParameter("d2", d2)
					.getResultList();
			return mapRows(rows);
		}
	}

	public Facturepassager getfacturebycode(String code) {
		List<Facturepassager> result = em
				.createQuery("SELECT u FROM Facturepassager u where u.codes = :code and u.statut = :statut",
						Facturepassager.class)
				.setParameter("code", code).setParameter("statut", Statut.ACTIF).getResultList();

		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

	public Facturepassager getBLbycodes(String code) {
		List<Facturepassager> result = em
				.createQuery("SELECT u FROM Facturepassager u where u.codes = :code and u.statut = :statut",
						Facturepassager.class)
				.setParameter("code", code).setParameter("statut", Statut.ACTIF).getResultList();

		if (result.size() > 0) {
			return result.get(0);
		} else {
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
			return result.get(0);
		} else {
			return null;
		}
	}

	private List<Facturepassager> mapRows(List<Object[]> rows) {
		List<Facturepassager> factures = new ArrayList<Facturepassager>();
		for (Object[] row : rows) {
			Facturepassager facture = new Facturepassager();
			facture.setNumero(TransportTsvMapper.asInteger(row[0]));
			facture.setCode(TransportTsvMapper.asInteger(row[1]));
			facture.setCodes(TransportTsvMapper.asString(row[2]));
			facture.setDate(TransportTsvMapper.asDate(row[3]));
			facture.setTotalht(TransportTsvMapper.asDouble(row[4]));
			facture.setTotalttc(TransportTsvMapper.asDouble(row[5]));
			facture.setTotaltva(TransportTsvMapper.asDouble(row[6]));
			facture.setTimbres(TransportTsvMapper.asDouble(row[7]));
			facture.setStatus(TransportTsvMapper.asStatus(row[8]));
			facture.setStatut(TransportTsvMapper.asStatut(row[9]));

			Client client = new Client();
			client.setCode(TransportTsvMapper.asInteger(row[10]));
			client.setNom(TransportTsvMapper.asString(row[13]));
			facture.setClient(client);

			Vhecule vhecule = new Vhecule();
			vhecule.setId(TransportTsvMapper.asInteger(row[11]));
			vhecule.setMatricule(TransportTsvMapper.asString(row[14]));
			facture.setVhecule(vhecule.getId() == null ? null : vhecule);

			Chauffeur chauffeur = new Chauffeur();
			chauffeur.setId(TransportTsvMapper.asInteger(row[12]));
			chauffeur.setNompenom(TransportTsvMapper.asString(row[15]));
			facture.setChauffeur(chauffeur.getId() == null ? null : chauffeur);
			factures.add(facture);
		}
		return factures;
	}
}
