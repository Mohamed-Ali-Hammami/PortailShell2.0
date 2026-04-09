package com.tn.shell.dao.gestat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tn.shell.model.gestat.*;
import com.tn.shell.model.shop.Produit;

@Repository
public class LigneIndexDaoImpl implements LigneindexDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Ligneindex c) {
		em.persist(c);

	}

	@Transactional
	public List<Ligneindex> getAllbyProduit(Produit p) {
		double res = 0;
		List result = em
				.createQuery("SELECT c FROM Ligneindex c where c.statut = :statut and c.produit.id = :id",
						Ligneindex.class)
				.setParameter("statut", Statut.ACTIF).setParameter("id", p.getId()).getResultList();

		return result;
	}

	@Transactional
	public List<Ligneindex> getAllventeparposte(String d, Poste poste) {
		List<Ligneindex> result = em
				.createQuery(
						"SELECT c FROM Ligneindex c where c.statut = :statut and c.poste = :poste and c.dates = :date",
						Ligneindex.class)
				.setParameter("statut", Statut.ACTIF).setParameter("poste", poste).setParameter("date", d)
				.getResultList();

		if (result.size() > 0) {
			System.out.println("listlignindex >0" + result.size());

			return result;
		} else {
			System.out.println("listlignindex null" + result.size());
			return result;
		}

	}

	
	@Transactional
	public List<Ligneindex> getAllventepardate(String d, Articlecarburant poste) {
		List<Ligneindex> result = em
				.createQuery(
						"SELECT c FROM Ligneindex c where c.statut = :statut and c.pompe.articlecarburant.id = :poste and c.dates = :date",
						Ligneindex.class)
				.setParameter("statut", Statut.ACTIF).setParameter("poste", poste.getId()).setParameter("date", d)
				.getResultList();

		if (result.size() > 0) {
			System.out.println("listlignindex >0" + result.size());

			return result;
		} else {
			System.out.println("listlignindex null" + result.size());
			return result;
		}

	}

	public double getAllventepardatearticle( Articlecarburant a,Date d1,Date d2) {

		Query q = em.createQuery(
				"SELECT SUM(c.mantant) FROM Ligneindex  c where c.statut = :statut and c.pompe.articlecarburant.id = :id and c.date BETWEEN :date1 and :date2  "
				 ).setParameter("statut", Statut.ACTIF).setParameter("id", a.getId())
				.setParameter("date1", d1).setParameter("date2",d2);
		try {
			Double result = (Double) q.getSingleResult();

			return result;
		} catch (Exception e) {
			return 0;
		}

	}
	public double getquantitebyarticledates(Articlecarburant a,String d) {
		Query q = em.createQuery(
				"SELECT SUM(c.quantite) FROM Ligneindex  c where c.statut = :statut and c.pompe.articlecarburant.id = :id and c.dates = :date1  "
				 ).setParameter("statut", Statut.ACTIF).setParameter("id", a.getId())
				.setParameter("date1", d);
		try {
			Double result = (Double) q.getSingleResult();

			return result;
		} catch (Exception e) {
			return 0;
		}	
	}
	public double getAllventepardatearticlequantite( Articlecarburant a,Date d1,Date d2) {
		Query q = em.createQuery(
				"SELECT SUM(c.quantite) FROM Ligneindex  c where c.statut = :statut and c.pompe.articlecarburant.id = :id  and c.date BETWEEN :date1 and :date2  "
				).setParameter("statut", Statut.ACTIF).setParameter("id", a.getId())
						.setParameter("date1", d1).setParameter("date2",d2);
				 
		try {
			Double result = (Double) q.getSingleResult();

			return result;
		} catch (Exception e) {
			return 0;
		}

	}
	
	public double getAllventepardatearticlequantitepardate( Articlecarburant a,String d1) {
		Query q = em.createQuery(
				"SELECT SUM(c.mantant) FROM Ligneindex  c where c.statut = :statut and c.pompe.articlecarburant.id = :id  and c.dates = :date1 "
				).setParameter("statut", Statut.ACTIF).setParameter("id", a.getId())
						.setParameter("date1", d1);
				 
		try {
			Double result = (Double) q.getSingleResult();

			return result;
		} catch (Exception e) {
			return 0;
		}

	}
	
	
	

	@Transactional
	public List<Ligneindex> getAllparposte(Caisse c) {
		List<Ligneindex> result = em
				.createQuery("SELECT c FROM Ligneindex c where c.statut = :statut and c.caisse.id = :caisse",
						Ligneindex.class)
				.setParameter("statut", Statut.ACTIF).setParameter("caisse", c.getId()).getResultList();
		if (result.size() > 0) {
			System.out.println("listlignindex >0" + result.size());

			return result;
		} else {
			System.out.println("listlignindex null" + result.size());
			return result;
		}
	}

	@Transactional
	public List<Ligneindex> getAllventepardate(String d) {

		List result = em.createQuery("SELECT c FROM Ligneindex c   where c.statut = :statut  and c.dates = :date ",
				Ligneindex.class).setParameter("statut", Statut.ACTIF).setParameter("date", d).getResultList();
		if (result.size() > 0) {
			System.out.println("listlignindex >0" + result.size());
			for (Object p : result)
				System.out.println("o" + p);
			return result;
		} else {
			System.out.println("listlignindex null" + result.size());
			return result;
		}
	}

	@Transactional
	public double getquantitebyarticle(Articlecarburant a, Caisse c) {
		List<Ligneindex> VheculeListem = em.createQuery(
				"SELECT SUM(c.quantite) FROM  Ligneindex c where c.pompe.articlecarburant.nom = :nom and c.statut = :statut and c.caisse.id = :id",
				Ligneindex.class).setParameter("nom", a.getNom()).setParameter("statut", Statut.ACTIF)
				.setParameter("id", c.getId()).getResultList();

		if (VheculeListem.size() > 0) {
			System.out.println("objet trouvé " + VheculeListem.get(0).getId() + "\n\n\n");
			return VheculeListem.get(0).getQuantite();
		} else {
			System.out.println("\n\nl  objet Articlecarburant n exsite pas\n\n");
			return 0;
		}
	}

	@Transactional
	public List<Ligneindex> getAllventeentredate(Date d, Date d2, Articlecarburant a) {

		List result = em.createQuery(
				"SELECT c FROM Ligneindex c   where c.statut = :statut and c.pompe.articlecarburant.id = :id  and c.date BETWEEN :date1 and :date2",
				Ligneindex.class).setParameter("statut", Statut.ACTIF).setParameter("id", a.getId())
				.setParameter("date1", d).setParameter("date2", d2).getResultList();
		if (result.size() > 0) {
			System.out.println("listlignindex size " + result.size());
			for (Object p : result)
				System.out.println("o" + p);
			return result;
		} else {
			System.out.println("listlignindex null" + result.size());
			return result;
		}
	}

	@Transactional
	public Ligneindex getAllventepardateandpompeandposte(String dates1, Pompe p, Poste Poste1) {
		Ligneindex l = new Ligneindex();
		List<Ligneindex> result = em.createQuery(
				"SELECT c FROM Ligneindex c   where c.statut = :statut and c.dates = :dates  and c.pompe.id = :p and c.poste = :s",
				Ligneindex.class).setParameter("statut", Statut.ACTIF).setParameter("dates", dates1)
				.setParameter("p", p.getId()).setParameter("s", Poste1).getResultList();
		if (result.size() > 0) {
			System.out.print("\n\n\n" + p.getLibelle());
			System.out.println(" listlignindex > 0" + result.size() + "\n\n\n");

			return result.get(0);
		} else {

			System.out.println("listlignindex null");
			return null;
		}
	}

	@Transactional
	public Ligneindex getAllventepardateandpompeandposte2(Pompe p, Caisse c) {
		Ligneindex l = new Ligneindex();
		List<Ligneindex> result = em.createQuery(
				"SELECT c FROM Ligneindex c   where c.statut = :statut   and c.pompe.id = :p   and c.caisse.id = :s",
				Ligneindex.class).setParameter("statut", Statut.ACTIF).setParameter("p", p.getId())
				.setParameter("s", c.getId()).getResultList();
		if (result.size() > 0) {
			System.out.print("\n\n\n" + p.getLibelle());
			System.out.println(" listlignindex > 0" + result.size() + "\n\n\n");

			return result.get(0);
		} else {

			System.out.println("listlignindex null");
			return null;
		}
	}

	@Transactional
	public double getmaxcode(Pompe p, Caisse c) {
		List<Ligneindex> result = em.createQuery(
				"SELECT a FROM Ligneindex a  where a.statut = :statut and a.pompe.id = :id and a.caisse.id = :c",
				Ligneindex.class).setParameter("statut", Statut.ACTIF).setParameter("id", p.getId())
				.setParameter("c", c.getId() - 1)

				.getResultList();
		if (result.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return result.get(0).getIndexouverture();
		} else {
			System.out.println("\n\nl  objet Achatcaisse n exsite pas\n\n");
			return 0;
		}
	}

	@Transactional
	public Ligneindex getmaxcode() {
		List<Ligneindex> result = em.createQuery(
				"SELECT a FROM Ligneindex a  where a.statut = :statut  and a.id =(select MAX(b.id) from Ligneindex b) ",
				Ligneindex.class).setParameter("statut", Statut.ACTIF)

				.getResultList();
		if (result.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return result.get(0);
		} else {
			System.out.println("\n\nl  objet Achatcaisse n exsite pas\n\n");
			return null;
		}
	}

	@Transactional
	public List<Ligneindex> getAllventeparposteNegatif(String d, Poste poste) {
		List<Ligneindex> result = em
				.createQuery(
						"SELECT c FROM Ligneindex c where c.statut = :statut and c.poste = :poste  and c.dates = :date",
						Ligneindex.class)
				.setParameter("statut", Statut.ACTIF).setParameter("poste", poste).setParameter("date", d)
				.getResultList();
		return result;
	}

	@Transactional
	public List<Ligneindex> getLigneindexbycreditclient(Credit credit) {
		List<Ligneindex> result = em
				.createQuery("SELECT c FROM Ligneindex c where c.statut = :statut and c.creditclient.id = :poste",
						Ligneindex.class)
				.setParameter("statut", Statut.ACTIF).setParameter("poste", credit.getId()).getResultList();

		if (result.size() > 0)
			return result;
		else
			return null;
	}

	@Transactional
	public List<Ligneindex> getAll() {
		List<Ligneindex> result = em
				.createQuery("SELECT c FROM Ligneindex c where c.statut = :statut", Ligneindex.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public void update(Ligneindex c) {
		Ligneindex lc = em.find(Ligneindex.class, c.getId());
		lc.setPoste(c.getPoste());

		lc.setQuantite(c.getQuantite());
		lc.setIndexfermuture(c.getIndexfermuture());
		lc.setIndexouverture(c.getIndexouverture());
		lc.setDate(c.getDate());
		em.merge(lc);

	}

	@Transactional
	public void delete(Ligneindex c) {
		Ligneindex lc = em.find(Ligneindex.class, c.getId());
		lc.setStatut(Statut.DEACTIF);
		em.merge(lc);

	}

}
