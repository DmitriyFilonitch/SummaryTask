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
 */
@WebServlet(urlPatterns = { "/editUser" })
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditUserServlet() {
		super();
	}

	// Отобразить страницу редактирования продукта.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);

		int user_id = Integer.parseInt(request.getParameter("user_id"));

		UserAccount user = null;

		String errorString = null;

		try {
			user = DBUtils.findForEditUser(conn, user_id);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		// Ошибки не имеются.
		// Продукт не существует для редактирования (edit).
		// Redirect sang trang danh sách sản phẩm.
		if (errorString != null && user == null) {
			response.sendRedirect(request.getServletPath() + "/adminInfo");
			return;
		}

		// Сохранить информацию в request attribute перед тем как forward к views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("user", user);

		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editUserView.jsp");
		dispatcher.forward(request, response);

	}

	// После того, как пользователь отредактировал информацию продукта и нажал на
	// Submit.
	// Данный метод будет выполнен.
	@SuppressWarnings("unused")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);

		int user_id = Integer.parseInt(request.getParameter("user_id"));
		String userName = (String) request.getParameter("userName");
		String fullname = (String) request.getParameter("fullname");
		String gender = (String) request.getParameter("gender");
		float balance = Float.parseFloat(request.getParameter("balance"));
		boolean block_status = Boolean.parseBoolean(request.getParameter("block_status"));
		String nameRole = (String) request.getParameter("name");

		UserAccount user = new UserAccount(user_id, userName, fullname, gender, null, balance, block_status, nameRole);

		String errorString = null;

		try {
			DBUtils.updateUser(conn, user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Сохранить информацию в request attribute перед тем как forward к views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("user", user);

		// Если имеется ошибка, forward к странице edit.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/editUserView.jsp");
			dispatcher.forward(request, response);
		}
		// Если все хорошо.
		// Redirect к странице со списком продуктов.
		else {
			response.sendRedirect(request.getContextPath() + "/adminInfo");
		}
	}

}