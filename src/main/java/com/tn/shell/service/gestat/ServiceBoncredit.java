package  com.tn.shell.service.gestat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*; 

 

@Service("ServiceBoncredit")
public class ServiceBoncredit {
@Autowired
BoncreditDAO BoncreditDAO;
/*
public void save(Boncredit Boncredit){
	BoncreditDAO.save(Boncredit);
}
 
public Boncredit Findbydes(String des) {
	return BoncreditDAO.Findbydes(des);
}
public List<Boncredit> getAll(){
	return BoncreditDAO.getAll();
}
public List<Boncredit> getAllbyfamille(String des){
	return BoncreditDAO.getAllbyfamille(des);
}
public Boncredit Findbycode(Integer nom) {
	return BoncreditDAO.Findbycode(nom);
}
public List<Boncredit> getAllQtenegatif(){
	return BoncreditDAO.getAllQtenegatif();
}

public List<Boncredit> getAll2(){
	return BoncreditDAO.getAll2();
}

public void update(Boncredit Boncredit){
	 BoncreditDAO.update(Boncredit);
}
*/
}
