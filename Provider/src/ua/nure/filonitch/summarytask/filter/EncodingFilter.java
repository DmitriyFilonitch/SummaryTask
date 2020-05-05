package ua.nure.filonitch.summarytask.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * @author D.Filonich
 *
 */
@WebFilter(filterName = "encodingFilter", urlPatterns = { "/*" })
public class EncodingFilter implements Filter {
	private static final Logger LOGGER = Logger.getLogger(EncodingFilter.class);

	public EncodingFilter() {
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		LOGGER.debug("Filter initialization starts");

		LOGGER.debug("Filter initialization finished");
	}

	@Override
	public void destroy() {
		LOGGER.debug("Filter destruction starts");

		LOGGER.debug("Filter destruction finished");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		LOGGER.debug("Filter starts");
		LOGGER.trace("Request uri --> " + req.getServletPath());
		request.setCharacterEncoding("UTF-8");
		LOGGER.debug("Filter finished");
		chain.doFilter(request, response);
	}

}