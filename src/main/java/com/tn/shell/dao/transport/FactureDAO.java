package com.tn.shell.dao.transport;

import java.util.Date;
import java.util.List;
import com.tn.shell.model.transport.*;

public interface FactureDAO {
public void save(Facture facture);
public List<Facture> getAll();
public List<Facture> getAllPasager();
public List<Facture> getAllTransport();
public List<Facture> getbydate(Date d1,Date d2);
public void update(Facture facture);
public void delete(Facture facture);
public Facture getfacturebycode(String code);
public Facture getBLbycodes(String  code);
 
public Facture getBLbycode(Integer code);
public Facture getMaxfacture();
public List<Facture> getfacturebetwenndate(Date d1,Date d2);
}
