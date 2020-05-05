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

import ua.nure.filonitch.summarytask.beans.Tarif;
import ua.nure.filonitch.summarytask.utils.DBUtils;
import ua.nure.filonitch.summarytask.utils.MyUtils;

/**
 * @author D.Filonich
 *
 * EDIT TARIF SERVLET
 *
 */
@WebServlet(urlPatterns = { "/editTarif" })
public class EditTarifServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(EditTarifServlet.class);
	private static final long serialVersionUID = 1L;

	public EditTarifServlet() {
		super();
	}

	// Отобразить страницу редактирования продукта.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		LOGGER.debug("DOGET");
		
		Connection conn = MyUtils.getStoredConnection(request);

		String code = (String) request.getParameter("code");
		Tarif tarif = null;

		String errorString = null;

		try {
			tarif = DBUtils.findTarif(conn, code);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		// Ошибки не имеются.
		// Продукт не существует для редактирования (edit).
		// Redirect sang trang danh sách sản phẩm.
		if (errorString != null && tarif == null) {
			response.sendRedirect(request.getServletPath() + "/adminInfo");
			return;
		}

		// Сохранить информацию в request attribute перед тем как forward к views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("tarif", tarif);

		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editTarifView.jsp");
		dispatcher.forward(request, response);

	}

	// После того, как пользователь отредактировал информацию продукта и нажал на
	// Submit.
	// Данный метод будет выполнен.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.debug("DOPOST");
		
		Connection conn = MyUtils.getStoredConnection(request);

		String code = (String) request.getParameter("code");
		String name = (String) request.getParameter("name");
		String priceStr = (String) request.getParameter("price");
		String description = (String) request.getParameter("description");
		int service_id = Integer.parseInt(request.getParameter("service_id"));
		float price = 0;
		try {
			price = Float.parseFloat(priceStr);
		} catch (Exception e) {
		}
		Tarif tarif = new Tarif(code, name, price, description, service_id);

		String errorString = null;

		try {
			DBUtils.updateTarif(conn, tarif);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		// Сохранить информацию в request attribute перед тем как forward к views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("tarif", tarif);

		// Если имеется ошибка, forward к странице edit.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/editTarifView.jsp");
			dispatcher.forward(request, response);
		}
		// Если все хорошо.
		// Redirect к странице со списком продуктов.
		else {
			response.sendRedirect(request.getContextPath() + "/adminInfo");
		}
	}

}