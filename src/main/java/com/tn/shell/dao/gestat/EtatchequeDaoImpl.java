package com.tn.shell.dao.gestat;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.Etatcheque;

@Repository
public class EtatchequeDaoImpl implements EtatchequeDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<Etatcheque> getAll() {
		return em.createQuery("SELECT a FROM Etatcheque a", Etatcheque.class).getResultList();
	}
	
	@Transactional
	public List<Etatcheque> getSoldetpebydates(String date) {
		List<Etatcheque> result = new ArrayList<Etatcheque>();
		result = em.createQuery("SELECT a FROM Etatcheque a", Etatcheque.class).getResultList();
		if (result.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return result;
		} else {
			System.out.println("\n\nl  objet Soldetpe n exsite pas\n\n");
			return result;
		}
	}

	@Transactional
	public void save(Etatcheque c) {
		em.persist(c);
	}
	
	@Transactional
	public Etatcheque getmaxcode() {
		List<Etatcheque> result = em.createQuery("SELECT a FROM Etatcheque a", Etatcheque.class).getResultList();
		if (result.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return result.get(0);
		} else {
			System.out.println("\n\nl  objet Soldetpe n exsite pas\n\n");
			return null;
		}
	}

	@Transactional
	public Etatcheque getSoldetpebyid(Integer id) {
		return em.find(Etatcheque.class, id);
	}

	@Transactional
	public void update(Etatcheque c) {
		Etatcheque a = em.find(Etatcheque.class, c.getId());
		a.setSoldenet(c.getSoldenet());
		a.setTotalsoldejazira(c.getTotalsoldejazira());
		a.setTotalcrediteurzitouna(c.getTotalcrediteurzitouna());
		a.setTotalsoldebiat(c.getTotalsoldebiat());
		em.merge(a);
	}
}
