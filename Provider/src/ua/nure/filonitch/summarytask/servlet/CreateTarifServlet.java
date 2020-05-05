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

import ua.nure.filonitch.summarytask.beans.Tarif;
import ua.nure.filonitch.summarytask.utils.DBUtils;
import ua.nure.filonitch.summarytask.utils.MyUtils;

/**
 * @author D.Filonich
 *
 * CREATE NEW TARIF SERVLET
 *
 */
@WebServlet(urlPatterns = { "/createTarif" })
public class CreateTarifServlet extends HttpServlet {
	private static final String REGEX = "[a-zA-Z0-9]+";
	private static final long serialVersionUID = 1L;

	public CreateTarifServlet() {
		super();
	}

	// Отобразить страницу создания продукта.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/createTarifView.jsp");
		dispatcher.forward(request, response);
	}

	// Когда пользователь вводит информацию продукта, и нажимает Submit.
	// Этот метод будет вызван.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);

		String code = (String) request.getParameter("code");
		String name = (String) request.getParameter("name");
		String priceStr = (String) request.getParameter("price");
		String description = (String) request.getParameter("description");
		// String service_id = (String) request.getParameter("serviceid");
		int service_id = Integer.parseInt(request.getParameter("service_id"));

		float price = 0;
		try {
			price = Float.parseFloat(priceStr);
		} catch (Exception e) {
		}
		Tarif tarif = new Tarif(code, name, price, description, service_id);

		String errorString = null;

		// Кодом продукта является строка [a-zA-Z_0-9]
		// Имеет минимум 1 символ.

		if (code == null || !code.matches(REGEX)) {
			errorString = "Tarif Code invalid!";
		}

		if (errorString == null) {
			try {
				DBUtils.insertTarif(conn, tarif);
			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
		}

		// Сохранить информацию в request attribute перед тем как forward к views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("tarif", tarif);

		// Если имеется ошибка forward (перенаправления) к странице 'edit'.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/createTarifView.jsp");
			dispatcher.forward(request, response);
		}
		// Если все хорошо.
		// Redirect (перенаправить) к странице со списком продуктов.
		else {
			response.sendRedirect(request.getContextPath() + "/adminInfo");
		}
	}

}