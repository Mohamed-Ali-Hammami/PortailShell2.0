package com.tn.shell.service.transport;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.transport.*;
import com.tn.shell.model.transport.*;

 

@Service("ServiceBonLivraison")
public class ServiceBonLivraison {
	@Autowired
	Bonlivraisondao bonlivraisondao;
	 public List<Bonlivraison> getBLbyfacture(Facture f){
		 return bonlivraisondao.getBLbyfacture(f);
	 }
	 public List<Chauffeur> getlistchauffeurbydate(Date d1,Date d2){
		 return bonlivraisondao.getlistchauffeurbydate(d1, d2);
				 
	 }
	 public List<Bonlivraison> getBLNonAffectee() {
		 return bonlivraisondao.getBLNonAffectee();
	 }
		public Double sumdtransportv(Date d1,Date d2,int id) {
			return bonlivraisondao.sumdtransportv(d1, d2, id);
		}
		
		
	 public List<Bonlivraison>getAllventepardatearticle(Chauffeur a,String s)
	 { return bonlivraisondao.getAllventepardatearticle(a, s);}
	 public List<Bonlivraison> getbonlivraisonbetween(Date d1,Date d2){
		 return bonlivraisondao.getbonlivraisonbetween(d1, d2);
	 }
	 public double getquantitrparclient(Integer code){
		 return bonlivraisondao.getquantitrparclient(code);
	 }
	 public double gettotaltransport(Vhecule v,Date d1,Date d2) {
		 return bonlivraisondao.gettotaltransport(v, d1, d2);
	 }
	 public List<Bonlivraison> getBLbystatutsandclient(Status s,String nom){
		 return bonlivraisondao.getBLbystatutsandclient(s, nom);
	 }
	 public double getchiifreaffaireparclient(Integer code){
		 return bonlivraisondao.getchiifreaffaireparclient(code);
	 }
	 public Integer getnbBLparclient(Integer code){
		 return bonlivraisondao.getnbBLparclient(code);
	 }
	 public List<Bonlivraison> getBLbystatuts(Status s){
		 return bonlivraisondao.getBLbystatuts(s);
	 }
	 public Integer getmaxcode() {
		 return bonlivraisondao.getmaxcode();
	 }
	 public Bonlivraison getBLbycode(Integer code){
		 return bonlivraisondao.getBLbycode(code);
	 }
	 public Bonlivraison getBLbycodes(String  code){
		 return bonlivraisondao.getBLbycodes(code) ;
	 }
	public void save(Bonlivraison c){
		bonlivraisondao.save(c);
	}
	public void update(Bonlivraison b){
		bonlivraisondao.update(b);
	} 
	public List<Bonlivraison> getAll(){
		return bonlivraisondao.getAll();
	}
	public void delete(Bonlivraison b){
		bonlivraisondao.delete(b);
	}
	 public Bonlivraison getMaxbl() {
		 return bonlivraisondao.getMaxbl();
	 }
}
