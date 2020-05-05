package ua.nure.filonitch.summarytask.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.filonitch.summarytask.beans.Services;
import ua.nure.filonitch.summarytask.beans.Tarif;
import ua.nure.filonitch.summarytask.beans.UserAccount;
import ua.nure.filonitch.summarytask.utils.DBUtils;
import ua.nure.filonitch.summarytask.utils.MyUtils;

/**
 * @author D.Filonich
 *
 * ORDERING NEW TARIF SERVLET
 *
 */
@WebServlet(urlPatterns = { "/orderTarif" })
public class orderTarifServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(orderTarifServlet.class);
	private static final long serialVersionUID = 1L;

	public orderTarifServlet() {
		super();
	}

	@SuppressWarnings("unused")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.debug("DOGET");
		Connection conn = MyUtils.getStoredConnection(request);
		HttpSession session = request.getSession();

		// Проверить, вошел ли пользователь в систему (login) или нет.
		UserAccount loginedUser = MyUtils.getLoginedUser(session);

		int user_id = loginedUser.getUser_id();

		Tarif tarif = null;
		Services service = null;
		String errorString = null;
		String errorString2 = null;
		List<Services> list2 = null;
		try {
			list2 = DBUtils.queryServices(conn);
		} catch (SQLException e2) {
			e2.printStackTrace();
			errorString2 = e2.getMessage();
		}
		if (errorString != null && tarif == null) {
			response.sendRedirect(request.getServletPath() + "/orderTarif");
			return;
		}

		// Если еще не вошел в систему (login).
		if (loginedUser == null) {
			// Redirect (Перенаправить) к странице login.
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		// Сохранить информацию в request attribute перед тем как forward к views.
		request.setAttribute("errorString", errorString2);
		request.setAttribute("serviceList", list2);
		// Сохранить информацию в request attribute перед тем как forward
		// (перенаправить).
		request.setAttribute("user", loginedUser);
		request.setAttribute("errorString", errorString);

		// Если пользователь уже вошел в систему (login), то forward (перенаправить) к
		// странице
		// /WEB-INF/views/userInfoView.jsp
 
		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/orderTarifView.jsp");
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
