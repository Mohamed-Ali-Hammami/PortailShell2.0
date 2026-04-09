package  com.tn.shell.service.gestat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*;
 

 

@Service("ServiceLigneAlimentationcar")
public class ServiceLigneAlimentationcar {
	@Autowired
	LigneAlimentaioncardao ligneAlimentaiondao;

	public void save(Lignealimentationcar typearticle) {
		ligneAlimentaiondao.save(typearticle);
	} 
	
	public double getprixbydate(Date d1, Date d2, Articlecarburant a) {
		return ligneAlimentaiondao.getprixbydate(d1, d2, a);
	}
	
	 public List<Lignealimentationcar> getLignebyachat(Achatcarburant c){
		 return ligneAlimentaiondao.getLignebyachat(c);
	 }
	public  List<Lignealimentationcar> getAll(){
		return ligneAlimentaiondao.getAll();
	} 
	 public List<Lignealimentationcar>  getAllventepardatearticle(String s, Articlecarburant a){
		 return ligneAlimentaiondao.getAllventepardatearticle(s, a);
	 }
	public List<Lignealimentationcar> getLignebyproduit(Lignealimentationcar l){
		return ligneAlimentaiondao.getLignebyproduit(l);
	}
	 
	public void update(Lignealimentationcar typearticle) {
		ligneAlimentaiondao.update(typearticle);
	} 
	public void delete(Lignealimentationcar typearticle) {
		ligneAlimentaiondao.delete(typearticle);
		
	}
	 
}
