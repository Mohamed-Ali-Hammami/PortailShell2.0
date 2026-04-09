package com.tn.shell.dao.transport;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.transport.*;
 
@Repository
public class  FamilleDepensetransDaoImpl implements FamilleDepensetransDAO {
	@PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Familledepensetransport familledepense) {
		em.persist(familledepense);
		
	}
@Transactional
	public List<Familledepensetransport> getAll() {
	List<Familledepensetransport> result = em.createQuery("SELECT a FROM Familledepensetransport a order by a.id Desc", Familledepensetransport.class).getResultList();
    return result;
	}

public Familledepensetransport getFamilebyeibelle(String libelle){
	 List<Familledepensetransport> VheculeListem=em.createQuery("SELECT c FROM  Familledepensetransport c where c.libelle = :nom and c.statut = :statut",Familledepensetransport.class).setParameter("statut", Statut.ACTIF).setParameter("nom", libelle).getResultList();
     
     if (VheculeListem.size() > 0){
     	System.out.println("objet trouvé "+VheculeListem.get(0).getId()+"\n\n\n");
         return VheculeListem.get(0);}
     else{
     	System.out.println("\n\nl  objet Vhecule n exsite pas\n\n");
         return null;}   
}

}
