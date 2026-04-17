package com.tn.shell.dao.gestat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.*;

 
@Repository
public class  FamilleDepenseDaoImpl implements FamilleDepenseDAO {
	@PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Familledepensegestat familledepense) {
		em.persist(familledepense);
		
	}
@Transactional
	public List<Familledepensegestat> getAll() {
	List<Familledepensegestat> result = em.createQuery("SELECT a FROM Familledepensegestat a order by a.id Desc", Familledepensegestat.class).getResultList();
    return result;
	}
@Transactional
public Familledepensegestat getFamilebyeibelle(String libelle){
	 List<Familledepensegestat> VheculeListem=em.createQuery("SELECT c FROM  Familledepensegestat c where c.libelle = :nom and c.statut = :statut",Familledepensegestat.class).setParameter("statut", Statut.ACTIF).setParameter("nom", libelle).getResultList();
     
     if (VheculeListem.size() > 0){
         return VheculeListem.get(0);}
     else{
         return null;}   
}
@Transactional
public Familledepensegestat getFamilebyeid(Integer id) {
List<Familledepensegestat> VheculeListem=em.createQuery("SELECT c FROM  Familledepensegestat c where c.id = :nom and c.statut = :statut",Familledepensegestat.class).setParameter("statut", Statut.ACTIF).setParameter("nom", id).getResultList();

if (VheculeListem.size() > 0){
    return VheculeListem.get(0);}
else{
    return null;}   
}
@Transactional
public void update(Familledepensegestat typearticle) {
	 Familledepensegestat t=em.find(Familledepensegestat.class, typearticle.getId());
	t.setLibelle(typearticle.getLibelle());
	em.merge(t);
	
}
 @Transactional
public void delete(Familledepensegestat typearticle) {
	 Familledepensegestat t=em.find(Familledepensegestat.class, typearticle.getId());
	 t.setStatut(Statut.DEACTIF);
	em.merge(t);
	
}

}
