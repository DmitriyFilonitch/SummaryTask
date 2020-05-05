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
 * EDIT SERVICE SERVLET
 *
 */
@WebServlet(urlPatterns = { "/editService" })
public class EditServiceServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(EditServiceServlet.class);

	private static final long serialVersionUID = 1L;

	public EditServiceServlet() {
		super();
	}

	// Отобразить страницу редактирования продукта.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		LOGGER.debug("DOGET");
		
		Connection conn = MyUtils.getStoredConnection(request);

		int service_id = Integer.parseInt(request.getParameter("service_id"));

		Services service = null;

		String errorString = null;

		try {
			service = DBUtils.findService(conn, service_id);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		// Ошибки не имеются.
		// Продукт не существует для редактирования (edit).
		// Redirect sang trang danh sách sản phẩm.
		if (errorString != null && service == null) {
			response.sendRedirect(request.getServletPath() + "/adminInfo");
			return;
		}

		// Сохранить информацию в request attribute перед тем как forward к views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("service", service);

		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editServiceView.jsp");
		dispatcher.forward(request, response);

	}

	// После того, как пользователь отредактировал информацию продукта и нажал на
	// Submit.
	// Данный метод будет выполнен.
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

		try {
			DBUtils.updateService(conn, service);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		// Сохранить информацию в request attribute перед тем как forward к views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("service", service);

		// Если имеется ошибка, forward к странице edit.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/editServiceView.jsp");
			dispatcher.forward(request, response);
		}
		// Если все хорошо.
		// Redirect к странице со списком продуктов.
		else {
			response.sendRedirect(request.getContextPath() + "/adminInfo");
		}
	}

}