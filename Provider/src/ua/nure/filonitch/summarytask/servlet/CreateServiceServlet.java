package ua.nure.filonitch.summarytask.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.filonitch.summarytask.beans.Services;
import ua.nure.filonitch.summarytask.utils.DBUtils;
import ua.nure.filonitch.summarytask.utils.MyUtils;

/**
 * @author D.Filonich
 *
 * CREATE NEW SERVICE SERVLET
 *
 */
@WebServlet(urlPatterns = { "/createService" })
public class CreateServiceServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(CreateServiceServlet.class);
	private static final String REGEX = "[А-ЯЁа-яё\\s]+";
	private static final long serialVersionUID = 1L;

	public CreateServiceServlet() {
		super();
	}

	// Отобразить страницу создания продукта.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LOGGER.debug("DOGET");
		
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/createServiceView.jsp");
		dispatcher.forward(request, response);
	}

	// Когда пользователь вводит информацию продукта, и нажимает Submit.
	// Этот метод будет вызван.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		LOGGER.debug("DOPOST");
		
		Connection conn = MyUtils.getStoredConnection(request);

		int service_id = Integer.parseInt(request.getParameter("service_id"));
		String service_name = (String) request.getParameter("service_name");
		String service_description = (String) request.getParameter("service_description");
		Services service = new Services(service_id, service_name, service_description);

		String errorString = null;

		// Кодом продукта является строка [a-zA-Z_0-9]
		// Имеет минимум 1 символ.

		if (service_name == null || !service_name.matches(REGEX)) {
			errorString = "Service name invalid!";
		}

		if (errorString == null) {
			try {
				DBUtils.insertService(conn, service);
			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
		}

		// Сохранить информацию в request attribute перед тем как forward к views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("service", service);

		// Если имеется ошибка forward (перенаправления) к странице 'edit'.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/createServiceView.jsp");
			dispatcher.forward(request, response);
		}
		// Если все хорошо.
		// Redirect (перенаправить) к странице со списком продуктов.
		else {
			response.sendRedirect(request.getContextPath() + "/adminInfo");
		}
	}

}