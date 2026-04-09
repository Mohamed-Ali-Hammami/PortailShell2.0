package com.tn.shell.dao.gestat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.tn.shell.model.gestat.*;

 
public interface DepenseChequeDAO {
public void save(DepenseCheque depense);
public DepenseCheque getdepensebyid(DepenseCheque d);
public DepenseCheque getmaxdepense();
public List<DepenseCheque> getAll();
public void update(DepenseCheque depense);
public void delete(DepenseCheque depense);
public List<DepenseCheque> getdepensebyCaisse(Caisse c);
public List<DepenseCheque> getdepensebydate(String c);
public List<DepenseCheque> getdepensebetweendate(Date d1,Date d2);
public BigDecimal getsummontantbydate( Date d1,Date d2) ;
public double getsummontantbydateandfamille( Date d1,Date d2,int f) ;
public double  getDepenseBylibelle(String fonction,Date d1,Date d2);
public double getdepensebetweendateandfamille2(Date d1, Date d2, int fonction);
}
