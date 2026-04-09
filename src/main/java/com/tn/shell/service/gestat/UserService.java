package com.tn.shell.service.gestat;

import java.util.List;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.dao.gestat.UserDAO;
import com.tn.shell.model.gestat.EProfil;
import com.tn.shell.model.gestat.Utilisateur;
 
 
 
 

 

/**
 * 
 * User Service
 * 
 * @author MOUSSI Ibtissem
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */

@Service("UserService")
public class UserService   {

	// UserDAO is injected...
	@Autowired
	UserDAO userDAO;
	
	
	
	/**
	 * getCurrentUser
	 * @param 
	 * */
	 
	 public Utilisateur getCurrentUser() {
	        SecurityContext securityContext = SecurityContextHolder.getContext();
	        org.springframework.security.core.userdetails.User springSecurityUser = ( org.springframework.security.core.userdetails.User) securityContext.getAuthentication().getPrincipal();
	        return this.userDAO.getUtilisateur(springSecurityUser.getUsername());
	    }
 
	 
	 
	/**
	 * Add User
	 * 
	 * @param  User user
	 */
	@Transactional(readOnly = false)
	 
	public void addUser(Utilisateur user) {
		getUserDAO().addUtilisateur(user);
	}

	/**
	 * Delete User
	 * 
	 * @param  User user
	 */
	@Transactional(readOnly = false)
	 
	public void deleteUser(Utilisateur user) {
		getUserDAO().deleteUtilisateur(user);
	}
	
	/**
	 * Update User
	 * 
	 * @param  User user
	 */
	@Transactional(readOnly = false)
	 
	public void updateUser(Utilisateur user) {
		getUserDAO().updateUtilisateur(user);
	}
	
	/**
	 * Get User
	 * 
	 * @param  int User Id
	 */
	 
	public Utilisateur getUserById(int id) {
		return getUserDAO().getUtilisateurById(id);
	}

	/**
	 * Get User List
	 * 
	 */
	 
	public List<Utilisateur> getUsers() {	
		return getUserDAO().getUtilisateurs();
	}

	
	
	 public Utilisateur getUser(String login) 
	    {
		return userDAO.getUtilisateur(login);
		}
	/**
	 * Get User DAO
	 * 
	 * @return IUserDAO - User DAO
	 */
	public UserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * Set User DAO
	 * 
	 * @param UserDAO - User DAO
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public List<Utilisateur> getUtilisateurByIdAndstatut(int id){
		return userDAO.getUtilisateurByIdAndstatut(id);
	}
	 
	public List<Utilisateur> findbyProfil1(EProfil E){
		return userDAO.findbyProfil1(E);
	}
	public void addUtilisateur(Utilisateur Utilisateur){
		 userDAO.addUtilisateur(Utilisateur);
	}
	
}
