package ua.nure.filonitch.summarytask.filter;

import java.io.IOException;

import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import ua.nure.filonitch.summarytask.conn.ConnectionUtils;
import ua.nure.filonitch.summarytask.utils.MyUtils;

import org.apache.log4j.Logger;

/**
 * @author D.Filonich
 *
 */
@WebFilter(filterName = "jdbcFilter", urlPatterns = { "/*" })
public class JDBCFilter implements Filter {
	private static final Logger LOGGER = Logger.getLogger(JDBCFilter.class);

	public JDBCFilter() {
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

	// Проверить является ли Servlet цель текущего request?
	private boolean needJDBC(HttpServletRequest request) {
		System.out.println("JDBC Filter");
		LOGGER.debug("JDBC Filter");
		//
		// Servlet Url-pattern: /spath/*
		//
		// => /spath
		String servletPath = request.getServletPath();
		// => /abc/mnp
		String pathInfo = request.getPathInfo();

		String urlPattern = servletPath;

		if (pathInfo != null) {
			// => /spath/*
			urlPattern = servletPath + "/*";
		}

		// Key: servletName.
		// Value: ServletRegistration
		Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
				.getServletRegistrations();

		// Коллекционировать все Servlet в вашем WebApp.
		Collection<? extends ServletRegistration> values = servletRegistrations.values();
		for (ServletRegistration sr : values) {
			Collection<String> mappings = sr.getMappings();
			if (mappings.contains(urlPattern)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		LOGGER.debug("Filter starts");

		// Открыть connection (соединение) только для request со специальной ссылкой.
		// (Например ссылка к servlet, jsp, ..)
		// Избегать открытия Connection для обычных запросов.
		// (Например image, css, javascript,... )
		if (this.needJDBC(req)) {

			// System.out.println("Open Connection for: " + req.getServletPath());
			LOGGER.trace("Request uri --> " + req.getServletPath());

			Connection conn = null;
			try {
				// Создать объект Connection подключенный к database.
				conn = ConnectionUtils.getConnection();
				// Настроить автоматический commit false, чтобы активно контролировать.
				conn.setAutoCommit(false);

				// Сохранить объект Connection в attribute в request.
				MyUtils.storeConnection(request, conn);

				// Разрешить request продвигаться далее.
				// (Далее к следующему Filter tiếp или к цели).
				LOGGER.debug("Allow next request");
				chain.doFilter(request, response);

				// Вызвать метод commit() чтобы завершить транзакцию с DB.
				conn.commit();
			} catch (Exception e) {
				e.printStackTrace();
				ConnectionUtils.rollbackQuietly(conn);
				throw new ServletException();
			} finally {
				ConnectionUtils.closeQuietly(conn);
			}
		}
		// Для обычных request (image,css,html,..)
		// не нужно открывать connection.
		else {
			// Разрешить request продвигаться далее.
			// (Далее к следующему Filter tiếp или к цели).
			chain.doFilter(request, response);
			LOGGER.debug("Filter finished");
		}

	}

}