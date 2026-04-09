package   com.tn.shell.dao.gestat;

import java.util.Date;
import java.util.List;

import com.tn.shell.model.gestat.*;
import com.tn.shell.model.shop.Produit;

 

public interface LigneindexDAO {
public void save(Ligneindex c);
public void update(Ligneindex c);
public void delete(Ligneindex c);
public List<Ligneindex> getAll();
public List<Ligneindex> getAllventeparposte(String d ,Poste poste);
public double getAllventepardatearticle( Articlecarburant a,Date d1,Date d2);
public double getAllventepardatearticlequantite( Articlecarburant a,Date d1,Date d2);
public List<Ligneindex> getAllventepardate(String d);
public List<Ligneindex> getAllventeparposteNegatif(String d ,Poste poste);
public double getmaxcode(Pompe p,Caisse c);
public List<Ligneindex> getAllparposte(Caisse c) ;
public Ligneindex getmaxcode();
public List<Ligneindex> getAllbyProduit(Produit p);
public List<Ligneindex>  getLigneindexbycreditclient(Credit credit);
public  Ligneindex  getAllventepardateandpompeandposte2(Pompe p,Caisse s);
public  Ligneindex  getAllventepardateandpompeandposte(String dates1,Pompe p, Poste Poste1);
public List<Ligneindex> getAllventeentredate(Date d,Date d2,Articlecarburant a) ;
public double getquantitebyarticle(Articlecarburant a,Caisse c);
public double getquantitebyarticledates(Articlecarburant a,String d);
public double getAllventepardatearticlequantitepardate( Articlecarburant a,String d1) ;
public List<Ligneindex> getAllventepardate(String d, Articlecarburant poste);
}
