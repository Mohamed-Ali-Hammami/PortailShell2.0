package com.tn.shell.service.gestat;

import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*;
import com.tn.shell.model.shop.Fournisseur;
 

 

@Service("ServiceRetourcuve")
public class ServiceRetourcuve {
	@Autowired
	RetourcuveDAO RetourcuveDAO;
	
	public List<Retourcuve> getAll(){
		return RetourcuveDAO.getAll();
	}
	public List<Retourcuve> getRetourcuvebydate(String c){
		return RetourcuveDAO.getRetourcuvebydate(c);
	}
	public long getquantitebyarticledates(Articlecarburant a,String d) {
		return  RetourcuveDAO.getquantitebyarticledates(a, d);
	}
	public List<Retourcuve> getRetourcuvebyCaisse(Caisse c){
		return RetourcuveDAO.getRetourcuvebyCaisse(c);
	}
	public void save(Retourcuve c){
		RetourcuveDAO.save(c);
	}
	public void update(Retourcuve c){
		RetourcuveDAO.update(c);
	}
	 
	public Integer getmaxcode() {		 
		return RetourcuveDAO.getmaxcode();
	}
	 
	public Retourcuve getRetourcuvebyid(Integer id) {
		return RetourcuveDAO.getRetourcuvebyid(id);
	}
	
	public Retourcuve getretpourbypompe(Pompe p,Caisse c) {
		return RetourcuveDAO.getretpourbypompe(p,c);
	}
}
