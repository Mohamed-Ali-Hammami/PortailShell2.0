package com.tn.shell.dao.paie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.paie.*;

@Repository
public class PaieprimeDaoImpl implements PaieprimeDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<Paieprime> getAll() {
		List<Paieprime> result = em.createQuery("SELECT a FROM Paieprime a  where a.statut = :statut", Paieprime.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public List<Paieprime> getPaieByEmployee(Employee e) {
		List<Paieprime> result = em
				.createQuery(
						"SELECT a FROM Paieprime a  where a.statut = :statut and a.employee.matricule = :matricule ",
						Paieprime.class)
				.setParameter("statut", Statut.ACTIF)

				.setParameter("matricule", e.getMatricule())

				.getResultList();
		if (result.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return result;
		} else {
			System.out.println("\n\nl  objet client n exsite pas\n\n");
			return null;
		}
	}

	@Transactional
	public List<Paieprime> getPaieByAnnee(Integer annee) {
		List<Paieprime> result = em.createQuery(
				"SELECT a FROM Paieprime a  where a.statut = :statut and a.annee = :annee and a.employee.status = :status",
				Paieprime.class).setParameter("statut", Statut.ACTIF).setParameter("annee", annee)
				.setParameter("status", Status.Declare).getResultList();
		if (result.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return result;
		} else {
			System.out.println("\n\nl  objet client n exsite pas\n\n");
			return null;
		}
	}

	@Transactional
	public List<Paieprime> getPaieByAnneeAndMois(Integer annee, Integer mois) {
		List<Paieprime> result = em
				.createQuery(
						"SELECT a FROM Paieprime a  where a.statut = :statut and a.annee = :annee and a.mois = :mois ",
						Paieprime.class)
				.setParameter("statut", Statut.ACTIF).setParameter("annee", annee).setParameter("mois", mois)

				.getResultList();
		if (result.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return result;
		} else {
			System.out.println("\n\nl  objet client n exsite pas\n\n");
			return null;
		}
	}
	
	@Transactional
	public double getPaieByAnneeAndMois2(Integer annee, Integer mois) {
		Query q=em.createQuery( 
						"SELECT SUM(a.formulaire_Paie.salaire_net) FROM Paieprime a  where a.statut = :statut and a.annee = :annee and a.mois = :mois " )
				.setParameter("statut", Statut.ACTIF).setParameter("annee", annee).setParameter("mois", mois);

		 try {
				Double result = (Double) q.getSingleResult();

				return result;
			} catch (Exception e) {
				return 0;
			}
	}

	@Transactional
	public List<Paieprime> getPaieNondeclareByAnneeAndMois(Integer annee, Integer mois) {
		List<Paieprime> result = em.createQuery(
				"SELECT a FROM Paieprime a  where a.statut = :statut and a.annee = :annee and a.mois = :mois and a.employee.status = :status",
				Paieprime.class).setParameter("statut", Statut.ACTIF).setParameter("annee", annee)
				.setParameter("mois", mois).setParameter("status", Status.NonDeclaree).getResultList();
		if (result.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return result;
		} else {
			System.out.println("\n\nl  objet client n exsite pas\n\n");
			return null;
		}
	}

	@Transactional
	public List<Paieprime> getPaieByAnneeAndMoisEmployee(Employee e, Integer annee, Integer mois) {
		List<Paieprime> result = em.createQuery(
				"SELECT a FROM Paieprime a  where a.statut = :statut and a.employee.matricule = :matricule and a.annee = :annee and a.mois = :mois ",
				Paieprime.class).setParameter("statut", Statut.ACTIF)

				.setParameter("matricule", e.getMatricule()).setParameter("annee", annee).setParameter("mois", mois)
				.getResultList();
		if (result.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return result;
		} else {
			System.out.println("\n\nl  objet client n exsite pas\n\n");
			return null;
		}
	}

	@Transactional
	public List<Paieprime> getPaieByAnneeAndEmployee(Employee e, Integer annee) {
		List<Paieprime> result = em.createQuery(
				"SELECT a FROM Paieprime a  where a.statut = :statut and a.employee.matricule = :matricule and a.annee = :annee ",
				Paieprime.class).setParameter("statut", Statut.ACTIF)

				.setParameter("matricule", e.getMatricule()).setParameter("annee", annee)

				.getResultList();
		if (result.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return result;
		} else {
			System.out.println("\n\nl  objet client n exsite pas\n\n");
			return null;
		}
	}

	@Transactional
	public void save(Paieprime c) {
		em.persist(c);
	}

	@Transactional
	public void update(Paieprime c) {
		Paie a = em.find(Paie.class, c.getId());
		a.setAnnee(c.getAnnee());
		a.setMois(c.getMois());
		a.setEmployee(c.getEmployee());
		a.setStatut(c.getStatut());
		em.merge(a);

	}

	@Transactional
	public void detele(Paieprime c) {
		Paieprime a = em.find(Paieprime.class, c.getId());
		a.setStatut(c.getStatut());
		em.merge(a);

	}

}
