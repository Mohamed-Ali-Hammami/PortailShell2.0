package com.tn.shell.dao.gestat;

import java.util.List;
import com.tn.shell.model.gestat.*;

 
 

public interface SoldetpeDAO {
public List<Soldetpe> getAll();
public void save(Soldetpe c);
public Soldetpe getmaxcode();
public void update(Soldetpe c);
 
}
