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

import ua.nure.filonitch.summarytask.utils.DBUtils;
import ua.nure.filonitch.summarytask.utils.MyUtils;

/**
 * @author D.Filonich
 *
 */
@WebServlet(urlPatterns = { "/deleteService" })
public class DeleteServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteServiceServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);

		int service_id = Integer.parseInt(request.getParameter("service_id"));

		String errorString = null;

		try {
			DBUtils.deleteService(conn, service_id);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		// Если происходит ошибка, forward (перенаправить) к странице оповещающей
		// ошибку.
		if (errorString != null) {
			// Сохранить информацию в request attribute перед тем как forward к views.
			request.setAttribute("errorString", errorString);
			//
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/errorPage.jsp");
			dispatcher.forward(request, response);
		}
		// Если все хорошо.
		// Redirect (перенаправить) к странице со списком продуктов.
		else {
			response.sendRedirect(request.getContextPath() + "/adminInfo");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}