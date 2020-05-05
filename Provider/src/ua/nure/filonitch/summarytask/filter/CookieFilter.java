package ua.nure.filonitch.summarytask.filter;

import java.io.IOException;


import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.nure.filonitch.summarytask.beans.UserAccount;
import ua.nure.filonitch.summarytask.utils.DBUtils;
import ua.nure.filonitch.summarytask.utils.MyUtils;

import org.apache.log4j.Logger;

/**
 * @author D.Filonich
 *
 */
@WebFilter(filterName = "cookieFilter", urlPatterns = { "/*" })
public class CookieFilter implements Filter {
	private static final Logger LOGGER = Logger.getLogger(CookieFilter.class);

	public CookieFilter() {
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		LOGGER.debug("Filter initialization starts");
	}

	@Override
	public void destroy() {
		LOGGER.debug("Filter destruction starts");

		LOGGER.debug("Filter destruction finished");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		LOGGER.debug("Cookie Filter");
		LOGGER.debug("Filter starts");

		HttpServletRequest req = (HttpServletRequest) request;
		LOGGER.trace("Request uri --> " + req.getServletPath());
		HttpSession session = req.getSession();

		UserAccount userInSession = MyUtils.getLoginedUser(session);
		//
		if (userInSession != null) {
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
			chain.doFilter(request, response);
			return;
		}

		// Connection создан в JDBCFilter.
		Connection conn = MyUtils.getStoredConnection(request);

		// Флаг(flag) для проверки Cookie.
		String checked = (String) session.getAttribute("COOKIE_CHECKED");
		if (checked == null && conn != null) {
			String userName = MyUtils.getUserNameInCookie(req);
			try {
				UserAccount user = DBUtils.findUser(conn, userName);
				MyUtils.storeLoginedUser(session, user);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// Отметить проверенные Cookie.
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
		}
		LOGGER.debug("Filter finished");

		chain.doFilter(request, response);
	}

}