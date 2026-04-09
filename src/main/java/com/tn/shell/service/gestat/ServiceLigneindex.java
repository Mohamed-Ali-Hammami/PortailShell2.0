package com.tn.shell.service.gestat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*;
import com.tn.shell.model.shop.Produit;
 

 
 

@Service("ServiceLigneindex")
public class ServiceLigneindex {
	@Autowired
	LigneindexDAO ligneTransfertdao;

	public void save(Ligneindex typearticle) {
		ligneTransfertdao.save(typearticle);
	}
	public double getAllventepardatearticle( Articlecarburant a,Date d1,Date d2){
		return ligneTransfertdao.getAllventepardatearticle(  a,  d1,  d2);
	}
	public double getquantitebyarticledates(Articlecarburant a,String d) {
		return ligneTransfertdao.getquantitebyarticledates(a,d);
	}
	public double getAllventepardatearticlequantite( Articlecarburant a,Date d1,Date d2){
		return ligneTransfertdao.getAllventepardatearticlequantite(  a,  d1,  d2);
	}
	public double getAllventepardatearticlequantitepardate( Articlecarburant a,String d1) {
		return ligneTransfertdao.getAllventepardatearticlequantitepardate(a,d1);
	}
	public List<Ligneindex> getAllventepardate(String d, Articlecarburant poste){
		return ligneTransfertdao.getAllventepardate(d, poste);
	}
	public List<Ligneindex> getAllparposte(Caisse c) {
		return ligneTransfertdao.getAllparposte(c);
	}
	public double getmaxcode(Pompe p,Caisse c) {
		return ligneTransfertdao.getmaxcode(p,c);
	}
	public double getquantitebyarticle(Articlecarburant a,Caisse c) {
		return ligneTransfertdao.getquantitebyarticle(a, c);
	}
	public List<Ligneindex> getAllventeentredate(Date d,Date d2,Articlecarburant a) {
		return ligneTransfertdao.getAllventeentredate(d, d2,a);
	}
	public Ligneindex getmaxcode() {
		return ligneTransfertdao.getmaxcode( );
	}
	public  Ligneindex  getAllventepardateandpompeandposte2(Pompe p,Caisse s){
		return ligneTransfertdao.getAllventepardateandpompeandposte2( p, s);
	}
	
	public  Ligneindex  getAllventepardateandpompeandposte(String dates1,Pompe p, Poste Poste1) {
		return ligneTransfertdao.getAllventepardateandpompeandposte(dates1, p, Poste1);
	}
	public List<Ligneindex> getAll() {
		return ligneTransfertdao.getAll();
	}
	 
	public List<Ligneindex> getAllventeparposte(String d, Poste poste) {
		return ligneTransfertdao.getAllventeparposte(d, poste);
	}
	public List<Ligneindex>  getAllventepardate(String d){
		return  ligneTransfertdao.getAllventepardate(d);
	}
	public List<Ligneindex>  getLigneindexbycreditclient(Credit credit)  {
		return ligneTransfertdao.getLigneindexbycreditclient(credit);
	}
	public void update(Ligneindex typearticle) {
		ligneTransfertdao.update(typearticle);
	}

	public void delete(Ligneindex typearticle) {
		ligneTransfertdao.delete(typearticle);

	}

	public List<Ligneindex> getAllventeparposteNegatif(String d, Poste poste) {
		return ligneTransfertdao.getAllventeparposteNegatif(d, poste);
	}
	public List<Ligneindex> getAllbyProduit(Produit p){
		return ligneTransfertdao.getAllbyProduit(p);
	}

	 
	 

}
