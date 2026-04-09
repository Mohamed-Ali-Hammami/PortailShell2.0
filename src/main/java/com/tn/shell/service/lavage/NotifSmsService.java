package com.tn.shell.service.lavage;

import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tn.shell.dao.lavage.NotifSmsingDAO;
import com.tn.shell.dao.lavage.NotifSmsingDAO;
import com.tn.shell.model.lavage.NotifSmsing;
 

@Service("NotifSmsService")
public class NotifSmsService {
	
	@Autowired
	NotifSmsingDAO NotifSmsingDAO;
	public NotifSmsing getNotifSmsingbyNom(String nom) {
		return NotifSmsingDAO.getNotifSmsingbyNom(nom);
	}
	
	public BigInteger getmaxcode() {
		return NotifSmsingDAO.getmaxcode();
	}
	public List<NotifSmsing> getAll(){
		return NotifSmsingDAO.getAll();
	}
	public void save(NotifSmsing c) {
		NotifSmsingDAO.save(c);
	}
	 
	public void update(NotifSmsing c) {
		NotifSmsingDAO.update(c);
	}
	public NotifSmsing getNotifSmsingbyid(Integer id) {
		return NotifSmsingDAO.getNotifSmsingbyid(id);
	}
	 
}
