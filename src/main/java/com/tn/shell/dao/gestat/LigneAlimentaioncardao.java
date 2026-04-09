package   com.tn.shell.dao.gestat;

import java.util.Date;
import java.util.List;
import com.tn.shell.model.gestat.*;

public interface LigneAlimentaioncardao {
public void save(Lignealimentationcar c);
public void update(Lignealimentationcar c);
public void delete(Lignealimentationcar c);
public List<Lignealimentationcar> getAll();
public List<Lignealimentationcar> getLignebyproduit(Lignealimentationcar l);
public List<Lignealimentationcar> getLignebyachat(Achatcarburant c);
public List<Lignealimentationcar>  getAllventepardatearticle(String s, Articlecarburant a);
public double getprixbydate(Date d1,Date d2,Articlecarburant a);
 
}
