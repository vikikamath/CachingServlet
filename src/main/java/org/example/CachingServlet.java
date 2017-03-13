package org.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Servlet implementation class CachingServlet
 */
@WebServlet("/hello/*")
public class CachingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ENC_UTF_8 = "UTF-8";
	private static final String CONTENT_TYPE_JSON = "applicaton/json";
	
	private static final Log logger = LogFactory.getLog(CachingServlet.class);
	
	private void writeResponse(HttpServletResponse response, String contentType, String enc, String respData) throws IOException {
		response.setContentType(contentType);
	    response.setCharacterEncoding(enc);
	    if (respData != null) response.getWriter().write(respData);
	}
       
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("START>>" + request.getMethod() + request.getRequestURI());
		if (CacheState.cacheFound(request.getRequestURI())){
			request.setAttribute("jsonData", CacheState.getCache(request.getRequestURI()));
			request.setAttribute("cachedURI", request.getRequestURI());
		}
		request.getRequestDispatcher("/hello.jsp").forward(request, response);
		logger.info("END>>" + request.getMethod() + "URI: " + request.getRequestURI() +" JSONData: " + request.getAttribute("jsonData"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("START>>" + request.getMethod() + request.getRequestURI());
		String bodyJsonString = IOUtils.toString(request.getInputStream());
		String url = request.getRequestURI();
		if (url != null && bodyJsonString != null && bodyJsonString.length() > 0) {
			CacheState.setCache(url, bodyJsonString);
			logger.info("CacheState contains: " + CacheState.getProperties().entrySet().size());
		}
	    writeResponse(response, CONTENT_TYPE_JSON, ENC_UTF_8, bodyJsonString);
	    logger.info("END>>" + request.getMethod() + "URI: " + request.getRequestURI() +" JSONData: " + bodyJsonString);
	}

}
