package  com.tn.shell.service.shop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tn.shell.dao.shop.*;
import com.tn.shell.model.shop.*;

 
@Service("ServiceFactureAchat")
public class ServiceFactureAchat {
@Autowired
FactureAchatDAO factureAchatDAO;
 
public void save(Factureachat facture){
	factureAchatDAO.save(facture);
}
public List<Factureachat> getfacturebydate(Date d1, Date d2) {
	return factureAchatDAO.getfacturebydate(d1, d2);
}
public List<Factureachat> getAll(){
	List<Factureachat> l=factureAchatDAO.getAll();
	
	
	return l;
}
public void update(Factureachat facture){
	factureAchatDAO.update(facture);
	
}


public void delete(Factureachat facture){
	factureAchatDAO.delete(facture);
}
public Factureachat getbycode(String code){
	return factureAchatDAO.getbycode(code);
}
}
