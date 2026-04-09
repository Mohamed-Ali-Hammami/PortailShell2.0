package com.tn.shell.ui.paie;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
public class UserAgentListner implements Filter {
private ServletContext context;
	public void init(FilterConfig filterConfig) throws ServletException {
		 Map<String,Integer> agents=new LinkedHashMap<String, Integer>();
		 agents.put("papier", 0);
		 agents.put("wiro", 0);
		 agents.put("ancre", 0);
		 context=filterConfig.getServletContext();
		 context.setAttribute("browserStats", agents);
		
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		String agent =((HttpServletRequest)req).getHeader("User-Agent");
		if(agent !=null){
			boolean match=false;
			Map<String,Integer> agents=(Map) context.getAttribute("browserStats");
			for(Map.Entry<String, Integer> entry:agents.entrySet()){
				String key=entry.getKey();
				if(agent.indexOf(key)!=-1){
					Integer value=agents.get(key);
					agents.put(key, ++value);
					match=true;
					break;
				}
			}
			if(match==false){
				Integer value=agents.get("other");
						agents.put("other", ++value);
			}
			chain.doFilter(req, resp);
		}
		// TODO Auto-generated method stub
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
