package com.tn.shell.dao.transport;

import java.util.List;

import com.tn.shell.model.transport.*;

 

public interface VheculeDao {
public void save(Vhecule vhecule);
public List<Vhecule> getAll();
public Vhecule Findbynom(String nom);
public Vhecule Findbycode(Integer code);
public Vhecule Findbymf(String nom);
public void update(Vhecule vhecule);
public void delete(Vhecule vhecule);
public List<String> getAllnom();
}
