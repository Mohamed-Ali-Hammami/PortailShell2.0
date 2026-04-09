package com.tn.shell.dao.gestat;

import java.util.Date;
import java.util.List;
import com.tn.shell.model.gestat.*;

 
 

public interface AvoirDAO {
	public List<Avoir> getBETWENNDATES(Date d1,Date d2);
public List<Avoir> getAll();
public void save(Avoir c);
public Integer getmaxcode();
public void update(Avoir c);
public Avoir getAvoirbyid(Integer id);
 public List<Avoir> getavoirbydates(String date);
}
