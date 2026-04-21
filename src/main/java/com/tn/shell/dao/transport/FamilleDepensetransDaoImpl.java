package com.tn.shell.dao.transport;

import java.util.ArrayList;
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
	private static final String ACTIVE_STATUS_SQL = "(f.statut is null or trim(f.statut) = '' or lower(trim(f.statut)) = 'actif')";
	 
	 @Transactional
	public void save(Familledepensetransport familledepense) {
		em.persist(familledepense);
		
	}
@Transactional
	public List<Familledepensetransport> getAll() {
		try {
			List<Familledepensetransport> result = em.createQuery(
					"SELECT a FROM Familledepensetransport a where a.statut = :statut order by a.id Desc",
					Familledepensetransport.class)
					.setParameter("statut", Statut.ACTIF)
					.getResultList();
			if (result != null && !result.isEmpty()) {
				return result;
			}
		} catch (RuntimeException ex) {
		}
		List<Object[]> rows = em.createNativeQuery(
				"SELECT id,libelle,statut FROM familledepensetransport WHERE " + ACTIVE_STATUS_SQL
						+ " ORDER BY CAST(id AS UNSIGNED) DESC")
				.getResultList();
		List<Familledepensetransport> familles = new ArrayList<Familledepensetransport>();
		for (Object[] row : rows) {
			Familledepensetransport famille = new Familledepensetransport();
			famille.setId(TransportTsvMapper.asInteger(row[0]));
			famille.setLibelle(TransportTsvMapper.asString(row[1]));
			famille.setStatut(TransportTsvMapper.asStatut(row[2]));
			familles.add(famille);
		}
		return familles;
	}

public Familledepensetransport getFamilebyeibelle(String libelle){
	 List<Familledepensetransport> VheculeListem=em.createQuery("SELECT c FROM  Familledepensetransport c where c.libelle = :nom and c.statut = :statut",Familledepensetransport.class).setParameter("statut", Statut.ACTIF).setParameter("nom", libelle).getResultList();
     
     if (VheculeListem.size() > 0){
         return VheculeListem.get(0);}
     else{
         return null;}   
}

}
