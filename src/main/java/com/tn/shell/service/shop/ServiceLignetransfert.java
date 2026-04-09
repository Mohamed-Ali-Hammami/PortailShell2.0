package  com.tn.shell.service.shop;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 

import com.tn.shell.dao.shop.*;
import com.tn.shell.model.shop.*;


 

@Service("ServiceLignetransfert")
public class ServiceLignetransfert {
	@Autowired
	LigneTransfertdao ligneTransfertdao;

	public void save(Lignetransert typearticle) {
		ligneTransfertdao.save(typearticle);
	} 
	public  List<Lignetransert> getAll(){
		return ligneTransfertdao.getAll();
	} 
	public List<Lignetransert> getlisttransferbydateandproduit(String d,Produit p){
		return ligneTransfertdao.getlisttransferbydateandproduit(d, p);
	}
	 
	public void update(Lignetransert typearticle) {
		ligneTransfertdao.update(typearticle);
	} 
	public void delete(Lignetransert typearticle) {
		ligneTransfertdao.delete(typearticle);
		
	}
	 public List<Lignetransert> getAllbydate(String d){
		 return  ligneTransfertdao.getAllbydate(d);
	 }
	 public List<Lignetransert> getAllbydatemoins(String d){
		 return ligneTransfertdao.getAllbydatemoins(d);
	 }
	 
}
