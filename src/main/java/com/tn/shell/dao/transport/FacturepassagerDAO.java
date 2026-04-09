package com.tn.shell.dao.transport;

import java.util.Date;
import java.util.List;
import com.tn.shell.model.transport.*;

public interface FacturepassagerDAO {
public void save(Facturepassager facture);
public List<Facturepassager> getAll();
public List<Facturepassager> getAllPasager();
public List<Facturepassager> getAllTransport();
public List<Facturepassager> getbydate(Date d1,Date d2);
public void update(Facturepassager facture);
public void delete(Facturepassager facture);
public Facturepassager getfacturebycode(String code);
public Facturepassager getBLbycodes(String  code);
 
public Facturepassager getBLbycode(Integer code);
public Facturepassager getMaxfacture();
public List<Facturepassager> getfacturebetwenndate(Date d1,Date d2);
}
