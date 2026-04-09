package com.tn.shell.dao.transport;

import java.util.List;

import com.tn.shell.model.transport.*;

public interface ClientDao {
public void save(Client client);
public List<Client> getAll();
public List<String> getAllnom();
public Client Findbynom(String nom);
public Client Findbycode(Integer code);
public Client Findbymf(String nom);
public void update(Client client);
public void delete(Client client);
}
