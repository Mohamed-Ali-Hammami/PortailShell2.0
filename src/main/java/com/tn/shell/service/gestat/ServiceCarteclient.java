package com.tn.shell.service.gestat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.gestat.CarteclientDao;
import com.tn.shell.model.gestat.Carteclient;
import com.tn.shell.model.gestat.Clientgestat;

@Service("ServiceCarteClient")
public class ServiceCarteclient {

	@Autowired
	CarteclientDao carteDao;

	public void save(Carteclient carte) {
		carteDao.save(carte);
	}

	public List<Carteclient> getAll() {
		return carteDao.getAll();
	}

	public List<Carteclient> getAllhistory() {
		return carteDao.getAllhistory();
	}

	public List<Carteclient> getByClient(Clientgestat client) {
		return carteDao.getByClient(client);
	}

	public Carteclient Findbycode(Integer id) {
		return carteDao.Findbycode(id);
	}

	public void update(Carteclient carte) {
		carteDao.update(carte);
	}

	public void delete(Carteclient carte) {
		carteDao.delete(carte);
	}
}
