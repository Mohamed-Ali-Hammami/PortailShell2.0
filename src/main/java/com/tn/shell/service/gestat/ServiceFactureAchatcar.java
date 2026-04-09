package  com.tn.shell.service.gestat;

 
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*;
import com.tn.shell.model.shop.Fournisseur;
 

 
 
@Service("ServiceFactureAchatcar")
public class ServiceFactureAchatcar {
@Autowired
FactureAchatcarburantDAO  factureAchatDAO;
 
public void save(Factureachatcarburant facture){
	factureAchatDAO.save(facture);
}
public List<Factureachatcarburant> getfacturebydate(Date d1, Date d2) {
	return factureAchatDAO.getfacturebydate(d1, d2);
}
public List<Factureachatcarburant> getAll(){
	List<Factureachatcarburant> l=factureAchatDAO.getAll();
	 
	return l;
}
public List<Factureachatcarburant> getfacturebyfour(Fournisseur four){
	return factureAchatDAO.getfacturebyfour(four);
}
public List<Factureachatcarburant> getfacturebyStatus(Status s){
	return factureAchatDAO.getfacturebyStatus(s);
}
public void update(Factureachatcarburant facture){
	factureAchatDAO.update(facture);
	
}


public void delete(Factureachatcarburant facture){
	factureAchatDAO.delete(facture);
}
public Factureachatcarburant getbycode(String code){
	return factureAchatDAO.getbycode(code);
}
}
