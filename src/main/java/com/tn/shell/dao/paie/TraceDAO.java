package com.tn.shell.dao.paie;

import java.util.List;
import com.tn.shell.model.paie.*;

public interface TraceDAO {
public void save(Tracepaie trace);
public List<Tracepaie> getAll();

}
