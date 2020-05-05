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

import ua.nure.filonitch.summarytask.beans.UserAccount;
import ua.nure.filonitch.summarytask.utils.DBUtils;
import ua.nure.filonitch.summarytask.utils.MyUtils;

/**
 * @author D.Filonich
 *
 * CREATE USER SERVLET
 *
 */
@WebServlet(urlPatterns = { "/createUser" })
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateUserServlet() {
		super();
	}

	// Отобразить страницу создания продукта.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/createUserView.jsp");
		dispatcher.forward(request, response);
	}

	// Когда пользователь вводит информацию продукта, и нажимает Submit.
	// Этот метод будет вызван.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);

		int user_id = Integer.parseInt(request.getParameter("user_id"));

		// UserAccount user = null;
		String userName = (String) request.getParameter("userName");
		String fullname = (String) request.getParameter("fullname");
		String gender = (String) request.getParameter("gender");
		String password = (String) request.getParameter("password");
		float balance = Float.parseFloat(request.getParameter("balance"));
		int role_id = Integer.parseInt(request.getParameter("role_id"));

		UserAccount user = new UserAccount(user_id, userName, fullname, gender, password, balance, role_id, null);

		String errorString = null;

		// Кодом продукта является строка [a-zA-Z_0-9]
		// Имеет минимум 1 символ.
		String regex = "\\w+";

		if (password == null || !password.matches(regex)) {
			errorString = "User password is invalid!";
		}

		if (errorString == null) {
			try {
				DBUtils.insertUser(conn, user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Сохранить информацию в request attribute перед тем как forward к views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("user", user);

		// Если имеется ошибка forward (перенаправления) к странице 'edit'.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/createUserView.jsp");
			dispatcher.forward(request, response);
		}
		// Если все хорошо.
		// Redirect (перенаправить) к странице админа.
		else {
			response.sendRedirect(request.getContextPath() + "/adminInfo");
		}
	}

}