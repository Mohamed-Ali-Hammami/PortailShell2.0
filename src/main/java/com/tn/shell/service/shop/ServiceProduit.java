package  com.tn.shell.service.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tn.shell.dao.shop.*;
import com.tn.shell.model.shop.*;
 

 

@Service("ServiceProduit")
public class ServiceProduit {
@Autowired
ProduitDAO produitDAO;

public void save(Produit produit){
	produitDAO.save(produit);
}
 
public List<Produit> getAllbyfamille2(String des){
	return produitDAO.getAllbyfamille2(des);
}
public Produit getProduitbyCodeAbare(String designation) {
	return produitDAO.getProduitbyCodeAbare(designation);
}
public Produit Findbycodes(String des) {
	return produitDAO.Findbycodes(des);
}
public List<Produit> getAllbyfamilles0(String des){
	return produitDAO.getAllbyfamilles0(des);
}
public List<Produit> getAllbyfamilles(String des){
	return produitDAO.getAllbyfamilles(des);
}
 
public List<String> getAllnom(){
	return produitDAO.getAllnom();
}
public List<Produit> getAlls(){
	return produitDAO.getAlls();
}
public Produit Findbydes(String des) {
	return produitDAO.Findbydes(des);
}
public List<Produit> getAll(){
	return produitDAO.getAll();
}
public Produit Findbycodeshop(Integer nom) {
	return produitDAO.Findbycodeshop(nom);
}
public List<Produit> getAllbyfamille(String des){
	return produitDAO.getAllbyfamille(des);
}
public Produit Findbycode(Integer nom) {
	return produitDAO.Findbycode(nom);
}
public List<Produit> getAllQtenegatif(){
	return produitDAO.getAllQtenegatif();
}

public List<Produit> getAll2(){
	return produitDAO.getAll2();
}

public void update(Produit produit){
	 produitDAO.update(produit);
}
public Produit getProduitbydesignation(String designation){
	return produitDAO.getProduitbydesignation(designation);
}
}
