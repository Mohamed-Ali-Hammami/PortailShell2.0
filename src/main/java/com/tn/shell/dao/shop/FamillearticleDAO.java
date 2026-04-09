package com.tn.shell.dao.shop;

import java.util.List;

import com.tn.shell.model.shop.*;
 

public interface FamillearticleDAO {
public void save(Famillearticle typearticle);
public  List<Famillearticle> getAll();
public  List<Famillearticle> getAll2();
public  List<String> getAllnom();
public Famillearticle findbyDesignation(String designation);
public void update(Famillearticle typearticle);
public void delete(Famillearticle typearticle);

}
