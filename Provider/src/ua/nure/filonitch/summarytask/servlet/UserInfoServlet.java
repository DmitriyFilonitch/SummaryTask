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

import ua.nure.filonitch.summarytask.beans.Services;
import ua.nure.filonitch.summarytask.beans.Tarif;
import ua.nure.filonitch.summarytask.beans.UserAccount;
import ua.nure.filonitch.summarytask.beans.UserTarif;
import ua.nure.filonitch.summarytask.utils.DBUtils;
import ua.nure.filonitch.summarytask.utils.Manipulate;
import ua.nure.filonitch.summarytask.utils.MyUtils;

import org.apache.log4j.Logger;

/**
 * @author D.Filonich
 *
 * USER PERSONAL CABINET SERVLET
 *
 */
@WebServlet(urlPatterns = { "/userInfo" })
public class UserInfoServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(UserInfoServlet.class);
	private static final long serialVersionUID = 1L;

	public UserInfoServlet() {
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
		String ok = "Contract has been successfully downloaded!";
		if (loginedUser == null) {
			// Redirect (Перенаправить) к странице login.
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		int id_user = loginedUser.getUser_id();
		String userName = loginedUser.getUserName();
		List<Tarif> tarif = null;
		List<Services> service = null;
		UserTarif ut = null;
		int status = 0;
		String errorString = null;
		float total = 0;
		float balance = 0;
		boolean block = false;
		UserAccount user1 = null;
		UserAccount user2 = null;

		try {
			tarif = DBUtils.usersTarifs(conn, id_user);
			total = DBUtils.getFullPrice(conn, id_user);
			status = DBUtils.getPaymentStatus(conn, id_user);
			balance = DBUtils.getBalance(conn, id_user);
			user1 = DBUtils.findUser(conn, userName);
			block = user1.isBlock_status();

		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		if (balance < total) {
			user1.setBlock_status(true);
			try {
				DBUtils.setBlockStatus(conn, user1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("block", user1.isBlock_status());
		} else {
			user1.setBlock_status(false);
			try {
				DBUtils.setBlockStatus(conn, user1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("block", user1.isBlock_status());
		}

		if (request.getParameter("downloadTAriff") != null) {

			Manipulate.writeFile(loginedUser.getUserName(), Manipulate.createFileForUser(loginedUser, tarif, total));
			request.setAttribute("ok", ok);
		}

		if (errorString != null && tarif == null) {
			response.sendRedirect(request.getServletPath() + "/userInfo");
			return;
		}

		// Если еще не вошел в систему (login).
		if (loginedUser == null) {
			// Redirect (Перенаправить) к странице login.
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		// Сохранить информацию в request attribute перед тем как forward
		// (перенаправить).
		request.setAttribute("user", loginedUser);
		request.setAttribute("errorString", errorString);
		request.setAttribute("tarif", tarif);
		request.setAttribute("service", service);
		request.setAttribute("total", total);
		request.setAttribute("status", status);
		session.setAttribute("total", total);
		session.setAttribute("user1", user1);
		request.setAttribute("balance", balance);
		request.setAttribute("block", block);

		// Если пользователь уже вошел в систему (login), то forward (перенаправить) к
		// странице
		// /WEB-INF/views/userInfoView.jsp

		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/userInfoView.jsp");
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
