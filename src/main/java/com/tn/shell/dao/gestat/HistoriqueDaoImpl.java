package com.tn.shell.dao.gestat;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.*;

@Repository
public class HistoriqueDaoImpl implements HistoriqueDao {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Historiquepayement historiquepayementclient) {
		em.persist(historiquepayementclient);

	}

	@Transactional
	public Historiquepayement findbyCredit(Creditpassation c) {
		List<Historiquepayement> result = em
				.createQuery("SELECT c FROM Historiquepayement c where c.statut = :statut and c.creditpassation.id = :id",
						Historiquepayement.class)
				.setParameter("statut", Statut.ACTIF).setParameter("id", c.getId()).getResultList();
		if (result.size() > 0)
			return result.get(0);
		return null;
	}
	
	@Transactional
	public  List<Historiquepayement> findbyClient(Clientpassation c) {
		List<Historiquepayement> result = em
				.createQuery("SELECT c FROM Historiquepayement c where c.statut = :statut and c.clientpassation.id = :id",
						Historiquepayement.class)
				.setParameter("statut", Statut.ACTIF).setParameter("id", c.getId()).getResultList();
		if (result.size() > 0)
			return result;
		return null;
	}

	@Transactional
	public List<Historiquepayement> getAll() {
		List<Historiquepayement> result = em
				.createQuery("SELECT c FROM Historiquepayement c where c.statut = :statut ", Historiquepayement.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public List<Historiquepayement> getListcreditdate(String date) {
		List<Historiquepayement> result = new ArrayList<Historiquepayement>();
		result = em.createQuery(
				"SELECT a FROM Historiquepayement a where a.statut = :statut   and a.dates = :caiise",
				Historiquepayement.class).setParameter("statut", Statut.ACTIF).setParameter("caiise", date)
				.getResultList();

		if (result.size() > 0)
			return result;
		else
			return result;
	}

	@Transactional
	public List<Historiquepayement> getListcreditdateandclient(String date, Clientpassation c) {
		List<Historiquepayement> result = new ArrayList<Historiquepayement>();
		result = em.createQuery(
				"SELECT a FROM Historiquepayement a where a.statut = :statut   and a.dates = :caiise and a.client.code = :code",
				Historiquepayement.class).setParameter("statut", Statut.ACTIF).setParameter("caiise", date)
				.setParameter("id", c.getId()).getResultList();

		if (result.size() > 0)
			return result;
		else
			return result;
	}

	@Transactional
	public List<Historiquepayement> Findbynom(String nom) {
		List<Historiquepayement> HistoriquepayementclientListem = em
				.createQuery("SELECT c FROM  Historiquepayement c where c.numero = :nom and c.statut = :statut",
						Historiquepayement.class)
				.setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();

		if (HistoriquepayementclientListem.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return HistoriquepayementclientListem;
		} else {
			System.out.println("\n\nl  objet Historiquepayementclient n exsite pas\n\n");
			return null;
		}
	}
 
	@Transactional
	public Historiquepayement getCreditclientbyCaisseandclient(Historiquepayement cl) {
		List<Historiquepayement> result = new ArrayList<Historiquepayement>();
		result = em
				.createQuery("SELECT a FROM Historiquepayement a where a.statut = :statut     and a.id = :code",
						Historiquepayement.class)
				.setParameter("statut", Statut.ACTIF)

				.setParameter("code", cl.getId()).getResultList();

		if (result.size() > 0) {
			System.out.println("" + result.get(0).getCreditpassation().getId());
			return result.get(0);
		}

		else {
			System.out.println("\n\npas de crdit" + cl.getId());
			return null;
		}
	}

	@Transactional
	public Historiquepayement Findbycode(Integer nom) {
		List<Historiquepayement> HistoriquepayementclientListem = em
				.createQuery("SELECT c FROM  Historiquepayement c where c.code = :nom and c.statut = :statut",
						Historiquepayement.class)
				.setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();

		if (HistoriquepayementclientListem.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return HistoriquepayementclientListem.get(0);
		} else {
			System.out.println("\n\nl  objet Historiquepayementclient n exsite pas\n\n");
			return null;
		}
	}

	@Transactional
	public Historiquepayement Findbymf(String nom) {
		List<Historiquepayement> HistoriquepayementclientListem = em
				.createQuery("SELECT c FROM  Historiquepayement c where c.mf = :nom and c.statut = :statut",
						Historiquepayement.class)
				.setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();

		if (HistoriquepayementclientListem.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return HistoriquepayementclientListem.get(0);
		} else {
			System.out.println("\n\nl  objet Historiquepayementclient n exsite pas\n\n");
			return null;
		}
	}

	@Transactional
	public void update(Historiquepayement a) {
		Historiquepayement c = em.find(Historiquepayement.class, a.getId());
		c.setMontant(a.getMontant());

		c.setCreditpassation(a.getCreditpassation());

		em.merge(c);
	}

	@Transactional
	public void delete(Historiquepayement Historiquepayementclient) {
		Historiquepayement c = em.find(Historiquepayement.class, Historiquepayementclient.getId());
         c.setStatut(Historiquepayementclient.getStatut());
		em.merge(c);

	}

	public Historiquepayement getcreditbyid(Integer id) {
		List<Historiquepayement> result = new ArrayList<Historiquepayement>();
		result = em
				.createQuery("SELECT a FROM Historiquepayement  a where a.statut = :statut   and a.id = :caiise",
						Historiquepayement.class)
				.setParameter("statut", Statut.ACTIF).setParameter("caiise", id).getResultList();

		if (result.size() > 0)
			return result.get(0);
		else
			return null;
	}

}
