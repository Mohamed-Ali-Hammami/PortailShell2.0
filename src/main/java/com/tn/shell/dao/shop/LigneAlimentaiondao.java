package   com.tn.shell.dao.shop;

import java.util.Date;
import java.util.List;

import com.tn.shell.model.shop.*;

public interface LigneAlimentaiondao {
public void save(Lignealimentation c);
public void update(Lignealimentation c);
public void delete(Lignealimentation c);
public List<Lignealimentation> getAll();
public double getchiffreAffaireAchat(Famillearticle e) ;
public double gettvaAchat(Famillearticle e);
public double getlisttransferbydateandproduit(String d,Produit p);
public List<Lignealimentation> getbetwendates(Date d1,Date d2,Produit p);
public List<Lignealimentation> getLignebyachat(Achat achat);
public double getlisttransferbydateandproduit2(Date d,Date d2, Produit p);
}
