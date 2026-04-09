package com.tn.shell.dao.gestat;

import java.util.List;

import com.tn.shell.model.gestat.*;
 
 
 

public interface RoleDAO {
	 
    public Role getRole(int id);
    public List<Role> getRoles() ;
    public void save(Role r);
    public Role getRolebyprifil(EProfil e);
 
}
