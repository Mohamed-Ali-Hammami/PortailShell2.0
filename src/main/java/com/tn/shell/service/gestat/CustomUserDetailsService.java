package com.tn.shell.service.gestat;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.dao.gestat.UserDAO;
 
import com.tn.shell.model.gestat.*;
 
 
@Service
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService {
   
    @Autowired
    private UserDAO userDAO;   

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
       
    	com.tn.shell.model.gestat.Utilisateur domainUser = userDAO.getUtilisateur(login);
       
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new User(
                domainUser.getLogin(),
                domainUser.getMotdepasse() ,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAuthorities(domainUser.getRole().getId())   
            );
    }
   
    public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
        return authList;
    }
   
    public List<String> getRoles(Integer role) {

        List<String> roles = new ArrayList<String>();
        //ServiceClient,Entrepot
        if (role.intValue() == 1) {
            roles.add(EProfil.Admin+"");
           // roles.add("ROLE_ADMIN");
        } else if (role.intValue() == 2) {
            roles.add(EProfil.Caissier+"");
        }
       
       
       
        return roles;
    }
   
    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));            
        }
        return authorities;
    }

}
