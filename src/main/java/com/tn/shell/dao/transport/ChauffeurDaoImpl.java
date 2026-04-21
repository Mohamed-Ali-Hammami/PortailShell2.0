package com.tn.shell.dao.transport;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tn.shell.model.transport.*;
 
@Repository
public class ChauffeurDaoImpl implements ChauffeurDao {
	@PersistenceContext
	 private EntityManager em;
	private static final String ACTIVE_STATUS_SQL = "(f.statut is null or trim(f.statut) = '' or lower(trim(f.statut)) = 'actif')";
	 
	 @Transactional
	public void save(Chauffeur Chauffeur) {
	em.persist(Chauffeur);
		
	}
	 @Transactional
	 public List<String> getAllnom(){
		 List<String> l=new ArrayList<String>();
		 List<Chauffeur> result = getAll();
		if(result!=null) for(Chauffeur c:result) l.add(c.getNompenom());
		    return l  ;
	 }
	@Transactional
	public List<Chauffeur> getAll() {
		try {
			List<Chauffeur> result = em.createQuery("SELECT c FROM Chauffeur c  where c.statut = :statut", Chauffeur.class)
					.setParameter("statut", Statut.ACTIF)
					.getResultList();
			if (result != null && !result.isEmpty()) {
				return result;
			}
		} catch (RuntimeException ex) {
		}
		List<Object[]> rows = em.createNativeQuery(
				"SELECT id,nompenom,cin,numtel,salaire,statut "
						+ "FROM chauffeur WHERE " + ACTIVE_STATUS_SQL + " ORDER BY CAST(id AS UNSIGNED) DESC")
				.getResultList();
		List<Chauffeur> chauffeurs = new ArrayList<Chauffeur>();
		for (Object[] row : rows) {
			Chauffeur chauffeur = new Chauffeur();
			chauffeur.setId(TransportTsvMapper.asInteger(row[0]));
			chauffeur.setNompenom(TransportTsvMapper.asString(row[1]));
			chauffeur.setCin(TransportTsvMapper.asString(row[2]));
			chauffeur.setNumtel(TransportTsvMapper.asString(row[3]));
			chauffeur.setSalaire(TransportTsvMapper.asDouble(row[4]));
			chauffeur.setStatut(TransportTsvMapper.asStatut(row[5]));
			chauffeurs.add(chauffeur);
		}
		return chauffeurs;
	}
	 @Transactional
	public Chauffeur Findbynom(String nom) {
		 List<Chauffeur> ChauffeurListem=em.createQuery("SELECT c FROM  Chauffeur c where c.nompenom = :nom and c.statut = :statut",Chauffeur.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
	        
	        if (ChauffeurListem.size() > 0){
	            return ChauffeurListem.get(0);}
	        else{
	            return null;}   
	}
	 
	 @Transactional
		public Chauffeur Findbycode(Integer nom) {
			 List<Chauffeur> ChauffeurListem=em.createQuery("SELECT c FROM  Chauffeur c where c.nom = :nom and c.statut = :statut",Chauffeur.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
		        
		        if (ChauffeurListem.size() > 0){
		            return ChauffeurListem.get(0);}
		        else{
		            return null;}   
		}
	 
	 @Transactional
	public Chauffeur Findbymf(String nom) {
		 List<Chauffeur> ChauffeurListem=em.createQuery("SELECT c FROM  Chauffeur c where c.matriculefiscal = :nom and c.statut = :statut",Chauffeur.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
	        
	        if (ChauffeurListem.size() > 0){
	            return ChauffeurListem.get(0);}
	        else{
	            return null;}   
	}
	 @Transactional
	public void update(Chauffeur chauffeur) {
		Chauffeur c=em.find(Chauffeur.class, chauffeur.getId());
		 
		  
		c.setNompenom(chauffeur.getNompenom());
		c.setNumtel(chauffeur.getNumtel());
		em.merge(c);
	}
	 @Transactional
	public void delete(Chauffeur chauffeur) {
		 Chauffeur c=em.find(Chauffeur.class, chauffeur.getId());
		 c.setStatut(chauffeur.getStatut());
		 em.merge(c);
		
	}

}
