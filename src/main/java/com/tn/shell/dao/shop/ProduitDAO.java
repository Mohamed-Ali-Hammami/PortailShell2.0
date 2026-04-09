package   com.tn.shell.dao.shop;

import java.util.List;

import com.tn.shell.model.shop.*;
public interface ProduitDAO {
public void save(Produit produit);
public List<Produit> getAll();
public List<Produit> getAll2();
public void update(Produit produit);
public Produit getProduitbydesignation(String designation);
public Produit getProduitbyCodeAbare(String designation);
public List<String> getAllnom();
public List<Produit> getAllbyfamille(String designation);
public List<Produit> getAllQtenegatif();
public Produit Findbycode(Integer nom) ;
public Produit Findbydes(String des) ;
public Produit Findbycodes(String des);
public List<Produit> getAlls();
public List<Produit> getAllbyfamille2(String des);
public Produit Findbycodeshop(Integer nom);
public List<Produit> getAllbyfamilles(String des);
public List<Produit> getAllbyfamilles0(String des);

}
