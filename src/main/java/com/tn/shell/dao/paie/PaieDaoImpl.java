package com.tn.shell.dao.paie;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.paie.*;

@Repository
public class PaieDaoImpl implements PaieDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<Paie> getAll() {
		List<Paie> result = em.createQuery("SELECT a FROM Paie a  where a.statut = :statut", Paie.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}
	
	 
		public double  getTotalCnss(String fonction,Integer annee,Integer mois) { 
			 Query q=em.createNativeQuery("SELECT SUM(a.retenue_cnss) FROM Paie a  where a.statut = 'ACTIF' "+" and a.annee = "+annee+" and a.mois ="+mois+" and a.employeeid in (select p.matricule from employee p where p.fonction = '"+fonction+"')"); 
						 try {
					double result =   (Double) q.getSingleResult();
					return result;
				} catch (Exception e) {
					return 0;
				} 
		}	
		@Transactional
		public double  getTotalPayement(String fonction,Integer annee,Integer mois) {
		 	 Query q=em.createNativeQuery("SELECT SUM(a.avance+a.net_apayer) FROM Paie a  where a.statut = 'ACTIF' "+" and a.annee = "+annee+" and a.mois ="+mois+" and a.employeeid in (select p.matricule from employee p where p.status != 'ParVoiture' and  p.fonction = '"+fonction+"')");
			 try {
					Double result = (Double) q.getSingleResult();

					return result;
				} catch (Exception e) {
					return 0;
				}
		}
	
	@Transactional
	public List<Paie> getPaieNondeclare2ByAnneeAndMois(Integer annee,Integer mois) {
		List<Paie> result = em.createQuery(
				"SELECT a FROM Paie a  where a.statut = :statut and a.annee = :annee and a.mois = :mois and a.employee.status = :status",
				Paie.class).setParameter("statut", Statut.ACTIF).setParameter("annee", annee).setParameter("mois", mois)
				.setParameter("status", Status.ParVoiture).getResultList();
		if (result.size() > 0) {
			return result;
		} else {
			return null;
		}
	}

	@Transactional
	public List<Paie> getPaieByEmployee(Employee e) {
		List<Paie> result = em
				.createQuery("SELECT a FROM Paie a  where a.statut = :statut and a.employee.matricule = :matricule ",
						Paie.class)
				.setParameter("statut", Statut.ACTIF)

				.setParameter("matricule", e.getMatricule())

				.getResultList();
		if (result.size() > 0) {
			return result;
		} else {
			return null;
		}
	}

	@Transactional
	public List<Paie> getPaieByAnnee(Integer annee) {
		List<Paie> result = em.createQuery(
				"SELECT a FROM Paie a  where a.statut = :statut and a.annee = :annee and a.employee.status = :status",
				Paie.class).setParameter("statut", Statut.ACTIF).setParameter("annee", annee)
				.setParameter("status", Status.Declare).getResultList();
		if (result.size() > 0) {
			return result;
		} else {
			return null;
		}
	}

	@Transactional
	public List<Paie> getPaieByAnneeAndMois(Integer annee, Integer mois) {
		List<Paie> result = em.createQuery(
				"SELECT a FROM Paie a  where a.statut = :statut and a.annee = :annee and a.mois = :mois and a.employee.status = :status",
				Paie.class).setParameter("statut", Statut.ACTIF).setParameter("annee", annee).setParameter("mois", mois)
				.setParameter("status", Status.Declare).getResultList();
		if (result.size() > 0) {
			return result;
		} else {
			return null;
		}
	}

	@Transactional
	public List<Paie> getPaieByAnneeAndMois2(Integer annee, Integer mois) {
		List<Paie> result = em
				.createQuery("SELECT a FROM Paie a  where a.statut = :statut and a.annee = :annee and a.mois = :mois ",
						Paie.class)
				.setParameter("statut", Statut.ACTIF).setParameter("annee", annee).setParameter("mois", mois)

				.getResultList();
		if (result.size() > 0) {
			return result;
		} else {
			return null;
		}
	}
	
	
	@Transactional
	public double getPaieByAnneeAndMoiss(Integer annee, Integer mois) {
		 Query q=em.createQuery("SELECT SUM(a.formulaire_Paie.net_apayer+a.formulaire_Paie.avance) FROM Paie a  where a.statut = :statut and a.annee = :annee and a.mois = :mois ")
				 .setParameter("statut", Statut.ACTIF).setParameter("annee", annee).setParameter("mois", mois)  ;
		 try {
				Double result = (Double) q.getSingleResult();

				return result;
			} catch (Exception e) {
				return 0;
			}
	}
	@Transactional
	public double getMinSalaireByAnneeAndMoisEmployee(Employee e,Integer annee) {
		 Query q=em.createQuery("SELECT MIN(a.formulaire_Paie.salairedebase/a.formulaire_Paie.nb_heure) FROM Paie a  where a.statut = :statut and a.annee = :annee and a.employee.matricule = :e")
				 .setParameter("statut", Statut.ACTIF).setParameter("annee", annee).setParameter("e",e.getMatricule())  ;
		 try {
				Double result = (Double) q.getSingleResult();

				return result;
			} catch (Exception e1) {
				return  0;
	}
	}

	@Transactional
	public List<Paie> getPaieNondeclareByAnneeAndMois(Integer annee, Integer mois) {
		List<Paie> result = em.createQuery(
				"SELECT a FROM Paie a  where a.statut = :statut and a.annee = :annee and a.mois = :mois and a.employee.status = :status",
				Paie.class).setParameter("statut", Statut.ACTIF).setParameter("annee", annee).setParameter("mois", mois)
				.setParameter("status", Status.NonDeclaree).getResultList();
		if (result.size() > 0) {
			return result;
		} else {
			return null;
		}
	}

	@Transactional
	public Paie getPaieByAnneeAndMoisEmployee(Employee e, Integer annee, Integer mois) {
		List<Paie> result = em.createQuery(
				"SELECT a FROM Paie a  where a.statut = :statut and a.employee.matricule = :matricule and a.annee = :annee and a.mois = :mois ",
				Paie.class).setParameter("statut", Statut.ACTIF)

				.setParameter("matricule", e.getMatricule()).setParameter("annee", annee).setParameter("mois", mois)
				.getResultList();
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	public List<Paie> getPaieByAnneeAndEmployee(Employee e, Integer annee) {
		List<Paie> result = em.createQuery(
				"SELECT a FROM Paie a  where a.statut = :statut and a.employee.matricule = :matricule and a.annee = :annee ",
				Paie.class).setParameter("statut", Statut.ACTIF)

				.setParameter("matricule", e.getMatricule()).setParameter("annee", annee)

				.getResultList();
		if (result.size() > 0) {
			return result;
		} else {
			return null;
		}
	}

	@Transactional
	public void save(Paie c) {
		em.persist(c);
	}

	@Transactional
	public void update(Paie c) {
		Paie a = em.find(Paie.class, c.getId());
		a.setAnnee(c.getAnnee());
		a.setMois(c.getMois());
		a.setEmployee(c.getEmployee());
		a.setFormulaire_Paie(c.getFormulaire_Paie());
		a.setStatut(c.getStatut());
		em.merge(a);

	}

	@Transactional
	public void detele(Paie c) {
		Paie a = em.find(Paie.class, c.getId());
		a.setStatut(c.getStatut());
		em.merge(a);

	}

}
