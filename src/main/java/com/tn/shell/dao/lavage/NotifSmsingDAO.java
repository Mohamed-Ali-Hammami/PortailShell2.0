package com.tn.shell.dao.lavage;

import java.math.BigInteger;
import java.util.List;

import com.tn.shell.model.lavage.NotifSmsing;
 

 

public interface NotifSmsingDAO {
	public List<NotifSmsing> getAll();
	public void save(NotifSmsing c);
	public BigInteger getmaxcode();
	public void update(NotifSmsing c);
	public NotifSmsing getNotifSmsingbyid(Integer id);
	public NotifSmsing getNotifSmsingbyNom(String nom); 
	 
}
