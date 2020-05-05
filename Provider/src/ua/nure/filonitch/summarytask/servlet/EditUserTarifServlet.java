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

import ua.nure.filonitch.summarytask.beans.UserTarif;
import ua.nure.filonitch.summarytask.utils.DBUtils;
import ua.nure.filonitch.summarytask.utils.MyUtils;

/**
 * @author D.Filonich
 *
 * EDIT USERS TARIF SERVLT
 * 
 */
@WebServlet(urlPatterns = { "/editUserTarif" })
public class EditUserTarifServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditUserTarifServlet() {
		super();
	}

	// Отобразить страницу редактирования продукта.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);

		int id_user = Integer.parseInt(request.getParameter("id_user"));
		String code = (String) request.getParameter("code");
		UserTarif userTarif = null;

		String errorString = null;

		try {
			userTarif = DBUtils.editUserTarif(conn, id_user, code);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		// Ошибки не имеются.
		// Продукт не существует для редактирования (edit).
		// Redirect sang trang danh sách sản phẩm.
		if (errorString != null && userTarif == null) {
			response.sendRedirect(request.getServletPath() + "/adminInfo");
			return;
		}

		// Сохранить информацию в request attribute перед тем как forward к views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("userTarif", userTarif);

		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editUserTarifView.jsp");
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
		int id_user = Integer.parseInt(request.getParameter("id_user"));
		String code = (String) request.getParameter("code");
		int payment_status = Integer.parseInt(request.getParameter("payment_status"));

		UserTarif userTarif = new UserTarif(id_user, code, payment_status);

		String errorString = null;

		try {
			DBUtils.updateUserTarif(conn, userTarif, code);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Сохранить информацию в request attribute перед тем как forward к views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("user", userTarif);

		// Если имеется ошибка, forward к странице edit.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/editUserTarifView.jsp");
			dispatcher.forward(request, response);
		}
		// Если все хорошо.
		// Redirect к странице со списком продуктов.
		else {
			response.sendRedirect(request.getContextPath() + "/adminInfo");
		}
	}

}