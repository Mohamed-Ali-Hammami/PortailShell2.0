package com.tn.shell.dao.gestat;

import java.util.Date;
import java.util.List;
import com.tn.shell.model.gestat.*;

 
 

public interface AvoirbanqDAO {
public List<Avoirbancaire> getAll();
public List<Avoirbancaire> getBETWENNDATES(Date d1,Date d2);
public void save(Avoirbancaire c);
public Integer getmaxcode();
public void update(Avoirbancaire c);
public Avoirbancaire getAvoirbyid(Integer id);
 public List<Avoirbancaire> getavoirbydates(String date);
}
