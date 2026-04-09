package com.tn.shell.dao.gestat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.*;
 
 


@Repository
public class RoleDAOImpl implements RoleDAO {
   

	 @PersistenceContext
	 private EntityManager em;
	 
    @Transactional
    public Role getRole(int id) {
       Role Rolees = (Role)  em.find(Role.class, id);
        return Rolees;
    	 
    }
    @Transactional
	public List<Role> getRoles() {
    	 List list = em.createQuery("SELECT r from Role r",Role.class).getResultList();
	        return list;
	}
	public void save(Role r) {
		em.persist(r);
		
	}
	public Role getRolebyprifil(EProfil e) {
		 List<Role> list = em.createQuery("SELECT r from Role r where r.role= :role",Role.class).setParameter("role", e).getResultList();
		 if (list.size() > 0){
	        	 
	            return list.get(0);}
	        else{
	        	System.out.println("\n\nl  objet client n exsite pas\n\n");
	            return null;}  
		 
	}

}
