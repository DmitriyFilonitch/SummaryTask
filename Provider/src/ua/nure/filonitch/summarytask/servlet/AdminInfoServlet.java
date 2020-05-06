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

import ua.nure.filonitch.summarytask.beans.DolgList;
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
 *         ADMIN SERVLET
 *
 */
@WebServlet(urlPatterns = { "/adminInfo" })
public class AdminInfoServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(AdminInfoServlet.class);
	private static final long serialVersionUID = 1L;

	public AdminInfoServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LOGGER.debug("DOGET");

		HttpSession session = request.getSession();
		Connection conn = MyUtils.getStoredConnection(request);

		// Проверить, вошел ли пользователь в систему (login) или нет.
		UserAccount loginedUser = MyUtils.getLoginedUser(session);
		// Если еще не вошел в систему (login).
		if (loginedUser == null) {
			// Redirect (Перенаправить) к странице login.
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		if (loginedUser.getRole_id() != 1) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		// Сохранить информацию в request attribute перед тем как forward
		// (перенаправить).
		request.setAttribute("user", loginedUser);

		// Если пользователь уже вошел в систему (login), то forward (перенаправить) к
		// странице
		// /WEB-INF/views/userInfoView.jsp

		String operation = request.getParameter("op");
		if (operation == null) {
			operation = "1";
		}
		String opT = request.getParameter("opT");
		if (opT == null) {
			opT = "1";
		}
		String opU = request.getParameter("opU");
		if (opU == null) {
			opU = "2";
		}

		String opUT = request.getParameter("opUT");
		if (opUT == null) {
			opUT = "2";
		}

		String dolg = request.getParameter("dolg");
		if (dolg == null) {
			dolg = "2";
		}

		String errorString = null;
		List<Tarif> list = null;

		try {
			list = DBUtils.queryProduct(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		if (opT.equals("1")) {
			Manipulate.getTarifSortedByPriceHtL(list);
		} else if (opT.equals("2")) {
			Manipulate.getTarifSortedByPriceLtH(list);
		} else if (opT.equals("3")) {
			Manipulate.getTarifSortedByCodeAZ(list);
		} else if (opT.equals("4")) {
			Manipulate.getTarifSortedByCodeZA(list);
		} else if (opT.equals("5")) {
			Manipulate.getTarifSortedByServiceIDHtL(list);
		} else if (opT.equals("6")) {
			Manipulate.getTarifSortedByServiceIDLtH(list);
		} else if (opT.equals("7")) {
			Manipulate.getTarifSortedByNameAZ(list);
		} else if (opT.equals("8")) {
			Manipulate.getTarifSortedByNameZA(list);
		}

		// Сохранить информацию в request attribute перед тем как forward к views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("tarifList", list);

		String errorString1 = null;
		List<UserAccount> list1 = null;
		try {
			list1 = DBUtils.queryUsers(conn);
		} catch (SQLException e1) {
			e1.printStackTrace();
			errorString1 = e1.getMessage();
		}

		if (opU.equals("1")) {
			Manipulate.getUserSortedByIDHighToLow(list1);
		} else if (opU.equals("2")) {
			Manipulate.getUserSortedByIDLowToHigh(list1);
		} else if (opU.equals("3")) {
			Manipulate.getUsersSortedByUserNameAZ(list1);
		} else if (opU.equals("4")) {
			Manipulate.getUsersSortedByUserNameZA(list1);
		}

		// Сохранить информацию в request attribute перед тем как forward к views.
		request.setAttribute("errorString", errorString1);
		request.setAttribute("userList", list1);

		String errorString2 = null;
		List<Services> list2 = null;
		try {
			list2 = DBUtils.queryServices(conn);
		} catch (SQLException e2) {
			e2.printStackTrace();
			errorString2 = e2.getMessage();
		}

		if (operation.equals("1")) { // sort by name
			Manipulate.getServicesSortedByNameAZ(list2);
		} else if (operation.equals("2")) { // sort by name
			Manipulate.getServicesSortedByNameZA(list2);
		}

		// Сохранить информацию в request attribute перед тем как forward к views.
		request.setAttribute("errorString", errorString2);
		request.setAttribute("serviceList", list2);

		String errorString3 = null;
		List<UserTarif> list3 = null;
		try {
			list3 = DBUtils.queryUserTarif(conn);
		} catch (SQLException e3) {
			e3.printStackTrace();
			errorString3 = e3.getMessage();
		}

		if (opUT.equals("1")) {
			Manipulate.getUsersTarifsSortedByIDHighToLow(list3);
		} else if (opUT.equals("2")) {
			Manipulate.getUsersTarifsSortedByIDLowToHigh(list3);
		} else if (opUT.equals("3")) {
			Manipulate.getUsersTarifSortedByCodeAZ(list3);
		} else if (opUT.equals("4")) {
			Manipulate.getUsersTarifSortedByCodeZA(list3);
		}

		String errorString4 = null;

		try {
			List<DolgList> dolgList = DBUtils.getDolgList(conn);	
			request.setAttribute("dolgList", dolgList);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("errorString", errorString4);

		// Сохранить информацию в request attribute перед тем как forward к views.
		request.setAttribute("errorString3", errorString3);
		request.setAttribute("userTarifList", list3);

		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/adminInfoView.jsp");
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
