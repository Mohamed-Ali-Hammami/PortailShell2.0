package com.tn.shell.dao.banque;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.banque.Compte;
import com.tn.shell.model.gestat.*;

@Repository
public class CompteDaoImpl implements CompteDao {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Compte Compte) {
		em.persist(Compte);

	}

	@Transactional
	public List<Compte> getAll() {
		List<Compte> result = em.createQuery("SELECT c FROM Compte c where c.statut = :statut ", Compte.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public Compte Findbynom(String nom) {
		List<Compte> CompteListem = em
				.createQuery("SELECT c FROM  Compte c where c.numerodecompte = :nom and c.statut = :statut",
						Compte.class)
				.setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();

		if (CompteListem.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return CompteListem.get(0);
		} else {
			System.out.println("\n\nl  objet Compte n exsite pas\n\n");
			return null;
		}
	}

	@Transactional
	public Compte Findbycode(Integer nom) {
		List<Compte> CompteListem = em
				.createQuery("SELECT c FROM  Compte c where c.id = :nom and c.statut = :statut", Compte.class)
				.setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();

		if (CompteListem.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return CompteListem.get(0);
		} else {
			System.out.println("\n\nl  objet Compte n exsite pas\n\n");
			return null;
		}
	}

	@Transactional
	public void update(Compte d) {
		System.out.println("\n\n\n update");
		Compte c = em.find(Compte.class, d.getId());
		c=d;
		em.merge(c);
		System.out.println("\n\n\n apres update" + c.getSolde());
	}

	@Transactional
	public void delete(Compte Compte) {
		Compte c = em.find(Compte.class, Compte.getId());

		c.setStatut(Statut.DEACTIF);
		em.merge(c);

	}

}
