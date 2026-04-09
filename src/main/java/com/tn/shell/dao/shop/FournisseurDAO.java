package   com.tn.shell.dao.shop;

import java.util.List;

import com.tn.shell.model.shop.*;

public interface FournisseurDAO {

	public void save(Fournisseur fournisseur);
	public Fournisseur getbyname(String name)	;
	public List<Fournisseur> getAll();
	public Fournisseur getbymf(String name) ;
	public Fournisseur getbyid(Integer name) ;
	public void update(Fournisseur fournisseur);
	public void delete(Fournisseur fournisseur);
	 public List<String> getAllnom();
}
