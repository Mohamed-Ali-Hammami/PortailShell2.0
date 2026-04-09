package com.tn.shell.dao.gestat;

import java.util.Date;
import java.util.List;
import com.tn.shell.model.gestat.*;

 
public interface DepensegestatDAO {
public void save(Depensegestat depense);
public Depensegestat getdepensebyid(Depensegestat d);
public Depensegestat getmaxdepense();
public List<Depensegestat> getAll();
public void update(Depensegestat depense);
public void delete(Depensegestat depense);
public List<Depensegestat> getdepensebyCaisse(Caisse c);
public List<Depensegestat> getdepensebydate(String c);
public List<Depensegestat> getdepensebetweendate(Date d1,Date d2);
public List<Depensegestat> getdepensebetweendateAndFamille(Date d1,Date d2,int f);
public double getsummontantbydate( Date d1,Date d2) ;
public double getsummontantbydateandfamille( Date d1,Date d2,int f) ;
}
