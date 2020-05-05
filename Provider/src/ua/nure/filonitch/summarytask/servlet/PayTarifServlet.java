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
import javax.servlet.http.HttpSession;

import ua.nure.filonitch.summarytask.beans.UserAccount;
import ua.nure.filonitch.summarytask.utils.DBUtils;
import ua.nure.filonitch.summarytask.utils.MyUtils;

/**
 * @author D.Filonich
 *
 */
@WebServlet(urlPatterns = { "/payTarif" })
public class PayTarifServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PayTarifServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);
		HttpSession session = request.getSession();

		// Проверить, вошел ли пользователь в систему (login) или нет.
		UserAccount user = MyUtils.getLoginedUser(session);
		int id_user = user.getUser_id();
		float total = (float) session.getAttribute("total");
		float balance = 0;
		try {
			balance = DBUtils.getBalance(conn, id_user);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String errorString = null;
		String message = null;
		UserAccount user1 = (UserAccount) session.getAttribute("user1");

		if (balance >= total) {

			user1.setBlock_status(false);
			try {
				DBUtils.setBlockStatus(conn, user1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("block", user1.isBlock_status());

			message = "OK";
			float newBalance = balance - (float) session.getAttribute("total");
			user.setBalance(newBalance);
			System.out.println(newBalance);

			try {
				DBUtils.payTarif(conn, user);
				DBUtils.setPaymentStatus(conn, id_user);
				System.out.println(message);
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
				response.sendRedirect(request.getContextPath() + "/userInfo");
			}
		} else {
			message = "BALANCE IS TOO LOW!!!";
			session.setAttribute("message", message);
			System.out.println(message);
			response.sendRedirect(request.getContextPath() + "/userInfo");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}