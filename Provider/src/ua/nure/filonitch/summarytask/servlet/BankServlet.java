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
 * BANK SERVLET
 *
 */
@WebServlet(urlPatterns = { "/bank" })
public class BankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BankServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);

		int user_id = Integer.parseInt(request.getParameter("user_id"));

		float startbalance = 0;

		String errorString = null;

		try {
			startbalance = DBUtils.getBalance(conn, user_id);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		// Ошибки не имеются.
		// user не существует для пополнения.
		// Redirect sang trang danh sách sản phẩm.
		if (errorString != null && startbalance == 0) {
			response.sendRedirect(request.getServletPath() + "/userInfo");
			return;
		}

		// Сохранить информацию в request attribute перед тем как forward к views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("startbalance", startbalance);

		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/bank.jsp");
		dispatcher.forward(request, response);

	}

	@SuppressWarnings("unused")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);
		HttpSession session = request.getSession();
		UserAccount loginedUser = MyUtils.getLoginedUser(session);
		int user_id = loginedUser.getUser_id();
		float startbalance = 0;
		try {
			startbalance = DBUtils.getBalance(conn, user_id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		float wantedmoney = Float.parseFloat(request.getParameter("bbb"));
		System.out.println(wantedmoney);
		float newbalance = startbalance + wantedmoney;
		System.out.println(newbalance);
		String error = null;
		if (wantedmoney <= 0) {
			error = "Сумма не может быть отрицательной или равной 0!!";
			request.setAttribute("error", error);
			try {
				request.setAttribute("startbalance", DBUtils.getBalance(conn, user_id));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/bank.jsp");
			dispatcher.forward(request, response);
			return;

		}

		String errorString = null;

		try {
			DBUtils.setBalance(conn, loginedUser, newbalance);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Сохранить информацию в request attribute перед тем как forward к views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("newbalance", newbalance);
		float total = (float) session.getAttribute("total");
		if (newbalance > total) {
			loginedUser.setBlock_status(false);
			try {
				DBUtils.setBlockStatus(conn, loginedUser);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("block", loginedUser.isBlock_status());
		}
		// Если имеется ошибка, forward к странице edit.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/bank.jsp");
			dispatcher.forward(request, response);
		}
		// Если все хорошо.
		// Redirect к странице со списком продуктов.
		else {
			response.sendRedirect(request.getContextPath() + "/userInfo");
		}
	}

}