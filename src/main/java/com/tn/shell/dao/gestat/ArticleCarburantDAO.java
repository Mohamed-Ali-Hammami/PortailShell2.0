package   com.tn.shell.dao.gestat;

import java.util.List;
import com.tn.shell.model.gestat.*;
  

public interface ArticleCarburantDAO {
public void save(Articlecarburant Articlecarburant);
public List<Articlecarburant> getAll();
public List<Articlecarburant> getAll2();
public void update(Articlecarburant Articlecarburant);
public Articlecarburant getArticlecarburantbydesignation(String designation);
 
public List<Articlecarburant> getAllbyfamille(String designation);
public List<Articlecarburant> getAllQtenegatif();
public Articlecarburant Findbycode(Integer nom) ;
public Articlecarburant Findbydes(String des) ;

}
