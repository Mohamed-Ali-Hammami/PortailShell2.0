package com.tn.shell.dao.gestat;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.*;

@Repository
public class CreditPassationDaoImpl implements CreditpassationDao {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Creditpassation creditpassationclient) {
		em.persist(creditpassationclient);

	}

	@Transactional
	public Creditpassation findbyCredit(Credit c) {
		List<Creditpassation> result = em
				.createQuery("SELECT c FROM Creditpassation c where  c.credit.id = :id",
						Creditpassation.class)
				.setParameter("id", c.getId()).getResultList();
		if (result.size() > 0)
			return result.get(0);
		return null;
	}
	
	@Transactional
	public  List<Creditpassation> findbyClient(Clientpassation c) {
		List<Creditpassation> result = em
				.createQuery("SELECT c FROM Creditpassation c where c.statut = :statut and c.clientpassation.id = :id",
						Creditpassation.class)
				.setParameter("statut", Statut.ACTIF).setParameter("id", c.getId()).getResultList();
		if (result.size() > 0)
			return result;
		return null;
	}

	@Transactional
	public List<Creditpassation> getAll() {
		List<Creditpassation> result = em
				.createQuery("SELECT c FROM Creditpassation c where c.statut = :statut ", Creditpassation.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public List<Creditpassation> getListcreditdate(String date) {
		List<Creditpassation> result = new ArrayList<Creditpassation>();
		result = em.createQuery(
				"SELECT a FROM Creditpassation a where a.statut = :statut   and a.dates = :caiise order by a.client.code",
				Creditpassation.class).setParameter("statut", Statut.ACTIF).setParameter("caiise", date)
				.getResultList();

		if (result.size() > 0)
			return result;
		else
			return result;
	}

	@Transactional
	public List<Creditpassation> getListcreditdateandclient(String date, Clientpassation c) {
		List<Creditpassation> result = new ArrayList<Creditpassation>();
		result = em.createQuery(
				"SELECT a FROM Creditpassation a where a.statut = :statut   and a.dates = :caiise and a.client.code = :code",
				Creditpassation.class).setParameter("statut", Statut.ACTIF).setParameter("caiise", date)
				.setParameter("id", c.getId()).getResultList();

		if (result.size() > 0)
			return result;
		else
			return result;
	}

	@Transactional
	public List<Creditpassation> Findbynom(String nom) {
		List<Creditpassation> CreditpassationclientListem = em
				.createQuery("SELECT c FROM  Creditpassation c where c.numero = :nom and c.statut = :statut",
						Creditpassation.class)
				.setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();

		if (CreditpassationclientListem.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return CreditpassationclientListem;
		} else {
			System.out.println("\n\nl  objet Creditpassationclient n exsite pas\n\n");
			return null;
		}
	}

	@Transactional
	public List<Creditpassation> getCreditclientbyCaisse(Caisse c) {
		List<Creditpassation> result = new ArrayList<Creditpassation>();
		result = em.createQuery(
				"SELECT a FROM Creditpassation a where a.statut = :statut   and a.caisse.id = :caiise and a.montant != 0 order by a.client.code",
				Creditpassation.class).setParameter("statut", Statut.ACTIF).setParameter("caiise", c.getId())
				.getResultList();

		if (result.size() > 0) {
			System.out.println("" + result.get(0).getClientpassation().getId());
			return result;
		}

		else {
			System.out.println("\n\npas de crdit" + c.getId());
			return result;
		}
	}

	@Transactional
	public Creditpassation getCreditclientbyCaisseandclient(Creditpassation cl) {
		List<Creditpassation> result = new ArrayList<Creditpassation>();
		result = em
				.createQuery("SELECT a FROM Creditpassation a where a.statut = :statut     and a.id = :code",
						Creditpassation.class)
				.setParameter("statut", Statut.ACTIF)

				.setParameter("code", cl.getId()).getResultList();

		if (result.size() > 0) {
			System.out.println("" + result.get(0).getClientpassation().getId());
			return result.get(0);
		}

		else {
			System.out.println("\n\npas de crdit" + cl.getId());
			return null;
		}
	}

	@Transactional
	public Creditpassation Findbycode(Integer nom) {
		List<Creditpassation> CreditpassationclientListem = em
				.createQuery("SELECT c FROM  Creditpassation c where c.code = :nom and c.statut = :statut",
						Creditpassation.class)
				.setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();

		if (CreditpassationclientListem.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return CreditpassationclientListem.get(0);
		} else {
			System.out.println("\n\nl  objet Creditpassationclient n exsite pas\n\n");
			return null;
		}
	}

	@Transactional
	public Creditpassation Findbymf(String nom) {
		List<Creditpassation> CreditpassationclientListem = em
				.createQuery("SELECT c FROM  Creditpassation c where c.mf = :nom and c.statut = :statut",
						Creditpassation.class)
				.setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();

		if (CreditpassationclientListem.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return CreditpassationclientListem.get(0);
		} else {
			System.out.println("\n\nl  objet Creditpassationclient n exsite pas\n\n");
			return null;
		}
	}

	@Transactional
	public void update(Creditpassation a) {
		Creditpassation c = em.find(Creditpassation.class, a.getId());
		c.setMontant(a.getMontant());

		c.setClientpassation(a.getClientpassation());

		em.merge(c);
	}

	@Transactional
	public void delete(Creditpassation creditpassationclient) {
		Creditpassation c = em.find(Creditpassation.class, creditpassationclient.getId());
         c.setStatut(creditpassationclient.getStatut());
		em.merge(c);

	}

	public Creditpassation getcreditbyid(Integer id) {
		List<Creditpassation> result = new ArrayList<Creditpassation>();
		result = em
				.createQuery("SELECT a FROM Creditpassation  a where a.statut = :statut   and a.id = :caiise",
						Creditpassation.class)
				.setParameter("statut", Statut.ACTIF).setParameter("caiise", id).getResultList();

		if (result.size() > 0)
			return result.get(0);
		else
			return null;
	}

}
