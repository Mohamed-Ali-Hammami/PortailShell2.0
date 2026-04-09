package com.tn.shell.dao.gestat;

import java.util.List;

import com.tn.shell.model.gestat.Tracegestat;
import com.tn.shell.model.shop.*;

public interface TracegestatDAO {
public void save(Tracegestat trace);
public List<Tracegestat> getAll();

}
