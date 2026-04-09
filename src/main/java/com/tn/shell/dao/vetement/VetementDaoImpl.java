package com.tn.shell.dao.vetement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.Statut;
import com.tn.shell.model.paie.Employee;
import com.tn.shell.model.vetement.Vetement;

 

@Repository
public class VetementDaoImpl implements VetementDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<Vetement> getAll() {
		List<Vetement> result = em.createQuery("SELECT a FROM Vetement a  where a.statut = :statut", Vetement.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public void save(Vetement c) {
		em.persist(c);
	}
	 
	@Transactional
	public Integer getmaxcode() {
		List<Vetement> result = em
				.createQuery("SELECT a FROM Vetement a  where a.statut = :statut and a.id =(select MAX(b.id) from Vetement b)", Vetement.class)
				.setParameter("statut", Statut.ACTIF)

				.getResultList();
		if (result.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return result.get(0).getId();
		} else {
			System.out.println("\n\nl  objet Vetement n exsite pas\n\n");
			return 0;
		}
	}
	@Transactional
	public Vetement getVetementbyid(Integer id) {
		return em.find(Vetement.class, id);
	}
	 

	@Transactional
	public List<Vetement> getVetementbyEmployee(Employee f) {
		List<Vetement> result = em.createQuery(
				"SELECT b FROM Vetement  b  where  b.employee.matricule = :numero ",
				Vetement.class).setParameter("numero", f.getMatricule()).getResultList();
		return result;
	}

	 
}
