package com.tn.shell.ui.gestat;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
 
 
@ManagedBean(name = "HotkeyView")
@SessionScoped
public class HotkeyView {     
    private String text;
    private String username;
    private String email;
    
 
    public String getText() {
        return text;
    }
 
    public void setText(String text) {
        this.text = text;
    }
     
    public void save() {
        FacesContext.getCurrentInstance().addMessage(text, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "raccourcis clavier"));
    } 
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
     
    public void save2() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "User Saved"));
        username = null;
        email = null;
    }
}
