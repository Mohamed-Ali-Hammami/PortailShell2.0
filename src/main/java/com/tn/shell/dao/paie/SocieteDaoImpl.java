package com.tn.shell.dao.paie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.paie.*;
@Repository
public class SocieteDaoImpl implements SocieteDAO{
	@PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Societe s) {
		em.persist(s);
		
	}
	 @Transactional
	public void update(Societe s) {
		Societe ss=em.find(Societe.class, s.getId());
		ss.setAddress(s.getAddress());
		ss.setBanque(s.getBanque());
		ss.setLibelle(s.getLibelle());
		ss.setCodetva(s.getCodetva());
		ss.setTel1(s.getTel1());
		ss.setTel2(s.getTel2());
		ss.setMatricuefiscale(s.getMatricuefiscale());
		ss.setNumcompte(s.getNumcompte());
		em.merge(ss);
	}

	public List<Societe> getAll() {
		List<Societe> result = em.createQuery("SELECT a FROM Societe a", Societe.class).getResultList();
	    return result;
		}

}
