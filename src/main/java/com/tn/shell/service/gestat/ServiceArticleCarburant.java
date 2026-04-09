package  com.tn.shell.service.gestat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*; 
 

 

@Service("ServiceArticleCarburant")
public class ServiceArticleCarburant {
@Autowired
ArticleCarburantDAO ProduitcuveDAO;

public void save(Articlecarburant Produitcuve){
	ProduitcuveDAO.save(Produitcuve);
} 

 
public Articlecarburant Findbydes(String des) {
	return ProduitcuveDAO.Findbydes(des);
}
public List<Articlecarburant> getAll(){
	return ProduitcuveDAO.getAll();
}
public List<Articlecarburant> getAllbyfamille(String des){
	return ProduitcuveDAO.getAllbyfamille(des);
}
public Articlecarburant Findbycode(Integer nom) {
	return ProduitcuveDAO.Findbycode(nom);
}
public List<Articlecarburant> getAllQtenegatif(){
	return ProduitcuveDAO.getAllQtenegatif();
}

public List<Articlecarburant> getAll2(){
	return ProduitcuveDAO.getAll2();
}

public void update(Articlecarburant Produitcuve){
	 ProduitcuveDAO.update(Produitcuve);
}
public Articlecarburant getArticlecarburantbydesignation(String designation){
	return ProduitcuveDAO.getArticlecarburantbydesignation(designation);
}
}
