package com.tn.shell.service.gestat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*;
import com.tn.shell.model.gestat.Poste;
 
@Service("ServiceRole")
public class ServiceRole {
	@Autowired
	RoleDAO roleDAO;
	
	@Transactional
	public Role getRole(int id){
		return roleDAO.getRole(id);
	}
	@Transactional
    public List<Role> getRoles() {
    	return roleDAO.getRoles();
    }
	@Transactional
    public void save(Role r){
    	roleDAO.save(r);
    }
	@Transactional
    public Role getRolebyprifil(EProfil e){
    	return roleDAO.getRolebyprifil(e);
    }
}
