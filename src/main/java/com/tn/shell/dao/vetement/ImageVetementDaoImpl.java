package com.tn.shell.dao.vetement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.paie.Employee;
import com.tn.shell.model.vetement.Vetement;

@Repository
public class ImageVetementDaoImpl implements ImageVetementDAO {

	@PersistenceContext
	private EntityManager em;

	 
	 
 
	 
	@Transactional
	public List<Vetement> getVetementbyEmployee(Employee f) {
		List<Vetement> result = em.createQuery(
				"SELECT b FROM Vetement  b  where  b.employee.matricule = :numero ",
				Vetement.class).setParameter("numero", f.getMatricule()).getResultList();
		return result;
	}
 
}
