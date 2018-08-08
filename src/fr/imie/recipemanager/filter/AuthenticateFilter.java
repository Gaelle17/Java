package fr.imie.recipemanager.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/auth/*")
public class AuthenticateFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse res, FilterChain filter) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getSession().getAttribute("username") != null && req.getSession().getAttribute("pwd") != null) {
			/* get user if Ok -> */filter.doFilter(req, res);
		} else {
			req.getRequestDispatcher("/login").forward(req, res);
		}
		
	}

}