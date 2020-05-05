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

import ua.nure.filonitch.summarytask.beans.Tarif;
import ua.nure.filonitch.summarytask.utils.DBUtils;
import ua.nure.filonitch.summarytask.utils.Manipulate;
import ua.nure.filonitch.summarytask.utils.MyUtils;

/**
 * @author D.Filonich
 *
 * SHOW LIST OF TARIFFS FOR CHOOSING
 *
 */
@WebServlet(urlPatterns = { "/showTarif" })
public class ShowTarifServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(ShowTarifServlet.class);
	private static final long serialVersionUID = 1L;

	public ShowTarifServlet() {
		super();
	}

	// Отобразить страницу редактирования продукта.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		LOGGER.debug("DOGET");
		
		Connection conn = MyUtils.getStoredConnection(request);
		HttpSession session = request.getSession();
		String opT = request.getParameter("opT");
		int service_id = 0;
		if (opT == null) {
			opT = "1";
		}

		if ((request.getParameter("service_id")) != null) {
			service_id = Integer.parseInt(request.getParameter("service_id"));
		} else {
			service_id = (int) session.getAttribute("service_id");
		}

		List<Tarif> tarif = null;

		String errorString = null;

		try {
			tarif = DBUtils.getTarif(conn, service_id);
			session.setAttribute("service_id", service_id);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		if (opT.equals("1")) {
			Manipulate.getTarifSortedByPriceHtL(tarif);
		} else if (opT.equals("2")) { 
			Manipulate.getTarifSortedByPriceLtH(tarif);
		} else if (opT.equals("3")) {
			Manipulate.getTarifSortedByCodeAZ(tarif);
		} else if (opT.equals("4")) { 
			Manipulate.getTarifSortedByCodeZA(tarif);
		} else if (opT.equals("5")) { 
			Manipulate.getTarifSortedByServiceIDHtL(tarif);
		} else if (opT.equals("6")) { 
			Manipulate.getTarifSortedByServiceIDLtH(tarif);
		}else if (opT.equals("7")) { 
			Manipulate.getTarifSortedByNameAZ(tarif);
		} else if (opT.equals("8")) { 
			Manipulate.getTarifSortedByNameZA(tarif);
		}
		// Ошибки не имеются.
		// Продукт не существует для редактирования (edit).
		// Redirect sang trang danh sách sản phẩm.
		if (errorString != null && tarif == null) {
			response.sendRedirect(request.getServletPath() + "/orderTarif");
			return;
		}

		// Сохранить информацию в request attribute перед тем как forward к views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("tarifList", tarif);

		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/showTarifView.jsp");
		dispatcher.forward(request, response);

	}

	// После того, как пользователь отредактировал информацию продукта и нажал на
	// Submit.
	// Данный метод будет выполнен.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}