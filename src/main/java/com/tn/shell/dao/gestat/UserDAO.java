package com.tn.shell.dao.gestat;

import java.util.List;

import com.tn.shell.model.gestat.*;

 


 




/**
 * 
 * Utilisateur DAO Interface
 * 
 * @author onlinetechvision.com
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */
public interface UserDAO {
	
	public List<Utilisateur> findbyProfil(EProfil E);
	
	public Utilisateur getUtilisateur(String login);

	/**
	 * Add Utilisateur
	 * 
	 * @param  Utilisateur Utilisateur
	 */
	public void addUtilisateur(Utilisateur Utilisateur);
	
	/**
	 * Update Utilisateur
	 * 
	 * @param  Utilisateur Utilisateur
	 */
	public void updateUtilisateur(Utilisateur Utilisateur);
	
	/**
	 * Delete Utilisateur
	 * 
	 * @param  Utilisateur Utilisateur
	 */
	public void deleteUtilisateur(Utilisateur Utilisateur);
	
	/**
	 * Get Utilisateur
	 * 
	 * @param  int Utilisateur Id
	 */
	public Utilisateur getUtilisateurById(int id);
	
	/**
	 * Get Utilisateur List
	 * 
	 */
	public List<Utilisateur> getUtilisateurs();
	public List<Utilisateur>  getUtilisateurByIdAndstatut(int id);
	public List<Utilisateur> findbyProfil1(EProfil E);
}
