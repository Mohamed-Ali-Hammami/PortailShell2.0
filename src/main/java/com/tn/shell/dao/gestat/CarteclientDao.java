package com.tn.shell.dao.gestat;

import java.util.List;

import com.tn.shell.model.gestat.Carteclient;
import com.tn.shell.model.gestat.Clientgestat;

public interface CarteclientDao {
	void save(Carteclient carte);

	List<Carteclient> getAll();

	List<Carteclient> getAllhistory();

	List<Carteclient> getByClient(Clientgestat client);

	Carteclient Findbycode(Integer id);

	void update(Carteclient carte);

	void delete(Carteclient carte);
}
