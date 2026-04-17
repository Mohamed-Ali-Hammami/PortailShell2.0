package com.tn.shell.dao.gestat;

 
import java.util.List; 

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext; 

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.*;
import com.tn.shell.model.gestat.Utilisateur.UserStatut;

 


 
 

/**
 * 
 * Utilisateur DAO
 * 
 * @author ibtissem
 *  
 *
 */
@Repository
public class UserDaoImpl implements UserDAO {
	
	

	 @PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	    public Utilisateur getUtilisateur(String login) {
	    
	        List<Utilisateur> UtilisateurListem=em.createQuery("SELECT u FROM  Utilisateur u where u.login = :login",Utilisateur.class).setParameter("login", login).getResultList();
	        
	        if (UtilisateurListem.size() > 0){
	            return UtilisateurListem.get(0);}
	        else{
	            return null;}   
	    } 
	 
	 
	/**
	 * Add Utilisateur
	 * 
	 * @param  Utilisateur Utilisateur
	 */
	 
	 @Transactional
	  public List<Utilisateur> getAll() {
	    List<Utilisateur> result = em.createQuery("SELECT p FROM Utilisateur p  where p.statut = :statut", Utilisateur.class).setParameter("statut", Statut.ACTIF).getResultList();
	    return result;
	  }
	   
	   
	  @Transactional
	public void addUtilisateur(Utilisateur Utilisateur) {
		em.persist(Utilisateur);
		
	}
	  /**
		 * Update Utilisateur
		 * 
		 * @param  Utilisateur Utilisateur
		 */
		  
		@Transactional
	public void updateUtilisateur(Utilisateur utilisateur) {
		 Utilisateur u=em.find(Utilisateur.class, utilisateur.getLogin());		 
		u.setNom(utilisateur.getNom());		 
		u.setLogin(utilisateur.getLogin());		 
		u.setMotdepasse(utilisateur.getMotdepasse());		 
		u.setRole(utilisateur.getRole()); 
		em.merge(utilisateur);
	}

	/**
	 * Delete Utilisateur
	 * 
	 * @param  Utilisateur Utilisateur
	 */
	@Transactional
	public void deleteUtilisateur(Utilisateur utilisateur) {
		Utilisateur t=em.find(Utilisateur.class, utilisateur.getLogin());
		t.setStatut(UserStatut.DEACTIF);
		em.merge(t);
		
	}
	/**
	 * Get Utilisateur
	 * 
	 * @param  int Utilisateur Id
	 * @return Utilisateur 
	 */

	public Utilisateur getUtilisateurById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get Utilisateur List
	 * 
	 * @return List - Utilisateur list
	 */
	 
    @Transactional
	public List<Utilisateur> getUtilisateurs() {
		List<Utilisateur> result = em.createQuery("SELECT p FROM Utilisateur p", Utilisateur.class).getResultList();
	    return result;
	}


	public List<Utilisateur> getUtilisateurByIdAndstatut(int id) {
		// users = User.find("byBoutique.idAndStatut",boutique.id,UserStatut.ACTIF).fetch();
		 List<Utilisateur> UtilisateurListem=em.createQuery("SELECT u FROM  Utilisateur u where u.boutique.id =:id AND u.statut =:statut",Utilisateur.class).setParameter("id", id).setParameter("statut", Utilisateur.UserStatut.ACTIF).getResultList();
	        
	        if (UtilisateurListem.size() > 0){
	            return UtilisateurListem;}
	        else{
	            return null;}   
	}


	public List<Utilisateur> findbyProfil(EProfil E) {
		//  users = User.find("select u from User u where u.profil=? and u.statut=? ", p, UserStatut.ACTIF).fetch();
		 List<Utilisateur> UtilisateurListem=em.createQuery("SELECT u FROM  Utilisateur u where u.role.role =:profil  AND u.statut =:statut",Utilisateur.class).setParameter("profil", E).setParameter("statut", Utilisateur.UserStatut.ACTIF).getResultList();
	        
	        if (UtilisateurListem.size() > 0){
	            return UtilisateurListem;}
	        else{
	            return null;}   
	}
    
	public List<Utilisateur> findbyProfil1(EProfil E) {
		 
		 List<Utilisateur> UtilisateurListem=em.createQuery("SELECT u FROM  Utilisateur u where u.role.role =:profil ",Utilisateur.class).setParameter("profil", E).getResultList();
	        
	        if (UtilisateurListem.size() > 0){
	            return UtilisateurListem;}
	        else{
	            return null;}   
	}
}
