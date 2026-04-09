package  com.tn.shell.service.shop;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 

import com.tn.shell.dao.shop.*;
import com.tn.shell.model.shop.*;


 

@Service("ServiceLigneAlimentation")
public class ServiceLigneAlimentation {
	@Autowired
	LigneAlimentaiondao ligneAlimentaiondao;

	public void save(Lignealimentation typearticle) {
		ligneAlimentaiondao.save(typearticle);
	} 
	public  List<Lignealimentation> getAll(){
		return ligneAlimentaiondao.getAll();
	}
	
	public double getlisttransferbydateandproduit2(Date d,Date d2, Produit p) {
		return ligneAlimentaiondao.getlisttransferbydateandproduit2(d, d2, p);
	}
	
	public List<Lignealimentation> getLignebyachat(Achat achat){
		return ligneAlimentaiondao.getLignebyachat(achat);
	}
	 public double getlisttransferbydateandproduit(String d,Produit p){
		 return ligneAlimentaiondao.getlisttransferbydateandproduit(d, p);
	 }
	 
	 public List<Lignealimentation> getbetwendates(Date d1,Date d2,Produit p){
		 return ligneAlimentaiondao.getbetwendates(d1, d2, p);
	 }
	 
	public void update(Lignealimentation typearticle) {
		ligneAlimentaiondao.update(typearticle);
	} 
	public void delete(Lignealimentation typearticle) {
		ligneAlimentaiondao.delete(typearticle);
		
	}
	public double getchiffreAffaireAchat(Famillearticle e) {
		return ligneAlimentaiondao.getchiffreAffaireAchat(e);
	}
	public double gettvaAchat(Famillearticle e) {
		return ligneAlimentaiondao.gettvaAchat(e);
	}
}
