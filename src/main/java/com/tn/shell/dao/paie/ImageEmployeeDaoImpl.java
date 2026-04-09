package com.tn.shell.dao.paie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.paie.Employee;
import com.tn.shell.model.vetement.Vetement;
import com.tn.shell.model.paie.LigneImageEmployee;

@Repository
public class ImageEmployeeDaoImpl implements ImageEmployeeDAO {

	@PersistenceContext
	private EntityManager em;

	 
	 
	@Transactional
	public  LigneImageEmployee getImagebyEmployeeandpositions(Employee f,String position) {
		try {
		 LigneImageEmployee result = em.createQuery(
				"SELECT b FROM LigneImageEmployee  b  where  b.employee.matricule = :numero and b.position = :pos",
				LigneImageEmployee.class).setParameter("numero", f.getMatricule()).setParameter("pos", position).getSingleResult();
		 if (result!=null) {
				System.out.println("objet trouvé " + "\n\n\n");
				return result;
			} else {
				System.out.println("\n\nl  objet LigneImagerendement n exsite pas\n\n");
				return null;
			}
			}
			catch(Exception ee) {
				return null;
					}
		}
	
	 
 
	 
	@Transactional
	public  List<LigneImageEmployee> getImagebyEmployee(Employee f) {
		List<LigneImageEmployee> result = em.createQuery(
				"SELECT b FROM LigneImageEmployee  b  where  b.employee.matricule = :numero ",
				LigneImageEmployee.class).setParameter("numero", f.getMatricule()).getResultList();
		return result;
	}
 
}
