package com.fieldmi.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fieldmi.service.FMUserActivityService;
import com.fieldmi.service.FMUserService;
import com.softtantra.salesapp.pojo.LoginDetails;
import com.softtantra.ws.Credentials;

@WebFilter(urlPatterns = "/*")
public class SalesMIRequestFilter implements Filter {

	@Autowired
	FMUserService fmUserService;

	@Autowired
	FMUserActivityService fmUserActivityService;


	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
			throws IOException, ServletException {

		MultiReadHttpServletRequest httpReq = new MultiReadHttpServletRequest(((HttpServletRequest) request));
		ServletContext servletContext = httpReq.getServletContext();

		String contextPath = httpReq.getRequestURI();
		//contextPath = contextPath.substring((contextPath.lastIndexOf("/") + 1));		

		if (fmUserService == null) {

			WebApplicationContext webApplicationContext = WebApplicationContextUtils
					.getWebApplicationContext(servletContext);
			fmUserService = webApplicationContext.getBean(FMUserService.class);
			fmUserActivityService = webApplicationContext.getBean(FMUserActivityService.class);
		}

		int u_id = 0;
		int c_id = -1;
		if (contextPath.contains("Login")) {

			try {
				
				ObjectMapper oMapper = new ObjectMapper();
				Credentials credentials = oMapper.readValue(httpReq.getReader(), Credentials.class);

				String username = credentials.getUsername();
				String password = credentials.getPassword();
				int company_id = credentials.getCompany_id().intValue();
				LoginDetails result = fmUserService.getLoginDetails(username, password, company_id);
				if (result != null) {
					u_id = result.getUser_id();
					c_id = result.getCompany_id();
				}
			} catch (JSONException e) {
				// crash and burn
				throw new IOException("Error parsing JSON request string");
			}

		}

		filterchain.doFilter(httpReq, response);
	}

	@Override
	public void init(FilterConfig filterconfig) throws ServletException {

	}

}