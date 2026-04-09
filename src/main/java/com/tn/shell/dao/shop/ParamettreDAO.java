package   com.tn.shell.dao.shop;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.shop.*;

public interface ParamettreDAO {
	public List<Paramettre> getAll(); 
	public void update(Paramettre paramettre) ;
	public void save(Paramettre paramettre) ;
}
