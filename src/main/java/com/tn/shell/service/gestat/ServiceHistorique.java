package com.tn.shell.service.gestat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*; 
 

@Service("ServiceHistorique")
public class ServiceHistorique {
	@Autowired
	 HistoriqueDao HistoriquepayementclientDao;
	
	public Historiquepayement Findbymf(String nom){
		return HistoriquepayementclientDao.Findbymf(nom);
	}
	public Historiquepayement findbyCredit(Creditpassation c) {
		return HistoriquepayementclientDao.findbyCredit(c);
	}
	public void save(Historiquepayement Historiquepayementclient){
		HistoriquepayementclientDao.save(Historiquepayementclient);
	}
	public List<Historiquepayement> getListcreditdate(String date){
		return HistoriquepayementclientDao.getListcreditdate(date);
	}
	public  List<Historiquepayement> findbyClient(Clientpassation c){
		return HistoriquepayementclientDao.findbyClient(c);
	}
	public Historiquepayement getHistoriquepayementbyid(Integer id) {
	return 	HistoriquepayementclientDao.getcreditbyid(id);
	}
	public List<Historiquepayement> getListHistoriquepayementdateandclient(String date,Clientpassation c){
		return HistoriquepayementclientDao.getListcreditdateandclient(date, c); 
	}
	public List<Historiquepayement> getAll(){
		return HistoriquepayementclientDao.getAll();
	}
	 public Historiquepayement getHistoriquepayementclientbyCaisseandclient(Historiquepayement cl){
		 return HistoriquepayementclientDao.getCreditclientbyCaisseandclient( cl);
	 }
	public List<Historiquepayement> getListHistoriquepayementdate(String date){
		return HistoriquepayementclientDao.getListcreditdate(date);
	}
	 
	public List<Historiquepayement> Findbynom(String nom) {
		return HistoriquepayementclientDao.Findbynom(nom);
	}
	public void update(Historiquepayement Historiquepayementclient){
		HistoriquepayementclientDao.update(Historiquepayementclient);
	}
	public void delete(Historiquepayement Historiquepayementclient){
		HistoriquepayementclientDao.delete(Historiquepayementclient);
	}
	public Historiquepayement Findbycode(Integer codeHistoriquepayementclient) {
		// TODO Auto-generated method stub
		return HistoriquepayementclientDao.Findbycode(codeHistoriquepayementclient);
	}
}
