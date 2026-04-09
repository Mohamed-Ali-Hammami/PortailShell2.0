package   com.tn.shell.dao.shop;

import java.util.Date;
import java.util.List;

import com.tn.shell.model.shop.*;

public interface LigneTransfertdao {
public void save(Lignetransert c);
public void update(Lignetransert c);
public void delete(Lignetransert c);
public List<Lignetransert> getAll(); 
public List<Lignetransert> getAllbydate(String d);
public List<Lignetransert> getAllbydatemoins(String d);
public List<Lignetransert> getlisttransferbydateandproduit(String d,Produit p);
}
