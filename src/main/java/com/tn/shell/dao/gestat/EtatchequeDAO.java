package com.tn.shell.dao.gestat;

import java.util.List;
import com.tn.shell.model.gestat.*;

 
 

public interface EtatchequeDAO {
public List<Etatcheque> getAll();
public void save(Etatcheque c);
public Etatcheque getmaxcode();
public void update(Etatcheque c);
 
}
