package com.tn.shell.ui.gestat;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.tn.shell.model.banque.Banque;
import com.tn.shell.model.gestat.*;
import com.tn.shell.model.shop.Produit;
import com.tn.shell.model.transport.Vhecule;
import com.tn.shell.service.banque.ServiceBanque;
import com.tn.shell.service.gestat.*;
import com.tn.shell.service.shop.ServiceProduit;
import com.tn.shell.service.transport.ServiceVhecule;

 

 

@ManagedBean(name = "LoginBean")
@SessionScoped
public class LoginBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	private static final String SAVED_REQUEST_KEY = "SPRING_SECURITY_SAVED_REQUEST";
	private String userName;
	private String password;
	private Utilisateur user;
	private List<Utilisateur> listuser;
	private EProfil[] profils;
	private EProfil profil;

	private String logine;
	private String nom;

	private String motdepass;
 
	@ManagedProperty(value = "#{authenticationManager}")
	private AuthenticationManager authenticationManager = null;

	@ManagedProperty(value = "#{UserService}")
	UserService userService;
	@ManagedProperty(value = "#{ServiceRole}")
	ServiceRole servicerole;
	@ManagedProperty(value = "#{ServiceBanque}")
	ServiceBanque serviceBanque;
	private List<Banque> listBanque;
	private List<Produit> listProduit;
	@ManagedProperty(value = "#{ServiceProduit}")
	ServiceProduit serviceProduit;

	public String getallUser() {
		listuser = new ArrayList<Utilisateur>();
		listuser = userService.getUsers();
		profils = EProfil.values();
		logine=null;motdepass=null;nom=null;
		return SUCCESS;
	}

	public String saveuser() {
		user = userService.getUser(logine);
		if (user == null) {
			Role r=servicerole.getRolebyprifil(profil);
			if(r==null) {
				r=new Role();
				r.setRole(profil);
				servicerole.save(r);
			}
			user = new Utilisateur();
			user.setLogin(logine);
			user.setMotdepasse(motdepass);
			user.setNom(nom);			
			user.setRole(r);
			userService.addUser(user);
		} else {
			String message = "user existe deja";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
		}
		listuser = new ArrayList<Utilisateur>();
		listuser = userService.getUsers();
		return SUCCESS;
	}

	public String deleteuser() {
		userService.deleteUser(user);
		listuser = new ArrayList<Utilisateur>();
		listuser = userService.getUsers();
		return SUCCESS;
	}

	public void onRowEdit1(RowEditEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "utilisateur change",
				((Utilisateur) event.getObject()).getLogin());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		((Utilisateur) event.getObject()).setRole(servicerole.getRolebyprifil(profil));
		updateUtilisateur((Utilisateur) event.getObject());
	}

	public String updateUtilisateur(Utilisateur u) {
		try {
			Utilisateur u2 = userService.getUser(u.getLogin());
			if (u2 == null) {
				userService.updateUser(u);
				return SUCCESS;
			}
		} catch (DataAccessException e) {
		}
		return ERROR;
	}

	public String nuveauUser() {

		return SUCCESS;
	}
	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	public String login() {
		try {
		 /* Utilisateur u = new Utilisateur();
			u.setLogin("shell");
			u.setMotdepasse("123");
			Role r = new Role();
			r.setRole(EProfil.Admin);
			servicerole.
			save(r);
			u.setRole(r);
			userService.addUser(u);  
*/
			
		 
			authenticateCurrentUser();
              
		} catch (AuthenticationException e) {
			String message = "Login ou mot de passe incorrecte";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
			return "incorrect";
		}

		return resolvePostLoginTarget("/index.xhtml");
	}
	
	
	
	
	
	public String logintoshop() {
		try {
		 /* Utilisateur u = new Utilisateur();
			u.setLogin("shell");
			u.setMotdepasse("123");
			Role r = new Role();
			r.setRole(EProfil.Admin);
			servicerole.
			save(r);
			u.setRole(r);
			userService.addUser(u);  
*/
			authenticateCurrentUser();

		} catch (AuthenticationException e) {
			String message = "Login ou mot de passe incorrecte";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
			return "incorrect";
		}
		listProduit=new ArrayList<Produit>();
		listProduit=serviceProduit.getAllQtenegatif();
	/*	if(listProduit.size()>0 ) {			 
		      SoundService  s=new SoundService();		      
		      URL res = getClass().getClassLoader().getResource("AlarmeLS.wav");
		      File file=null;
			try {
				file = Paths.get(res.toURI()).toFile();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
			}
		      String absolutePath = file.getAbsolutePath();
		      s.sound(absolutePath);
		} */
		return resolvePostLoginTarget("/SHELL-shop/index.xhtml");
	}
	
	
	public String logintobanque() {
		try {
		  
			authenticateCurrentUser();

		} catch (AuthenticationException e) {
			String message = "Login ou mot de passe incorrecte";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
			return "incorrect";
		}
		listBanque=new ArrayList<Banque>();
		listBanque=serviceBanque.getAll();
		return resolvePostLoginTarget("/SHELL-banque/index.xhtml");
	}
	
	
	public String logintopaie() {
		try {
	 
			authenticateCurrentUser();

		} catch (AuthenticationException e) {
			String message = "Login ou mot de passe incorrecte";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
			return "incorrect";
		}

		return resolvePostLoginTarget("/shellpaie/index.xhtml");
	}

	private void authenticateCurrentUser() {
		Md5 var = new Md5(this.getPassword());
		Authentication request = new UsernamePasswordAuthenticationToken(this.getUserName(), var.getCode());
		Authentication result = authenticationManager.authenticate(request);
		SecurityContextHolder.getContext().setAuthentication(result);
		user = userService.getCurrentUser();
	}

	private String resolvePostLoginTarget(String defaultViewId) {
		String target = defaultViewId;
		FacesContext context = FacesContext.getCurrentInstance();
		if (context != null) {
			ExternalContext externalContext = context.getExternalContext();
			Object savedRequest = externalContext.getSessionMap().remove(SAVED_REQUEST_KEY);
			if (savedRequest instanceof SavedRequest) {
				String requestedTarget = extractAppRelativePath(((SavedRequest) savedRequest).getRedirectUrl(), externalContext);
				if (requestedTarget != null && requestedTarget.trim().length() > 0) {
					target = normalizeLoginTarget(requestedTarget, defaultViewId);
				}
			}
		}
		return target + "?faces-redirect=true";
	}

	private String extractAppRelativePath(String redirectUrl, ExternalContext externalContext) {
		if (redirectUrl == null || redirectUrl.trim().isEmpty()) {
			return null;
		}
		String contextPath = externalContext.getRequestContextPath();
		int contextIndex = redirectUrl.indexOf(contextPath);
		if (contextIndex >= 0) {
			String path = redirectUrl.substring(contextIndex + contextPath.length());
			int queryIndex = path.indexOf('?');
			if (queryIndex >= 0) {
				path = path.substring(0, queryIndex);
			}
			int sessionIndex = path.indexOf(";jsessionid=");
			if (sessionIndex >= 0) {
				path = path.substring(0, sessionIndex);
			}
			return path;
		}
		return null;
	}

	private String normalizeLoginTarget(String requestedTarget, String defaultViewId) {
		if (requestedTarget == null || requestedTarget.trim().isEmpty()) {
			return defaultViewId;
		}
		if ("/unsecure/login.xhtml".equals(requestedTarget) || "/unsecure/index.xhtml".equals(requestedTarget)) {
			return defaultViewId;
		}
		if ("/SHELL-shop/unsecure/login.xhtml".equals(requestedTarget)
				|| "/SHELL-shop/unsecure/index.xhtml".equals(requestedTarget)) {
			return "/SHELL-shop/index.xhtml";
		}
		if ("/SHELL-banque/unsecure/login.xhtml".equals(requestedTarget)
				|| "/SHELL-banque/unsecure/index.xhtml".equals(requestedTarget)) {
			return "/SHELL-banque/index.xhtml";
		}
		if ("/shellpaie/unsecure/login.xhtml".equals(requestedTarget)
				|| "/shellpaie/unsecure/index.xhtml".equals(requestedTarget)) {
			return "/shellpaie/index.xhtml";
		}
		return requestedTarget;
	}

	

	public String cancel() {
		return null;
	}

	public String logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
		SecurityContextHolder.clearContext();
		FacesContext context = FacesContext.getCurrentInstance();
		if (context != null && context.getExternalContext() != null) {
			context.getExternalContext().invalidateSession();
		}
		user = null;
		userName = null;
		password = null;
		listBanque = null;
		listProduit = null;
		return "loggedout";
	}

	public String demarrer() {

		return "demarrer";
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Utilisateur getUser() {

		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	 

	public ServiceRole getServicerole() {
		return servicerole;
	}

	public void setServicerole(ServiceRole servicerole) {
		this.servicerole = servicerole;
	}

	public List<Utilisateur> getListuser() {
		return listuser;
	}

	public void setListuser(List<Utilisateur> listuser) {
		this.listuser = listuser;
	}

	public EProfil[] getProfils() {
		return profils;
	}

	public void setProfils(EProfil[] profils) {
		this.profils = profils;
	}

	public EProfil getProfil() {
		return profil;
	}

	public void setProfil(EProfil profil) {
		this.profil = profil;
	}

	public String getLogine() {
		return logine;
	}

	public void setLogine(String logine) {
		this.logine = logine;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMotdepass() {
		return motdepass;
	}

	public void setMotdepass(String motdepass) {
		this.motdepass = motdepass;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static String getSuccess() {
		return SUCCESS;
	}

	public static String getError() {
		return ERROR;
	}

	public List<Produit> getListProduit() {
		return listProduit;
	}

	public void setListProduit(List<Produit> listProduit) {
		this.listProduit = listProduit;
	}

	public ServiceProduit getServiceProduit() {
		return serviceProduit;
	}

	public void setServiceProduit(ServiceProduit serviceProduit) {
		this.serviceProduit = serviceProduit;
	}

	public ServiceBanque getServiceBanque() {
		return serviceBanque;
	}

	public void setServiceBanque(ServiceBanque serviceBanque) {
		this.serviceBanque = serviceBanque;
	}

	public List<Banque> getListBanque() {
		return listBanque;
	}

	public void setListBanque(List<Banque> listBanque) {
		this.listBanque = listBanque;
	}

	 
	
	

}
