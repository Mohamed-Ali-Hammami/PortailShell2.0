package com.tn.shell.dao.gestat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.*;

@Repository
public class AvoirbanqDaoImpl implements AvoirbanqDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<Avoirbancaire> getAll() {
		List<Avoirbancaire> result = em.createQuery("SELECT a FROM Avoirbancaire a  where a.statut = :statut", Avoirbancaire.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}
	public List<Avoirbancaire> getBETWENNDATES(Date d1,Date d2){
		List<Avoirbancaire> result =new ArrayList<Avoirbancaire>();
		   result = em
				.createQuery("SELECT a FROM Avoirbancaire a  where a.statut = :statut  and a.date BETWEEN :d1 and :d2 ORDER BY a.id DESC", Avoirbancaire.class)
				.setParameter("statut", Statut.ACTIF)
				.setParameter("d1",d1)
				.setParameter("d2",d2)
				.getResultList();
		if (result.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return result;
		} else {
			System.out.println("\n\nl  objet Avoir n exsite pas\n\n");
			return result;
		}
	}
	
	@Transactional
	 public List<Avoirbancaire> getavoirbydates(String date){
		List<Avoirbancaire> result =new ArrayList<Avoirbancaire>();
		   result = em
				.createQuery("SELECT a FROM Avoirbancaire a  where a.statut = :statut  and a.dates = :dates ORDER BY a.id DESC", Avoirbancaire.class)
				.setParameter("statut", Statut.ACTIF)
				.setParameter("dates",date)
				.getResultList();
		if (result.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return result;
		} else {
			System.out.println("\n\nl  objet Avoir n exsite pas\n\n");
			return result;
		}
	}
	@Transactional
	public void save(Avoirbancaire c) {
		em.persist(c);
	}
	 
	
	
	 	@Transactional
	public Integer getmaxcode() {
		List<Avoirbancaire> result = em
				.createQuery("SELECT a FROM Avoirbancaire a  where a.statut = :statut ORDER BY a.id DESC LIMIT 1", Avoirbancaire.class)
				.setParameter("statut", Statut.ACTIF)

				.getResultList();
		if (result.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return result.get(0).getId();
		} else {
			System.out.println("\n\nl  objet Avoir n exsite pas\n\n");
			return 0;
		}
	}
	@Transactional
	public Avoirbancaire getAvoirbyid(Integer id) {
		List<Avoirbancaire> result = em
				.createQuery("SELECT a FROM Avoirbancaire a  where a.statut = :statut and a.id = :id", Avoirbancaire.class)
				.setParameter("statut", Statut.ACTIF)
				.setParameter("id", id)
				.getResultList();
		if (result.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return result.get(0);
		} else {
			System.out.println("\n\nl  objet Avoir n exsite pas\n\n");
			return null;
		}
	}
	@Transactional
	public void update(Avoirbancaire c) {
		Avoirbancaire a = em.find(Avoirbancaire.class, c.getId());
	a=c;
		em.merge(a);

	}

	 
}
