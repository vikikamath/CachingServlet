package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

/**
 * Servlet Filter implementation class CacheFilter
 */
@WebFilter("/*")
public class CacheFilter implements Filter {
	
	private static final String CACHE_NAME = "appCache.properties";
	
	

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		OutputStream os;
		try {
			os = new FileOutputStream(new File(CACHE_NAME));
			System.out.println("Before Shutting Down Cache: \n" + CacheState.getProperties());
			CacheState.getProperties().store(os, "Delete this file every release");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		Properties properties = new Properties();
		try {
			InputStream is = new FileInputStream(new File(CACHE_NAME));
			properties.load(is);
			CacheState.getInstance(properties);
			System.out.println("Starting up with Cache: \n" + CacheState.getProperties());
		} catch (FileNotFoundException e) {
			CacheState.getInstance(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
