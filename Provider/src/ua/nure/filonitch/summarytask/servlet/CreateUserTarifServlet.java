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

import ua.nure.filonitch.summarytask.beans.UserTarif;
import ua.nure.filonitch.summarytask.utils.DBUtils;
import ua.nure.filonitch.summarytask.utils.MyUtils;

/**
 * @author D.Filonich
 *
 * CONNECT USER TO TARIF BY ADMIN
 *
 */
@WebServlet(urlPatterns = { "/createUserTarif" })
public class CreateUserTarifServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(CreateUserTarifServlet.class);
	private static final String REGEX = "[a-zA-Z0-9]+";
	private static final long serialVersionUID = 1L;

	public CreateUserTarifServlet() {
		super();
	}

	// Отобразить страницу создания тарифа пользователя.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		LOGGER.debug("DOGET");

		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/createUserTarifView.jsp");
		dispatcher.forward(request, response);
	}

	// Когда пользователь вводит информацию тарифа пользователя, и нажимает Submit.
	// Этот метод будет вызван.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		LOGGER.debug("DOPOST");
		
		Connection conn = MyUtils.getStoredConnection(request);

		int id_user = Integer.parseInt(request.getParameter("id_user"));

		// UserAccount user = null;
		String code = (String) request.getParameter("code");
		UserTarif userTarif = new UserTarif();
		userTarif.setId_user(id_user);
		userTarif.setCode(code);

		String errorString = null;

		// Кодом продукта является строка [a-zA-Z_0-9]
		// Имеет минимум 1 символ.

		if (code == null || !code.matches(REGEX)) {
			errorString = "Tarif code is invalid!";
		}

		if (errorString == null) {
			try {
				DBUtils.insertUserTarif(conn, userTarif);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Сохранить информацию в request attribute перед тем как forward к views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("userTarif", userTarif);

		// Если имеется ошибка forward (перенаправления) к странице 'edit'.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/createUserTarifView.jsp");
			dispatcher.forward(request, response);
		}
		// Если все хорошо.
		// Redirect (перенаправить) к странице со списком продуктов.
		else {
			response.sendRedirect(request.getContextPath() + "/adminInfo");
		}
	}

}